package com.payn.ink.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payn.ink.domain.ArticleComment;
import com.payn.ink.mapper.ArticleCommentMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.ArticleCommentService;
import com.payn.ink.vo.vdo.ArticleCommentVdo;
import com.payn.ink.vo.vdo.ArticleSubCommentVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/19 9:19
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

	@Autowired
	private ArticleCommentMapper articleCommentMapper;

	@Override
	public PageInfo<ArticleCommentVdo> getArticleCommentWithPage(Long articleId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize, "articleCommentId DESC");
		//主楼评论
		List<ArticleCommentVdo> mainCommentList = articleCommentMapper.getMainComment(articleId);
		PageInfo<ArticleCommentVdo> articleCommentVdoPageInfo = new PageInfo<>(mainCommentList);
		if (!CollectionUtils.isEmpty(mainCommentList)) {
			for (ArticleCommentVdo mainComment : mainCommentList) {
				List<ArticleSubCommentVdo> subCommentList = articleCommentMapper.getSubCommentList(mainComment.getArticleCommentId());
				mainComment.setSubCommentList(subCommentList);
			}
			articleCommentVdoPageInfo.setList(mainCommentList);
		}
		return articleCommentVdoPageInfo;
	}

	@Override
	public String getCommentatorNameById(Long articleCommentId) {
		return articleCommentMapper.getCommentatorNameById(articleCommentId);
	}

	@Override
	public List<ArticleComment> recentComments(Integer pageSize) {
		PageHelper.startPage(1, pageSize);
		return articleCommentMapper.recentComments();
	}

	@Override
	public int saveComment(ArticleComment articleComment) {
		return articleCommentMapper.insertSelective(articleComment);
	}

	@Override
	public PageInfo<ArticleCommentVdo> getCommentPage(int pageNum, int pageSize, HttpServletRequest request) {
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleCommentVdo> articleCommentList = articleCommentMapper.getCommentPage();
		return new PageInfo<>(articleCommentList);
	}

	@Override
	public ResponseResult deleteCommentById(Long articleCommentId) {
		int c = articleCommentMapper.deleteCommentById(articleCommentId);
		return ResponseResult.success("删除成功！");
	}

	@Override
	public PageInfo<ArticleCommentVdo> showRelyListById(int pageNum, int pageSize, Long articleCommentId) {
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleCommentVdo> articleCommentVdos = articleCommentMapper.getRelyListById(articleCommentId);
		return new PageInfo<>(articleCommentVdos);
	}

}
