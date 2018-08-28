package com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Environment;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.AddEnterpriseSimpleActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys.PreviewActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys.PreviewSdActivity;
import com.example.lzc.myspms.adapters.ImageGridAdapter;
import com.example.lzc.myspms.adapters.MyPrintAdapter;
import com.example.lzc.myspms.adapters.ProjectRecheckSimpleAdapter;
import com.example.lzc.myspms.adapters.ProjectSimpleAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.CheckCatalogModel;
import com.example.lzc.myspms.models.CheckMessageModel;
import com.example.lzc.myspms.models.CheckProjectFindModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumCommunityModel;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.FindRecheckProjectModel;
import com.example.lzc.myspms.models.InstrumentModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.NewCheckInfoModel;
import com.example.lzc.myspms.models.QyJsonModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.example.lzc.myspms.utils.ShowMenuPopup;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckProjectActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public static final String TAG = CheckProjectActivity.class.getSimpleName();
    private String jclx;
    private int page = 1;
    private Gson gson = new Gson();
    private TextView textView;
    private TextView tvTitle;
    private ImageView imgVideoCall;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgTitleCall;
    private ImageView imgMessage;
    private ImageView imgBack;
    private List<CheckProjectFindModel.CheckProjectFindMsgModel.ListBean> list = new ArrayList<>();
    private List<FindRecheckProjectModel.FindRecheckProjectMsgModel.ListBean> reCheckList = new ArrayList<>();
    private ProjectSimpleAdapter projectAdapter;
    private TextView tvQymc;
    private String jcxmlx = "1";
    //整个企业的检查结果
    private String jcId;
    private String jcjg;
    private TextView tvComponyName;
    private TextView tvIndustry;
    private TextView tvSupervision;
    private TextView tvCommunity;
    private TextView tvLegal;
    private TextView tvMainResponse;
    private TextView tvSafeResponse;
    private Button btnReview;
    private Button btnPrint;
    private ClearEditText etChangeTime;
    private RadioButton rbPublic;
    private RadioButton rbDanger;
    private PullToRefreshListView listView;
    private String qyJson;
    //行业类别代码数据
    private List<EnumModel> dataIndustryCateary = new ArrayList<>();
    //监管分类数据
    private List<EnumModel> dataRegulatory = new ArrayList<>();
    //社区数据
    private List<EnumModel> dataCommunity = new ArrayList<>();
    private QyJsonModel qyJsonModel;
    //上一个页面传进来 setrecheck用的
    private String rwId;
    //setrecheck 用
    private String qyId;
    //判断是打印还是预览
    private boolean isReview = true;
    private TextView tvChangeTime;
    private ImageView imgCall;
    private TextView tvFrdb;
    private TextView tvZyfzr;
    private TextView tvAqfzr;
    private CheckProjectFindModel.CheckProjectFindMsgModel checkProjectFindMsgModel;
    private CheckMessageModel.CheckMessageMsgModel checkMessageMsgModel;
    //初查传kssj 复查传jzsj
    private String jcsj;
    private String rwzt;
    private boolean checkQualified;
    private String zgqx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_project);
        jclx = getIntent().getStringExtra("jclx");
        rwzt = getIntent().getStringExtra("rwzt");
        jcjg = getIntent().getStringExtra("jcjg");
        jcId = getIntent().getStringExtra("jcId");
        qyJson = getIntent().getStringExtra("qyJson");
        rwId = getIntent().getStringExtra("rwId");
        jcsj = getIntent().getStringExtra("jcsj");
        zgqx = getIntent().getStringExtra("zgqx");
        Log.e(TAG, "onCreate: rwzt" + rwzt+"jcjg"+jcjg+"zgqx"+zgqx+"jcId"+jcId);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        page = 1;
        getDataFromServer();
        super.onResume();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgTitleCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(CheckProjectActivity.this, R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    getDataFromServer();
                }

            }
        });
        etChangeTime.setOnClickListener(this);
        btnReview.setOnClickListener(this);
        btnPrint.setOnClickListener(this);
        rbPublic.setOnCheckedChangeListener(this);
        imgCall.setOnClickListener(this);
    }

    //
    private void initData() {
        if (jclx.equals("1")&&!rwzt.equals("1")) {//如果是初查并且是未检查或者检查中状态
            etChangeTime.setVisibility(View.VISIBLE);
            tvChangeTime.setVisibility(View.VISIBLE);
        }else{
            if (("1").equals(jcjg)) {//检查已合格
                if (jclx.equals("1")) {
                    btnReview.setVisibility(View.GONE);
                    btnPrint.setVisibility(View.GONE);
                    tvChangeTime.setVisibility(View.VISIBLE);
                    tvChangeTime.setText("当前企业检查已合格");
                    etChangeTime.setVisibility(View.GONE);
                }else{
                    tvChangeTime.setText("当前企业复查已合格");
                    tvChangeTime.setVisibility(View.VISIBLE);
                    etChangeTime.setVisibility(View.GONE);
                }

            }else{
                    if (zgqx!=null) {
                        Log.e(TAG, "initData: zgqx"+zgqx );
                        if (zgqx.length()>9) {
                            etChangeTime.setVisibility(View.GONE);
                            tvChangeTime.setVisibility(View.GONE);
                        }else{
                            etChangeTime.setVisibility(View.VISIBLE);
                            tvChangeTime.setVisibility(View.VISIBLE);
                        }
                    }else{
                        etChangeTime.setVisibility(View.GONE);
                        tvChangeTime.setVisibility(View.GONE);
                    }


            }

        }
        qyJsonModel = gson.fromJson(qyJson, QyJsonModel.class);
        tvComponyName.setText(qyJsonModel.getQymc());
        qyId = qyJsonModel.getId() + "";
        //获取行业类别代码并设置对应值
        initEnumData("/enum/getEnums", "INDUSTRY_CATEGORY_TYPE", dataIndustryCateary);
        //获取监管级别代码并设置对应值
        initEnumData("/enum/getEnums", "REGULATORY_TYPE", dataRegulatory);
        //获取社区代码并设置对应值
        initCommunityData();
        tvSafeResponse.setText("安全负责人：" + qyJsonModel.getAqfzr());
        tvMainResponse.setText("主要负责人：" + qyJsonModel.getZyfzr());
        tvLegal.setText("企业法人：" + qyJsonModel.getFddbr());

        if (jclx.equals("1")) {
            tvTitle.setText("企业项目检查");
        } else {
            tvTitle.setText("企业项目复查");
        }
        etChangeTime.setFocusable(false);
        textView = new TextView(this);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        listView.getRefreshableView().addFooterView(textView);
        //设置listView的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }



    private void initCommunityData() {
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/community/findAll")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e(TAG, "onError: findall" + e.getMessage() + e.getCause());
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                if (response != null) {
                    //解析json
                    EnumCommunityModel allCommunityModel = gson.fromJson(response, EnumCommunityModel.class);
                    try {
                        JSONArray jsonArray = new JSONArray(allCommunityModel.getMsg());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject o = (JSONObject) jsonArray.get(i);
                            String value = o.getString("sqmc");
                            String key = o.getString("id");
                            dataCommunity.add(new EnumModel(key, value));
                        }
                        for (int i = 0; i < dataCommunity.size(); i++) {
                            if (dataCommunity.get(i).getKey().equals(qyJsonModel.getSqId() + "")) {
                                tvCommunity.setText("所属社区：" + dataCommunity.get(i).getValue());
                                break;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void initEnumData(String url, final String params, final List<EnumModel> dataList) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + url)
                .addParams("code", params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                NetUtil.errorTip(CheckProjectActivity.this, e.getMessage());
            }

            @Override
            public void onBefore(Request request) {

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
                            value = o.getString("value");
                            String key = o.getString("key");
                            dataList.add(new EnumModel(key, value));
                        }
                        if (params.equals("INDUSTRY_CATEGORY_TYPE")) {
                            for (int i = 0; i < dataList.size(); i++) {
                                if (dataList.get(i).getKey().equals(qyJsonModel.getHylbdm() + "")) {
                                    tvIndustry.setText("行业归属：" + dataList.get(i).getValue());
                                    break;
                                }
                            }
                        } else {
                            for (int i = 0; i < dataList.size(); i++) {
                                if (dataList.get(i).getKey().equals(qyJsonModel.getJgfldj() + "")) {
                                    tvSupervision.setText("监管等级：" + dataList.get(i).getValue());
                                    break;
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    //
//    /**
//     * @desc 从服务器获取初查数据
//     * @date 2018/5/11 10:27
//     */
    private void getDataFromServer() {
        Log.e(TAG, "getDataFromServer: " + jcId + "  " + jcxmlx);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/find")
                .addParams("jcId", jcId + "")
                .addParams("jclx", jcxmlx)//1 通项 2 危险源
                .addParams("pn", page + "")
                .addParams("size", "1000")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(CheckProjectActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /checkProject/find" + response);
                        CheckProjectFindModel checkProjectFindModel = gson.fromJson(response, CheckProjectFindModel.class);
                        if (checkProjectFindModel.isData()) {
                            checkProjectFindMsgModel = gson.fromJson(checkProjectFindModel.getMsg(), CheckProjectFindModel.CheckProjectFindMsgModel.class);
                            Log.e(TAG, "onResponse: " + checkProjectFindMsgModel.getTotal());
                            if (checkProjectFindMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                if (!("null".equals(checkProjectFindMsgModel.getList())) && checkProjectFindMsgModel.getList() != null) {
                                    list = checkProjectFindMsgModel.getList();
                                    Log.e(TAG, "onResponse: " + list.size());
                                } else {
                                    Toast.makeText(CheckProjectActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                if (!("null".equals(checkProjectFindMsgModel.getList())) || checkProjectFindMsgModel.getList() != null) {
                                    list.addAll(checkProjectFindMsgModel.getList());
                                    Log.e(TAG, "onResponse: " + list.size());
                                } else {
                                    Toast.makeText(CheckProjectActivity.this, "第" + page + "页数据为空", Toast.LENGTH_SHORT).show();
                                }
                            }
                            if (list.size() == 0) {
                                //显示项目信息
                                projectAdapter = new ProjectSimpleAdapter(list, CheckProjectActivity.this, jcId, jcjg, jcxmlx, qyJsonModel.getQymc());
                                listView.setAdapter(projectAdapter);
                                listView.onRefreshComplete();
                            } else {
                                Log.e(TAG, "onResponse: jcjg1111"+jcjg );
                                projectAdapter = new ProjectSimpleAdapter(list, CheckProjectActivity.this, jcId, jcjg, jcxmlx, qyJsonModel.getQymc());
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    projectAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(projectAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                            //判断企业是否检查完毕 是的话直接隐藏点整改时间 将预览文书文字改为检查完成 将打印文书按钮隐藏
                            if (projectAdapter!=null) {
                                checkQualified = false;
                                if (list!=null) {
                                    for (int i = 0; i < list.size(); i++) {
                                        if (list.get(i).getJcjg()!=1) {
                                            Log.e(TAG, "onResponse: 不合格"+list.get(i).getJcjg() );
                                            checkQualified = false;
                                            break;
                                        }else{
                                            Log.e(TAG, "onResponse: 合格"+list.get(i).getJcjg() );
                                            checkQualified = true;
                                        }
                                    }
                                    Log.e(TAG, "onResume: checkQualified"+ checkQualified);
                                    if (checkQualified) {
                                        Log.e(TAG, "onResume: checkQualified 全部企业都合格"+ checkQualified);
//                                        tvChangeTime.setVisibility(View.GONE);
//                                        etChangeTime.setVisibility(View.GONE);
//                                        btnPrint.setVisibility(View.INVISIBLE);
//                                        btnReview.setVisibility(View.INVISIBLE);
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(CheckProjectActivity.this, checkProjectFindModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.activity_check_project_header).findViewById(R.id.title);
        imgVideoCall = (ImageView) findViewById(R.id.activity_check_project_header).findViewById(R.id.videocall);
        imgBack = (ImageView) findViewById(R.id.activity_check_project_header).findViewById(R.id.back);
        imgNotice = (ImageView) findViewById(R.id.activity_check_project_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_check_project_header).findViewById(R.id.message);
        imgTitleCall = (ImageView) findViewById(R.id.activity_check_project_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_check_project_header).findViewById(R.id.add);
        tvComponyName = (TextView) findViewById(R.id.activity_check_project_compony_name);
        tvIndustry = (TextView) findViewById(R.id.activity_check_project_tv_industry);
        tvSupervision = (TextView) findViewById(R.id.activity_check_project_tv_supervision);
        tvCommunity = (TextView) findViewById(R.id.activity_check_project_tv_community);
        tvLegal = (TextView) findViewById(R.id.activity_check_project_tv_legal);
        tvMainResponse = (TextView) findViewById(R.id.activity_check_project_tv_main_response);
        tvSafeResponse = (TextView) findViewById(R.id.activity_check_project_tv_safe_response);
        imgCall = (ImageView) findViewById(R.id.activity_check_project_img_call);
        btnReview = (Button) findViewById(R.id.activity_check_project_btn_review);
        btnPrint = (Button) findViewById(R.id.activity_check_project_btn_print);
        etChangeTime = (ClearEditText) findViewById(R.id.activity_check_project_change_time);
        tvChangeTime = (TextView) findViewById(R.id.activity_check_project_tv_change_time);
        rbPublic = (RadioButton) findViewById(R.id.activity_check_project_rb_public);
        rbDanger = (RadioButton) findViewById(R.id.activity_check_project_rb_danger);
        listView = (PullToRefreshListView) findViewById(R.id.activity_check_project_pulltorefresh);

    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            Intent intent = null;
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), CheckProjectActivity.this,CheckProjectActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_check_project_change_time:
                    setDate(etChangeTime);
                    break;
                case R.id.activity_check_project_btn_review:
                    if (checkQualified) {//全部检查都为合格的情况
                        generateAndUpload();
                        return;
                    }
                    isReview = true;
                    //先设置整改 文书才能生成
                    //根据初查还是复查，初查的未检查和检查中调用setrecheck 其他直接预览文书
                    if (jclx.equals("1") ) {
                        if (etChangeTime.getVisibility()== View.GONE) {
                            generateAndUpload();
                        }else{
                            if (etChangeTime.getText().toString().trim().length() > 0) {
                                Log.e(TAG, "onClick: " + jcId + " " + etChangeTime.getText().toString().trim());
                                OkHttpUtils.post()
                                        .url(Constant.SERVER_URL + "/checkInfo/setReCheck")
                                        .addParams("id", jcId)
//                                .addParams("qyId",qyId)
                                        .addParams("repairDate", etChangeTime.getText().toString().trim())
                                        .build()
                                        .connTimeOut(10000)
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Request request, Exception e) {
                                                NetUtil.errorTip(CheckProjectActivity.this, e.getMessage() + "/checkInfo/setReCheck");
                                            }

                                            @Override
                                            public void onResponse(String response) {
                                                Log.e(TAG, "onResponse: " + response + "/checkInfo/setReCheck");
                                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                                if (infoModel.isData()) {
                                                    Toast.makeText(CheckProjectActivity.this, "修改整改期限成功", Toast.LENGTH_SHORT).show();
                                                    //下载文书并跳转到打印
                                                    downLoadAndPrintDoc(gson);
                                                } else {
                                                    if (infoModel.getMsg().equals("目前正在处于整改期")) {
                                                        downLoadAndPrintDoc(gson);
                                                    } else {
                                                        Toast.makeText(CheckProjectActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(this, "请先设置整改期限", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else{
                        generateAndUpload();
                    }
                    break;
                case R.id.activity_check_project_btn_print:
                    isReview = false;
                    if (jclx.equals("1")) {
                        if (etChangeTime.getVisibility()== View.GONE) {
                            generateAndUpload();
                        }else{
                            if (etChangeTime.getText().toString().trim().length() > 0) {
                                Log.e(TAG, "onClick: " + jcId + " " + etChangeTime.getText().toString().trim());
                                OkHttpUtils.post()
                                        .url(Constant.SERVER_URL + "/checkInfo/setReCheck")
                                        .addParams("id", jcId)
//                                .addParams("qyId",qyId)
                                        .addParams("repairDate", etChangeTime.getText().toString().trim())
                                        .build()
                                        .connTimeOut(10000)
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Request request, Exception e) {
                                                NetUtil.errorTip(CheckProjectActivity.this, e.getMessage() + "/checkInfo/setReCheck");
                                            }

                                            @Override
                                            public void onResponse(String response) {
                                                Log.e(TAG, "onResponse: " + response + "/checkInfo/setReCheck");
                                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                                if (infoModel.isData()) {
                                                    Toast.makeText(CheckProjectActivity.this, "修改整改期限成功", Toast.LENGTH_SHORT).show();
                                                    //下载文书并跳转到打印
                                                    downLoadAndPrintDoc(gson);
                                                } else {
                                                    if (infoModel.getMsg().equals("目前正在处于整改期")) {
                                                        downLoadAndPrintDoc(gson);
                                                    } else {
                                                        Toast.makeText(CheckProjectActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(this, "请先设置整改期限", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else{
                        generateAndUpload();
                    }
                    break;
                case R.id.activity_check_project_img_call:
                    //用来记录没有联系人的次数 如果是3 那么一个联系人都没有
                    int count = 0;
                    //给企业人员打电话
                    PopupWindow popupWindow = new PopupWindow();
                    View inflate = View.inflate(CheckProjectActivity.this, R.layout.popup_check_call_enterprise, null);
                    popupWindow.setWidth((int) (getWindowManager().getDefaultDisplay().getWidth() * 0.6));
                    popupWindow.setHeight((int) (getWindowManager().getDefaultDisplay().getHeight() * 0.4));
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setContentView(inflate);
                    tvFrdb = (TextView) inflate.findViewById(R.id.popup_check_call_tv_frdb);
                    tvZyfzr = (TextView) inflate.findViewById(R.id.popup_check_call_tv_zyfzr);
                    tvAqfzr = (TextView) inflate.findViewById(R.id.popup_check_call_tv_aqfzr);
                    Log.e(TAG, "onClick: "+qyJsonModel.getLxdh() );
                    if (qyJsonModel.getLxdh() != null) {
                        if (qyJsonModel.getLxdh().length()>0) {
                            tvFrdb.setText("拨打法人电话：" + qyJsonModel.getLxdh());
                            tvFrdb.setOnClickListener(this);
                        }else{
                            count++;
                            tvFrdb.setVisibility(View.GONE);
                        }
                    } else {
                        count++;
                        tvFrdb.setVisibility(View.GONE);
                    }
                    if (qyJsonModel.getZyfzryddhhm() != null) {
                        if (qyJsonModel.getZyfzryddhhm().length()>0) {
                            tvZyfzr.setText("拨打主要负责人电话：" + qyJsonModel.getZyfzryddhhm());
                            tvZyfzr.setOnClickListener(this);
                        }else{
                            tvZyfzr.setVisibility(View.GONE);
                            count++;
                        }
                    } else {
                        count++;
                        tvZyfzr.setVisibility(View.GONE);
                    }
                    if (qyJsonModel.getLxdh() != null) {
                        if (qyJsonModel.getLxdh().length()>0) {
                            tvAqfzr.setText("拨打安全负责人电话：" + qyJsonModel.getAqfzryddhhm());
                            tvAqfzr.setOnClickListener(this);
                        }else{
                            count++;
                            tvAqfzr.setVisibility(View.GONE);
                        }
                    } else {
                        count++;
                        tvAqfzr.setVisibility(View.GONE);
                    }
                    if (count ==3) {
                        Toast.makeText(this, "当前企业没有填写任何联系方式", Toast.LENGTH_SHORT).show();
                    }else{
                        popupWindow.showAtLocation(imgVideoCall, Gravity.CENTER, 0, 0);
                    }
                    break;
                case R.id.popup_check_call_tv_frdb:
                    doCall(qyJsonModel.getLxdh());
                    break;
                case R.id.popup_check_call_tv_zyfzr:
                    doCall(qyJsonModel.getZyfzryddhhm());
                    break;
                case R.id.popup_check_call_tv_aqfzr:
                    doCall(qyJsonModel.getAqfzryddhhm());
                    break;
            }
        }
    }

    private void doCall(String number) {
        Intent intent;
        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    private void downLoadAndPrintDoc(final Gson gson) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkUpload/find")
                .addParams("jcId", jcId)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                NetUtil.errorTip(CheckProjectActivity.this, "/checkUpload/find" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: /checkUpload/find" + response);
                final LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                if (infoModel.isData()) {
                    InstrumentModel.InstrumentMsgModel instrumentMsgModel = gson.fromJson(infoModel.getMsg(), InstrumentModel.InstrumentMsgModel.class);
                    //如果文书数量大于0 那么直接去下载打印 如果文书数量等于零 那么先去保存再去打印
                    if (instrumentMsgModel.getTotal() == 0) {
                        //先保存后打印
                        Log.e(TAG, "onResponse: " + "jciD" + jcId + "jclx" + jclx);
                        generateAndUpload();
                    } else {
                        //直接打印文书
                        downloadAndPrint(instrumentMsgModel);
                    }
                } else {
                    Toast.makeText(CheckProjectActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * @param edittext 需要设置显示内容的Edittext
     * @desc 显示日历，并将选择的时间变为指定格式显示在Edittext上 必须大于当前日期
     * @date 2017/12/11 14:27
     */
    private void setDate(final EditText edittext) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(CheckProjectActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (list!=null) {
                    Log.e(TAG, "onDateSet: "+jcsj );
                }
                int currentYear = calendar.get(Calendar.YEAR);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                judgeDateIsQualified(year, monthOfYear, dayOfMonth, currentYear, currentMonth, currentDay, calendar, edittext);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    /**
     *
     *@desc 判断整改时间是否合法
     *@param
     *@date 2018/6/28 15:03
    */
    private void judgeDateIsQualified(int year, int monthOfYear, int dayOfMonth, int currentYear, int currentMonth, int currentDay, Calendar calendar, EditText edittext) {
        if (currentYear < year) {
            calendar.set(year, monthOfYear, dayOfMonth);
            edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
        } else if (currentYear == year) {
            if (currentMonth < monthOfYear) {
                calendar.set(year, monthOfYear, dayOfMonth);
                edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
            } else if (currentMonth == monthOfYear) {
                if (currentDay < dayOfMonth) {
                    calendar.set(year, monthOfYear, dayOfMonth);
                    edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
                } else {
                    Toast.makeText(CheckProjectActivity.this, "整改时间需晚于当前时间", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CheckProjectActivity.this, "整改时间需晚于当前时间", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CheckProjectActivity.this, "整改时间需晚于当前时间", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            jcxmlx = "1";
            page = 1;
            getDataFromServer();
        } else {
            jcxmlx = "2";
            page = 1;
            getDataFromServer();
        }
    }
//

    /**
     * @desc 生成并上传文书
     * @date 2018/5/8 14:24
     */
    private void generateAndUpload() {
        Log.e(TAG, "generateAndUpload: "+"jcId"+jcId+"jclx"+jclx);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkReport/generateAndUpload")
                .addParams("id", jcId)
                .addParams("jclx", jclx)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(CheckProjectActivity.this, "checkReport/generateAndUpload" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /checkReport/generateAndUpload" + response);
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        //上传成功的话就下载并打印打印文书
                        if (infoModel.isData()) {
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/checkUpload/find")
                                    .addParams("jcId", jcId)
                                    .build().execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    NetUtil.errorTip(CheckProjectActivity.this, e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: /checkUpload/find" + response);
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    if (infoModel.isData()) {
                                        final InstrumentModel.InstrumentMsgModel instrumentMsgModel = gson.fromJson(infoModel.getMsg(), InstrumentModel.InstrumentMsgModel.class);
                                        if (instrumentMsgModel.getTotal() == 0) {//文书数量为零
                                            Log.e(TAG, "onResponse: " + "文书数量为零");
                                            Toast.makeText(CheckProjectActivity.this, "还没有生成文书", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Log.e(TAG, "onResponse: " + "文书数量不为零");
                                            downloadAndPrint(instrumentMsgModel);
                                        }
                                    } else {
                                        Toast.makeText(CheckProjectActivity.this, "", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(CheckProjectActivity.this, "生成文书失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
//

    /**
     * @param instrumentMsgModel 数据源 包含文书地址什么的
     * @desc 下载文书并打印
     * @date 2018/5/8 14:36
     */
    private void downloadAndPrint(final InstrumentModel.InstrumentMsgModel instrumentMsgModel) {
        final String scnr = instrumentMsgModel.getList().get(0).getScnr();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ scnr.substring(scnr.lastIndexOf("/") + 1, scnr.length()));
        if (file.exists()) {
            if (isReview) {
                Intent intent = new Intent();
                Log.e(TAG, "downloadAndPrint: "+scnr.substring(scnr.lastIndexOf("/") + 1, scnr.length()) );
                intent.putExtra("scnr",scnr.substring(scnr.lastIndexOf("/") + 1, scnr.length()));
                intent.setClass(CheckProjectActivity.this, PreviewSdActivity.class);
                startActivity(intent);
            } else {
//                                    onPrintPdf(Constant.UPLOAD_IMG_IP + instrumentMsgModel.getList().get(0).getScnr(),null);
                onPrintPdf(Constant.UPLOAD_IMG_IP + scnr,null);
            }
        }else{
            OkHttpUtils.get()
                    .url(Constant.UPLOAD_IMG_IP + scnr)
                    .build()
                    .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), scnr.substring(scnr.lastIndexOf("/") + 1, scnr.length())) {
                        @Override
                        public void inProgress(float progress) {
                            Log.e(TAG, "inProgress: progress" + progress);
                        }

                        @Override
                        public void onError(Request request, Exception e) {
                            NetUtil.errorTip(CheckProjectActivity.this, e.getMessage());
                        }

                        @Override
                        public void onResponse(File response) {
                            Log.e(TAG, "onResponse: 文书下载完成" + response.getAbsolutePath());
                            //保存下载记录
                            if (scnr != null) {
                                String wsmc = scnr.substring(scnr.lastIndexOf("/") + 1, scnr.length());
                                OkHttpUtils.post()
                                        .url(Constant.SERVER_URL + "/checkDocDownload/save")
                                        .addParams("wsmc", wsmc)
                                        .addParams("wsbdlj", scnr)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Request request, Exception e) {
                                                NetUtil.errorTip(CheckProjectActivity.this, e.getMessage() + "/checkDocDownload/save");
                                            }

                                            @Override
                                            public void onResponse(String response) {
                                                Log.e(TAG, "onResponse: 保存下载记录成功" + response);
                                            }
                                        });
                                if (isReview) {
                                    Intent intent = new Intent();
                                    intent.setClass(CheckProjectActivity.this, PreviewActivity.class);
                                    Log.e(TAG, "onResponse: " + Constant.UPLOAD_IMG_IP + instrumentMsgModel.getList().get(0).getScnr());
                                    intent.putExtra("url", Constant.UPLOAD_IMG_IP + instrumentMsgModel.getList().get(0).getScnr());
                                    startActivity(intent);
//                                    intent.setClass(CheckProjectActivity.this, PreviewSdActivity.class);
//                                    startActivity(intent);
                                } else {
//                                    onPrintPdf(Constant.UPLOAD_IMG_IP + instrumentMsgModel.getList().get(0).getScnr(),null);
                                    onPrintPdf(Constant.UPLOAD_IMG_IP + scnr,null);
                                }
                            } else {
                                Log.e(TAG, "onResponse: 文书地址为空");
                            }
                        }
                    });
        }

    }

    /**
     * @param url
     * @desc 打印pdf
     * @date 2018/5/22 17:38
     */
    private void onPrintPdf(String url,File file) {
        PrintManager printManager = (PrintManager) CheckProjectActivity.this.getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(PrintAttributes.COLOR_MODE_COLOR);
            printManager.print("test pdf print", new MyPrintAdapter(this,url,jcId), builder.build());

//        printManager.print("test pdf print", new MyPrintAdapter(this, url, "ddd", "13455209261", "2018-06-12", "2018-06-12", "hahah", 5), builder.build());
    }


}
