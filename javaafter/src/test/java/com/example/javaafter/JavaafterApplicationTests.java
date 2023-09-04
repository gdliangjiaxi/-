package com.example.javaafter;

import com.example.javaafter.entity.Register;
import com.example.javaafter.mapper.RegisterMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JavaafterApplicationTests {

    @Resource
    private   RegisterMapper registerMapper;

    @Test
    void contextLoads() {
           registerMapper.getSickInfomation("ljx");

    }

}
