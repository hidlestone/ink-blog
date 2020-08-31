package com.payn.ink.service;

import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.invo.InstallInVo;

import java.util.Map;

/**
 * @author: payn
 * @date: 2020/8/18 17:21
 */
public interface SystemSettingService {
	
	Map<String, String> getAllSysSettings();

	ResponseResult saveSiteConfig(Map<String, String> configMap);
}
