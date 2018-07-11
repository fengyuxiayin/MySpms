package com.example.lzc.myspms.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailDangerActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailSimpleActivity;
import com.example.lzc.myspms.models.CheckProjectFindModel;
import com.example.lzc.myspms.models.MyInfoModel;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class ProjectSimpleAdapter extends BaseAdapter {
    public static final String TAG = ProjectSimpleAdapter.class.getSimpleName();
    private List<CheckProjectFindModel.CheckProjectFindMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;
    private String jcId;
    //这个是企业的检查结果
    private String jcjg;
    private String jcxmlx;
    private String qymc;

    public ProjectSimpleAdapter(List<CheckProjectFindModel.CheckProjectFindMsgModel.ListBean> data, Context context, String jcId, String jcjg, String jcxmlx, String qymc) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = (Activity) context;
        this.jcId = jcId;
        this.jcjg = jcjg;
        this.jcxmlx = jcxmlx;
        this.qymc = qymc;
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
        convertView = inflater.inflate(R.layout.activity_project_simple_item, parent, false);
        TextView tvProjectName = (TextView) convertView.findViewById(R.id.activity_project_simple_project_name);
        ImageView ivProjectStatus = (ImageView) convertView.findViewById(R.id.activity_project_simple_status);
        ImageView ivProjectEdit = (ImageView) convertView.findViewById(R.id.activity_project_simple_edit);
        if (jcxmlx.equals("1")) {
            tvProjectName.setText(data.get(position).getStandardDescription());
        }else{
            tvProjectName.setText(data.get(position).getWxymc());
        }
        //根据检查结果设置状态图片 2 未检查 0 检查不合格 1检查合格
        Log.e(TAG, "getView: "+data.get(position).getJcjg() );
        if (data.get(position).getJcjg() == 2 ) {
            ivProjectStatus.setImageResource(R.mipmap.uncheck);
        } else if (data.get(position).getJcjg() == 0) {
            ivProjectStatus.setImageResource(R.mipmap.check_unqualified);
        } else {
            ivProjectStatus.setImageResource(R.mipmap.check_qualified);
        }
        if (this.jcjg!=null) {
            if (this.jcjg.equals("1")) {
                ivProjectEdit.setImageResource(R.mipmap.new_check_img_view);
            }
        }
        // TODO: 2018/6/18 项目类别还没写
        ivProjectEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("jcId",jcId);
                intent.putExtra("id",data.get(position).getId()+"");
                if (ProjectSimpleAdapter.this.jcjg.equals("1")) {
                    intent.putExtra("isView","isView");
                }else{
                    intent.putExtra("isView","isNotView");
                }
                intent.putExtra("qymc",qymc);
                if (jcxmlx.equals("1")) {
                    intent.setClass(context, ProjectDetailSimpleActivity.class);
                    context.startActivity(intent);
                }else{
                    intent.setClass(context, ProjectDetailDangerActivity.class);
                    context.startActivity(intent);
                }

            }
        });
        return convertView;
    }
}
