package com.ling.hr.base.utils;

import com.ling.hr.base.model.Result;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求上下文工具类
 */
public class RequestContextUtil {

    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = getServletRequestAttributes();
        return attributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = getServletRequestAttributes();
        return attributes.getResponse();
    }

    public static void write(Result result) {
        HttpServletResponse response = RequestContextUtil.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(FastJsonUtil.toJSONString(result));
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void write(String result) {
        HttpServletResponse response = RequestContextUtil.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(result);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void write(byte[] bytes) {
        HttpServletResponse response = RequestContextUtil.getResponse();
        response.setCharacterEncoding("UTF-8");
        // response.setContentType("image/jpeg");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write(InputStream inputStream) {
        HttpServletResponse response = RequestContextUtil.getResponse();
        response.setCharacterEncoding("UTF-8");
        // response.setContentType("image/jpeg");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取cookie值
     *
     * @return
     */
    public static Map<String, String> getCookies() {
        HttpServletRequest request = RequestContextUtil.getRequest();
        Map<String, String> data = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                data.put(name, value);
            }
        }
        return data;
    }

    /**
     * 获取用户授权码
     *
     * @return
     */
    public static String getAuthToken() {
        HttpServletRequest request = RequestContextUtil.getRequest();
        String authToken = request.getHeader(ContextUtil.AUTH_TOKEN);
        if (StringUtil.isBlank(authToken)) {
            authToken = request.getParameter(ContextUtil.AUTH_TOKEN);
        }
        if (StringUtil.isBlank(authToken)) {
            Map<String, String> cookies = RequestContextUtil.getCookies();
            authToken = cookies.get(ContextUtil.AUTH_TOKEN);
        }
        return authToken;
    }

}
