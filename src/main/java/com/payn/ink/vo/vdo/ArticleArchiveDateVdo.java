package com.payn.ink.vo.vdo;

import com.payn.ink.domain.Article;

import java.util.Date;
import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/19 11:15
 */
public class ArticleArchiveDateVdo {

	//日期
	private String dateStr;
	//文章数量
	private Integer count;
	//文章记录
	private List<ArticleVdo> articleList;

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ArticleVdo> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleVdo> articleList) {
		this.articleList = articleList;
	}
}
