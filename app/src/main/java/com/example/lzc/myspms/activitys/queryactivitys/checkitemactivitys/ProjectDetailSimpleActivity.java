package com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.VideoCallActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.NoticeActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.adapters.ImageGridAdapter;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.ProjectSafeFragment;
import com.example.lzc.myspms.models.CheckProjectFindModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.ProjectInfoModel;
import com.example.lzc.myspms.models.ProjectPublicInfoModel;
import com.example.lzc.myspms.models.ProjectSafeInfo;
import com.example.lzc.myspms.models.StandardJsonModel;
import com.example.lzc.myspms.utils.BitmapUtils;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.SetMenuClick;
import com.example.lzc.myspms.utils.TakePhotoUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectDetailSimpleActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = ProjectDetailSimpleActivity.class.getSimpleName();
    private Gson gson = new Gson();
    private TextView tvTitle;
    private ImageView imgBack;
    private ImageView imgVideoCall;
    private ImageView imgNotice;
    private ImageView imgAdd;
    private ImageView imgCall;
    private ImageView imgMessage;
    private GridView gridView;
    private CheckBox cvQualified;
    private CheckBox cvUnQualified;
    private Button btnSave;
    private Button btnUpload;
    //取消 拍照 相册
    private Dialog mCameraDialog;
    //拍照的图片的临时文件
    private File mTmpFile;
    private String picPath;
    //上上个页面传过来的jcId
    private String jcId;
    //第一个是asList获取到的列表不可增删
    private List<String> jctpList = new ArrayList<>();
    //这个是存储jctpList数据的列表
    private List<String> jctpNewList = new ArrayList<>();
    private ImageGridAdapter imageGridAdapter;
    private RadioButton rbQualified;
    private RadioButton rbUnqualified;
    private TextView tvZtzrsx;
    private ListView lvCfqx;
    private ListView lvCfyj;
    private EditText etDescription;
    private Button btnCommit;
    private String isView;
    private RadioGroup radioGroup;
    private List<String> pubishList = new ArrayList<>();
    private List<String> refrenceBasisList = new ArrayList<>();
    private String jctp = "";
    private String jcjg = "0";
    private String id;
    private List<CheckProjectFindModel.CheckProjectFindMsgModel.ListBean> ids;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail_simple);
        jcId = getIntent().getStringExtra("jcId");
        id = getIntent().getStringExtra("id");
        position = getIntent().getIntExtra("position",0);
        ids = (List<CheckProjectFindModel.CheckProjectFindMsgModel.ListBean>) getIntent().getSerializableExtra("ids");
        isView = getIntent().getStringExtra("isView");
        Log.e(TAG, "onCreate: "+isView+"  "+ids.size() );
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
        if (isView.equals("isView")) {
            //判断是只能看
            Log.e(TAG, "initListener: view");
            rbQualified.setClickable(false);
            rbUnqualified.setClickable(false);
            etDescription.setEnabled(false);
            gridView.setEnabled(false);
            btnCommit.setVisibility(View.GONE);
        } else {
            Log.e(TAG, "initListener: notview");
            btnCommit.setOnClickListener(this);
            lvCfqx.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView view1 = (TextView) view;
                    view1.setTextIsSelectable(true);
                    return false;
                }
            });
            tvZtzrsx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!etDescription.getText().toString().contains(tvZtzrsx.getText().toString())) {
                        etDescription.setText(etDescription.getText().toString() + tvZtzrsx.getText().toString() + "\n");
                    }
                }
            });
