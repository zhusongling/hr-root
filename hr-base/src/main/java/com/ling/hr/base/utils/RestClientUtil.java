package com.ling.hr.base.utils;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST客户端工具类
 */
public class RestClientUtil {

    static RestTemplate restTemplate = new RestTemplate();

    private static String expand(String url, Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        List<String> uriVariables = new ArrayList<String>();
        for (String key : params.keySet()) {
            uriVariables.add(String.format("%s={%s}", key, key));
        }
        if (StringUtil.contains(url, "?")) {
            url += "&";
        } else {
            url += "?";
        }
        url += StringUtil.join(uriVariables, "&");

        return url;
    }

    public static String get(String url, Map<String, Object> params) {
        if (params != null) {
            url = expand(url, params);
            return restTemplate.getForEntity(url, String.class, params).getBody();
        } else {
            return restTemplate.getForEntity(url, String.class).getBody();
        }
    }

    public static <T> T get(String url, Class<T> responseType, Map<String, Object> params) {
        url = expand(url, params);
        return restTemplate.getForEntity(url, responseType, params).getBody();
    }

    public static String post(String url, Object request, Map<String, Object> params) {
        url = expand(url, params);
        return restTemplate.postForEntity(url, request, String.class, params).getBody();
    }

    public static <T> T post(String url, Object request, Class<T> responseType, Map<String, Object> params) {
        url = expand(url, params);
        return restTemplate.postForEntity(url, request, responseType, params).getBody();
    }

    public static void put(String url, Object request, Map<String, Object> params) {
        url = expand(url, params);
        restTemplate.put(url, request, params);
    }

    public static void delete(String url, Map<String, Object> params) {
        url = expand(url, params);
        restTemplate.delete(url, params);
    }
}
