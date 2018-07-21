package com.wisdom.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdom.dao.PermissionDao;
import com.wisdom.dao.RoleDao;
import com.wisdom.dao.UserDao;
import com.wisdom.entity.Navigation;
import com.wisdom.entity.Permission;
import com.wisdom.entity.Role;
import com.wisdom.entity.User;
import com.wisdom.entity.UserRole;
import com.wisdom.exception.DatabaseException;
import com.wisdom.service.PasswordService;
import com.wisdom.service.UserService;

@Transactional
@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private PasswordService passwordService;

	@Override
	public void addUser(User user, int... roleIds) {
		try {
			passwordService.encryptPassword(user);
			userDao.addUser(user);
			if (roleIds != null && roleIds.length > 0) {
				for (int roleId : roleIds) {
					userDao.addUserRole(new UserRole(user.getUserId(), roleId));
				}
			}
		} catch (Exception e) {
			
		}
	}

	@Override
	public void deleteUser(int userId) {
		try {
			
			userDao.deleteUserRole(userId);
			userDao.deleteUser(userId);
		} catch (Exception e) {
			
		}
	}

	@Override
	public void deleteMoreUsers(int... userIds) {
		try {
			
			if (userIds != null && userIds.length > 0) {
				for (int userId : userIds) {
					deleteUser(userId);
				}
			}
		} catch (Exception e) {
			
		}
	}

	@Override
	public User getUserByUserName(String userName) {
		User user = null;
		try {
			user = userDao.findUserByUserName(userName);
//			roleDao.findById(roleId);
		} catch (Exception e) {
			log.info("find user by name Error", e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		try {
			return userDao.findAllUsers();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUserRoles(int userId, int... roleIds) {
		try {
			userDao.deleteUserRole(userId);
			if (roleIds != null && roleIds.length > 0) {
				for (int roleId : roleIds) {
					userDao.addUserRole(new UserRole(userId, roleId));
				}
			}
			
		} catch (Exception e) {
			
		}
	}

	@Override
	public Set<String> findRolesByUserName(String userName) {
		try {
			return new HashSet<String>(userDao.findRolesByUserName(userName));
		} catch (DatabaseException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<String> findPermissionsByUserName(String userName) {
		try {
			return new HashSet<String>(userDao.findPermissionsByUserName(userName));
		} catch (DatabaseException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Navigation> getNavigationBar(String userName) {
		List<Navigation> navigationBar = new ArrayList<Navigation>();
		try {
			List<Role> role = roleDao.findRolesByUserName(userName);
			Navigation navigation = null;
			for(Role r : role) {
				List<Permission> per = permissionDao.findNavisByRoleId(r.getRoleId());
				if(per.size() > 0) {
					navigation = new Navigation();
					navigation.setId(r.getRoleId());
					navigation.setNavigationName(r.getRoleDesc());
					navigation.setChildNavigations(per);
					navigationBar.add(navigation);
				}
			}
		} catch (DatabaseException e) {
			log.info("get navigation bar error", e.getMessage());
		}
		return navigationBar;
	}

}
