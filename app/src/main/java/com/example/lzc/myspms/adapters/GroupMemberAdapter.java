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
import com.example.lzc.myspms.models.MemberModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/24.
 */
public class GroupMemberAdapter extends BaseAdapter {
    private List<MemberModel> data;
    private LayoutInflater inflater;
    public GroupMemberAdapter(List<MemberModel> data, Context context) {
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
            convertView = inflater.inflate(R.layout.popup_person_grid_member, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(data.get(position).getName());
        holder.tvPosition.setText(data.get(position).getPosition());
        return convertView;
    }
    private static class ViewHolder {
        TextView tvName;
        TextView tvPosition;
        ImageView imageView;

        public ViewHolder(View itemView) {
            tvName = (TextView) itemView.findViewById(R.id.popup_main_personal_tv_name);
            tvPosition = (TextView) itemView.findViewById(R.id.popup_main_personal_tv_position);
            imageView = (ImageView) itemView.findViewById(R.id.popup_main_personal_img_icon);
        }
    }
}
