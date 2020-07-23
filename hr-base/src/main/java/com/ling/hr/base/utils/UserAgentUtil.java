package com.ling.hr.base.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentUtil {

    // \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
    // 字符串在编译时会被转码一次,所以是 "\\b"
    // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)

    /**
     * 检测是否是移动设备访问
     *
     * @param userAgent 浏览器标识
     * @return true:移动设备接入，false:pc端接入
     */
    public static boolean isMobileDevice(String userAgent) {
        if (userAgent == null) {
            userAgent = "";
        }
        // 移动设备正则匹配：手机端、平板
        String phoneRegex = "\\b(ip(hone|od)|android|okhttp|opera m(ob|in)i|windows (phone|ce)|blackberry|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp|laystation portable)|nokia|fennec|htc[-_]|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
        Pattern phonePattern = Pattern.compile(phoneRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcherPhone = phonePattern.matcher(userAgent);
        boolean isMobileDevice = matcherPhone.find();
        if (!isMobileDevice) {
            String tableRegex = "\\b(ipad|tablet|(Nexus 7)|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
            Pattern tablePattern = Pattern.compile(tableRegex, Pattern.CASE_INSENSITIVE);
            Matcher matcherTable = tablePattern.matcher(userAgent);
            isMobileDevice = matcherTable.find();
        }

        return isMobileDevice;
    }

    /**
     * 检测是否是移动设备访问
     *
     * @param request
     * @return true:移动设备接入，false:pc端接入
     */
    public static boolean isMobileDevice(HttpServletRequest request) {
        // 获取ua，用来判断是否为移动端访问
        String userAgent = request.getParameter("userAgent");
        if (StringUtil.isBlank(userAgent)) {
            userAgent = request.getHeader("User-Agent");
        }
        if (StringUtil.isBlank(userAgent)) {
            userAgent = request.getHeader("USER-AGENT");
        }
        return isMobileDevice(userAgent);
    }
}
