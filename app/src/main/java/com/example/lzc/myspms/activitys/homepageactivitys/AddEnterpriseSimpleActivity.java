package com.example.lzc.myspms.activitys.homepageactivitys;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentBreadCrumbs;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.NoticeActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailSimpleActivity;
import com.example.lzc.myspms.adapters.ImageGridAdapter;
import com.example.lzc.myspms.adapters.ProjectSafeAdapter;
import com.example.lzc.myspms.adapters.SafeInfoStaffAdapter;
import com.example.lzc.myspms.adapters.SafeInfoTzzyAdapter;
import com.example.lzc.myspms.adapters.SafeInfoWhpAdapter;
import com.example.lzc.myspms.adapters.SafeInfoZsryAdapter;
import com.example.lzc.myspms.adapters.SimpleTreeAdapter;
import com.example.lzc.myspms.bean.FileBean;
import com.example.lzc.myspms.bean.Node;
import com.example.lzc.myspms.bean.TreeListViewAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.custom.MyGridView;
import com.example.lzc.myspms.custom.PicassoImageLoder;
import com.example.lzc.myspms.fragments.NewCheckFragment;
import com.example.lzc.myspms.fragments.homepagefragments.ComponyBasicInfoFragment;
import com.example.lzc.myspms.fragments.homepagefragments.ComponySafeInfoFragment;
import com.example.lzc.myspms.fragments.homepagefragments.ComponyStaffInfoFragment;
import com.example.lzc.myspms.fragments.homepagefragments.menufragments.AlreadyDownloadFragment;
import com.example.lzc.myspms.fragments.homepagefragments.menufragments.CanDownloadFragment;
import com.example.lzc.myspms.fragments.homepagefragments.menufragments.UploadFragment;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumCommunityModel;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.FindByIdWithStaffModel;
import com.example.lzc.myspms.models.JsonModel;
import com.example.lzc.myspms.models.Location;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.TzzyryModel;
import com.example.lzc.myspms.models.WhpJsonModel;
import com.example.lzc.myspms.models.ZsryJsonModel;
import com.example.lzc.myspms.utils.BitmapUtils;
import com.example.lzc.myspms.utils.GpsUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.example.lzc.myspms.utils.ShowMenuPopup;
import com.example.lzc.myspms.utils.TakePhotoUtils;
import com.example.lzc.myspms.utils.ValidateUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddEnterpriseSimpleActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public static final String TAG = AddEnterpriseSimpleActivity.class.getSimpleName();
    private String which;
    private String qyId = "";
    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgMenu;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;
    private boolean popupMenuShow = false;
    private RadioGroup radioGroup;
    private Fragment mShowFragment;
    private Gson gson = new Gson();
    private FindByIdWithStaffModel findModel = new FindByIdWithStaffModel();
    private FindByIdWithStaffModel.FindByIdWithStaffMsgModel componyInfo =findModel.new FindByIdWithStaffMsgModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enterprise_simple);
        which = getIntent().getStringExtra("which");
        if (!which.equals("add")) {
            qyId = getIntent().getStringExtra("qyId");
            Log.e(TAG, "onCreate: " + qyId);
        }
        initView();
        initData();
        initListener();
    }

    private void initData() {
        if (which.equals("add")) {
            tvTitle.setText("添加企业信息");
            radioGroup.setVisibility(View.INVISIBLE);
        } else if (which.equals("view")) {
            tvTitle.setText("查看企业信息");

        } else {
            radioGroup.setVisibility(View.INVISIBLE);
            tvTitle.setText("修改企业信息");
        }
        if (!which.equals("add")) {
            //传componyInfo
            initDataFromServer();

        }else{
            mShowFragment = new ComponyBasicInfoFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.activity_add_enterprise_simple_fl, mShowFragment, ComponyBasicInfoFragment.TAG);
            Bundle bundle = new Bundle();
            bundle.putString("which", which);
            bundle.putString("qyId", qyId);
            bundle.putSerializable("componyInfo",componyInfo);
            mShowFragment.setArguments(bundle);
            transaction.show(mShowFragment);
            transaction.commit();
        }
    }


    private void initListener() {
        imgBack.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        if (which.equals("view")) {
            radioGroup.setOnCheckedChangeListener(this);
        }
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.activity_add_enterprise_simple_header).findViewById(R.id.title);
        imgBack = (ImageView) findViewById(R.id.activity_add_enterprise_simple_header).findViewById(R.id.back);
        imgMenu = (ImageView) findViewById(R.id.activity_add_enterprise_simple_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_add_enterprise_simple_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_add_enterprise_simple_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_add_enterprise_simple_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_add_enterprise_simple_header).findViewById(R.id.add);
        radioGroup = (RadioGroup) findViewById(R.id.activity_add_enterprise_simple_rg);
    }


    @Override
    public void onClick(View v) {
        if (v != null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), AddEnterpriseSimpleActivity.this,AddEnterpriseSimpleActivity.this);
            setMenuClick.setMenuClick();
        }
    }

    /**
     * @desc 将数据加载并设置到控件上
     * @date 2018/5/12 9:02
     */
    private void initDataFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/findByIdWithStaff")
                .addParams("id", qyId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(AddEnterpriseSimpleActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Log.e(TAG, "onResponse: /baseEnterprise/findByIdWithStaff" + response);
                            FindByIdWithStaffModel enterpriseInfoModel = gson.fromJson(response, FindByIdWithStaffModel.class);
                            if (enterpriseInfoModel.isData()) {
                                componyInfo = gson.fromJson(enterpriseInfoModel.getMsg(), FindByIdWithStaffModel.FindByIdWithStaffMsgModel.class);
                                mShowFragment = new ComponyBasicInfoFragment();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.add(R.id.activity_add_enterprise_simple_fl, mShowFragment, ComponyBasicInfoFragment.TAG);
                                Bundle bundle = new Bundle();
                                bundle.putString("which", which);
                                bundle.putString("qyId", qyId);
                                bundle.putSerializable("componyInfo", componyInfo);
                                mShowFragment.setArguments(bundle);
                                transaction.show(mShowFragment);
                                transaction.commit();
                            } else {
                                Toast.makeText(AddEnterpriseSimpleActivity.this, enterpriseInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        if (componyInfo == null&&qyId.length()>0) {
//            Toast.makeText(this, "正在加载数据...", Toast.LENGTH_SHORT).show();
//        } else {
            switch (checkedId) {
                case R.id.activity_add_enterprise_simple_rb_basicinfo:
                    switchPages(ComponyBasicInfoFragment.TAG, ComponyBasicInfoFragment.class);
                    break;
                case R.id.activity_add_enterprise_simple_rb_staffinfo:
                    switchPages(ComponyStaffInfoFragment.TAG, ComponyStaffInfoFragment.class);
                    break;
                case R.id.activity_add_enterprise_simple_rb_safeinfo:
                    switchPages(ComponySafeInfoFragment.TAG, ComponySafeInfoFragment.class);
                    break;
            }
//        }

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
                bundle.putString("which", which);
                bundle.putString("qyId", qyId);
                bundle.putSerializable("componyInfo", componyInfo);
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
            transaction.add(R.id.activity_add_enterprise_simple_fl, mShowFragment, tag);
        }
        transaction.commit();
    }
}
