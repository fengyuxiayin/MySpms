package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.fragments.homepagefragments.menufragments.AlreadyDownloadFragment;
import com.example.lzc.myspms.fragments.homepagefragments.menufragments.CanDownloadFragment;
import com.example.lzc.myspms.fragments.homepagefragments.menufragments.UploadFragment;
import com.example.lzc.myspms.utils.SetMenuClick;

import java.lang.reflect.InvocationTargetException;

public class InstrumentActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private RadioGroup radioGroup;
    private RadioButton rbCanDownload;
    private RadioButton rbAlreadyDownload;
    private RadioButton rbDownloading;
//    private PullToRefreshListView listView;
    private TextView textView;
    private Fragment mShowFragment;
    private boolean popupMenuShow = false;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument);
        initView();
        initListener();
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
        tvTitle = (TextView) findViewById(R.id.activity_instrument_header).findViewById(R.id.title);
        tvTitle.setText("文书管理");
        imgBack = (ImageView) findViewById(R.id.activity_instrument_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_instrument_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_instrument_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_instrument_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_instrument_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_instrument_header).findViewById(R.id.add);
        radioGroup = (RadioGroup) findViewById(R.id.activity_instrument_rg);
        rbCanDownload = (RadioButton) findViewById(R.id.activity_instrument_rb_can_download);
        rbAlreadyDownload = (RadioButton) findViewById(R.id.activity_instrument_rb_already_download);
        rbDownloading = (RadioButton) findViewById(R.id.activity_instrument_rb_uploaded);
        //显示第一个fragment
        mShowFragment = new CanDownloadFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_instrument_fl, mShowFragment, CanDownloadFragment.TAG);
        transaction.show(mShowFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), InstrumentActivity.this,InstrumentActivity.this);
            setMenuClick.setMenuClick();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.activity_instrument_rb_can_download:
                switchPages(CanDownloadFragment.TAG, CanDownloadFragment.class);
                break;
            case R.id.activity_instrument_rb_already_download:
                switchPages(AlreadyDownloadFragment.TAG, AlreadyDownloadFragment.class);
                break;
            case R.id.activity_instrument_rb_uploaded:
                switchPages(UploadFragment.TAG, UploadFragment.class);
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
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.activity_instrument_fl, mShowFragment, tag);
        }
        transaction.commit();
    }
}
