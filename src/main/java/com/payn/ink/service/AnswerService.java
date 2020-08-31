package com.payn.ink.service;

import com.payn.ink.domain.Answer;
import com.payn.ink.response.ResponseResult;

public interface AnswerService {
	
	ResponseResult addAdminAnswer(Answer answer);

	ResponseResult deleteAnswerById(Long answerId);
}
