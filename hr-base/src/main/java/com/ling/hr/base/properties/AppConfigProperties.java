package com.ling.hr.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "appconfig")
public class AppConfigProperties {

	private String instId;
	private String address;
	private String getFileInfo;
	private String topicDetailUrl;
	private String articleDetailUrl;
}
