package com.example.lzc.myspms.fragments.homepagefragments.menufragments;

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
import com.example.lzc.myspms.adapters.AlreadyDownLoadAdapter;
import com.example.lzc.myspms.adapters.CurrentCheckAdapter;
import com.example.lzc.myspms.adapters.UploadAdapter;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.DownloadModel;
import com.example.lzc.myspms.models.HomePageCheckInfoModel;
import com.example.lzc.myspms.models.InstrumentModel;
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

public class UploadFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    public static final String TAG = UploadFragment.class.getSimpleName();
    private PullToRefreshListView listView;
    private Gson gson;
    private int page = 1;
    private TextView textView;
    private List<InstrumentModel.InstrumentMsgModel.ListBean> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upload, container, false);
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
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_upload_pulltorefresh);
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        listView.getRefreshableView().addFooterView(textView);
        //设置listview的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    /**
     * @desc 获取当前正在检查的企业的CheckInfo
     * @date 2017/12/20 15:18
     */
    private void getCheckInfo() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkUpload/find")
                .addParams("accountId",Constant.ACCOUNT_ID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);

                        InstrumentModel instrumentModel = gson.fromJson(response, InstrumentModel.class);
//                        //没有正在检查的企业时isData = false
                        if (instrumentModel.isData()) {
                            InstrumentModel.InstrumentMsgModel instrumentMsgModel = gson.fromJson(instrumentModel.getMsg(), InstrumentModel.InstrumentMsgModel.class);
                            if (instrumentMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (instrumentMsgModel.getList()==null) {
                                    list = new ArrayList<InstrumentModel.InstrumentMsgModel.ListBean>();
                                }else{
                                    list = instrumentMsgModel.getList();
                                }
                                Log.e(TAG, "onResponse: "+ list.size() );
                            } else {
                                list.addAll(instrumentMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                UploadAdapter uploadAdapter = new UploadAdapter(list, getContext());
                                listView.setAdapter(uploadAdapter);
                                listView.onRefreshComplete();
                            } else {
                                UploadAdapter uploadAdapter = new UploadAdapter(list, getContext());
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    uploadAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(uploadAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                            Log.e(TAG, "onResponse: " );
                        } else {
                            Toast.makeText(getContext(), instrumentModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pos = position - 1;

    }
}
