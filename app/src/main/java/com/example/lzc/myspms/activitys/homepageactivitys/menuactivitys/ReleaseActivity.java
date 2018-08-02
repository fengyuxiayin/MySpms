package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.EnterpriseInfoQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.EnterpriseQueryActivity;
import com.example.lzc.myspms.adapters.CheckTaskAdapter;
import com.example.lzc.myspms.adapters.EnterpriseInfoQueryAdapter;
import com.example.lzc.myspms.adapters.MyFilterAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.custom.MyListView;
import com.example.lzc.myspms.models.CheckTaskModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnterpriseInfoQueryModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReleaseActivity extends AppCompatActivity {
    public static final String TAG = ReleaseActivity.class.getSimpleName();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
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
                    Toast.makeText(ReleaseActivity.this, "请填写任务名称后再提交", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etDate.getText().toString().trim().length() < 1) {
                    Toast.makeText(ReleaseActivity.this, "请选择计划完成时间后再提交", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (qyItems.size() < 1) {
                    Toast.makeText(ReleaseActivity.this, "请选择企业后再提交", Toast.LENGTH_SHORT).show();
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
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: " + response);
                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                Toast.makeText(ReleaseActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
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
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
                        NetUtil.errorTip(ReleaseActivity.this, e.getMessage());
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
                                filterAdapter = new MyFilterAdapter<>(ReleaseActivity.this, R.layout.activity_area_enterprise_item, dataList);
                                listView.setAdapter(filterAdapter);
                            } else {
                                Toast.makeText(ReleaseActivity.this, "没有查到符合条件企业", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ReleaseActivity.this, enterpriseInfoQueryModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //获取小组发布的任务
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
                            if (list!=null) {
                                checkTaskAdapter = new CheckTaskAdapter(list,ReleaseActivity.this);
                                listViewShow.setAdapter(checkTaskAdapter);
                            }
                        }else{
                            Toast.makeText(ReleaseActivity.this, checkTaskModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.activity_release_lv);
        etEnterprise = (ClearEditText) findViewById(R.id.activity_release_et_enterprise);
        etRwmc = (ClearEditText) findViewById(R.id.activity_release_et_rwmc);
        etDate = (ClearEditText) findViewById(R.id.activity_release_et_date);
        btnCommit = (Button) findViewById(R.id.activity_release_btn_commit);
        listViewShow = (PullToRefreshListView) findViewById(R.id.activity_release_lv_show);
        listView.setNestedScrollingEnabled(false);
        listViewShow.setNestedScrollingEnabled(false);
    }
}
