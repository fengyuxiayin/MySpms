package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectCheckRecordActivity;
import com.example.lzc.myspms.models.ProjectCheckRecordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/12/4.
 */

public class ProjectCheckRecordAdapter extends BaseAdapter {
    private  final String TAG = this.getClass().getSimpleName();
    private List<ProjectCheckRecordModel.ProjectCheckRecordMsgModel.PagerBean.ListBean> data;
    private Context context;
    private LayoutInflater inflater;

    public ProjectCheckRecordAdapter(List<ProjectCheckRecordModel.ProjectCheckRecordMsgModel.PagerBean.ListBean> data, ProjectCheckRecordActivity context) {
        if (data!=null) {
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data==null?null:data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getView: " );
        ViewHolder holder;
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.activity_enterprise_check_record_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        long jckssj = data.get(position).getJckssj();
        CharSequence format = DateFormat.format("yyyy-MM-dd", jckssj);
        holder.textViewDate.setText(format);
        if (data.get(position).getSzqsj()==0) {
            holder.textViewCycle.setText("当前记录未检查");
        }else{
            long szqsj = data.get(position).getSzqsj();
            long bzqsj = data.get(position).getBzqsj();
            CharSequence formatSzqsj = DateFormat.format("yyyy-MM-dd", szqsj);
            CharSequence formatBzqsj = DateFormat.format("yyyy-MM-dd", bzqsj);
            holder.textViewCycle.setText(formatSzqsj+"至"+formatBzqsj);
        }
        holder.textViewChecker.setText(data.get(position).getJcdwMc());
        return convertView;
    }
    public class ViewHolder{
        TextView textViewDate;
        TextView textViewCycle;
        TextView textViewChecker;
        public ViewHolder(View itemView) {
            textViewDate = (TextView) itemView.findViewById(R.id.activity_ebterprise_check_record_item_date);
            textViewCycle = (TextView) itemView.findViewById(R.id.activity_ebterprise_check_record_item_cycle);
            textViewChecker = (TextView) itemView.findViewById(R.id.activity_ebterprise_check_record_item_checker);
        }

    }
}
