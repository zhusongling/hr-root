package com.ling.hr.base.utils;

import java.util.HashMap;
import java.util.Map;

public class I18NUtil {

    private static final Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();

    public static void put(String lang, String key, String value) {
        cache.putIfAbsent(key, new HashMap<String, String>());
        cache.get(key).put(lang, value);
    }

    public static String get(String key, String lang) {
        String value = "";
        if (cache.containsKey(key)) {
            value = cache.get(key).get(lang);
        }
        return value;
    }
}
