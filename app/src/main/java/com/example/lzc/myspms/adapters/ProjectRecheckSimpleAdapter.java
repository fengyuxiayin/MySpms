package com.example.lzc.myspms.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailSimpleActivity;
import com.example.lzc.myspms.models.FindRecheckProjectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class ProjectRecheckSimpleAdapter extends BaseAdapter {
    public static final String TAG = ProjectRecheckSimpleAdapter.class.getSimpleName();
    private List<FindRecheckProjectModel.FindRecheckProjectMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;
    private String jcId;
    private String jcjg;

    public ProjectRecheckSimpleAdapter(List<FindRecheckProjectModel.FindRecheckProjectMsgModel.ListBean> data, Context context, String jcId, String jcjg) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = (Activity) context;
        this.jcId = jcId;
        this.jcjg = jcjg;
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
        convertView = inflater.inflate(R.layout.activity_project_simple_item, parent, false);
        TextView tvProjectName = (TextView) convertView.findViewById(R.id.activity_project_simple_project_name);
        ImageView ivProjectStatus = (ImageView) convertView.findViewById(R.id.activity_project_simple_status);
        ImageView ivProjectEdit = (ImageView) convertView.findViewById(R.id.activity_project_simple_edit);
        tvProjectName.setText(data.get(position).getDescription());
        //根据检查结果设置状态图片 null 未检查 0 检查不合格 1检查合格
        Log.e(TAG, "getView: " + data.get(position).getJcjg());

        if (data.get(position).getJcjg() == 0) {
            ivProjectStatus.setImageResource(R.mipmap.check_unqualified);
        } else {
            ivProjectStatus.setImageResource(R.mipmap.check_qualified);
        }
        if (this.jcjg != null) {
            if (this.jcjg.equals("1")) {
                ivProjectEdit.setImageResource(R.mipmap.view);
            }
        }
        ivProjectEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("jcId", jcId);
                intent.putExtra("standardId", data.get(position).getStandardId() + "");
                intent.putExtra("jcjg", data.get(position).getJcjg() + "");
                intent.putExtra("jctp", data.get(position).getJctp() + "");
                intent.putExtra("jcxId", data.get(position).getId() + "");
                intent.putExtra("description", data.get(position).getDescription());
                intent.putExtra("pubish",data.get(position).getPubish());
                intent.putExtra("refrenceBasis",data.get(position).getRefrenceBasis());
                if (ProjectRecheckSimpleAdapter.this.jcjg != null) {
                    if (ProjectRecheckSimpleAdapter.this.jcjg.equals("1")) {
                        //判断企业是否已经检查完毕
                        intent.putExtra("isView", "isView");
                    } else {
                        intent.putExtra("isView", "isNotView");
                    }
                } else {
                    intent.putExtra("isView", "isNotView");
                }

                intent.setClass(context, ProjectDetailSimpleActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
