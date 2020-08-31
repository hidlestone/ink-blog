package com.payn.ink.vo.outvo;

/**
 * @author: payn
 * @date: 2020/8/24 16:43
 */
public class AdminIndexDataOutVo {

	//文章数量
	private Integer articleCount;
	//访客数量
	private Integer visitorNum;
	//留言(主楼评论)数量
	private Integer commentCount;
	//附件数量
	private Integer attachCount;
	//问题数量
	private Integer questionCount;
	
	//上月文章发布数
	private Integer lastMonArticleCount;
	//上月附件上传数
	private Integer lastMonAttachCount;
	//上月访问量
	private Integer lastMonVisitorCount;
	//上月评论数(主楼+分支)
	private Integer lastMonCommmentCount;

	//本月文章发布数
	private Integer thisMonArticleCount;
	//本月附件上传数
	private Integer thisMonAttachCount;
	//本月访问量
	private Integer thisMonVisitorCount;
	//本月评论数(主楼+分支)
	private Integer thisMonCommmentCount;

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public Integer getVisitorNum() {
		return visitorNum;
	}

	public void setVisitorNum(Integer visitorNum) {
		this.visitorNum = visitorNum;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(Integer attachCount) {
		this.attachCount = attachCount;
	}

	public Integer getLastMonArticleCount() {
		return lastMonArticleCount;
	}

	public void setLastMonArticleCount(Integer lastMonArticleCount) {
		this.lastMonArticleCount = lastMonArticleCount;
	}

	public Integer getLastMonAttachCount() {
		return lastMonAttachCount;
	}

	public void setLastMonAttachCount(Integer lastMonAttachCount) {
		this.lastMonAttachCount = lastMonAttachCount;
	}

	public Integer getLastMonVisitorCount() {
		return lastMonVisitorCount;
	}

	public void setLastMonVisitorCount(Integer lastMonVisitorCount) {
		this.lastMonVisitorCount = lastMonVisitorCount;
	}

	public Integer getLastMonCommmentCount() {
		return lastMonCommmentCount;
	}

	public void setLastMonCommmentCount(Integer lastMonCommmentCount) {
		this.lastMonCommmentCount = lastMonCommmentCount;
	}

	public Integer getThisMonArticleCount() {
		return thisMonArticleCount;
	}

	public void setThisMonArticleCount(Integer thisMonArticleCount) {
		this.thisMonArticleCount = thisMonArticleCount;
	}

	public Integer getThisMonAttachCount() {
		return thisMonAttachCount;
	}

	public void setThisMonAttachCount(Integer thisMonAttachCount) {
		this.thisMonAttachCount = thisMonAttachCount;
	}

	public Integer getThisMonVisitorCount() {
		return thisMonVisitorCount;
	}

	public void setThisMonVisitorCount(Integer thisMonVisitorCount) {
		this.thisMonVisitorCount = thisMonVisitorCount;
	}

	public Integer getThisMonCommmentCount() {
		return thisMonCommmentCount;
	}

	public void setThisMonCommmentCount(Integer thisMonCommmentCount) {
		this.thisMonCommmentCount = thisMonCommmentCount;
	}

	public Integer getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}
}
