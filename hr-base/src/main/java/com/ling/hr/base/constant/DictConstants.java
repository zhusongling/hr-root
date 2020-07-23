package com.ling.hr.base.constant;

/**
 * 字典常量类
 */
public class DictConstants {

    public static final String CONFIG_KEY = "configValue";

    public static final String T_APP_BASE_CONFIG = "APP_BASE_CONFIG";
    public static final String I_APP_BASE_CONFIG_SCORE_ANALYSE_RATIO = "SCORE_ANALYSE_RATIO";
    public static final String I_APP_BASE_CONFIG_SCORE_ANALYSE_FLAG = "SCORE_ANALYSE_FLAG";

    public static final String T_BATCH_TYPE = "BATCH_TYPE";// 院校录取批次
    public static final String T_SUBJECT_TYPE = "SUBJECT_TYPE";// 科目类别
    public static final String T_DELIVER_TYPE = "DELIVER_TYPE";// 投档类型

    public static final String T_ENROLL_FORECAST = "ENROLL_FORECAST";// 院校录取预测
    public static final String I_ENROLL_FORECAST_ANALYSE_YEAR = "analyseYear";// 分析年份
    public static final String I_DAN_ZHAO_YEAR = "danZhaoYear";// 单招年份
    public static final String I_ENROLL_FORECAST_LESS_THAN = "lessThan"; // 低于冲刺（概率低于0）
    public static final String I_ENROLL_FORECAST_CHONG_CI = "chongCi"; // 冲刺
    public static final String I_ENROLL_FORECAST_SHI_ZHONG = "shiZhong"; // 适中
    public static final String I_ENROLL_FORECAST_BAO_DI = "baoDi"; // 保底
    public static final String I_ENROLL_FORECAST_GREATER_THAN = "greaterThan"; // 高于保底（概率大于100）

}
