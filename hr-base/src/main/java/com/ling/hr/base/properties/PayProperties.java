package com.ling.hr.base.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "pay")
public class PayProperties {

    @Data
    public static  class AliPay{
        private String appId;
        private String appPrivateKey;
        private String privateKey;
        private String publicKey;
        private String notifyUrl;
    }

    @Data
    public static  class WeChatPay{
        private String appId;
        private String mchId;
        private String notifyUrl;
        private String xcxAppId;
    }

    private AliPay aliPay;
    private WeChatPay weChatPay;
}
