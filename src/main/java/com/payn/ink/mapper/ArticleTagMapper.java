package com.payn.ink.mapper;

import com.payn.ink.domain.ArticleTag;

public interface ArticleTagMapper {
	
	int deleteByArticleId(Long articleId);

	int insertSelective(ArticleTag articleTag);
}
