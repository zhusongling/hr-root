package com.ling.hr.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "careerquiz")
public class CareerQuizProperties {

	private String baseAddress;
	private String prefix;
	private String port;
}
