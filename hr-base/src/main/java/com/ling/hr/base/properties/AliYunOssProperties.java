package com.ling.hr.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss", ignoreInvalidFields = true)
public class AliYunOssProperties {
    private String bucketName;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    private String baseAddress;
    private String product; // 商品文件夹
    private String teacher;
    private String videos;

    private Map<String, String> modules = new HashMap<String, String>();

    private String userProfile;
    private String collegeBadge;
    private String collegePicture;
    private String articleContent;
    private String articlePicture;
    private String ueditor;
}
