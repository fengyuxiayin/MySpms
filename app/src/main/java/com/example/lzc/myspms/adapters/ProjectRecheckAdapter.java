package com.example.lzc.myspms.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.CustomScanActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys.CheckCatalogActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.newcheckactivitys.CheckItemsActivity;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ViewProjectActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.checkrecordactivitys.CheckRecordItemActivity;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.ProjectByJcIdModel;
import com.example.lzc.myspms.models.ProjectDataModel;
import com.example.lzc.myspms.models.ProjectInfoModel;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/11/6.
 */
public class ProjectRecheckAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String TAG = "ProjectRecheckAdapter";
    private Context context;
    private LayoutInflater inflater;
    private List<ProjectByJcIdModel.ProjectByJcIdMsgModel.ListBean> data;
    private int position;
    private boolean isView;
    private int POSITION = 1;
    private int ISVIEW = 2;
    //判断是那个类型，对应显示编辑 二维码 查看
    private int xmlx;
    //周期记录id 下一个Activity需要
    private String zqjlId;
    //检查id
    private String jcId;
    //修改的时候 jcIdChange = jcId  查看的时候jcIdChange = ""
    private String jcIdChange;
    private Gson gson;
    private Activity activity;

    public ProjectRecheckAdapter(List<ProjectByJcIdModel.ProjectByJcIdMsgModel.ListBean> data, Context context, Boolean isView, int xmlx, String zqjlId, String jcId) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
        this.isView = isView;
        this.inflater = LayoutInflater.from(context);
        this.xmlx = xmlx;
        this.zqjlId = zqjlId;
        this.activity = (Activity) context;
        this.jcId = jcId;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if ("1".equals(data.get(position).getJcjg())) {
            isView = true;
        } else {
            isView = false;
        }
