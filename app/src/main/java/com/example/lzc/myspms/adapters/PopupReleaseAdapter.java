package com.example.lzc.myspms.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.CheckTaskFindModel;
import com.example.lzc.myspms.models.CheckTaskModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * 每日任务页面右侧的list的适配器
 */
public class PopupReleaseAdapter extends BaseAdapter {
    public static final String TAG = PopupReleaseAdapter.class.getSimpleName();
    private List<CheckTaskFindModel.CheckTaskFindMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Gson gson = new Gson();
    private Context context;
    private Activity activity;

    public PopupReleaseAdapter(List<CheckTaskFindModel.CheckTaskFindMsgModel.ListBean> data, Context context) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = (Activity) context;
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
        ViewHolder holder = null;
        if (convertView==null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.popup_release_item,parent,false);
            holder.line = (View) convertView.findViewById(R.id.popup_release_item_line);
            holder.tvQymc = (TextView) convertView.findViewById(R.id.popup_release_item_qymc);
            holder.tvDate = (TextView) convertView.findViewById(R.id.popup_release_item_date);
            holder.tvZdqydj = (TextView) convertView.findViewById(R.id.popup_release_item_zdqydj);
            holder.tvjcdw = (TextView) convertView.findViewById(R.id.popup_release_item_jcdw);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.popup_release_item_opreation);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if (position==0) {
            holder.line.setVisibility(View.GONE);
        }
        holder.tvQymc.setText(data.get(position).getQymc());
        holder.tvDate.setText(DateUtil.long2Date(data.get(position).getJzsj()));
        holder.tvZdqydj.setText(data.get(position).getBaseEnterprise().getSfzd());
        holder.tvjcdw.setText(data.get(position).getJcdwmc());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: "+data.get(position).getRwzt() );
                if (data.get(position).getRwzt()==0) {
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL + "/checkTaskSettings/delete")
                            .addParams("id", data.get(position).getId() + "")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    Log.e(TAG, "onError: " + e.getMessage());
                                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: " + response);
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    if (infoModel.isData()) {
                                        data.remove(position);
                                        notifyDataSetChanged();
                                    }
                                    Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(context, "任务已经开始检查，不能删除", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }
    public class ViewHolder{
        TextView tvQymc;
        TextView tvDate;
        TextView tvZdqydj;
        TextView tvjcdw;
        ImageView imgDelete;
        View line;
    }
}
