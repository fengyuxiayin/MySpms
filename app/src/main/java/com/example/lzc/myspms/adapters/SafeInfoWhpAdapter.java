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
import android.widget.ImageView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.WhpJsonModel;

import java.util.Calendar;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class SafeInfoWhpAdapter extends BaseAdapter {
    public static final String TAG = SafeInfoWhpAdapter.class.getSimpleName();
    private List<WhpJsonModel> data;
    private LayoutInflater inflater;
    private Context context;
    private List<EnumModel> dataMeasureUnitType;

    public SafeInfoWhpAdapter(List<WhpJsonModel> data, Context context, List<EnumModel> dataMeasureUnitType) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.dataMeasureUnitType = dataMeasureUnitType;
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
        View view = inflater.inflate(R.layout.popup_compony_info_whp_item, parent, false);
         final EditText etMc = (EditText) view.findViewById(R.id.popup_compony_info_whp_item_mc);
         final EditText etSl = (EditText) view.findViewById(R.id.popup_compony_info_whp_item_sl);
         final EditText etDw = (EditText) view.findViewById(R.id.popup_compony_info_whp_item_dw);
         ImageView imgDelete = (ImageView) view.findViewById(R.id.popup_compony_info_whp_item_operate);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        etMc.setText(data.get(position).getWhpmc());
        etSl.setText((data.get(position).getWhpsl()==null?"":data.get(position).getWhpsl()+""));

        for (int i = 0; i < dataMeasureUnitType.size(); i++) {
            if (dataMeasureUnitType.get(i).getKey().equals(data.get(position).getWhpdw()+"")) {
                etDw.setText(dataMeasureUnitType.get(i).getValue());
                break;
            }
        }
       etDw.setFocusable(false);
        etDw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(dataMeasureUnitType,etDw);
            }
        });
        etDw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                WhpJsonModel whpJsonModel = data.get(position);
                for (int i = 0; i < dataMeasureUnitType.size(); i++) {
                    if (dataMeasureUnitType.get(i).getValue().equals(etDw.getText().toString().trim())) {
                        whpJsonModel.setWhpdw(Integer.valueOf(dataMeasureUnitType.get(i).getKey()));
                        break;
                    }
                }
                data.set(position,whpJsonModel);
            }
        });
        etMc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                WhpJsonModel whpJsonModel = data.get(position);
                whpJsonModel.setWhpmc(etMc.getText().toString().trim());
                data.set(position,whpJsonModel);
            }
        });
        etSl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                WhpJsonModel whpJsonModel = data.get(position);
                if (etSl.getText().toString().length()>0) {
                    whpJsonModel.setWhpsl(Double.valueOf(etSl.getText().toString().trim()));
                }else{

                }
                data.set(position,whpJsonModel);
            }
        });
        return view;
    }

    /**
     * @param data 最新的数据源
     * @desc 更新数据源
     * @date 2018/5/9 14:39
     */
    public void notifyData(WhpJsonModel data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    /**
     * @desc 将data数据返到fragment中去
     * @date 2018/5/9 14:37
     */
    public List<WhpJsonModel> getwhpData() {

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
                edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
    private void showChooseDialog(final List<EnumModel> data, final EditText epointName) {
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
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
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
