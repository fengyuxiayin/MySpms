package com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys;

import android.app.Activity;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.adapters.ImageGridAdapter;
import com.example.lzc.myspms.adapters.UnsafeAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.UnsafeFindModel;
import com.example.lzc.myspms.utils.BitmapUtils;
import com.example.lzc.myspms.utils.NetUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnsafeActivity extends AppCompatActivity {
    public static final String TAG = UnsafeActivity.class.getSimpleName();
    //这个是存储jctpList数据的列表
    private List<String> jctpNewList = new ArrayList<>();
    //第一个是asList获取到的列表不可增删
    private List<String> jctpList = new ArrayList<>();
    private ImageGridAdapter imageGridAdapter;
    private ClearEditText etYhdmc;
    private ClearEditText etYhdxh;
    private ClearEditText etYhdxq;
    private ClearEditText etBz;
    private GridView gridView;
    private Button btnSave;
    private String yhdxq;
    private String xh;
    private String yhdmc;
    private String bz;
    private String yhdtp;
    private String picPath;
    private File mTmpFile;
    private UnsafeFindModel.UnsafeFindMsgModel.ListBean data;
    private String position;
    private EditProjectActivity activity;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsafe);
        yhdmc = getIntent().getStringExtra("yhdmc");
        if (!yhdmc.equals("")) {
            yhdxq = getIntent().getStringExtra("yhdxq");
            xh = getIntent().getStringExtra("xh");
            bz = getIntent().getStringExtra("bz");
            yhdtp = getIntent().getStringExtra("yhdtp");
            position = getIntent().getStringExtra("position");
            activity = (EditProjectActivity) getIntent().getSerializableExtra("activity");
            data = (UnsafeFindModel.UnsafeFindMsgModel.ListBean) getIntent().getSerializableExtra("data");
        } else {
            UnsafeFindModel.UnsafeFindMsgModel unsafeFindMsgModel = new UnsafeFindModel.UnsafeFindMsgModel();
            data = unsafeFindMsgModel.getInstance();
            Log.e(TAG, "onCreate: " + gson.toJson(data));
            data.setStatus(1);
            yhdtp = "";
        }

        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etYhdxq.getText().toString().trim().length() > 0 && etYhdmc.getText().toString().trim().length() > 0) {
                    yhdtp = "";
                    for (int i = 0; i < jctpNewList.size() - 1; i++) {
                        if (i == 0) {
                            yhdtp += jctpNewList.get(i);
                        } else {
                            yhdtp += "," + jctpNewList.get(i);
                        }
                    }
                    data.setYhdtp(yhdtp.trim());
//                    Intent intent = getIntent();
//                    dataListener.setData(Integer.parseInt(position),data);
                    Intent intent = new Intent();
                    intent.putExtra("data", (Serializable) data);
                    intent.putExtra("position", position);
                    setResult(10010, intent);
                    finish();
                } else {
                    Toast.makeText(UnsafeActivity.this, "请先填写必填项", Toast.LENGTH_SHORT).show();
                }
            }
        });
        etYhdmc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.setYhdmc(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etYhdxh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.setXh(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etBz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.setBz(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etYhdxq.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.setYhdxq(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

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

    public interface DataListener {
        void setData(int pos, UnsafeFindModel.UnsafeFindMsgModel.ListBean data);
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
                        NetUtil.errorTip(UnsafeActivity.this, e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        gson = new Gson();
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            Toast.makeText(UnsafeActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                            if (yhdtp.length() > 0) {
                                yhdtp += ",";
                                yhdtp += infoModel.getMsg();
                            } else {
                                yhdtp += infoModel.getMsg();
                            }
                            imageGridAdapter.notifyData(infoModel.getMsg());
                        } else {
                            Toast.makeText(UnsafeActivity.this, infoModel.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void initData() {
        if (!yhdmc.equals("")) {
            etYhdmc.setText(data.getYhdmc() + "");
            etYhdxq.setText(data.getYhdxq() + "");
//            if (data.getXh().equals("0")) {
//                etYhdxq.setText("");
//                data.setXh("0");
//            }else{
//                etYhdxq.setText(data.getYhdxq() + "");
//            }
//            Log.e(TAG, "initData: "+data.getXh().toString() );
            if (data.getXh()==null) {
                etYhdxh.setText("");
            } else {
                etYhdxh.setText(data.getXh()+"");
            }
            etBz.setText(data.getBz() + "");
            judgeJctpIsEmpty(data.getYhdtp());
        } else {
            judgeJctpIsEmpty("");
        }

    }

    private void initView() {
        etYhdmc = (ClearEditText) findViewById(R.id.popup_unsafe_et_yhdmc);
        etYhdxh = (ClearEditText) findViewById(R.id.popup_unsafe_et_yhdxh);
        etYhdxq = (ClearEditText) findViewById(R.id.popup_unsafe_et_yhdxq);
        etBz = (ClearEditText) findViewById(R.id.popup_unsafe_et_bz);
        gridView = (GridView) findViewById(R.id.popup_unsafe_gv);
        btnSave = (Button) findViewById(R.id.popup_unsafe_btn_save);
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

}
