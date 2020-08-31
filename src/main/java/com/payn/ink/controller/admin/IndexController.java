package com.payn.ink.controller.admin;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.User;
import com.payn.ink.service.AdminIndexService;
import com.payn.ink.vo.outvo.AdminIndexDataOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 后台首页
 *
 * @author: payn
 * @date: 2020/8/24 16:35
 */
@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {

	@Autowired
	private AdminIndexService adminIndexService;

	/**
	 * 后台登陆页
	 */
	@RequestMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	/**
	 * 跳转后台首页
	 */
	@RequestMapping("/index")
	public String adminHome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(InkConstants.LOGIN_SESSION_KEY);
		//获取后台首页数据信息
		AdminIndexDataOutVo outVo = adminIndexService.getAdminIndexDataInfo(user, request);
		request.setAttribute("adminIndexData", outVo);
		request.setAttribute("active", "index");
		return "admin/index";
	}

}
