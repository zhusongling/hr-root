package com.ling.hr.base.provider;

import com.ling.hr.base.constant.EurekaServiceUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class PayProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;


    /**
     * 支付宝下单
     *
     * @param par
     * @return
     */
    public String aliPayOrder(Map<String, Object> par) {
        String result = null;
        String url = EurekaServiceUrl.GET_ALI_ORDER;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        par.forEach((key, value) -> {
            params.add(key, value);
        });
        HttpEntity request = new HttpEntity(params, headers);
        try {
//			result = this.restTemplate.getForObject(url,String.class,par);
            result = this.restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付宝下单异常", result);
        }
        return result;
    }

    /**
     * 支付宝订单查询
     *
     * @param par
     * @return
     */
    public String aliPayQuery(Map<String, Object> par) {
        String result = null;
        String url = EurekaServiceUrl.GET_ALI_QUERY_ORDER;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        par.forEach((key, value) -> {
            params.add(key, value);
        });
        HttpEntity request = new HttpEntity(params, headers);
        try {
            result = this.restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付宝查询订单异常", result);
        }
        return result;
    }


    /**
     * 微信下单
     *
     * @param par
     * @return
     */
    public String weChatPayOrder(Map<String, Object> par) {
        String result = null;
        String url = EurekaServiceUrl.GET_WE_CHAT_ORDER;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        par.forEach((key, value) -> {
            params.add(key, value);
        });
        HttpEntity request = new HttpEntity(params, headers);
        try {
            result = this.restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("微信下单异常", result);
        }
        return result;
    }

    /**
     * 微信订单查询
     *
     * @param par
     * @return
     */
    public String weChatPayQuery(Map<String, Object> par) {
        String result = null;
        String url = EurekaServiceUrl.GET_WE_CHAT_QUERY_ORDER;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        par.forEach((key, value) -> {
            params.add(key, value);
        });
        HttpEntity request = new HttpEntity(params, headers);
        try {
            result = this.restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("微信查询订单异常", result);
        }
        return result;
    }

    /**
     * 支付宝验签
     *
     * @param par
     * @return
     */
    public String aliCheckSign(Map<String, Object> par) {
        String result = null;
        String url = EurekaServiceUrl.GET_ALI_CHECK_SIGN;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        par.forEach((key, value) -> {
            params.add(key, value);
        });
        HttpEntity request = new HttpEntity(params, headers);
        try {
            result = this.restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("微信查询订单异常", result);
        }
        return result;
    }


}
