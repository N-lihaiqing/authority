--清空表auth_permission,id从0开始排序
TRUNCATE table AUTH_PERMISSION;

INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('1', 'F', 'user:list', '用户列表', '/user/list');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('2', 'T', 'user:add', '添加新用户', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('3', 'T', 'user:delete', '删除用户', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('4', 'T', 'user:showroles', '查看用户角色', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('5', 'T', 'user:corelationrole', '添加用户角色关联', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('6', 'F', 'role:list', '角色列表', '/role/list');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('7', 'T', 'role:add', '添加新角色', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('8', 'T', 'role:delete', '删除角色', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('9', 'T', 'role:findinfo', '查看角色信息', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('10', 'T', 'role:corelationperm', '添加角色权限关联', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('11', 'T', 'role:showperms', '查看角色权限', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('12', 'F', 'perm:list', '权限列表', '/perm/list');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('13', 'T', 'perm:add', '添加新权限', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('14', 'T', 'perm:delete', '删除权限', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('15', 'T', 'perm:update', '更新权限信息', '');
INSERT INTO `auth_permission`(PERMISSION_ID, IS_NAVI, PERMISSION_CODE, PERMISSION_DESC, URL) VALUES ('16', 'T', 'perm:deptTree', '部门架构', '');
