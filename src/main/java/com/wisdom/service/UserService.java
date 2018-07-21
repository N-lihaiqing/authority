package com.wisdom.service;

import java.util.List;
import java.util.Set;

import com.wisdom.entity.Navigation;
import com.wisdom.entity.User;

public interface UserService {
	void addUser(User user, int... roleIds);// 添加用户

	void deleteUser(int userId);// 删除用户

	void deleteMoreUsers(int... userIds);// 批量删除用户

	User getUserByUserName(String userName);// 根据用户名获取用户

	List<User> getAllUsers();// 获取所有用户

	void updateUserRoles(int userId, int... roleIds);// 添加用户角色关联

	Set<String> findRolesByUserName(String userName);// 根据用户名获取用户所有角色

	Set<String> findPermissionsByUserName(String userName);// 根据用户名获取用户所有权限

	List<Navigation> getNavigationBar(String userName);// 获取导航栏内容
}
