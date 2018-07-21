package com.wisdom.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisdom.core.Config;
import com.wisdom.dao.LegacyDAO;


public class HibernateUtil {
	
	private static Logger log = LoggerFactory.getLogger(HibernateUtil.class);
	
	private static SessionFactory sessionFactory;
	private static SessionFactory sessionFactoryFS;

	public static String HBM2DDL_CREATE = "create";
	public static String HBM2DDL_UPDATE = "update";
	public static String HBM2DDL_NONE = "none";
	
	private HibernateUtil() {
		
	}
	
	public static SessionFactory getSessionFactory() {
		
		try {
			
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
		} catch (Exception e) {
			log.info("get sessionFactory error", e.getMessage());
		}
		
		return sessionFactory;
	}
	
	public static Configuration getConfiguration() {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		return cfg;
	}
	
	public static SessionFactory getSessionFactory(String hbm2ddl) {
		if(sessionFactory==null){
			Configuration cfg = getConfiguration();
			cfg.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
			sessionFactory = getSessionFactory(cfg);
			initDB(hbm2ddl);
		}
		return sessionFactory;
	}
	
	public static SessionFactory getSessionFactoryFS(){
		if(sessionFactoryFS==null){
			Configuration cfg = getConfigurationFS();
			sessionFactoryFS = getSessionFactory(cfg);
			initFSDB(cfg.getProperty("hibernate.hbm2ddl.auto"));
		}
		return sessionFactoryFS;
	}
	
	private static SessionFactory getSessionFactory(Configuration cfg) {
		SessionFactory sessionFactory = null;
		try {
			String hbm2ddl = cfg.getProperty("hibernate.hbm2ddl.auto");

			// Show configuration
			log.info("Hibernate 'hibernate.dialect' = {}", cfg.getProperty("hibernate.dialect"));
			log.info("Hibernate 'hibernate.connection.datasource' = {}", cfg.getProperty("hibernate.connection.datasource"));
			log.info("Hibernate 'hibernate.hbm2ddl.auto' = {}", cfg.getProperty("hibernate.hbm2ddl.auto"));
			log.info("Hibernate 'hibernate.show_sql' = {}", cfg.getProperty("hibernate.show_sql"));
			log.info("Hibernate 'hibernate.generate_statistics' = {}", cfg.getProperty("hibernate.generate_statistics"));
			log.info("Hibernate 'hibernate.search.default.directory_provider' = {}",
					cfg.getProperty("hibernate.search.default.directory_provider"));
			log.info("Hibernate 'hibernate.search.default.indexBase' = {}", cfg.getProperty("hibernate.search.default.indexBase"));

			if (HBM2DDL_CREATE.equals(hbm2ddl)) {
				// In case of database schema creation, also clean filesystem data.
				// This means, conversion cache, file datastore and Lucene indexes.
//				log.info("Cleaning filesystem data from: {}", Config.REPOSITORY_HOME);
//				FileUtils.deleteQuietly(new File(Config.REPOSITORY_HOME));
			}

			// Create database schema, if needed
			sessionFactory = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		}
		return sessionFactory;
	}
	
	/**
	 * Construct annotation configuration
	 */
	private static Configuration getConfigurationFS() {
		Configuration cfg = new Configuration().configure("/hibernate.fscfg.xml");
		String url = cfg.getProperty("hibernate.connection.url_fs");
		String username = cfg.getProperty("hibernate.connection.username_fs");
		String password = cfg.getProperty("hibernate.connection.password_fs");
		cfg.setProperty("hibernate.connection.url", url);
		cfg.setProperty("hibernate.connection.username", username);
		cfg.setProperty("hibernate.connection.password", password);
		return cfg;
	}
	
