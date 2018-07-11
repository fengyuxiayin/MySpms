package com.example.lzc.myspms.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.ComponyEditInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.utils.GpsUtil;
import com.example.lzc.myspms.utils.ValidateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/11/1.
 */
public class AddEnterpriseAdapter extends BaseAdapter implements View.OnClickListener {
    //错误信息
    private String errorContent;
    private ImageView imgLocation;
    private EditText etContent;
    private Handler handler = new Handler();
    private static final String TAG = "BasicAdapter";
    private LayoutInflater inflater;
    private Context context;
    //公司信息的所有条目
    private List<ComponyEditInfoModel> mData;
    //企业行政类型代码数据
    private List<EnumModel> dataAdiministrite = new ArrayList<>();
    //经济类型代码数据
    private List<EnumModel> dataEconomic = new ArrayList<>();
    //社区名称数据
    private List<EnumModel> dataCommunity = new ArrayList<>();
    //行业类别代码数据
    private List<EnumModel> dataIndustryCateary = new ArrayList<>();
    //safe页面的Enum数据（安全机构设置 规模情况 企业规模）
    private List<EnumModel> dataSafeSet = new ArrayList<>();
    private List<EnumModel> dataScope = new ArrayList<>();
    private List<EnumModel> dataEnterprise = new ArrayList<>();
    //Array 用来存储dialog的内容
    private String[] arrayAdiministrite;
    private String[] arrayEconomic;
    private String[] arrayCommunity;
    private String[] arrayIndustryCateary;
    private String[] arrayOperatStatus;
    private String[] arraySafeSet;
    private String[] arrayScope;
    private String[] arrayEnterprise;

    private TextView tvTitle;
    private setButtonVisibility listener;
    private Activity activity;
    private ComponyEditInfoModel componyEditInfoModel;
    //记录改变后的data（id） 因为往服务器发的Enum的数据都是id，因此就不能直接将data传出去，要根据edittext的值找到对应id
    private List<ComponyEditInfoModel> mDataChange = new ArrayList<>();
    //判断是不是basicfragment
    private boolean isBasicFragment;
    //判断返回的布局类型 0为无图 1为有图
    private int TYPE_NO_IMAGE = 0;
    private int TYPE_IMAGE = 1;
    //判断Gps是否打开
    private boolean isOpen;
    //判断数据是否输入合法
    private boolean isValidate;
    //当前是哪个Activity开启了fragment
    private String whichActivity;
    //判断edittext是否可点击
    private boolean canEdit;
    //判断移动电话是否有内容
    private boolean haveContent;

