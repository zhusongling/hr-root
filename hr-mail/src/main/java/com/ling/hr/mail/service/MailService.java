package com.ling.hr.mail.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface MailService {

    /**
     * 发送邮件
     *
     * @param param
     */
    void sendMail(Map<String, Object> param);

    /**
     * 发送附件邮件
     *
     * @param map(subject:主题、content:内容)
     * @param file
     */
    void sendMailFile(Map<String, Object> map, MultipartFile file);
}
