package com.example.javaafter.controller;


import cn.hutool.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.javaafter.common.Result;
import com.example.javaafter.entity.Sick;
import com.example.javaafter.mapper.SickMapper;
import com.example.javaafter.service.SickService;
import com.example.javaafter.util.JWTUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/sick")
public class SickController {

    @Resource
    private SickService sickService;

    //获取登录的学生病人个人的基本信息
    @GetMapping
    public Result getsick() {
        return sickService.getsick();

    }


}