//        if (!isView) {
//            Log.e(TAG, "getView: false" );
//            //正常情况下
//            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.fragment_project_item, parent, false);
//                holder = new ViewHolder(convertView);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            holder.tvProjectName.setText(data.get(position).getXmmc());
//            holder.ivProjectEdit.setTag(position);
//            holder.ivProjectEdit.setOnClickListener(this);
//            holder.ivProjectDelete.setTag(position);
//            holder.ivProjectDelete.setOnClickListener(this);
//            //根据项目类型生成bitmap
//            Bitmap bitmap = null;
//            switch (xmlx) {
//                case 359:
//                    bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.qrcode);
//                    break;
//                case 56:
//                    bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.basic_info);
//                    break;
//                case 1:
//                    bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.basic_info);
//                    break;
//                case 167:
//                    bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.basic_info);
//                    break;
//                case 76:
//                    bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.qrcode);
//                    break;
//            }
//            Log.e(TAG, "getView: "+data.get(position).getJcjg() );
//            //根据检查结果设置状态图片 null 未检查 0 检查不合格 1检查合格
//            if (data.get(position).getJcjg()==null) {
//                holder.ivProjectStatus.setImageResource(R.mipmap.uncheck);
//                holder.ivProjectEdit.setImageBitmap(bitmap);
//            } else if (data.get(position).getJcjg().equals("0")) {
//                holder.ivProjectEdit.setImageBitmap(bitmap);
//                holder.ivProjectStatus.setImageResource(R.mipmap.check_unqualified);
//            } else{
//                bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.view);
//                holder.ivProjectEdit.setImageBitmap(bitmap);
//                holder.ivProjectStatus.setImageResource(R.mipmap.check_qualified);
//            }
//
//        } else {
        //查看项目
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_project_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//根据项目类型生成bitmap
        Bitmap bitmap = null;
        switch (xmlx) {
            case 359:
                if ("1".equals(data.get(position).getJcjg())) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.view);
                    holder.ivProjectDelete.setVisibility(View.INVISIBLE);
                } else {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.qrcode);
                }
                break;
            case 56:
                if ("1".equals(data.get(position).getJcjg())) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.view);
                    holder.ivProjectDelete.setVisibility(View.INVISIBLE);
                } else {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.basic_info);
                }
                break;
            case 1:
                if ("1".equals(data.get(position).getJcjg())) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.view);
                    holder.ivProjectDelete.setVisibility(View.INVISIBLE);
                } else {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.basic_info);
                }
                break;
            case 167:
                if ("1".equals(data.get(position).getJcjg())) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.view);
                    holder.ivProjectDelete.setVisibility(View.INVISIBLE);
                } else {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.basic_info);
                }
                break;
            case 76:
                if ("1".equals(data.get(position).getJcjg())) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.view);
                    holder.ivProjectDelete.setVisibility(View.INVISIBLE);
                } else {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.qrcode);
                }
                break;
        }
        holder.ivProjectEdit.setImageBitmap(bitmap);
        //根据检查结果设置状态图片 null 未检查 0 检查不合格 1检查合格
        Log.e(TAG, "getView: "+data.get(position).getJcjg() );
        if (data.get(position).getJcjg() == 0) {
            holder.ivProjectStatus.setImageResource(R.mipmap.check_unqualified);
        } else if (data.get(position).getJcjg() == 1) {
            holder.ivProjectStatus.setImageResource(R.mipmap.check_qualified);
        } else {
            holder.ivProjectStatus.setImageResource(R.mipmap.uncheck);
        }
        //设置项目名称
        holder.tvProjectName.setText(data.get(position).getXmmc());
        holder.ivProjectEdit.setTag(R.id.tag_first, position);
        holder.ivProjectEdit.setTag(R.id.tag_second, isView);
        holder.ivProjectEdit.setOnClickListener(this);
        holder.ivProjectDelete.setTag(position);
        holder.ivProjectDelete.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent();
        gson = new Gson();
        if (v != null) {
            switch (v.getId()) {
                case R.id.fragment_project_edit:
                    final int position = (int) v.getTag(R.id.tag_first);
                    boolean isView = (boolean) v.getTag(R.id.tag_second);
                    if (isView) {
                        intent.setClass(context, ViewProjectActivity.class);
                        intent.putExtra("jcxId", data.get(position).getId() + "");
                        context.startActivity(intent);
                    } else {
                        if (xmlx == 359 || xmlx == 76) {
                            //跳转到扫描二维码界面
                            new IntentIntegrator(activity)
                                    .setOrientationLocked(false)
                                    .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
                                    .initiateScan();
                            Log.e(TAG, "onClick: "+activity.getClass().getSimpleName() );
                            if ("CheckRecordItemActivity".equals(activity.getClass().getSimpleName())) {
                                //把检查项目id传出去与扫描二维码获得的id作比较，相同才跳转
                                CheckRecordItemActivity activity = (CheckRecordItemActivity) this.activity;
                                activity.getJcxmId(data.get(position).getJcxmId()+"");
                            }else{//CheckCatalogActivity
                                CheckCatalogActivity activity = (CheckCatalogActivity) this.activity;
                                activity.getJcxmId(data.get(position).getJcxmId()+"");
                            }
                        } else {
                            getProjectDetailData(intent, position);
                        }
                    }

                    break;
                case R.id.fragment_project_delete:
                    final int pos = (int) v.getTag();
                    //    通过AlertDialog.Builder这个类来实例化一个AlertDialog的对象
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    //    设置Title的内容
                    builder.setTitle("提示");
                    //    设置Content来显示一个信息
                    builder.setMessage("确定删除" + data.get(pos).getXmmc() + "项目的所有信息吗");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //删除项目信息
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/project/delete")
                                    .addParams("id", data.get(pos).getJcxmId() + "")
                                    .build().execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    Log.e(TAG, "onError: " + e.getMessage());
                                    NetUtil.errorTip(context, e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: " + response);
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    if (infoModel.isData()) {
                                        data.remove(pos);
                                        notifyDataSetChanged();
                                    } else {

                                    }
                                    Toast.makeText(context, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void getProjectDetailData(final Intent intent, final int position) {
        Log.e(TAG, "getProjectDetailData: "+data.get(position).getId() + ""+jcId );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/findByJcIdAndJcxmId")
                .addParams("jcxmId", data.get(position).getJcxmId() + "")
                .addParams("jcId", jcId)
                .build()
                .execute(new StringCallback() {
                    private ProjectInfoModel.ProjectInfoMsgModel projectInfoMsgModel;
                    private ProjectInfoModel projectInfoModel;
                    private LoginInfoModel loginInfoModel;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(context, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse:asdawasdawdasdawdsaawd " + response);
                        projectInfoModel = gson.fromJson(response, ProjectInfoModel.class);
                        if (projectInfoModel.isData()) {
                            projectInfoMsgModel = gson.fromJson(projectInfoModel.getMsg(), ProjectInfoModel.ProjectInfoMsgModel.class);
                            Log.e(TAG, "onResponse: projectrecheckadapter" +"jcid"+ data.get(position).getJcId()+jcId + "zqjlId"+zqjlId+"jcxmId"+data.get(position).getJcxmId() + "");
                            intent.putExtra("zqjlId", zqjlId);
                            intent.putExtra("jcxmId", data.get(position).getJcxmId() + "");
                            intent.putExtra("jcId", jcId);
                            intent.putExtra("jcxId", projectInfoMsgModel.getId() + "");
                            intent.putExtra("qyId", data.get(position).getQyId() + "");
                            intent.putExtra("status", isView ? "1.0" : data.get(position).getJcjg() == 1 ? "1.0" : "");
                            intent.setClass(context, CheckItemsActivity.class);
                            context.startActivity(intent);
                        } else {
                            OkHttpUtils.post()
                                    .url(Constant.SERVER_URL + "/checkProject/save")
                                    .addParams("jcxmId", data.get(position).getJcxmId() + "")
                                    .addParams("jcId", jcId)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            Log.e(TAG, "onError: " + e.getMessage());
                                            NetUtil.errorTip(context, e.getMessage());
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Log.e(TAG, "onResponse: save" + response);
                                            projectInfoModel = gson.fromJson(response, ProjectInfoModel.class);
                                            if (projectInfoModel.isData()) {
                                                OkHttpUtils.post()
                                                        .url(Constant.SERVER_URL + "/checkProject/findByJcIdAndJcxmId")
                                                        .addParams("jcxmId", data.get(position).getJcxmId() + "")
                                                        .addParams("jcId", jcId)
                                                        .build()
                                                        .execute(new StringCallback() {
                                                            @Override
                                                            public void onError(Request request, Exception e) {
                                                                NetUtil.errorTip(context,e.getMessage());
                                                            }

                                                            @Override
                                                            public void onResponse(String response) {
                                                                projectInfoModel = gson.fromJson(response, ProjectInfoModel.class);
                                                                if (projectInfoModel.isData()) {
                                                                    projectInfoMsgModel = gson.fromJson(projectInfoModel.getMsg(), ProjectInfoModel.ProjectInfoMsgModel.class);
                                                                    Log.e(TAG, "onResponse: projectrecheckadapter" + data.get(position).getJcId() + "");
                                                                    intent.putExtra("zqjlId", zqjlId);
                                                                    intent.putExtra("jcxmId", data.get(position).getJcxmId() + "");
                                                                    intent.putExtra("jcId", jcId);
                                                                    intent.putExtra("jcxId", projectInfoMsgModel.getId() + "");
                                                                    intent.putExtra("qyId", data.get(position).getQyId() + "");
                                                                    intent.putExtra("status", isView ? "1.0" : data.get(position).getJcjg() == 1 ? "1.0" : "");
                                                                    intent.setClass(context, CheckItemsActivity.class);
                                                                    context.startActivity(intent);
                                                                }else{
                                                                    Toast.makeText(context, projectInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });

                                            } else {
                                                Toast.makeText(context, "保存检查点信息失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    public class ViewHolder {
        TextView tvProjectName;
        ImageView ivProjectStatus;
        ImageView ivProjectEdit;
        ImageView ivProjectDelete;
        ImageView ivProjectView;

        public ViewHolder(View itemView) {
            tvProjectName = (TextView) itemView.findViewById(R.id.fragment_project_project_name);
            ivProjectStatus = (ImageView) itemView.findViewById(R.id.fragment_project_status);
            ivProjectEdit = (ImageView) itemView.findViewById(R.id.fragment_project_edit);
            ivProjectDelete = (ImageView) itemView.findViewById(R.id.fragment_project_delete);
        }

//        public ViewHolder(View itemView, String isView) {
//            tvProjectName = (TextView) itemView.findViewById(R.id.fragment_homepage_viewproject_project_name);
//            ivProjectStatus = (ImageView) itemView.findViewById(R.id.fragment_homepage_viewproject_status);
//            ivProjectView = (ImageView) itemView.findViewById(R.id.fragment_homepage_viewproject_view);
//        }
    }
}
