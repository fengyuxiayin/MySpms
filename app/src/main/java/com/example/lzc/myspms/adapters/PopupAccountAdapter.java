package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.MyInfoModel;
import com.example.lzc.myspms.models.NeteaseAccountFindModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class PopupAccountAdapter extends BaseAdapter {
    private List<NeteaseAccountFindModel.NeteaseAccountFindMsgModel.ListBean> data;
    private LayoutInflater inflater;

    public PopupAccountAdapter(List<NeteaseAccountFindModel.NeteaseAccountFindMsgModel.ListBean> data, Context context) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
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
        View view = inflater.inflate(R.layout.popup_videocall_account_item,parent,false);
        TextView textView = (TextView) view.findViewById(R.id.popup_videocall_account_item_tv);
        ImageView imgView = (ImageView) view.findViewById(R.id.popup_videocall_account_item_img);
        textView.setText(data.get(position).getAccid()+"");
        return view;
    }
}
