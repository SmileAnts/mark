package com.smile.util;

/**
 * 返回集
 * 
 * @author 许永强
 *
 * @param <T>
 */
public class Result<T> {

	private int code;
	private String msg;
	private int count;
	private T data;

	/**
	 * layui 的返回格式
	 * 
	 * @param data
	 * @param size
	 */
	private Result(T data, int size) {
		this.code = Constants.TABLE_CODE;
		this.msg = Constants.SUCCESS;
		this.data = data;
		this.count = size;
	}

	private Result(T data) {
		this.code = Constants.SUCCESS_CODE;
		this.msg = Constants.SUCCESS;
		this.data = data;
	}

	private Result(T data, String msg, int code) {
		this.data = data;
		this.msg = msg;
		this.code = code;
	}

	private Result(CodeMsg cm) {
		if (cm == null) {
			return;
		}
		this.code = cm.getRetCode();
		this.msg = cm.getMessage();
	}

	/**
	 * 返回带长度的成功类
	 * 
	 * @param data 返回数据
	 * @param size 数据长度
	 * @return
	 */
	public static <T> Result<T> success(T data, int size) {
		return new Result<T>(data, size);
	}

	public static <T> Result<T> success(T data) {
		return new Result<T>(data);
	}

	public static <T> Result<T> success(T data, String msg, int code) {
		return new Result<T>(data, msg, code);
	}

	/**
	 * 成功，不需要传入参数
	 */
	@SuppressWarnings("unchecked")
	public static <T> Result<T> success(String message) {
		return (Result<T>) success(message, 0);
	}

	@SuppressWarnings("unchecked")
	public static <T> Result<T> success() {
		return (Result<T>) success(Constants.SUCCESS_CODE);
	}

	/**
	 * 返回封装 返回实体， 信息， 状态码
	 * 
	 * @param data
	 * @param msg
	 * @param code
	 * @return
	 */
	public static <T> Result<T> result(T data, String msg, int code) {
		return new Result<T>(data, msg, code);
	}

	/**
	 * 失败时候的调用
	 */
	public static <T> Result<T> error(CodeMsg cm) {
		return new Result<T>(cm);
	}

	/**
	 * 自定义
	 */
	public static <T> Result<T> error(CodeMsg cm, String msg) {
		cm.setMessage(msg);
		return new Result<T>(cm);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
