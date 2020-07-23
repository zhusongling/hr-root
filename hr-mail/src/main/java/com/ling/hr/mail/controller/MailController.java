package com.ling.hr.mail.controller;

import com.ling.hr.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件
     *
     * @param param
     */
    @RequestMapping(value = "sendMail")
    public void sendMail(@RequestParam Map<String, Object> param) {
        mailService.sendMail(param);
    }

    /**
     * 发送附件邮件
     *
     * @param map
     * @param file
     */
    @PostMapping(value = "sendMailFile")
    public void sendMailFile(@RequestParam Map<String, Object> map, MultipartFile file) {
        this.mailService.sendMailFile(map, file);
    }
}
