-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` (USER_ID, USER_NAME, PASSWORD, PASSWORD_SALT) VALUES ('1', 'admin', '1637fcf8b38df9216beeab1ee78cabb8', '84012b1f7ab582eb1a0eb46ed08fb5a9');

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` (ROLE_ID, ROLE_CODE, ROLE_DESC, ROLE_NAME, ROL_PARENT_ID) VALUES ('1', 'administrator', '管理员', '用户管理员', '1');
INSERT INTO `auth_role` (ROLE_ID, ROLE_CODE, ROLE_DESC, ROLE_NAME, ROL_PARENT_ID) VALUES ('2', 'secadmin', '策略管理', '策略管理员', '2');
INSERT INTO `auth_role` (ROLE_ID, ROLE_CODE, ROLE_DESC, ROLE_NAME, ROL_PARENT_ID) VALUES ('3', 'sysadmin', '系统管理', '系统管理员', '3');
INSERT INTO `auth_role` (ROLE_ID, ROLE_CODE, ROLE_DESC, ROLE_NAME, ROL_PARENT_ID) VALUES ('4', 'logadmin', '日志管理', '日志管理员', '4');

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` (ID, ROLE_ID, USER_ID) VALUES ('1', '1', '1');

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('1', 'F', 'user:list', '用户列表', '/user/list');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('2', 'T', 'user:add', '添加新用户', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('3', 'T', 'user:delete', '删除用户', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('4', 'T', 'user:showroles', '查看用户角色', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('5', 'T', 'user:corelationrole', '添加用户角色关联', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('6', 'F', 'role:list', '角色列表', '/role/list');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('7', 'T', 'role:add', '添加新角色', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('8', 'T', 'role:delete', '删除角色', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('9', 'T', 'role:findinfo', '查看角色信息', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('10', 'T', 'role:corelationperm', '添加角色权限关联', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('11', 'T', 'role:showperms', '查看角色权限', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('12', 'F', 'perm:list', '权限列表', '/perm/list');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('13', 'T', 'perm:add', '添加新权限', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('14', 'T', 'perm:delete', '删除权限', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('15', 'T', 'perm:update', '更新权限信息', '');
INSERT INTO `auth_permission` ('PERMISSION_ID', 'IS_NAVI', 'PERMISSION_CODE', 'PERMISSION_DESC', 'URL') VALUES ('16', 'T', 'perm:deptTree', '部门架构', '');

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '1');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '2');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '3');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '4');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '5');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '6');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '7');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '8');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '9');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '10');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '11');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '12');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '13');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '14');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '15');
INSERT INTO `auth_role_permission` (ROLE_ID, PERMISSION_ID) VALUES ('1', '16');
