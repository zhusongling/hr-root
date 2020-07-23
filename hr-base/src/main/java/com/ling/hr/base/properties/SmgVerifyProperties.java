package com.ling.hr.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "smg")
public class SmgVerifyProperties {
	private String appKey;
	private String zone;
	private String verifyUrl;
}
