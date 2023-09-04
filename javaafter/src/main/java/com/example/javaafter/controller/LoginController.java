package com.example.javaafter.controller;


import com.example.javaafter.common.Result;
import com.example.javaafter.entity.Admin;
import com.example.javaafter.entity.Doctor;
import com.example.javaafter.entity.Sick;
import com.example.javaafter.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/sick")
    public Result loginsick(@RequestBody Sick sick) {
        return loginService.loginsick(sick);

    }

    @PostMapping("/doctor")
    public Result logindoctor(@RequestBody Doctor doctor) {
        return loginService.logindoctor(doctor);

    }

    @PostMapping("/admin")
    public Result loginadmin(@RequestBody Admin admin) {
        return loginService.loginadmin(admin);

    }


}
