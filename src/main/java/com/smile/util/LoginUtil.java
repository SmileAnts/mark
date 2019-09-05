package com.smile.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * 登录类
 * 
 * @author 许永强
 *
 */
public class LoginUtil {

	public static Object login(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				return Result.success();
			} else {
				return Result.error(CodeMsg.FALSE, "登录失败！");
			}
		} catch (UnknownAccountException use) {
			return Result.error(CodeMsg.FALSE, "用户不存在！");
		} catch (LockedAccountException lock) {
			return Result.error(CodeMsg.FALSE, "用户被锁定！");
		} catch (AuthenticationException e) {
			return Result.error(CodeMsg.FALSE, "账号或密码错误！");
		}
	}
}
