package com.example.lzc.myspms.activitys.queryactivitys.communityinfoactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.models.CommunityBasicInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class EditCommunityInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = EditCommunityInfoActivity.class.getSimpleName();
    //根据社区id去查社区基本信息和社区人员信息
    private String sqId;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private TextView tvTitle;
    private EditText etCommunityName;
    private EditText etCommunityAddress;
    private EditText etCommunityPeople;
    private EditText etCommunityAcreage;
    private Button btnSave;
    private LinearLayout linearlayout;
    private LinearLayout linearLayout1;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_community_info);
        sqId = getIntent().getStringExtra("sqId");
        initView();
        initData();
        initListener();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/community/findById")
                .addParams("id",sqId)
                .build()
                .execute(new StringCallback() {

                    private Gson gson;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(EditCommunityInfoActivity.this,e.getMessage());
                        linearlayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        linearlayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        gson = new Gson();
                        CommunityBasicInfoModel communityBasicInfoModel = gson.fromJson(response, CommunityBasicInfoModel.class);
                        if (communityBasicInfoModel.isData()) {
                            CommunityBasicInfoModel.CommunityBasicInfoMsgModel communityBasicInfoMsgModel = gson.fromJson(communityBasicInfoModel.getMsg(), CommunityBasicInfoModel.CommunityBasicInfoMsgModel.class);
                            etCommunityName.setText(communityBasicInfoMsgModel.getSqmc());
                            etCommunityName.setSelection(etCommunityName.getText().toString().length());
                            etCommunityAddress.setText(communityBasicInfoMsgModel.getSqdz());
                            etCommunityPeople.setText(String.valueOf(communityBasicInfoMsgModel.getSqrk()));
                            etCommunityAcreage.setText(String.valueOf(communityBasicInfoMsgModel.getSqmj()));
                        }else{
                            Toast.makeText(EditCommunityInfoActivity.this,communityBasicInfoModel.getMsg() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
    }

    private void initView() {
        linearlayout = (LinearLayout) findViewById(R.id.activity_edit_community_info_no_network_connection);
        linearLayout1 = (LinearLayout) findViewById(R.id.activity_edit_community_info_ll);
        imgBack = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.title);
        tvTitle.setText("修改社区信息");
        etCommunityName = (EditText) findViewById(R.id.activity_edit_community_info_et_community_name);
        etCommunityAddress = (EditText) findViewById(R.id.activity_edit_community_info_et_community_address);
        etCommunityPeople = (EditText) findViewById(R.id.activity_edit_community_info_et_community_people);
        etCommunityAcreage = (EditText) findViewById(R.id.activity_edit_community_info_et_community_acreage);
        //设置hint 和光标位置到末尾
        etCommunityName.setHint("必填项");
        etCommunityName.setHintTextColor(getResources().getColor(R.color.hintRed));
        etCommunityAddress.setHint("必填项");
        etCommunityAddress.setHintTextColor(getResources().getColor(R.color.hintRed));
        etCommunityPeople.setHint("选填项 (数字)");
        etCommunityAcreage.setHint("选填项 (数字)");
        btnSave = (Button) findViewById(R.id.activity_edit_community_info_btn_save_info);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), EditCommunityInfoActivity.this,EditCommunityInfoActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_edit_community_info_btn_save_info:
                    //先判断数据是否合法
                    if (etCommunityName.getText().toString().trim()==null) {
                        Toast.makeText(this, "社区名称不能为空", Toast.LENGTH_SHORT).show();
                    }
                    if (etCommunityAddress.getText().toString().trim()==null) {
                        Toast.makeText(this, "社区地址不能为空", Toast.LENGTH_SHORT).show();
                    }
                    //向服务器发送修改过后的社区信息
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL+"/community/save")
                            .addParams("id",sqId)
                            .addParams("sqmc",etCommunityName.getText().toString().trim())
                            .addParams("sqdz",etCommunityAddress.getText().toString().trim())
                            .addParams("sqrk",etCommunityPeople.getText().toString().trim())
                            .addParams("sqmj",etCommunityAcreage.getText().toString().trim())
                            .build()
                            .execute(new StringCallback() {
                                private Gson gson;

                                @Override
                                public void onError(Request request, Exception e) {
                                    Log.e(TAG, "onError: "+e.getMessage() );
                                    NetUtil.errorTip(EditCommunityInfoActivity.this,e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: "+response );
                                    gson = new Gson();
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    Toast.makeText(EditCommunityInfoActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    break;
            }

        }
    }
}
