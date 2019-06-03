package com.smile.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

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
		ByteSource salt = ByteSource.Util.bytes(user.getUsername());
		String newPs = new SimpleHash("MD5", user.getPassword(), salt, 3).toHex();
		user.setPassword(newPs);
		return user;
	}
}
