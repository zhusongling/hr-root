package com.ling.hr.controller;

import cn.hutool.core.util.RandomUtil;
import com.ling.hr.base.model.WebResult;
import com.ling.hr.base.utils.MD5Util;
import com.ling.hr.base.utils.RandImageUtil;
import com.ling.hr.base.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/sys")
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;

    private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    @GetMapping(value = "randomImage")
    public WebResult randomImage() throws IOException {
        String key = "1621480090673";

        String code = RandomUtil.randomString(BASE_CHECK_CODES, 4);
        String lowerCaseCode = code.toLowerCase();
        String realKey = MD5Util.MD5Encode(lowerCaseCode + key, "utf-8");
        System.out.println("=====>>" + realKey);
        redisUtil.set(realKey, lowerCaseCode, 60);
        String base64 = RandImageUtil.generate(code);
        return WebResult.success(base64);
    }

    @GetMapping(value = "hello")
    public WebResult HelloWorld(String captcha) {

        String checkKey = "1621480090673";
        System.out.println("captcha===========>>: " + captcha);

        String lowerCaseCaptcha = captcha.toLowerCase();
        System.out.println("lowerCaseCaptcha===========>>: " + lowerCaseCaptcha);
        System.out.println("checkKey===========>>: " + checkKey);
        String realKey = MD5Util.MD5Encode(lowerCaseCaptcha + checkKey, "utf-8");
        System.out.println("realKey===========>>: " + realKey);
        Object checkCode = redisUtil.get(realKey);
        System.out.println(checkCode);
        return null;

    }

}
