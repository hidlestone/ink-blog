package com.payn.ink.extension;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.domain.Article;
import com.payn.ink.domain.ArticleComment;
import com.payn.ink.service.ArticleCommentService;
import com.payn.ink.service.ArticleMetaService;
import com.payn.ink.service.ArticleService;
import com.payn.ink.util.ContentUtil;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

/**
 * thymeleaf 页面使用的主题公共函数
 *
 * @author: payn
 * @date: 2020/8/20 14:30
 */
@Component
public class Commons {

	private static final String[] ICONS = {"bg-ico-book", "bg-ico-game", "bg-ico-note", "bg-ico-chat", "bg-ico-code", "bg-ico-image", "bg-ico-web", "bg-ico-link", "bg-ico-design", "bg-ico-lock"};
	private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleMetaService articleMetaService;
	@Autowired
	private ArticleCommentService articleCommentService;

	/**
	 * 网站关键字
	 */
	public String siteKeywords() {
		return getSysSetting("site_keywords");
	}

	/**
	 * tab栏标题
	 */
	public String siteTitle() {
		return getSysSetting("site_title");
	}

	/**
	 * 网站描述
	 */
	public String siteDescription() {
		return getSysSetting("site_description");
	}

	/**
	 * 网站地址
	 */
	public String siteUrl() {
		return getSysSetting("site_url");
	}

	/**
	 * 文章缩略图url
	 */
	public String getThumbImgUrl(Long articleId) {
		String thumbImgUrl = articleService.getThumbImgById(articleId);
		if (!StringUtils.isEmpty(thumbImgUrl)) {
			return thumbImgUrl;
		}
		long rand = articleId % 20;
		return "/themes/img/rand/" + rand + ".jpg";
	}

	/**
	 * 获取文章分类图标
	 */
	public String getCategoryIcon(Long articleId) {
		String iconName = articleMetaService.getIconNameByArticleId(articleId);
		if (StringUtils.isNoneEmpty(iconName)) {
			return "bg-ico-" + iconName;
		}
		return ICONS[(int) (articleId % ICONS.length)];
	}

	/**
	 * 获取开头 introLen 个字符，作为文章介绍
	 */
	public static String getIntroContent(String content, int introLen) {
		int pos = content.indexOf("<!--more-->");
		if (pos != -1) {
			String html = content.substring(0, pos);
			return ContentUtil.htmlToText(ContentUtil.mdToHtml(html));
		} else {
			String text = ContentUtil.htmlToText(ContentUtil.mdToHtml(content));
			if (text.length() > introLen) {
				return text.substring(0, introLen);
			}
			return text;
		}
	}

	/**
	 * :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!
	 * 这种格式的字符转换为emoji表情
	 */
	public static String emojiStrToUnicode(String content) {
		return EmojiParser.parseToUnicode(content);
	}

	/**
	 * 显示标签
	 */
	public static String showTags(String tags) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(tags)) {
			String[] arr = tags.split(",");
			StringBuffer sbuf = new StringBuffer();
			for (String tagStr : arr) {
				String[] tagParamArr = tagStr.split("-");
				sbuf.append("<a href=\"/tag/" + tagParamArr[0] + "\">" + tagParamArr[1] + "</a>");
			}
			return sbuf.toString();
		}
		return "";
	}

	/**
	 * 文章完整链接
	 */
	public String articlrPermalink(Long articleId) {
		return getSysSetting("site_url") + "/article/" + articleId;
	}

	/**
	 * 当前文章的上一篇文章链接
	 */
	public String thePrevArticle(Long articleId) {
		String prevArticleId = articleService.getPrevArticleId(articleId);
		return articlrPermalink(Long.valueOf(prevArticleId));
	}

	/**
	 * 当前文章的下一篇文章链接
	 */
	public String theNextArticle(Long articleId) {
		String nextArticleId = articleService.getNextArticleId(articleId);
		return articlrPermalink(Long.valueOf(nextArticleId));
	}

	/**
	 * 获取系统配置参数
	 */
	private String getSysSetting(String key) {
		if (StringUtils.isEmpty(key)) {
			return "";
		}
		String config = InkConstants.INIT_CONFIG_MAP.get(key);
		if (StringUtils.isEmpty(config)) {
			return "";
		}
		return config;
	}

	/**
	 * 父级评论者名称
	 */
	public String commentAt(Long articleCommentId) {
		String commentatorName = articleCommentService.getCommentatorNameById(articleCommentId);
		if (StringUtils.isNoneEmpty(commentatorName)) {
			return "@" + commentatorName;
		}
		return "";
	}

	/**
	 * 最近的 pageSize 篇文章
	 */
	public List<Article> recentArticles(Integer pageSize) {
		return articleService.recentArticles(pageSize);
	}

	/**
	 * 最近的 pageSize 条评论
	 */
	public List<ArticleComment> recentComments(Integer pageSize) {
		return articleCommentService.recentComments(pageSize);
	}

	/**
	 * 社交账号等链接
	 */
	public String socialLink(String type) {
		String link = getSysSetting("social_" + type);
		if (StringUtils.isEmpty(link)) {
			return "javascript:void(0);";
		}
		switch (link) {
			case "github":
				return "https://github.com/" + link;
			case "weibo":
				return "http://weibo.com/" + link;
			case "cnblogs":
				return "https://www.cnblogs.com/" + link;
			case "zhihu":
				return "https://www.zhihu.com/people/" + link;
			default:
				return "javascript:void(0);";
		}
	}

	/**
	 * 标签随机颜色
	 */
	public static String randColor() {
		int r = rand(0, COLORS.length - 1);
		return COLORS[r];
	}

	private static final Random random = new Random();

	public static int rand(int min, int max) {
		return random.nextInt(max) % (max - min + 1) + min;
	}
}
