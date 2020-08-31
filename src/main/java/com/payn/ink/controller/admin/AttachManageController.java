package com.payn.ink.controller.admin;

import com.github.pagehelper.PageInfo;
import com.payn.ink.config.PathConfig;
import com.payn.ink.constant.InkConstants;
import com.payn.ink.constant.TypeEnum;
import com.payn.ink.domain.User;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.AttachService;
import com.payn.ink.util.FileUtil;
import com.payn.ink.util.LoginUtil;
import com.payn.ink.vo.vdo.AttachVdo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件管理
 *
 * @author: payn
 * @date: 2020/8/24 19:02
 */
@Controller
@RequestMapping("/admin/files")
public class AttachManageController {

	private Logger logger = LoggerFactory.getLogger(AttachManageController.class);

	@Autowired
	private AttachService attachService;
	@Autowired
	private PathConfig pathConfig;

	/**
	 * 文件管理页面
	 */
	@RequestMapping("")
	public String showFiles(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
							@RequestParam(value = "pageSize", defaultValue = "6") int pageSize,
							HttpServletRequest request) {
		PageInfo<AttachVdo> attachs = attachService.getUploadImgPage(pageNum, pageSize);
		request.setAttribute("active", "files");
		request.setAttribute("attachs", attachs);
		return "admin/files";
	}

	/**
	 * 上传文件
	 */
	@ResponseBody
	@PostMapping("/upload")
	public ResponseResult uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
		UUID uuid = UUID.randomUUID();
		User loginUser = LoginUtil.getLoginUser(request);
		Long userId = loginUser.getUserId();
		try {
			String fname = multipartFile.getOriginalFilename();
			if (multipartFile.getSize() <= InkConstants.MAX_FILE_SIZE) {
				//获取文件后缀名
				String fkey = uuid + "." + FileUtil.getFileExt(fname);
				//判断图片是否是图片类型，并记录类型
				String ftype = FileUtil.isImage(multipartFile.getInputStream()) ? TypeEnum.IMAGE.getType() : TypeEnum.FILE.getType();
				//设置文件保存的路径，不存在则创建文件夹
				String filepath = FileUtil.getFilePath(fkey, pathConfig.getFilePath());
				File file = new File(filepath);
				//进行对上传文件的IO拷贝操作
				try {
					FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
				int c = attachService.saveUploadFile(fname, fkey, ftype, userId);
				if (c > 0) {
					return ResponseResult.success("上传文件成功！");
				}
			}
		} catch (IOException e) {
			logger.error(">>>>>> 上传文件失败！");
			e.printStackTrace();
		}
		return ResponseResult.fail("上传文件失败！");
	}

	/**
	 * 删除文件
	 */
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public ResponseResult deleteFile(@PathVariable("id") Long id) {
		return attachService.deleteFileById(id);
	}

}
