package com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckProjectActivity;
import com.example.lzc.myspms.adapters.CommunityCheckRecordAdapter;
import com.example.lzc.myspms.adapters.EnterpriseCheckRecordAdapter;
import com.example.lzc.myspms.models.CheckRecordInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.FindCheckInfoModel;
import com.example.lzc.myspms.models.QueryCheckRecordModel;
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

public class EnterpriseCheckRecordActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private PullToRefreshListView listView;
    //页码
    private int page = 1;
    //从上一页传进来的企业id
    private String qyId;
    //从上一页传进来的企业名称
    private String qymc;
    private Gson gson;
    //存储企业的所有检查记录的列表
    private List<CheckRecordInfoModel.CheckRecordMsgInfoModel.ListBean> checkRecordList = new ArrayList<>();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private TextView tvTitle;
    private TextView textView;
    private List<FindCheckInfoModel.FindCheckInfoMsgModel.ListBean> list = new ArrayList<>();
    private String loginId;
    private String startClass;//group community enterprise
    private CommunityCheckRecordAdapter communityCheckRecordAdapter;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_check_record);
        startClass = getIntent().getStringExtra("startClass");
        if (startClass.equals("MainActivity")) {
            loginId = getIntent().getStringExtra("loginId");
        } else {
            qyId = getIntent().getStringExtra("qyId");
            qymc = getIntent().getStringExtra("qymc");
        }
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        listView.setOnItemClickListener(this);
        //刷新监听
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(EnterpriseCheckRecordActivity.this, R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    if (startClass.equals("group")) {
                        getGroupCheckRecordData();
                    }else{
                        getEnterpriseData();
                    }
                }
            }
        });
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (startClass.equals("MainActivity")) {
            getLoginCheckRecordData();
        } else {
            if (startClass.equals("group")) {
                getGroupCheckRecordData();
            }else{
                getEnterpriseData();
            }
        }

    }

    private void getEnterpriseData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkInfo/findCheckInfo")
