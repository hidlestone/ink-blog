package com.payn.ink.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量
 *
 * @author: payn
 * @date: 2020/8/18 17:14
 */
public class InkConstants {

	/**
	 * 登录用户信息
	 */
	public static final String LOGIN_SESSION_KEY = "login_user";

	/**
	 * 最大页码
	 */
	public static final Integer MAX_PAGE = 100;

	/**
	 * 初始化配置
	 */
	public static Map<String, String> INIT_CONFIG_MAP = new HashMap<>();

	/**
	 * 记住评论作者名称，cookie 名称
	 */
	public static final String REMEMBER_AUTHOR_INK = "remember_author_ink";

	/**
	 * 记住评论作者邮箱，cookie 名称
	 */
	public static final String REMEMBER_MAIL_INK = "remember_mail_ink";

	/**
	 * 记住评论作者URL，cookie 名称
	 */
	public static final String REMEMBER_URL_INK = "remember_url_ink";

	/**
	 * 上传文件最大1M 1024*1024
	 */
	public static Integer MAX_FILE_SIZE = 1048576;

}
