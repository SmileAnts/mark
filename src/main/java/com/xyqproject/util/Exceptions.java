package com.xyqproject.util;

/**
 * 异常工具类
 * 
 * @author smile
 *
 */
public class Exceptions {
	public static RuntimeException unchecked(String msg) {
		return new RuntimeException(msg);
	}
}
