package com.ling.hr.base.constant;

public class AppCacheKeys {
	public static final String SESSION_AUTH_TOKEN = "HR.SESSION:TOKEN:AuthToken_"; // 登陆用户SESSION

	// 后台任务
	public static final String TASK_MONITOR_TASK_LIST = "YXS.TASK:MONITOR:TASK_LIST:TaskType_"; // 任务监控
	public static final String TASK_COLLEGE_ENROLL_FORECAST = "YXS.TASK:ENROLL_FORECAST:COLLEGE:CollegeId_%"; // 院校录取分数预测
	public static final String TASK_MAJOR_ENROLL_FORECAST = "YXS.TASK:ENROLL_FORECAST:MAJOR:MajorId_%"; // 专业录取分数预测

	public static final String MUO_SESSION_WEB_USER = "MUO:SESSION:WEB_USER:AuthToken_"; // web用户SESSION
	public static final String MUO_SESSION_APP_USER = "MUO_SESSION_APP_USER:AuthToken_"; // app用户SESSION
	public static final String MUO_SESSION_APP_INST = "MUO_SESSION_APP_INST:AuthToken_"; // 应用实例SESSION
	public static final String MUO_ONINE_USER = "MUO_ONLINE_USER:AppInstId_"; // 在线用户列表

	public static final String I18N = "HR.SYSTEM.CONFIG:I18N:"; // 错误码
	public static final String ERROR_CODE = "HR.SYSTEM.CONFIG:I18N:ERROR_CODE_"; // 错误码

	// 微信公众号 基础信息
	public static final String WECHAT_GZH_FW = "YXS.WECHAT.GZH:FW"; // 微信公众号 服务号
	public static final String WECHAT_GZH_DY = "YXS.WECHAT.GZH:DY"; // 微信公众号 服务号

	public static final Integer SESSION_DEFAULT_EXPIRE_TIME = 60 * 60; // 用户SESSION默认过期时间，单位：s
	public static final Integer SESSION_MOBILE_EXPIRE_TIME = 7 * 24 * 60 * 60; // 用户SESSION（Mobile）过期时间，单位：s

}
