package com.payn.ink.service.impl;

import com.payn.ink.domain.User;
import com.payn.ink.mapper.ArticleCommentMapper;
import com.payn.ink.mapper.ArticleMapper;
import com.payn.ink.mapper.AttachMapper;
import com.payn.ink.mapper.QuestionMapper;
import com.payn.ink.service.AdminIndexService;
import com.payn.ink.vo.outvo.AdminIndexDataOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: payn
 * @date: 2020/8/24 16:37
 */
@Service
public class AdminIndexServiceImpl implements AdminIndexService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleCommentMapper articleCommentMapper;
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public AdminIndexDataOutVo getAdminIndexDataInfo(User user, HttpServletRequest request) {
		AdminIndexDataOutVo outVo = new AdminIndexDataOutVo();
		//文章数量
		int articleNum = articleMapper.countArticleNum();
		//留言数量
		int commentNum = articleCommentMapper.countCommentNum();
		//附件数量
		int attachNum = attachMapper.countAttachNum();
		//问题数量
		int questionNum = questionMapper.countQuestionNum();

		//上月文章发布数量
		int lastMonArticleCount = articleMapper.countLastMonthPubArticle();
		//上月附件数量
		int lastMonAttachCount = attachMapper.countLastMonthUpload();
		//上月评论数量
		int lastMonCommentNum = articleCommentMapper.countLastMonthComment();
		//本月文章发布数量
		int thisMonArticleCount = articleMapper.countThisMonthPubArticle();
		//本月附件数量
		int thisMonAttachCount = attachMapper.countThisMonthUpload();
		//本月评论数量
		int thisMonCommentNum = articleCommentMapper.countThisMonthComment();

		outVo.setArticleCount(articleNum);
		outVo.setCommentCount(commentNum);
		outVo.setAttachCount(attachNum);
		outVo.setQuestionCount(questionNum);
		outVo.setLastMonArticleCount(lastMonArticleCount);
		outVo.setLastMonAttachCount(lastMonAttachCount);
		outVo.setLastMonCommmentCount(lastMonArticleCount);
		outVo.setThisMonArticleCount(thisMonArticleCount);
		outVo.setThisMonAttachCount(thisMonAttachCount);
		outVo.setThisMonCommmentCount(thisMonCommentNum);
		return outVo;
	}
}