//            lvCfyj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    etDescription.setText(etDescription.getText().toString().trim()+"\n"+refrenceBasisList.get(position));
//                }
//            });
            rbQualified.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        etDescription.setText("");
                    }else{
                        etDescription.setText(etDescription.getText().toString() + tvZtzrsx.getText().toString() + "\n");
                    }
                }
            });

        }
    }

    private void initData() {
        tvTitle.setText("项目详细信息");
        //一开始默认为合格 描述项设置为空
        etDescription.setText("");
        initDataFromServer();
    }

    private void initDataFromServer() {
        jctp = "";
        jcjg = "0";
        jctpList = new ArrayList<>();
        jctpNewList = new ArrayList<>();
        pubishList = new ArrayList<>();
        refrenceBasisList = new ArrayList<>();
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/checkProject/findById")
                .addParams("id", ids.get(position).getId()+"")
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Request request, Exception e) {
                                 NetUtil.errorTip(ProjectDetailSimpleActivity.this, e.getMessage());
                             }

                             @Override
                             public void onResponse(String response) {
                                 Log.e(TAG, "onResponse: /checkProject/findById" + response);
                                 LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                 if (infoModel.isData()) {
                                     ProjectPublicInfoModel projectPublicInfoModel = gson.fromJson(infoModel.getMsg(), ProjectPublicInfoModel.class);
                                     String standardJson = projectPublicInfoModel.getStandardJson();
                                     Log.e(TAG, "onResponse: " + standardJson);
                                     if (standardJson != null) {
                                         StandardJsonModel standardJsonModel = gson.fromJson(standardJson, StandardJsonModel.class);
                                         try {
                                             JSONArray jsonArray = new JSONArray(standardJsonModel.getPubish());
                                             for (int i = 0; i < jsonArray.length(); i++) {
                                                 JSONObject jsonObject = jsonArray.optJSONObject(i);
                                                 Log.e(TAG, "initData: " + jsonObject.optString("cfqx"));
                                                 pubishList.add(jsonObject.optString("cfqx"));
                                             }
                                         } catch (JSONException e) {
                                             e.printStackTrace();
                                         }
                                         Log.e(TAG, "initData: " + pubishList.size());
                                         try {
                                             JSONArray jsonArray = new JSONArray(standardJsonModel.getRefrenceBasis());
                                             for (int i = 0; i < jsonArray.length(); i++) {
                                                 JSONObject jsonObject = jsonArray.optJSONObject(i);
                                                 Log.e(TAG, "onResponse: "+ jsonObject.optString("cktj"));
                                                 refrenceBasisList.add(jsonObject.optString("cktj"));
                                             }
                                         } catch (JSONException e) {
                                             e.printStackTrace();
                                         }
                                     }
                                     tvZtzrsx.setText(projectPublicInfoModel.getStandardDescription());
                                     jctp = projectPublicInfoModel.getJctp();
                                     jcjg = String.valueOf(projectPublicInfoModel.getJcjg());
                                     if (jcjg.equals("1")||jcjg.equals("2")) {
                                         rbQualified.setChecked(true);
                                     } else {
                                         rbUnqualified.setChecked(true);
                                     }
                                     String bhgyy = projectPublicInfoModel.getBhgyy();
                                     if (bhgyy!=null) {
                                         if (bhgyy.length()<1) {
                                             etDescription.setText("");
                                         }else{
                                             if (!rbQualified.isChecked()) {
                                                 etDescription.setText(bhgyy);
                                             }
                                         }
                                     }

                                     judgeJctpIsEmpty();
                                     lvCfqx.setAdapter(new ArrayAdapter<>(ProjectDetailSimpleActivity.this, R.layout.popup_person_grid_text_only, pubishList.toArray()));
                                     lvCfyj.setAdapter(new ArrayAdapter<>(ProjectDetailSimpleActivity.this, R.layout.popup_person_grid_text_only, refrenceBasisList.toArray()));
                                 }
                             }
                         }

                );
    }

    /**
     * @desc 判断检查图片是否为空
     * @date 2018/5/14 18:22
     */

    private void judgeJctpIsEmpty() {
        if (jctp != null) {
            if (jctp.length() > 0) {
                //有检查点图片返回时
                Log.e(TAG, "initData: 检查点图片不为空" + jctp);
                jctp+=", ";
                jctpNewList.clear();
                Log.e(TAG, "judgeJctpIsEmpty: "+jctp );
                if (jctp.contains(",")) {
                    jctpList = Arrays.asList(jctp.split(","));
                    for (int i = 0; i < jctpList.size(); i++) {
                        if (i == jctpList.size()-1) {
                            jctpList.set(i,"");
                        }else{
                            jctpList.set(i,jctpList.get(i));
                        }
                    }
                }
                jctpNewList.addAll(jctpList);
                imageGridAdapter = new ImageGridAdapter(jctpNewList, ProjectDetailSimpleActivity.this,isView);
                gridView.setAdapter(imageGridAdapter);
            } else {
                //没有图片时
                jctpNewList.clear();
                jctpNewList.add("");
                imageGridAdapter = new ImageGridAdapter(jctpNewList, ProjectDetailSimpleActivity.this,isView);
                gridView.setAdapter(imageGridAdapter);
                Log.e(TAG, "initData: 检查点图片为空");
            }
        } else {
            jctp = "";
            jctpNewList.clear();
            jctpNewList.add("");
            imageGridAdapter = new ImageGridAdapter(jctpNewList, ProjectDetailSimpleActivity.this,isView);
            gridView.setAdapter(imageGridAdapter);
            Log.e(TAG, "initData: 检查点图片为空");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: " + resultCode);
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
                        NetUtil.errorTip(ProjectDetailSimpleActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        gson = new Gson();
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            Toast.makeText(ProjectDetailSimpleActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                            if (jctp.length() > 0) {
                                jctp += ",";
                                jctp += infoModel.getMsg();
                            } else {
                                jctp += infoModel.getMsg();
                            }
                            imageGridAdapter.notifyData(infoModel.getMsg());
                        } else {
                            Toast.makeText(ProjectDetailSimpleActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.activity_project_detail_simple_header).findViewById(R.id.title);
        imgBack = (ImageView) findViewById(R.id.activity_project_detail_simple_header).findViewById(R.id.back);
        imgVideoCall = (ImageView) findViewById(R.id.activity_project_detail_simple_header).findViewById(R.id.videocall);
        imgNotice = (ImageView) findViewById(R.id.activity_project_detail_simple_header).findViewById(R.id.notice);
        imgMessage = (ImageView) findViewById(R.id.activity_project_detail_simple_header).findViewById(R.id.message);
        imgCall = (ImageView) findViewById(R.id.activity_project_detail_simple_header).findViewById(R.id.call);
        imgAdd = (ImageView) findViewById(R.id.activity_project_detail_simple_header).findViewById(R.id.add);
        //左面三个控件
        tvZtzrsx = (TextView) findViewById(R.id.activity_project_detail_simple_lv_ztzrsx);
        lvCfqx = (ListView) findViewById(R.id.activity_project_detail_simple_lv_cfqx);
        lvCfyj = (ListView) findViewById(R.id.activity_project_detail_simple_lv_cfyj);
        //radiobutton group
        radioGroup = (RadioGroup) findViewById(R.id.activity_check_items_item_rg);
        rbQualified = (RadioButton) findViewById(R.id.activity_project_detail_simple_rb_qualified);
        rbUnqualified = (RadioButton) findViewById(R.id.activity_project_detail_simple_rb_unqualified);
        //右面的详细描述输入
        etDescription = (EditText) findViewById(R.id.activity_project_detail_simple_et_xxms);
        btnCommit = (Button) findViewById(R.id.activity_project_detail_simple_btn_commit);
        //gridview
        gridView = (GridView) findViewById(R.id.activity_project_detail_simple_gv);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v != null) {
            SetMenuClick setMenuClick = new SetMenuClick(v.getId(), ProjectDetailSimpleActivity.this,ProjectDetailSimpleActivity.this);
            setMenuClick.setMenuClick();
            switch (v.getId()) {
                case R.id.activity_project_detail_simple_btn_commit:
                    if (rbQualified.isChecked()) {
                        jcjg = "1";
                    } else {
                        jcjg = "0";
                        if (etDescription.getText().toString().trim().length()<1) {
                            Toast.makeText(this, "详细描述未填写，不能提交", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    String jctp = "";
                    for (int i = 0; i < jctpNewList.size(); i++) {
                        if (i!=jctpNewList.size()-1) {
                            jctp+= jctpNewList.get(i).replace(Constant.UPLOAD_IMG_IP,"");
                            if (i!=jctpNewList.size()-2) {
                                jctp+=",";
                            }
                        }
                    }

                        OkHttpUtils.post()
                                .url(Constant.SERVER_URL + "/checkProject/check")
                                .addParams("id",ids.get(position).getId()+"")
                                .addParams("jcId", jcId)
                                .addParams("jclx", "1")//1 通项 2 危险源
                                .addParams("jctp", jctp)
                                .addParams("bhgyy", etDescription.getText().toString().trim())
                                .addParams("jcjg", jcjg)//0 不合格 1 合格
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {
                                        NetUtil.errorTip(ProjectDetailSimpleActivity.this, e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        Log.e(TAG, "onResponse: "+response );
                                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                        if (infoModel.isData()) {
                                            if (ids.size()>1) {
                                                ids.remove(0);
                                                if (ids.size() == 1) {
                                                    btnCommit.setText("完成");
                                                }
                                                initDataFromServer();
                                            }else {
                                                finish();
                                            }

                                        }
                                        Toast.makeText(ProjectDetailSimpleActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                });


                    break;
            }
        }
    }

    /**
     * @desc 弹出选项菜单
     * @date 2017/12/11 16:56
     */
    private void setDialog() {
        mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.bottom_dialog, null);
        //初始化视图
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) this.getResources().getDisplayMetrics().widthPixels / 2; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

}
