package com.example.javaafter.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {


    // 自定义签名
    private static final String SIGN = "!kcsjLJX";
    public static final Map<String, String> LOGINInformation = new HashMap<>();

    //获取token令牌
    public static String getToken(Map<String, String> claim) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 15);  // 15分钟过期

        // 创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        // payload -- 把传进来的map放入builder.withClaim()中
        claim.forEach((key, value) -> {
            builder.withClaim(key, value);
        });


        return builder.withExpiresAt(calendar.getTime())       // 指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));// 指定签名sign

    }

    /**
     * 解析token令牌
     *
     * @param token 传入token字符串
     * @return verify证明
     */
    public static DecodedJWT getVerify(String token) {
        // 解析的签名
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

//    Map<String, Claim> claims = verify.getClaims();
//    Claim claim = claims.get("doctorName");
//    String doctorName = claim.asString();


    //医生登录获取JWT载荷中登录的姓名
    public static DecodedJWT getVerifyDoctorName(String token) {
        // 解析的签名
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
