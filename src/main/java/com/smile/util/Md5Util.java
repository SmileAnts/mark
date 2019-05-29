package com.smile.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.smile.operation.user.entity.Users;

/**
 * @author 许永强
 */
public class Md5Util {
	/**
	 * 返回加密密码
	 */
	public static Users password(Users user) {
		return Md5Util.md5(user);
	}

	/**
	 * md5加盐加密
	 */
	private static Users md5(Users user) {
		String random = new SecureRandomNumberGenerator().nextBytes().toHex();
		user.setPassword(new Md5Hash(user.getPassword(), random, 3).toString());
		user.setSalt(random);
		return user;
	}
}
