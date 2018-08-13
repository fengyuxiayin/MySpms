package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.PointInfoModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LZC on 2017/10/24.
 */
public class PointGridAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    public static final String TAG = PointGridAdapter.class.getSimpleName();
    private List<PointInfoModel.PointInfoMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private Gson gson = new Gson();
    private String jctp = "";
    private List<String> jctpList = new ArrayList<>();
    private List<String> jctpNewList = new ArrayList<>();
    private String isView;
    private ImageGridAdapter imageGridAdapter;

    public PointGridAdapter(List<PointInfoModel.PointInfoMsgModel.ListBean> data, Context context, String isView) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        Log.e(TAG, "PointGridAdapter: "+this.data.size() );
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.isView = isView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_project_detail_danger_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(data.get(position).getYhdmc());
        if (data.get(position).getJcjg() == 0) {
            holder.rbUnQualified.setChecked(true);
        } else {
            holder.rbQualified.setChecked(true);
        }
        holder.etDescription.setText(data.get(position).getYhms());
        jctp = data.get(position).getJctp();
//        Log.e(TAG, "getView: "+jctp );
        judgeJctpIsEmpty(jctp,holder.gridView,position);
        //设置单选按钮的监听
        holder.rbQualified.setOnCheckedChangeListener(this);
        holder.rbQualified.setTag(position);
        //设置隐患详情的监听
        holder.etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PointInfoModel.PointInfoMsgModel.ListBean listBean = data.get(position);
                listBean.setYhms(s.toString().trim());
                data.set(position,listBean);
            }
        });
        if (isView.equals("isView")) {
            holder.etDescription.setFocusable(false);
            holder.rbQualified.setClickable(false);
            holder.rbUnQualified.setClickable(false);
        }
        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = (int) buttonView.getTag();
        if (isChecked) {
            PointInfoModel.PointInfoMsgModel.ListBean listBean = data.get(pos);
            listBean.setJcjg(1);
            data.set(pos,listBean);
            notifyDataSetChanged();
        }else{
            PointInfoModel.PointInfoMsgModel.ListBean listBean = data.get(pos);
            listBean.setJcjg(0);
            data.set(pos,listBean);
            notifyDataSetChanged();
        }

    }

    private static class ViewHolder {
        TextView tvName;
        RadioButton rbQualified;
        RadioButton rbUnQualified;
        EditText etDescription;
        GridView gridView;

        public ViewHolder(View itemView) {
            tvName = (TextView) itemView.findViewById(R.id.activity_project_detail_danger_item_tv_name);
            rbQualified = (RadioButton) itemView.findViewById(R.id.activity_project_detail_danger_item_rb_qualified);
            rbUnQualified = (RadioButton) itemView.findViewById(R.id.activity_project_detail_danger_item_rb_unqualified);
            etDescription = (EditText) itemView.findViewById(R.id.activity_project_detail_danger_item_et_descroption);
            gridView = (GridView) itemView.findViewById(R.id.activity_project_detail_danger_item_gridview);
        }
    }

    private void judgeJctpIsEmpty(String jctp,GridView gridView,int position) {
        if (jctp != null) {
            if (jctp.length() > 0) {
                //有检查点图片返回时
                Log.e(TAG, "initData: 检查点图片不为空" + jctp);
                jctp+=", ";
                jctpNewList = new ArrayList<>();
                Log.e(TAG, "judgeJctpIsEmpty: "+jctp );
                if (jctp.contains(",")) {
                    jctpList = Arrays.asList(jctp.split(","));
                    for (int i = 0; i < jctpList.size(); i++) {
                        if (i == jctpList.size()-1) {
                            jctpList.set(i,"");
                        }else{
                            jctpList.set(i,jctpList.get(i));
                        }
                    }
                }
                jctpNewList.addAll(jctpList);
                imageGridAdapter = new ImageGridAdapter(jctpNewList,context,isView,position);
                gridView.setAdapter(imageGridAdapter);
            } else {
                //没有图片时
                jctpNewList = new ArrayList<>();
                jctpNewList.add("");
                imageGridAdapter = new ImageGridAdapter(jctpNewList,context,isView,position);
                gridView.setAdapter(imageGridAdapter);
                Log.e(TAG, "initData: 检查点图片为空");
            }
        } else {
            jctpNewList = new ArrayList<>();
            jctpNewList.add("");
            imageGridAdapter = new ImageGridAdapter(jctpNewList,context,isView,position);
            gridView.setAdapter(imageGridAdapter);
            Log.e(TAG, "initData: 检查点图片为空");
        }
        //当imgAdapter里面删除图片的时候回调
        imageGridAdapter.setListener(new ImageGridAdapter.CallBack() {
            @Override
            public void doSomeThing(List<String> data) {//对数据源的jctp进行重新赋值
                PointInfoModel.PointInfoMsgModel.ListBean listBean = PointGridAdapter.this.data.get(Constant.GRID_POS_ONE);
                String jctp = "";
                for (int i = 0; i < data.size(); i++) {
                    if (i!=data.size()-1) {//去掉最后的“”
                        jctp+= data.get(i).replace(Constant.UPLOAD_IMG_IP,"");
                        if (i!=data.size()-2) {//除了倒数第二项都加逗号
                            jctp+=",";
                        }
                    }
                }
                listBean.setJctp(jctp);
                PointGridAdapter.this.data.set(Constant.GRID_POS_ONE,listBean);
            }
        });
        Log.e(TAG, "judgeJctpIsEmpty: "+jctpNewList.size() );
    }
}
