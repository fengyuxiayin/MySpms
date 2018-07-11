package com.example.lzc.myspms.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.AddEnterpriseSimpleActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.NoticeActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.SendMessageActivity;
import com.example.lzc.myspms.adapters.PopupMainAdapter;

/**
 * Created by LZC on 2018/6/14.
 */

public class ShowMenuPopup {
    public static final String TAG = ShowMenuPopup.class.getSimpleName();
    private Activity activity;
    private View contentView;
    private PopupWindow popupWindow;
    private View view;
    private boolean isSendMessageActivity;
    //菜单按钮的文字内容
    private String[] titleArray = new String[]{"添加企业", "普通通话", "视频通话", "消息查看", "发送消息"};
    //菜单按钮的图片资源
    private Integer[] imgArray = new Integer[]{R.mipmap.add_enterprise, R.mipmap.call, R.mipmap.videocall, R.mipmap.message, R.mipmap.send_message};

    public ShowMenuPopup(Activity activity, View view,boolean isSendMessageActivity) {
        this.activity = activity;
        this.view = view;
        this.isSendMessageActivity = isSendMessageActivity;
    }

    public void showpopup() {
        if (!isSendMessageActivity) {
            titleArray = new String[]{"添加企业", "普通通话", "视频通话", "消息查看"};
            imgArray = new Integer[]{R.mipmap.add_enterprise, R.mipmap.call, R.mipmap.videocall, R.mipmap.message};
        }
        //弹出popupwindow
        contentView = View.inflate(activity, R.layout.popup_main_menu, null);
        ListView listView = (ListView) contentView.findViewById(R.id.popup_main_menu_lv);
        PopupMainAdapter popupMainAdapter = new PopupMainAdapter(titleArray, imgArray, activity);
        listView.setAdapter(popupMainAdapter);
        popupWindow = new PopupWindow();
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setWidth((int) (activity.getWindowManager().getDefaultDisplay().getWidth() * 0.2));
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.showAsDropDown(view, 0, 0, Gravity.RIGHT);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0://添加企业信息
                        intent = new Intent();
                        intent.setClass(activity, AddEnterpriseSimpleActivity.class);
                        intent.putExtra("which", "add");
                        activity.startActivity(intent);
                        popupWindow.dismiss();
                        break;
                    case 1://普通通话
                        Uri uri = Uri.parse("content://contacts/people");
                        intent = new Intent(Intent.ACTION_PICK, uri);
                        activity.startActivity(intent);
                        popupWindow.dismiss();
                        break;
                    case 2://视频通话
                        intent = new Intent();
                        intent.setClass(activity, VideoCallActivity.class);
                        activity.startActivity(intent);
                        popupWindow.dismiss();
                        break;
                    case 3:
                        intent = new Intent(activity, NoticeActivity.class);
                        activity.startActivity(intent);
                        popupWindow.dismiss();
                        break;
                    case 4:
                        intent = new Intent();
                        intent.putExtra("isSend", true);
                        intent.putExtra("title", "发送邮件");
                        intent.setClass(activity, SendMessageActivity.class);
                        activity.startActivity(intent);
                        break;
                }
            }
        });

    }
}
