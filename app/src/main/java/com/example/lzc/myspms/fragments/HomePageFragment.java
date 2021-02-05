package com.example.lzc.myspms.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.custom.BarChartView;
import com.example.lzc.myspms.custom.LineChartManager;
import com.example.lzc.myspms.custom.LineChartView;
import com.example.lzc.myspms.custom.WaveHelper;
import com.example.lzc.myspms.custom.WaveView;
import com.example.lzc.myspms.models.CheckEnterpriseResultModel;
import com.example.lzc.myspms.models.CheckProjectResultModel;
import com.example.lzc.myspms.models.CheckQyTimesForZd;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LZC on 2017/10/27.
 */
public class HomePageFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    public static final String TAG = HomePageFragment.class.getSimpleName();
    private View view;
    //第一小块
    private WaveView wvCommunity;
    private TextView tvFzsqs;
    private TextView tvGroupName;
    private TextView tvCommunityNumber;
    private TextView tvCommunityCount;
    private TextView tvCommunityProportion;
    //第五小块
    private WaveView wvCompony;
    private TextView tvFzqys;
    private TextView tvComponyNumber;
    private TextView tvComponyCount;
    private TextView tvComponyProportion;
    //第二小块
    private TextView tvLjjcyhsTitle;
    private TextView tvLjjcqyTimes;
    private TextView tvLjjcqyPos;
    private BarChartView bcLjjcqy;
    //第三小块
    private TextView tvLjjcyhsTimes;
    private TextView tvLjjcyhsPos;
    private BarChartView bcLjjcyhs;
    //第四小块
    private TextView tvLjfcqycsTimes;
    private TextView tvLjfcqycsPos;
    private BarChartView bcLjfcqycs;
    private TextView tvLjfcqycsTitle;
    private TextView tvLjfcqycsName;
    //第六小块
    private BarChartView bcLjjcxmcs;
    private TextView tvLjjcxmcsTimes;
    private TextView tvLjjcxmcsPos;
    private TextView tvLjjcxmcsTitle;
    //第七小块
    private TextView tvLjjcscTimes;
    private TextView tvLjjcscPos;
    private BarChartView bcLjjcsc;
    private TextView tvLjjcscTitle;
    private TextView tvLjjcscName;
    //第九小块
    private LineChartView lineChartCompony;
    //第十小块
    private LineChartView lineChartProject;
    //x轴坐标对应的数据
    private List<String> xValueCompony = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValueCompony = new ArrayList<>();
    //折线对应的数据
    private Map<String, Double> valueCompony = new HashMap<>();
    //x轴坐标对应的数据
    private List<String> xValueProject = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValueProject = new ArrayList<>();
    //折线对应的数据
    private Map<String, Double> valueProject = new HashMap<>();
    private Gson gson = new Gson();
    private TextView tvZdjgqyA;
    private TextView tvZdjgqyD;
    private TextView tvZdjgqyB;
    private TextView tvZdjgqyC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, container, false);
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
    }

    private void initData() {

        tvGroupName.setText(Constant.USER_NAME);
        //通用柱状图数据
        List<BarChartView.BarGraphInfo> list = new ArrayList<>();
        list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press),"本小组数量",100));
        list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple),"平均数量",80));
        //第一小块数据初始化
        //小区波浪球的设置
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/mobileHomePage/fzsqs")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+"/mobileHomePage/fzsqs");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+"/mobileHomePage/fzsqs" );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                int count =  jsonObject.optInt("count",0);
                                tvCommunityNumber.setText("负责社区数："+count+"");
                                int totalCount =  jsonObject.optInt("totalCount",1);
                                tvCommunityCount.setText("社区总数："+totalCount+"");
                                String zb = jsonObject.optString("zb","no data");
                                tvCommunityProportion.setText("所占比例："+zb);
                                tvFzsqs.setText(zb);
                                wvCommunity.setBorder(2, getResources().getColor(R.color.homepage_text_purple));
                                if (totalCount!=0) {
                                    WaveHelper whCommunity = new WaveHelper(wvCommunity, (float)count/(float)totalCount);
                                    whCommunity.start();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });

        //第五小块数据初始化
        //企业波浪球的设置
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/mobileHomePage/findFzQys")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+"/mobileHomePage/findFzQys");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+"/mobileHomePage/findFzQys " );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                int count =  jsonObject.optInt("fzQys",0);
                                tvComponyNumber.setText("负责企业数："+count+"");
                                int totalCount =  jsonObject.optInt("totalQys",1);
                                tvComponyCount.setText("企业总数："+totalCount+"");
                                String zb = jsonObject.optString("zb","no data");
                                tvComponyProportion.setText("所占比例："+zb);
                                tvFzqys.setText(zb);
                                wvCompony.setBorder(2, getResources().getColor(R.color.homepage_text_purple));
                                if (totalCount!=0) {
                                    WaveHelper whCompony = new WaveHelper(wvCompony, (float)count/(float)totalCount);
                                    whCompony.start();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
        //第二小块数据初始化
        setBarChatViewPostData("/mobileHomePage/checkQyTimes",tvLjjcqyTimes,tvLjjcqyPos,bcLjjcqy,"1");
        //第三小块数据初始化
        tvLjjcyhsTitle.setText("累计检出隐患数");
        setBarChatViewData("/mobileHomePage/checkYhTimes",tvLjjcyhsTimes,tvLjjcyhsPos,bcLjjcyhs);
        //第四小块数据初始化
        tvLjfcqycsTitle.setText("累计复查企业次数");
        tvLjfcqycsName.setText("次");
        setBarChatViewPostData("/mobileHomePage/checkQyTimes",tvLjfcqycsTimes,tvLjfcqycsPos,bcLjfcqycs,"2");
        //第六小块数据初始化
        tvLjjcxmcsTitle.setText("累计检查项目次数");
        setBarChatViewData("/mobileHomePage/checkXmTimes",tvLjjcxmcsTimes,tvLjjcxmcsPos,bcLjjcxmcs);
        //第七小块数据初始化
        tvLjjcscTitle.setText("累计检查时长");
        tvLjjcscName.setText("小时");
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/mobileHomePage/checkTotalTimes")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+"/mobileHomePage/checkTotalTimes");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+"/mobileHomePage/checkTotalTimes" );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.getMsg().equals("")||infoModel.getMsg()==null||infoModel.getMsg().equals("null")) {
                            Log.e(TAG, "onResponse: "+"还没有数据" );
                        }else{
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                double totalHours =  jsonObject.optDouble("totalHours",0);
                                double avgHours =  jsonObject.optDouble("avgHours",0);
                                int rowNo = (int) jsonObject.optInt("rowNo",0);
                                tvLjjcscTimes.setText(totalHours+"");
                                tvLjjcscPos.setText(rowNo+"");
                                List<BarChartView.BarGraphInfo> list = new ArrayList<>();
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press),"本小组数量", (float) totalHours));
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple),"平均数量", (float) avgHours));
                                bcLjjcsc.setList(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
        //第八小块数据初始化
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/mobileHomePage/checkQyTimesForZd")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+"/mobileHomePage/checkQyTimesForZd");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+"/mobileHomePage/checkQyTimesForZd" );
                        CheckQyTimesForZd checkQyTimesForZd = gson.fromJson(response, CheckQyTimesForZd.class);
                        if (checkQyTimesForZd.isData()) {
                            if (checkQyTimesForZd.getMsg().equals("")||checkQyTimesForZd.getMsg()==null||checkQyTimesForZd.getMsg().equals("[]")) {
                                Log.e(TAG, "onResponse: "+"还没有数据" );
                            }else{
                                    CheckQyTimesForZd.CheckQyTimesForZdMsg checkQyTimesForZdMsg = gson.fromJson(checkQyTimesForZd.getMsg(), CheckQyTimesForZd.CheckQyTimesForZdMsg.class);
                                    List<CheckQyTimesForZd.CheckQyTimesForZdMsg.ListBean> list = checkQyTimesForZdMsg.getList();
                                    if (list!=null) {
                                        for (int i = 0; i < list.size(); i++) {
                                            if (list.get(i).getZddj().equals("A")) {
                                                tvZdjgqyA.setText(list.get(i).getJccs()+"");
                                            }
                                            if (list.get(i).getZddj().equals("B")) {
                                                tvZdjgqyB.setText(list.get(i).getJccs()+"");
                                            }
                                            if (list.get(i).getZddj().equals("C")) {
                                                tvZdjgqyC.setText(list.get(i).getJccs()+"");
                                            }
                                            if (list.get(i).getZddj().equals("D")) {
                                                tvZdjgqyD.setText(list.get(i).getJccs()+"");
                                            }
                                        }
                                    }
                            }
                        }
                    }
                });
        //第九小块折线图数据初始化
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/mobileHomePage/checkEnterpriseResult")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+"checkEnterpriseResult");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+"/mobileHomePage/checkEnterpriseResult" );
                        CheckEnterpriseResultModel checkEnterpriseResultModel = gson.fromJson(response, CheckEnterpriseResultModel.class);
                        if (checkEnterpriseResultModel.isData()) {
                            CheckEnterpriseResultModel.CheckEnterpriseResultMsgModel checkEnterpriseResultMsgModel = gson.fromJson(checkEnterpriseResultModel.getMsg(), CheckEnterpriseResultModel.CheckEnterpriseResultMsgModel.class);
                            List<CheckEnterpriseResultModel.CheckEnterpriseResultMsgModel.ListBean> list = checkEnterpriseResultMsgModel.getList();
                            if (list!=null) {
                                for (int i = 0; i < list.size(); i++) {
                                    xValueCompony.add(DateUtil.long2Date(list.get(i).getJckssj()));
                                    valueCompony.put(DateUtil.long2Date(list.get(i).getJckssj()), list.get(i).getPassPercent());//60--100
                                }
                                for (int i = 0; i < 5; i++) {
                                    yValueCompony.add(i * 20);
                                }
                                lineChartCompony.setValue(valueCompony,xValueCompony,yValueCompony);
                            }
                        }

                    }
                });
        //第十小块折线图数据初始化
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/mobileHomePage/checkProjectResult ")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+"/mobileHomePage/checkProjectResult ");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+"/mobileHomePage/checkProjectResult" );
                        CheckProjectResultModel checkProjectResultModel = gson.fromJson(response, CheckProjectResultModel.class);
                        if (checkProjectResultModel.isData()) {
                            CheckProjectResultModel.CheckProjectResultMsgModel checkProjectResultMsgModel = gson.fromJson(checkProjectResultModel.getMsg(), CheckProjectResultModel.CheckProjectResultMsgModel.class);
                            List<CheckProjectResultModel.CheckProjectResultMsgModel.ListBean> list = checkProjectResultMsgModel.getList();
                            if (list!=null) {
                                for (int i = 0; i < list.size(); i++) {
                                    xValueProject.add(DateUtil.long2Date(list.get(i).getJckssj()));
                                    valueProject.put(DateUtil.long2Date(list.get(i).getJckssj()),list.get(i).getPassPercent());
                                }
                                for (int i =0 ; i < 5; i++) {
                                    yValueProject.add(i * 20);
                                }
                                lineChartProject.setValue(valueProject,xValueProject,yValueProject);
                            }
                        }
                    }
                });


    }
