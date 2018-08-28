package com.example.lzc.myspms.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.lzc.myspms.GpsService;
import com.example.lzc.myspms.R;

import com.example.lzc.myspms.activitys.homepageactivitys.AddEnterpriseSimpleActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.NoticeActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.SendMessageActivity;
import com.example.lzc.myspms.adapters.ListAdapter;
import com.example.lzc.myspms.adapters.SimpleArrayAdapter;
import com.example.lzc.myspms.adapters.TeamAvChatAdapter;
import com.example.lzc.myspms.avchats.AVChatActivity;
import com.example.lzc.myspms.avchats.AVChatKit;
import com.example.lzc.myspms.avchats.AVChatProfile;
import com.example.lzc.myspms.avchats.AVChatTimeoutObserver;
import com.example.lzc.myspms.avchats.LogUtil;
import com.example.lzc.myspms.avchats.TeamAVChatActivity;
import com.example.lzc.myspms.avchats.TeamAVChatAdapter;
import com.example.lzc.myspms.avchats.TeamAVChatProfile;
import com.example.lzc.myspms.custom.MyGridView;
import com.example.lzc.myspms.fragments.CheckFragment;
import com.example.lzc.myspms.fragments.CheckProgressFragment;
import com.example.lzc.myspms.fragments.HomePageNewFragment;
import com.example.lzc.myspms.fragments.NewCheckFragment;
import com.example.lzc.myspms.fragments.PublishTaskFragment;
import com.example.lzc.myspms.fragments.ReCheckFragment;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.GroupModel;
import com.example.lzc.myspms.models.MyInfoModel;
import com.example.lzc.myspms.models.NeteaseAccountFindModel;
import com.example.lzc.myspms.models.NeteaseAccountModel;
import com.example.lzc.myspms.models.VersionControlModel;
import com.example.lzc.myspms.utils.GpsUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.PermissionUtil;
import com.example.lzc.myspms.utils.ServiceUtils;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.example.lzc.myspms.utils.ShowMenuPopup;
import com.example.lzc.myspms.utils.ShowPersonPopup;
import com.example.lzc.myspms.utils.TimeUtil;
import com.google.gson.Gson;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;

import com.netease.nimlib.sdk.avchat.AVChatCallback;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.model.AVChatChannelInfo;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.msg.model.CustomNotificationConfig;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import de.tavendo.autobahn.WebSocketOptions;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnTouchListener {
    private final String TAG = this.getClass().getSimpleName();
    private ImageView mImgBack;
    private TextView mTvTitle;
    private ImageView mImgVideoCall;
    private RadioGroup mRadioGroup;
    private Fragment mShowFragment;
    private RadioButton radioButtonHomepage;
    private RadioButton radioButtonCheck;
    private RadioButton radioButtonNewCheck;
    private RadioButton radioButtonCheckProgress;
    private RadioButton radioButtonRecheck;
    //判断popup是否创建
    private boolean popupMenuShow = false;
    //判断popup是否创建
    private boolean popupPersonShow = false;
    //判断双击退出的
    private boolean mIsExit;
    //内容观察者对象
    private ContentObserver mGPSMonitor;
    //位置服务对象
//    private LocationManager locationManager;
    //定时器
    private Timer timer;
    //判断是否是工作日的标志位
    private boolean isTimeToOpenGps;
    //一个实现了run方法的实现类对象
    private TimerTask mTimerTask;
    //经纬度
    private String longitude;
    private String latitude;
    //上传服务器的位置信息
    private String locationInfo;
    //声明AMapLocationClient类对象
    StringBuilder stringBuilder = new StringBuilder();
    private RelativeLayout relativeLayout;
    private View contentView;
    private PopupWindow popupWindow;
    private ListView listView;
    private ArrayList<MyInfoModel> data;
    private ListAdapter listAdapter;
    private String loginId;
    private String locationProvider;
    private LocationListener locationListener;
    private String loginType;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private Gson gson;
    //登录页面传过来的数据 用于小组管理的查询
    private String ssId;
    private GroupModel.GroupMsgModel groupMsgModel;
    //apk升级用到的字段
    private PackageInfo packageInfo;
    private int nativeVversionCode;
    private String downloadUrl;
    private String currentVersion;
    private int isMust;
    private String target;
    private WebSocketClient webSocketClient;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            webSocketClient.close();
        }
    };
    private int page = 1;
    private List<NeteaseAccountFindModel.NeteaseAccountFindMsgModel.ListBean> list = new ArrayList<>();
    private ImageView mImgSendMessage;
    private ImageView mImgNotice;
    private ImageView mImgCall;
    private ImageView mImgAdd;
    private Intent intentService;
    private String address = "wss://120.25.251.167:6445/WebSocket/globalWebSocket/";
    private URI uri;
    private WebSocketClient mWebSocketClient;
    private String uuId;
    Intent intent = new Intent();
    private LaunchTransaction transaction;
    private RadioButton radioButtonPublishTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginId = getIntent().getStringExtra("loginId");
        loginType = getIntent().getStringExtra("loginType");
        //如果gps已经是打开状态，那么直接就上传数据
        gpsIsOpenUploadDate();
        ssId = getIntent().getStringExtra("ssId");
        Log.e(TAG, "onCreate: " + "loginId " + loginId + " loginType " + loginType + " ssId " + ssId);
        gson = new Gson();
        uuId = java.util.UUID.randomUUID().toString();
        String replace = uuId.replace("-", "");
        address = address + replace + "/" + loginId + "/2";
        Log.e(TAG, "onCreate: address" + address);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initWebSocket();
            }
        }).start();
