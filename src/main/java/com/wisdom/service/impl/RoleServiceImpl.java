package com.wisdom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdom.dao.RoleDao;
import com.wisdom.entity.Role;
import com.wisdom.entity.RolePermission;
import com.wisdom.exception.DatabaseException;
import com.wisdom.service.RoleService;

@Transactional
@Service(value="roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public int addRole(Role role, int... permissionIds) {
		try {
			roleDao.addRole(role);
			if (permissionIds != null && permissionIds.length > 0) {
				for (int permissionId : permissionIds) {
					roleDao.addRolePermission(new RolePermission(role.getRoleId(), permissionId));
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return role.getRoleId();
	}

	@Override
	public void deleteRole(int roleId) {
		try {
			
			roleDao.deleteUserRole(roleId);
			roleDao.deleteRolePermission(roleId);
			roleDao.deleteRole(roleId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void deleteMoreRoles(int... roleIds) {
		if (roleIds != null && roleIds.length > 0) {
			for (int roleId : roleIds) {
				deleteRole(roleId);
			}
		}
	}

	@Override
	public Role getRoleById(int roleId) {
		try {
			return roleDao.findByRoleId(roleId);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Role> getRolesByUserName(String userName) {
		try {
			return roleDao.findRolesByUserName(userName);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Role> getAllRoles() {
		try {
			return roleDao.findAllRoles();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRole(Role role, int... permIds) {
		try {
			roleDao.updateRole(role);
			roleDao.deleteRolePermission(role.getRoleId());
			addRolePermissions(role.getRoleId(), permIds);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void addRolePermissions(int roleId, int... permissionIds) {
		try {
			if (permissionIds != null && permissionIds.length > 0) {
				for (int permissionId : permissionIds) {
					roleDao.addRolePermission(new RolePermission(roleId, permissionId));
				}
			}
		} catch (Exception e) {
			
		}
	}

}
