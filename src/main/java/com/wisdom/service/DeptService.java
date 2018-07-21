package com.wisdom.service;

import com.wisdom.entity.TreeModel;

public interface DeptService {

	String getDeptTreeById(int id);

	/**
	 * 返回所有的单位及部门的树形结构
	 * 
	 * @param id
	 *            部门id，即作为下一级子节点的pid
	 * @param containsDept
	 *            是否包含子节点
	 * @return TreeModel
	 */
	TreeModel selectTree(int id, boolean containsDept);

}
