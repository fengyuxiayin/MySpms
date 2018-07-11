package com.example.lzc.myspms.utils;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.lzc.myspms.activitys.VideoCallActivity;

import java.util.Timer;

/**
 * Created by LZC on 2018/4/9.
 */

public class JsInterface {
    public static final String TAG = JsInterface.class.getSimpleName();
    private VideoCallActivity activity = null;

    public JsInterface(VideoCallActivity activity) {
        this.activity = activity;
    }

    //点击js的返回键销毁掉当前activity
    @JavascriptInterface
    public void returnPrevious() {
        Log.e(TAG, "returnPrevious: " );
        activity.finish();
    }

}
