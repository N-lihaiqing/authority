package com.wisdom.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.wisdom.entity.User;
import com.wisdom.service.UserService;
import com.wisdom.spring.SpringBeanUtil;

public class UserRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserService userService = (UserService) SpringBeanUtil.getBeanFromSpringByBeanName("userService");
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findPermissionsByUserName(userName));
		authorizationInfo.setStringPermissions(userService.findPermissionsByUserName(userName));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UserService userService = (UserService) SpringBeanUtil.getBeanFromSpringByBeanName("userService");
		String userName = (String) token.getPrincipal();
		User user = userService.getUserByUserName(userName);
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(), ByteSource.Util.bytes(user.getUserName() + user.getPasswordSalt()), getName());
		return authenticationInfo;
	}

}
