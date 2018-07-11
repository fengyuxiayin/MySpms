package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.myspms.R;


/**
 * Created by LZC on 2017/10/30.
 */
public class PopupMainAdapter extends BaseAdapter {
    private String[] dataTitle;
    private Integer[] dataIcon;
    private LayoutInflater inflater;

    public PopupMainAdapter(String[] dataTitle, Integer[] dataIcon, Context context) {
        if (dataTitle==null) {
            this.dataTitle = new String[0];
        }else{
            this.dataTitle = new String[0];
            this.dataTitle =dataTitle;
        }
        if (dataTitle==null) {
            this.dataIcon = new Integer[0];
        }else{
            this.dataIcon = new Integer[0];
            this.dataIcon = dataIcon;
        }
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataTitle==null?0:dataTitle.length;
    }

    @Override
    public Object getItem(int position) {
        return dataTitle==null?dataTitle[0]:dataTitle[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.popup_main_menu_item,parent,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.popupwindow_item_icon);
        TextView textView = (TextView) view.findViewById(R.id.popupwindow_item_tv);
        imageView.setImageResource(dataIcon[position]);
        textView.setText(dataTitle[position]);
        return view;
    }
}
