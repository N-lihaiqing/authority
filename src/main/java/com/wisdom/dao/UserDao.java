package com.wisdom.dao;

import java.util.List;

import com.wisdom.entity.User;
import com.wisdom.entity.UserRole;
import com.wisdom.exception.DatabaseException;

public interface UserDao {
	void addUser(User user) throws DatabaseException;

	void deleteUser(long userId) throws DatabaseException;

	User findUserByUserName(String userName) throws DatabaseException;

	List<User> findAllUsers() throws DatabaseException;

	void deleteUserRole(long userId) throws DatabaseException;

	void addUserRole(UserRole userRole) throws DatabaseException;

	List<String> findRolesByUserName(String userName) throws DatabaseException;

	List<String> findPermissionsByUserName(String userName) throws DatabaseException;
}
