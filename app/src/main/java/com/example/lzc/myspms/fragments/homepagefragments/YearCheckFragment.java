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
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CheckIsExistModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.HomePageCheckInfoModel;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/12/6.
 */

public class YearCheckFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    public static final String TAG = YearCheckFragment.class.getSimpleName();
    private PullToRefreshListView listView;
    private PieChart pieChart;
    private TextView tvTip;
    private Gson gson;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private int page = 1;
    private TextView textView;
    private List<HomePageCheckInfoModel.HomePageCheckInfoMsgModel.ListBean> list = new ArrayList<>();
    //piechat所在的linearlayout
    private LinearLayout linearLayout2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_year_check, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取activity的标志位和qyId
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(getContext(), R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    getCheckInfo();
                }
            }
        });
        listView.setOnItemClickListener(this);
    }

    private void initData() {
        gson = new Gson();
    }

    @Override
    public void onResume() {
        super.onResume();
        getCheckInfo();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getCheckInfo();
        }
    }

    private void initView() {
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_year_check_plv);
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        listView.getRefreshableView().addFooterView(textView);
        //设置listview的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
        pieChart = (PieChart) view.findViewById(R.id.fragment_year_check_pc);
        tvTip = (TextView) view.findViewById(R.id.fragment_year_check_tv);
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_year_check_no_network_connection);
        linearLayout1 = (LinearLayout) view.findViewById(R.id.fragment_year_check_ll);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.fragment_year_check_pc_ll);
    }

    /**
     * @desc 获取当前正在检查的企业的CheckInfo
     * @date 2017/12/20 15:18
     */
    private void getCheckInfo() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkInfo/findCheckCount")
                .addParams("keywords", "y")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        HomePageCheckInfoModel homePageCheckInfoModel = gson.fromJson(response, HomePageCheckInfoModel.class);
