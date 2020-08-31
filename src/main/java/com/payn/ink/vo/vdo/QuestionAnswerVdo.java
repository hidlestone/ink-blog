package com.payn.ink.vo.vdo;

import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/23 21:07
 */
public class QuestionAnswerVdo {

	private Long questionId;

	private String questionerName;

	private String questionerMail;

	private String questionerIp;

	private String questionerUrl;

	private String questionGmtCreateStr;

	private String questionGmtModifiedStr;

	private String questionContent;

	private Long answerId;
	
	private String questionStatus;

	List<AnswerVdo> answerList;

	public String getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}

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
		this.questionerName = questionerName;
	}

	public String getQuestionerMail() {
		return questionerMail;
	}

	public void setQuestionerMail(String questionerMail) {
		this.questionerMail = questionerMail;
	}

	public String getQuestionerIp() {
		return questionerIp;
	}

	public void setQuestionerIp(String questionerIp) {
		this.questionerIp = questionerIp;
	}

	public String getQuestionerUrl() {
		return questionerUrl;
	}

	public void setQuestionerUrl(String questionerUrl) {
		this.questionerUrl = questionerUrl;
	}

	public String getQuestionGmtCreateStr() {
		return questionGmtCreateStr;
	}

	public void setQuestionGmtCreateStr(String questionGmtCreateStr) {
		this.questionGmtCreateStr = questionGmtCreateStr;
	}

	public String getQuestionGmtModifiedStr() {
		return questionGmtModifiedStr;
	}

	public void setQuestionGmtModifiedStr(String questionGmtModifiedStr) {
		this.questionGmtModifiedStr = questionGmtModifiedStr;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public List<AnswerVdo> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerVdo> answerList) {
		this.answerList = answerList;
	}
}
