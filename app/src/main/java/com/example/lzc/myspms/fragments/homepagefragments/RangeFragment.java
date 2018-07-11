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
import com.example.lzc.myspms.activitys.queryactivitys.CommunityInfoQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckProjectActivity;
import com.example.lzc.myspms.adapters.CommunityInfoQueryAdapter;
import com.example.lzc.myspms.adapters.CurrentCheckAdapter;
import com.example.lzc.myspms.adapters.RangeFragmentAdapter;
import com.example.lzc.myspms.custom.WaveHelper;
import com.example.lzc.myspms.custom.WaveView;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CheckIsExistModel;
import com.example.lzc.myspms.models.CommunityInfoQueryModel;
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

public class RangeFragment extends BaseFragment{
    public static final String TAG = RangeFragment.class.getSimpleName();
    //负责社区数
    private WaveView wvCommunity;
    private TextView tvFzsqs;
    private TextView tvGroupName;
    private TextView tvCommunityNumber;
    private TextView tvCommunityCount;
    private TextView tvCommunityProportion;
    //负责企业数
    private WaveView wvCompony;
    private TextView tvFzqys;
    private TextView tvComponyNumber;
    private TextView tvComponyCount;
    private TextView tvComponyProportion;
    private Gson gson = new Gson();
    private WaveHelper whCompony;
    private WaveHelper whCommunity;
    private PullToRefreshListView listView;
    private int page = 1;
    //记录小组id 和社区id
    private String xzID = "";
    private String sqID = "";
    private CommunityInfoQueryModel communityInfoQueryModel;
    private CommunityInfoQueryModel.CommunityInfoQueryMsgModel communityInfoQueryMsgModel;
    private TextView textView;
    private List<CommunityInfoQueryModel.CommunityInfoQueryMsgModel.ListBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_range, container, false);
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
                    getCommunityData();
                }
            }
        });
    }

    private void initData() {
        //负责社区数据初始化
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/mobileHomePage/fzsqs")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(), e.getMessage() + "/mobileHomePage/fzsqs");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/mobileHomePage/fzsqs");
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                int count = jsonObject.optInt("count", 0);
                                tvCommunityNumber.setText("负责社区数：" + count + "");
                                int totalCount = jsonObject.optInt("totalCount", 1);
                                tvCommunityCount.setText("社区总数：" + totalCount + "");
                                String zb = jsonObject.optString("zb", "no data");
                                tvCommunityProportion.setText("所占比例：" + zb);
                                tvFzsqs.setText(zb);
                                wvCommunity.setBorder(2, getResources().getColor(R.color.homepage_text_purple));
                                if (totalCount != 0) {
                                    whCommunity = new WaveHelper(wvCommunity, (float) count / (float) totalCount);
                                    whCommunity.start();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
        //负责企业数数据初始化
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/mobileHomePage/findFzQys")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(), e.getMessage() + "/mobileHomePage/findFzQys");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/mobileHomePage/findFzQys ");
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                int count = jsonObject.optInt("fzQys", 0);
                                tvComponyNumber.setText("负责企业数：" + count + "");
                                int totalCount = jsonObject.optInt("totalQys", 1);
                                tvComponyCount.setText("企业总数：" + totalCount + "");
                                String zb = jsonObject.optString("zb", "no data");
                                tvComponyProportion.setText("所占比例：" + zb);
                                tvFzqys.setText(zb);
                                wvCompony.setBorder(2, getResources().getColor(R.color.homepage_text_purple));
                                if (totalCount != 0) {
                                    whCompony = new WaveHelper(wvCompony, (float) count / (float) totalCount);
                                    whCompony.start();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
        //listView数据的初始化
        //根据账号类型判断能看到那些数据 社区 小组 企业 街道 成员单位 1 2 3 4 5
        switch (Constant.ACCOUNT_TYPE) {
            case "1"://社区
                sqID = Constant.ENTERPRISE_ID;
                xzID = "";
                break;
            case "2"://小组
                xzID = Constant.ENTERPRISE_ID;
                sqID = "";
                break;
            case "4"://街道
                xzID = "";
                sqID = "";
                break;
        }
        getCommunityData();
    }

    private void getCommunityData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/community/find")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .addParams("xzId",xzID)
                .addParams("sqId",sqID)
                .build()
                .execute(new StringCallback() {
                    private RangeFragmentAdapter rangeFragmentAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError:/community/find "+e.getMessage() );
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /community/find"+response );
                        communityInfoQueryModel = gson.fromJson(response, CommunityInfoQueryModel.class);
                        if (communityInfoQueryModel.isData()) {
                            communityInfoQueryMsgModel = gson.fromJson(communityInfoQueryModel.getMsg(), CommunityInfoQueryModel.CommunityInfoQueryMsgModel.class);
                            //如果list为空证明没有该社区，否则展示搜索到的结果
                            if (communityInfoQueryMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (communityInfoQueryMsgModel.getList()==null) {
                                    list = new ArrayList<CommunityInfoQueryModel.CommunityInfoQueryMsgModel.ListBean>();
                                }else{
                                    list = communityInfoQueryMsgModel.getList();
                                }
                                Log.e(TAG, "onResponse: "+ list.size() );
                            } else {
                                list.addAll(communityInfoQueryMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                rangeFragmentAdapter = new RangeFragmentAdapter(list,getContext(),getActivity());
                                listView.setAdapter(rangeFragmentAdapter);
                                listView.onRefreshComplete();
                            } else {
                                rangeFragmentAdapter = new RangeFragmentAdapter(list,getContext(),getActivity());
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    rangeFragmentAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(rangeFragmentAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(getActivity(), communityInfoQueryModel.getMsg(), Toast.LENGTH_SHORT).show();
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
        //负责社区数
        wvCommunity = (WaveView) view.findViewById(R.id.fragment_range_wv_fzsqs);
        tvFzsqs = (TextView) view.findViewById(R.id.fragment_range_tv_fzsqs);
        tvCommunityNumber = (TextView) view.findViewById(R.id.fragment_range_tv_community_number);
        tvCommunityCount = (TextView) view.findViewById(R.id.fragment_range_tv_community_count);
        tvCommunityProportion = (TextView) view.findViewById(R.id.fragment_range_tv_community_proportion);
        //负责企业数
        wvCompony = (WaveView) view.findViewById(R.id.fragment_range_wv_fzqys);
        tvFzqys = (TextView) view.findViewById(R.id.fragment_range_tv_fzqys);
        tvComponyNumber = (TextView) view.findViewById(R.id.fragment_range_tv_compony_number);
        tvComponyCount = (TextView) view.findViewById(R.id.fragment_range_tv_compony_count);
        tvComponyProportion = (TextView) view.findViewById(R.id.fragment_range_tv_compony_proportion);
        //listView
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_range_lv);
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        textView.setText("我也是有底线的");
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getRefreshableView().addFooterView(textView);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (whCompony!=null) {
            whCommunity.cancel();
        }
        if (whCompony!=null) {
            whCompony.cancel();
        }
    }
}
