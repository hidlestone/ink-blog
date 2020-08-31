package com.payn.ink.config;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.service.SystemSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 读取系统参数到内存中
 *
 * @author: payn
 * @date: 2020/8/18 17:17
 */
@Component
@Order(value = 2)
public class SysSettingsCacheConfig implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(SysSettingsCacheConfig.class);

	@Autowired
	private SystemSettingService systemSettingService;

	@Override
	public void run(String... args) throws Exception {
		logger.info("--- 读取系统参数到内存中 ---");
		InkConstants.INIT_CONFIG_MAP = systemSettingService.getAllSysSettings();
	}
}
