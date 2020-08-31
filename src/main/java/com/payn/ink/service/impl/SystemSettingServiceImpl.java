package com.payn.ink.service.impl;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.SystemSetting;
import com.payn.ink.mapper.SystemSettingMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.SystemSettingService;
import com.payn.ink.vo.invo.InstallInVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: payn
 * @date: 2020/8/18 17:23
 */
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

	private Logger logger = LoggerFactory.getLogger(SystemSettingServiceImpl.class);

	@Autowired
	private SystemSettingMapper systemSettingMapper;

	@Override
	public Map<String, String> getAllSysSettings() {
		List<SystemSetting> systemSettings = systemSettingMapper.getAllSysSettings();
		Map<String, String> sysMap = new HashMap<>();
		for (SystemSetting sys : systemSettings) {
			sysMap.put(sys.getSystemKey(), sys.getSystemValue());
		}
		return sysMap;
	}

	@Override
	@Transactional
	public ResponseResult saveSiteConfig(Map<String, String> configMap) {
		for (Map.Entry<String, String> entry : configMap.entrySet()) {
			logger.info("Enter insertSystemSetting method:key={},value={}", entry.getKey(), entry.getValue());
			SystemSetting systemSetting = new SystemSetting(entry.getKey(), entry.getValue());
			int c = systemSettingMapper.countByKey(entry.getKey());
			if (c > 0) {
				//更新
				int c1 = systemSettingMapper.updateSystemSettingByKey(systemSetting);
			} else {
				//插入
				int c2 = systemSettingMapper.insertSelective(systemSetting);
			}
		}
		return ResponseResult.success("保存成功!");
	}

	
	
}
