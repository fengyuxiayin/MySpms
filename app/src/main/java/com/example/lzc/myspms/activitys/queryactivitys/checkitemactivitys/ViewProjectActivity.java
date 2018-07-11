package com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.ViewProjectAdapter;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.FindProjectPointsModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class ViewProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ViewProjectActivity.class.getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private TextView tvTitle;
    private TextView tvProjectName;
    private TextView tvEnterpriseName;
    private TextView tvCheckTime;
    private TextView tvCheckPeople;
    private TextView tvCheckResult;
    private String jcxId;
    private PullToRefreshListView listView;
    private TextView textView;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);
        jcxId = getIntent().getStringExtra("jcxId");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);

        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkPoint/findProjectAndPoints")
                .addParams("jcxId", jcxId)
                .build()
                .execute(new StringCallback() {
                    private ViewProjectAdapter viewProjectAdapter;
                    private Gson gson;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                        NetUtil.errorTip(ViewProjectActivity.this,e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        gson = new Gson();
                        FindProjectPointsModel findProjectPointsModel = gson.fromJson(response, FindProjectPointsModel.class);
                        if (findProjectPointsModel.isData()) {
                            FindProjectPointsModel.FindProjectPointsMsgModel findProjectPointsMsgModel = gson.fromJson(findProjectPointsModel.getMsg(), FindProjectPointsModel.FindProjectPointsMsgModel.class);
                            tvProjectName.setText(findProjectPointsMsgModel.getXmmc());
                            tvEnterpriseName.setText(findProjectPointsMsgModel.getQyMc());
                            tvCheckPeople.setText(findProjectPointsMsgModel.getJcry());
                            tvCheckResult.setText("1".equals(findProjectPointsMsgModel.getJcjg()) ? "合格" : "不合格");
                            tvCheckTime.setText(DateUtil.long2Date(findProjectPointsMsgModel.getJckssj()));
                            viewProjectAdapter = new ViewProjectAdapter(findProjectPointsMsgModel.getPoints(), ViewProjectActivity.this, findProjectPointsMsgModel.getXmmc());
                            listView.setAdapter(viewProjectAdapter);
                        } else {
                            Toast.makeText(ViewProjectActivity.this, findProjectPointsModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.activity_view_project_no_network_connection);
        linearLayout1 = (LinearLayout) findViewById(R.id.activity_view_project_ll);
        imgBack = (ImageView) findViewById(R.id.activity_view_project_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_view_project_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_view_project_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_view_project_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_view_project_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_view_project_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_view_project_header).findViewById(R.id.title);
        tvProjectName = (TextView) findViewById(R.id.activity_view_project_project_name);
        tvEnterpriseName = (TextView) findViewById(R.id.activity_view_project_enterprise_name);
        tvCheckTime = (TextView) findViewById(R.id.activity_view_project_check_time);
        tvCheckPeople = (TextView) findViewById(R.id.activity_view_project_check_people);
        tvCheckResult = (TextView) findViewById(R.id.activity_view_project_check_result);
        listView = (PullToRefreshListView) findViewById(R.id.activity_view_project_pulltorefresh);
//        textView = new TextView(this);
//        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextSize(16);
//        textView.setText("我也是有底线的");
//        listView.getRefreshableView().addFooterView(textView);
        //设置listView的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), ViewProjectActivity.this,ViewProjectActivity.this);
            setMenuClick.setMenuClick();
        }
    }
}
