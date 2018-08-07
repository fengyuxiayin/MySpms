package com.example.lzc.myspms.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.lzc.myspms.models.Constant;


/**
 * Created by LZC on 2017/9/8.
 */
public class GpsUtil {
    //打开Gps
    public static void openGps(final Activity activity) {
        Toast.makeText(activity, "请打开GPS", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage("请打开GPS");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        // 转到手机设置界面，用户设置GPS
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        activity.startActivityForResult(intent, 0); // 设置完成后返回到原来的界面

                    }
                });
        dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });
        dialog.show();
    }

    //判断Gps是否打开
    public static boolean checkGPSIsOpen(Context context) {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isOpen;
    }

    /**
     *
     *@desc
     *@param
     *@date 2018/3/28 9:16
    */
    public static boolean checkGpsPermission(Activity context){
        boolean havePermission;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // 没有权限，申请权限。
                        Toast.makeText(context, "没有权限", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, Constant.ACCESS_FINE_LOCATION_COARSE_LOCATION_REQUEST_CODE);
            havePermission = false;
        } else {
            // 有权限了，去放肆吧。
           havePermission = true;
        }
        return havePermission;
    }
    //打开Gps设置
//    @JavascriptInterface
    public static boolean openGPSSettings(final Activity context) {
        if (checkGPSIsOpen(context)) {
//            Toast.makeText(context, "Gps已开启", Toast.LENGTH_SHORT).show();
            boolean b = checkGpsPermission(context);
            return b;
        } else {
            //没有打开则弹出对话框
            new AlertDialog.Builder(context)
                    // 拒绝, 退出应用
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
                                    Toast.makeText(context, "需要开启定位功能，请稍后手动开启", Toast.LENGTH_SHORT).show();
                                }
                            })

                    .setPositiveButton("设置",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //跳转GPS设置界面
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    context.startActivityForResult(intent, Constant.GPS_REQUEST_CODE);
                                }
                            })
                    .setTitle("设置")
                    .setMessage("Gps未开启，是否前往设置")
                    .setCancelable(false)
                    .show();
            return false;
        }
    }
}
