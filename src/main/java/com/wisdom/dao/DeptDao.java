package com.wisdom.dao;

import java.util.List;

import com.wisdom.entity.Department;
import com.wisdom.exception.DatabaseException;

public interface DeptDao {
	
	List<Long> findDeptTreeById(int id) throws DatabaseException;
	
	Department findDeptById(long id) throws DatabaseException;
	
	List<Department> findChidrenById(long id) throws DatabaseException;
	
	List<Department> findChidrenByParentid(long parentid) throws DatabaseException;
	

}