//                .addParams("jcdwlx", "3")
                .addParams("qyId",qyId)
                .build()
                .execute(new StringCallback() {

                    private EnterpriseCheckRecordAdapter enterpriseCheckRecordAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(EnterpriseCheckRecordActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        FindCheckInfoModel findCheckInfoModel = gson.fromJson(response, FindCheckInfoModel.class);
                        if (findCheckInfoModel.isData()) {
                            FindCheckInfoModel.FindCheckInfoMsgModel findCheckInfoMsgModel = gson.fromJson(findCheckInfoModel.getMsg(), FindCheckInfoModel.FindCheckInfoMsgModel.class);
                            list = findCheckInfoMsgModel.getList();
                            if (list==null) {
                                Toast.makeText(EnterpriseCheckRecordActivity.this, "当前企业没有检查数据", Toast.LENGTH_SHORT).show();
                            }else{
                                communityCheckRecordAdapter = new CommunityCheckRecordAdapter(list, EnterpriseCheckRecordActivity.this,"企业",qyId,"");
                                listView.setAdapter(communityCheckRecordAdapter);
                            }

                        }

                    }
                });
    }

    private void getLoginCheckRecordData() {
//        OkHttpUtils.post()
//                .url(Constant.SERVER_URL + "/baseEnterprise/queryCheckRecords")
//                .addParams("loginId", loginId)
//                .build()
//                .execute(new StringCallback() {
//                    private EnterpriseCheckRecordAdapter enterpriseCheckRecordAdapter;
//
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        Log.e(TAG, "onError: " + e.getMessage());
//                        NetUtil.errorTip(EnterpriseCheckRecordActivity.this,e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e(TAG, "onResponse: " + response);
//                        QueryCheckRecordModel queryCheckRecordModel = gson.fromJson(response, QueryCheckRecordModel.class);
//                        if (queryCheckRecordModel.isData()) {
//                            QueryCheckRecordModel.QueryCheckRecordMsgModel queryCheckRecordMsgModel = gson.fromJson(queryCheckRecordModel.getMsg(), QueryCheckRecordModel.QueryCheckRecordMsgModel.class);
////                            List<CheckRecordInfoModel.CheckRecordMsgInfoModel.ListBean> list = queryCheckRecordMsgModel.getList();
//                            if (queryCheckRecordMsgModel.getTotal() - page * 10 > 0) {
//                                textView.setText("上拉加载更多数据");
//                            } else {
//                                textView.setText("我也是有底线的");
//                            }
//                            if (page == 1) {
//                                list.clear();
//                                if (queryCheckRecordMsgModel.getList()==null) {
//                                    list = new ArrayList<QueryCheckRecordModel.QueryCheckRecordMsgModel.ListBean>();
//                                }else{
//                                    list = queryCheckRecordMsgModel.getList();
//                                }
//                                Log.e(TAG, "onResponse: "+ list.size() );
//                            } else {
//                                list.addAll(queryCheckRecordMsgModel.getList());
//                            }
//                            if (list.size() == 0) {
//                                enterpriseCheckRecordAdapter = new EnterpriseCheckRecordAdapter(list, getApplicationContext());
//                                listView.setAdapter(enterpriseCheckRecordAdapter);
//                                listView.onRefreshComplete();
//                            } else {
//                                enterpriseCheckRecordAdapter = new EnterpriseCheckRecordAdapter(list, getApplicationContext());
//                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
//                                if (page > 1) {
//                                    enterpriseCheckRecordAdapter.notifyDataSetChanged();
//                                } else {
//                                    listView.setAdapter(enterpriseCheckRecordAdapter);
//                                }
//                                listView.onRefreshComplete();
//                            }
//                        } else {
//                            Toast.makeText(EnterpriseCheckRecordActivity.this, queryCheckRecordModel.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

    /**
     * @param
     * @desc 获取小组的所有检查记录
     * @date 2017/12/4 15:44
     */
    private void getGroupCheckRecordData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkInfo/findCheckInfo")
                .addParams("jcdwlx", Constant.ACCOUNT_TYPE)
                .addParams("jcdwId", Constant.ENTERPRISE_ID)
                .build()
                .execute(new StringCallback() {

                    private EnterpriseCheckRecordAdapter enterpriseCheckRecordAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(EnterpriseCheckRecordActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
//                        QueryCheckRecordModel queryCheckRecordModel = gson.fromJson(response, QueryCheckRecordModel.class);
//                        if (queryCheckRecordModel.isData()) {
//                            QueryCheckRecordModel.QueryCheckRecordMsgModel queryCheckRecordMsgModel = gson.fromJson(queryCheckRecordModel.getMsg(), QueryCheckRecordModel.QueryCheckRecordMsgModel.class);
////                            List<CheckRecordInfoModel.CheckRecordMsgInfoModel.ListBean> list = queryCheckRecordMsgModel.getList();
//                            if (queryCheckRecordMsgModel.getTotal() - page * 10 > 0) {
//                                textView.setText("上拉加载更多数据");
//                            } else {
//                                textView.setText("我也是有底线的");
//                            }
//                            if (page == 1) {
//                                list.clear();
//                                if (queryCheckRecordMsgModel.getList()==null) {
//                                    list = new ArrayList<QueryCheckRecordModel.QueryCheckRecordMsgModel.ListBean>();
//                                }else{
//                                    list = queryCheckRecordMsgModel.getList();
//                                }
//                                Log.e(TAG, "onResponse: "+ list.size() );
//                            } else {
//                                list.addAll(queryCheckRecordMsgModel.getList());
//                            }
//                            if (list.size() == 0) {
//                                enterpriseCheckRecordAdapter = new EnterpriseCheckRecordAdapter(list, getApplicationContext());
//                                listView.setAdapter(enterpriseCheckRecordAdapter);
//                                listView.onRefreshComplete();
//                            } else {
//                                enterpriseCheckRecordAdapter = new EnterpriseCheckRecordAdapter(list, getApplicationContext());
//                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
//                                if (page > 1) {
//                                    enterpriseCheckRecordAdapter.notifyDataSetChanged();
//                                } else {
//                                    listView.setAdapter(enterpriseCheckRecordAdapter);
//                                }
//                                listView.onRefreshComplete();
//                            }
//                        }else{
//                            Toast.makeText(EnterpriseCheckRecordActivity.this, queryCheckRecordModel.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
                        FindCheckInfoModel findCheckInfoModel = gson.fromJson(response, FindCheckInfoModel.class);
                        if (findCheckInfoModel.isData()) {
                            FindCheckInfoModel.FindCheckInfoMsgModel findCheckInfoMsgModel = gson.fromJson(findCheckInfoModel.getMsg(), FindCheckInfoModel.FindCheckInfoMsgModel.class);
                            list = findCheckInfoMsgModel.getList();
                            if (list == null) {
                                Toast.makeText(EnterpriseCheckRecordActivity.this, "当前小组没有检查记录", Toast.LENGTH_SHORT).show();
                            }else{
                                communityCheckRecordAdapter = new CommunityCheckRecordAdapter(list, EnterpriseCheckRecordActivity.this,"小组","","");
                                listView.setAdapter(communityCheckRecordAdapter);
                            }
                        }

                    }
                });
    }

    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.activity_enterprise_check_record_pulltorefresh);
        textView = new TextView(this);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        listView.getRefreshableView().addFooterView(textView);
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        //设置listview只能上拉加载 设置加载的文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
        imgBack = (ImageView) findViewById(R.id.activity_enterprise_check_record_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_enterprise_check_record_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_enterprise_check_record_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_enterprise_check_record_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_enterprise_check_record_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_enterprise_check_record_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_enterprise_check_record_header).findViewById(R.id.title);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//        qyId = list.get(position-1).getQyId()+"";
