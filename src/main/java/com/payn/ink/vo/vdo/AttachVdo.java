package com.payn.ink.vo.vdo;

/**
 * @author: payn
 * @date: 2020/8/24 19:24
 */
public class AttachVdo {

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
	private String gmtCreateStr;

	/**
	 * 更改时间
	 */
	private String gmtModifiedStr;

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

	public String getGmtCreateStr() {
		return gmtCreateStr;
	}

	public void setGmtCreateStr(String gmtCreateStr) {
		this.gmtCreateStr = gmtCreateStr;
	}

	public String getGmtModifiedStr() {
		return gmtModifiedStr;
	}

	public void setGmtModifiedStr(String gmtModifiedStr) {
		this.gmtModifiedStr = gmtModifiedStr;
	}
}
