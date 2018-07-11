package com.example.lzc.myspms.fragments.homepagefragments;

import android.content.Intent;
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
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckProjectActivity;
import com.example.lzc.myspms.adapters.NotCheckAdapter;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CheckIsExistModel;
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

/**
 * Created by LZC on 2017/12/6.
 */

public class NotCheckFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    public static final String TAG = NotCheckFragment.class.getSimpleName();
    private PullToRefreshListView listView;
    private TextView textView;
    private int page = 1;
    private List<NotCheckModel.NotCheckMsgModel.ListBean> list = new ArrayList<>();
    private Gson gson;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_not_check, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取activity的标志位和qyId
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
                    getQueryData();
                }
            }
        });
        listView.setOnItemClickListener(this);
    }

    private void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        getQueryData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            page = 1;
            getQueryData();
        }
    }

    private void getQueryData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkInfo/progressUncheck")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .build()
                .execute(new StringCallback() {
                    private NotCheckAdapter notCheckAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        gson = new Gson();
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
                                Log.e(TAG, "onResponse: " + list.size());
                            } else {
                                list.addAll(notCheckMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                notCheckAdapter = new NotCheckAdapter(list, getContext());
                                listView.setAdapter(notCheckAdapter);
                                listView.onRefreshComplete();
                            } else {
                                notCheckAdapter = new NotCheckAdapter(list, getContext());
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    notCheckAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(notCheckAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        } else {
                            Toast.makeText(getContext(), notCheckModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        linearLayout = (LinearLayout) view.findViewById(R.id.activity_not_check_no_network_connection);
        linearLayout1 = (LinearLayout) view.findViewById(R.id.fragment_not_check_ll);
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_not_check_pulltorefresh);
        textView = new TextView(getContext());
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
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if (position - 1 < list.size()) {
            OkHttpUtils.post()
                    .url(Constant.SERVER_URL + "/checkInfo/isExist")
                    .addParams("zqjlId", list.get(position-1).getId() + "")
                    .addParams("jclx", "1")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            NetUtil.errorTip(getContext(), e.getMessage());
                        }

                        @Override
                        public void onResponse(String response) {
                            CheckIsExistModel checkIsExistModel = gson.fromJson(response, CheckIsExistModel.class);
                            if (checkIsExistModel.isData() == false) {//先判断这个检查记录是否已经存在服务器数据库
                                Log.e(TAG, "onResponse: save checkrecord");
                                OkHttpUtils.post()
                                        .url(Constant.SERVER_URL + "/checkInfo/save")

                                        .addParams("qyId", list.get(position-1).getQyId() + "")
                                        .addParams("zqjlId", list.get(position-1).getId() + "")
                                        .addParams("jclx", "1")
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
                                                OkHttpUtils.post()
                                                        .url(Constant.SERVER_URL + "/checkInfo/isExist")
                                                        .addParams("zqjlId", list.get(position-1).getId() + "")
                                                        .addParams("jclx", "1")
                                                        .build()
                                                        .execute(new StringCallback() {
                                                            @Override
                                                            public void onError(Request request, Exception e) {
                                                                NetUtil.errorTip(getContext(), e.getMessage());
                                                            }

                                                            @Override
                                                            public void onResponse(String response) {
                                                                CheckIsExistModel checkIsExistModel = gson.fromJson(response, CheckIsExistModel.class);
                                                                if (checkIsExistModel.isData()) {
                                                                    CheckIsExistModel.CheckIsExistMsgModel checkIsExistMsgModel = gson.fromJson(checkIsExistModel.getMsg(), CheckIsExistModel.CheckIsExistMsgModel.class);
                                                                    //数据库有数据，那么就跳转界面
                                                                    Intent intent = new Intent();
                                                                    intent.putExtra("jcId", checkIsExistMsgModel.getId() + "");
                                                                    intent.putExtra("qyId", checkIsExistMsgModel.getQyId() + "");
                                                                    intent.putExtra("jclx", "1");
                                                                    intent.putExtra("zqjlId", list.get(position-1).getId() + "");
                                                                    intent.putExtra("qymc", list.get(position-1).getQymc());
                                                                    intent.setClass(getContext(), CheckProjectActivity.class);
                                                                    getContext().startActivity(intent);
                                                                } else {
                                                                    Toast.makeText(getContext(), checkIsExistModel.getMsg(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });


                                            }
                                        });
                            } else {
                                //数据库有数据，那么就跳转界面
                                CheckIsExistModel.CheckIsExistMsgModel checkIsExistMsgModel = gson.fromJson(checkIsExistModel.getMsg(), CheckIsExistModel.CheckIsExistMsgModel.class);
                                Log.e(TAG, "onResponse: nosave" + checkIsExistMsgModel.getId() + "");
                                Intent intent = new Intent();
                                intent.putExtra("jcId", checkIsExistMsgModel.getId() + "");
                                intent.putExtra("qyId", checkIsExistMsgModel.getQyId() + "");
                                intent.putExtra("jclx", "1");
                                intent.putExtra("zqjlId", list.get(position-1).getId() + "");
                                intent.putExtra("qymc", list.get(position-1).getQymc());
                                intent.setClass(getContext(), CheckProjectActivity.class);
                                getContext().startActivity(intent);
                            }
                        }
                    });
        }

    }
}
