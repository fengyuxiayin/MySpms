package com.example.lzc.myspms;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.avchats.AVChatKit;
import com.example.lzc.myspms.avchats.AVChatOptions;
import com.example.lzc.myspms.avchats.IUserInfoProvider;
import com.example.lzc.myspms.bean.NoticeInfo;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import com.netease.nimlib.sdk.uinfo.model.UserInfo;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;
import okio.Buffer;

/**
 * Created by LZC on 2017/10/25.
 */
public class MyApp extends Application {
    public static int width;
    public static int height;
    private static final String TAG = "MyApp";
//    private static String CER_SPMS = "-----BEGIN CERTIFICATE-----\n" +
//            "MIICbTCCAdYCCQDTHAFZHX6vRzANBgkqhkiG9w0BAQUFADB7MQswCQYDVQQGEwJD\n" +
//            "TjELMAkGA1UECAwCU0QxCzAJBgNVBAcMAlFEMQ0wCwYDVQQKDARMSEtEMQ0wCwYD\n" +
//            "VQQLDARMSEtEMQ0wCwYDVQQDDARzcG1zMSUwIwYJKoZIhvcNAQkBFhZhZG1pbkBs\n" +
//            "b25naHVha2VkYW8uY29tMB4XDTE3MDkwODA3MjA1N1oXDTE4MDkwODA3MjA1N1ow\n" +
//            "ezELMAkGA1UEBhMCQ04xCzAJBgNVBAgMAlNEMQswCQYDVQQHDAJRRDENMAsGA1UE\n" +
//            "CgwETEhLRDENMAsGA1UECwwETEhLRDENMAsGA1UEAwwEc3BtczElMCMGCSqGSIb3\n" +
//            "DQEJARYWYWRtaW5AbG9uZ2h1YWtlZGFvLmNvbTCBnzANBgkqhkiG9w0BAQEFAAOB\n" +
//            "jQAwgYkCgYEAra2srTgVgUI9VwXmpK/W6Qj0DY0GWeGHexbDqwf+3cxI9qmE+8Q/\n" +
//            "SITvp1u8dxvMtlsYi3cvOQGmdV54uYc6NYyFEdT6jW31LjKfGNSjiwe8wa5m+XaJ\n" +
//            "Lf25FhZWQ/nM23jVttr2ucTNZHq2NPYtwL1QQ1OSxHZbMpJNNCSKU70CAwEAATAN\n" +
//            "BgkqhkiG9w0BAQUFAAOBgQAF9RGWaxtIHeZHajBk9w4aACrtpaJnnwDHmo1IohXd\n" +
//            "eGJcCEmnijVrC0JQRuJUBMLxMwwhj/D36gQqoX+v0IGZR8VuiKH1YFpwH4YHNJf9\n" +
//            "mEzqlkbTpSc+1XwlBppRPGTAZlgnLftzmwLP+ySSnvV2btyl7G8KWLHatrjaqJ5S\n" +
//            "wA==\n" +
//            "-----END CERTIFICATE-----";
//private String CER_SPMS = "-----BEGIN CERTIFICATE-----\n" +
//        "MIICbTCCAdYCCQDTHAFZHX6vRzANBgkqhkiG9w0BAQUFADB7MQswCQYDVQQGEwJD\n" +
//        "TjELMAkGA1UECAwCU0QxCzAJBgNVBAcMAlFEMQ0wCwYDVQQKDARMSEtEMQ0wCwYD\n" +
//        "VQQLDARMSEtEMQ0wCwYDVQQDDARzcG1zMSUwIwYJKoZIhvcNAQkBFhZhZG1pbkBs\n" +
//        "b25naHVha2VkYW8uY29tMB4XDTE3MDkwODA3MjA1N1oXDTE4MDkwODA3MjA1N1ow\n" +
//        "ezELMAkGA1UEBhMCQ04xCzAJBgNVBAgMAlNEMQswCQYDVQQHDAJRRDENMAsGA1UE\n" +
//        "CgwETEhLRDENMAsGA1UECwwETEhLRDENMAsGA1UEAwwEc3BtczElMCMGCSqGSIb3\n" +
//        "DQEJARYWYWRtaW5AbG9uZ2h1YWtlZGFvLmNvbTCBnzANBgkqhkiG9w0BAQEFAAOB\n" +
//        "jQAwgYkCgYEAra2srTgVgUI9VwXmpK/W6Qj0DY0GWeGHexbDqwf+3cxI9qmE+8Q/\n" +
//        "SITvp1u8dxvMtlsYi3cvOQGmdV54uYc6NYyFEdT6jW31LjKfGNSjiwe8wa5m+XaJ\n" +
//        "Lf25FhZWQ/nM23jVttr2ucTNZHq2NPYtwL1QQ1OSxHZbMpJNNCSKU70CAwEAATAN\n" +
//        "BgkqhkiG9w0BAQUFAAOBgQAF9RGWaxtIHeZHajBk9w4aACrtpaJnnwDHmo1IohXd\n" +
//        "eGJcCEmnijVrC0JQRuJUBMLxMwwhj/D36gQqoX+v0IGZR8VuiKH1YFpwH4YHNJf9\n" +
//        "mEzqlkbTpSc+1XwlBppRPGTAZlgnLftzmwLP+ySSnvV2btyl7G8KWLHatrjaqJ5S\n" +
//        "wA==\n" +
//        "-----END CERTIFICATE-----";
//    private String CER_SPMS = "-----BEGIN CERTIFICATE-----\n" +
//            "MIICbTCCAdYCCQDTHAFZHX6vRzANBgkqhkiG9w0BAQUFADB7MQswCQYDVQQGEwJD\n" +
//            "TjELMAkGA1UECAwCU0QxCzAJBgNVBAcMAlFEMQ0wCwYDVQQKDARMSEtEMQ0wCwYD\n" +
//            "VQQLDARMSEtEMQ0wCwYDVQQDDARzcG1zMSUwIwYJKoZIhvcNAQkBFhZhZG1pbkBs\n" +
//            "b25naHVha2VkYW8uY29tMB4XDTE3MDkwODA3MjA1N1oXDTE4MDkwODA3MjA1N1ow\n" +
//            "ezELMAkGA1UEBhMCQ04xCzAJBgNVBAgMAlNEMQswCQYDVQQHDAJRRDENMAsGA1UE\n" +
//            "CgwETEhLRDENMAsGA1UECwwETEhLRDENMAsGA1UEAwwEc3BtczElMCMGCSqGSIb3\n" +
//            "DQEJARYWYWRtaW5AbG9uZ2h1YWtlZGFvLmNvbTCBnzANBgkqhkiG9w0BAQEFAAOB\n" +
//            "jQAwgYkCgYEAra2srTgVgUI9VwXmpK/W6Qj0DY0GWeGHexbDqwf+3cxI9qmE+8Q/\n" +
//            "SITvp1u8dxvMtlsYi3cvOQGmdV54uYc6NYyFEdT6jW31LjKfGNSjiwe8wa5m+XaJ\n" +
//            "Lf25FhZWQ/nM23jVttr2ucTNZHq2NPYtwL1QQ1OSxHZbMpJNNCSKU70CAwEAATAN\n" +
//            "BgkqhkiG9w0BAQUFAAOBgQAF9RGWaxtIHeZHajBk9w4aACrtpaJnnwDHmo1IohXd\n" +
//            "eGJcCEmnijVrC0JQRuJUBMLxMwwhj/D36gQqoX+v0IGZR8VuiKH1YFpwH4YHNJf9\n" +
//            "mEzqlkbTpSc+1XwlBppRPGTAZlgnLftzmwLP+ySSnvV2btyl7G8KWLHatrjaqJ5S\n" +
//            "wA==\n" +
//            "-----END CERTIFICATE-----";
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        Configuration.Builder builder = new Configuration.Builder(this);
        //手动的添加模型类
        builder.addModelClasses(NoticeInfo.class);
        ActiveAndroid.initialize(builder.create());
        //设置信任自签名证书
        OkHttpUtils.getInstance().getOkHttpClient().setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        OkHttpUtils.getInstance().getOkHttpClient().setConnectTimeout(300000,TimeUnit.MILLISECONDS);
        OkHttpUtils.getInstance().getOkHttpClient().setReadTimeout(300000,TimeUnit.MILLISECONDS);
        StrictMode.VmPolicy.Builder builder1 = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder1.build());
        builder1.detectFileUriExposure();
        //OkHttpUtils 设置信任自签名证书
