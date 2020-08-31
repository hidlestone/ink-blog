package com.payn.ink.controller.admin;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.User;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.UserService;
import com.payn.ink.util.EHCacheUtil;
import com.payn.ink.util.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台登陆登出
 *
 * @author: payn
 * @date: 2020/8/24 15:41
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	/**
	 * 后台用户登陆
	 */
	@ResponseBody
	@PostMapping("/dologin")
	public ResponseResult doLogin(User user, HttpServletRequest request) {
		//从缓存中查询输入账号密码错误的次数
		String ip = IPUtil.getIpAddrByRequest(request);
		Integer errorCount = (Integer) EHCacheUtil.get("login_error_count", ip);
		if (null == errorCount || errorCount < 3) {
			ResponseResult responseResult = userService.login(user, request);
			if (responseResult.getCode() == -2) {//账号或密码错误
				errorCount = (null == errorCount ? 1 : ++errorCount);
				EHCacheUtil.put("login_error_count", ip, errorCount);
			}
			return responseResult;
		}
		return ResponseResult.fail("账号或密码错误已超过3次，请10分钟后尝试");
	}

	/**
	 * 用户登出
	 */
	@ResponseBody
	@PostMapping("/logout")
	public ResponseResult logout(HttpServletRequest request) {
		try {
			//从缓存中删除登陆错误次数
			String ip = IPUtil.getIpAddrByRequest(request);
			EHCacheUtil.remove("login_error_count", ip);
			//将登陆的用户从session中删除
			request.getSession().removeAttribute(InkConstants.LOGIN_SESSION_KEY);
			return ResponseResult.success("用户登出成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseResult.fail("用户登出失败");
		}
	}

}
