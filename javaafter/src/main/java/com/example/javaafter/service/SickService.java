package com.example.javaafter.service;


import cn.hutool.core.bean.BeanUtil;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javaafter.common.Result;
import com.example.javaafter.dto.SickDto;
import com.example.javaafter.entity.Register;
import com.example.javaafter.entity.Sick;
import com.example.javaafter.mapper.RegisterMapper;
import com.example.javaafter.mapper.SickMapper;
import com.example.javaafter.util.JWTUtil;
import com.example.javaafter.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.javaafter.common.Result.success;

@Service
public class SickService {

    @Resource
    private SickMapper sickMapper;
    @Resource
    private RegisterMapper registerMapper;


    public Result getsick() {

        String sickXh = JWTUtil.LOGINInformation.get("sickXh");
        //获取登录的病人对象
        LambdaQueryWrapper<Sick> sickLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sickLambdaQueryWrapper.eq(Sick::getSickXh, sickXh);
        Sick sick1 = sickMapper.selectOne(sickLambdaQueryWrapper);
        //获取该病人的当天预约医生
        String doctorInfomation = registerMapper.getDoctorInfomation(sick1.getSickName());
        if (doctorInfomation == null) {
            doctorInfomation = "没有预约";
        }
        //传入Dto
        SickDto dto = new SickDto();
        //对象属性拷贝
        BeanUtil.copyProperties(sick1, dto);
        dto.setDoctorName(doctorInfomation);
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(dto);
        return success(objects);
    }
}
