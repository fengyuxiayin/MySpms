package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.DownloadModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class CanDownLoadAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = CanDownLoadAdapter.class.getSimpleName();
    private List<DownloadModel.DownloadMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private String wsmc;

    public CanDownLoadAdapter(List<DownloadModel.DownloadMsgModel.ListBean> data, Context context) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? data.get(0) : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getView: ");
        View view = inflater.inflate(R.layout.fragment_can_download_item, parent, false);
        final TextView tvDate = (TextView) view.findViewById(R.id.fragment_can_download_item_tv_date);
        ImageView imgOperation = (ImageView) view.findViewById(R.id.fragment_can_download_item_operation);
        final TextView tvName = (TextView) view.findViewById(R.id.fragment_can_download_item_tv_name);
        tvName.setText(data.get(position).getWsmc());
        tvDate.setText(DateUtil.long2Date(data.get(position).getCreateTime()));
        imgOperation.setTag(position);
        imgOperation.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            Integer pos = (Integer) v.getTag();
            final String wsbdlj = data.get(pos).getWsbdlj();
            if (wsbdlj != null) {
                wsmc = wsbdlj.substring(wsbdlj.lastIndexOf("/") + 1, wsbdlj.length());
                OkHttpUtils.get()
                        .url(Constant.UPLOAD_IMG_IP+wsbdlj)
                        .build()
                        .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), wsmc) {
                            @Override
                            public void inProgress(float progress) {
                                Log.e(TAG, "inProgress: progress" + progress);
                            }

                            @Override
                            public void onError(Request request, Exception e) {
                                NetUtil.errorTip(context, e.getMessage());
                            }

                            @Override
                            public void onResponse(File response) {
                                Log.e(TAG, "onResponse: 文件下载完成" + response);
                                OkHttpUtils.post()
                                        .url(Constant.SERVER_URL + "/checkDocDownload/save")
                                        .addParams("wsmc", wsmc)
                                        .addParams("wsbdlj", wsbdlj)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Request request, Exception e) {
                                                NetUtil.errorTip(context, e.getMessage());
                                            }

                                            @Override
                                            public void onResponse(String response) {
                                                Log.e(TAG, "onResponse: 保存下载记录成功" + response);
                                                Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
            } else {
                Log.e(TAG, "onClick: 文书本地路径为空");
            }
        }
    }
}
