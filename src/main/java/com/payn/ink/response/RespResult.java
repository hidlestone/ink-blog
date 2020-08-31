package com.payn.ink.response;

/**
 * 响应信息
 * @author payn
 * @date 2020/8/18 20:03 
*/
public interface RespResult {

	//操作是否成功：true-成功，false-失败
	boolean success();

	//操作代码
	int code();

	//提示信息
	String message();
}
