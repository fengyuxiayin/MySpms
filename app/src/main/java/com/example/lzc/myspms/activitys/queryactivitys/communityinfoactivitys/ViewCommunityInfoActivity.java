package com.example.lzc.myspms.activitys.queryactivitys.communityinfoactivitys;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.fragments.queryfragments.communityinfofragments.CommunityBasicInfoFragment;
import com.example.lzc.myspms.fragments.queryfragments.communityinfofragments.CommunityCheckRecordFragment;
import com.example.lzc.myspms.fragments.queryfragments.communityinfofragments.CommunityStaffInfoFragment;
import com.example.lzc.myspms.utils.SetMenuClick;

import java.lang.reflect.InvocationTargetException;

public class ViewCommunityInfoActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public static final String TAG = ViewCommunityInfoActivity.class.getSimpleName();
    //根据社区id去查社区基本信息和社区人员信息
    private String sqId;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private TextView tvTitle;
    private RadioButton rbBasicInfo;
    private RadioGroup radioGroup;
    //当前显示的fragment
    private Fragment mShowFragment;
    private RadioButton rbStaffInfo;
    private RadioButton rbCheckRecord;
    private boolean isView;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_community_info);
        sqId = getIntent().getStringExtra("sqId");
        isView = getIntent().getBooleanExtra("isView",true);
        Log.e(TAG, "onCreate: "+isView );
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShowFragment.onActivityResult(requestCode, resultCode, data);
    }

    private void initData() {
        //显示第一个fragment
        mShowFragment = new CommunityBasicInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sqId",sqId);
        bundle.putBoolean("isView",isView);
        mShowFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_view_community_info_fl, mShowFragment, CommunityBasicInfoFragment.TAG);
        transaction.show(mShowFragment);
        transaction.commit();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);

    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.avtivity_view_community_info_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.avtivity_view_community_info_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.avtivity_view_community_info_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.avtivity_view_community_info_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.avtivity_view_community_info_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.avtivity_view_community_info_header).findViewById(R.id.add);
        tvTitle = (TextView) findViewById(R.id.avtivity_view_community_info_header).findViewById(R.id.title);
        if (isView) {
            tvTitle.setText("查看社区信息");
        }else{
            tvTitle.setText("修改社区信息");
        }
        radioGroup = (RadioGroup) findViewById(R.id.activity_view_community_info_radiogroup);
        rbBasicInfo = (RadioButton) findViewById(R.id.activity_view_community_info_rb_basic_info);
        rbStaffInfo = (RadioButton) findViewById(R.id.activity_view_community_info_rb_staff_info);
        rbCheckRecord = (RadioButton) findViewById(R.id.activity_view_community_info_rb_check_record);

    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), ViewCommunityInfoActivity.this,ViewCommunityInfoActivity.this);
            setMenuClick.setMenuClick();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.activity_view_community_info_rb_basic_info:
                switchPages(CommunityBasicInfoFragment.TAG, CommunityBasicInfoFragment.class);
                break;
            case R.id.activity_view_community_info_rb_staff_info:
                switchPages(CommunityStaffInfoFragment.TAG, CommunityStaffInfoFragment.class);
                break;
            case R.id.activity_view_community_info_rb_check_record:
                switchPages(CommunityCheckRecordFragment.TAG, CommunityCheckRecordFragment.class);
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
                bundle.putString("sqId",sqId);
                bundle.putBoolean("isView",isView);
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
            transaction.add(R.id.activity_view_community_info_fl, mShowFragment, tag);

        }
        transaction.commit();
    }
}
