package com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.ProjectBasicFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.ProjectSafeFragment;
import com.example.lzc.myspms.models.ComponyAllInfo;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.ProjectBasicInfoModel;
import com.example.lzc.myspms.models.ProjectSafeInfo;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.UnsafeUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AddProjectActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = AddProjectActivity.class.getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private TextView tvTitle;
    private RadioGroup radioGroup;
    //显示的fragment
    private Fragment mShowFragment;
    //企业id 企业名称 项目类型(在新建检查添加项目时需要)
    private String qyId;
    private String qymc;
    //项目basic页面信息
    private List<ComponyAllInfo> basicData;
    //项目safe页面信息
    private List<ProjectSafeInfo> safeData;
    private String startClass;
    private String xmId;
    //最上面的标题
    private String title;
    private ProjectBasicFragment basicInfoFragment;
    private ProjectBasicInfoModel basicInfo;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        startClass = getIntent().getStringExtra("startClass");
        if (startClass.equals("CheckItemSearchResultActivity")) {
            xmId = getIntent().getStringExtra("xmId");
            title = "修改项目";
        } else {
            title = "添加项目";
        }
        qyId = getIntent().getStringExtra("qyId");
        qymc = getIntent().getStringExtra("qymc");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initData() {
        gson = new Gson();
        mShowFragment = new ProjectBasicFragment();
        Bundle bundle = new Bundle();
        bundle.putString("qymc", qymc);
        bundle.putString("xmId", xmId);
        bundle.putString("startClass", startClass);
        mShowFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_add_project_fl, mShowFragment, ProjectBasicFragment.TAG);
        transaction.show(mShowFragment);
        transaction.commit();
        basicInfoFragment = (ProjectBasicFragment) mShowFragment;
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.activity_add_project_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_add_project_header).findViewById(R.id.videocall);
        tvTitle = (TextView) findViewById(R.id.activity_add_project_header).findViewById(R.id.title);
        tvTitle.setText(title);
        radioGroup = (RadioGroup) findViewById(R.id.activity_add_project_rg);
