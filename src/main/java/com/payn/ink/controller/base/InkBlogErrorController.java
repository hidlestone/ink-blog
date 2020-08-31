package com.payn.ink.controller.base;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 跳转同一错误页面\全局异常处理方法二
 *
 * @Author payn
 * @Date 2019/8/4 0:48
 */
@Controller
public class InkBlogErrorController implements ErrorController {

	/**
	 * 默认错误
	 */
	private static final String path_default = "/error";

	@Override
	public String getErrorPath() {
		return path_default;
	}

	/**
	 * JSON格式错误信息
	 */
	@RequestMapping(value = path_default)
	public String error(HttpServletRequest request) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		switch (statusCode) {
			case 404:
				return "comm/error_404";
			case 500:
				return "comm/error_500";
			default:
				return "comm/error_500";
		}
	}
}
