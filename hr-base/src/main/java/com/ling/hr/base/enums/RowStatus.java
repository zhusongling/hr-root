package com.ling.hr.base.enums;

public enum RowStatus {
	INVALID(2), VALID(1);

	private Integer value;

	RowStatus(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
