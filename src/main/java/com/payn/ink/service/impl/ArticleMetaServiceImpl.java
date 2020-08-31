package com.payn.ink.service.impl;

import com.payn.ink.mapper.ArticleMetaMapper;
import com.payn.ink.service.ArticleMetaService;
import com.payn.ink.vo.vdo.ArticleMetaVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/19 14:05
 */
@Service
public class ArticleMetaServiceImpl implements ArticleMetaService {
	
	@Autowired
	private ArticleMetaMapper articleMetaMapper;
	
	@Override
	public List<ArticleMetaVdo> getAllArticleMeta() {
		return articleMetaMapper.getAllArticleMeta();
	}

	@Override
	public String getIconNameByArticleId(Long articleId) {
		return articleMetaMapper.getIconNameByArticleId(articleId);
	}

	@Override
	public String getMetaNameByPrimaryKey(Long articleMetaId) {
		return articleMetaMapper.getMetaNameByPrimaryKey(articleMetaId);
	}

	@Override
	public List<ArticleMetaVdo> getAllMetaCategory() {
		return articleMetaMapper.getAllMetaCategory();
	}

	@Override
	public List<ArticleMetaVdo> getAllMetaTag() {
		return articleMetaMapper.getAllMetaTag();
	}
}
