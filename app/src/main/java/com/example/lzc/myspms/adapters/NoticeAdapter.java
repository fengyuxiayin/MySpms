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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.ViewMessageActivity;
import com.example.lzc.myspms.bean.NoticeInfo;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.MessageFindModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class NoticeAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = NoticeAdapter.class.getSimpleName();
    private List<MessageFindModel.MessageFindMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private Gson gson = new Gson();

    public NoticeAdapter(List<MessageFindModel.MessageFindMsgModel.ListBean> data, Context context) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public MessageFindModel.MessageFindMsgModel.ListBean getItem(int position) {
        return data == null ? data.get(0) : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.activity_notice_item, parent, false);
        TextView tvDate = (TextView) view.findViewById(R.id.activity_notice_item_tv_date);
        TextView tvFjr = (TextView) view.findViewById(R.id.activity_notice_item_tv_fjr);
        TextView tvTitle = (TextView) view.findViewById(R.id.activity_notice_item_tv_title);
        TextView tvStatus = (TextView) view.findViewById(R.id.activity_notice_item_tv_status);
        ImageView imgRead = (ImageView) view.findViewById(R.id.activity_notice_item_img_read);
        ImageView imgDelete = (ImageView) view.findViewById(R.id.activity_notice_item_img_delete);
        tvDate.setText(DateUtil.long2Date(data.get(position).getCreateTime()));
        tvTitle.setText(data.get(position).getXxbt());
        tvFjr.setText(data.get(position).getFjrMc());
        tvStatus.setText(data.get(position).getXxzt() == 0 ? "未读" : "已读");
        imgRead.setOnClickListener(this);
        imgRead.setTag(position);
        imgDelete.setOnClickListener(this);
        imgDelete.setTag(position);
        return view;
    }

    @Override
    public void onClick(View v) {
        final int pos = (int) v.getTag();
        if (v != null) {
            switch (v.getId()) {
                case R.id.activity_notice_item_img_read:
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL+"/sysMessage/markRead")
                            .addParams("id",data.get(pos).getId()+"")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    NetUtil.errorTip(context,e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: "+response );
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    if (infoModel.isData()) {
                                        Intent intent = new Intent();
                                        intent.setClass(context, ViewMessageActivity.class);
                                        intent.putExtra("fjr", data.get(pos).getFjrMc());
                                        intent.putExtra("sjr", data.get(pos).getSjrMc());
                                        //发送时间
                                        intent.putExtra("time", DateUtil.long2Date(data.get(pos).getCreateTime()));
                                        intent.putExtra("parentId", data.get(pos).getId() + "");
                                        intent.putExtra("fj", data.get(pos).getFj());
                                        intent.putExtra("bt",data.get(pos).getXxbt()+"");
                                        intent.putExtra("zw",data.get(pos).getXxnr()+"");
                                        context.startActivity(intent);
                                    }else{
                                        Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    break;
                case R.id.activity_notice_item_img_delete:
                    AlertDialog dialog = new AlertDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage("是否删除该邮件")
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    OkHttpUtils.post()
                                            .url(Constant.SERVER_URL + "/sysMessage/delete")
                                            .addParams("id", data.get(pos).getId() + "")
                                            .build().execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            NetUtil.errorTip(context, e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Log.e(TAG, "onResponse: " + response);
                                            LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                            Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                            data.remove(pos);
                                        }
                                    });
                                }
                            }).create();
                    dialog.show();
                    Button btnPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    btnPositive.setTextColor(context.getResources().getColor(R.color.homepage_text_press));
                    Button btnNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    btnNegative.setTextColor(context.getResources().getColor(R.color.homepage_text_press));
//                    try {
//                        Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
//                        mAlert.setAccessible(true);
//                        Object mAlertController = mAlert.get(dialog);
//                        Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
//                        mMessage.setAccessible(true);
//                        TextView mMessageView = (TextView) mMessage.get(mAlertController);
//                        mMessageView.setTextColor(context.getResources().getColor(R.color.homepage_text_press));
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (NoSuchFieldException e) {
//                        e.printStackTrace();
//                    }
                    break;
            }
        }
    }
}
