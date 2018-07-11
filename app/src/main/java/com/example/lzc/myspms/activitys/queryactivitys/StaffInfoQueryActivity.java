package com.example.lzc.myspms.activitys.queryactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.StaffInfoQueryAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.StaffInfoQueryModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class StaffInfoQueryActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = StaffInfoQueryActivity.class.getSimpleName();
    private ClearEditText etSearch;
    private ImageView imgBack;
    private ImageView imgVideocall;
    private PullToRefreshListView listview;
    //向服务器请求数据的页码
    private int page = 1;
    private Gson gson;
    //传入adapter的数据
    private List<StaffInfoQueryModel.StaffInfoQueryMsgModel.ListBean> allList = new ArrayList<>();
    //当前请求页的数据
    private List<StaffInfoQueryModel.StaffInfoQueryMsgModel.ListBean> list = new ArrayList<>();
    private TextView textView;
    private Button btnSearch;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private TextView tvTitle;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_info_query);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        page = 1;
        getQueryData();

    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listview.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listview.onRefreshComplete();
                            Toast.makeText(StaffInfoQueryActivity.this,R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    getQueryData();
                }
            }
        });
        btnSearch.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
    }

    private void initData() {
    }

    private void getQueryData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/baseStaff/find")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .addParams("rymc", etSearch.getText().toString())
                .build()
                .execute(new StringCallback() {
                    private StaffInfoQueryAdapter staffInfoQueryAdapter;
                    private StaffInfoQueryModel.StaffInfoQueryMsgModel staffInfoQueryMsgModel;
                    private StaffInfoQueryModel staffInfoQueryModel;
                    private Gson gson;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(StaffInfoQueryActivity.this,e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        gson = new Gson();
                        staffInfoQueryModel = gson.fromJson(response, StaffInfoQueryModel.class);
                        if (staffInfoQueryModel.isData()) {
                            staffInfoQueryMsgModel = gson.fromJson(staffInfoQueryModel.getMsg(), StaffInfoQueryModel.StaffInfoQueryMsgModel.class);
                            Log.e(TAG, "onResponse: "+staffInfoQueryMsgModel.getTotal() );
                            if (staffInfoQueryMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (staffInfoQueryMsgModel.getList()==null) {
                                    list = new ArrayList<>();
                                }else{
                                    list = staffInfoQueryMsgModel.getList();
                                }
                                Log.e(TAG, "onResponse: "+ list.size() );
                            } else {
                                list.addAll(staffInfoQueryMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                staffInfoQueryAdapter = new StaffInfoQueryAdapter(list,getApplicationContext(), StaffInfoQueryActivity.this);
                                listview.setAdapter(staffInfoQueryAdapter);
                                listview.onRefreshComplete();
                            } else {
                                staffInfoQueryAdapter = new StaffInfoQueryAdapter(list,getApplicationContext(), StaffInfoQueryActivity.this);
                                listview.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    staffInfoQueryAdapter.notifyDataSetChanged();
                                } else {
                                    listview.setAdapter(staffInfoQueryAdapter);
                                }
                                listview.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(StaffInfoQueryActivity.this, staffInfoQueryModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.activity_staff_info_no_network_connection);
        linearLayout1 = (LinearLayout) findViewById(R.id.activity_staff_info_ll);
        textView = new TextView(this);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        etSearch = (ClearEditText) findViewById(R.id.activity_staff_info_query_et_search);
        btnSearch = (Button) findViewById(R.id.activity_staff_info_query_btn_search);
        imgBack = (ImageView) findViewById(R.id.activity_staff_info_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_staff_info_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_staff_info_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_staff_info_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_staff_info_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_staff_info_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_staff_info_header).findViewById(R.id.title);
        tvTitle.setText("人员信息查询");
        listview = (PullToRefreshListView) findViewById(R.id.activity_staff_info_query_pulltorefresh);
        listview.getRefreshableView().addFooterView(textView);
        listview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listview.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listview.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listview.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v!=null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), StaffInfoQueryActivity.this,StaffInfoQueryActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_staff_info_query_btn_search:
                    page = 1;
                    getQueryData();
                    break;
            }
        }
    }
}
