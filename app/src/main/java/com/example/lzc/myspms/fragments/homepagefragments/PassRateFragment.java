package com.example.lzc.myspms.fragments.homepagefragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckProjectActivity;
import com.example.lzc.myspms.adapters.CurrentCheckAdapter;
import com.example.lzc.myspms.custom.LineChartView;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CheckEnterpriseResultModel;
import com.example.lzc.myspms.models.CheckIsExistModel;
import com.example.lzc.myspms.models.CheckProjectResultModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.HomePageCheckInfoModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by LZC on 2017/12/6.
 */

public class PassRateFragment extends BaseFragment {
    public static final String TAG = PassRateFragment.class.getSimpleName();
    //第九小块
    private LineChartView lineChartCompony;
    //第十小块
    private LineChartView lineChartProject;
    private Gson gson = new Gson();
    //x轴坐标对应的数据
    private List<String> xValueCompony = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValueCompony = new ArrayList<>();
    //折线对应的数据
    private Map<String, Double> valueCompony = new HashMap<>();
    //x轴坐标对应的数据
    private List<String> xValueProject = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValueProject = new ArrayList<>();
    //折线对应的数据
    private Map<String, Double> valueProject = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pass_rate, container, false);
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
    }

    private void initData() {
        //第九小块折线图数据初始化
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/mobileHomePage/checkEnterpriseResult")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(), e.getMessage() + "checkEnterpriseResult");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/mobileHomePage/checkEnterpriseResult");
                        CheckEnterpriseResultModel checkEnterpriseResultModel = gson.fromJson(response, CheckEnterpriseResultModel.class);
                        if (checkEnterpriseResultModel.isData()) {
                            CheckEnterpriseResultModel.CheckEnterpriseResultMsgModel checkEnterpriseResultMsgModel = gson.fromJson(checkEnterpriseResultModel.getMsg(), CheckEnterpriseResultModel.CheckEnterpriseResultMsgModel.class);
                            List<CheckEnterpriseResultModel.CheckEnterpriseResultMsgModel.ListBean> list = checkEnterpriseResultMsgModel.getList();
                            xValueCompony.clear();
                            yValueCompony.clear();
                            valueCompony.clear();
                            if (list != null) {
                                for (int i = 0; i < list.size(); i++) {
                                    xValueCompony.add(DateUtil.long2Date(list.get(i).getJckssj()));
                                    Log.e(TAG, "onResponse:valueCompony "+list.get(i).getPassPercent() );
                                    valueCompony.put(DateUtil.long2Date(list.get(i).getJckssj() ), list.get(i).getPassPercent());
                                }
                                for (int i = 0; i < 6; i++) {
                                    yValueCompony.add(i * 20);
                                }
                                Log.e(TAG, "onResponse: valueCompony"+valueCompony.size() );
                                lineChartCompony.setValue(valueCompony, xValueCompony, yValueCompony);
                            }
                        }

                    }
                });
        //第十小块折线图数据初始化
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/mobileHomePage/checkProjectResult")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(), e.getMessage() + "/mobileHomePage/checkProjectResult ");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/mobileHomePage/checkProjectResult");
                        CheckProjectResultModel checkProjectResultModel = gson.fromJson(response, CheckProjectResultModel.class);
                        if (checkProjectResultModel.isData()) {
                            CheckProjectResultModel.CheckProjectResultMsgModel checkProjectResultMsgModel = gson.fromJson(checkProjectResultModel.getMsg(), CheckProjectResultModel.CheckProjectResultMsgModel.class);
                            List<CheckProjectResultModel.CheckProjectResultMsgModel.ListBean> list = checkProjectResultMsgModel.getList();
                            xValueProject.clear();
                            yValueProject.clear();
                            valueProject.clear();
                            if (list != null) {
                                for (int i = 0; i < list.size(); i++) {
                                    xValueProject.add(DateUtil.long2Date(list.get(i).getJckssj()));
                                    Log.e(TAG, "onResponse: valueProject"+ list.get(i).getPassPercent());
                                    valueProject.put(DateUtil.long2Date(list.get(i).getJckssj()), list.get(i).getPassPercent());

                                }
                                for (int i = 0; i < 6; i++) {
                                    yValueProject.add(i * 20);
                                }
                                Log.e(TAG, "onResponse: valueProject"+valueProject.size() );
                                lineChartProject.setValue(valueProject, xValueProject, yValueProject);
                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    private void initView() {
//第九小块
        lineChartCompony = (LineChartView) view.findViewById(R.id.fragment_passrate_lineChart_compony);
        //第十小块
        lineChartProject = (LineChartView) view.findViewById(R.id.fragment_passrate_lineChart_project);
    }
}
