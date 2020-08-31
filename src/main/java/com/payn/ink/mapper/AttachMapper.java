package com.payn.ink.mapper;

import com.github.pagehelper.PageInfo;
import com.payn.ink.domain.Attach;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.vdo.AttachVdo;

import java.util.List;

public interface AttachMapper {
	
	int countAttachNum();

	int countLastMonthUpload();

	int countThisMonthUpload();

	List<AttachVdo> getUploadImgPage();

	int insertSelective(Attach attachPo);

	int deleteFileById(Long id);

	Attach getAttachById(Long id);
}