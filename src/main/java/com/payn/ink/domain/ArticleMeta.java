package com.payn.ink.domain;

import java.io.Serializable;
import java.util.Date;

public class ArticleMeta implements Serializable {
	/**
	 * 博客文章tag|分类id
	 */
	private Long articleMetaId;

	/**
	 * 文章tag|分类名称
	 */
	private String metaName;

	/**
	 * 导航处slug名称
	 */
	private String slug;

	/**
	 * 类型：tag|category
	 */
	private String metaType;

	/**
	 * 分类或标签的描述
	 */
	private String description;

	/**
	 * 排序字段
	 */
	private Integer sort;

	/**
	 * 父级分类id
	 */
	private Integer parent;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 更改时间
	 */
	private Date gmtModified;

	private static final long serialVersionUID = 1L;

	public Long getArticleMetaId() {
		return articleMetaId;
	}

	public void setArticleMetaId(Long articleMetaId) {
		this.articleMetaId = articleMetaId;
	}

	public String getMetaName() {
		return metaName;
	}

	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getMetaType() {
		return metaType;
	}

	public void setMetaType(String metaType) {
		this.metaType = metaType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
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