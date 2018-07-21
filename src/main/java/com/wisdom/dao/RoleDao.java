package com.wisdom.dao;

import java.util.List;

import com.wisdom.entity.Role;
import com.wisdom.entity.RolePermission;
import com.wisdom.exception.DatabaseException;

public interface RoleDao {
	void addRole(Role role) throws DatabaseException;

	void deleteRole(long roleId) throws DatabaseException;

	Role findByRoleId(long roleId) throws DatabaseException;

	List<Role> findRolesByUserName(String userName) throws DatabaseException;

	List<Role> findAllRoles() throws DatabaseException;

	void updateRole(Role role) throws DatabaseException;

	void deleteUserRole(long roleId) throws DatabaseException;

	void deleteRolePermission(long roleId) throws DatabaseException;

	void addRolePermission(RolePermission rolePermission) throws DatabaseException;
}
