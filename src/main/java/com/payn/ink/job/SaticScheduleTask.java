package com.payn.ink.job;

import com.payn.ink.config.PathConfig;
import com.payn.ink.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * @author: payn
 * @date: 2020/8/26 16:09
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

	private Logger logger = LoggerFactory.getLogger(SaticScheduleTask.class);

	@Autowired
	private PathConfig pathConfig;

	//备份DB数据的定时器
	@Scheduled(cron = "0 0 0 1 * ?")
	private void backupDB() throws IOException {
		logger.info(">>>>> 开始执行数据库备份：" + DateUtil.dateFormat(new Date()));
		//导出数据库
		Runtime rt = Runtime.getRuntime();
		Process child = rt.exec(pathConfig.getMysqlExec() + "");
		InputStream backupIn = child.getInputStream();
		InputStreamReader backupReader = new InputStreamReader(backupIn, "utf8");
		StringBuffer result = new StringBuffer("");
		BufferedReader br = new BufferedReader(backupReader);
		String instr = "";
		while ((instr = br.readLine()) != null) {
			result.append(instr + "\r\n");
		}
		String backupPath = pathConfig.getBackupPath();
		if (!new File(backupPath).exists()) {
			new File(backupPath).mkdirs();
		}
		FileOutputStream fout = new FileOutputStream(backupPath + "backup.sql");
		OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
		writer.write(result.toString());
		writer.flush();
		backupIn.close();
		backupReader.close();
		br.close();
		writer.close();
		fout.close();
		logger.info(">>>>> 结束执行数据库备份：" + DateUtil.dateFormat(new Date()));
	}

}
