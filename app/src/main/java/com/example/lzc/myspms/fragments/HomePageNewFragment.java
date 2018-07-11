package com.example.lzc.myspms.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.custom.BarChartView;
import com.example.lzc.myspms.custom.LineChartView;
import com.example.lzc.myspms.custom.WaveHelper;
import com.example.lzc.myspms.custom.WaveView;
import com.example.lzc.myspms.fragments.homepagefragments.AchieveFragment;
import com.example.lzc.myspms.fragments.homepagefragments.PassRateFragment;
import com.example.lzc.myspms.fragments.homepagefragments.RangeFragment;
import com.example.lzc.myspms.models.CheckEnterpriseResultModel;
import com.example.lzc.myspms.models.CheckProjectResultModel;
import com.example.lzc.myspms.models.CheckQyTimesForZd;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LZC on 2017/10/27.
 */
public class HomePageNewFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    public static final String TAG = HomePageNewFragment.class.getSimpleName();
    private View view;
    private Fragment mShowFragment;
    private TextView tvGroupName;
    private RadioGroup radioGroup;
    private RadioButton rbRange;
    private RadioButton rbAchieve;
    private RadioButton rbPassRate;
    private FrameLayout framlayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_homepage, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        tvGroupName.setText(Constant.USER_NAME);
        //显示第一个fragment
        mShowFragment = new RangeFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_new_homepage_fl, mShowFragment, RangeFragment.TAG);
        transaction.show(mShowFragment);
        transaction.commit();
    }

    private void initView() {
        //最上面的小组
        tvGroupName = (TextView) view.findViewById(R.id.fragment_new_homepage_tv_group_name);
        //radiogroup 和 radiobutton
        radioGroup = (RadioGroup) view.findViewById(R.id.fragment_new_homepage_rg);
        rbRange = (RadioButton) view.findViewById(R.id.fragment_new_homepage_rb_range);
        rbAchieve = (RadioButton) view.findViewById(R.id.fragment_new_homepage_rb_achieve);
        rbPassRate = (RadioButton) view.findViewById(R.id.fragment_new_homepage_rb_passrate);
        //framlayout
        framlayout = (FrameLayout) view.findViewById(R.id.fragment_new_homepage_fl);
    }

    private void initListener() {
radioGroup.setOnCheckedChangeListener(this);
    }
    private void switchPages(String tag, Class<? extends Fragment> cls) {
        /**
         * 将当前显示的碎片进行隐藏，之后将要显示的页面显示出来
         */
        FragmentManager fm = getActivity().getSupportFragmentManager();
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
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.fragment_new_homepage_fl, mShowFragment, tag);
        }
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.fragment_new_homepage_rb_range:
                switchPages(RangeFragment.TAG,RangeFragment.class);
                break;
            case R.id.fragment_new_homepage_rb_achieve:
                switchPages(AchieveFragment.TAG,AchieveFragment.class);
                break;
            case R.id.fragment_new_homepage_rb_passrate:
                switchPages(PassRateFragment.TAG,PassRateFragment.class);
                break;
        }
    }
}
