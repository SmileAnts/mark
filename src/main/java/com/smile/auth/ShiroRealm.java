package com.smile.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.smile.auth.entity.Module;
import com.smile.auth.entity.Role;
import com.smile.operation.user.entity.User;
import com.smile.operation.user.service.IUserService;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
		List<String> permission = new ArrayList<>();
		Set<Role> roles = user.getRoles();
		if (roles.size() > 0) {
			for (Role role : roles) {
				Set<Module> modules = role.getPermissions();
				if (modules.size() > 0) {
					for (Module module : modules) {
						permission.add(module.getMname());
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permission);
		return info;
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
		User user = userService.findUserByUserName(userName);
		// 当前realm对象的name
		String realmName = getName();
		// 盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
		// 封装用户信息，构建AuthenticationInfo对象并返回
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt,
				realmName);
		return authcInfo;
	}

}
