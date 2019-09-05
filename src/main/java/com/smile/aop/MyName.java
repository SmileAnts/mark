package com.smile.aop;

public class MyName {
	@FieldAnnotation
	private String name;

	@FieldAnnotation(description = "苍天已死，黄天当道")
	private String descriptation;

	@MethodAnnotation(name = "123", value = "13245")
	public String getString() {
		return "Today money is important!";
	}
}
