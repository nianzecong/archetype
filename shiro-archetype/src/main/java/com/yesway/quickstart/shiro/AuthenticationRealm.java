package com.yesway.quickstart.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yesway.quickstart.entity.User;
import com.yesway.quickstart.service.UserService;

public class AuthenticationRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		if (userName != null && !"".equals(userName)) {
			// 从数据库或 接口通过userName获取用户信息
			User account = userService.getUserByLoginName(userName);
			if (account == null) {
				throw new UnknownAccountException();
			}
			if (!account.getIsEnabled().booleanValue()) {
				throw new DisabledAccountException();
			}
			if (account.getIsLocked().booleanValue()) {
				throw new LockedAccountException();
			}
			if (!account.getPassword().equals(password)) {
				throw new IncorrectCredentialsException();
			}
			return new SimpleAuthenticationInfo(account, account.getPassword(), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User account = (User) principals.getPrimaryPrincipal();
		if (account != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.addRole(account.getRoleName());
			info.addStringPermissions(account.getPermissionList());

			return info;
		}
		return null;
	}

}
