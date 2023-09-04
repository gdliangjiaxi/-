package com.example.javaafter.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaafter.common.Result;
import com.example.javaafter.entity.Doctor;
import com.example.javaafter.mapper.DoctorMapper;
import com.example.javaafter.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @Resource
    DoctorMapper doctorMapper;

    @PostMapping
    public Result<?> save(@RequestBody Doctor user) {
        doctorMapper.insert(user);
        return Result.success();
    }

    @GetMapping("/information")
    public Result information() {
        return doctorService.getInformation();
    }

    @PutMapping
    public Result<?> update(@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable String id) {
        doctorMapper.deleteById(id);
        return Result.success();
    }


    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<Doctor> wrapper = Wrappers.<Doctor>lambdaQuery();
        if (StrUtil.isAllNotBlank(search)) {
            wrapper.like(Doctor::getDoctorName, search);
        }

        Page<Doctor> userPage = doctorMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        for (Doctor record : userPage.getRecords()) {
            if (record.getDoctorStatus() == 1) {
                record.setDoctorWork("请假");
            } else {
                record.setDoctorWork(record.getDoctorStartwork() + "-" + record.getDoctorEndwork());
                //查询医生预约人数
                Integer people = doctorService.queryRegisterPerson(record.getDoctorName());
                record.setDoctorRegisterperson(people);
            }
        }
        return Result.success(userPage);
    }
}
