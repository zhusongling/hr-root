package com.ling.hr.base.model;

import lombok.Data;

/**
 * 微信支付用
 */
@Data
public class UnifiedOrder {
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String body;
    private String detail;
    private String out_trade_no;
    private String spbill_create_ip;
    private String notify_url;
    private String trade_type;
    private Integer total_fee;
    private String transaction_id;

    private String openid;
    private String return_code; //服务端返给微信的参数
}
