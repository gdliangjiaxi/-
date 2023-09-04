package com.example.javaafter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.example.javaafter.mapper")
@SpringBootApplication
public class JavaafterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaafterApplication.class, args);
    }

}
