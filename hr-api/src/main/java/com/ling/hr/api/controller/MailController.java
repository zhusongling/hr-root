package com.ling.hr.api.controller;

import com.ling.hr.base.provider.MailProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    MailProvider mailProvider;

    /**
     * 发送邮件
     *
     * @param param
     */
    @RequestMapping(value = "sendMail")
    public void sendMail(@RequestParam Map<String, Object> param) {
        mailProvider.sendMail(param);
    }
}
