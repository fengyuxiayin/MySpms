package com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.CheckPointAdapter;
import com.example.lzc.myspms.models.CheckPointModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class CheckItemsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = CheckItemsActivity.class.getSimpleName();
    private TextView tvEnterpriseName;
    private TextView tvProjectName;
    private PullToRefreshListView listview;
    private String zqjlId;
    private String jcxmId;
    private Gson gson;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private TextView tvTitle;
    private Button btnSave;
    private List<CheckPointModel.CheckPointMsgModel.UnsafeBean> unSafeData = new ArrayList<CheckPointModel.CheckPointMsgModel.UnsafeBean>();
    private String jcId;
    private String jcxId;
    private String qyId;
    private String status;
    private CheckPointModel.CheckPointMsgModel checkPointMsgModel;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_items);
        zqjlId = getIntent().getStringExtra("zqjlId");
        jcxmId = getIntent().getStringExtra("jcxmId");
        jcId = getIntent().getStringExtra("jcId");
        jcxId = getIntent().getStringExtra("jcxId");
        qyId = getIntent().getStringExtra("qyId");
        status = getIntent().getStringExtra("status");
        Log.e(TAG, "onCreate: "+"zqjlId"+zqjlId+"jcxmId"+jcxmId+"jcId"+jcId+"jcxId"+jcxId);
        initView();
        if (status.equals("1.0")) {
            btnSave.setVisibility(View.GONE);
        } else {
            btnSave.setVisibility(View.VISIBLE);
        }
        initData();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkPoint/find")
                .addParams("jcxmId", jcxmId)
                .addParams("recordId", zqjlId)
                .build()
                .execute(new StringCallback() {
                    private CheckPointAdapter checkPointAdapter;

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        Log.e(TAG, "onBefore: "+request.url() );
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        CheckPointModel checkPointModel = gson.fromJson(response, CheckPointModel.class);
                        if (checkPointModel.isData()) {
                            linearLayout.setVisibility(View.VISIBLE);
                            checkPointMsgModel = gson.fromJson(checkPointModel.getMsg(), CheckPointModel.CheckPointMsgModel.class);
                            tvEnterpriseName.setText(checkPointMsgModel.getQymc());
                            tvProjectName.setText(checkPointMsgModel.getXmmc());
                            if (checkPointMsgModel.getUnsafe()!=null) {
                                unSafeData = checkPointMsgModel.getUnsafe();
                            }
                            if (checkPointMsgModel.getSafety()!=null) {
                                List<CheckPointModel.CheckPointMsgModel.SafetyBean> safety = checkPointMsgModel.getSafety();
                                for (int i = 0; i < safety.size(); i++) {
                                    unSafeData.add(new CheckPointModel.CheckPointMsgModel.UnsafeBean(safety.get(i).getId()+"",jcId,safety.get(i).getJcdId()+"",safety.get(i).getJcjg()+"","",safety.get(i).getJcxId()+"","",safety.get(i).getText(),qyId,"2"));
                                }
                            }
                            checkPointAdapter = new CheckPointAdapter(unSafeData, getApplicationContext(), CheckItemsActivity.this);
                            listview.setAdapter(checkPointAdapter);
                        }else{
                            Toast.makeText(CheckItemsActivity.this, checkPointModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.activity_check_items);
        imgBack = (ImageView) findViewById(R.id.activity_check_items_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_check_items_header).findViewById(R.id.videocall);
        tvTitle = (TextView) findViewById(R.id.activity_check_items_header).findViewById(R.id.title);
        tvTitle.setText("检查项");
        tvEnterpriseName = (TextView) findViewById(R.id.activity_check_items_enterprise_name);
        tvProjectName = (TextView) findViewById(R.id.activity_check_items_project_name);
        listview = (PullToRefreshListView) findViewById(R.id.activity_check_items_pulltorefresh);
        btnSave = (Button) findViewById(R.id.activity_check_items_btn_save_info);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.back:
                    finish();
                    break;
                case R.id.videocall:
                    Intent intent = new Intent();
                    intent.setClass(CheckItemsActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
                case R.id.activity_check_items_btn_save_info:
                    String s = unsafeToJson();
                    if (s!=null) {
                        OkHttpUtils.post()
                                .url(Constant.SERVER_URL + "/checkPoint/save")
                                .addParams("jsonStr", s)
                                .build()
                                .execute(new StringCallback() {
                                    private LoginInfoModel loginInfoModel;

                                    @Override
                                    public void onError(Request request, Exception e) {
                                        Log.e(TAG, "onError: " + e.getMessage());
                                        NetUtil.errorTip(CheckItemsActivity.this,e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        Log.e(TAG, "onResponse: checkpoint" + response);
                                        loginInfoModel = gson.fromJson(response, LoginInfoModel.class);
                                        Toast.makeText(CheckItemsActivity.this, loginInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                        if (loginInfoModel.isData()) {
                                            finish();
                                        }
                                    }
                                });
                    }else if(checkPointMsgModel.getSafety()==null&&checkPointMsgModel.getUnsafe()==null){//没有安全隐患
                        Log.e(TAG, "onClick: 没有安全信息"+jcxmId+" jcx"+jcxId+"jcId"+jcId );
                        OkHttpUtils.post()
                                .url(Constant.SERVER_URL + "/checkProject/save")
                                .addParams("id", jcxId)
                                .build()
                                .execute(new StringCallback() {
                                    private LoginInfoModel loginInfoModel;

                                    @Override
                                    public void onError(Request request, Exception e) {
                                        Log.e(TAG, "onError: " + e.getMessage());
                                        NetUtil.errorTip(CheckItemsActivity.this,e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        Log.e(TAG, "onResponse: checkproject" + response);
                                        loginInfoModel = gson.fromJson(response, LoginInfoModel.class);
                                        Toast.makeText(CheckItemsActivity.this, loginInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                        if (loginInfoModel.isData()) {
                                            finish();
                                        }
                                    }
                                });
                }

                    break;
            }
        }
    }

    /**
     * @desc 把数据源转化为对应的json
     * @date 2017/12/21 15:23
     */
    private String unsafeToJson() {
        String s = null;
        if (unSafeData.size()>0) {
//            stringBuilder.append("[");
//            for (int i = 0; i < unSafeData.size(); i++) {
//                stringBuilder.append("{" + "\"" + "id" + "\"" + ":" + "\"" + unSafeData.get(i).getId() + "\"" + ",");
//                stringBuilder.append("\"" + "jcxmId" + "\"" + ":" + "\"" + unSafeData.get(i).getJcxmId() + "\"" + ",");
////                stringBuilder.append("\""+"jcxId"+"\""+":"+"\""+("null".equals(String.valueOf(unSafeData.get(i).getJcxId()))?"":unSafeData.get(i).getJcxId())+"\""+",");
//                stringBuilder.append("\"" + "jcxId" + "\"" + ":" + "\"" + jcxId + "\"" + ",");
////                stringBuilder.append("\""+"jcId"+"\""+":"+"\""+("null".equals(String.valueOf(unSafeData.get(i).getJcId()))?"":unSafeData.get(i).getJcId())+"\""+",");
//                stringBuilder.append("\"" + "jcId" + "\"" + ":" + "\"" + jcId + "\"" + ",");
//                stringBuilder.append("\"" + "memo" + "\"" + ":" + "\"" + ("null".equals(String.valueOf(unSafeData.get(i).getMemo())) ? "" : unSafeData.get(i).getMemo()) + "\"" + ",");
//                stringBuilder.append("\"" + "jcjg" + "\"" + ":" + "\"" + ("null".equals(String.valueOf(unSafeData.get(i).getJcjg())) ? "" : unSafeData.get(i).getJcjg()) + "\"" + ",");
////                stringBuilder.append("\""+"qyId"+"\""+":"+"\""+("null".equals(String.valueOf(unSafeData.get(i).getQyId()))?"":unSafeData.get(i).getQyId())+"\""+",");
//                stringBuilder.append("\"" + "qyId" + "\"" + ":" + "\"" + qyId + "\"" + ",");
//                stringBuilder.append("\"" + "jctp" + "\"" + ":" + "\"" + ("null".equals(String.valueOf(unSafeData.get(i).getJctp())) ? "" : unSafeData.get(i).getJctp()) + "\"" + ",");
//                stringBuilder.append("\"" + "yhly" + "\"" + ":" + "\"" + "1" + "\"" + ",");
//                if (i == unSafeData.size() - 1) {
////                    stringBuilder.append("jcdId:"+"\""+(String.valueOf(unSafeData.get(i).getJcdId()).equals("null")?"":unSafeData.get(i).getJcdId())+"\""+"}");
//                    stringBuilder.append("\"" + "jcdId" + "\"" + ":" + "\"" + ("null".equals(String.valueOf(unSafeData.get(i).getJcdId())) ? "" : unSafeData.get(i).getJcdId()) + "\"" + "}");
//                } else {
//                    stringBuilder.append("\"" + "jcdId" + "\"" + ":" + "\"" + ("null".equals(String.valueOf(unSafeData.get(i).getJcdId())) ? "" : unSafeData.get(i).getJcdId()) + "\"" + "}" + ",");
////                    stringBuilder.append("jcdId:"+"\""+"\""+"}"+",");
//                }
//
//
//            }
//            stringBuilder.append("]");
//            for (int i = 0; i < unSafeData.size(); i++) {
//                String s = gson.toJson(unSafeData);
//                    stringBuilder.append(s);
//                }
//            Log.e(TAG, "unsafeToJson: " + stringBuilder);
            for (int i = 0; i < unSafeData.size(); i++) {
                if (unSafeData.get(i).getJcxmId().length()>0) {//判断是否为unsafe里面的数据 unsafe的检查项目id有值
                    Log.e(TAG, "unsafeToJson: "+unSafeData.get(i).getJcxmId()+"   "+unSafeData.get(i).getMemo() );
                    unSafeData.get(i).setJcId(jcId);
                    unSafeData.get(i).setYhly("1");
                }
                unSafeData.get(i).setJcjg(unSafeData.get(i).getJcjg());
                unSafeData.get(i).setJcxId(jcxId);

            }
            s = gson.toJson(unSafeData);
        }else{
            Log.e(TAG, "unsafeToJson: 没有填写安全信息" );
        }
        Log.e(TAG, "unsafeToJson: "+s );
        return s;
    }

    /**
     * @param unSafeData 传过来的adapter的数据
     * @desc 获取adapter传过来的unsafeData的数据
     * @date 2017/12/21 15:24
     */
    public void getUnsafeData(List<CheckPointModel.CheckPointMsgModel.UnsafeBean> unSafeData) {
        this.unSafeData = unSafeData;

    }
}
