package com.example.lzc.myspms.avchats;

import android.util.Log;
import android.widget.Toast;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.CustomNotification;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by hzchenkang on 2017/5/5.
 */

public class TeamAVChatProfile {
    public static final String TAG = TeamAVChatProfile.class.getSimpleName();

    private static final String KEY_ID = "id";
    private static final String KEY_MEMBER = "members";
    private static final String KEY_TID = "teamId";
    private static final String KEY_RID = "room";
    private static final String KEY_TNAME = "teamName";

    private static final long OFFLINE_EXPIRY = 45 * 1000;

    private static final int ID = 3;

    private boolean isTeamAVChatting = false;
    private boolean isSyncComplete = true; // 未开始也算同步完成，可能存在不启动同步的情况

    public String buildContent(String roomName, String teamID, List<String> accounts, String teamName) {
        JSONObject json = new JSONObject();
        json.put(KEY_ID, ID);
        JSONArray array = new JSONArray();
        array.add(AVChatKit.getAccount());
        for (String account : accounts) {
            array.add(account);
        }
        json.put(KEY_MEMBER, array);
        json.put(KEY_TID, teamID);
        json.put(KEY_RID, roomName);
        json.put(KEY_TNAME, teamName);
        return json.toString();
    }

    private JSONObject parseContentJson(CustomNotification notification) {
        if (notification != null) {
            String content = notification.getContent();
            Log.e(TAG, "parseContentJson: "+content );
            return JSONObject.parseObject(content);
        }
        return null;
    }

    private boolean isTeamAVChatInvite(JSONObject json) {
        if (json != null) {
            int id = json.getInteger(KEY_ID);
            Log.e(TAG, "isTeamAVChatInvite: "+(id == ID) );
            return id == ID;

        }
        return false;
    }

    /**
     * 监听自定义通知消息，id = 3 是群视频邀请
     *
     * @param register
     */

    private Observer<CustomNotification> customNotificationObserver = new Observer<CustomNotification>() {
        @Override
        public void onEvent(CustomNotification customNotification) {
            Log.e(TAG, "onEvent: "+customNotification );
            try {
                JSONObject jsonObject = parseContentJson(customNotification);
                Log.e(TAG, "onEvent: "+jsonObject );
                // 收到群视频邀请
                if (isTeamAVChatInvite(jsonObject)) {
                    final String roomName = jsonObject.getString(KEY_RID);
                    final String teamId = jsonObject.getString(KEY_TID);
                    JSONArray accountArray = jsonObject.getJSONArray(KEY_MEMBER);
                    final ArrayList<String> accounts = new ArrayList<>();
                    final String teamName = jsonObject.getString(KEY_TNAME);
                    if (accountArray != null) {
                        for (Object o : accountArray) {
                            accounts.add((String) o);
                        }
                    }
                    for (int i = 0; i < accounts.size(); i++) {
                        Log.e(TAG, "onEvent: "+accounts.get(i) );
                    }
                    // 接收到群视频邀请，启动来电界面
                    LogUtil.ui("receive team video chat notification " + teamId + " room " + roomName);
                    if (isTeamAVChatting || AVChatProfile.getInstance().isAVChatting()) {
                        Log.e(TAG, "onEvent: 正在进行视频通话" +isTeamAVChatting+AVChatProfile.getInstance().isAVChatting());
                        LogUtil.ui("cancel launch team av chat isTeamAVChatting = " + isTeamAVChatting);
                        Toast.makeText(AVChatKit.getContext(), "正在进行视频通话", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    LogUtil.ui("isSyncComplete = " + isSyncComplete);
                    if (isSyncComplete || !checkOfflineOutTime(customNotification)) {
                        isTeamAVChatting = true;
                        launchActivity(teamId, roomName, accounts, teamName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    private void launchActivity(final String teamId, final String roomName, final ArrayList<String> accounts, final String teamName) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // 欢迎界面正在运行，则等MainActivity启动之后再启动，否则直接启动 TeamAVChatActivity
                if (!AVChatKit.isMainTaskLaunching()) {
                    TeamAVChatActivity.startActivity(AVChatKit.getContext(), true, teamId, roomName, accounts, teamName);
                    Log.e(TAG, "run: 进入接听页" );
                } else {
                    LogUtil.ui("launch TeamAVChatActivity delay for WelComeActivity is Launching");
                    launchActivity(teamId, roomName, accounts, teamName);
                }
            }
        };

        Handlers.sharedHandler(AVChatKit.getContext()).postDelayed(r, 200);
    }

    private Observer<LoginSyncStatus> loginSyncStatusObserver = new Observer<LoginSyncStatus>() {
        @Override
        public void onEvent(LoginSyncStatus loginSyncStatus) {
            isSyncComplete = (loginSyncStatus == LoginSyncStatus.SYNC_COMPLETED ||
                    loginSyncStatus == LoginSyncStatus.NO_BEGIN);
        }
    };

    public boolean checkOfflineOutTime(CustomNotification notification) {
        // 时间差在45s内，考虑本地时间误差，条件适当放宽
        long time = TimeUtil.currentTimeMillis() - notification.getTime();
        LogUtil.ui("rev offline team AVChat request time = " + time);
        return time > OFFLINE_EXPIRY || time < -OFFLINE_EXPIRY;
    }

    public void setTeamAVChatting(boolean teamAVChatting) {
        Log.e(TAG, "setTeamAVChatting: "+teamAVChatting );
        isTeamAVChatting = teamAVChatting;
    }

    public boolean isTeamAVChatting() {
        return isTeamAVChatting;
    }

    public void registerObserver(boolean register) {
        NIMClient.getService(AuthServiceObserver.class).observeLoginSyncDataStatus(loginSyncStatusObserver, register);
        NIMClient.getService(MsgServiceObserve.class).observeCustomNotification(customNotificationObserver, register);
        //超时连接
//        AVChatTimeoutObserver.getInstance().observeTimeoutNotification(timeoutObserver, register, true);

    }
    Observer<Integer> timeoutObserver = new Observer<Integer>() {
                @Override
        public void onEvent(Integer integer) {

//            avChatController.hangUp(AVChatExitCode.CANCEL);
//            finish();
        }
    };
    public static TeamAVChatProfile sharedInstance() {
        return InstanceHolder.teamAVChatProfile;
    }

    private static class InstanceHolder {
        private final static TeamAVChatProfile teamAVChatProfile = new TeamAVChatProfile();
    }
}
