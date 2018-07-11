package com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.ProjectDetailModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = ProjectDetailActivity.class.getSimpleName();
    private TextView tvSbmc;
    private TextView tvSbbm;
    private TextView tvSsbm;
    private TextView tvSydd;
    private TextView tvSbxh;
    private TextView tvSbpp;
    private TextView tvEddy;
    private TextView tvGlxh;
    private TextView tvSbjz;
    private TextView tvQyrq;
    private TextView tvBxrq;
    private TextView tvJdrq;
    private TextView tvScrq;
    private TextView tvSsqy;
    private String xmId;
    private Gson gson;
    private String qymc;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        xmId = getIntent().getStringExtra("xmId");
        qymc = getIntent().getStringExtra("qymc");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/project/findById")
                .addParams("id", xmId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(ProjectDetailActivity.this,e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        ProjectDetailModel projectDetailModel = gson.fromJson(response, ProjectDetailModel.class);
                        if (projectDetailModel.isData()) {
                            ProjectDetailModel.ProjectDetailMsgModel projectDetailMsgModel = gson.fromJson(projectDetailModel.getMsg(), ProjectDetailModel.ProjectDetailMsgModel.class);
                            setData(projectDetailMsgModel);
                        }else{
                            Toast.makeText(ProjectDetailActivity.this,projectDetailModel.getMsg() , Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void setData(ProjectDetailModel.ProjectDetailMsgModel data) {
        tvSbmc.setText(data.getXmmc());
        tvSbbm.setText(data.getXmbm());
        tvSsbm.setText(data.getSsbm());
        tvSydd.setText(data.getSydd());
        tvSbxh.setText(data.getSbxh());
        tvSbpp.setText(data.getSbpp());
        tvEddy.setText(data.getEddy());
        tvGlxh.setText(data.getGlxh());
        tvSbjz.setText(data.getSbjz());
        tvSsqy.setText(qymc);
        tvSbbm.setText(data.getXmmc());
        tvScrq.setText(dateFormat(data.getScrq()));
        tvQyrq.setText(dateFormat(data.getQyrq()));
        tvBxrq.setText(dateFormat(data.getBxrq()));
        tvJdrq.setText(dateFormat(data.getJdrq()));
    }
/**
 *
 *@desc 把long类型的时间转化为指定格式
 *@param time long类型的时间
 *@date 2017/12/19 10:41
*/
    private String dateFormat(long time) {
        //data中没有对应的值时 time=0
        if (time > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(new Date(time));
            return format;
        } else {
            return "";
        }
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.activity_project_detail_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_project_detail_header).findViewById(R.id.videocall);
        tvTitle = (TextView) findViewById(R.id.activity_project_detail_header).findViewById(R.id.title);
        tvTitle.setText("项目详细信息");
        tvSbmc = (TextView) findViewById(R.id.activity_project_detail_tv_sbmc);
        tvSbbm = (TextView) findViewById(R.id.activity_project_detail_tv_sbbm);
        tvSsbm = (TextView) findViewById(R.id.activity_project_detail_tv_ssbm);
        tvSydd = (TextView) findViewById(R.id.activity_project_detail_tv_sydd);
        tvSbxh = (TextView) findViewById(R.id.activity_project_detail_tv_sbxh);
        tvSbpp = (TextView) findViewById(R.id.activity_project_detail_tv_sbpp);
        tvEddy = (TextView) findViewById(R.id.activity_project_detail_tv_eddy);
        tvGlxh = (TextView) findViewById(R.id.activity_project_detail_tv_glxh);
        tvSbjz = (TextView) findViewById(R.id.activity_project_detail_tv_sbjz);
        tvScrq = (TextView) findViewById(R.id.activity_project_detail_tv_scrq);
        tvQyrq = (TextView) findViewById(R.id.activity_project_detail_tv_qyrq);
        tvBxrq = (TextView) findViewById(R.id.activity_project_detail_tv_bxrq);
        tvJdrq = (TextView) findViewById(R.id.activity_project_detail_tv_jdrq);
        tvSsqy = (TextView) findViewById(R.id.activity_project_detail_tv_ssqy);
    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            switch (v.getId()) {
                case R.id.back:
                    finish();
                    break;
                case R.id.videocall:
                    Intent intent = new Intent();
                    intent.setClass(ProjectDetailActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
