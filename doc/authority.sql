/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : auth_control

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2018-01-13 23:42:36
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for `auth_permission`
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `bPermissionId` bigint(20) NOT NULL,
  `tIsNavi` char(1) NOT NULL DEFAULT 'F',
  `vPermissionCode` varchar(30) DEFAULT NULL,
  `vPermissionDesc` varchar(30) DEFAULT NULL,
  `vUrl` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`bPermissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('1', 'F', 'user:list', '用户列表', '/user/list');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('2', 'T', 'user:add', '添加新用户', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('3', 'T', 'user:delete', '删除用户', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('4', 'T', 'user:showroles', '查看用户角色', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('5', 'T', 'user:corelationrole', '添加用户角色关联', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('6', 'F', 'role:list', '角色列表', '/role/list');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('7', 'T', 'role:add', '添加新角色', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('8', 'T', 'role:delete', '删除角色', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('9', 'T', 'role:findinfo', '查看角色信息', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('10', 'T', 'role:corelationperm', '添加角色权限关联', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('11', 'T', 'role:showperms', '查看角色权限', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('12', 'F', 'perm:list', '权限列表', '/perm/list');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('13', 'T', 'perm:add', '添加新权限', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('14', 'T', 'perm:delete', '删除权限', '');
INSERT INTO `auth_permission`(bPermissionId, tIsNavi, vPermissionCode, vPermissionDesc, vUrl) VALUES ('15', 'T', 'perm:update', '更新权限信息', '');

-- ----------------------------
-- Table structure for `auth_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `bRoleId` bigint(20) NOT NULL,
  `vRoleCode` varchar(15) DEFAULT NULL,
  `vRoleDesc` varchar(30) DEFAULT NULL,
  `vRoleName` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`bRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` (bRoleId, vRoleCode, vRoleDesc, vRoleName) VALUES ('1', 'userAdmin', '用户管理', '用户管理员');
INSERT INTO `auth_role` (bRoleId, vRoleCode, vRoleDesc, vRoleName) VALUES ('2', 'classAdmin', '类目管理', '类目管理员');
INSERT INTO `auth_role` (bRoleId, vRoleCode, vRoleDesc, vRoleName) VALUES ('3', 'commodityAdmin', '商品管理', '商品管理员');
INSERT INTO `auth_role` (bRoleId, vRoleCode, vRoleDesc, vRoleName) VALUES ('4', 'commentAdmin', '评论管理', '评论管理员');

-- ----------------------------
-- Table structure for `auth_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission` (
  `bPermissionId` bigint(20) NOT NULL,
  `bRoleId` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1001', '1', '1');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1002', '1', '2');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1003', '1', '3');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1004', '1', '4');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1005', '1', '5');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1006', '1', '6');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1007', '1', '7');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1008', '1', '8');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1009', '1', '9');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1010', '1', '10');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1011', '1', '11');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1012', '1', '12');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1013', '1', '13');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1014', '1', '14');
INSERT INTO `auth_role_permission` (id, bRoleId, bPermissionId) VALUES ('1015', '1', '15');

-- ----------------------------
-- Table structure for `auth_user`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `bUserId` bigint(20) NOT NULL,
  `cPassword` varchar(32) NOT NULL,
  `cPasswordSalt` varchar(32) NOT NULL,
  `vUserName` varchar(15) NOT NULL,
  PRIMARY KEY (`bUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` (bUserId, vUserName, cPassword, cPasswordSalt) VALUES ('1', 'admin', '1637fcf8b38df9216beeab1ee78cabb8', '84012b1f7ab582eb1a0eb46ed08fb5a9');
INSERT INTO `auth_user` (bUserId, vUserName, cPassword, cPasswordSalt) VALUES ('2', 'sisu', '05be14bd7947b8ee42f2f5aed24fada1', '35fcf26ebc6db17471d33ae21dab1f16');
INSERT INTO `auth_user` (bUserId, vUserName, cPassword, cPasswordSalt) VALUES ('3', 'lailai', '0e04ce8cf9e1dfa071940aac54fb7d87', '4eb75ed8d81b48a0e6e612694ee91582');
INSERT INTO `auth_user` (bUserId, vUserName, cPassword, cPasswordSalt) VALUES ('4', 'mingming', 'e1ad6a808a8bddbd858c98befc16b763', '90ccceaeff871d1c764f51574961b244');

-- ----------------------------
-- Table structure for `auth_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` bigint(20) NOT NULL,
  `bRoleId` bigint(20) NOT NULL,
  `bUserId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('17', '1', '2');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('18', '2', '2');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('19', '3', '2');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('20', '4', '2');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('27', '2', '3');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('28', '3', '3');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('29', '4', '3');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('30', '1', '1');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('31', '2', '1');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('32', '3', '1');
INSERT INTO `auth_user_role` (id, bRoleId, bUserId) VALUES ('33', '4', '1');

