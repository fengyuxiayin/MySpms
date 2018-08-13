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

import java.io.Serializable;
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
    private ViewHolder holder;

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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_project_simple_item, parent, false);

            holder = new ViewHolder();
            holder.tvProjectName = (TextView) convertView.findViewById(R.id.activity_project_simple_project_name);
            holder.ivProjectStatus = (ImageView) convertView.findViewById(R.id.activity_project_simple_status);
            holder.ivProjectEdit = (ImageView) convertView.findViewById(R.id.activity_project_simple_edit);
            convertView.setTag(holder);
        
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        if (jcxmlx.equals("1")) {
            holder.tvProjectName.setText(data.get(position).getStandardDescription());
        }else{
            holder.tvProjectName.setText(data.get(position).getWxymc());
        }
        //根据检查结果设置状态图片 2 未检查 0 检查不合格 1检查合格
        Log.e(TAG, "getView: "+data.get(position).getJcjg() );
        if (data.get(position).getJcjg() == 2 ) {
            holder.ivProjectStatus.setImageResource(R.mipmap.uncheck);
            holder.ivProjectEdit.setImageResource(R.mipmap.enterprise_info_query_item_img_edit);

        } else if (data.get(position).getJcjg() == 0) {
            holder.ivProjectStatus.setImageResource(R.mipmap.check_unqualified);
            holder.ivProjectEdit.setImageResource(R.mipmap.new_check_img_view);
        } else {
            holder.ivProjectStatus.setImageResource(R.mipmap.check_qualified);
            holder.ivProjectEdit.setImageResource(R.mipmap.new_check_img_view);
        }
        if (this.jcjg!=null) {
            if (this.jcjg.equals("1")) {
                Log.e(TAG, "getView: 1111"+jcjg );
                holder.ivProjectEdit.setImageResource(R.mipmap.new_check_img_view);
            }else{
                Log.e(TAG, "getView: 2222"+jcjg );

            }
        }
        // TODO: 2018/6/18 项目类别还没写
        holder.ivProjectEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("jcId",jcId);
                intent.putExtra("id",data.get(position).getId()+"");
                List<CheckProjectFindModel.CheckProjectFindMsgModel.ListBean> ids = new ArrayList<CheckProjectFindModel.CheckProjectFindMsgModel.ListBean>();
                if (data.get(position).getJcjg() != 2) {//只要项目不是未检查就只能看不能修改
                    intent.putExtra("isView","isView");
                    intent.putExtra("position",position);
                    ids.addAll(data);//查看时直接将所有项目放入ids
                }else{
                    intent.putExtra("isView","isNotView");
                    intent.putExtra("position",0);
                    for (int i = 0; i < data.size(); i++) {//项目未检查 把未检查的项目添加到ids中
                        if (data.get(i).getJcjg() == 2){
                            ids.add(data.get(i));
                        }
                    }
                }
                intent.putExtra("ids", (Serializable) ids);
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
    private static class ViewHolder {
        public TextView tvProjectName;
        public ImageView ivProjectStatus;
        public ImageView ivProjectEdit;
        
    }

}
