package com.wisdom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.wisdom.core.Config;
import com.wisdom.dao.DeptDao;
import com.wisdom.entity.Department;
import com.wisdom.entity.TreeModel;
import com.wisdom.exception.DatabaseException;
import com.wisdom.service.DeptService;
import com.wisdom.util.TreeUtil;

@Transactional
@Service(value = "deptService")
public class DeptServiceImpl implements DeptService {
	
	private static Logger log = LoggerFactory.getLogger(DeptServiceImpl.class);

	@Autowired
	DeptDao deptDao;

	@Override
	public String getDeptTreeById(int id) {
		try {
			List<Long> deptIds = deptDao.findDeptTreeById(id);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public TreeModel selectTree(int id, boolean containsDept) {
		TreeModel tm = new TreeModel();
		
		// 在此只保留部门或单位的id及name属性,可拓展
		String[] str = new String[] { "getId", "getName", "", "", "getState" };
		List<Department> li = new ArrayList<>();
		try {
			Department dept = new Department();
			dept.setId(0);
			dept.setName(Config.SYSTEM_DEPARTMENT_NAME);
			dept.setParentid(0);
			dept.setState("open");
			if(id  == 0) {
				li.add(0, (dept));
			}
			this.tree(tm, li, str);
		} catch (DatabaseException e) {
			log.error(e.getMessage(), e);
		}
		return tm;
	}

//	private List<Department> selectChildren(int id) throws DatabaseException {
//		Map<String, Object> para = new HashMap<String, Object>();
//		para.put("parentid", id);
//		return deptDao.findChidrenByParentid(id);
//	}

	// 构造组织机构树数据
	private void tree(TreeModel tm, List<Department> li, String[] str) throws DatabaseException {
		if (!CollectionUtils.isEmpty(li)) {
			for (Department dept : li) {
				TreeModel ntm = new TreeModel();
				dept.setState(dept.getId() == 0 ? "open" : "closed");
				TreeUtil.copyModel(ntm, dept, str);// 复制值到TreeModel
				tm.getChildren().add(ntm);// 添加到孩子节点列表
				
				List<Department> list = deptDao.findChidrenByParentid(dept.getId());
				if(list.size() != 0)
					tree(ntm, list, str);// 递归，实现无限层级
			}
		}
	}

}
