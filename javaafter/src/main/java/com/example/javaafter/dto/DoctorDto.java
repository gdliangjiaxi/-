package com.example.javaafter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private  Integer doctorId;

    private  String doctorName;

    private  Integer doctorAge;

    private  String doctorSex;

    private Timestamp createTime;

    private  Timestamp endTime;

    private  String doctorDepartment;

    private Date date;

    //特征
    private  String doctorFeature;


    //工作时间
    private String doctorStartwork;

    private String doctorEndwork;

    private String doctorWork;

    private Integer doctorPassword;

    //工作状态 1为请假
    private  Integer doctorStatus;

    //照片地址
    private  String imgaddress;

    private  Integer doctorRegisterperson;
}
