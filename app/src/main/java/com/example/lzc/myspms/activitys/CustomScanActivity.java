package com.example.lzc.myspms.activitys;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class CustomScanActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private CaptureManager captureManager;     //捕获管理器
    private DecoratedBarcodeView dbv;
    private Button btnFlash;
    private Button btnCancel;
    private boolean isOpen = false;
    private Camera.Parameters params;
    private String TAG = CustomScanActivity.class.getSimpleName();
    private ZBarView zBarView;
    private TextView tvContent;
    private Button btnCreate;
    private String qymc = "";
    private String zch = "";
    private String fddbr = "";
    private String zcdz = "";
    private String tze = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scan);
        initView();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "开始识别", Toast.LENGTH_SHORT).show();
        zBarView.startCamera();
        zBarView.startSpot();
    }

    @Override
    protected void onStop() {
        zBarView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zBarView.onDestroy(); /// 销毁二维码扫描控件
        super.onDestroy();
    }

    private void initListener() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvContent.getText().toString() != null && tvContent.getText().toString().length() > 0) {
                    Log.e(TAG, "onClick: " + tvContent.getText().toString());
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL + "/baseEnterprise/save")
                            .addParams("qymc", qymc)
                            .addParams("zch", zch)
                            .addParams("fddbr", fddbr)
                            .addParams("zcdz", zcdz)
                            .addParams("tze", tze)
                            .addParams("qyzt", "1")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    Gson gson = new Gson();
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    Toast.makeText(CustomScanActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });

                } else {
                    Toast.makeText(CustomScanActivity.this, "还未获取二维码扫描内容", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        tvContent = (TextView) findViewById(R.id.activity_custom_scan_content);
        btnCreate = (Button) findViewById(R.id.activity_custom_scan_btn_create);
        zBarView = (ZBarView) findViewById(R.id.zbarview);
        zBarView.setDelegate(this);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, "扫描成功", Toast.LENGTH_SHORT).show();
        if (result.contains("http")) {
            OkHttpUtils.get()
                    .url(result)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Toast.makeText(CustomScanActivity.this, "工商管理局网站出错", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: " + response);
                            String[] split = response.split("<br>");
                            Log.e(TAG, "onResponse: "+split[0] );
                            qymc = split[2].substring(3);
                            zch = split[0].substring(split[0].length()-18);
                            fddbr = split[1].substring(6);
                            tvContent.setText("名称："+qymc+"法定代表人："+fddbr+"\n"+"统一社会信用代码："+zch);
                        }
                    });
        } else {
            String[] split = tvContent.getText().toString().split("\\n");
            qymc = split[2].substring(3);
            zch = split[0].substring(9);
            fddbr = split[4].substring(6);
            zcdz = split[6].substring(3);
            tze = split[5].substring(5, split[5].length() - 2);
            tvContent.setText(result);
        }
        zBarView.startSpot(); //
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
//        Toast.makeText(this, "环境昏暗", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "相机出错", Toast.LENGTH_SHORT).show();
    }
}
