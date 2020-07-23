package com.ling.hr.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ios")
public class IosProperties {
	private String sandBox;
	private String prod;
}
