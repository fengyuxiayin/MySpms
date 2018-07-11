package com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.CheckItemsSearchResultAdapter;
import com.example.lzc.myspms.models.CheckItemsQueryModel;
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

public class CheckItemSearchResultActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String TAG = CheckItemSearchResultActivity.class.getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private TextView tvTitle;
    private PullToRefreshListView listView;
    private TextView tvBottom;
    //向服务器请求数据的页码
    private int page = 1;
    //从服务器拿回的数据 （每页）
    private List<CheckItemsQueryModel.CheckItemsQueryMsgModel.ListBean> list = new ArrayList<>();
    //传入adapter的数据
    private List<CheckItemsQueryModel.CheckItemsQueryMsgModel.ListBean> allList = new ArrayList<>();
    private CheckItemsSearchResultAdapter checkItemsSearchResultAdapter;
    //item中的下拉图标
    private ImageView imgPull;
    //判断当前是否是下拉箭头的标志
    private boolean isPullDown = true;
    //存储isPullDown信息
    private List<Boolean> isPullDownList = new ArrayList<>();
    //item中的两个初始隐藏布局
    private LinearLayout linearLayoutFirst;
    private LinearLayout linearLayoutSecond;
    //两个linearlayout中的按钮
    private Button btnViewProject;
    private Button btnEditProject;
    private Button btnDeleteProject;
    private Button btnViewRecord;
    private int position;
    private Gson gson;
    private String qymc;
    private String xmmc;
    private String xmbm;
    private String checkData;
    private String bhgjlStart;
    private String bhgjlEnd;
    private String yhg;
    private String whg;
    private String xmlx;
    //判断是不是首次创建xhlLoading
    private boolean isFirstCreateLoading;
    private TextView textView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_item_search_result);
        qymc = getIntent().getStringExtra("qymc");
        xmmc = getIntent().getStringExtra("xmmc");
        xmbm = getIntent().getStringExtra("xmbm");
        xmlx = getIntent().getStringExtra("xmlx");
        checkData = getIntent().getStringExtra("checkDate");
        bhgjlStart = getIntent().getStringExtra("bhgjlStart");
        bhgjlEnd = getIntent().getStringExtra("bhgjlEnd");
        yhg = getIntent().getStringExtra("yhg");
        whg = getIntent().getStringExtra("whg");
//        list = (List<CheckItemsQueryModel.CheckItemsQueryMsgModel.ListBean>) getIntent().getSerializableExtra("data");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(CheckItemSearchResultActivity.this, R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    getDataFromServer();
                }
            }
        });
        listView.setOnItemClickListener(this);
    }

    private void getDataFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/project/query")
                .addParams("qymc", qymc)
                .addParams("xmmc", xmmc)
                .addParams("xmbm", xmbm)
                .addParams("xmlx", xmlx)
                .addParams("checkData", checkData)
                .addParams("bhgjlStart", bhgjlStart)
                .addParams("bhgjlEnd", bhgjlEnd)
                .addParams("yhg", yhg)
                .addParams("pn", page + "")
                .addParams("whg", whg)
                .addParams("size", "10")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(CheckItemSearchResultActivity.this,e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        linearLayout.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        CheckItemsQueryModel checkItemsQueryModel = gson.fromJson(response, CheckItemsQueryModel.class);
                        if (checkItemsQueryModel.isData()) {
                            CheckItemsQueryModel.CheckItemsQueryMsgModel checkItemsQueryMsgModel = gson.fromJson(checkItemsQueryModel.getMsg(), CheckItemsQueryModel.CheckItemsQueryMsgModel.class);
                            Log.e(TAG, "onResponse: "+ checkItemsQueryMsgModel.getTotal());
                            if (checkItemsQueryMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (checkItemsQueryMsgModel.getList()==null) {
                                    list = new ArrayList<>();
                                }else{
                                    list = checkItemsQueryMsgModel.getList();
                                }
                            } else {
                                list.addAll(checkItemsQueryMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                checkItemsSearchResultAdapter = new CheckItemsSearchResultAdapter(list, CheckItemSearchResultActivity.this);
                                listView.setAdapter(checkItemsSearchResultAdapter);
                                listView.onRefreshComplete();
                            } else {
                                checkItemsSearchResultAdapter = new CheckItemsSearchResultAdapter(list, CheckItemSearchResultActivity.this);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    checkItemsSearchResultAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(checkItemsSearchResultAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            listView.onRefreshComplete();
                        }
                    }
                });
    }

    private void initData() {
        gson = new Gson();
        getDataFromServer();
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.activity_check_item_search_result_no_network_connection);
        imgBack = (ImageView) findViewById(R.id.activity_check_item_search_result_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_check_item_search_result_header).findViewById(R.id.videocall);
        tvTitle = (TextView) findViewById(R.id.activity_check_item_search_result_header).findViewById(R.id.title);
        tvTitle.setText("项目多维度查询");
        listView = (PullToRefreshListView) findViewById(R.id.activity_check_item_search_result_pulltorefresh);
        textView = new TextView(this);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("上拉加载更多数据");
        listView.getRefreshableView().addFooterView(textView);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
