package com.payn.ink.vo.vdo;

/**
 * @author: payn
 * @date: 2020/8/19 14:19
 */
public class ArticleMetaVdo {
	
	private Long articleMetaId;
	private String metaName;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
