package com.smile.util;

/**
 * 异常工具类
 * 
 * @author 许永强
 *
 */
public class Exceptions {
	public static RuntimeException unchecked(String msg) {
		return new RuntimeException(msg);
	}
}
