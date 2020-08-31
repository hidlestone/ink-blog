package com.payn.ink.service;

import com.github.pagehelper.PageInfo;
import com.payn.ink.domain.Article;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.invo.ArticlePublishInVo;
import com.payn.ink.vo.outvo.ArchiveDateArticleOutVo;
import com.payn.ink.vo.vdo.ArticleVdo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/18 21:57
 */
public interface ArticleService {

	PageInfo<ArticleVdo> getPostArticlePage(Integer pageNum, Integer pageSize);

	ArticleVdo getArticleDetailById(Long articleId) throws InvocationTargetException, IllegalAccessException;

	ArticleVdo getArticleBySlug(String slug);

	ArchiveDateArticleOutVo archiveByDate(int pageNum, int pageSize);

	List<ArticleVdo> getArticleByTagId(Long articleMetaId);

	List<ArticleVdo> getArticleByKeyword(String keyword);

	String getThumbImgById(Long articleId);

	String getPrevArticleId(Long articleId);

	String getNextArticleId(Long articleId);

	List<Article> recentArticles(Integer pageSize);

	List<ArticleVdo> getAllowRssArticle();

	int updateCachedHitByArticleId(Long articleId, Long hitCount);

	PageInfo<ArticleVdo> getArticlePage(int pageNum, int pageSize);

	ResponseResult deleteByArticleId(Long articleId);

	PageInfo<ArticleVdo> selectArticlesByKeyword(int pageNum, int pageSize, String keyword);

	ResponseResult publishArticle(ArticlePublishInVo article, Long userId);

	ResponseResult updateArticle(ArticlePublishInVo article);

	int updateCommentNum(Long articleId);

	List<ArticleVdo> getArticlearticleMetaId(Long articleMetaId);
}
