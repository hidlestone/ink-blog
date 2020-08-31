package com.payn.ink.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payn.ink.constant.ErrorCode;
import com.payn.ink.constant.TypeEnum;
import com.payn.ink.domain.Answer;
import com.payn.ink.domain.Question;
import com.payn.ink.domain.User;
import com.payn.ink.mapper.AnswerMapper;
import com.payn.ink.mapper.QuestionMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.QuestionService;
import com.payn.ink.service.UserService;
import com.payn.ink.util.EHCacheUtil;
import com.payn.ink.util.IPUtil;
import com.payn.ink.util.RegExpValidatorUtil;
import com.payn.ink.vo.invo.QuestionAnswerInVo;
import com.payn.ink.vo.vdo.AnswerVdo;
import com.payn.ink.vo.vdo.QuestionAnswerVdo;
import com.payn.ink.vo.vdo.QuestionVdo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/23 21:04
 */
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private AnswerMapper answerMapper;
	@Autowired
	private UserService userService;

	@Override
	public PageInfo<QuestionAnswerVdo> getQuestionAnswerPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<QuestionAnswerVdo> questionAnswerVdoList = questionMapper.getQuestionAnswerPage();
		//获取问题的答案
		for (QuestionAnswerVdo questionAnswer : questionAnswerVdoList) {
			List<AnswerVdo> answerVdoList = answerMapper.getAllAnswerByQuestionId(questionAnswer.getQuestionId());
			questionAnswer.setAnswerList(answerVdoList);
		}
		return new PageInfo<>(questionAnswerVdoList);
	}

	@Override
	public ResponseResult saveQuestionAnswer(QuestionAnswerInVo inVo, HttpServletRequest request) {
		//ip地址+评论的文章id
		String key = IPUtil.getIpAddrByRequest(request) + ":question";
		String value = (String) EHCacheUtil.get(TypeEnum.COMMENTS_FREQUENCY.getType(), key);
		if (StringUtils.isNotEmpty(value)) {
			return ResponseResult.fail("您发表内容太快了(30s)，请稍后再试");
		}
		//参数表单校验
		String ref = request.getHeader("Referer");
		if (StringUtils.isBlank(ref)) {
			return ResponseResult.fail(ErrorCode.BAD_REQUEST);
		}
		if (StringUtils.isBlank(inVo.getAuthorName())) {
			return ResponseResult.fail("姓名不能为空");
		}
		User adminUser = userService.findAdminUser();
		if (inVo.getAuthorName().toLowerCase().contains(adminUser.getUsername().toLowerCase())) {
			return ResponseResult.fail("当前使用的昵称已存在！");
		}
		if (inVo.getAuthorName().length() > 12) {
			return ResponseResult.fail("姓名不能超过12个字符");
		}
		if (StringUtils.isNotBlank(inVo.getAuthorMail()) && !RegExpValidatorUtil.isEmail(inVo.getAuthorMail())) {
			return ResponseResult.fail("请输入正确的邮箱格式");
		}
		if (StringUtils.isNotBlank(inVo.getAuthorUrl()) && !RegExpValidatorUtil.isURL(inVo.getAuthorUrl())) {
			return ResponseResult.fail("请输入正确的URL格式");
		}
		if (StringUtils.isEmpty(inVo.getContent()) || inVo.getContent().length() > 1000) {
			return ResponseResult.fail("请输入1000个字符以内的内容！");
		}
		//提问
		if (StringUtils.isEmpty(inVo.getQuestionId())) {
			Question questionPo = new Question();
			questionPo.setQuestionerName(inVo.getAuthorName());
			questionPo.setQuestionerMail(inVo.getAuthorMail());
			questionPo.setQuestionerIp(IPUtil.getIpAddrByRequest(request));
			questionPo.setQuestionerUrl(inVo.getAuthorUrl());
			questionPo.setContent(inVo.getContent());
			questionPo.setQuestionStatus("1");
			int c1 = questionMapper.insertSelective(questionPo);
			EHCacheUtil.put(TypeEnum.COMMENTS_FREQUENCY.getType(), key, "1");
			return ResponseResult.SUCCESS();
		} else {
			//回答提问
			Answer answerPo = new Answer();
			answerPo.setQuestionId(Long.parseLong(inVo.getQuestionId()));
			answerPo.setAnswerName(inVo.getAuthorName());
			answerPo.setAnswerMail(inVo.getAuthorMail());
			answerPo.setAnswerUrl(inVo.getAuthorUrl());
			answerPo.setContent(inVo.getContent());
			answerPo.setAnswerStatus("1");
			int c2 = answerMapper.insertSelective(answerPo);
			EHCacheUtil.put(TypeEnum.COMMENTS_FREQUENCY.getType(), key, "1");
			return ResponseResult.SUCCESS();
		}
	}

	@Override
	public PageInfo<QuestionVdo> getQuestionPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<QuestionVdo> questionVdos = questionMapper.getQuestionPage();
		return new PageInfo<>(questionVdos);
	}

	@Override
	public ResponseResult deleteQuestionById(Long questionId) {
		int c = questionMapper.deleteQuestionById(questionId);
		return ResponseResult.success("删除成功！");
	}

	@Override
	public PageInfo<AnswerVdo> getAnswerPage(int pageNum, int pageSize, Long questionId) {
		PageHelper.startPage(pageNum, pageSize);
		List<AnswerVdo> answerVdoList = answerMapper.getAnswerPage(questionId);
		return new PageInfo<>(answerVdoList);
	}
}
