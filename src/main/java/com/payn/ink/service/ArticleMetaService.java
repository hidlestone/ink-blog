package com.payn.ink.service;

import com.payn.ink.vo.vdo.ArticleMetaVdo;

import java.util.List;

public interface ArticleMetaService {
	
	List<ArticleMetaVdo> getAllArticleMeta();

	String getIconNameByArticleId(Long articleId);

	String getMetaNameByPrimaryKey(Long articleMetaId);

	List<ArticleMetaVdo> getAllMetaCategory();

	List<ArticleMetaVdo> getAllMetaTag();
}
