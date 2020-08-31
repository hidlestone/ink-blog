package com.payn.ink.controller.admin;

import com.payn.ink.domain.ArticleMeta;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.CategoryTagService;
import com.payn.ink.vo.vdo.ArticleMetaManageVdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分类标签管理
 *
 * @author: payn
 * @date: 2020/8/24 20:48
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryTagManageController {

	@Autowired
	private CategoryTagService categoryTagService;

	/**
	 * 跳转到分类|标签设置页面
	 *
	 * @param request
	 * @author payn
	 * @date 2019/7/30 23:53
	 */
	@RequestMapping(value = "")
	public String categoryAndTag(HttpServletRequest request) {
		List<ArticleMetaManageVdo> categoryList = categoryTagService.getAllMetaByType("category");
		List<ArticleMetaManageVdo> tagList = categoryTagService.getAllMetaByType("tag");
		request.setAttribute("tags", tagList);
		request.setAttribute("categories", categoryList);
		request.setAttribute("active", "other");
		return "admin/category";
	}

	/**
	 * 添加文章分类
	 */
	@ResponseBody
	@PostMapping("/addcate")
	public ResponseResult addcate(ArticleMeta articleMeta) {
		return categoryTagService.addCategoryRecord(articleMeta);
	}

	/**
	 * 添加标签
	 */
	@ResponseBody
	@PostMapping("/addtag")
	public ResponseResult addtag(ArticleMeta articleMeta) {
		return categoryTagService.addTagRecord(articleMeta);
	}

	/**
	 * 修改文章分类
	 */
	@ResponseBody
	@PostMapping("/updatecate")
	public ResponseResult updatecate(ArticleMeta articleMeta) {
		return categoryTagService.updateCategory(articleMeta);
	}

	/**
	 * 修改标签
	 */
	@ResponseBody
	@PostMapping("/updatetag")
	public ResponseResult updatetag(ArticleMeta articleMeta) {
		return categoryTagService.updateTag(articleMeta);
	}


	/**
	 * 删除文章分类
	 */
	@ResponseBody
	@PostMapping("/deleteCate")
	public ResponseResult deleteCategory(@RequestParam(value = "articleMetaId") Long articleMetaCatId) {
		return categoryTagService.deleteCate(articleMetaCatId);
	}
	
	/**
	 * 删除tag
	 */
	@ResponseBody
	@PostMapping("/deleteTag")
	public ResponseResult deleteTag(@RequestParam(value = "articleMetaId") Long articleMetaId) {
		return categoryTagService.deleteTag(articleMetaId);
	}
}
