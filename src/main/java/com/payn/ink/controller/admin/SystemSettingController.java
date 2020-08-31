package com.payn.ink.controller.admin;

import com.payn.ink.config.PathConfig;
import com.payn.ink.constant.InkConstants;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数设置
 *
 * @author: payn
 * @date: 2020/8/24 21:59
 */
@Controller
@RequestMapping("/admin/setting")
public class SystemSettingController {

	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private PathConfig pathConfig;

	/**
	 * 系统设置页面
	 */
	@RequestMapping("")
	public String setting(HttpServletRequest request) {
		Map<String, String> options = InkConstants.INIT_CONFIG_MAP;
		request.setAttribute("options", options);
		request.setAttribute("active", "setting");
		return "admin/setting";
	}

	/**
	 * 保存网站设置信息
	 */
	@ResponseBody
	@PostMapping("/saveSite")
	public ResponseResult saveSiteInfo(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String> configMap = new HashMap<>();
		parameterMap.forEach((key, value) -> {
			configMap.put(key, value[0]);
			//将新设置的系统配置参数保存到内存中
			InkConstants.INIT_CONFIG_MAP.put(key, value[0]);
		});
		return systemSettingService.saveSiteConfig(configMap);
	}

	/**
	 * 保存个性化信息
	 */
	@ResponseBody
	@PostMapping("/savePro")
	public ResponseResult savePro(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String> querys = new HashMap<>();
		parameterMap.forEach((key, value) -> {
			querys.put(key, value[0]);
			//将新设置的系统配置参数保存到内存中
			InkConstants.INIT_CONFIG_MAP.put(key, value[0]);
		});
		return systemSettingService.saveSiteConfig(querys);
	}

	/**
	 * 导出DB 备份
	 */
	@GetMapping(value = "/backup")
	public void backuop(HttpServletResponse response) {
		try {
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
			//下载文件
			String filePath = pathConfig.getBackupPath();
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=backup.sql");
			byte[] buff = new byte[1024];
			BufferedInputStream bis = null;
			OutputStream os = null;
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(filePath + "backup.sql")));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
			if (bis != null) {
				bis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
