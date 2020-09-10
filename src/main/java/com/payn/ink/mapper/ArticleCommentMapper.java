package com.payn.ink.mapper;

import com.payn.ink.domain.ArticleComment;
import com.payn.ink.vo.vdo.ArticleCommentVdo;
import com.payn.ink.vo.vdo.ArticleSubCommentVdo;

import java.util.List;

public interface ArticleCommentMapper {

	List<ArticleCommentVdo> getMainComment(Long articleId);

	List<ArticleSubCommentVdo> getSubCommentList(Long articleCommentId);

	String getCommentatorNameById(Long articleCommentId);

	List<ArticleComment> recentComments();

	int insertSelective(ArticleComment articleComment);

	int countCommentNum();

	int countLastMonthComment();

	int countThisMonthComment();

	List<ArticleCommentVdo> getCommentPage();

	int deleteCommentById(Long articleCommentId);

	List<ArticleCommentVdo> getRelyListById(Long articleCommentId);

	String getCommentatorMainById(Long articleCommentId);
}