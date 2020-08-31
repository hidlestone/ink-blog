package com.payn.ink.controller;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.controller.base.ControllerUtil;
import com.payn.ink.domain.User;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.InstallService;
import com.payn.ink.service.SystemSettingService;
import com.payn.ink.service.UserService;
import com.payn.ink.vo.invo.InstallInVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 安装INKBLOG
 *
 * @author: payn
 * @date: 2020/8/18 17:15
 */
@Controller
public class InstallController {

	private Logger logger = LoggerFactory.getLogger(InstallController.class);

	@Autowired
	private InstallService installService;
	@Autowired
	private UserService userService;

	/**
	 * 安装页面
	 * THEME : "themes/default"
	 */
	@RequestMapping("/install")
	public String installl(HttpServletRequest request) {
		Map configMap = InkConstants.INIT_CONFIG_MAP;
		if ("0".equals(configMap.get("allow_install"))) {
			return ControllerUtil.render404();
		}
		User admin = userService.findAdminUser();
		request.setAttribute("admin", admin);
		request.setAttribute("site_description", configMap.get("site_description"));
		request.setAttribute("site_keywords", configMap.get("site_keywords"));
		request.setAttribute("site_title", configMap.get("site_title"));
		request.setAttribute("site_url", configMap.get("site_url"));
		request.setAttribute("social_cnblogs", configMap.get("social_cnblogs"));
		request.setAttribute("social_github", configMap.get("social_github"));
		request.setAttribute("social_weibo", configMap.get("social_weibo"));
		request.setAttribute("social_zhihu", configMap.get("social_zhihu"));
		request.setAttribute("admin_mail", configMap.get("admin_mail"));
		return ControllerUtil.render("install");
	}

	/**
	 * 保存设置
	 */
	@ResponseBody
	@PostMapping("/install/save")
	public ResponseResult saveinstallOpt(InstallInVo installInVo) {
		return installService.saveInstallOpt(installInVo);
	}

}
