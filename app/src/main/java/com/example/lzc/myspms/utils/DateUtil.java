package com.example.lzc.myspms.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LZC on 2017/12/6.
 */

public class DateUtil {
    public static final String TAG = DateUtil.class.getSimpleName();
    /**
     * @param time 需要转换的long
     * @desc 将long类型的时间转化为date类型 格式 2017-10-1
     * @date 2017/12/6 16:43
     */
    public static String long2Date(Long time) {
        String format = "";
        if (time!=null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            format = simpleDateFormat.format(new Date(time));
        }else{
            Log.e(TAG, "long2Date: 传进来的long类型的时间为null" );
        }
        return format;
    }
    /**
     * @param time 需要转换的long
     * @desc 将long类型的时间转化为date类型 格式 2017-10-1
     * @date 2017/12/6 16:43
     */
    public static String long2DateComplex(Long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = simpleDateFormat.format(new Date(time));
        return format;
    }
    /**
     *
     *@desc 获取当前时间 long类型
     *@date 2018/3/14 10:16
    */
    public static long getCurrentTimeLong(){
        return System.currentTimeMillis();
    }
    /**
     *
     *@desc 获取当前时间 yyyy-MM-dd 类型
     *@date 2018/3/14 10:16
     */
    public static String getCurrentTime(){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }
}
