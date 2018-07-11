package com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.fragments.homepagefragments.ComponyBasicInfoFragment;
import com.example.lzc.myspms.fragments.homepagefragments.ComponySafeInfoFragment;

import java.lang.reflect.InvocationTargetException;

public class EnterpriseInfoActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private  final String TAG = this.getClass().getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private RadioGroup radioGroup;    
    private Fragment mShowFragment;
    private String qyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_info);
        qyId = getIntent().getStringExtra("qyId");
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
        //显示第一个fragment
        mShowFragment = new ComponyBasicInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("whichActivity","EnterpriseInfoActivity");
        bundle.putString("qyId",qyId);
        mShowFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_enterprise_info_fl, mShowFragment, ComponyBasicInfoFragment.TAG);
        transaction.show(mShowFragment);
        transaction.commit();
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.activity_enterprise_info_back);
        imgVideocall = (ImageView) findViewById(R.id.activity_enterprise_info_videocall); 
        radioGroup = (RadioGroup) findViewById(R.id.activity_enterprise_info_rg);

    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            switch (v.getId()) {
                case R.id.activity_enterprise_info_back:
                    this.finish();
                    break;
                case R.id.activity_enterprise_info_videocall:
                    Intent intent = new Intent();
                    intent.setClass(this, VideoCallActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.activity_enterprise_info_rb_basic_info:
                //隐藏Safe页显示Basic页
                switchPages(ComponyBasicInfoFragment.TAG, ComponyBasicInfoFragment.class);
                break;
            case R.id.activity_enterprise_info_rb_safe_info:
                //隐藏Basic页显示Safe页
                switchPages(ComponySafeInfoFragment.TAG, ComponySafeInfoFragment.class);
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
                bundle.putString("whichActivity","EnterpriseInfoActivity");
                bundle.putString("qyId",qyId);
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
            transaction.add(R.id.activity_enterprise_info_fl, mShowFragment, tag);

        }
        transaction.commit();
    }
}
