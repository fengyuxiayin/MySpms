package com.example.lzc.myspms.activitys.queryactivitys.staffinfoactivitys;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.queryactivitys.StaffInfoQueryActivity;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.StaffInfoDetailModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.PermissionUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class StaffInfoDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = StaffInfoDetailActivity.class.getSimpleName();
    private TextView tvStaffName;
    private TextView tvCommunityName;
    private TextView tvPosition;
    private TextView tvNumber;
    private TextView tvPhoneNumber;
    private Button btnCall;
    //上个页面传进来的id
    private String id;
    //上个页面传进来的所属单位名称
    private String ssdwmc;
    private StaffInfoDetailModel.StaffInfoDetailMsgModel staffInfoDetailMsgModel;
    private StaffInfoDetailModel staffInfoDetailModel;
    private Gson gson;
    private ImageView imgBack;
    private ImageView imgVideocall;
    private TextView tvTitle;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    Intent intent = new Intent();
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_info_detail);
        id = getIntent().getStringExtra("id");
        ssdwmc = getIntent().getStringExtra("ssdwmc");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btnCall.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/baseStaff/findById")
                .addParams("id",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(StaffInfoDetailActivity.this,e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        gson = new Gson();
                        staffInfoDetailModel = gson.fromJson(response, StaffInfoDetailModel.class);
                        if (staffInfoDetailModel.isData()) {
                            staffInfoDetailMsgModel = gson.fromJson(staffInfoDetailModel.getMsg(), StaffInfoDetailModel.StaffInfoDetailMsgModel.class);
                            tvStaffName.setText(staffInfoDetailMsgModel.getRymc());
                            tvNumber.setText(staffInfoDetailMsgModel.getGzbh());
                            tvPhoneNumber.setText(staffInfoDetailMsgModel.getLxdh());
                            if (staffInfoDetailMsgModel.getRyzc()==1) {
                                tvPosition.setText("协查员");
                            }else{
                                tvPosition.setText("执法员");
                            }
                            tvCommunityName.setText(ssdwmc);
                        }else{
                            Toast.makeText(StaffInfoDetailActivity.this, staffInfoDetailModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.activity_staff_info_detail_no_network_connection);
        linearLayout1 = (LinearLayout) findViewById(R.id.activity_staff_info_detail_ll);
        imgBack = (ImageView) findViewById(R.id.activity_staff_info_detail_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_staff_info_detail_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_staff_info_detail_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_staff_info_detail_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_staff_info_detail_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_staff_info_detail_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_staff_info_detail_header).findViewById(R.id.title);
        tvTitle.setText("人员信息");
        tvStaffName = (TextView) findViewById(R.id.activity_staff_info_detail_tv_staff_name);
        tvCommunityName = (TextView) findViewById(R.id.activity_staff_info_detail_tv_community_name);
        tvPosition = (TextView) findViewById(R.id.activity_staff_info_detail_tv_position);
        tvNumber = (TextView) findViewById(R.id.activity_staff_info_detail_tv_number);
        tvPhoneNumber = (TextView) findViewById(R.id.activity_staff_info_detail_tv_phonenumber);
        btnCall = (Button) findViewById(R.id.activity_staff_info_detail_btn_call);
//        Drawable drawable = getResources().getDrawable(R.mipmap.call);
//        drawable.setBounds(0,0,35,35);
//        btnCall.setCompoundDrawables(drawable,null,null,null);
    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), StaffInfoDetailActivity.this,StaffInfoDetailActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_staff_info_detail_btn_call:
                    PermissionUtil.checkAndRequestPermission(this, Manifest.permission.CALL_PHONE,1, new PermissionUtil.PermissionRequestSuccessCallBack() {
                        @Override
                        public void onHasPermission() {
                            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +staffInfoDetailMsgModel.getLxdh()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });

                    break;
            }
        }

    }
}
