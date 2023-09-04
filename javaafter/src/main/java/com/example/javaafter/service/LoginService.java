package com.example.javaafter.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.javaafter.common.Result;
import com.example.javaafter.entity.Admin;
import com.example.javaafter.entity.Doctor;
import com.example.javaafter.entity.Register;
import com.example.javaafter.entity.Sick;
import com.example.javaafter.mapper.AdminMapper;
import com.example.javaafter.mapper.DoctorMapper;
import com.example.javaafter.mapper.SickMapper;
import com.example.javaafter.util.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private SickMapper sickMapper;

    @Resource
    private AdminMapper adminMapper;

    public Result logindoctor(Doctor doctor) {
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Doctor::getDoctorName, doctor.getDoctorName()).eq(Doctor::getDoctorPassword, doctor.getDoctorPassword());
        Doctor userdoctor = doctorMapper.selectOne(queryWrapper);
        if (userdoctor == null) {
            return Result.error("1", "用户不存在");
        }
        Map<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("doctorName", userdoctor.getDoctorName());
        //生成token
        String token = JWTUtil.getToken(objectObjectHashMap);
        return Result.success(token);
    }

    //病人登录
    public Result loginsick(Sick sick) {
        LambdaQueryWrapper<Sick> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Sick::getSickXh, sick.getSickXh()).eq(Sick::getSickPassword, sick.getSickPassword());
        Sick sick1 = sickMapper.selectOne(queryWrapper);
        if (sick1 == null) {
            return Result.error("1", "用户不存在");
        }
        Map<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("sickXh", sick1.getSickXh());
        //生成token
        String token = JWTUtil.getToken(objectObjectHashMap);

        return Result.success(token);
    }
    //管理员登录
    public Result loginadmin(Admin admin) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminName, admin.getAdminName()).eq(Admin::getAdminPassword, admin.getAdminPassword());
        Admin admin1 = adminMapper.selectOne(queryWrapper);
        if (admin1 == null) {
            return Result.error("1", "用户不存在");
        }
        Map<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("adminName", admin1.getAdminName());
        //生成token
        String token = JWTUtil.getToken(objectObjectHashMap);
        return Result.success(token);

    }
}
