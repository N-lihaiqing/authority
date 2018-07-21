package com.wisdom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdom.dao.PermissionDao;
import com.wisdom.entity.Permission;
import com.wisdom.exception.DatabaseException;
import com.wisdom.service.PermissionService;

@Transactional
@Service(value="permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public int addPermission(Permission permission) {
		try {
			permissionDao.addPermission(permission);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permission.getPermissionId();
	}

	@Override
	public void deletePermission(int permissionId) {
		try {
			permissionDao.deleteRolePermission(permissionId);
			permissionDao.deletePermission(permissionId);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMorePermissions(int... permIds) {
		if (permIds != null && permIds.length > 0) {
			for (int permId : permIds) {
				deletePermission(permId);
			}
		}
	}

	@Override
	public Permission findById(int permId) {
		try {
			return permissionDao.findById(permId);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Permission> getPermissionsByRoleId(int roleId) {
		try {
			return permissionDao.findPermissionsByRoleId(roleId);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Permission> getAllPermissions() {
		try {
			return permissionDao.findAllPermissions();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePermission(Permission permission) {
		try {
			permissionDao.updatePermission(permission);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
