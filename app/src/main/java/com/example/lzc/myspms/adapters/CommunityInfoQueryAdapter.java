package com.example.lzc.myspms.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.communityinfoactivitys.AreaEnterpriseActivity;
import com.example.lzc.myspms.activitys.queryactivitys.communityinfoactivitys.EditCommunityInfoActivity;
import com.example.lzc.myspms.activitys.queryactivitys.communityinfoactivitys.ViewCommunityInfoActivity;
import com.example.lzc.myspms.custom.DragView;
import com.example.lzc.myspms.models.CommunityInfoQueryModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class CommunityInfoQueryAdapter extends BaseAdapter implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private List<CommunityInfoQueryModel.CommunityInfoQueryMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private LinearLayout linearLayout;
    private LinearLayout llAreaEnterprise;
    private Context context;
    private Intent intent = new Intent();
    private Gson gson = new Gson();
    private Activity activity;
    private TextView tvCommunityName;
    private ImageView imgEdit;
    private ImageView imgDelete;
    private LinearLayout llCommunityInfo;

    public CommunityInfoQueryAdapter(List<CommunityInfoQueryModel.CommunityInfoQueryMsgModel.ListBean> data, Context context, Activity activity) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.activity_community_info_query_item, parent, false);
        tvCommunityName = (TextView) view.findViewById(R.id.activity_community_info_query_item_tv_community_name);
        imgEdit = (ImageView) view.findViewById(R.id.activity_community_info_query_item_img_edit);
        imgDelete = (ImageView) view.findViewById(R.id.activity_community_info_query_item_img_delete);
        llCommunityInfo = (LinearLayout) view.findViewById(R.id.activity_community_info_query_item_ll_community_info);
        llAreaEnterprise = (LinearLayout) view.findViewById(R.id.activity_community_info_query_item_ll_area_compony);
       tvCommunityName.setText(data.get(position).getSqmc());
        imgEdit.setOnClickListener(this);
        imgEdit.setTag(position);
        imgDelete.setOnClickListener(this);
        imgDelete.setTag(position);
        llCommunityInfo.setOnClickListener(this);
        llCommunityInfo.setTag(position);
        llAreaEnterprise.setOnClickListener(this);
        llAreaEnterprise.setTag(position);
        return view;
    }

    @Override
    public void onClick(final View v) {
        final int pos = (int) v.getTag();
        switch (v.getId()) {
            case R.id.activity_community_info_query_item_img_edit:
                intent.setClass(context, ViewCommunityInfoActivity.class);
                intent.putExtra("sqId", data.get((Integer) pos).getId() + "");
                intent.putExtra("isView",false);
                context.startActivity(intent);
                break;
            case R.id.activity_community_info_query_item_img_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                //    设置Title的内容
                builder.setTitle("提示");
                //    设置Content来显示一个信息
                builder.setMessage("确定删除" + data.get(pos).getSqmc() + "社区的所有信息吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除社区信息
                        OkHttpUtils.post()
                                .url(Constant.SERVER_URL + "/community/delete")
                                .addParams("id", data.get(pos).getId() + "")
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                                NetUtil.errorTip(context,e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                data.remove(pos);
                                notifyDataSetChanged();
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                //    显示出该对话框
                builder.show();
                break;
            case R.id.activity_community_info_query_item_ll_community_info:
                intent.setClass(context, ViewCommunityInfoActivity.class);
                intent.putExtra("sqId", data.get((Integer) pos).getId() + "");
                intent.putExtra("isView",true);
                context.startActivity(intent);
                break;
            case R.id.activity_community_info_query_item_ll_area_compony:
                intent.setClass(context, AreaEnterpriseActivity.class);
                intent.putExtra("sqId", data.get((Integer) pos).getId() + "");
                context.startActivity(intent);
                break;

        }
    }
}
