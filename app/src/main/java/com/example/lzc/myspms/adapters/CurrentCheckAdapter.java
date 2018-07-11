package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.HomePageCheckInfoModel;
import com.example.lzc.myspms.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;

/**
 * Created by LZC on 2017/10/30.
 */
public class CurrentCheckAdapter extends BaseAdapter {
    public static final String TAG = CurrentCheckAdapter.class.getSimpleName();
    private List<HomePageCheckInfoModel.HomePageCheckInfoMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;

    public CurrentCheckAdapter(List<HomePageCheckInfoModel.HomePageCheckInfoMsgModel.ListBean> data, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getView: " );
        View view = inflater.inflate(R.layout.fragment_current_check_item,parent,false);
        TextView tvName = (TextView) view.findViewById(R.id.fragment_current_check_item_tv_name);
        TextView tvStartTime = (TextView) view.findViewById(R.id.fragment_current_check_item_tv_start_time);
        TextView tvPercent = (TextView) view.findViewById(R.id.fragment_current_check_item_tv_percent);
        TextView tvEndTime = (TextView) view.findViewById(R.id.fragment_current_check_item_tv_end_time);
        TextView tvCheckedCount= (TextView) view.findViewById(R.id.fragment_current_check_item_tv_checked_count);
        TextView tvQualifiedCount = (TextView) view.findViewById(R.id.fragment_current_check_item_tv_qualified_count);
        TextView tvUnqualifiedCount = (TextView) view.findViewById(R.id.fragment_current_check_item_tv_unqualified_count);
        ZzHorizontalProgressBar progressBar = (ZzHorizontalProgressBar) view.findViewById(R.id.fragment_current_check_item_pb);
        tvName.setText(data.get(position).getQymc());
        String startDate = DateUtil.long2Date(data.get(position).getCheckInfo().getJckssj());
        tvStartTime.setText("开始时间："+startDate);
//        String endDate = DateUtil.long2Date(data.get(position).getCheckInfo().getZgqx());
//        tvEndTime.setText("开始时间："+endDate);
        double countYhg = data.get(position).getCountQualified();
        double countXmsl = data.get(position).getCountTotal();
        double countYjc = data.get(position).getCountChecked();
        double countWhg = data.get(position).getCountUnqualified();
        Log.e(TAG, "getView: "+countXmsl );
        if (countXmsl!=0) {
            int percent = (int) ((countYhg*100) / countXmsl);
            if (percent>100) {
                percent = 100;
            }
            tvPercent.setText(percent+"%");
            progressBar.setProgress((int) percent);
        }else{
            Toast.makeText(context, "当前检查企业没有项目", Toast.LENGTH_SHORT).show();
        }
        tvCheckedCount.setText("已检查项目："+countYjc);
        tvQualifiedCount.setText("已合格项目："+countYhg);
        tvUnqualifiedCount.setText("不合格项目："+countWhg);

        return view;
    }
}
