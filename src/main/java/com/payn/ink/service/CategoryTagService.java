package com.payn.ink.service;

import com.payn.ink.domain.ArticleMeta;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.vdo.ArticleMetaManageVdo;

import java.util.List;

public interface CategoryTagService {
	
	List<ArticleMetaManageVdo> getAllMetaByType(String category);

	ResponseResult addCategoryRecord(ArticleMeta articleMeta);

	ResponseResult deleteCate(Long id);

	ResponseResult updateCategory(ArticleMeta articleMeta);

	ResponseResult deleteTag(Long articleMetaId);

	ResponseResult addTagRecord(ArticleMeta articleMeta);

	ResponseResult updateTag(ArticleMeta articleMeta);
}
