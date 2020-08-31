package com.payn.ink.response;

/**
 * 通用响应信息枚举
 *
 * @author: payn
 * @date: 2020/8/18 20:04
 */
public enum CommonRespResultEnum implements RespResult {

	SUCCESS(true, 10000, "操作成功！"),
	FAIL(false, 11111, "操作失败！"),
	UNAUTHENTICATED(false, 10001, "此操作需要登陆系统！"),
	UNAUTHORISE(false, 10002, "权限不足，无权操作！"),
	ILLEGAL_PARAM(false, 10003, "非法参数！"),
	UNSUPPORTED_METHOD(false, 10004, "请求方式不支持！"),
	SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！");

	//操作是否成功
	boolean success;
	//操作代码
	int code;
	//提示信息
	String message;

	CommonRespResultEnum(boolean success, int code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

	@Override
	public boolean success() {
		return success;
	}

	@Override
	public int code() {
		return code;
	}

	@Override
	public String message() {
		return message;
	}
}
