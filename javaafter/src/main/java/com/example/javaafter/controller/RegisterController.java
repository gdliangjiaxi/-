package com.example.javaafter.controller;


import com.example.javaafter.common.Result;
import com.example.javaafter.dto.DoctorDto;
import com.example.javaafter.dto.SickDto;
import com.example.javaafter.entity.Doctor;
import com.example.javaafter.entity.Register;
import com.example.javaafter.service.RegisterServeice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


@RequestMapping("/register")
@RestController
public class RegisterController {


    @Resource
    private RegisterServeice registerServeice;

    @GetMapping("information")
    public Result information() {
        return registerServeice.getInformation();
    }

    //病人预约医生
    @PostMapping("appointment")
    public Result appointment(@RequestBody DoctorDto doctor) throws ParseException {
        return registerServeice.appointment(doctor);

    }

    //医生完成就诊
    @PutMapping("complete/{sickName}")
    public Result complete(@PathVariable String sickName) {
        return registerServeice.completeVisit(sickName);

    }

    //病人取消预约
    @DeleteMapping("cancel/{doctorName}")
    public Result deleteAppointment(@PathVariable String doctorName) {
        return registerServeice.deleteAppointment(doctorName);

    }

    //获取医生的所有病人预约记录
    @GetMapping("doctorRecord")
    public Result doctorRecord() {
        return registerServeice.getdoctorRecord();

    }

    //获取病人的所有医生预约记录
    @GetMapping("sickRecord")
    public Result sickRecord() {
        return registerServeice.getsickRecord();

    }
}
