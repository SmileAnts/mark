package com.smile.util;

/**
  *  返回消息接口
 * @author 许永强
 *
 */
public class CodeMsg {
	private int retCode;
	private String message;
	
	public static CodeMsg SUCCESS = new CodeMsg(0,"success");
	public static CodeMsg SERVER_EXCEPTION = new CodeMsg(500100,"服务端异常");
	public static CodeMsg PARAMETER_ISNULL = new CodeMsg(500101,"输入参数为空");
	public static CodeMsg USER_NOT_EXSIST = new CodeMsg(500102,"用户不存在"); 
	
	private CodeMsg(int retCode, String message) {
		this.retCode = retCode;
		this.message = message;
	}
	public int getRetCode() {
		return retCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
