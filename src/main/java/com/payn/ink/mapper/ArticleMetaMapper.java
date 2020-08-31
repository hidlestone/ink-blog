package com.payn.ink.mapper;

import com.payn.ink.domain.ArticleMeta;
import com.payn.ink.vo.vdo.ArticleMetaManageVdo;
import com.payn.ink.vo.vdo.ArticleMetaVdo;

import java.util.List;

public interface ArticleMetaMapper {
	
	String getMetaNameByPrimaryKey(Long articleMetaCatId);

	List<ArticleMetaVdo> getAllArticleMeta();

	String getIconNameByArticleId(Long articleId);

	List<ArticleMetaVdo> getArticleTagsByArticleId(Long articleId);

	List<ArticleMetaManageVdo> getAllMetaByType(String metaType);

	int insertSelective(ArticleMeta articleMeta);

	int deleteByMetaId(Long articleMetaId);

	int updateByPrimaryKeySelective(ArticleMeta articleMeta);

	long countAticleInTag(Long articleMetaId);

	List<ArticleMetaVdo> getAllMetaCategory();

	List<ArticleMetaVdo> getAllMetaTag();

	List<ArticleMetaManageVdo> getTagCountInfo();

	int countByMetaName(ArticleMeta articleMeta);
}