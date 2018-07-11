package com.example.lzc.myspms.activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.custom.BarChartView;
import com.example.lzc.myspms.custom.GradientTextView;
import com.example.lzc.myspms.custom.LineChartView;
import com.example.lzc.myspms.custom.WaveHelper;
import com.example.lzc.myspms.custom.WaveView;
import com.example.lzc.myspms.models.CheckQyHglList;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisCommunityDataActivity extends AppCompatActivity {
    public static final String TAG = AnalysisCommunityDataActivity.class.getSimpleName();
    private TextView tvTaskName;
    private TextView tvCheckPeople;
    private TextView tvDate;
    private TextView tvNumber;
    private TextView tvComponyOne;
    private TextView tvComponyTwo;
    private WaveView waveView;
    private TextView tvWavePercent;
    private TextView tvWaveNumber;
    private TextView tvQualified;
    private TextView tvUnqualified;
    private TextView tvPercent;
    private RadarChart radarChat;
    private PieChart pieChart;
    private BarChartView barAllCommunity;
    private BarChartView barAllCompony;
    private BarChartView barThisCommunity;
    private BarChartView barThisCompony;
    private BarChartView barComponyA;
    private BarChartView barComponyB;
    private BarChartView barComponyC;
    private BarChartView barComponyD;
    private LineChartView lineChart;
    private String jcdwlx;
    private String jcdwId;
    private String tags;
    private String rwmc;
    private String jcry;
    private String jcrq;
    private Gson gson = new Gson();
    //x轴坐标对应的数据
    private List<String> xValueProject = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValueProject = new ArrayList<>();
    //折线对应的数据
    private Map<String, Double> valueProject = new HashMap<>();
    private TextView tvCommunityName;
    private String jcdwmc;
    private ArrayList<PieEntry> entries;
    private ArrayList<RadarEntry> radarEntry;
    private String which;
    private TextView tvTitle;
    private TextView tvTitleOne;
    private TextView tvTitleTwo;
    private TextView tvThisTitle;
    private TextView tvThisTitleOne;
    private TextView tvThisTitleTwo;
    private TextView tvEnterpriseTitle;
    private TextView tvEnterpriseTitleOne;
    private TextView tvEnterpriseTitleTwo;
    private WaveHelper waveHelper;
    private String qyId;
    private LinearLayout llQyzthgl;
    private LinearLayout llEnterprise;
    private LinearLayout llHybhgzb;
    private RelativeLayout rlRight;
    private LinearLayout llPjdb;
    private LinearLayout llBqpjdb;
    private ViewGroup.LayoutParams layoutParams;
    private ViewGroup.LayoutParams params;
    private String sqId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_community_data);
        jcdwlx = getIntent().getStringExtra("jcdwlx");
        jcdwId = getIntent().getStringExtra("jcdwId");
        rwmc = getIntent().getStringExtra("rwmc");
        tags = getIntent().getStringExtra("tags");
        jcry = getIntent().getStringExtra("jcry");
        jcrq = getIntent().getStringExtra("jcrq");
        jcdwmc = getIntent().getStringExtra("jcdwmc");
        which = getIntent().getStringExtra("which");
        qyId = getIntent().getStringExtra("qyId");
        sqId = getIntent().getStringExtra("sqId");
        initView();
        initData();
    }

    private void initData() {
        final DecimalFormat df = new DecimalFormat("#.00");
        tvTaskName.setText(rwmc);
        tvCheckPeople.setText(jcry);
        tvDate.setText(jcrq);
        //根据which 改变平均对比 本期平均对比的文字内容
        switch (which) {
            case "小组":
                tvTitle.setText("小组平均对比");
                tvTitleOne.setText("所有小组平均");
                tvTitleTwo.setText("本小组");
                tvThisTitle.setText("本小组平均对比");
                tvThisTitleOne.setText("本小组平均");
                tvThisTitleTwo.setText("本期");
                tvEnterpriseTitleOne.setText("所有小组平均");
                tvEnterpriseTitleTwo.setText("本小组");
                break;
            case "企业":
                jcdwId = "";
                jcdwlx = "";
                tvTitle.setText("企业平均对比");
                tvTitleOne.setText("所有企业平均");
                tvTitleTwo.setText("本企业");
                tvThisTitle.setText("本企业平均对比");
                tvThisTitleOne.setText("本企业平均");
                tvThisTitleTwo.setText("本期");
                tvEnterpriseTitleOne.setText("所有企业平均");
                tvEnterpriseTitleTwo.setText("本企业");
                //隐藏企业整体合格率和重点监管企业
                llQyzthgl.setVisibility(View.GONE);
                llEnterprise.setVisibility(View.GONE);
                //调整平均对比和本期平均对比的位置
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,llPjdb.getLayoutParams().height);
                params.addRule(RelativeLayout.RIGHT_OF,R.id.activity_analysis_community_data_ll_industry);
                params.setMargins(10,0,0,0);
                llPjdb.setLayoutParams(params);
                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,llPjdb.getLayoutParams().height);
                params1.addRule(RelativeLayout.RIGHT_OF,R.id.activity_analysis_community_data_ll_industry);
                params1.addRule(RelativeLayout.BELOW,R.id.activity_analysis_community_data_ll_community_all);
                params1.setMargins(10,10,0,0);
                llBqpjdb.setLayoutParams(params1);
                //设置雷达图的大小
                ViewGroup.LayoutParams layoutParams = radarChat.getLayoutParams();
                layoutParams.width = 800;
                layoutParams.height = 800;
                radarChat.setLayoutParams(layoutParams);
                //设置行业不合格占比的宽度
                ViewGroup.LayoutParams layoutParams1 = llHybhgzb.getLayoutParams();
                layoutParams1.width = 1200;
                llHybhgzb.setLayoutParams(layoutParams1);
                //隐藏详细信息的企业总数和重点监管企业数量
                tvNumber.setVisibility(View.GONE);
                tvComponyOne.setVisibility(View.GONE);
                tvComponyTwo.setVisibility(View.GONE);
                break;
            case "社区":
                jcdwId = "";
                jcdwlx = "";
                tvTitle.setText("社区平均对比");
                tvTitleOne.setText("所有社区平均");
                tvTitleTwo.setText("本社区");
                tvThisTitle.setText("本社区平均对比");
                tvThisTitleOne.setText("本社区平均");
                tvThisTitleTwo.setText("本期");
                tvEnterpriseTitleOne.setText("所有社区平均");
                tvEnterpriseTitleTwo.setText("本社区");
                break;
        }
        //详细信息
        Log.e(TAG, "initData: jcdwlx"+jcdwlx+"jcdwId"+jcdwId+"tags"+tags+"qyId"+qyId+"sqId"+sqId );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/dataAnalysis/detail")
                .addParams("jcdwlx", jcdwlx)
                .addParams("jcdwId", jcdwId)
                .addParams("tags", tags)
                .addParams("qyId",qyId)
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /dataAnalysis/detail" + response);
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        try {
                            JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                            tvNumber.setText(jsonObject.optInt("totalCount") + "");
                            tvComponyOne.setText("A级 " + jsonObject.optInt("Acount") + " B级 " + jsonObject.optInt("Bcount"));
                            tvComponyTwo.setText("C级 " + jsonObject.optInt("Ccount") + " D级 " + jsonObject.optInt("Dcount"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "onResponse: "+e.getMessage() );
                        }
                    }
                });
        //行业不合格占比
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/dataAnalysis/hyBhgRate")
                .addParams("jcdwlx", jcdwlx)
                .addParams("jcdwId", jcdwId)
                .addParams("tags", tags)
                .addParams("qyId",qyId)
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /dataAnalysis/hyBhgRate" + response);
                        entries = new ArrayList<PieEntry>();