    @Override
    public void onClick(View v) {
        if (v != null) {
            Log.e(TAG, "onClick: " + v.getTag());
            switch (mData.get((Integer) v.getTag()).getTitle()) {
                case "经济类型代码":
                    showChooseDialog(arrayEconomic, (EditText) v, (int) v.getTag());
                    break;
                case "行业类别代码":
                    showChooseDialog(arrayIndustryCateary, (EditText) v, (int) v.getTag());
                    break;
                case "企业行政隶属关系":
                    showChooseDialog(arrayAdiministrite, (EditText) v, (int) v.getTag());
                    break;
                case "经营状态":
                    showChooseDialog(arrayOperatStatus, (EditText) v, (int) v.getTag());
                    break;
                case "所属社区":
                    showChooseDialog(arrayCommunity, (EditText) v, (int) v.getTag());
                    break;
                case "安全机构设置情况":
                    showChooseDialog(arraySafeSet, (EditText) v, (int) v.getTag());
                    break;
                case "规模情况":
                    showChooseDialog(arrayScope, (EditText) v, (int) v.getTag());
                    break;
                case "企业规模":
                    showChooseDialog(arrayEnterprise, (EditText) v, (int) v.getTag());
                    break;
                case "企业位置":
                    ////gps是打开状态的话，就去设置位置信息，反之提示用户去打开gps
                    isOpen = GpsUtil.openGPSSettings(activity);
                    if (isOpen) {//gps是打开状态的话
                        View view = getView((int) v.getTag(), null, null);
                        EditText edittext = (EditText) view.findViewById(R.id.fragment_homepage_compony_item2_content);
                        if (!Constant.LOCATION_INFO.equals("")) {//还没定位成功的时候 提示用户 定位成功的话，设置位置信息
                            Toast.makeText(context, "企业位置定位成功", Toast.LENGTH_SHORT).show();
                            edittext.setText(Constant.LOCATION_INFO);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Gps正在定位，请稍等", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case "企业范围":
                    isOpen = GpsUtil.openGPSSettings(activity);
                    if (isOpen) {
                        //获取当前的edittext
                        View view = getView((int) v.getTag(), null, null);
                        EditText edittext = (EditText) view.findViewById(R.id.fragment_homepage_compony_item2_content);
                        if (!Constant.LOCATION_INFO.equals("")) {//如果位置信息不为空串（就是已经获取到了位置信息）
                            String content = edittext.getText().toString();
                            if (content.length() > 0) {
                                //点击一次及一次以上定位按钮
                                String substring;
                                if (content.contains(";")) {
                                    //点击2次及2次以上定位按钮
                                    substring = content.substring(content.lastIndexOf(";") + 1, content.length());
                                } else {//点击一次定位按钮
                                    substring = content;
                                }
                                if (substring.equals(Constant.LOCATION_INFO)) {//重复定位问题
                                    Toast.makeText(context, "定位点重复，请重新定位", Toast.LENGTH_SHORT).show();
                                } else {
                                    edittext.setText(edittext.getText().toString() + ";" + Constant.LOCATION_INFO);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "企业范围定位成功", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                edittext.setText(Constant.LOCATION_INFO);
                                notifyDataSetChanged();
                                Toast.makeText(context, "企业范围定位成功", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Gps正在定位，请稍等", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

            }
        }
    }

    //定义接口 fragment实现
    public interface setButtonVisibility {

        public void setButtonVisiable();

        public List<ComponyEditInfoModel> sendData(List<ComponyEditInfoModel> data, String whichActivity);

        public void setEditTextError(EditText et);

    }

    //延迟一秒 弹出toast
    private Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            if (isValidate) {
            } else {
                int index = (int) etContent.getTag();
                Toast.makeText(context, mData.get(index).getTitle() + errorContent, Toast.LENGTH_SHORT).show();
                Log.e(TAG, mData.get(index).getTitle() + errorContent);
            }
        }
    };

    /**
     * @param mData           展示的数据
     * @param context         上下文对象
     * @param listener        接口对象 将改变后的数据传回fragment
     * @param isBasicFragment 判断是哪个fragmeant
     * @param whichActivity   判断是哪个activity调用了fragment
     * @param canEdit         判断是edittext是否可以编辑
     * @desc safeFragment的构造方法
     * @date 2017/11/30 15:52
     */
    public AddEnterpriseAdapter(List<ComponyEditInfoModel> mData, Context context, setButtonVisibility listener, FragmentActivity activity, boolean isBasicFragment, String whichActivity, boolean canEdit) {
        if (mData != null) {
            this.mData = mData;
            this.mDataChange = new ArrayList<>();
            this.mDataChange.addAll(mData);
        } else {
            this.mData = new ArrayList<>();
        }
        this.context = context;
        this.listener = listener;
        this.activity = activity;
        this.whichActivity = whichActivity;
        this.canEdit = canEdit;
        inflater = LayoutInflater.from(context);
        this.isBasicFragment = isBasicFragment;
        dataEnterprise.add(new EnumModel("1", "微型"));
        dataEnterprise.add(new EnumModel("2", "小型"));
        dataEnterprise.add(new EnumModel("3", "中型"));
        dataEnterprise.add(new EnumModel("4", "大型"));
        dataSafeSet.add(new EnumModel("0", "未设置"));
        dataSafeSet.add(new EnumModel("1", "设置"));
        dataScope.add(new EnumModel("0", "否"));
        dataScope.add(new EnumModel("1", "是"));
    }

    /**
     * @param dataCommunity 前四个参数为从enum取回来的数据
     * @param mData         展示的参数
     * @param whichActivity 当前是哪个Activity开启了Fragment
     * @param canEdit       判断edittext是否可点击
     * @desc basicFragment的构造方法
     * @date 2017/11/30 15:56
     */
    public AddEnterpriseAdapter(List<EnumModel> dataCommunity, List<EnumModel> dataIndustryCateary, List<EnumModel> dataEconomic, List<EnumModel> dataAdiministrite, List<ComponyEditInfoModel> mData, Context context, setButtonVisibility listener, Activity activity, boolean isBasicFragment, String whichActivity, boolean canEdit) {
        if (mData != null) {
            this.mData = mData;
            this.mDataChange = new ArrayList<>();
            this.mDataChange.addAll(mData);
        } else {
            this.mData = new ArrayList<>();
        }
        if (dataAdiministrite != null) {
            this.dataAdiministrite = dataAdiministrite;
        } else {
            this.dataAdiministrite = new ArrayList<>();
        }
        if (dataIndustryCateary != null) {
            this.dataIndustryCateary = dataIndustryCateary;
        } else {
            this.dataIndustryCateary = new ArrayList<>();
        }
        if (dataEconomic != null) {
            this.dataEconomic = dataEconomic;
        } else {
            this.dataEconomic = new ArrayList<>();
        }
        if (dataCommunity != null) {
            this.dataCommunity = dataCommunity;
        } else {
            this.dataCommunity = new ArrayList<>();
        }
        this.context = context;
        this.listener = listener;
        this.activity = activity;
        inflater = LayoutInflater.from(context);
        this.isBasicFragment = isBasicFragment;
        this.whichActivity = whichActivity;
        this.canEdit = canEdit;
    }

    @Override
    public int getCount() {
        return this.mData == null ? 0 : this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mData == null ? null : this.mData.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 14 || position == 16) {
            return TYPE_IMAGE;
        } else {
            return TYPE_NO_IMAGE;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        if (isBasicFragment == true) {
            if (itemViewType == 1) {
                //basicfragment 且 类型等于1
                convertView = inflater.inflate(R.layout.fragment_homepage_compony_item2, parent, false);
                tvTitle = (TextView) convertView.findViewById(R.id.fragment_homepage_compony_item2_title);
                imgLocation = (ImageView) convertView.findViewById(R.id.fragment_homepage_compony_item2_location);
                etContent = (EditText) convertView.findViewById(R.id.fragment_homepage_compony_item2_content);
            } else {
                convertView = inflater.inflate(R.layout.fragment_homepage_compony_item, parent, false);
                tvTitle = (TextView) convertView.findViewById(R.id.fragment_homepage_compony_item_title);
                etContent = (EditText) convertView.findViewById(R.id.fragment_homepage_compony_item_content);
            }
            if (canEdit) {
                //给basic页面设置hint
                setBasicInfoHint(position);
            }
            tvTitle.setText(mData.get(position).getTitle());
            etContent.setText(mData.get(position).getContent());
        } else {
            convertView = inflater.inflate(R.layout.fragment_homepage_compony_item, parent, false);
            tvTitle = (TextView) convertView.findViewById(R.id.fragment_homepage_compony_item_title);
            etContent = (EditText) convertView.findViewById(R.id.fragment_homepage_compony_item_content);
            etContent.setTag(position);
            tvTitle.setText(mData.get(position).getTitle());
            etContent.setText(mData.get(position).getContent());
            if (canEdit) {
                //设置safe页面的hint
                Log.e(TAG, "getView: " + etContent.getText().toString() + "position" + position);
//                if ((!(etContent.getText().toString().equals("")) && position == 1) || (!(etContent.getText().toString().equals("")) && position == 5)) {
//                    haveContent = true;
//                    Log.e(TAG, "haveContent: true");
//                } else {
//                    Log.e(TAG, "haveContent: false");
//                    haveContent = false;
//                }
                setSafeInfoHint(position);
            }
        }


        //特定数据进行特定操作
        setSpecialLocationData(position);
        //Edittext输入的监听
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e(TAG, "beforeTextChanged: " + position + "count" + count);
                if (isBasicFragment) {
                    if (position != 15 && position != 8 && position != 10 && position != 11 && position != 13) {
                        etContent.setTag(position);
                    } else {

                    }
                } else {
                    etContent.setTag(position);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (mData.get(position).getTitle()) {
                    case "企业名称":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        }
                        break;
                    case "工商注册号":
                        componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                        mData.set(position, componyEditInfoModel);
                        mDataChange.set(position, componyEditInfoModel);
                        break;
                    case "组织机构代码":
                        if (TextUtils.isEmpty(s.toString())) {
                        } else {
                            if (!ValidateUtil.isNumberNine(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写9位组织机构代码";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }

                        break;
                    case "法定代表人":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        }
                        break;
                    case "联系电话":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                            if (!ValidateUtil.isMobileOrPhone(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写11位有效手机号";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "电子邮箱":
                        if (TextUtils.isEmpty(s.toString())) {

                        } else {
                            if (!ValidateUtil.isEmail(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写有效的邮箱地址";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "注册地址":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        }
                        break;
                    case "邮政编码":
                        if (TextUtils.isEmpty(s.toString())) {

                        } else {
                            if (!ValidateUtil.isNumberSix(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写六位邮政编码";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }

                        break;
                    case "经济类型代码":
                        for (int i = 0; i < dataEconomic.size(); i++) {
                            if (s.toString().equals(dataEconomic.get(i).getValue())) {
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), dataEconomic.get(i).getKey(), true);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }

                        break;
                    case "行政区划代码":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                            if (!ValidateUtil.isNumberSix(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写六位行政区划代码";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "行业类别代码":
                        for (int i = 0; i < dataIndustryCateary.size(); i++) {
                            if (s.toString().equals(dataIndustryCateary.get(i).getValue())) {
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), dataIndustryCateary.get(i).getKey(), true);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "企业行政隶属关系":
                        for (int i = 0; i < dataAdiministrite.size(); i++) {
                            if (s.toString().equals(dataAdiministrite.get(i).getValue())) {
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), dataAdiministrite.get(i).getKey(), true);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "经营范围":
                        componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                        mDataChange.set(position, componyEditInfoModel);
                        mData.set(position, componyEditInfoModel);
                        break;
                    case "经营状态":
                        if (s.toString().equals("有效")) {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), "1", true);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), "0", true);
                            mDataChange.set(position, componyEditInfoModel);
                        }
                        break;
                    case "所属社区":
                        for (int i = 0; i < dataCommunity.size(); i++) {
                            if (s.toString().equals(dataCommunity.get(i).getValue())) {
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), dataCommunity.get(i).getKey(), true);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "企业位置":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
//                            notifyDataSetChanged();
                        }
                        break;
                    case "企业范围":
                        componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                        mData.set(position, componyEditInfoModel);
                        mDataChange.set(position, componyEditInfoModel);
                        break;
                    case "主要负责人":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        }
                        break;
                    case "主要负责人移动电话":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
//                            View view = getView(position +1, null, null);
//                            EditText edittext = (EditText) view.findViewById(R.id.fragment_homepage_compony_item_content);
//                            edittext.setHint("sdasdad");
                            if (!ValidateUtil.isMobileOrPhone(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写11位有效手机号";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "主要负责人固定电话":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                            if (!ValidateUtil.isTelephone(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写固定电话";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "主要负责人邮箱":
                        if (TextUtils.isEmpty(s.toString())) {

                        } else {
                            if (!ValidateUtil.isEmail(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写有效的邮箱地址";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }

                        break;
                    case "安全负责人":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        }
                        break;
                    case "安全负责人移动电话":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                            if (!ValidateUtil.isMobileOrPhone(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写11位有效手机号";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "安全负责人固定电话":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                            if (!ValidateUtil.isTelephone(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写固定电话";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "安全负责人邮箱":
                        if (TextUtils.isEmpty(s.toString())) {

                        } else {
                            if (!ValidateUtil.isEmail(s.toString())) {
                                isValidate = false;
                                postDelay();
                                errorContent = "项请填写有效的邮箱地址";
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            } else {
                                isValidate = true;
                                postDelay();
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                                mData.set(position, componyEditInfoModel);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "安全机构设置情况":
                        for (int i = 0; i < dataSafeSet.size(); i++) {
                            if (s.toString().equals(dataSafeSet.get(i).getValue())) {
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), dataSafeSet.get(i).getKey(), true);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "从业人员数量":
                        componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                        mData.set(position, componyEditInfoModel);
                        mDataChange.set(position, componyEditInfoModel);
                        break;
                    case "特种作业人员数量":
                        componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                        mData.set(position, componyEditInfoModel);
                        mDataChange.set(position, componyEditInfoModel);
                        break;
                    case "专职安全生产管理人员数量":
                        componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                        mData.set(position, componyEditInfoModel);
                        mDataChange.set(position, componyEditInfoModel);
                        break;
                    case "专职应急管理人员数":
                        componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                        mData.set(position, componyEditInfoModel);
                        mDataChange.set(position, componyEditInfoModel);
                        break;
                    case "注册安全工程师人员数":
                        componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                        mData.set(position, componyEditInfoModel);
                        mDataChange.set(position, componyEditInfoModel);
                        break;
                    case "安全监管监察机构":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        }
                        break;
                    case "生产/经营地址":
                        if (TextUtils.isEmpty(s.toString())) {
                            postDelay();
                            errorContent = "项为必填项";
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), false);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        } else {
                            componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), s.toString(), true);
                            mData.set(position, componyEditInfoModel);
                            mDataChange.set(position, componyEditInfoModel);
                        }
                        break;
                    case "规模情况":
                        for (int i = 0; i < dataScope.size(); i++) {
                            if (s.toString().equals(dataScope.get(i).getValue())) {
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), dataScope.get(i).getKey(), true);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                    case "企业规模":
                        for (int i = 0; i < dataEnterprise.size(); i++) {
                            if (s.toString().equals(dataEnterprise.get(i).getValue())) {
                                componyEditInfoModel = new ComponyEditInfoModel(mData.get(position).getTitle(), dataEnterprise.get(i).getKey(), true);
                                mDataChange.set(position, componyEditInfoModel);
                            }
                        }
                        break;
                }
                AddEnterpriseAdapter.this.listener.sendData(mDataChange, whichActivity);
            }
        });

        return convertView;
    }

    /**
     * @param position 位置
     * @desc 根据是否可编辑分为两类 一类可编辑，但需要对特定位置进行特定操作 另一类为不可编辑，所有项都不能编辑
     * @date 2017/11/30 16:23
     */
    private void setSpecialLocationData(int position) {

        if ("经济类型代码".equals(tvTitle.getText().toString())) {
            //经济类型代码
            Log.e(TAG, "getView: " + position);
            for (int i = 0; i < dataEconomic.size(); i++) {
                if (dataEconomic.get(i).getKey().equals(mData.get(position).getContent())) {
                    etContent.setText(dataEconomic.get(i).getValue());
                    //将数据源转化为array
                    arrayEconomic = new String[dataEconomic.size()];
                    data2Array(dataEconomic, arrayEconomic);
                    break;
                }
            }
            //将edittext设为不可编辑
            etContent.setFocusable(false);
            etContent.setTag(position);
            etContent.setFocusableInTouchMode(false);
            //对特定位置的edittext的点击监听
            if (canEdit) {
                etContent.setOnClickListener(this);
            }
        } else if ("行业类别代码".equals(tvTitle.getText().toString())) {
            //行业类别代码
            for (int i = 0; i < dataIndustryCateary.size(); i++) {
                if (dataIndustryCateary.get(i).getKey().equals(mData.get(position).getContent())) {
                    etContent.setText(dataIndustryCateary.get(i).getValue());
                    //将edittext设为不可点击
                    etContent.setFocusable(false);
                    etContent.setTag(position);
                    etContent.setFocusableInTouchMode(false);
                    //将数据源转化为array
                    arrayIndustryCateary = new String[dataIndustryCateary.size()];
                    data2Array(dataIndustryCateary, arrayIndustryCateary);
                    //对特定位置的edittext的点击监听
                    if (canEdit) {
                        etContent.setOnClickListener(this);
                    }
                    break;
                }
            }

        } else if ("企业行政隶属关系".equals(tvTitle.getText().toString())) {
            //行政隶属关系
            for (int i = 0; i < dataAdiministrite.size(); i++) {
                if (dataAdiministrite.get(i).getKey().equals(mData.get(position).getContent())) {
                    etContent.setText(dataAdiministrite.get(i).getValue());
                    //将edittext设为不可点击
                    etContent.setFocusable(false);
                    etContent.setTag(position);
                    etContent.setFocusableInTouchMode(false);
                    //将数据源转化为array
                    arrayAdiministrite = new String[dataAdiministrite.size()];
                    data2Array(dataAdiministrite, arrayAdiministrite);
                    //对特定位置的edittext的点击监听
                    if (canEdit) {
                        etContent.setOnClickListener(this);
                    }
                    break;
                }
            }

        } else if ("所属社区".equals(tvTitle.getText().toString())) {
            //所属社区
            for (int i = 0; i < dataCommunity.size(); i++) {
                if (dataCommunity.get(i).getKey().equals(mData.get(position).getContent())) {
                    etContent.setText(dataCommunity.get(i).getValue());
                    //将edittext设为不可点击
                    etContent.setFocusable(false);
                    etContent.setTag(position);
                    etContent.setFocusableInTouchMode(false);
                    //将数据源转化为array
                    Log.e(TAG, "getView: " + dataCommunity.size() + "position" + position);
                    arrayCommunity = new String[dataCommunity.size()];
                    data2Array(dataCommunity, arrayCommunity);
                    //对特定位置的edittext的点击监听
                    if (canEdit) {
                        etContent.setOnClickListener(this);
                    }
                    break;
                }
            }

        } else if ("经营状态".equals(tvTitle.getText().toString())) {
            if ("0".equals(mData.get(position).getContent())) {
                etContent.setText("无效");
            } else {
                etContent.setText("有效");
            }
            //将edittext设为不可点击
            etContent.setFocusable(false);
            etContent.setTag(position);
            etContent.setFocusableInTouchMode(false);
            arrayOperatStatus = new String[]{"无效", "有效"};
            if (canEdit) {
                etContent.setOnClickListener(this);
            }

        } else if ("安全机构设置情况".equals(tvTitle.getText().toString())) {
            for (int i = 0; i < dataSafeSet.size(); i++) {
                if (dataSafeSet.get(i).getKey().equals(mData.get(position).getContent())) {
                    etContent.setText(dataSafeSet.get(i).getValue());
                    //将edittext设为不可点击
                    etContent.setFocusable(false);
                    etContent.setTag(position);
                    etContent.setFocusableInTouchMode(false);
                    arraySafeSet = new String[]{"未设置", "设置"};
                    if (canEdit) {
                        etContent.setOnClickListener(this);
                    }
                    break;
                }
            }
        } else if ("规模情况".equals(tvTitle.getText().toString())) {
            for (int i = 0; i < dataScope.size(); i++) {
                if (dataScope.get(i).getKey().equals(mData.get(position).getContent())) {
                    etContent.setText(dataScope.get(i).getValue());
                    //将edittext设为不可点击
                    etContent.setFocusable(false);
                    etContent.setTag(position);
                    etContent.setFocusableInTouchMode(false);
                    arrayScope = new String[]{"否", "是"};
                    if (canEdit) {
                        etContent.setOnClickListener(this);
                    }
                    break;
                }
            }
        } else if ("企业规模".equals(tvTitle.getText().toString())) {
            for (int i = 0; i < dataEnterprise.size(); i++) {
                if (dataEnterprise.get(i).getKey().equals(mData.get(position).getContent())) {
                    etContent.setText(dataEnterprise.get(i).getValue());
                    //将edittext设为不可点击
                    etContent.setFocusable(false);
                    etContent.setTag(position);
                    etContent.setFocusableInTouchMode(false);
                    arrayEnterprise = new String[]{"微型", "小型", "中型", "大型"};
                    if (canEdit) {
                        etContent.setOnClickListener(this);
                    }
                    break;
                }
            }
        } else if ("企业位置".equals(tvTitle.getText().toString())) {
            //将位置相关项全部设为不可编辑
            etContent.setFocusable(false);
            etContent.setTag(position);
            etContent.setFocusableInTouchMode(false);
            //设置定位图标的按钮的监听
            if (canEdit) {
                imgLocation.setOnClickListener(this);
            }
            imgLocation.setTag(position);
        } else if ("企业范围".equals(tvTitle.getText().toString())) {
            //将位置相关项全部设为不可编辑
            etContent.setFocusable(false);
            etContent.setTag(position);
            etContent.setFocusableInTouchMode(false);
            //设置定位图标的按钮的监听
            if (canEdit) {
                imgLocation.setOnClickListener(this);
            }
            imgLocation.setTag(position);
        }

    }

    private void setBasicInfoHint(int position) {
        switch (position) {
            case 0:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case 1:
                etContent.setHint("选填项");
                break;
            case 2:
                etContent.setHint("选填项 填写9位组织机构代码");
                break;
            case 3:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case 4:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项 填写11位有效手机号");
                break;
            case 5:
                etContent.setHint("选填项");
                break;
            case 6:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case 7:
                etContent.setHint("选填项 填写6位邮政编码");
                break;
            case 8:
                break;
            case 9:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项 填写6位行政区划代码");
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                etContent.setHint("选填项");
                break;
            case 13:
//                    etContent.setHint("必填项");
                break;
            case 14:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项 点击右侧图标定位");
                break;
            case 15:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case 16:
                etContent.setHint("选填项 点击右侧图标定位");
                break;
        }
    }

    private void setSafeInfoHint(int position) {
        Log.e(TAG, "setSafeInfoHint: " + position);
        switch (position) {
            case 0:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case 1:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项 填写11位有效手机号");
                break;
            case 2:
                etContent.setHint("选填项 固话格式 053212345678");
//                if (haveContent) {
//                    etContent.setHint("选填项 固话格式 053212345678");
//                } else {
//                    etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
//                    etContent.setHint("必填项 固话格式 053212345678");
//                }
                break;
            case 3:
                etContent.setHint("选填项");
                break;
            case 4:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case 5:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项 填写11位有效手机号");
                break;
            case 6:
                etContent.setHint("选填项 固话格式 053212345678");
//                if (haveContent) {
//                    etContent.setHint("选填项 固话格式 053212345678");
//                } else {
//                    etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
//                    etContent.setHint("必填项 固话格式 053212345678");
//                }
                break;
            case 7:
                etContent.setHint("选填项");
                break;
            case 8:
                break;
            case 9:
                etContent.setHint("选填项");
                break;
            case 10:
                etContent.setHint("选填项");
                break;
            case 11:
                etContent.setHint("选填项");
                break;
            case 12:
                etContent.setHint("选填项");
                break;
            case 13:
                etContent.setHint("选填项");
                break;
            case 14:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项 ");
                break;
            case 15:
                etContent.setHintTextColor(activity.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case 16:
//                    etContent.setHint("选填项 点击右侧图标自动填写");
                break;
            case 17:
//                    etContent.setHint("选填项 点击右侧图标自动填写");
                break;
        }
    }

    /**
     * @param data  待转化数据源
     * @param array 转化完的数组
     * @desc data2Array 将数据源转化为array
     * @date 2017/11/27 14:56
     */
    private void data2Array(List<EnumModel> data, String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = data.get(i).getValue();
        }
    }

    private void postDelay() {
        if (delayRun != null) {
            //每次editText有变化的时候，则移除上次发出的延迟线程
            handler.removeCallbacks(delayRun);
        }
        //延迟800ms，如果不再输入字符，则执行该线程的run方法
        handler.postDelayed(delayRun, 1000);
    }

    private void showChooseDialog(final String[] arry, final EditText epointName, final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);// 自定义对话框
        int which = 0;
        for (int i = 0; i < arry.length; i++) {
            if (epointName.getText().toString().equals(arry[i])) {
                which = i;
                break;
            }
        }
        builder.setSingleChoiceItems(arry, which, new DialogInterface.OnClickListener() {// 0默认的选中
            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                epointName.setText(arry[which]);
                mData.set(position, new ComponyEditInfoModel(mData.get(position).getTitle(), epointName.getText().toString(), true));
                dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }

}
