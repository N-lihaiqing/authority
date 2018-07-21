--以下内容服务器初始化时会创建--
-- ----------------------------
-- Table structure for `FILESTORE_###`/*文件存储*/
-- ----------------------------
DROP TABLE IF EXISTS `FILESTORE_###`;
CREATE TABLE `FILESTORE_###` (
  `FS_CHECKSUM` varchar(64) NOT NULL,
  `FS_PATHID` bigint(20)  NOT NULL,
  `FS_MIME_TYPE` varchar(128) DEFAULT NULL,
  `FS_USED_COUNT` bigint(20) NOT NULL,
  `FS_SIZE` bigint(20)  NOT NULL,
  PRIMARY KEY (`FS_CHECKSUM`)
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;