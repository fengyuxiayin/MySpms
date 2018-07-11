package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.EnterpriseQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.communityinfoactivitys.AreaEnterpriseActivity;
import com.example.lzc.myspms.models.EnumModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class AreaEnterpriswAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    public static final String TAG = AreaEnterpriswAdapter.class.getSimpleName();
    private List<EnumModel> data;
    private LayoutInflater inflater;
    private Context context;
    private AreaEnterpriseActivity areaEnterpriseActivity;
    private EnterpriseQueryActivity enterpriseQueryActivity;
    //存储被选中的checkbox的位置
    private List<Integer> dataPosition = new ArrayList<>();

    public AreaEnterpriswAdapter(List<EnumModel> data, Context context, AreaEnterpriseActivity activity) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.context = context;
        this.areaEnterpriseActivity = activity;
        this.inflater = LayoutInflater.from(context);
    }

    public AreaEnterpriswAdapter(List<EnumModel> data, Context context, EnterpriseQueryActivity activity) {
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
        this.context = context;
        this.enterpriseQueryActivity = activity;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        Log.e(TAG, "getCount: "+data.size() );
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
        View view = inflater.inflate(R.layout.activity_area_enterprise_item,parent,false);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.activity_area_enterprise_cv);
        checkBox.setText(data.get(position).getValue());
        checkBox.setTextColor(context.getResources().getColor(R.color.white));
        Drawable drawableHomepage = context.getResources().getDrawable(R.drawable.checkbox_style);
        drawableHomepage.setBounds(0, 0, 40, 40);//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        checkBox.setCompoundDrawables(drawableHomepage, null, null, null);
        checkBox.setTag(position);
        checkBox.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position = (int) buttonView.getTag();
        if (isChecked) {
            //如果被选中时还没有元素 直接添加
            dataPosition.add(position);
        }else{
            //判断 如果添加过那么就删除
            for (int i = 0; i <dataPosition.size() ; i++) {
                if (dataPosition.get(i)==position) {
                    dataPosition.remove(i);
                }
            }
        }
        if (areaEnterpriseActivity!=null) {
            areaEnterpriseActivity.getCheckedPosition(dataPosition);
        }else{
            enterpriseQueryActivity.getCheckedPosition(dataPosition);
        }

    }
}
