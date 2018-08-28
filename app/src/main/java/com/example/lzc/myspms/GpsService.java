package com.example.lzc.myspms;

import android.*;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.GpsUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.ObjectUtils;
import com.example.lzc.myspms.utils.ServiceUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by LZC on 2018/5/22.
 */

public class GpsService extends Service {
    public static final String TAG = GpsService.class.getSimpleName();
    private String longitude = "";
    private String latitude = "";
    private Gson gson = new Gson();
    private boolean initialization = false;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    longitude = String.valueOf(aMapLocation.getLongitude());
                    latitude = String.valueOf(aMapLocation.getLatitude());
                    if (longitude.length() > 10) {
                        longitude = longitude.substring(0, 9);
                    }
                    if (latitude.length() > 10) {
                        latitude = latitude.substring(0, 9);
                    }
                    Constant.LOCATION_INFO = longitude + "," + latitude;
                    //定位成功回调信息，设置相关消息
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    aMapLocation.getLatitude();//获取纬度
                    aMapLocation.getLongitude();//获取经度
                    aMapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    df.format(date);//定位时间
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    private String locationInfo;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //获取位置信息
                    if (!initialization) {
                        getLocation();
                    }
                    locationInfo = "{\"jd\":" + longitude + ",\"wd\":" + latitude + "}";
                    //通过https验证 允许自签名证书
                    OkHttpUtils.getInstance().getOkHttpClient().setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
                    //上传数据到服务器
                    if (gpsIsOpen) {//如果gps打开就上传位置到服务器
                        if (longitude == null) {
                            Toast.makeText(getApplicationContext(), "正在获取位置信息，请稍候", Toast.LENGTH_SHORT).show();
                        } else {
                            Constant.LOCATION_INFO = longitude + "," + latitude;
                            Log.e(TAG, "handler: " + Constant.LOCATION_INFO.length());
                            Log.e(TAG, "handleMessage: " + loginType + " " + longitude + " " + latitude);
                            if (Constant.LOCATION_INFO.length() > 2) {
                                OkHttpUtils.post().url(Constant.SERVER_URL + "/baseStaffPolyline/save")
                                        .addParams("zhId", Constant.ACCOUNT_ID)
                                        .addParams("zhlx", loginType)
                                        .addParams("jd", longitude)
                                        .addParams("wd", latitude)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Request request, Exception e) {
                                                Log.e(TAG, "onError: " + e.getMessage() + "存坐标");
                                            }

                                            @Override
                                            public void onResponse(String response) {
                                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                                if (!infoModel.isData()) {
                                                    if (infoModel.getMsg().equals("登录失效")) {
                                                        boolean serviceRunning = ServiceUtils.isServiceRunning(getApplicationContext(), "com.example.lzc.myspms.GpsService");
                                                        if (serviceRunning) {
                                                            ServiceUtils.stopMyService(getApplicationContext(), "com.example.lzc.myspms.GpsService");
                                                        }
                                                    }
                                                }
                                            }
                                        });
                            } else {
                                Log.e(TAG, "handleMessage: 还没有获取到正确的坐标点");
                            }
                        }
                    }else{
                        Log.e(TAG, "handleMessage: gps被手动关闭了" );
                    }

//                    Log.e(TAG, "handleMessage: 测试死机" );
                    break;
                default:
                    break;
            }
        }
    };
    private Timer timer;
    private TimerTask mTimerTask;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private String loginType;
    private Notification notification;
    private PowerManager pm;
    private PowerManager.WakeLock wakeLock;
    private LocationManager locationManager;
    private boolean gpsIsOpen = true;
    private final ContentObserver mGpsMonitor = new ContentObserver(null) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            boolean enabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (enabled) {
                gpsIsOpen = true;
            }else{
                gpsIsOpen = false;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        timer = new Timer();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        getContentResolver()
                .registerContentObserver(
                        Settings.Secure
                                .getUriFor(Settings.System.LOCATION_PROVIDERS_ALLOWED), false, mGpsMonitor);
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        //创建PowerManager对象
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //保持cpu一直运行，不管屏幕是否黑屏
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "CPUKeepRunning");
        wakeLock.acquire();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        loginType = intent.getStringExtra("loginType");
        notification = new Notification.Builder(this.getApplicationContext())
                .setContentText("前台服务")
                .setSmallIcon(R.mipmap.icon)
                .setWhen(System.currentTimeMillis())
                .build();
        startForeground(110, notification);
        initGPS(true);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mGpsMonitor);
        Log.e(TAG, "onDestroy: ");
        if (wakeLock != null) {
            wakeLock.release();
        }
        if (handler != null) {
            timer.cancel();
//            handler.getLooper().quitSafely();
        }
    }

    /**
     * @param timeToOpenGps 判断当前是否是工作日的标志位
     * @desc 定时器每隔1分钟向服务器发送handler, 在handler中获取当前的位置信息（每隔60s获取一次或者位置变化超过8m获取一次）
     * @date 2017/11/28 9:56
     */
    private void initGPS(boolean timeToOpenGps) {
        if (timeToOpenGps) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    //what = 1 向服务器发送位置信息
                    Message msg = Message.obtain();
                    msg.what = 1;
                    handler.sendMessageDelayed(msg, 3000);
                }
            };
            timer.schedule(mTimerTask, 0, 2000);
        }
    }

    /**
     * @param
     * @desc 获取location 并在特定条件下更新location
     * @date 2017/11/28 10:12
     */
    private void getLocation() {
        initialization = true;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "没有获取位置权限", Toast.LENGTH_SHORT).show();
            return;
        }
        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(mLocationListener);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();
    }
}
