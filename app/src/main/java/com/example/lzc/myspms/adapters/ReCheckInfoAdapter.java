package com.example.lzc.myspms.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.CheckProjectActivity;
import com.example.lzc.myspms.models.CheckIsExistModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.NewCheckInfoModel;
import com.example.lzc.myspms.models.QyJsonModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.FastClickUtil;
import com.example.lzc.myspms.utils.GpsUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class ReCheckInfoAdapter extends BaseAdapter implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private List<NewCheckInfoModel.NewCheckMsgInfoModel.ListBean> data;
    private LayoutInflater inflater;
    private SimpleDateFormat simpleDateFormat;
    private String format;
    private Activity activity;
    private Gson gson = new Gson();
    private String rwzt;

    public ReCheckInfoAdapter(List<NewCheckInfoModel.NewCheckMsgInfoModel.ListBean> data, Context context, Activity activity, String rwzt) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.activity = activity;
        this.rwzt = rwzt;
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
        Log.e(TAG, "getView: ");
        View view = inflater.inflate(R.layout.fragment_recheck_item, parent, false);
        TextView tvDate = (TextView) view.findViewById(R.id.fragment_recheck_item_tv_date);
        TextView tvNumber = (TextView) view.findViewById(R.id.fragment_recheck_item_tv_number);
        TextView tvComponyName = (TextView) view.findViewById(R.id.fragment_recheck_item_tv_compony);
        TextView tvRegularLevel = (TextView) view.findViewById(R.id.fragment_recheck_item_tv_level);
        ImageView imgView = (ImageView) view.findViewById(R.id.fragment_recheck_item_img_operation);
        if (rwzt.equals("1")) {
            imgView.setImageResource(R.mipmap.new_check_img_view);
        }
        // TODO: 2018/6/19  截止时间不对
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (data.get(position).getJzsj() > 0) {
            format = simpleDateFormat.format(new Date(data.get(position).getJzsj()));
        }
        // TODO: 2018/6/19 合格数待确定
        Log.e(TAG, "getView: "+data.get(position).getPassCount() );
        tvNumber.setText(data.get(position).getXmsl()-data.get(position).getPassCount() + "");
        tvDate.setText(format);
        QyJsonModel qyJsonModel = gson.fromJson(data.get(position).getQyJson(), QyJsonModel.class);
        tvComponyName.setText(qyJsonModel.getQymc());
        tvRegularLevel.setText(data.get(position).getJgfj());
        imgView.setOnClickListener(this);
        imgView.setTag(position);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            final int position = (int) v.getTag();
            //判断gps是否打开
            boolean isOpen = GpsUtil.openGPSSettings(activity);
            if (isOpen) {
                //gps打开了，判断是否已经定位成功
                if (Constant.LOCATION_INFO.equals("")) {
                    //定位未完成
                    Toast.makeText(activity, "GPS正在定位，请稍候", Toast.LENGTH_SHORT).show();
                } else {
//                    定位完成 获取经纬度
//                    String jd = Constant.LOCATION_INFO.substring(0, Constant.LOCATION_INFO.lastIndexOf(","));
//                    String wd = Constant.LOCATION_INFO.substring(Constant.LOCATION_INFO.lastIndexOf(",") + 1, Constant.LOCATION_INFO.length());
////                    向服务器验证当前企业是否在范围内
//                    OkHttpUtils.post()
//                            .url(Constant.SERVER_URL + "/baseEnterprise/isInPolygon")
//                            .addParams("qyId", data.get(position).getQyId() + "")
//                            .addParams("lng", jd)
//                            .addParams("lat", wd)
//                            .build()
//                            .execute(new StringCallback() {
//                                @Override
//                                public void onError(Request request, Exception e) {
//                                    Log.e(TAG, "onError: " + e.getMessage());
//                                    if (("timeout").equals(e.getMessage())) {
//                                        Toast.makeText(activity, "连接超时，请稍后重试", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onResponse(String response) {
//                                    Log.e(TAG, "onResponse: 企业范围" + response);
//                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
//                                    if (!infoModel.isData()) {
//                                        Toast.makeText(activity, "当前位置不在企业检查范围内", Toast.LENGTH_SHORT).show();
//                                    } else {
                    if (FastClickUtil.isFastClick()) {
                        if (data.get(position).getRwzt() != 1) {//检查中或者未检查
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/checkInfo/beginCheck")
                                    .addParams("id", data.get(position).getId() + "")
                                    .addParams("rwId", data.get(position).getRwId() + "")
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            NetUtil.errorTip(activity, e.getMessage() + "/checkInfo/beginCheck");
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Log.e(TAG, "onResponse: " + response + "/checkInfo/beginCheck");
                                            LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                            if (infoModel.isData()) {
                                                Intent intent = new Intent();
                                                intent.putExtra("jclx", "2");//1是初查 2是复查
                                                intent.putExtra("rwzt", data.get(position).getRwzt() + "");//0 是未检查 1已检查 2是检查中
                                                intent.putExtra("jcjg", data.get(position).getJcjg() + "");//0 是不合格 1 已合格 2 未检查
                                                intent.putExtra("jcId", data.get(position).getId() + "");
                                                intent.putExtra("qyJson", data.get(position).getQyJson());
                                                intent.putExtra("rwId", data.get(position).getRwId());
                                                intent.putExtra("jcsj", DateUtil.long2Date(data.get(position).getJzsj()));
                                                intent.setClass(activity, CheckProjectActivity.class);
                                                activity.startActivity(intent);
//                                                        Toast.makeText(activity, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(activity, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }else{//检查完成
                            Intent intent = new Intent();
                            intent.putExtra("jclx", "2");//1是初查 2是复查
                            intent.putExtra("rwzt", data.get(position).getRwzt() + "");//0 是未检查 1已检查 2是检查中
                            intent.putExtra("jcjg", data.get(position).getJcjg() + "");//0 是不合格 1 已合格 2 未检查
                            intent.putExtra("jcId", data.get(position).getId() + "");
                            intent.putExtra("qyJson", data.get(position).getQyJson());
                            intent.putExtra("rwId", data.get(position).getRwId());
                            intent.putExtra("jcsj", DateUtil.long2Date(data.get(position).getJzsj()));
                            intent.setClass(activity, CheckProjectActivity.class);
                            activity.startActivity(intent);
                        }

                    } else {
                        Log.e(TAG, "onClick: 重复点击了");
                        Toast.makeText(activity, "请不要重复点击检查", Toast.LENGTH_SHORT).show();
                    }
                }

//                                }
//                                }
//                            });
            }
//            }
//    else {
//                Toast.makeText(activity, "需要Gps定位功能", Toast.LENGTH_SHORT).show();
//            }


        }
    }
}
