package com.payn.ink.vo.outvo;

import com.github.pagehelper.PageInfo;
import com.payn.ink.vo.vdo.ArticleArchiveDateVdo;

/**
 * 文章按日期归档
 *
 * @author: payn
 * @date: 2020/8/19 11:01
 */
public class ArchiveDateArticleOutVo {

	private PageInfo<ArticleArchiveDateVdo> archiveDateVdoPageInfo;

	public PageInfo<ArticleArchiveDateVdo> getArchiveDateVdoPageInfo() {
		return archiveDateVdoPageInfo;
	}

	public void setArchiveDateVdoPageInfo(PageInfo<ArticleArchiveDateVdo> archiveDateVdoPageInfo) {
		this.archiveDateVdoPageInfo = archiveDateVdoPageInfo;
	}
}
