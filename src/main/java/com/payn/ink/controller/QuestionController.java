package com.payn.ink.controller;

import com.github.pagehelper.PageInfo;
import com.payn.ink.controller.base.ControllerUtil;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.QuestionService;
import com.payn.ink.vo.invo.QuestionAnswerInVo;
import com.payn.ink.vo.vdo.QuestionAnswerVdo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: payn
 * @date: 2020/8/23 21:02
 */
@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	/**
	 * 问答页面
	 */
	@RequestMapping("/question/{pageNum}")
	public String poor(@PathVariable(value = "pageNum") Integer pageNum,
					   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest request) {
		//分页
		String cp = request.getParameter("cp");
		if (StringUtils.isEmpty(cp)) {
			cp = "1";
		}
		PageInfo<QuestionAnswerVdo> questionAnswerPageInfo = questionService.getQuestionAnswerPage(Integer.valueOf(cp), pageSize);
		request.setAttribute("allowQuestion", true);
		request.setAttribute("title", "你问我答");
		request.setAttribute("questionAnswers", questionAnswerPageInfo);
		return ControllerUtil.render("questionanswer");
	}

	@ResponseBody
	@PostMapping("/question/savequestionanswer")
	public ResponseResult saveQuestionAnswer(QuestionAnswerInVo inVo, HttpServletRequest request) {
		return questionService.saveQuestionAnswer(inVo, request);
	}

}
