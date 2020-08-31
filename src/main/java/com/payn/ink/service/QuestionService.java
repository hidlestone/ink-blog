package com.payn.ink.service;

import com.github.pagehelper.PageInfo;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.invo.QuestionAnswerInVo;
import com.payn.ink.vo.vdo.AnswerVdo;
import com.payn.ink.vo.vdo.QuestionAnswerVdo;
import com.payn.ink.vo.vdo.QuestionVdo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: payn
 * @date: 2020/8/23 21:04
 */
public interface QuestionService {
	
	PageInfo<QuestionAnswerVdo> getQuestionAnswerPage(Integer pageNum, Integer pageSize);

	ResponseResult saveQuestionAnswer(QuestionAnswerInVo inVo, HttpServletRequest request);

	PageInfo<QuestionVdo> getQuestionPage(int pageNum, int pageSize);

	ResponseResult deleteQuestionById(Long questionId);

	PageInfo<AnswerVdo> getAnswerPage(int pageNum, int pageSize, Long questionId);
}
