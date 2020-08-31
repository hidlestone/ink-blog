package com.payn.ink.response;

/**
 * 前端响应结果
 *
 * @author: payn
 * @date: 2020/8/18 19:58
 */
public class ResponseResult {

	//操作是否成功
	private boolean success;
	//操作代码
	private int code;
	//提示信息
	private String message;
	//结果数据
	private Object result;

	public ResponseResult(RespResult RespResult) {
		this.success = RespResult.success();
		this.code = RespResult.code();
		this.message = RespResult.message();
	}

	public ResponseResult(RespResult RespResult, Object result) {
		this.success = RespResult.success();
		this.code = RespResult.code();
		this.message = RespResult.message();
		this.result = result;
	}

	public ResponseResult(boolean success, int code, String message, Object result) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public ResponseResult(boolean success, int code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

	public static ResponseResult fail(String message) {
		return new ResponseResult(false, -1, message);
	}

	public static ResponseResult success(String message) {
		return new ResponseResult(true, 1, message);
	}

	public static ResponseResult SUCCESS() {
		return new ResponseResult(CommonRespResultEnum.SUCCESS);
	}

	public static ResponseResult FAIL() {
		return new ResponseResult(CommonRespResultEnum.FAIL);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
