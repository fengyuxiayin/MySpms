package com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.ProjectFindAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.ProjectFindModel;
import com.example.lzc.myspms.utils.DialogUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditEnterpriseDangerActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = EditEnterpriseDangerActivity.class.getSimpleName();

    private ClearEditText etFxdw;
    private ClearEditText etMc;
    private ClearEditText etSybm;
    private ClearEditText etBm;
    private Button btnSearch;
    private Button btnReset;
    private Button btnAdd;
    private PullToRefreshListView listView;
    private String wxylx = "";
    private String qyId;
    private int page = 1;
    private Gson gson = new Gson();
    //项目类型的数据
    private List<EnumModel> projectList = new ArrayList<>();
    private List<String> projectStrList = new ArrayList<>();
    private List<ProjectFindModel.ProjectFindMsgModel.ListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_enterprise_danger);
        qyId = getIntent().getStringExtra("qyId");
        initView();
        initData();
        initListener();

    }

    @Override
    protected void onStart() {
        super.onStart();
        getDataFromServer();
    }

    private void initListener() {
        etFxdw.setFocusable(false);
        etFxdw.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    private void initData() {
        initEnumData("/enum/getEnums", "PROJECT_TYPE", projectList);
        //获取当前页面的数据设置给listview
        getDataFromServer();
    }

    //获取枚举数据
    private void initEnumData(String url, final String params, final List<EnumModel> data) {
        //获取其他类型的Enum数据时都需要参数
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + url)
                .addParams("code", params)
                .build().execute(new com.zhy.http.okhttp.callback.StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                NetUtil.errorTip(EditEnterpriseDangerActivity.this, e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                Log.e(TAG, "initEnumData: " + params + response);
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //解析json数据 这里用不了gson 因为gson要求object开头
                            JSONObject o = (JSONObject) jsonArray.get(i);
                            //监管等级 标准化等级 企业风险分级 企业规模
                            String value = "";
                            value = o.optString("value") + o.optString("memo");
                            String key = o.optString("key");
                            data.add(new EnumModel(key, value));
                            projectStrList.add(value);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void getDataFromServer() {
        for (int i = 0; i < projectList.size(); i++) {
            if (etFxdw.getText().toString().equals(projectList.get(i).getValue())) {
                wxylx = projectList.get(i).getKey();
                break;
            } else {
                wxylx = "";
            }
        }
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/project/find")
                .addParams("wxylx", wxylx)
                .addParams("wxymc", etMc.getText().toString().trim())
                .addParams("bm", etBm.getText().toString().trim())
                .addParams("qyId", qyId)
                .addParams("size", "100")
                .addParams("pn", page + "")
                .addParams("sybm", etSybm.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    private ProjectFindModel.ProjectFindMsgModel projectFindMsgModel;
                    private ProjectFindModel projectFindModel;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getCause() + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        projectFindModel = gson.fromJson(response, ProjectFindModel.class);
                        if (projectFindModel.isData()) {
                            projectFindMsgModel = gson.fromJson(projectFindModel.getMsg(), ProjectFindModel.ProjectFindMsgModel.class);
                            list = projectFindMsgModel.getList();
                            if (list != null) {
                                ProjectFindAdapter projectFindAdapter = new ProjectFindAdapter(list, EditEnterpriseDangerActivity.this,projectList);
                                listView.setAdapter(projectFindAdapter);
                            }

                        } else {
                            Toast.makeText(EditEnterpriseDangerActivity.this, projectFindModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        etFxdw = (ClearEditText) findViewById(R.id.activity_edit_enterprise_danger_et_fxdw);
        etMc = (ClearEditText) findViewById(R.id.activity_edit_enterprise_danger_et_mc);
        etSybm = (ClearEditText) findViewById(R.id.activity_edit_enterprise_danger_et_sybm);
        etBm = (ClearEditText) findViewById(R.id.activity_edit_enterprise_danger_et_bm);
        btnSearch = (Button) findViewById(R.id.activity_edit_enterprise_danger_btn_search);
        btnReset = (Button) findViewById(R.id.activity_edit_enterprise_danger_btn_reset);
        btnAdd = (Button) findViewById(R.id.activity_edit_enterprise_danger_btn_add);
        listView = (PullToRefreshListView) findViewById(R.id.activity_edit_enterprise_danger_lv);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.activity_edit_enterprise_danger_et_fxdw:
                    DialogUtil.showChooseDialog(projectList, etFxdw, this);
                    break;
                case R.id.activity_edit_enterprise_danger_btn_search:
                    getDataFromServer();
                    break;
                case R.id.activity_edit_enterprise_danger_btn_reset:
                    etFxdw.setText("");
                    etBm.setText("");
                    etMc.setText("");
                    etSybm.setText("");
                    getDataFromServer();
                    break;
                case R.id.activity_edit_enterprise_danger_btn_add:
                    Intent intent = new Intent();
                    intent.setClass(EditEnterpriseDangerActivity.this, EditProjectActivity.class);
                    intent.putExtra("qyId",list!=null?list.get(0).getQyId()+"":"");
                    intent.putExtra("id","");
                    intent.putExtra("isEdit",false);
                    startActivity(intent);
                    break;
            }
        }
    }
}
