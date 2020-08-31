package com.payn.ink.service.impl;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.SystemSetting;
import com.payn.ink.domain.User;
import com.payn.ink.mapper.SystemSettingMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.InstallService;
import com.payn.ink.service.SystemSettingService;
import com.payn.ink.service.UserService;
import com.payn.ink.util.MD5Util;
import com.payn.ink.util.RegExpValidatorUtil;
import com.payn.ink.vo.invo.InstallInVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: payn
 * @date: 2020/8/18 20:18
 */
@Service
public class InstallServiceImpl implements InstallService {

	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private UserService userService;
	@Autowired
	private SystemSettingMapper systemSettingMapper;

	@Override
	public ResponseResult saveInstallOpt(InstallInVo installLnVo) {
		// 设置的参数校验
		if (null == installLnVo) {
			return new ResponseResult(false, -1, "参数错误");
		}
		if (StringUtils.isBlank(installLnVo.getSiteTitle())) {
			return new ResponseResult(false, -1, "网站名称不能为空");
		}
		if (StringUtils.isBlank(installLnVo.getSiteUrl())) {
			return new ResponseResult(false, -1, "网站地址不能为空");
		}
		if (StringUtils.isBlank(installLnVo.getSiteDescription())) {
			return new ResponseResult(false, -1, "网站描述不能为空");
		}
		if (!installLnVo.getSiteUrl().startsWith("http://") && !installLnVo.getSiteUrl().startsWith("https://")) {
			return new ResponseResult(false, -1, "网站地址请加：http://或者https://");
		}
		if (StringUtils.isBlank(installLnVo.getEmail())) {
			return new ResponseResult(false, -1, "邮箱不能为空");
		}
		if (!RegExpValidatorUtil.isEmail(installLnVo.getEmail())) {
			return new ResponseResult(false, -1, "邮箱格式错误");
		}
		if (StringUtils.isBlank(installLnVo.getPassword())) {
			return new ResponseResult(false, -1, "密码不能为空");
		}
		if (installLnVo.getPassword().length() < 6) {
			return new ResponseResult(false, -1, "密码不能小于6位");
		}
		if (StringUtils.isBlank(installLnVo.getConfirmPwd())) {
			return new ResponseResult(false, -1, "确认密码不能为空");
		}
		if (!StringUtils.equals(installLnVo.getPassword(), installLnVo.getConfirmPwd())) {
			return new ResponseResult(false, -1, "两次密码不同");
		}
		//更新system_setting表--系统初始化的时候已经相关用到的字段写入到数据库中了
		Map<String, Object> map = new HashMap<>();
		map.put("site_title", installLnVo.getSiteTitle());
		map.put("site_url", installLnVo.getSiteUrl());
		map.put("allow_install", "0");
		map.put("site_description",installLnVo.getSiteDescription());
		map.put("admin_mail",installLnVo.getEmail());
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			SystemSetting systemSetting = new SystemSetting();
			systemSetting.setSystemKey(entry.getKey());
			systemSetting.setSystemValue(entry.getValue().toString());
			systemSettingMapper.updateSystemSettingByKey(systemSetting);
		}
		//更新管理用户信息
		String userName = installLnVo.getAccount();
		String password = installLnVo.getPassword();
		String pwd = MD5Util.MD5encode(password);
		User user = new User();
		user.setUsername(userName);
		user.setPassword(pwd);
		user.setEmail(installLnVo.getEmail());
		user.setScreenName("admin");
		user.setIsActive("1");
		//先将原来此账号的用户删除
		int c = userService.deleteUserByUsername(userName);
		//创建用户
		int u = userService.createUser(user);
		//更新系统参数到内存中
		InkConstants.INIT_CONFIG_MAP = systemSettingService.getAllSysSettings();
		return ResponseResult.success("INKBLOG安装成功");
	}
}
