package com.ling.hr.base.enums;

/**
 * 用户是否已完善个人信息枚举类
 */
public enum UserInfoFlag {
	NO("2"), YES("1");

	private String value;

	UserInfoFlag(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
