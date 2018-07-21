package com.wisdom.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisdom.dao.ConfigDAO;
import com.wisdom.util.EnvironmentDetector;

public class Config {
	private static Logger log = LoggerFactory.getLogger(Config.class);
	public static TreeMap<String, String> values = new TreeMap<String, String>();
	
	// Server specific configuration
	public static final String HOME_DIR = EnvironmentDetector.getServerHomeDir();
	public static final String TMP_DIR = EnvironmentDetector.getTempDir();
	public static final String LOG_DIR = EnvironmentDetector.getServerLogDir();
	public static final String NULL_DEVICE = EnvironmentDetector.getNullDevice();
	public static final String JNDI_BASE = EnvironmentDetector.getServerJndiBase();
	public static final boolean IN_SERVER = EnvironmentDetector.inServer();
	public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	
	public static final String SYSTEM_DEPARTMENT_NAME = "XXXX科技股份有限公司";
	
	public static final String DEFAULT_CONTEXT = "lddsm";
	public static final String FILESTORE_DB = "filestore";
	
	public static String HIBERNATE_DATASOURCE = JNDI_BASE + "jdbc/" + DEFAULT_CONTEXT + "DS";
	public static String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
	
	public static String SYSTEM_PACK_PROPTIES_BASE = ""; //配置文件路径
	public static final String SYSTEM_PACK_PROPTIES_FILE = "pack.properties"; //配置文件名
	
	public static final String PROPERTY_SYSTEM_PACKING_TYPE = "system.packing.type"; //打包类型
	public static final String PACKING_TYPE_GOV = "free";
	public static String SYSTEM_PACKING_TYPE = "gov"; //系统打包类型（政府or企业）：图标与命名有区别
	public static boolean VERSION_STABLE = false; //是否稳定版本
	
	public static String LDDSM_DOCUMENT_CONVETER_TIMES = "18:00:00-06:00:00";
	public static final String PROPERTY_LDDSM_DOCUMENT_CONVETER_TIMES = "lddsm.document.conveter.times";

	public static String DEFAULT_USER_ROLE = "2";
	public static final String PROPERTY_DEFAULT_USER_ROLE = "default.user.role";
	
	
	/**
	 * Load lddsm configuration from lddsm.cfg
	 */
	public static Properties load(ServletContext sc) {
		Properties config = new Properties();
		
		FileInputStream fis = null;
		try {
			Properties packConfig = new Properties();
			SYSTEM_PACK_PROPTIES_BASE = sc.getRealPath("WEB-INF/classes/pack/");
			fis = new FileInputStream(SYSTEM_PACK_PROPTIES_BASE + File.separator + SYSTEM_PACK_PROPTIES_FILE);
			packConfig.load(fis);
			
			SYSTEM_PACKING_TYPE = packConfig.getProperty(PROPERTY_SYSTEM_PACKING_TYPE);
			VERSION_STABLE = Boolean.valueOf(packConfig.getProperty("stable"));
			
		} catch(Exception e){
			SYSTEM_PACKING_TYPE = PACKING_TYPE_GOV;
			log.warn("** IOError reading {} **", SYSTEM_PACK_PROPTIES_BASE + SYSTEM_PACK_PROPTIES_FILE);
		}finally{
			IOUtils.closeQuietly(fis);
		}
		
		// Hibernate
//		HIBERNATE_DIALECT = config.getProperty(PROPERTY_HIBERNATE_DIALECT, HIBERNATE_DIALECT);
//		values.put(PROPERTY_HIBERNATE_DIALECT, HIBERNATE_DIALECT);
//		HIBERNATE_DATASOURCE = config.getProperty(PROPERTY_HIBERNATE_DATASOURCE, JNDI_BASE + "jdbc/" + (CONTEXT.isEmpty() ? DEFAULT_CONTEXT : CONTEXT) + "DS");
//		values.put(PROPERTY_HIBERNATE_DATASOURCE, HIBERNATE_DATASOURCE);
//		HIBERNATE_DATASOURCE_FS = config.getProperty(PROPERTY_HIBERNATE_DATASOURCE_FS, JNDI_BASE + "jdbc/" +  FILESTORE_DB );
//		values.put(PROPERTY_HIBERNATE_DATASOURCE_FS, HIBERNATE_DATASOURCE_FS);
//		HIBERNATE_HBM2DDL = config.getProperty(PROPERTY_HIBERNATE_HBM2DDL, HIBERNATE_HBM2DDL);
//		values.put(PROPERTY_HIBERNATE_HBM2DDL, HIBERNATE_HBM2DDL);
//		HIBERNATE_SHOW_SQL = config.getProperty(PROPERTY_HIBERNATE_SHOW_SQL, HIBERNATE_SHOW_SQL);
//		values.put(PROPERTY_HIBERNATE_SHOW_SQL, HIBERNATE_SHOW_SQL);
//		HIBERNATE_STATISTICS = config.getProperty(PROPERTY_HIBERNATE_STATISTICS, HIBERNATE_STATISTICS);
//		values.put(PROPERTY_HIBERNATE_STATISTICS, HIBERNATE_STATISTICS);
//		HIBERNATE_SEARCH_ANALYZER = config.getProperty(PROPERTY_HIBERNATE_SEARCH_ANALYZER, HIBERNATE_SEARCH_ANALYZER);
//		values.put(PROPERTY_HIBERNATE_SEARCH_ANALYZER, HIBERNATE_SEARCH_ANALYZER);
//		HIBERNATE_CREATE_AUTOFIX = config.getProperty(PROPERTY_HIBERNATE_CREATE_AUTOFIX, HIBERNATE_CREATE_AUTOFIX);
//		values.put(PROPERTY_HIBERNATE_CREATE_AUTOFIX, HIBERNATE_CREATE_AUTOFIX);
//		HIBERNATE_SEARCH_INDEX_EXCLUSIVE = config.getProperty(PROPERTY_HIBERNATE_SEARCH_INDEX_EXCLUSIVE, HIBERNATE_SEARCH_INDEX_EXCLUSIVE);
//		values.put(PROPERTY_HIBERNATE_SEARCH_INDEX_EXCLUSIVE, HIBERNATE_SEARCH_INDEX_EXCLUSIVE);

		
		return config;
	}
	
	public static void reload(ServletContext sc, Properties cfg) {
		
		try {
			DEFAULT_USER_ROLE = ConfigDAO.getString(PROPERTY_DEFAULT_USER_ROLE, cfg.getProperty(PROPERTY_DEFAULT_USER_ROLE ,DEFAULT_USER_ROLE));
			values.put(PROPERTY_DEFAULT_USER_ROLE, DEFAULT_USER_ROLE);
			
			LDDSM_DOCUMENT_CONVETER_TIMES = ConfigDAO.getString(PROPERTY_LDDSM_DOCUMENT_CONVETER_TIMES, LDDSM_DOCUMENT_CONVETER_TIMES);
			values.put(PROPERTY_LDDSM_DOCUMENT_CONVETER_TIMES, String.valueOf(LDDSM_DOCUMENT_CONVETER_TIMES));
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
}
