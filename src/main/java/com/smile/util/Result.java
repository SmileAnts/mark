package com.smile.util;

/**
 * 返回集
 * 
 * @author smile
 *
 * @param <T>
 */
public class Result<T> {
	private int code;
	private String message;
	private T data;

	/**
	 * 成功调用
	 * 
	 * @param data 返回值
	 */
	private Result(T data) {
		this.code = Constants.SUCCESS_CODE;
		this.message = Constants.SUCCESS;
		this.data = data;
	}

	public static <T> Result<T> success(T data) {
		return new Result<T>(data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
