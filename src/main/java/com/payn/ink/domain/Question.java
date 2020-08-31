package com.payn.ink.domain;

import java.util.Date;

public class Question {
	private Long questionId;

	private String questionerName;

	private String questionerMail;

	private String questionerIp;

	private String questionerUrl;

	private String questionStatus;

	private Date gmtCreate;

	private Date gmtModified;

	private String content;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionerName() {
		return questionerName;
	}

	public void setQuestionerName(String questionerName) {
		this.questionerName = questionerName == null ? null : questionerName.trim();
	}

	public String getQuestionerMail() {
		return questionerMail;
	}

	public void setQuestionerMail(String questionerMail) {
		this.questionerMail = questionerMail == null ? null : questionerMail.trim();
	}

	public String getQuestionerIp() {
		return questionerIp;
	}

	public void setQuestionerIp(String questionerIp) {
		this.questionerIp = questionerIp == null ? null : questionerIp.trim();
	}

	public String getQuestionerUrl() {
		return questionerUrl;
	}

	public void setQuestionerUrl(String questionerUrl) {
		this.questionerUrl = questionerUrl == null ? null : questionerUrl.trim();
	}

	public String getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}