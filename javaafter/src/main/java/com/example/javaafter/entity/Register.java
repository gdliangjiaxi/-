package com.example.javaafter.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName("register")
public class Register {

    @TableId(value="register_id", type = IdType.AUTO)
    private Integer registerId;

    private String sickName;

    private  String doctorName;


    private Timestamp createTime;

    private Timestamp  endTime;

    private Timestamp  registerTime;
}
