package com.smile.util;

/**
 * 返回消息接口
 * 
 * @author 许永强
 *
 */
public class CodeMsg {
	private int retCode;
	private String message;
	private Boolean status;

	public static CodeMsg SUCCESS = new CodeMsg(Constants.SUCCESS_CODE, "success", Constants.SUCCESS_STATUS);
	public static CodeMsg SERVER_EXCEPTION = new CodeMsg(500100, "服务端异常", Constants.FALSE_STATUS);
	public static CodeMsg PARAMETER_ISNULL = new CodeMsg(500101, "输入参数为空", Constants.FALSE_STATUS);
	public static CodeMsg USER_NOT_EXSIST = new CodeMsg(500102, "用户不存在", Constants.FALSE_STATUS);
	public static CodeMsg USER_EXSIST = new CodeMsg(500103, "用户已存在", Constants.FALSE_STATUS);
	public static CodeMsg FALSE = new CodeMsg(Constants.FALSE_CODE, "操作失败", Constants.FALSE_STATUS);

	public CodeMsg(int i, String string, boolean b) {
		this.retCode = i;
		this.message = string;
		this.status = b;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

}
