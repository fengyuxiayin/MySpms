package com.example.lzc.myspms.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.AnalysisCommunityDataActivity;
import com.example.lzc.myspms.models.FindCheckInfoModel;
import com.example.lzc.myspms.models.MyInfoModel;
import com.example.lzc.myspms.utils.DateUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/10/30.
 */
public class CommunityCheckRecordAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = CommunityCheckRecordAdapter.class.getSimpleName();
    private List<FindCheckInfoModel.FindCheckInfoMsgModel.ListBean> data;
    private LayoutInflater inflater;
    private Context context;
    private StringBuilder rymc;
    private String which;
    private String qyId;
    private String sqId;

    //which  判断 是企业 社区 还是小组
    public CommunityCheckRecordAdapter(List<FindCheckInfoModel.FindCheckInfoMsgModel.ListBean> data, Context context, String which, String qyId,String sqId) {
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.which = which;
        this.qyId = qyId;
        this.sqId = sqId;
    }

    @Override
    public int getCount() {
        Log.e(TAG, "getCount: ");
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
        View view = inflater.inflate(R.layout.fragment_community_check_record_item, parent, false);
        Log.e("CommunityCheckRecord", "getView: ");
        TextView tvDate = (TextView) view.findViewById(R.id.fragment_community_check_record_item_tv_date);
        TextView tvTask = (TextView) view.findViewById(R.id.fragment_community_check_record_item_tv_task);
        TextView tvCehck = (TextView) view.findViewById(R.id.fragment_community_check_record_item_tv_check);
        TextView tvNumber = (TextView) view.findViewById(R.id.fragment_community_check_record_item_tv_number);
        ImageView imgEdit = (ImageView) view.findViewById(R.id.fragment_community_check_record_item_img_edit);
        tvDate.setText(DateUtil.long2Date(data.get(position).getKssj()));
        tvNumber.setText(data.get(position).getUnPassCount() + "");
        tvTask.setText(data.get(position).getRwmc());
//        if (data.get(position).getJcry_json()==null) {
        tvCehck.setText(data.get(position).getJcdwmc());
//        }else{
//            try {
//                JSONArray jsonArray = new JSONArray(data.get(position).getJcry_json());
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject o = (JSONObject) jsonArray.get(i);
//                    String rymc1 = o.optString("rymc");
//                    rymc.append(rymc1+" ");
//                }
//                tvCehck.setText(rymc);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        imgEdit.setOnClickListener(this);
        imgEdit.setTag(position);
        return view;
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        if (v != null) {
            Intent intent = new Intent();
            intent.putExtra("jcdwlx", data.get(pos).getJcdwlx() + "");
            intent.putExtra("jcdwId", data.get(pos).getJcdwId() + "");
            intent.putExtra("tags", data.get(pos).getTags() + "");
            intent.putExtra("rwmc", data.get(pos).getRwmc());
            intent.putExtra("jcry", data.get(pos).getJcdwmc());
            intent.putExtra("jcrq", DateUtil.long2Date(data.get(pos).getKssj()));
            intent.putExtra("jcdwmc", data.get(pos).getJcdwmc());
            intent.putExtra("which", this.which);
            intent.putExtra("qyId", qyId);
            intent.putExtra("sqId",sqId);
            intent.setClass(context, AnalysisCommunityDataActivity.class);
            context.startActivity(intent);
        }
    }
}
