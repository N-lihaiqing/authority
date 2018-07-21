package com.wisdom.service;

import java.util.List;

import com.wisdom.entity.Permission;

public interface PermissionService {
	
	int addPermission(Permission permission);

	void deletePermission(int permissionId);

	void deleteMorePermissions(int... permIds);

	Permission findById(int permId);

	List<Permission> getPermissionsByRoleId(int roleId);

	List<Permission> getAllPermissions();

	void updatePermission(Permission permission);
}
