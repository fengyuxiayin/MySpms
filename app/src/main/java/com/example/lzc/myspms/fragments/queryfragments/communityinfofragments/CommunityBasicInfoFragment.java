package com.example.lzc.myspms.fragments.queryfragments.communityinfofragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.checkitemactivitys.ProjectDetailSimpleActivity;
import com.example.lzc.myspms.activitys.queryactivitys.communityinfoactivitys.EditCommunityInfoActivity;
import com.example.lzc.myspms.adapters.ImageGridAdapter;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.CommunityBasicInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by LZC on 2017/10/31.
 */
public class CommunityBasicInfoFragment extends BaseFragment {
    public static final String TAG = CommunityBasicInfoFragment.class.getSimpleName();
    private String sqId;
    private EditText tvCommunityName;
    private EditText tvCommunityAddress;
    private EditText tvCommunityPeople;
    private EditText tvCommunityAcreage;
    private View linearlayout;
    private boolean isView;
    private String isView1;
    private Button btnSave;
    private GridView gridView;
    //这个是存储jctpList数据的列表
    private List<String> jctpNewList = new ArrayList<>();
    //第一个是asList获取到的列表不可增删
    private List<String> jctpList = new ArrayList<>();
    private ImageGridAdapter imageGridAdapter;
    private File mTmpFile;
    private String picPath;
    private String sqtp = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community_basic_info, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sqId = getArguments().getString("sqId");
        isView = getArguments().getBoolean("isView", false);
        initView();
        initData();
        initListener();
        if (isView) {
            isView1 = "isView";
            tvCommunityName.setFocusable(false);
            tvCommunityAddress.setFocusable(false);
            tvCommunityPeople.setFocusable(false);
            tvCommunityAcreage.setFocusable(false);
            btnSave.setVisibility(View.GONE);
        }else{
            isView1 = "";
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
     * @param data intent包含的数据
     * @desc 选择图片上传到服务器 获取其返回的url
     * @date 2017/12/11 17:20
     */
    private void doPhoto(Intent data) {
        Log.e(TAG, "doPhoto: " + data);
        if (data == null) {
            Toast.makeText(getContext(), "未选择图片", Toast.LENGTH_LONG).show();
            return;
        }
        Uri photoUri = data.getData();
        if (photoUri == null) {
            Toast.makeText(getContext(), "选择图片文件出错", Toast.LENGTH_LONG).show();
            return;
        }
        String[] pojo = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(photoUri, pojo, null, null, null);
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
            Toast.makeText(getContext(), "选择图片文件不正确", Toast.LENGTH_LONG).show();
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
                        NetUtil.errorTip(getContext(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        gson = new Gson();
                        LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                        if (infoModel.isData()) {
                            Toast.makeText(getContext(), "图片上传成功", Toast.LENGTH_SHORT).show();
                            if (sqtp.length() > 0) {
                                sqtp += ",";
                                sqtp += infoModel.getMsg();
                            } else {
                                sqtp += infoModel.getMsg();
                            }
                            imageGridAdapter.notifyData(infoModel.getMsg());
                        } else {
                            Toast.makeText(getContext(), infoModel.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void initListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断数据是否合法
                if (tvCommunityName.getText().toString().trim()==null) {
                    Toast.makeText(getContext(), "社区名称不能为空", Toast.LENGTH_SHORT).show();
                }
                if (tvCommunityAddress.getText().toString().trim()==null) {
                    Toast.makeText(getContext(), "社区地址不能为空", Toast.LENGTH_SHORT).show();
                }
                //将社区图片数据修正
                sqtp = "";
                for (int i = 0; i < jctpNewList.size(); i++) {
                    if (i!=jctpNewList.size()-1) {
                        sqtp+= jctpNewList.get(i).replace(Constant.UPLOAD_IMG_IP,"");
                        if (i!=jctpNewList.size()-2) {
                            sqtp+=",";
                        }
                    }
                }
                //向服务器发送修改过后的社区信息
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL+"/community/save")
                        .addParams("id",sqId)
                        .addParams("sqmc",tvCommunityName.getText().toString().trim())
                        .addParams("sqdz",tvCommunityAddress.getText().toString().trim())
                        .addParams("sqrk",tvCommunityPeople.getText().toString().trim())
                        .addParams("sqmj",tvCommunityAcreage.getText().toString().trim())
                        .addParams("sqtp",sqtp)
                        .build()
                        .execute(new StringCallback() {
                            private Gson gson;

                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: "+e.getMessage() );
                                NetUtil.errorTip(getContext(),e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: "+response );
                                gson = new Gson();
                                LoginInfoModel infoModel = gson.fromJson(response, LoginInfoModel.class);
                                Toast.makeText(getContext(), infoModel.getMsg(), Toast.LENGTH_SHORT).show();
                                initData();
                            }
                        });
            }
        });
    }

    private void initView() {
        linearlayout = view.findViewById(R.id.fragment_community_basic_info);
        tvCommunityName = (EditText) view.findViewById(R.id.activity_community_basic_info_detail_tv_community_name);
        tvCommunityAddress = (EditText) view.findViewById(R.id.activity_community_basic_info_detail_tv_community_address);
        tvCommunityPeople = (EditText) view.findViewById(R.id.activity_community_basic_info_detail_tv_people);
        tvCommunityAcreage = (EditText) view.findViewById(R.id.activity_community_basic_info_detail_tv_acreage);
        btnSave = (Button) view.findViewById(R.id.activity_community_basic_info_btn_save);
        gridView = (GridView) view.findViewById(R.id.activity_community_basic_info_gv);
    }

    private void initData() {
        jctpList = new ArrayList<>();
        jctpNewList = new ArrayList<>();
        sqtp = "";
        //根据社区id从服务器把社区信息加载下来
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/community/findById")
                .addParams("id", sqId)
                .build()
                .execute(new StringCallback() {

                    private Gson gson;

                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        linearlayout.setVisibility(View.VISIBLE);
                        gson = new Gson();
                        CommunityBasicInfoModel communityBasicInfoModel = gson.fromJson(response, CommunityBasicInfoModel.class);
                        if (communityBasicInfoModel.isData()) {
                            CommunityBasicInfoModel.CommunityBasicInfoMsgModel communityBasicInfoMsgModel = gson.fromJson(communityBasicInfoModel.getMsg(), CommunityBasicInfoModel.CommunityBasicInfoMsgModel.class);
                            tvCommunityName.setText(communityBasicInfoMsgModel.getSqmc());
                            tvCommunityAddress.setText(communityBasicInfoMsgModel.getSqdz());
                            Log.e(TAG, "onResponse: " + communityBasicInfoMsgModel.getSqrk() + "" + communityBasicInfoMsgModel.getSqmj());
                            tvCommunityPeople.setText(String.valueOf(communityBasicInfoMsgModel.getSqrk()));
                            tvCommunityAcreage.setText(String.valueOf(communityBasicInfoMsgModel.getSqmj()));
                            sqtp = communityBasicInfoMsgModel.getSqtp();
                            judgeJctpIsEmpty(communityBasicInfoMsgModel.getSqtp());
                        } else {
                            Toast.makeText(getContext(), communityBasicInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    /**
     * @desc 判断检查图片是否为空
     * @date 2018/5/14 18:22
     */

    private void judgeJctpIsEmpty(String sqtp) {
        if (sqtp != null) {
            if (sqtp.length() > 0) {
                //有检查点图片返回时
                Log.e(TAG, "initData: 检查点图片不为空" + sqtp);
                sqtp+=", ";//加个空格是判断后面有值 不能删除
                jctpNewList.clear();
                Log.e(TAG, "judgeJctpIsEmpty: "+sqtp );
                if (sqtp.contains(",")) {
                    jctpList = Arrays.asList(sqtp.split(","));
                    for (int i = 0; i < jctpList.size(); i++) {
                        if (i == jctpList.size()-1) {
                            jctpList.set(i,"");
                        }else{
                            jctpList.set(i,jctpList.get(i));
                        }
                    }
                }
                jctpNewList.addAll(jctpList);
                Log.e(TAG, "judgeJctpIsEmpty: jctpNewList.size:"+jctpNewList.size() );
                imageGridAdapter = new ImageGridAdapter(jctpNewList, getActivity(),isView1);
                gridView.setAdapter(imageGridAdapter);
            } else {
                //没有图片时
                jctpNewList.clear();
                jctpNewList.add("");
                imageGridAdapter = new ImageGridAdapter(jctpNewList, getActivity(),isView1);
                gridView.setAdapter(imageGridAdapter);
                Log.e(TAG, "initData: 检查点图片为空");
            }
        } else {
            sqtp = "";
            jctpNewList.clear();
            jctpNewList.add("");
            imageGridAdapter = new ImageGridAdapter(jctpNewList, getActivity(),isView1);
            gridView.setAdapter(imageGridAdapter);
            Log.e(TAG, "initData: 检查点图片为空");
        }
    }
}
