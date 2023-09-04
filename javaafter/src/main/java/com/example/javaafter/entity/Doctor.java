package com.example.javaafter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@TableName("doctor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {

    @TableId(value="doctor_id", type = IdType.AUTO)
    private  Integer doctorId;

    private  String doctorName;

    private  String doctorSex;

    //部门
    private  String doctorDepartment;

    //特征
    private  String doctorFeature;

    private  Integer doctorAge;

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
