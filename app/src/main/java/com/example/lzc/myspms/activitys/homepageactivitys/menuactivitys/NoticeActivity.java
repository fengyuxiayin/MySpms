package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.MainActivity;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.queryactivitys.CheckItemsQueryActivity;
import com.example.lzc.myspms.adapters.NoticeAdapter;
import com.example.lzc.myspms.adapters.ProjectRecheckSimpleAdapter;
import com.example.lzc.myspms.bean.NoticeInfo;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.AccountFindAllModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.MessageFindModel;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.DbManagerNotice;
import com.example.lzc.myspms.utils.ExampleUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.example.lzc.myspms.utils.ShowMenuPopup;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class NoticeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = NoticeActivity.class.getSimpleName();
    private String noticeStr;
    public static boolean isForeground = false;
    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;
    private PullToRefreshListView listView;
    private TextView textView;
    private int page = 1;
    private Gson gson = new Gson();
    private List<MessageFindModel.MessageFindMsgModel.ListBean> messageList = new ArrayList<>();
    private NoticeAdapter noticeAdapter;
    private List<AccountFindAllModel.AccountFindAllMsgModel.ListBean> accountList = new ArrayList<>();
    private boolean popupMenuShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        initView();
        initData();
        registerMessageReceiver();
        initListener();
    }

    private void initData() {
        getAccount();
    }
    /**
     * @desc 获取所有的账户信息
     * @date 2018/5/28 16:00
     */
    private void getAccount() {
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/authAccount/findAll")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(NoticeActivity.this, e.getMessage() + "/authAccount/findAll");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/authAccount/findAll");
                        AccountFindAllModel accountFindAllModel = gson.fromJson(response, AccountFindAllModel.class);
                        if (accountFindAllModel.isData()) {
                            AccountFindAllModel.AccountFindAllMsgModel accountFindAllMsgModel = gson.fromJson(accountFindAllModel.getMsg(), AccountFindAllModel.AccountFindAllMsgModel.class);
                            accountList = accountFindAllMsgModel.getList();
                        } else {
                            Toast.makeText(NoticeActivity.this, accountFindAllModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getMessageFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/sysMessage/find")
                .addParams("sjrId", Constant.ACCOUNT_ID)
                .addParams("sjrlx", Constant.ACCOUNT_TYPE)
                .addParams("pn", page + "")
                .addParams("size", "10")
                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(NoticeActivity.this, e.getMessage() + "/sysMessage/find");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /sysMessage/find" + response);
                        MessageFindModel messageFindModel = gson.fromJson(response, MessageFindModel.class);
                        if (messageFindModel.isData()) {
                            MessageFindModel.MessageFindMsgModel messageFindMsgModel = gson.fromJson(messageFindModel.getMsg(), MessageFindModel.MessageFindMsgModel.class);
                            if (messageFindMsgModel.getTotal() - page * 10 > 0) {
                                textView.setText("上拉加载更多数据");
                            } else {
                                textView.setText("我也是有底线的");
                            }
                            if (page == 1) {
                                if (!"null".equals(messageFindMsgModel.getList()) && messageFindMsgModel.getList() != null) {
                                    messageList = messageFindMsgModel.getList();
                                    Log.e(TAG, "onResponse: " + messageList.size());
                                } else {
                                    Toast.makeText(NoticeActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                if (!"null".equals(messageFindMsgModel.getList()) || messageFindMsgModel.getList() != null) {
                                    messageList.addAll(messageFindMsgModel.getList());
                                    Log.e(TAG, "onResponse: " + messageList.size());
                                } else {
                                    Toast.makeText(NoticeActivity.this, "第" + page + "页数据为空", Toast.LENGTH_SHORT).show();
                                }
                            }
                            if (messageList.size() == 0) {
                                //显示项目信息
                                noticeAdapter = new NoticeAdapter(messageList, NoticeActivity.this);
                                listView.setAdapter(noticeAdapter);
                                listView.onRefreshComplete();
                            } else {
                                noticeAdapter = new NoticeAdapter(messageList, NoticeActivity.this);
                                listView.getRefreshableView().smoothScrollToPosition((page - 1) * 10);
                                if (page > 1) {
                                    noticeAdapter.notifyDataSetChanged();
                                } else {
                                    listView.setAdapter(noticeAdapter);
                                }
                                listView.onRefreshComplete();
                            }
                        } else {
                            Toast.makeText(NoticeActivity.this, messageFindModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void initListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (textView.getText().toString().equals("我也是有底线的")) {
                    listView.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                            Toast.makeText(NoticeActivity.this, R.string.pull_to_refresh_no_data, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);
                } else {
                    //改变查询条件
                    page++;
                    getMessageFromServer();
                }

            }
        });
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.activity_notice_header).findViewById(R.id.title);
        tvTitle.setText("消息");
        imgBack = (ImageView) findViewById(R.id.activity_notice_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_notice_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_notice_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_notice_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_notice_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_notice_header).findViewById(R.id.add);
        listView = (PullToRefreshListView) findViewById(R.id.activity_notice_pulltorefresh);
        textView = new TextView(NoticeActivity.this);
        textView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setText("我也是有底线的");
        listView.getRefreshableView().addFooterView(textView);
        //设置listView的模式和加载文字
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        listView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        listView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        getMessageFromServer();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }



    @Override
    public void onClick(View v) {
        if (v != null) {
            Intent intent = new Intent();
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), NoticeActivity.this,NoticeActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.popup_notice_mail_et_recipient:
                    Toast.makeText(this, "显示选择的人", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.popup_notice_mail_tv_upload:
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, 1);
                    break;
                case R.id.popup_notice_mail_btn_send:

                    break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position-1<messageList.size()) {
            Log.e(TAG, "onItemClick: "+accountList.size() );
            if (accountList.size()>0) {
                MessageFindModel.MessageFindMsgModel.ListBean item = noticeAdapter.getItem(position - 1);
                Intent intent = new Intent();
                intent.setClass(this,ViewMessageActivity.class);
                //循环获取发件人名称
                for (int i = 0; i < accountList.size(); i++) {
                    if (accountList.get(i).getId()==item.getFjrId()) {
                        intent.putExtra("fjr",accountList.get(i).getSsmc());
                        break;
                    }
                }
                //循环获取收件人名称
                for (int i = 0; i < accountList.size(); i++) {
                    if (accountList.get(i).getId()==item.getSjrId()) {
                        intent.putExtra("sjr",accountList.get(i).getSsmc());
                        break;
                    }
                }
                //发送时间
                intent.putExtra("time",DateUtil.long2Date(item.getCreateTime()));
                intent.putExtra("parentId",item.getId()+"");
                intent.putExtra("fj",item.getFj());
                intent.putExtra("bt",item.getXxbt());
                intent.putExtra("zw",item.getXxnr());
                startActivity(intent);
            }else{
                Toast.makeText(this, "正在获取账号信息", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    Log.e(TAG, "onReceive: " + showMsg);
//                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e) {
            }
        }
    }



}
