package com.smile.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ClassAnnotation(age = 12)
public class AopTest {
	public static void classTest(Class<?> c) {
		ClassAnnotation ca = c.getAnnotation(ClassAnnotation.class);
		if (ca == null) {
			System.out.println("ca is null");
		} else {
			System.out.println("age = " + ca.age());
		}
	}

	public static void methodTest(Class<?> c) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			MethodAnnotation m = method.getAnnotation(MethodAnnotation.class);
			if (m == null) {
				System.out.println("m is null");
			} else {
				System.out.println(m.name());
				System.out.println(m.value());
			}
		}
	}

	public static void fieldTest(Class<?> c) {
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("属性名称：" + field.getName());
			descFiledInfo(field);
		}
	}

	/**
	 *   * 分别获取注解的详细信息   * @param field  
	 */
	private static void descFiledInfo(Field field) {
		FieldAnnotation fa = field.getAnnotation(FieldAnnotation.class);
		if (fa == null) {
			System.out.println("fa is null");
		} else {
			System.out.println(fa.name());
			System.out.println(fa.description());
		}
	}

	public static void main(String[] args) {
		classTest(AopTest.class);
		System.out.println("========================");
		methodTest(MyName.class);
		System.out.println("========================");
		fieldTest(MyName.class);
	}
}
