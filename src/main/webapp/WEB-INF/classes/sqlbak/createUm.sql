--以下内容客户端登陆时服务器端为每台电脑自动创建--
-- ----------------------------
-- Table structure for `ldfbs_node_base_###`/*节点基本信息表，包含文件节点和目录节点*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_base_###`;
CREATE TABLE `ldfbs_node_base_###` (
  `NBS_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NBS_AUTHOR` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NBS_CONTEXT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NBS_CREATED` datetime DEFAULT NULL,
  `NBS_NAME` varchar(256) COLLATE utf8_general_ci DEFAULT NULL,
  `NBS_PARENT` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NBS_PATH` varchar(1024) COLLATE utf8_general_ci DEFAULT NULL,
  `NDC_SCRIPT_CODE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NDC_SCRIPTING` char(1) COLLATE utf8_bin NOT NULL,
  `NDC_DELETED` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'F',
  `NDC_ISFOLDER` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'F',
  `NBS_LAST_MODIFIED` datetime DEFAULT NULL,
  PRIMARY KEY (`NBS_UUID`),
  KEY `IDX_NODE_BASE_PARENT` (`NBS_PARENT`)
)DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `ldfbs_node_document_###`/*文件信息表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_document_###`;
CREATE TABLE `ldfbs_node_document_###`(
  `NDC_CHECKED_OUT` char(1) COLLATE utf8_bin NOT NULL,
  `NDC_DESCRIPTION` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `NDC_LANGUAGE` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `NDC_LAST_MODIFIED` datetime DEFAULT NULL,
  `NLK_CREATED` datetime DEFAULT NULL,
  `NLK_OWNER` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NLK_TOKEN` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDC_LOCKED` char(1) COLLATE utf8_bin NOT NULL,
  `NDC_MIME_TYPE` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `NDC_TEXT_EXTRACTED` char(1) COLLATE utf8_bin NOT NULL,
  `NDC_EXTRACT_FAIL_TIME`  bigint(20) NOT NULL DEFAULT 0 ,
  `NDC_TITLE` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `NBS_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NDC_LAST_CHECKSUM` varchar(64) COLLATE utf8_bin NOT NULL,
  `NDC_LAST_PATHID` bigint(20) NOT NULL,
  `NDC_TEXT_INDEXED`  char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'F' ,
  PRIMARY KEY (`NBS_UUID`),
  KEY `FK_NODE_DOCUMENT_NBS_UUID` (`NBS_UUID`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `ldfbs_node_document_text_###`/*文件信息表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_document_text_###`;
CREATE TABLE `ldfbs_node_document_text_###`(
  `NBS_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NDC_TEXT` longtext COLLATE utf8_bin,
  PRIMARY KEY (`NBS_UUID`),
  KEY `FK_DOCUMENT_TEXT_NBS_UUID` (`NBS_UUID`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `ldfbs_node_document_version_###`/*文件版本表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_document_version_###`;
CREATE TABLE `ldfbs_node_document_version_###` (
  `NDV_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NDV_AUTHOR` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_CHECKSUM` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_COMMENT` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `NDV_CONTENT` longblob,
  `NDV_CREATED` datetime DEFAULT NULL,
  `NDV_CURRENT` char(1) COLLATE utf8_bin NOT NULL,
  `NDV_MIME_TYPE` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `NDV_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_PARENT` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_PREVIOUS` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_SIZE` bigint(20) DEFAULT NULL,
  `NDV_STORE_PATH` bigint(20) NOT NULL,
  PRIMARY KEY (`NDV_UUID`),
  UNIQUE KEY `NDV_PARENT` (`NDV_PARENT`,`NDV_NAME`),
  KEY `IDX_NOD_DOC_VER_PARENT` (`NDV_PARENT`),
  KEY `IDX_NOD_DOC_VER_PARCUR` (`NDV_PARENT`,`NDV_CURRENT`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `ldfbs_node_folder_UMID`/*目录信息表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_folder_###`;
CREATE TABLE `ldfbs_node_folder_###` (
  `NFL_DESCRIPTION` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `NBS_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NFL_REIDX_TIME`  bigint(20) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`NBS_UUID`),
  KEY `FK_NODE_FOLDER_NBS_UUID` (`NBS_UUID`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `ldfbs_task_###`/*任务表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_task_###`;
CREATE TABLE `ldfbs_task_###` (
  `T_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `T_TYPE` int DEFAULT '0' NOT NULL,
  `T_CREATED` datetime DEFAULT NULL,
  `T_CHECKSUM` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `T_SIZE` bigint(20) DEFAULT '0',
  `T_SPATH` varchar(1024) COLLATE utf8_general_ci NOT NULL,
  `T_SVERSION` varchar(64),
  `SPH_ID` bigint(20) NOT NULL DEFAULT '0',
  `T_DPATH` varchar(1024) COLLATE utf8_general_ci DEFAULT NULL,
  `T_ETYPE` int DEFAULT '0' NOT NULL,
  `T_RTYPE` int DEFAULT '0' NOT NULL,
  `FS_IP`  			varchar(80),
  `FS_PORT`  		varchar(20),
  `FS_ALIAS_NAME`  	varchar(50),
  PRIMARY KEY (`T_UUID`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--以下内容客户端登陆时服务器端为每台电脑自动创建--
-- ----------------------------
-- Table structure for `ldfbs_node_base_###`/*节点基本信息表，包含文件节点和目录节点*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_base_v_###`;
CREATE TABLE `ldfbs_node_base_v_###` (
  `NBS_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NBS_AUTHOR` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NBS_CONTEXT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NBS_CREATED` datetime DEFAULT NULL,
  `NBS_NAME` varchar(256) COLLATE utf8_general_ci DEFAULT NULL,
  `NBS_PARENT` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NBS_PATH` varchar(1024) COLLATE utf8_general_ci DEFAULT NULL,
  `NDC_SCRIPT_CODE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NDC_SCRIPTING` char(1) COLLATE utf8_bin NOT NULL,
  `NDC_DELETED` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'F',
  `NDC_ISFOLDER` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'F',
  `NBS_LAST_MODIFIED` datetime DEFAULT NULL,
  PRIMARY KEY (`NBS_UUID`),
  KEY `IDX_NODE_BASE_PARENT` (`NBS_PARENT`)
)DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `ldfbs_node_document_###`/*文件信息表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_document_v_###`;
CREATE TABLE `ldfbs_node_document_v_###`(
  `NDC_CHECKED_OUT` char(1) COLLATE utf8_bin NOT NULL,
  `NDC_DESCRIPTION` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `NDC_LANGUAGE` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `NDC_LAST_MODIFIED` datetime DEFAULT NULL,
  `NLK_CREATED` datetime DEFAULT NULL,
  `NLK_OWNER` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NLK_TOKEN` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDC_LOCKED` char(1) COLLATE utf8_bin NOT NULL,
  `NDC_MIME_TYPE` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `NDC_TEXT_EXTRACTED` char(1) COLLATE utf8_bin NOT NULL,
  `NDC_EXTRACT_FAIL_TIME`  bigint(20) NOT NULL DEFAULT 0 ,
  `NDC_TITLE` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `NBS_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NDC_LAST_CHECKSUM` varchar(64) COLLATE utf8_bin NOT NULL,
  `NDC_LAST_PATHID` bigint(20) NOT NULL,
  `NDC_TEXT_INDEXED`  char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'F' ,
  PRIMARY KEY (`NBS_UUID`),
  KEY `FK_NODE_DOCUMENT_NBS_UUID` (`NBS_UUID`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `ldfbs_node_document_text_###`/*文件信息表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_document_text_v_###`;
CREATE TABLE `ldfbs_node_document_text_v_###`(
  `NBS_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NDC_TEXT` longtext COLLATE utf8_bin,
  PRIMARY KEY (`NBS_UUID`),
  KEY `FK_DOCUMENT_TEXT_NBS_UUID` (`NBS_UUID`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `ldfbs_node_document_version_###`/*文件版本表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_document_version_v_###`;
CREATE TABLE `ldfbs_node_document_version_v_###` (
  `NDV_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NDV_AUTHOR` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_CHECKSUM` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_COMMENT` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `NDV_CONTENT` longblob,
  `NDV_CREATED` datetime DEFAULT NULL,
  `NDV_CURRENT` char(1) COLLATE utf8_bin NOT NULL,
  `NDV_MIME_TYPE` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `NDV_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_PARENT` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_PREVIOUS` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NDV_SIZE` bigint(20) DEFAULT NULL,
  `NDV_STORE_PATH` bigint(20) NOT NULL,
  PRIMARY KEY (`NDV_UUID`),
  UNIQUE KEY `NDV_PARENT` (`NDV_PARENT`,`NDV_NAME`),
  KEY `IDX_NOD_DOC_VER_PARENT` (`NDV_PARENT`),
  KEY `IDX_NOD_DOC_VER_PARCUR` (`NDV_PARENT`,`NDV_CURRENT`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `ldfbs_node_folder_UMID`/*目录信息表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_node_folder_v_###`;
CREATE TABLE `ldfbs_node_folder_v_###` (
  `NFL_DESCRIPTION` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `NBS_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `NFL_REIDX_TIME`  bigint(20) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`NBS_UUID`),
  KEY `FK_NODE_FOLDER_NBS_UUID` (`NBS_UUID`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `ldfbs_task_###`/*任务表*/
-- ----------------------------
DROP TABLE IF EXISTS `ldfbs_task_v_###`;
CREATE TABLE `ldfbs_task_v_###` (
  `T_UUID` varchar(64) COLLATE utf8_bin NOT NULL,
  `T_TYPE` int DEFAULT '0' NOT NULL,
  `T_CREATED` datetime DEFAULT NULL,
  `T_CHECKSUM` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `T_SIZE` bigint(20) DEFAULT '0',
  `T_SPATH` varchar(1024) COLLATE utf8_general_ci NOT NULL,
  `T_SVERSION` varchar(64),
  `SPH_ID` bigint(20) NOT NULL DEFAULT '0',
  `T_DPATH` varchar(1024) COLLATE utf8_general_ci DEFAULT NULL,
  `T_ETYPE` int DEFAULT '0' NOT NULL,
  `T_RTYPE` int DEFAULT '0' NOT NULL,
  `FS_IP`  			varchar(80),
  `FS_PORT`  		varchar(20),
  `FS_ALIAS_NAME`  	varchar(50),
  PRIMARY KEY (`T_UUID`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;