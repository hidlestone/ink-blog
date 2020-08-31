package com.payn.ink.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie 相关工具类
 *
 * @author: payn
 * @date: 2020/8/22 23:55
 */
public class CookieUtil {

	/**
	 * 浏览器保存cookie
	 *
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param response
	 */
	public static void saveCookie(String name, String value, int maxAge, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setSecure(false);
		response.addCookie(cookie);
	}

}
