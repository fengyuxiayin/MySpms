package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.CheckInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/24.
 */
public class GridAdapter extends BaseAdapter {
    private List<CheckInfoModel> data;
    private LayoutInflater inflater;
    public GridAdapter(List<CheckInfoModel> data, Context context) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>() ;
        }
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_check_gridview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(data.get(position).getName());
        ViewGroup.LayoutParams para;
        para = holder.imageView.getLayoutParams();

        para.height = 40;
        para.width = 40;
        holder.imageView.setLayoutParams(para);
        holder.imageView.setImageResource(data.get(position).getImgId());
        

        return convertView;
    }
    private static class ViewHolder {
        TextView title;
        ImageView imageView;

        public ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.fragment_check_gridview_item_tv);
            imageView = (ImageView) itemView.findViewById(R.id.fragment_check_gridview_item_img);
        }
    }
}
