package com.ling.hr.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "product")
public class ProductProperties {

	// 专家一对一
	@Data
	public static class Expert {
		private String productId; // 商品ID
		private String productName; // 产品名称
		private String totalAmount; // 商品价格
		private String payAmount; // 商品优惠价格
		private String productCode; // 商品编号
	}

	// 志愿检测
	@Data
	public static class Detect {
		private String productId; // 商品ID
		private String productName; // 产品名称
		private String totalAmount; // 商品价格
		private String payAmount; // 商品优惠价格
		private String productCode; // 商品编号
	}

	// 志愿检测升级一对一
	@Data
	public static class Upgrade {
		private String productId; // 商品ID
		private String productName; // 产品名称
		private String totalAmount; // 商品价格
		private String payAmount; // 商品优惠价格
		private String productCode; // 商品编号
	}
	
	private Expert expert;
	private Detect detect;
	private Upgrade upgrade;
}
