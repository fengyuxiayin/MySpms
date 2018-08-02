package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.lzc.myspms.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义带匹配规则的adapter
 * Created by LZC on 2018/6/13.
 */
public class MyFilterAdapter<T> extends BaseAdapter implements Filterable
{
    private List<T> mOriginalValues;
    private List<T> mObject;
    private final Object mLock = new Object();
    private int mResouce;
    private MyFilter myFilter = null;
    private LayoutInflater inflater;
    private Context context;
    //存储被选中的checkbox的位置
    private List<String> dataEnterprise = new ArrayList<>();

    public MyFilterAdapter(Context context, int TextViewResouceId, List<T> objects)
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

    private View getViewFromResouce(final int position, View convertView,
                                    ViewGroup parent, int mResouce2) {
        ViewHolder holder = null;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(mResouce2, parent,false);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.activity_area_enterprise_cv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.checkBox.setText(mObject.get(position).toString());
        holder.checkBox.setTextColor(context.getResources().getColor(R.color.white));
        Drawable drawableHomepage = context.getResources().getDrawable(R.drawable.checkbox_style);
        drawableHomepage.setBounds(0, 0, 40, 40);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        holder.checkBox.setCompoundDrawables(drawableHomepage, null, null, null);
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果被选中时还没有元素 直接添加
                    dataEnterprise.add(mObject.get(position).toString());
                }else{
                    //判断 如果添加过那么就删除
                    for (int i = 0; i <dataEnterprise.size() ; i++) {
                        if (dataEnterprise.get(i).equals(mObject.get(position).toString())) {
                            dataEnterprise.remove(i);
                        }
                    }
                }
            }
        });
        return convertView;
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
    public List<String> returnDataEnterprise(){
        if (dataEnterprise.size() == 0) {
            return null;
        }else{
            return dataEnterprise;
        }
    }
    public class ViewHolder{
        CheckBox checkBox;
    }

}