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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectCheckRecordActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.custom.DragView;
import com.example.lzc.myspms.models.CheckItemsQueryModel;
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
public class CheckItemsSearchResultAdapter extends BaseAdapter implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private List<CheckItemsQueryModel.CheckItemsQueryMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private LinearLayout linearLayout;
    private Context context;
    private Activity activity;
    private Intent intent = new Intent();
    private Gson gson = new Gson();

    public CheckItemsSearchResultAdapter(List<CheckItemsQueryModel.CheckItemsQueryMsgModel.ListBean> data, Context context) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = (Activity) context;
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
        View view = inflater.inflate(R.layout.activity_check_items_search_result_item, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.activity_check_items_search_result_item_tv);
        textView.setText(data.get(position).getXmmc());
//        TextView tvCheckRecord = (TextView) view.findViewById(R.id.activity_check_items_search_result_item_tv_check_record);
//        tvCheckRecord.setTag(position);
//        tvCheckRecord.setOnClickListener(this);
        DragView dragView = (DragView) view.findViewById(R.id.activity_check_items_search_result_item_dv);
        dragView.setTag(position);
        dragView.setOnDragStateListener(new DragView.DragStateListener() {
            private LinearLayout llCheckRecord;
            private LinearLayout llBasicInfo;

            @Override
            public void onOpened(DragView dragView) {

            }

            @Override
            public void onClosed(DragView dragView) {

            }

            @Override
            public void onForegroundViewClick(DragView dragView, View v) {
                int pos = (int) dragView.getTag();
                //设置下面布局的隐藏和显示
                linearLayout = (LinearLayout) dragView.findViewById(R.id.activity_check_items_search_result_item_ll);
                if (linearLayout.getVisibility() == View.VISIBLE) {
                    linearLayout.setVisibility(View.GONE);
                } else {
                    linearLayout.setVisibility(View.VISIBLE);
                }
                //找到对应的两个布局，设置点击监听
                llBasicInfo = (LinearLayout) linearLayout.findViewById(R.id.activity_check_items_search_result_item_ll_basic_info);
                llCheckRecord = (LinearLayout) linearLayout.findViewById(R.id.activity_check_items_search_result_item_ll_check_record);
                llBasicInfo.setTag(pos);
                llBasicInfo.setOnClickListener(CheckItemsSearchResultAdapter.this);
                llCheckRecord.setTag(pos);
                llCheckRecord.setOnClickListener(CheckItemsSearchResultAdapter.this);
            }

            @Override
            public void onBackgroundViewClick(DragView dragView, final View v) {
                final Integer pos = (Integer) dragView.getTag();
                if ("修改".equals(((Button)v).getText())) {
                    intent.setClass(context, AddProjectActivity.class);
                    //qyId 是添加项目时需要 xmId是获取项目信息时需要 startClass是判断是哪一个activity
                    intent.putExtra("qyId", data.get(pos).getQyId() + "");
                    intent.putExtra("xmId", data.get(pos).getXmid() + "");
                    intent.putExtra("qymc", data.get(pos).getQymc() + "");
                    intent.putExtra("startClass", "CheckItemSearchResultActivity");
                    context.startActivity(intent);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    //    设置Title的内容
                    builder.setTitle("提示");
                    //    设置Content来显示一个信息
                    builder.setMessage("确定删除" + data.get(pos).getQymc() + "项目的所有信息吗");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //删除项目信息
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/project/delete")
                                    .addParams("id", data.get(pos).getXmid() + "")
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
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(final View v) {
        Intent intent = new Intent();
        if (v != null) {
            switch (v.getId()) {
                case R.id.activity_check_items_search_result_item_ll_basic_info:
                    intent.setClass(context, ProjectDetailActivity.class);
                    intent.putExtra("xmId", data.get((Integer) v.getTag()).getXmid() + "");
                    intent.putExtra("qymc", data.get((Integer) v.getTag()).getQymc() + "");
                    context.startActivity(intent);
                    break;
                case R.id.activity_check_items_search_result_item_ll_check_record:
                    //检查记录
                    Log.e(TAG, "onClick: " );
                    intent.setClass(context, ProjectCheckRecordActivity.class);
                    intent.putExtra("jcxmId",data.get((Integer) v.getTag()).getXmid()+"");
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
