package com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.PointGridAdapter;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.PointInfoModel;
import com.example.lzc.myspms.models.ProjectPublicInfoModel;
import com.example.lzc.myspms.models.StandardJsonModel;
import com.example.lzc.myspms.models.WxyJsonModel;
import com.example.lzc.myspms.models.YhdItemModel;
import com.example.lzc.myspms.models.YhdJsonModel;
import com.example.lzc.myspms.utils.BitmapUtils;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.example.lzc.myspms.utils.ShowMenuPopup;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class ProjectDetailDangerActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = ProjectDetailDangerActivity.class.getSimpleName();
    //findById用的
    private String id;
    //判断是查看还是可以编辑
    private String isView;
    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;
    //判断popup是否创建
    private boolean popupMenuShow = false;
    private TextView tvDanger;
    private TextView tvComponyName;
    private TextView tvType;
    private TextView tvDate;
    private TextView tvDangerName;
    private TextView tvCheck;
    private TextView tvYear;
    private Button btnCommit;
    private GridView gridView;
    private Gson gson = new Gson();
    private String qymc;
    private String picPath;
    private File mTmpFile;
    private List<PointInfoModel.PointInfoMsgModel.ListBean> list = new ArrayList<>();
    private PointGridAdapter pointGridAdapter;
    private List<YhdItemModel> yhdItemList = new ArrayList<>();
    private String jcId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail_danger);
        id = getIntent().getStringExtra("id");
        jcId = getIntent().getStringExtra("jcId");
        isView = getIntent().getStringExtra("isView");
        qymc = getIntent().getStringExtra("qymc");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        imgVideoCall.setOnClickListener(this);
        imgNotice.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        imgMessage.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
    }

    private void initData() {
        tvTitle.setText("重大隐患源检查");
        initDataFromServer();
    }

    private void initDataFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/findById")
                .addParams("id", id)
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Request request, Exception e) {
                                 NetUtil.errorTip(ProjectDetailDangerActivity.this, e.getMessage());
                             }

                             @Override
                             public void onResponse(String response) {
                                 Log.e(TAG, "onResponse: /checkProject/findById" + response);
                                 LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                 if (infoModel.isData()) {
                                     ProjectPublicInfoModel projectPublicInfoModel = gson.fromJson(infoModel.getMsg(), ProjectPublicInfoModel.class);
                                     String wxyJson = projectPublicInfoModel.getWxyJson();
                                     Log.e(TAG, "onResponse: " + wxyJson);
                                     if (wxyJson != null) {
                                         WxyJsonModel wxyJsonModel = gson.fromJson(wxyJson, WxyJsonModel.class);
                                        tvDangerName.setText("重大危险源名称："+wxyJsonModel.getWxymc());
                                        tvDanger.setText(wxyJsonModel.getWxymc());
                                         tvDate.setText("生产日期："+ DateUtil.long2Date(wxyJsonModel.getScrq()));
                                         tvComponyName.setText("企业名称："+qymc);
                                         tvYear.setText("使用年限："+wxyJsonModel.getBfnx());
                                         // TODO: 2018/6/19  没有检查人员姓名 和项目分类
                                         getPointData(projectPublicInfoModel);
                                     }
                                 }
                             }
                         }

                );

    }

    private void getPointData(ProjectPublicInfoModel projectPublicInfoModel) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkPoint/findByJcxId")
                .addParams("jcxId", projectPublicInfoModel.getId() + "")
                .build()
                .execute(new StringCallback() {
                    private PointInfoModel.PointInfoMsgModel pointInfoMsgModel;
                    private PointInfoModel pointInfoModel;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: /checkPoint/findByJcxId" + response);
                        pointInfoModel = gson.fromJson(response, PointInfoModel.class);
                        if (pointInfoModel.isData()) {
                            pointInfoMsgModel = gson.fromJson(pointInfoModel.getMsg(), PointInfoModel.PointInfoMsgModel.class);
                            list = pointInfoMsgModel.getList();
                            pointGridAdapter = new PointGridAdapter(list, ProjectDetailDangerActivity.this,isView);
                            gridView.setAdapter(pointGridAdapter);
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                //请求相机
                //监听返回键，如果返回直接return
                if (resultCode == Activity.RESULT_CANCELED) {//按下了返回键
                    return;
                } else {
                    Bitmap bitmap = BitmapUtils.getBitmap(Constant.PHOTO_ABSOLUTE_PATH, 2050, 2050);
                    Log.e(TAG, "onActivityResult: " + bitmap);
                    mTmpFile = saveBitmapFile(bitmap, Constant.PHOTO_ABSOLUTE_PATH);
                    if (mTmpFile != null) {
                        try {
                            long fileSize = getFileSize(mTmpFile);
                            Log.e(TAG, "onActivityResult: fileSize 压缩" + fileSize);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.e(TAG, "onActivityResult: 请求相机： " + mTmpFile.getAbsolutePath());
                        //上传图片到服务器
                        sendImgToServer(mTmpFile);
                    }
                }
                break;
            //请求相册
            case 1:
                doPhoto(data);
                break;

        }
        
    }
    /**
     * 将bitmap转成文件
     *
     * @param bitmap
     * @return
     * @throws Exception
     */
    public File saveBitmapFile(Bitmap bitmap, String filePath) {
        if (filePath != null) {
            File file = new File(filePath);//将要保存图片的路径
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;

        } else {
            return null;
        }

    }
    /**
     * @param data intent包含的数据
     * @desc 选择图片上传到服务器 获取其返回的url
     * @date 2017/12/11 17:20
     */
    private void doPhoto(Intent data) {
        Log.e(TAG, "doPhoto: " + data);
        if (data == null) {
            Toast.makeText(this, "未选择图片", Toast.LENGTH_LONG).show();
            return;
        }
        Uri photoUri = data.getData();
        if (photoUri == null) {
            Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
            return;
        }
        String[] pojo = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.managedQuery(photoUri, pojo, null, null, null);
        if (cursor != null) {
            //获取文件路径
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);
            try {
                //4.0以上的版本会自动关闭 (4.0--14;; 4.0.3--15)
                if (Integer.parseInt(Build.VERSION.SDK) < 14) {
                    cursor.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "doPhoto: " + e.getMessage());
            }
        }
        //以文件形式上传图片
        if (picPath != null && (picPath.endsWith(".png") || picPath.endsWith(".PNG") || picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {
            File file = new File(picPath);
            try {
                long fileSize = getFileSize(file);
                //图片文件长度大于2M就压缩
                if (fileSize > 2000000) {
                    Bitmap bitmap = BitmapUtils.getBitmap(picPath, 2050, 2050);
                    file = saveBitmapFile(bitmap, picPath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sendImgToServer(file);
        } else {
            Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @param file 图片文件
     * @desc 将图片文件上传到服务器 获取其返回的url
     * @date 2017/12/11 17:19
     */
    private void sendImgToServer(final File file) {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/fileUpload/doFileUpload")
                .addParams("folder", "/source/project/")
                .addFile("file", file.getAbsolutePath(), file)
                .build()
                .execute(new StringCallback() {
                    private Gson gson;

                    @Override
                    public void onBefore(Request request) {
                        Log.e(TAG, "onBefore: " + request.url());
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + file.getAbsolutePath());
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(ProjectDetailDangerActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        gson = new Gson();
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            Toast.makeText(ProjectDetailDangerActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                            if (infoModel.isData()) {
                                Toast.makeText(ProjectDetailDangerActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                                PointInfoModel.PointInfoMsgModel.ListBean listBean = list.get(Constant.GRID_POS_ONE);
                                String jctp = listBean.getJctp();
                                Log.e(TAG, "onResponse: "+infoModel.getMsg() );
                                if (jctp!=null) {
                                    if (jctp.length() > 0) {
                                        jctp += ",";
                                        jctp += infoModel.getMsg();
                                    } else {
                                        jctp += infoModel.getMsg();
                                    }
                                }else{
                                    jctp = "";
                                    jctp += infoModel.getMsg();
                                }
                                listBean.setJctp(jctp);
                                Log.e(TAG, "onResponse: "+Constant.GRID_POS_ONE+"size"+list.size() );
                                list.set(Constant.GRID_POS_ONE,listBean);
                                pointGridAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(ProjectDetailDangerActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ProjectDetailDangerActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.activity_project_detail_danger_header).findViewById(R.id.title);
        imgBack = (ImageView) findViewById(R.id.activity_project_detail_danger_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_project_detail_danger_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_project_detail_danger_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_project_detail_danger_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_project_detail_danger_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_project_detail_danger_header).findViewById(R.id.add);
        tvDanger = (TextView) findViewById(R.id.activity_project_detail_danger_tv_danger);
        tvComponyName = (TextView) findViewById(R.id.activity_project_detail_danger_tv_compony_name);
        tvType = (TextView) findViewById(R.id.activity_project_detail_danger_tv_type);
        tvDate = (TextView) findViewById(R.id.activity_project_detail_danger_tv_date);
        tvDangerName = (TextView) findViewById(R.id.activity_project_detail_danger_tv_danger_name);
        tvCheck = (TextView) findViewById(R.id.activity_project_detail_danger_tv_check);
        tvYear = (TextView) findViewById(R.id.activity_project_detail_danger_tv_year);
        btnCommit = (Button) findViewById(R.id.activity_project_detail_danger_btn_commit);
        gridView = (GridView) findViewById(R.id.activity_project_detail_danger_grid);
    }

    @Override
    public void onClick(View v) {
        if (v!=null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), ProjectDetailDangerActivity.this,ProjectDetailDangerActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_project_detail_danger_btn_commit:
                    for (int i = 0; i < list.size(); i++) {
                        YhdItemModel yhdItemModel = new YhdItemModel(list.get(i).getId(), list.get(i).getJcxId(), list.get(i).getJctp(),list.get(i).getYhms(), list.get(i).getJcjg());
                        yhdItemList.add(yhdItemModel);
                    }
                    Log.e(TAG, "onClick: "+gson.toJson(yhdItemList) );
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL + "/checkProject/check")
                            .addParams("id",id)
                            .addParams("jcId", jcId)
                            .addParams("jclx", "2")//1 通项 2 危险源
                            .addParams("yhdItem", gson.toJson(yhdItemList))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    NetUtil.errorTip(ProjectDetailDangerActivity.this, e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: "+response );
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    Toast.makeText(ProjectDetailDangerActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    break;
            }
        }
    }
}
