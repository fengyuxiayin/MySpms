package com.example.lzc.myspms.fragments.queryfragments.communityinfofragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.CommunityCheckRecordAdapter;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CheckRecordInfoModel;
import com.example.lzc.myspms.models.CommunityBasicInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.FindCheckInfoModel;
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
 * Created by LZC on 2017/10/31.
 */
public class CommunityCheckRecordFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    public static final String TAG = CommunityCheckRecordFragment.class.getSimpleName();
    private String sqId;
    private PullToRefreshListView listView;
    private List<FindCheckInfoModel.FindCheckInfoMsgModel.ListBean> list = new ArrayList<>();
    private CommunityCheckRecordAdapter communityCheckRecordAdapter;
    private TextView textView;
    private Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_check_record, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sqId = getArguments().getString("sqId");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        listView.setOnItemClickListener(this);
    }

    private void initView() {
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_check_record_pulltorefresh);
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        listView.getRefreshableView().addFooterView(textView);
        //设置listview的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    private void initData() {
        Log.e(TAG, "initData: "+sqId );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/checkInfo/findCheckInfo")
//                .addParams("jcdwlx","1")
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /checkInfo/findCheckInfo"+response );
                        FindCheckInfoModel findCheckInfoModel = gson.fromJson(response, FindCheckInfoModel.class);
                        if (findCheckInfoModel.isData()) {
                            FindCheckInfoModel.FindCheckInfoMsgModel findCheckInfoMsgModel = gson.fromJson(findCheckInfoModel.getMsg(), FindCheckInfoModel.FindCheckInfoMsgModel.class);
                            list = findCheckInfoMsgModel.getList();
                        }
                        if (list==null) {
                            Toast.makeText(getContext(), "当前社区没有检查记录", Toast.LENGTH_SHORT).show();
                        }else{
                            communityCheckRecordAdapter = new CommunityCheckRecordAdapter(list,getContext(),"社区","",sqId);
                            listView.setAdapter(communityCheckRecordAdapter);
                        }
                    }
                });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pos = position-1;
        if(pos<list.size()){
            Intent intent = new Intent();

        }
    }
}
