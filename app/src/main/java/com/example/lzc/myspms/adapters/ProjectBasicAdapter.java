package com.example.lzc.myspms.adapters;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.models.ComponyAllInfo;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.TreeDataArray;
import com.example.lzc.myspms.models.TreeDataModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LZC on 2017/11/7.
 */
public class ProjectBasicAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "ProjectBasicAdapter";
    private Context context;
    private LayoutInflater inflater;
    //传进来并显示在界面上的数据源
    private List<ComponyAllInfo> data;
    //返回并提交的数据源
    private List<ComponyAllInfo> changeData;
    private TextView tvTitle;
    private EditText etContent;
    //一级安全要素数组和对应的数据源
    private String[] arrayOne = new String[5];
    private List<TreeDataArray> dataOne = new ArrayList<>();
    //二级安全要素数组和对应的数据源
    private String[] arrayTwo;
    private List<TreeDataArray> dataTwo = new ArrayList<>();
    //三级安全要素数组和对应的数据源
    private String[] arrayThree;
    private List<TreeDataArray> dataThree = new ArrayList<>();
    //四级安全要素数组和对应的数据源
    private String[] arrayFour;
    private List<TreeDataArray> dataFour = new ArrayList<>();
    private List<TreeDataArray> dataFive = new ArrayList<>();
    //应该显示showPosition项和之前的项
    private int showposition = 0;
    //判断是不是显示显示下一层的标志 如果为true那么就不显示这一层，反之...
    private boolean haveNumber;
    //存储havenumber的值
    private List<Boolean> dataHaveNumber = new ArrayList<>();
    //AddProjectActivity对象
    private AddProjectActivity addProjectActivity;
    private String startClass;
    private Gson gson;
    //
    private List<List<TreeDataArray>> list = new ArrayList<>();
    //
    private boolean isFirstIn = true;

    public ProjectBasicAdapter(List<ComponyAllInfo> data, Context context,AddProjectActivity addProjectActivity,String startClass) {
        if (data != null) {
            this.data = data;
            this.changeData = new ArrayList<>();
            this.changeData.addAll(data);
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
        this.addProjectActivity = addProjectActivity;
        inflater = LayoutInflater.from(context);
        this.startClass = startClass;
    }


    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.fragment_project_basic_item, parent, false);
        tvTitle = (TextView) convertView.findViewById(R.id.fragment_project_basic_item_title);
        etContent = (EditText) convertView.findViewById(R.id.fragment_project_basic_item_content);
        tvTitle.setText(data.get(position).getTitle());
        //edittext设置tag 在后续的点击事件中使用
        etContent.setTag(position);
        //改变showposition的值 因为如果0-3项有数据，那么就显示到有数据的下一层
        if (position==0) {
            switch (changeData.get(position).getContent()) {
                case "359":
                    etContent.setText("设备类");
                    break;
                case "56":
                    etContent.setText("现场类");
                    break;
                case "1":
                    etContent.setText("资料类");
                    break;
                case "167":
                    etContent.setText("消防类");
                    break;
                case "76":
                    etContent.setText("特种设备类");
                    break;
            }
        }
        //给dataHaveNumber赋初值
        for (int i = dataHaveNumber.size(); i < 5; i++) {
            dataHaveNumber.add(false);
        }
        if (isFirstIn) {
            if(position>0&&position<4){
                //判断前四项是否有合法值
                if (!data.get(position).getContent().equals("")&&!data.get(position).getContent().equals("全部")&&!data.get(position).getContent().equals("-1")) {
                    showposition = position;
                    if (position == 1) {
                        setZeroToThreeName(position, etContent,dataTwo);
                    }
                    if (position == 2) {
                        setZeroToThreeName(position, etContent,dataThree);
                    }
                    if (position == 3) {
                        setZeroToThreeName(position, etContent,dataFour);
                    }
                }
            }
            else if(position>3){
                etContent.setText(data.get(position).getContent());
            }
        }else{
            if (position>0) {
                etContent.setText(data.get(position).getContent());
            }
        }
        //设置edittext的hint 隐藏指定条目 设置指定条目只可点击，不可编辑，并设置点击监听
        setHintAndHideItem(position, convertView);


        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isFirstIn = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                int position = (int) etContent.getTag();
                Log.e(TAG, "afterTextChanged: " + s.toString());
                switch (data.get(position).getTitle()) {
                    case "一级安全要素":
                        break;
                    case "二级安全要素":
                        if (s.toString().equals("全部")) {
                        } else {
                        }
                        break;
                    case "三级安全要素":
                        if (s.toString().equals("全部")) {
                        } else {
                        }
                        break;
                    case "四级安全要素":
                        if (s.toString().equals("全部")) {
                        } else {
                            Toast.makeText(context, "最后一层了", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "项目名称":
                        changeDataSource(s, position);
                        break;
                    case "所属企业":
                        break;
                    case "项目编码":
                        changeDataSource(s, position);
                        break;
                    case "所属部门":
                        changeDataSource(s, position);
                        break;
                    case "使用地点":
                        changeDataSource(s, position);
                        break;
                    case "设备品牌":
                        changeDataSource(s, position);
                        break;
                    case "设备型号":
                        changeDataSource(s, position);
                        break;
                    case "额定电压":
                        changeDataSource(s, position);
                        break;
                    case "功率消耗":
                        changeDataSource(s, position);
                        break;
                    case "设备净重":
                        changeDataSource(s, position);
                        break;
                    case "生产日期":
                        changeDataSource(s, position);
                        break;
                    case "启用日期":
                        changeDataSource(s, position);
                        break;
                    case "保修日期":
                        changeDataSource(s, position);
                        break;
                    case "建档日期":
                        changeDataSource(s, position);
                        break;

                }
                addProjectActivity.getBasicData(changeData);
            }
        });
        return convertView;
    }

    private void setZeroToThreeName(final int position, final EditText etContent, final List<TreeDataArray> treeData) {
        Log.e(TAG, "setZeroToThreeName: changeData.get(position).getContent()+\"\""+changeData.get(position).getContent()+"" );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseSafetyHazard/getTreeGrid")
                .addParams("id", changeData.get(position-1).getContent()+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                        NetUtil.errorTip(context,e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: "+response );
                        gson = new Gson();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            TreeDataModel treeDataModel;
                            //判断response有没有数据 没有数据时response = []
                            if (jsonArray.length() > 0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    treeDataModel = gson.fromJson(jsonArray.get(i).toString(), TreeDataModel.class);
                                    //判断这俩数据相等，如果想等就改变数据源并赋值
                                    //给下一层的data和array赋值
                                    treeData.add(new TreeDataArray(treeDataModel.getId(), treeDataModel.getName()));
                                    if ((treeDataModel.getId()+"").equals(changeData.get(position).getContent())) {
                                        etContent.setText(treeDataModel.getName());
                                        data.set(position,new ComponyAllInfo(data.get(position).getTitle(),treeDataModel.getName(),true));
                                    }
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * @desc 将改变后的数据（0-3项除外，在showChooseDialog里面设置到数据源中了）设置到数据源中
     * @param s        Edittext中的数据
     * @param position 点击的那个条目
     * @date 2017/12/11 14:25
     */
    private void changeDataSource(Editable s, int position) {
        ComponyAllInfo componyAllInfoModel;
        componyAllInfoModel = new ComponyAllInfo(data.get(position).getTitle(), s.toString(), true);
        data.set(position, componyAllInfoModel);
        changeData.set(position, componyAllInfoModel);
    }

    /**
     * @param position    条目位置
     * @param convertView 条目
     * @desc 设置edittext的hint 隐藏指定条目 设置指定条目只可点击，不可编辑，并设置点击监听
     * @date 2017/12/7 16:31
     */
    private void setHintAndHideItem(int position, View convertView) {
        Log.e(TAG, "setHintAndHideItem: " + position+"number"+dataHaveNumber.size()+"showposition "+showposition);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.fragment_project_basic_ll);
        switch (data.get(position).getTitle()) {
            case "一级安全要素":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                etContent.setOnClickListener(this);
                etContent.setHintTextColor(context.getResources().getColor(R.color.hintRed));
                etContent.setHint("一级安全要素为必填项 点击选取");
                break;
            case "二级安全要素":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                etContent.setOnClickListener(this);
                if (showposition < position) {
                    linearLayout.setVisibility(View.GONE);
                } else {
                    if (dataHaveNumber.get(position)) {
                        linearLayout.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(context, "我显示了第" + position + "项", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case "三级安全要素":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                etContent.setOnClickListener(this);
                if (showposition < position) {
                    linearLayout.setVisibility(View.GONE);
                } else {
                    if (dataHaveNumber.get(position)) {
                        linearLayout.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(context, "我显示了第" + position + "项", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case "四级安全要素":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                etContent.setOnClickListener(this);
                if (showposition < position) {
                    linearLayout.setVisibility(View.GONE);
                } else {
                    if (dataHaveNumber.get(position)) {
                        linearLayout.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(context, "我显示了第" + position + "项", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case "项目名称":
                etContent.setHintTextColor(context.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case "所属企业":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                break;
            case "项目编码":
                etContent.setHintTextColor(context.getResources().getColor(R.color.hintRed));
                etContent.setHint("必填项");
                break;
            case "所属部门":
                etContent.setHint("选填项");
                break;
            case "使用地点":
                etContent.setHint("选填项");
                break;
            case "设备品牌":
                etContent.setHint("选填项");
                break;
            case "设备型号":
                etContent.setHint("选填项");
                break;
            case "额定电压":
                etContent.setHint("选填项");
                break;
            case "功率消耗":
                etContent.setHint("选填项");
                break;
            case "设备净重":
                etContent.setHint("选填项");
                break;
            case "生产日期":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                etContent.setHint("选填项 点击选取日期");
                etContent.setOnClickListener(this);
                break;
            case "启用日期":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                etContent.setHint("选填项 点击选取日期");
                etContent.setOnClickListener(this);
                break;
            case "保修日期":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                etContent.setHint("选填项 点击选取日期");
                etContent.setOnClickListener(this);
                break;
            case "建档日期":
                etContent.setFocusable(false);
                etContent.setFocusableInTouchMode(false);
                etContent.setHint("选填项 点击选取日期");
                etContent.setOnClickListener(this);
                break;

        }
    }

    /**
     * @param arry      向用户展示的内容
     * @param data      这一层的数据
     * @param dataNext  下一层的数据
     * @param etContent 被点击项的edittext
     * @param position  被点击项的位置
     * @desc 显示菜单向用户提供选择 设置enittext的内容 显示下一级菜单
     * @date 2017/12/7 17:19
     */
    private void showChooseDialog(final String[] arry, final List<TreeDataArray> data, final List<TreeDataArray> dataNext, final EditText etContent, final int position, final boolean isYjys) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);// 自定义对话框
        int whichIsChecked = 0;
        //根据edittext内容来选择显示array中的第几条数据
        for (int i = 0; i < arry.length; i++) {
            if (etContent.getText().toString().equals(arry[i])) {
                whichIsChecked = i;
            }
        }
        builder.setSingleChoiceItems(arry, whichIsChecked, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int which) {
                //每次重新点击都要清空一下data,不然所有数据都会添加
                dataNext.clear();
                //根据点击的选项加载下一层的数据
                gson = new Gson();
                //
                int changeWhich = which;
                if (!isYjys) {
                    changeWhich = which-1;
                }
                //点击除了第一项全部项的处理
                if (which == 0&&!isYjys) {
                    ProjectBasicAdapter.this.data.set(position, new ComponyAllInfo(ProjectBasicAdapter.this.data.get(position).getTitle(), arry[0]));
                    ProjectBasicAdapter.this.changeData.set(position,new ComponyAllInfo(ProjectBasicAdapter.this.changeData.get(position).getTitle(),-1+""));
                    etContent.setText(arry[0]);
                    showposition = position;
                    notifyDataSetChanged();
                }
                //其他可能的处理
                else{
                    //改变数据源中的数据
                    ProjectBasicAdapter.this.data.set(position, new ComponyAllInfo(ProjectBasicAdapter.this.data.get(position).getTitle(), arry[which],true));
                    ProjectBasicAdapter.this.changeData.set(position,new ComponyAllInfo(ProjectBasicAdapter.this.changeData.get(position).getTitle(),data.get(changeWhich).getKey()+"",true));
                    getTreeData(which, changeWhich);
                }

                dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
            }

            private void getTreeData(final int which, int changeWhich) {
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL + "/baseSafetyHazard/getTreeGrid")
                        .addParams("id", data.get(changeWhich).getKey() + "")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                                NetUtil.errorTip(context,e.getMessage());
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
                                                dataNext.add(new TreeDataArray(treeDataModel.getId(), treeDataModel.getName()));
                                                etContent.setText(arry[which]);
                                                count++;
                                            } else {
                                            }
                                        }
                                        etContent.setText(arry[which]);
                                    } else {
                                        etContent.setText(arry[which]);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (count>0) {
                                    Log.e(TAG, "onResponse: count"+count );
                                    dataHaveNumber.set(position+1,false);
                                    showposition = position+1;
                                }else{
                                    Log.e(TAG, "onResponse: count"+count );
                                    showposition = position;
                                    dataHaveNumber.set(position+1,true);
                                }
                                notifyDataSetChanged();
                            }

                        });
            }
        });
        builder.show();// 让弹出框显示
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (data.get(position).getTitle()) {
            case "一级安全要素":
                arrayOne[0] = "设备类";
                arrayOne[1] = "现场类";
                arrayOne[2] = "资料类";
                arrayOne[3] = "消防类";
                arrayOne[4] = "特种设备类";
                dataOne.add(new TreeDataArray(359, "设备类"));
                dataOne.add(new TreeDataArray(56, "现场类"));
                dataOne.add(new TreeDataArray(1, "资料类"));
                dataOne.add(new TreeDataArray(167, "消防类"));
                dataOne.add(new TreeDataArray(76, "特种设备类"));
                //把原先的下层数据改为全部，欺骗作用
                data.set(position + 1, new ComponyAllInfo(data.get(position + 1).getTitle(), "全部"));
                changeData.set(position + 1, new ComponyAllInfo(changeData.get(position + 1).getTitle(), "-1"));
                data.set(position + 2, new ComponyAllInfo(data.get(position + 2).getTitle(), "全部"));
                changeData.set(position + 2, new ComponyAllInfo(changeData.get(position + 2).getTitle(), "-1"));
                data.set(position + 3, new ComponyAllInfo(data.get(position + 3).getTitle(), "全部"));
                changeData.set(position + 3, new ComponyAllInfo(changeData.get(position + 3).getTitle(), "-1"));
                showChooseDialog(arrayOne, dataOne, dataTwo, (EditText) v, position,true);
                break;
            case "二级安全要素":
                arrayTwo = new String[dataTwo.size() + 1];
                arrayTwo[0] = "全部";
                //给arrayTwo赋值
                for (int i = 0; i < dataTwo.size(); i++) {
                    arrayTwo[i + 1] = dataTwo.get(i).getValue();
                }
                data.set(position + 1, new ComponyAllInfo(data.get(position + 1).getTitle(), "全部"));
                changeData.set(position + 1, new ComponyAllInfo(changeData.get(position + 1).getTitle(), "-1"));
                data.set(position + 2, new ComponyAllInfo(data.get(position + 2).getTitle(), "全部"));
                changeData.set(position + 2, new ComponyAllInfo(changeData.get(position + 2).getTitle(), "-1"));
                showChooseDialog(arrayTwo, dataTwo, dataThree, (EditText) v, position,false);
                break;
            case "三级安全要素":
                arrayThree = new String[dataThree.size() + 1];
                arrayThree[0] = "全部";
                //给arrayTwo赋值
                for (int i = 0; i < dataThree.size(); i++) {
                    arrayThree[i + 1] = dataThree.get(i).getValue();
                }
                data.set(position + 1, new ComponyAllInfo(data.get(position + 1).getTitle(), "全部"));
                changeData.set(position + 1, new ComponyAllInfo(changeData.get(position + 1).getTitle(), "-1"));
                showChooseDialog(arrayThree, dataThree, dataFour, (EditText) v, position,false);
                break;
            case "四级安全要素":
                arrayFour = new String[dataFour.size() + 1];
                arrayFour[0] = "全部";
                //给arrayTwo赋值
                for (int i = 0; i < dataFour.size(); i++) {
                    arrayFour[i + 1] = dataFour.get(i).getValue();
                }
                showChooseDialog(arrayFour, dataFour, dataFive, (EditText) v, position,false);
                break;
            case "生产日期":
                setDate((EditText) v);
                break;
            case "启用日期":
                setDate((EditText) v);
                break;
            case "保修日期":
                setDate((EditText) v);
                break;
            case "建档日期":
                setDate((EditText) v);
                break;
        }
    }

    /**
     * @desc 显示日历，并将选择的时间变为指定格式显示在Edittext上
     * @param edittext 需要设置显示内容的Edittext
     * @date 2017/12/11 14:27
     */
    private void setDate(final EditText edittext) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(year, monthOfYear, dayOfMonth);
                edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
