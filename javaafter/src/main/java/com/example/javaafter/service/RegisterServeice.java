package com.example.javaafter.service;


import ch.qos.logback.classic.spi.EventArgUtil;
import cn.hutool.core.bean.BeanUtil;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.javaafter.common.Result;
import com.example.javaafter.dto.DoctorDto;
import com.example.javaafter.dto.SickDto;
import com.example.javaafter.entity.Doctor;
import com.example.javaafter.entity.Register;
import com.example.javaafter.entity.Sick;
import com.example.javaafter.mapper.RegisterMapper;
import com.example.javaafter.mapper.SickMapper;
import com.example.javaafter.util.JWTUtil;
import com.example.javaafter.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegisterServeice {

    @Resource
    private RegisterMapper registerMapper;

    @Resource
    private SickMapper sickMapper;


    public Result getInformation() {
        String doctorName1 = JWTUtil.LOGINInformation.get("doctorName");
        //获取登录医生的所有病人对象
        List<SickDto> sickInfomation = registerMapper.getSickInfomation(doctorName1);
        //对病人对象筛选出预约未结束的
        List<SickDto> collect = sickInfomation.stream().filter(sickDto -> sickDto.getEndTime() == null).collect(Collectors.toList());
        return Result.success(collect);
    }

    //病人预约医生
    public Result appointment(DoctorDto doctor) throws ParseException {

        String sickXH = JWTUtil.LOGINInformation.get("sickXh");
        //获取登录的病人对象
        LambdaQueryWrapper<Sick> sickLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sickLambdaQueryWrapper.eq(Sick::getSickXh, sickXH);
        Sick sick1 = sickMapper.selectOne(sickLambdaQueryWrapper);
        //判断是否已有预约医生
        LambdaQueryWrapper<Register> registerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        registerLambdaQueryWrapper.eq(Register::getSickName, sick1.getSickName()).isNull(Register::getEndTime);
        Register register1 = registerMapper.selectOne(registerLambdaQueryWrapper);
        Register register = new Register();
        if (register1 != null) {
            return Result.error("1", "您已经预约过了");
        }
        else if (!TimeUtil.compareDate(doctor.getDate())){
            if (!TimeUtil.CompareTime(doctor.getDoctorEndwork())){
           return  Result.error("1", "医生不在预约时间");
       }
                register.setRegisterTime(new Timestamp(doctor.getDate().getTime()));
        }

        //更新病人的病人病情
        LambdaUpdateWrapper<Sick> sickLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        sickLambdaUpdateWrapper.eq(Sick::getSickXh, sickXH).set(Sick::getSickCondition, sick1.getSickCondition());
        //对象拷贝，预约医生插入到register表中

        register.setSickName(sick1.getSickName());
        register.setDoctorName(doctor.getDoctorName());
        //插入创建时间
        register.setCreateTime(TimeUtil.getTime());
        registerMapper.insert(register);
        return Result.success();
    }

    //医生完成就诊
    public Result completeVisit(String sickName) {
        String doctorName = JWTUtil.LOGINInformation.get("doctorName");
        Timestamp endTime = TimeUtil.getTime();
        //完成就诊更新结束时间
        Integer integer = registerMapper.updateAppointment(sickName, doctorName, endTime);
        if (integer != 1) {
            return Result.error("1", "更新失败");
        }
        return Result.success();
    }

    public Result deleteAppointment(String doctorName) {
        String sickXH = JWTUtil.LOGINInformation.get("sickXh");
        //获取登录的病人对象
        LambdaQueryWrapper<Sick> sickLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sickLambdaQueryWrapper.eq(Sick::getSickXh, sickXH);
        Sick sick1 = sickMapper.selectOne(sickLambdaQueryWrapper);
        //删除记录
        LambdaQueryWrapper<Register> registerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        registerLambdaQueryWrapper.eq(Register::getSickName, sick1.getSickName()).eq(Register::getDoctorName, doctorName).isNull(Register::getEndTime);
        int delete = registerMapper.delete(registerLambdaQueryWrapper);
        if (delete == 0) {
            return Result.error("1", "您还没有预约医生");

        }
        return Result.success();

    }

    public Result getdoctorRecord() {
        String doctorName = JWTUtil.LOGINInformation.get("doctorName");
        //获取登录医生的所有病人对象
        List<SickDto> sickInfomation = registerMapper.getSickInfomation(doctorName);
        return Result.success(sickInfomation);
    }

    public Result getsickRecord() {
        String sickXH = JWTUtil.LOGINInformation.get("sickXh");
        LambdaQueryWrapper<Sick> sickLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sickLambdaQueryWrapper.eq(Sick::getSickXh, sickXH);
        Sick sick1 = sickMapper.selectOne(sickLambdaQueryWrapper);
        //获取登录病人的所有医生对象

        List<DoctorDto> sickInfomation = registerMapper.getDoctorRecordInfomation(sick1.getSickName());
        return Result.success(sickInfomation);
    }
}
