package com.example.javaafter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javaafter.dto.DoctorDto;
import com.example.javaafter.dto.SickDto;
import com.example.javaafter.entity.Register;
import com.example.javaafter.entity.Sick;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;


@Mapper
public interface RegisterMapper extends BaseMapper<Register> {

    //获取登录医生的所有病人对象
    List<SickDto> getSickInfomation(String doctorName);

    //获取该病人今天的预约医生
    String getDoctorInfomation(String sickName);


     Integer updateAppointment(String sickName, String doctorName, Timestamp endTime);

    //获取登录病人的所有医生对象
    List<DoctorDto> getDoctorRecordInfomation(String sickName);

}
