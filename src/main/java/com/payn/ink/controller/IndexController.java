package com.payn.ink.controller;

import com.github.pagehelper.PageInfo;
import com.payn.ink.constant.ErrorCode;
import com.payn.ink.constant.InkConstants;
import com.payn.ink.constant.TypeEnum;
import com.payn.ink.controller.base.ControllerUtil;
import com.payn.ink.domain.ArticleComment;
import com.payn.ink.domain.User;
import com.payn.ink.extension.Commons;
import com.payn.ink.response.ResponseResult;
import com.payn.ink.service.ArticleCommentService;
import com.payn.ink.service.ArticleMetaService;
import com.payn.ink.service.ArticleService;
import com.payn.ink.service.UserService;
import com.payn.ink.service.common.MailService;
import com.payn.ink.util.ContentUtil;
import com.payn.ink.util.CookieUtil;
import com.payn.ink.util.EHCacheUtil;
import com.payn.ink.util.IPUtil;
import com.payn.ink.util.RegExpValidatorUtil;
import com.payn.ink.vo.outvo.ArchiveDateArticleOutVo;
import com.payn.ink.vo.outvo.ArticleMetaOutVo;
import com.payn.ink.vo.vdo.ArticleCommentVdo;
import com.payn.ink.vo.vdo.ArticleMetaVdo;
import com.payn.ink.vo.vdo.ArticleVdo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 首页
 *
 * @author: payn
 * @date: 2020/8/18 21:41
 */
@Controller
public class IndexController {

	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	private static long lastExecuteTime = 0;//上次更新时间　
	private static long EXECUTE_SEP = 12 * 60 * 60 * 1000;//定义更新间隔时间，单位毫秒，12小时

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleCommentService articleCommentService;
	@Autowired
	private ArticleMetaService articleMetaService;
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;

	/**
	 * INKBLOG首页及首页数据
	 */
	@RequestMapping("/")
	public String indexPage(HttpServletRequest request) {
		return pageIndex(1, 6, request);
	}

	/**
	 * 文章分页数据
	 */
	@RequestMapping("/page/{pageNum}")
	public String pageIndex(@PathVariable(value = "pageNum") Integer pageNum,
							@RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize, HttpServletRequest request) {
		pageNum = pageNum < 0 || pageNum > InkConstants.MAX_PAGE ? 1 : pageNum;
		PageInfo<ArticleVdo> articlePagenfo = articleService.getPostArticlePage(pageNum, pageSize);
		List<ArticleVdo> articleList = articlePagenfo.getList();
		for (ArticleVdo article : articleList) {
			String introContent = Commons.getIntroContent(article.getContent(), 75);
			article.setContent(introContent);
		}
		request.setAttribute("articles", articlePagenfo);
		return ControllerUtil.render("index");
	}

	/**
	 * 获取文章明细
	 */
	@RequestMapping("/article/{articleId}")
	public String getArticleDetail(@PathVariable(value = "articleId") Long articleId, HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
		ArticleVdo articleVdo = articleService.getArticleDetailById(articleId);
		//文章评论
		String cp = request.getParameter("cp");
		if (StringUtils.isEmpty(cp)) {
			cp = "1";
		}
		PageInfo<ArticleCommentVdo> articleCommentPageInfo = getArticleCommentList(articleVdo, Integer.valueOf(cp));
		request.setAttribute("comments", articleCommentPageInfo);
		request.setAttribute("is_post", true);
		request.setAttribute("title", articleVdo.getTitle());
		//将markdown内容转化为html
		articleVdo.setContent(ContentUtil.mdToHtml(articleVdo.getContent()));
		request.setAttribute("article", articleVdo);
		//更新文章的点击量
		Long nowHit = updateArticleHit(articleId, articleVdo.getHit());
		articleVdo.setHit(nowHit);
		return ControllerUtil.render("article");
	}

	/**
	 * 更新缓存和数据库中文章的点击量
	 */
	private Long updateArticleHit(Long articleId, Long preHit) {
		//从缓存中获取文章点击量
		Long hitCount = (Long) EHCacheUtil.get(TypeEnum.ARTICLE_HIT.getType(), articleId);
		if (null == hitCount) {
			hitCount = preHit;
		}
		hitCount++;
		long now = System.currentTimeMillis(); //获取当前系统时间
		if ((now - lastExecuteTime) > EXECUTE_SEP) {
			lastExecuteTime = now;
			int c = articleService.updateCachedHitByArticleId(articleId, hitCount);
		}
		EHCacheUtil.put(TypeEnum.ARTICLE_HIT.getType(), articleId, hitCount);
		return hitCount;
	}

	/**
	 * 获取文章评论列表
	 */
	private PageInfo<ArticleCommentVdo> getArticleCommentList(ArticleVdo articleVdo, Integer commentPageNum) {
		//文章评论信息
		if ("1".equals(articleVdo.getAllowComment())) {
			return articleCommentService.getArticleCommentWithPage(articleVdo.getArticleId(), commentPageNum, 10);
		}
		return null;
	}

