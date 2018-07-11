package com.example.lzc.myspms.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.CheckItemsQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.CommunityInfoQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.EnterpriseInfoQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.EnterpriseQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.StaffInfoQueryActivity;
import com.example.lzc.myspms.adapters.GridAdapter;
import com.example.lzc.myspms.models.CheckInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/27.
 */
public class CheckFragment extends BaseFragment implements  View.OnClickListener {
    public static final String TAG = CheckFragment.class.getSimpleName();
    private View view;
    private ImageView imgComponyInfo;
    private ImageView imgCommunityInfo;
    private ImageView imgStaffInfo;
    private ImageView imgCompony;
    private ImageView imgCheckItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_check, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgComponyInfo.setOnClickListener(this);
        imgCommunityInfo.setOnClickListener(this);
        imgStaffInfo.setOnClickListener(this);
        imgCompony.setOnClickListener(this);
        imgCheckItems.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        imgComponyInfo = (ImageView) view.findViewById(R.id.fragment_check_img_compony_info);
        imgCommunityInfo = (ImageView) view.findViewById(R.id.fragment_check_img_community_info);
        imgStaffInfo = (ImageView) view.findViewById(R.id.fragment_check_img_staff_info);
        imgCompony = (ImageView) view.findViewById(R.id.fragment_check_img_compony);
        imgCheckItems = (ImageView) view.findViewById(R.id.fragment_check_img_check_items);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v!=null) {
            switch (v.getId()) {
                case R.id.fragment_check_img_compony_info:
                                            //企业信息查询
                        intent.setClass(getActivity(), EnterpriseInfoQueryActivity.class);
                        intent.putExtra("startClass","CheckFragment");
                        startActivity(intent);
                    break;
                case R.id.fragment_check_img_community_info:
                                            //社区信息查询
                        intent.setClass(getActivity(), CommunityInfoQueryActivity.class);
                        startActivity(intent);
                    break;
                case R.id.fragment_check_img_staff_info:
                    //人员信息查询
                    intent.setClass(getActivity(), StaffInfoQueryActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fragment_check_img_compony:
                    //企业
                    intent.setClass(getActivity(), EnterpriseQueryActivity.class);
                    startActivity(intent);
                    break;
                case R.id.fragment_check_img_check_items:
                    //检查项
                    intent.setClass(getActivity(), CheckItemsQueryActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}
