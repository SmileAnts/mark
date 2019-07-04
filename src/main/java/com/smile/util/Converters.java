package com.smile.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Converters {
	/**
	 * 获取当前时间
	 */
	public static Date nowTime() {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		Timestamp now = Timestamp.valueOf(nowTime);
		return now;
	}

	/**
	 * 获取当前时间不带连接符的字符串 例如:20180101
	 */
	public static String nowTimeString() {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		return nowTime;
	}

	/**
	 * 获取当前时间不带连接符的字符串 例如:2018-01-01
	 */
	public static String nowTimeToString() {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		return nowTime;
	}

	/**
	 * 判断当前时间是am 还是 pm
	 * 
	 * @return
	 */
	public static String amPm() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int hour = Integer.valueOf(sdf.format(new Date()));
		if (hour < 13) {
			return "am";
		} else {
			return "pm";
		}
	}

	private Converters() {

	}
}
