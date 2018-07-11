package com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.CustomScanActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckProjectActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.adapters.PopupMainAdapter;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.SpecialEquipmentFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.DataFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.EquipmentFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.FireFragment;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.SceneFragment;
import com.example.lzc.myspms.models.CheckCatalogModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.NewCheckInfoModel;
import com.example.lzc.myspms.models.ProjectInfoModel;
import com.example.lzc.myspms.models.ScanResultModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CheckCatalogActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private final String TAG = this.getClass().getSimpleName();
    private ImageView imgBack;
    private ImageView imgVideocall;
    private TextView tvTitle;
    private TextView tvEnterpriseName;
    private TextView tvStartTime;
    private Button btnCheckRecord;
    private RadioGroup radioGroup;
    private Fragment mShowFragment;
    //检查id 企业id 检查类型
    private String jcId;
    private String qyId;
    private String jclx;
    //周期记录id
    private String zqjlId;
    private TextView tvDataName;
    private CheckCatalogModel.CheckCatalogMsgModel newCheckMsgInfoModel;
    private CheckCatalogModel newCheckInfoModel;
    private Gson gson;
    private Intent intent = new Intent();
    private Button btnAddProject;
    //从fragment(EquipFragment SpecialEquipment)中传出来的jcxmId 与扫描二维码获取的数据中的id作比较
    private String jcxmId;
    private String qymc = "";
    private TextView tvScan;
    private PopupWindow popupWindow;
    //菜单按钮的文字内容
    private String[] titleArray = new String[]{"扫描项目", "视频通话"};
    //菜单按钮的图片资源
    private Integer[] imgArray = new Integer[]{R.mipmap.qrcode_white, R.mipmap.videocall};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_catalog);
        //获取上一页面传过来的数据
        jcId = getIntent().getStringExtra("jcId");
        qyId = getIntent().getStringExtra("qyId");
        jclx = getIntent().getStringExtra("jclx");
        Log.e(TAG, "onCreate: " + "jcid"+jcId+"qyid"+qyId);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideocall.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        btnCheckRecord.setOnClickListener(this);
        btnAddProject.setOnClickListener(this);
    }

    private void initData() {
        getCatalogData();
    }

    /**
     * @param
     * @desc 获取企业目录页的所有信息 显示第一个fragment 设置资料名称
     * @date 2017/12/7 9:56
     */
    private void getCatalogData() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/jobCycleRecord/find")
                .addParams("jclx", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(CheckCatalogActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        gson = new Gson();
                        NewCheckInfoModel newCheckInfoModel = gson.fromJson(response, NewCheckInfoModel.class);
                        if (newCheckInfoModel.isData()) {
                            NewCheckInfoModel.NewCheckMsgInfoModel newCheckMsgInfoModel = gson.fromJson(newCheckInfoModel.getMsg(), NewCheckInfoModel.NewCheckMsgInfoModel.class);
                            List<NewCheckInfoModel.NewCheckMsgInfoModel.ListBean> list = newCheckMsgInfoModel.getList();
                            //根据企业id来设置企业信息
                            for (int i = 0; i < list.size(); i++) {
                                if ((list.get(i).getQyId()+"").equals(qyId)) {
                                    //设置企业名称
//                                    tvEnterpriseName.setText(newCheckMsgInfoModel.getList().get(i).getQymc());
//                                    qymc = newCheckMsgInfoModel.getList().get(i).getQymc();
                                    //格式化数据，设置开始时间
                                    String date = DateUtil.long2Date(newCheckMsgInfoModel.getList().get(i).getCreateTime());
                                    tvStartTime.setText(date);
                                    zqjlId = newCheckMsgInfoModel.getList().get(i).getId()+"";
                                    Log.e(TAG, "onResponse: 周期记录id"+zqjlId );
                                    break;
                                }
                            }
                            //更改资料名称
                            tvDataName.setText("检查名称");
                            //加载第一个fragment
                            mShowFragment = new EquipmentFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("qyId", qyId);
                            //传到对应fragmment  需要通过这两个参数进行后续操作
                            bundle.putString("jcId", jcId);
                            bundle.putString("zqjlId", zqjlId + "");
                            bundle.putString("jclx", jclx);
                            //主要是为了ProjectAdapter判断是检查项目还是查看项目 CheckCatalogActivity是修改项目
                            bundle.putString("startClass", "CheckCatalogActivity");
                            mShowFragment.setArguments(bundle);
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.add(R.id.activity_check_catalog_fl, mShowFragment, EquipmentFragment.TAG);
                            transaction.show(mShowFragment);
                            transaction.commit();
                        } else {
                            Toast.makeText(CheckCatalogActivity.this, newCheckInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //获取二维码扫描回来的数据
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            Log.e(TAG, "onActivityResult: CheckCatalog");
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "内容为空或取消了扫描", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "扫描成功", Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                Log.e(TAG, "onActivityResult: " + ScanResult);
                if (ScanResult.length()>0) {
                    gson = new Gson();
                    ScanResultModel scanResultModel = gson.fromJson(ScanResult, ScanResultModel.class);
                    if (jcxmId.equals(scanResultModel.getId())) {//扫描的二维码就是当前这个项目生成的
                        //跳转到CheckItesActivity
                        getProjectDetailData(scanResultModel.getQyId());
                    }else{
                        Toast.makeText(this, "当前二维码不属于该项目", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "这是个报废二维码，里面没有数据", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    /**
     *
     *@desc 从fragment传过来的检查项目id
     *@param jcxmId 检查项目id
     *@date 2018/3/23 10:22
    */
    public void getJcxmId(String jcxmId) {
        this.jcxmId = jcxmId;
    }
    /**
     *
     *@desc 获取当前项目的详细信息并跳转到对应的aavtivity
     *@param qyId 企业id
     *@date 2018/3/23 10:24
     */
    private void getProjectDetailData(final String qyId) {
        final Intent intent = new Intent();
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/findByJcIdAndJcxmId")
                .addParams("jcxmId", jcxmId)
                .addParams("jcId", jcId)
                .build()
                .execute(new StringCallback() {
                    private ProjectInfoModel.ProjectInfoMsgModel projectInfoMsgModel;
                    private ProjectInfoModel projectInfoModel;
                    private LoginInfoModel loginInfoModel;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(CheckCatalogActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse:asdawasdawdasdawdsaawd " + response);
                        projectInfoModel = gson.fromJson(response, ProjectInfoModel.class);
                        if (projectInfoModel.isData()) {
                            projectInfoMsgModel = gson.fromJson(projectInfoModel.getMsg(), ProjectInfoModel.ProjectInfoMsgModel.class);
//                            Log.e(TAG, "onResponse: projectadapter" +"jcid"+ data.get(position).getJcId()+jcId + "zqjlId"+zqjlId+"jcxmId"+data.get(position).getId() + "");
                            intent.putExtra("zqjlId", zqjlId);
                            intent.putExtra("jcxmId", jcxmId);
                            intent.putExtra("jcId", jcId);
                            intent.putExtra("jcxId", projectInfoMsgModel.getId() + "");
                            intent.putExtra("qyId", qyId);
                            //status 是1.0的时候隐藏保存按钮 但是我这里既然能扫描 肯定不是1.0 所以我就随便传了一个值
                            intent.putExtra("status", "0");
                            intent.setClass(CheckCatalogActivity.this, CheckItemsActivity.class);
                            CheckCatalogActivity.this.startActivity(intent);
                        } else {
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/checkProject/save")
                                    .addParams("jcxmId", jcxmId)
                                    .addParams("jcId", jcId)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            Log.e(TAG, "onError: " + e.getMessage());
                                            NetUtil.errorTip(CheckCatalogActivity.this, e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Log.e(TAG, "onResponse: save" + response);
                                            projectInfoModel = gson.fromJson(response, ProjectInfoModel.class);
                                            if (projectInfoModel.isData()) {
                                                projectInfoMsgModel = gson.fromJson(projectInfoModel.getMsg(), ProjectInfoModel.ProjectInfoMsgModel.class);
//                                                Log.e(TAG, "onResponse: projectadapter" + data.get(position).getJcId() + "");
                                                intent.putExtra("zqjlId", zqjlId);
                                                intent.putExtra("jcxmId", jcxmId);
                                                intent.putExtra("jcId", jcId);
                                                intent.putExtra("jcxId", projectInfoMsgModel.getId() + "");
                                                intent.putExtra("qyId", qyId);
                                                intent.putExtra("status", "0");
                                                intent.setClass(CheckCatalogActivity.this, CheckItemsActivity.class);
                                                CheckCatalogActivity.this.startActivity(intent);
                                            } else {
                                                Toast.makeText(CheckCatalogActivity.this, "保存检查点信息失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
    private void initView() {
        imgBack = (ImageView) findViewById(R.id.activity_check_catalog_header).findViewById(R.id.back);
        imgVideocall = (ImageView) findViewById(R.id.activity_check_catalog_header).findViewById(R.id.videocall);
        imgVideocall.setImageResource(R.mipmap.add_white);
        tvTitle = (TextView) findViewById(R.id.activity_check_catalog_header).findViewById(R.id.title);
        tvTitle.setText("企业检查目录");
        tvEnterpriseName = (TextView) findViewById(R.id.activity_check_catalog_name);
        tvStartTime = (TextView) findViewById(R.id.activity_check_catalog_time);
        btnCheckRecord = (Button) findViewById(R.id.activity_check_catalog_btn_result);
        btnAddProject = (Button) findViewById(R.id.activity_check_catalog_btn_add_project);
        radioGroup = (RadioGroup) findViewById(R.id.activity_check_catalog_rg);
        tvDataName = (TextView) findViewById(R.id.activity_check_catalog_tv_data_name);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.back:
                    finish();
                    break;
                case R.id.videocall:
                    View contentView = View.inflate(this, R.layout.popup_main_menu, null);
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                        popupWindow = null;
                    } else {
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_check_catalog_header);
                        ListView listView = (ListView) contentView.findViewById(R.id.popup_main_menu_lv);
                        PopupMainAdapter popupMainAdapter = new PopupMainAdapter(titleArray, imgArray, this);
                        listView.setAdapter(popupMainAdapter);
                        popupWindow = new PopupWindow();
                        popupWindow.setBackgroundDrawable(new BitmapDrawable());
                        popupWindow.setOutsideTouchable(true);
                        popupWindow.setWidth((int) (getWindowManager().getDefaultDisplay().getWidth() * 0.2));
                        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupWindow.setContentView(contentView);
                        popupWindow.showAsDropDown(linearLayout, 0, 0, Gravity.RIGHT);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent;
                                switch (position) {
                                    case 0://添加企业信息
                                        new IntentIntegrator(CheckCatalogActivity.this)
                                                .setOrientationLocked(false)
                                                .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
                                                .initiateScan();
                                        popupWindow.dismiss();
                                        popupWindow = null;
                                        break;
                                    case 1://视频通话
                                        intent = new Intent();
                                        intent.setClass(CheckCatalogActivity.this, VideoCallActivity.class);
                                        startActivity(intent);
                                        popupWindow.dismiss();
                                        popupWindow = null;
                                        break;
                                }
                            }
                        });
                    }
                    break;
                case R.id.activity_check_catalog_btn_result:
                    intent.setClass(this, CheckProjectActivity.class);
                    intent.putExtra("qymc", qymc);
                    intent.putExtra("qyId", qyId);
                    //传到对应fragmment  需要通过这两个参数进行后续操作
                    intent.putExtra("jcId", jcId);
                    intent.putExtra("zqjlId", zqjlId + "");
                    intent.putExtra("jclx", jclx);
                    startActivity(intent);
                    break;
                case R.id.activity_check_catalog_btn_add_project:
                    intent.setClass(this, AddProjectActivity.class);
                    intent.putExtra("qyId", qyId);
                    intent.putExtra("qymc", qymc);
                    intent.putExtra("startClass", "CheckCatalogActivity");
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.activity_check_catalog_equipment:
                //更改资料名称
                tvDataName.setText("检查名称");
                switchPages(EquipmentFragment.TAG, EquipmentFragment.class);
                break;
            case R.id.activity_check_catalog_scene:
                //更改资料名称
                tvDataName.setText("现场检查点名称");
                switchPages(SceneFragment.TAG, SceneFragment.class);
                break;
            case R.id.activity_check_catalog_data:
                //更改资料名称
                tvDataName.setText("资料名称");
                switchPages(DataFragment.TAG, DataFragment.class);
                break;
            case R.id.activity_check_catalog_fire:
                //更改资料名称
                tvDataName.setText("消防设施名称");
                switchPages(FireFragment.TAG, FireFragment.class);
                break;
            case R.id.activity_check_catalog_special_equipment:
                //更改资料名称
                tvDataName.setText("特殊设备名称");
                switchPages(SpecialEquipmentFragment.TAG, SpecialEquipmentFragment.class);
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
//                bundle.putString("qymc", qymc);
                bundle.putString("qyId", qyId);
                //传到对应fragmment  需要通过这两个参数进行后续操作
                bundle.putString("jcId", jcId);
                bundle.putString("zqjlId", zqjlId + "");
                bundle.putString("jclx", jclx);
                //主要是为了ProjectAdapter判断是检查项目还是查看项目 CheckCatalogActivity是修改项目
                bundle.putString("startClass", "CheckCatalogActivity");
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
            transaction.add(R.id.activity_check_catalog_fl, mShowFragment, tag);
        }
        transaction.commit();
    }
}
