package com.payn.ink.constant;

/**
 * @author: payn
 * @date: 2020/8/19 14:11
 */
public enum TypeEnum {

	TAG("tag"),
	CATEGORY("category"),
	ARTICLE("post"),
	PUBLISH("publish"),
	PAGE("page"),
	DRAFT("draft"),
	LINK("link"),
	IMAGE("image"),
	FILE("file"),
	CSRF_TOKEN("csrf_token"),
	COMMENTS_FREQUENCY("comments:frequency"),
	ARTICLE_HIT("article:hit"),
	NEXT("next"),
	PREV("prev"),

	/**
	 * 附件存放的URL，默认为网站地址，如集成第三方则为第三方CDN域名
	 */
	ATTACH_URL("attach_url"),

	/**
	 * 网站要过滤，禁止访问的ip列表
	 */
	BLOCK_IPS("site_block_ips");

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	TypeEnum(String type) {
		this.type = type;
	}

}
