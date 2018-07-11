package com.example.lzc.myspms.activitys;

import android.Manifest;
import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.avchats.AVChatAudioUI;
import com.example.lzc.myspms.avchats.AVChatController;
import com.example.lzc.myspms.avchats.AVChatSoundPlayer;
import com.example.lzc.myspms.avchats.AVChatVideoUI;
import com.example.lzc.myspms.avchats.CallStateEnum;
import com.example.lzc.myspms.avchats.SimpleAVChatStateObserver;
import com.example.lzc.myspms.utils.JsInterface;
import com.example.lzc.myspms.utils.PermissionUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.avchat.AVChatCallback;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.AVChatManagerLite;
import com.netease.nimlib.sdk.avchat.AVChatStateObserver;
import com.netease.nimlib.sdk.avchat.constant.AVChatEventType;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.constant.AVChatVideoScalingType;
import com.netease.nimlib.sdk.avchat.model.AVChatAudioFrame;
import com.netease.nimlib.sdk.avchat.model.AVChatCalleeAckEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatCameraCapturer;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.netease.nimlib.sdk.avchat.model.AVChatNetworkStats;
import com.netease.nimlib.sdk.avchat.model.AVChatNotifyOption;
import com.netease.nimlib.sdk.avchat.model.AVChatParameters;
import com.netease.nimlib.sdk.avchat.model.AVChatSessionStats;
import com.netease.nimlib.sdk.avchat.model.AVChatSurfaceViewRenderer;
import com.netease.nimlib.sdk.avchat.model.AVChatVideoCapturerFactory;
import com.netease.nimlib.sdk.avchat.model.AVChatVideoFrame;
import com.netease.nrtc.sdk.common.TextureViewRender;
import com.netease.nrtc.video.gl.GlCommon;
import com.netease.nrtc.video.render.RenderCommon;

import java.util.Map;

