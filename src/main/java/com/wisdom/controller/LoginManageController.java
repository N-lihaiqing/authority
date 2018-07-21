package com.wisdom.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/login")
public class LoginManageController {
	
	@RequestMapping(value="/user")
	public ModelAndView login(HttpServletRequest req) {
		String error = null;
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("error", error);
		return mav;
	}

}
