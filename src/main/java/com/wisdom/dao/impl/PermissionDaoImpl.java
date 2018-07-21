package com.wisdom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisdom.dao.PermissionDao;
import com.wisdom.entity.Permission;
import com.wisdom.entity.RolePermission;
import com.wisdom.exception.DatabaseException;
import com.wisdom.util.HibernateUtil;

@Repository(value="PermissionDao")
public class PermissionDaoImpl implements PermissionDao{
	
	private static Logger log = LoggerFactory.getLogger(PermissionDaoImpl.class);

	@Override
	public void addPermission(Permission permission) throws DatabaseException {
		
		log.debug("addPermission({})", permission);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(permission);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@Override
	public void deletePermission(long permissionId) throws DatabaseException {
		
		log.debug("deletePermission({})", permissionId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Permission permission = session.load(Permission.class, permissionId);
			session.delete(permission);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@Override
	public Permission findById(long permId) throws DatabaseException {
		
		log.debug("findById({})", permId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("FROM Permission WHERE permissionId =:permissionId");
			query.setLong("permissionId", permId);
			Permission ret = (Permission)query.setMaxResults(1).uniqueResult();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findNavisByRoleId(long roleId) throws DatabaseException {
		
		log.debug("findNavisByRoleId({})", roleId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sql = "SELECT p FROM RolePermission rp,Permission p "
				+ " WHERE rp.roleId =:roleId AND rp.permissionId = p.permissionId AND p.isNavi = 'F'";
		
		try {
			Query query = session.createQuery(sql);
			query.setLong("roleId", roleId);
			List<Permission> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findPermissionsByRoleId(long roleId) throws DatabaseException {
		
		log.debug("findPermissionsByRoleId({})", roleId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sql = "SELECT p FROM RolePermission rp, Permission p WHERE "
				+ " rp.roleId =:roleId AND rp.permissionId = p.permissionId";
		
		try {
			Query query = session.createQuery(sql);
			query.setLong("roleId", roleId);
			List<Permission> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findAllPermissions() throws DatabaseException {
		log.debug("findAllPermissions({})");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery(" from Permission");
			List<Permission> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@Override
	public void updatePermission(Permission permission) throws DatabaseException {
		
		log.debug("updatePermission({})");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(permission);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@Override
	public void deleteRolePermission(long permissionId) throws DatabaseException {
		
		log.debug("deleteRolePermission({})", permissionId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			RolePermission rolePermission = session.load(RolePermission.class, permissionId);
			session.delete(rolePermission);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

}
