package com.payn.ink.vo.vdo;

/**
 * @author: payn
 * @date: 2020/8/24 21:00
 */
public class ArticleMetaManageVdo {

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

	private Integer count;

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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