/**
 *
 *@desc
 *@param jclx 1初查 2 复查
 *@date 2018/6/17 20:50
*/
    private void setBarChatViewPostData(final String url, final TextView tvTimes, final TextView tvPos, final BarChartView barChatView,String jclx) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL+url)
                .addParams("jclx",jclx)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+url);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+url );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.getMsg().equals("")||infoModel.getMsg()==null||infoModel.getMsg().equals("null")) {
                            Log.e(TAG, "onResponse: "+"还没有数据" );
                        }else{
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                int jccs =  jsonObject.optInt("jccs",0);
                                double avgCnt =  jsonObject.optDouble("avgCnt",0);
                                int rowNo = jsonObject.optInt("rowNo",0);
                                tvTimes.setText(jccs+"");
                                tvPos.setText(rowNo+"");
                                List<BarChartView.BarGraphInfo> list = new ArrayList<>();
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press),"本小组数量", (float) jccs));
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple),"平均数量", (float) avgCnt));
                                barChatView.setList(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }

    private void setBarChatViewData(final String url, final TextView tvTimes, final TextView tvPos, final BarChartView barChatView) {
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(),e.getMessage()+url);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response+url );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.getMsg().equals("")||infoModel.getMsg()==null||infoModel.getMsg().equals("null")) {
                            Log.e(TAG, "onResponse: "+"还没有数据" );
                        }else{
                            try {
                                JSONObject jsonObject = new JSONObject(infoModel.getMsg());
                                int totalHours =  jsonObject.optInt("jccs",0);
                                int avgHours =  jsonObject.optInt("avgCnt",0);
                                int rowNo = (int) jsonObject.optInt("rowNo",0);
                                tvTimes.setText(totalHours+"");
                                tvPos.setText(rowNo+"");
                                List<BarChartView.BarGraphInfo> list = new ArrayList<>();
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_press),"本小组数量", (float) totalHours));
                                list.add(new BarChartView(getContext()).new BarGraphInfo(getResources().getColor(R.color.homepage_text_purple),"平均数量", (float) avgHours));
                                barChatView.setList(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
        }
    }

    private void initView() {
        //小组名称
        tvGroupName = (TextView) view.findViewById(R.id.fragment_homepage_tv_group_name);
        //第一小块
        wvCommunity = (WaveView) view.findViewById(R.id.fragment_homepage_wv_fzsqs);
        tvFzsqs = (TextView) view.findViewById(R.id.fragment_homepage_tv_fzsqs);
        tvCommunityNumber = (TextView) view.findViewById(R.id.fragment_homepage_tv_community_number);
        tvCommunityCount = (TextView) view.findViewById(R.id.fragment_homepage_tv_community_count);
        tvCommunityProportion = (TextView) view.findViewById(R.id.fragment_homepage_tv_community_proportion);
        //第二小块
        tvLjjcqyTimes = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcqycs).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjjcqyPos = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcqycs).findViewById(R.id.fragment_homepage_tv_pos);//排名
        bcLjjcqy = (BarChartView) view.findViewById(R.id.fragment_homepage_barchart_ljjcqycs).findViewById(R.id.fragment_homepage_bc);
        //第三小块
        tvLjjcyhsTimes = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcyhs).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjjcyhsTitle = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcyhs).findViewById(R.id.fragment_homepage_tv_title);//标题
        tvLjjcyhsPos = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcyhs).findViewById(R.id.fragment_homepage_tv_pos);//排名
        bcLjjcyhs = (BarChartView) view.findViewById(R.id.fragment_homepage_barchart_ljjcyhs).findViewById(R.id.fragment_homepage_bc);
        //第四小块
        tvLjfcqycsTimes = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjfcqycsTitle = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_tv_title);//标题
        tvLjfcqycsName = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_tv_times_name);//公里
        tvLjfcqycsPos = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_tv_pos);//排名
        bcLjfcqycs = (BarChartView) view.findViewById(R.id.fragment_homepage_barchart_ljfcqycs).findViewById(R.id.fragment_homepage_bc);
        //第五小块
        wvCompony = (WaveView) view.findViewById(R.id.fragment_homepage_wv_fzqys);
        tvFzqys = (TextView) view.findViewById(R.id.fragment_homepage_tv_fzqys);
        tvComponyNumber = (TextView) view.findViewById(R.id.fragment_homepage_tv_compony_number);
        tvComponyCount = (TextView) view.findViewById(R.id.fragment_homepage_tv_compony_count);
        tvComponyProportion = (TextView) view.findViewById(R.id.fragment_homepage_tv_compony_proportion);
        //第六小块
        tvLjjcxmcsTimes = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcxmcs).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjjcxmcsTitle = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcxmcs).findViewById(R.id.fragment_homepage_tv_title);//排名
        tvLjjcxmcsPos = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcxmcs).findViewById(R.id.fragment_homepage_tv_pos);//排名
        bcLjjcxmcs = (BarChartView) view.findViewById(R.id.fragment_homepage_barchart_ljjcxmcs).findViewById(R.id.fragment_homepage_bc);
        //第七小块
        tvLjjcscTimes = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcsc).findViewById(R.id.fragment_homepage_tv_times);//次数
        tvLjjcscTitle = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcsc).findViewById(R.id.fragment_homepage_tv_title);//排名
        tvLjjcscPos = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcsc).findViewById(R.id.fragment_homepage_tv_pos);//排名
        tvLjjcscName = (TextView) view.findViewById(R.id.fragment_homepage_barchart_ljjcsc).findViewById(R.id.fragment_homepage_tv_times_name);//排名
        bcLjjcsc = (BarChartView) view.findViewById(R.id.fragment_homepage_barchart_ljjcsc).findViewById(R.id.fragment_homepage_bc);
        //第八小块
        tvZdjgqyA = (TextView) view.findViewById(R.id.fragment_homepage_tv_zdjgqyljjccs_a);
        tvZdjgqyB = (TextView) view.findViewById(R.id.fragment_homepage_tv_zdjgqyljjccs_b);
        tvZdjgqyC = (TextView) view.findViewById(R.id.fragment_homepage_tv_zdjgqyljjccs_c);
        tvZdjgqyD = (TextView) view.findViewById(R.id.fragment_homepage_tv_zdjgqyljjccs_d);
        //第九小块
        lineChartCompony = (LineChartView) view.findViewById(R.id.fragment_homepage_lineChart_compony);
        //第十小块
        lineChartProject = (LineChartView) view.findViewById(R.id.fragment_homepage_lineChart_project);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
    }
}
