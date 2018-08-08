package com.example.lzc.myspms.fragments.homepagefragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.SafeInfoStaffAdapter;
import com.example.lzc.myspms.adapters.SafeInfoWhpAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.custom.MyListView;
import com.example.lzc.myspms.custom.XHLoadingView;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.BaseEnterpriseModel;
import com.example.lzc.myspms.models.ComponyEditInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnterpriseInfoModel;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.FindByIdWithStaffModel;
import com.example.lzc.myspms.models.JsonModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.TzzyryModel;
import com.example.lzc.myspms.models.WhpJsonModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.ValidateUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.adapter.Call;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by LZC on 2017/11/27.
 */

public class ComponySafeInfoFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = ComponySafeInfoFragment.class.getSimpleName();
    private String which;
    private String qyId;
    private FindByIdWithStaffModel.FindByIdWithStaffMsgModel componyInfo;
    private Fragment mShowFragment = this;
    private EditText etSfszaqjg;
    private EditText etAqjgjcjg;
    private EditText etJgfl;
    private EditText etQyfxfj;
    private EditText etJgjb;
    private EditText etQyzc;
    private EditText etSflgtx;
    private EditText etLgtxcjsj;
    private EditText etLgtxfssj;
    private EditText etSfbzh;
    private EditText etBzhdj;
    private EditText etBzhcjsj;
    private EditText etBzhfssj;
    private EditText etSfwhp;
    private ImageView imgAdd;
    private MyListView listView;
    //
    private TextView tvLgtxsj;
    private LinearLayout llLgtxsj;
    private TextView tvBzhdj;
    private LinearLayout llBzhsj;
    private LinearLayout llWhp;
    private List<EnumModel> commonList = new ArrayList<>();
    //企业风险等级数据
    private List<EnumModel> dataEnterpriseRisk = new ArrayList<>();
    //危化品单位数据
    private List<EnumModel> dataMeasureUnitType = new ArrayList<>();
    //监管级别数据
    private List<EnumModel> dataRegulatoryLevel = new ArrayList<>();
    //标准化等级数据
    private List<EnumModel> dataStandradLevel = new ArrayList<>();
    //监管分类数据
    private List<EnumModel> dataRegulatory = new ArrayList<>();
    //二级分类数据
    private List<EnumModel> dataSecondClassFication = new ArrayList<>();
    //行业主管部门数据
    private List<EnumModel> dataHyzgbm = new ArrayList<>();
    private ArrayList<WhpJsonModel> whpList = new ArrayList<>();
    private LinearLayout linearLayout;
    private Button btnUp;
    private Button btnSave;
    private Gson gson = new Gson();
    private SafeInfoWhpAdapter safeInfoWhpAdapter;
    private EditText etEjfl;
    private EditText ethyzgbm;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_compony_safe_info, container, false);
        Log.e(TAG, "onCreateView: ");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: ");
        //获取activity的标志位和qyId
        which = getArguments().getString("which");
        qyId = getArguments().getString("qyId");
        componyInfo = (FindByIdWithStaffModel.FindByIdWithStaffMsgModel) getArguments().getSerializable("componyInfo");
        initView();
        initData();
        initListener();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mShowFragment = this;
            componyInfo = (FindByIdWithStaffModel.FindByIdWithStaffMsgModel) getArguments().getSerializable("componyInfo");
        }
    }
    private void initData() {
        commonList.add(new EnumModel("1", "是"));
        commonList.add(new EnumModel("2", "否"));
        //监管分类
        initEnumData("/enum/getEnums", "REGULATORY_TYPE", dataRegulatory);
        //重点等级 我仅仅是把key换了 dataRegulatoryLevel的名字没有换
        initEnumData("/enum/getEnums", "KEY_ENTERPRISES_LEVEL", dataRegulatoryLevel);
        //标准化等级
        initEnumData("/enum/getEnums", "STANDARDIZATION_LEVEL", dataStandradLevel);
        //企业风险等级
        initEnumData("/enum/getEnums", "ENTERPRISE_RISK_TYPE", dataEnterpriseRisk);
        //危化品单位
        initEnumData("/enum/getEnums", "MEASUREMENT_UNIT", dataMeasureUnitType);
        //二级分类
        initEnumData("/enum/getEnums", "CHILD_INDUSTRY_CATEGORY_TYPE", dataSecondClassFication);
        //行业主管部门信息
        OkHttpUtils.get()
                .url(Constant.SERVER_URL+"/baseDepartment/findAll")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: /baseDepartment/findAll"+e.getMessage() );
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /baseDepartment/findAll"+response );
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        try {
                            JSONArray jsonArray = new JSONArray(infoModel.getMsg());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //解析json数据 这里用不了gson 因为gson要求object开头
                                JSONObject o = (JSONObject) jsonArray.get(i);
                                String value = "";
                                value = o.optString("departName");
                                String key = o.optString("id");
                                dataHyzgbm.add(new EnumModel(key, value));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        //危化品信息
        if (which.equals("edit")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    linearLayout.setVisibility(View.VISIBLE);
                    setData();
                }
            }, 500);
        } else if (which.equals("add")) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setData();
                    linearLayout.setVisibility(View.GONE);
                }
            }, 500);
        }

    }

    private void setData() {
        etSfszaqjg.setText(componyInfo.getAqjgszqk() == 1 ? "是" : "否");
        etAqjgjcjg.setText(componyInfo.getAqjgjcjg());
        for (int i = 0; i < dataRegulatory.size(); i++) {
            if (dataRegulatory.get(i).getKey().equals(componyInfo.getJgfl() + "")) {
                etJgfl.setText(dataRegulatory.get(i).getValue());
                break;
            }
        }
        for (int i = 0; i < dataEnterpriseRisk.size(); i++) {
            if ((dataEnterpriseRisk.get(i).getKey()).equals(componyInfo.getQyfxfj() + "")) {
                etQyfxfj.setText(dataEnterpriseRisk.get(i).getValue());
                break;
            }
        }
        for (int i = 0; i < dataRegulatoryLevel.size(); i++) {
            if ((dataRegulatoryLevel.get(i).getKey()).equals(componyInfo.getSfzd() + "")) {
                etJgjb.setText(dataRegulatoryLevel.get(i).getValue());
                break;
            }
        }
        for (int i = 0; i < dataSecondClassFication.size(); i++) {
            if ((dataSecondClassFication.get(i).getKey()).equals(componyInfo.getQyejfl() + "")) {
                etEjfl.setText(dataSecondClassFication.get(i).getValue());
                break;
            }
        }
        for (int i = 0; i < dataHyzgbm.size(); i++) {
            if ((dataHyzgbm.get(i).getKey()).equals(componyInfo.getHyzgbm() + "")) {
                ethyzgbm.setText(dataHyzgbm.get(i).getValue());
                break;
            }
        }
        Log.e(TAG, "setData: " + componyInfo.getIsqyzc());
        etQyzc.setText(componyInfo.getIsqyzc() == 1 ? "是" : "否");
        etSflgtx.setText(componyInfo.getIslgtx() == 1 ? "是" : "否");
        if (componyInfo.getIslgtx() == 1) {
            llLgtxsj.setVisibility(View.VISIBLE);
            tvLgtxsj.setVisibility(View.VISIBLE);
            etLgtxcjsj.setVisibility(View.VISIBLE);
            etLgtxcjsj.setText(componyInfo.getLgtxcjsj());
            etLgtxfssj.setText(componyInfo.getLgtxfssj());
        }

        etSfbzh.setText(componyInfo.getIsbzh() == 1 ? "是" : "否");
        if (componyInfo.getIsbzh() == 1) {
            llBzhsj.setVisibility(View.VISIBLE);
            tvBzhdj.setVisibility(View.VISIBLE);
            etBzhdj.setVisibility(View.VISIBLE);
            etBzhcjsj.setText(componyInfo.getBzhcjsj());
            etBzhfssj.setText(componyInfo.getBzhfssj());
            for (int i = 0; i < dataStandradLevel.size(); i++) {
                if ((componyInfo.getBzhfj() + "").equals(dataStandradLevel.get(i).getKey())) {
                    etBzhdj.setText(dataStandradLevel.get(i).getValue());
                    break;
                }
            }
        }
        etSfwhp.setText(componyInfo.getIswhp() == 1 ? "是" : "否");
        if (componyInfo.getIswhp() == 1) {
            llWhp.setVisibility(View.VISIBLE);
            try {
                JSONArray jsonArray = new JSONArray(componyInfo.getWhpJson());
                for (int i = 0; i < jsonArray.length(); i++) {
                    WhpJsonModel whpJsonModel = gson.fromJson(jsonArray.getJSONObject(i).toString(), WhpJsonModel.class);
                    whpList.add(whpJsonModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            safeInfoWhpAdapter = new SafeInfoWhpAdapter(whpList, getActivity(), dataMeasureUnitType);
            listView.setAdapter(safeInfoWhpAdapter);
        }

    }

    private void initEnumData(String url, final String params, final List<EnumModel> data) {

        //获取其他类型的Enum数据时都需要参数
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + url)
                .addParams("code", params)
                .build().execute(new com.zhy.http.okhttp.callback.StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                NetUtil.errorTip(getContext(), e.getMessage());
            }

            @Override
            public void onBefore(Request request) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                Log.e(TAG, "initEnumData: " + params + response);
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //解析json数据 这里用不了gson 因为gson要求object开头
                            JSONObject o = (JSONObject) jsonArray.get(i);
                            //监管等级 标准化等级 企业风险分级 企业规模
                            String value = "";
                            value = o.optString("value") + o.optString("memo");
                            String key = o.optString("key");
                            data.add(new EnumModel(key, value));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }


        });


    }

    private void initView() {
        etSfszaqjg = (EditText) view.findViewById(R.id.fragment_safe_info_et_sfszaqjg);
        etAqjgjcjg = (EditText) view.findViewById(R.id.fragment_safe_info_et_aqjgjcjg);
        etJgfl = (EditText) view.findViewById(R.id.fragment_safe_info_et_jgfl);
        etQyfxfj = (EditText) view.findViewById(R.id.fragment_safe_info_et_qyfxfj);
        etJgjb = (EditText) view.findViewById(R.id.fragment_safe_info_et_jgjb);
        etQyzc = (EditText) view.findViewById(R.id.fragment_safe_info_et_qyzc);
        etEjfl = (EditText) view.findViewById(R.id.fragment_safe_info_et_ejfl);
        ethyzgbm = (EditText) view.findViewById(R.id.fragment_safe_info_et_hyzgbm);
        etSflgtx = (EditText) view.findViewById(R.id.fragment_safe_info_et_sflgtx);
        etLgtxcjsj = (EditText) view.findViewById(R.id.fragment_safe_info_et_lgtxcjsj);
        etLgtxfssj = (EditText) view.findViewById(R.id.fragment_safe_info_et_lgtxfssj);
        etSfbzh = (EditText) view.findViewById(R.id.fragment_safe_info_et_sfbzh);
        etBzhcjsj = (EditText) view.findViewById(R.id.fragment_safe_info_et_bzhcjsj);
        etBzhfssj = (EditText) view.findViewById(R.id.fragment_safe_info_et_bzhfssj);
        etSfwhp = (EditText) view.findViewById(R.id.fragment_safe_info_et_sfwhp);
        etBzhdj = (EditText) view.findViewById(R.id.fragment_safe_info_et_bzhdj);
        imgAdd = (ImageView) view.findViewById(R.id.whp_add);
        listView = (MyListView) view.findViewById(R.id.whp_list);
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_safe_info_textview_ll);
        btnUp = (Button) view.findViewById(R.id.fragment_safe_info_btn_up);
        btnSave = (Button) view.findViewById(R.id.fragment_safe_info_btn_save);
        //用来显示和隐藏的控件
        tvLgtxsj = (TextView) view.findViewById(R.id.fragment_safe_info_tv_lgtxsj);
        llLgtxsj = (LinearLayout) view.findViewById(R.id.fragment_safe_info_ll_lgtxsj);
        tvBzhdj = (TextView) view.findViewById(R.id.fragment_safe_info_tv_bzhdj);
        llBzhsj = (LinearLayout) view.findViewById(R.id.fragment_safe_info_ll_bzhsj);
        llWhp = (LinearLayout) view.findViewById(R.id.fragment_safe_info_ll_whp);
    }

    private void initListener() {
        if (which.equals("view")) {
            //让所有控件不可响应
            etSfszaqjg.setEnabled(false);
            etAqjgjcjg.setEnabled(false);
            etJgfl.setEnabled(false);
            etQyfxfj.setEnabled(false);
            etJgjb.setEnabled(false);
            etEjfl.setEnabled(false);
            ethyzgbm.setEnabled(false);
            etQyzc.setEnabled(false);
            etSflgtx.setEnabled(false);
            etLgtxcjsj.setEnabled(false);
            etLgtxfssj.setEnabled(false);
            etSfbzh.setEnabled(false);
            etBzhdj.setEnabled(false);
            etBzhcjsj.setEnabled(false);
            etBzhfssj.setEnabled(false);
            etSfwhp.setEnabled(false);
            imgAdd.setEnabled(false);

        } else {
            etSfszaqjg.setFocusable(false);
            etJgfl.setFocusable(false);
            etQyfxfj.setFocusable(false);
            etEjfl.setFocusable(false);
            ethyzgbm.setFocusable(false);
            etJgjb.setFocusable(false);
            etQyzc.setFocusable(false);
            etSflgtx.setFocusable(false);
            etLgtxcjsj.setFocusable(false);
            etLgtxfssj.setFocusable(false);
            etSfbzh.setFocusable(false);
            etBzhdj.setFocusable(false);
            etBzhcjsj.setFocusable(false);
            etBzhfssj.setFocusable(false);
            etSfwhp.setFocusable(false);
            //
            etSfszaqjg.setOnClickListener(this);
            etJgfl.setOnClickListener(this);
            etQyfxfj.setOnClickListener(this);
            etJgjb.setOnClickListener(this);
            etEjfl.setOnClickListener(this);
            ethyzgbm.setOnClickListener(this);
            etQyzc.setOnClickListener(this);
            etSflgtx.setOnClickListener(this);
            etLgtxcjsj.setOnClickListener(this);
            etLgtxfssj.setOnClickListener(this);
            etSfbzh.setOnClickListener(this);
            etBzhdj.setOnClickListener(this);
            etBzhcjsj.setOnClickListener(this);
            etBzhfssj.setOnClickListener(this);
            etSfwhp.setOnClickListener(this);
            imgAdd.setOnClickListener(this);
            btnSave.setOnClickListener(this);
            btnUp.setOnClickListener(this);
            //根据是否判断布局的显示和隐藏
            etSflgtx.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if ("是".equals(s.toString())) {
//                        llLgtxsj.setVisibility(View.VISIBLE);
                        tvLgtxsj.setVisibility(View.VISIBLE);
                        etLgtxcjsj.setVisibility(View.VISIBLE);
                    } else {
//                        llLgtxsj.setVisibility(View.INVISIBLE);
                        tvLgtxsj.setVisibility(View.INVISIBLE);
                        etLgtxcjsj.setVisibility(View.INVISIBLE);
                    }
                }
            });
            etSfbzh.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if ("是".equals(s.toString())) {
                        llBzhsj.setVisibility(View.VISIBLE);
                        tvBzhdj.setVisibility(View.VISIBLE);
                        etBzhdj.setVisibility(View.VISIBLE);
                    } else {
                        llBzhsj.setVisibility(View.INVISIBLE);
                        tvBzhdj.setVisibility(View.INVISIBLE);
                        etBzhdj.setVisibility(View.INVISIBLE);
                    }
                }
            });
            etSfwhp.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if ("是".equals(s.toString())) {
                        llWhp.setVisibility(View.VISIBLE);
                    } else {
                        llWhp.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.fragment_safe_info_et_sfszaqjg:
                    showChooseDialog(commonList, etSfszaqjg, "是否设置安全机构");
                    break;
                case R.id.fragment_safe_info_et_jgfl:
                    showChooseDialog(dataRegulatory, etJgfl, "监管分类");
                    break;
                case R.id.fragment_safe_info_et_qyfxfj:
                    showChooseDialog(dataEnterpriseRisk, etQyfxfj, "企业风险等级");
                    break;
                case R.id.fragment_safe_info_et_qyzc:
                    showChooseDialog(commonList, etQyzc, "企业自查");
                    break;
                case R.id.fragment_safe_info_et_jgjb:
                    showChooseDialog(dataRegulatoryLevel, etJgjb, "监管级别");
                    break;
                case R.id.fragment_safe_info_et_sflgtx:
                    showChooseDialog(commonList, etSflgtx, "是否两个体系");
                    break;
                case R.id.fragment_safe_info_et_ejfl:
                    showChooseDialog(dataSecondClassFication, etEjfl, "二级分类");
                    break;
                case R.id.fragment_safe_info_et_hyzgbm:
                    showChooseDialog(dataHyzgbm, ethyzgbm, "行业主管部门");
                    break;
                case R.id.fragment_safe_info_et_sfbzh:
                    showChooseDialog(commonList, etSfbzh, "是否标准化");
                    break;
                case R.id.fragment_safe_info_et_sfwhp:
                    showChooseDialog(commonList, etSfwhp, "是否危化品");
                    break;
                case R.id.fragment_safe_info_et_lgtxcjsj:
                    setDate(etLgtxcjsj);
                    break;
                case R.id.fragment_safe_info_et_lgtxfssj:
                    setDate(etLgtxfssj);
                    break;
                case R.id.fragment_safe_info_et_bzhdj:
                    showChooseDialog(dataStandradLevel, etBzhdj, "标准化等级");
                    break;
                case R.id.fragment_safe_info_et_bzhcjsj:
                    setDate(etBzhcjsj);
                    break;
                case R.id.fragment_safe_info_et_bzhfssj:
                    setDate(etBzhfssj);
                    break;
                case R.id.whp_add:
                    whpList.add(new WhpJsonModel());
                    if (safeInfoWhpAdapter != null) {
                        listView.setAdapter(safeInfoWhpAdapter);
                        safeInfoWhpAdapter.notifyDataSetChanged();
                    } else {
                        safeInfoWhpAdapter = new SafeInfoWhpAdapter(whpList, getContext(), dataMeasureUnitType);
                        listView.setAdapter(safeInfoWhpAdapter);
                    }
                    break;
                case R.id.fragment_safe_info_btn_up:
                    switchPages(ComponyStaffInfoFragment.TAG, ComponyStaffInfoFragment.class);
                    break;
                case R.id.fragment_safe_info_btn_save:
                    isDataQualified();
                    break;

            }
        }
    }

    private void isDataQualified() {
        if (getEdittextContent(etAqjgjcjg).length() < 1) {
            Toast.makeText(getContext(), "安全监管监察机构为必填项", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (getEdittextContent(etQyfxfj).length() < 1) {
//            Toast.makeText(getContext(), "企业风险分级为必填项", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (getEdittextContent(etQyzc).length() < 1) {
            Toast.makeText(getContext(), "企业自查为必填项", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getEdittextContent(etJgjb).length() < 1) {
            Toast.makeText(getContext(), "监管级别为必填项", Toast.LENGTH_SHORT).show();
            return;
        }
        if (getEdittextContent(etSfszaqjg).equals("是")) {
            componyInfo.setAqjgszqk(1);
        } else {
            componyInfo.setAqjgszqk(2);
        }
        componyInfo.setAqjgjcjg(getEdittextContent(etAqjgjcjg));
        for (int i = 0; i < dataRegulatory.size(); i++) {
            if (getEdittextContent(etJgfl).equals(dataRegulatory.get(i).getValue())) {
                componyInfo.setJgfl(dataRegulatory.get(i).getKey());
                break;
            }
        }
        for (int i = 0; i < dataSecondClassFication.size(); i++) {
            if (getEdittextContent(etEjfl).equals(dataSecondClassFication.get(i).getValue())) {
                componyInfo.setQyejfl(dataSecondClassFication.get(i).getKey());
                break;
            }
        }
        for (int i = 0; i < dataHyzgbm.size(); i++) {
            if (getEdittextContent(ethyzgbm).equals(dataHyzgbm.get(i).getValue())) {
                componyInfo.setHyzgbm(Long.parseLong(dataHyzgbm.get(i).getKey()));
                break;
            }
        }
        for (int i = 0; i < dataEnterpriseRisk.size(); i++) {
            if (dataEnterpriseRisk.get(i).getValue().equals(getEdittextContent(etQyfxfj))) {
                componyInfo.setJgfl(dataEnterpriseRisk.get(i).getKey());
                break;
            }
        }
        for (int i = 0; i < dataRegulatoryLevel.size(); i++) {
            if (dataRegulatoryLevel.get(i).getValue().equals(getEdittextContent(etJgjb))) {
                componyInfo.setSfzd(dataRegulatoryLevel.get(i).getKey());
                break;
            }
        }
        if (getEdittextContent(etQyzc).equals("是")) {
            componyInfo.setIsqyzc(1);
        } else {
            componyInfo.setIsqyzc(2);
        }
        if (getEdittextContent(etSflgtx).equals("是")) {
            componyInfo.setIslgtx(1);
            componyInfo.setLgtxcjsj(getEdittextContent(etLgtxcjsj));
            componyInfo.setLgtxfssj(getEdittextContent(etLgtxfssj));
        } else {
            componyInfo.setIslgtx(2);
            componyInfo.setLgtxcjsj("");
            componyInfo.setLgtxfssj("");
        }
        if (getEdittextContent(etSfbzh).equals("是")) {
            componyInfo.setIsbzh(1);
            componyInfo.setBzhcjsj(getEdittextContent(etBzhcjsj));
            componyInfo.setBzhfssj(getEdittextContent(etBzhfssj));
            for (int i = 0; i < dataStandradLevel.size(); i++) {
                if (dataStandradLevel.get(i).getValue().equals(getEdittextContent(etBzhdj))) {
                    componyInfo.setBzhfj(Integer.parseInt(dataStandradLevel.get(i).getKey()));
                    break;
                }
            }
        } else {
            componyInfo.setIsbzh(2);
            componyInfo.setBzhfj(0);
            componyInfo.setBzhcjsj("");
            componyInfo.setBzhfssj("");
        }
        if (getEdittextContent(etSfwhp).equals("是")) {
            componyInfo.setIswhp(1);
            componyInfo.setWhpJson(gson.toJson(whpList));
        } else {
            componyInfo.setIswhp(2);
            componyInfo.setWhpJson("");
        }
        Log.e(TAG, "isDataQualified: " + gson.toJson(componyInfo));
//        String s = gson.toJson(componyInfo);
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", qyId.length() == 0 ? "" : qyId);
//        map.put("qymc", componyInfo.getQymc() == null ? "" : componyInfo.getQymc());
//        map.put("zch", componyInfo.getZch() + "" == null ? "" : componyInfo.getZch() + "");
//        map.put("fddbr", componyInfo.getFddbr() == null ? "" : componyInfo.getFddbr() + "");
//        map.put("zcdz", componyInfo.getZcdz() == null ? "" : componyInfo.getZcdz() + "");
//        map.put("lxdh", componyInfo.getLxdh() == null ? "" : componyInfo.getLxdh() + "");
//        map.put("sqId", componyInfo.getSqId() + "" == null ? "" : componyInfo.getSqId() + "");
//        map.put("qyzt", componyInfo.getQyzt() + "" == null ? "" : componyInfo.getQyzt() + "");
//        map.put("jjlxdm", componyInfo.getJjlxdm() + "" == null ? "" : componyInfo.getJjlxdm() + "");
//        map.put("hylbdm", componyInfo.getHylbdm() == null ? "" : componyInfo.getHylbdm() + "");
//        map.put("qylsgx", componyInfo.getQylsgx() + "" == null ? "" : componyInfo.getQylsgx() + "");
//        map.put("qywzjd", componyInfo.getQywzjd() + "" == null ? "" : componyInfo.getQywzjd() + "");
//        map.put("qywzwd", componyInfo.getQywzwd() + "" == null ? "" : componyInfo.getQywzwd() + "");
//        map.put("jyfw", componyInfo.getJyfw() == null ? "" : componyInfo.getJyfw() + "");
//        map.put("zyfzr", componyInfo.getZyfzr() == null ? "" : componyInfo.getZyfzr() + "");
//        map.put("zyfzryddhhm", componyInfo.getZyfzryddhhm() == null ? "" : componyInfo.getZyfzryddhhm() + "");
//        map.put("zyfzrgddhhm", componyInfo.getZyfzrgddhhm() == null ? "" : componyInfo.getZyfzrgddhhm() + "");
//        map.put("zyfzrdzyx", componyInfo.getZyfzrdzyx() == null ? "" : componyInfo.getZyfzrdzyx() + "");
//        map.put("dzyx", componyInfo.getDzyx() == null ? "" : componyInfo.getDzyx());
//               map.put("iszyfzrxx", componyInfo.getIszyfzrxx() + ""==null?"":componyInfo.getIszyfzrxx() + "");
//               map.put("aqfzr", componyInfo.getAqfzr() == null ? "" : componyInfo.getAqfzr() + "");
//               map.put("aqfzryddhhm", componyInfo.getAqfzryddhhm() == null ? "" : componyInfo.getAqfzryddhhm() + "");
//               map.put("aqfzrgddhhm", componyInfo.getAqfzrgddhhm() == null ? "" : componyInfo.getAqfzrgddhhm() + "");
//               map.put("aqfzrdzyx", componyInfo.getAqfzrdzyx() == null ? "" : componyInfo.getAqfzrdzyx() + "");
//               map.put("isaqfzrxx", componyInfo.getIsaqfzrxx() + ""==null?"":componyInfo.getIsaqfzrxx() + "");
//               map.put("cyrysl", componyInfo.getCyrysl() + ""==null?"":componyInfo.getCyrysl() + "");
//               map.put("tzzyrysl", componyInfo.getTzzyrysl() + ""==null?"":componyInfo.getTzzyrysl() + "");
//               map.put("zzaqscglrys", componyInfo.getZzaqscglrys() + ""==null?"":componyInfo.getZzaqscglrys() + "");
//               map.put("zzyjglrys", componyInfo.getZzyjglrys() + ""==null?"":componyInfo.getZzyjglrys() + "");
//               map.put("zcaqgcsrys", componyInfo.getZcaqgcsrys() + ""==null?"":componyInfo.getZcaqgcsrys() + "");
//               map.put("zsrysl", componyInfo.getZsrysl() + ""==null?"": componyInfo.getZsrysl() + "");
//               map.put("qyjc", componyInfo.getQyjc() + ""==null?"":componyInfo.getQyjc() + "");
//               map.put("isbzh", componyInfo.getIsbzh() + ""==null?"":componyInfo.getIsbzh() + "");
//               map.put("isqyzc", componyInfo.getIsqyzc() + ""==null?"":componyInfo.getIsqyzc() + "");
//               map.put("cyryJson", componyInfo.getCyryJson() == null ? "" : componyInfo.getCyryJson() + "");
//               map.put("tzzyryJson", componyInfo.getTzzyryJson() == null ? "" : componyInfo.getTzzyryJson() + "");
//               map.put("zzaqscryJson", componyInfo.getZzaqscryJson() == null ? "" : componyInfo.getZzaqscryJson() + "");
//               map.put("zzyjglryJson", componyInfo.getZzyjglryJson() == null ? "" : componyInfo.getZzyjglryJson() + "");
//               map.put("zcaqgcsJson", componyInfo.getZcaqgcsJson() == null ? "" : componyInfo.getZcaqgcsJson() + "");
////               map.put("ssJson", componyInfo.getZsryJson() == null ? "" : componyInfo.getZsryJson() + "")
//               map.put("ssJson", componyInfo.getSsJson() == null ? "" : componyInfo.getSsJson() + "");
//               map.put("aqjgszqk", componyInfo.getAqjgszqk() + ""==null?"":componyInfo.getAqjgszqk() + "");
//               map.put("aqjgjcjg", componyInfo.getAqjgjcjg() == null ? "" : componyInfo.getAqjgjcjg() + "");
//               map.put("gmqk", componyInfo.getGmqk() + ""==null?"":componyInfo.getGmqk() + "");
//               map.put("jgfl", componyInfo.getJgfl() + "" == null?"" :componyInfo.getJgfl() + "");
//               map.put("jgfljb", componyInfo.getJgfljb() + ""==null?"":componyInfo.getJgfljb() + "");
//               map.put("bzhfj", componyInfo.getBzhfj() + ""==null?"": componyInfo.getBzhfj() + "");
//               map.put("bzhcjsj", componyInfo.getBzhcjsj() + ""==null?"":componyInfo.getBzhcjsj() + "");
//               map.put("bzhfssj", componyInfo.getBzhfssj() + ""==null?"":componyInfo.getBzhfssj() + "");
//               map.put("qyfxfj", componyInfo.getQyfxfj() + ""==null?"":componyInfo.getQyfxfj() + "");
//               map.put("scjydz", componyInfo.getScjydz() == null ? "" : componyInfo.getScjydz() + "");
//               map.put("iswhp", componyInfo.getIswhp() + ""==null?"":componyInfo.getIswhp() + "");
//               map.put("islgtx", componyInfo.getIslgtx() + ""==null?"":componyInfo.getIslgtx() + "");
//               map.put("lgtxcjsj", componyInfo.getLgtxcjsj() == null ? "" : componyInfo.getLgtxcjsj() + "");
//               map.put("lgtxfssj", componyInfo.getLgtxfssj() == null ? "" : componyInfo.getLgtxfssj() + "");
//               map.put("whpJson", componyInfo.getWhpJson() == null ? "" : componyInfo.getWhpJson() + "");
//               map.put("tze", componyInfo.getTze() + ""==null?"":componyInfo.getTze() + "");
//               map.put("tzedw", componyInfo.getTzedw() == null ? "" : componyInfo.getTzedw() + "");
//               map.put("yye", componyInfo.getYye() + ""==null?"":componyInfo.getYye() + "");
//               map.put("yyedw", componyInfo.getYyedw() == null ? "" : componyInfo.getYyedw() + "");
//               map.put("qyfwzb", componyInfo.getQyfwzb() == null ? "" : componyInfo.getQyfwzb() + "");
//               map.put("qytp", componyInfo.getQytp() == null ? "" : componyInfo.getQytp() + "");
//               map.put("fddh", componyInfo.getFddh() == null ? "" : componyInfo.getFddh() + "");
//               map.put("fdxm", componyInfo.getFdxm() == null ? "" : componyInfo.getFdxm() + "");
//               map.put("sfzd",componyInfo.getSfzd()==null?"":componyInfo.getSfzd()+"");
//        JSONObject js = new JSONObject(map);
//        OkGo.<String>post(Constant.SERVER_URL + "/baseEnterprise/save")
//                .upJson(js)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Log.e(TAG, "onSuccess: " + response);
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        Log.e(TAG, "onError: " + response.getException());
//                    }
//                });
//        OkHttpUtils.postString()
//                .url(Constant.SERVER_URL + "/baseEnterprise/save")
//                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .content("\""+gson.toJson(componyInfo)+"\"")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        Log.e(TAG, "onError: "+e.getMessage()+e.getCause() );
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e(TAG, "onResponse: "+response );
//                    }
//                });

        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/save")
                .addParams("id", qyId.length() == 0 ? "" : qyId)
                .addParams("qymc", componyInfo.getQymc()==null?"":componyInfo.getQymc())
                .addParams("zch", componyInfo.getZch() ==null?"":componyInfo.getZch() + "")
                .addParams("fddbr", componyInfo.getFddbr() == null ? "" : componyInfo.getFddbr() + "")
                .addParams("zcdz", componyInfo.getZcdz() == null ? "" : componyInfo.getZcdz() + "")
                .addParams("lxdh", componyInfo.getLxdh() == null ? "" : componyInfo.getLxdh() + "")
                .addParams("sqId", (componyInfo.getSqId() +"").equals("null")?"":componyInfo.getSqId() + "")
                .addParams("qyzt", (componyInfo.getQyzt() +"").equals("null")?"":componyInfo.getQyzt() + "")
                .addParams("jjlxdm", (componyInfo.getJjlxdm() +"").equals("null")? "":componyInfo.getJjlxdm() + "")
                .addParams("hylbdm", componyInfo.getHylbdm() == null ? "" : componyInfo.getHylbdm() + "")
                .addParams("qylsgx", (componyInfo.getQylsgx() +"").equals("null")?"":componyInfo.getQylsgx() + "")
                .addParams("qywzjd", (componyInfo.getQywzjd() +"").equals("null")?"":componyInfo.getQywzjd() + "")
                .addParams("qywzwd", (componyInfo.getQywzwd() +"").equals("null")?"":componyInfo.getQywzwd() + "")
                .addParams("jyfw", componyInfo.getJyfw() == null ? "" : componyInfo.getJyfw() + "")
                .addParams("zyfzr", componyInfo.getZyfzr() == null ? "" : componyInfo.getZyfzr() + "")
                .addParams("zyfzryddhhm", componyInfo.getZyfzryddhhm() == null ? "" : componyInfo.getZyfzryddhhm() + "")
                .addParams("zyfzrgddhhm", componyInfo.getZyfzrgddhhm() == null ? "" : componyInfo.getZyfzrgddhhm() + "")
                .addParams("zyfzrdzyx", componyInfo.getZyfzrdzyx() == null ? "" : componyInfo.getZyfzrdzyx() + "")
                .addParams("dzyx", componyInfo.getDzyx() == null ? "" : componyInfo.getDzyx())
                .addParams("iszyfzrxx", (componyInfo.getIszyfzrxx() +"").equals("null")?"":componyInfo.getIszyfzrxx() + "")
                .addParams("aqfzr", componyInfo.getAqfzr() == null ? "" : componyInfo.getAqfzr() + "")
                .addParams("aqfzryddhhm", componyInfo.getAqfzryddhhm() == null ? "" : componyInfo.getAqfzryddhhm() + "")
                .addParams("aqfzrgddhhm", componyInfo.getAqfzrgddhhm() == null ? "" : componyInfo.getAqfzrgddhhm() + "")
                .addParams("aqfzrdzyx", componyInfo.getAqfzrdzyx() == null ? "" : componyInfo.getAqfzrdzyx() + "")
                .addParams("isaqfzrxx", (componyInfo.getIsaqfzrxx() +"").equals("null")?"":componyInfo.getIsaqfzrxx() + "")
                .addParams("cyrysl", (componyInfo.getCyrysl() +"").equals("null")?"":componyInfo.getCyrysl() + "")
                .addParams("tzzyrysl", (componyInfo.getTzzyrysl() +"").equals("null")?"":componyInfo.getTzzyrysl() + "")
                .addParams("zzaqscglrys", (componyInfo.getZzaqscglrys() +"").equals("null")?"":componyInfo.getZzaqscglrys() + "")
                .addParams("zzyjglrys", (componyInfo.getZzyjglrys() +"").equals("null")?"":componyInfo.getZzyjglrys() + "")
                .addParams("zcaqgcsrys", (componyInfo.getZcaqgcsrys() +"").equals("null")?"":componyInfo.getZcaqgcsrys() + "")
                .addParams("zsrysl", (componyInfo.getZsrysl() +"").equals("null")?"": componyInfo.getZsrysl() + "")
                .addParams("qyjc", (componyInfo.getQyjc() +"").equals("null")?"":componyInfo.getQyjc() + "")
                .addParams("isbzh", (componyInfo.getIsbzh() +"").equals("null")?"":componyInfo.getIsbzh() + "")
                .addParams("isqyzc", (componyInfo.getIsqyzc() +"").equals("null")?"":componyInfo.getIsqyzc() + "")
                .addParams("cyryJson", componyInfo.getCyryJson() == null ? "" : componyInfo.getCyryJson() + "")
                .addParams("tzzyryJson", componyInfo.getTzzyryJson() == null ? "" : componyInfo.getTzzyryJson() + "")
                .addParams("zzaqscryJson", componyInfo.getZzaqscryJson() == null ? "" : componyInfo.getZzaqscryJson() + "")
                .addParams("zzyjglryJson", componyInfo.getZzyjglryJson() == null ? "" : componyInfo.getZzyjglryJson() + "")
                .addParams("zcaqgcsJson", componyInfo.getZcaqgcsJson() == null ? "" : componyInfo.getZcaqgcsJson() + "")
//                .addParams("ssJson", componyInfo.getZsryJson() == null ? "" : componyInfo.getZsryJson() + "")
                .addParams("ssJson", componyInfo.getSsJson() == null ? "" : componyInfo.getSsJson() + "")
                .addParams("aqjgszqk", (componyInfo.getAqjgszqk() +"").equals("null")?"":componyInfo.getAqjgszqk() + "")
                .addParams("aqjgjcjg", componyInfo.getAqjgjcjg() == null ? "" : componyInfo.getAqjgjcjg() + "")
                .addParams("gmqk", (componyInfo.getGmqk() +"").equals("null")?"":componyInfo.getGmqk() + "")
                .addParams("jgfl", componyInfo.getJgfl()  == null?"" :componyInfo.getJgfl() + "")
                .addParams("jgfljb", (componyInfo.getJgfljb() +"").equals("null")?"":componyInfo.getJgfljb() + "")
                .addParams("bzhfj",( componyInfo.getBzhfj() +"").equals("null")?"": componyInfo.getBzhfj() + "")
                .addParams("bzhcjsj", (componyInfo.getBzhcjsj() +"").equals("null")?"":componyInfo.getBzhcjsj() + "")
                .addParams("bzhfssj", (componyInfo.getBzhfssj() +"").equals("null")?"":componyInfo.getBzhfssj() + "")
                .addParams("qyfxfj", (componyInfo.getQyfxfj() +"").equals("null")?"":componyInfo.getQyfxfj() + "")
                .addParams("scjydz", componyInfo.getScjydz() == null ? "" : componyInfo.getScjydz() + "")
                .addParams("iswhp", (componyInfo.getIswhp() +"").equals("null")?"":componyInfo.getIswhp() + "")
                .addParams("islgtx",( componyInfo.getIslgtx() +"").equals("null")?"":componyInfo.getIslgtx() + "")
                .addParams("lgtxcjsj", componyInfo.getLgtxcjsj() == null ? "" : componyInfo.getLgtxcjsj() + "")
                .addParams("lgtxfssj", componyInfo.getLgtxfssj() == null ? "" : componyInfo.getLgtxfssj() + "")
                .addParams("whpJson", componyInfo.getWhpJson() == null ? "" : componyInfo.getWhpJson() + "")
                .addParams("tze", (componyInfo.getTze() +"").equals("null")?"":componyInfo.getTze() + "")
                .addParams("tzedw", componyInfo.getTzedw() == null ? "" : componyInfo.getTzedw() + "")
                .addParams("yye", (componyInfo.getYye() +"").equals("null")?"":componyInfo.getYye() + "")
                .addParams("yyedw", componyInfo.getYyedw() == null ? "" : componyInfo.getYyedw() + "")
                .addParams("qyfwzb", componyInfo.getQyfwzb() == null ? "" : componyInfo.getQyfwzb() + "")
                .addParams("qytp", componyInfo.getQytp() == null ? "" : componyInfo.getQytp() + "")
                .addParams("fddh", componyInfo.getFddh() == null ? "" : componyInfo.getFddh() + "")
                .addParams("fdxm", componyInfo.getFdxm() == null ? "" : componyInfo.getFdxm() + "")
                .addParams("sfzd",componyInfo.getSfzd()==null?"":componyInfo.getSfzd()+"")
                .addParams("qyejfl",componyInfo.getQyejfl()==null?"":componyInfo.getQyejfl()+"")
                .addParams("hyzgbm",(componyInfo.getHyzgbm()+"").equals("null")?"":componyInfo.getHyzgbm()+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getActivity(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        LoginInfoModel info = gson.fromJson(response, LoginInfoModel.class);
                        Toast.makeText(getContext(), "" + info.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public String getEdittextContent(EditText editText) {
        return editText.getText().toString().trim();
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
                Bundle bundle = new Bundle();
                bundle.putString("which", which);
                bundle.putString("qyId", qyId);
                bundle.putSerializable("componyInfo", componyInfo);
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
            transaction.add(R.id.activity_add_enterprise_simple_fl, mShowFragment, tag);
        }
        transaction.commit();
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
     * @param epointName 显示内容的edittext
     * @param data       dialog显示的内容
     * @param methodName 判断是哪个Edittext
     * @desc 点击对应项弹出dialog供用户选择
     * @date 2018/2/21 11:14
     */
    private void showChooseDialog(final List<EnumModel> data, final EditText epointName, final String methodName) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());// 自定义对话框
        final String[] array = data2Array(data);
        int which = 0;
        for (int i = 0; i < array.length; i++) {
            if (epointName.getText().toString().trim().equals(array[i])) {
                which = i;
                break;
            }
        }
        builder.setSingleChoiceItems(array, which, new DialogInterface.OnClickListener() {// 0默认的选中
            @Override
            public void onClick(DialogInterface dialog, final int which) {// which是被选中的位置
                epointName.setText(array[which]);
                switch (methodName) {
                    case "是否设置安全机构":
                        componyInfo.setAqjgszqk(Integer.parseInt(commonList.get(which).getKey()));
                        break;
                    case "监管分类":
                        componyInfo.setJgfl(dataRegulatory.get(which).getKey());
                        break;
                    case "企业风险等级":
                        componyInfo.setQyfxfj(Integer.parseInt(dataEnterpriseRisk.get(which).getKey()));
                        break;
                    case "企业自查":
                        componyInfo.setIsqyzc(Integer.parseInt(commonList.get(which).getKey()));
                        break;
                    case "监管级别":
                        componyInfo.setSfzd(dataRegulatoryLevel.get(which).getKey());
                        break;
                    case "是否两个体系":
                        componyInfo.setIslgtx(Integer.parseInt(commonList.get(which).getKey()));
                        break;
                    case "是否标准化":
                        componyInfo.setIsbzh(Integer.parseInt(commonList.get(which).getKey()));
                        break;
                    case "是否危化品":
                        componyInfo.setIswhp(Integer.parseInt(commonList.get(which).getKey()));
                        break;
                    case "标准化等级":
                        componyInfo.setBzhfj(Integer.parseInt(dataStandradLevel.get(which).getKey()));
                        break;
                    case "二级分类":
                        componyInfo.setQyejfl(dataSecondClassFication.get(which).getKey());
                        break;
                    case "行业主管部门":
                        componyInfo.setQyejfl(dataHyzgbm.get(which).getKey());
                        break;
                }
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
}

