package com.ling.hr.utils;

import java.util.UUID;

/**
 * uuid生成
 */
public class UUIDUtils {

    public static String obtainUUID32() {
        return UUID.randomUUID().toString();
    }

    public static String obtainUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
