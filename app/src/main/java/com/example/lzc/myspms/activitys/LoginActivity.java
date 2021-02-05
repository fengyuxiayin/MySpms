package com.example.lzc.myspms.activitys;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.LoginInfoMsgModel;
import com.example.lzc.myspms.models.VersionControlModel;
import com.example.lzc.myspms.utils.FastClickUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.PermissionUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.login.LoginException;

public class
LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private EditText etUserName;
    private EditText etPassWord;
    private ImageView ivHidePassword;
    private TextView tvResetPassword;
    private Button btnLogin;
    private String userName;
    private String passWord;
    private LoginInfoModel loginInfoModel;
    private LoginInfoMsgModel loginInfoMsgModel;
    //判断双击退出的
    private boolean mIsExit;
    private View inflate;
    private PopupWindow popupWindow;
    private EditText etOld;
    private EditText etNewFirst;
    private EditText etNewSecond;
    private SharedPreferences sp;
    private Button btnFinger;
    private String downloadUrl;
    private String currentVersion;
    private int isMust;
    private PackageInfo packageInfo;
    private int nativeVversionCode;
    // 下载的apk的存储路径
    private String target;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkNetState();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        inflate = View.inflate(this, R.layout.activity_login,null);
        initView();
        setData();
        initListener();
    }



    private void initListener() {
        btnLogin.setOnClickListener(this);
        btnFinger.setOnClickListener(this);
        ivHidePassword.setOnClickListener(this);
        tvResetPassword.setOnClickListener(this);
    }

    private void setData() {
        etUserName.setSelection(etUserName.getText().length());
        etPassWord.setSelection(etPassWord.getText().length());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //检查网络
        checkNetState();
        //判断屏幕旋转是否打开
        judgeScreenRotation();
        //判断app是否需要更新
        judgeApkNeedUpdate();
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
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/appVersion/findLatest")
                .addParams("appKey","1")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(LoginActivity.this, e.getMessage() + "/appVersion/findLatest");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /appVersion/findLatest" + response);
                        Gson gson = new Gson();
                        VersionControlModel versionControlModel = gson.fromJson(response, VersionControlModel.class);
                        if (versionControlModel.isData()) {
                            if (!"null".equals(versionControlModel.getMsg())) {
                                VersionControlModel.VersionControlMsgModel versionControlMsgModel = gson.fromJson(versionControlModel.getMsg(), VersionControlModel.VersionControlMsgModel.class);
                                if (versionControlMsgModel.getAppType()==1) {
                                    downloadUrl = Constant.UPLOAD_IMG_IP + versionControlMsgModel.getDownloadUrl();
                                    currentVersion = versionControlMsgModel.getVersionCode();
                                    isMust = versionControlMsgModel.getIsMust();
                                    if ((int) (Float.parseFloat(currentVersion)) > nativeVversionCode) {
//                            if (2 > nativeVversionCode) {
                                        if (isMust == 1) {
                                            Toast.makeText(LoginActivity.this, "请安装最新的版本", Toast.LENGTH_SHORT).show();
                                            downLoadApp(downloadUrl);
                                        } else {
                                            new AlertDialog.Builder(LoginActivity.this)
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
                    }
                });
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
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: sdasdsad" + e.getMessage() + e.getCause());
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
        new AlertDialog.Builder(LoginActivity.this)
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
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isMust == 0) {
                            dialog.dismiss();
                        } else {
                            LoginActivity.this.finish();
                        }
                    }
                }).show();
    }
    /**
     *
     *@desc 判断屏幕旋转是否打开
     *@date 2018/5/14 11:18
    */
    private void judgeScreenRotation() {
        try {
            int screenchange = Settings.System.getInt(this.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION);
            if (screenchange==1) {

            }else{
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("提示")
                        .setMessage("检测到设备未开启自动旋转功能，请前往设置")
                        .setPositiveButton("前往设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                                startActivity(intent);
                            }
                        }).create().show();

            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.login_et_username);
        etPassWord = (EditText) findViewById(R.id.login_et_password);
        ivHidePassword = (ImageView) findViewById(R.id.login_iv_hide_password);
        tvResetPassword = (TextView) findViewById(R.id.login_tv_reset_password);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
        btnFinger = (Button) findViewById(R.id.login_btn_finger);
        //直接从sp中取值 如果有直接设置上
        sp = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
        String loginName = sp.getString("LoginName", "");
        String loginPwd = sp.getString("LoginPwd", "");
        etUserName.setText(loginName);
        etPassWord.setText(loginPwd);
    }
    private void checkNetState() {
        if (NetUtil.isNetworkAvalible(this)) {
            if (NetUtil.netState(this)) {
                if (NetUtil.IsMobileNetConnect(this)) {
                    Toast.makeText(LoginActivity.this, "正在使用数据流量，请注意流量使用情况！", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            NetUtil.checkNetwork(this);
        }
    }
    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.login_btn_login:
                    userName = etUserName.getText().toString().trim();
                    passWord = etPassWord.getText().toString().trim();
                    //验证密码
                    verifyPassword();
                    break;
                case R.id.login_btn_finger:
                    String pcgName = "com.android.settings";
                    String clsName = "com.android.settings.fingerprint.FingerprintSettingsActivity";
                    Intent intent = new Intent();
                    ComponentName componentName = new ComponentName(pcgName, clsName);
                    intent.setComponent(componentName);
                    intent.setAction(Intent.ACTION_VIEW);
                    startActivity(intent);judgeApkNeedUpdate();
                    break;
                case R.id.login_iv_hide_password:
                    int inputType = etPassWord.getInputType();
                    //设置EditText的inputtype 131073为none 18为numberPassword
                    if (inputType == 18) {
                        etPassWord.setInputType(131073);
                    } else {
                        etPassWord.setInputType(18);
                    }
                    break;
                case R.id.login_tv_reset_password:
                    View contentView = View.inflate(LoginActivity.this, R.layout.popup_reset_pwd, null);
                    popupWindow = new PopupWindow(contentView,getWindowManager().getDefaultDisplay().getWidth() / 2, ViewGroup.LayoutParams.WRAP_CONTENT,true);
//                        popupWindow.setFocusable(true);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
                    etOld = (EditText) contentView.findViewById(R.id.popup_reset_pwd_old);
                    etNewFirst = (EditText) contentView.findViewById(R.id.popup_reset_pwd_first);
                    etNewSecond = (EditText) contentView.findViewById(R.id.popup_reset_pwd_second);
                    Button btnCancel = (Button) contentView.findViewById(R.id.popup_reset_pwd_btn_cancel);
                    Button btnDetermine = (Button) contentView.findViewById(R.id.popup_reset_pwd_btn_determine);
                    btnCancel.setOnClickListener(LoginActivity.this);
                    btnDetermine.setOnClickListener(LoginActivity.this);
                    break;
                case R.id.popup_reset_pwd_btn_determine:
                    if (etOld.getText().length() < 1) {
                        Toast.makeText(this, "请输入旧密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (etNewFirst.getText().length() < 1) {
                        Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (etNewSecond.getText().length() < 1) {
                        Toast.makeText(this, "请确认新密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.e(TAG, "onClick: "+etNewFirst.getText()+"  "+etNewSecond.getText() );
                    if (!(etNewFirst.getText().toString().equals(etNewSecond.getText().toString()))) {
                        Toast.makeText(this, "两次输入密码不一致，请检查", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        OkHttpUtils.post()
                                .url(Constant.SERVER_URL+"/authAccount/resetPwd")
                                .addParams("oldPwd", String.valueOf(etOld.getText()))
                                .addParams("loginPwd", String.valueOf(etNewFirst.getText()))
                                .addParams("id",loginInfoMsgModel.getAccount().getId()+"")
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {
                                        Log.e(TAG, "onError: "+e.getMessage() );
                                        NetUtil.errorTip(LoginActivity.this,e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        Log.e(TAG, "onResponse: "+response );
                                        Gson gson = new Gson();
                                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                        if (infoModel.isData()) {
                                            Toast.makeText(LoginActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                            popupWindow.dismiss();
                                        }else{
                                            Toast.makeText(LoginActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    break;
                case R.id.popup_reset_pwd_btn_cancel:
                    popupWindow.dismiss();
                    break;
            }
        }
    }

    private void verifyPassword() {
        OkHttpUtils.getInstance().getOkHttpClient().setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        if (!FastClickUtil.isFastClick()) {
            Log.e(TAG, "verifyPassword: " );
//            OkGo.<String>post(Constant.SERVER_URL+"/mobile/login/doLogin")
////                    .upJson("{\"loginName\":\"18562825065\",\"loginPwd\":\"825065\"}")
//                    .params("loginName",userName)
//                    .params("loginPwd",passWord)
//                    .execute(new com.lzy.okgo.callback.StringCallback() {
//                        @Override
//                        public void onSuccess(Response<String> response) {
//                            final Gson gson = new Gson();
//                            loginInfoModel = gson.fromJson(response.body(), LoginInfoModel.class);
//                            Log.e(TAG, "onSuccess: "+gson.toJson(loginInfoModel) );
//                            boolean data = loginInfoModel.isData();
//                            if (data) {
//                                loginInfoMsgModel = gson.fromJson(loginInfoModel.getMsg(), LoginInfoMsgModel.class);
//                                Log.e(TAG, "onSuccess: "+gson.toJson(loginInfoMsgModel) );
//                                final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                            intent.putExtra("Msg", loginInfoMsgModel);
//                                intent.putExtra("loginId",loginInfoMsgModel.getAccount().getId()+"");
//                                intent.putExtra("loginType",loginInfoMsgModel.getAccount().getLoginType()+"");
//                                intent.putExtra("ssId",loginInfoMsgModel.getAccount().getSsId()+"");
//                                //获取企业id和账号id
//                                Constant.ENTERPRISE_ID = String.valueOf(loginInfoMsgModel.getAccount().getSsId()+"");
//                                Constant.ACCOUNT_ID = String.valueOf(loginInfoMsgModel.getAccount().getId());
//                                Constant.ACCOUNT_TYPE = String.valueOf(loginInfoMsgModel.getAccount().getLoginType());
//                                Constant.USER_NAME = loginInfoMsgModel.getUserName();
//                                Log.e(TAG, "onResponse: "+Constant.USER_NAME+"   "+loginInfoMsgModel.getUserName() );
//                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                                SharedPreferences.Editor edit = sp.edit();
//                                edit.putString("LoginName", userName);
//                                edit.putString("LoginPwd",passWord);
//                                edit.commit();
//                                startActivity(intent);
//                                LoginActivity.this.finish();
//                            } else {
//                                if ("密码错误".equals(loginInfoModel.getMsg())) {
//                                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                        @Override
//                        public void onError(Response<String> response) {
//                            super.onError(response);
//                            Log.e(TAG, "onError: "+response.getException() );
//                        }
//                    });
            OkHttpUtils.post()
                    .url(Constant.SERVER_URL + "/mobile/login/doLogin")
                    .addParams("loginName", userName)
                    .addParams("loginPwd", passWord)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onBefore(Request request) {
                        }

                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e(TAG, "onError: "+e.getMessage());
                            NetUtil.errorTip(LoginActivity.this,e.getMessage());
                        }

                        @Override
                        public void onResponse(String response) {
                            final Gson gson = new Gson();
                            Log.e(TAG, "onResponse: "+response);
                            loginInfoModel = gson.fromJson(response, LoginInfoModel.class);
                            if (loginInfoModel!=null) {
                                boolean data = loginInfoModel.isData();
                                if (data) {
                                    loginInfoMsgModel = gson.fromJson(loginInfoModel.getMsg(), LoginInfoMsgModel.class);
                                    final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    //                            intent.putExtra("Msg", loginInfoMsgModel);
                                    intent.putExtra("loginId",loginInfoMsgModel.getAccount().getId()+"");
                                    intent.putExtra("loginType",loginInfoMsgModel.getAccount().getLoginType()+"");
                                    intent.putExtra("ssId",loginInfoMsgModel.getAccount().getSsId()+"");
                                    //获取企业id和账号id
                                    Constant.ENTERPRISE_ID = String.valueOf(loginInfoMsgModel.getAccount().getSsId()+"");
                                    Constant.ACCOUNT_ID = String.valueOf(loginInfoMsgModel.getAccount().getId());
                                    Constant.ACCOUNT_TYPE = String.valueOf(loginInfoMsgModel.getAccount().getLoginType());
                                    Constant.USER_NAME = loginInfoMsgModel.getUserName();
                                    Log.e(TAG, "onResponse: "+Constant.USER_NAME+"   "+loginInfoMsgModel.getUserName() );
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putString("LoginName", userName);
                                    edit.putString("LoginPwd",passWord);
                                    edit.commit();
                                    startActivity(intent);
                                    LoginActivity.this.finish();
                                } else {
                                    if ("密码错误".equals(loginInfoModel.getMsg())) {
                                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                Toast.makeText(LoginActivity.this, "获取到的登录信息为空", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }else{
            Log.e(TAG, "onClick: 重复点击了登录");
        }

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
}
