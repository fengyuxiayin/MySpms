package com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys;

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
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.NoticeActivity;
import com.example.lzc.myspms.adapters.ProjectCheckRecordAdapter;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.ProjectCheckRecordModel;
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

public class ProjectCheckRecordActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = ProjectCheckRecordActivity.class.getSimpleName();
    private TextView tvEquipmentName;
    private TextView tvEnterpriseName;
    private PullToRefreshListView listView;
    private TextView textView;
    private ImageView imgBack;
    private ImageView imgVideocall;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;
    private TextView tvTitle;
    private int page = 1;
    private String jcxmId;
    private ProjectCheckRecordAdapter projectCheckRecordAdapter;
    private List<ProjectCheckRecordModel.ProjectCheckRecordMsgModel.PagerBean.ListBean> list = new ArrayList<>();
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_check_record);
        jcxmId = getIntent().getStringExtra("jcxmId");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(ProjectCheckRecordActivity.this, R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    getCheckRecord(gson);
                }
            }
        });
    }

    private void initData() {
        gson = new Gson();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCheckRecord(gson);
    }

    private void getCheckRecord(final Gson gson) {
        if (jcxmId != null) {
            OkHttpUtils.post()
                    .url(Constant.SERVER_URL + "/checkProject/checkRecord")
                    .addParams("jcxmId", jcxmId)
                    .addParams("pn", page + "")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            NetUtil.errorTip(ProjectCheckRecordActivity.this, e.getMessage());
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: " + response);
                            ProjectCheckRecordModel projectCheckRecordModel = gson.fromJson(response, ProjectCheckRecordModel.class);
                            if (projectCheckRecordModel.isData()) {
                                ProjectCheckRecordModel.ProjectCheckRecordMsgModel projectCheckRecordMsgModel = gson.fromJson(projectCheckRecordModel.getMsg(), ProjectCheckRecordModel.ProjectCheckRecordMsgModel.class);
                                tvEquipmentName.setText(projectCheckRecordMsgModel.getXmmc());
                                tvEnterpriseName.setText(projectCheckRecordMsgModel.getQymc());
                                ProjectCheckRecordModel.ProjectCheckRecordMsgModel.PagerBean pager = projectCheckRecordMsgModel.getPager();
                                if (pager != null) {
                                    list = pager.getList();
                                    Log.e(TAG, "onResponse: " + list.size());
                                    if (list != null) {
                                        if (pager.getTotal() - page * 10 > 0) {
                                            textView.setText("上拉加载更多数据");
                                        } else {
                                            textView.setText("我也是有底线的");
                                        }
                                        if (page == 1) {
                                            list.clear();
                                            if (pager.getList()==null) {
                                                list = new ArrayList<>();
                                            }else{
                                                list = pager.getList();
                                            }
                                            Log.e(TAG, "onResponse: " + list.size());
                                        } else {
                                            list.addAll(pager.getList());
                                        }
                                        if (list.size() == 0) {
                                            projectCheckRecordAdapter = new ProjectCheckRecordAdapter(list, ProjectCheckRecordActivity.this);
                                            listView.setAdapter(projectCheckRecordAdapter);
                                            listView.onRefreshComplete();
                                        } else {
                                            projectCheckRecordAdapter = new ProjectCheckRecordAdapter(list, ProjectCheckRecordActivity.this);
                                            listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                            if (page > 1) {
                                                projectCheckRecordAdapter.notifyDataSetChanged();
                                            } else {
                                                listView.setAdapter(projectCheckRecordAdapter);
                                            }
                                            listView.onRefreshComplete();
                                        }
                                    }
                                }
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "检查项目id为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        tvEquipmentName = (TextView) findViewById(R.id.activity_project_check_record_equipment_name);
        tvEnterpriseName = (TextView) findViewById(R.id.activity_project_check_record_enterprise_name);
        listView = (PullToRefreshListView) findViewById(R.id.activity_project_check_record_pulltorefresh);
        textView = new TextView(this);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        listView.getRefreshableView().addFooterView(textView);
        //设置listview只能上拉加载 设置加载的文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
        imgBack = (ImageView) findViewById(R.id.activity_project_check_record_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_project_check_record_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_project_check_record_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_project_check_record_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_project_check_record_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_project_check_record_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_project_check_record_header).findViewById(R.id.title);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v != null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), ProjectCheckRecordActivity.this,ProjectCheckRecordActivity.this);
            setMenuClick.setMenuClick();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pos = position - 1;
        Intent intent = new Intent();
        intent.setClass(this,ViewProjectActivity.class);
        intent.putExtra("jcxId",list.get(pos).getId()+"");
        startActivity(intent);
    }
}
