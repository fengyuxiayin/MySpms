package com.example.lzc.myspms.activitys;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
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
import com.example.lzc.myspms.avchats.AVChatExitCode;
import com.example.lzc.myspms.avchats.AVChatKit;
import com.example.lzc.myspms.avchats.AVChatNotification;
import com.example.lzc.myspms.avchats.AVChatProfile;
import com.example.lzc.myspms.avchats.AVChatSoundPlayer;
import com.example.lzc.myspms.avchats.AVChatTimeoutObserver;
import com.example.lzc.myspms.avchats.AVChatVideoUI;
import com.example.lzc.myspms.avchats.CallStateEnum;
import com.example.lzc.myspms.avchats.SimpleAVChatStateObserver;
import com.example.lzc.myspms.models.Constant;
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
import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
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
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

import static com.example.lzc.myspms.avchats.AVChatActivity.FROM_BROADCASTRECEIVER;
import static com.example.lzc.myspms.avchats.AVChatActivity.FROM_INTERNAL;
import static com.example.lzc.myspms.avchats.AVChatActivity.FROM_UNKNOWN;

public class VideoCallActivity extends AppCompatActivity {
    public static final String TAG = VideoCallActivity.class.getSimpleName();
    private static final String KEY_IN_CALLING = "KEY_IN_CALLING";
    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    private static final String KEY_DISPLAY_NAME = "KEY_DISPLAY_NAME";
    private static final String KEY_CALL_TYPE = "KEY_CALL_TYPE";
    private static final String KEY_SOURCE = "source";
    private static final String KEY_CALL_CONFIG = "KEY_CALL_CONFIG";
    public static final String INTENT_ACTION_AVCHAT = "INTENT_ACTION_AVCHAT";
    private static boolean needFinish;
    private WebView mWebView;
    private JsInterface jsInterface;
    private AVChatCameraCapturer mVideoCapturer;
    private AVChatData avChatData;
    private View root; //音视频通话的界面
    private int state; // calltype 音频或视频 -1 是未知 1是音频 2是视频
    private String receiverId; //
    private AVChatController avChatController;//音视频控制器 在这里不用控制，直接删去音频代码就好了
    private AVChatVideoUI avChatVideoUI;
    private View videoRoot; //video的界面，我也不知道啥样 应该就是下面的打电话挂电话的界面
    private View surfaceRoot; //显示视频的界面
    private String account = Constant.WANGYIYUN_ACCOUNT; //对方的account
    private boolean isCallEstablished = false; // 电话是否接通
    private SimpleAVChatStateObserver avchatStateObserver = new SimpleAVChatStateObserver() {
        @Override
        public void onAVRecordingCompletion(String account, String filePath) {
            if (account != null && filePath != null && filePath.length() > 0) {
                String msg = "音视频录制已结束, " + "账号：" + account + " 录制文件已保存至：" + filePath;
                Toast.makeText(VideoCallActivity.this, msg, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(VideoCallActivity.this, "录制已结束.", Toast.LENGTH_SHORT).show();
            }
            avChatVideoUI.resetRecordTip();
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
            Log.e(TAG, "onUserJoined: ");
            avChatVideoUI.initLargeSurfaceView(account);

        }

        @Override
        public void onUserLeave(String account, int event) {

        }

        @Override
        public void onCallEstablished() {
            AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, false, mIsInComingCall);
            Log.e(TAG, "onCallEstablished: ");
            if (avChatController.getTimeBase() == 0)
                avChatController.setTimeBase(SystemClock.elapsedRealtime());

            // 接通以后，自己是小屏幕显示图像，对方是大屏幕显示图像
            avChatVideoUI.initSmallSurfaceView(AVChatKit.getAccount());
            Log.e(TAG, "onCallEstablished: " + account + "  " + AVChatKit.getAccount());
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
    private String displayName;
    private boolean mIsInComingCall;
    private AVChatNotification notifier;
    private Observer<AVChatCalleeAckEvent> callAckObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);
        permissionRequest();
//        initView();
        root = LayoutInflater.from(this).inflate(R.layout.avchat_activity, null);
        setContentView(root);
        initAccount();
        state = 2;
        parseIntent();
        initData();
        showUI();
        notifier = new AVChatNotification(this);
        notifier.init(account != null ? account : avChatData.getAccount(), displayName);
        //激活音视频通话底层引擎
//        AVChatManagerLite.getInstance().enableRtc();
//        //激活视频模块
//        AVChatManagerLite.getInstance().enableVideo();
//        //创建视频捕获器
//        AVChatCameraCapturer cameraCapturer = AVChatVideoCapturerFactory.createCameraCapturer();
//        boolean b = AVChatManager.getInstance().setupLocalVideoRender(new AVChatSurfaceViewRenderer(VideoCallActivity.this), false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);
////        Log.e(TAG, "onCreate: "+b );
//
//        //设置视频捕获器
//        AVChatManagerLite.getInstance().setupVideoCapturer(cameraCapturer);
//        //开启视频预览
//        AVChatManagerLite.getInstance().startVideoPreview();
//        //
//        AVChatNotifyOption notifyOption = new AVChatNotifyOption();
//        //是否兼容WebRTC模式
//        notifyOption.extendMessage="extra";
//        //发起通话
//        AVChatManagerLite.getInstance().call2(account, AVChatType.VIDEO, notifyOption, new AVChatCallback<AVChatData>() {
//            @Override
//            public void onSuccess(AVChatData avChatData) {
//                Log.e(TAG, "onSuccess: " );
//                Log.e(TAG, "onSuccess: "+avChatData.getAccount() );
//                VideoCallActivity.this.avChatData = avChatData;
//                avChatController = new AVChatController(VideoCallActivity.this, avChatData);
//                avChatVideoUI = new AVChatVideoUI(VideoCallActivity.this, root, avChatData, account, avChatController, null, null);
//            }
//
//            @Override
//            public void onFailed(int code) {
//                Log.e(TAG, "onFailed:1111111111111"+"code "+code );
//            }
//
//            @Override
//            public void onException(Throwable exception) {
//                Log.e(TAG, "onException:11111111111 "+exception );
//            }
//        });
//对方选择状态的监听
        callAckObserver = new Observer<AVChatCalleeAckEvent>() {
            @Override
            public void onEvent(AVChatCalleeAckEvent ackInfo) {
                if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_BUSY) {
                    // 对方正在忙
                    Toast.makeText(VideoCallActivity.this, "对方正忙", Toast.LENGTH_SHORT).show();
                    avChatController.onHangUp(AVChatExitCode.HANGUP);
                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_REJECT) {
                    // 对方拒绝接听
                    Toast.makeText(VideoCallActivity.this, "对方拒绝了您的通话请求", Toast.LENGTH_SHORT).show();
                    avChatController.onHangUp(AVChatExitCode.HANGUP);
                } else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE) {
                    // 对方同意接听
                    Log.e(TAG, "onEvent: 同意接听");
                    avChatController.isCallEstablish.set(true);
                }
            }
        };
        registerObserves(true);

