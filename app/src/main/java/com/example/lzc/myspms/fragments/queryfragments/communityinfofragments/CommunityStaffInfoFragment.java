package com.example.lzc.myspms.fragments.queryfragments.communityinfofragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.CommunityStaffInfoAdapter;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CommunityStaffInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/31.
 */
public class CommunityStaffInfoFragment extends BaseFragment {
    public static final String TAG = CommunityStaffInfoFragment.class.getSimpleName();
    private String sqId;
    private PullToRefreshListView listView;
    private int page = 1;
    private TextView textView;
    private List<CommunityStaffInfoModel.CommunityStaffInfoMsgModel.ListBean> list = new ArrayList<>();
    private boolean isView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community_staff_info, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sqId = getArguments().getString("sqId");
        isView = getArguments().getBoolean("isView");
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
                    getDataFromServer();
                }
            }
        });
    }

    private void initView() {
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_community_staff_info_pulltorefresh);
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        listView.getRefreshableView().addFooterView(textView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    private void initData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseStaff/findStaff")
                .addParams("ssdwId", sqId)
                .addParams("pn", page + "")
                .build()
                .execute(new StringCallback() {

                    private Gson gson;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(),e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        gson = new Gson();
                        CommunityStaffInfoModel communityStaffInfoModel = gson.fromJson(response, CommunityStaffInfoModel.class);
                        if (communityStaffInfoModel.isData()) {
                            CommunityStaffInfoModel.CommunityStaffInfoMsgModel communityStaffInfoMsgModel = gson.fromJson(communityStaffInfoModel.getMsg(), CommunityStaffInfoModel.CommunityStaffInfoMsgModel.class);
                            if (communityStaffInfoMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (communityStaffInfoMsgModel.getList()==null) {
                                    list = new ArrayList<>();
                                }else{
                                    list = communityStaffInfoMsgModel.getList();
                                }
                                Log.e(TAG, "onResponse: " + list.size());
                            } else {
                                list.addAll(communityStaffInfoMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                CommunityStaffInfoAdapter communityStaffInfoAdapter = new CommunityStaffInfoAdapter(list, getContext());
                                listView.setAdapter(communityStaffInfoAdapter);
                                listView.onRefreshComplete();
                            } else {
                                CommunityStaffInfoAdapter communityStaffInfoAdapter = new CommunityStaffInfoAdapter(list, getContext());
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    communityStaffInfoAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(communityStaffInfoAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(getContext(), communityStaffInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
