package com.ling.hr.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "message-push")
public class MessagePushProperties {
	@Data
	public static class GeTui {
		private String appId;
		private String appSecret;
		private String appKey;
		private String masterSecret;
		private String host;
	}

	private GeTui geTui;
}
