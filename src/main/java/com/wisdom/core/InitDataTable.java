package com.wisdom.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisdom.entity.User;
import com.wisdom.service.UserService;
import com.wisdom.spring.SpringBeanUtil;
import com.wisdom.util.ConfigUtil;
import com.wisdom.util.DatabaseDialectAdapter;
import com.wisdom.util.HibernateUtil;

public class InitDataTable extends TimerTask{
	
	public static Logger log = LoggerFactory.getLogger(InitDataTable.class);
	
	@Override
	public void run() {
		try {
			List<String> initFileNameList = ConfigUtil.getResources("sql/init");
			Collections.sort(initFileNameList);
			for(int i = 0; i < initFileNameList.size(); i++) {
				InputStream inputStream = ConfigUtil.getResourceAsStream("sql/init/"+initFileNameList.get(i));
				String adapted = DatabaseDialectAdapter.dialectAdapter(inputStream, Config.HIBERNATE_DIALECT);
				HibernateUtil.executeSentences(new StringReader(adapted));
				IOUtils.closeQuietly(inputStream);
			}
		} catch (URISyntaxException e) {
			log.info("文件路径异常", e.getMessage());
		} catch (IOException e) {
			log.info("文件读取错误", e.getMessage());
		}
		
		UserService userService = (UserService) SpringBeanUtil.getBeanFromSpringByBeanName("userService");
		List<User> userList = userService.getAllUsers();
		if(userList.size() == 0) {
			try {
				InputStream is = ConfigUtil.getResourceAsStream("sql/user.sql");
				String adapted = DatabaseDialectAdapter.dialectAdapter(is, Config.HIBERNATE_DIALECT);
				HibernateUtil.executeSentences(new StringReader(adapted));
				IOUtils.closeQuietly(is);
			} catch (IOException e) {
				log.info("初始化用户数据异常", e.getMessage());
			}
		}
	}

}
