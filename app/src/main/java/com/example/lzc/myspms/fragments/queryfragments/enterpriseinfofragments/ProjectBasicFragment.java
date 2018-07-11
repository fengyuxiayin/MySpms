package com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.adapters.ProjectBasicAdapter;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.ComponyAllInfo;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.ProjectBasicInfoModel;
import com.example.lzc.myspms.models.ProjectDetailModel;
import com.example.lzc.myspms.models.ProjectSafeInfo;
import com.example.lzc.myspms.models.TreeDataModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LZC on 2017/10/31.
 */
public class ProjectBasicFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = ProjectBasicFragment.class.getSimpleName();
    private String xmId;
    private String qymc;
    private String startClass;
    private Gson gson;
    //basic页面的数据
    private List<ComponyAllInfo> data = new ArrayList<>();
    private Gson gson1;
    private ProjectDetailModel.ProjectDetailMsgModel projectDetailMsgModel;
    private List<ProjectSafeInfo> safeData = new ArrayList<>();
    private EditText etSsqy;
    private EditText etXmlx;
    private EditText etEjaqys;
    private EditText etSjaqys;
    private LinearLayout llTwoThree;
    private TextView tvEjaqys;
    private TextView tvSjaqys;
    private LinearLayout llFourFive;
    private TextView tvSjaays;
    private EditText etSjaays;
    private TextView tvWjaqys;
    private EditText etWjaqys;
    private EditText etXmmc;
    private EditText etXmbm;
    private EditText etSsbm;
    private EditText etSydd;
    private EditText etSbpp;
    private EditText etSbxh;
    private EditText etEddy;
    private EditText etGlxh;
    private EditText etSbjz;
    private EditText etScrq;
    private EditText etQyrq;
    private EditText etBxrq;
    private EditText etJdrq;
    private List<EnumModel> dataXmlx = new ArrayList<>();
    private List<EnumModel> dataEjys = new ArrayList<>();
    private List<EnumModel> dataSjys = new ArrayList<>();
    private List<EnumModel> dataSays = new ArrayList<>();
    private List<EnumModel> dataWjys = new ArrayList<>();
    private ProjectBasicInfoModel projectBasicInfoModel;
    //项目类型的数据 写死的
    private Map<Integer, String> mapXmlx = new HashMap<Integer, String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project_basic, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xmId = getArguments().getString("xmId");
        qymc = getArguments().getString("qymc");
        startClass = getArguments().getString("startClass");
        initView();
        initData();
        initListener();
    }

    private void initListener() {

    }

    private void initView() {
        projectBasicInfoModel = new ProjectBasicInfoModel();
        etSsqy = (EditText) view.findViewById(R.id.fragment_project_basic_et_below_ssqy);
        etXmlx = (EditText) view.findViewById(R.id.fragment_project_basic_et_xmlx);
        llTwoThree = (LinearLayout) view.findViewById(R.id.fragment_project_basic_ll_23);
        tvEjaqys = (TextView) view.findViewById(R.id.fragment_project_basic_tv_ejaqys);
        etEjaqys = (EditText) view.findViewById(R.id.fragment_project_basic_et_ejaqys);
        tvSjaqys = (TextView) view.findViewById(R.id.fragment_project_basic_tv_sjaqys);
        etSjaqys = (EditText) view.findViewById(R.id.fragment_project_basic_et_sjaqys);
        llFourFive = (LinearLayout) view.findViewById(R.id.fragment_project_basic_ll_45);
        tvSjaays = (TextView) view.findViewById(R.id.fragment_project_basic_tv_sjaays);
        etSjaays = (EditText) view.findViewById(R.id.fragment_project_basic_et_sjaays);
        tvWjaqys = (TextView) view.findViewById(R.id.fragment_project_basic_tv_wjaqys);
        etWjaqys = (EditText) view.findViewById(R.id.fragment_project_basic_et_wjaqys);
        etXmmc = (EditText) view.findViewById(R.id.fragment_project_basic_et_xmmc);
        etXmbm = (EditText) view.findViewById(R.id.fragment_project_basic_et_xmbm);
        etSsbm = (EditText) view.findViewById(R.id.fragment_project_basic_et_below_ssbm);
        etSydd = (EditText) view.findViewById(R.id.fragment_project_basic_et_sydd);
        etSbpp = (EditText) view.findViewById(R.id.fragment_project_basic_et_sbpp);
        etSbxh = (EditText) view.findViewById(R.id.fragment_project_basic_et_sbxh);
        etEddy = (EditText) view.findViewById(R.id.fragment_project_basic_et_eddy);
        etGlxh = (EditText) view.findViewById(R.id.fragment_project_basic_et_glxh);
        etSbjz = (EditText) view.findViewById(R.id.fragment_project_basic_et_sbjz);
        etScrq = (EditText) view.findViewById(R.id.fragment_project_basic_et_scrq);
        etQyrq = (EditText) view.findViewById(R.id.fragment_project_basic_et_qyrq);
        etBxrq = (EditText) view.findViewById(R.id.fragment_project_basic_et_bxrq);
        etJdrq = (EditText) view.findViewById(R.id.fragment_project_basic_et_jdrq);
        //设置EditText点击的监听
        etXmlx.setOnClickListener(this);
        etEjaqys.setOnClickListener(this);
        etSjaqys.setOnClickListener(this);
        etSjaays.setOnClickListener(this);
        etScrq.setOnClickListener(this);
        etQyrq.setOnClickListener(this);
        etBxrq.setOnClickListener(this);
        etJdrq.setOnClickListener(this);
        //
    }

    private void initData() {
        gson = new Gson();
        dataXmlx.clear();
        dataXmlx.add(new EnumModel("359", "设备类"));
        dataXmlx.add(new EnumModel("56", "现场类"));
        dataXmlx.add(new EnumModel("1", "资料类"));
        dataXmlx.add(new EnumModel("167", "消防类"));
        dataXmlx.add(new EnumModel("76", "特种设备类"));
        if (startClass.equals("EnterpriseInfoQueryActivity") || startClass.equals("SceneFragment") || startClass.equals("CheckCatalogActivity")) {
            etSsqy.setText(qymc);
//            ProjectBasicAdapter projectBasicAdapter = new ProjectBasicAdapter(data, getContext(), (AddProjectActivity) getActivity(), startClass);
        } else {//修改项目信息
            getDataFromServer();
        }

    }

    /**
     * @desc 从服务器把数据拉下来
     * @date 2017/12/19 11:05
     */
    private void getDataFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/project/findById")
                .addParams("id", xmId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        ProjectDetailModel projectDetailModel = gson.fromJson(response, ProjectDetailModel.class);
                        if (projectDetailModel.isData()) {
                            projectDetailMsgModel = gson.fromJson(projectDetailModel.getMsg(), ProjectDetailModel.ProjectDetailMsgModel.class);
                            setBasicData(projectDetailMsgModel);
                            setSafeData(projectDetailMsgModel);
                            AddProjectActivity activity = (AddProjectActivity) getActivity();
                            activity.getSafeData(safeData);
                            activity.getBasicData(data);
                            ProjectBasicAdapter projectBasicAdapter = new ProjectBasicAdapter(data, getContext(), (AddProjectActivity) getActivity(), startClass);
                            etSsqy.setText(qymc);
                        }else{
                            Toast.makeText(getContext(), projectDetailModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    /**
     * @param projectData 从服务器获取的数据
     * @desc 把从服务器获取的数据赋值到safeData中
     * @date 2017/12/19 11:14
     */
    private void setSafeData(ProjectDetailModel.ProjectDetailMsgModel projectData) {
        try {
            JSONArray jsonArray = new JSONArray(projectDetailMsgModel.getUnsafe());
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    ProjectDetailModel.ProjectDetailMsgModel.ProjectDetailUnsafeModel projectDetailUnsafeModel = gson.fromJson(jsonArray.get(i).toString(), ProjectDetailModel.ProjectDetailMsgModel.ProjectDetailUnsafeModel.class);
                    String jctp = projectDetailUnsafeModel.getJctp();
                    if (jctp != null) {
                        //有检查点图片返回时
                        Log.e(TAG, "initData: 检查点图片不为空");
                        List<String> list = Arrays.asList(jctp.split(","));
                        safeData.add(new ProjectSafeInfo(projectDetailUnsafeModel.getId() + "", projectDetailUnsafeModel.getMemo(), list));
                    } else {
                        //没有图片时
                        safeData.add(new ProjectSafeInfo(projectDetailUnsafeModel.getId() + "", projectDetailUnsafeModel.getMemo(), new ArrayList<String>()));
                        Log.e(TAG, "initData: 检查点图片为空");
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param projectData 从服务器获取的数据
     * @desc 把从服务器获取的数据赋值到data中
     * @date 2017/12/19 11:14
     */
    private void setBasicData(ProjectDetailModel.ProjectDetailMsgModel projectData) {
        for (int i = 0; i < dataXmlx.size(); i++) {
            if (dataXmlx.get(i).getKey().equals(projectData.getXmlx() + "")) {
                etXmlx.setText(dataXmlx.get(i).getValue());
                projectBasicInfoModel.setXmlx(Integer.valueOf(dataXmlx.get(i).getKey()));
                break;
            }
        }
        //设置2 3 4 级安全要素EditText的值以及同级别安全要素的全部数据
        if (projectData.getEjys() != null) {
            llTwoThree.setVisibility(View.VISIBLE);
            etEjaqys.setVisibility(View.VISIBLE);
            tvEjaqys.setVisibility(View.VISIBLE);
            etSjaqys.setVisibility(View.INVISIBLE);
            tvSjaqys.setVisibility(View.INVISIBLE);
            getTreeData(projectData.getXmlx() + "", projectData.getEjys() + "", dataEjys, etEjaqys, 2);
        } else {
            llTwoThree.setVisibility(View.GONE);
        }
        if (projectData.getSays() != null) {
            getTreeData(projectData.getEjys() + "", projectData.getSays() + "", dataSjys, etSjaqys, 3);
            llTwoThree.setVisibility(View.VISIBLE);
            etEjaqys.setVisibility(View.VISIBLE);
            tvEjaqys.setVisibility(View.VISIBLE);
            etSjaqys.setVisibility(View.VISIBLE);
            tvSjaqys.setVisibility(View.VISIBLE);
        } else {
            llTwoThree.setVisibility(View.VISIBLE);
            etEjaqys.setVisibility(View.VISIBLE);
            tvEjaqys.setVisibility(View.VISIBLE);
            etSjaqys.setVisibility(View.INVISIBLE);
            tvSjaqys.setVisibility(View.INVISIBLE);
        }
        if (projectData.getSjys() != null) {
            llTwoThree.setVisibility(View.VISIBLE);
            etEjaqys.setVisibility(View.VISIBLE);
            tvEjaqys.setVisibility(View.VISIBLE);
            etSjaqys.setVisibility(View.VISIBLE);
            tvSjaqys.setVisibility(View.VISIBLE);
            llFourFive.setVisibility(View.VISIBLE);
            tvSjaays.setVisibility(View.VISIBLE);
            etSjaays.setVisibility(View.VISIBLE);
            getTreeData(projectData.getSays() + "", projectData.getSjys() + "", dataSays, etSjaays, 4);
        } else {
            llTwoThree.setVisibility(View.VISIBLE);
            etEjaqys.setVisibility(View.VISIBLE);
            tvEjaqys.setVisibility(View.VISIBLE);
            etSjaqys.setVisibility(View.VISIBLE);
            tvSjaqys.setVisibility(View.VISIBLE);
        }
        etXmmc.setText(projectData.getXmmc());
        etXmbm.setText(projectData.getXmbm());
        etSsbm.setText(projectData.getSsbm());
        etSydd.setText(projectData.getSydd());
        etSbpp.setText(projectData.getSbpp());
        etSbxh.setText(projectData.getSbxh());
        etEddy.setText(projectData.getEddy());
        etGlxh.setText(projectData.getGlxh());
        etSbjz.setText(projectData.getSbjz());
        etScrq.setText(dateFormat(projectData.getScrq()));
        etQyrq.setText(dateFormat(projectData.getQyrq()));
        etBxrq.setText(dateFormat(projectData.getBxrq()));
        etJdrq.setText(dateFormat(projectData.getJdrq()));
    }

    /**
     * @param time long类型的时间
     * @desc 把long类型的时间转化为指定格式
     * @date 2017/12/19 10:41
     */
    private String dateFormat(long time) {
        //data中没有对应的值时 time=0
        if (time > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(new Date(time));
            return format;
        } else {
            return "";
        }
    }

    /**
     * @desc 创建新的数据，新建项目时需要
     * @date 2017/12/19 11:06
     */
    private void createNewBasicData() {
        data.add(new ComponyAllInfo("一级安全要素", "", false));
        data.add(new ComponyAllInfo("二级安全要素", "", true));
        data.add(new ComponyAllInfo("三级安全要素", "", true));
        data.add(new ComponyAllInfo("四级安全要素", "", true));
        data.add(new ComponyAllInfo("项目名称", "", false));
        data.add(new ComponyAllInfo("所属企业", qymc, true));
        data.add(new ComponyAllInfo("项目编码", "", false));
        data.add(new ComponyAllInfo("所属部门", "", true));
        data.add(new ComponyAllInfo("使用地点", "", true));
        data.add(new ComponyAllInfo("设备品牌", "", true));
        data.add(new ComponyAllInfo("设备型号", "", true));
        data.add(new ComponyAllInfo("额定电压", "", true));
        data.add(new ComponyAllInfo("功率消耗", "", true));
        data.add(new ComponyAllInfo("设备净重", "", true));
        data.add(new ComponyAllInfo("生产日期", "", true));
        data.add(new ComponyAllInfo("启用日期", "", true));
        data.add(new ComponyAllInfo("保修日期", "", true));
        data.add(new ComponyAllInfo("建档日期", "", true));
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.fragment_project_basic_et_xmlx:
                    //创建项目类型的data
                    dataXmlx.clear();
                    dataXmlx.add(new EnumModel("359", "设备类"));
                    dataXmlx.add(new EnumModel("56", "现场类"));
                    dataXmlx.add(new EnumModel("1", "资料类"));
                    dataXmlx.add(new EnumModel("167", "消防类"));
                    dataXmlx.add(new EnumModel("76", "特种设备类"));
                    showChooseDialog(dataXmlx, etXmlx, dataEjys, 1);
                    break;
                case R.id.fragment_project_basic_et_ejaqys:
                    showChooseDialog(dataEjys, etEjaqys, dataSjys, 2);
                    break;
                case R.id.fragment_project_basic_et_sjaqys:
                    showChooseDialog(dataSjys, etSjaqys, dataSays, 3);
                    break;
                case R.id.fragment_project_basic_et_sjaays:
                    showChooseDialog(dataSays, etSjaays, dataWjys, 4);
                    break;
                case R.id.fragment_project_basic_et_scrq:
                    setDate(etScrq);
                    break;
                case R.id.fragment_project_basic_et_qyrq:
                    setDate(etQyrq);
                    break;
                case R.id.fragment_project_basic_et_bxrq:
                    setDate(etBxrq);
                    break;
                case R.id.fragment_project_basic_et_jdrq:
                    setDate(etJdrq);
                    break;
            }
        }
    }

    /**
     * @param edittext 需要设置显示内容的Edittext
     * @desc 显示日历，并将选择的时间变为指定格式显示在Edittext上
     * @date 2017/12/11 14:27
     */
    private void setDate(final EditText edittext) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(year, monthOfYear, dayOfMonth);
                edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    /**
     * @param data       当前层级的数据
     * @param epointName 设置信息的Edittext
     * @param dataNext   下一层的数据
     * @param level      当前层级的标识
     * @desc 设置安全要素的数据已经布局的隐藏和显示
     * @date 2018/2/26 16:55
     */
    private void showChooseDialog(final List<EnumModel> data, final EditText epointName, final List<EnumModel> dataNext, final int level) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());// 自定义对话框
        int which = 0;
        final String[] array = data2Array(data);
        for (int i = 0; i < array.length; i++) {
            if (epointName.getText().toString().equals(array[i])) {
                which = i;
                break;
            }
        }
        builder.setSingleChoiceItems(array, which, new DialogInterface.OnClickListener() {// 0默认的选中
            @Override
            public void onClick(DialogInterface dialog, final int which) {// which是被选中的位置
                epointName.setText(array[which]);
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL + "/baseSafetyHazard/getTreeGrid")
                        .addParams("id", data.get(which).getKey() + "")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                                NetUtil.errorTip(getContext(), e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: " + response);
                                int count = 0;
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    TreeDataModel treeDataModel;
                                    //count是用来记录有下层记录（number=0）的个数
                                    //判断response有没有数据 没有数据时response = []
                                    if (jsonArray.length() > 0) {
                                        dataNext.clear();
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            treeDataModel = gson.fromJson(jsonArray.get(i).toString(), TreeDataModel.class);
                                            //判断numbers是不是有值，有的话 那么就不显示该项
                                            Log.e(TAG, "onResponse: number" + String.valueOf(treeDataModel.getNumber()));
                                            switch (level) {
                                                case 1:
                                                    epointName.setError(null);
                                                    projectBasicInfoModel.setXmlx(Integer.valueOf(data.get(which).getKey()));
                                                    projectBasicInfoModel.setEjys(null);
                                                    etEjaqys.setText("");
                                                    projectBasicInfoModel.setSays(null);
                                                    etSjaqys.setText("");
                                                    projectBasicInfoModel.setSjys(null);
                                                    etSjaays.setText("");
                                                    break;
                                                case 2:
                                                    projectBasicInfoModel.setEjys(Integer.valueOf(data.get(which).getKey()));
                                                    projectBasicInfoModel.setSays(null);
                                                    etSjaqys.setText("");
                                                    projectBasicInfoModel.setSjys(null);
                                                    etSjaays.setText("");
                                                    break;
                                                case 3:
                                                    projectBasicInfoModel.setSays(Integer.valueOf(data.get(which).getKey()));
                                                    projectBasicInfoModel.setSjys(null);
                                                    etSjaays.setText("");
                                                    break;
                                                case 4:
                                                    projectBasicInfoModel.setSjys(Integer.valueOf(data.get(which).getKey()));
                                                    break;
                                            }

                                            if (treeDataModel.getNumber() == 0) {
                                                //给下一层的data赋值
                                                dataNext.add(new EnumModel(treeDataModel.getId() + "", treeDataModel.getName()));
//                                                    etContent.setText(arry[which]);
                                                count++;
                                            } else {
                                            }
                                        }
//                                            etContent.setText(arry[which]);
                                    } else {
//                                            etContent.setText(arry[which]);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (count > 0) {
                                    //如果下层数据有值，那么就显示下一个视图
                                    switch (level) {
                                        case 1:
                                            //显示2 3的外布局 显示2
                                            llTwoThree.setVisibility(View.VISIBLE);
                                            tvEjaqys.setVisibility(View.VISIBLE);
                                            etEjaqys.setVisibility(View.VISIBLE);
                                            //隐藏 4 5 外布局
                                            llFourFive.setVisibility(View.GONE);
                                            //设置 3
                                            tvSjaqys.setVisibility(View.INVISIBLE);
                                            etSjaqys.setVisibility(View.INVISIBLE);
                                            break;
                                        case 2:
                                            llFourFive.setVisibility(View.GONE);
                                            tvSjaqys.setVisibility(View.VISIBLE);
                                            etSjaqys.setVisibility(View.VISIBLE);
                                            break;
                                        case 3:
                                            llFourFive.setVisibility(View.VISIBLE);
                                            tvSjaays.setVisibility(View.VISIBLE);
                                            etSjaays.setVisibility(View.VISIBLE);
                                            tvWjaqys.setVisibility(View.INVISIBLE);
                                            etWjaqys.setVisibility(View.INVISIBLE);
                                            break;
                                        case 4:
                                            break;
                                    }
                                } else {
                                    //下层数据没值，隐藏
                                    switch (level) {
                                        case 1:
                                            //显示2 3的外布局 显示2
                                            llTwoThree.setVisibility(View.GONE);
                                            tvEjaqys.setVisibility(View.GONE);
                                            etEjaqys.setVisibility(View.GONE);
                                            //隐藏 4 5 外布局
                                            llFourFive.setVisibility(View.GONE);
                                            //设置 3
                                            tvSjaqys.setVisibility(View.INVISIBLE);
                                            etSjaqys.setVisibility(View.INVISIBLE);
                                            break;
                                        case 2:
                                            tvSjaqys.setVisibility(View.INVISIBLE);
                                            etSjaqys.setVisibility(View.INVISIBLE);
                                            break;
                                        case 3:
                                            llFourFive.setVisibility(View.GONE);
                                            tvSjaays.setVisibility(View.GONE);
                                            etSjaays.setVisibility(View.GONE);
                                            break;
                                        case 4:
                                            break;
                                    }
                                }
                            }

                        });
                dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }

    /**
     * @param data 待转化数据源
     * @desc data2Array 将数据源转化为array
     * @date 2017/11/27 14:56
     */
    private String[] data2Array(List<EnumModel> data) {
        String[] array = new String[data.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = data.get(i).getValue();
        }
        return array;
    }

    /**
     * @param id       父级节点id
     * @param dataNext 子节点的数据集合
     * @param editText
     * @param level    第几层安全要素
     * @desc 根据父级id获取下一层子节点的数据
     * @date 2018/3/1 10:03
     */
    private void getTreeData(final String id, final String childId, final List<EnumModel> dataNext, final EditText editText, final int level) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseSafetyHazard/getTreeGrid")
                .addParams("id", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        int count = 0;
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            TreeDataModel treeDataModel;
                            //count是用来记录有下层记录（number=0）的个数
                            //判断response有没有数据 没有数据时response = []
                            if (jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    treeDataModel = gson.fromJson(jsonArray.get(i).toString(), TreeDataModel.class);

                                    //判断numbers是不是有值，有的话 那么就不显示该项
                                    Log.e(TAG, "onResponse: number" + String.valueOf(treeDataModel.getNumber()));
                                    if (treeDataModel.getNumber() == 0) {
                                        //给下一层的data赋值
                                        dataNext.add(new EnumModel(treeDataModel.getId() + "", treeDataModel.getName()));
                                    } else {
                                    }
                                }
                                for (int i = 0; i < dataNext.size(); i++) {
                                    if (dataNext.get(i).getKey().equals(childId)) {
                                        editText.setText(dataNext.get(i).getValue());
                                        switch (level) {
                                            case 2:
                                                projectBasicInfoModel.setEjys(Integer.valueOf(dataNext.get(i).getKey()));
                                                break;
                                            case 3:
                                                projectBasicInfoModel.setSays(Integer.valueOf(dataNext.get(i).getKey()));
                                                break;
                                            case 4:
                                                projectBasicInfoModel.setSjys(Integer.valueOf(dataNext.get(i).getKey()));
                                                break;
                                        }
                                        break;
                                    }
                                }
                            } else {
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
    }

    /**
     * @param
     * @desc AddprojectActivity获取当前页面的数据
     * @date 2018/2/27 10:15
     */
    public ProjectBasicInfoModel getBasicInfo() {
        if (etXmmc != null) {//fragment页面初始化完成
            projectBasicInfoModel.setXmmc(etXmmc.getText().toString() == null ? "" : etXmmc.getText().toString());
            projectBasicInfoModel.setXmbm(etXmbm.getText().toString() == null ? "" : etXmbm.getText().toString());
            projectBasicInfoModel.setSsbm(etSsbm.getText().toString() == null ? "" : etSsbm.getText().toString());
            projectBasicInfoModel.setSydd(etSydd.getText().toString() == null ? "" : etSydd.getText().toString());
            projectBasicInfoModel.setSbpp(etSbpp.getText().toString() == null ? "" : etSbpp.getText().toString());
            projectBasicInfoModel.setSbxh(etSbxh.getText().toString() == null ? "" : etSbxh.getText().toString());
            projectBasicInfoModel.setEddy(etEddy.getText().toString() == null ? "" : etEddy.getText().toString());
            projectBasicInfoModel.setGlxh(etGlxh.getText().toString() == null ? "" : etGlxh.getText().toString());
            projectBasicInfoModel.setSbjz(etSbjz.getText().toString() == null ? "" : etSbjz.getText().toString());
            projectBasicInfoModel.setScrq(etScrq.getText().toString() == null ? "" : etScrq.getText().toString());
            projectBasicInfoModel.setQyrq(etQyrq.getText().toString() == null ? "" : etQyrq.getText().toString());
            projectBasicInfoModel.setBxrq(etBxrq.getText().toString() == null ? "" : etBxrq.getText().toString());
            projectBasicInfoModel.setJdrq(etJdrq.getText().toString() == null ? "" : etJdrq.getText().toString());
            //判断必填项是否填写
            if (etXmlx.getText().toString().length() == 0) {
                etXmlx.setError("必填项");
                return null;
            }
            if (etXmmc.getText().toString().length() == 0) {
                etXmmc.setError("必填项");
                return null;
            }
            if (etXmbm.getText().toString().length() == 0) {
                etXmbm.setError("必填项");
                return null;
            }
            return projectBasicInfoModel;
        } else {
            return null;
        }
    }
}
