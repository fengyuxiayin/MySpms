package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys.CheckItemsActivity;
import com.example.lzc.myspms.models.CheckPointModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class CheckPointAdapter extends BaseAdapter implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    public static final String TAG = CheckPointAdapter.class.getSimpleName();
    private List<CheckPointModel.CheckPointMsgModel.UnsafeBean> data;
    private LayoutInflater inflater;
    private RadioButton rbQualified;
    private RadioButton rbUnqualified;
    private Context context;
    private CheckItemsActivity activity;

    public CheckPointAdapter(List<CheckPointModel.CheckPointMsgModel.UnsafeBean> data, Context context,CheckItemsActivity activity) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
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
        View view = inflater.inflate(R.layout.activity_check_items_item, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.activity_check_items_item_iv_pic);
        TextView tvProjectName = (TextView) view.findViewById(R.id.activity_check_items_item_xmmc);
        tvProjectName.setText(data.get(position).getMemo());
        RadioGroup radiogroup = (RadioGroup) view.findViewById(R.id.activity_check_items_item_rg);
        rbQualified = (RadioButton) view.findViewById(R.id.activity_check_items_item_rb_qualified);
        rbUnqualified = (RadioButton) view.findViewById(R.id.activity_check_items_item_rb_unqualified);
        if ("0".equals(data.get(position).getJcjg())||data.get(position).getJcjg()==null) {
            rbQualified.setChecked(false);
            rbUnqualified.setChecked(true);
            data.get(position).setJcjg("0");
        } else {
            rbQualified.setChecked(true);
            rbUnqualified.setChecked(false);
            data.get(position).setJcjg("1");
        }
        Drawable drawableQualified = context.getResources().getDrawable(R.drawable.checkbox_qualified_style);
        drawableQualified.setBounds(0, 0, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbQualified.setCompoundDrawables(null, drawableQualified, null, null);
        Drawable drawableUnqualified = context.getResources().getDrawable(R.drawable.checkbox_unqualified_style);
        drawableUnqualified.setBounds(0, 0, 65, 65);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbUnqualified.setCompoundDrawables(null, drawableUnqualified, null, null);
        radiogroup.setOnCheckedChangeListener(this);
        radiogroup.setTag(position);
        imageView.setOnClickListener(this);
        imageView.setTag(position);
        if (data.get(position).getJctp()!=null) {
            if (data.get(position).getJctp().toString().length()>0) {
                Log.e(TAG, "getView: "+data.get(position).getJctp().toString() );
                List<String> list = Arrays.asList(data.get(position).getJctp().toString().split(","));
                for (int i = 0; i < list.size(); i++) {
                    Log.e(TAG, "getView: "+list.get(i) );
                }
                Picasso.with(context).load(list.get(0)).into(imageView);
            }else{
//                Toast.makeText(context, "当前检查点没有图片", Toast.LENGTH_SHORT).show();
            }
        }else{
//            Toast.makeText(context, "当前检查点没有图片", Toast.LENGTH_SHORT).show();
        }
        activity.getUnsafeData(data);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int position = (int) group.getTag();
        switch (checkedId) {
            case R.id.activity_check_items_item_rb_qualified:
                group.check(R.id.activity_check_items_item_rb_qualified);
                data.get(position).setJcjg("1");
                break;
            case R.id.activity_check_items_item_rb_unqualified:
                group.check(R.id.activity_check_items_item_rb_unqualified);
                data.get(position).setJcjg("0");
                break;
        }
        activity.getUnsafeData(data);

    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            Integer pos = (Integer) v.getTag();
            switch (v.getId()) {
                case R.id.activity_check_items_item_iv_pic:

                    break;
            }
        }
    }
}
