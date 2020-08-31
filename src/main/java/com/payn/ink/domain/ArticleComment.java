package com.payn.ink.domain;

import java.io.Serializable;
import java.util.Date;

public class ArticleComment implements Serializable {
	/**
	 * 文章主楼评论id
	 */
	private Long articleCommentId;

	/**
	 * 被评论的文章id
	 */
	private Long articleId;

	/**
	 * 评论者id：0或者null-游客评论,其他-用户评论
	 */
	private Long authorId;

	/**
	 * 主评论id，如果此条记录为子评论
	 */
	private Long mainCommentId;

	/**
	 * 评论者昵称
	 */
	private String commentatorName;

	/**
	 * 评论者邮箱
	 */
	private String commentatorMail;

	/**
	 * 评论者ip
	 */
	private String commentatorIp;

	/**
	 * 评论者网址url
	 */
	private String commentatorUrl;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 评论类型
	 */
	private String type;

	/**
	 * 主楼评论状态：0-未读，1-已读，2-已回复
	 */
	private String commentStatus;

	/**
	 * 父级评论id
	 */
	private Long parentId;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 更改时间
	 */
	private Date gmtModified;

	private static final long serialVersionUID = 1L;

	public Long getArticleCommentId() {
		return articleCommentId;
	}

	public void setArticleCommentId(Long articleCommentId) {
		this.articleCommentId = articleCommentId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getMainCommentId() {
		return mainCommentId;
	}

	public void setMainCommentId(Long mainCommentId) {
		this.mainCommentId = mainCommentId;
	}

	public String getCommentatorName() {
		return commentatorName;
	}

	public void setCommentatorName(String commentatorName) {
		this.commentatorName = commentatorName;
	}

	public String getCommentatorMail() {
		return commentatorMail;
	}

	public void setCommentatorMail(String commentatorMail) {
		this.commentatorMail = commentatorMail;
	}

	public String getCommentatorIp() {
		return commentatorIp;
	}

	public void setCommentatorIp(String commentatorIp) {
		this.commentatorIp = commentatorIp;
	}

	public String getCommentatorUrl() {
		return commentatorUrl;
	}

	public void setCommentatorUrl(String commentatorUrl) {
		this.commentatorUrl = commentatorUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}