//        basicInfoFragment.clearFocous();
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
                    intent.setClass(AddProjectActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
//                case R.id.save:
//                    basicInfo = basicInfoFragment.getBasicInfo();
//                    Log.e(TAG, "initData: " + gson.toJson(basicInfo));
//                    //上传项目信息
//                    if (basicInfo == null) {
//                        Toast.makeText(AddProjectActivity.this, "请先检查数据是否合法", Toast.LENGTH_SHORT).show();
//                    } else {
//                        if (safeData == null) {//没有检查点信息
//                            Log.e(TAG, "onClick: "+"qyId "+qyId+"xmmc "+ basicInfo.getXmmc()+"xmlx "+ basicInfo.getXmlx()+"ejys "+basicInfo.getEjys()+"says "+ basicInfo.getSays()+
//                                    "sjys "+ basicInfo.getSjys()+"xmbm "+ basicInfo.getXmbm()+"ssbm "+ basicInfo.getSsbm()+"sydd "+ basicInfo.getSydd()+"sbpp "+ basicInfo.getSbpp()
//                            +"sbxh "+ basicInfo.getSbxh()+"eddy "+ basicInfo.getEddy()+"glxh "+ basicInfo.getGlxh()+"sbjz "+ basicInfo.getSbjz()+"scrq "+ basicInfo.getScrq()
//                            +"qyrq "+ basicInfo.getQyrq()+"bxrq "+ basicInfo.getBxrq()+"jdrq "+ basicInfo.getJdrq()+"unsafe "+ UnsafeUtils.setPointDataToJson(safeData));
//                            OkHttpUtils.post()
//                                    .url(Constant.SERVER_URL + "/checkProject/saveCK")
//                                    .addParams("qyId", qyId + "")
//                                    .addParams("xmmc", basicInfo.getXmmc())
//                                    .addParams("xmlx", basicInfo.getXmlx() + "")
//                                    .addParams("ejys", basicInfo.getEjys() == null ? "" : basicInfo.getEjys() + "")
//                                    .addParams("says", basicInfo.getSays() == null ? "" : basicInfo.getSays() + "")
//                                    .addParams("sjys", basicInfo.getSjys() == null ? "" : basicInfo.getSjys() + "")
//                                    .addParams("xmbm", basicInfo.getXmbm() + "")
//                                    .addParams("ssbm", basicInfo.getSsbm() + "")
//                                    .addParams("sydd", basicInfo.getSydd() + "")
//                                    .addParams("sbpp", basicInfo.getSbpp() + "")
//                                    .addParams("sbxh", basicInfo.getSbxh() + "")
//                                    .addParams("eddy", basicInfo.getEddy() + "")
//                                    .addParams("glxh", basicInfo.getGlxh() + "")
//                                    .addParams("sbjz", basicInfo.getSbjz() + "")
//                                    .addParams("scrq", basicInfo.getScrq() + "")
//                                    .addParams("qyrq", basicInfo.getQyrq() + "")
//                                    .addParams("bxrq", basicInfo.getBxrq() + "")
//                                    .addParams("jdrq", basicInfo.getJdrq() + "")
//                                    .addParams("unsafe", UnsafeUtils.setPointDataToJson(safeData))
//                                    .build()
//                                    .execute(new StringCallback() {
//                                        @Override
//                                        public void onError(Request request, Exception e) {
//                                            Log.e(TAG, "onError: 添加" + e.getMessage());
//                                            NetUtil.errorTip(AddProjectActivity.this,e.getMessage());
//                                        }
//
//                                        @Override
//                                        public void onResponse(String response) {
//                                            Log.e(TAG, "onResponse: 添加" + response);
//                                            Gson gson = new Gson();
//                                            LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                                            Toast.makeText(AddProjectActivity.this, "" + infoModel.getMsg(), Toast.LENGTH_SHORT).show();
////                                            if (infoModel.isData()&&"操作成功".equals(infoModel.getMsg())) {
////                                                finish();
////                                            }
//                                        }
//                                    });
//                        } else {
//                            Log.e(TAG, "onClick: " + UnsafeUtils.setPointDataToJson(safeData)+xmId+qyId);
//                            if (startClass.equals("CheckItemSearchResultActivity")) {//需要审核
//                                OkHttpUtils.post()
//                                        .url(Constant.SERVER_URL + "/project/save")
//                                        .addParams("id", xmId)
//                                        .addParams("qyId", qyId)
//                                        .addParams("xmmc", basicInfo.getXmmc())
//                                        .addParams("xmlx", basicInfo.getXmlx() + "")
//                                        .addParams("ejys", basicInfo.getEjys() == null ? "" : basicInfo.getEjys() + "")
//                                        .addParams("says", basicInfo.getSays() == null ? "" : basicInfo.getSays() + "")
//                                        .addParams("sjys", basicInfo.getSjys() == null ? "" : basicInfo.getSjys() + "")
//                                        .addParams("xmbm", basicInfo.getXmbm() + "")
//                                        .addParams("ssbm", basicInfo.getSsbm() + "")
//                                        .addParams("sydd", basicInfo.getSydd() + "")
//                                        .addParams("sbpp", basicInfo.getSbpp() + "")
//                                        .addParams("sbxh", basicInfo.getSbxh() + "")
//                                        .addParams("eddy", basicInfo.getEddy() + "")
//                                        .addParams("glxh", basicInfo.getGlxh() + "")
//                                        .addParams("sbjz", basicInfo.getSbjz() + "")
//                                        .addParams("scrq", basicInfo.getScrq() + "")
//                                        .addParams("qyrq", basicInfo.getQyrq() + "")
//                                        .addParams("bxrq", basicInfo.getBxrq() + "")
//                                        .addParams("jdrq", basicInfo.getJdrq() + "")
//                                        .addParams("sbtp", "mobile")
//                                        .addParams("result", "mobile")
//                                        .addParams("countermeasure", "mobile")
//                                        .addParams("unsafe", UnsafeUtils.setPointDataToJson(safeData))
//                                        .build()
//                                        .execute(new StringCallback() {
//                                            @Override
//                                            public void onError(Request request, Exception e) {
//                                                Log.e(TAG, "onError: " + e.getMessage());
//                                                NetUtil.errorTip(AddProjectActivity.this,e.getMessage());
//                                            }
//
//                                            @Override
//                                            public void onResponse(String response) {
//                                                Log.e(TAG, "onResponse: 修改" + response);
//                                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                                                Toast.makeText(AddProjectActivity.this, "" + infoModel.getMsg(), Toast.LENGTH_SHORT).show();
////                                            data=null;
////                                            pointData = null;
//                                            }
//                                        });
//                            } else {//添加项目不需要审核
//                                OkHttpUtils.post()
//                                        .url(Constant.SERVER_URL + "/checkProject/saveCK")
//                                        .addParams("qyId", qyId + "")
//                                        .addParams("xmmc", basicInfo.getXmmc())
//                                        .addParams("xmlx", basicInfo.getXmlx() + "")
//                                        .addParams("ejys", basicInfo.getEjys() == null ? "" : basicInfo.getEjys() + "")
//                                        .addParams("says", basicInfo.getSays() == null ? "" : basicInfo.getSays() + "")
//                                        .addParams("sjys", basicInfo.getSjys() == null ? "" : basicInfo.getSjys() + "")
//                                        .addParams("xmbm", basicInfo.getXmbm() + "")
//                                        .addParams("ssbm", basicInfo.getSsbm() + "")
//                                        .addParams("sydd", basicInfo.getSydd() + "")
//                                        .addParams("sbpp", basicInfo.getSbpp() + "")
//                                        .addParams("sbxh", basicInfo.getSbxh() + "")
//                                        .addParams("eddy", basicInfo.getEddy() + "")
//                                        .addParams("glxh", basicInfo.getGlxh() + "")
//                                        .addParams("sbjz", basicInfo.getSbjz() + "")
//                                        .addParams("scrq", basicInfo.getScrq() + "")
//                                        .addParams("qyrq", basicInfo.getQyrq() + "")
//                                        .addParams("bxrq", basicInfo.getBxrq() + "")
//                                        .addParams("jdrq", basicInfo.getJdrq() + "")
//                                        .addParams("unsafe", UnsafeUtils.setPointDataToJson(safeData))
//                                        .build()
//                                        .execute(new StringCallback() {
//                                            @Override
//                                            public void onError(Request request, Exception e) {
//                                                Log.e(TAG, "onError: 添加" + e.getMessage());
//                                                NetUtil.errorTip(AddProjectActivity.this,e.getMessage());
//                                            }
//
//                                            @Override
//                                            public void onResponse(String response) {
//                                                Log.e(TAG, "onResponse: 添加" + response);
//                                                Gson gson = new Gson();
//                                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                                                Toast.makeText(AddProjectActivity.this, "" + infoModel.getMsg(), Toast.LENGTH_SHORT).show();
//                                                if (infoModel.isData()) {
//                                                    finish();
//                                                }
//                                            }
//                                        });
//                            }
//                        }
//
//                    }
//
//                    break;
            }
        }
    }

    /**
     * @param
     * @desc 判断数据是否合法（必填项是否已经填写）
     * @date 2017/12/12 14:23
     */
    private boolean judgeDataCanCommit(List<ComponyAllInfo> data) {
        boolean canCommit = true;
        for (int i = 0; i < 7; i++) {
            canCommit = data.get(i).isCanCommit();
            if (!canCommit) {
                Toast.makeText(AddProjectActivity.this, data.get(i).getTitle() + "项不能为空", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return canCommit;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.activity_add_project_rb_basic_info:
                switchPages(ProjectBasicFragment.TAG, ProjectBasicFragment.class);
                basicInfo = basicInfoFragment.getBasicInfo();
                Log.e(TAG, "initData: " + gson.toJson(basicInfo));
                break;
            case R.id.activity_add_project_rb_should_check_point:
                switchPages(ProjectSafeFragment.TAG, ProjectSafeFragment.class);
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
                bundle.putString("xmId", xmId);
                bundle.putString("qymc", qymc);
                bundle.putString("startClass", startClass);
                mShowFragment.setArguments(bundle);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.activity_add_project_fl, mShowFragment, tag);
        }
        transaction.commit();
    }

    /**
     * @param changeData basic页面的数据
     * @desc 获取从ProjectBasicFragment传过来的basic页面的数据
     * @date 2017/12/11 16:35
     */
    public void getBasicData(List<ComponyAllInfo> changeData) {
        basicData = changeData;
        for (int i = 0; i < basicData.size(); i++) {
            Log.e(TAG, "getBasicData: " + basicData.get(i).getTitle() + " content:" + basicData.get(i).getContent());
        }
    }

    /**
     * @param changeData safe页面的数据
     * @desc 获取从ProjectafeFragment传过来的basic页面的数据
     * @date 2017/12/11 16:35
     */
    public void getSafeData(List<ProjectSafeInfo> changeData) {
        safeData = changeData;
        for (int i = 0; i < safeData.size(); i++) {
            Log.e(TAG, "getSafeData: " + safeData.get(i).getName() + " img ");
        }
    }

    /**
     * @param
     * @desc 给ProjectSafeFragment页面用的，用来获取basic页面已经设置好的安全要素
     * @date 2017/12/12 14:18
     */
    public ProjectBasicInfoModel getBasicInfo() {
        basicInfo = basicInfoFragment.getBasicInfo();
        return basicInfo;
    }
}
