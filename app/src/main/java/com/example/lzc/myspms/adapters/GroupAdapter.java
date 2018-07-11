package com.example.lzc.myspms.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.staffinfoactivitys.StaffInfoDetailActivity;
import com.example.lzc.myspms.models.GroupDetailModel;
import com.example.lzc.myspms.models.MyInfoModel;
import com.example.lzc.myspms.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class GroupAdapter extends BaseAdapter implements View.OnClickListener {
    private List<GroupDetailModel.GroupDetailMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Intent intent;
    private Context context;

    public GroupAdapter(List<GroupDetailModel.GroupDetailMsgModel.ListBean> data, Context context) {
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
        View view = inflater.inflate(R.layout.activity_group_item,parent,false);
        TextView tvName = (TextView) view.findViewById(R.id.activity_group_item_name);
        TextView tvSex = (TextView) view.findViewById(R.id.activity_group_item_sex);
        TextView tvPosition = (TextView) view.findViewById(R.id.activity_group_item_position);
        ImageView imgView = (ImageView) view.findViewById(R.id.activity_group_item_img_view);
        ImageView imgCall = (ImageView) view.findViewById(R.id.activity_group_item_img_call);
        tvName.setText(data.get(position).getRymc());
        if (data.get(position).getRyzc()==1) {
            tvPosition.setText("协查员");
        }else{
            tvPosition.setText("执法员");
        }
        imgView.setTag(position);
        imgView.setOnClickListener(this);
        imgCall.setTag(position);
        imgCall.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            final int pos = (int) v.getTag();
            switch (v.getId()) {
                case R.id.activity_group_item_img_view:
                    intent = new Intent(context, StaffInfoDetailActivity.class);
                    intent.putExtra("id",data.get(pos).getId()+"");
                    context.startActivity(intent);
                    break;
                case R.id.activity_group_item_img_call:
                    PermissionUtil.checkAndRequestPermission(context, Manifest.permission.CALL_PHONE, 1, new PermissionUtil.PermissionRequestSuccessCallBack() {
                        @Override
                        public void onHasPermission() {
                            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +data.get(pos).getLxdh()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });

                    break;
            }
        }
    }
}
