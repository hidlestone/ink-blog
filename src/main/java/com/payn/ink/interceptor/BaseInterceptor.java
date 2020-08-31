package com.payn.ink.interceptor;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.User;
import com.payn.ink.extension.Commons;
import com.payn.ink.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基础拦截器
 *
 * @author: payn
 * @date: 2020/8/18 19:22
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

	@Autowired
	private Commons commons;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		//虚拟目录：www.inknblog.com/ 即域名
		String contextPath = request.getContextPath();
		//获取请求URI：/admin 即域名后的地址
		String uri = request.getRequestURI();
		User user = LoginUtil.getLoginUser(request);
		//静态资源不拦截
		if (uri.contains("css") || uri.contains("js") || uri.contains("fonts")) {
			return true;
		}
		//是否运行重新安装INKBLOG
		if ("0".equals(InkConstants.INIT_CONFIG_MAP.get("ALLOW_INSTALL")) && !uri.startsWith(contextPath + "/install")) {
			response.sendRedirect(contextPath + "/install");
			return false;
		}
		//访问后台请求的拦截
		if (uri.startsWith(contextPath + "/admin")) {
			if (user == null && !uri.equals("/admin/login")) {
				//未登录 
				response.sendRedirect(contextPath + "/admin/login");
				return false;
			}
			if (user != null && (uri.equals("/admin") || uri.equals("/admin/"))) {
				//已登录
				response.sendRedirect(contextPath + "/admin/index");
				return false;
			}
		}
		return true;
	}

	/**
	 * 拦截器后置处理
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		request.setAttribute("commons", commons);
	}
}
