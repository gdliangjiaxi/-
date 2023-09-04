package com.example.javaafter.util;

import cn.hutool.json.JSONUtil;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    private static final Date Date = new Date();


    public static void main(String[] args) {
        System.out.println(TimeUtil.getTime());
    }

    //获取当天的0点整
    public static Timestamp getZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        return timestamp;
    }

    //反格式化timesteamp返回给前端
    public static String setTime(Timestamp time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(time);
        return dateString;
    }


    //格式化成timesteamp
    public static Timestamp getTime() {
        return new Timestamp(Date.getTime());
    }

    //判断小时分钟是否在现在时间之后
    public  static  boolean CompareTime(String HM) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 将当前日期和时间合并
        String dateTimeString = dateFormat.format(Date) + " " + HM;
        DateFormat combinedDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date combinedDateTime = combinedDateFormat.parse(dateTimeString);
        if (Date.before(combinedDateTime)) {
            return true;
        }
            return false;
    }

    //判断日期是否大于今天
    public  static  boolean compareDate(Date date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        Date parse = dateFormat.parse(format);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date threeDaysLater = calendar.getTime();
        if (parse.after(Date)&&parse.before(threeDaysLater)) {
            return  true;
        }
                return false;
    }



}

