package com.ling.hr.base.enums;

/**
 * 三方支付方式
 */
public enum PayType {
	AliPay(1), WeChat(2), Iap(3), H5AliPay(4), XCXPay(5);

	private Integer value;

	PayType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