//        AVChatManager.getInstance().observeControlNotification(callControlObserver, true);

    }

    //初始化网易云账号
    private void initAccount() {
//        OkHttpUtils.post()
//                .url(Constant.SERVER_URL+"/neteaseAccount/find")
//                .addParams("")
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        if (avChatVideoUI != null) {
            avChatVideoUI.onDestroy();
        }

        registerObserves(false);
        AVChatProfile.getInstance().setAVChatting(false);
        cancelCallingNotifier();
        needFinish = true;
        super.onDestroy();
    }

    private void registerObserves(boolean register) {
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, register);
        AVChatManager.getInstance().observeAVChatState(avchatStateObserver, register);
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, register);
        AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, register, mIsInComingCall);

    }
    Observer<Integer> timeoutObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer integer) {

            avChatController.hangUp(AVChatExitCode.CANCEL);
            Toast.makeText(VideoCallActivity.this, "对方未接听", Toast.LENGTH_SHORT).show();
            // 来电超时，自己未接听
            if (mIsInComingCall) {
                activeMissCallNotifier();
            }

            finish();
        }
    };
    private void parseIntent() {
        mIsInComingCall = getIntent().getBooleanExtra(KEY_IN_CALLING, false);
        displayName = getIntent().getStringExtra(KEY_DISPLAY_NAME);
        switch (getIntent().getIntExtra(KEY_SOURCE, FROM_UNKNOWN)) {
            case FROM_BROADCASTRECEIVER: // incoming call
                avChatData = (AVChatData) getIntent().getSerializableExtra(KEY_CALL_CONFIG);
                state = avChatData.getChatType().getValue();
                break;
            case FROM_INTERNAL: // outgoing call
                Log.e(TAG, "parseIntent: " + account);
                account = getIntent().getStringExtra(KEY_ACCOUNT);
                state = getIntent().getIntExtra(KEY_CALL_TYPE, -1);
                break;
            default:
                break;
        }
    }

    /**
     * @desc 初始化音视频控制器
     * @date 2018/4/24 16:50
     */
    private void initData() {
        avChatController = new AVChatController(this, avChatData);
        avChatVideoUI = new AVChatVideoUI(this, root, avChatData, Constant.WANGYIYUN_DISPLAYNAME, avChatController, null, null);//后面两个参数一个是点击右上角小框的切换的回调 一个是音视频切换的监听
//        avChatVideoUI = new AVChatVideoUI(this, root, avChatData, displayName, avChatController, this, this);

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
        if (mIsInComingCall) {
            // 来电
            AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.RING);
            avChatVideoUI.showIncomingCall(avChatData);
        } else {
            // 去电
            AVChatSoundPlayer.instance().play(AVChatSoundPlayer.RingerTypeEnum.CONNECTING);
            avChatVideoUI.doOutgoingCall(account);
        }

    }

    // 接听来电
    public static void incomingCall(Context context, AVChatData config, String displayName, int source) {
        needFinish = false;
        Intent intent = new Intent();
        intent.setClass(context, VideoCallActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(KEY_CALL_CONFIG, config);
        intent.putExtra(KEY_DISPLAY_NAME, displayName);
        intent.putExtra(KEY_IN_CALLING, true);
        intent.putExtra(KEY_SOURCE, source);
        context.startActivity(intent);
    }

    // 通话过程中，收到对方挂断电话
//    Observer<AVChatCommonEvent> callHangupObserver = new Observer<AVChatCommonEvent>() {
//        @Override
//        public void onEvent(AVChatCommonEvent avChatHangUpInfo) {
//            avChatData = avChatController.getAvChatData();
//            Log.e(TAG, "hangup: " );
//            if (avChatData != null && avChatData.getChatId() == avChatHangUpInfo.getChatId()) {
//                Log.e(TAG, "hangup: id" );
//                avChatController.onHangUp(AVChatExitCode.HANGUP);
//                cancelCallingNotifier();
//                // 如果是incoming call主叫方挂断，那么通知栏有通知
//                if (mIsInComingCall && !isCallEstablished) {
//                    activeMissCallNotifier();
//                }
//            }
//
//        }
//    };
    // 通话过程中，收到对方挂断电话
    Observer<AVChatCommonEvent> callHangupObserver = new Observer<AVChatCommonEvent>() {
        @Override
        public void onEvent(AVChatCommonEvent avChatHangUpInfo) {
            avChatData = avChatController.getAvChatData();
            if (avChatData != null && avChatData.getChatId() == avChatHangUpInfo.getChatId()) {
                AVChatProfile.getInstance().setAVChatting(false);
                avChatController.onHangUp(AVChatExitCode.HANGUP);

                cancelCallingNotifier();
                // 如果是incoming call主叫方挂断，那么通知栏有通知
                if (mIsInComingCall && !isCallEstablished) {
                    activeMissCallNotifier();
                }
            }

        }
    };

    private void cancelCallingNotifier() {
        if (notifier != null) {
            notifier.activeCallingNotification(false);
        }
    }

    private void activeMissCallNotifier() {
        if (notifier != null) {
            notifier.activeMissCallNotification(true);
        }
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
        PermissionUtil.checkAndRequestMorePermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 2);
    }
}
