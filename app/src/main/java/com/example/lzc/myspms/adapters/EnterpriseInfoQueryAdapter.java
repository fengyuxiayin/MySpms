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
    private LinearLayout llBasicInfo;
    private LinearLayout llCheckRecord;
    private Context context;
    private Intent intent = new Intent();
    private Gson gson = new Gson();
    private EnterpriseInfoQueryActivity activity;
    private LinearLayout llGps;
    private TextView tvComponyName;
    private ImageView imgEdit;
    private ImageView imgDelete;


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
    public View getView(int position, final View convertView, ViewGroup parent) {
        Log.e(TAG, "getView: ");
        View view = inflater.inflate(R.layout.activity_enterprise_info_query_item, parent, false);
        tvComponyName = (TextView) view.findViewById(R.id.activity_enterprise_info_query_item_tv_compony);
        tvComponyName.setText(data.get(position).getQymc());
        imgEdit = (ImageView) view.findViewById(R.id.activity_enterprise_info_query_item_img_edit);
        imgEdit.setTag(position);
        imgEdit.setOnClickListener(this);
        imgDelete = (ImageView) view.findViewById(R.id.activity_enterprise_info_query_item_img_delete);
        imgDelete.setTag(position);
        imgDelete.setOnClickListener(this);
        llBasicInfo = (LinearLayout) view.findViewById(R.id.activity_enterprise_info_query_item_ll_basic_info);
        llBasicInfo.setTag(position);
        llBasicInfo.setOnClickListener(this);
        llCheckRecord = (LinearLayout) view.findViewById(R.id.activity_enterprise_info_query_item_ll_check_record);
        llCheckRecord.setTag(position);
        llCheckRecord.setOnClickListener(this);
        llGps = (LinearLayout) view.findViewById(R.id.activity_enterprise_info_query_item_ll_gps);
        llGps.setTag(position);
        llGps.setOnClickListener(this);
        return view;
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
