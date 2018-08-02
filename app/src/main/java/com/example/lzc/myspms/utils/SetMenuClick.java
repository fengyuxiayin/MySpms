package com.example.lzc.myspms.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.AddEnterpriseSimpleActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.NoticeActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.SendMessageActivity;
import com.example.lzc.myspms.adapters.SimpleArrayAdapter;
import com.example.lzc.myspms.avchats.AVChatActivity;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.NeteaseAccountFindModel;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2018/6/22.
 */

public class SetMenuClick {
    public static final String TAG = SetMenuClick.class.getSimpleName();
    private int viewId;
    private Context context;
    private Activity activity;
    private Intent intent;
    private ArrayAdapter<String> adapterLimit;


    public SetMenuClick(int viewId, Context context, Activity activity) {
        this.viewId = viewId;
        this.context = context;
        this.activity = activity;
    }

    public void setMenuClick() {
        Log.e(TAG, "setMenuClick: ");
        switch (viewId) {
            case R.id.back:
                activity.finish();
                break;
            case R.id.videocall:
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL + "/neteaseAccount/find")
                        .addParams("inUse", "1")
                        .addParams("pn", "1")
                        .addParams("size", "1000")
                        .build()
                        .execute(new StringCallback() {
                            private ImageView imgVideocall;
                            private Button btn;
                            private Spinner spinner;

                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: " + response);
                                Gson gson = new Gson();
                                NeteaseAccountFindModel neteaseAccountFindModel = gson.fromJson(response, NeteaseAccountFindModel.class);
                                if (neteaseAccountFindModel.isData()) {
                                    NeteaseAccountFindModel.NeteaseAccountFindMsgModel neteaseAccountFindMsgModel = gson.fromJson(neteaseAccountFindModel.getMsg(), NeteaseAccountFindModel.NeteaseAccountFindMsgModel.class);
                                    final List<NeteaseAccountFindModel.NeteaseAccountFindMsgModel.ListBean> list = neteaseAccountFindMsgModel.getList();
                                    PopupWindow popupWindow = new PopupWindow();
                                    View contentView = View.inflate(activity, R.layout.popup_videocall, null);
                                    imgVideocall = (ImageView) activity.findViewById(R.id.videocall);
//                                    spinner = (Spinner) contentView.findViewById(R.id.popup_videocall_sp);
                                    btn = (Button) contentView.findViewById(R.id.popup_videocall_btn);
                                    List<String> limitList = new ArrayList<>();
                                    if (list!=null) {
                                        for (int i = 0; i < list.size(); i++) {
                                            limitList.add(list.get(i).getShowName());
                                        }
                                    }
                                    Log.e(TAG, "onResponse: "+limitList.size()+limitList.get(0) );
//                                    adapterLimit = new ArrayAdapter<>(activity, R.layout.textview_only, limitList);
//                                    adapterLimit.setDropDownViewResource(R.layout.spinner_item_textview);
//                                    spinner.setAdapter(adapterLimit);
//                                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                        @Override
//                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                            Constant.WANGYIYUN_ACCOUNT = list.get(position).getAccid();
//                                            Constant.WANGYIYUN_DISPLAYNAME = list.get(position).getName();
//                                        }
//
//                                        @Override
//                                        public void onNothingSelected(AdapterView<?> parent) {
//
//                                        }
//                                    });
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            intent = new Intent();
                                            intent.setClass(activity, VideoCallActivity.class);
                                            activity.startActivity(intent);
                                        }
                                    });
                                    popupWindow = new PopupWindow();
                                    popupWindow.setWidth((int) (activity.getWindowManager().getDefaultDisplay().getWidth() * (0.5)));
                                    popupWindow.setHeight((int) (activity.getWindowManager().getDefaultDisplay().getHeight() * 0.3));
                                    popupWindow.setContentView(contentView);
                                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                                    popupWindow.setOutsideTouchable(true);
                                    popupWindow.setFocusable(true);
                                    popupWindow.showAtLocation(imgVideocall, Gravity.CENTER, 0, 0);
                                }
                            }
                        });

//                Toast.makeText(context, "功能正在开发...", Toast.LENGTH_SHORT).show();
                return;
            case R.id.message:
                intent = new Intent();
                intent.putExtra("isSend", true);
                intent.putExtra("title", "发送邮件");
                intent.setClass(activity, SendMessageActivity.class);
                activity.startActivity(intent);
                break;
            case R.id.notice:
                intent = new Intent(activity, NoticeActivity.class);
                activity.startActivity(intent);
                break;
            case R.id.call:
                Uri uri = Uri.parse("content://contacts/people");
                intent = new Intent(Intent.ACTION_PICK, uri);
                activity.startActivity(intent);
                break;
            case R.id.add:
                intent = new Intent();
                intent.setClass(activity, AddEnterpriseSimpleActivity.class);
                intent.putExtra("which", "add");
                activity.startActivity(intent);
                break;
        }

    }
}
