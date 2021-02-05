package com.example.lzc.myspms.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
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
import com.example.lzc.myspms.models.JsonModel;
import com.example.lzc.myspms.models.MyInfoModel;
import com.example.lzc.myspms.utils.ValidateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class SafeInfoStaffAdapter extends BaseAdapter {
    public static final String TAG = SafeInfoStaffAdapter.class.getSimpleName();
    private List<JsonModel> data;
    private LayoutInflater inflater;
    private Context context;

    public SafeInfoStaffAdapter(List<JsonModel> data, Context context) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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
        View view = inflater.inflate(R.layout.popup_compony_safe_info_staff_item, parent, false);
        final EditText etName = (EditText) view.findViewById(R.id.popup_compony_safe_info_staff_item_et_name);
        final EditText etNumber = (EditText) view.findViewById(R.id.popup_compony_safe_info_staff_item_et_number);
        final ImageView imgDelete = (ImageView) view.findViewById(R.id.popup_compony_safe_info_staff_item_img_operate);
        etName.setText(data.get(position).getRymc()==null?"":data.get(position).getRymc() + "");
        etNumber.setText(data.get(position).getLxdh()==null?"":data.get(position).getLxdh() + "");
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getStatus()!=null){
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
                JsonModel jsonModel = data.get(position);
                jsonModel.setRymc(etName.getText().toString());
                data.set(position, jsonModel);
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
                JsonModel jsonModel = data.get(position);
                jsonModel.setLxdh(etNumber.getText().toString());
                data.set(position, jsonModel);

            }
        });
        return view;
    }

    /**
     * @param data 最新的数据源
     * @desc 更新数据源
     * @date 2018/5/9 14:39
     */
    public void notifyData(JsonModel data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    /**
     * @desc 将data数据返到fragment中去
     * @date 2018/5/9 14:37
     */
    public List<JsonModel> getStaffData() {
        for (int i = 0; i < data.size(); i++) {
            JsonModel jsonModel = data.get(i);
            if (!ValidateUtil.isPhone(jsonModel.getLxdh())) {
                return null;
            }
        }
        return data;
    }
}
