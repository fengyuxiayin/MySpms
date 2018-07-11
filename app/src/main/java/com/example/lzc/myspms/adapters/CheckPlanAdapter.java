package com.example.lzc.myspms.adapters;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.NotCheckModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class CheckPlanAdapter extends BaseAdapter {
    public static final String TAG = CheckPlanAdapter.class.getSimpleName();
    private List<NotCheckModel.NotCheckMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;

    public CheckPlanAdapter(List<NotCheckModel.NotCheckMsgModel.ListBean> data, Context context) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data==null?data.get(0):data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.activity_check_plan_item,parent,false);
        TextView tvName = (TextView) view.findViewById(R.id.activity_check_plan_item_tv_name);
        final EditText etTime = (EditText) view.findViewById(R.id.activity_check_plan_item_et_time);
        tvName.setText(data.get(position).getQymc());
        etTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL+"/jobCycleRecord/setCheckDate")
                        .addParams("checkDate",s.toString())
                        .addParams("id",data.get(position).getId()+"")
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
                            }
                        });
            }
        });
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTime.setTag(position);
                setDate(etTime);
            }
        });
        return view;
    }
    /**
     * @desc 显示日历，并将选择的时间变为指定格式显示在Edittext上
     * @param edittext 需要设置显示内容的Edittext
     * @date 2017/12/11 14:27
     */
    private void setDate(final EditText edittext) {
        final int pos = (int) edittext.getTag();
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(year, monthOfYear, dayOfMonth);
                edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL+"/jobCycleRecord/setCheckDate")
                        .addParams("id",data.get(pos).getId()+"")
                        .addParams("checkDate", (String) DateFormat.format("yyyy-MM-dd", calendar))
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
                                Gson gson = new Gson();
                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }
}
