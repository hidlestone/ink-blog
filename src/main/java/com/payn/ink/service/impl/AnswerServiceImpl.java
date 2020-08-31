package com.payn.ink.service.impl;

import com.payn.ink.domain.Answer;
import com.payn.ink.mapper.AnswerMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: payn
 * @date: 2020/8/25 19:41
 */
@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerMapper answerMapper;

	@Override
	public ResponseResult addAdminAnswer(Answer answer) {
		int c = answerMapper.insertSelective(answer);
		return ResponseResult.success("保存成功！");
	}

	@Override
	public ResponseResult deleteAnswerById(Long answerId) {
		int c = answerMapper.deleteAnswerById(answerId);
		return ResponseResult.success("删除成功！");
	}
}
