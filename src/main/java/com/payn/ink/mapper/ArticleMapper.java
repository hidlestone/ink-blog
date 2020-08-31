package com.payn.ink.mapper;

import com.payn.ink.domain.Article;
import com.payn.ink.vo.invo.ArticlePublishInVo;
import com.payn.ink.vo.vdo.ArticleArchiveDateVdo;
import com.payn.ink.vo.vdo.ArticleVdo;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {

	List<ArticleVdo> getPostArticlePage();

	ArticleVdo selectByPrimaryKey(Long articleId);

	ArticleVdo getArticleBySlug(String slug);

	List<ArticleArchiveDateVdo> selectArcticleArcheiveByMonth(int pageNum, int pageSize);

	List<ArticleVdo> getBetweenDateArticleList(Map param);

	List<ArticleVdo> getArticleByTagId(Long articleMetaId);

	List<ArticleVdo> getArticleByKeyword(String keyword);

	String getThumbImgById(Long articleId);

	String getPrevArticleId(Long articleId);

	String getNextArticleId(Long articleId);

	List<Article> recentArticles();

	List<ArticleVdo> getAllowRssArticle();

	int updateCachedHitByArticleId(Map param);

	int countArticleNum();

	int countLastMonthPubArticle();

	int countThisMonthPubArticle();

	int updateArticleToDefaultCategory(Long articleMetaCatId);

	List<ArticleVdo> selectSlugPages();

	int deleteArticleById(Long id);

	List<ArticleVdo> getArticlePage();

	Long insertSelective(ArticlePublishInVo articlePo);

	int updateByArticleIdSelective(ArticlePublishInVo article);

	int updateCommentNum(Long articleId);

	int countAticleInCategory(Long articleMetaCatId);

	List<ArticleVdo> getArticlearticleMetaId(Long articleMetaId);
}