//                        entries.add(new PieEntry((float) (12.8), "冶金行业"));
//                        entries.add(new PieEntry((float) (21.6), "有色行业"));
//                        entries.add(new PieEntry((float) (9.3), "建材行业"));
//                        entries.add(new PieEntry((float) (20.6), "机械行业"));
//                        entries.add(new PieEntry((float) (12.7), "轻工行业"));
//                        entries.add(new PieEntry((float) (14.6), "纺织行业"));
//                        entries.add(new PieEntry((float) (8.4), "烟草行业"));
//                        entries.add(new PieEntry((float) (13.9), "商贸行业"));
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        try {
                            JSONArray jsonArray = new JSONArray(infoModel.getMsg());
                            if (jsonArray!=null) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONArray content = (JSONArray) jsonArray.optJSONArray(i);
                                    if (content==null) {

                                    }else{
                                        JSONArray jsonArray1 = jsonArray.optJSONArray(i);
                                        entries.add(new PieEntry( (Float.valueOf(String.valueOf(jsonArray1.get(1)))), jsonArray1.get(0)+""));
//                                        entries.add(new PieEntry( 20, jsonArray1.get(0)+""));
//                                        entries.add(new PieEntry( 30, jsonArray1.get(0)+""));
//                                        entries.add(new PieEntry( 40, jsonArray1.get(0)+""));
//                                        entries.add(new PieEntry( 10, jsonArray1.get(0)+""));
//                                        entries.add(new PieEntry( 20, jsonArray1.get(0)+""));
//                                        entries.add(new PieEntry( 30, jsonArray1.get(0)+""));
//                                        entries.add(new PieEntry( 40, jsonArray1.get(0)+""));
//                                        entries.add(new PieEntry( 10, jsonArray1.get(0)+""));
                                    }
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setPieData();
                    }
                });
        //社区平均对比
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/dataAnalysis/cntPjdb")
                .addParams("jcdwlx", jcdwlx)
                .addParams("jcdwId", jcdwId)
                .addParams("tags", tags)
                .addParams("qyId",qyId)
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /dataAnalysis/cntPjdb" + response);
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                        List<BarChartView.BarGraphInfo> list = new ArrayList<>();
//                        list.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        list.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        barAllCommunity.setList(list);
//                        List<BarChartView.BarGraphInfo> list1 = new ArrayList<>();
//                        list1.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        list1.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        barAllCompony.setList(list1);
                        try {
                            JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                            double rateZt = jsonObject.optDouble("rateZt");
                            double rateDwzt = jsonObject.optDouble("rateDwzt");
                            List<BarChartView.BarGraphInfo> list = new ArrayList<>();
                            list.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", (float) rateDwzt));
                            list.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", (float) rateZt));
                            barAllCommunity.setList(list);
                            double rateJg = jsonObject.optDouble("rateJg");
                            double rateDwjg = jsonObject.optDouble("rateDwjg");
                            List<BarChartView.BarGraphInfo> list1 = new ArrayList<>();
                            list1.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", (float) rateDwjg));
                            list1.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", (float) rateJg));
                            barAllCompony.setList(list1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        //本社区平均对比
        Log.e(TAG, "initData: "+jcdwlx+" "+jcdwId+" "+tags );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/dataAnalysis/cntBdwPjdb")
                .addParams("jcdwlx", jcdwlx)
                .addParams("jcdwId", jcdwId)
                .addParams("tags", tags)
                .addParams("qyId",qyId)
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /dataAnalysis/cntBdwPjdb" + response);
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                        List<BarChartView.BarGraphInfo> list = new ArrayList<>();
//                        list.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", Float.parseFloat(df.format((float) Math.random()*4))));
//                        list.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        barThisCommunity.setList(list);
//                        List<BarChartView.BarGraphInfo> list1 = new ArrayList<>();
//                        list1.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", Float.parseFloat(df.format((float) Math.random()*4))));
//                        list1.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", Float.parseFloat(df.format((float) Math.random()*5))));
//                        barThisCompony.setList(list1);
                        try {
                            JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                            double rateZt = jsonObject.optDouble("rateZt");
                            double rateDwzt = jsonObject.optDouble("rateDwzt");
                            List<BarChartView.BarGraphInfo> list = new ArrayList<>();
                            list.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", (float) rateDwzt));
                            list.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", (float) rateZt));
                            barThisCommunity.setList(list);
                            double rateJg = jsonObject.optDouble("rateJg");
                            double rateDwjg = jsonObject.optDouble("rateDwjg");
                            List<BarChartView.BarGraphInfo> list1 = new ArrayList<>();
                            list1.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", (float) rateDwjg));
                            list1.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", (float) rateJg));
                            barThisCompony.setList(list1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        //企业总体合格率
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/dataAnalysis/qualifiRate")
                .addParams("jcdwlx", jcdwlx)
                .addParams("jcdwId", jcdwId)
                .addParams("tags", tags)
                .addParams("qyId",qyId)
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /dataAnalysis/qualifiRate" + response);
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        try {
                            JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                            tvQualified.setText("合格数：" + jsonObject.optInt("HgCount"));
                            tvUnqualified.setText("不合格数：" + jsonObject.optInt("BhgCount"));
                            tvWaveNumber.setText("企业总数：" + jsonObject.optInt("totalCount"));
                            tvWavePercent.setText(jsonObject.optString("zb"));
                            tvPercent.setText("合格率：" + jsonObject.optString("zb"));
                            String zb = jsonObject.optString("zb");
                            if (zb!=null) {
                                if (zb.length()>0) {
                                    String substring = zb.substring(0, zb.length() - 1);
                                    setWaveViewData(waveView, Float.valueOf(substring));
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        //检查类别合格率
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/dataAnalysis/checkLbHgl")
                .addParams("jcdwlx", jcdwlx)
                .addParams("jcdwId", jcdwId)
                .addParams("tags", tags)
                .addParams("qyId",qyId)
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /dataAnalysis/checkLbHgl" + response);
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        radarEntry = new ArrayList<RadarEntry>();
                        try {
                            JSONArray jsonArray = new JSONArray(infoModel.getMsg());
                            if (jsonArray!=null) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONArray content = (JSONArray) jsonArray.optJSONArray(i);
                                    if (content==null) {

                                    }else{
                                        JSONArray jsonArray1 = jsonArray.optJSONArray(i);
                                        if (i==1) {
                                            for (int j = 0; j < jsonArray1.length(); j++) {
                                                radarEntry.add(new RadarEntry( Float.valueOf(String.valueOf(jsonArray1.get(j)))));
//                                                radarEntry.add(new RadarEntry((float) 100.0));
                                            }
                                        }
                                    }
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setRadarData();
                    }
                });
        //重点监管企业合格率
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/dataAnalysis/zdjgQyHgl")
                .addParams("jcdwlx", jcdwlx)
                .addParams("jcdwId", jcdwId)
                .addParams("tags", tags)
                .addParams("qyId",qyId)
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /dataAnalysis/zdjgQyHgl" + response);
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                        List<BarChartView.BarGraphInfo> listA = new ArrayList<>();
//                        listA.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        listA.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        barComponyA.setList(listA);
//                        List<BarChartView.BarGraphInfo> listB = new ArrayList<>();
//                        listB.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        listB.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        barComponyB.setList(listB);
//                        List<BarChartView.BarGraphInfo> listC = new ArrayList<>();
//                        listC.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        listC.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        barComponyC.setList(listC);
//                        List<BarChartView.BarGraphInfo> listD = new ArrayList<>();
//                        listD.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        listD.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", Float.parseFloat(df.format((float) Math.random()*3))));
//                        barComponyD.setList(listD);
                        try {
                            JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                            double ztHglA = jsonObject.optDouble("ztHglA");
                            double bdwHglA = jsonObject.optDouble("bdwHglA");
                            List<BarChartView.BarGraphInfo> listA = new ArrayList<>();
                            listA.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", (float) bdwHglA));
                            listA.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", (float) ztHglA));
                            barComponyA.setList(listA);
                            double ztHglB = jsonObject.optDouble("ztHglB");
                            double bdwHglB = jsonObject.optDouble("bdwHglB");
                            List<BarChartView.BarGraphInfo> listB = new ArrayList<>();
                            listB.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", (float) bdwHglB));
                            listB.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", (float) ztHglB));
                            barComponyB.setList(listB);
                            double ztHglC = jsonObject.optDouble("ztHglC");
                            double bdwHglC = jsonObject.optDouble("bdwHglC");
                            List<BarChartView.BarGraphInfo> listC = new ArrayList<>();
                            listC.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", (float) bdwHglC));
                            listC.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", (float) ztHglC));
                            barComponyC.setList(listC);
                            double ztHglD = jsonObject.optDouble("ztHglD");
                            double bdwHglD = jsonObject.optDouble("bdwHglD");
                            List<BarChartView.BarGraphInfo> listD = new ArrayList<>();
                            listD.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press), "", (float) bdwHglD));
                            listD.add(new BarChartView(AnalysisCommunityDataActivity.this).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple), "", (float) ztHglD));
                            barComponyD.setList(listD);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        //企业检查合格率变化
        Log.e(TAG, "initData: "+"jcdwlx"+jcdwlx+"jcdwId"+jcdwId+"tags"+tags );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/dataAnalysis/checkQyHglList")
                .addParams("jcdwlx", jcdwlx)
                .addParams("jcdwId", jcdwId)
                .addParams("tags", tags)
                .addParams("qyId",qyId)
                .addParams("sqId",sqId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage()+"/dataAnalysis/checkQyHglList" );
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /dataAnalysis/checkQyHglList" + response);
                        CheckQyHglList checkQyHglList = gson.fromJson(response, CheckQyHglList.class);
                        if (checkQyHglList.isData()) {
                            CheckQyHglList.CheckQyHglListMsg checkQyHglListMsg = gson.fromJson(checkQyHglList.getMsg(), CheckQyHglList.CheckQyHglListMsg.class);
                            if (checkQyHglListMsg!=null) {
                                List<CheckQyHglList.CheckQyHglListMsg.ListBean> list = checkQyHglListMsg.getList();
                                if (list != null) {
                                    for (int i = 0; i < list.size(); i++) {
                                        xValueProject.add(DateUtil.long2Date(list.get(i).getKssj()));
                                        valueProject.put(DateUtil.long2Date(list.get(i).getKssj()), (double) list.get(i).getPassPercent()*100);
                                    }
                                    for (int i = 0; i < 6; i++) {
                                        yValueProject.add(i * 20);
                                    }
                                    lineChart.setValue(valueProject, xValueProject, yValueProject);
                            }

                            }else{

//                                Toast.makeText(AnalysisCommunityDataActivity.this, "CheckQyHglList.CheckQyHglListMsg为空", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void setPieData() {
        //饼状图
        //模拟数据
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setWordWrapEnabled(true);
        l.setTextColor(getResources().getColor(R.color.homepage_text_press));
        ArrayList<Integer> colors = new ArrayList<Integer>();
        //模拟颜色
        colors.add(getResources().getColor(R.color.industry_one));
        colors.add(getResources().getColor(R.color.industry_two));
        colors.add(getResources().getColor(R.color.industry_three));
        colors.add(getResources().getColor(R.color.industry_four));
        colors.add(getResources().getColor(R.color.industry_five));
        colors.add(getResources().getColor(R.color.industry_six));
        colors.add(getResources().getColor(R.color.industry_seven));
        colors.add(getResources().getColor(R.color.industry_eight));
        //设置数据
        PieDataSet dataSet = new PieDataSet(entries, "");
        //设置颜色
        dataSet.setColors(colors);
        dataSet.setValueTextColor(getResources().getColor(R.color.homepage_text_press));
        //设置块与块之间的距离
        dataSet.setSliceSpace(5f);
        //设置点击突出的距离
        dataSet.setSelectionShift(10f);

        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        //是否绘制中间的圆
        Log.e(TAG, "setPieData: " );
        pieChart.setDrawHoleEnabled(false);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    /**
     * @param
     * @desc 给WaveView设置数据
     * @date 2018/6/14 13:54
     */
    private void setWaveViewData(WaveView waveView, float number) {
        waveView.setBorder(2, getResources().getColor(R.color.homepage_text_purple));
        waveHelper = new WaveHelper(waveView, number/100);
        waveHelper.start();
//        textView.setText(progress);
    }

    private void setRadarData() {
        XAxis xAxis = radarChat.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"设备类", "特种设备类", "资料类", "现场类", "消防类"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);
        YAxis yAxis = radarChat.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);
        radarChat.getXAxis().setEnabled(true);
        radarChat.getYAxis().setEnabled(true);
        RadarDataSet set1 = new RadarDataSet(radarEntry, "Last Week");
        set1.setColor(getResources().getColor(R.color.white));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setValueTextColor(getResources().getColor(R.color.homepage_text_press));
        set1.setDrawValues(true);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        radarChat.getDescription().setEnabled(false);
        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setValueTextColor(getResources().getColor(R.color.white));
        data.setDrawValues(true);
        data.setValueTextColor(Color.WHITE);

        radarChat.setData(data);
        radarChat.invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (waveHelper!=null) {
            waveHelper.cancel();
        }
    }

    private void initView() {
        tvCommunityName = (TextView) findViewById(R.id.activity_analysis_community_data_tv_community);
        tvCommunityName.setText(jcdwmc);
        //详细信息
        tvTaskName = (TextView) findViewById(R.id.activity_analysis_community_data_tv_task);
        tvCheckPeople = (TextView) findViewById(R.id.activity_analysis_community_data_tv_check);
        tvDate = (TextView) findViewById(R.id.activity_analysis_community_data_tv_date);
        tvNumber = (TextView) findViewById(R.id.activity_analysis_community_data_tv_number);
        tvComponyOne = (TextView) findViewById(R.id.activity_analysis_community_data_tv_compony_one);
        tvComponyTwo = (TextView) findViewById(R.id.activity_analysis_community_data_tv_compony_two);
        //企业总体合格率
        llQyzthgl = (LinearLayout) findViewById(R.id.activity_analysis_community_ll_qyzthgl);
        waveView = (WaveView) findViewById(R.id.activity_analysis_community_data_tv_wv);
        tvWavePercent = (TextView) findViewById(R.id.activity_analysis_community_data_tv_wv_precent);
        tvWaveNumber = (TextView) findViewById(R.id.activity_analysis_community_data_tv_wave_number);
        tvQualified = (TextView) findViewById(R.id.activity_analysis_community_data_tv_qualified);
        tvUnqualified = (TextView) findViewById(R.id.activity_analysis_community_data_tv_unqualified);
        tvPercent = (TextView) findViewById(R.id.activity_analysis_community_data_tv_percent);
        //检查类别合格率雷达图
        radarChat = (RadarChart) findViewById(R.id.activity_analysis_community_data_radar);
        //右面除了合格率变化之外的relativelayout
        rlRight = (RelativeLayout) findViewById(R.id.activity_analysis_community_data_rl_right);
        //行业不合格占比
        llHybhgzb = (LinearLayout) findViewById(R.id.activity_analysis_community_data_ll_industry);
        pieChart = (PieChart) findViewById(R.id.activity_analysis_community_data_pie);
        //社区平均对比
        llPjdb = (LinearLayout) findViewById(R.id.activity_analysis_community_data_ll_community_all);
        barAllCommunity = (BarChartView) findViewById(R.id.activity_analysis_community_data_bar_all);
        barAllCompony = (BarChartView) findViewById(R.id.activity_analysis_community_data_bar_compony);
        tvTitle = (TextView) findViewById(R.id.activity_analysis_community_data_all_title);
        tvTitleOne = (TextView) findViewById(R.id.activity_analysis_community_data_all_title_one);
        tvTitleTwo = (TextView) findViewById(R.id.activity_analysis_community_data_all_title_two);
        //本社区平均对比
        llBqpjdb = (LinearLayout) findViewById(R.id.activity_analysis_community_data_ll_community_this);
        barThisCommunity = (BarChartView) findViewById(R.id.activity_analysis_community_data_bar_this_all);
        barThisCompony = (BarChartView) findViewById(R.id.activity_analysis_community_data_bar_this_compony);
        tvThisTitle = (TextView) findViewById(R.id.activity_analysis_community_data_this_title);
        tvThisTitleOne = (TextView) findViewById(R.id.activity_analysis_community_data_this_title_one);
        tvThisTitleTwo = (TextView) findViewById(R.id.activity_analysis_community_data_this_title_two);
        //重点监管企业合格率
        llEnterprise = (LinearLayout) findViewById(R.id.activity_analysis_community_data_ll_enterprise);
        barComponyA = (BarChartView) findViewById(R.id.activity_analysis_community_data_bar_a);
        barComponyB = (BarChartView) findViewById(R.id.activity_analysis_community_data_bar_b);
        barComponyC = (BarChartView) findViewById(R.id.activity_analysis_community_data_bar_c);
        barComponyD = (BarChartView) findViewById(R.id.activity_analysis_community_data_bar_d);
        tvEnterpriseTitle = (TextView) findViewById(R.id.activity_analysis_community_data_enterprise_title);
        tvEnterpriseTitleOne = (TextView) findViewById(R.id.activity_analysis_community_data_enterprise_title_one);
        tvEnterpriseTitleTwo = (TextView) findViewById(R.id.activity_analysis_community_data_enterprise_title_two);
        //企业检查合格率变化图
        lineChart = (LineChartView) findViewById(R.id.activity_analysis_community_data_line);
    }
}
