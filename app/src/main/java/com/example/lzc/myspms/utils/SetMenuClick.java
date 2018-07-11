package com.example.lzc.myspms.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.AddEnterpriseSimpleActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.NoticeActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.SendMessageActivity;
import com.example.lzc.myspms.models.Constant;

/**
 * Created by LZC on 2018/6/22.
 */

public class SetMenuClick {
    public static final String TAG = SetMenuClick.class.getSimpleName();
    private int viewId;
    private Context context;
    private Activity activity;
    public SetMenuClick(int viewId, Context context,Activity activity) {
        this.viewId = viewId;
        this.context = context;
        this.activity =activity;
    }

    public void setMenuClick() {
        Log.e(TAG, "setMenuClick: " );
        Intent intent;
        switch (viewId) {
            case R.id.back:
                activity.finish();
                break;
            case R.id.videocall:
//                intent = new Intent();
//                intent.setClass(activity, VideoCallActivity.class);
//                activity.startActivity(intent);
                Toast.makeText(context, "功能正在开发...", Toast.LENGTH_SHORT).show();
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
