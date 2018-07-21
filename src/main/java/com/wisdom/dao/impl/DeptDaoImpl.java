package com.wisdom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisdom.dao.DeptDao;
import com.wisdom.entity.Department;
import com.wisdom.exception.DatabaseException;
import com.wisdom.util.HibernateUtil;

@Repository(value="DeptDao")
public class DeptDaoImpl implements DeptDao{
	
	private static Logger log = LoggerFactory.getLogger(DeptDaoImpl.class);

	@Override
	public List<Long> findDeptTreeById(int id) throws DatabaseException {
		
		log.debug("findDeptTreeById({id})", id);
		
		List<Long> deptIdList = new ArrayList<Long>();
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery("select getAllChildDeptIds(?)");
			query.setInteger(0, id);
			String deptIds = (String) query.setMaxResults(1).uniqueResult();
			if(deptIds != null && !deptIds.trim().equals("")){
				String[] deptIdArray = deptIds.split(",");
				for (int i = 0; i < deptIdArray.length; i++) {
					deptIdList.add(Long.parseLong(deptIdArray[i]));
				}
			}
			return deptIdList;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@Override
	public Department findDeptById(long id) throws DatabaseException {
		
		log.debug("finddeptById({id})", id);
		
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Department department = session.get(Department.class, id);
			return department;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findChidrenById(long id) throws DatabaseException {
		
		log.debug("findChidrenById({id})", id);
		String sql = "from Department where id=:id";
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setLong("id", id);
			List<Department> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findChidrenByParentid(long parentid) throws DatabaseException {
		log.debug("findChidrenById({id})", parentid);
		String sql = "from Department where parentid=:parentid";
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setLong("parentid", parentid);
			List<Department> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

}