public class VideoCallActivity extends AppCompatActivity {
    public static final String TAG = VideoCallActivity.class.getSimpleName();
    private WebView mWebView;
    private JsInterface jsInterface;
    private AVChatCameraCapturer mVideoCapturer;
    private AVChatData avChatData;
    private View root; //音视频通话的界面
    private int state; // calltype 音频或视频 -1 是未知 1是音频 2是视频
    private String receiverId; // 对方的account
    private AVChatController avChatController;//音视频控制器 在这里不用控制，直接删去音频代码就好了
    private AVChatVideoUI avChatVideoUI;
    private View videoRoot; //video的界面，我也不知道啥样 应该就是下面的打电话挂电话的界面
    private View surfaceRoot; //显示视频的界面
    private String account = "mumuadmin";
    private boolean isCallEstablished = false; // 电话是否接通
    private SimpleAVChatStateObserver avchatStateObserver = new SimpleAVChatStateObserver() {
        @Override
        public void onAVRecordingCompletion(String account, String filePath) {
        }

        @Override
        public void onAudioRecordingCompletion(String filePath) {

        }

        @Override
        public void onLowStorageSpaceWarning(long availableSize) {

        }

        @Override
        public void onJoinedChannel(int code, String audioFile, String videoFile, int i) {

        }

        @Override
        public void onUserJoined(String account) {

        }

        @Override
        public void onUserLeave(String account, int event) {

        }

        @Override
        public void onCallEstablished() {
            Log.e(TAG, "onCallEstablished: " );
            if (avChatController.getTimeBase() == 0)
                avChatController.setTimeBase(SystemClock.elapsedRealtime());

                // 接通以后，自己是小屏幕显示图像，对方是大屏幕显示图像
                avChatVideoUI.initSmallSurfaceView(account);
                avChatVideoUI.doOutgoingCall(account);
                avChatVideoUI.showVideoInitLayout();

            isCallEstablished = true;
        }

        @Override
        public boolean onVideoFrameFilter(AVChatVideoFrame frame, boolean maybeDualInput) {


            return true;
        }

        @Override
        public boolean onAudioFrameFilter(AVChatAudioFrame frame) {
            return true;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);
        permissionRequest();
//        initView();
        root = LayoutInflater.from(this).inflate(R.layout.avchat_activity, null);
        setContentView(root);
        state = 2;
        receiverId = "test02";
        initData();
        showUI();
        //激活音视频通话底层引擎
        AVChatManagerLite.getInstance().enableRtc();
        //激活视频模块
        AVChatManagerLite.getInstance().enableVideo();
        //创建视频捕获器
        AVChatCameraCapturer cameraCapturer = AVChatVideoCapturerFactory.createCameraCapturer();
        boolean b = AVChatManager.getInstance().setupLocalVideoRender(new AVChatSurfaceViewRenderer(VideoCallActivity.this), false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
//        Log.e(TAG, "onCreate: "+b );

        //设置视频捕获器
        AVChatManagerLite.getInstance().setupVideoCapturer(cameraCapturer);
        //开启视频预览
        AVChatManagerLite.getInstance().startVideoPreview();
        //
        AVChatNotifyOption notifyOption = new AVChatNotifyOption();
        //是否兼容WebRTC模式
        notifyOption.extendMessage="extra";
        //发起通话
        AVChatManagerLite.getInstance().call2(account, AVChatType.VIDEO, notifyOption, new AVChatCallback<AVChatData>() {
            @Override
            public void onSuccess(AVChatData avChatData) {
                Log.e(TAG, "onSuccess: " );
                Log.e(TAG, "onSuccess: "+avChatData.getAccount() );
                avChatController = new AVChatController(VideoCallActivity.this, avChatData);
                avChatVideoUI = new AVChatVideoUI(VideoCallActivity.this, root, avChatData, account, avChatController, null, null);
            }

            @Override
            public void onFailed(int code) {
                Log.e(TAG, "onFailed:1111111111111"+"code "+code );
            }

            @Override
            public void onException(Throwable exception) {
                Log.e(TAG, "onException:11111111111 "+exception );
            }
        });
        Observer<AVChatCalleeAckEvent> callAckObserver = new Observer<AVChatCalleeAckEvent>() {
            @Override
            public void onEvent(AVChatCalleeAckEvent ackInfo) {
                if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_BUSY) {
                    // 对方正在忙
                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_REJECT) {
                    // 对方拒绝接听
                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE) {
                    // 对方同意接听
                    Log.e(TAG, "onEvent: 同意接听" );
                    avChatController.isCallEstablish.set(true);
                }
            }
        };
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, true);
        AVChatManager.getInstance().observeAVChatState(avchatStateObserver,true);

    }

    /**
     * @desc 初始化音视频控制器
     * @date 2018/4/24 16:50
     */
    private void initData() {
        avChatController = new AVChatController(this, avChatData);
        avChatVideoUI = new AVChatVideoUI(this, root, avChatData, "测试的名字哦", avChatController, null, null);//后面两个参数一个是点击右上角小框的切换的回调 一个是音视频切换的监听
    }

    /**
     * @desc 展示视频通话的界面
     * @date 2018/4/24 16:55
     */
    private void showUI() {
        videoRoot = root.findViewById(R.id.avchat_video_layout);
        surfaceRoot = root.findViewById(R.id.avchat_surface_layout);
        // 视频
        videoRoot.setVisibility(View.VISIBLE);
        surfaceRoot.setVisibility(View.VISIBLE);
        // 去电
        AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.CONNECTING);
        avChatVideoUI.doOutgoingCall(receiverId);
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.activity_video_call_wv);
        //解决点击链接跳转浏览器问题
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {

                VideoCallActivity.this.runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        request.grant(request.getResources());
                    }// run
                });// VideoCallActivity
            }// onPermissionRequest

        });

        //js支持
        WebSettings settings = mWebView.getSettings();
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //允许访问assets目录
        settings.setAllowFileAccess(true);
        //设置WebView排版算法, 实现单列显示, 不允许横向移动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //
        settings.setDomStorageEnabled(true);
        //
        settings.setJavaScriptEnabled(true);
//        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //服务器文件路径
//        final String path = "https://192.168.3.19:8080/mobile/login/login";
//        final String path = "https://192.168.3.19:443/view/video/video_p.html";

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        jsInterface = new JsInterface(VideoCallActivity.this);
        mWebView.addJavascriptInterface(jsInterface, "AndroidWebView");
        mWebView.loadUrl("https://27.223.106.182:9443/mobile/basePage/call");
    }

    @Override
    public void onPause() {
        super.onPause();
//        mWebView.onPause();
//        mWebView.pauseTimers();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mWebView.resumeTimers();
//        mWebView.onResume();
    }


    @Override
    protected void onDestroy() {
//        mWebView.destroy();
//        mWebView = null;
        super.onDestroy();
    }

    /**
     * @desc 获取权限
     * @date 2018/4/9 18:47
     */
    private void permissionRequest() {
        String[] array = new String[4];
        array[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        array[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
        array[2] = Manifest.permission.CAMERA;
        array[3] = Manifest.permission.RECORD_AUDIO;
        PermissionUtil.checkAndRequestMorePermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO},2);
    }
}
