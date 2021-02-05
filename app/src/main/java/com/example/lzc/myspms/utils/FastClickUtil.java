package com.example.lzc.myspms.utils;

/**
 * Created by LZC on 2018/6/27.
 */

public class FastClickUtil {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime = 0;

    public static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
