package com.payn.ink.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取自定义配置 application.yml 中的配置项
 * 路径配置
 *
 * @author: payn
 * @date: 2020/8/18 16:32
 */
@Component
@ConfigurationProperties(prefix = "inkblog")
public class PathConfig {

	//文件上传保存路径
	private String filePath;
	//数据库名称
	private String databaseName;
	//导入、导出mysql数据库，执行的 mysql 命令行命令
	private String mysqlExec;
	//数据库备份路径
	private String backupPath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getMysqlExec() {
		return mysqlExec;
	}

	public void setMysqlExec(String mysqlExec) {
		this.mysqlExec = mysqlExec;
	}

	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}
}
