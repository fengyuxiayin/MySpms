package com.example.lzc.myspms.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.EditProjectActivity;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.MyInfoModel;
import com.example.lzc.myspms.models.ProjectFindModel;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class ProjectFindAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = ProjectFindAdapter.class.getSimpleName();
    private List<ProjectFindModel.ProjectFindMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private List<EnumModel> projectList;

    public ProjectFindAdapter(List<ProjectFindModel.ProjectFindMsgModel.ListBean> data, Context context, List<EnumModel> projectList) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.projectList = projectList;
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
        View view = inflater.inflate(R.layout.activity_edit_enterprise_danger_item,parent,false);
        TextView tvFxdw = (TextView) view.findViewById(R.id.activity_edit_enterprise_danger_item_fxdw);
        TextView tvMc = (TextView) view.findViewById(R.id.activity_edit_enterprise_danger_item_mc);
        TextView tvBm = (TextView) view.findViewById(R.id.activity_edit_enterprise_danger_item_bm);
        ImageView imgDelete = (ImageView) view.findViewById(R.id.activity_edit_enterprise_danger_item_delete);
        ImageView imgEdit = (ImageView) view.findViewById(R.id.activity_edit_enterprise_danger_item_edit);
        for (int i = 0; i < projectList.size(); i++) {
            if ((data.get(position).getWxylx()+"").equals(projectList.get(i).getKey())) {
                tvFxdw.setText(projectList.get(i).getValue());
                break;
            }
        }
        Log.e(TAG, "getView: "+data.get(position).getBm() );
        tvBm.setText(data.get(position).getBm()==null?"":data.get(position).getBm()+"");
        tvMc.setText(data.get(position).getWxymc()==null?"":data.get(position).getWxymc()+"");
        imgEdit.setTag(position);
        imgEdit.setOnClickListener(this);
        imgDelete.setTag(position);
        imgDelete.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            final int pos = (int) v.getTag();
            switch (v.getId()) {
                case R.id.activity_edit_enterprise_danger_item_delete:
                    new AlertDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage("是否删除此条危险源信息")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    OkHttpUtils.post()
                                            .url(Constant.SERVER_URL+"/project/delete")
                                            .addParams("id",data.get(pos).getId()+"")
                                            .build()
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Request request, Exception e) {
                                                    Log.e(TAG, "onError: "+e.getMessage()+e.getCause() );
                                                }

                                                @Override
                                                public void onResponse(String response) {
                                                    Log.e(TAG, "onResponse: "+response );
                                                    Gson gson = new Gson();
                                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                                    if (infoModel.isData()) {
                                                        data.remove(pos);
                                                        notifyDataSetChanged();                                                        Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                                        Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                }
                            })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                    break;
                case R.id.activity_edit_enterprise_danger_item_edit:
                    Intent intent = new Intent();
                    intent.setClass(context, EditProjectActivity.class);
                    intent.putExtra("qyId",data.get(pos).getQyId()+"");
                    intent.putExtra("id",data.get(pos).getId()+"");
                    intent.putExtra("isEdit",true);
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
