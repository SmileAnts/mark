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
import org.springframework.beans.factory.annotation.Autowired;

import com.smile.auth.entity.Module;
import com.smile.auth.entity.Role;
import com.smile.operation.user.entity.User;
import com.smile.operation.user.service.IUserService;
import com.smile.util.Exceptions;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService IUserServiceImpl;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next(); // 获取session中的用户
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
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;// 获取用户输入的token
		String username = utoken.getUsername();
		User user = IUserServiceImpl.findUserByUserName(username);
		if (user == null) {
			throw Exceptions.unchecked("用户不存在！");
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName()); // 检验密码
	}

}