//                        //没有正在检查的企业时isData = false
                        if (homePageCheckInfoModel.isData()) {
                            HomePageCheckInfoModel.HomePageCheckInfoMsgModel homePageCheckInfoMsgModel = gson.fromJson(homePageCheckInfoModel.getMsg(), HomePageCheckInfoModel.HomePageCheckInfoMsgModel.class);
                            if (homePageCheckInfoMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (homePageCheckInfoMsgModel.getList()==null) {
                                    list = new ArrayList<>();
                                }else{
                                    list = homePageCheckInfoMsgModel.getList();
                                }
                                Log.e(TAG, "onResponse: " + list.size());
                            } else {
                                list.addAll(homePageCheckInfoMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                linearLayout2.setVisibility(View.GONE);
                                CurrentCheckAdapter yearCheckAdapter = new CurrentCheckAdapter(list, getActivity());
                                listView.setAdapter(yearCheckAdapter);
                                listView.onRefreshComplete();
                            } else {
                                initPieChart(list,0);
//                                linearLayout2.setVisibility(View.VISIBLE);
                                CurrentCheckAdapter yearCheckAdapter = new CurrentCheckAdapter(list, getActivity());
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    yearCheckAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(yearCheckAdapter);
                                }
                                listView.onRefreshComplete();
                            }

                            Log.e(TAG, "onResponse: ");
                        } else {
                            Toast.makeText(getContext(), homePageCheckInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * @param list 数据源
     * @param pos
     * @desc 初始化饼状图的数据
     * @date 2018/3/21 9:48
     */
    private void initPieChart(List<HomePageCheckInfoModel.HomePageCheckInfoMsgModel.ListBean> list, int pos) {
        pieChart.clear();
        double countYhg = 0;
        double countXmsl = 0;
        double countYjc = 0;
        double countWhg = 0;
//        for (int i = 0; i < list.size(); i++) {
//            countYhg += list.get(i).getCountQualified();
//            countXmsl += list.get(i).getCountTotal();
//            countYjc += list.get(i).getCountChecked();
//            countWhg += list.get(i).getCountUnqualified();
//        }
        countYhg = list.get(pos).getCountQualified();
        countXmsl = list.get(pos).getCountTotal();
        countYjc  = list.get(pos).getCountChecked();
        countWhg  = list.get(pos).getCountUnqualified();
        Log.e(TAG, "initPieChart: "+countWhg+"  "+countXmsl+"  "+countYhg+"  "+countYjc+"  " );
        //饼状图
        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        //模拟颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        if (countXmsl!=0) {
            if (countWhg*100/countXmsl>=100) {
                entries.add(new PieEntry((float) (100), "检查均不合格"));
                colors.add(Color.rgb(254,1,0));
            }else if((countXmsl-countYjc)*100/countXmsl>=100){
                entries.add(new PieEntry((float) (100), "企业还未开始检查"));
                colors.add(Color.rgb(128,128,128));
            }else if(countYhg*100/countXmsl>=100){
                entries.add(new PieEntry((float) (100), "检查全部合格"));
                colors.add(Color.rgb(21,104,53));
            }else{
                entries.add(new PieEntry((float) (countWhg*100/countXmsl), "检查不合格"));
                entries.add(new PieEntry((float) ((countXmsl-countYjc)*100/countXmsl), "未检查"));
                entries.add(new PieEntry((float) (countYhg*100/countXmsl), "检查合格"));
                colors.add(Color.rgb(254,1,0));
                colors.add(Color.rgb(128,128,128));
                colors.add(Color.rgb(21,104,53));
            }
        }else{
            entries.add(new PieEntry((float) (100), "该企业没有项目"));
            colors.add(Color.rgb(128,128,128));
        }

        //设置数据
        PieDataSet dataSet = new PieDataSet(entries, "");
        //设置颜色
        dataSet.setColors(colors);

        //设置块与块之间的距离
        dataSet.setSliceSpace(5f);
        //设置点击突出的距离
        dataSet.setSelectionShift(10f);

        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        //是否绘制中间的圆
        pieChart.setDrawHoleEnabled(false);
        pieChart.setData(data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        int pos = position -1;
        if (pos<list.size()) {
//            initPieChart(list,pos);
            OkHttpUtils.post()
                    .url(Constant.SERVER_URL + "/checkInfo/isExist")
                    .addParams("zqjlId", list.get(position - 1).getId() + "")
                    .addParams("jclx", list.get(position - 1).getJclx() + "")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            NetUtil.errorTip(getContext(),e.getMessage());
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: 111" + response);
                            CheckIsExistModel checkIsExistModel = gson.fromJson(response, CheckIsExistModel.class);
                            if (checkIsExistModel.isData()) {
                                CheckIsExistModel.CheckIsExistMsgModel checkIsExistMsgModel = gson.fromJson(checkIsExistModel.getMsg(), CheckIsExistModel.CheckIsExistMsgModel.class);
                                Intent intent = new Intent();
                                intent.setClass(getContext(), CheckProjectActivity.class);
                                intent.putExtra("qyId", checkIsExistMsgModel.getQyId() + "");
                                intent.putExtra("qymc", list.get(position - 1).getQymc() + "");
                                intent.putExtra("zqjlId", checkIsExistMsgModel.getZqjlId() + "");
                                intent.putExtra("jclx", checkIsExistMsgModel.getJclx() + "");
                                intent.putExtra("jcId", checkIsExistMsgModel.getId() + "");
                                intent.putExtra("jcjg", checkIsExistMsgModel.getJcjg() + "");
                                Log.e(TAG, "onActivityCreated: " + list.get(position - 1).getId() + "" + "  " + list.get(position - 1).getQymc() + "  " + list.get(position - 1).getZqjlId() + "" + "  " + list.get(position - 1).getJclx() + "" + "  " + checkIsExistMsgModel.getId());
                                startActivity(intent);
                            }else{
                                Toast.makeText(getContext(), checkIsExistModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
