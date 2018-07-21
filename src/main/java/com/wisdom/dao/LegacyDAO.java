package com.wisdom.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisdom.core.Config;
import com.wisdom.exception.DatabaseException;
import com.wisdom.util.FormatUtil;
import com.wisdom.util.HibernateUtil;


public class LegacyDAO {
	private static Logger log = LoggerFactory.getLogger(LegacyDAO.class);
	private static DataSource ds = null;
	
	/**
	 * Return JDBC Connection
	 */
	public static Connection getConnection() {
		try {
			if (ds == null) {
				log.info("Looking for {} DataSource...", Config.HIBERNATE_DATASOURCE);
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup(Config.HIBERNATE_DATASOURCE);
				ctx.close();
			}
			
			return ds.getConnection();
		} catch (NamingException e) {
			log.error("DataSource not found: {}", e.getMessage());
			throw new RuntimeException(e);
		} catch (SQLException e) {
			log.error("Can't get connection from DataSource", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Execute sentence
	 */
	public static void execute(Connection con, String sql) throws IOException, SQLException {
		Statement stmt = con.createStatement();
		
		try {
			log.info("execute: {}", sql);
			stmt.execute(sql);
		} finally {
			close(stmt);
		}
	}
	
	/**
	 * Execute script
	 */
	public static List<HashMap<String, String>> executeScript(Connection con, Reader file) throws 
			IOException, SQLException {
		List<HashMap<String, String>> errors = new ArrayList<HashMap<String, String>>();
		BufferedReader br = new BufferedReader(file);
		Statement stmt = con.createStatement();
		String sql = null;
		int lineNo = 0;
		
		StringBuffer sbsql = new StringBuffer();
		
		try {
			while ((sql = br.readLine()) != null) {
				String trimmedSql = sql.trim();
				lineNo++;
				if(trimmedSql.length() > 0){
					if(trimmedSql.toUpperCase().startsWith("CREATE FUNCTION")||trimmedSql.toUpperCase().startsWith("CREATE TRIGGER")){
						sbsql.append(sql);
						continue;
					}else if(trimmedSql.toUpperCase().startsWith("END;")){
						sbsql.append(sql);
						stmt.execute(sbsql.toString());
						sbsql = new StringBuffer();
						continue;
					}else if(sbsql.length()>0){
						sbsql.append(" "+sql+" ");
						continue;
					}
				}
				if (trimmedSql.length() > 0 && !trimmedSql.startsWith("--")) {
					try {
						if (trimmedSql.endsWith(";")) {
							trimmedSql = trimmedSql.substring(0, trimmedSql.length() - 1);
						}
						
						stmt.execute(trimmedSql);
					} catch (SQLException e) {
						HashMap<String, String> error = new HashMap<String, String>();
						error.put("ln", Integer.toString(lineNo));
						error.put("sql", trimmedSql);
						error.put("msg", e.getMessage());
						errors.add(error);
					}
				}
			}
		} finally {
			close(stmt);
		}
		
		return errors;
	}
	
	/**
	 * Execute script
	 */
	public static List<HashMap<String, String>> executeScripts(Connection con, BufferedReader br) throws 
			IOException, SQLException {
		List<HashMap<String, String>> errors = new ArrayList<HashMap<String, String>>();
		Statement stmt = con.createStatement();
		String sql = null;
		String sqlTotal = "";
		int lineNo = 0;
		
		try {
			while ((sql = br.readLine()) != null) {
				String trimmedSql = sql.trim();
				lineNo++;
				
				if (trimmedSql.length() > 0 && !trimmedSql.startsWith("--")) {
					try {
						if (trimmedSql.endsWith(";")) {
							trimmedSql = trimmedSql.substring(0, trimmedSql.length() - 1);
							sqlTotal = sqlTotal + trimmedSql;
							stmt.execute(sqlTotal);
							
							sqlTotal = "";
						}else{
							sqlTotal = sqlTotal + trimmedSql;
						}
					} catch (SQLException e) {
						HashMap<String, String> error = new HashMap<String, String>();
						error.put("ln", Integer.toString(lineNo));
						error.put("sql", trimmedSql);
						error.put("msg", e.getMessage());
						errors.add(error);
					}
				}
			}
		} finally {
			close(stmt);
		}
		
		return errors;
	}
	
	/**
	 * Convenient method to close connections
	 */
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				log.warn("Error closing connection: " + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * Convenient method to close resultset
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.warn("Error closing resultset: " + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * Convenient method to close statements
	 */
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.warn("Error closing statement: " + e.getMessage(), e);
			}
		}	
	}

	/**
	 * Execute query
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> executeQuery(String query) throws DatabaseException {
		log.debug("executeValueQuery({})", query);
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(query);
			List<Object> ret = q.list();
			HibernateUtil.commit(tx);
			log.debug("executeValueQuery: {}", ret);
			return ret;
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	/**
	 * Execute query
	 */
	public static Object executeQueryUnique(String query) throws DatabaseException {
		log.debug("executeQueryUnique({})", query);
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(query);
			Object ret = q.uniqueResult();
			HibernateUtil.commit(tx);
			log.debug("executeQueryUnique: {}", ret);
			return ret;
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	/**
	 * Execute HQL query
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> executeHQL(final String query) throws DatabaseException {
		log.debug("executeHQL({})", query);
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(query);
			List<Object> ret = q.list();
			HibernateUtil.commit(tx);
			log.debug("executeHQL: {}", ret);
			return ret;
		} catch (Exception e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	/**
	 * * Execute SQL query
	 */
	public static List<List<String>> executeSQL(final String query) throws DatabaseException {
		ResultWorker worker = new ResultWorker();
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			worker.setSql(query);
			session.doWork(worker);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}

		return worker.getResults();
	}

	/**
	 /**
	 * Utility inner class
	 */
	public static class ResultWorker implements Work {
		private List<List<String>> results = new ArrayList<List<String>>();
		private String sql = null;

		public void setSql(String sql) {
			this.sql = sql;
		}

		public List<List<String>> getResults() {
			return results;
		}

		@Override
		public void execute(Connection con) throws SQLException {
			Statement st = null;
			ResultSet rs = null;

			if (sql != null && !sql.isEmpty()) {
				try {
					st = con.createStatement();

					if (sql.toUpperCase().startsWith("SELECT") || sql.toUpperCase().startsWith("DESCRIBE")) {
						rs = st.executeQuery(sql);
						ResultSetMetaData md = rs.getMetaData();

						while (rs.next()) {
							List<String> row = new ArrayList<String>();

							for (int j = 1; j < md.getColumnCount() + 1; j++) {
								if (Types.BLOB == md.getColumnType(j)) {
									row.add("BLOB");
								} else {
									if (rs.getString(j) != null) {
										row.add(FormatUtil.escapeHtml(rs.getString(j)));
									} else {
										row.add(rs.getString(j));
									}
								}
							}

							results.add(row);
						}
					} else {
						st.executeUpdate(sql);
					}
				} finally {
					close(rs);
					close(st);
				}
			}
		}
	}
}
