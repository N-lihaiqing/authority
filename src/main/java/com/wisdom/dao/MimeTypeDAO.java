package com.wisdom.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisdom.entity.MimeType;
import com.wisdom.exception.DatabaseException;
import com.wisdom.util.HibernateUtil;


public class MimeTypeDAO {
	
	private static Logger log = LoggerFactory.getLogger(MimeTypeDAO.class);
	
	public static List<MimeType> findAll(String sort) throws DatabaseException {
		log.debug("findAll()");
		String qs = "from MimeType mt order by " + sort;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(qs);
			List<MimeType> ret = q.list();
			HibernateUtil.commit(tx);
			log.debug("findAll: {}", ret);
			return ret;
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}

}
