package com.payn.ink.vo.vdo;

/**
 * @author: payn
 * @date: 2020/8/23 21:11
 */
public class QuestionVdo {

	private Long questionId;

	private String questionerName;

	private String questionerMail;

	private String questionerIp;

	private String questionerUrl;

	private String gmtCreateStr;

	private String gmtModifiedStr;

	private String content;

	private Integer questionStatus;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(Integer questionStatus) {
		this.questionStatus = questionStatus;
	}
}
