package com.wisdom.service;

import java.util.List;

import com.wisdom.entity.Role;

public interface RoleService {
	int addRole(Role role, int... permissionIds);

	void deleteRole(int roleId);

	void deleteMoreRoles(int... roleIds);

	Role getRoleById(int roleId);

	List<Role> getRolesByUserName(String userName);

	List<Role> getAllRoles();

	void updateRole(Role role, int... permIds);

	void addRolePermissions(int roleId, int... permissionIds);
}
