package com.payn.ink.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payn.ink.domain.Article;
import com.payn.ink.domain.ArticleTag;
import com.payn.ink.mapper.ArticleMapper;
import com.payn.ink.mapper.ArticleMetaMapper;
import com.payn.ink.mapper.ArticleTagMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.ArticleService;
import com.payn.ink.util.DateUtil;
import com.payn.ink.vo.invo.ArticlePublishInVo;
import com.payn.ink.vo.outvo.ArchiveDateArticleOutVo;
import com.payn.ink.vo.vdo.ArticleArchiveDateVdo;
import com.payn.ink.vo.vdo.ArticleMetaVdo;
import com.payn.ink.vo.vdo.ArticleVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: payn
 * @date: 2020/8/18 21:57
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleMetaMapper articleMetaMapper;
	@Autowired
	private ArticleTagMapper articleTagMapper;

	@Override
	public PageInfo<ArticleVdo> getPostArticlePage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize, "gmtCreate DESC");
		List<ArticleVdo> articles = articleMapper.getPostArticlePage();
		return new PageInfo<>(articles);
	}

	@Override
	public ArticleVdo getArticleDetailById(Long articleId) throws InvocationTargetException, IllegalAccessException {
		ArticleVdo vdo = articleMapper.selectByPrimaryKey(articleId);
		List<ArticleMetaVdo> articleTagList = articleMetaMapper.getArticleTagsByArticleId(articleId);
		vdo.setTagList(articleTagList);
		return vdo;
	}

	@Override
	public ArticleVdo getArticleBySlug(String slug) {
		ArticleVdo articleVdo = articleMapper.getArticleBySlug(slug);
		return articleVdo;
	}

	@Override
	public ArchiveDateArticleOutVo archiveByDate(int pageNum, int pageSize) {
		ArchiveDateArticleOutVo outVo = new ArchiveDateArticleOutVo();
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleArchiveDateVdo> archiveDateVdoList = articleMapper.selectArcticleArcheiveByMonth(pageNum, pageSize);
		for (ArticleArchiveDateVdo archiveDate : archiveDateVdoList) {
			String dateStr = archiveDate.getDateStr();
			Date startDate = DateUtil.dateFormat(dateStr, "yyyy年MM月");
			Calendar calen = Calendar.getInstance();
			calen.setTime(startDate);
			calen.set(Calendar.DAY_OF_MONTH, calen.getActualMaximum(Calendar.DAY_OF_MONTH));//此月份最后一天
			calen.set(Calendar.HOUR_OF_DAY, 23);
			calen.set(Calendar.MINUTE, 59);
			calen.set(Calendar.SECOND, 59);
			calen.set(Calendar.MILLISECOND, 999);
			Date endDate = calen.getTime();

			Map param = new HashMap();
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			List<ArticleVdo> articleList = articleMapper.getBetweenDateArticleList(param);
			archiveDate.setArticleList(articleList);
		}
		PageInfo<ArticleArchiveDateVdo> archiveDateVdoPageInfo = new PageInfo<>(archiveDateVdoList);
		outVo.setArchiveDateVdoPageInfo(archiveDateVdoPageInfo);
		return outVo;
	}

	@Override
	public List<ArticleVdo> getArticleByTagId(Long articleMetaId) {
		return articleMapper.getArticleByTagId(articleMetaId);
	}

	@Override
	public List<ArticleVdo> getArticleByKeyword(String keyword) {
		return articleMapper.getArticleByKeyword(keyword);
	}

	@Override
	public String getThumbImgById(Long articleId) {
		return articleMapper.getThumbImgById(articleId);
	}

	@Override
	public String getPrevArticleId(Long articleId) {
		return articleMapper.getPrevArticleId(articleId);
	}

	@Override
	public String getNextArticleId(Long articleId) {
		return articleMapper.getNextArticleId(articleId);
	}

	@Override
	public List<Article> recentArticles(Integer pageSize) {
		PageHelper.startPage(1, pageSize);
		return articleMapper.recentArticles();
	}

	@Override
	public List<ArticleVdo> getAllowRssArticle() {
		return articleMapper.getAllowRssArticle();
	}

	@Override
	public int updateCachedHitByArticleId(Long articleId, Long hit) {
		Map param = new HashMap();
		param.put("articleId", articleId);
		param.put("hit", hit);
		return articleMapper.updateCachedHitByArticleId(param);
	}

	@Override
	public PageInfo<ArticleVdo> getArticlePage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleVdo> articleVdos = articleMapper.getArticlePage();
		return new PageInfo<>(articleVdos);
	}

	@Override
	public ResponseResult deleteByArticleId(Long articleId) {
		int c = articleMapper.deleteArticleById(articleId);
		return ResponseResult.success("删除成功！");
	}

	@Override
	public PageInfo<ArticleVdo> selectArticlesByKeyword(int pageNum, int pageSize, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleVdo> articleVdos = articleMapper.getArticleByKeyword(keyword);
		return new PageInfo<>(articleVdos);
	}

	@Override
	@Transactional
	public ResponseResult publishArticle(ArticlePublishInVo articlePo, Long userId) {
		articlePo.setUserId(userId);
		articlePo.setArticleType("post");
		articlePo.setHit(0L);
		articlePo.setCommentNum(0L);
		articlePo.setEditorType("markdown");
		Long c = articleMapper.insertSelective(articlePo);
		//删除之前文章对用的tag
		int c1 = articleTagMapper.deleteByArticleId(articlePo.getArticleId());
		//重新插入文章和tag的对应关系
		List<Long> tagIdList = articlePo.getTagList();
		for (Long tagId : tagIdList) {
			ArticleTag articleTag = new ArticleTag();
			articleTag.setArticleId(articlePo.getArticleId());
			articleTag.setArticleMetaTagId(tagId);
			int c2 = articleTagMapper.insertSelective(articleTag);
		}
		return ResponseResult.fail("保存成功！");
	}

	@Override
	@Transactional
	public ResponseResult updateArticle(ArticlePublishInVo article) {
		//删除之前文章对用的tag
		int c1 = articleTagMapper.deleteByArticleId(article.getArticleId());
		//重新插入文章和tag的对应关系
		List<Long> tagIdList = article.getTagList();
		for (Long tagId : tagIdList) {
			ArticleTag articleTag = new ArticleTag();
			articleTag.setArticleMetaTagId(tagId);
			articleTag.setArticleId(article.getArticleId());
			int c2 = articleTagMapper.insertSelective(articleTag);
		}
		int c = articleMapper.updateByArticleIdSelective(article);
		return ResponseResult.success("更新文章成功！");
	}

	@Override
	public int updateCommentNum(Long articleId) {
		return articleMapper.updateCommentNum(articleId);
	}

	@Override
	public List<ArticleVdo> getArticlearticleMetaId(Long articleMetaId) {
		return articleMapper.getArticlearticleMetaId(articleMetaId);
	}
}
