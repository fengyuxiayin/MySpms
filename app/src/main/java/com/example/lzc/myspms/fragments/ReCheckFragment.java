package com.example.lzc.myspms.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.ReCheckInfoAdapter;
import com.example.lzc.myspms.adapters.SimpleArrayAdapter;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.NewCheckInfoModel;
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
 * Created by LZC on 2017/10/27.
 */
public class ReCheckFragment extends BaseFragment  {
    public static final String TAG = ReCheckFragment.class.getSimpleName();
    private View view;
    private PullToRefreshListView listView;
    private int page = 1;
    private TextView textView;
    private Button btnSearch;
    private List<NewCheckInfoModel.NewCheckMsgInfoModel.ListBean> list = new ArrayList<>();
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private Spinner spLimit;
    private Spinner spAmount;
    private Spinner spDistance;
    private String rwzt = "";
    private String rwztNext = "0";
    private List<String> limitList;
    private List<String> amountList;
    private List<String> distanceList;
    private String orderWay = "0";
    private String rwztNot = "1";
    private RadioButton rbUncheck;
    private RadioButton rbCheck;
    private Button btnReset;
    private SimpleArrayAdapter<String> adapterLimit;
    private SimpleArrayAdapter<String> adapterAmount;
    private SimpleArrayAdapter<String> adapterDistance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recheck, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                getQueryData();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spLimit.setSelection(adapterLimit.getCount());
                spAmount.setSelection(adapterAmount.getCount());
                orderWay = "0";
                page = 1;
                getQueryData();
            }
        });
        spLimit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spAmount.setSelection(amountList.size()-1);
                spDistance.setSelection(distanceList.size()-1);
                if (position == 0) {
                    orderWay = "1";
                }else if(position ==1) {
                    orderWay = "2";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spLimit.setSelection(limitList.size()-1);
                orderWay = "0";
            }
        });
        spAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spLimit.setSelection(limitList.size()-1);
                spDistance.setSelection(distanceList.size()-1);
                if (position == 0) {
                    orderWay = "3";
                }else if(position ==1){
                    orderWay = "4";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spAmount.setSelection(amountList.size()-1);
                orderWay = "0";
            }
        });
        rbUncheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rwztNext = "0";
                    rwzt = "";
                    rwztNot = "1";
                    page = 1;
                    getQueryData();
                } else{
                    rwztNext = "1";
                    rwztNot = "";
                    rwzt = "1";
                    page = 1;
                    getQueryData();
                }
            }
        });
    }

    private void initData() {
        limitList = new ArrayList<>();
        limitList.add("按完成期限升序排列");
        limitList.add("按完成期限降序排列");
        limitList.add("整改期限");
        adapterLimit = new SimpleArrayAdapter<>(getContext(), R.layout.textview_only, limitList);
        adapterLimit.setDropDownViewResource(R.layout.spinner_item_textview);
        spLimit.setAdapter(adapterLimit);
        spLimit.setSelection(adapterLimit.getCount());
        amountList = new ArrayList<>();
        amountList.add("按项目数量升序排列");
        amountList.add("按项目数量降序排列");
        amountList.add("项目数量");
        adapterAmount = new SimpleArrayAdapter<>(getContext(), R.layout.textview_only, amountList);
        adapterAmount.setDropDownViewResource(R.layout.spinner_item_textview);
        spAmount.setAdapter(adapterAmount);
        spAmount.setSelection(adapterAmount.getCount());
        distanceList = new ArrayList<>();
        distanceList.add("按距离升序排列");
        distanceList.add("按距离降序排列");
        distanceList.add("距离");
        adapterDistance = new SimpleArrayAdapter<>(getContext(), R.layout.textview_only, distanceList);
        adapterDistance.setDropDownViewResource(R.layout.spinner_item_textview);
        spDistance.setAdapter(adapterDistance);
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

    private void initView() {
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_recheck_no_network_connection);
        linearLayout1 = (LinearLayout) view.findViewById(R.id.fragment_recheck_ll);
        listView = (PullToRefreshListView) view.findViewById(R.id.activity_recheck_pulltorefresh);
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        btnSearch = (Button) view.findViewById(R.id.activity_recheck_btn_search);
        btnReset = (Button) view.findViewById(R.id.activity_recheck_btn_reset);
        listView.getRefreshableView().addFooterView(textView);
        //设置listview的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
        //三个spinner 期限 项目发布数量 距离
        spLimit = (Spinner) view.findViewById(R.id.activity_recheck_sp_limit);
        spAmount = (Spinner) view.findViewById(R.id.activity_recheck_sp_amount);
        spDistance = (Spinner) view.findViewById(R.id.activity_recheck_sp_distance);
        //radiobutton
        rbUncheck = (RadioButton) view.findViewById(R.id.fragment_recheck_rb_no_recheck);
        rbCheck = (RadioButton) view.findViewById(R.id.fragment_recheck_rb_have_recheck);
    }
    private void getQueryData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL +"/checkInfo/find")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .addParams("jcdwlx", Constant.ACCOUNT_TYPE)
                .addParams("jcdwId", Constant.ENTERPRISE_ID  )
                .addParams("jclx","2")
                .addParams("rwzt",rwzt)//0 未检查 1 已检查 2检查中
                .addParams("orderWay",orderWay)
                .addParams("rwztNot", rwztNot)
                .build()
                .execute(new StringCallback() {
                    private ReCheckInfoAdapter reCheckInfoAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(),e.getMessage());
                        linearLayout.setVisibility(View.VISIBLE);
                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        linearLayout.setVisibility(View.GONE);
                        linearLayout1.setVisibility(View.VISIBLE);
                        Gson gson = new Gson();
                        NewCheckInfoModel newCheckInfoModel = gson.fromJson(response, NewCheckInfoModel.class);
                        NewCheckInfoModel.NewCheckMsgInfoModel newCheckMsgInfoModel = gson.fromJson(newCheckInfoModel.getMsg(), NewCheckInfoModel.NewCheckMsgInfoModel.class);
                        if (newCheckMsgInfoModel.getTotal() - page * 10 > 0) {
                            textView.setText("上拉加载更多数据");
                        } else {
                            textView.setText("我也是有底线的");
                        }
                        if (page == 1) {
                            list.clear();
                            if (newCheckMsgInfoModel.getList()==null) {
                                list = new ArrayList<NewCheckInfoModel.NewCheckMsgInfoModel.ListBean>();
                            }else{
                                list = newCheckMsgInfoModel.getList();
                            }
                            Log.e(TAG, "onResponse: "+ list.size() );
                        } else {
                            list.addAll(newCheckMsgInfoModel.getList());
                        }
                        if (list.size() == 0) {
                            reCheckInfoAdapter = new ReCheckInfoAdapter(list, getContext(), getActivity(),rwztNext);
                            listView.setAdapter(reCheckInfoAdapter);
                            listView.onRefreshComplete();
                        } else {
                            reCheckInfoAdapter = new ReCheckInfoAdapter(list, getContext(), getActivity(),rwztNext);
                            listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                            if (page > 1) {
                                reCheckInfoAdapter.notifyDataSetChanged();
                            } else {
                                listView.setAdapter(reCheckInfoAdapter);
                            }
                            listView.onRefreshComplete();
                        }
                    }
                });
    }
}
