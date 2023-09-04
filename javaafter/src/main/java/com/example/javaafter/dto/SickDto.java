package com.example.javaafter.dto;

import com.example.javaafter.entity.Sick;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SickDto {
    private  Integer sickId;

    private  String sickName;


    private  Integer sickAge;

    private  String sickSex;

    private  String sickPhone;

    private  String sickCondition;


    private  String sickXh;

    private String doctorName;

    private Timestamp createTime;

    private  String  doctorEndwork;

    private  Timestamp endTime;
}
