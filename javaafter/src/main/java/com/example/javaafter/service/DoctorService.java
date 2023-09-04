package com.example.javaafter.service;


import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javaafter.common.Result;
import com.example.javaafter.entity.Doctor;
import com.example.javaafter.entity.Register;
import com.example.javaafter.mapper.RegisterMapper;
import com.example.javaafter.mapper.DoctorMapper;
import com.example.javaafter.util.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DoctorService {
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private RegisterMapper registerMapper;

    public Result updateDoctor(Doctor doctor) {
        doctorMapper.updateById(doctor);
        return Result.success();
    }

    //查询医生预约人数
    public Integer queryRegisterPerson(String doctorName) {
        LambdaQueryWrapper<Register> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Register::getDoctorName, doctorName).isNull(Register::getEndTime);

        return Math.toIntExact(registerMapper.selectCount(queryWrapper));
    }


    public Result getInformation() {
        String doctorName = JWTUtil.LOGINInformation.get("doctorName");
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Doctor::getDoctorName, doctorName);
        Doctor doctor = doctorMapper.selectOne(queryWrapper);
        if (doctor.getDoctorStatus() == 1) {
            doctor.setDoctorWork("请假");
        } else {
            doctor.setDoctorWork(doctor.getDoctorStartwork() + "-" + doctor.getDoctorEndwork());

        }
        ArrayList<Doctor> objects = new ArrayList<>();
        objects.add(doctor);
        return Result.success(objects);
    }
}

