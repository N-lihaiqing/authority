  /**
	* Copyright (c) 2015,厦门天锐科技有限公司
	* All rights reserved.
	*
	* 文件名称：Config.java
	* 摘 要：系统全局配置文件
	*
	* 当前版本：
	* 作 者：
	* 完成日期：
    */

package com.wisdom.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	private static Logger log = LoggerFactory.getLogger(Config.class);
	public static TreeMap<String, String> values = new TreeMap<String, String>();
	/**云文档系统标识符*/
	public static final String PRO_LDDSM = "lddsm";
	/**自动备份系统标识符*/
	public static final String PRO_LDFBS = "ldfbs";
	/**个人文件保险柜*/
	public static final String PRO_LDPSB = "ldpsb";
	
	public static String currentPro = "";
	/**当前显示系统*/
	public static String PRO_DEFAULT = PRO_LDDSM;
	public static String PRO_CURRENT = "product.current.show";
	/**是否开启切换产品*/
	public static boolean IS_CHANGE_PRO = true;
	
	public static final String DEFAULT_CONTEXT = "lddsm";
	public static final String FILESTORE_DB = "filestore";
	
	//用于记录管理员是否点击了试用按钮，点击一次就不再在登录时弹出注册提醒框
	public static boolean TRIAL_CONFIRM = false;
	
	
	public static long DEV_ID = 120;
	
	// Scripting
	public static final String START_SCRIPT = "start.bsh";
	public static final String STOP_SCRIPT = "stop.bsh";
	public static final String START_JAR = "start.jar";
	public static final String STOP_JAR = "stop.jar";
	public static final String START_SQL = "start.sql";
	public static final String STOP_SQL = "stop.sql";
	
	// Configuration files
	public static final String lddsm_CONFIG = "lddsm.cfg";
	public static final String NODE_DEFINITIONS = "CustomNodes.cnd";
	public static String CONTEXT;
	public static String INSTANCE_HOME;
	public static String INSTANCE_DIRNAME = "instances";
	public static String INSTANCE_CHROOT_PATH;
	public static String JBPM_CONFIG;
	public static String PROPERTY_GROUPS_XML;
	public static String PROPERTY_GROUPS_CND;
	public static String DTD_BASE;
	public static String LANG_PROFILES_BASE;

	// Default users
	public static String PROPERTY_SYSTEM_USER = "user.system";
	public static String PROPERTY_ADMIN_USER = "user.admin";

	//Login safe
	public static String PROPERTY_USER_ATTEMPTS="user.attempts";
	public static String PROPERTY_USER_PASSWORD_CHANGE="user.password.change";
	public static String PROPERTY_USER_FIRSTLOGIN_PASSWORD_CHANGE="user.firstlogin.password.change";
	public static String PROPERTY_USER_LOGIN_TIMEOUT="user.login.timeout";
	public static String PROPERTY_USER_AUTOMATIC_DELOCK="user.automatic.delock";
	public static String PROPERTY_USER_SENDMAIL_LOGIN="user.sendmail.login";
	public static String PROPERTY_USER_SENDMAIL_PASSWOED="user.sendmail.password";
	public static String PROPERTY_USER_SENDMAIL_MAIL="user.sendmail.mail";
	public static String PROPERTY_USER_SENDMAIL_SMTPHOST="user.sendmail.smtphost";
	
	public static String PROPERTY_USER_PASSWORD_CHANGE_TRUEORFALSE="user.changepwd.trueorfalse";
	public static String PROPERTY_USER_ATTEMPTS_TRUEORFALSE="user.attempts.trueorfalse";
	public static String PROPERTY_USER_PASSWORD_INTENSITY_TRUEORFALSE="user.password.intensity.trueorfalse";
	public static String PROPERTY_ADMIN_SUPER_TRUEORFALSE="admin.super.trueorfalse";//是否使用超级管理员
	//智能备份批量下载文件大小限制
	public static String PROPERTY_DOWNLOAD_SIZE_LIMIT ="download.size.limit";
	
	//menu name
	public static String PROPERTY_DSM_ROOT="dsm.root";
	public static String PROPERTY_DSM_MAIL="dsm.mail";
	public static String PROPERTY_DSM_METADATA="dsm.metadata";
	public static String PROPERTY_DSM_THESAURUS="dsm.thesaurus";
	public static String PROPERTY_DSM_TRASH="dsm.trash";
	public static String PROPERTY_DSM_TEMPLATES="dsm.templates";
	public static String PROPERTY_DSM_PERSONALSHARE="dsm.personalshare";
	public static String PROPERTY_DSM_MYSHARE="dsm.myshare";
	public static String PROPERTY_DSM_CATEGORIES="dsm.categories";
	public static String PROPERTY_DSM_PERSONAL="dsm.personal";
	public static String PROPERTY_DSM_DOCUMENTLIB="dsm.documentlib";
	public static String PROPERTY_DSM_TASKLIB="dsm.tasklib";
	//company name
	public static String PROPERTY_DSM_COMPANY_NAME = "dsm.company.name";
	
	//Upload safe
	public static String  PROPERTY_DSM_UPLOADFILE_LIMIT_FORMATS="dsm.uploadfile.limit.formats";

	// General configuration
	public static String EXPORT_METADATA_EXT = ".dsm";
	public static String ROOT_NODE_UUID = "cafebabe-cafe-babe-cafe-babecafebabe";
	public static String DEFAULT_CRONTAB_MAIL = "noreply@lddsm.com";
	
	// Default script
	public static final String PROPERTY_DEFAULT_SCRIPT = "default.script";
	
	// Preview cache
	public static String REPOSITORY_CACHE_HOME;
	public static String REPOSITORY_CACHE_DIRNAME = "cache";
	public static String REPOSITORY_CACHE_DXF;
	public static String REPOSITORY_CACHE_PDF;
	public static String REPOSITORY_CACHE_SWF;
	
	/**公司名和注册码配置*/
	public static final String PROPERTY_COMPANY_NAME = "company.name";
	public static String COMPANY_NAME = "";
	public static final String PROPERTY_MAIN_KEY = "main.key";
	public static String MAIN_KEY = "";
	/**数据库保存日志数量*/
	public static final String PROPERTY_LOG_MAX_NUM = "log.maxNum";
	/**数据库保存数量大于设定值，则删除对应数量的日志*/
	public static final String PROPERTY_LOG_DELETE_NUM = "log.deleteNum";
	public static Integer LOG_MAX_NUM = 100000;
	public static Integer LOG_DELETE_NUM = 10000;
	/**局域网地址和掩码配置*/
	public static final String PROPERTY_LAN_MARK = "lan.mark";
	public static String LAN_MARK = "255.255.255.0";
    public static final String PROPERTY_LAN_SERVERIP = "lan.serverip";
    public static String LAN_SERVERIP = "127.0.0.1";
	/**ad服务器*/
	public static final String PROPERTY_DEFAULT_ADSERVER = "ad.server";
	public static String DEFAULT_ADSERVER = "192.168.4.135";
	
	public static final String PROPERTY_DEFAULT_ADSUFFIX = "ad.Suffix";
	public static String DEFAULT_ADSUFFIX = "@test.com";
	
	public static final String PROPERTY_DEFAULT_ADADMIN = "ad.AdAdmin";
	public static String DEFAULT_ADADMIN = "Administrator@ldaptest.com";
	
	public static final String PROPERTY_DEFAULT_ADKEY = "ad.AdKey";
	public static String DEFAULT_ADKEY = "ss123!@#";
	
	public static final String PROPERTY_DEFAULT_LDAPURL = "ad.ldapURL";
	public static String DEFAULT_LDAPURL = "LDAP://192.168.5.165:389";
	/**ad服务器定时同步开关*/
	public static final String PROPERTY_USER_WEB_DOMAIN_TRUEORFALSE="ad.timer.switch";
	public static boolean USER_WEB_DOMAIN_TRUEORFALSE=false;//ad服务器定时同步开关是否开启
	public static final String PROPERTY_USER_WEB_DOMAIN_TIMER="ad.timer";
	public static long USER_WEB_DOMAIN_TIMER=1;//ad服务器定时同步天数
	/**web判断是否验证ad服务器*/
	public static final String PROPERTY_USER_WEB_DOMAIN_CHECK="ad.check";
	public static boolean USER_WEB_DOMAIN_CHECK=false;//是否使用域验证
	// Experimental features
	public static final String PROPERTY_PLUGIN_DEBUG = "plugin.debug";
	public static final String PROPERTY_MANAGED_TEXT_EXTRACTION = "managed.text.extraction";
	public static final String PROPERTY_MANAGED_TEXT_EXTRACTION_BATCH = "managed.text.extraction.batch";
	public static final String PROPERTY_MANAGED_TEXT_EXTRACTION_POOL_SIZE = "managed.text.extraction.pool.size";
	public static final String PROPERTY_MANAGED_TEXT_EXTRACTION_POOL_THREADS = "managed.text.extraction.pool.threads";
	public static final String PROPERTY_MANAGED_TEXT_EXTRACTION_POOL_TIMEOUT = "managed.text.extraction.pool.timeout";
	public static final String PROPERTY_MANAGED_TEXT_EXTRACTION_PERIOD = "managed.text.extraction.period";
	public static final String PROPERTY_MANAGED_TEXT_EXTRACTION_CONCURRENT = "managed.text.extraction.concurrent";
	public static final String PROPERTY_REPOSITORY_NATIVE = "repository.native";
	public static final String PROPERTY_REPOSITORY_CONTENT_CHECKSUM = "repository.content.checksum";
	public static final String PROPERTY_REPOSITORY_PURGATORY_HOME = "repository.purgatory.home";
	public static final String PROPERTY_AMAZON_ACCESS_KEY = "amazon.access.key";
	public static final String PROPERTY_AMAZON_SECRET_KEY = "amazon.secret.key";
	public static final String PROPERTY_USER_PASSWORD_RESET = "user.password.reset";
	public static final String PROPERTY_KEEP_SESSION_ALIVE_INTERVAL = "keep.session.alive.interval";
	public static final String PROPERTY_STORE_NODE_PATH = "store.node.path";
	public static final String PROPERTY_TOMCAT_CONNECTOR_URI_ENCODING = "tomcat.connector.uri.encoding";
	
	// Security properties
	public static final String PROPERTY_SECURITY_ACCESS_MANAGER = "security.access.manager";
	public static final String PROPERTY_SECURITY_SEARCH_EVALUATION = "security.search.evaluation";
	public static final String PROPERTY_SECURITY_MODE_MULTIPLE = "security.mode.multiple";
	public static final String PROPERTY_SECURITY_LIVE_CHANGE_NODE_LIMIT = "security.live.change.node.limit";
	
	// Configuration properties
	public static final String PROPERTY_REPOSITORY_UUID = "repository.uuid";
	public static final String PROPERTY_REPOSITORY_VERSION = "repository.version";
	public static final String PROPERTY_REPOSITORY_CONFIG = "repository.config";
	public static final String PROPERTY_REPOSITORY_HOME = "repository.home";
	public static final String PROPERTY_REPOSITORY_DATASTORE_BACKEND = "repository.datastore.backend";
	public static final String PROPERTY_REPOSITORY_DATASTORE_HOME = "repository.datastore.home";
	public static final String PROPERTY_REPOSITORY_CACHE_HOME = "repository.cache.home";
	public static final String PROPERTY_VERSION_NUMERATION_ADAPTER = "version.numeration.adapter";
	public static final String PROPERTY_VERSION_NUMERATION_FORMAT = "version.numeration.format";
	public static final String PROPERTY_MAX_FILE_SIZE = "max.file.size";
	public static final String PROPERTY_MAX_SEARCH_RESULTS = "max.search.results";
	public static final String PROPERTY_MIN_SEARCH_CHARACTERS = "min.search.characters";
	public static final String PROPERTY_SEND_MAIL_FROM_USER = "send.mail.from.user";
	public static final String PROPERTY_DEFAULT_USER_ROLE = "default.user.role";
	public static final String PROPERTY_DEFAULT_ADMIN_ROLE = "default.admin.role";
	
	// Text extractors
	public static final String PROPERTY_REGISTERED_TEXT_EXTRACTORS = "registered.text.extractors";
	
	// Workflow
	public static final String PROPERTY_WORKFLOW_START_TASK_AUTO_RUN = "workflow.start.task.auto.run";
	public static final String PROPERTY_WORKFLOW_RUN_CONFIG_FORM = "workflow.run.config.form";
	
	// Principal
	public static final String PROPERTY_PRINCIPAL_ADAPTER = "principal.adapter";
	public static final String PROPERTY_PRINCIPAL_DATABASE_FILTER_INACTIVE_USERS = "principal.database.filter.inactive.users";
	public static final String PROPERTY_PRINCIPAL_HIDE_CONNECTION_ROLES = "principal.hide.connection.roles";
	public static final String PROPERTY_PRINCIPAL_IDENTIFIER_VALIDATION = "principal.identifier.validation";
	
	// LDAP
	public static final String PROPERTY_PRINCIPAL_LDAP_SERVER = "principal.ldap.server";
	public static final String PROPERTY_PRINCIPAL_LDAP_SECURITY_PRINCIPAL = "principal.ldap.security.principal";
	public static final String PROPERTY_PRINCIPAL_LDAP_SECURITY_CREDENTIALS = "principal.ldap.security.credentials";
	public static final String PROPERTY_PRINCIPAL_LDAP_REFERRAL = "principal.ldap.referral";
	public static final String PROPERTY_PRINCIPAL_LDAP_USERS_FROM_ROLES = "principal.ldap.users.from.roles";
	
	public static final String PROPERTY_PRINCIPAL_LDAP_USER_SEARCH_BASE = "principal.ldap.user.search.base";
	public static final String PROPERTY_PRINCIPAL_LDAP_USER_SEARCH_FILTER = "principal.ldap.user.search.filter";
	public static final String PROPERTY_PRINCIPAL_LDAP_USER_ATTRIBUTE = "principal.ldap.user.attribute";
	
	public static final String PROPERTY_PRINCIPAL_LDAP_ROLE_SEARCH_BASE = "principal.ldap.role.search.base";
	public static final String PROPERTY_PRINCIPAL_LDAP_ROLE_SEARCH_FILTER = "principal.ldap.role.search.filter";
	public static final String PROPERTY_PRINCIPAL_LDAP_ROLE_ATTRIBUTE = "principal.ldap.role.attribute";
	
	public static final String PROPERTY_PRINCIPAL_LDAP_USERNAME_SEARCH_BASE = "principal.ldap.username.search.base";
	public static final String PROPERTY_PRINCIPAL_LDAP_USERNAME_SEARCH_FILTER = "principal.ldap.username.search.filter";
	public static final String PROPERTY_PRINCIPAL_LDAP_USERNAME_ATTRIBUTE = "principal.ldap.username.attribute";
	
	public static final String PROPERTY_PRINCIPAL_LDAP_MAIL_SEARCH_BASE = "principal.ldap.mail.search.base";
	public static final String PROPERTY_PRINCIPAL_LDAP_MAIL_SEARCH_FILTER = "principal.ldap.mail.search.filter";
	public static final String PROPERTY_PRINCIPAL_LDAP_MAIL_ATTRIBUTE = "principal.ldap.mail.attribute";
	
	public static final String PROPERTY_PRINCIPAL_LDAP_USERS_BY_ROLE_SEARCH_BASE = "principal.ldap.users.by.role.search.base";
	public static final String PROPERTY_PRINCIPAL_LDAP_USERS_BY_ROLE_SEARCH_FILTER = "principal.ldap.users.by.role.search.filter";
	public static final String PROPERTY_PRINCIPAL_LDAP_USERS_BY_ROLE_ATTRIBUTE = "principal.ldap.users.by.role.attribute";
	
	public static final String PROPERTY_PRINCIPAL_LDAP_ROLES_BY_USER_SEARCH_BASE = "principal.ldap.roles.by.user.search.base";
	public static final String PROPERTY_PRINCIPAL_LDAP_ROLES_BY_USER_SEARCH_FILTER = "principal.ldap.roles.by.user.search.filter";
	public static final String PROPERTY_PRINCIPAL_LDAP_ROLES_BY_USER_ATTRIBUTE = "principal.ldap.roles.by.user.attribute";
	
	public static final String PROPERTY_RESTRICT_FILE_MIME = "restrict.file.mime";
	public static final String PROPERTY_RESTRICT_FILE_NAME = "restrict.file.name";
	
	public static final String PROPERTY_NOTIFICATION_MESSAGE_SUBJECT = "notification.message.subject";
	public static final String PROPERTY_NOTIFICATION_MESSAGE_BODY = "notification.message.body";
	
	public static final String PROPERTY_SUBSCRIPTION_MESSAGE_SUBJECT = "subscription.message.subject";
	public static final String PROPERTY_SUBSCRIPTION_MESSAGE_BODY = "subscription.message.body";
	
	public static final String PROPERTY_SUBSCRIPTION_TWITTER_USER = "notify.twitter.user";
	public static final String PROPERTY_SUBSCRIPTION_TWITTER_PASSWORD = "notify.twitter.password";
	public static final String PROPERTY_SUBSCRIPTION_TWITTER_STATUS = "notify.twitter.status";
	
	public static final String PROPERTY_SYSTEM_MULTIPLE_INSTANCES = "system.multiple.instances";
	public static final String PROPERTY_SYSTEM_APACHE_REQUEST_HEADER_FIX = "system.apache.request.header.fix";
	public static final String PROPERTY_SYSTEM_WEBDAV_SERVER = "system.webdav.server";
	public static final String PROPERTY_SYSTEM_WEBDAV_FIX = "system.webdav.fix";
	public static final String PROPERTY_SYSTEM_READONLY = "system.readonly";
	public static final String PROPERTY_SYSTEM_MAINTENANCE = "system.maintenance";
	public static final String PROPERTY_SYSTEM_OCR = "system.ocr";
	public static final String PROPERTY_SYSTEM_OCR_ROTATE = "system.ocr.rotate";
	public static final String PROPERTY_SYSTEM_PDF_FORCE_OCR = "system.pdf.force.ocr";
	public static final String PROPERTY_SYSTEM_OPENOFFICE_PATH = "system.openoffice.path";
	public static final String PROPERTY_SYSTEM_OPENOFFICE_TASKS = "system.openoffice.tasks";
	public static final String PROPERTY_SYSTEM_OPENOFFICE_PORT = "system.openoffice.port";
	public static final String PROPERTY_SYSTEM_OPENOFFICE_SERVER = "system.openoffice.server";
	public static final String PROPERTY_SYSTEM_OPENOFFICE_DICTIONARY = "system.openoffice.dictionary";
	public static final String PROPERTY_SYSTEM_IMAGEMAGICK_CONVERT = "system.imagemagick.convert";
	public static final String PROPERTY_SYSTEM_SWFTOOLS_PDF2SWF = "system.swftools.pdf2swf";
	public static final String PROPERTY_SYSTEM_GHOSTSCRIPT = "system.ghostscript";
	public static final String PROPERTY_SYSTEM_ANTIVIR = "system.antivir";
	public static final String PROPERTY_SYSTEM_CATDOC_XLS2CSV = "system.catdoc.xls2csv";
	public static final String PROPERTY_SYSTEM_LOGIN_LOWERCASE = "system.login.lowercase";
	public static final String PROPERTY_SYSTEM_PREVIEWER = "system.previewer";
	public static final String PROPERTY_SYSTEM_DOCUMENT_NAME_MISMATCH_CHECK = "system.document.name.mismatch.check";
	public static final String PROPERTY_SYSTEM_KEYWORD_LOWERCASE = "system.keyword.lowercase";
	public static final String PROPERTY_SYSTEM_EXECUTION_TIMEOUT = "system.execution.timeout";
	public static final String PROPERTY_SYSTEM_PROFILING = "system.profiling";
	public static final String PROPERTY_SYSTEM_PDFTOOLS_DOC2PDF = "system.pdftools.doc2pdf";
	public static final String PROPERTY_SYSTEM_DOC2PDF_COUNT = "system.doc2pdf.count";
	public static final String PROPERTY_STYTEM_PDFTOOLS_DWG2PDF = "system.pdftools.dwg2pdf";
	
	public static final String PROPERTY_APPLICATION_URL = "application.url";
	public static final String PROPERTY_DEFAULT_LANG = "default.lang";
	public static final String PROPERTY_USER_ASSIGN_DOCUMENT_CREATION = "user.assign.document.creation";
	public static final String PROPERTY_USER_ITEM_CACHE = "user.item.cache";
	public static final String PROPERTY_UPLOAD_THROTTLE_FILTER = "upload.throttle.filter";
	public static final String PROPERTY_REMOTE_CONVERSION_SERVER = "remote.conversion.server";
	
	// Schedule
	public static final String PROPERTY_SCHEDULE_SESSION_KEEPALIVE = "schedule.session.keepalive";
	public static final String PROPERTY_SCHEDULE_DASHBOARD_REFRESH = "schedule.dashboard.refresh";
	public static final String PROPERTY_SCHEDULE_UI_NOTIFICATION = "schedule.ui.notification";
	
	// KEA
	// Used in generate_thesaurus.jsp
	public static final String PROPERTY_KEA_THESAURUS_SKOS_FILE = "kea.thesaurus.skos.file";
	public static final String PROPERTY_KEA_THESAURUS_OWL_FILE = "kea.thesaurus.owl.file";
	public static final String PROPERTY_KEA_THESAURUS_VOCABULARY_SERQL = "kea.thesaurus.vocabulary.serql";
	public static final String PROPERTY_KEA_THESAURUS_BASE_URL = "kea.thesaurus.base.url";
	public static final String PROPERTY_KEA_THESAURUS_TREE_ROOT = "kea.thesaurus.tree.root";
	public static final String PROPERTY_KEA_THESAURUS_TREE_CHILDS = "kea.thesaurus.tree.childs";
	
	// Validator
	public static final String PROPERTY_VALIDATOR_PASSWORD = "validator.password";
	
	public static final String PROPERTY_VALIDATOR_PASSWORD_MIN_LENGTH = "validator.password.min.length";
	public static final String PROPERTY_VALIDATOR_PASSWORD_MAX_LENGTH = "validator.password.max.length";
	public static final String PROPERTY_VALIDATOR_PASSWORD_MIN_LOWERCASE = "validator.password.min.lowercase";
	public static final String PROPERTY_VALIDATOR_PASSWORD_MIN_UPPERCASE = "validator.password.min.uppercase";
	public static final String PROPERTY_VALIDATOR_PASSWORD_MIN_DIGITS = "validator.password.min.digits";
	public static final String PROPERTY_VALIDATOR_PASSWORD_MIN_SPECIAL = "validator.password.mini.special";
	
	// Hibernate
	public static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String PROPERTY_HIBERNATE_DATASOURCE = "hibernate.datasource";
	public static final String PROPERTY_HIBERNATE_DATASOURCE_FS = "hibernate.datasourcefs";
	public static final String PROPERTY_HIBERNATE_HBM2DDL = "hibernate.hbm2ddl"; // Used in login.jsp
	public static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String PROPERTY_HIBERNATE_STATISTICS = "hibernate.statistics";
	public static final String PROPERTY_HIBERNATE_SEARCH_ANALYZER = "hibernate.search.analyzer";
	public static final String PROPERTY_HIBERNATE_CREATE_AUTOFIX = "hibernate.create.autofix";
	public static final String PROPERTY_HIBERNATE_INDEXER_MASS_INDEXER = "hibernate.indexer.mass.indexer";
	public static final String PROPERTY_HIBERNATE_INDEXER_BATCH_SIZE_LOAD_OBJECTS = "hibernate.indexer.batch.size.load.objects";
	public static final String PROPERTY_HIBERNATE_INDEXER_THREADS_SUBSEQUENT_FETCHING = "hibernate.indexer.threads.subsequent.fetching";
	public static final String PROPERTY_HIBERNATE_INDEXER_THREADS_LOAD_OBJECTS = "hibernate.indexer.threads.load.objects";
	public static final String PROPERTY_HIBERNATE_INDEXER_THREADS_INDEX_WRITER = "hibernate.indexer.threads.index.writer";
	
	// Hibernate Search indexes
	public static String PROPERTY_HIBERNATE_SEARCH_INDEX_HOME = "hibernate.search.index.home";
	public static String PROPERTY_HIBERNATE_SEARCH_INDEX_EXCLUSIVE = "hibernate.search.index.exclusive";

	// Logo icons & login texts
	public static final String PROPERTY_LOGO_LOGIN = "logo.login";
	public static final String PROPERTY_LOGO_REPORT = "logo.report";

	//Download Config
	public static final String PROPERTY_ANDROID_QR_CODE ="android.qr.code";
	public static final String PROPERTY_IOS_QR_CODE ="ios.qr.code";
	public static final String PROPERTY_IOS_CLIENT_DOWNLOAD="ios.client.download";
	public static final String PROPERTY_WIN_CLIENT_DOWNLOAD="win.client.download";
	public static final String PROPERTY_ANDROID_CLIENT_DOWNLOAD="android.client.download";
	// Zoho
	public static final String PROPERTY_ZOHO_USER = "zoho.user";
	public static final String PROPERTY_ZOHO_PASSWORD = "zoho.password";
	public static final String PROPERTY_ZOHO_API_KEY = "zoho.api.key";
	public static final String PROPERTY_ZOHO_SECRET_KEY = "zoho.secret.key";
	
	// OpenMeetings
	public static final String PROPERTY_OPENMEETINGS_URL = "openmeetings.url";
	public static final String PROPERTY_OPENMEETINGS_PORT = "openmeetings.port";
	public static final String PROPERTY_OPENMEETINGS_USER = "openmeetings.user";
	public static final String PROPERTY_OPENMEETINGS_CREDENTIALS = "openmeetings.credentials";
	
	// CSV
	public static String PROPERTY_CSV_FORMAT_DELIMITER = "csv.format.delimiter";
	public static String PROPERTY_CSV_FORMAT_QUOTE_CHARACTER = "csv.format.quote.character";
	public static String PROPERTY_CSV_FORMAT_COMMENT_INDICATOR = "csv.format.comment.indicator";
	public static String PROPERTY_CSV_FORMAT_SKIP_HEADER = "csv.format.skip_header";
	public static String PROPERTY_CSV_FORMAT_IGNORE_EMPTY_LINES = "csv.format.ignore.empty.lines";
	
	// Unit Testing
	public static final String PROPERTY_UNIT_TESTING_USER = "unit.testing.user";
	public static final String PROPERTY_UNIT_TESTING_PASSWORD = "unit.testing.password";
	public static final String PROPERTY_UNIT_TESTING_FOLDER = "unit.testing.folder";
	
	//ROLE CONFIG
	public static final String PROPERTY_ROLE_ENABLE_AUTHORITIES_REPEAT = "role.enable.authorities.repeat"; //子角色权限是否可以重复
		
	// STATUS POPUP
	public static final String PROPERTY_STATUS_POPUP_TIMEOUT = "status.popup.timeout"; //消息提示弹出框超时自动关闭时间
	
	// VERSION KEEP
	public static final String PROPERTY_DOCUMENT_VERSION_KEEP_MAX = "document.version.keep.max"; //文档历史版本最大保留数
	
	// CLIENT PARAMS
	public static final String PROPERTY_CLIENT_DOMAIN_SERVER_ADDRESS = "client.domain.server.address"; //客户端域名服务器地址
	
	// packing 不存储数据库
	public static String SYSTEM_PACK_PROPTIES_BASE = ""; //配置文件路径
	public static final String SYSTEM_PACK_PROPTIES_FILE = "pack.properties"; //配置文件名
	public static final String PROPERTY_SYSTEM_PACKING_TYPE = "system.packing.type";
	public static final String PACKING_TYPE_GOV = "gov";
	public static final String PACKING_TYPE_CORP = "corp";
	public static final String PACKING_TYPE_FREE = "free";
	public static String SYSTEM_PACKING_TYPE = "gov"; //系统打包类型（政府or企业）：图标与命名有区别
	public static boolean VERSION_STABLE = false; //是否稳定版本
	
	public static final String PREVIEW_PLUGIN_TYPE_OPENOFFICE = "openoffice";
	public static final String PREVIEW_PLUGIN_TYPE_ASPOSE = "aspose";
	public static final String PREVIEW_PLUGIN_TYPE_YOZO = "yozo";
	
	//消息提醒设置
	public static final String PROPERTY_MESSAGE_ACTIVE = "message.active"; //是否启用
	public static final String PROPERTY_MESSAGE_FORCE = "message.force"; //是否强制处理（模态非模态对话框）
	public static final String PROPERTY_MESSAGE_POPUP_POSITION = "message.popup.position"; //消息提醒显示位置
	public static final String PROPERTY_MESSAGE_GET_PERIOD = "message.get.period"; //消息循环获取间（单位：分钟）
	public static boolean MESSAGE_ACTIVE = true;
	public static boolean MESSAGE_FORCE = false;
	public static String MESSAGE_POPUP_POSITION = "(bottom,right)";//middle,top,bottom,left,right
	public static int MESSAGE_GET_PERIOD = 5;
	
	/**
	 * Default values
	 */
	// Experimental features
	public static boolean PLUGIN_DEBUG = false;
	public static boolean MANAGED_TEXT_EXTRACTION = true;
	public static int MANAGED_TEXT_EXTRACTION_BATCH = 10;
	public static int MANAGED_TEXT_EXTRACTION_POOL_SIZE = 5;
	public static int MANAGED_TEXT_EXTRACTION_POOL_THREADS = 5;
	public static int MANAGED_TEXT_EXTRACTION_POOL_TIMEOUT = 1; // 1 minute
	public static int MANAGED_TEXT_EXTRACTION_PERIOD = 5; //每隔多少分钟用EXE进程进行文本提取
	public static boolean MANAGED_TEXT_EXTRACTION_CONCURRENT = false;
	public static boolean REPOSITORY_NATIVE = true;
	public static boolean REPOSITORY_CONTENT_CHECKSUM = true;
	public static String REPOSITORY_PURGATORY_HOME = "";
	public static String AMAZON_ACCESS_KEY = "";
	public static String AMAZON_SECRET_KEY = "";
	public static boolean USER_PASSWORD_RESET = true;
	public static int KEEP_SESSION_ALIVE_INTERVAL = 5; // 5 minutes
	public static boolean STORE_NODE_PATH = false;
	public static String TOMCAT_CONNECTOR_URI_ENCODING = "UTF-8";
	
	
	//login safe
	public static int USER_ATTEMPTS=3;//密码尝试错误次数
	public static int USER_PASSWORD_CHANGE=30;//30 days 多久改密码		
	public static boolean USER_FIRSTLOGIN_PASSWORD_CHANGE = false;//首次登录修改密码
	public static int USER_LOGIN_TIMEOUT=30;//30 minutes 多久超时
	public static int USER_AUTOMATIC_DELOCK=30;//多久自动解锁分钟
	public static String USER_SENDMAIL_LOGIN="";//发送邮箱的登录名
	public static String USER_SENDMAIL_PASSWOED="";//登录密码
	public static String USER_SENDMAIL_MAIL="";//发送邮箱
	public static String USER_SENDMAIL_SMTPHOST="";//邮箱服务器
	
	public static boolean USER_PASSWORD_CHANGE_TRUEORFALSE=true;//是否定期修改密码
	public static boolean USER_ATTEMPTS_TRUEORFALSE=true;//是否限制登录时密码输入尝试次数
	public static boolean USER_PASSWORD_INTENSITY_TRUEORFALSE=true;//是否使用密码复杂度
	public static boolean ADMIN_SUPER_TRUEORFALSE=true;//是否使用超级管理员
	public static int DOWNLOAD_SIZE_LIMIT=500;
	
	//menu name 中文
	public static String DSM_ROOT="天锐科技";
	public static String DSM_MAIL="我的邮件";
	public static String DSM_METADATA="我的数据";
	public static String DSM_THESAURUS="我的知识库";
	public static String DSM_TRASH="我的回收站";
	public static String DSM_TEMPLATES="我的模板";
	public static String DSM_PERSONALSHARE="其他共享";
	public static String DSM_MYSHARE="我的共享";
	public static String DSM_CATEGORIES="我的分组";
	public static String DSM_PERSONAL ="我的文档";
	public static String DSM_DOCUMENTLIB="我的文件库";
	public static String DSM_TASKLIB="我的任务库";

	//menu name 英文
	public static String DSM_ROOT_EN="Tipray";
	public static String DSM_MAIL_EN="My Email";
	public static String DSM_METADATA_EN="My Data";
	public static String DSM_THESAURUS_EN="My Knowledge Base";
	public static String DSM_TRASH_EN="My Trash";
	public static String DSM_TEMPLATES_EN="My Template";
	public static String DSM_PERSONALSHARE_EN="Other Share";
	public static String DSM_MYSHARE_EN="My Share";
	public static String DSM_CATEGORIES_EN="My Grouping";
	public static String DSM_PERSONAL_EN ="My Documents";
	public static String DSM_DOCUMENTLIB_EN="My Document Library";
	public static String DSM_TASKLIB_EN="My Task Library";
	//company name
	public static String DSM_COMPANY_NAME = "company";
	
	//Upload safe
	public static String DSM_UPLOADFILE_LIMIT_FORMATS = "";//禁止上传的文件格式 
	
	//stack name
	public static String TREE_TASK_UN="未完成任务";
	public static String TREE_TASK="已完成任务";
	public static String TREE_UNBIND_UM="*未绑定终端";
	
	// Security properties
	public static String SECURITY_ACCESS_MANAGER = "";
	public static String SECURITY_SEARCH_EVALUATION = "";
	public static boolean SECURITY_MODE_MULTIPLE = false;
	public static int SECURITY_LIVE_CHANGE_NODE_LIMIT = 100;
	
	// Configuration properties
	public static String REPOSITORY_CONFIG;
	public static String REPOSITORY_HOME;
	public static String REPOSITORY_DIRNAME = "repository";
	public static String REPOSITORY_DATASTORE_BACKEND;
	public static String REPOSITORY_DATASTORE_HOME;
	public static String DEFAULT_SCRIPT = "print(\"UserId: \" + session.getUserID());\n" +
		"print(\"EventType: \" + eventType);\n" +
		"print(\"EventNode: \" + eventNode.getPath());\n" +
		"print(\"ScriptNode: \" + scriptNode.getPath());";
	public static String VERSION_NUMERATION_FORMAT = "%d";
	public static long MAX_FILE_SIZE;
	public static int MAX_SEARCH_RESULTS;
	public static int MIN_SEARCH_CHARACTERS;
	public static boolean SEND_MAIL_FROM_USER;
	public static String SYSTEM_USER = "system";
	public static String ADMIN_USER = "admin";
	public static String SYS_ADMIN = "sysadmin";
	public static String SEC_ADMIN = "secadmin";
	public static String LOG_ADMIN = "logadmin";
	
	public static String DEFAULT_USER_ROLE = "2";
	public static String DEFAULT_ADMIN_ROLE = "1";
	
	// Text extractors
	public static List<String> REGISTERED_TEXT_EXTRACTORS = new ArrayList<String>(); 
	private static final String DEFAULT_REGISTERED_TEXT_EXTRACTORS = 
		"org.apache.jackrabbit.extractor.PlainTextExtractor\n" +
		"org.apache.jackrabbit.extractor.MsWordTextExtractor\n" +
		"org.apache.jackrabbit.extractor.MsExcelTextExtractor\n" +
		"org.apache.jackrabbit.extractor.MsPowerPointTextExtractor\n" +
		"org.apache.jackrabbit.extractor.OpenOfficeTextExtractor\n" +
		"org.apache.jackrabbit.extractor.RTFTextExtractor\n" +
		"org.apache.jackrabbit.extractor.HTMLTextExtractor\n" +
		"org.apache.jackrabbit.extractor.XMLTextExtractor\n" +
		"org.apache.jackrabbit.extractor.PngTextExtractor\n" +
		"org.apache.jackrabbit.extractor.MsOutlookTextExtractor\n" +
		"com.lddsm.extractor.PdfTextExtractor\n" +
		"com.lddsm.extractor.AudioTextExtractor\n" +
		"com.lddsm.extractor.ExifTextExtractor\n" +
		"com.lddsm.extractor.CuneiformTextExtractor\n" +
		"com.lddsm.extractor.SourceCodeTextExtractor\n" +
		"com.lddsm.extractor.MsOffice2007TextExtractor";
	
	// Workflow
	public static String WORKFLOW_RUN_CONFIG_FORM = "run_config";
	public static boolean WORKFLOW_START_TASK_AUTO_RUN = true;
	public static String WORKFLOW_PROCESS_INSTANCE_VARIABLE_UUID = "uuid";
	public static String WORKFLOW_PROCESS_INSTANCE_VARIABLE_PATH = "path";
	
	// Principal
	public static boolean PRINCIPAL_DATABASE_FILTER_INACTIVE_USERS = true;
	public static boolean PRINCIPAL_HIDE_CONNECTION_ROLES = false;
	public static String PRINCIPAL_IDENTIFIER_VALIDATION = "^[a-zA-Z0-9_]+$";
	
	// LDAP
	public static String PRINCIPAL_LDAP_SERVER; // ldap://phoenix.server:389
	public static String PRINCIPAL_LDAP_SECURITY_PRINCIPAL; // "cn=Administrator,cn=Users,dc=lddsm,dc=com"
	public static String PRINCIPAL_LDAP_SECURITY_CREDENTIALS; // "xxxxxx"
	public static String PRINCIPAL_LDAP_REFERRAL;
	public static boolean PRINCIPAL_LDAP_USERS_FROM_ROLES;
	
	public static List<String> PRINCIPAL_LDAP_USER_SEARCH_BASE = new ArrayList<String>(); // ou=people,dc=lddsm,dc=com
	public static String PRINCIPAL_LDAP_USER_SEARCH_FILTER; // (&(objectClass=posixAccount)(!(objectClass=gosaUserTemplate)))
	public static String PRINCIPAL_LDAP_USER_ATTRIBUTE; // uid
	
	public static List<String> PRINCIPAL_LDAP_ROLE_SEARCH_BASE = new ArrayList<String>(); // ou=groups,dc=lddsm,dc=com
	public static String PRINCIPAL_LDAP_ROLE_SEARCH_FILTER; // (&(objectClass=posixGroup)(cn=*)(|(description=*lddsm*)(cn=users)))
	public static String PRINCIPAL_LDAP_ROLE_ATTRIBUTE; // cn
	
	public static String PRINCIPAL_LDAP_USERNAME_SEARCH_BASE; // ou=people,dc=lddsm,dc=com
	public static String PRINCIPAL_LDAP_USERNAME_SEARCH_FILTER; // (&(objectClass=posixAccount)(!(objectClass=gosaUserTemplate)))
	public static String PRINCIPAL_LDAP_USERNAME_ATTRIBUTE; // displayName
	
	public static String PRINCIPAL_LDAP_MAIL_SEARCH_BASE; // uid={0},ou=people,dc=lddsm,dc=com
	public static String PRINCIPAL_LDAP_MAIL_SEARCH_FILTER; // (&(objectClass=inetOrgPerson)(mail=*))
	public static String PRINCIPAL_LDAP_MAIL_ATTRIBUTE; // mail
	
	public static String PRINCIPAL_LDAP_USERS_BY_ROLE_SEARCH_BASE;
	public static String PRINCIPAL_LDAP_USERS_BY_ROLE_SEARCH_FILTER; // (&(objectClass=group)(cn={0}))
	public static String PRINCIPAL_LDAP_USERS_BY_ROLE_ATTRIBUTE;
	
	public static String PRINCIPAL_LDAP_ROLES_BY_USER_SEARCH_BASE;
	public static String PRINCIPAL_LDAP_ROLES_BY_USER_SEARCH_FILTER; // (&(objectClass=group)(cn={0}))
	public static String PRINCIPAL_LDAP_ROLES_BY_USER_ATTRIBUTE;
	
	public static boolean RESTRICT_FILE_MIME;
	public static String RESTRICT_FILE_NAME;
	
	public static String NOTIFICATION_MESSAGE_SUBJECT;
	public static String NOTIFICATION_MESSAGE_BODY;
	
	public static String SUBSCRIPTION_MESSAGE_SUBJECT;
	public static String SUBSCRIPTION_MESSAGE_BODY;
	
	public static String SUBSCRIPTION_TWITTER_USER;
	public static String SUBSCRIPTION_TWITTER_PASSWORD;
	public static String SUBSCRIPTION_TWITTER_STATUS;

	public static boolean SYSTEM_MULTIPLE_INSTANCES;
	public static boolean SYSTEM_APACHE_REQUEST_HEADER_FIX;
	public static boolean SYSTEM_WEBDAV_SERVER;
	public static boolean SYSTEM_WEBDAV_FIX;
	public static boolean SYSTEM_MAINTENANCE;
	public static boolean SYSTEM_READONLY;
	public static String SYSTEM_OCR = "";
	public static String SYSTEM_OCR_ROTATE = "";
	public static boolean SYSTEM_PDF_FORCE_OCR;
	public static String SYSTEM_OPENOFFICE_PATH = "";
	public static int SYSTEM_OPENOFFICE_TASKS;
	public static int SYSTEM_OPENOFFICE_PORT;
	public static String SYSTEM_OPENOFFICE_SERVER = "";
	public static String SYSTEM_OPENOFFICE_DICTIONARY = "";
	public static String SYSTEM_IMAGEMAGICK_CONVERT = "";
	public static String SYSTEM_SWFTOOLS_PDF2SWF = "";
	public static String SYSTEM_GHOSTSCRIPT = "";
	public static String SYSTEM_ANTIVIR = "";
	public static String SYSTEM_CATDOC_XLS2CSV = "";
	public static boolean SYSTEM_LOGIN_LOWERCASE = true;
	public static String SYSTEM_PREVIEWER = "";
	public static boolean SYSTEM_DOCUMENT_NAME_MISMATCH_CHECK = true;
	public static boolean SYSTEM_KEYWORD_LOWERCASE = false;
	public static int SYSTEM_EXECUTION_TIMEOUT = 5; // 5 min
	public static boolean SYSTEM_PROFILING = false;
	public static String SYSTEM_PDFTOOLS_DOC2PDF = "";
	public static int SYSTEM_DOC2PDF_COUNT = 5;
	public static String STYTEM_PDFTOOLS_DWG2PDF = "";
	
	public static boolean UPDATE_INFO = true;
	public static String APPLICATION_URL;
	public static String APPLICATION_BASE;
	public static String DEFAULT_LANG = "";
	public static boolean USER_ASSIGN_DOCUMENT_CREATION = true;
	public static boolean USER_ITEM_CACHE = true;
	public static boolean UPLOAD_THROTTLE_FILTER;
	public static String REMOTE_CONVERSION_SERVER = "";
	
	// Schedule
	public static int SCHEDULE_SESSION_KEEPALIVE = 15; // 15 min
	public static int SCHEDULE_DASHBOARD_REFRESH = 30; // 30 min
	public static int SCHEDULE_UI_NOTIFICATION = 1; // 1 min
	
	// KEA
	public static String KEA_THESAURUS_SKOS_FILE;
	public static String KEA_THESAURUS_OWL_FILE;
	public static String KEA_THESAURUS_VOCABULARY_SERQL;
	public static String KEA_THESAURUS_BASE_URL;
	public static String KEA_THESAURUS_TREE_ROOT;
	public static String KEA_THESAURUS_TREE_CHILDS;
	
	// Validator
	
	public static int VALIDATOR_PASSWORD_MIN_LENGTH;
	public static int VALIDATOR_PASSWORD_MAX_LENGTH;
	public static int VALIDATOR_PASSWORD_MIN_LOWERCASE;
	public static int VALIDATOR_PASSWORD_MIN_UPPERCASE;
	public static int VALIDATOR_PASSWORD_MIN_DIGITS;
	public static int VALIDATOR_PASSWORD_MIN_SPECIAL;
	
	public static String VALIDATOR_PASSWORD_ERROR_MIN_LENGTH = "Password error: too short";
	public static String VALIDATOR_PASSWORD_ERROR_MAX_LENGTH = "Password error: too long";
	public static String VALIDATOR_PASSWORD_ERROR_MIN_LOWERCASE = "Password error: too few lowercase characters";
	public static String VALIDATOR_PASSWORD_ERROR_MIN_UPPERCASE = "Password error: too few uppercase characters";
	public static String VALIDATOR_PASSWORD_ERROR_MIN_DIGITS = "Password error: too few digits";
	public static String VALIDATOR_PASSWORD_ERROR_MIN_SPECIAL = "Password error: too few special characters";
	
	// Hibernate
	//public static String HIBERNATE_DIALECT = "org.hibernate.dialect.HSQLDialect";
	public static String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
	public static String HIBERNATE_HBM2DDL = "update";
	public static String HIBERNATE_SHOW_SQL = "false";
	public static String HIBERNATE_STATISTICS = "false";
	//public static String HIBERNATE_SEARCH_ANALYZER = "org.apache.lucene.analysis.standard.StandardAnalyzer";
	public static String HIBERNATE_SEARCH_ANALYZER = "org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer";
	public static String HIBERNATE_CREATE_AUTOFIX = "true";
	public static boolean HIBERNATE_INDEXER_MASS_INDEXER = false;
	public static int HIBERNATE_INDEXER_BATCH_SIZE_LOAD_OBJECTS = 30;
	public static int HIBERNATE_INDEXER_THREADS_SUBSEQUENT_FETCHING = 8;
	public static int HIBERNATE_INDEXER_THREADS_LOAD_OBJECTS = 4;
	public static int HIBERNATE_INDEXER_THREADS_INDEX_WRITER = 3;
	
	// Hibernate Search indexes
	public static String HIBERNATE_SEARCH_INDEX_HOME;
	public static String HIBERNATE_SEARCH_INDEX_DIRNAME = "index";
	public static String HIBERNATE_SEARCH_INDEX_EXCLUSIVE = "true";
	
	// Logo icons and login texts
	public static String TEXT_BANNER = "&nbsp;";
	public static String TEXT_WELCOME = "<p>简便管理</p>";
	public static String SYSTEM_TITLE = "";
	public static String SYSTEM_TITLE_EN = "";
	public static String SYSTEM_TILE_TW = "";
	public static String WIN_CLIENT_DOWNLOAD_NAME;
	public static String IOS_CLIENT_DOWNLOAD_NAME;
	public static String ANDROID_CLIENT_DOWNLOAD_NAME;
	// Zoho
	public static String ZOHO_USER;
	public static String ZOHO_PASSWORD;
	public static String ZOHO_API_KEY;
	public static String ZOHO_SECRET_KEY;
	
	// OpenMeetings
	public static String OPENMEETINGS_URL;
	public static String OPENMEETINGS_PORT;
	public static String OPENMEETINGS_USER;
	public static String OPENMEETINGS_CREDENTIALS;
	
	// CSV
	public static String CSV_FORMAT_DELIMITER = ";";
	public static String CSV_FORMAT_QUOTE_CHARACTER = "\"";
	public static String CSV_FORMAT_COMMENT_INDICATOR = "#";
	public static boolean CSV_FORMAT_SKIP_HEADER = false;
	public static boolean CSV_FORMAT_IGNORE_EMPTY_LINES = true;
	
	// Unit Testing
	public static String UNIT_TESTING_USER = "admin";
	public static String UNIT_TESTING_PASSWORD = "admin";
	public static String UNIT_TESTING_FOLDER = "/dsm:root/dsmTesting";
	
	// Misc
	public static String LIST_SEPARATOR = ";";
	
	//ROLE CONFIG
	public static boolean ROLE_ENABLE_AUTHORITIES_REPEAT = false; //子角色权限是否可以重复
	
	// STATUS POPUP
	public static int STATUS_POPUP_TIMEOUT = 30000; //消息提示弹出框超时自动关闭时间
	
	//VERSION KEEP
	public static int DOCUMENT_VERSION_KEEP_MAX = 10; //文档历史版本最大保留数
	
	//CLIENT PARAMS
	public static String CLIENT_DOMAIN_SERVER_ADDRESS = ""; //客户端域名服务器地址
	
	//文件存储路径最小可用空间
	public static String FREE_DISK_SPACE_MIN = "10 G";
	public static String PROPERTY_FREE_DISK_SPACE_MIN = "free.disk.space.min";
	
	//静态的标题配置
	private static Map<String, String> titleMap = new HashMap<String, String>();
	static {
		titleMap.put("gov", "天锐绿盾电子文档");
		titleMap.put("corp", "天锐绿盘");
		titleMap.put("free", "天锐云文档");
		titleMap.put("rylb", "锐云绿贝");
	}

	//静态的标题配置
	private static Map<String, String> titleMapEn = new HashMap<String, String>();
	static {
		titleMapEn.put("gov", "天锐绿盾电子文档");
		titleMapEn.put("corp", "Tipray LeaderDisk");
		titleMapEn.put("rylb", "锐云绿贝");
		titleMapEn.put("free", "Tipray LeaderDisk");
	}
	
	//静态的标题配置
	private static Map<String, String> titleMapTw = new HashMap<String, String>();
	static {
		titleMapTw.put("trlp.gov", "天锐绿盾电子文档");
		titleMapTw.put("trlp.corp", "天銳綠盤 ");
		titleMapTw.put("rylb.gov", "銳雲綠貝");
		titleMapTw.put("rylb.corp", "銳雲綠貝");
	}
	
	//关于-公司名称
	public static Map<String, String> teamNameMap = new HashMap<String, String>();
	static {
		teamNameMap.put("gov", "厦门天锐科技股份有限公司");
		teamNameMap.put("free", "厦门天锐科技股份有限公司");
		teamNameMap.put("corp", "厦门天锐科技股份有限公司");
		teamNameMap.put("rylb", "宁波锐云软件有限公司");
	}
	
	//关于-公司地址
	public static Map<String, String> suportAddrMap = new HashMap<String, String>();
	static {
		suportAddrMap.put("gov", "http://www.tipray.com");
		suportAddrMap.put("free", "http://www.tipray.com");
		suportAddrMap.put("corp", "http://www.tipray.com");
		suportAddrMap.put("rylb", "http://www.ldfile.com");
	}
	
	//session有效时长配置
	public static int SESSION_EXPIRATION = 30; // 30 seconds (session.getMaxInactiveInterval())
	public static String PROPERTY_SESSION_EXPIRATION = "property.session.expiration";
	
	//是否启动exe进程提取文本内容
	public static boolean DOCUMENT_TEXT_EXTRACTED_BY_EXE = true;
	public static final String PROPERTY_DOCUMENT_TEXT_EXTRACTED_BY_EXE = "document.text.extracted.by.exe";
	
	//系统维护功能是否可见（用于开启平时隐藏的一些功能，比如“文件库表清理”、“重建索引”）
	public static boolean SYSTEM_MAINTAIN_FUNTION_VISIABLE = false;
	public static final String PROPERTY_SYSTEM_MAINTAIN_FUNTION_VISIABLE = "system.maintain.function.visiable";

	//智能备份是否彻底删除文件开关
	public static boolean LDFBS_BACKUP_THOROUGHLY_DELETING = false;
	public static final String PROPERTY_LDFBS_BACKUP_THOROUGHLY_DELETING = "ldfbs.backup.thoroughly_deleting";

	
	//文件持续修改的话间隔多少分钟备份一个版本
	public static int LDFBS_BACKUP_INTERTIME_MINUTES = 20;
	public static final String PROPERTY_LDFBS_BACKUP_INTERTIME_MINUTES = "ldfbs.backup.intertime.judge.minutes";

	//本地存储多少个版本
	public static int LDFBS_BACKUP_VERSION_NUMBER = 40;
	public static final String PROPERTY_LDFBS_BACKUP_VERSION_NUMBER = "ldfbs.backup.version.judge.number";

	//本地存储多少个版本
	public static int LDFBS_BACKUP_UPLOAD_SIZE = 1024;
	public static final String PROPERTY_LDFBS_BACKUP_UPLOAD_SIZE = "ldfbs.backup.version.judge.size";

	//超时未备份判断依据
	public static int LDFBS_BACKUP_OVERTIME_JUDGE_DAYS = 1;
	public static final String PROPERTY_LDFBS_BACKUP_OVERTIME_JUDGE_DAYS = "ldfbs.backup.overtime.judge.days";
	
	//备份消息提醒接收邮箱
	public static String LDFBS_BACKUP_MSG_REC_EMAIL = "";
	public static final String PROPERTY_LDFBS_BACKUP_MSG_REC_EMAIL = "ldfbs.backup.msg.rec.email";
	
	//备份客户端安装完毕之后是否默认隐藏标志（0否1是）
	public static int LDFBS_CLIENT_HIDDEN_FLAG = 1;
	public static final String PROPERTY_LDFBS_CLIENT_HIDDEN_FLAG = "ldfbs.client.hidden.flag";
	
	//是否需要将备份信息上报，对于内网客户或者没安装备份的，就不要上报了
	public static boolean LDFBS_BACKUP_INFO_REPORT_NEED = false;
	public static final String PROPERTY_LDFBS_BACKUP_INFO_REPORT_NEED = "ldfbs.backup.info.report.need";
	
	//备份信息接收电话
	public static String LDFBS_BACKUP_MSG_REC_PHONE = "";
	public static final String PROPERTY_LDFBS_BACKUP_MSG_REC_PHONE = "ldfbs.backup.msg.rec.phone";
	
	//接口调用信息是否打印(用于在拦截器中打印接口消息使用)
	public static boolean WEBSERVICE_INVOKE_LOG_PRINT = false;
	public static final String PROPERTY_WEBSERVICE_INVOKE_LOG_PRINT = "webservice.invoke.log.print";
	
	//限制账户
	public static String LIMITED_ACCOUNTS = "";
	public static final String PROPERTY_LIMITED_ACCOUNTS = "limited.public.accounts"; //限制公用账户
	
	//智能备份时间限制
	public static String LDFBS_LIMITED_TIME = "00:00:00-00:00:00";
	public static final String PROPERTY_LDFBS_LIMITED_TIME = "ldfbs.limited.time";
	
	//服务器表示uuid，用于备份客户端判断是否切换了服务器，重新开始备份
	public static String SERVER_IDENTIFY_UUID = UUID.randomUUID().toString();
	public static final String PROPERTY_SERVER_IDENTIFY_UUID = "server.identify.uuid";
	
	//备份客户端最低版本要求
	public static String LDFBS_VERSION_LOWEST = "3.03.160520.SC";
	public static final String PROPERTY_LDFBS_VERSION_LOWEST = "ldfbs.version.lowest";
	
	//服务器外网ip
	public static String SERVER_OUTER_IP;
	public static String PROPERTY_SERVER_OUTER_IP = "server.outer.ip";
	
	//服务器外网端口
	public static int SERVER_OUTER_PORT;
	public static String PROPERTY_SERVER_OUTER_PORT = "server.outer.port";
	
	//终端绑定
	public static boolean USER_BIND_CLIENT_SWITCH = false; //是否开启终端绑定功能（比较特殊，默认是不开的）
	public static String PROPERTY_USER_BIND_CLIENT_SWITCH = "user.bind.client.switch";
	
	//默认每个账号绑定终端个数（admin不限制）
	public static int USER_BIND_CLIENT_NUM = 1;
	public static String PROPERTY_USER_BIND_CLIENT_NUM = "user.bind.client.num";
	
	//文件预览支持NTKO控件
	public static boolean FILE_PREVIEW_USING_OPENOFFICE = false;
	public static String PROPERTY_FILE_PREVIEW_USING_OPENOFFICE = "file.preview.using.openoffice";
	
	//文件预览使用ASPOSE控件
	public static boolean FILE_PREVIEW_USING_ASPOSE = true;
	public static String PROPERTY_FILE_PREVIEW_USING_ASPOSE = "file.preview.using.aspose";
	
	//文件预览使用ASPOSE控件
	public static boolean FILE_PREVIEW_USING_YOZO = false;
	public static String PROPERTY_FILE_PREVIEW_USING_YOZO = "file.preview.using.yozo";
		
	//智能备份菜单权限
	//因备份菜单目前较少，且只有部分客户有不要“删除”菜单的需求，故不跟云文档一样做成“用户属性”这样的单独模块，做成高级配置以支持备份菜单的可配置
	//等备份扩展了菜单和功能按钮时，可以再来考虑增加单独配置模块
	public static boolean LDFBS_RECOVER_MENU_VISIBLE = true;
	public static final String PROPERTY_LDFBS_RECOVER_MENU_VISIBLE = "ldfbs.recover.menu.visible";
	
	public static boolean LDFBS_DOWNLOAD_MENU_VISIBLE = true;
	public static final String PROPERTY_LDFBS_DOWNLOAD_MENU_VISIBLE = "ldfbs.download.menu.visible";
	
	public static boolean LDFBS_DELETE_MENU_VISIBLE = true;
	public static final String PROPERTY_LDFBS_DELETE_MENU_VISIBLE = "ldfbs.delete.menu.visible";
	
	//是否对图片添加水印
	public static boolean LDDSM_IMGPDF_ADD_WATERMARK = true;
	public static final String PROPERTY_LDDSM_IMGPDF_ADD_WATERMARK = "lddsm.imgpdf.add.watermark";
	
	//添加水印使用的文字
	public static String LDDSM_IMGPDF_WATERMARK_CONTENT = "厦门天锐科技股份有限公司";
	public static String PROPERTY_LDDSM_IMGPDF_WATERMARK_CONTENT = "lddsm.imgpdf.watermark.content";

	//是否持续从绿盾Dblist.mark文件中读取操作员id，进行智能备份终端的绑定
	public static boolean LDFBS_CLIENT_BIND_FROM_LD = false;
	public static final String PROPERTY_LDFBS_CLIENT_BIND_FROM_LD = "ldfbs.client.bind.from.ld";
	
	//工作流功能是否开启开关
	public static boolean ACTIVITI_MODULE_ON = false; //工作流功能是否开启开关
	public static final String PROPERTY_ACTIVITI_MODULE_ON = "activiti.module.on";
	
	//activiti工作流接口地址
	public static String ACTIVITI_SERVICE_ADDRESS="http://127.0.0.1:8180/tractiviti";
	public static String PROPERTY_ACTIVITI_SERVICE_ADDRESS = "activiti.service.address";
	
	//工作流相关开关
	public static boolean LDFBS_RECOVER_NEED_APPROVAL = false; //智能备份恢复是否需要审批
	public static final String PROPERTY_LDFBS_RECOVER_NEED_APPROVAL = "ldfbs.recover.need.approval";
	public static boolean LDDSM_FILE_SHARE_NEED_APPROVAL = false; //云文档个人文档共享是否需要审批
	public static final String PROPERTY_LDDSM_FILE_SHARE_NEED_APPROVAL = "lddsm.file.share.need.approval";
	
	//oem
	public static String logoOem = ""; //logo图片OEM类型（lnga：辽宁公安厅警卫局） 
	
	//NAC参数
	public static String NAC_LOGIN_AUTH_ACCESS_DURATION = "1D";
	public static final String PROPERTY_NAC_LOGIN_AUTH_ACCESS_DURATION = "nac.login.auth.access.duration";
	
	/**
	 * Get url base
	 */
	private static String getBase(String url) {
		int idx = url.lastIndexOf('/');
		String ret = "";
		
		if (idx > 0) {
			ret = url.substring(0, idx);
		}
		
		return ret;
	}
	
	/**
	 * Load lddsm configuration from lddsm.cfg
	 */
	public static Properties load(ServletContext sc) {
		Properties config = new Properties();
		CONTEXT = sc.getContextPath().isEmpty() ? "" : sc.getContextPath().substring(1);
		
		// Initialize DTD location
		// TODO Add trailing "/" when upgrade to Tomcat 8 => "WEB-INF/classes/dtd/"
		DTD_BASE = sc.getRealPath("WEB-INF/classes/dtd");
		log.info("** Application {} has DTDs at {} **", sc.getServletContextName(), DTD_BASE);
		
		// Initialize language profiles location
		// TODO Add trailing "/" when upgrade to Tomcat 8 => "WEB-INF/classes/lang-profiles/"
		LANG_PROFILES_BASE = sc.getRealPath("WEB-INF/classes/lang-profiles");
		log.info("** Language profiles at {} **", LANG_PROFILES_BASE);
		
		FileInputStream fis = null;
		try{
			Properties packConfig = new Properties();
			SYSTEM_PACK_PROPTIES_BASE = sc.getRealPath("WEB-INF/classes/pack/");
			fis = new FileInputStream(SYSTEM_PACK_PROPTIES_BASE + File.separator + SYSTEM_PACK_PROPTIES_FILE);
			packConfig.load(fis);
			SYSTEM_PACKING_TYPE = packConfig.getProperty(PROPERTY_SYSTEM_PACKING_TYPE);
			VERSION_STABLE = Boolean.valueOf(packConfig.getProperty("stable"));
		}catch(Exception e){
			SYSTEM_PACKING_TYPE = PACKING_TYPE_GOV;
			log.warn("** IOError reading {} **", SYSTEM_PACK_PROPTIES_BASE + SYSTEM_PACK_PROPTIES_FILE);
		}finally{
		}
		SYSTEM_TITLE = getSystemTitle();
		SYSTEM_TITLE_EN = getSystemTitleEn();
		SYSTEM_TILE_TW = getSystemTitleTw();
		
		// Read config
		try {
			/*文件不存在
			 * FileInputStream fis = new FileInputStream(configFile);
			config.load(fis);
			fis.close();*/
			
			// Hibernate
			HIBERNATE_DIALECT = config.getProperty(PROPERTY_HIBERNATE_DIALECT, HIBERNATE_DIALECT);
			values.put(PROPERTY_HIBERNATE_DIALECT, HIBERNATE_DIALECT);
			HIBERNATE_HBM2DDL = config.getProperty(PROPERTY_HIBERNATE_HBM2DDL, HIBERNATE_HBM2DDL);
			values.put(PROPERTY_HIBERNATE_HBM2DDL, HIBERNATE_HBM2DDL);
			HIBERNATE_SHOW_SQL = config.getProperty(PROPERTY_HIBERNATE_SHOW_SQL, HIBERNATE_SHOW_SQL);
			values.put(PROPERTY_HIBERNATE_SHOW_SQL, HIBERNATE_SHOW_SQL);
			HIBERNATE_STATISTICS = config.getProperty(PROPERTY_HIBERNATE_STATISTICS, HIBERNATE_STATISTICS);
			values.put(PROPERTY_HIBERNATE_STATISTICS, HIBERNATE_STATISTICS);
			HIBERNATE_SEARCH_ANALYZER = config.getProperty(PROPERTY_HIBERNATE_SEARCH_ANALYZER, HIBERNATE_SEARCH_ANALYZER);
			values.put(PROPERTY_HIBERNATE_SEARCH_ANALYZER, HIBERNATE_SEARCH_ANALYZER);
			HIBERNATE_CREATE_AUTOFIX = config.getProperty(PROPERTY_HIBERNATE_CREATE_AUTOFIX, HIBERNATE_CREATE_AUTOFIX);
			values.put(PROPERTY_HIBERNATE_CREATE_AUTOFIX, HIBERNATE_CREATE_AUTOFIX);
			HIBERNATE_SEARCH_INDEX_EXCLUSIVE = config.getProperty(PROPERTY_HIBERNATE_SEARCH_INDEX_EXCLUSIVE, HIBERNATE_SEARCH_INDEX_EXCLUSIVE);
			values.put(PROPERTY_HIBERNATE_SEARCH_INDEX_EXCLUSIVE, HIBERNATE_SEARCH_INDEX_EXCLUSIVE);
			
			SYSTEM_MULTIPLE_INSTANCES = "on".equalsIgnoreCase(config.getProperty(PROPERTY_SYSTEM_MULTIPLE_INSTANCES, "off"));
			values.put(PROPERTY_SYSTEM_MULTIPLE_INSTANCES, Boolean.toString(SYSTEM_MULTIPLE_INSTANCES));
			
			if (config.getProperty(PROPERTY_REPOSITORY_NATIVE) != null) {
				REPOSITORY_NATIVE = "on".equalsIgnoreCase(config.getProperty(PROPERTY_REPOSITORY_NATIVE, "off"));
			}
			
			values.put(PROPERTY_REPOSITORY_NATIVE, Boolean.toString(REPOSITORY_NATIVE));
			
			
			
			values.put(PROPERTY_REPOSITORY_CACHE_HOME, REPOSITORY_CACHE_HOME);
			REPOSITORY_CACHE_DXF = REPOSITORY_CACHE_HOME + File.separator + "dxf";
			values.put("repository.cache.dxf", REPOSITORY_CACHE_DXF);
			REPOSITORY_CACHE_PDF = REPOSITORY_CACHE_HOME + File.separator + "pdf";
			values.put("repository.cache.pdf", REPOSITORY_CACHE_PDF);
			REPOSITORY_CACHE_SWF = REPOSITORY_CACHE_HOME + File.separator + "swf";
			values.put("repository.cache.swf", REPOSITORY_CACHE_SWF);
			values.put(PROPERTY_HIBERNATE_SEARCH_INDEX_HOME, HIBERNATE_SEARCH_INDEX_HOME);
			values.put(PROPERTY_REPOSITORY_DATASTORE_BACKEND, REPOSITORY_DATASTORE_BACKEND);
			values.put(PROPERTY_REPOSITORY_DATASTORE_HOME, REPOSITORY_DATASTORE_HOME);
			values.put(PROPERTY_REPOSITORY_CONFIG, REPOSITORY_CONFIG);
			values.put(PROPERTY_REPOSITORY_HOME, REPOSITORY_HOME);
			
			JBPM_CONFIG = INSTANCE_HOME + File.separator + "jbpm.xml";
			values.put("jbpm.config", JBPM_CONFIG);
			
			PROPERTY_GROUPS_XML = INSTANCE_HOME + File.separator + "PropertyGroups.xml";
			values.put("property.groups.xml", PROPERTY_GROUPS_XML);
			PROPERTY_GROUPS_CND = INSTANCE_HOME + File.separator + "PropertyGroups.cnd";
			values.put("property.groups.cnd", PROPERTY_GROUPS_CND);
			
			for (Entry<String, String> entry : values.entrySet()) {
				log.info("LOAD - {}={}", entry.getKey(), entry.getValue());
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.warn("** IOError reading {}, set default config **", lddsm_CONFIG);
		}
		
		return config;
	}
	
	/**
	 * Reload lddsm configuration from database
	 */
	public static void reload(ServletContext sc, Properties cfg) {
	}
	
	
	public static String getSystemTitle(){
		return titleMap.get(SYSTEM_PACKING_TYPE);
	}

	public static String getSystemTitleEn(){
		return titleMapEn.get(SYSTEM_PACKING_TYPE);
	}
	
	public static String getSystemTitleTw(){
		return titleMapTw.get(SYSTEM_PACKING_TYPE);
	}
	
}
