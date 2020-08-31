package com.payn.ink.util;

import com.payn.ink.constant.InkConstants;
import com.payn.ink.extension.Commons;
import com.payn.ink.vo.vdo.ArticleVdo;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;
import org.apache.commons.lang3.StringUtils;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 不同格式类型文本转换
 *
 * @author: payn
 * @date: 2020/8/20 17:33
 */
public class ContentUtil {

	/**
	 * markdown转换为html文本
	 */
	public static String mdToHtml(String markdown) {
		if (StringUtils.isBlank(markdown)) {
			return "";
		}
		List<Extension> extensions = Arrays.asList(TablesExtension.create());
		Parser parser = Parser.builder().extensions(extensions).build();
		Node document = parser.parse(markdown);
		HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
		String content = renderer.render(document);
		content = Commons.emojiStrToUnicode(content);
		// 支持网易云音乐输出
		if (content.contains("app.support_163_music") && content.contains("[mp3:")) {
			content = content.replaceAll("\\[mp3:(\\d+)\\]", "<iframe frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" width=350 height=106 src=\"//music.163.com/outchain/player?type=2&id=$1&auto=0&height=88\"></iframe>");
		}
		// 支持gist代码输出
		if (content.contains("app.support_gist") && content.contains("https://gist.github.com/")) {
			content = content.replaceAll("&lt;script src=\"https://gist.github.com/(\\w+)/(\\w+)\\.js\">&lt;/script>", "<script src=\"https://gist.github.com/$1/$2\\.js\"></script>");
		}
		return content;
	}

	/**
	 * 提取html文本
	 */
	public static String htmlToText(String html) {
		if (StringUtils.isNotBlank(html)) {
			return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
		}
		return "";
	}

	/**
	 * 替换HTML脚本
	 */
	public static String cleanXSS(String value) {
		//You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}

	/**
	 * 将文章转换成RSS输出
	 */
	public static String getArticleRssXml(List<ArticleVdo> articles) throws FeedException {
		Commons commons = new Commons();
		Channel channel = new Channel("rss_2.0");
		channel.setTitle(InkConstants.INIT_CONFIG_MAP.get("site_title"));
		channel.setLink(commons.siteUrl());
		channel.setDescription(InkConstants.INIT_CONFIG_MAP.get("site_description"));
		channel.setLanguage("zh-CN");
		List<Item> items = new ArrayList<>();

		for (ArticleVdo article : articles) {
			Item item = new Item();
			item.setTitle(article.getTitle());
			Content content = new Content();
			String contentHtml = ContentUtil.mdToHtml(article.getContent());

			char[] xmlChar = contentHtml.toCharArray();
			for (int i = 0; i < xmlChar.length; ++i) {
				if (xmlChar[i] > 0xFFFD) {
					//直接替换掉0xb
					xmlChar[i] = ' ';
				} else if (xmlChar[i] < 0x20 && xmlChar[i] != 't' & xmlChar[i] != 'n' & xmlChar[i] != 'r') {
					//直接替换掉0xb
					xmlChar[i] = ' ';
				}
			}
			contentHtml = new String(xmlChar);
			content.setValue(contentHtml);
			item.setContent(content);
			item.setLink(commons.siteUrl() + "/article" + article.getArticleId());
			item.setPubDate(article.getGmtCreate());
			items.add(item);
		}
		channel.setItems(items);
		WireFeedOutput out = new WireFeedOutput();
		return out.outputString(channel);
	}
}
