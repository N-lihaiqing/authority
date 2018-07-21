package com.wisdom.mapper;

import java.util.List;

import com.wisdom.entity.Role;
import com.wisdom.entity.RolePermission;

public interface RoleMapper {
	void addRole(Role role);
	void deleteRole(Long roleId);
	Role findById(Long roleId);
	List<Role> findRolesByUserName(String userName);
	List<Role> findAllRoles();
	void updateRole(Role role);

	void deleteUserRole(Long roleId);
	void deleteRolePermission(Long roleId);
	void addRolePermission(RolePermission rolePermission);
}
