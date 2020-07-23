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
public class MailProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    public String sendMail(Map<String, Object> param) {
        String result = null;
        String url = EurekaServiceUrl.POST_SEND_MAIL;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        param.forEach((key, value) -> {
            params.add(key, value);
        });
        HttpEntity request = new HttpEntity(params, headers);
        try {
            result = this.restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("邮件发送异常", result);
        }
        return result;
    }
}
