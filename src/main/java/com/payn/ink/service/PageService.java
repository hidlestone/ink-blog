package com.payn.ink.service;

import com.github.pagehelper.PageInfo;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.vdo.ArticleVdo;

public interface PageService {

	PageInfo<ArticleVdo> selectSlugPages(int pageNum, int pageSize);

	ResponseResult deletePageById(Long articleId);
}
