package com.ling.hr.base.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "task-executor", ignoreInvalidFields = true)
public class TaskExecutorProperties {
	private Map<String, Integer> maxThreadCount = new HashMap<String, Integer>();
}
