package com.wisdom.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.entity.TreeModel;
import com.wisdom.service.DeptService;

@Controller
@RequestMapping(value="/dept")
public class DepartmentController {
	
	// 表示根目录的id  
	public static final int ROOT = 0;  
	
	@Autowired
	DeptService deptService;
	
	@RequiresPermissions("perm:deptTree")
	@RequestMapping(value="/deptTree", method= RequestMethod.POST )
	@ResponseBody
	public List<TreeModel> deptTree() {
		int id = ROOT;  
		TreeModel tm = deptService.selectTree(id, false);
		return tm.getChildren();
	}
	

}
