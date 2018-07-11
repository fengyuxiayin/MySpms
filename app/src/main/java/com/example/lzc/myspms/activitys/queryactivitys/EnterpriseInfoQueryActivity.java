package com.example.lzc.myspms.activitys.queryactivitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.EnterpriseInfoQueryAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.EnterpriseInfoQueryModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseInfoQueryActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private final String TAG = this.getClass().getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private PullToRefreshListView listView;
    //向服务器请求数据的页码
    private int page = 1;
    //从服务器拿回的数据 （每页）
    private List<EnterpriseInfoQueryModel.ListSet.ListBean> list = new ArrayList<>();
    //传入adapter的数据
    private List<EnterpriseInfoQueryModel.ListSet.ListBean> allList = new ArrayList<>();
    //存储isPullDown信息
    private List<Boolean> isPullDownList = new ArrayList<>();
    //记录点击的是listview的哪个条目
    private int position;
    //判断是不是首次创建xhlLoading
    //从上个页面传过来的数据
    private String startClass;
    private String sqId;
    private String tzeStart;
    private String tzeEnd;
    private String cyryslStart;
    private String cyryslEnd;
    private String hylbdm;
    private String yyeStart;
    private String yyeEnd;
    private List<String> jjlxdmArr = new ArrayList<>();
    //判断是不是checkfragment的标志
    private boolean isCheckFragment;
    private Gson gson;
    private String listToJson;
    private TextView textView;
    private LinearLayout llSearch;
    private Button btnSearch;
    private TextView tvTitle;
    private ClearEditText etSearch;
    private ImageView imgNotice;
    private ImageView imgMessage;
    private ImageView imgCall;
    private ImageView imgAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_info_query);
        startClass = getIntent().getStringExtra("startClass");
        Log.e(TAG, "onCreate: " + startClass);
        if (startClass.equals("CheckFragment")) {
            isCheckFragment = true;
        } else {
            isCheckFragment = false;
            //从辖区企业跳转过来的数据
            sqId = getIntent().getStringExtra("sqId");
            Log.e(TAG, "onCreate: sqId" + sqId);
            tzeStart = getIntent().getStringExtra("tzeStart");
            Log.e(TAG, "onCreate: tzeStart" + tzeStart);
            tzeEnd = getIntent().getStringExtra("tzeEnd");
            Log.e(TAG, "onCreate: tzeEnd" + tzeEnd);
            cyryslStart = getIntent().getStringExtra("cyryslStart");
            Log.e(TAG, "onCreate: cyryslStart" + cyryslStart);
            cyryslEnd = getIntent().getStringExtra("cyryslEnd");
            Log.e(TAG, "onCreate: cyryslEnd" + cyryslEnd);
            hylbdm = getIntent().getStringExtra("hylbdm");
            Log.e(TAG, "onCreate: hylbdm" + hylbdm);
            yyeStart = getIntent().getStringExtra("yyeStart");
            Log.e(TAG, "onCreate: yyeStart" + yyeStart);
            yyeEnd = getIntent().getStringExtra("yyeEnd");
            Log.e(TAG, "onCreate: yyeEnd" + yyeEnd);
            listToJson = (String) getIntent().getSerializableExtra("jjlxdmArr");
        }
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setFocusable(true);
        page = 1;
        //根据谁启动了这个activity来加载不同的数据 目前有checkfragment 和AreaEnterpriseActivity这两个
        if (isCheckFragment) {
            getQueryData();
        } else {
            getAreaQueryData();
        }
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(EnterpriseInfoQueryActivity.this, R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    getQueryData();
                }
            }
        });
        btnSearch.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
    }

    private void initData() {
        //根据谁启动了这个activity来加载不同的数据 目前有checkfragment 和AreaEnterpriseActivity这两个
//        if (isCheckFragment) {
//            getQueryData();
//        } else {
//            getAreaQueryData();
//        }
    }

    /**
     * @param
     * @desc 从服务器获取辖区企业的数据
     * @date 2017/12/15 10:29
     */
    private void getAreaQueryData() {
        gson = new Gson();
        llSearch.setVisibility(View.GONE);
        Log.e(TAG, "getAreaQueryData: listToJson" + listToJson);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/find")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .addParams("sqId", sqId)
                .addParams("tzeStart", tzeStart)
                .addParams("tzeEnd", tzeEnd)
                .addParams("cyryslStart", cyryslStart)
                .addParams("cyryslEnd", cyryslEnd)
                .addParams("hylbdm", hylbdm)
                .addParams("yyeStart", yyeStart)
                .addParams("yyeEnd", yyeEnd)
                .addParams("jjlxdmArr[]", listToJson)
                .build()
                .execute(new StringCallback() {
                    private EnterpriseInfoQueryAdapter enterpriseInfoQueryAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(EnterpriseInfoQueryActivity.this, e.getMessage());

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        Gson gson = new Gson();
                        EnterpriseInfoQueryModel enterpriseInfoQueryModel = gson.fromJson(response, EnterpriseInfoQueryModel.class);
                        if (enterpriseInfoQueryModel.isData()) {
                            EnterpriseInfoQueryModel.ListSet listSet = gson.fromJson(enterpriseInfoQueryModel.getMsg(), EnterpriseInfoQueryModel.ListSet.class);
                            if (listSet.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (listSet.getList()==null) {
                                    list = new ArrayList<EnterpriseInfoQueryModel.ListSet.ListBean>();
                                }else{
                                    list = listSet.getList();
                                }
                                Log.e(TAG, "onResponse: " + list.size());
                            } else {
                                list.addAll(listSet.getList());
                            }
                            if (list.size() == 0) {
                                enterpriseInfoQueryAdapter = new EnterpriseInfoQueryAdapter(list, getApplicationContext(), EnterpriseInfoQueryActivity.this);
                                listView.setAdapter(enterpriseInfoQueryAdapter);
                                listView.onRefreshComplete();
                            } else {
                                enterpriseInfoQueryAdapter = new EnterpriseInfoQueryAdapter(list, getApplicationContext(), EnterpriseInfoQueryActivity.this);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    enterpriseInfoQueryAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(enterpriseInfoQueryAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        } else {
                            Toast.makeText(EnterpriseInfoQueryActivity.this, enterpriseInfoQueryModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void initView() {
        btnSearch = (Button) findViewById(R.id.activity_enterprise_info_query_btn_search);
        llSearch = (LinearLayout) findViewById(R.id.activity_enterprise_info_ll_query);
        etSearch = (ClearEditText) findViewById(R.id.activity_enterprise_info_query_et_search);
        textView = new TextView(this);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        imgBack = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_enterprise_info_query_header).findViewById(R.id.title);
        tvTitle.setText("企业信息查询");
        listView = (PullToRefreshListView) findViewById(R.id.activity_enterprise_info_query_pulltorefresh);
        listView.getRefreshableView().addFooterView(textView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    @Override
    public void onClick(final View v) {
        final Gson gson = new Gson();
        Intent intent = new Intent();
        if (v != null) {
            switch (v.getId()) {
                case R.id.back:
                    this.finish();
                    break;
                case R.id.videocall:

                    break;
                case R.id.activity_enterprise_info_query_btn_search:
                    page = 1;
                    //根据谁启动了这个activity来加载不同的数据 目前有checkfragment 和AreaEnterpriseActivity这两个
                    if (isCheckFragment) {
                        getQueryData();
                    } else {
                        getAreaQueryData();
                    }
                    break;
            }
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), EnterpriseInfoQueryActivity.this,EnterpriseInfoQueryActivity.this);
            setMenuClick.setMenuClick();
        }
    }

    /**
     * @param
     * @desc 获取checkfragment页面跳转查询到的数据
     * @date 2017/12/1 9:26
     */
    private void getQueryData() {
        gson = new Gson();
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/find")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .addParams("qymc", etSearch.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    private EnterpriseInfoQueryAdapter enterpriseInfoQueryAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(EnterpriseInfoQueryActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        Gson gson = new Gson();
                        EnterpriseInfoQueryModel enterpriseInfoQueryModel = gson.fromJson(response, EnterpriseInfoQueryModel.class);
                        if (enterpriseInfoQueryModel.isData()) {
                            EnterpriseInfoQueryModel.ListSet listSet = gson.fromJson(enterpriseInfoQueryModel.getMsg(), EnterpriseInfoQueryModel.ListSet.class);
                            if (listSet.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (listSet.getList() != null) {
                                    list = listSet.getList();
                                }else{
                                    list = new ArrayList<EnterpriseInfoQueryModel.ListSet.ListBean>();
                                }
                            } else {
                                list.addAll(listSet.getList());
                            }
                            if (list.size() == 0) {
                                enterpriseInfoQueryAdapter = new EnterpriseInfoQueryAdapter(list, getApplicationContext(), EnterpriseInfoQueryActivity.this);
                                listView.setAdapter(enterpriseInfoQueryAdapter);
                                listView.onRefreshComplete();
                            } else {
                                enterpriseInfoQueryAdapter = new EnterpriseInfoQueryAdapter(list, getApplicationContext(), EnterpriseInfoQueryActivity.this);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    enterpriseInfoQueryAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(enterpriseInfoQueryAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        } else {
                            Toast.makeText(EnterpriseInfoQueryActivity.this, enterpriseInfoQueryModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
