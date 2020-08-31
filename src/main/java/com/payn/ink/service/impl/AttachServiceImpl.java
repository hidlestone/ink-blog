package com.payn.ink.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payn.ink.config.PathConfig;
import com.payn.ink.domain.Attach;
import com.payn.ink.mapper.AttachMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.AttachService;
import com.payn.ink.vo.vdo.AttachVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/24 19:04
 */
@Service
public class AttachServiceImpl implements AttachService {

	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private PathConfig pathConfig;

	@Override
	public PageInfo<AttachVdo> getUploadImgPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<AttachVdo> attachVdoList = attachMapper.getUploadImgPage();
		return new PageInfo<>(attachVdoList);
	}

	@Override
	public int saveUploadFile(String fname, String fkey, String ftype, Long userId) {
		Attach attachPo = new Attach();
		attachPo.setFname(fname);
		attachPo.setUserId(userId);
		attachPo.setFkey(fkey);
		attachPo.setFtype(ftype);
		return attachMapper.insertSelective(attachPo);
	}

	@Override
	public ResponseResult deleteFileById(Long id) {
		Attach attach = attachMapper.getAttachById(id);
		int c = attachMapper.deleteFileById(id);
		if (c > 0) {
			new File(pathConfig.getFilePath() + "/" + attach.getFkey()).delete();
			return ResponseResult.success("删除附件成功！");
		}
		return ResponseResult.fail("删除附件失败！");
	}
}
