package com.ling.hr.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.ling.hr.pay.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("aliPay")
public class AliPayController {

    @Autowired
    AliPayService aliPayService;

    /**
     * 支付宝，预支付下单
     *
     * @param par
     * @return
     */
    @RequestMapping("aliPayOrder")
    public JSONObject aliPayOrder(@RequestParam Map<String, Object> par) {
        return aliPayService.aliPayOrder(par);
    }


    /**
     * 支付宝，查询订单
     *
     * @param par
     * @return
     */
    @RequestMapping("aliPayQuery")
    public JSONObject aliPayQuery(@RequestParam Map<String, Object> par) {
        return aliPayService.aliPayQuery(par);
    }

    /**
     * 支付宝，验签
     *
     * @param par
     * @return
     */
    @RequestMapping("aliCheckSign")
    public JSONObject aliCheckSign(@RequestParam Map<String, Object> par) {
        return aliPayService.aliCheckSign(par);
    }

}
