package com.example.javaafter.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sick {

    @TableId(value="sick_id", type = IdType.AUTO)
    private  Integer sickId;

    private  String sickName;

    private  String sickPassword;

    private  Integer sickAge;

    private  String sickSex;

    private  String sickPhone;

    private  String sickCondition;


    private  String sickXh;





}
