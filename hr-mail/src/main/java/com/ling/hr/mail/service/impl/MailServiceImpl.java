package com.ling.hr.mail.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ling.hr.base.utils.FastJsonUtil;
import com.ling.hr.mail.service.MailService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Map;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${spring.mail.username}")
    private String userName; // 发件方
    @Value("${spring.mail.password}")
    private String passWord; // 发件方授权码
    @Value("${spring.mail.recipient}")
    private String recipient; // 收件方

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(Map<String, Object> param) {
        JSONObject jsonObject = FastJsonUtil.parseObject(param);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(jsonObject.getString("to"));// 收信人
        message.setSubject(jsonObject.getString("subject"));// 主题
        message.setText(jsonObject.getString("content"));// 内容
        message.setFrom(userName);// 发信人
        mailSender.send(message);
    }

    @Override
    public void sendMailFile(Map<String, Object> map, MultipartFile multipartFile) {
        logger.info("====================== 开始发送带附件的邮件 ======================");
        JSONObject jsonObject = FastJsonUtil.parseObject(map);

        /**
         * 邮箱登录用户鉴权
         */
        Session session = this.getsession();

        try {
            // 创建消息设置
            Message message = new MimeMessage(session);
            message.setSubject(jsonObject.getString("subject")); // youjian zh
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, new Address[]{new InternetAddress(recipient)});

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(jsonObject.getString("content"), "text/html;charset=UTF-8");
            multipart.addBodyPart(bodyPart);

            File file = new File("/opt/" + multipartFile.getOriginalFilename());
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);

            if (file != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource dataSource = new FileDataSource(file);
                attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }
            message.setContent(multipart);
            message.saveChanges();    //生成邮件
            Transport.send(message);
            logger.info("====================== 发送带附件的邮件成功 ======================");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("====================== 发送带附件的邮件失败 ======================" + e);
        }
    }

    /**
     * 使用加密的方式,利用465端口进行传输邮件,开启ssl
     *
     * @return
     */
    private Session getsession() {
        final String smtpPort = "465";
        Properties props = new Properties(); // 配置发送邮件的环境属性
        props.setProperty("mail.smtp.port", smtpPort); // 邮箱发送服务器端口,这里设置为465端口
        props.setProperty("mail.smtp.socketFactory.port", smtpPort); // 邮箱发送服务器端口,这里设置为465端口
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // 开启ssl
        props.setProperty("mail.smtp.socketFactory.fallback", "false"); // 开启ssl

        props.setProperty("mail.smtp.auth", "true");  // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.setProperty("mail.transport.protocol", "smtp"); //传输协议
        props.setProperty("mail.host", "smtp.163.com"); // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）

        // 构建授权信息，用于进行smtp进行身份验证
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, passWord);
                    }
                });
        session.setDebug(true); //是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
        return session;
    }
}
