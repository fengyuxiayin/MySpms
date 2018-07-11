package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.AlreadyCheckModel;
import com.example.lzc.myspms.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class AlreadyCheckAdapter extends BaseAdapter {
    public static final String TAG = AlreadyCheckAdapter.class.getSimpleName();
    private List<AlreadyCheckModel.AlreadyCheckMsgModel.ListBean> data;
    private LayoutInflater inflater;

    public AlreadyCheckAdapter(List<AlreadyCheckModel.AlreadyCheckMsgModel.ListBean> data, Context context) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
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
        View view = inflater.inflate(R.layout.fragment_already_check_item,parent,false);
        ImageView imgStatus = (ImageView) view.findViewById(R.id.fragment_already_check_item_status);
        TextView tvTime = (TextView) view.findViewById(R.id.fragment_already_check_item_check_time);
        TextView tvType = (TextView) view.findViewById(R.id.fragment_already_check_item_type);
        TextView tvName = (TextView) view.findViewById(R.id.fragment_already_check_item_qymc);
        Log.e(TAG, "getView: " +data.get(position).getJcjg());
        if (data.get(position).getJcjg()==0) {
            imgStatus.setImageResource(R.mipmap.wrong_checked);
        }else if(data.get(position).getJcjg()==1){
            imgStatus.setImageResource(R.mipmap.check_qualified);
        }else{
            imgStatus.setImageResource(R.mipmap.uncheck);
        }
        tvTime.setText(DateUtil.long2Date(data.get(position).getJckssj()));
        tvType.setText(data.get(position).getJclx()==1?"初查":"复查");
        tvName.setText(data.get(position).getQymc());
        return view;
    }
}
