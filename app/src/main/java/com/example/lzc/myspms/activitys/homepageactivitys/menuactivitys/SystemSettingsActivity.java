package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.LoginActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.GridAdapter;
import com.example.lzc.myspms.models.CheckInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.ClearCacheUtils;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.ServiceUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class SystemSettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SystemSettingsActivity.class.getSimpleName();

    private TextView cacheSize;
    private String totalCacheSize;
    private List<CheckInfoModel> mData = new ArrayList<>();
    private PopupWindow popupWindow;
    private EditText etOld;
    private EditText etNewFirst;
    private EditText etNewSecond;
    private String id;
    private View inflate;
    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private ImageView imgResetPwd;
    private ImageView imgClearCache;
    private ImageView imgLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_settings);
        inflate = View.inflate(this, R.layout.activity_system_settings,null);
        id = getIntent().getStringExtra("id");
        initView();
        initData();
        initListener();
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.avtivity_system_srttings_header).findViewById(R.id.title);
        tvTitle.setText("系统设置");
        imgBack = (ImageView) findViewById(R.id.avtivity_system_srttings_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.avtivity_system_srttings_header).findViewById(R.id.videocall);
        imgResetPwd = (ImageView) findViewById(R.id.activity_system_srttings_img_reset_password);
        imgClearCache = (ImageView) findViewById(R.id.activity_system_srttings_img_clear_cache);
        imgLogout = (ImageView) findViewById(R.id.activity_system_srttings_img_logout);
    }

    private void initData() {

    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgResetPwd.setOnClickListener(this);
        imgClearCache.setOnClickListener(this);
        imgLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
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
                                .addParams("id",id)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {
                                        Log.e(TAG, "onError: "+e.getMessage() );
                                        NetUtil.errorTip(SystemSettingsActivity.this,e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        Log.e(TAG, "onResponse: "+response );
                                        Gson gson = new Gson();
                                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                        if (infoModel.isData()) {
                                            Toast.makeText(SystemSettingsActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                            popupWindow.dismiss();
                                        }else{
                                            Toast.makeText(SystemSettingsActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    break;
                case R.id.popup_reset_pwd_btn_cancel:
                    popupWindow.dismiss();
                    break;
                case R.id.back:
                    finish();
                    break;
                case R.id.videocall:
                    Intent intent = new Intent();
                    intent.setClass(SystemSettingsActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
                case R.id.activity_system_srttings_img_reset_password:
                    View contentView = View.inflate(SystemSettingsActivity.this, R.layout.popup_reset_pwd, null);
                    popupWindow = new PopupWindow(contentView,getWindowManager().getDefaultDisplay().getWidth() / 2,ViewGroup.LayoutParams.WRAP_CONTENT,true);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
                    etOld = (EditText) contentView.findViewById(R.id.popup_reset_pwd_old);
                    etNewFirst = (EditText) contentView.findViewById(R.id.popup_reset_pwd_first);
                    etNewSecond = (EditText) contentView.findViewById(R.id.popup_reset_pwd_second);
                    Button btnCancel = (Button) contentView.findViewById(R.id.popup_reset_pwd_btn_cancel);
                    Button btnDetermine = (Button) contentView.findViewById(R.id.popup_reset_pwd_btn_determine);
                    btnCancel.setOnClickListener(SystemSettingsActivity.this);
                    btnDetermine.setOnClickListener(SystemSettingsActivity.this);
                    break;
                case R.id.activity_system_srttings_img_logout:
                    new AlertDialog.Builder(SystemSettingsActivity.this)
                            .setTitle("提示")
                            .setMessage("是否注销账号")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean serviceRunning = ServiceUtils.isServiceRunning(getApplicationContext(), "com.example.lzc.myspms.GpsService");
                                    if (serviceRunning) {
                                        ServiceUtils.stopMyService(getApplicationContext(), "com.example.lzc.myspms.GpsService");
                                    }
                                    startActivity(new Intent(SystemSettingsActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                }
                            })
                            .create().show();
                    break;
                case R.id.activity_system_srttings_img_clear_cache:
                    new AlertDialog.Builder(SystemSettingsActivity.this)
                            .setTitle("提示")
                            .setMessage("是否清除缓存")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ClearCacheUtils.cleanApplicationData(SystemSettingsActivity.this.getApplication().getApplicationContext());
                                    try {
//                                            totalCacheSize = ClearCacheUtils.getTotalCacheSize(getApplicationContext());
//                                            cacheSize.setText(totalCacheSize);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .create().show();
                    break;
            }
        }
    }
}
