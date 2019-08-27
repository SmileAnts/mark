package com.smile.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {

	public static void parseList(String params) {

	}

	/**
	 * <pre>
	 * list类型转换Long
	 * </pre>
	 * 
	 * @param         <T> List类型
	 * 
	 * @param objects 传入的object集合
	 * @return
	 */
	public static <T> List<Long> parseLong(List<T> objects) {
		List<Long> result = new ArrayList<Long>(objects.size());
		for (T object : objects) {
			result.add(Long.valueOf(object.toString()));
		}
		return result;
	}
}
