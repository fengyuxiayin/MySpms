package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.PrintActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys.PreviewActivity;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.DownloadModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LZC on 2017/10/30.
 */
public class AlreadyDownLoadAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = AlreadyDownLoadAdapter.class.getSimpleName();
    private List<DownloadModel.DownloadMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private String wsmc;
    private String filePath;

    public AlreadyDownLoadAdapter(List<DownloadModel.DownloadMsgModel.ListBean> data, Context context) {
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
        View view = inflater.inflate(R.layout.fragment_already_download_item, parent, false);
        final TextView tvDate = (TextView) view.findViewById(R.id.fragment_already_download_item_tv_date);
        final TextView tvName = (TextView) view.findViewById(R.id.fragment_already_download_item_tv_name);
        ImageView imgView = (ImageView) view.findViewById(R.id.fragment_already_download_item_view);
        ImageView imgPrint = (ImageView) view.findViewById(R.id.fragment_already_download_item_print);
        ImageView imgDelete = (ImageView) view.findViewById(R.id.fragment_already_download_item_delete);
        tvName.setText(data.get(position).getWsmc());
        tvDate.setText(DateUtil.long2Date(data.get(position).getCreateTime()));
        imgView.setTag(position);
        imgView.setOnClickListener(this);
        imgPrint.setTag(position);
        imgPrint.setOnClickListener(this);
        imgDelete.setTag(position);
        imgDelete.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            final Integer pos = (Integer) v.getTag();
            final String wsbdlj = data.get(pos).getWsbdlj();

            if (wsbdlj != null) {
                wsmc = wsbdlj.substring(wsbdlj.lastIndexOf("/") + 1, wsbdlj.length());
                filePath = Constant.UPLOAD_IMG_IP + wsbdlj;
                Log.e(TAG, "onClick: "+filePath );
            }else{
                Log.e(TAG, "onClick: 文书本地路径为空" );
            }
            Log.e(TAG, "onClick: wsbdlj"+filePath );
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.fragment_already_download_item_view:
//                    intent.setClass(context, PrintActivity.class);
                    intent.putExtra("filePath", filePath);
//                    context.startActivity(intent);
//                    onPrintPdf();
                    intent.setClass(context, PreviewActivity.class);
                    intent.putExtra("url", filePath);
                    context.startActivity(intent);
                    break;
                case R.id.fragment_already_download_item_print:
//                    intent.setClass(context, PrintActivity.class);
                    intent.putExtra("filePath", filePath);
//                    context.startActivity(intent);
                    onPrintPdf();
                    break;
                case R.id.fragment_already_download_item_delete:
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    //    设置Title的内容
                    builder.setTitle("提示");
                    //    设置Content来显示一个信息
                    builder.setMessage("确定删除此条下载记录吗？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //删除项目信息
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL+"/checkDocDownload/delete")
                                    .addParams("id",data.get(pos).getId()+"")
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            NetUtil.errorTip(context,e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Log.e(TAG, "onResponse: "+ response);
                                        }
                                    });
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    //    显示出该对话框
                    builder.show();
                    break;
            }

        }
    }
    /**
     *
     *@desc 打印pdf
     *@date 2018/5/22 17:38
    */
    private void onPrintPdf() {
        PrintManager printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(PrintAttributes.COLOR_MODE_COLOR);
        printManager.print("test pdf print", new MyPrintAdapter(context,filePath), builder.build());
    }
}
