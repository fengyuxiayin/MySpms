package com.example.lzc.myspms.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckProjectActivity;
import com.example.lzc.myspms.adapters.CheckProgressAdapter;
import com.example.lzc.myspms.adapters.SimpleArrayAdapter;
import com.example.lzc.myspms.custom.GradientTextView;
import com.example.lzc.myspms.custom.WaveHelper;
import com.example.lzc.myspms.custom.WaveView;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumCommunityModel;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.FindByTagsModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.NewCheckInfoModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.GpsUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/27.
 */
public class CheckProgressFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    public static final String TAG = CheckProgressFragment.class.getSimpleName();
    private View view;
    //用来显示实际进度条的bitmap
    private Bitmap mBitmap;
    //实际进度
    private double progress = 0;
    //最上方两个spinner+查询按钮
    private Button btnSearch;
    private Spinner spTask;
    private Spinner spCommunity;
    //左侧五个进度条+五个合格率
    private ImageView imgAll;
    private ImageView imgA;
    private ImageView imgB;
    private ImageView imgC;
    private ImageView imgD;
    private TextView tvAll;
    private TextView tvA;
    private TextView tvB;
    private TextView tvC;
    private TextView tvD;
    private WaveView wvAll;
    private GradientTextView gtvAll;
    private WaveView wvA;
    private GradientTextView gtvA;
    private WaveView wvB;
    private GradientTextView gtvB;
    private WaveView wvC;
    private GradientTextView gtvC;
    private WaveView wvD;
    private GradientTextView gtvD;
    private WaveView wvItems;
    private GradientTextView gtvItems;
    private WaveView wvDangerous;
    private GradientTextView gtvDangerous;
    private PullToRefreshListView listView;
    private int page = 1;
    private TextView textView;
    private List<NewCheckInfoModel.NewCheckMsgInfoModel.ListBean> list = new ArrayList<>();
    private Gson gson = new Gson();
    private List<EnumModel> dataTask = new ArrayList<>();
    private List<EnumModel> dataCommunity = new ArrayList<>();
    private List<String> taskList = new ArrayList<>();
    private List<String> communityList = new ArrayList<>();
    private String taskTags = "";
    private String rwmc = "";
    private String communityId = "";
    private Button btnReset;
    private SimpleArrayAdapter<String> adapterTask;
    private SimpleArrayAdapter<String> adapterCommunity;
    private WaveHelper whItems;
    private WaveHelper whDangerous;
    private WaveHelper whAll;
    private WaveHelper whA;
    private WaveHelper whB;
    private WaveHelper whC;
    private WaveHelper whD;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_check_progress, container, false);
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
        listView.setOnItemClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(getContext(), R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    page++;
                    getData();
                }
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                //进度条数据初始化
                getProgressData("");
                getProgressData("A");
                getProgressData("B");
                getProgressData("C");
                getProgressData("D");
                //企业波浪球初始化
                getPercentEnterprise("");
                getPercentEnterprise("A");
                getPercentEnterprise("B");
                getPercentEnterprise("C");
                getPercentEnterprise("D");
                //项目波浪球初始化
                getPercentProject("1");
                getPercentProject("2");
                getData();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spTask.setSelection(adapterTask.getCount());
                spCommunity.setSelection(adapterCommunity.getCount());
                taskTags = "";
                communityId = "";
                page = 1;
                //进度条数据初始化
                getProgressData("");
                getProgressData("A");
                getProgressData("B");
                getProgressData("C");
                getProgressData("D");
                //企业波浪球初始化
                getPercentEnterprise("");
                getPercentEnterprise("A");
                getPercentEnterprise("B");
                getPercentEnterprise("C");
                getPercentEnterprise("D");
                //项目波浪球初始化
                getPercentProject("1");
                getPercentProject("2");
                getData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getData();
        }
    }

    private void getData() {
        // TODO: 2018/6/18   社区登录时社区下拉应该只有当前社区
        Log.e(TAG, "getData: " + "rwmc" + rwmc + "sqId" + communityId);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkInfo/find")
                .addParams("pn", page + "")
                .addParams("size", "10")
                .addParams("jcdwlx", Constant.ACCOUNT_TYPE)
                .addParams("jcdwId", Constant.ENTERPRISE_ID)
                .addParams("rwmc", rwmc)
                .addParams("sqId", communityId)
                .build()
                .execute(new StringCallback() {
                    private CheckProgressAdapter checkProgressAdapter;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        Gson gson = new Gson();
                        NewCheckInfoModel newCheckInfoModel = gson.fromJson(response, NewCheckInfoModel.class);
                        if (newCheckInfoModel.isData()) {
                            NewCheckInfoModel.NewCheckMsgInfoModel newCheckMsgInfoModel = gson.fromJson(newCheckInfoModel.getMsg(), NewCheckInfoModel.NewCheckMsgInfoModel.class);
                            if (newCheckMsgInfoModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                list.clear();
                                // TODO: 2018/5/15 null的问题
                                if (newCheckMsgInfoModel.getList() == null) {
                                    list = new ArrayList<NewCheckInfoModel.NewCheckMsgInfoModel.ListBean>();
                                } else {
                                    list = newCheckMsgInfoModel.getList();
                                }
                            } else {
                                list.addAll(newCheckMsgInfoModel.getList());
                            }
                            if (list.size() == 0) {
                                checkProgressAdapter = new CheckProgressAdapter(list, getContext(), getActivity(), "2");//最后一个参数暂时没有用到
                                listView.setAdapter(checkProgressAdapter);
                                listView.onRefreshComplete();
                            } else {
                                checkProgressAdapter = new CheckProgressAdapter(list, getContext(), getActivity(), "2");
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    checkProgressAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(checkProgressAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        } else {
                            Toast.makeText(getContext(), newCheckInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void initData() {
        textView = new TextView(getContext());
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        textView.setTextColor(getResources().getColor(R.color.homepage_text_press));
        listView.getRefreshableView().addFooterView(textView);
        //设置listview的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
        Log.e(TAG, "initData: " + Constant.ACCOUNT_TYPE);
        getTaskInfo();
        getCommunityInfo();
        //进度条数据初始化
        getProgressData("");
        getProgressData("A");
        getProgressData("B");
        getProgressData("C");
        getProgressData("D");
        //企业波浪球初始化
        getPercentEnterprise("");
        getPercentEnterprise("A");
        getPercentEnterprise("B");
        getPercentEnterprise("C");
        getPercentEnterprise("D");
        //项目波浪球初始化
        getPercentProject("1");
        getPercentProject("2");
        //listview的数据
        getData();
    }

    /**
     * @param jclx 1 普通项 2 重大危险源
     * @desc 获取项目的波浪球数据
     * @date 2018/6/18 20:48
     */
    private void getPercentProject(final String jclx) {
        Log.e(TAG, "getPercentProject: " + taskTags + " " + communityId);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProcess/passPercentForProject")
                .addParams("taskTags", taskTags)
                .addParams("communityId", communityId)
                .addParams("jclx", jclx)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(), e.getMessage() + "/checkProcess/passPercentForProject");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/checkProcess/passPercentForProject2");
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            switch (jclx) {
                                case "1":
                                    whItems = new WaveHelper(wvItems, Float.parseFloat(infoModel.getMsg()) / 100);
                                    setWaveViewData(wvItems, infoModel.getMsg() + "%", gtvItems, whItems);
                                    break;
                                case "2":
                                    whDangerous = new WaveHelper(wvDangerous, Float.parseFloat(infoModel.getMsg()) / 100);
                                    setWaveViewData(wvDangerous, infoModel.getMsg() + "%", gtvDangerous, whDangerous);
                                    break;
                            }
                        }
                    }
                });
    }

    /**
     * @param level 企业级别
     * @desc 获取企业waveview的百分比
     * @date 2018/6/18 20:26
     */
    private void getPercentEnterprise(final String level) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProcess/passPercentForEnterprise")
                .addParams("taskTags", taskTags)
                .addParams("communityId", communityId)
                .addParams("level", level)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(), e.getMessage() + "/checkProcess/passPercentForEnterprise");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/checkProcess/passPercentForEnterprise");
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            switch (level) {
                                case "":
                                    whAll = new WaveHelper(wvAll, Float.parseFloat(infoModel.getMsg()) / 100);
                                    setWaveViewData(wvAll, infoModel.getMsg() + "%", gtvAll, whAll);
                                    break;
                                case "A":
                                    whA = new WaveHelper(wvA, Float.parseFloat(infoModel.getMsg()) / 100);
                                    setWaveViewData(wvA, infoModel.getMsg() + "%", gtvA, whA);
                                    break;
                                case "B":
                                    whB = new WaveHelper(wvB, Float.parseFloat(infoModel.getMsg()) / 100);
                                    setWaveViewData(wvB, infoModel.getMsg() + "%", gtvB, whB);
                                    break;
                                case "C":
                                    whC = new WaveHelper(wvC, Float.parseFloat(infoModel.getMsg()) / 100);
                                    setWaveViewData(wvC, infoModel.getMsg() + "%", gtvC, whC);
                                    break;
                                case "D":
                                    whD = new WaveHelper(wvD, Float.parseFloat(infoModel.getMsg()) / 100);
                                    setWaveViewData(wvD, infoModel.getMsg() + "%", gtvD, whD);
                                    break;
                            }
                        }
                    }
                });
    }

    /**
     * @param level 企业等级
     * @desc 获取进度条的数据
     * @date 2018/6/18 20:27
     */
    private void getProgressData(final String level) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProcess/checkProcessForEnterprise")
                .addParams("taskTags", taskTags)
                .addParams("communityId", communityId)
                .addParams("level", level)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(getContext(), e.getMessage() + e.getCause() + "/checkProcess/checkProcessForEnterprise");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/checkProcess/checkProcessForEnterprise");
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            switch (level) {
                                case "":
                                    progress = Double.parseDouble((infoModel.getMsg()));
                                    setProgressData(tvAll, imgAll, progress);
                                    break;
                                case "A":
                                    progress = Double.parseDouble((infoModel.getMsg()));
                                    setProgressData(tvA, imgA, progress);
                                    break;
                                case "B":
                                    progress = Double.parseDouble((infoModel.getMsg()));
                                    setProgressData(tvB, imgB, progress);
                                    break;
                                case "C":
                                    progress = Double.parseDouble((infoModel.getMsg()));
                                    setProgressData(tvC, imgC, progress);
                                    break;
                                case "D":
                                    progress = Double.parseDouble((infoModel.getMsg()));
                                    setProgressData(tvD, imgD, progress);
                                    break;
                            }
                        }
                    }
                });
    }

    private void getCommunityInfo() {
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/community/findAll")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    //解析json
                    EnumCommunityModel allCommunityModel = gson.fromJson(response, EnumCommunityModel.class);
                    try {
                        JSONArray jsonArray = new JSONArray(allCommunityModel.getMsg());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject o = (JSONObject) jsonArray.get(i);
                            String value = o.getString("sqmc");
                            String key = o.getString("id");
                            dataCommunity.add(new EnumModel(key, value));
                            communityList.add(value);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    communityList.add("请选择社区");
                    adapterCommunity = new SimpleArrayAdapter<>(getContext(), R.layout.textview_only, communityList);
                    adapterCommunity.setDropDownViewResource(R.layout.spinner_item_textview);
                    spCommunity.setAdapter(adapterCommunity);
                    spCommunity.setSelection(adapterCommunity.getCount());
                    spCommunity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position != communityList.size() - 1) {
                                communityId = dataCommunity.get(position).getKey();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }
        });
    }

    /**
     * @desc 获取任务信息
     * @date 2018/6/21 14:30
     */
    private void getTaskInfo() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkTaskSettings/findByTags")
                .addParams("jcdwlx", Constant.ACCOUNT_TYPE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/checkTaskSettings/findByTags");
                        FindByTagsModel findByTagsModel = gson.fromJson(response, FindByTagsModel.class);
                        if (findByTagsModel.isData()) {
                            FindByTagsModel.FindByTagsMsgModel findByTagsMsgModel = gson.fromJson(findByTagsModel.getMsg(), FindByTagsModel.FindByTagsMsgModel.class);
                            List<FindByTagsModel.FindByTagsMsgModel.ListBean> list = findByTagsMsgModel.getList();
                            if (list != null) {
                                for (int i = 0; i < list.size(); i++) {
                                    dataTask.add(new EnumModel(list.get(i).getTags(), list.get(i).getRwmc()));
                                    taskList.add(list.get(i).getRwmc());
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), findByTagsModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        taskList.add("请选择任务名称");
                        adapterTask = new SimpleArrayAdapter<>(getContext(), R.layout.textview_only, taskList);
                        adapterTask.setDropDownViewResource(R.layout.spinner_item_textview);
                        spTask.setAdapter(adapterTask);
                        spTask.setSelection(adapterTask.getCount());
                        spTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Log.e(TAG, "onItemSelected: " + position);
                                if (position != taskList.size() - 1) {
                                    taskTags = dataTask.get(position).getKey();
                                    rwmc = dataTask.get(position).getValue();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                });
    }

    /**
     * @param
     * @desc 给WaveView设置数据
     * @date 2018/6/14 13:54
     */
    private void setWaveViewData(WaveView waveView, String progress, GradientTextView textView,WaveHelper waveHelper) {
        waveView.setBorder(2, getResources().getColor(R.color.homepage_text_purple));
        waveHelper.start();
        textView.setText(progress);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * @param
     * @param textView
     * @desc 进度条数据初始化
     * @date 2018/6/14 10:37
     */
    private void setProgressData(TextView textView, final ImageView imageView, final double progress) {
        textView.setText(progress + "%");
        imageView.post(new Runnable() {
            @Override
            public void run() {
                setImageViewProgress(imageView, progress);
            }
        });
    }

    /**
     * @param imageView
     * @param progress  进度
     * @desc 根据进度裁剪图片并对ImageView进行设置
     * @date 2018/6/14 10:34
     */
    private void setImageViewProgress(ImageView imageView, double progress) {
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        if (progress != 0) {
//            imageView.setAlpha(0f);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.progressbar_fill);
//        if (progress != 0) {
            // 计算缩放比例.
            float scaleWidth = ((float) width) / bitmap.getWidth();
            float scaleHeight = ((float) height) / bitmap.getHeight();
            // 取得想要缩放的matrix参数.
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // TODO: 2018/7/11 bitmap的 getwidth = 0 为什么？
            if (bitmap.getWidth()!=0) {
                mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                mBitmap = cropBitmap(mBitmap, (int) (width * (progress / 100.0)), height);
                imageView.setImageBitmap(mBitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
            }
        } else {
//            imageView.setAlpha(1f);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.progressbar_empty);
//        if (progress != 0) {
            // 计算缩放比例.
            float scaleWidth = ((float) width) / bitmap.getWidth();
            float scaleHeight = ((float) height) / bitmap.getHeight();
            // 取得想要缩放的matrix参数.
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            if (bitmap.getWidth()!=0) {
                mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                mBitmap = cropBitmap(mBitmap, (int) width, height);
                imageView.setImageBitmap(mBitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
            }

        }

//        }else{
//            imageView.setImageResource(R.mipmap.progressbar_empty);
//        }
    }

    /**
     * 裁剪
     *
     * @param bitmap 原图
     * @return 裁剪后的图像
     */
    private Bitmap cropBitmap(Bitmap bitmap, int width, int height) {
        int h = bitmap.getHeight();
        int w = bitmap.getWidth();
        Log.e(TAG, "cropBitmap: " + w + " " + width + " " + h + " " + height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, h, null, false);
    }

    private void initView() {
        //最上方两个spinner+查询按钮
        spTask = (Spinner) view.findViewById(R.id.activity_check_progress_sp_task);
        spCommunity = (Spinner) view.findViewById(R.id.activity_check_progress_sp_community);
        btnSearch = (Button) view.findViewById(R.id.activity_check_progress_btn_search);
        btnReset = (Button) view.findViewById(R.id.activity_check_progress_btn_reset);
        //左侧五个进度条+五个合格率
        imgAll = (ImageView) view.findViewById(R.id.fragment_check_progress_img_all);
        imgA = (ImageView) view.findViewById(R.id.fragment_check_progress_img_a);
        imgB = (ImageView) view.findViewById(R.id.fragment_check_progress_img_b);
        imgC = (ImageView) view.findViewById(R.id.fragment_check_progress_img_c);
        imgD = (ImageView) view.findViewById(R.id.fragment_check_progress_img_d);
        tvAll = (TextView) view.findViewById(R.id.fragment_check_progress_tv_progress_all);
        tvA = (TextView) view.findViewById(R.id.fragment_check_progress_tv_progress_a);
        tvB = (TextView) view.findViewById(R.id.fragment_check_progress_tv_progress_b);
        tvC = (TextView) view.findViewById(R.id.fragment_check_progress_tv_progress_c);
        tvD = (TextView) view.findViewById(R.id.fragment_check_progress_tv_progress_d);
        //波浪球和文字
        wvAll = (WaveView) view.findViewById(R.id.fragment_check_progress_wv_all);
        gtvAll = (GradientTextView) view.findViewById(R.id.fragment_check_progress_gtv_all);
        wvA = (WaveView) view.findViewById(R.id.fragment_check_progress_wv_a);
        gtvA = (GradientTextView) view.findViewById(R.id.fragment_check_progress_gtv_a);
        wvB = (WaveView) view.findViewById(R.id.fragment_check_progress_wv_b);
        gtvB = (GradientTextView) view.findViewById(R.id.fragment_check_progress_gtv_b);
        wvC = (WaveView) view.findViewById(R.id.fragment_check_progress_wv_c);
        gtvC = (GradientTextView) view.findViewById(R.id.fragment_check_progress_gtv_c);
        wvD = (WaveView) view.findViewById(R.id.fragment_check_progress_wv_d);
        gtvD = (GradientTextView) view.findViewById(R.id.fragment_check_progress_gtv_d);
        wvItems = (WaveView) view.findViewById(R.id.fragment_check_progress_wv_items);
        gtvItems = (GradientTextView) view.findViewById(R.id.fragment_check_progress_gtv_items);
        wvDangerous = (WaveView) view.findViewById(R.id.fragment_check_progress_wv_dangerous);
        gtvDangerous = (GradientTextView) view.findViewById(R.id.fragment_check_progress_gtv_dangerous);
        //listView
        listView = (PullToRefreshListView) view.findViewById(R.id.activity_check_progress_pulltorefresh);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final int pos = position - 1;
//        if(pos<list.size()){
//            //判断gps是否打开
//            boolean isOpen = GpsUtil.openGPSSettings(getActivity());
//            if (isOpen) {
        //gps打开了，判断是否已经定位成功
//            Log.e(TAG, "onClick: " + Constant.LOCATION_INFO);
//            if (Constant.LOCATION_INFO.equals("null,null") || Constant.LOCATION_INFO.length() < 1) {
//                //定位未完成
//                Toast.makeText(activity, "GPS正在定位，请稍候", Toast.LENGTH_SHORT).show();
//            } else {
//                    //定位完成 获取经纬度
//                    String jd = Constant.LOCATION_INFO.substring(0, Constant.LOCATION_INFO.lastIndexOf(","));
//                    String wd = Constant.LOCATION_INFO.substring(Constant.LOCATION_INFO.lastIndexOf(",") + 1, Constant.LOCATION_INFO.length());
//                    //向服务器验证当前企业是否在范围内
//                    OkHttpUtils.post()
//                            .url(Constant.SERVER_URL + "/baseEnterprise/isInPolygon")
//                            .addParams("qyId", data.get(position).getQyId() + "")
//                            .addParams("lng", jd)
//                            .addParams("lat", wd)
//                            .build()
//                            .execute(new StringCallback() {
//                                @Override
//                                public void onError(Request request, Exception e) {
//                                    Log.e(TAG, "onError: 111111" + e.getMessage());
//                                    if (("timeout").equals(e.getMessage())) {
//                                        Toast.makeText(activity, "连接超时，请稍后重试", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                @Override
//                                public void onResponse(String response) {
//                                    Log.e(TAG, "onResponse: 企业范围" + response);
//                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                                    if (!infoModel.isData()) {
//                                        Toast.makeText(activity, "当前位置不在企业检查范围内", Toast.LENGTH_SHORT).show();
//                                    } else {
        if (pos < list.size()) {
            Intent intent = new Intent();
            if (list.get(pos).getRwzt()!=1) {
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL + "/checkInfo/beginCheck")
                        .addParams("id", list.get(pos).getId() + "")
                        .addParams("rwId", list.get(pos).getRwId() + "")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                NetUtil.errorTip(getContext(), e.getMessage() + "/checkInfo/beginCheck");
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: " + response + "/checkInfo/beginCheck");
                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                if (infoModel.isData()) {
                                    Intent intent = new Intent();
                                    intent.putExtra("jclx", list.get(pos).getJclx() + "");//1是初查 2是复查
                                    intent.putExtra("rwzt", list.get(pos).getRwzt() + "");//0 是未检查 1已检查 2是检查中
                                    intent.putExtra("jcjg", list.get(pos).getJcjg() + "");//0 是不合格 1 已合格 2 未检查
                                    intent.putExtra("jcId", list.get(pos).getId() + "");
                                    intent.putExtra("qyJson", list.get(pos).getQyJson());
                                    intent.putExtra("rwId", list.get(pos).getRwId() + "");
                                    intent.putExtra("jcsj", DateUtil.long2Date(list.get(pos).getKssj()));
                                    intent.setClass(getContext(), CheckProjectActivity.class);
                                    getActivity().startActivity(intent);
//                                Toast.makeText(activity, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                intent = new Intent();
                intent.putExtra("jclx", list.get(pos).getJclx() + "");//1是初查 2是复查
                intent.putExtra("rwzt", list.get(pos).getRwzt() + "");//0 是未检查 1已检查 2是检查中
                intent.putExtra("jcjg", list.get(pos).getJcjg() + "");//0 是不合格 1 已合格 2 未检查
                intent.putExtra("jcId", list.get(pos).getId() + "");
                intent.putExtra("qyJson", list.get(pos).getQyJson());
                intent.putExtra("rwId", list.get(pos).getRwId() + "");
                intent.putExtra("jcsj", DateUtil.long2Date(list.get(pos).getKssj()));
                intent.setClass(getContext(), CheckProjectActivity.class);
                getActivity().startActivity(intent);
            }


        }
//            }
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: CheckProgressFragment销毁" );
        if (whA!=null) {
            whA.cancel();
        }
        if (whB!=null) {
            whB.cancel();
        }
        if (whC!=null) {
            whC.cancel();
        }
        if (whD!=null) {
            whD.cancel();
        }
        if (whAll!=null) {
            whAll.cancel();
        }
        if (whDangerous!=null) {
            whDangerous.cancel();
        }
        if (whItems!=null) {
            whItems.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        page = 1;
        //进度条数据初始化
        getProgressData("");
        getProgressData("A");
        getProgressData("B");
        getProgressData("C");
        getProgressData("D");
        //企业波浪球初始化
        getPercentEnterprise("");
        getPercentEnterprise("A");
        getPercentEnterprise("B");
        getPercentEnterprise("C");
        getPercentEnterprise("D");
        //项目波浪球初始化
        getPercentProject("1");
        getPercentProject("2");
        getData();
    }
}
