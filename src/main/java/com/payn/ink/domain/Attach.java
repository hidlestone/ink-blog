package com.payn.ink.domain;

import java.io.Serializable;
import java.util.Date;

public class Attach implements Serializable {
	/**
	 * 附件id
	 */
	private Long attachId;

	/**
	 * 上传用户id
	 */
	private Long userId;

	/**
	 * 附件文件名
	 */
	private String fname;

	/**
	 * 附件类型如：image
	 */
	private String ftype;

	/**
	 * 附件key
	 */
	private String fkey;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 更改时间
	 */
	private Date gmtModified;

	private static final long serialVersionUID = 1L;

	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getFkey() {
		return fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}