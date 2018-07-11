package com.example.lzc.myspms.utils;

/**
 * Created by LZC on 2017/9/4.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class NetUtil {
    public static final String TAG = NetUtil.class.getSimpleName();

    /**
     * 判断网络情况
     *
     * @param context 上下文
     * @return false 表示没有网络 true 表示有网络
     */
    public static boolean isNetworkAvalible(Context context) {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();

            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 如果没有网络，则弹出网络设置对话框
    public static void checkNetwork(final Activity activity) {
        if (!NetUtil.isNetworkAvalible(activity)) {
            TextView msg = new TextView(activity);
            msg.setText("        当前没有可以使用的网络，请设置网络！");
            new AlertDialog.Builder(activity).setTitle("网络状态提示").setView(msg).setPositiveButton("连接wlan", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    // 跳转到设置界面
                    activity.startActivityForResult(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
                }
            })
                    .setNegativeButton("连接移动网络", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            activity.startActivityForResult(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS), 0);
                        }
                    })
                    .create().show();
        }
        return;
    }

    // 如果没有网络，则弹出网络设置对话框
    public static void changeMoileToWlan(final Activity activity) {

        TextView msg = new TextView(activity);
        msg.setText("当前使用的是数据流量,是否前往设置");
        new AlertDialog.Builder(activity).setTitle("网络状态提示").setView(msg).setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                // 跳转到设置界面
                activity.startActivityForResult(new Intent(Settings.ACTION_WIRELESS_SETTINGS), 0);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(activity, "请注意流量使用情况", Toast.LENGTH_SHORT).show();
            }
        }).create().show();
        return;
    }

    /**
     * 判断网络是否连接
     **/
    public static boolean netState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取代表联网状态的NetWorkInfo对象
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        // 获取当前的网络连接是否可用
        boolean available = false;
        try {
            available = networkInfo.isAvailable();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (available) {
            Log.i("通知", "当前的网络连接可用");
            return true;
        } else {
            Log.i("通知", "当前的网络连接可用");
            return false;
        }
    }

    /**
     * 在连接到网络基础之上,判断设备是否是SIM网络连接
     *
     * @param context
     * @return
     */
    public static boolean IsMobileNetConnect(Context context) {
        try {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            if (State.CONNECTED == state)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param errorMessage 服务器返回的错误信息
     * @param context      上下文对象
     * @desc 网络请求时的错误提示
     * @date 2018/3/15 14:42
     */
    public static void errorTip(Context context, String errorMessage) {
        Log.e(TAG, "errorTip: " + errorMessage);
        if (errorMessage.equals("timeout")) {
            Toast.makeText(context, "网络连接超时", Toast.LENGTH_SHORT).show();
        } else if (errorMessage.equals("Network is unreachable")) {
            Toast.makeText(context, "未连接网络", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "请求数据发生错误", Toast.LENGTH_SHORT).show();
        }
    }


}
