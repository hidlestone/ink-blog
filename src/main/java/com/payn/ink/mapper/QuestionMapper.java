package com.payn.ink.mapper;

import com.github.pagehelper.PageInfo;
import com.payn.ink.domain.Question;
import com.payn.ink.vo.vdo.QuestionAnswerVdo;
import com.payn.ink.vo.vdo.QuestionVdo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionMapper {

	List<QuestionAnswerVdo> getQuestionAnswerPage();

	int insertSelective(Question questionPo);

	int countQuestionNum();

	List<QuestionVdo> getQuestionPage();

	int deleteQuestionById(Long questionId);
}
