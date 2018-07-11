package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.StaffInfoQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.staffinfoactivitys.StaffInfoDetailActivity;
import com.example.lzc.myspms.models.StaffInfoQueryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class StaffInfoQueryAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = StaffInfoQueryAdapter.class.getSimpleName();
    private List<StaffInfoQueryModel.StaffInfoQueryMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private StaffInfoQueryActivity activity;

    public StaffInfoQueryAdapter(List<StaffInfoQueryModel.StaffInfoQueryMsgModel.ListBean> data, Context context, StaffInfoQueryActivity activity) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.context = context;
        this.activity = activity;
        this.inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.activity_staff_info_query_item,parent,false);
        Button button = (Button) view.findViewById(R.id.activity_staff_info_query_item_img_detail);
        TextView textView = (TextView) view.findViewById(R.id.activity_staff_info_query_item_tv_name);
        textView.setText("姓名："+data.get(position).getRymc());
        button.setTag(position);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Intent intent = new Intent();
        Log.e(TAG, "onClick: "+data.get(position).getId() );
        intent.setClass(context, StaffInfoDetailActivity.class);
        intent.putExtra("id",data.get(position).getId()+"");
        intent.putExtra("ssdwmc",data.get(position).getSsdwmc()+"");
        activity.startActivity(intent);
    }
}
