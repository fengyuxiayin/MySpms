package com.example.lzc.myspms.activitys.queryactivitys;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckItemSearchResultActivity;
import com.example.lzc.myspms.models.CheckItemsQueryModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckItemsQueryActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = CheckItemsQueryActivity.class.getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private TextView tvTitle;
    private EditText etEnterprise;
    private EditText etProjectName;
    private EditText etProjectNumber;
    private EditText etProjectType;
    private EditText etCheckTime;
    private EditText etRecordStart;
    private EditText etRecordEnd;
    private CheckBox cvQualified;
    private CheckBox cvUnQualified;
    //项目类型的数据
    private String[] arrayType;
    private List<EnumModel> dataType = new ArrayList<>();
    private Button btnSearch;
    //根据checkbox的选中状态改变其值，用来提交到服务器
    private String qualified = "";
    private String unQualified = "";
    private Gson gson;
    private String xmlx;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_items_query);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSearch.setFocusable(true);
        btnSearch.setFocusableInTouchMode(true);
        btnSearch.requestFocus();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        cvQualified.setOnCheckedChangeListener(this);
        cvUnQualified.setOnCheckedChangeListener(this);
        etProjectType.setOnClickListener(this);
        etCheckTime.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);

    }

    private void initData() {
        gson = new Gson();
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.activity_check_items_query_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_check_items_query_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_check_items_query_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_check_items_query_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_check_items_query_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_check_items_query_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_check_items_query_header).findViewById(R.id.title);
        tvTitle.setText("检查项多维度查询");
        etEnterprise = (EditText) findViewById(R.id.activity_check_items_query_et_enterprise);
        etProjectName = (EditText) findViewById(R.id.activity_check_items_query_et_projectname);
        etProjectNumber = (EditText) findViewById(R.id.activity_check_items_query_et_projectnumber);
        etProjectType = (EditText) findViewById(R.id.activity_check_items_query_et_projecttype);
        etCheckTime = (EditText) findViewById(R.id.activity_check_items_query_et_checktime);
        etRecordStart = (EditText) findViewById(R.id.activity_check_items_query_et_record_start);
        etRecordEnd = (EditText) findViewById(R.id.activity_check_items_query_et_record_end);
        cvQualified = (CheckBox) findViewById(R.id.activity_check_items_query_cv_qualified);
        cvUnQualified = (CheckBox) findViewById(R.id.activity_check_items_query_cv_unqualified);
        btnSearch = (Button) findViewById(R.id.activity_check_items_query_btn_search_info);
    }

    @Override
    public void onClick(View v) {

        if (v != null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), CheckItemsQueryActivity.this,CheckItemsQueryActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {

                case R.id.activity_check_items_query_et_projecttype:
                    arrayType = new String[6];
                    arrayType[0] = "全部";
                    arrayType[1] = "设备类";
                    arrayType[2] = "现场类";
                    arrayType[3] = "资料类";
                    arrayType[4] = "消防类";
                    arrayType[5] = "特种设备类";
                    dataType.add(new EnumModel("", "全部"));
                    dataType.add(new EnumModel("359", "设备类"));
                    dataType.add(new EnumModel("56", "现场类"));
                    dataType.add(new EnumModel("1", "资料类"));
                    dataType.add(new EnumModel("167", "消防类"));
                    dataType.add(new EnumModel("76", "特种设备类"));
                    showChooseDialog(arrayType, etProjectType);
                    break;
                case R.id.activity_check_items_query_et_checktime:
                    setData(etCheckTime);
                    break;
                case R.id.activity_check_items_query_btn_search_info:
                    xmlx = "";
                    for (int i = 0; i < dataType.size(); i++) {
                        if (dataType.get(i).getValue().equals(etProjectType.getText().toString().trim())) {
                            xmlx = dataType.get(i).getKey() + "";
                            break;
                        }
                    }
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL + "/project/query")
                            .addParams("qymc", etEnterprise.getText().toString().trim()+"")
                            .addParams("xmmc", etProjectName.getText().toString().trim()+"")
                            .addParams("xmbm", etProjectNumber.getText().toString().trim()+"")
                            .addParams("xmlx", xmlx)
                            .addParams("checkData", etCheckTime.getText().toString().trim()+"")
                            .addParams("bhgjlStart", etRecordStart.getText().toString().trim()+"")
                            .addParams("bhgjlEnd", etRecordEnd.getText().toString().trim()+"")
                            .addParams("yhg", qualified)
                            .addParams("whg", unQualified)
                            .addParams("pn","1")
                            .addParams("size","10")
                            .build()
                            .execute(new StringCallback() {
                                private List<CheckItemsQueryModel.CheckItemsQueryMsgModel.ListBean> list;

                                @Override
                                public void onError(Request request, Exception e) {
                                    NetUtil.errorTip(CheckItemsQueryActivity.this,e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: " + response);
                                    CheckItemsQueryModel checkItemsQueryModel = gson.fromJson(response, CheckItemsQueryModel.class);
                                    if (checkItemsQueryModel.isData()) {
                                        CheckItemsQueryModel.CheckItemsQueryMsgModel checkItemsQueryMsgModel = gson.fromJson(checkItemsQueryModel.getMsg(), CheckItemsQueryModel.CheckItemsQueryMsgModel.class);
                                        list = checkItemsQueryMsgModel.getList();
                                        if (list.size() > 0) {
                                            Intent intent = new Intent();
                                            intent.putExtra("qymc", etEnterprise.getText().toString().trim());
                                            intent.putExtra("xmmc", etProjectName.getText().toString().trim());
                                            intent.putExtra("xmbm", etProjectNumber.getText().toString().trim());
                                            intent.putExtra("xmlx", xmlx);
                                            intent.putExtra("checkDate", etCheckTime.getText().toString().trim());
                                            intent.putExtra("bhgjlStart", etRecordStart.getText().toString().trim());
                                            intent.putExtra("bhgjlEnd", etRecordEnd.getText().toString().trim());
                                            intent.putExtra("yhg", qualified);
                                            intent.putExtra("whg", unQualified);
                                            intent.setClass(CheckItemsQueryActivity.this, CheckItemSearchResultActivity.class);
                                            intent.putExtra("data", (Serializable) list);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(CheckItemsQueryActivity.this, "没有找到符合条件的项目", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(CheckItemsQueryActivity.this,checkItemsQueryModel.getMsg()+"121112" , Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                    break;
            }
        }
    }

    /**
     * @param edittext 需要设置显示内容的Edittext
     * @desc 显示日历，并将选择的时间变为指定格式显示在Edittext上
     * @date 2017/12/11 14:27
     */
    private void setData(final EditText edittext) {
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

    private void showChooseDialog(final String[] arry, final EditText epointName) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框
        int which = 0;
        for (int i = 0; i < arry.length; i++) {
            if (epointName.getText().toString().equals(arry[i])) {
                which = i;
                break;
            }
        }
        builder.setSingleChoiceItems(arry, which, new DialogInterface.OnClickListener() {// 0默认的选中
            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                epointName.setText(arry[which]);
                dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Drawable drawable = getResources().getDrawable(R.drawable.checkbox_style);
        drawable.setBounds(0, 0, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        switch (buttonView.getId()) {
            case R.id.activity_check_items_query_cv_qualified:
                if (isChecked) {
                    qualified = "1";
                } else {
                    qualified = "";
                }
                break;
            case R.id.activity_check_items_query_cv_unqualified:
                if (isChecked) {
                    unQualified = "0";
                } else {
                    unQualified = "";
                }
                break;
        }
    }
}
