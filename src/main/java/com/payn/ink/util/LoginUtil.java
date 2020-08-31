package com.payn.ink.util;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: payn
 * @date: 2020/8/18 19:31
 */
public class LoginUtil {

	/**
	 * 获取session中用户信息
	 */
	public static User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (null == session) {
			return null;
		}
		return (User) session.getAttribute(InkConstants.LOGIN_SESSION_KEY);
	}
}
