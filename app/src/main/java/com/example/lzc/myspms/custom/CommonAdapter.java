package com.example.lzc.myspms.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2018/8/7.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private List<T> mData = new ArrayList<>();
    private Context context;
    private int layoutId;

    public CommonAdapter(Context context, List<T> mData, int layoutId) {
        if (mData!=null) {
            this.mData = mData;
        }
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.getViewHolder(parent,convertView,context,layoutId,position);
        convert(viewHolder,getItem(position),position);
        return viewHolder.getContentView();
    }
    public abstract void convert(ViewHolder helper, T item,int position);
}
