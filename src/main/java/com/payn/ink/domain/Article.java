package com.payn.ink.domain;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
	/**
	 * 博客文章id
	 */
	private Long articleId;

	/**
	 * 作者id
	 */
	private Long userId;

	/**
	 * 文章所属分类，默认为：0-默认分类
	 */
	private Long articleMetaCatId;

	/**
	 * 文章标题
	 */
	private String title;

	/**
	 * 文章链接
	 */
	private String slug;

	/**
	 * 文章缩略图
	 */
	private String thumbImg;

	/**
	 * 文章内容
	 */
	private String content;

	/**
	 * 文章类型：page|post
	 */
	private String articleType;

	/**
	 * 文章状态：0-未发布，1-已发布
	 */
	private String articleStatus;

	/**
	 * 文章使用的编辑器：默认markdown
	 */
	private String editorType;

	/**
	 * 文章点击数
	 */
	private Long hit;

	/**
	 * 文章评论数
	 */
	private Long commentNum;

	/**
	 * 是否允许评论：0-不允许，1-允许
	 */
	private String allowComment;

	/**
	 * 是否允许PING：0-不允许，1-允许
	 */
	private String allowPing;

	/**
	 * 是否允许反馈：0-不允许，1-允许
	 */
	private String allowFeed;

	/**
	 * 是否允许前端展示缩略图(即首图)：0-不允许，1-允许
	 */
	private String allowThumbImg;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 更改时间
	 */
	private Date gmtModified;

	private static final long serialVersionUID = 1L;

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getArticleMetaCatId() {
		return articleMetaCatId;
	}

	public void setArticleMetaCatId(Long articleMetaCatId) {
		this.articleMetaCatId = articleMetaCatId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus;
	}

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public Long getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
	}

	public String getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}

	public String getAllowPing() {
		return allowPing;
	}

	public void setAllowPing(String allowPing) {
		this.allowPing = allowPing;
	}

	public String getAllowFeed() {
		return allowFeed;
	}

	public void setAllowFeed(String allowFeed) {
		this.allowFeed = allowFeed;
	}

	public String getAllowThumbImg() {
		return allowThumbImg;
	}

	public void setAllowThumbImg(String allowThumbImg) {
		this.allowThumbImg = allowThumbImg;
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