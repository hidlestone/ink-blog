package com.payn.ink.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payn.ink.mapper.ArticleMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.PageService;
import com.payn.ink.vo.vdo.ArticleVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/25 0:13
 */
@Service
public class PageServiceImpl implements PageService {

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public PageInfo<ArticleVdo> selectSlugPages(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleVdo> articleVdos = articleMapper.selectSlugPages();
		return new PageInfo<>(articleVdos);
	}

	@Override
	public ResponseResult deletePageById(Long articleId) {
		int c = articleMapper.deleteArticleById(articleId);
		return ResponseResult.fail("删除失败！");
	}


}
