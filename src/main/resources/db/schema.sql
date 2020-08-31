
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attach
-- ----------------------------
DROP TABLE IF EXISTS `attach`;
CREATE TABLE `attach` (
  `attach_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '上传用户id',
  `fname` varchar(100) DEFAULT NULL COMMENT '附件文件名',
  `ftype` varchar(50) DEFAULT NULL COMMENT '附件类型如：image',
  `fkey` varchar(100) DEFAULT NULL COMMENT '附件key',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`attach_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件信息';

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment` (
  `article_comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章评论id',
  `article_id` bigint(20) DEFAULT '0' COMMENT '被评论的文章id',
  `author_id` bigint(20) DEFAULT NULL COMMENT '评论者id：0或者null-游客评论,其他-用户评论',
  `main_comment_id` bigint(20) DEFAULT '0' COMMENT '主评论id，如果此条记录为子评论',
  `commentator_name` varchar(200) DEFAULT NULL COMMENT '评论者昵称',
  `commentator_mail` varchar(200) DEFAULT NULL COMMENT '评论者邮箱',
  `commentator_ip` varchar(100) DEFAULT NULL COMMENT '评论者ip',
  `commentator_url` varchar(200) DEFAULT NULL COMMENT '评论者网址url',
  `content` text DEFAULT NULL COMMENT '评论内容',
  `type` varchar(50) DEFAULT NULL COMMENT '评论类型',
  `comment_status` CHAR(1) NOT NULL DEFAULT '0' COMMENT '主楼评论状态：0-未读，1-已读，2-已回复',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级评论id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`article_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章评论';

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客文章id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '作者id',
  `article_meta_cat_id` bigint(20) DEFAULT 0 COMMENT '文章所属分类，默认为：0-默认分类',
  `title` varchar(500) DEFAULT NULL COMMENT '文章标题',
  `slug` varchar(500) DEFAULT NULL COMMENT '文章链接',
  `thumb_img` varchar(500) DEFAULT NULL COMMENT '文章缩略图',
  `content` longtext COMMENT '文章内容',
  `article_type` varchar(50) DEFAULT NULL COMMENT '文章类型：page|post',
  `article_status` CHAR(1) NOT NULL DEFAULT '0' COMMENT '文章状态：0-未发布，1-已发布',
  `editor_type` varchar(50) DEFAULT 'markdown' COMMENT '使用的编辑器：默认markdown',
  `hit` bigint(20) DEFAULT '0' COMMENT '文章点击数',
  `comment_num` bigint(20) DEFAULT '0' COMMENT '文章评论数',
  `allow_comment` CHAR(1) DEFAULT 1 COMMENT '是否允许评论：0-不允许，1-允许',
  `allow_ping` CHAR(1) DEFAULT 1 COMMENT '是否允许PING：0-不允许，1-允许',
  `allow_feed` CHAR(1) DEFAULT 1 COMMENT '是否允许rss订阅：0-不允许，1-允许',
  `allow_thumb_img` CHAR(1) DEFAULT 1 COMMENT '是否允许前端展示缩略图(即首图)：0-不允许，1-允许',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='博客文章';

INSERT INTO `article` (`article_id`, `user_id`, `article_meta_cat_id`, `title`, `slug`, `thumb_img`, `content`, `article_type`, `article_status`, `editor_type`, `hit`, `comment_num`, `allow_comment`, `allow_ping`, `allow_feed`, `allow_thumb_img`, `gmt_create`, `gmt_modified`) VALUES ('1', '1', NULL, '关于', 'about', NULL, '### 目的\r\n  开通本网站目的很简单。\r\n\r\n主要是熟悉整个流程，以及linux的学习。\r\n\r\n另外就是方便整理一些笔记，属实是学习到了很多东西。\r\n\r\n在这条道路上走着，还是给自己留一点东西下来吧~\r\n\r\n:wink::wink::wink::wink:\r\n### 介绍\r\n主修Java\r\n\r\n90后，双子座，中度强迫症吧~~~\r\n\r\n音乐：纯音 电音 偏欧美 日系 粤语\r\n\r\n电影：偏科幻 搞笑\r\n\r\n目前经常出没地上海，老家在遥远的四川遂宁\r\n\r\n### 网站\r\n本网站主语言Java~~\r\n\r\n使用了Spring Boot开发，网页主题采用了漂亮的 Pinghsu\r\n\r\n数据库是比较常规的MySql\r\n', 'page', '1', 'markdown',  '399', '0', '1', '1', '1', '1', '2019-07-30 19:36:38', '2019-08-02 17:19:50');
INSERT INTO `article` (`article_id`, `user_id`, `article_meta_cat_id`, `title`, `slug`, `thumb_img`, `content`, `article_type`, `article_status`, `editor_type`, `hit`, `comment_num`, `allow_comment`, `allow_ping`, `allow_feed`, `allow_thumb_img`, `gmt_create`, `gmt_modified`) VALUES ('2', '1', '18', '网站使用技术及更新记录', NULL, NULL, '## 前言\r\n本项目修改自开源项目Tale，\r\n\r\n## 使用相关\r\n为了表示感谢原作者以及以后的学习这里放点链接\r\n\r\n- <p><a href=\'https://gitee.com/biezhi/tale\' target=\'_blank\'>Tale</a> 博客系统</p>\r\n- <p><a href=\'https://github.com/chakhsu/pinghsu\' target=\'_blank\'>Pinghsu</a> 主题</p>\r\n- <p><a href=\'https://github.com/lets-blade/blade\' target=\'_blank\'>Blade</a> 轻量级mvc框架</p>\r\n- <p><a href=\'https://github.com/vdurmont/emoji-java\' target=\'_blank\'>Emoji-Java</a> 轻量级的java库</p>\r\n- <p><a href=\'https://github.com/subchen/jetbrick-template-2x\' target=\'_blank\'>Jetbrick-Template</a> 模板引擎</p>\r\n\r\n## 网站更新\r\n\r\n- 添加了独立搜索页\r\n- 修改原系统数据库Sqllite为MySql\r\n## 变迁历史\r\n- 于2017年11月上线\r\n- 于2017年12月买了第一个域名www.yangxs.ink\r\n\r\n## 服务器\r\n- 阿里云 CenterOS\r\n- 域名也是阿里云的', 'post', '1', 'markdown', '377', '0', '1', '1', '1', '1', '2019-07-30 19:39:42', '2019-07-31 22:39:51');
INSERT INTO `article` (`article_id`, `user_id`, `article_meta_cat_id`, `title`, `slug`, `thumb_img`, `content`, `article_type`, `article_status`, `editor_type`, `hit`, `comment_num`, `allow_comment`, `allow_ping`, `allow_feed`, `allow_thumb_img`, `gmt_create`, `gmt_modified`) VALUES ('3', '1', NULL, '友情链接', 'links', NULL, '## 友情链接\r\n\r\n- :lock: <a href=\'http://www.begincode.net\' target=\'blank\'>Java技术论坛</a>\r\n\r\n## 链接须知\r\n> 请确定贵站可以稳定运营\r\n> 原创博客优先，技术类博客优先，设计、视觉类博客优先\r\n> 经常过来访问和评论，眼熟的\r\n\r\n备注：默认申请友情链接均为内页（当前页面）\r\n\r\n## 基本信息\r\n\r\n                网站名称：yangxs博客\r\n                网站地址：www.yangxs.ink\r\n请在当页通过评论来申请友链，其他地方不予回复\r\n\r\n暂时先这样，同时欢迎互换友链，这个页面留言即可。 ^_^\r\n\r\n还有，我会不定时对无法访问的网址进行清理，请保证自己的链接长期有效。', 'page', '1', 'markdown',  '416', '0', '1', '1', NULL, '1', '2019-07-30 19:39:42', '2019-07-30 19:39:42');
INSERT INTO `article` (`article_id`, `user_id`, `article_meta_cat_id`, `title`, `slug`, `thumb_img`, `content`, `article_type`, `article_status`, `editor_type`, `hit`, `comment_num`, `allow_comment`, `allow_ping`, `allow_feed`, `allow_thumb_img`, `gmt_create`, `gmt_modified`) VALUES ('5', '1', '4', '最近听的歌曲', NULL, NULL, '### 分享一些歌曲：\r\n---\r\n<iframe frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" width=330 height=86 src=\"//music.163.com/outchain/player?type=2&id=509313150&auto=0&height=66\"></iframe>\r\n\r\n<iframe frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" width=330 height=86 src=\"//music.163.com/outchain/player?type=2&id=436514415&auto=0&height=66\"></iframe>\r\n\r\n<iframe frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" width=330 height=86 src=\"//music.163.com/outchain/player?type=2&id=515143305&auto=0&height=66\"></iframe>', 'post', '1', 'markdown',  '309', '0', '1', '1', '1', '1', '2019-07-30 19:39:42', '2019-07-30 20:16:52');


-- ----------------------------
-- Table structure for article_meta
-- ----------------------------
DROP TABLE IF EXISTS `article_meta`;
CREATE TABLE `article_meta` (
  `article_meta_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客文章tag|分类id',
  `meta_name` varchar(200) DEFAULT NULL COMMENT '文章tag|分类名称',
  `slug` varchar(200) DEFAULT NULL COMMENT '导航处slug名称',
  `meta_type` varchar(32) DEFAULT NULL COMMENT '类型：tag|category',
  `description` varchar(255) DEFAULT NULL COMMENT '分类或标签的描述',
  `sort` int(11) DEFAULT '0' COMMENT '排序字段',
  `parent` int(11) DEFAULT '0' COMMENT '父级分类id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`article_meta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='博客文章元信息(分类|标签)';

-- ----------------------------
-- Records of article_meta
-- ----------------------------
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('日志', 'note', 'category', 'note', '0', '0', NULL, NULL);
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('技术/代码', 'code', 'category', 'code', '0', '0', NULL, NULL);
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('web开发', 'web', 'category', 'web', '0', '0', NULL, NULL);
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('设计', 'design', 'category', 'design', '0', '0', NULL, NULL);
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('游戏', 'game', 'category', 'game', '0', '0', NULL, NULL);
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('日常', 'link', 'category', 'link', '0', '0', NULL, NULL);
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('对话', 'chat', 'category', 'chat', '0', '0', NULL, NULL);
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('记录', 'image', 'category', 'image', '0', '0', NULL, NULL);
INSERT INTO `article_meta` ( `meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('秘密', 'lock', 'category', 'lock', '0', '0', NULL, NULL);

INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('音乐', '音乐', 'tag', NULL, '0', '0', '2020-08-19 09:12:58', '2020-08-19 09:12:58');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('博客', '博客', 'tag', NULL, '0', '0', '2020-08-19 09:12:58', '2020-08-19 09:12:58');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('日志', '日志', 'tag', NULL, '0', '0', '2020-08-19 09:12:58', '2020-08-19 09:12:58');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('游戏', 'game', 'tag', 'game', '0', '0', NULL, '2020-08-21 15:30:11');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('日志', 'note', 'tag', 'note', '0', '0', NULL, '2020-08-21 15:30:13');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('对话', 'chat', 'tag', 'chat', '0', '0', NULL, '2020-08-21 15:30:13');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('技术/代码', 'code', 'tag', 'code', '0', '0', NULL, '2020-08-21 15:30:14');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('趣图', 'image', 'tag', 'image', '0', '0', NULL, '2020-08-21 15:30:15');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('web开发', 'web', 'tag', 'web', '0', '0', NULL, '2020-08-21 15:30:16');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('链接', 'link', 'tag', 'link', '0', '0', NULL, '2020-08-21 15:30:17');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('设计', 'design', 'tag', 'design', '0', '0', NULL, '2020-08-21 15:30:18');
INSERT INTO `article_meta` (`meta_name`, `slug`, `meta_type`, `description`, `sort`, `parent`, `gmt_create`, `gmt_modified`) VALUES ('秘密', 'lock', 'tag', 'lock', '0', '0', NULL, '2020-08-21 15:30:19');

-- ----------------------------
-- Table structure for system_setting
-- ----------------------------
DROP TABLE IF EXISTS `system_setting`;
CREATE TABLE `system_setting` (
  `system_key` varchar(100) NOT NULL,
  `system_value` varchar(255),
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`system_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统设置';

-- ----------------------------
-- Records of system_setting
-- ----------------------------
INSERT INTO `system_setting` VALUES ('allow_install', '1', '是否允许重新安装博客');
INSERT INTO `system_setting` VALUES ('site_description', '', null);
INSERT INTO `system_setting` VALUES ('site_keywords', '', null);
INSERT INTO `system_setting` VALUES ('site_theme', 'default', null);
INSERT INTO `system_setting` VALUES ('site_title', '', null);
INSERT INTO `system_setting` VALUES ('site_url', '', null);
INSERT INTO `system_setting` VALUES ('social_github', '', null);
INSERT INTO `system_setting` VALUES ('social_cnblogs', '-zhuang', null);
INSERT INTO `system_setting` VALUES ('social_weibo', '', null);
INSERT INTO `system_setting` VALUES ('social_zhihu', '', null);
INSERT INTO `system_setting` VALUES ('admin_mail', '', '管理员邮箱');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `home_url` varchar(255) DEFAULT NULL COMMENT '主页地址url',
  `screen_name` varchar(100) DEFAULT NULL,
  `is_active` CHAR(1) DEFAULT 1 COMMENT '是否激活：0-未激活，1-已激活',
  `logged` CHAR(1) DEFAULT 0,
  `group_name` varchar(16) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `AK_UNQ_BL_USER_USERNAME` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';


DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `article_tag_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章标签id',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `article_meta_tag_id` varchar(100) DEFAULT NULL COMMENT '文章tag id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`article_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章tag对照';

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '问题id',
  `questioner_name` varchar(200) DEFAULT NULL COMMENT '提问者昵称',
  `questioner_mail` varchar(200) DEFAULT NULL COMMENT '提问者邮箱',
  `questioner_ip` varchar(100) DEFAULT NULL COMMENT '提问者ip',
  `questioner_url` varchar(200) DEFAULT NULL COMMENT '提问者网址url',
  `content` text DEFAULT NULL COMMENT '提问内容',
  `question_status` char(1) DEFAULT NULL COMMENT '状态：0-删除，1-未删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提问表';

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `answer_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '回答id',
  `question_id` bigint(20) NOT NULL COMMENT '问题id',
  `answer_name` varchar(200) DEFAULT NULL COMMENT '回答者昵称',
  `answer_mail` varchar(200) DEFAULT NULL COMMENT '回答者邮箱',
  `answer_ip` varchar(100) DEFAULT NULL COMMENT '回答者ip',
  `answer_url` varchar(200) DEFAULT NULL COMMENT '回答者网址url',
  `content` text DEFAULT NULL COMMENT '回答内容',
  `answer_status` char(1) DEFAULT NULL COMMENT '状态：0-删除，1-未删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回答表';
