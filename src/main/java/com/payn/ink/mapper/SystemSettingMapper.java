package com.payn.ink.mapper;

import com.payn.ink.domain.SystemSetting;

import java.util.List;

public interface SystemSettingMapper {
	
	List<SystemSetting> getAllSysSettings();

	int updateSystemSettingByKey(SystemSetting systemSetting);

	int countByKey(String key);

	int insertSelective(SystemSetting systemSetting);
}