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
import com.example.lzc.myspms.adapters.CommunityInfoQueryAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.CommunityInfoQueryModel;
import com.example.lzc.myspms.models.Constant;
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

public class CommunityInfoQueryActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = CommunityInfoQueryActivity.class.getSimpleName();
    private PullToRefreshListView listview;
    private TextView tvBottom;
    //向服务器请求数据的页码
    private int page = 1;
    private Gson gson;
    //从服务器拿回的数据 （每页）
    private List<CommunityInfoQueryModel.CommunityInfoQueryMsgModel.ListBean> list = new ArrayList<>();
    //传入adapter的数据
    private List<CommunityInfoQueryModel.CommunityInfoQueryMsgModel.ListBean> allList = new ArrayList<>();
    //判断当前是否是下拉箭头的标志
    private boolean isPullDown = true;
    //存储isPullDown信息
    private List<Boolean> isPullDownList = new ArrayList<>();
    private CommunityInfoQueryModel communityInfoQueryModel;
    private CommunityInfoQueryModel.CommunityInfoQueryMsgModel communityInfoQueryMsgModel;
    //当前那个条目被点击
    private int position;
    private TextView textView;
    private Button btnSearch;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private TextView tvTitle;
    private ClearEditText etSearch;
    private ImageView imgMessage;
    private ImageView imgNotice;
    private ImageView imgCall;
    private ImageView imgAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_info_query);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        page = 1;
        getQueryData();
        listview.setFocusable(true);
        listview.setFocusableInTouchMode(true);
        listview.requestFocus();
        super.onResume();
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_edit_community_info_header).findViewById(R.id.title);
        tvTitle.setText("社区信息查询");
        etSearch = (ClearEditText) findViewById(R.id.activity_community_info_query_et_search);
        btnSearch = (Button) findViewById(R.id.activity_community_info_query_btn_search);
        textView = new TextView(this);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        textView.setText("我也是有底线的");
        listview = (PullToRefreshListView) findViewById(R.id.activity_community_info_query_pulltorefresh);
        listview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listview.getRefreshableView().addFooterView(textView);
        listview.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listview.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listview.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }
    private void initData() {
    }
    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listview.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listview.onRefreshComplete();
                            Toast.makeText(CommunityInfoQueryActivity.this, R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
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

/**
 *
 *@desc 获取查询到的社区信息
 *@param
 *@date 2017/12/13 9:07
*/
    private void getQueryData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/community/find")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .addParams("keyword1", etSearch.getText().toString().trim())
                .build()
                .execute(new StringCallback() {

                    private CommunityInfoQueryAdapter communityInfoQueryAdapter;
                    
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(CommunityInfoQueryActivity.this,e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        gson = new Gson();
                        communityInfoQueryModel = gson.fromJson(response, CommunityInfoQueryModel.class);
                        if (communityInfoQueryModel.isData()) {
                            communityInfoQueryMsgModel = gson.fromJson(communityInfoQueryModel.getMsg(), CommunityInfoQueryModel.CommunityInfoQueryMsgModel.class);
                            //如果list为空证明没有该企业，否则展示搜索到的结果
                            if (communityInfoQueryMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (communityInfoQueryMsgModel.getList()==null) {
                                    list = new ArrayList<CommunityInfoQueryModel.CommunityInfoQueryMsgModel.ListBean>();
                                }else{
                                    list = communityInfoQueryMsgModel.getList();
                                }
                                Log.e(TAG, "onResponse: "+ list.size() );
                            } else {
                                list.addAll(communityInfoQueryMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                communityInfoQueryAdapter = new CommunityInfoQueryAdapter(list,getApplicationContext(),CommunityInfoQueryActivity.this);
                                listview.setAdapter(communityInfoQueryAdapter);
                                listview.onRefreshComplete();
                            } else {
                                communityInfoQueryAdapter = new CommunityInfoQueryAdapter(list,getApplicationContext(),CommunityInfoQueryActivity.this);
                                listview.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    communityInfoQueryAdapter.notifyDataSetChanged();
                                } else {
                                    listview.setAdapter(communityInfoQueryAdapter);
                                }
                                listview.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(CommunityInfoQueryActivity.this, communityInfoQueryModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(final View v) {
        Intent intent = new Intent();

        if (v!=null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), CommunityInfoQueryActivity.this,CommunityInfoQueryActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_community_info_query_btn_search:
                    page = 1;
                    getQueryData();
                    break;
            }
        }
    }

}