//        tvBottom = (TextView) findViewById(R.id.activity_check_item_search_result_tv_bottom);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v != null) {
            switch (v.getId()) {
                case R.id.back:
                    finish();
                    break;
                case R.id.videocall:
                    intent = new Intent();
                    intent.setClass(CheckItemSearchResultActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
//                case R.id.activity_check_items_search_result_item_btn_view_project:
//                    //查看
//                    intent.setClass(this,ProjectDetailActivity.class);
//                    intent.putExtra("xmId",list.get((Integer) v.getTag()).getXmid()+"");
//                    intent.putExtra("qymc",list.get((Integer) v.getTag()).getQymc()+"");
//                    startActivity(intent);
//                    break;
//                case R.id.activity_check_items_search_result_item_btn_edit_project:
//                    //编辑
//                    intent.setClass(this,AddProjectActivity.class);
//                    //qyId 是添加项目时需要 xmId是获取项目信息时需要 startClass是判断是哪一个activity
//                    intent.putExtra("qyId",list.get((Integer) v.getTag()).getQyId()+"");
//                    intent.putExtra("xmId",list.get((Integer) v.getTag()).getXmid()+"");
//                    intent.putExtra("qymc",list.get((Integer) v.getTag()).getQymc()+"");
//                    intent.putExtra("startClass","CheckItemSearchResultActivity");
//                    startActivity(intent);
//                    break;
//                case R.id.activity_check_items_search_result_item_btn_delete_project:
//                    //删除
//                    //    通过AlertDialog.Builder这个类来实例化一个AlertDialog的对象
//                    AlertDialog.Builder builder = new AlertDialog.Builder(CheckItemSearchResultActivity.this);
//                    //    设置Title的内容
//                    builder.setTitle("提示");
//                    //    设置Content来显示一个信息
//                    builder.setMessage("确定删除" + allList.get(position).getQymc() + "项目的所有信息吗");
//                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //删除项目信息
//                            OkHttpUtils.post()
//                                    .url(Constant.SERVER_URL + "/project/delete")
//                                    .addParams("id", allList.get(position).getXmid() + "")
//                                    .build().execute(new StringCallback() {
//                                @Override
//                                public void onError(Request request, Exception e) {
//                                    Log.e(TAG, "onError: " + e.getMessage());
//                                }
//
//                                @Override
//                                public void onResponse(String response) {
//                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                                    Toast.makeText(CheckItemSearchResultActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    });
//                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    //    显示出该对话框
//                    builder.show();
//                    break;
//                case R.id.activity_check_items_search_result_item_btn_view_record:
//                    //查看检查记录
//
//                    break;
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
