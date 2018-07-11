package com.example.lzc.myspms.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.SystemSettingsActivity;
import com.example.lzc.myspms.adapters.ListAdapter;
import com.example.lzc.myspms.models.MyInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/27.
 */
public class MyFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    public static final String TAG = MyFragment.class.getSimpleName();
    private View view;
    private ListView listView;
    private List<MyInfoModel> data;
    private ListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        ininListener();
    }

    private void ininListener() {
        listView.setOnItemClickListener(this);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new MyInfoModel("检查计划",R.mipmap.check_plan));
        data.add(new MyInfoModel("文书管理",R.mipmap.document_management));
        data.add(new MyInfoModel("上传管理",R.mipmap.upload_management));
        data.add(new MyInfoModel("消息查看",R.mipmap.read_message));
        data.add(new MyInfoModel("检查记录",R.mipmap.check_record));
        data.add(new MyInfoModel("系统设置",R.mipmap.system_setup));
        data.add(new MyInfoModel("gps定位 工作日8:00-17:00开启",R.mipmap.gps));
        listAdapter = new ListAdapter(data,getContext());
        listView.setAdapter(listAdapter);

    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.fragment_my_lv);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0://检查计划
                intent = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent);
                break;
            case 1://检查计划
                intent = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent);
                break;
            case 2://检查计划
                intent = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent);
                break;
            case 3://检查计划
                intent = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent);
                break;
            case 4://检查计划
                intent = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent);
                break;
            case 5://检查计划
                intent = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent);
                break;
            case 6://检查计划
                intent = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent);
                break;
            
        }
    }
}
