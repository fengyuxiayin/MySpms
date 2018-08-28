package com.example.lzc.myspms.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.EditProjectActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.UnsafeActivity;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.MyInfoModel;
import com.example.lzc.myspms.models.UnsafeFindModel;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class UnsafeAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = UnsafeAdapter.class.getSimpleName();
    private List<UnsafeFindModel.UnsafeFindMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private ImageView imgEdit;
    private ImageView imgDelete;
    private EditProjectActivity activity;

    private GridView gridView;
    private UnsafeAdapter unsafeAdapter;
    private UnsafeActivity.DataListener dataListener;


    public UnsafeAdapter(List<UnsafeFindModel.UnsafeFindMsgModel.ListBean> data, Context context) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = (EditProjectActivity) context;
    }
public void setUnsafeAdapter(UnsafeAdapter unsafeAdapter){
    this.unsafeAdapter = unsafeAdapter;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.unsafe_adapter_item, parent, false);
        TextView tvYhdmc = (TextView) view.findViewById(R.id.unsafe_adapter_item_tv_yhdmc);
        TextView tvYhdxq = (TextView) view.findViewById(R.id.unsafe_adapter_item_tv_yhdxq);
        imgEdit = (ImageView) view.findViewById(R.id.unsafe_adapter_item_img_edit);
        imgDelete = (ImageView) view.findViewById(R.id.unsafe_adapter_item_img_delete);
        tvYhdmc.setText(data.get(position).getYhdmc());
        tvYhdxq.setText(data.get(position).getYhdxq());
        imgEdit.setTag(position);
        imgEdit.setOnClickListener(this);
        imgDelete.setTag(position);
        imgDelete.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(final View v) {
        if (v != null) {
            final int pos = (int) v.getTag();
            switch (v.getId()) {
                case R.id.unsafe_adapter_item_img_edit:
                    Intent intent = new Intent();
                    intent.setClass(context, UnsafeActivity.class);
                    intent.putExtra("yhdmc", data.get(pos).getYhdmc() + "");
                    intent.putExtra("xh", data.get(pos).getXh() + "");
                    intent.putExtra("yhdxq", data.get(pos).getYhdxq() + "");
                    intent.putExtra("bz", data.get(pos).getBz() + "");
                    intent.putExtra("yhdtp", data.get(pos).getYhdtp() + "");
                    intent.putExtra("position", pos+"");
                    intent.putExtra("data",(Serializable) data.get(pos));
                    activity.startActivityForResult(intent,10010);
                    break;
                case R.id.unsafe_adapter_item_img_delete:
                    int status = data.get(pos).getStatus();
                    if (status==1) {
                        new AlertDialog.Builder(context)
                                .setTitle("提示")
                                .setMessage("是否删除该隐患点")
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int status = data.get(pos).getStatus();
                                        if (status == 1) {
                                            data.get(pos).setStatus(2);
                                        } else {
                                            Toast.makeText(context, "该隐患点已删除", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .show();
                    }else{
                        v.setBackgroundResource(R.mipmap.deleted);
                        Toast.makeText(context, "该隐患点已删除", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    }

    public void setData(int pos, UnsafeFindModel.UnsafeFindMsgModel.ListBean data) {
            Log.e(TAG, "setData: 执行了" );
            if (pos == -1) {//添加
                this.data.add(data);
            }else{//修改
                this.data.set(pos, data);
            }
            notifyDataSetChanged();
    }
}
