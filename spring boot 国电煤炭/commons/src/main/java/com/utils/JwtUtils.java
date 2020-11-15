package com.utils;

import com.entity.Payload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

public class JwtUtils {

    private static final String JWT_PAYLOAD_USER_KEY="user";

    /**
     * ID生成
     * @return
     */
    private static String createJTI(){
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 私钥加密token
     * @param userInfo  载荷数据
     * @param privateKey    私钥
     * @param expire    过期时间，单位分钟
     * @return
     */
    public static String generateTokenExpireInMinutes(Object userInfo, PrivateKey privateKey,int expire){
        return Jwts.builder()
                //放置一个Map(key,value)
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                //设置id
                .setId(createJTI())
                //过期时间
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                //签名方式
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    /**
     * 公钥解析token
     * @param token 用户请求中的token
     * @param publicKey 公钥
     * @return  Jws<Claims>
     */
    public static Jws<Claims> parserToken(String token, PublicKey publicKey){
        return  Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     * @param token 用户请求中的token
     * @param publicKey 公钥
     * @return
     */
    public static <T>Payload<T> getInfoFromToken(String token,PublicKey publicKey,Class<T> userType){
        Jws<Claims> claimsJws=parserToken(token,publicKey);
        Claims body=claimsJws.getBody();
        Payload<T> claims=new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(),userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 获取token中的载荷信息
     * @param token
     * @param publicKey
     * @return
     */
    public static <T>Payload<T> getInfoFromToken(String token,PublicKey publicKey){
        Jws<Claims> claimsJws=parserToken(token,publicKey);
        Claims body=claimsJws.getBody();
        Payload<T> claims=new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }

}
