package com.ling.hr.base.constant;

public class EurekaServiceUrl {

    public static final String POST_SEND_MAIL = "http://MAIL-SERVICE/mail/mail/sendMail"; // 发送邮件

    public static final String GET_FULL_SEQUENCE = "http://SMG-SERVICE/smg/sequence/getFullSequence";
    public static final String GET_ALI_ORDER = "http://PAY-SERVICE/pay/aliPay/aliPayOrder";
    public static final String GET_ALI_QUERY_ORDER = "http://PAY-SERVICE/pay/aliPay/aliPayQuery";
    public static final String GET_ALI_CHECK_SIGN = "http://PAY-SERVICE/pay/aliPay/aliCheckSign";
    public static final String GET_WE_CHAT_ORDER = "http://PAY-SERVICE/pay/weChatPay/weChatPayOrder";
    public static final String GET_WE_CHAT_QUERY_ORDER = "http://PAY-SERVICE/pay/weChatPay/weChatPayQuery";
}
