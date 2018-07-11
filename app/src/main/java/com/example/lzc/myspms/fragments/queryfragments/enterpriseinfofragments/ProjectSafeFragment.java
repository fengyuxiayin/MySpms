package com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.adapters.ProjectSafeAdapter;
import com.example.lzc.myspms.adapters.SimpleTreeAdapter;
import com.example.lzc.myspms.bean.FileBean;
import com.example.lzc.myspms.bean.Node;
import com.example.lzc.myspms.bean.TreeListViewAdapter;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.models.ProjectBasicInfoModel;
import com.example.lzc.myspms.models.ProjectDetailModel;
import com.example.lzc.myspms.models.ProjectSafeInfo;
import com.example.lzc.myspms.utils.BitmapUtils;
import com.example.lzc.myspms.utils.NetUtil;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by LZC on 2017/10/31.
 */
public class ProjectSafeFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = ProjectSafeFragment.class.getSimpleName();
    private String xmId;
    private String qymc;
    private String startClass;
    private Gson gson;
    private ImageView imgSafe;
    private ImageView imgCustom;
    private LinearLayout linearLayout;
    private ListView listViewSafe;
    private ListView listViewCustom;
    private Button btnFresh;
    //判断项是否展开或者关闭
    private boolean imgCustomChange;
    private boolean imgSafeChange;
    //传进Adapter的数据
    private List<ProjectSafeInfo> data = new ArrayList<>();
    private ProjectSafeAdapter projectSafeAdapter;
    //拍照的图片的临时文件
    private File mTmpFile;
    //选择图片的文件路径
    private String picPath;
    //存储treedata的数组
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private SimpleTreeAdapter mTreeDataAdapter;
    private ProjectDetailModel.ProjectDetailMsgModel projectDetailMsgModel;
    private TextView tvRefresh;
    //判断在当前fragment是否按下了返回键
    private boolean isBack = false;
    private List<String> jctpList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project_safe, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xmId = getArguments().getString("xmId");
        qymc = getArguments().getString("qymc");
        startClass = getArguments().getString("startClass");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