	public static void initDB(String hbm2ddl){
		try {
			long userNum = 1L;
			try {
				//用户数为0时， 自动加载sql文件
//				userNum = AuthDAO.getUserCount();
				if(userNum==0){
					hbm2ddl = HBM2DDL_CREATE;
				}
			} catch (Exception e) {
				log.error("initDB countAllUsers error",e);
			}
			if (HBM2DDL_CREATE.equals(hbm2ddl)) {
//				log.info("Executing specific import for: {}", Config.HIBERNATE_DIALECT);
//				List<String> initFileNameList = ConfigUtils.getResources("sqlbak/init");
//				Collections.sort(initFileNameList);//add by wuxs 20160819 为保证在linux下按数字顺序执行文件，再进行一次升序排序
//				for (int i=0; i<initFileNameList.size(); i++) {
//					InputStream is = ConfigUtils.getResourceAsStream("sqlbak/init/"+initFileNameList.get(i));
//					String adapted = DatabaseDialectAdapter.dialectAdapter(is, Config.HIBERNATE_DIALECT);
//					executeSentences(new StringReader(adapted));
//					IOUtils.closeQuietly(is);
//				}
			}
			if (HBM2DDL_UPDATE.equals(hbm2ddl)) {
//				try {
//					InputStream is = ConfigUtils.getResourceAsStream("sqlbak/update/update.sql");
//					String adapted = DatabaseDialectAdapter.dialectAdapter(is, Config.HIBERNATE_DIALECT);
//					executeSentences(new StringReader(adapted));
//					IOUtils.closeQuietly(is);
//				} catch (Exception e) {
//					log.error("import update.sql error:",e);
//				}
			}
			if (HBM2DDL_CREATE.equals(hbm2ddl) || HBM2DDL_UPDATE.equals(hbm2ddl)) {
				// Create or update translations
//				for (String res : ConfigUtils.getResources("sqlbak/i18n")) {
//					String oldTrans = null;
//					String langId = null;
//
//					InputStream isLang = ConfigUtils.getResourceAsStream("sqlbak/i18n/" + res);
//					log.info("Importing translation: {}", res);
//					executeSentences(new InputStreamReader(isLang,"utf-8"));
//					IOUtils.closeQuietly(isLang);
//				}

				// Replace "create" or "update" by "none" to prevent repository reset on restart
//				if (Boolean.parseBoolean(Config.HIBERNATE_CREATE_AUTOFIX)) {
//					log.info("Executing Hibernate create autofix");
//					//hibernateCreateAutofix(Config.HOME_DIR + "/" + Config.lddsm_CONFIG);
//				} else {
//					log.info("Hibernate create autofix not executed because of {}={}", Config.PROPERTY_HIBERNATE_CREATE_AUTOFIX, Config.HIBERNATE_CREATE_AUTOFIX);
//				}
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		} 
	}
	
	private static void initFSDB(String hbm2ddl){
		try {
			boolean tableExist = true;
			try {
				tableExist = isTableExist(sessionFactoryFS, "filestore_00");
				if(!tableExist){
					hbm2ddl = HBM2DDL_CREATE;
				}
			} catch (Exception e) {
				log.error("table FILE_STORE exist error",e);
			}
			if (HBM2DDL_CREATE.equals(hbm2ddl)) {
				log.info("Executing specific import for: {}", Config.HIBERNATE_DIALECT);
				log.info("start import : {}", "createFS.sql");
				BufferedReaderExt br = null;
				InputStream is = null;
				String[] c=new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
				for (int i = 0; i < c.length; i++) {
					for (int j = 0; j < c.length; j++) {
						String cc = c[i]+c[j];
//						is = ConfigUtils.getResourceAsStream("sqlbak/createFS.sql");
//						br = new BufferedReaderExt(new InputStreamReader(is,"utf-8"));
						br.setFrom("###");
						br.setTo(cc);
						log.info("import : FILESTORE_{}", cc);
						executeScripts(sessionFactoryFS,br);
					}
				}
				log.info("end import : {}", "createFS.sql");
				IOUtils.closeQuietly(is);
			}
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		} 
	}
	
	/**
	 * Load specific database import
	 */
	public static void executeSentences(final Reader rd) {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.doWork(new Work() {
				@Override
				public void execute(Connection con) throws SQLException {
					try {
						for (HashMap<String, String> error : LegacyDAO.executeScript(con, rd)) {
							log.error("Error during script execution at line {}: {} [ {} ]", new Object[] { error.get("ln"), error.get("msg"), error.get("sqlbak") });
						}
					} catch (IOException e) {
						log.error(e.getMessage(), e);
					} finally {
						IOUtils.closeQuietly(rd);
					}
				}
			});

			commit(tx);
		} catch (Exception e) {
			rollback(tx);
			log.error(e.getMessage(), e);
		}
	}
	
	public static boolean isTableExist(SessionFactory sessionFactory,String tableName) throws HibernateException {
		log.debug("isTableExist({})", tableName);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("select database()");
			String dbName = String.valueOf(query.uniqueResult());
			query = session.createSQLQuery("SELECT * FROM information_schema.TABLES WHERE table_schema = :dbName and table_name =:tableName");
			query.setString("dbName", dbName);
			query.setString("tableName", tableName);
			return query.list().size()>0;
		} catch(HibernateException e){
			log.error("HibernateUtil-isTableExist", e);
			throw e;
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	public static void executeScripts(SessionFactory sessionFactory ,final BufferedReader br) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			session.doWork(new Work() {
				@Override
				public void execute(Connection con) throws SQLException {
					try {
						for (HashMap<String, String> error : LegacyDAO.executeScripts(con, br)) {
							log.error("Error during script execution at line {}: {} [ {} ]",
									new Object[] { error.get("ln"), error.get("msg"), error.get("sqlbak") });
						}
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					} finally {

					}
				}
			});

			commit(tx);
		} catch (Exception e) {
			rollback(tx);
			log.error(e.getMessage(), e);
		}
	}
	
	public static void commit(Transaction tx) {
		if(tx != null)
		if (tx != null) {
			tx.commit();
		}
	}
	
	public static void close(Session session) {
		if (session != null || session.isOpen()) {
			session.close();
		}
	}
	
	/**
	 * Rollback transaction
	 */
	public static void rollback(Transaction tx) {
		if (tx != null) {
			tx.rollback();
		}
	}
	

}
