package com.ling.hr.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.ling.hr.base.constant.BaseErrorCode;
import com.ling.hr.base.exception.BusinessException;
import com.ling.hr.base.utils.FastJsonUtil;
import com.ling.hr.base.utils.StringUtil;
import com.ling.hr.pay.service.AliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AliPayServiceImpl implements AliPayService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public JSONObject aliPayOrder(Map<String, Object> par) {
        JSONObject parData = FastJsonUtil.parseObject(par);
        String notifyUrl = parData.getString("notifyUrl");
        String orderTitle = parData.getString("orderTitle");
        String remark = parData.getString("remark");
        String orderId = parData.getString("orderId"); // 本平台订单号
        String payAmount = parData.getString("payAmount"); // 订单价格，单位：元 精确到小数点后两位
        String payUrl = "https://openapi.alipay.com/gateway.do";
        String appid = parData.getString("appId");
        String appPrivateKey = parData.getString("appPrivateKey");
        String publicKey = parData.getString("publicKey");
        // 实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(payUrl, appid, appPrivateKey, "json", "UTF-8", publicKey, "RSA2");

        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        // 商品的标题/交易标题/订单标题/订单关键字等
        model.setSubject(orderTitle);
        // 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
        model.setBody(remark);
        model.setOutTradeNo(orderId); // 商户订单号 订单号
        model.setTotalAmount(payAmount);// 支付金额
        // 设置未付款支付宝交易的超时时间，一旦超时，该笔交易就会自动被关闭。
        // 当用户进入支付宝收银台页面（不包括登录页面），会触发即刻创建支付宝交易，此时开始计时。
        // 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        model.setTimeoutExpress("1d"); // 1天
        // 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]这里调试每次支付1分钱，在项目上线前应将此处改为订单的总金额

        // 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY

        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);// 商户外网可以访问的异步地址
        String orderString = null;
        try {
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            // 就是orderString 可以直接给客户端请求，无需再做处理。
            orderString = response.getBody();
            logger.info(orderString);
        } catch (AlipayApiException e) {
            logger.error(e.getMessage(), e);
        }

        JSONObject returnData = new JSONObject();
        returnData.put("orderString", orderString);
        return returnData;
    }

    @Override
    public JSONObject aliPayQuery(Map<String, Object> par) {
        JSONObject paramObject = FastJsonUtil.parseObject(par);
        String trade_no = paramObject.getString("tradeNo"); // 三方交易号
        String out_trade_no = paramObject.getString("orderId"); // 本平台订单号
        String payUrl = "https://openapi.alipay.com/gateway.do";
        String appid = paramObject.getString("appId");
        String appPrivateKey = paramObject.getString("appPrivateKey");
        String publicKey = paramObject.getString("publicKey");

        // 实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(payUrl, appid, appPrivateKey, "json", "UTF-8", publicKey, "RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject data = new JSONObject();
        data.put("trade_no", trade_no);
        // data.put("out_trade_no", out_trade_no);
        request.setBizContent(data.toJSONString());
        AlipayTradeQueryResponse response = new AlipayTradeQueryResponse();
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        String tradeStatus = "";
        if (response.isSuccess()) {
            tradeStatus = response.getTradeStatus();
        } else {
            throw new BusinessException(BaseErrorCode.STATUS_FAILED);
        }
        if (StringUtil.isBlank(tradeStatus)) {
            throw new BusinessException(BaseErrorCode.THIRD_ORDER__QUER_FAIL);
        }
        JSONObject returnData = new JSONObject();
        returnData.put("orderStatus", tradeStatus);
        return returnData;
    }

    @Override
    public JSONObject aliCheckSign(Map<String, Object> par) {
        JSONObject jsonObject = FastJsonUtil.parseObject(par);
        String publicKey = jsonObject.getString("publicKey");
        String param = jsonObject.getString("content");
        String sign = jsonObject.getString("sign");
        boolean flag = false;
        try {
            flag = AlipaySignature.rsa256CheckContent(param, sign, publicKey, "utf-8");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        JSONObject returnData = new JSONObject();
        returnData.put("flag", flag);
        return returnData;
    }
}
