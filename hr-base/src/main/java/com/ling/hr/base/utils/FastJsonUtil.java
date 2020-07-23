package com.ling.hr.base.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON工具类
 */
public class FastJsonUtil {

	public static String toJSONString(Object param) {
		return JSON.toJSONString(param);
	}

	public static <T> T toJavaObject(String param, Class<T> clazz) {
		return toJavaObject(parseObject(param), clazz);
	}

	public static <T> T toJavaObject(Object param, Class<T> clazz) {
		return toJavaObject(parseObject(param), clazz);
	}

	public static <T> T toJavaObject(JSONObject param, Class<T> clazz) {
		return JSON.toJavaObject(param, clazz);
	}

	public static JSONObject parseObject(String param) {
		return JSON.parseObject(param);
	}

	public static <T> JSONObject parseObject(T param) {
		return JSON.parseObject(toJSONString(param));
	}

	public static <T> JSONArray parseArray(List<T> params) {
		return JSON.parseArray(toJSONString(params));
	}

	public static <T> JSONArray parseArray(String params) {
		return JSON.parseArray(params);
	}

}
