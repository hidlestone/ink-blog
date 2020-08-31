package com.payn.ink.service;

import com.github.pagehelper.PageInfo;
import com.payn.ink.domain.ArticleComment;
import com.payn.ink.domain.User;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.vdo.ArticleCommentVdo;
import com.payn.ink.vo.vdo.ArticleVdo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author payn
 * @date 2020/8/19 9:19
 */
public interface ArticleCommentService {

	PageInfo<ArticleCommentVdo> getArticleCommentWithPage(Long articleId, int pageNum, int pageSize);

	String getCommentatorNameById(Long articleCommentId);

	List<ArticleComment> recentComments(Integer pageSize);

	int saveComment(ArticleComment articleComment);

	PageInfo<ArticleCommentVdo> getCommentPage(int pageNum, int pageSize, HttpServletRequest request);

	ResponseResult deleteCommentById(Long articleCommentId);

	PageInfo<ArticleCommentVdo> showRelyListById(int pageNum, int pageSize, Long articleCommentId);
}
