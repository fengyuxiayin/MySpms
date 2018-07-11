package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailSimpleActivity;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewMessageActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String TAG = ViewMessageActivity.class.getSimpleName();
    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private ListView listView;
    private Button btnSend;
    private String fjr;
    private String sjr;
    private String time;
    private String parentId;
    private String fj;
    private TextView tvFjr;
    private TextView tvSj;
    private TextView tvSjr;
    private List<String> strings;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        fjr = getIntent().getStringExtra("fjr");
        sjr = getIntent().getStringExtra("sjr");
        time = getIntent().getStringExtra("time");
        parentId = getIntent().getStringExtra("parentId");
        fj = getIntent().getStringExtra("fj");
        Log.e(TAG, "onCreate: " + "fj " + fj);
        initView();
        initData();
        initListener();
        //去掉最外面的大括号
        String substring = fj.substring(1, fj.length() - 1);
        if (substring.length()>0) {
            String[] split = substring.split(",");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].substring(1,split[i].length()-1);
            }
            strings = Arrays.asList(split);
        }else{
//            Toast.makeText(this, "附件为空", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onCreate: 附件为空" );
            listView.setVisibility(View.GONE);
        }
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    private void initData() {
        tvTitle.setText("邮件详细信息");
        imgVideoCall.setVisibility(View.INVISIBLE);
        tvFjr.setText(fjr);
        tvSjr.setText(sjr);
        tvSj.setText(time);
        List<String> list = new ArrayList<>();
        //取出文件名称
        if (strings!=null) {
            for (int i = 0; i < strings.size(); i++) {
                String substring = strings.get(i).substring(strings.get(i).lastIndexOf("/") + 1, strings.get(i).length());
                list.add(substring);
            }
        }
        listView.setAdapter(new ArrayAdapter<>(ViewMessageActivity.this, android.R.layout.simple_list_item_1, list));
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.activity_view_message_header).findViewById(R.id.title);
        imgBack = (ImageView) findViewById(R.id.activity_view_message_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_view_message_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_view_message_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_view_message_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_view_message_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_view_message_header).findViewById(R.id.add);
        tvFjr = (TextView) findViewById(R.id.activity_view_message_tv_fjr);
        tvSj = (TextView) findViewById(R.id.activity_view_message_tv_sj);
        tvSjr = (TextView) findViewById(R.id.activity_view_message_tv_sjr);
        listView = (ListView) findViewById(R.id.activity_view_message_lv);
        btnSend = (Button) findViewById(R.id.activity_view_message_btn_Reply);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), ViewMessageActivity.this,ViewMessageActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_view_message_btn_Reply:
                    Intent intent = new Intent();
                    intent.setClass(ViewMessageActivity.this,SendMessageActivity.class);
                    intent.putExtra("isSend",false);
                    intent.putExtra("title","发送消息");
                    intent.putExtra("parentId",parentId);
                    intent.putExtra("sjr",fjr);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //文件路径
        String filePath = strings.get(position);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        OkHttpUtils.get()
                .url(Constant.UPLOAD_IMG_IP + filePath)
                .build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName) {
            @Override
            public void inProgress(float progress) {

            }

            @Override
            public void onError(Request request, Exception e) {
                NetUtil.errorTip(ViewMessageActivity.this,e.getMessage()+e.getCause()+"下载邮件附件");
            }

            @Override
            public void onResponse(File response) {
                Log.e(TAG, "onResponse: "+response+"下载邮件附件" );
                Toast.makeText(ViewMessageActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
