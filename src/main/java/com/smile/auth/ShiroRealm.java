package com.smile.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.smile.operation.user.entity.Users;
import com.smile.operation.user.service.IUserServiceImpl;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserServiceImpl userService;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("进入授权");
		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token携带了用户信息
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		// 获取前端输入的用户名
		String userName = usernamePasswordToken.getUsername();
		// 根据用户名查询数据库中对应的记录
		Users user = userService.findUserByUserName(userName);
		if (user == null) {
			throw new UnknownAccountException("用户不存在");
		}
		if (user.getLocked()) {
			throw new LockedAccountException("用户被锁定");
		}
		// 当前realm对象的name
		String realmName = getName();
		// 盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
		// 封装用户信息，构建AuthenticationInfo对象并返回
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, realmName);
		return authcInfo;
	}

}
