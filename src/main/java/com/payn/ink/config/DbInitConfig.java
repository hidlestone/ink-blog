package com.payn.ink.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库初始化
 *
 * @author: payn
 * @date: 2020/8/18 16:31
 */
@Component
@Order(value = 1)
public class DbInitConfig implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(DbInitConfig.class);

	@Autowired
	private PathConfig pathConfig;
	@Autowired
	private DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		Connection conn = dataSource.getConnection();
		try {
			String sql = "select count(*) from information_schema.tables where table_schema='" + pathConfig.getDatabaseName() + "'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			int count = 0;
			if (rs != null && rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				//说明数据库未创建，不存在表
				logger.info("initialize import database starting");
				//创建ScriptRunner，用于执行 SQL 脚本 
				ScriptRunner runner = new ScriptRunner(conn);
				runner.setErrorLogWriter(null);
				runner.setLogWriter(null);
				runner.runScript(Resources.getResourceAsReader("db/init.sql"));
				runner.runScript(Resources.getResourceAsReader("db/schema.sql"));
				logger.info("initialize import database finished");
			}
			logger.info("tables had inited : ------" + count + "------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

}
