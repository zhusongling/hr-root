package com.ling.hr.base.constant;

public class BaseErrorCode {
    public static final Integer STATUS_SUCCESS = 1; // 成功
    public static final Integer STATUS_FAILED = 0; // 失败

    public static final Integer INTERNAL_SERVER_ERROR = 500; // 内部服务器错误
    public static final Integer DATA_ACCESS_ERROR = 600; // 数据访问异常
    public static final Integer BUSINESS_ERROR = 700; // 业务逻辑异常

    /* 会话管理 */
    public static final Integer SESSION_INVALID = 800;// 回话无效
    public static final Integer SESSION_TIMEOUT = 801;// 回话超时
    public static final Integer NEED_USER_LOGIN = 802;// 需要用户登录
    public static final Integer NEED_VIP_AUTH = 803;// 需要用户的会员权限

    /* 用户注册、登陆 */
    public static final Integer LOGIN_NAME_EXISTED = 810;// 注册时，登录名已存在
    public static final Integer SAVE_ACCOUNT_FAILED = 811;// 注册时，账号保存失败
    public static final Integer LOGIN_NAME_OR_PWD_WRONG = 812;// 用户名或密码错误
    public static final Integer LOGIN_TYPE_NOT_ALLOW = 813;// 不允许的登陆方式
    public static final Integer THIRD_ACCOUNT_TOKEN_EXPIRE = 814;// 三方登录授权失效，即三方授权过期
    public static final Integer THIRD_ORDER__QUER_FAIL = 815;// 三方订单查询失败
    public static final Integer THIRD_PAY_CHECK_SIGN_FAIL = 816;// 三方验签失败

    /* 通用错误码 */
    public static final Integer PARAM_BLANK = 900;// 参数为空
    public static final Integer PARAM_ILLEGAL = 901;// 参数非法
    public static final Integer CONTENT_IS_BLANK = 902;// 内容为空

    // 定义业务错误码，从1000开始
    public static final Integer OSS_UPLOAD_FAILED = 1001;// OSS文件上传失败

    public static final Integer LOGIN_TYPE_UNALLOW = 1013;// 不允许的登陆方式

    /* 通用错误码 */
    public static final Integer PARAMETER_BLANK = 2000;// 参数为空
    public static final Integer PARAMETER_ILLEGAL = 2001;// 参数非法

    /* 职业测评 */
    public static final Integer CAREER_QUIZ_0 = 7000; // 接口请求失败
    public static final Integer CAREER_QUIZ_USER_TEST_110 = 7001; // 用户名重复
    public static final Integer CAREER_QUIZ_NO_USER = 7002; // 用户名不存在
    public static final Integer CAREER_QUIZ_USER_TEST_111 = 7003; // 用户名含有非法字符
    public static final Integer CAREER_QUIZ_USER_TEST_112 = 7004; // 用户注册失败
    public static final Integer CAREER_QUIZ_USER_TEST_113 = 7005; // 所提交的参数错误
    public static final Integer CAREER_QUIZ_USER_TEST_114 = 7006; // 没有该试卷（出现可能原因为测评标识错误）
    public static final Integer CAREER_QUIZ_USER_TEST_115 = 7007; // 该学员为自己的学员

    /* 服务端验证码验证 */
    public static final Integer PHONE_FORMATDAY_ERROR = 457;// 手机号码格式错误
    public static final Integer CODE_IS_NULL = 466;// 请求校验的验证码为空
    public static final Integer REQUEST_CHECK_FREQUENCY = 467;// 请求校验验证码频繁
    public static final Integer ILLEGAL_CHECK_REQUEST = 468;// 验证码错误

    public static final Integer HAS_NO_SEQ_CONFIG = 5000; // 没有相应的序列配置信息
    public static final Integer HAS_NO_SHENGYUANDI = 5001;// 没有生源地信息
    public static final Integer HAS_NO_SUBJECT_CATEGORY = 5002;// 没有科目类别信息
    public static final Integer HAS_NO_LUQUPICI = 5003;// 没有录取批次信息
    public static final Integer HAS_NO_YUCEDENGJI = 5004;// 没有预测等级信息
    public static final Integer HAS_NO_YUCENIANFEN = 5005;// 没有预测年份信息
    public static final Integer PHONE_IS_NULL = 5006;// 手机号为空

}
