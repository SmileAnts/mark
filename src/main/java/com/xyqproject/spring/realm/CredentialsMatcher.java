package com.xyqproject.spring.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		// 获取用户输入的密码（可以采用加盐的模式）
		String inPassWord = new String(uToken.getPassword());
		// 获取数据库的密码
		String dbPassWord = (String) info.getCredentials();
		return this.equals(inPassWord, dbPassWord);
	}
}
