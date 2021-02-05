package com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.fragments.homepagefragments.ComponyBasicInfoFragment;
import com.example.lzc.myspms.fragments.homepagefragments.ComponySafeInfoFragment;
import com.example.lzc.myspms.models.BaseEnterpriseModel;
import com.example.lzc.myspms.models.ComponyEditInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class EditEnterpriseInfoActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private final String TAG = this.getClass().getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private RadioGroup radioGroup;
    private Fragment mShowFragment;
    private String qyId;
    //提交的数据
    private List<ComponyEditInfoModel> allData;
    private List<ComponyEditInfoModel> basicData;
    private List<ComponyEditInfoModel> safeData;

    private Gson gson;
    private ComponyBasicInfoFragment basicInfoFragment;
    private BaseEnterpriseModel basicInfo;
    private ComponySafeInfoFragment safeInfoFragment;
    private BaseEnterpriseModel safeInfo;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_enterprise_info);
        qyId = getIntent().getStringExtra("qyId");
        initView();
        initData();
        initListener();
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.activity_edit_enterprise_info_back);
        imgVideocall = (ImageView) findViewById(R.id.activity_edit_enterprise_info_videocall);
        btnSave = (Button) findViewById(R.id.activity_add_enterprise_btn_save);
        radioGroup = (RadioGroup) findViewById(R.id.activity_edit_enterprise_info_rg);
    }

    private void initData() {
        //显示第一个fragment
        mShowFragment = new ComponyBasicInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("whichActivity", "EditEnterpriseInfoActivity");
        bundle.putString("qyId", qyId);
        mShowFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_edit_enterprise_info_fl, mShowFragment, ComponyBasicInfoFragment.TAG);
        transaction.show(mShowFragment);
        transaction.commit();
        basicInfoFragment = (ComponyBasicInfoFragment) mShowFragment;

    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (safeInfoFragment!=null) {
//            safeInfoFragment.closePopupwindow();
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.activity_edit_enterprise_info_back:
                    this.finish();
                    break;
                case R.id.activity_edit_enterprise_info_videocall:
                    Intent intent = new Intent();
                    intent.setClass(EditEnterpriseInfoActivity.this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
                case R.id.activity_add_enterprise_btn_save:
//                    basicInfo = basicInfoFragment.getBasicInfo();
                    if (basicInfo == null) {
                        return;
                    }
                    if (safeInfoFragment != null) {
//                        safeInfo = safeInfoFragment.getSafeInfo();
                    }
                    //判断填写数据是否合格
                    if (basicInfo == null && safeInfo == null) {//页面什么信息也没有填写
                    } else if (basicInfo != null && safeInfo == null) {
                            //这种情况是只修改了基本信息
                            sendComponyInfoToServer(new StringBuilder(),basicInfo);
                    } else if (basicInfo == null && safeInfo != null) {
                        //这种情况应该是不可能出现的
                        Toast.makeText(this, "您还没有填写企业基本信息，请填写后提交", Toast.LENGTH_SHORT).show();
                    } else {
//                        boolean error2 = safeInfoFragment.isDataAuthorized();
//                        if (!error2) {
//                            Toast.makeText(this, "内容填写有误，请检查", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else {
//                            safeInfoFragment.clearFocous();
//                            safeInfo = safeInfoFragment.getSafeInfo();
//                        }
                        sendComponyInfoToServer(new StringBuilder(),safeInfo);
                    }
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.activity_edit_enterprise_info_rb_basic_info:
                //隐藏Safe页显示Basic页
                switchPages(ComponyBasicInfoFragment.TAG, ComponyBasicInfoFragment.class);
                basicInfoFragment = (ComponyBasicInfoFragment) mShowFragment;
//                basicInfoFragment.getBasicInfo();
                break;
            case R.id.activity_edit_enterprise_info_rb_safe_info:
                //隐藏Basic页显示Safe页
                switchPages(ComponySafeInfoFragment.TAG, ComponySafeInfoFragment.class);
                safeInfoFragment = (ComponySafeInfoFragment) mShowFragment;
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
                bundle.putString("whichActivity", "EditEnterpriseInfoActivity");
                bundle.putString("qyId", qyId);
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
            transaction.add(R.id.activity_edit_enterprise_info_fl, mShowFragment, tag);

        }
        transaction.commit();
    }

    /**
     * @param data 修改后的basic页面的数据
     * @desc 得到修改后的basic页面的数据
     * @date 2017/11/28 17:35
     */
    public void getBasicData(List<ComponyEditInfoModel> data) {
        if (data != null) {
            Toast.makeText(this, "basic不为空", Toast.LENGTH_SHORT).show();
            if (basicData == null) {
                basicData = new ArrayList<>();
            }
            basicData = data;
            for (int i = 0; i < basicData.size(); i++) {
                Log.e(TAG, "getBasicData: title" + basicData.get(i).getTitle() + "  content" + basicData.get(i).getContent() + "  cancommit" + basicData.get(i).isCanCommit());
            }
        } else {
            Toast.makeText(this, "basic为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param data 修改后的safe页面的数据
     * @desc 得到修改后的safe页面的数据
     * @date 2017/11/28 17:35
     */
    public void getSafeData(List<ComponyEditInfoModel> data) {
        if (data != null) {
            Toast.makeText(this, "safe不为空", Toast.LENGTH_SHORT).show();
            if (safeData == null) {
                safeData = new ArrayList<>();
            }
            safeData = data;
            for (int i = 0; i < safeData.size(); i++) {
                Log.e(TAG, "getSafeData: title" + safeData.get(i).getTitle() + "  content" + safeData.get(i).getContent() + "  cancommit" + safeData.get(i).isCanCommit());

            }
        } else {
            Toast.makeText(this, "safe为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param data 需要验证的数据
     * @desc 验证传入的数据是否合法
     * @date 2017/11/29 9:36
     */
    private boolean checkDataIsQualified(List<ComponyEditInfoModel> data) {
        for (int i = 0; i < data.size(); i++) {
            if (!data.get(i).isCanCommit()) {
                Toast.makeText(this, "请检查" + data.get(i).getTitle() + "项", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    /**
     * @param safeInfo 分两种情况 1是未修改安检信息 此时安检信息都在basicInfo里 2是修改了安检信息 安检信息在safeInfo里面
     * @param stringBuilder
     * @desc 发送企业信息到服务器
     * @date 2017/12/1 16:02
     */
    private void sendComponyInfoToServer(StringBuilder stringBuilder,BaseEnterpriseModel safeInfo) {
        gson = new Gson();
        Log.e(TAG, "sendComponyInfoToServer: "+basicInfo );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/save")
                .addParams("id", qyId)
                .addParams("qymc", basicInfo.getQymc())
                .addParams("zch", basicInfo.getZch()+"")
                .addParams("zzjgdm", basicInfo.getZzjgdm() == null ? "" : basicInfo.getZzjgdm() + "")
                .addParams("fddbr", basicInfo.getFddbr() == null ? "" : basicInfo.getFddbr() + "")
                .addParams("lxdh", basicInfo.getLxdh() + "")
                .addParams("dzyx", basicInfo.getDzyx() + "")
                .addParams("zcdz", basicInfo.getZcdz() + "")
                .addParams("jgfl", basicInfo.getJgfl() + "")
                .addParams("yzbm", basicInfo.getYzbm() == null ? "" : basicInfo.getYzbm() + "")
                .addParams("jjlxdm", basicInfo.getJjlxdm() == null ? "" : basicInfo.getJjlxdm() + "")
                .addParams("xzqhdm", basicInfo.getXzqhdm() == null ? "" : basicInfo.getXzqhdm() + "")
                .addParams("hylbdm", basicInfo.getHylbdm() + "")
                .addParams("qylsgx", basicInfo.getQylsgx() == null ? "" : basicInfo.getQylsgx() + "")
                .addParams("jyfw", basicInfo.getJyfw() + "")
                .addParams("qyzt", basicInfo.getQyzt() == null ? "" : basicInfo.getQyzt() + "")
                .addParams("sfzd", basicInfo.getSfzd() == null ? "" : basicInfo.getSfzd() + "")
                .addParams("qywzjd", basicInfo.getQywzjd()+"")
                .addParams("qywzwd", basicInfo.getQywzwd()+"")
                .addParams("sqId", basicInfo.getSqId() == null ? "" : basicInfo.getSqId() + "")
                .addParams("qyfwzb", basicInfo.getQyfwzb()+"")
                .addParams("zyfzr", safeInfo.getZyfzr() + "")
                .addParams("zyfzrgddhhm", safeInfo.getZyfzrgddhhm() + "")
                .addParams("zyfzryddhhm", safeInfo.getZyfzryddhhm() + "")
                .addParams("zyfzrdzyx", safeInfo.getZyfzrdzyx() + "")
                .addParams("aqfzr", safeInfo.getAqfzr() + "")
                .addParams("aqfzryddhhm", safeInfo.getAqfzryddhhm() + "")
                .addParams("aqfzrgddhhm", safeInfo.getAqfzrgddhhm() + "")
                .addParams("aqfzrdzyx", safeInfo.getAqfzrdzyx() + "")
                .addParams("wgfzr", safeInfo.getWgfzr() + "")
                .addParams("wgfzryddhhm", safeInfo.getWgfzryddhhm() + "")
                .addParams("wgfzrgddhhm", safeInfo.getWgfzrgddhhm() + "")
                .addParams("wgfzrdzyx", safeInfo.getWgfzrdzyx() + "")
                .addParams("aqjgszqk", safeInfo.getAqjgszqk() == null ? "" : safeInfo.getAqjgszqk() + "")
                .addParams("cyrysl", safeInfo.getCyrysl() == null ? "" : safeInfo.getCyrysl() + "")
                .addParams("tzzyrysl", safeInfo.getTzzyrysl() == null ? "" : safeInfo.getTzzyrysl() + "")
                .addParams("zzaqscglrysl", safeInfo.getZzaqscglrys() == null ? "" : safeInfo.getZzaqscglrys() + "")
                .addParams("zzyjglrysl", safeInfo.getZzyjglrys() == null ? "" : safeInfo.getZzyjglrys() + "")
                .addParams("zcaqgcsrys", safeInfo.getZcaqgcsrys() == null ? "" : safeInfo.getZcaqgcsrys() + "")
                .addParams("zsrysl", safeInfo.getZsrysl() == null ? "" : safeInfo.getZsrysl() + "")
                .addParams("cyryJson",safeInfo.getCyryJson()+"")
                .addParams("tzzyryJson",safeInfo.getTzzyryJson()+"")
                .addParams("zzaqscryJson",safeInfo.getZzaqscryJson()+"")
                .addParams("zzyjglryJson",safeInfo.getZzyjglryJson()+"")
                .addParams("zcaqgcsJson",safeInfo.getZcaqgcsJson()+"")
                .addParams("zsryJson",safeInfo.getZsryJson()+"")
                .addParams("aqjgjcjg", safeInfo.getAqjgjcjg() == null ? "" : safeInfo.getAqjgjcjg() + "")
                .addParams("scjydz", safeInfo.getScjydz() + "")
                .addParams("gmqk", safeInfo.getGmqk() == null ? "" : safeInfo.getGmqk() + "")
                .addParams("qygm", safeInfo.getQygm() == null ? "" : safeInfo.getQygm() + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(EditEnterpriseInfoActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        LoginInfoModel info = gson.fromJson(response, LoginInfoModel.class);
                        Toast.makeText(EditEnterpriseInfoActivity.this, "" + info.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
