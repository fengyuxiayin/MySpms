package com.example.lzc.myspms.utils;

/**
 * Created by LZC on 2018/6/27.
 */

public class FastClickUtil {
    private static final int MIN_CLICK_DELAY_TIME = 3000;
    private static long lastClickTime = 0;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if (lastClickTime==0) {
            flag = true;
        }else{
            if((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                flag = true;
            }
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
