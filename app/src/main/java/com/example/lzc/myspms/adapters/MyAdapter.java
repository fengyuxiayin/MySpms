package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义带匹配规则的adapter
 * Created by LZC on 2018/6/13.
 */
public class MyAdapter<T> extends BaseAdapter implements Filterable
{
    private List<T> mOriginalValues;
    private List<T> mObject;
    private final Object mLock = new Object();
    private int mResouce;
    private MyFilter myFilter = null;
    private LayoutInflater inflater;
    private Context context;

    public MyAdapter(Context context, int TextViewResouceId, List<T> objects)
    {
        this.context = context;
        if (context != null) {
            init(this.context,TextViewResouceId,objects);
        }
    }

    private void init(Context context, int textViewResouceId, List<T> objects)
    {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mObject = objects;
        Log.e("MyAdapter", "init: "+mObject.size() );
        mResouce = textViewResouceId;
        myFilter = new MyFilter();
    }

    @Override
    public int getCount() {
        return mObject.size();
    }

    @Override
    public T getItem(int position) {
        return mObject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewFromResouce(position,convertView,parent,mResouce);
    }

    private View getViewFromResouce(int position, View convertView,
                                    ViewGroup parent, int mResouce2) {
        if(convertView == null)
        {
            convertView = inflater.inflate(mResouce2, parent,false);
        }
        TextView tv = (TextView)convertView;
        T item = getItem(position);
        if(item instanceof CharSequence)
        {
            tv.setText((CharSequence)item);
        }
        else
        {
            tv.setText(item.toString());
        }
        return tv;
    }

    //返回过滤器
    @Override
    public Filter getFilter() {
        return myFilter;
    }

    //自定义过滤器
    private class MyFilter extends Filter
    {
        //得到过滤结果
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(mOriginalValues == null)
            {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<T>(mObject);
                }
            }

            int count = mOriginalValues.size();
            ArrayList<T> values = new ArrayList<T>();
            for(int i = 0;i < count;i++)
            {
                T value = mOriginalValues.get(i);
                String valueText = value.toString();
                //自定义匹配规则
                if(valueText != null && constraint != null && valueText.contains(constraint))
                {
                    values.add(value);
                }
            }
            results.values = values;
            results.count = values.size();
            return results;
        }
        //发布过滤结果
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            //把搜索结果赋值给mObject这样每次输入字符串的时候就不必
            //从所有的字符串中查找，从而提高了效率
            mObject = (List<T>)results.values;
            for (int i = 0; i < mObject.size(); i++) {
                Log.e("MyAdapter", "publishResults: "+mObject.get(i).toString() );
            }
            if(results.count > 0)
            {
                notifyDataSetChanged();
            }
            else
            {
                notifyDataSetInvalidated();
            }
        }

    }

}