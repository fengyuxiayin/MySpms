package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.lzc.myspms.R;

import com.example.lzc.myspms.models.InstrumentModel;
import com.example.lzc.myspms.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by LZC on 2017/10/30.
 */
public class UploadAdapter extends BaseAdapter {
    public static final String TAG = UploadAdapter.class.getSimpleName();
    private List<InstrumentModel.InstrumentMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private String wsmc;
    private String filePath;

    public UploadAdapter(List<InstrumentModel.InstrumentMsgModel.ListBean> data, Context context) {
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
        View view = inflater.inflate(R.layout.fragment_upload_item, parent, false);
        final TextView tvDate = (TextView) view.findViewById(R.id.fragment_upload_item_tv_date);
        final TextView tvName = (TextView) view.findViewById(R.id.fragment_upload_item_tv_name);
        String scnr = data.get(position).getScnr();
        if (scnr!=null) {
            String substring = scnr.substring(scnr.lastIndexOf("/") + 1, scnr.length());
            tvName.setText(substring);
        }else{
            Log.e(TAG, "getView: 文书名称为空" );
        }
        tvDate.setText(DateUtil.long2Date(data.get(position).getCreateTime()));

        return view;
    }

}
