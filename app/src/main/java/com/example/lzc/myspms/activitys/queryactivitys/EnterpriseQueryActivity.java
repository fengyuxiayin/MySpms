package com.example.lzc.myspms.activitys.queryactivitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.adapters.AreaEnterpriswAdapter;
import com.example.lzc.myspms.adapters.EnterpriseInfoQueryAdapter;
import com.example.lzc.myspms.custom.MyGridView;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnterpriseInfoQueryModel;
import com.example.lzc.myspms.models.EnumCommunityModel;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseQueryActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = EnterpriseQueryActivity.class.getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private TextView tvTitle;
    private MyGridView gridView;
    private ScrollView scrollview;
    //投资额的编辑框
    private EditText etInvestmentStart;
    private EditText etInvestmentEnd;
    //职工人数的编辑框
    private EditText etPeopleStart;
    private EditText etPeopleEnd;
    //行业归属编辑框
    private EditText etIndustry;
    //营业额编辑框
    private EditText etTurnoverStart;
    private EditText etTurnoverEnd;
    //判断gridView是否显示 true 为不显示
    private boolean isPullDown = true;
    private ImageView imgXiala;
    //搜索信息
    private Button btnSearch;
    //所属社区
    private EditText etSssq;
    //存储行业类别的信息
    private String[] arrayIndustry;
    private List<EnumModel> dataIndustry = new ArrayList<>();
    //存储所属社区的信息
    private String[] arrayCommunity;
    private List<EnumModel> dataCommunity = new ArrayList<>();
    //存储选中的checkbox的位置信息
    private List<Integer> dataPosition = new ArrayList<>();
    private List<String> dataEconomicChecked = new ArrayList<>();
    //经济类型代码数据
    private List<EnumModel> dataEconomic = new ArrayList<>();
    //与AreaEnterpriseActivity共用一个adapter
    private AreaEnterpriswAdapter areaEnterpriswAdapter;
    private Gson gson;
    private String listToJson;
    private List<EnterpriseInfoQueryModel.ListSet.ListBean> list;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_query);
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
//        linearlayout.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        etIndustry.setOnClickListener(this);
        etSssq.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
        getEnumData("INDUSTRY_CATEGORY_TYPE");
        getEnumData("ECONOMIC_TYPE");
        getCommunityEnumData("/community/findAll");
    }

    private void getCommunityEnumData(String params) {
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + params)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                
            }

            @Override
            public void onResponse(String response) {
                if (response != null) {
                    //解析json
                    EnumCommunityModel allCommunityModel = gson.fromJson(response, EnumCommunityModel.class);
                    try {
                        JSONArray jsonArray = new JSONArray(allCommunityModel.getMsg());
                        if (jsonArray==null) {
                            Toast.makeText(EnterpriseQueryActivity.this, "社区信息为空", Toast.LENGTH_SHORT).show();
                        }else{
                            arrayCommunity = new String[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject o = (JSONObject) jsonArray.get(i);
                                String value = o.getString("sqmc");
                                String key = o.getString("id");
                                dataCommunity.add(new EnumModel(key, value));
                                arrayCommunity[i] = value;
                            }
//                            etSssq.setText(arrayCommunity[0]);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.activity_enterprise_query_no_network_connection);
//        linearLayout1 = (LinearLayout) findViewById(R.id.activity_enterprise_query_ll);
        imgBack = (ImageView) findViewById(R.id.activity_enterprise_query_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_enterprise_query_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_enterprise_query_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_enterprise_query_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_enterprise_query_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_enterprise_query_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.activity_enterprise_query_header).findViewById(R.id.title);
        tvTitle.setText("企业多维度查询");
        etSssq = (EditText) findViewById(R.id.activity_enterprise_query_et_sssq);
        gridView = (MyGridView) findViewById(R.id.activity_enterprise_query_gv);
        etInvestmentStart = (EditText) findViewById(R.id.activity_enterprise_query_et_investment_start);
        etInvestmentEnd = (EditText) findViewById(R.id.activity_enterprise_query_et_investment_end);
        etPeopleStart = (EditText) findViewById(R.id.activity_enterprise_query_et_people_start);
        etPeopleEnd = (EditText) findViewById(R.id.activity_enterprise_query_et_people_end);
        etIndustry = (EditText) findViewById(R.id.activity_enterprise_query_et_industry);
        etTurnoverStart = (EditText) findViewById(R.id.activity_enterprise_query_et_turnover_start);
        etTurnoverEnd = (EditText) findViewById(R.id.activity_enterprise_query_et_turnover_end);
        btnSearch = (Button) findViewById(R.id.activity_enterprise_query_btn_search_info);
    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), EnterpriseQueryActivity.this,EnterpriseQueryActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_enterprise_query_btn_search_info:
                    //提交信息，搜索企业，跳转到企业界面
                    for (int i = 0; i < dataPosition.size(); i++) {
                        dataEconomicChecked.add(i,dataEconomic.get(dataPosition.get(i)).getKey());
                    }
                    //根据etSssd的内容，找到对应的sqId
                    String sqId = "";
                    for (int i = 0; i < dataCommunity.size(); i++) {
                        if (etSssq.getText().toString().trim().equals(dataCommunity.get(i).getValue())) {
                            sqId = dataCommunity.get(i).getKey()+"";
                        }
                    }
                    //设置行业类别代码
                    String hylbdm = "";
                    String trim = etIndustry.getText().toString().trim();
                    if ("".equals(trim)||"全部".equals(trim)) {
                        hylbdm = "";
                    }else{
                        for (int i = 0; i < dataIndustry.size(); i++) {
                            if (dataIndustry.get(i).getValue().equals(trim)) {
                                hylbdm = dataIndustry.get(i).getKey();
                                break;
                            }
                        }
                    }
                    String tzeStart = etInvestmentStart.getText().toString().trim();
                    String tzeEnd = etInvestmentEnd.getText().toString().trim();
                    String cyryslStart = etPeopleStart.getText().toString().trim();
                    String cyryslEnd = etPeopleEnd.getText().toString().trim();
                    String yyeStart = etTurnoverStart.getText().toString().trim();
                    String yyeEnd = etTurnoverEnd.getText().toString().trim();
                    getAreaQueryData(sqId,tzeStart,tzeEnd,cyryslStart,cyryslEnd,hylbdm,yyeStart,yyeEnd,dataEconomicChecked);
                    dataEconomicChecked.clear();
                    break;
                case R.id.activity_enterprise_query_et_industry:
                    if (arrayIndustry==null) {
                        Toast.makeText(this, "数据正在加载，请稍候", Toast.LENGTH_SHORT).show();
                    }else{
                        showChooseDialog(arrayIndustry,etIndustry);
                    }
                    break;
                case R.id.activity_enterprise_query_et_sssq:
                    if (arrayCommunity==null) {
                        Toast.makeText(this, "数据正在加载，请稍候", Toast.LENGTH_SHORT).show();
                    }else{
                        showChooseDialog(arrayCommunity,etSssq);
                    }
                    break;
            }
        }
    }
    /**
     *
     *@desc 根据传入的参数获取对应的枚举数据
     *@param params 接口需要的枚举类型
     *@date 2017/12/15 9:12
     */
    private void getEnumData(final String params) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+"/enum/getEnums")
                .addParams("code",params)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(EnterpriseQueryActivity.this,e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        //分情况加载数据
                        if (params.equals("ECONOMIC_TYPE")) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    //解析json数据 这里用不了gson 因为gson要求object开头
                                    JSONObject o = (JSONObject) jsonArray.get(i);
                                    String value = o.getString("value");
                                    String key = o.getString("key");
                                    dataEconomic.add(new EnumModel(key, value));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.e(TAG, "onResponse: "+dataEconomic.size() );
                            areaEnterpriswAdapter = new AreaEnterpriswAdapter(dataEconomic,getApplicationContext(),EnterpriseQueryActivity.this);
                            gridView.setAdapter(areaEnterpriswAdapter);
                            setListViewHeightBasedOnChildren(gridView);
                        }else{
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                //判断jsonArray为空
                                if (jsonArray == null) {
                                    Toast.makeText(EnterpriseQueryActivity.this, "Enum数据为空", Toast.LENGTH_SHORT).show();
                                }else{
                                    arrayIndustry = new String[jsonArray.length()+1];
                                    arrayIndustry[0] = "全部";
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        //解析json数据 这里用不了gson 因为gson要求object开头
                                        JSONObject o = (JSONObject) jsonArray.get(i);
                                        String value = o.getString("value");
                                        String key = o.getString("key");
                                        dataIndustry.add(new EnumModel(key, value));
                                        arrayIndustry[i+1] = value;
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });
    }
    /**
     *
     *@desc 动态设置gridView条目的高度
     *@param listView 需要进行设置的gridView
     *@date 2017/12/15 9:33
     */
    public void setListViewHeightBasedOnChildren(GridView listView) {

        int totalHeight = 0;
        areaEnterpriswAdapter = (AreaEnterpriswAdapter) listView.getAdapter();
        int itemHeight = 0;
        for (int i = 0; i < areaEnterpriswAdapter.getCount()/3+3; i++) {
            View listItem = areaEnterpriswAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
//            itemHeight = listItem.getMeasuredHeight();
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
//                + (listView.getDividerHeight() * (areaEnterpriswAdapter.getCount() - 1));

        listView.setLayoutParams(params);
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
    /**
     *
     *@desc checkbox 的选中状态改变时调用，将选中的条目列表传过来
     *@param dataPosition 列表
     *@date 2017/12/15 9:53
     */
    public void getCheckedPosition(List<Integer> dataPosition){
        this.dataPosition = dataPosition;
        for (int i = 0; i < dataPosition.size(); i++) {
            Log.e(TAG, "getCheckedPosition: "+dataPosition.get(i) );
        }
    }
    /**
     * @desc 从服务器获取社区企业的数据
     * @param sqId
     * @param tzeStart
     * @param tzeEnd
     * @param cyryslStart
     * @param cyryslEnd
     * @param hylbdm
     * @param yyeStart
     * @param yyeEnd
     * @param dataEconomicChecked
     * @date 2017/12/15 10:29
     */
    private void getAreaQueryData(final String sqId, final String tzeStart, final String tzeEnd, final String cyryslStart, final String cyryslEnd, final String hylbdm, final String yyeStart, final String yyeEnd, final List<String> dataEconomicChecked) {
        listToJson = listToJson(dataEconomicChecked);
        Log.e(TAG, "getAreaQueryData: listToJson"+ listToJson);
        Log.e(TAG, "getAreaQueryData: "+dataEconomicChecked.size() );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/find")
                .addParams("size", "10")
                .addParams("sqId",sqId)
                .addParams("tzeStart", tzeStart)
                .addParams("tzeEnd", tzeEnd)
                .addParams("cyryslStart", cyryslStart)
                .addParams("cyryslEnd", cyryslEnd)
                .addParams("hylbdm", hylbdm)
                .addParams("yyeStart", yyeStart)
                .addParams("yyeEnd", yyeEnd)
                .addParams("jjlxdmArr[]", listToJson)
                .build()
                .execute(new StringCallback() {
                    private EnterpriseInfoQueryAdapter enterpriseInfoQueryAdapter;
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(EnterpriseQueryActivity.this,e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        EnterpriseInfoQueryModel enterpriseInfoQueryModel = gson.fromJson(response, EnterpriseInfoQueryModel.class);
                        if (enterpriseInfoQueryModel.isData()) {
                            EnterpriseInfoQueryModel.ListSet listSet = gson.fromJson(enterpriseInfoQueryModel.getMsg(), EnterpriseInfoQueryModel.ListSet.class);
                            list = listSet.getList();
                            if (list!=null) {
                                Intent intent = new Intent();
                                intent.setClass(EnterpriseQueryActivity.this, EnterpriseInfoQueryActivity.class);
                                intent.putExtra("startClass","AreaEnterpriseActivity");
                                intent.putExtra("sqId",sqId);
                                Log.e(TAG, "onClick: etTurnoverStart.getText()"+etTurnoverStart.getText().toString().trim() );
                                intent.putExtra("jjlxdmArr", (Serializable) listToJson);
                                intent.putExtra("tzeStart",tzeStart);
                                intent.putExtra("tzeEnd",tzeEnd);
                                intent.putExtra("cyryslStart",cyryslStart);
                                intent.putExtra("cyryslEnd",cyryslEnd);
                                intent.putExtra("hylbdm",hylbdm);
                                intent.putExtra("yyeStart",yyeStart);
                                intent.putExtra("yyeEnd",yyeEnd);
                                startActivity(intent);
                            }else{
                                Toast.makeText(EnterpriseQueryActivity.this, "没有查到符合条件企业", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(EnterpriseQueryActivity.this, enterpriseInfoQueryModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    /**
     *
     *@desc 将List变为json字符串
     *@param list 待转变的字符串
     *@date 2017/12/15 10:39
     */
    private String listToJson(List<String> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size()-1) {
                str = str + list.get(i) + ",";
            } else {
                str = str + list.get(i);
            }
        }
        return str;
    }
}
