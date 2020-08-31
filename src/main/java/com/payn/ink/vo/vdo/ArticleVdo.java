package com.payn.ink.vo.vdo;

import java.util.Date;
import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/18 22:04
 */
public class ArticleVdo {

	private Long articleId;

	private Long userId;

	private Long articleMetaCatId;

	//文章所属分类名称
	private String category;

	private String title;

	private String slug;

	private String thumbImg;

	private String content;

	private String articleType;

	private Integer articleStatus;

	private String editorType;

	private String tag;

	private Long hit;

	private Long commentNum;

	private String allowComment;

	private String allowPing;

	private String allowFeed;

	private String allowThumbImg;

	private Date gmtCreate;

	private Date gmtModified;

	private String publishDateStr;//发布日期

	private String modifiedDateStr;//修改日期

	private String gmtCreateStr;
	
	private String gmtModifiedStr;
	
	private List<ArticleMetaVdo> tagList;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Integer getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(Integer articleStatus) {
		this.articleStatus = articleStatus;
	}

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public String getPublishDateStr() {
		return publishDateStr;
	}

	public void setPublishDateStr(String publishDateStr) {
		this.publishDateStr = publishDateStr;
	}

	public String getModifiedDateStr() {
		return modifiedDateStr;
	}

	public void setModifiedDateStr(String modifiedDateStr) {
		this.modifiedDateStr = modifiedDateStr;
	}

	public String getGmtCreateStr() {
		return gmtCreateStr;
	}

	public void setGmtCreateStr(String gmtCreateStr) {
		this.gmtCreateStr = gmtCreateStr;
	}

	public String getGmtModifiedStr() {
		return gmtModifiedStr;
	}

	public void setGmtModifiedStr(String gmtModifiedStr) {
		this.gmtModifiedStr = gmtModifiedStr;
	}

	public List<ArticleMetaVdo> getTagList() {
		return tagList;
	}

	public void setTagList(List<ArticleMetaVdo> tagList) {
		this.tagList = tagList;
	}
}