	/**
	 * 造福页面
	 */
	@RequestMapping("/friend")
	public String friendDonate(HttpServletRequest request) {
		request.setAttribute("title", "friend");
		return ControllerUtil.render("friend");
	}

	/**
	 * poor页面
	 */
	@RequestMapping("/poor")
	public String poor(HttpServletRequest request) {
		request.setAttribute("title", "poor");
		return ControllerUtil.render("poor");
	}

	@RequestMapping("/reallove")
	public String reallove(HttpServletRequest request) {
		request.setAttribute("title", "reallove");
		return ControllerUtil.render("reallove");
	}

	/**
	 * 文章归档，按照日期
	 * 一页显示 6 个月份
	 */
	@RequestMapping("/archives/{pageNum}")
	public String archiveByDate(@PathVariable(value = "pageNum") int pageNum,
								@RequestParam(value = "pageSize", defaultValue = "6") int pageSize, HttpServletRequest request) {
		ArchiveDateArticleOutVo outVo = articleService.archiveByDate(pageNum, pageSize);
		request.setAttribute("archives", outVo.getArchiveDateVdoPageInfo());
		request.setAttribute("title", "文章归档");
		return ControllerUtil.render("archives");
	}

	/**
	 * 跳转自定义SLUG页面
	 */
	@RequestMapping("/pages/{slug}")
	public String jumpCustomPage(@PathVariable(value = "slug") String slug, HttpServletRequest request) {
		ArticleVdo articleVdo = articleService.getArticleBySlug(slug);
		if (null == articleVdo) {
			ControllerUtil.render404();
		}
		//获取slug页面评论
		String cp = request.getParameter("cp");
		if (StringUtils.isEmpty(cp)) {
			cp = "1";
		}
		PageInfo<ArticleCommentVdo> articleCommentVdoPageInfo = getArticleCommentList(articleVdo, Integer.valueOf(cp));
		request.setAttribute("is_post", false);
		request.setAttribute("title", articleVdo.getTitle());
		//将markdown内容转化为html
		articleVdo.setContent(ContentUtil.mdToHtml(articleVdo.getContent()));
		request.setAttribute("article", articleVdo);
		request.setAttribute("comments", articleCommentVdoPageInfo);
		//更新文章的点击量
		Long nowHit = updateArticleHit(articleVdo.getArticleId(), articleVdo.getHit());
		articleVdo.setHit(nowHit);
		return ControllerUtil.render("page");
	}

	/**
	 * 搜索页面，展示tah及tag下文章数量
	 */
	@RequestMapping("/search")
	public String search(HttpServletRequest request) {
		ArticleMetaOutVo outVo = new ArticleMetaOutVo();
		//标签名称及标签下文章数量
		List<ArticleMetaVdo> articleMetaVdoList = articleMetaService.getAllArticleMeta();
		outVo.setArticleMetaVdoList(articleMetaVdoList);
		request.setAttribute("title", "搜索");
		request.setAttribute("metas", articleMetaVdoList);
		return ControllerUtil.render("search");
	}

	/**
	 * 根据tag进行搜素
	 */
	@RequestMapping("/tag/{articleMetaId}")
	public String searchByTag(@PathVariable(value = "articleMetaId") Long articleMetaId, HttpServletRequest request) {
		List<ArticleVdo> articleList = articleService.getArticleByTagId(articleMetaId);
		String tagName = articleMetaService.getMetaNameByPrimaryKey(articleMetaId);
		request.setAttribute("type", "标签");
		request.setAttribute("key", tagName);
		request.setAttribute("articles", articleList);
		request.setAttribute("title", tagName);
		return ControllerUtil.render("result");
	}

	/**
	 * 根据分类的名称进行搜素
	 */
	@RequestMapping("/category/{articleMetaId}")
	public String searchByCategory(@PathVariable(value = "articleMetaId") Long articleMetaId, HttpServletRequest request) {
		List<ArticleVdo> articleList = articleService.getArticlearticleMetaId(articleMetaId);
		String categoryName = articleMetaService.getMetaNameByPrimaryKey(articleMetaId);
		request.setAttribute("type", "分类");
		request.setAttribute("key", categoryName);
		request.setAttribute("articles", articleList);
		request.setAttribute("title", categoryName);
		return ControllerUtil.render("result");
	}

	/**
	 * 根据关键字搜素
	 */
	@RequestMapping("/search/{keyword}")
	public String searchByKeyword(@PathVariable(value = "keyword") String keyword, HttpServletRequest request) {
		List<ArticleVdo> articleList = articleService.getArticleByKeyword(keyword);
		request.setAttribute("type", "关键字");
		request.setAttribute("key", keyword);
		request.setAttribute("articles", articleList);
		request.setAttribute("title", keyword);
		return ControllerUtil.render("result");
	}

