package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.example.lzc.myspms.activitys.homepageactivitys.AddEnterpriseSimpleActivity;
import com.example.lzc.myspms.activitys.queryactivitys.EnterpriseInfoQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.EditEnterpriseInfoActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.EnterpriseCheckRecordActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.EnterpriseInfoActivity;
import com.example.lzc.myspms.custom.DragView;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnterpriseInfoQueryModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class EnterpriseInfoQueryAdapter extends BaseAdapter implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private List<EnterpriseInfoQueryModel.ListSet.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private Intent intent = new Intent();
    private Gson gson = new Gson();
    private EnterpriseInfoQueryActivity activity;



    public EnterpriseInfoQueryAdapter(List<EnterpriseInfoQueryModel.ListSet.ListBean> data, Context context, EnterpriseInfoQueryActivity activity) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = activity;
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
    public View getView(int position,  View convertView, ViewGroup parent) {
        Log.e(TAG, "getView: ");
        ViewHolder holder = null;
        if (holder==null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_enterprise_info_query_item, parent, false);
            holder.tvComponyName = (TextView) convertView.findViewById(R.id.activity_enterprise_info_query_item_tv_compony);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.activity_enterprise_info_query_item_img_edit);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.activity_enterprise_info_query_item_img_delete);
            holder.llBasicInfo = (LinearLayout) convertView.findViewById(R.id.activity_enterprise_info_query_item_ll_basic_info);
            holder.llCheckRecord = (LinearLayout) convertView.findViewById(R.id.activity_enterprise_info_query_item_ll_check_record);
            holder.llGps = (LinearLayout) convertView.findViewById(R.id.activity_enterprise_info_query_item_ll_gps);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvComponyName.setText(data.get(position).getQymc());
        holder.imgEdit.setTag(position);
        holder.imgEdit.setOnClickListener(this);
        holder.imgDelete.setTag(position);
        holder.imgDelete.setOnClickListener(this);
        holder.llBasicInfo.setTag(position);
        holder.llBasicInfo.setOnClickListener(this);
        holder.llCheckRecord.setTag(position);
        holder.llCheckRecord.setOnClickListener(this);
        holder.llGps.setTag(position);
        holder.llGps.setOnClickListener(this);
        return convertView;
    }
    public class ViewHolder{
        TextView tvComponyName;
        ImageView imgEdit;
        ImageView imgDelete;
        LinearLayout llBasicInfo;
        LinearLayout llCheckRecord;
        LinearLayout llGps;
    }
    @Override
    public void onClick(View v) {
        final Integer pos = (Integer) v.getTag();
        if (v != null) {
            switch (v.getId()) {
                case R.id.activity_enterprise_info_query_item_img_edit://编辑
                    intent.setClass(context, AddEnterpriseSimpleActivity.class);
                    intent.putExtra("which","edit");
                    intent.putExtra("qyId", data.get(pos).getId() + "");
                    context.startActivity(intent);
                    break;
                case R.id.activity_enterprise_info_query_item_img_delete:
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    //    设置Title的内容
                    builder.setTitle("提示");
                    //    设置Content来显示一个信息
                    builder.setMessage("确定删除" + data.get(pos).getQymc() + "企业的所有信息吗");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //删除企业信息
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/baseEnterprise/delete")
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
                                    data.remove(pos);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
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
                case R.id.activity_enterprise_info_query_item_ll_basic_info:
                    intent.setClass(context, AddEnterpriseSimpleActivity.class);
                    intent.putExtra("which","view");
                    intent.putExtra("qyId", data.get((Integer) pos).getId() + "");
                    context.startActivity(intent);
                    break;
                case R.id.activity_enterprise_info_query_item_ll_check_record:
                    intent.setClass(context, EnterpriseCheckRecordActivity.class);
                    intent.putExtra("qyId", data.get((Integer) pos).getId() + "");
                    intent.putExtra("qymc", data.get((Integer) pos).getQymc() + "");
                    intent.putExtra("startClass", "enterprise");
                    context.startActivity(intent);
                    break;
                case R.id.activity_enterprise_info_query_item_ll_gps:
                    invokingGD(data.get((Integer) pos).getQymc() + "");
                    break;
            }
        }
    }
    public void invokingGD(String address){

        //  com.autonavi.minimap这是高德地图的包名
        Intent intent = new Intent("android.intent.action.VIEW",android.net.Uri.parse("androidamap://navi?sourceApplication=应用名称&lat="+ "&dev=0"));
        intent.setPackage("com.autonavi.minimap");
        intent.setData(Uri.parse("androidamap://poi?sourceApplication=softname&keywords="+address));

        if(isInstallByread("com.autonavi.minimap")){
            context.startActivity(intent);
            Log.e("GasStation", "高德地图客户端已经安装") ;
        }else{
            Toast.makeText(context, "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 判断是否安装目标应用
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
}