//        initWebSocket();
        //网易云信监听用户在线状态
        doLogin();
//        authServiceObserver();
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //判断应用是否需要更新
//        judgeApkNeedUpdate();
    }

    private void initWebSocket() {

        try {
            uri = new URI(address);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e(TAG, "initWebSocket: " + e.getMessage());
        }


        Log.e(TAG, "initWebSocket: " + address);

        Request request = null;
//        HttpUrl httpUrl = HttpUrl.Builder.;
        request = new Request.Builder()
                .url(address)
//                    .url("ws://120.25.251.167:68/")
//                    .url(new URL("http://120.25.251.167:68/WebSocket/globalWebSocket/"+uuId+"/"+loginId+"/2"))
                .build();
        ;

        OkHttpClient client = new OkHttpClient().newBuilder().retryOnConnectionFailure(true)
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public okhttp3.Response intercept(Chain chain) throws IOException {
//                        return null;
//                    }
//                })
                .build();
        WebSocket webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, okhttp3.Response response) {
                Log.e(TAG, "onOpen: ");
                super.onOpen(webSocket, response);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                Log.e(TAG, "onMessage: ");
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                Log.e(TAG, "onClosing: " + code + reason);
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                Log.e(TAG, "onClosed: " + code + reason);
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
                Log.e(TAG, "onFailure: " + t.getCause() + t.getCause() + response + webSocket.toString() + webSocket.request().url());
                super.onFailure(webSocket, t, response);
            }
        });
