package com.smile.util;

/**
 * 异常工具类
 * 
 * @author 许永强
 *
 */
public class Exceptions extends Exception {
	private static final long serialVersionUID = -8956392800055458558L;
	private String message;

	public Exceptions(String ErrorMessagr) {
		this.setMessage(ErrorMessagr);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static RuntimeException unchecked(String msg) {
		return new RuntimeException(msg);
	}

	public Exceptions() {
		super();
	}

}
