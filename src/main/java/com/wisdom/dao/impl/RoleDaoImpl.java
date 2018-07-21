package com.wisdom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisdom.dao.RoleDao;
import com.wisdom.entity.Role;
import com.wisdom.entity.RolePermission;
import com.wisdom.entity.User;
import com.wisdom.entity.UserRole;
import com.wisdom.exception.DatabaseException;
import com.wisdom.util.HibernateUtil;

@Repository(value="RoleDao")
public class RoleDaoImpl implements RoleDao{
	
	private static Logger log = LoggerFactory.getLogger(RoleDaoImpl.class);

	@Override
	public void addRole(Role role) throws DatabaseException {
		
		log.debug("addRole({})", role);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(role);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			log.info("add permission error", e.getMessage());
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@Override
	public void deleteRole(long roleId) throws DatabaseException {

		log.debug("deleteRole({})", roleId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Role role = session.load(Role.class, roleId);
			session.delete(role);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@Override
	public Role findByRoleId(long roleId) throws DatabaseException {

		log.debug("findById({})", roleId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("FROM Role r WHERE r.roleId =:roleId");
			query.setLong("roleId", roleId);
			Role ret = (Role)query.setMaxResults(1).uniqueResult();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRolesByUserName(String userName) throws DatabaseException {
		
		log.debug("findRolesByUserName({})", userName);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "from Role r where r.roleId in (select ur.roleId from UserRole ur where ur.userId = (select u.userId from User u where u.userName=:userName))";
			
		try {
			Query query = session.createQuery(hql);
			query.setString("userName", userName);
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAllRoles() throws DatabaseException {
		
		log.debug("findAllRoles({})");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery("FROM Role");
			List<Role> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@Override
	public void updateRole(Role role) throws DatabaseException {

		log.debug("updateRole({})");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(role);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
		
	}

	@Override
	public void deleteUserRole(long roleId) throws DatabaseException {

		log.debug("deleteUserRole({})", roleId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createSQLQuery("DELETE FROM UserRole ur WHERE ur.roleId =:roleId");
			query.setLong("roleId", roleId);
			query.executeUpdate();
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@Override
	public void deleteRolePermission(long roleId) throws DatabaseException {

		log.debug("deleteRolePermission({})", roleId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createSQLQuery("DELETE FROM RolePermission rp ur WHERE rp.roleId =:roleId");
			query.setLong("roleId", roleId);
			query.executeUpdate();
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@Override
	public void addRolePermission(RolePermission rolePermission) throws DatabaseException {

		log.debug("addRolePermission({})", rolePermission);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(rolePermission);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

}
