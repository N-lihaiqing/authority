package com.wisdom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.wisdom.dao.UserDao;
import com.wisdom.entity.User;
import com.wisdom.entity.UserRole;
import com.wisdom.exception.DatabaseException;
import com.wisdom.util.HibernateUtil;

@Repository(value="UserDao")
public class UserDaoImpl implements UserDao{
	
	private static Logger log = LoggerFactory.getLogger(RoleDaoImpl.class);
	
	@Override
	public void addUser(User user) throws DatabaseException {

		log.debug("addUser({})", user);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(user);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@Override
	public void deleteUser(long userId) throws DatabaseException {
		
		log.debug("deleteUser({})", userId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			User user = session.load(User.class, userId);
			session.delete(user);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
		
	}

	@Override
	public User findUserByUserName(String userName) throws DatabaseException {

		log.debug("findUserByUserName({})", userName);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("FROM User u WHERE u.userName =:userName");
			query.setString("userName", userName);
			User ret = (User)query.setMaxResults(1).uniqueResult();
			log.debug("findUserByUserName: {}", ret);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() throws DatabaseException {

		log.debug("findAllUsers({})");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Query query = session.createQuery("FROM User");
			List<User> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@Override
	public void deleteUserRole(long userId) throws DatabaseException {

		log.debug("deleteUserRole({})", userId);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createSQLQuery("DELETE FROM UserRole WHERE userId =:userId");
			query.setLong("userId", userId);
			query.executeUpdate();
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@Override
	public void addUserRole(UserRole userRole) throws DatabaseException {
		log.debug("addUserRole({})", userRole);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(userRole);
			HibernateUtil.commit(tx);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findRolesByUserName(String userName) throws DatabaseException {
		log.debug("findRolesByUserName({})", userName);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sql = "SELECT r.roleCode FROM User u,Role r,UserRole ur "
			+ " WHERE u.userName ="+userName+" AND u.userId = ur.userId AND ur.roleId = r.roleId";
		
		try {
			Query query = session.createSQLQuery(sql);
			List<String> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findPermissionsByUserName(String userName) throws DatabaseException {
		log.debug("findPermissionsByUserName({})", userName);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String sql = "SELECT p.permissionCode FROM User u,Role r,UserRole ur,Permission p,RolePermission rp"
			+ " WHERE u.userName ='"+userName+"' AND u.userId = ur.userId AND ur.roleId = r.roleId AND r.roleId = rp.roleId AND rp.permissionId = p.permissionId";
		
		try {
			Query query = session.createQuery(sql);
			List<String> ret = query.list();
			return ret;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

}
