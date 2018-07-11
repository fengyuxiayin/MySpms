package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

public static final String TAG = UploadActivity.class.getSimpleName();
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initView();

    }


    private void initView() {
        webView = (WebView) findViewById(R.id.wv);
        //解决点击链接跳转浏览器问题
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {

                UploadActivity.this.runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        request.grant(request.getResources());
                    }// run
                });// VideoCallActivity
            }// onPermissionRequest

        });

        //js支持
        WebSettings settings = webView.getSettings();
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //允许访问assets目录
        settings.setAllowFileAccess(true);
        //设置WebView排版算法, 实现单列显示, 不允许横向移动
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //
        settings.setDomStorageEnabled(true);
        //
        settings.setJavaScriptEnabled(true);
//        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setUseWideViewPort(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        //
//        int screenDensity = getResources().getDisplayMetrics().densityDpi ;
//        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM ;
//        switch (screenDensity){
//            case DisplayMetrics.DENSITY_LOW :
//                zoomDensity = WebSettings.ZoomDensity.CLOSE;
//                break;
//            case DisplayMetrics.DENSITY_MEDIUM:
//                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
//                break;
//            case DisplayMetrics.DENSITY_HIGH:
//                zoomDensity = WebSettings.ZoomDensity.FAR;
//                break ;
//        }
//        settings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
////        // 设置出现缩放工具
//        webView.getSettings().setBuiltInZoomControls(true);
        // 为图片添加放大缩小功能
//        webView.getSettings().setUseWideViewPort(true);
//        webView.setInitialScale(25);
//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        //服务器文件路径
//        final String path = "https://192.168.3.19:8080/mobile/login/login";
//        final String path = "https://192.168.3.19:443/view/video/video_p.html";

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                Log.e(TAG, "onScaleChanged: "+"old"+oldScale+"new "+newScale  );
            }
        });
//        webView.loadUrl("https://120.25.251.167:1443");
        webView.loadUrl("http://192.168.3.66:8080");

    }

    @Override
    public void onClick(View v) {

    }
}
