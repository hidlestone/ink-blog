package com.payn.ink.controller.admin;

import com.github.pagehelper.PageInfo;
import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.Answer;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.AnswerService;
import com.payn.ink.service.QuestionService;
import com.payn.ink.util.IPUtil;
import com.payn.ink.vo.vdo.AnswerVdo;
import com.payn.ink.vo.vdo.QuestionVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 问题管理页面
 *
 * @author: payn
 * @date: 2020/8/25 14:47
 */
@Controller
@RequestMapping("/admin/question")
public class QuestionManageController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;

	/**
	 * 问题管理页面
	 */
	@RequestMapping("")
	public String questionPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
							   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
							   @RequestParam(value = "questionId", defaultValue = "0") Long questionId, HttpServletRequest request) {
		PageInfo<QuestionVdo> questionVdoPageInfo = null;
		PageInfo<AnswerVdo> answerVdoPageInfo = null;
		if (null == questionId || questionId == 0) {
			//问题
			questionVdoPageInfo = questionService.getQuestionPage(pageNum, pageSize);
			request.setAttribute("type", "main");
			request.setAttribute("questionId", "");
		} else {
			//回答
			answerVdoPageInfo = questionService.getAnswerPage(pageNum, pageSize, questionId);
			request.setAttribute("type", "sub");
			request.setAttribute("questionId", questionId);
		}
		request.setAttribute("questions", questionVdoPageInfo);
		request.setAttribute("answers", answerVdoPageInfo);
		request.setAttribute("active", "other");
		return "admin/question";
	}

	@ResponseBody
	@PostMapping("/deletequestion")
	public ResponseResult questionDelete(@RequestParam(value = "questionId") Long questionId) {
		return questionService.deleteQuestionById(questionId);
	}

	@ResponseBody
	@PostMapping("/addanswer")
	public ResponseResult addAnswer(Answer answer, HttpServletRequest request) {
		answer.setAnswerName("payn");
		answer.setAnswerMail("809566095@qq.com");
		answer.setAnswerIp(IPUtil.getIpAddrByRequest(request));
		answer.setAnswerUrl(InkConstants.INIT_CONFIG_MAP.get("site_url"));
		answer.setAnswerStatus("1");
		return answerService.addAdminAnswer(answer);
	}

	@ResponseBody
	@PostMapping("/deleteanswer")
	public ResponseResult answerDelete(@RequestParam(value = "answerId") Long answerId) {
		return answerService.deleteAnswerById(answerId);
	}

}
