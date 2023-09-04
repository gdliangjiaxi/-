package com.example.javaafter.interceptor;


import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.javaafter.common.Result;
import com.example.javaafter.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        try {
            JWTUtil.getVerify(token); //去JWTutils中验证令牌
            DecodedJWT verify = JWTUtil.getVerify(token);
            Map<String, Claim> claims = verify.getClaims();
            Set<Map.Entry<String, Claim>> entries = claims.entrySet();
            //获取登录凭证，该算法仅能在单线程中使用
            for (Map.Entry<String, Claim> entry : entries) {
                String loginProof = entry.getValue().asString();
                switch (entry.getKey()) {
                    case "sickXh":
                        JWTUtil.LOGINInformation.put("sickXh", loginProof);

                    case "doctorName":
                        JWTUtil.LOGINInformation.put("doctorName", loginProof);
                    case "adminName":
                        JWTUtil.LOGINInformation.put("adminName", loginProof);
                }

            }
            return true; //这一步到拦截器中去进行验证
        } catch (Exception e) {
            String err = JSONUtil.toJsonStr(Result.error("2", "token无效"));
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(err);

        }

        return false;
    }
}
