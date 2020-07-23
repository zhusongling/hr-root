package com.ling.hr.pay.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface WeChatPayService {
    /**
     * 微信下单
     *
     * @param par
     * @return
     */
    public JSONObject weChatPayOrder(Map<String, Object> par);


    /**
     * 微信订单查询
     *
     * @param par
     * @return
     */
    public JSONObject weChatPayQuery(Map<String, Object> par);
}
