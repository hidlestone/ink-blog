package com.payn.ink.domain;

import java.io.Serializable;

public class SystemSetting implements Serializable {
	
	private String systemKey;

	private String systemValue;

	private String description;

	private static final long serialVersionUID = 1L;

	public SystemSetting() {
	}

	public SystemSetting(String systemKey, String systemValue) {
		this.systemKey = systemKey;
		this.systemValue = systemValue;
	}

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public String getSystemValue() {
		return systemValue;
	}

	public void setSystemValue(String systemValue) {
		this.systemValue = systemValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}