package com.payn.ink.service.impl;

import com.payn.ink.constant.TypeEnum;
import com.payn.ink.domain.ArticleMeta;
import com.payn.ink.mapper.ArticleMapper;
import com.payn.ink.mapper.ArticleMetaMapper;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.CategoryTagService;
import com.payn.ink.vo.vdo.ArticleMetaManageVdo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: payn
 * @date: 2020/8/24 20:53
 */
@Service
public class CategoryTagServiceImpl implements CategoryTagService {

	@Autowired
	private ArticleMetaMapper articleMetaMapper;
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<ArticleMetaManageVdo> getAllMetaByType(String metaType) {
		if (metaType.equals("category")) {
			return articleMetaMapper.getAllMetaByType(metaType);
		} else {
			return articleMetaMapper.getTagCountInfo();
		}
	}

	@Override
	public ResponseResult addCategoryRecord(ArticleMeta articleMeta) {
		articleMeta.setMetaType(TypeEnum.CATEGORY.getType());
		int c1 = articleMetaMapper.countByMetaName(articleMeta);
		if (c1 > 0) {
			return ResponseResult.fail("当前分类的名称已经存在，不能添加！");
		}
		int c = articleMetaMapper.insertSelective(articleMeta);
		if (c > 0) {
			return ResponseResult.success("保存成功！");
		}
		return ResponseResult.fail("保存失败");
	}

	@Override
	public ResponseResult deleteCate(Long articleMetaCatId) {
		int c1 = articleMapper.countAticleInCategory(articleMetaCatId);
		if (c1 > 0) {
			return ResponseResult.fail("该分类下存在文章，不能删除！");
		}
		int c2 = articleMetaMapper.deleteByMetaId(articleMetaCatId);
		if (c2 > 0) {
			return ResponseResult.success("删除成功");
		}
		return ResponseResult.fail("删除失败");
	}

	@Override
	public ResponseResult updateCategory(ArticleMeta articleMeta) {
		int c = articleMetaMapper.updateByPrimaryKeySelective(articleMeta);
		return ResponseResult.success("保存成功");
	}

	@Override
	public ResponseResult deleteTag(Long articleMetaId) {
		long c1 = articleMetaMapper.countAticleInTag(articleMetaId);
		if (c1 > 0) {
			return ResponseResult.fail("该tag下存在文章，不能删除！");
		}
		int c2 = articleMetaMapper.deleteByMetaId(articleMetaId);
		if (c2 > 0) {
			return ResponseResult.success("删除成功！");
		}
		return ResponseResult.fail("删除失败！");
	}

	@Override
	public ResponseResult addTagRecord(ArticleMeta articleMeta) {
		articleMeta.setMetaType(TypeEnum.TAG.getType());
		int c1 = articleMetaMapper.countByMetaName(articleMeta);
		if (c1 > 0) {
			return ResponseResult.fail("当前标签的名称已经存在，不能添加！");
		}
		int c = articleMetaMapper.insertSelective(articleMeta);
		return ResponseResult.success("保存成功！");
	}

	@Override
	public ResponseResult updateTag(ArticleMeta articleMeta) {
		int c = articleMetaMapper.updateByPrimaryKeySelective(articleMeta);
		return ResponseResult.success("保存成功");
	}
}
