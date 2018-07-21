package com.wisdom.mapper;

import java.util.List;

import com.wisdom.entity.User;
import com.wisdom.entity.UserRole;

public interface UserMapper {
	void addUser(User user);

	void deleteUser(Long userId);

	User findUserByUserName(String userName);

	List<User> findAllUsers();

	void deleteUserRole(Long userId);

	void addUserRole(UserRole userRole);

	List<String> findRolesByUserName(String userName);

	List<String> findPermissionsByUserName(String userName);
}
