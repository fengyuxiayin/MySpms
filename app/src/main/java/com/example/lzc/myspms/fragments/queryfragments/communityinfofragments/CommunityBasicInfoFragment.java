package com.example.lzc.myspms.fragments.queryfragments.communityinfofragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.communityinfoactivitys.EditCommunityInfoActivity;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CommunityBasicInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


/**
 * Created by LZC on 2017/10/31.
 */
public class CommunityBasicInfoFragment extends BaseFragment {
    public static final String TAG = CommunityBasicInfoFragment.class.getSimpleName();
    private String sqId;
    private EditText tvCommunityName;
    private EditText tvCommunityAddress;
    private EditText tvCommunityPeople;
    private EditText tvCommunityAcreage;
    private View linearlayout;
    private boolean isView;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community_basic_info, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sqId = getArguments().getString("sqId");
        isView = getArguments().getBoolean("isView", false);
        initView();
        initData();
        initListener();
        if (isView) {
            tvCommunityName.setFocusable(false);
            tvCommunityAddress.setFocusable(false);
            tvCommunityPeople.setFocusable(false);
            tvCommunityAcreage.setFocusable(false);
            btnSave.setVisibility(View.GONE);
        }
    }

    private void initListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断数据是否合法
                if (tvCommunityName.getText().toString().trim()==null) {
                    Toast.makeText(getContext(), "社区名称不能为空", Toast.LENGTH_SHORT).show();
                }
                if (tvCommunityAddress.getText().toString().trim()==null) {
                    Toast.makeText(getContext(), "社区地址不能为空", Toast.LENGTH_SHORT).show();
                }
                //向服务器发送修改过后的社区信息
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL+"/community/save")
                        .addParams("id",sqId)
                        .addParams("sqmc",tvCommunityName.getText().toString().trim())
                        .addParams("sqdz",tvCommunityAddress.getText().toString().trim())
                        .addParams("sqrk",tvCommunityPeople.getText().toString().trim())
                        .addParams("sqmj",tvCommunityAcreage.getText().toString().trim())
                        .build()
                        .execute(new StringCallback() {
                            private Gson gson;

                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: "+e.getMessage() );
                                NetUtil.errorTip(getContext(),e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: "+response );
                                gson = new Gson();
                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                Toast.makeText(getContext(), infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void initView() {
        linearlayout = view.findViewById(R.id.fragment_community_basic_info);
        tvCommunityName = (EditText) view.findViewById(R.id.activity_community_basic_info_detail_tv_community_name);
        tvCommunityAddress = (EditText) view.findViewById(R.id.activity_community_basic_info_detail_tv_community_address);
        tvCommunityPeople = (EditText) view.findViewById(R.id.activity_community_basic_info_detail_tv_people);
        tvCommunityAcreage = (EditText) view.findViewById(R.id.activity_community_basic_info_detail_tv_acreage);
        btnSave = (Button) view.findViewById(R.id.activity_community_basic_info_btn_save);
    }

    private void initData() {
        //根据社区id从服务器把社区信息加载下来
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/community/findById")
                .addParams("id", sqId)
                .build()
                .execute(new StringCallback() {

                    private Gson gson;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        linearlayout.setVisibility(View.VISIBLE);
                        gson = new Gson();
                        CommunityBasicInfoModel communityBasicInfoModel = gson.fromJson(response, CommunityBasicInfoModel.class);
                        if (communityBasicInfoModel.isData()) {
                            CommunityBasicInfoModel.CommunityBasicInfoMsgModel communityBasicInfoMsgModel = gson.fromJson(communityBasicInfoModel.getMsg(), CommunityBasicInfoModel.CommunityBasicInfoMsgModel.class);
                            tvCommunityName.setText(communityBasicInfoMsgModel.getSqmc());
                            tvCommunityAddress.setText(communityBasicInfoMsgModel.getSqdz());
                            Log.e(TAG, "onResponse: " + communityBasicInfoMsgModel.getSqrk() + "" + communityBasicInfoMsgModel.getSqmj());
                            tvCommunityPeople.setText(String.valueOf(communityBasicInfoMsgModel.getSqrk()));
                            tvCommunityAcreage.setText(String.valueOf(communityBasicInfoMsgModel.getSqmj()));
                        } else {
                            Toast.makeText(getContext(), communityBasicInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
