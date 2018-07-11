package com.example.lzc.myspms.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.custom.LoadingState;
import com.example.lzc.myspms.custom.XHLoadingView;

/**
 * Created by LZC on 2017/12/13.
 */

public class XhlLoadingUtil {
    /**
     *
     *@desc 在请求信息的onBefore中显示xhlLoading
     *@param xhLoadingView 需要显示的控件
     *@date 2017/12/13 9:40
    */
    public static void showXhlLoadingOnBefore(XHLoadingView xhLoadingView){
        xhLoadingView.withLoadingIcon(R.drawable.loading_animation).withEmptyIcon(R.mipmap.lost_page).withLoadErrorText("网页连接出错了").withLoadingText("加载中...").withOnRetryListener(new XHLoadingView.OnRetryListener() {
            @Override
            public void onRetry() {
            }
        }).build();
        xhLoadingView.setVisibility(View.VISIBLE);
        xhLoadingView.setState(LoadingState.STATE_LOADING);
    }
    /**
     *
     *@desc 在请求信息的onBefore中显示xhlLoading
     *@param e 传进来的错误信息
     *@param context 上下文对象
     *@date 2017/12/13 9:43
    */
    public static void showXhlLoadingOnError(Exception e, Context context,String tag,XHLoadingView xhLoadingView){
        Log.e(tag, "onError: " + e.getMessage());
        if (("timeout").equals(e.getMessage())) {
            Toast.makeText(context, "连接超时，请稍后重试", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "网页连接出错了...", Toast.LENGTH_SHORT).show();
        }
        xhLoadingView.setState(LoadingState.STATE_ERROR);
    }
}
