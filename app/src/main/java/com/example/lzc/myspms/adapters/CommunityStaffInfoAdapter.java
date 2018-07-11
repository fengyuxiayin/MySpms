package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.CommunityStaffInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.Position;
import com.example.lzc.myspms.utils.NetUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class CommunityStaffInfoAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = CommunityStaffInfoAdapter.class.getSimpleName();
    private List<CommunityStaffInfoModel.CommunityStaffInfoMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private String lxdh;
    //存储职称信息的列表
    private List<Position> positionList = new ArrayList<>();

    public CommunityStaffInfoAdapter(List<CommunityStaffInfoModel.CommunityStaffInfoMsgModel.ListBean> data, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.fragment_community_staff_info_item, parent, false);
        TextView tvName = (TextView) view.findViewById(R.id.fragment_community_staff_info_item_name);
        final TextView tvPosition = (TextView) view.findViewById(R.id.fragment_community_staff_info_item_position);
        ImageView imgOperate = (ImageView) view.findViewById(R.id.fragment_community_staff_info_item_opreate);
        imgOperate.setOnClickListener(this);
        imgOperate .setTag(position);
        tvName.setText(data.get(position).getRymc());
        Log.e(TAG, "getView: " + data.get(position).getRyzc());
        //从服务器获取职业职称类型的枚举数据
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/enum/getEnums")
                .addParams("code", "JOB_TITLE_TYPE")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(context,e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if (jsonArray != null) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                                    positionList.add(new Position((String) jsonObject.opt("key"), (String) jsonObject.opt("value")));
                                }
                            }
                            //循环判断当前数据源中的类型与职称列表中哪条相等
                            if (positionList.size() > 0) {
                                for (int i = 0; i < positionList.size(); i++) {
                                    if (positionList.get(i).getKey().equals(data.get(position).getRyzc() + "")) {
                                        tvPosition.setText(positionList.get(i).getValue());
                                        break;
                                    }
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return view;
    }

    @Override
    public void onClick(View v) {
        //拨打电话
        int position = (int) v.getTag();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.get(position).getLxdh()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