//        qymc = list.get(position-1).getQymc();
//        Intent intent = new Intent();
//        intent.setClass(EnterpriseCheckRecordActivity.this, CheckProjectActivity.class);
//        qyId = getIntent().getStringExtra("qyId");
//        qymc = getIntent().getStringExtra("qymc");
//        intent.putExtra("qyId",qyId);
//        intent.putExtra("qymc",qymc);
//        intent.putExtra("jcId",list.get(position-1).getId()+"");
//        intent.putExtra("zqjlId",list.get(position-1).getJcrId()+"");
//        startActivity(intent);
//        OkHttpUtils.post()
//                .url(Constant.SERVER_URL+"/baseEnterprise/queryCheckRecords")
//                .addParams("qyId",qyId)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        Log.e(TAG, "onError: "+e.getMessage() );
//                        NetUtil.errorTip(EnterpriseCheckRecordActivity.this,e.getMessage());
//                    }
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e(TAG, "onResponse: "+response );
//                        QueryCheckRecordModel queryCheckRecordModel = gson.fromJson(response, QueryCheckRecordModel.class);
//                        if (queryCheckRecordModel.isData()) {
//                            QueryCheckRecordModel.QueryCheckRecordMsgModel queryCheckRecordMsgModel = gson.fromJson(queryCheckRecordModel.getMsg(), QueryCheckRecordModel.QueryCheckRecordMsgModel.class);
//                            Intent intent = new Intent();
//                            intent.setClass(EnterpriseCheckRecordActivity.this, CheckRecordItemActivity.class);
//                            intent.putExtra("qyId",qyId);
//                            intent.putExtra("qymc",qymc);
//                            intent.putExtra("jcId",list.get(position-1).getId()+"");
//                            startActivity(intent);
//                        }else{
//                            Toast.makeText(EnterpriseCheckRecordActivity.this,queryCheckRecordModel.getMsg() , Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });

    }

    @Override
    public void onClick(View v) {
        if (v != null) {
        SetMenuClick setMenuClick = new SetMenuClick(v.getId(), EnterpriseCheckRecordActivity.this,EnterpriseCheckRecordActivity.this);
        setMenuClick.setMenuClick();
        }
    }
}
