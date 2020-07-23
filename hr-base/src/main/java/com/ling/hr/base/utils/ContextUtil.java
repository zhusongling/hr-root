package com.ling.hr.base.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.ling.hr.base.model.UserObject;

public class ContextUtil {

	public static final String AUTH_TOKEN = "authToken"; // 用户授权码
	public static final String CURRENT_USER = "currentUser";
	public static final String REMOTE_ADDR = "remoteAddr";
	public static final String REQUEST_URI = "requestURI";
	public static final String REQUEST_ID = "requestId";
	public static final String SESSION_ID = "sessionId";
	public static final String LOCALE = "locale";
	public static final String SERVER_NAME = "serverName";
	public static final String LOGIN_TIME = "loginTime";

	private static ThreadLocal<Map<String, Object>> cache = new ThreadLocal<Map<String, Object>>();

	public static Map<String, Object> getLocalCache() {
		if (cache.get() == null) {
			cache.set(new HashMap<String, Object>());
		}
		return cache.get();
	}

	/**
	 * 清空context的值
	 */
	public static void clearContext() {
		getLocalCache().clear();
	}

	public static void setContext(String remoteAddr, String requestURI, String requestId, String sessionId, Locale locale, String serverName) {
		setRemoteAddr(remoteAddr);
		setRequestURI(requestURI);
		setRequestId(requestId);
		setSessionId(sessionId);
		setLocale(locale);
		setServerName(serverName);
	}

	/**
	 * 设置key
	 *
	 * @param key
	 * @param value
	 */
	public static void set(String key, Object value) {
		getLocalCache().put(key, value);
	}

	/**
	 * 获得key
	 *
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return getLocalCache().get(key);
	}

	/**
	 * 是否包含key
	 *
	 * @param key
	 * @return
	 */
	public static boolean hasKey(String key) {
		return getLocalCache().containsKey(key);
	}

	/**
	 * 删除key
	 *
	 * @param key
	 */
	public static void remove(String key) {
		getLocalCache().remove(key);
	}

	/**
	 * 获取当前用户
	 *
	 * @return 当前用户信息
	 */
	public static UserObject getCurrentUser() {
		return (UserObject) get(CURRENT_USER);
	}

	/**
	 * 设置当前用户
	 *
	 * @param currentUser
	 */
	public static void setCurrentUser(UserObject currentUser) {
		set(CURRENT_USER, currentUser);
	}

	/**
	 * 获取当前用户ID
	 *
	 * @return
	 */
	public static String getUserId() {
		UserObject currentUser = getCurrentUser();
		String userId = "";
		if (currentUser != null) {
			userId = currentUser.getUserId();
		}
		return userId;
	}

	/**
	 * 获取当前用户名
	 *
	 * @return
	 */
	public static String getCurrentUserName() {
		UserObject currentUser = getCurrentUser();
		String username = "";
		if (currentUser != null) {
			username = currentUser.getUserName();
		}
		return username;
	}

	/**
	 * 获取请求ID
	 *
	 * @return 请求ID
	 */
	public static String getRequestId() {
		return (String) get(REQUEST_ID);
	}

	/**
	 * 设置请求ID
	 *
	 * @param requestId
	 */
	public static void setRequestId(String requestId) {
		set(REQUEST_ID, requestId);
	}

	public static String getRemoteAddr() {
		return (String) get(REMOTE_ADDR);
	}

	public static void setRemoteAddr(String remoteAddr) {
		set(REMOTE_ADDR, remoteAddr);
	}

	public static String getRequestURI() {
		return (String) get(REQUEST_URI);
	}

	public static void setRequestURI(String requestURI) {
		set(REQUEST_URI, requestURI);
	}

	public static String getSessionId() {
		return (String) get(SESSION_ID);
	}

	public static void setSessionId(String sessionId) {
		set(SESSION_ID, sessionId);
	}

	public static Locale getLocale() {
		return (Locale) get(LOCALE);
	}

	public static void setLocale(Locale locale) {
		set(LOCALE, locale);
	}

	public static String getServerName() {
		return (String) get(SERVER_NAME);
	}

	public static void setServerName(String serverName) {
		set(SERVER_NAME, serverName);
	}

	public static String getAuthToken() {
		return (String) get(AUTH_TOKEN);
	}

	public static void setAuthToken(String authToken) {
		set(AUTH_TOKEN, authToken);
	}
}
