package com.example.lzc.myspms.adapters;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.homepageactivitys.AddEnterpriseSimpleActivity;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.TzzyryModel;
import com.example.lzc.myspms.models.WhpJsonModel;
import com.example.lzc.myspms.models.ZsryJsonModel;
import com.example.lzc.myspms.utils.DialogUtil;
import com.example.lzc.myspms.utils.ValidateUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class SafeInfoZsryAdapter extends BaseAdapter {
    public static final String TAG = SafeInfoZsryAdapter.class.getSimpleName();
    private List<ZsryJsonModel> data;
    private LayoutInflater inflater;
    private Context context;
    private List<EnumModel> dataStructure;

    public SafeInfoZsryAdapter(List<ZsryJsonModel> data, Context context, List<EnumModel> dataStructure) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.dataStructure = dataStructure;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? data.get(0) : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.popup_compony_safe_info_zsry_item, parent, false);
        final EditText etName = (EditText) view.findViewById(R.id.popup_compony_safe_info_zsry_item_et_name);
        final EditText etNumber = (EditText) view.findViewById(R.id.popup_compony_safe_info_zsry_item_et_number);
        final EditText etJzjg= (EditText) view.findViewById(R.id.popup_compony_safe_info_zsry_item_jzjg);
        final EditText etJzmj = (EditText) view.findViewById(R.id.popup_compony_safe_info_zsry_item_et_jzmj);
        final EditText etJzsj = (EditText) view.findViewById(R.id.popup_compony_safe_info_zsry_item_et_jzsj);
        final EditText etZsrs = (EditText) view.findViewById(R.id.popup_compony_safe_info_zsry_item_et_zsrs);
        final ImageView imgDelete = (ImageView) view.findViewById(R.id.popup_compony_safe_info_zsry_item_img_operate);
        etName.setText(data.get(position).getGlry()==null?"":data.get(position).getGlry()+"");
        etNumber.setText(data.get(position).getGlrylxdh()==null?"":data.get(position).getGlrylxdh()+"");
        for (int i = 0; i < dataStructure.size(); i++) {
            if (dataStructure.get(i).getKey().equals(data.get(position).getJzjg()+"")) {
                etJzjg.setText(dataStructure.get(i).getValue());
                break;
            }
        }
        etJzmj.setText(data.get(position).getJzmj()==null?"":data.get(position).getJzmj()+"");
        etJzsj.setText(data.get(position).getJzsj()==null?"":data.get(position).getJzsj()+"");
        etZsrs.setText(data.get(position).getZsrs()==null?"":data.get(position).getZsrs()+"");
        etJzsj.setFocusable(false);
        etJzsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(etJzsj);
            }
        });
        etJzjg.setFocusable(false);
        etJzjg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(dataStructure,etJzjg,"");
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position)!=null){
                    if (data.get(position).getStatus()==2) {
                        return;
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);// 自定义对话框
                builder.setTitle("提示")
                        .setMessage("是否删除此条信息")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (data.get(position).getId()!=null) {
                                    if (data.get(position).getStatus()==1) {
                                        data.get(position).setStatus(2);
                                        Log.e(TAG, "onClick: " );
                                        imgDelete.setImageResource(R.mipmap.deleted);
                                    }
                                }else{
                                    data.remove(position);
                                    notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();// 让弹出框显示
            }

        });
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.get(position).setGlry(etName.getText().toString());
            }
        });
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.get(position).setGlrylxdh(etNumber.getText().toString());
            }
        });
        etJzmj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etJzmj.getText().toString().length()>0) {
                    data.get(position).setJzmj(Double.valueOf(etJzmj.getText().toString()));
                }else{
                    data.get(position).setJzmj(0.0);
                }
            }
        });
        etZsrs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(etZsrs.getText().toString().length()>0){
                    data.get(position).setZsrs(Integer.valueOf(etZsrs.getText().toString()));
                }else{
                    data.get(position).setZsrs(0);
                }
            }
        });
        etJzjg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for (int i = 0; i < dataStructure.size(); i++) {
                    if (dataStructure.get(i).getValue().equals(etJzjg.getText().toString())) {
                        data.get(position).setJzjg(Integer.valueOf(dataStructure.get(i).getKey()));
                        break;
                    }
                }
            }
        });
        etJzsj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.get(position).setJzsj(etJzsj.getText().toString());
            }
        });
        return view;
    }

    /**
     * @param data 最新的数据源
     * @desc 更新数据源
     * @date 2018/5/9 14:39
     */
    public void notifyData(ZsryJsonModel data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    /**
     * @desc 将data数据返到fragment中去
     * @date 2018/5/9 14:37
     */
    public List<ZsryJsonModel> getZsryData() {
        for (int i = 0; i < data.size(); i++) {
            ZsryJsonModel zsryJsonModel = data.get(i);
            if (zsryJsonModel.getGlrylxdh().length()>0) {
                if (!ValidateUtil.isPhone(zsryJsonModel.getGlrylxdh())) {
                    Toast.makeText(context, "请检查手机号格式", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
            if (zsryJsonModel.getJzmj()!=null) {
                if (!ValidateUtil.isAllNumber(String.valueOf(zsryJsonModel.getJzmj()))) {
                    Toast.makeText(context, "建筑面积只能填写数字", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
            if (zsryJsonModel.getZsrs()!=null) {
                if (!ValidateUtil.isAllNumber(String.valueOf(zsryJsonModel.getZsrs()))) {
                    Toast.makeText(context, "住宿人数只能填写数字", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }
        return data;
    }

    /**
     * @param edittext 需要设置显示内容的Edittext
     * @desc 显示日历，并将选择的时间变为指定格式显示在Edittext上
     * @date 2017/12/11 14:27
     */
    private void setDate(final EditText edittext) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(year, monthOfYear, dayOfMonth);
                edittext.setText(DateFormat.format("yyyy", calendar));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        DatePicker dp = findDatePicker((ViewGroup) dialog.getWindow().getDecorView());
        if (dp != null) {
            ((ViewGroup)((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
            ((ViewGroup)((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(1).setVisibility(View.GONE);
        }
    }
    /**
     *
     *@desc 找到datepicker的子控件
     *@param group
     *@date 2018/5/25 13:57
     */
    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }
    /**
     * @param epointName 显示内容的edittext
     * @param data       dialog显示的内容
     * @param methodName 判断是哪个Edittext
     * @desc 点击对应项弹出dialog供用户选择
     * @date 2018/2/21 11:14
     */
    private void showChooseDialog(final List<EnumModel> data, final EditText epointName, final String methodName) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);// 自定义对话框
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
