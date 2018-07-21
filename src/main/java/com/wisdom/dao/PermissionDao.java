package com.wisdom.dao;

import java.util.List;

import com.wisdom.entity.Permission;
import com.wisdom.exception.DatabaseException;

public interface PermissionDao {
	
	void addPermission(Permission permission) throws DatabaseException;

	void deletePermission(long permissionId) throws DatabaseException;

	Permission findById(long permId) throws DatabaseException;

	List<Permission> findNavisByRoleId(long roleId) throws DatabaseException;

	List<Permission> findPermissionsByRoleId(long roleId) throws DatabaseException;

	List<Permission> findAllPermissions() throws DatabaseException;

	void updatePermission(Permission permission) throws DatabaseException;

	void deleteRolePermission(long permissionId) throws DatabaseException;
}
