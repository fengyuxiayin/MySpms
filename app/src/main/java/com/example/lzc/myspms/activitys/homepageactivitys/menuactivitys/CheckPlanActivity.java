package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

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
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.CheckPlanAdapter;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.NotCheckModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class CheckPlanActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = CheckPlanActivity.class.getSimpleName();
    private SearchView searchView;
    private Button btnSearch;
    private PullToRefreshListView listView;
    private Gson gson;
    private int page = 1;
    private TextView textView;
    private List<NotCheckModel.NotCheckMsgModel.ListBean> list = new ArrayList<>();
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private ImageView imgBack;
    private TextView tvTitle;
    private ImageView imgVideoCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_plan);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btnSearch.setOnClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(CheckPlanActivity.this, R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    getDataFromServer();
                }
            }
        });
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setFocusable(true);
        listView.setFocusableInTouchMode(true);
        listView.requestFocus();
        getDataFromServer();
    }

    private void getDataFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/jobCycleRecord/find")
                .addParams("jczt","0")
                .addParams("qymc",searchView.getQuery().toString())
                .addParams("pn",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(CheckPlanActivity.this,e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        //modle类和 NotCheckModel一样
                        NotCheckModel notCheckModel = gson.fromJson(response, NotCheckModel.class);
                        if (notCheckModel.isData()) {
                            NotCheckModel.NotCheckMsgModel notCheckMsgModel = gson.fromJson(notCheckModel.getMsg(), NotCheckModel.NotCheckMsgModel.class);
                            if (notCheckMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (notCheckMsgModel.getList()==null) {
                                    list = new ArrayList<NotCheckModel.NotCheckMsgModel.ListBean>();
                                }else{
                                    list = notCheckMsgModel.getList();
                                }
                                Log.e(TAG, "onResponse: "+ list.size() );
                            } else {
                                list.addAll(notCheckMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                CheckPlanAdapter checkPlanAdapter = new CheckPlanAdapter(list, CheckPlanActivity.this);
                                listView.setAdapter(checkPlanAdapter);
                                listView.onRefreshComplete();
                            } else {
                                CheckPlanAdapter checkPlanAdapter = new CheckPlanAdapter(list, CheckPlanActivity.this);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    checkPlanAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(checkPlanAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(CheckPlanActivity.this, notCheckModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.activity_check_plan_header).findViewById(R.id.back);
        tvTitle = (TextView) findViewById(R.id.activity_check_plan_header).findViewById(R.id.title);
        tvTitle.setText("检查计划");
        imgVideoCall = (ImageView) findViewById(R.id.activity_check_plan_header).findViewById(R.id.videocall);
        linearLayout = (LinearLayout) findViewById(R.id.activity_check_plan_no_network_connection);
        linearLayout1 = (LinearLayout) findViewById(R.id.activity_check_plan_ll);
        searchView = (SearchView) findViewById(R.id.activity_check_plan_sv);
        searchView.setIconifiedByDefault(false);
        btnSearch = (Button) findViewById(R.id.activity_check_plan_btn_search);
        listView = (PullToRefreshListView) findViewById(R.id.activity_check_plan_pulltorefresh);
        textView = new TextView(this);
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

    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            switch (v.getId()) {
                case R.id.activity_check_plan_btn_search:
                    page = 1;
                    getDataFromServer();
                    break;
                case R.id.back:
                    finish();
                    break;
                case R.id.videocall:
                    Intent intent = new Intent();
                    intent.setClass(this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
