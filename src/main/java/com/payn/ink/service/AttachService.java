package com.payn.ink.service;

import com.github.pagehelper.PageInfo;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.vdo.AttachVdo;

public interface AttachService {
	
	PageInfo<AttachVdo> getUploadImgPage(int pageNum, int pageSize);

	int saveUploadFile(String fname, String fkey, String ftype, Long userId);

	ResponseResult deleteFileById(Long id);
}