//        client.dispatcher().executorService().shutdown();


    }


    //设置okhttp信任所有证书
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
            Log.e("TrulyRandom", "createSSLSocketFactory: " + e.getMessage());
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * @desc 获取权限
     * @date 2018/4/9 18:47
     */
    private void permissionRequest() {
        String[] array = new String[2];
        array[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        array[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
        PermissionUtil.checkAndRequestMorePermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
    }

    private void judgeApkNeedUpdate() {
        //
        boolean canWrite = PermissionUtil.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        boolean canRead = PermissionUtil.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionRequest();
        if (canWrite && canWrite) {
        } else {
            return;
        }
        //获取本地版本号
        PackageManager packageManager = getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } finally {
            nativeVversionCode = packageInfo.versionCode;
        }
        // 获取服务器版本号
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/appVersion/findLatest")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(com.squareup.okhttp.Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /appVersion/findLatest" + response);
                        Gson gson = new Gson();
                        VersionControlModel versionControlModel = gson.fromJson(response, VersionControlModel.class);
                        if (versionControlModel.isData()) {
                            if (!"null".equals(versionControlModel.getMsg())) {
                                VersionControlModel.VersionControlMsgModel versionControlMsgModel = gson.fromJson(versionControlModel.getMsg(), VersionControlModel.VersionControlMsgModel.class);
                                downloadUrl = Constant.UPLOAD_IMG_IP + versionControlMsgModel.getDownloadUrl();
                                currentVersion = versionControlMsgModel.getVersionCode();
                                isMust = versionControlMsgModel.getIsMust();
                                if ((int) (Float.parseFloat(currentVersion)) > nativeVversionCode) {
//                            if (2 > nativeVversionCode) {
                                    if (isMust == 1) {
                                        Toast.makeText(MainActivity.this, "请安装最新的版本", Toast.LENGTH_SHORT).show();
                                        downLoadApp(downloadUrl);
                                    } else {
                                        new AlertDialog.Builder(MainActivity.this)
                                                .setTitle("版本提示")
                                                .setMessage("检测到新版本，是否更新？")
                                                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        downLoadApp(downloadUrl);
                                                    }
                                                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).create().show();
                                    }
                                }
                            }

                        }
                    }
                });
    }

    protected void downLoadApp(String downloadUrl) {
        target = Environment.getExternalStorageDirectory().getAbsolutePath() + "/spms.apk";
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("正在下载最新版本");
        dialog.setMessage("请稍候");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
        Log.e(TAG, "downLoadApp: " + downloadUrl);
        OkHttpUtils.get()
                .url(downloadUrl)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "/spms.apk") {
                    @Override
                    public void inProgress(float progress) {
                        Log.e(TAG, "inProgress: " + progress);
                        dialog.setProgress((int) (progress * 100));
                    }

                    @Override
                    public void onError(com.squareup.okhttp.Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(File response) {
                        Log.e(TAG, "onResponse: success");
                        dialog.dismiss();
                        installApk();
                    }
                });
    }

    protected void installApk() {
        Log.e(TAG, "installApk: ");
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("下载完成")
                .setMessage("是否立刻安装最新版本")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //安装app
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.addCategory("android.intent.category.DEFAULT");
                        Uri data = Uri.parse("file://" + target);
                        intent.setDataAndType(data, "application/vnd.android.package-archive");
                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isMust == 0) {
                            dialog.dismiss();
                        } else {
                            MainActivity.this.finish();
                        }
                    }
                }).show();
    }

    /**
     * @desc 设置网易云信对用户在线的监听
     * @date 2018/4/23 15:00
     */
    private void authServiceObserver() {
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(
                new Observer<StatusCode>() {
                    public void onEvent(StatusCode status) {
                        Log.e(TAG, "onEvent: " + status.getValue());
                        if (status.wontAutoLogin()) {
                            // 被踢出、账号被禁用、密码错误等情况，自动登录失败，需要返回到登录界面进行重新登录操作
                            Log.e(TAG, "onEvent: 网易云登录");

                            doLogin();
                        } else {
                            Log.e(TAG, "onEvent: 自动登录成功");

                        }
                    }
                }, true);
    }

    /**
     * @desc 网易云信的登录
     * @date 2018/4/23 14:36
     */
    public void doLogin() {
        Log.e(TAG, "doLogin: " + Constant.ACCOUNT_ID);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/neteaseAccount/findByAccount")
                .addParams("accountId", Constant.ACCOUNT_ID)
                .build()
                .execute(new StringCallback() {
                    private LoginInfo info;

                    @Override
                    public void onError(com.squareup.okhttp.Request request, Exception e) {
                        NetUtil.errorTip(MainActivity.this, e.getMessage() + "/neteaseAccount/findByAccount");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /neteaseAccount/findByAccount" + response);
                        NeteaseAccountModel neteaseAccountModel = gson.fromJson(response, NeteaseAccountModel.class);
                        Log.e(TAG, "onResponse: " + response);
                        if (neteaseAccountModel.isData()) {
                            final NeteaseAccountModel.NeteaseAccountMsgModel neteaseAccountMsgModel = gson.fromJson(neteaseAccountModel.getMsg(), NeteaseAccountModel.NeteaseAccountMsgModel.class);
                            info = new LoginInfo(neteaseAccountMsgModel.getAccid(), neteaseAccountMsgModel.getToken());
                            RequestCallback<LoginInfo> callback =
                                    new RequestCallback<LoginInfo>() {
                                        @Override
                                        public void onSuccess(LoginInfo param) {
                                            Log.e(TAG, "onSuccess: " + param.getAccount() + param.getToken());
                                            SharedPreferences sp = getApplicationContext().getSharedPreferences("WangYiYunXin", MODE_PRIVATE);
                                            SharedPreferences.Editor edit = sp.edit();
                                            edit.putString("account", neteaseAccountMsgModel.getAccid());
                                            edit.putString("token", neteaseAccountMsgModel.getToken());
                                            edit.commit();
                                            Constant.WANGYIYUN_SHOWNAME = neteaseAccountMsgModel.getAccid();
                                            Log.e(TAG, "onSuccess: " + neteaseAccountMsgModel.getAccid() + "token" + neteaseAccountMsgModel.getToken());
                                            AVChatKit.setAccount(neteaseAccountMsgModel.getAccid());
                                            //设置对群组通话的监听
                                            TeamAVChatProfile.sharedInstance().registerObserver(true);

                                        }

                                        @Override
                                        public void onFailed(int code) {
                                            Log.e(TAG, "onFailed: " + code);
                                        }

                                        @Override
                                        public void onException(Throwable exception) {

                                        }
                                        // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                                    };
                            NIMClient.getService(AuthService.class).login(info)
                                    .setCallback(callback);
                        }
                    }
                });
//
    }

    private void gpsIsOpenUploadDate() {
//        if (GpsUtil.openGPSSettings(this)) {
//            Log.e(TAG, "gpsIsOpenUploadDate: GPS一开始就是打开的");
        if (timer == null) {
            timer = new Timer();
            registerGpsListener();
        }
//        }
    }

    protected void initListener() {
        mImgBack.setOnTouchListener(this);
        mImgVideoCall.setOnTouchListener(this);
        mImgNotice.setOnTouchListener(this);
        mImgAdd.setOnTouchListener(this);
        mImgCall.setOnTouchListener(this);
//        mImgRelease.setOnClickListener(this);
//        mImgBack.setOnClickListener(this);
//        mImgVideoCall.setOnClickListener(this);
//        mImgNotice.setOnClickListener(this);
//        mImgAdd.setOnClickListener(this);
//        mImgCall.setOnClickListener(this);
        mImgSendMessage.setOnTouchListener(this);
        mRadioGroup.setOnCheckedChangeListener(this);


    }

    protected void initData() {
        //设置radiobutton的图片大小
        Drawable drawableHomepage = getResources().getDrawable(R.drawable.homepage_selecter);
        drawableHomepage.setBounds(0, 15, 50, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        radioButtonHomepage.setCompoundDrawables(null, drawableHomepage, null, null);

        Drawable drawableCheck = getResources().getDrawable(R.drawable.check_selecter);
        drawableCheck.setBounds(0, 15, 50, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        radioButtonCheck.setCompoundDrawables(null, drawableCheck, null, null);

        Drawable drawableNewCheck = getResources().getDrawable(R.drawable.newcheck_selecter);
        drawableNewCheck.setBounds(0, 15, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        radioButtonNewCheck.setCompoundDrawables(null, drawableNewCheck, null, null);

        Drawable drawableCheckProgress = getResources().getDrawable(R.drawable.check_progress_selecter);
        drawableCheckProgress.setBounds(0, 15, 50, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        radioButtonCheckProgress.setCompoundDrawables(null, drawableCheckProgress, null, null);

        Drawable drawableReCheck = getResources().getDrawable(R.drawable.recheck_selecter);
        drawableReCheck.setBounds(0, 15, 50, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        radioButtonRecheck.setCompoundDrawables(null, drawableReCheck, null, null);

        Drawable drawablePublishTask = getResources().getDrawable(R.drawable.publish_task_selecter);
        drawablePublishTask.setBounds(0, 15, 50, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        radioButtonPublishTask.setCompoundDrawables(null, drawablePublishTask, null, null);
        //显示第一个fragment
        mShowFragment = new HomePageNewFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_main, mShowFragment, HomePageNewFragment.TAG);
        transaction.show(mShowFragment);
        transaction.commit();
    }

    protected void initView() {
        relativeLayout = (RelativeLayout) findViewById(R.id.main_rl);
        mImgBack = (ImageView) findViewById(R.id.main_img_personal);
        mTvTitle = (TextView) findViewById(R.id.main_tv_title);
        mImgVideoCall = (ImageView) findViewById(R.id.main_img_videocall);
        mImgSendMessage = (ImageView) findViewById(R.id.main_iv_send_message);
        mImgNotice = (ImageView) findViewById(R.id.main_iv_message);
        mImgCall = (ImageView) findViewById(R.id.main_img_call);
        mImgAdd = (ImageView) findViewById(R.id.main_img_add);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);
        radioButtonHomepage = (RadioButton) findViewById(R.id.rb_main_homepage);
        radioButtonCheck = (RadioButton) findViewById(R.id.rb_main_check);
        radioButtonNewCheck = (RadioButton) findViewById(R.id.rb_main_new_check);
        radioButtonCheckProgress = (RadioButton) findViewById(R.id.rb_main_check_progress);
        radioButtonRecheck = (RadioButton) findViewById(R.id.rb_main_recheck);
        radioButtonPublishTask = (RadioButton) findViewById(R.id.rb_main_publish_task);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main_homepage:
                mTvTitle.setText("首页");
                switchPages(HomePageNewFragment.TAG, HomePageNewFragment.class);
                break;
            case R.id.rb_main_check:
                mTvTitle.setText("查询");
                switchPages(CheckFragment.TAG, CheckFragment.class);
                break;
            case R.id.rb_main_new_check:
                mTvTitle.setText("新的检查");
                switchPages(NewCheckFragment.TAG, NewCheckFragment.class);
                break;
            case R.id.rb_main_check_progress:
                mTvTitle.setText("检查进度");
                switchPages(CheckProgressFragment.TAG, CheckProgressFragment.class);
                break;
            case R.id.rb_main_recheck:
                mTvTitle.setText("复查流程");
                switchPages(ReCheckFragment.TAG, ReCheckFragment.class);
                break;
            case R.id.rb_main_publish_task:
                mTvTitle.setText("发布任务");
                switchPages(PublishTaskFragment.TAG, PublishTaskFragment.class);
                break;

        }
    }

    private void switchPages(String tag, Class<? extends Fragment> cls) {
        /**
         * 将当前显示的碎片进行隐藏，之后将要显示的页面显示出来
         */
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏显示的页面
        transaction.hide(mShowFragment);
        // 根据TAG去FragmentManager中进行查找碎片
        mShowFragment = fm.findFragmentByTag(tag);
        // 如果找到了，直接进行显示
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            // 如果没找到，添加到容器中并且设置了一个标记
            try {
                // 使用反射进行了一个对象的实例化
                mShowFragment = cls.getConstructor().newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.fl_main, mShowFragment, tag);
        }
        transaction.commit();
    }

    /**
     * @param
     * @desc 满足条件后（是否在工作时间段内）注册内容观察者（实现代码在initGps中）
     * @date 2017/11/28 9:48
     */
    private void registerGpsListener() {

        initGPS(true);
//        }
    }

    /**
     * @param timeToOpenGps 判断当前是否是工作日的标志位
     * @desc 定时器每隔1分钟向服务器发送handler, 在handler中获取当前的位置信息（每隔60s获取一次或者位置变化超过8m获取一次）
     * @date 2017/11/28 9:56
     */
    private void initGPS(boolean timeToOpenGps) {
        if (timeToOpenGps) {
//            mTimerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    //what = 1 向服务器发送位置信息
//                    Message msg = Message.obtain();
//                    msg.what = 1;
//                    handler.sendMessageDelayed(msg, 3000);
//                }
//            };
//            timer.schedule(mTimerTask, 0, 10000);
            //先判断服务是否开启
            boolean serviceRunning = ServiceUtils.isServiceRunning(getApplicationContext(), "com.example.lzc.myspms.GpsService");
//            boolean serviceRunning = ServiceUtils.isServiceRunning(getApplicationContext(), GpsService.class.getName());
            Log.e(TAG, "initGPS: " + serviceRunning);
            if (!serviceRunning) {
                intentService = new Intent(MainActivity.this, GpsService.class);
                intentService.putExtra("loginType", loginType);
                Log.e(TAG, "initGPS: " + longitude);
                startService(intentService);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        stopService(intentService);
    }

    @Override
    /**
     * 双击返回键退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.main_img_personal:
                Log.e(TAG, "onTouch: " + popupPersonShow);
                ShowPersonPopup showPersonPopup = new ShowPersonPopup(MainActivity.this, loginType, ssId, loginId, relativeLayout);
                if (!popupPersonShow) {
                    showPersonPopup.showPopup();
                }
                popupPersonShow = !popupPersonShow;
                break;
            case R.id.main_img_videocall:
                intent = new Intent();
//                intent.setClass(MainActivity.this, VideoCallActivity.class);
//                startActivity(intent);
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL + "/neteaseAccount/find")
                        .addParams("inUse", "1")
                        .addParams("pn", "1")
                        .addParams("size", "1000")
                        .build()
                        .execute(new StringCallback() {
                            private GridView gridView;
                            private ArrayAdapter<String> adapterLimit;
                            private Button btn;
                            private Spinner spinner;

                            @Override
                            public void onError(com.squareup.okhttp.Request request, Exception e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: " + response);
                                Gson gson = new Gson();
                                NeteaseAccountFindModel neteaseAccountFindModel = gson.fromJson(response, NeteaseAccountFindModel.class);
                                if (neteaseAccountFindModel.isData()) {
                                    NeteaseAccountFindModel.NeteaseAccountFindMsgModel neteaseAccountFindMsgModel = gson.fromJson(neteaseAccountFindModel.getMsg(), NeteaseAccountFindModel.NeteaseAccountFindMsgModel.class);
                                    final List<NeteaseAccountFindModel.NeteaseAccountFindMsgModel.ListBean> list = neteaseAccountFindMsgModel.getList();
                                    PopupWindow popupWindow = new PopupWindow();
                                    View contentView = View.inflate(MainActivity.this, R.layout.popup_videocall, null);
                                    gridView = (GridView) contentView.findViewById(R.id.popup_videocall_gv);
                                    btn = (Button) contentView.findViewById(R.id.popup_videocall_btn);
                                    List<EnumModel> limitList = new ArrayList<>();
                                    if (list != null) {
                                        for (int i = 0; i < list.size(); i++) {
                                            limitList.add(new EnumModel(list.get(i).getAccid() + "", list.get(i).getShowName()));
                                        }
                                    }
                                    final TeamAvChatAdapter teamAvChatAdapter = new TeamAvChatAdapter(limitList, MainActivity.this);
                                    gridView.setAdapter(teamAvChatAdapter);

//                                    spinner = (Spinner) contentView.findViewById(R.id.popup_videocall_sp);
//                                    adapterLimit = new ArrayAdapter<>(MainActivity.this, R.layout.textview_only, limitList);
//                                    adapterLimit.setDropDownViewResource(R.layout.spinner_item_textview);
//                                    spinner.setAdapter(adapterLimit);
//                                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                        @Override
//                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                            Constant.WANGYIYUN_ACCOUNT = list.get(position).getAccid();
//                                            Constant.WANGYIYUN_DISPLAYNAME = list.get(position).getName();
//                                        }
//
//                                        @Override
//                                        public void onNothingSelected(AdapterView<?> parent) {
//
//                                        }
//                                    });
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
//                                            intent.setClass(MainActivity.this, VideoCallActivity.class);
//                                            MainActivity.this.startActivity(intent);
                                            //创建房间
                                            if (NetUtil.isNetworkAvalible(MainActivity.this)) {
                                                ArrayList<String> accounts = new ArrayList<String>();
                                                accounts.add(AVChatKit.getAccount());
                                                List<String> strings = teamAvChatAdapter.returnCheckAccounts();
                                                accounts.addAll(strings);
                                                Log.e(TAG, "onResponse: " + accounts.size());
                                                startAudioVideoCall(AVChatType.VIDEO, accounts);
                                            } else {
                                                Toast.makeText(MainActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                                    popupWindow = new PopupWindow();
                                    popupWindow.setWidth((int) (MainActivity.this.getWindowManager().getDefaultDisplay().getWidth() * (0.5)));
                                    popupWindow.setHeight((int) (MainActivity.this.getWindowManager().getDefaultDisplay().getHeight() * 0.6));
                                    popupWindow.setContentView(contentView);
                                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                                    popupWindow.setOutsideTouchable(true);
                                    popupWindow.setFocusable(true);
                                    popupWindow.showAtLocation(mImgVideoCall, Gravity.CENTER, 0, 0);
                                }
                            }
                        });
//                Toast.makeText(this, "功能正在开发...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_iv_send_message:
                intent = new Intent();
                intent.putExtra("isSend", true);
                intent.putExtra("title", "发送邮件");
                intent.setClass(MainActivity.this, SendMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.main_iv_message:
                intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.main_img_call:
                Uri uri = Uri.parse("content://contacts/people");
                intent = new Intent(Intent.ACTION_PICK, uri);
                startActivity(intent);
                break;
            case R.id.main_img_add:
                intent = new Intent();
                intent.setClass(MainActivity.this, AddEnterpriseSimpleActivity.class);
                intent.putExtra("which", "add");
                startActivity(intent);
                break;

        }
        //设置menu的点击事件
//        private ImageView imgNotice;
//        private ImageView imgAdd;
//        private ImageView imgCall;
//        private ImageView imgMessage;
//
//        imgNotice.setOnClickListener(this);
//        imgAdd.setOnClickListener(this);
//        imgCall.setOnClickListener(this);
//        imgMessage.setOnClickListener(this);
//
//        imgNotice = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.notice);
//        imgMessage = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.message);
//        imgCall = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.call);
//        imgAdd = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.add);
//
//        SetMenuClick setMenuClick = new SetMenuClick(v.getId(), MainActivity.this,MainActivity.this);
//        setMenuClick.setMenuClick();
        return false;

    }

    public void startAudioVideoCall(AVChatType avChatType, final ArrayList<String> accounts) {

        if (AVChatProfile.getInstance().isAVChatting()) {
            Toast.makeText(MainActivity.this, "正在进行P2P视频通话，请先退出", Toast.LENGTH_SHORT).show();
            return;
        }
        if (accounts.size() < 2) {
            Toast.makeText(this, "人数少于两人，无法进行语音通话", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (TeamAVChatProfile.sharedInstance().isTeamAVChatting()) {
//            // 视频通话界面正在运行，singleTop所以直接调起来
//            Log.e(TAG, "startAudioVideoCall: 直接吊起来" );
//            Intent localIntent = new Intent();
//            localIntent.setClass(MainActivity.this, TeamAVChatActivity.class);
//            localIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            MainActivity.this.startActivity(localIntent);
//            return;
//        }

        if (transaction != null) {
            return;
        }
        transaction = new LaunchTransaction();
        final String roomName = UUID.randomUUID().toString().replaceAll("-", "");
        AVChatManager.getInstance().createRoom(roomName, null, new AVChatCallback<AVChatChannelInfo>() {
            @Override
            public void onSuccess(AVChatChannelInfo avChatChannelInfo) {
                LogUtil.ui("create room " + roomName + " success !");
//                向各个成员发送通知 不发通知收不到
                onCreateRoomSuccess(roomName, accounts);
                transaction.setRoomName(roomName);

                TeamAVChatProfile.sharedInstance().setTeamAVChatting(true);
                AVChatKit.outgoingTeamCall(MainActivity.this, false, transaction.getTeamID(), roomName, accounts, Constant.WANGYIYUN_SHOWNAME);
                transaction = null;
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(MainActivity.this, "创建房间失败,错误码：" + code, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onException(Throwable exception) {
                Toast.makeText(MainActivity.this, "创建房间失败" + exception.getMessage() + exception.getCause(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void onCreateRoomSuccess(String roomName, ArrayList<String> accounts) {
        // 对各个成员发送点对点自定义通知
        String teamName = Constant.WANGYIYUN_SHOWNAME;
        accounts.remove(0);
        String content = TeamAVChatProfile.sharedInstance().buildContent(roomName, "", accounts, teamName);
        CustomNotificationConfig config = new CustomNotificationConfig();
        config.enablePush = true;
        config.enablePushNick = false;
        config.enableUnreadCount = true;
        for (String account : accounts) {
            CustomNotification command = new CustomNotification();
            command.setSessionId(account);
            command.setSessionType(SessionTypeEnum.P2P);
            command.setConfig(config);
            command.setContent(content);
//            command.setApnsText(teamNick + getActivity().getString(R.string.t_avchat_push_content));

            command.setSendToOnlineUserOnly(false);
            NIMClient.getService(MsgService.class).sendCustomNotification(command);
        }
    }

    private class LaunchTransaction implements Serializable {
        private String teamID;
        private String roomName;

        public String getRoomName() {
            return roomName;
        }

        public String getTeamID() {
            return teamID;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public void setTeamID(String teamID) {
            this.teamID = teamID;
        }
    }
}
