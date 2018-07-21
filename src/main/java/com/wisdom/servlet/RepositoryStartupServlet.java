package com.wisdom.servlet;

import java.util.Properties;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisdom.core.Config;
import com.wisdom.core.InitDataTable;
import com.wisdom.core.InitDepartmentTimer;

public class RepositoryStartupServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(RepositoryStartupServlet.class);
	
	static Timer initDataTableTimer;
	static Timer initDepartmentTimer;
	
	static InitDataTable initDataTable;
	static InitDepartmentTimer initDepartment;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		ServletContext sc = getServletContext();
		
		Properties config = Config.load(sc);
		
		Config.reload(sc, config);
		
		//开启各种定时器
		startTimer();
	}
	
	void startTimer() {
		initTable();
		initDepartment();
	}
	
	void initTable() {
		initDataTableTimer = new Timer(true);
		initDataTable = new InitDataTable();
		initDataTableTimer.schedule(initDataTable, 1000);
	}
	
	void initDepartment() {
		initDepartmentTimer = new Timer();
		initDepartment = new InitDepartmentTimer();
		initDepartmentTimer.schedule(initDepartment, 1000);
	}
	

}
