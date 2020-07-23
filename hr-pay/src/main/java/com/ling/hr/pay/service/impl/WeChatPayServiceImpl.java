package com.ling.hr.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ling.hr.base.constant.BaseErrorCode;
import com.ling.hr.base.enums.PayType;
import com.ling.hr.base.exception.BusinessException;
import com.ling.hr.base.model.UnifiedOrder;
import com.ling.hr.base.utils.*;
import com.ling.hr.pay.service.WeChatPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public JSONObject weChatPayOrder(Map<String, Object> par) {
        /**
         * 1、组装数据，去微信服务端下单
         * 2、再次签名，返回客户端
         */
        JSONObject paramObject = FastJsonUtil.parseObject(par);
        Integer payType = paramObject.getInteger("payType");  //payType的值: 2   5

        // 参数组：
        String nonce_str = RandomUtil.uuid32(); // 随机字符串
        String body = paramObject.getString("orderTitle");// "商品名称";
        // String detail = "0.01_元测试开始";//"商品描述， 不是必传的";
        int total_fee = (int) paramObject.getFloatValue("payAmount");  // 商品价格，必传 单位为分
        String spbill_create_ip = ContextUtil.getRemoteAddr();  // "用户端ip";
        String notify_url = paramObject.getString("notifyUrl"); // 三方回调地址
        String out_trade_no = paramObject.getString("orderId"); //本平台id
        String appid = paramObject.getString("appId");
        String mch_id = paramObject.getString("mchId");
        String trade_type = "";
        String openId = paramObject.getString("openId");
        if (PayType.WeChat.getValue().equals(payType)) {
            trade_type = "APP";
        }
        if (PayType.XCXPay.getValue().equals(payType)) {
            trade_type = "JSAPI";
        }
        logger.info("微信支付回调地址：" + notify_url);
        // 参数：开始生成签名
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("nonce_str", nonce_str);
        parameters.put("body", body);
        parameters.put("out_trade_no", out_trade_no);
        parameters.put("total_fee", total_fee);
        parameters.put("notify_url", notify_url);
        parameters.put("trade_type", trade_type);
        parameters.put("spbill_create_ip", spbill_create_ip);
        if (PayType.XCXPay.getValue().equals(payType)) {
            parameters.put("openid", openId);
        }


        String signValue = WXSignUtils.createSign("UTF-8", parameters);
        logger.debug("签名是：" + signValue);

        UnifiedOrder unifiedOrder = new UnifiedOrder();
        unifiedOrder.setAppid(appid);
        unifiedOrder.setMch_id(mch_id);
        unifiedOrder.setNonce_str(nonce_str);
        unifiedOrder.setSign(signValue);
        unifiedOrder.setBody(body);
        unifiedOrder.setOut_trade_no(out_trade_no);
        unifiedOrder.setSpbill_create_ip(spbill_create_ip);
        unifiedOrder.setNotify_url(notify_url);
        unifiedOrder.setTrade_type(trade_type);
        unifiedOrder.setTotal_fee(total_fee);
        if (PayType.XCXPay.getValue().equals(payType)) {
            unifiedOrder.setOpenid(openId);
        }
        // 构造xml参数
        String xmlInfo = HttpXmlUtils.jsonToXml(unifiedOrder);
        logger.debug("xml参数:" + xmlInfo);

        String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder"; //微信支付url
        String method = "POST";
        logger.debug("开始调微信下单接口");
        String weiXinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
        logger.info("微信下单接口调用结束,返回数据：" + weiXinPost);


        JSONObject returnData = new JSONObject();
        try {
            Map<String, String> orderMap = HttpXmlUtils.xmlToMap(weiXinPost);
            returnData = FastJsonUtil.parseObject(orderMap);

            appid = returnData.getString("appid");
            String timestamp = Long.toString(System.currentTimeMillis() / 1000);
            nonce_str = returnData.getString("nonce_str");
            String prepayid = returnData.getString("prepay_id");
            String partnerid = "";

            // 微信需要再次签名
            parameters.clear();


            if (PayType.WeChat.getValue().equals(payType)) { // 如果是微信支付
                parameters.put("noncestr", nonce_str);
                parameters.put("timestamp", timestamp);
                parameters.put("appid", appid);
                partnerid = returnData.getString("mch_id");
                parameters.put("package", "Sign=WXPay");
                parameters.put("partnerid", partnerid);
                parameters.put("prepayid", prepayid);
            }
            if (PayType.XCXPay.getValue().equals(payType)) { // 如果小程序支付
                parameters.put("nonceStr", nonce_str);
                parameters.put("timeStamp", timestamp);
                parameters.put("appId", appid);
                parameters.put("package", "prepay_id=" + prepayid);
                parameters.put("signType", "MD5");
            }

            // 签名
            signValue = WXSignUtils.createSign("UTF-8", parameters);
            returnData.clear();
            // 返回给app数据
            returnData.put("timestamp", timestamp);
            returnData.put("paySign", signValue);
            returnData.put("nonceStr", nonce_str);
            returnData.put("prepayid", prepayid);

            if (PayType.WeChat.getValue().equals(payType)) {
                returnData.put("appid", appid);
                returnData.put("partnerid", partnerid);
                returnData.put("package", "Sign=WXPay");
            }
            if (PayType.XCXPay.getValue().equals(payType)) {
                returnData.put("package", "prepay_id=" + prepayid);
                returnData.put("signType", "MD5");
            }
            logger.info("返给app的数据：" + returnData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnData;
    }


    @Override
    public JSONObject weChatPayQuery(Map<String, Object> par) {
        JSONObject paramObject = FastJsonUtil.parseObject(par);

        String appid = paramObject.getString("appId");
        String mch_id = paramObject.getString("mchId");
        String out_trade_no = paramObject.getString("orderId"); //本平台的订单号
        String nonce_str = RandomUtil.uuid32();

        // 生成签名
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("nonce_str", nonce_str);
        parameters.put("out_trade_no", out_trade_no);
        String sign = WXSignUtils.createSign("UTF-8", parameters);

        // 构造xml参数
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        unifiedOrder.setAppid(appid);
        unifiedOrder.setMch_id(mch_id);
        unifiedOrder.setNonce_str(nonce_str);
        unifiedOrder.setOut_trade_no(out_trade_no);
        unifiedOrder.setSign(sign);
        String xmlInfo = HttpXmlUtils.jsonToXml(unifiedOrder);
        logger.info("微信查询订单请求的xml参数:" + xmlInfo);

        String wxUrl = "https://api.mch.weixin.qq.com/pay/orderquery";  //微信查询接口

        String method = "POST";
        String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
        Map<String, String> map = null;
        try {
            map = HttpXmlUtils.xmlToMap(weixinPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String return_code = map.get("return_code");
        if (StringUtil.equals(return_code, "SUCCESS")) { // 接口成功调用
            String result_code = map.get("result_code");
            if (StringUtil.equals(result_code, "SUCCESS")) {
                sign = map.get("sign"); // 微信返回的签名
                map.remove("sign");
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    logger.debug("取map里的值：" + entry.getKey() + ":" + entry.getValue());
                    parameters.put(entry.getKey(), entry.getValue());
                }
                String signValue = WXSignUtils.createSign("UTF-8", parameters);
                if (!StringUtil.equals(sign, signValue)) { //验签不成功
                    throw new BusinessException(BaseErrorCode.STATUS_FAILED);
                }
            } else {
                throw new BusinessException(BaseErrorCode.STATUS_FAILED);
            }
        } else {
            throw new BusinessException(BaseErrorCode.STATUS_FAILED);
        }
        JSONObject returnData = new JSONObject();
        returnData.put("orderStatus", map.get("trade_state"));
        return returnData;
    }
}
