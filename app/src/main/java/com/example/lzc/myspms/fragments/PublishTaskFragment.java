package com.example.lzc.myspms.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.CheckTaskAdapter;
import com.example.lzc.myspms.adapters.EnterpriseInfoQueryAdapter;
import com.example.lzc.myspms.adapters.MyFilterAdapter;
import com.example.lzc.myspms.adapters.ReCheckInfoAdapter;
import com.example.lzc.myspms.adapters.SimpleArrayAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.custom.CommonAdapter;
import com.example.lzc.myspms.custom.ViewHolder;
import com.example.lzc.myspms.models.CheckTaskModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnterpriseInfoQueryModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.NewCheckInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LZC on 2017/10/27.
 */
public class PublishTaskFragment extends BaseFragment {
    public static final String TAG = PublishTaskFragment.class.getSimpleName();
    private View view;
    private MyFilterAdapter<String> filterAdapter;
    private ListView listView;
    private Gson gson = new Gson();
    private List<EnterpriseInfoQueryModel.ListSet.ListBean> list = new ArrayList<>();
    private ClearEditText etEnterprise;
    private ClearEditText etRwmc;
    private ClearEditText etDate;
    private Button btnCommit;
    private List<String> dataList;
    private PullToRefreshListView listViewShow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_publish_task, container, false);
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
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(etDate);
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转化一下id
                List<String> dataEnterprise = filterAdapter.returnDataEnterprise();
                List<Integer> qyItems = new ArrayList<Integer>();
                if (dataEnterprise != null) {
                    for (int i = 0; i < list.size(); i++) {
                        for (int j = 0; j < dataEnterprise.size(); j++) {
                            if (list.get(i).getQymc().equals(dataEnterprise.get(j))) {
                                qyItems.add(list.get(i).getId());
                            }
                        }
                    }
                }
                Log.e(TAG, "onClick: " + gson.toJson(qyItems));
                if (etRwmc.getText().length() < 1) {
                    Toast.makeText(getActivity(), "请填写任务名称后再提交", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etDate.getText().toString().trim().length() < 1) {
                    Toast.makeText(getActivity(), "请选择计划完成时间后再提交", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (qyItems.size() < 1) {
                    Toast.makeText(getActivity(), "请选择企业后再提交", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL + "/checkTaskSettings/save")
                        .addParams("rwmc", etRwmc.getText().toString().trim())
                        .addParams("jcdwlx", Constant.ACCOUNT_TYPE)
                        .addParams("jcdwIds", Constant.ENTERPRISE_ID)
                        .addParams("startDate", etDate.getText().toString().trim())
                        .addParams("endDate", etDate.getText().toString().trim())
                        .addParams("qyItem", gson.toJson(qyItems))
                        .addParams("jcbzId", "2")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: " + response);
                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                if (infoModel.isData()) {
                                    getTaskFromServer();
                                }
                                Toast.makeText(getActivity(), infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        etEnterprise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (filterAdapter != null) {
                    filterAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * @param edittext 需要设置显示内容的Edittext
     * @desc 显示日历，并将选择的时间变为指定格式显示在Edittext上
     * @date 2017/12/11 14:27
     */
    private void setDate(final EditText edittext) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(year, monthOfYear, dayOfMonth);
                edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void initData() {
        //获取小组管辖的企业
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/find")
                .addParams("size", "10000")
                .addParams("jcdwlx", Constant.ACCOUNT_TYPE)
                .addParams("jcdwId", Constant.ENTERPRISE_ID)
                .build()
                .execute(new StringCallback() {
                    private EnterpriseInfoQueryAdapter enterpriseInfoQueryAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getActivity(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        EnterpriseInfoQueryModel enterpriseInfoQueryModel = gson.fromJson(response, EnterpriseInfoQueryModel.class);
                        if (enterpriseInfoQueryModel.isData()) {
                            EnterpriseInfoQueryModel.ListSet listSet = gson.fromJson(enterpriseInfoQueryModel.getMsg(), EnterpriseInfoQueryModel.ListSet.class);
                            list = listSet.getList();
                            if (list != null) {
                                dataList = new ArrayList<>();
                                for (int i = 0; i < list.size(); i++) {
                                    dataList.add(list.get(i).getQymc());
                                }
                                filterAdapter = new MyFilterAdapter<>(getActivity(), R.layout.activity_area_enterprise_item, dataList);
                                listView.setAdapter(filterAdapter);
                            } else {
                                Toast.makeText(getActivity(), "没有查到符合条件企业", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), enterpriseInfoQueryModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //获取小组发布的任务
        getTaskFromServer();
    }

    private void getTaskFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkTaskSettings/findByTags")
                .addParams("jcdwlx", Constant.ACCOUNT_TYPE)
                .addParams("jcdwId", Constant.ENTERPRISE_ID)
                .addParams("rwlx", "1")
                .build()
                .execute(new StringCallback() {
                    private CheckTaskAdapter checkTaskAdapter;
                    private CheckTaskModel.CheckTaskMsgModel checkTaskMsgModel;
                    private CheckTaskModel checkTaskModel;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage() + e.getCause());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        checkTaskModel = gson.fromJson(response, CheckTaskModel.class);
                        if (checkTaskModel.isData()) {
                            checkTaskMsgModel = gson.fromJson(checkTaskModel.getMsg(), CheckTaskModel.CheckTaskMsgModel.class);
                            List<CheckTaskModel.CheckTaskMsgModel.ListBean> list = checkTaskMsgModel.getList();
                            if (list != null) {
                                checkTaskAdapter = new CheckTaskAdapter(list, getActivity());
                                listViewShow.setAdapter(checkTaskAdapter);
//                                CommonAdapter<CheckTaskModel.CheckTaskMsgModel.ListBean> commonAdapter = new CommonAdapter<CheckTaskModel.CheckTaskMsgModel.ListBean>(getContext(), list, R.layout.activity_release_item) {
//
//                                    @Override
//                                    public void convert(ViewHolder helper, CheckTaskModel.CheckTaskMsgModel.ListBean item, int position) {
//                                        View line = helper.getView(R.id.activity_release_item_line);
//                                        TextView tvRwmc = helper.getView(R.id.activity_release_item_rwmc);
//                                        tvRwmc.setText(item.getRwmc());
//                                    }
//                                };
//                                listViewShow.setAdapter(commonAdapter);

                            }
                        } else {
                            Toast.makeText(getActivity(), checkTaskModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getTaskFromServer();
        }
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.activity_release_lv);
        etEnterprise = (ClearEditText) view.findViewById(R.id.activity_release_et_enterprise);
        etRwmc = (ClearEditText) view.findViewById(R.id.activity_release_et_rwmc);
        etDate = (ClearEditText) view.findViewById(R.id.activity_release_et_date);
        btnCommit = (Button) view.findViewById(R.id.activity_release_btn_commit);
        listViewShow = (PullToRefreshListView) view.findViewById(R.id.activity_release_lv_show);
    }
}
