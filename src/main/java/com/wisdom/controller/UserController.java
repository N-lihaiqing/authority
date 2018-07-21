package com.wisdom.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wisdom.core.GridPage;
import com.wisdom.core.PageVo;
import com.wisdom.entity.Role;
import com.wisdom.entity.User;
import com.wisdom.service.RoleService;
import com.wisdom.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
//	@Autowired
//	private PasswordService passwordService;

	@RequiresPermissions("user:list")
	@RequestMapping("/list")
	public ModelAndView showUserList() {
		ModelAndView mav = new ModelAndView("userList");
		return mav;
	}
	
	@RequiresPermissions("user:list")
	@RequestMapping("/userPage")
	@ResponseBody
	public GridPage<User> userList(@ModelAttribute User user, @ModelAttribute PageVo page){
		List<User> list = userService.getAllUsers();
		return new GridPage<>(list.size(), list);
	}
	
	

	@RequiresPermissions("user:add")
	@RequestMapping("/add")
	@ResponseBody
	public User addUser(User user, int... roleIds) {
		userService.addUser(user, roleIds);
		return user;
	}

	@RequiresPermissions("user:showroles")
	@RequestMapping("/showroles")
	@ResponseBody
	public List<Role> shwoRoles(String userName) {
		return roleService.getRolesByUserName(userName);
	}

	@RequiresPermissions("role:list")
	@RequestMapping("/listRoles")
	@ResponseBody
	public List<Role> getRoles() {
		return roleService.getAllRoles();
	}

	@RequiresPermissions("user:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deleteUser(int userId) {
		userService.deleteUser(userId);
	}

	@RequiresPermissions("user:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMoreUsers(int... userIds) {
		userService.deleteMoreUsers(userIds);
	}

	@RequiresPermissions("user:corelationrole")
	@RequestMapping("/corelationRole")
	@ResponseBody
	public void corelationRole(int userId, int... roleIds) {
		userService.updateUserRoles(userId, roleIds);
	}
}