//        OkHttpUtils
//                .getInstance()
//                .setCertificates(new Buffer()
//                        .writeUtf8(CER_SPMS)
//                        .inputStream());
        //OkGo初始化
        OkHttpClient.Builder okgoBulider = new OkHttpClient.Builder();
        //方法一：信任所有证书,不安全有风险
//        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
//        okgoBulider.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        okgoBulider.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
//        HttpHeaders headers = new HttpHeaders();
//        HttpParams params = new HttpParams();
//        OkGo.getInstance().init(this).setOkHttpClient(okgoBulider.build()).addCommonHeaders(headers).addCommonParams(params).setRetryCount(3);
        OkGo.getInstance().init(this);
        //极光推送的初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //网易云信设置上下文对象
        AVChatKit.setContext(this);
        //网易云信的初始化
        NIMClient.init(this, loginInfo(), options());
        if (NIMUtil.isMainProcess(this)) {
            // 初始化音视频模块
            initAVChatKit();
        }
        initBugly();

    }
    private void initBugly() {
        //        CrashReport.initCrashReport(getApplicationContext(), "5fc8465b1e", false);
        Context context = getApplicationContext();
        String packageName = context.getPackageName();
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, "87cb2bc402", false, strategy);
    }
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
    private void initAVChatKit() {
        AVChatOptions avChatOptions = new AVChatOptions() {
            @Override
            public void logout(Context context) {

            }
        };
        AVChatKit.init(avChatOptions);

        // 初始化日志系统
//        LogHelper.init();
//        // 设置用户相关资料提供者
//        AVChatKit.setUserInfoProvider(new IUserInfoProvider() {
//            @Override
//            public UserInfo getUserInfo(String account) {
//                return NimUIKit.getUserInfoProvider().getUserInfo(account);
//            }
//
//            @Override
//            public String getUserDisplayName(String account) {
//                return UserInfoHelper.getUserDisplayName(account);
//            }
//        });
    }
    private SDKOptions options() {
        SDKOptions options = new SDKOptions();
        return options;
    }

    private LoginInfo loginInfo() {
        //如果sharedPreferences里面没有存储登录信息 那么返回null 否则返回登录的信息
//        SharedPreferences sp = getSharedPreferences("WangYiYunXin", MODE_PRIVATE);
//        String account = sp.getString("account", null);
//        String token = sp.getString("token", null);
//        if (account == null) {
//            return null;
//        } else {
//            LoginInfo loginInfo = new LoginInfo("123123","6bbde2f10fbb7fc013da1d507051fb6f" );
//            return loginInfo;
//        }
        return null;
    }

}
