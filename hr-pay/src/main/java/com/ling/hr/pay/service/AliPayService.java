package com.ling.hr.pay.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface AliPayService {
    /**
     * 支付宝下单
     *
     * @param par
     * @return
     */
    public JSONObject aliPayOrder(Map<String, Object> par);

    /**
     * 支付宝订单查询
     *
     * @param par
     * @return
     */
    public JSONObject aliPayQuery(Map<String, Object> par);

    /**
     * 支付宝验签
     *
     * @param par
     * @return
     */
    JSONObject aliCheckSign(Map<String, Object> par);
}
