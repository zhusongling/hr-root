package com.ling.hr.base.utils;

import org.apache.commons.lang.math.RandomUtils;

import java.util.UUID;

public class RandomUtil extends RandomUtils {
    private static char[] charArr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static final String next(int length) {
        String data = "";
        if (length > 0) {
            char[] buffer = new char[length];
            for (int i = 0; i < length; i++) {
                buffer[i] = charArr[nextInt(62)];
            }
            data = new String(buffer);
        }
        return data;
    }

    /**
     * 获取uuid
     *
     * @return UUID
     */
    public static final String uuid() {
        return UUID.randomUUID().toString();
    }

    public static final String uuid32() {
        return uuid().replace("-", "");
    }

    /**
     * 获取指定个数的UUID
     *
     * @param number UUID个数
     * @return UUID
     */
    public static final String[] uuid(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = uuid();
        }
        return ss;
    }
}
