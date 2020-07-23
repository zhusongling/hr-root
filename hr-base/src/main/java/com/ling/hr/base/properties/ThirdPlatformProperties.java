package com.ling.hr.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "thirdplatform")
public class ThirdPlatformProperties {

	@Data
	public static class Qq {
		private String validateUrl;
		private String appId;
		private String appKey;
	}

	@Data
	public static class WeChat {
		private String appId;
		private String appKey;
		private String validateUrl;
		private String userInfoUrl;
		private String refreshTokenUrl;
	}

	@Data
	public static class SinaWeiBo {
		private String validateUrl;
		private String userInfoUrl;
		private String appId;
		private String appKey;
	}

	@Data
	public static class Mob {
		private String validateUrl;
		private String appId;
		private String appKey;
	}

	@Data
	public static class Yxt {
		private String appId;
		private String appKey;
		private String validateUrl;
		private String userInfoUrl;
		private String refreshTokenUrl;
	}

	@Data
	public static class WeChatSmallProgram {
		private String appId;
		private String appSecret;
		private String grantType;
		private String accreditUrl;
	}

	private Qq qq;
	private WeChat weChat;
	private SinaWeiBo sinaWeiBo;
	private Mob mob;
	private Yxt yxt;
	private WeChatSmallProgram weChatSmallProgram;
}
