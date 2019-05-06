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
	private String message;
	private T data;

	private Result(T data) {
		this.code = Constants.SUCCESS_CODE;
		this.message = Constants.SUCCESS;
		this.data = data;
	}
	
	private Result(CodeMsg cm) {
		if(cm == null){
			return;
		}
		this.code = cm.getRetCode();
		this.message = cm.getMessage();
	}
	
	/**
	  *  成功
	 */
	public static <T> Result<T> success(T data) {
		return new Result<T>(data);
	}
	
	/**
	  * 成功，不需要传入参数
	 */
	@SuppressWarnings("unchecked")
	public static <T> Result<T> success(){
		return (Result<T>) success("");
	}
	
	/**
	 * 失败时候的调用
	 * @return
	 */
	public static <T> Result<T> error(CodeMsg cm){
		return new Result<T>(cm);
	}

	public static <T> Result<T> error(CodeMsg cm,String msg){
		cm.setMessage(cm.getMessage()+"--"+msg);
		return new Result<T>(cm);
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
