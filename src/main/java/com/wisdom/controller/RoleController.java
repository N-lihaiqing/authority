package com.wisdom.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wisdom.entity.Role;
import com.wisdom.service.PermissionService;
import com.wisdom.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@RequiresPermissions("role:list")
	@RequestMapping("/list")
	public ModelAndView showRoleList() {
		List list = roleService.getAllRoles();

		ModelAndView mav = new ModelAndView("role-list");
		mav.addObject("roles", list);
		return mav;
	}

	@RequiresPermissions("role:showperms")
	@RequestMapping("/listperms")
	@ResponseBody
	public List getPerms() {
		return permissionService.getAllPermissions();
	}

	@RequiresPermissions("role:add")
	@RequestMapping("/add")
	@ResponseBody
	public Role addRole(Role role, int... permIds) {
		roleService.addRole(role, permIds);
		return role;
	}

	@RequiresPermissions("role:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public void deleteRole(int roleId) {
		roleService.deleteRole(roleId);
	}

	@RequiresPermissions("role:delete")
	@RequestMapping("/deletemore")
	@ResponseBody
	public void deleteMoreRoles(int... roleIds) {
		roleService.deleteMoreRoles(roleIds);
	}

	@RequiresPermissions("role:showperms")
	@RequestMapping("/showroleperms")
	@ResponseBody
	public List showRolePerms(int roleId) {
		return permissionService.getPermissionsByRoleId(roleId);
	}

	@RequiresPermissions("role:findinfo")
	@RequestMapping("/getrole")
	@ResponseBody
	public Role getRoleById(int roleId) {
		return roleService.getRoleById(roleId);
	}

	@RequiresPermissions("role:corelationperm")
	@RequestMapping("/updaterole")
	@ResponseBody()
	public void updateRole(Role role, int... permIds) {
		roleService.updateRole(role, permIds);
	}
}
