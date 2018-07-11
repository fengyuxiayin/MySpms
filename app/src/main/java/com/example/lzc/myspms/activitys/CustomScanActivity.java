package com.example.lzc.myspms.activitys;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.lzc.myspms.R;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class CustomScanActivity extends AppCompatActivity implements View.OnClickListener, DecoratedBarcodeView.TorchListener {
    private CaptureManager captureManager;     //捕获管理器
    private DecoratedBarcodeView dbv;
    private Button btnFlash;
    private Button btnCancel;
    private boolean isOpen = false;
    private Camera.Parameters params;
    private String TAG = CustomScanActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scan);
        initiView(savedInstanceState);
        initListener();
    }

    private void initListener() {
        dbv.setTorchListener(this);
        btnFlash.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initiView(Bundle savedInstanceState) {
        dbv = (DecoratedBarcodeView) findViewById(R.id.dbv_custom);
        btnFlash = (Button) findViewById(R.id.activity_custom_scan_btn_flash);
        btnCancel = (Button) findViewById(R.id.activity_custom_scan_btn_cancel);
        captureManager = new CaptureManager(this,dbv);
        captureManager.initializeFromIntent(getIntent(),savedInstanceState);
        captureManager.decode();
    }
    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return dbv.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            switch (v.getId()) {
                case R.id.activity_custom_scan_btn_flash:
//                    CameraManager cameraManager = new CameraManager(this);
////                    if (cameraManager.isTorchOn()) {
////                        cameraManager.setTorch(false);
////                    }else{
////                        cameraManager.setTorch(true);
////                    }
////                    Camera camera = Camera.open();
////                    CameraManager manager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
//
//                    Camera camera = cameraManager.getCamera();
//                    Log.e(TAG, "onClick: "+camera );
//                    if (isOpen) {//关闭闪光灯
//                        dbv.setTorchOff();
//                    }else{
//                        dbv.setTorchOn();
//                    }
                    break;
                case R.id.activity_custom_scan_btn_cancel:
                    finish();
                    break;
            }
        }
    }

    @Override
    public void onTorchOn() {
        isOpen = true;
    }

    @Override
    public void onTorchOff() {
        isOpen = false;

    }
    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
}
