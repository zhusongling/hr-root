package com.ling.hr.base.utils;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token的生成和解析
 */
public class JwtTokenUtil {

    private static final String JWT_SECRET = "c03d79c91da44e4284a24cc72c675378";

    public static void main(String[] args) {
        System.out.println(RandomUtil.uuid());
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", RandomUtil.uuid32());
        claims.put("cid", RandomUtil.uuid32());
        claims.put("ctp", RandomUtil.uuid32());
        String token = createToken(RandomUtil.uuid32(), "dgy-yxs-user-id", claims);
        System.out.println(token);
        System.out.println(parseToken(token));

    }

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header.payload.signature
     *
     * @param id
     * @param subject
     * @param claims
     */
    public static String createToken(String id, String subject, Map<String, Object> claims) {
        return createToken(id, subject, claims, 0);
    }

    public static String createToken(String id, String subject, Map<String, Object> claims, long ttlMillis) {
        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();// 生成JWT的时间
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setClaims(claims).setId(id).setIssuer("dgy-yxs").setSubject(subject).setIssuedAt(now).signWith(signatureAlgorithm, generateKey());

        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp); // 设置过期时间
        }

        return builder.compact();
    }

    /**
     * 解析Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parseToken(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
        return jws.getBody();
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generateKey() {
        String stringKey = JWT_SECRET;// 本地配置文件中加密的密文
        byte[] encodedKey = Base64.decodeBase64(stringKey);// 本地的密码解码[B@152f6e2
        // System.out.println(encodedKey);//[B@152f6e2
        // System.out.println(Base64.encodeBase64URLSafeString(encodedKey));//7786df7fc3a34e26a61c034d5ec8245d
        // 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有
        SecretKey key = new SecretKeySpec(encodedKey, "AES");
        return key;
    }

}
