package com.payn.ink.vo.vdo;

import java.util.Date;

/**
 * @author: payn
 * @date: 2020/8/23 21:09
 */
public class AnswerVdo {

	private Long answerId;

	private Long questionId;

	private String answerName;

	private String answerMail;

	private String answerIp;

	private String answerUrl;

	private String answerStatus;

	private String answerGmtCreateStr;

	private Date answermtModifiedStr;

	private String answerContent;
	
	private String questionerName;

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
		this.answerName = answerName;
	}

	public String getAnswerMail() {
		return answerMail;
	}

	public void setAnswerMail(String answerMail) {
		this.answerMail = answerMail;
	}

	public String getAnswerIp() {
		return answerIp;
	}

	public void setAnswerIp(String answerIp) {
		this.answerIp = answerIp;
	}

	public String getAnswerUrl() {
		return answerUrl;
	}

	public void setAnswerUrl(String answerUrl) {
		this.answerUrl = answerUrl;
	}

	public String getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}

	public String getAnswerGmtCreateStr() {
		return answerGmtCreateStr;
	}

	public void setAnswerGmtCreateStr(String answerGmtCreateStr) {
		this.answerGmtCreateStr = answerGmtCreateStr;
	}

	public Date getAnswermtModifiedStr() {
		return answermtModifiedStr;
	}

	public void setAnswermtModifiedStr(Date answermtModifiedStr) {
		this.answermtModifiedStr = answermtModifiedStr;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getQuestionerName() {
		return questionerName;
	}

	public void setQuestionerName(String questionerName) {
		this.questionerName = questionerName;
	}
}
