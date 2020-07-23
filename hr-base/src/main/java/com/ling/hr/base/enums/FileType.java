package com.ling.hr.base.enums;

/**
 * 文件上传的用途
 */
public enum FileType {
	COllEGE("1"), ADVERTISE("2");
	private String value;

	FileType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
