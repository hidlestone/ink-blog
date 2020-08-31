package com.payn.ink.controller.admin;

import com.github.pagehelper.PageInfo;
import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.ArticleComment;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.ArticleCommentService;
import com.payn.ink.service.ArticleService;
import com.payn.ink.util.IPUtil;
import com.payn.ink.vo.vdo.ArticleCommentVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论管理
 *
 * @author: payn
 * @date: 2020/8/24 22:32
 */
@Controller
@RequestMapping("/admin/comments")
public class CommentManageController {

	@Autowired
	private ArticleCommentService articleCommentService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 评论管理页面
	 */
	@RequestMapping("")
	public String commentsPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
							   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
							   @RequestParam(value = "articleCommentId", defaultValue = "0") Long articleCommentId, HttpServletRequest request) {
		PageInfo<ArticleCommentVdo> commentPageInfo = null;
		if (null == articleCommentId || articleCommentId == 0) {
			//主评论
			commentPageInfo = articleCommentService.getCommentPage(pageNum, pageSize, request);
			request.setAttribute("type", "main");
			request.setAttribute("articleCommentId", "");
		} else {
			//子评论
			commentPageInfo = articleCommentService.showRelyListById(pageNum, pageSize, articleCommentId);
			request.setAttribute("type", "sub");
			request.setAttribute("articleCommentId", articleCommentId);
		}
		request.setAttribute("comments", commentPageInfo);
		request.setAttribute("active", "other");
		return "admin/comments";
	}

	/**
	 * 删除评论
	 */
	@ResponseBody
	@PostMapping("/delete")
	public ResponseResult delete(@RequestParam(value = "articleCommentId") Long articleCommentId) {
		return articleCommentService.deleteCommentById(articleCommentId);
	}

	/**
	 * 管理员添加回复
	 */
	@ResponseBody
	@PostMapping("/addrely")
	public ResponseResult addCommentRely(ArticleComment articleComment, HttpServletRequest request) {
		articleComment.setCommentatorName("payn");
		articleComment.setCommentatorMail("809566095@qq.com");
		articleComment.setCommentatorIp(IPUtil.getIpAddrByRequest(request));
		articleComment.setCommentatorUrl(InkConstants.INIT_CONFIG_MAP.get("site_url"));
		articleComment.setCommentStatus("1");
		//更新评论数量
		articleService.updateCommentNum(articleComment.getArticleId());
		int c = articleCommentService.saveComment(articleComment);
		return ResponseResult.success("回复成功！");
	}
}
