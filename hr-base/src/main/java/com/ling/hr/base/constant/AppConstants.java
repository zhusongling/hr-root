package com.ling.hr.base.constant;

public class AppConstants {

	public static final String SEPARATOR_DOT = ".";// 字符串拼接分隔符，点
	public static final String SEPARATOR_COMMA = ",";// 字符串拼接分隔符，逗号
	public static final String SEPARATOR_COLON = ":";// 字符串拼接分隔符，冒号
	public static final String SEPARATOR_SEMICOLON = ";";// 字符串拼接分隔符，分号
	public static final String SEPARATOR_UNDERLINE = "_";// 字符串拼接分隔符，下划线
	public static final String SEPARATOR_AT = "@";// 字符串拼接分隔符
	public static final String SEPARATOR_AMPERSAND = "&";// 字符串拼接分隔符

	public static final String DEFAULT_USER = "00000000000000000000000000000000"; // 系统默认用户
	public static String APP_INST_ID = "00000000000000000000000000000000"; // 系统默认应用实例
	public static final String DEFAULT_APP_INST = "00000000000000000000000000000000"; // 系统默认应用实例

	public static final Integer SESSION_DEFAULT_EXPIRE_TIME = 60 * 60; // 用户SESSION默认过期时间，单位：s
	public static final Integer SESSION_MOBILE_EXPIRE_TIME = 7 * 24 * 60 * 60; // 用户SESSION（Mobile）过期时间，单位：s

	// 数据库表公共字段
	public static final String ROW_STATUS = "rowStatus";
	public static final String ROW_CREATOR = "rowCreator";
	public static final String ROW_CREATE_TIME = "rowCreateTime";
	public static final String ROW_CREATE_INST_ID = "rowCreateInstId";
	public static final String ROW_UPDATER = "rowUpdater";
	public static final String ROW_UPDATE_TIME = "rowUpdateTime";
	public static final String ROW_UPDATE_INST_ID = "rowUpdateInstId";
}
