package com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailSimpleActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.adapters.MyAdapter;
import com.example.lzc.myspms.adapters.UploadListAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.ProjectSafeFragment;
import com.example.lzc.myspms.models.AccountFindAllModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.JszModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.ProjectSafeInfo;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.example.lzc.myspms.utils.StringUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = SendMessageActivity.class.getSimpleName();
    private ClearEditText etRecipient;
    private EditText etTitle;
    private EditText etContent;
    private TextView tvUpload;
    private ListView listView;
    private Button btnSend;
    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;
    private Boolean isSend;
    private String title;
    private Gson gson = new Gson();
    private List<AccountFindAllModel.AccountFindAllMsgModel.ListBean> accountList = new ArrayList<>();
    private AutoCompleteTextView autoRecipient;
    private String fjPath = "";
    //记录上传到服务器的文件路径
    private List<String> pathList = new ArrayList<>();
    //记录本地路径
    private List<String> localPathList = new ArrayList<>();
    private UploadListAdapter uploadListAdapter;
    private String parentId;
    private String sjr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        isSend = getIntent().getBooleanExtra("isSend", true);
        title = getIntent().getStringExtra("title");
        if (!isSend) {
            parentId = getIntent().getStringExtra("parentId");
            sjr = getIntent().getStringExtra("sjr");
        }
        initView();
        initData();
        initListener();
    }

    private void initData() {
        tvTitle.setText(title);
        if (isSend) {
            getAccount();
        }else{
            autoRecipient.setEnabled(false);
            etRecipient.setText(sjr);
        }
        uploadListAdapter = new UploadListAdapter(pathList, SendMessageActivity.this, listView);
        listView.setAdapter(uploadListAdapter);
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
                        NetUtil.errorTip(SendMessageActivity.this, e.getMessage() + "/authAccount/findAll");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/authAccount/findAll");
                        AccountFindAllModel accountFindAllModel = gson.fromJson(response, AccountFindAllModel.class);
                        if (accountFindAllModel.isData()) {
                            AccountFindAllModel.AccountFindAllMsgModel accountFindAllMsgModel = gson.fromJson(accountFindAllModel.getMsg(), AccountFindAllModel.AccountFindAllMsgModel.class);
                            accountList = accountFindAllMsgModel.getList();
                            if (accountList.size() > 0) {
                                List<String> list = new ArrayList<>();
                                for (int i = 0; i < accountList.size(); i++) {
                                    if (!StringUtils.isEmpty(accountList.get(i).getSsmc())) {
                                        list.add(accountList.get(i).getSsmc()+"("+accountList.get(i).getLoginName()+")");
                                    }
                                }
                                Log.e(TAG, "onResponse: "+accountList.size()+" " +list.size());
//                                autoRecipient.setAdapter(new ArrayAdapter<>(SendMessageActivity.this, android.R.layout.simple_list_item_1, list));
                                autoRecipient.setAdapter(new MyAdapter<>(SendMessageActivity.this, android.R.layout.simple_list_item_1, list));
                            } else {
                                Toast.makeText(SendMessageActivity.this, "正在获取联系人信息，请稍候", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SendMessageActivity.this, accountFindAllModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        tvUpload.setOnClickListener(this);
        etRecipient.setFocusable(false);
        btnSend.setOnClickListener(this);
        autoRecipient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (etRecipient.getText().toString().trim().length() > 0) {
                    etRecipient.setText(etRecipient.getText().toString().trim() + ";" + autoRecipient.getText().toString());
                } else {
                    etRecipient.setText(autoRecipient.getText().toString());
                }
            }
        });

    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.activity_send_message_header).findViewById(R.id.title);
        imgBack = (ImageView) findViewById(R.id.activity_send_message_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_send_message_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_send_message_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_send_message_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_send_message_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_send_message_header).findViewById(R.id.add);
        etRecipient = (ClearEditText) findViewById(R.id.activity_send_message_et_recipient);
        autoRecipient = (AutoCompleteTextView) findViewById(R.id.activity_send_message_actv_recipient);
        etTitle = (EditText) findViewById(R.id.activity_send_message_et_title);
        etContent = (EditText) findViewById(R.id.activity_send_message_et_content);
        tvUpload = (TextView) findViewById(R.id.activity_send_message_tv_upload);
        listView = (ListView) findViewById(R.id.activity_send_message_lv);
        btnSend = (Button) findViewById(R.id.activity_send_message_btn_send);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                String realPathFromURI = getPath(SendMessageActivity.this, uri);
                Log.e(TAG, "onActivityResult: " + realPathFromURI);
                File file = new File(realPathFromURI);
                sendFileToServer(file);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param file 上传的文件
     * @desc 发送文件到服务器
     * @date 2018/5/28 17:49
     */
    private void sendFileToServer(File file) {
        final ProgressDialog progressDialog = new ProgressDialog(SendMessageActivity.this);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/fileUpload/doFileUpload")
                .addParams("folder", "/source/msgFile/")
                .addFile("file", file.getAbsolutePath(), file)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onBefore(Request request) {
                        Log.e(TAG, "onBefore: " + request.url());
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(SendMessageActivity.this, e.getMessage() + "/fileUpload/doFileUpload");
                    }

                    @Override
                    public void inProgress(float progress) {
                        progressDialog.setProgress((int) (progress*100));
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
                        progressDialog.setTitle("正在上传文件");
                        progressDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
                        progressDialog.show();
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "/fileUpload/doFileUpload");
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        progressDialog.dismiss();
                        if (infoModel.isData()) {
                            pathList.add(infoModel.getMsg());
//                            uploadListAdapter.notifyData(infoModel.getMsg());
                            uploadListAdapter.notifyDataSetChanged();
                            setListViewHeightBasedOnChildren(listView);
                            Toast.makeText(SendMessageActivity.this, "文件上传成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SendMessageActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
     */
    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {


        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;


        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];


                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {


                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));


                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];


                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }


                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};


                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {


        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};


        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param listView 需要进行设置的listView
     * @desc 动态设置ListView条目的高度
     * @date 2017/12/15 9:33
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        int totalHeight = 0;
        for (int i = 0; i < uploadListAdapter.getCount(); i++) {
            View listItem = uploadListAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (uploadListAdapter.getCount() - 1));
        Log.e(TAG, "setListViewHeightBasedOnChildren: " + params.height);
        listView.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            Intent intent = new Intent();
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), SendMessageActivity.this,SendMessageActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_send_message_et_recipient:

                    break;
                case R.id.activity_send_message_tv_upload:
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, 1);
                    break;
                case R.id.activity_send_message_btn_send:
                    if (etRecipient.getText().toString().trim().length() < 1) {
                        Toast.makeText(this, "收件人不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (etTitle.getText().toString().length() < 1) {
                        Toast.makeText(this, "主题不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    List<String> strings = Arrays.asList(etRecipient.getText().toString().split(";"));
                    //接收者字段
                    List<JszModel> jszModelList = new ArrayList<>();
                    for (int i = 0; i < accountList.size(); i++) {
                        for (int j = 0; j < strings.size(); j++) {
                            if (strings.get(j).equals(accountList.get(i).getSsmc()+"("+accountList.get(i).getLoginName()+")")) {
                                jszModelList.add(new JszModel( accountList.get(i).getLoginType(),accountList.get(i).getId()));
                            }
                        }
                    }
                    Log.e(TAG, "onClick: " + "xxbt " + etTitle.getText().toString() + "xxnr " + etContent.getText().toString() + "jszList " + gson.toJson(jszModelList) + "fj " + gson.toJson(pathList));
                    sendMessageToServer(jszModelList);
                    break;
            }
        }
    }

    private void sendMessageToServer(List<JszModel> jszModelList) {
        String fjJson = gson.toJson(pathList);
        Log.e(TAG, "sendMessageToServer: json  "+fjJson );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/sysMessage/sendMsg")
                .addParams("xxbt", etTitle.getText().toString().trim())
                .addParams("xxnr", etContent.getText().toString().trim())
                .addParams("jszList", jszModelList.size() > 0 ? gson.toJson(jszModelList) : "")
                .addParams("fj", fjJson.length()==2?"":fjJson)
                .addParams("xxlx", isSend ? "1" : "2")
                .addParams("xxzl","2")
                .addParams("parentId", isSend ? "0" : jszModelList.get(0).getSjrId()+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(SendMessageActivity.this, e.getMessage() + "/sysMessage/sendMsg");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            Toast.makeText(SendMessageActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SendMessageActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
