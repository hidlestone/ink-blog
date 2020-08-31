package com.payn.ink.controller.admin;

import com.payn.ink.domain.User;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.ArticleService;
import com.payn.ink.util.LoginUtil;
import com.payn.ink.vo.invo.ArticlePublishInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 文章编辑
 *
 * @author: payn
 * @date: 2020/8/25 10:45
 */
@Controller
@RequestMapping("/admin/articles")
public class ArticleEditController {

	@Autowired
	private ArticleService articleService;

	/**
	 * 发布文章
	 */
	@ResponseBody
	@PostMapping("/publish")
	public ResponseResult addArticle(@RequestBody ArticlePublishInVo article, HttpServletRequest request) {
		User user = LoginUtil.getLoginUser(request);
		return articleService.publishArticle(article, user.getUserId());
	}

	/**
	 * 修改文章
	 */
	@ResponseBody
	@PostMapping("/update")
	public ResponseResult update(@RequestBody ArticlePublishInVo article, HttpServletRequest request) {
		return articleService.updateArticle(article);
	}

}
