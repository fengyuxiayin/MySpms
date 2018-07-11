package com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.adapters.ProjectAdapter;
import com.example.lzc.myspms.adapters.ProjectRecheckAdapter;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.ProjectByJcIdModel;
import com.example.lzc.myspms.models.ProjectDataModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/31.
 */
public class SceneFragment extends BaseFragment  {
    public static final String TAG = SceneFragment.class.getSimpleName();
    private String qyId;
    private String jcId;
    private String zqjlId;
    private String jclx;
    private Gson gson;
    private PullToRefreshListView listView;
    private Button btnSave;
    private int page = 1;
    //存储应该显示在当前页面的所有信息
    private List<ProjectDataModel.ProjectDataMsgModel.ListBean> allList = new ArrayList<>();
    private TextView tvBottom;
    private int xmlx = 56;
    private TextView textView;
    private List<ProjectDataModel.ProjectDataMsgModel.ListBean> list = new ArrayList<>();
    private String startClass;
    private boolean isView = false;
    private ProjectAdapter projectAdapter;
    private ProjectDataModel.ProjectDataMsgModel projectDataMsgModel;
    private ProjectDataModel projectDataModel;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    //复查的项目数据
    private List<ProjectByJcIdModel.ProjectByJcIdMsgModel.ListBean> listRecheck = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        qyId = getArguments().getString("qyId");
        zqjlId = getArguments().getString("zqjlId");
        jclx = getArguments().getString("jclx");
        jcId = getArguments().getString("jcId");
        startClass = getArguments().getString("startClass");
        if ("CheckRecordItemActivity".equals(startClass)) {
            isView = true;
        }else{
            isView = false;
        }
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
                    if ("1".equals(jclx)) {
                        getNewCheckProjectData();
                    }
                    else if(jclx == null){//从企业信息查询的检查记录跳转过来的
                        getProjectData();
                    }else {
                        getReCheckProjectData();
                    }
                }

            }
        });
    }

    private void initView() {
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_project_no_network_connection);
        linearLayout1 = (LinearLayout) view.findViewById(R.id.fragment_project_ll);
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_project_pulltorefresh);
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        listView.getRefreshableView().addFooterView(textView);
        //设置listView的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    private void initData() {
        gson = new Gson();
    }
    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        if ("1".equals(jclx)) {
            getNewCheckProjectData();
        }
        else if(jclx == null){//从企业信息查询的检查记录跳转过来的
            getProjectData();
        }else {
            getReCheckProjectData();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        page = 1;
        if ("1".equals(jclx)) {
            getNewCheckProjectData();
        }
        else if(jclx == null){//从企业信息查询的检查记录跳转过来的
            getProjectData();
        }else {
            getReCheckProjectData();
        }
    }
    /**
     *
     *@desc 只根据qyId、jcId和项目类型来获取项目信息 跟检查类型无关
     *@date 2018/3/9 10:09
     */
    private void getProjectData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/checkProject/find")
                .addParams("qyId",qyId)
                .addParams("jcId",jcId)
                .addParams("xmlx",xmlx+"")
                .addParams("pn",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(getContext(),e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response);
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        projectDataModel = gson.fromJson(response, ProjectDataModel.class);
                        if (projectDataModel.isData()) {
                            projectDataMsgModel = gson.fromJson(projectDataModel.getMsg(), ProjectDataModel.ProjectDataMsgModel.class);
                            if (projectDataMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                list = projectDataMsgModel.getList();
                                Log.e(TAG, "onResponse: " + list.size());
                            } else {
                                list.addAll(projectDataMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                //显示项目信息
                                projectAdapter = new ProjectAdapter(list, getActivity(), isView, xmlx, zqjlId, jcId);
                                listView.setAdapter(projectAdapter);
                                listView.onRefreshComplete();
                            } else {
                                projectAdapter = new ProjectAdapter(list, getContext(), isView, xmlx, zqjlId, jcId);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    projectAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(projectAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(getContext(), projectDataModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    /**
     * @desc 获取复查的数据
     * @date 2017/12/20 16:51
     */
    private void getReCheckProjectData() {
        Log.e(TAG, "getReCheckProjectData: "+jcId );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/findByJcId")
                .addParams("jcId", jcId)
                .addParams("xmlx",xmlx+"")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(),e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: recheck" + response);
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        //数据里面checkinfo和jcxmid
                        ProjectByJcIdModel projectByJcIdModel = gson.fromJson(response, ProjectByJcIdModel.class);
                        if (projectByJcIdModel.isData()) {
                            ProjectByJcIdModel.ProjectByJcIdMsgModel projectByJcIdMsgModel = gson.fromJson(projectByJcIdModel.getMsg(), ProjectByJcIdModel.ProjectByJcIdMsgModel.class);
                            if (projectByJcIdMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                listRecheck.clear();
                                listRecheck = projectByJcIdMsgModel.getList();
                                Log.e(TAG, "onResponse: " + listRecheck.size());
                            } else {
                                listRecheck.addAll(projectByJcIdMsgModel.getList());
                            }
                            if (listRecheck.size() == 0) {
                                //显示项目信息
                                ProjectRecheckAdapter projectRecheckAdapter = new ProjectRecheckAdapter(listRecheck, getActivity(), isView, xmlx, zqjlId, jcId);
                                listView.setAdapter(projectRecheckAdapter);
                                listView.onRefreshComplete();
                            } else {
                                ProjectRecheckAdapter projectRecheckAdapter = new ProjectRecheckAdapter(listRecheck, getContext(), isView, xmlx, zqjlId, jcId);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    projectRecheckAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(projectRecheckAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(getContext(), projectByJcIdModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * @param
     * @desc 获得项目信息
     * @date 2017/12/7 11:08
     */
    private void getNewCheckProjectData() {
        Log.e(TAG, "getNewCheckProjectData: "+jcId );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/find")
                .addParams("qyId", qyId)
                .addParams("jcId", jcId)
                .addParams("xmlx", xmlx + "")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(),e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: newcheck" + response);
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        //数据里面checkinfo和jcxmid
                        projectDataModel = gson.fromJson(response, ProjectDataModel.class);
                        if (projectDataModel.isData()) {
                            projectDataMsgModel = gson.fromJson(projectDataModel.getMsg(), ProjectDataModel.ProjectDataMsgModel.class);
                            if (projectDataMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                list = projectDataMsgModel.getList();
                                Log.e(TAG, "onResponse: " + list.size());
                            } else {
                                list.addAll(projectDataMsgModel.getList());
                            }
                            if (list.size() == 0) {
                                //显示项目信息
                                projectAdapter = new ProjectAdapter(list, getActivity(), isView, xmlx, zqjlId, jcId);
                                listView.setAdapter(projectAdapter);
                                listView.onRefreshComplete();
                            } else {
                                projectAdapter = new ProjectAdapter(list, getContext(), isView, xmlx, zqjlId, jcId);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    projectAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(projectAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(getContext(), projectDataModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
