package com.payn.ink.controller.admin;

import com.github.pagehelper.PageInfo;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.ArticleMetaService;
import com.payn.ink.service.ArticleService;
import com.payn.ink.service.PageService;
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
 * @author: payn
 * @date: 2020/8/25 0:12
 */
@Controller
@RequestMapping("/admin/pages")
public class PageManageController {

	@Autowired
	private PageService pageService;
	@Autowired
	private ArticleMetaService articleMetaService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 跳转到页面管理页面
	 */
	@RequestMapping(value = "")
	public String pages(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
						@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
		PageInfo<ArticleVdo> pages = pageService.selectSlugPages(pageNum, pageSize);
		request.setAttribute("pages", pages);
		request.setAttribute("active", "pages");
		return "admin/pages";
	}

	/**
	 * 文章编辑页面
	 */
	@RequestMapping("/goPublish")
	public String goPublish(HttpServletRequest request) {
		//分类下拉列表
		List<ArticleMetaVdo> categories = articleMetaService.getAllMetaCategory();
		request.setAttribute("categories", categories);
		request.setAttribute("active", "edit");//菜单选中
		return "admin/article_edit";
	}

	/**
	 * 删除网站页面
	 */
	@ResponseBody
	@DeleteMapping("/delete/{articleId}")
	public ResponseResult deletePage(@RequestParam("articleId") Long articleId) {
		return pageService.deletePageById(articleId);
	}

	/**
	 * 跳转到页面编辑页面，即文章编辑页面
	 */
	@GetMapping("/edit/{articleId}")
	public String editPage(@PathVariable("articleId") Long articleId, HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
		//文章
		ArticleVdo articleVdo = articleService.getArticleDetailById(articleId);
		//文章分类名称
		String categoryName = articleMetaService.getMetaNameByPrimaryKey(articleVdo.getArticleMetaCatId());
		//分类下拉列表
		List<ArticleMetaVdo> categories = articleMetaService.getAllMetaCategory();
		request.setAttribute("categories", categories);
		request.setAttribute("article", articleVdo);
		request.setAttribute("active", "articles");
		return "admin/article_edit";
	}

}
