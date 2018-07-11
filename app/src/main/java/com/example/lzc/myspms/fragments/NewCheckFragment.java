package com.example.lzc.myspms.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.NewCheckInfoAdapter;
import com.example.lzc.myspms.adapters.SimpleArrayAdapter;
import com.example.lzc.myspms.adapters.SimpleTreeAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
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
public class NewCheckFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    public static final String TAG = NewCheckFragment.class.getSimpleName();
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
    private ClearEditText etCompony;
    private ClearEditText etTask;
    private RadioButton rbUnCheck;
    private RadioButton rbChecked;
    private String rwzt = "";
    //升降序的字段
    private String orderWay = "0";
    //三个spinner的数据源
    private List<String> limitList= new ArrayList<>();
    private List<String> amountList = new ArrayList<>();
    private List<String> distanceList = new ArrayList<>();
    //监管等级的字段
    private String jgdjItem = "";
    private CheckBox cbAll;
    private CheckBox cbA;
    private CheckBox cbB;
    private CheckBox cbC;
    private CheckBox cbD;
    private String rwztNot = "1";
    private String rwztNext = "0";
    private Button btnReset;
    private SimpleArrayAdapter<String> adapterLimit;
    private SimpleArrayAdapter<String> adapterAmount;
    private SimpleArrayAdapter<String> adapterDistance;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_new_check, container, false);
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
                    getData();
                }
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                getData();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                spLimit.setSelection(adapterLimit.getCount());
                spAmount.setSelection(adapterAmount.getCount());
                spDistance.setSelection(adapterDistance.getCount());
                cbAll.setChecked(false);
                cbA.setChecked(false);
                cbB.setChecked(false);
                cbC.setChecked(false);
                cbD.setChecked(false);
                orderWay = "0";
                etCompony.setText("");
                etTask.setText("");
                jgdjItem = "";

                getData();
            }
        });
        rbUnCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rwzt = "";
                    rwztNext = "0";
                    rwztNot = "1";
                    page = 1;
                    getData();
                } else{
                    rwztNext = "1";
                    rwztNot = "";
                    rwzt = "1";
                    page = 1;
                    getData();
                }
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
                spAmount.setSelection(limitList.size()-1);
                orderWay = "0";
            }
        });
        spDistance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spLimit.setSelection(limitList.size()-1);
                spAmount.setSelection(amountList.size()-1);
                if (position == 0) {
                    orderWay = "5";
                }else if(position ==1){
                    orderWay = "6";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spDistance.setSelection(limitList.size()-1);
                orderWay = "0";
            }
        });
        cbAll.setOnCheckedChangeListener(this);
        cbAll.setTag(0);
        cbA.setOnCheckedChangeListener(this);
        cbA.setTag(1);
        cbB.setOnCheckedChangeListener(this);
        cbB.setTag(2);
        cbC.setOnCheckedChangeListener(this);
        cbC.setTag(3);
        cbD.setOnCheckedChangeListener(this);
        cbD.setTag(4);
    }

    private void initData() {
//        spLimit.setBackground(getResources().getDrawable(R.drawable.new_check_pull_bg));
//        limitList.add("不选择");
        limitList.add("按完成期限升序排列");
        limitList.add("按完成期限降序排列");
        limitList.add("完成期限");
        adapterLimit = new SimpleArrayAdapter<>(getContext(), R.layout.textview_only, limitList);
        adapterLimit.setDropDownViewResource(R.layout.spinner_item_textview);
        spLimit.setAdapter(adapterLimit);
        spLimit.setSelection(adapterLimit.getCount());
        amountList = new ArrayList<>();
//        amountList.add("不选择");
        amountList.add("按项目数量升序排列");
        amountList.add("按项目数量降序排列");
        amountList.add("项目数量");
        adapterAmount = new SimpleArrayAdapter<>(getContext(), R.layout.textview_only, amountList);
        adapterAmount.setDropDownViewResource(R.layout.spinner_item_textview);
        spAmount.setSelection(adapterAmount.getCount());
        spAmount.setAdapter(adapterAmount);
        distanceList = new ArrayList<>();
//        distanceList.add("不选择");
        distanceList.add("按距离升序排列");
        distanceList.add("按距离降序排列");
        distanceList.add("距离");
        adapterDistance = new SimpleArrayAdapter<>(getContext(), R.layout.textview_only, distanceList);
        adapterDistance.setDropDownViewResource(R.layout.spinner_item_textview);
        spDistance.setAdapter(adapterDistance);
        spDistance.setSelection(adapterLimit.getCount());
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        getData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            page = 1;
            getData();
        }
    }

    private void initView() {
//        linearLayout = (LinearLayout) view.findViewById(R.id.activity_new_check_no_network_connection);
//        linearLayout1 = (LinearLayout) view.findViewById(R.id.activity_new_check_ll);
        listView = (PullToRefreshListView) view.findViewById(R.id.activity_new_check_pulltorefresh);
//        searchView = (SearchView) view.findViewById(R.id.activity_new_check_sv);
//        searchView.setIconifiedByDefault(false);
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        btnSearch = (Button) view.findViewById(R.id.activity_new_check_btn_search);
        btnReset = (Button) view.findViewById(R.id.activity_new_check_btn_reset);
        listView.getRefreshableView().addFooterView(textView);
        //设置listview的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
        //三个spinner 期限 项目发布数量 距离
        spLimit = (Spinner) view.findViewById(R.id.activity_new_check_sp_limit);
        spAmount = (Spinner) view.findViewById(R.id.activity_new_check_sp_amount);
        spDistance = (Spinner) view.findViewById(R.id.activity_new_check_sp_distance);
        //两个输入框
        etCompony = (ClearEditText) view.findViewById(R.id.activity_new_check_et_compony);
        etTask = (ClearEditText) view.findViewById(R.id.activity_new_check_et_task);
        //radiobutton 已检查 未检查
        rbUnCheck = (RadioButton) view.findViewById(R.id.fragment_new_check_rb_no_check);
        rbChecked = (RadioButton) view.findViewById(R.id.fragment_new_check_rb_have_check);
        //checkbox
        cbAll = (CheckBox) view.findViewById(R.id.activity_new_check_cb_all);
        cbA = (CheckBox) view.findViewById(R.id.activity_new_check_cb_a);
        cbB = (CheckBox) view.findViewById(R.id.activity_new_check_cb_b);
        cbC = (CheckBox) view.findViewById(R.id.activity_new_check_cb_c);
        cbD = (CheckBox) view.findViewById(R.id.activity_new_check_cb_d);
    }

    private void getData() {
        jgdjItem = "";
        if (cbAll.isChecked()) {
            jgdjItem = "A,B,C,D";
        }
        if(cbA.isChecked()){
            if (jgdjItem.length()>0) {
                jgdjItem = jgdjItem+",A";
            }else{
                jgdjItem = "A";
            }
        }
        if(cbB.isChecked()){
            if (jgdjItem.length()>0) {
                jgdjItem = jgdjItem+",B";
            }else{
                jgdjItem = "B";
            }
        }
        if(cbC.isChecked()){
            if (jgdjItem.length()>0) {
                jgdjItem = jgdjItem+",C";
            }else{
                jgdjItem = "C";
            }
        }
        if(cbD.isChecked()){
            if (jgdjItem.length()>0) {
                jgdjItem = jgdjItem+",D";
            }else{
                jgdjItem = "D";
            }
        }
        Log.e(TAG, "getData: "+"jcdwlx"+Constant.ACCOUNT_TYPE+"jcdwId"+Constant.ENTERPRISE_ID+"RWZT"+rwzt );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL +"/checkInfo/find")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .addParams("jcdwlx", Constant.ACCOUNT_TYPE)
                .addParams("jcdwId", Constant.ENTERPRISE_ID)
                .addParams("jclx","1")
                .addParams("rwzt",rwzt)// ""是未检查 "1"是已检查
                .addParams("qymc", etCompony.getText().toString().trim())
                .addParams("rwmc",etTask.getText().toString().trim())
                .addParams("orderWay",orderWay)
                .addParams("jgfjItem",jgdjItem)
                .addParams("rwztNot", rwztNot)
                .build()
                .execute(new StringCallback() {
                    private NewCheckInfoAdapter newCheckInfoAdapter;
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(),e.getMessage());
//                        linearLayout.setVisibility(View.VISIBLE);
//                        linearLayout1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
//                        linearLayout.setVisibility(View.GONE);
//                        linearLayout1.setVisibility(View.VISIBLE);
                        Gson gson = new Gson();
                        NewCheckInfoModel newCheckInfoModel = gson.fromJson(response, NewCheckInfoModel.class);
                        if (newCheckInfoModel.isData()) {
                            NewCheckInfoModel.NewCheckMsgInfoModel newCheckMsgInfoModel = gson.fromJson(newCheckInfoModel.getMsg(), NewCheckInfoModel.NewCheckMsgInfoModel.class);
                            if (newCheckMsgInfoModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                // TODO: 2018/5/15 null的问题
                                if (newCheckMsgInfoModel.getList()==null) {
                                    list = new ArrayList<NewCheckInfoModel.NewCheckMsgInfoModel.ListBean>();
                                }else{
                                    list = newCheckMsgInfoModel.getList();
                                }
                            } else {
                                list.addAll(newCheckMsgInfoModel.getList());
                            }
                            if (list.size() == 0) {
                                newCheckInfoAdapter = new NewCheckInfoAdapter(list, getContext(), getActivity(),rwztNext);
                                listView.setAdapter(newCheckInfoAdapter);
                                listView.onRefreshComplete();
                            } else {
//                                for (int i = 0; i < list.size(); i++) {
//                                    if (list.get(i).getRwzt()==2) {
//
//                                    }
//                                }
                                newCheckInfoAdapter = new NewCheckInfoAdapter(list, getContext(), getActivity(),rwztNext);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    newCheckInfoAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(newCheckInfoAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        }else{
                            Toast.makeText(getContext(), newCheckInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = (int) buttonView.getTag();
        if (pos==0) {
            if (isChecked) {
                cbA.setChecked(false);
                cbB.setChecked(false);
                cbC.setChecked(false);
                cbD.setChecked(false);
            }
        }else{
            if (isChecked) {
                cbAll.setChecked(false);
            }
        }
    }
}
