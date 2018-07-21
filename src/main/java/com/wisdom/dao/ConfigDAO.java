package com.wisdom.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.wisdom.core.MimeTypeConfig;
import com.wisdom.entity.Config;
import com.wisdom.entity.ConfigStoredFile;
import com.wisdom.entity.ConfigStoredOption;
import com.wisdom.entity.ConfigStoredSelect;
import com.wisdom.entity.vo.PageVo;
import com.wisdom.exception.DatabaseException;
import com.wisdom.util.HibernateUtil;
import com.wisdom.util.PathUtils;
import com.wisdom.util.SecureStore;
import com.wisdom.util.StringUtil;

public class ConfigDAO {
	
	private static Logger log = LoggerFactory.getLogger(ConfigDAO.class);
	private ConfigDAO() {}
	
	/**
	 * Find by pk with a default value
	 */
	public static String getString(String key, String defaultValue,String... index) throws DatabaseException {
		return getProperty(key, defaultValue, Config.STRING, index);
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static String getText(String key, String defaultValue,String... index) throws DatabaseException {
		return getProperty(key, defaultValue, Config.TEXT, index);
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static boolean getBoolean(String key, boolean defaultValue,String... index) throws DatabaseException {
		return "true".equalsIgnoreCase(getProperty(key, Boolean.toString(defaultValue), Config.BOOLEAN, index));
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static int getInteger(String key, int defaultValue,String... index) throws DatabaseException {
		return Integer.parseInt(getProperty(key, Integer.toString(defaultValue), Config.INTEGER,index));
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static long getLong(String key, long defaultValue,String... index) throws DatabaseException {
		return Long.parseLong(getProperty(key, Long.toString(defaultValue), Config.LONG, index));
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static ConfigStoredFile getFile(String key, String path, ServletContext sc,String... index) throws DatabaseException,
			IOException {
		InputStream is = null;
		
		try {
			if (sc == null) {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			} else {
				is = sc.getResourceAsStream(path);
			}
			
			ConfigStoredFile stFile = new ConfigStoredFile();
			
			if (is == null) {
				stFile.setContent("");
			} else {
				stFile.setContent(SecureStore.b64Encode(IOUtils.toByteArray(is)));				
			}
			
			stFile.setName(PathUtils.getName(path));
			stFile.setMime(MimeTypeConfig.mimeTypes.getContentType(stFile.getName()));
			
			// MIME still are not initialized from database
			if (MimeTypeConfig.MIME_UNDEFINED.equals(stFile.getMime())) {
				if (stFile.getName().toLowerCase().endsWith(".ico")) {
					stFile.setMime(MimeTypeConfig.MIME_ICO);
				}
			}
			
			String value = getProperty(key, new Gson().toJson(stFile), Config.FILE,index);
			return new Gson().fromJson(value, ConfigStoredFile.class);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	/**
	 * Find by pk with a default value
	 */
	public static ConfigStoredFile getFile(String path, ServletContext sc) throws DatabaseException, IOException {
		InputStream is = null;

		try {
			if (sc == null) {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			} else {
				is = sc.getResourceAsStream(path);
			}

			ConfigStoredFile stFile = new ConfigStoredFile();

			if (is == null) {
				stFile.setContent("");
			} else {
				stFile.setContent(SecureStore.b64Encode(IOUtils.toByteArray(is)));
			}

			stFile.setName(PathUtils.getName(path));
			stFile.setMime(MimeTypeConfig.mimeTypes.getContentType(stFile.getName()));

			// MIME still are not initialized from database
			if (MimeTypeConfig.MIME_UNDEFINED.equals(stFile.getMime())) {
				if (stFile.getName().toLowerCase().endsWith(".ico")) {
					stFile.setMime(MimeTypeConfig.MIME_ICO);
				}
			}

			return stFile;
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static void initSelect(String key, ConfigStoredSelect value) throws DatabaseException {
		Config cfg = new Config();
		cfg.setKey(key);
		cfg.setValue(new Gson().toJson(value));
		cfg.setType(Config.SELECT);
		delete(key);
		create(cfg);
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static ConfigStoredSelect getSelect(String key) throws DatabaseException {
		String dbValue = getProperty(key, null, Config.SELECT,null);
		
		if (dbValue == null || dbValue.equals("")) {
			return null;
		} else {
			return new Gson().fromJson(dbValue, ConfigStoredSelect.class);
		}
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static String getSelectedOption(String key, String value,String... index) throws DatabaseException {
		StringTokenizer st = new StringTokenizer(value, "|");
		ConfigStoredSelect stSelect = new ConfigStoredSelect();
		boolean selected = false;
		
		while (st.hasMoreTokens()) {
			String tk = st.nextToken().trim();
			ConfigStoredOption stOption = new ConfigStoredOption();
			
			if (tk.startsWith(ConfigStoredOption.SELECTED)) {
				stOption.setName(tk.substring(1));
				stOption.setValue(tk.substring(1));
				stOption.setSelected(true);
				selected = true;
			} else {
				stOption.setName(tk);
				stOption.setValue(tk);
				stOption.setSelected(false);
			}
			
			stSelect.getOptions().add(stOption);
		}
		
		// Set first option as default
		if (!selected && stSelect.getOptions().size() > 0) {
			stSelect.getOptions().get(0).setSelected(true);
		}
		
		String dbValue = getProperty(key, new Gson().toJson(stSelect), Config.SELECT, index);
		ConfigStoredSelect dbSelect = new Gson().fromJson(dbValue, ConfigStoredSelect.class);
		
		for (ConfigStoredOption option : dbSelect.getOptions()) {
			if (option.isSelected()) {
				return option.getValue();
			}
		}
		
		return "";
	}
	
	/**
	 * Find by pk with a default value
	 */
	public static List<String> getList(String key, String defaultValue,String... index) throws DatabaseException {
		List<String> list = new ArrayList<String>();
		String dbValue = getProperty(key, defaultValue, Config.LIST, index);
		StringTokenizer st = new StringTokenizer(dbValue, "\t\n\r\f");
		
		while (st.hasMoreTokens()) {
			String tk = st.nextToken().trim();
			
			if (tk != null && !tk.equals("")) {
				list.add(tk);
			}
		}
		
		return list;
	}
	
	/**
	 * Find by pk
	 */
	@SuppressWarnings("unchecked")
	public static List<Config> findAll() throws DatabaseException {
		log.debug("findAll()");
		String qs = "from Config cfg order by cfg.key";
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(qs);
			List<Config> ret = q.list();
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
	
	
	
	public static List<Config> findBySearchVo(Config searchVo,  PageVo pageVo) throws DatabaseException {
		log.debug("findAll()");
		String qs = "from Config "+ (StringUtil.isNotEmpty(pageVo.getSidx())?" order by "+pageVo.getSidx()+" "+pageVo.getSord():"");
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query q = session.createQuery(qs);
			q.setFirstResult((pageVo.getPage()-1)*pageVo.getRows());
			q.setMaxResults(pageVo.getRows());
			List<Config> ret = q.list();
			//add by wuxs20170511
			//文件类型的配置值因为转成字节码，内容庞大
			//为了加快页面响应速度，特别是IE8的，将文件类型的配置值置为空
			for(Config config : ret){
				if(Config.FILE.equals(config.getType())){
					config.setValue("");
				}
			}
			log.debug("findAll: {}", ret);
			return ret;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	
	
	public static Long countBySearchVo(Config searchVo) throws DatabaseException {
		log.debug("findAll()");
		String qs = "select count(*) from Config ";
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(qs);
			
			HibernateUtil.commit(tx);
			
			return (Long) q.uniqueResult();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	public static Long countBySearch(Config searchVo,String filter) throws DatabaseException {
		log.debug("findAll()");
		String qs = "select count(*) from Config co where co.key like :filter or co.type like :filter or co.value like :filter or co.index like :filter";
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(qs);
			q.setString("filter", "%"+filter+"%");
			HibernateUtil.commit(tx);
			
			return (Long) q.uniqueResult();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	public static List<Config> findBySearch(Config searchVo,  PageVo pageVo,String filter) throws DatabaseException {
		log.debug("findAll()");
		String qs = "from Config co where co.key like :filter or co.type like :filter or co.value like :filter or co.index like :filter"+ (com.wisdom.util.StringUtil.isNotEmpty(pageVo.getSidx())?" order by "+pageVo.getSidx()+" "+pageVo.getSord():"");
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(qs);
			q.setFirstResult((pageVo.getPage()-1)*pageVo.getRows());
			q.setMaxResults(pageVo.getRows());
			q.setString("filter", "%"+filter+"%");
			List<Config> ret = q.list();
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
	

	/**
	 * Find by pk with a default value
	 */
	private static String getProperty(String key, String defaultValue, String type, String... indexs) throws DatabaseException {
		log.debug("getProperty({}, {}, {})", new Object[] { key, defaultValue, type });
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Config ret = (Config) session.get(Config.class, key);
			
			if (ret == null) {
				ret = new Config();
				ret.setKey(key);
				ret.setType(type);
				ret.setValue(defaultValue);
				if(indexs!=null && indexs.length>0){
					StringBuffer sBuffer = new StringBuffer();
					for (String index : indexs) {
						sBuffer.append(sBuffer.length()>0?",":"");
						sBuffer.append(index);
					}
					ret.setIndex(sBuffer.toString());
				}
				session.save(ret);
			} else if (ret.getValue() == null) {
				// For Oracle '' are like NULL
				ret.setValue("");
			}
			
			HibernateUtil.commit(tx);
			log.debug("getProperty: {}", ret.getValue());
			return ret.getValue();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	/**
	 * Delete
	 */
	public static void delete(String key) throws DatabaseException {
		log.debug("delete({})", key);
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Config mt = (Config) session.load(Config.class, key);
			session.delete(mt);
			HibernateUtil.commit(tx);
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} finally {
			HibernateUtil.close(session);
		}
		
		log.debug("delete: void");
	}
	
	/**
	 * Create activity
	 */
	public static void create(Config cfg) throws DatabaseException {
	    Session session = null;
	    Transaction tx = null;
	    
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
	    	session.save(cfg);
	    	HibernateUtil.commit(tx);
	    } catch (HibernateException e) {
	    	HibernateUtil.rollback(tx);
	    	throw new DatabaseException(e.getMessage(), e);
	    } finally {
	    	HibernateUtil.close(session);
	    }
	}

}
