package com.example.lzc.myspms.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.example.lzc.myspms.GpsService;
import com.example.lzc.myspms.activitys.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2018/5/23.
 */

public class ServiceUtils {
    /**
     * 判断服务是否开启
     *
     * @return
     */
    public static boolean isServiceRunning(Context context, String ServiceName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
            if (serviceInfo.service.getClassName().equals(ServiceName)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 返回开启的服务对象
     *
     * @return
     */
    public static boolean stopMyService(Context context, String ServiceName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
            if (serviceInfo.service.getClassName().equals(ServiceName)) {
                ComponentName serviceCMP = serviceInfo.service;
                String serviceName = serviceCMP.getShortClassName(); // service 的类名
                String pkgName = serviceCMP.getPackageName();
                Intent intent = new Intent(context, GpsService.class);
//                intent.setComponent(serviceCMP);
                context.stopService(intent);
                return true;
            }
        }
        return false;
    }
}
