package com.example.lzc.myspms.custom;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lzc.myspms.adapters.CheckProgressAdapter;

/**
 * Created by LZC on 2018/8/7.
 */

public class ViewHolder {
    private SparseArray<View> viewArray = new SparseArray<>();
    private  View mContentView;
    private Context context;
    private int position;

    private ViewHolder(ViewGroup parent, Context context,int layoutId,int position) {
        mContentView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mContentView.setTag(this);
    }
    public static ViewHolder getViewHolder(ViewGroup parent, View convertView,Context context,int layoutId,int position){
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder(parent, context, layoutId, position);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return viewHolder;
    }
    public View getContentView(){
        return mContentView;
    }


    public <T extends View> T getView(int viewId){
        View view = viewArray.get(viewId);
        if (view==null) {
                view = mContentView.findViewById(viewId);
            viewArray.put(viewId,view);
        }
        return (T)view;
    }
}
