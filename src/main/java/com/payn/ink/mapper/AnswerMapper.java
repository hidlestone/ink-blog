package com.payn.ink.mapper;

import com.payn.ink.domain.Answer;
import com.payn.ink.vo.vdo.AnswerVdo;

import java.util.List;

public interface AnswerMapper {
	
	List<AnswerVdo> getAllAnswerByQuestionId(Long questionId);

	int insertSelective(Answer answerPo);

	List<AnswerVdo> getAnswerPage(Long questionId);

	int deleteAnswerById(Long answerId);
}