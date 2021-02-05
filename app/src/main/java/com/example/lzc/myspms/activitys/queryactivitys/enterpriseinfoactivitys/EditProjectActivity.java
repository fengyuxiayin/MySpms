package com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.ImageGridAdapter;
import com.example.lzc.myspms.adapters.UnsafeAdapter;
import com.example.lzc.myspms.custom.AutoFitTextView;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.ProjectFindByIdModel;
import com.example.lzc.myspms.models.UnsafeFindModel;
import com.example.lzc.myspms.utils.BitmapUtils;
import com.example.lzc.myspms.utils.DateUtil;
import com.example.lzc.myspms.utils.DialogUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.login.LoginException;

public class EditProjectActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = EditProjectActivity.class.getSimpleName();
    private boolean isEdit;
    private String id;
    private ClearEditText etFxdwmc;
    private ClearEditText etFxdwlx;
    private ClearEditText etBm;
    private ClearEditText etSybm;
    private ClearEditText etSydd;
    private ClearEditText etSbpp;
    private ClearEditText etSbxh;
    private ClearEditText etScrq;
    private ClearEditText etQyrq;
    private ClearEditText etSynx;
    private ClearEditText etBfnx;
    private ClearEditText etZyjgbm;
    private ClearEditText etZczq;
    private AutoFitTextView tvZczqdw;
    private GridView gridView;
    private PullToRefreshListView listView;
    private Button btnSave;
    private Gson gson = new Gson();
    //专业监管部门的数据
    private List<EnumModel> departmentList = new ArrayList<>();
    private List<String> departmentStrList = new ArrayList<>();
    //项目类型的数据
    private List<EnumModel> projectList = new ArrayList<>();
    private List<String> projectStrList = new ArrayList<>();
    //自查周期单位的数据
    private List<EnumModel> dwList = new ArrayList<>();
    private List<String> dwStrList = new ArrayList<>();
    //这个是存储jctpList数据的列表
    private List<String> jctpNewList = new ArrayList<>();
    //第一个是asList获取到的列表不可增删
    private List<String> jctpList = new ArrayList<>();
    private ImageGridAdapter imageGridAdapter;
    private String picPath;
    private File mTmpFile;
    private String sqtp;
    private ImageView imgAdd;
    private UnsafeAdapter unsafeAdapter;
    private List<UnsafeFindModel.UnsafeFindMsgModel.ListBean> list = new ArrayList<>();
    private ProjectFindByIdModel projectFindByIdModel;
    private String qyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        qyId = getIntent().getStringExtra("qyId");
        if (isEdit) {
            id = getIntent().getStringExtra("id");
        } else {
            id = "";
        }
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        etFxdwlx.setFocusable(false);
        etScrq.setFocusable(false);
        etQyrq.setFocusable(false);
        etZyjgbm.setFocusable(false);
        tvZczqdw.setFocusable(false);
        etFxdwlx.setOnClickListener(this);
        etScrq.setOnClickListener(this);
        etQyrq.setOnClickListener(this);
        etZyjgbm.setOnClickListener(this);
        tvZczqdw.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
    }

    public void setData(int pos, UnsafeFindModel.UnsafeFindMsgModel.ListBean data) {
        Log.e(TAG, "setData: " + unsafeAdapter);
        unsafeAdapter.setData(pos, data);
    }

    private void initData() {
        dwList.add(new EnumModel("1", "天"));
        dwList.add(new EnumModel("2", "周"));
        dwList.add(new EnumModel("3", "月"));
        dwStrList.add("天");
        dwStrList.add("周");
        dwStrList.add("月");
        initEnumData("/enum/getEnums", "PROJECT_TYPE", projectList);
        OkHttpUtils.get()
                .url(Constant.SERVER_URL + "/baseDepartment/findAll")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage() + e.getCause());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray msg = new JSONArray(jsonObject.optString("msg"));
                            for (int i = 0; i < msg.length(); i++) {
                                JSONObject o = (JSONObject) msg.get(i);
                                departmentList.add(new EnumModel(o.optString("id"), o.optString("departName")));
                                departmentStrList.add(o.optString("departName"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        initAllData();
                    }
                });
        if (isEdit) {
            OkHttpUtils.post()
                    .url(Constant.SERVER_URL + "/unsafe/find")
                    .addParams("wxyId", id)
                    .addParams("size", "100")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            NetUtil.errorTip(EditProjectActivity.this, e.getMessage() + e.getCause());
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: " + response);
                            UnsafeFindModel unsafeFindModel = gson.fromJson(response, UnsafeFindModel.class);
                            if (unsafeFindModel.isData()) {
                                UnsafeFindModel.UnsafeFindMsgModel unsafeFindMsgModel = gson.fromJson(unsafeFindModel.getMsg(), UnsafeFindModel.UnsafeFindMsgModel.class);
                                list = unsafeFindMsgModel.getList();
//                                if (list != null) {
                                unsafeAdapter = new UnsafeAdapter(list, EditProjectActivity.this);
                                listView.setAdapter(unsafeAdapter);
//                                }
                            }
                        }
                    });
        } else {
            unsafeAdapter = new UnsafeAdapter(list, EditProjectActivity.this);
            listView.setAdapter(unsafeAdapter);
        }
    }

    //获取当前页面的除去隐患点的数据
    private void initAllData() {
        if (isEdit) {
            //如果是编辑，那么请求网络数据并进行设置
            OkHttpUtils.post()
                    .url(Constant.SERVER_URL + "/project/findById")
                    .addParams("id", id)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e(TAG, "onError: " + e.getMessage() + e.getCause());
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: " + response);
                            LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                            if (infoModel.isData()) {
                                projectFindByIdModel = gson.fromJson(infoModel.getMsg(), ProjectFindByIdModel.class);
                                Log.e(TAG, "onResponse: " + projectFindByIdModel.getCreateBy());
                                etFxdwmc.setText(projectFindByIdModel.getWxymc() + "");
                                for (int i = 0; i < projectList.size(); i++) {
                                    Log.e(TAG, "onResponse: 1111" + projectFindByIdModel.getWxylx() + "" + projectList.get(i).getKey());
                                    if (projectList.get(i).getKey().equals(projectFindByIdModel.getWxylx() + "")) {
                                        etFxdwlx.setText(projectList.get(i).getValue());
                                        break;
                                    }
                                }
                                etBm.setText(projectFindByIdModel.getBm() != null ? projectFindByIdModel.getBm() + "" : "");
                                etSybm.setText(projectFindByIdModel.getSsbm() != null ? projectFindByIdModel.getSsbm() + "" : "");
                                etSydd.setText(projectFindByIdModel.getSydd() != null ? projectFindByIdModel.getSydd() + "" : "");
                                etSbpp.setText(projectFindByIdModel.getSbpp() != null ? projectFindByIdModel.getSbpp() + "" : "");
                                etSbxh.setText(projectFindByIdModel.getSbxh() != null ? projectFindByIdModel.getSbxh() + "" : "");
                                etScrq.setText(DateUtil.long2Date(projectFindByIdModel.getScrq()).contains("1970-01-01") ? "" : DateUtil.long2Date(projectFindByIdModel.getScrq()));
                                etQyrq.setText(DateUtil.long2Date(projectFindByIdModel.getQyrq()).contains("1970-01-01") ? "" : DateUtil.long2Date(projectFindByIdModel.getQyrq()));
                                etBfnx.setText(projectFindByIdModel.getBfnx() == null ? "" : projectFindByIdModel.getBfnx() + "");
                                etSynx.setText(projectFindByIdModel.getSynx() == null ? "" : projectFindByIdModel.getSynx() + "");
                                etZczq.setText(projectFindByIdModel.getZczq() == null ? "" : projectFindByIdModel.getZczq() + "");
                                if (projectFindByIdModel.getZczqdw() != null) {
                                    if (projectFindByIdModel.getZczqdw() != 0) {//等于0意味着自查周期单位没有设置过 默认为0
                                        tvZczqdw.setText(dwList.get(projectFindByIdModel.getZczqdw() - 1).getValue());
                                    }
                                } else {
                                    Toast.makeText(EditProjectActivity.this, "未给该企业设置自查周期！", Toast.LENGTH_SHORT).show();
                                }

                                for (int i = 0; i < departmentList.size(); i++) {
                                    if (departmentList.get(i).getKey().equals(projectFindByIdModel.getDeptId() + "")) {
                                        etZyjgbm.setText(departmentList.get(i).getValue());
                                        break;
                                    }
                                }
                                sqtp = projectFindByIdModel.getSbtp();
                                judgeJctpIsEmpty(projectFindByIdModel.getSbtp());
                            } else {
                                Toast.makeText(EditProjectActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        } else {
            //当是添加的时候
            sqtp = "";
            judgeJctpIsEmpty(sqtp);
        }
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
            case 10010:
                if (resultCode == 10010) {//如果不是10010 证明只是普通的返回，不是保存返回
                    String position = data.getStringExtra("position");
                    if (position == null) {
                        position = "-1";
                    }
                    UnsafeFindModel.UnsafeFindMsgModel.ListBean data1 = (UnsafeFindModel.UnsafeFindMsgModel.ListBean) data.getSerializableExtra("data");
                    setData(Integer.parseInt(position), data1);
                }
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
                .addParams("folder", "/source/community/")
                .addFile("file", file.getAbsolutePath(), file)
                .build()
                .execute(new StringCallback() {
                    private Gson gson;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + file.getAbsolutePath());
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(EditProjectActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        gson = new Gson();
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            Toast.makeText(EditProjectActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                            if (sqtp.length() > 0) {
                                sqtp += ",";
                                sqtp += infoModel.getMsg();
                            } else {
                                sqtp += infoModel.getMsg();
                            }
                            imageGridAdapter.notifyData(infoModel.getMsg());
                        } else {
                            Toast.makeText(EditProjectActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
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

    /**
     * @desc 判断检查图片是否为空 并绑定gridview的数据源
     * @date 2018/5/14 18:22
     */
    private void judgeJctpIsEmpty(String sqtp) {
        if (sqtp != null) {
            if (sqtp.length() > 0) {
                //有检查点图片返回时
                Log.e(TAG, "initData: 检查点图片不为空" + sqtp);
                sqtp += ", ";//加个空格是判断后面有值 不能删除
                jctpNewList.clear();
                Log.e(TAG, "judgeJctpIsEmpty: " + sqtp);
                if (sqtp.contains(",")) {
                    jctpList = Arrays.asList(sqtp.split(","));
                    for (int i = 0; i < jctpList.size(); i++) {
                        if (i == jctpList.size() - 1) {
                            jctpList.set(i, "");
                        } else {
                            jctpList.set(i, jctpList.get(i));
                        }
                    }
                }
                jctpNewList.addAll(jctpList);
                Log.e(TAG, "judgeJctpIsEmpty: jctpNewList.size:" + jctpNewList.size());
                imageGridAdapter = new ImageGridAdapter(jctpNewList, this, "");
                gridView.setAdapter(imageGridAdapter);
            } else {
                //没有图片时
                jctpNewList.clear();
                jctpNewList.add("");
                imageGridAdapter = new ImageGridAdapter(jctpNewList, this, "");
                gridView.setAdapter(imageGridAdapter);
                Log.e(TAG, "initData: 检查点图片为空");
            }
        } else {
            sqtp = "";
            jctpNewList.clear();
            jctpNewList.add("");
            imageGridAdapter = new ImageGridAdapter(jctpNewList, this, "");
            gridView.setAdapter(imageGridAdapter);
            Log.e(TAG, "initData: 检查点图片为空");
        }
    }

    //获取枚举数据
    private void initEnumData(String url, final String params, final List<EnumModel> data) {
        //获取其他类型的Enum数据时都需要参数
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + url)
                .addParams("code", params)
                .build().execute(new com.zhy.http.okhttp.callback.StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                NetUtil.errorTip(EditProjectActivity.this, e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                Log.e(TAG, "initEnumData: " + params + response);
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //解析json数据 这里用不了gson 因为gson要求object开头
                            JSONObject o = (JSONObject) jsonArray.get(i);
                            //监管等级 标准化等级 企业风险分级 企业规模
                            String value = "";
                            value = o.optString("value") + o.optString("memo");
                            String key = o.optString("key");
                            data.add(new EnumModel(key, value));
                            projectStrList.add(value);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initView() {
        etFxdwmc = (ClearEditText) findViewById(R.id.activity_edit_project_et_fxdwmc);
        etFxdwlx = (ClearEditText) findViewById(R.id.activity_edit_project_et_fxdwlx);
        etBm = (ClearEditText) findViewById(R.id.activity_edit_project_et_bm);
        etSybm = (ClearEditText) findViewById(R.id.activity_edit_project_et_sybm);
        etSydd = (ClearEditText) findViewById(R.id.activity_edit_project_et_sydd);
        etSbpp = (ClearEditText) findViewById(R.id.activity_edit_project_et_sbpp);
        etSbxh = (ClearEditText) findViewById(R.id.activity_edit_project_et_sbxh);
        etScrq = (ClearEditText) findViewById(R.id.activity_edit_project_et_scrq);
        etQyrq = (ClearEditText) findViewById(R.id.activity_edit_project_et_qyrq);
        etSynx = (ClearEditText) findViewById(R.id.activity_edit_project_et_synx);
        etBfnx = (ClearEditText) findViewById(R.id.activity_edit_project_et_bfnx);
        etZyjgbm = (ClearEditText) findViewById(R.id.activity_edit_project_et_zyjgbm);
        etZczq = (ClearEditText) findViewById(R.id.activity_edit_project_et_zczq);
        tvZczqdw = (AutoFitTextView) findViewById(R.id.activity_edit_project_et_zczqdw);
        gridView = (GridView) findViewById(R.id.activity_edit_project_gv);
        listView = (PullToRefreshListView) findViewById(R.id.activity_edit_project_lv);
        btnSave = (Button) findViewById(R.id.activity_edit_project_btn_save);
        imgAdd = (ImageView) findViewById(R.id.activity_edit_project_img_add);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.activity_edit_project_et_fxdwlx:
                    DialogUtil.showChooseDialog(projectList, etFxdwlx, this);
                    break;
                case R.id.activity_edit_project_et_qyrq:
                    setDate(etQyrq);
                    break;
                case R.id.activity_edit_project_et_scrq:
                    setDate(etScrq);
                    break;
                case R.id.activity_edit_project_et_zyjgbm:
                    DialogUtil.showChooseDialog(departmentList, etZyjgbm, this);
                    break;
                case R.id.activity_edit_project_et_zczqdw:
                    DialogUtil.showChooseDialog(dwList, tvZczqdw, this);
                    break;
                case R.id.activity_edit_project_btn_save:
                    Log.e(TAG, "onClick: ");
                    if (etFxdwmc.getText().toString().trim().length() < 1) {
                        Toast.makeText(this, "请先填写风险点位名称", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (etFxdwlx.getText().toString().trim().length() < 1) {
                        Toast.makeText(this, "请先选择风险定位类型", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (list.size() < 1) {
                        Toast.makeText(this, "请先添加隐患点", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String wxylx = "";
                    for (int i = 0; i < projectStrList.size(); i++) {
                        if (projectStrList.get(i).equals(etFxdwlx.getText().toString().trim())) {
                            wxylx = (i + 1) + "";
                            break;
                        }
                    }
                    String deptId = "";
                    for (int i = 0; i < departmentList.size(); i++) {
                        if (departmentList.get(i).getValue().equals(etZyjgbm.getText().toString().trim())) {
                            deptId = departmentList.get(i).getKey();
                            break;
                        }
                    }
                    String zczqdw = "";
                    for (int i = 0; i < dwList.size(); i++) {
                        if (dwList.get(i).getValue().equals(tvZczqdw.getText().toString().trim())) {
                            zczqdw = dwList.get(i).getKey();
                            break;
                        }
                    }
                    sqtp = "";
                    for (int i = 0; i < jctpNewList.size() - 1; i++) {
                        if (i == 0) {
                            sqtp += jctpNewList.get(i);
                        } else {
                            sqtp += "," + jctpNewList.get(i);

                        }
                    }
                    Log.e(TAG, "onClick: " + qyId + " wxylx" + wxylx + " sqtp" + sqtp + " deptId" + deptId + " yhdItem" + gson.toJson(list) + " id" + id + " zczqdw" + zczqdw);
                    OkHttpUtils.post()
                            .url(Constant.SERVER_URL + "/project/save")
                            .addParams("qyId", qyId)
                            .addParams("wxylx", wxylx)
                            .addParams("wxymc", etFxdwmc.getText().toString().trim() + "")
                            .addParams("bm", etBm.getText().toString().trim() + "")
                            .addParams("ssbm", etSybm.getText().toString().trim() + "")
                            .addParams("sydd", etSydd.getText().toString().trim() + "")
                            .addParams("sbpp", etSbpp.getText().toString().trim() + "")
                            .addParams("sbxh", etSbxh.getText().toString().trim() + "")
                            .addParams("sbtp", sqtp.trim())
                            .addParams("scrqStr", etScrq.getText().toString().trim() + "")
                            .addParams("qyrqStr", etQyrq.getText().toString().trim() + "")
                            .addParams("synx", etSynx.getText().toString().trim() + "")
                            .addParams("bfnx", etBfnx.getText().toString().trim() + "")
                            .addParams("yhdItem", gson.toJson(list))
                            .addParams("deptId", deptId)
                            .addParams("id", id)
                            .addParams("zczq", etZczq.getText().toString().trim() + "")
                            .addParams("zczqdw", zczqdw)
                            .addParams("sybm", etSybm.getText().toString().trim() + "")
                            .addParams("xynx", etSynx.getText().toString().trim() + "")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    Log.e(TAG, "onError: " + e.getMessage() + e.getCause());
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e(TAG, "onResponse: " + response);
                                    LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                    if (infoModel.isData()) {
                                        Toast.makeText(EditProjectActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    Toast.makeText(EditProjectActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    //先判断数据是否合格
                    break;
                case R.id.activity_edit_project_img_add:
                    Intent intent = new Intent();
                    intent.setClass(EditProjectActivity.this, UnsafeActivity.class);
                    intent.putExtra("yhdmc", "");
                    intent.putExtra("xh", "");
                    intent.putExtra("yhdxq", "");
                    intent.putExtra("bz", "");
                    intent.putExtra("yhdtp", "");
                    intent.putExtra("position", "");
                    intent.putExtra("data", "");
                    startActivityForResult(intent, 10010);
                    break;
            }
        }
    }

    /**
     * @param edittext 需要设置显示内容的Edittext
     * @desc 显示日历，并将选择的时间变为指定格式显示在Edittext上
     * @date 2017/12/11 14:27
     */
    private void setDate(final EditText edittext) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(year, monthOfYear, dayOfMonth);
                edittext.setText(DateFormat.format("yyyy-MM-dd", calendar));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
