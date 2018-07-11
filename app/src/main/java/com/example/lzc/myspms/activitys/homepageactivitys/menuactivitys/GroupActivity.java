package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.GroupAdapter;
import com.example.lzc.myspms.adapters.ProjectAdapter;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.GroupDetailModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = GroupActivity.class.getSimpleName();
    private PullToRefreshListView listView;
    private TextView textView;
    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;
    private String ssId;
    private int page = 1;
    private List<GroupDetailModel.GroupDetailMsgModel.ListBean> list = new ArrayList<>();
    private TextView tvGroupName;
    private TextView tvPosition;
    private String groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ssId = getIntent().getStringExtra("ssId");
        groupName = getIntent().getStringExtra("groupName");
        Log.e(TAG, "onCreate: "+"ssId "+ssId+" 小组名称 "+groupName );
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
    }

    private void initData() {
        Log.e(TAG, "initData: " );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/team/findStaff")
                .addParams("id", ssId)
                .build()
                .execute(new StringCallback() {
                    private Gson gson;

                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(GroupActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        gson = new Gson();
                        GroupDetailModel groupDetailModel = gson.fromJson(response, GroupDetailModel.class);
                        if (groupDetailModel.isData()) {
                            GroupDetailModel.GroupDetailMsgModel groupDetailMsgModel = gson.fromJson(groupDetailModel.getMsg(), GroupDetailModel.GroupDetailMsgModel.class);
                            if (groupDetailMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (groupDetailMsgModel.getList()==null) {
                                    list = new ArrayList<>();
                                }else{
                                    list = groupDetailMsgModel.getList();
                                }
                                Log.e(TAG, "onResponse: " + list.size());
                            } else {
                                list.addAll(groupDetailMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                //显示项目信息
                                GroupAdapter groupAdapter = new GroupAdapter(list, GroupActivity.this);
                                listView.setAdapter(groupAdapter);
                                listView.onRefreshComplete();
                            } else {
                                GroupAdapter groupAdapter = new GroupAdapter(list, GroupActivity.this);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    groupAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(groupAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(GroupActivity.this, groupDetailModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.activity_group_header).findViewById(R.id.title);
        tvTitle.setText("小组管理");
        imgBack = (ImageView) findViewById(R.id.activity_group_header).findViewById(R.id.back);
        imgBack.setOnClickListener(this);
        imgVideoCall = (ImageView) findViewById(R.id.activity_group_header).findViewById(R.id.videocall);
        imgVideoCall.setOnClickListener(this);
        listView = (PullToRefreshListView) findViewById(R.id.activity_group_pulltorefresh);
        tvGroupName = (TextView) findViewById(R.id.activity_group_tv_name);
        tvGroupName.setText(groupName);
        tvPosition = (TextView) findViewById(R.id.activity_group_tv_lsdw);
        //这个地方时写死的 如果有其他街道让后台返回数据
        tvPosition.setText("夏庄街道");
        textView = new TextView(GroupActivity.this);
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
        if (v != null) {
            switch (v.getId()) {
                case R.id.back:
                    finish();
                    break;
                case R.id.videocall:
                    Intent intent = new Intent();
                    intent.setClass(GroupActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
