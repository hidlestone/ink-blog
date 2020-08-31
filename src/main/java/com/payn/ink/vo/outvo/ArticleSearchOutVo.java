package com.payn.ink.vo.outvo;

import com.payn.ink.domain.Article;

import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/19 14:49
 */
public class ArticleSearchOutVo {

	private String type;
	private String key;
	private List<Article> articleList;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
}
