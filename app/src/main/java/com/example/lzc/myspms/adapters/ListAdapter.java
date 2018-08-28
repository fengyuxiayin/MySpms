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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class ListAdapter extends BaseAdapter {
    private List<MyInfoModel> data;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapter(List<MyInfoModel> data, Context context) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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
        View view = inflater.inflate(R.layout.fragment_my_listview_item,parent,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_my_listview_item_iv);
        TextView textView = (TextView) view.findViewById(R.id.fragment_my_listview_item_tv);
        imageView.setImageResource(data.get(position).getImgId());
        textView.setText(data.get(position).getName());
        return view;
    }
}
