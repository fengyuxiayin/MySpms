package com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.checkrecordactivitys;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.CustomScanActivity;
import com.example.lzc.myspms.activitys.PrintActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys.CheckItemsActivity;
import com.example.lzc.myspms.adapters.MyPrintAdapter;
import com.example.lzc.myspms.adapters.PopupMainAdapter;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.SpecialEquipmentFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.DataFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.EquipmentFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.FireFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.SceneFragment;
import com.example.lzc.myspms.models.AccountIdModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.DepartIdModel;
import com.example.lzc.myspms.models.InstrumentModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.ProjectByJcIdModel;
import com.example.lzc.myspms.models.ProjectInfoModel;
import com.example.lzc.myspms.models.ScanResultModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class CheckRecordItemActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = CheckRecordItemActivity.class.getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private TextView tvTitle;
    private TextView tvName;
    private Fragment mShowFragment;
    private RadioGroup radioGroup;
    //从上一页传进来的企业id
    private String qyId;
    //从上一页传进来的企业名称
    private String qymc;
    private String zqjlId;
    private String jclx;
    private String jcId;
    private TextView tvTime;
    private EditText etChangeTime;
    private Button btnCommit;
    private RadioButton rbEquipment;
    private RadioButton rbScene;
    private RadioButton rbData;
    private RadioButton rbFire;
    private RadioButton rbSpecialEquipment;
    //从fragment(EquipFragment SpecialEquipment)中传出来的jcxmId 与扫描二维码获取的数据中的id作比较
    private String jcxmId;
    private Gson gson = new Gson();
    private Button btnPrint;
    private boolean setReCheckSuccess = false;
    //菜单按钮的文字内容
    private String[] titleArray = new String[]{"扫描企业", "视频通话"};
    //菜单按钮的图片资源
    private Integer[] imgArray = new Integer[]{R.mipmap.qrcode_white, R.mipmap.videocall};
    private View contentView;
    private PopupWindow popupWindow;
    private LinearLayout linearLayout;
    //存储所有项目 合格项目 不合格项目的名称 推送给指定用户需要
    private StringBuilder sbAllProject = new StringBuilder();
    private StringBuilder sbQualifiedProject = new StringBuilder();
    private StringBuilder sbUnqialifiedProject = new StringBuilder();
    //部门id的集合
    private List<String> listDepart = new ArrayList<>();
    //账号id的集合
    private List<String> listAccount = new ArrayList<>();
    //账号id的字符串
    private StringBuilder sbAccountId = new StringBuilder();
    //延时执行的timertask
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (listAccount.size() != 0) {
                //在这里连接soccket
                connect();
            } else {
                Log.e(TAG, "run: 天啦噜，都没账户，还想推送，抓紧查查怎么回事吧");
            }
        }
    };
    //websocket
    //    public static final String wsurl = "wss://120.25.251.167:2443/sendMsg.so";
    public static final String wsurl = "ws://192.168.3.69:2017/";
    //    public static final String wsurl = "ws://192.168.3.69:8080/sendMsg.so";
    //websocket对象
    private WebSocketClient webSocketClient;
    private Timer timer;
    private String xxnr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_record_item);
        qyId = getIntent().getStringExtra("qyId");
        qymc = getIntent().getStringExtra("qymc");
        zqjlId = getIntent().getStringExtra("zqjlId");
        jclx = getIntent().getStringExtra("jclx");
        jcId = getIntent().getStringExtra("jcId");
        Log.e(TAG, "onCreate: " + "qyId" + qyId + "zqjlId" + zqjlId + "jclx" + jclx + "jcId" + jcId);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        //设置radiobutton的图片大小
        Drawable drawableHomepage = getResources().getDrawable(R.mipmap.equipment);
        drawableHomepage.setBounds(0, 5, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbEquipment.setCompoundDrawables(null, drawableHomepage, null, null);

        Drawable drawableCheck = getResources().getDrawable(R.mipmap.scene);
        drawableCheck.setBounds(0, 5, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbScene.setCompoundDrawables(null, drawableCheck, null, null);

        Drawable drawableNewCheck = getResources().getDrawable(R.mipmap.data);
        drawableNewCheck.setBounds(0, 5, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbData.setCompoundDrawables(null, drawableNewCheck, null, null);

        Drawable drawableNCheckProgress = getResources().getDrawable(R.mipmap.fire);
        drawableNCheckProgress.setBounds(0, 5, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbFire.setCompoundDrawables(null, drawableNCheckProgress, null, null);

        Drawable drawableReCheck = getResources().getDrawable(R.mipmap.special_equipment);
        drawableReCheck.setBounds(0, 5, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbSpecialEquipment.setCompoundDrawables(null, drawableReCheck, null, null);
        //显示当前第一个页面
        mShowFragment = new EquipmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("qyId", qyId);
        bundle.putString("qymc", qymc);
        bundle.putString("zqjlId", zqjlId);
        bundle.putString("jclx", jclx);
        bundle.putString("jcId", jcId);
        //主要是为了ProjectAdapter判断是检查项目还是查看项目 CheckCatalogActivity是修改项目
        bundle.putString("startClass", "CheckRecordItemActivity");
        mShowFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_check_record_item_fl, mShowFragment, EquipmentFragment.TAG);
        transaction.show(mShowFragment);
        transaction.commit();
    }

    private void connect() {
        Log.e(TAG, "ws connect....");
        try {
            webSocketClient = new WebSocketClient(new URI(wsurl)) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.e(TAG, "onOpen: ");
                    //像服务器发送推送的数据
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL + "/sysMessage/sendMsg")
                            .addParams("xxbt", "检查信息")
                            .addParams("xxnr", xxnr)
                            .addParams("jszlx", "5")
                            .addParams("ids", sbAccountId.toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: /sysMessage/sendMsg" + response);
                                }
                            });
                }

                @Override
                public void onMessage(String message) {
                    Log.e(TAG, "onMessage: " + message);

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.e(TAG, "onClose: " + "code: " + code + "  close reason: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    Log.e(TAG, "onError: " + ex.getMessage() + ex.getCause());
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            sslContext.init(null, new TrustManager[]{
                    new X509TrustManager() {

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {

                        }


                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            }, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        SSLSocketFactory factory = sslContext.getSocketFactory();
        webSocketClient.connect();

    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        etChangeTime.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
        btnPrint.setOnClickListener(this);
    }

    private void initView() {
        tvName = (TextView) findViewById(R.id.activity_check_record_item_name);
        tvName.setText(qymc);
        tvTime = (TextView) findViewById(R.id.activity_check_record_item_time);
        radioGroup = (RadioGroup) findViewById(R.id.activity_check_record_item_rg);
        rbEquipment = (RadioButton) findViewById(R.id.activity_check_record_item_equipment);
        rbScene = (RadioButton) findViewById(R.id.activity_check_record_item_scene);
        rbData = (RadioButton) findViewById(R.id.activity_check_record_item_data);
        rbFire = (RadioButton) findViewById(R.id.activity_check_record_item_fire);
        rbSpecialEquipment = (RadioButton) findViewById(R.id.activity_check_record_item_special_equipment);
        etChangeTime = (EditText) findViewById(R.id.activity_check_record_item_et_change_time);
        btnCommit = (Button) findViewById(R.id.activity_check_record_item_btn_commit);
        btnPrint = (Button) findViewById(R.id.activity_check_record_item_btn_print);
        linearLayout = (LinearLayout) findViewById(R.id.activity_check_record_item_header);
        imgBack = (ImageView) findViewById(R.id.activity_check_record_item_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_check_record_item_header).findViewById(R.id.videocall);
        imgVideocall.setImageResource(R.mipmap.menu);
        tvTitle = (TextView) findViewById(R.id.activity_check_record_item_header).findViewById(R.id.title);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.back:
                    finish();
                    break;
                case R.id.videocall:
                    contentView = View.inflate(this, R.layout.popup_main_menu, null);
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                        popupWindow = null;
                    } else {
                        ListView listView = (ListView) contentView.findViewById(R.id.popup_main_menu_lv);
                        PopupMainAdapter popupMainAdapter = new PopupMainAdapter(titleArray, imgArray, this);
                        listView.setAdapter(popupMainAdapter);
                        popupWindow = new PopupWindow();
                        popupWindow.setBackgroundDrawable(new BitmapDrawable());
                        popupWindow.setOutsideTouchable(true);
                        popupWindow.setWidth((int) (getWindowManager().getDefaultDisplay().getWidth() * 0.2));
                        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupWindow.setContentView(contentView);
                        popupWindow.showAsDropDown(linearLayout, 0, 0, Gravity.RIGHT);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent;
                                switch (position) {
                                    case 0://扫描二维码
                                        new IntentIntegrator(CheckRecordItemActivity.this)
                                                .setOrientationLocked(false)
                                                .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
                                                .initiateScan();
                                        popupWindow.dismiss();
                                        popupWindow = null;
                                        break;
                                    case 1://视频通话
                                        intent = new Intent();
                                        intent.setClass(CheckRecordItemActivity.this, VideoCallActivity.class);
                                        startActivity(intent);
                                        popupWindow.dismiss();
                                        popupWindow = null;
                                        break;
                                    case 2:
                                        new IntentIntegrator(CheckRecordItemActivity.this)
                                                .setOrientationLocked(false)
                                                .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
                                                .initiateScan();
                                        break;
                                }
                            }
                        });
                    }
                    break;
                case R.id.activity_check_record_item_et_change_time:
                    setDate(etChangeTime);
                    break;
                case R.id.activity_check_record_item_btn_commit:
                    //提交整改信息
                    if (etChangeTime.getText().length() > 0) {
                        Log.e(TAG, "onClick: " + "jcid" + jcId + "qyId" + qyId + "zqjlId" + zqjlId + "repairDate" + etChangeTime.getText().toString());
                        OkHttpUtils.post()
                                .url(Constant.SERVER_URL + "/checkInfo/setReCheck")
                                .addParams("id", jcId)
                                .addParams("qyId", qyId)
                                .addParams("zqjlId", zqjlId)
                                .addParams("repairDate", etChangeTime.getText().toString())
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {
                                        Log.e(TAG, "onError: " + e.getMessage());
                                        NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        Log.e(TAG, "onResponse: " + response);
                                        final Gson gson = new Gson();
                                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                        if (infoModel.isData()) {
                                            Toast.makeText(CheckRecordItemActivity.this, "修改整改期限成功", Toast.LENGTH_SHORT).show();
                                            //与服务器建立连接并向服务器推送项目的信息到指定账户id上
//                                            postMsgToAccount(gson);
                                        } else {
//                                            postMsgToAccount(gson);
                                            Toast.makeText(CheckRecordItemActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(this, "请先选择整改期限", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.activity_check_record_item_btn_print:
                    //下载文书并跳转到打印
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL + "/checkUpload/find")
                            .addParams("jcId", jcId)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: /checkUpload/find" + response);
                            gson = new Gson();
                            final LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                            if (infoModel.isData()) {
                                InstrumentModel.InstrumentMsgModel instrumentMsgModel = gson.fromJson(infoModel.getMsg(), InstrumentModel.InstrumentMsgModel.class);
                                //如果文书数量大于0 那么直接去下载打印 如果文书数量等于零 那么先去保存再去打印
                                if (instrumentMsgModel.getTotal() == 0) {
                                    //先保存后打印
                                    Log.e(TAG, "onResponse: " + "jciD" + jcId + "jclx" + jclx);
                                    generateAndUpload(infoModel);
                                } else {
                                    //直接打印文书
                                    downloadAndPrint(instrumentMsgModel);
                                }
                            } else {
                                Toast.makeText(CheckRecordItemActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
            }
        }
    }
/**
 *
 *@desc 生成并上传文书
 *@param infoModel 数据源
 *@date 2018/5/8 14:24
*/
    private void generateAndUpload(final LoginInfoModel infoModel) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkReport/generateAndUpload")
                .addParams("id", jcId)
                .addParams("jclx", jclx)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /checkReport/generateAndUpload" + response);
                        LoginInfoModel infoModel1 = gson.fromJson(response, LoginInfoModel.class);
                        //上传成功的话就下载并打印打印文书
                        if (infoModel.isData()) {
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/checkUpload/find")
                                    .addParams("jcId", jcId)
                                    .build().execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: /checkUpload/find"+response);
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    if (infoModel.isData()) {
                                        final InstrumentModel.InstrumentMsgModel instrumentMsgModel = gson.fromJson(infoModel.getMsg(), InstrumentModel.InstrumentMsgModel.class);
                                        if (instrumentMsgModel.getTotal() == 0) {//文书数量为零
                                            Log.e(TAG, "onResponse: " + "文书数量不为零");
                                            Toast.makeText(CheckRecordItemActivity.this, "还没有生成文书", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Log.e(TAG, "onResponse: " + "文书数量不为零");
                                            downloadAndPrint(instrumentMsgModel);
                                        }
                                    } else {
                                        Toast.makeText(CheckRecordItemActivity.this, "", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(CheckRecordItemActivity.this, "生成文书失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
/**
 *
 *@desc 下载文书并打印
 *@param instrumentMsgModel 数据源 包含文书地址什么的
 *@date 2018/5/8 14:36
*/
    private void downloadAndPrint(final InstrumentModel.InstrumentMsgModel instrumentMsgModel) {
        final String scnr = instrumentMsgModel.getList().get(0).getScnr();
        OkHttpUtils.get()
                .url(scnr)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), scnr.substring(scnr.lastIndexOf("/") + 1, scnr.length())) {
                    @Override
                    public void inProgress(float progress) {
                        Log.e(TAG, "inProgress: progress" + progress);
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(File response) {
                        Log.e(TAG, "onResponse: 文书下载完成" + response);
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
                                            NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Log.e(TAG, "onResponse: 保存下载记录成功" + response);
                                        }
                                    });
                            Intent intent1 = new Intent();
//                            intent1.setClass(CheckRecordItemActivity.this, PrintActivity.class);
                            onPrintPdf();
//                            intent1.putExtra("filePath", instrumentMsgModel.getList().get(0).getHtmlUrl());
//                            startActivity(intent1);
                        } else {
                            Log.e(TAG, "onResponse: 文书地址为空");
                        }
                    }
                });
    }
    /**
     *
     *@desc 打印pdf
     *@date 2018/5/22 17:38
     */
    private void onPrintPdf() {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(PrintAttributes.COLOR_MODE_COLOR);
        printManager.print("test pdf print", new MyPrintAdapter(this,"http://120.25.251.167:8088/hfs/source/document/20185/T3.pdf"), builder.build());
    }
    /**
     * @param gson
     * @desc 查询数据与服务器建立连接并向服务器推送查询出来的项目信息到指定账户id上
     * @date 2018/5/3 15:00
     */
    private void postMsgToAccount(final Gson gson) {
        //根据检查id查询项目信息
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/findByJcId")
                .addParams("jcId", jcId)
                .addParams("size", "1000")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /checkProject/findByJcId" + response);
                        ProjectByJcIdModel projectByJcIdModel = gson.fromJson(response, ProjectByJcIdModel.class);
                        if (projectByJcIdModel.isData()) {
                            ProjectByJcIdModel.ProjectByJcIdMsgModel projectByJcIdMsgModel = gson.fromJson(projectByJcIdModel.getMsg(), ProjectByJcIdModel.ProjectByJcIdMsgModel.class);
                            List<ProjectByJcIdModel.ProjectByJcIdMsgModel.ListBean> list = projectByJcIdMsgModel.getList();
                            if (list != null) {
                                //清空存储部门id的列表 账户id的列表
                                listDepart.clear();
                                listAccount.clear();
                                //每次进入时先清空stringbulider
                                sbAllProject.delete(0, sbAllProject.length());
                                sbQualifiedProject.delete(0, sbQualifiedProject.length());
                                sbUnqialifiedProject.delete(0, sbUnqialifiedProject.length());
                                sbAccountId.delete(0, sbAccountId.length());
                                //循环填写项目的数据 并获取部门id 并根据部门id获取账户id
                                for (int i = 0; i < list.size(); i++) {
                                    if (i == list.size() - 1) {
                                        sbAllProject.append(list.get(i).getXmmc() + ";");
                                        if (list.get(i).getJcjg() == 0) {
                                            sbUnqialifiedProject.append(list.get(i).getXmmc());
                                        } else {
                                            sbQualifiedProject.append(list.get(i).getXmmc() + ";");
                                        }
                                    } else {
                                        sbAllProject.append(list.get(i).getXmmc() + "、");
                                        if (list.get(i).getJcjg() == 0) {
                                            sbUnqialifiedProject.append(list.get(i).getXmmc() + "、");
                                        } else {
                                            sbQualifiedProject.append(list.get(i).getXmmc() + "、");
                                        }
                                    }
                                    //在这里去获取sendDepart 字段
                                    Log.e(TAG, "onResponse: getdepartid");
                                    getDepartId(list.get(i).getXmlx());
                                }
                                //因为上面的循环需要一定时间去执行 所以我们延迟20s执行一下task 本来应该写一个回调去执行的
                                timer = new Timer();
                                timer.schedule(task, 5000);
                                Log.e(TAG, "onResponse: " + sbAllProject + sbQualifiedProject + sbUnqialifiedProject);
                                //在这个地方组装 xxnr
                                if (sbAllProject.length() == 0) {
                                    sbAllProject.append("无;");
                                }
                                if (sbQualifiedProject.length() == 0) {
                                    sbQualifiedProject.append("无;");
                                }
                                if (sbUnqialifiedProject.length() == 0) {
                                    sbUnqialifiedProject.append("无");
                                }
                                xxnr = sbAllProject.toString() + sbQualifiedProject.toString() + sbUnqialifiedProject.toString();
                                //组装账户id的集合
                                for (int i = 0; i < listAccount.size(); i++) {
                                    if (i == listAccount.size() - 1) {
                                        sbAccountId.append(listAccount.get(i));
                                    } else {
                                        sbAccountId.append(listAccount.get(i) + ",");
                                    }
                                }
                                Log.e(TAG, "onResponse: " + "xxnr" + xxnr);

                            }

                        } else {
                            Toast.makeText(CheckRecordItemActivity.this, projectByJcIdModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * @param xmlx
     * @desc 获取部门id 并根据部门id获取账户id
     * @date 2018/4/27 9:34
     */
    private void getDepartId(int xmlx) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseSafetyHazard/findById")
//                .addParams("id", "359")
                .addParams("id", xmlx + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /baseSafetyHazard/findById" + response);
                        DepartIdModel departIdModel = gson.fromJson(response, DepartIdModel.class);
                        if (departIdModel.isData()) {
                            if (departIdModel.getMsg() != null) {
                                DepartIdModel.DepartIdMsgModel departIdMsgModel = gson.fromJson(departIdModel.getMsg(), DepartIdModel.DepartIdMsgModel.class);
                                String sendDepart = departIdMsgModel.getSendDepart();
                                if (sendDepart != null) {
                                    List<String> strings = Arrays.asList(sendDepart.split(","));
                                    for (int i = 0; i < strings.size(); i++) {
//                                        listDepart.add(strings.get(i));
                                        //获取账户id
                                        getAccountId(strings.get(i));
                                    }
                                }
                            } else {
                                Log.e(TAG, "onResponse: 项目类型很可能不对");
                            }
                            Log.e(TAG, "onResponse: " + listDepart.size());
                        } else {
                            Toast.makeText(CheckRecordItemActivity.this, departIdModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * @param ssId 所属部门id
     * @desc 根据部门id获取账户id
     * @date 2018/4/27 14:51
     */
    private void getAccountId(String ssId) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/authAccount/find")
                .addParams("loginType", "5")
                .addParams("ssId", ssId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /authAccount/find" + response);
                        AccountIdModel accountIdModel = gson.fromJson(response, AccountIdModel.class);
                        if (accountIdModel.isData()) {
                            AccountIdModel.AccountIdMsgModel accountIdMsgModel = gson.fromJson(accountIdModel.getMsg(), AccountIdModel.AccountIdMsgModel.class);
                            List<AccountIdModel.AccountIdMsgModel.ListBean> list = accountIdMsgModel.getList();
                            if (list != null) {
                                for (int j = 0; j < list.size(); j++) {
                                    listAccount.add(list.get(j).getId() + "");
                                }
                            }
                        } else {
                            Toast.makeText(CheckRecordItemActivity.this, accountIdModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * @param jcxmId 检查项目id
     * @desc 从fragment传过来的检查项目id
     * @date 2018/3/23 10:22
     */
    public void getJcxmId(String jcxmId) {
        this.jcxmId = jcxmId;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //获取二维码扫描回来的数据
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            Log.e(TAG, "onActivityResult: CheckRecordItem");
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "内容为空或取消了扫描", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "扫描成功", Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                Log.e(TAG, "onActivityResult: " + ScanResult);
                if (ScanResult.length() > 0) {
                    gson = new Gson();
                    ScanResultModel scanResultModel = gson.fromJson(ScanResult, ScanResultModel.class);
//                    if (jcxmId.equals(scanResultModel.getId())) {//扫描的二维码就是当前这个项目生成的
                    //跳转到CheckItesActivity
                    this.jcxmId = scanResultModel.getId() + "";
                    getProjectDetailData(scanResultModel.getQyId());
//                    } else {
//                        Toast.makeText(this, "当前二维码不属于该项目", Toast.LENGTH_SHORT).show();
//                    }
                } else {
                    Toast.makeText(this, "这是个报废二维码，里面没有数据", Toast.LENGTH_SHORT).show();
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * @param qyId 企业id
     * @desc 获取当前项目的详细信息并跳转到对应的aavtivity
     * @date 2018/3/23 10:24
     */
    private void getProjectDetailData(final String qyId) {
        Log.e(TAG, "getProjectDetailData: " + "jcxmid" + jcxmId + "jcid" + jcId);
        final Intent intent = new Intent();
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/findByJcIdAndJcxmId")
                .addParams("jcxmId", jcxmId)
                .addParams("jcId", jcId)
                .build()
                .execute(new StringCallback() {
                    private ProjectInfoModel.ProjectInfoMsgModel projectInfoMsgModel;
                    private ProjectInfoModel projectInfoModel;
                    private LoginInfoModel loginInfoModel;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "/checkProject/findByJcIdAndJcxmId" + response);
                        projectInfoModel = gson.fromJson(response, ProjectInfoModel.class);
                        if (projectInfoModel.isData()) {
                            projectInfoMsgModel = gson.fromJson(projectInfoModel.getMsg(), ProjectInfoModel.ProjectInfoMsgModel.class);
                            intent.putExtra("zqjlId", zqjlId);
                            intent.putExtra("jcxmId", jcxmId);
                            intent.putExtra("jcId", jcId);
                            intent.putExtra("jcxId", projectInfoMsgModel.getId() + "");
                            intent.putExtra("qyId", qyId);
                            //status 是1.0的时候隐藏保存按钮 但是我这里既然能扫描 肯定不是1.0 所以我就随便传了一个值
                            intent.putExtra("status", "0");
                            intent.setClass(CheckRecordItemActivity.this, CheckItemsActivity.class);
                            CheckRecordItemActivity.this.startActivity(intent);
                        } else {
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/checkProject/save")
                                    .addParams("jcxmId", jcxmId)
                                    .addParams("jcId", jcId)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            Log.e(TAG, "onError: " + e.getMessage());
                                            NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Log.e(TAG, "onResponse: save" + response);
                                            projectInfoModel = gson.fromJson(response, ProjectInfoModel.class);
                                            if (projectInfoModel.isData()) {
                                                //保存成功后再去查询数据
                                                OkHttpUtils.post()
                                                        .url(Constant.SERVER_URL + "/checkProject/findByJcIdAndJcxmId")
                                                        .addParams("jcxmId", jcxmId)
                                                        .addParams("jcId", jcId)
                                                        .build()
                                                        .execute(new StringCallback() {
                                                            @Override
                                                            public void onError(Request request, Exception e) {
                                                                NetUtil.errorTip(CheckRecordItemActivity.this, e.getMessage());
                                                            }

                                                            @Override
                                                            public void onResponse(String response) {
                                                                projectInfoModel = gson.fromJson(response, ProjectInfoModel.class);
                                                                if (projectInfoModel.isData()) {
                                                                    projectInfoMsgModel = gson.fromJson(projectInfoModel.getMsg(), ProjectInfoModel.ProjectInfoMsgModel.class);
                                                                    intent.putExtra("zqjlId", zqjlId + "");
                                                                    intent.putExtra("jcxmId", jcxmId);
                                                                    intent.putExtra("jcId", jcId);
                                                                    intent.putExtra("jcxId", projectInfoMsgModel.getId() + "");
                                                                    intent.putExtra("qyId", qyId);
                                                                    intent.putExtra("status", "0");
                                                                    intent.setClass(CheckRecordItemActivity.this, CheckItemsActivity.class);
                                                                    CheckRecordItemActivity.this.startActivity(intent);
                                                                } else {
                                                                    Toast.makeText(CheckRecordItemActivity.this, projectInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                                                }

                                                            }
                                                        });

                                            } else {
                                                Toast.makeText(CheckRecordItemActivity.this, "保存检查点信息失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
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
        DatePickerDialog dialog = new DatePickerDialog(CheckRecordItemActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int currentYear = calendar.get(Calendar.YEAR);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
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
                            Toast.makeText(CheckRecordItemActivity.this, "整改时间需晚于当前时间", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CheckRecordItemActivity.this, "整改时间需晚于当前时间", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CheckRecordItemActivity.this, "整改时间需晚于当前时间", Toast.LENGTH_SHORT).show();
                }

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.activity_check_record_item_equipment:
                switchPages(EquipmentFragment.TAG, EquipmentFragment.class);
                break;
            case R.id.activity_check_record_item_scene:
                switchPages(SceneFragment.TAG, SceneFragment.class);
                break;
            case R.id.activity_check_record_item_data:
                switchPages(DataFragment.TAG, DataFragment.class);
                break;
            case R.id.activity_check_record_item_fire:
                switchPages(FireFragment.TAG, FireFragment.class);
                break;
            case R.id.activity_check_record_item_special_equipment:
                switchPages(SpecialEquipmentFragment.TAG, SpecialEquipmentFragment.class);
                break;

        }
    }

    private void switchPages(String tag, Class<? extends Fragment> cls) {
        /**
         * 将当前显示的碎片进行隐藏，之后将要显示的页面显示出来
         */
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏显示的页面
        transaction.hide(mShowFragment);
        // 根据TAG去FragmentManager中进行查找碎片
        mShowFragment = fm.findFragmentByTag(tag);

        // 如果找到了，直接进行显示
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            // 如果没找到，添加到容器中并且设置了一个标记
            try {
                // 使用反射进行了一个对象的实例化
                mShowFragment = cls.getConstructor().newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("qyId", qyId);
                bundle.putString("qymc", qymc);
                bundle.putString("zqjlId", zqjlId);
                bundle.putString("jclx", jclx);
                bundle.putString("jcId", jcId);
                //主要是为了ProjectAdapter判断是检查项目还是查看项目 CheckCatalogActivity是修改项目
                bundle.putString("startClass", "CheckRecordItemActivity");
                mShowFragment.setArguments(bundle);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.activity_check_record_item_fl, mShowFragment, tag);
        }
        transaction.commit();
    }
}
