package com.wisdom.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.SQLServerDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseDialectAdapter {
	
	private static Logger log = LoggerFactory.getLogger(DatabaseDialectAdapter.class);
	
	/**
	 * Adapt "default.sql" to every supported database
	 */
	public static String dialectAdapter(InputStream is, String dialect) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		String line;
		
		try {
			br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			
			if (Oracle10gDialect.class.getCanonicalName().equals(dialect)) {
				log.info("Generation SQL for Oracle10gDialect...");
				
				while ((line = br.readLine()) != null) {
					sb.append(oracleAdapter(line)).append("\n");
				}
			} else if (SQLServerDialect.class.getCanonicalName().equals(dialect)) {
				log.info("Generation SQL for SQLServerDialect...");
				
				while ((line = br.readLine()) != null) {
					sb.append(sqlServerAdapter(line)).append("\n");
				}
			} else {
				log.info("Generation SQL for GeneralDialect...");
				
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(is);
		}
		
		return sb.toString().trim();
	}
	
	/**
	 * Oracle special stuff
	 */
	private static String oracleAdapter(String line) {
		log.debug("oracleAdapter({})", line);
		String str;
		
		if (line.startsWith("INSERT INTO DSM_FORUM")) {
			str = line.replaceAll("NOW\\(\\)", "SYSDATE");
		} else if (line.startsWith("INSERT INTO DSM_WIKI_PAGE")) {
			str = line.replaceAll("NOW\\(\\)", "SYSDATE");
		} else {
			str = line;
		}
		
		log.debug("oracleAdapter: {}", str);
		return str;
	}
	
	/**
	 * SQL Server special stuff
	 */
	private static String sqlServerAdapter(String line) {
		log.debug("sqlServerAdapter({})", line);
		StringBuilder sb = new StringBuilder();
		
		if (line.startsWith("INSERT INTO DSM_PROFILE")) {
			sb.append("SET IDENTITY_INSERT DSM_PROFILE ON;").append("\n");
			sb.append(line).append("\n");
			sb.append("SET IDENTITY_INSERT DSM_PROFILE OFF;");
		} else if (line.startsWith("INSERT INTO MIME_TYPE")) {
			sb.append("SET IDENTITY_INSERT MIME_TYPE ON;").append("\n");
			sb.append(line).append("\n");
			sb.append("SET IDENTITY_INSERT MIME_TYPE OFF;");
		} else if (line.startsWith("INSERT INTO DSM_FORUM")) {
			sb.append("SET IDENTITY_INSERT DSM_FORUM ON;").append("\n");
			sb.append(line.replaceAll("NOW\\(\\)", "GETDATE()")).append("\n");
			sb.append("SET IDENTITY_INSERT DSM_FORUM OFF;");
		} else if (line.startsWith("INSERT INTO DSM_WIKI_PAGE")) {
			sb.append("SET IDENTITY_INSERT DSM_WIKI_PAGE ON;").append("\n");
			sb.append(line.replaceAll("NOW\\(\\)", "GETDATE()")).append("\n");
			sb.append("SET IDENTITY_INSERT DSM_WIKI_PAGE OFF;");
		} else {
			sb.append(line);
		}
		
		log.debug("sqlServerAdapter: {}", sb.toString());
		return sb.toString();
	}

}
