package com.payn.ink.controller.base;

/**
 * @author: payn
 * @date: 2020/8/18 19:54
 */
public class ControllerUtil {

	//主题页面路径
	public static String THEME = "themes/default";

	/**
	 * 404 页面
	 */
	public static String render404() {
		return "comm/error_404";
	}

	/**
	 * 页面转发
	 */
	public static String render(String viewName) {
//		return InkConstants.INIT_CONFIG_MAP.get("THEME") + "/" +viewName;
		return THEME + "/" + viewName;
	}

}
