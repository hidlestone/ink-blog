package com.payn.ink.vo.outvo;

import com.github.pagehelper.PageInfo;
import com.payn.ink.vo.vdo.ArticleCommentVdo;
import com.payn.ink.vo.vdo.ArticleVdo;

/**
 * @author: payn
 * @date: 2020/8/19 10:10
 */
public class ArticleDetailOutVo {

	private ArticleVdo articleVdo;

	private PageInfo<ArticleCommentVdo> commentPageInfo;

	public ArticleVdo getArticleVdo() {
		return articleVdo;
	}

	public void setArticleVdo(ArticleVdo articleVdo) {
		this.articleVdo = articleVdo;
	}

	public PageInfo<ArticleCommentVdo> getCommentPageInfo() {
		return commentPageInfo;
	}

	public void setCommentPageInfo(PageInfo<ArticleCommentVdo> commentPageInfo) {
		this.commentPageInfo = commentPageInfo;
	}
}
