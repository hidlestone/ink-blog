package com.payn.ink.domain;

import java.util.Date;

public class ArticleTag {
	
	private Long articleTagId;

	private Long articleId;

	private Long articleMetaTagId;

	private Date gmtCreate;

	private Date gmtModified;

	public Long getArticleTagId() {
		return articleTagId;
	}

	public void setArticleTagId(Long articleTagId) {
		this.articleTagId = articleTagId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getArticleMetaTagId() {
		return articleMetaTagId;
	}

	public void setArticleMetaTagId(Long articleMetaTagId) {
		this.articleMetaTagId = articleMetaTagId;
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