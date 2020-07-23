package com.ling.hr.base.utils;

import com.ling.hr.base.utils.MD5Util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * 微信支付签名
 *
 * @author iYjrg_xiebin
 * @date 2016年10月25日下午4:47:07
 */
public class WXSignUtils {

    static String weChatKey = "0zhimakaimen2018yunxiaosheng2018";

    //http://mch.weixin.qq.com/wiki/doc/api/index.php?chapter=4_3
    //商户Key：改成公司申请的即可
    //32位密码设置地址：http://www.sexauth.com/ jdex1hvufnm1sdcb0e81t36k0d0f15nc

    /**
     * 微信支付签名算法sign
     *
     * @param characterEncoding
     * @param parameters
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        // 第一步：对参数按照key=value的格式，并按照参数名ASCII字典序排序如下：
        // stringA="appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        // 第二步：拼接API密钥：
        // stringSignTemp=stringA+"&key=192006250b4c09247ec02edce69f6a2d" //注：key为商户平台设置的密钥key
        sb.append("key=" + weChatKey);
        System.out.println("字符串拼接后是：" + sb.toString());

        // sign=MD5(stringSignTemp).toUpperCase()="9A0A8659F005D6984697E2CA0A9CF3B7" //注：MD5签名方式
        // sign=hash_hmac("sha256", stringSignTemp, key).toUpperCase()="6A9AE1657590FD6257D693A078E1C3E4BB6BA4DC30B23E0EE2496E54170DACD6" //注：HMAC-SHA256签名方式
        String signValue = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return signValue;
    }

}
