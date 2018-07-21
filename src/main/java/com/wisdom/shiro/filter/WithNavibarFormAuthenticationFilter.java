package com.wisdom.shiro.filter;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.wisdom.Jetty;
import com.wisdom.entity.Navigation;
import com.wisdom.entity.Permission;
import com.wisdom.service.UserService;
import com.wisdom.spring.SpringBeanUtil;

public class WithNavibarFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpReq=(HttpServletRequest)request;
		UserService userService = (UserService) SpringBeanUtil.getBeanFromSpringByBeanName("userService");
		String userName=(String)SecurityUtils.getSubject().getPrincipal();
		
		List<Navigation> navigationBar = userService.getNavigationBar(userName);
		NavigateJson jsonTree = new NavigateJson();
		String json = jsonTree.getTree(navigationBar);
		httpReq.getSession().setAttribute("navibar", json);
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	class NavigateJson{
		
		StringBuilder result = null;
		StringBuilder sb = null;
		List<Navigation> navigationBar = null;
		
		public String getTree(List<Navigation> navigationBar) {
			result = new StringBuilder();
			sb = new StringBuilder();
			this.navigationBar = navigationBar;
			if(navigationBar.size() != 0) {
				result.append("{ \"menus\":" + "[");
				for (Navigation nav : navigationBar) {
					result.append("{\"menuid\":\"" + nav.getId() + "\",\"icon\":\"" + "icon-sys" + "\",\"menuname\":\"" + nav.getNavigationName() + "\"");
					GetTreeJsonByList(nav.getChildNavigations());
					result.append("},");
				}
				result.deleteCharAt(result.length()-1);
				result.append("]}");
			}
			
			return result.toString();
		}
		
		public String GetTreeJsonByList(List<Permission> permission) {
			if(permission.size() > 0) {
				sb.append(",\"menus\":[");
				for(Permission per : permission) {
					if(!per.getIsNavi()) {
//						sb.append("{\"menuid\":\"" + per.getPermissionId() + "\",\"menuname\":\"" + per.getPermissionDesc() + "\",\"icon\":\"" + "icon-nav" + "\",\"url\":\"" + per.getUrl()+".html" + "\"");
						sb.append("{\"menuname\":\"" + per.getPermissionDesc() + "\",\"icon\":\"" + "icon-nav" + "\",\"url\":\"" + Jetty.path + per.getUrl()+".html" + "\"");
						sb.append("},");					
					}
				}
				sb.deleteCharAt(sb.length()-1);
				sb.append("]");
				result.append(sb.toString());
			}
			return result.toString();
		}
	}
}
