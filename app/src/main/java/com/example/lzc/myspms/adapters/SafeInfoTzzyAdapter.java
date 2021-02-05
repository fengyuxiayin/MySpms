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
import com.example.lzc.myspms.models.JsonModel;
import com.example.lzc.myspms.models.TzzyryModel;
import com.example.lzc.myspms.utils.DialogUtil;
import com.example.lzc.myspms.utils.ValidateUtil;

import java.util.Calendar;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class SafeInfoTzzyAdapter extends BaseAdapter {
    public static final String TAG = SafeInfoTzzyAdapter.class.getSimpleName();
    private List<TzzyryModel> data;
    private LayoutInflater inflater;
    private Context context;

    public SafeInfoTzzyAdapter(List<TzzyryModel> data, Context context) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        Log.e(TAG, "getCount: " + data.size());
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        Log.e(TAG, "getCount: " + data.size());
        return data == null ? data.get(0) : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.popup_compony_safe_info_tzzyry_item, parent, false);
        final EditText etName = (EditText) view.findViewById(R.id.popup_compony_safe_info_tzzyry_item_et_name);
        final EditText etNumber = (EditText) view.findViewById(R.id.popup_compony_safe_info_tzzyry_item_et_number);
        final EditText etZjh = (EditText) view.findViewById(R.id.popup_compony_safe_info_tzzyry_item_zjh);
        final EditText etXzsj = (EditText) view.findViewById(R.id.popup_compony_safe_info_tzzyry_item_et_xzsj);
        final EditText etFssj = (EditText) view.findViewById(R.id.popup_compony_safe_info_tzzyry_item_et_fssj);
        final EditText etYxsj = (EditText) view.findViewById(R.id.popup_compony_safe_info_tzzyry_item_et_yxsj);
        final ImageView imgDelete = (ImageView) view.findViewById(R.id.popup_compony_safe_info_tzzyry_item_img_operate);
        etZjh.setText(data.get(position).getZjh()==null?"":data.get(position).getZjh()+"");
        etXzsj.setText(data.get(position).getXzsj()==null?"":data.get(position).getXzsj()+"");
        etFssj.setText(data.get(position).getZjfssj()==null?"":data.get(position).getZjfssj());
        etYxsj.setText(data.get(position).getZjyxsj()==null?"":data.get(position).getZjyxsj());
        etXzsj.setFocusable(false);
        etXzsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(etXzsj);
            }
        });
        etFssj.setFocusable(false);
        etFssj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(etFssj);
            }
        });
        etYxsj.setFocusable(false);
        etYxsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(etYxsj);
            }
        });
        Log.e(TAG, "getView: " + data.get(position).getRymc() + "");
        etName.setText(data.get(position).getRymc()==null?"":data.get(position).getRymc() + "");
        etNumber.setText(data.get(position).getLxdh()==null?"":data.get(position).getLxdh() + "");
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getStatus()!=null){
                    Log.e(TAG, "onClick: "+data.get(position).getStatus() );
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
                data.get(position).setRymc(etName.getText().toString());
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
                data.get(position).setLxdh(etNumber.getText().toString());
            }
        });
        etZjh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.get(position).setZjh(etZjh.getText().toString());
            }
        });
        etXzsj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.get(position).setXzsj(etXzsj.getText().toString());
            }
        });
        etFssj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.get(position).setZjfssj(etFssj.getText().toString());
            }
        });
        etYxsj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data.get(position).setZjyxsj(etYxsj.getText().toString());
            }
        });
        return view;
    }

    /**
     * @param data 最新的数据源
     * @desc 更新数据源
     * @date 2018/5/9 14:39
     */
    public void notifyData(TzzyryModel data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    /**
     * @desc 将data数据返到fragment中去
     * @date 2018/5/9 14:37
     */
    public List<TzzyryModel> gettzzyryData() {
        for (int i = 0; i < data.size(); i++) {
            TzzyryModel tzzyryModel = data.get(i);
            if (!ValidateUtil.isPhone(tzzyryModel.getLxdh())) {
                return null;
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
                edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
