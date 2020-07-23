package com.ling.hr.base.enums;

public enum UserType {
	TEACHER("2"), STUDENT("1"), GUEST("0");

	private String value;

	UserType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
