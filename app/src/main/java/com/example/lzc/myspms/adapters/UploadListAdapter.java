package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.SendMessageActivity;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.MyInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class UploadListAdapter extends BaseAdapter {
    private List<String> data;
    private LayoutInflater inflater;
    private SendMessageActivity activity;
    private ListView listView;

    public UploadListAdapter(List<String> data, Context context,ListView listView) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.activity = (SendMessageActivity) context;
        this.listView = listView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.listview_upload_item,parent,false);
        ClearEditText etName = (ClearEditText) view.findViewById(R.id.listview_upload_item_et);
        etName.setText(data.get(position));
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0) {
                    data.remove(position);
                    activity.setListViewHeightBasedOnChildren(listView);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    public List<String> getdata() {
        return this.data;
    }

    public void notifyData(String msg) {
        data.add(msg);
        notifyDataSetChanged();
    }
}