//        imgSafe.setOnClickListener(this);
//        imgCustom.setOnClickListener(this);
//        btnFresh.setOnClickListener(this);
        tvRefresh.setOnClickListener(this);
    }

    private void initView() {
        listViewCustom = (ListView) view.findViewById(R.id.fragment_project_safe_lv);
        listViewSafe = (ListView) view.findViewById(R.id.fragment_project_safe_lv_treedata);
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_project_safe_ll);
        tvRefresh = (TextView) view.findViewById(R.id.fragment_project_safe_tv);
    }

    private void initData() {
        gson = new Gson();
        if (!startClass.equals("CheckItemSearchResultActivity")) {//添加项目
            data.add(new ProjectSafeInfo("", "", jctpList));
            projectSafeAdapter = new ProjectSafeAdapter(this, getContext(), data, (AddProjectActivity) getActivity(), listViewCustom, new ProjectSafeAdapter.IProjectSafeInfo() {
                @Override
                public void getSafeData(List<ProjectSafeInfo> data) {
                    if (ProjectSafeFragment.this.data != null) {  //safedata已经初始化过了
                    if (ProjectSafeFragment.this.data.size() == data.size()) { //如果数量相等，证明只是文字变了
                        Log.e(TAG, "getSafeData: 文字改变" + data.get(Constant.POINT_POSITION).getName() + Constant.POINT_POSITION);
                        ProjectSafeFragment.this.data.set(Constant.POINT_POSITION, new ProjectSafeInfo(data.get(Constant.POINT_POSITION).getId(), data.get(Constant.POINT_POSITION).getName(), ProjectSafeFragment.this.data.get(Constant.POINT_POSITION).getData()));
                    } else if (ProjectSafeFragment.this.data.size() < data.size()) {//如果数量变多了，先把新添加的数据添加上
                        Log.e(TAG, "getSafeData: 数量变了");
                        for (int i = ProjectSafeFragment.this.data.size(); i < data.size(); i++) {
                            Log.e(TAG, "getSafeData: 添加新数据" + i);
                            ProjectSafeFragment.this.data.add(data.get(i));
                        }
                        //然后把原先的数据修改一下
                        for (int i = 0; i < ProjectSafeFragment.this.data.size(); i++) {
                            Log.e(TAG, "getSafeData: 修改新数据" + i);
                            ProjectSafeFragment.this.data.set(i, new ProjectSafeInfo(data.get(i).getId(), data.get(i).getName(), ProjectSafeFragment.this.data.get(i).getData()));
                        }
                    } else {//如果数量变少了，证明删除了
                        ProjectSafeFragment.this.data.remove(Constant.POINT_POSITION);
                        Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    }
                    } else {
                        Log.e(TAG, "getSafeData: dasedata" + ProjectSafeFragment.this.data);
                        ProjectSafeFragment.this.data = new ArrayList<>();
                        ProjectSafeFragment.this.data.addAll(data);
                    }
                    ((AddProjectActivity) getActivity()).getSafeData(data);
                }
            });
            listViewCustom.setAdapter(projectSafeAdapter);
            listViewCustom.setTag("自定义");
            setListViewHeightBasedOnChildren(listViewCustom);
        } else {//修改项目
            getDataFromServer();
        }
//        getTreeData();

    }

    /**
     * @desc 从服务器把项目数据拉下来
     * @date 2017/12/19 11:05
     */
    private void getDataFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/project/findById")
                .addParams("id", xmId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        ProjectDetailModel projectDetailModel = gson.fromJson(response, ProjectDetailModel.class);
                        if (projectDetailModel.isData()) {
                            projectDetailMsgModel = gson.fromJson(projectDetailModel.getMsg(), ProjectDetailModel.ProjectDetailMsgModel.class);
                            try {
                                JSONArray jsonArray = new JSONArray(projectDetailMsgModel.getUnsafe());
                                Log.e(TAG, "onResponse: " + jsonArray.length());
                                if (jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        ProjectDetailModel.ProjectDetailMsgModel.ProjectDetailUnsafeModel projectDetailUnsafeModel = gson.fromJson(jsonArray.get(i).toString(), ProjectDetailModel.ProjectDetailMsgModel.ProjectDetailUnsafeModel.class);
                                        String jctp = projectDetailUnsafeModel.getJctp();
                                        if (jctp!=null) {
                                            if (jctp.length()>0) {
                                                //有检查点图片返回时
                                                Log.e(TAG, "initData: 检查点图片不为空"+jctp);
                                                jctpList = Arrays.asList(jctp.split(","));
                                                data.add(new ProjectSafeInfo(projectDetailUnsafeModel.getId() + "", projectDetailUnsafeModel.getMemo(), jctpList));
                                            } else {
                                                //没有图片时
                                                jctpList = new ArrayList<String>();
                                                data.add(new ProjectSafeInfo(projectDetailUnsafeModel.getId() + "", projectDetailUnsafeModel.getMemo(), jctpList));
                                                Log.e(TAG, "initData: 检查点图片为空");
                                            }
                                        }else{
                                            jctpList = new ArrayList<String>();
                                            data.add(new ProjectSafeInfo(projectDetailUnsafeModel.getId() + "", projectDetailUnsafeModel.getMemo(), jctpList));
                                            Log.e(TAG, "initData: 检查点图片为空");
                                        }


                                    }
                                    projectSafeAdapter = new ProjectSafeAdapter(ProjectSafeFragment.this, getContext(), data, (AddProjectActivity) getActivity(), listViewCustom, new ProjectSafeAdapter.IProjectSafeInfo() {
                                        @Override
                                        public void getSafeData(List<ProjectSafeInfo> data) {
                                            if (ProjectSafeFragment.this.data != null) {  //data已经初始化过了
                                                if (ProjectSafeFragment.this.data.size() == data.size()) { //如果数量相等，证明只是文字变了
                                                    Log.e(TAG, "getSafeData: 文字改变" + data.get(Constant.POINT_POSITION).getName() + Constant.POINT_POSITION);
                                                    ProjectSafeFragment.this.data.set(Constant.POINT_POSITION, new ProjectSafeInfo(data.get(Constant.POINT_POSITION).getId(), data.get(Constant.POINT_POSITION).getName(), ProjectSafeFragment.this.data.get(Constant.POINT_POSITION).getData()));
                                                } else if (ProjectSafeFragment.this.data.size() < data.size()) {//如果数量变多了，先把新添加的数据添加上
                                                    Log.e(TAG, "getSafeData: 数量变了");
                                                    for (int i = ProjectSafeFragment.this.data.size(); i < data.size(); i++) {
                                                        Log.e(TAG, "getSafeData: 添加新数据" + i);
                                                        ProjectSafeFragment.this.data.add(data.get(i));
                                                    }
                                                    //然后把原先的数据修改一下
                                                    for (int i = 0; i < ProjectSafeFragment.this.data.size(); i++) {
                                                        Log.e(TAG, "getSafeData: 修改新数据" + i);
                                                        ProjectSafeFragment.this.data.set(i, new ProjectSafeInfo(data.get(i).getId(), data.get(i).getName(), ProjectSafeFragment.this.data.get(i).getData()));
                                                    }
                                                } else {//如果数量变少了，证明删除了
                                                    ProjectSafeFragment.this.data.remove(Constant.POINT_POSITION);
                                                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                ProjectSafeFragment.this.data = new ArrayList<>();
                                                ProjectSafeFragment.this.data.addAll(data);
                                            }
//                                            Log.e(TAG, "getSafeData111: "+data.size() );
//                                            ProjectSafeFragment.this.data = new ArrayList<>();
//                                            for (int i = 0; i < data.size(); i++) {
//                                                ProjectSafeFragment.this.data.add(data.get(i));
//                                            }
//                                            Log.e(TAG, "getSafeData:111 "+ ProjectSafeFragment.this.data.size());
                                            ((AddProjectActivity) getActivity()).getSafeData(data);
                                        }
                                    });
                                    listViewCustom.setAdapter(projectSafeAdapter);
                                    setListViewHeightBasedOnChildren(listViewCustom);
                                } else {
                                    Toast.makeText(getContext(), "当前没有检查点信息", Toast.LENGTH_SHORT).show();
                                    data.add(new ProjectSafeInfo("", "", new ArrayList<String>()));
                                }
                            } catch (JSONException e) {
                                Log.e(TAG, "onResponse: " + e.getMessage());
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(getContext(),projectDetailModel.getMsg() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * @param
     * @desc 获取级联信息
     * @date 2017/12/7 15:13
     */
    private void getTreeData() {
        int pid = -1;
        ProjectBasicInfoModel basicInfo = ((AddProjectActivity) getActivity()).getBasicInfo();
        //判断是否填写首页信息
        if (basicInfo == null) {
            Toast.makeText(getContext(), "请先正确填写基础信息", Toast.LENGTH_SHORT).show();
        } else {//给pid赋值
            if (basicInfo.getXmlx() == null) {//
                Log.e(TAG, "getTreeData: ");
                return;
            }
            if (basicInfo.getSjys() == null) {
                if (basicInfo.getSays() == null) {
                    if (basicInfo.getEjys() == null) {
                        pid = basicInfo.getXmlx();
                    } else {
                        pid = basicInfo.getEjys();
                    }
                } else {
                    pid = basicInfo.getSays();
                }
            } else {
                pid = basicInfo.getSjys();
            }
            if (pid > -1) {
                OkHttpUtils.post()
                        .url(Constant.SERVER_URL + "/baseSafetyHazard/getTree")
                        .addParams("pid", pid + "")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                                NetUtil.errorTip(getContext(), e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: " + response);
                                initDatas(response);
                                try {
                                    mTreeDataAdapter = new SimpleTreeAdapter<FileBean>(listViewSafe, getContext(), mDatas, 10);
                                    mTreeDataAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                                        @Override
                                        public void onClick(Node node, int position) {
                                            if (node.isLeaf()) {
                                                Toast.makeText(getContext().getApplicationContext(), node.getName(),
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                            setListViewHeightBasedOnChildren(listViewSafe);
                                        }
                                    });

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                listViewSafe.setAdapter(mTreeDataAdapter);
                                listViewSafe.setTag("非自定义");
                                setListViewHeightBasedOnChildren(listViewSafe);
                            }
                        });
            } else {
                Toast.makeText(getContext(), "您还没有选择任何安全要素", Toast.LENGTH_SHORT).show();
            }
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
                        List<String> list = new ArrayList<String>();
                        list.addAll(ProjectSafeFragment.this.data.get(Constant.POINT_POSITION).getData());
                        if (infoModel.isData()) {
                            list.add(infoModel.getMsg());
                            data.set(Constant.POINT_POSITION, new ProjectSafeInfo(ProjectSafeFragment.this.data.get(Constant.POINT_POSITION).getId(), ProjectSafeFragment.this.data.get(Constant.POINT_POSITION).getName(), list));
                            ((AddProjectActivity) getActivity()).getSafeData(data);
                            projectSafeAdapter.changeData(data);
                            Toast.makeText(getContext(), "上传图片成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "上传图片失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                //安全隐患
//                case R.id.fragment_project_safe_imgcustom:
//                    if (!imgCustomChange) {
//                        listViewCustom.setVisibility(View.VISIBLE);
//                        imgCustomChange = !imgCustomChange;
//                        imgCustom.setImageResource(R.mipmap.shangla);
//                    } else {
//                        listViewCustom.setVisibility(View.GONE);
//                        imgCustomChange = !imgCustomChange;
//                        imgCustom.setImageResource(R.mipmap.xiala);
//                    }
//                    break;
                //自定义隐患
//                case R.id.fragment_project_safe_imgsafe:
//                    if (!imgSafeChange) {
//                        linearLayout.setVisibility(View.VISIBLE);
//                        imgSafeChange = !imgSafeChange;
//                        imgSafe.setImageResource(R.mipmap.shangla);
//                    } else {
//                        linearLayout.setVisibility(View.GONE);
//                        imgSafeChange = !imgSafeChange;
//                        imgSafe.setImageResource(R.mipmap.xiala);
//                    }
//                    break;
                //刷新按钮
                case R.id.fragment_project_safe_tv:
                    mDatas = new ArrayList<FileBean>();
                    getTreeData();
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        getTreeData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getTreeData();
        }
    }

    /**
     * @param listView 需要进行设置的listView
     * @desc 动态设置ListView条目的高度
     * @date 2017/12/15 9:33
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        int totalHeight = 0;
        int itemHeight = 0;
        switch (listView.getId()) {
            case R.id.fragment_project_safe_lv:
                projectSafeAdapter = (ProjectSafeAdapter) listView.getAdapter();
                for (int i = 0; i < projectSafeAdapter.getCount(); i++) {
                    View listItem = projectSafeAdapter.getView(i, null, listView);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                }
                ViewGroup.LayoutParams params = listView.getLayoutParams();
                params.height = totalHeight + (listView.getDividerHeight() * (projectSafeAdapter.getCount() - 1));
                listView.setLayoutParams(params);
                break;
            case R.id.fragment_project_safe_lv_treedata:
                mTreeDataAdapter = (SimpleTreeAdapter) listView.getAdapter();
                for (int i = 0; i < mTreeDataAdapter.getCount(); i++) {
                    View listItem = mTreeDataAdapter.getView(i, null, listView);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                }
                ViewGroup.LayoutParams params1 = listView.getLayoutParams();
                params1.height = totalHeight + (listView.getDividerHeight() * (mTreeDataAdapter.getCount() - 1));
                listView.setLayoutParams(params1);
                break;
        }

    }

    /**
     * @param response 数据源
     * @desc 将层级数据解析，总共5层
     * @date 2017/12/12 14:10
     */
    private void initDatas(String response) {
        mDatas.clear();
        try {
            JSONArray jsonArray = new JSONArray(response);
            //index index1 index2 index3全部是用来记录数据的个数的（好像可以用mDatas。size来代替，等以后试试）
            int index = jsonArray.length();
            //取出一级的object
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = (JSONObject) jsonArray.optJSONObject(i);
                //将一级object的name取出，放到数据源中 设置根节点为-1
                mDatas.add(new FileBean(i, -1, (i + 1) + ". " + o.opt("text").toString()));
                //将一级object的下一个子Array取出（如果有的话） 二级array
                JSONArray childrens = o.optJSONArray("children");
                if (childrens.length() != 0) {
                    index += childrens.length();
                    int index1 = index;
                    for (int j = 0; j < childrens.length(); j++) {
                        //二级object
                        JSONObject o1 = (JSONObject) childrens.optJSONObject(j);
                        mDatas.add(new FileBean(index - (childrens.length() - j), i, (j + 1) + ". " + o1.opt("text").toString()));
                        //三级array
                        JSONArray childrens1 = o1.optJSONArray("children");
                        if (childrens1.length() != 0) {
                            index1 += childrens1.length();
                            int index2 = index1;
                            for (int k = 0; k < childrens1.length(); k++) {
                                //三级object
                                JSONObject o2 = (JSONObject) childrens1.optJSONObject(k);
                                mDatas.add(new FileBean(index1 - (childrens1.length() - k), index - (childrens.length() - j), (k + 1) + ". " + o2.opt("text").toString()));
                                //四级Array
                                JSONArray childrens2 = o2.optJSONArray("children");
                                if (childrens2.length() != 0) {
                                    index2 += childrens2.length();
                                    int index3 = index2;
                                    for (int m = 0; m < childrens2.length(); m++) {
                                        //四级object
                                        JSONObject o3 = childrens2.optJSONObject(m);
                                        mDatas.add(new FileBean(index2 - (childrens2.length() - m), index1 - (childrens1.length() - k), (m + 1) + ". " + o3.opt("text").toString()));
                                        //五级Array
                                        JSONArray childrens3 = o3.optJSONArray("children");
                                        if (childrens3.length() != 0) {
                                            index += childrens3.length();
                                            for (int n = 0; n < childrens3.length(); n++) {
                                                //五级object 解析完成
                                                JSONObject o4 = childrens3.optJSONObject(n);
                                                mDatas.add(new FileBean(index3 - (childrens3.length() - n), index2 - (childrens2.length() - n), (n + 1) + ". " + o4.opt("text").toString()));
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

            }
        } catch (JSONException e) {
            Log.e(TAG, ": " + e.getCause());
            e.printStackTrace();
        }
    }
}
