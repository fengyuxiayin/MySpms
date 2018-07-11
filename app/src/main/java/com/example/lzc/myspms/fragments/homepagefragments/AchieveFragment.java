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
import com.example.lzc.myspms.custom.BarChartView;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CheckIsExistModel;
import com.example.lzc.myspms.models.CheckQyTimesForZd;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.HomePageCheckInfoModel;
import com.example.lzc.myspms.models.LoginInfoModel;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/12/6.
 */

public class AchieveFragment extends BaseFragment{
    public static final String TAG = AchieveFragment.class.getSimpleName();
    //第二小块
    private TextView tvLjjcyhsTitle;
    private TextView tvLjjcqyTimes;
    private TextView tvLjjcqyPos;
    private BarChartView bcLjjcqy;
    //第三小块
    private TextView tvLjjcyhsTimes;
    private TextView tvLjjcyhsPos;
    private BarChartView bcLjjcyhs;
    //第四小块
    private TextView tvLjfcqycsTimes;
    private TextView tvLjfcqycsPos;
    private BarChartView bcLjfcqycs;
    private TextView tvLjfcqycsTitle;
    private TextView tvLjfcqycsName;
    private Gson gson = new Gson();
    //第六小块
    private BarChartView bcLjjcxmcs;
    private TextView tvLjjcxmcsTimes;
    private TextView tvLjjcxmcsPos;
    private TextView tvLjjcxmcsTitle;
    //第七小块
    private TextView tvLjjcscTimes;
    private TextView tvLjjcscPos;
    private BarChartView bcLjjcsc;
    private TextView tvLjjcscTitle;
    private TextView tvLjjcscName;
    //第八小块
    private TextView tvZdjgqyA;
    private TextView tvZdjgqyD;
    private TextView tvZdjgqyB;
    private TextView tvZdjgqyC;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_achieve, container, false);
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
        //第二小块数据初始化
        setBarChatViewPostData("/mobileHomePage/checkQyTimes",tvLjjcqyTimes,tvLjjcqyPos,bcLjjcqy,"1");
        //第三小块数据初始化
        tvLjjcyhsTitle.setText("累计检出隐患数");
        setBarChatViewData("/mobileHomePage/checkYhTimes",tvLjjcyhsTimes,tvLjjcyhsPos,bcLjjcyhs);
        //第四小块数据初始化
        tvLjfcqycsTitle.setText("累计复查企业次数");
        tvLjfcqycsName.setText("次");
        setBarChatViewPostData("/mobileHomePage/checkQyTimes",tvLjfcqycsTimes,tvLjfcqycsPos,bcLjfcqycs,"2");
        //第六小块数据初始化
        tvLjjcxmcsTitle.setText("累计检查项目次数");
        setBarChatViewData("/mobileHomePage/checkXmTimes",tvLjjcxmcsTimes,tvLjjcxmcsPos,bcLjjcxmcs);
        //第七小块数据初始化
        tvLjjcscTitle.setText("累计检查时长");
        tvLjjcscName.setText("小时");
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/mobileHomePage/checkTotalTimes")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+"/mobileHomePage/checkTotalTimes");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+"/mobileHomePage/checkTotalTimes" );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.getMsg().equals("")||infoModel.getMsg()==null||infoModel.getMsg().equals("null")) {
                            Log.e(TAG, "onResponse: "+"还没有数据" );
                        }else{
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                double totalHours =  jsonObject.optDouble("totalHours",0);
                                double avgHours =  jsonObject.optDouble("avgHours",0);
                                int rowNo = (int) jsonObject.optInt("rowNo",0);
                                tvLjjcscTimes.setText(totalHours+"");
                                tvLjjcscPos.setText(rowNo+"");
                                List<BarChartView.BarGraphInfo> list = new ArrayList<>();
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press),"本小组数量", (float) totalHours));
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple),"平均数量", (float) avgHours));
                                bcLjjcsc.setList(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
        //第八小块数据初始化
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/mobileHomePage/checkQyTimesForZd")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+"/mobileHomePage/checkQyTimesForZd");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+"/mobileHomePage/checkQyTimesForZd" );
                        CheckQyTimesForZd checkQyTimesForZd = gson.fromJson(response, CheckQyTimesForZd.class);
                        if (checkQyTimesForZd.isData()) {
                            if (checkQyTimesForZd.getMsg().equals("")||checkQyTimesForZd.getMsg()==null||checkQyTimesForZd.getMsg().equals("[]")) {
                                Log.e(TAG, "onResponse: "+"还没有数据" );
                            }else{
                                CheckQyTimesForZd.CheckQyTimesForZdMsg checkQyTimesForZdMsg = gson.fromJson(checkQyTimesForZd.getMsg(), CheckQyTimesForZd.CheckQyTimesForZdMsg.class);
                                List<CheckQyTimesForZd.CheckQyTimesForZdMsg.ListBean> list = checkQyTimesForZdMsg.getList();
                                if (list!=null) {
                                    for (int i = 0; i < list.size(); i++) {
                                        if (list.get(i).getZddj().equals("A")) {
                                            tvZdjgqyA.setText(list.get(i).getJccs()+"");
                                        }
                                        if (list.get(i).getZddj().equals("B")) {
                                            tvZdjgqyB.setText(list.get(i).getJccs()+"");
                                        }
                                        if (list.get(i).getZddj().equals("C")) {
                                            tvZdjgqyC.setText(list.get(i).getJccs()+"");
                                        }
                                        if (list.get(i).getZddj().equals("D")) {
                                            tvZdjgqyD.setText(list.get(i).getJccs()+"");
                                        }
                                    }
                                }
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
        //第二小块
        tvLjjcqyTimes = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcqycs).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjjcqyPos = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcqycs).findViewById(R.id.fragment_homepage_tv_pos);//排名
        bcLjjcqy = (BarChartView) view.findViewById(R.id.fragment_achieve_barchart_ljjcqycs).findViewById(R.id.fragment_homepage_bc);
        //第三小块
        tvLjjcyhsTimes = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcyhs).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjjcyhsTitle = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcyhs).findViewById(R.id.fragment_homepage_tv_title);//标题
        tvLjjcyhsPos = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcyhs).findViewById(R.id.fragment_homepage_tv_pos);//排名
        bcLjjcyhs = (BarChartView) view.findViewById(R.id.fragment_achieve_barchart_ljjcyhs).findViewById(R.id.fragment_homepage_bc);
        //第四小块
        tvLjfcqycsTimes = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjfcqycsTitle = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_tv_title);//标题
        tvLjfcqycsName = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_tv_times_name);//公里
        tvLjfcqycsPos = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_tv_pos);//排名
        bcLjfcqycs = (BarChartView) view.findViewById(R.id.fragment_achieve_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_bc);
        //第六小块
        tvLjjcxmcsTimes = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcxmcs).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjjcxmcsTitle = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcxmcs).findViewById(R.id.fragment_homepage_tv_title);//排名
        tvLjjcxmcsPos = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcxmcs).findViewById(R.id.fragment_homepage_tv_pos);//排名
        bcLjjcxmcs = (BarChartView) view.findViewById(R.id.fragment_achieve_barchart_ljjcxmcs).findViewById(R.id.fragment_homepage_bc);
        //第七小块
        tvLjjcscTimes = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcsc).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjjcscTitle = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcsc).findViewById(R.id.fragment_homepage_tv_title);//排名
        tvLjjcscPos = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcsc).findViewById(R.id.fragment_homepage_tv_pos);//排名
        tvLjjcscName = (TextView) view.findViewById(R.id.fragment_achieve_barchart_ljjcsc).findViewById(R.id.fragment_homepage_tv_times_name);//排名
        bcLjjcsc = (BarChartView) view.findViewById(R.id.fragment_achieve_barchart_ljjcsc).findViewById(R.id.fragment_homepage_bc);
        //第八小块
        tvZdjgqyA = (TextView) view.findViewById(R.id.fragment_achieve_tv_zdjgqyljjccs_a);
        tvZdjgqyB = (TextView) view.findViewById(R.id.fragment_achieve_tv_zdjgqyljjccs_b);
        tvZdjgqyC = (TextView) view.findViewById(R.id.fragment_achieve_tv_zdjgqyljjccs_c);
        tvZdjgqyD = (TextView) view.findViewById(R.id.fragment_achieve_tv_zdjgqyljjccs_d);
    }
    /**
     *
     *@desc
     *@param jclx 1初查 2 复查
     *@date 2018/6/17 20:50
     */
    private void setBarChatViewPostData(final String url, final TextView tvTimes, final TextView tvPos, final BarChartView barChatView,String jclx) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+url)
                .addParams("jclx",jclx)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+url);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+url );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.getMsg().equals("")||infoModel.getMsg()==null||infoModel.getMsg().equals("null")) {
                            Log.e(TAG, "onResponse: "+"还没有数据" );
                        }else{
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                int jccs =  jsonObject.optInt("jccs",0);
                                double avgCnt =  jsonObject.optDouble("avgCnt",0);
                                int rowNo = jsonObject.optInt("rowNo",0);
                                tvTimes.setText(jccs+"");
                                tvPos.setText(rowNo+"");
                                List<BarChartView.BarGraphInfo> list = new ArrayList<>();
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press),"本小组数量", (float) jccs));
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple),"平均数量", (float) avgCnt));
                                barChatView.setList(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }
    private void setBarChatViewData(final String url, final TextView tvTimes, final TextView tvPos, final BarChartView barChatView) {
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+url);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+url );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.getMsg().equals("")||infoModel.getMsg()==null||infoModel.getMsg().equals("null")) {
                            Log.e(TAG, "onResponse: "+"还没有数据" );
                        }else{
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                int totalHours =  jsonObject.optInt("jccs",0);
                                int avgHours =  jsonObject.optInt("avgCnt",0);
                                int rowNo = (int) jsonObject.optInt("rowNo",0);
                                tvTimes.setText(totalHours+"");
                                tvPos.setText(rowNo+"");
                                List<BarChartView.BarGraphInfo> list = new ArrayList<>();
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press),"本小组数量", (float) totalHours));
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple),"平均数量", (float) avgHours));
                                barChatView.setList(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }

}
