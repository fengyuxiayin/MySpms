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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.custom.MyListView;
import com.example.lzc.myspms.models.CheckTaskFindModel;
import com.example.lzc.myspms.models.CheckTaskModel;
import com.example.lzc.myspms.models.Constant;
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
public class CheckTaskAdapter extends BaseAdapter {
    public static final String TAG = CheckTaskAdapter.class.getSimpleName();
    private List<CheckTaskModel.CheckTaskMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Gson gson = new Gson();
    private Context context;
    private Activity activity;
    private boolean isIdle = true;

    public CheckTaskAdapter(List<CheckTaskModel.CheckTaskMsgModel.ListBean> data, Context context) {
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
            convertView = inflater.inflate(R.layout.activity_release_item,parent,false);
            holder.line = (View) convertView.findViewById(R.id.activity_release_item_line);
            holder.tvRwmc = (TextView) convertView.findViewById(R.id.activity_release_item_rwmc);
            holder.tvDate = (TextView) convertView.findViewById(R.id.activity_release_item_date);
            holder.tvjcdx = (TextView) convertView.findViewById(R.id.activity_release_item_jcdx);
            holder.tvjczt = (TextView) convertView.findViewById(R.id.activity_release_item_jczt);
            holder.tvProgress = (TextView) convertView.findViewById(R.id.activity_release_item_progress);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Log.e(TAG, "getView: "+position );
//        if (position==0) {
//            holder.line.setVisibility(View.GONE);
//        }
        if (isIdle) {
            holder.tvRwmc.setText(data.get(position).getRwmc());
            holder.tvDate.setText(DateUtil.long2Date(data.get(position).getJzsj()));
            holder.tvjcdx.setText(data.get(position).getJcdx());
            holder.tvjczt.setText(data.get(position).getJczt());
            holder.tvProgress.setText(data.get(position).getRwjd()+"%");
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL+"/checkTaskSettings/find")
                        .addParams("tags",data.get(position).getTags())
                        .build()
                        .execute(new StringCallback() {
                            private PopupReleaseAdapter popupReleaseAdapter;
                            private CheckTaskFindModel.CheckTaskFindMsgModel checkTaskFindMsgModel;
                            private CheckTaskFindModel checkTaskFindModel;

                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: "+e.getMessage()+e.getCause() );
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: "+response );
                                checkTaskFindModel = gson.fromJson(response, CheckTaskFindModel.class);
                                if (checkTaskFindModel.isData()) {
                                    checkTaskFindMsgModel = gson.fromJson(checkTaskFindModel.getMsg(), CheckTaskFindModel.CheckTaskFindMsgModel.class);
                                    List<CheckTaskFindModel.CheckTaskFindMsgModel.ListBean> list = checkTaskFindMsgModel.getList();
                                    if (list!=null) {
                                        View contentView = View.inflate(activity, R.layout.popup_release, null);
                                        ListView listView = (ListView) contentView.findViewById(R.id.popup_release_lv);
                                        popupReleaseAdapter = new PopupReleaseAdapter(list,activity);
                                        listView.setAdapter(popupReleaseAdapter);
                                        PopupWindow popupWindow = new PopupWindow();
                                        popupWindow = new PopupWindow();
                                        popupWindow.setWidth((int) (activity.getWindowManager().getDefaultDisplay().getWidth() * (0.8)));
                                        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                                        popupWindow.setContentView(contentView);
                                        popupWindow.setBackgroundDrawable(new BitmapDrawable());
                                        popupWindow.setOutsideTouchable(true);
                                        popupWindow.setFocusable(true);
                                        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                                    }
                                }
                            }
                        });
            }
        });
        return convertView;
    }
    public void setScrollTdle(boolean isIdle){
        this.isIdle = isIdle;
    }
    public class ViewHolder{
        TextView tvRwmc;
        TextView tvDate;
        TextView tvjcdx;
        TextView tvjczt;
        TextView tvProgress;
        View line;
    }
}