	/**
	 * 游客评论
	 */
	@ResponseBody
	@PostMapping("/comment")
	public ResponseResult visitorComment(ArticleComment articleComment, HttpServletRequest request, HttpServletResponse response) throws MessagingException, javax.mail.MessagingException {
		//ip地址+评论的文章id
		String key = IPUtil.getIpAddrByRequest(request) + ":article:" + articleComment.getArticleId();
		String value = (String) EHCacheUtil.get(TypeEnum.COMMENTS_FREQUENCY.getType(), key);
		if (StringUtils.isNotEmpty(value)) {
			return ResponseResult.fail("您发表评论太快了(30s)，请稍后再试");
		}
		if (articleComment == null) {
			return ResponseResult.fail("请输入完整后评论");
		}
		String ref = request.getHeader("Referer");
		if (StringUtils.isBlank(ref)) {
			return ResponseResult.fail(ErrorCode.BAD_REQUEST);
		}
		//获取回复的评论的id
		if (articleComment.getArticleCommentId() != null) {
			articleComment.setParentId(articleComment.getArticleCommentId());
		}
		if (articleComment.getAuthorId() == null) {
			articleComment.setAuthorId(0L);//0|null 游客评论
		}
		if (StringUtils.isBlank(articleComment.getCommentatorName())) {
			return ResponseResult.fail("姓名不能为空");
		}
		if (articleComment.getCommentatorName().length() > 12) {
			return ResponseResult.fail("姓名不能超过12个字符");
		}
		if (StringUtils.isNotBlank(articleComment.getCommentatorMail()) && !RegExpValidatorUtil.isEmail(articleComment.getCommentatorMail())) {
			return ResponseResult.fail("请输入正确的邮箱格式");
		}
		if (StringUtils.isNotBlank(articleComment.getCommentatorUrl()) && !RegExpValidatorUtil.isURL(articleComment.getCommentatorUrl())) {
			return ResponseResult.fail("请输入正确的URL格式");
		}
		if (articleComment.getContent() == null || articleComment.getContent().length() > 200) {
			return ResponseResult.fail("请输入200个字符以内的评论");
		}

		String author = ContentUtil.cleanXSS(articleComment.getCommentatorName());
		User adminUser = userService.findAdminUser();
		if (author.toLowerCase().contains(adminUser.getUsername().toLowerCase())) {
			return ResponseResult.fail("当前使用的昵称已存在！");
		}
		String text = ContentUtil.cleanXSS(articleComment.getContent());
		author = EmojiParser.parseToAliases(author);
		text = EmojiParser.parseToAliases(text);
		articleComment.setCommentStatus("1");//删除状态
		articleComment.setArticleCommentId(null);//主键置null，自增
		articleComment.setCommentatorName(author);//评论者名称
		articleComment.setContent(text);//评论内容
		articleComment.setCommentatorIp(request.getRemoteAddr());//请求的ip地址

		try {
			int count2 = articleCommentService.saveComment(articleComment);
			//将游客输入的信息保存到浏览器的cookie中
			CookieUtil.saveCookie(InkConstants.REMEMBER_AUTHOR_INK, URLEncoder.encode(author, "UTF-8"), 7 * 24 * 60 * 60, response);
			if (StringUtils.isNotBlank(articleComment.getCommentatorMail())) {
				CookieUtil.saveCookie(InkConstants.REMEMBER_MAIL_INK, URLEncoder.encode(articleComment.getCommentatorMail(), "UTF-8"), 7 * 24 * 60 * 60, response);
			}
			if (StringUtils.isNotBlank(articleComment.getCommentatorUrl())) {
				CookieUtil.saveCookie(InkConstants.REMEMBER_URL_INK, URLEncoder.encode(articleComment.getCommentatorUrl(), "UTF-8"), 7 * 24 * 60 * 60, response);
			}
			EHCacheUtil.put(TypeEnum.COMMENTS_FREQUENCY.getType(), key, "1");
			if (count2 <= 0) {
				return ResponseResult.fail("发布评论失败！");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return ResponseResult.fail("发布评论失败！");
		}
		//文章更新评论数量
		int c = articleService.updateCommentNum(articleComment.getArticleId());

		//邮件通知
		String parentMail = articleCommentService.getCommentatorMainById(articleComment.getParentId());
		if (StringUtils.isNotEmpty(articleComment.getCommentatorMail()) && StringUtils.isNotEmpty(parentMail)) {
			String siteUrl = InkConstants.INIT_CONFIG_MAP.get("site_url");
			Long articleId = articleComment.getArticleId();///article/2#comment-8
			Long commentId = articleComment.getArticleCommentId();
			String content = "<a href='http://" + siteUrl + "/article/" + articleId + "comment-" + commentId + "'>【INK|BLOG】点击评论详情</a>";
			content += "<p>" +">>> "+ articleComment.getContent() + "</p>";
			mailService.sendHtmlMail(parentMail, "【INK|BLOG】评论回复", content);
		}
		return ResponseResult.success("发布评论成功！");
	}

	/**
	 * 获取RSS输出
	 */
	@RequestMapping("/feed")
	public void feed(HttpServletResponse response) {
		List<ArticleVdo> blogArticles = articleService.getAllowRssArticle();
		try {
			String xml = ContentUtil.getArticleRssXml(blogArticles);
			response.setContentType("text/xml; charset=utf-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.write(xml);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("====== feed article fail ======");
		}
	}

}
