package com.example.lzc.myspms.utils;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 判断当前时间是否是工作日时间内
 * Created by LZC on 2017/9/6.
 */
public class TimeUtil {
    public static final String TAG = TimeUtil.class.getSimpleName();
    public static boolean isTimeToOpenGps(Context context){
        boolean b = judgDateInScope();
        int todayIsWeekday = isTodayIsWeekday(context);
        if (b&&1<todayIsWeekday&&todayIsWeekday<7) {
            return true;
        }
        return false;
    }
    public static boolean judgDateInScope() {
        SimpleDateFormat formatters = new SimpleDateFormat("HH:mm");
        Date curDates = new Date(System.currentTimeMillis());// 获取当前时间
        String strs = formatters.format(curDates);
        System.out.println(strs);
        //开始时间
        int beginHour = 8;//小时
        int beginMinute = 00;//秒
        //结束时间
        int endHour = 17;//小时
        int endMinute = 00;//秒

        String[] dds = new String[]{};

        // 分取系统时间 小时分
        dds = strs.split(":");
        int systemHour = Integer.parseInt(dds[0]);
        int systemMinute = Integer.parseInt(dds[1]);
        if (beginHour <= systemHour && systemHour < endHour) {
            Log.e(TAG, "时间范围内");
            return true;
        } else {
            Log.e(TAG, "不在时间范围内");
            return false;
        }
    }
    //是否是工作日
    public static int isTodayIsWeekday(Context context) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;// Java月份从0开始算
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int number = 0;
//获取指定年份月份中指定某天是星期几
        calendar.set(Calendar.DAY_OF_MONTH, day);  //指定日
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                Log.e(TAG, "isTodayIsWeekday: 今天是星期天");
                number = 1;
            break;

            case 2:
                Log.e(TAG, "isTodayIsWeekday:今天是星期一 ");
                number = 2;
                break;
            case 3:
                Log.e(TAG, "isTodayIsWeekday: 今天是星期二");
                number = 3;
            break;
            case 4:
                Log.e(TAG, "isTodayIsWeekday: 今天是星期三");
                number = 4;
            break;
            case 5:
                Log.e(TAG, "isTodayIsWeekday: 今天是星期四");
                number = 5;
            break;
            case 6:
                Log.e(TAG, "isTodayIsWeekday: 今天是星期五");
                number = 6;
            break;
            case 7:
                Log.e(TAG, "isTodayIsWeekday: 今天是星期六");
                number = 7;
            break;

        }
        return number;
    }

}
