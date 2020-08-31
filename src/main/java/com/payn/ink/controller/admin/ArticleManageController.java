package com.payn.ink.controller.admin;

import com.github.pagehelper.PageInfo;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.ArticleMetaService;
import com.payn.ink.service.ArticleService;
import com.payn.ink.vo.vdo.ArticleMetaVdo;
import com.payn.ink.vo.vdo.ArticleVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 文章管理
 *
 * @author: payn
 * @date: 2020/8/25 10:19
 */
@Controller
@RequestMapping("/admin/articles")
public class ArticleManageController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleMetaService articleMetaService;

	/**
	 * 文章管理页面
	 */
	@RequestMapping("")
	public String index(@RequestParam(value = "pageNmu", defaultValue = "1") int pageNum,
						@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
		PageInfo<ArticleVdo> articlePageInfo = articleService.getArticlePage(pageNum, pageSize);
		request.setAttribute("articles", articlePageInfo);
		request.setAttribute("active", "articles");
		return "admin/articles";
	}

	/**
	 * 编辑发布文章页面
	 */
	@RequestMapping("/goPublish")
	public String goPublish(HttpServletRequest request) {
		//分类下拉列表
		List<ArticleMetaVdo> categories = articleMetaService.getAllMetaCategory();
		request.setAttribute("categories", categories);
		//tag下拉列表
		List<ArticleMetaVdo> tags = articleMetaService.getAllMetaTag();
		request.setAttribute("tags", tags);
		request.setAttribute("active", "edit");
		return "admin/article_edit";
	}

	/**
	 * 编辑文章页面。
	 */
	@GetMapping("/edit/{articleId}")
	public String editArticle(@PathVariable("articleId") Long articleId, HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
		//文章
		ArticleVdo articleVdo = articleService.getArticleDetailById(articleId);
		//文章分类名称
		String categoryName = articleMetaService.getMetaNameByPrimaryKey(articleVdo.getArticleMetaCatId());
		//分类下拉列表
		List<ArticleMetaVdo> categories = articleMetaService.getAllMetaCategory();
		//tag下拉列表
		List<ArticleMetaVdo> tags = articleMetaService.getAllMetaTag();
		request.setAttribute("tags", tags);
		request.setAttribute("categories", categories);
		request.setAttribute("article", articleVdo);
		request.setAttribute("active", "articles");
		return "admin/article_edit";
	}

	/**
	 * 删除文章
	 */
	@ResponseBody
	@DeleteMapping("/delete/{articleId}")
	public ResponseResult deleteArticle(@PathVariable("articleId") Long articleId) {
		return articleService.deleteByArticleId(articleId);
	}

	/**
	 * 文章关键词查询
	 */
	@PostMapping("/search")
	public String searchArticleBy(@RequestParam(value = "keyword") String keyword,
								  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
								  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
		PageInfo<ArticleVdo> articlePageInfo = articleService.selectArticlesByKeyword(pageNum, pageSize, keyword);
		request.setAttribute("articles", articlePageInfo);
		request.setAttribute("active", "article");
		request.setAttribute("keyword", keyword);
		return "admin/articles";
	}

}
