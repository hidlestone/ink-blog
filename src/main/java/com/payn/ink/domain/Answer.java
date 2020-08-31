package com.payn.ink.domain;

import java.util.Date;

public class Answer {
	private Long answerId;

	private Long questionId;

	private String answerName;

	private String answerMail;

	private String answerIp;

	private String answerUrl;

	private String answerStatus;
	
	private Date gmtCreate;

	private Date gmtModified;

	private String content;

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName == null ? null : answerName.trim();
	}

	public String getAnswerMail() {
		return answerMail;
	}

	public void setAnswerMail(String answerMail) {
		this.answerMail = answerMail == null ? null : answerMail.trim();
	}

	public String getAnswerIp() {
		return answerIp;
	}

	public void setAnswerIp(String answerIp) {
		this.answerIp = answerIp == null ? null : answerIp.trim();
	}

	public String getAnswerUrl() {
		return answerUrl;
	}

	public void setAnswerUrl(String answerUrl) {
		this.answerUrl = answerUrl == null ? null : answerUrl.trim();
	}

	public String getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
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