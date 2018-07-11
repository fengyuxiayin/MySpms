package com.example.lzc.myspms.adapters;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.AddProjectActivity;
import com.example.lzc.myspms.custom.DragView;
import com.example.lzc.myspms.fragments.queryfragments.enterpriseinfofragments.ProjectSafeFragment;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.ProjectSafeInfo;
import com.example.lzc.myspms.utils.TakePhotoUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2017/11/6.
 */
public class ProjectSafeAdapter extends BaseAdapter implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = "ProjectSafeAdapter";
    private Context context;
    private LayoutInflater inflater;
    private List<ProjectSafeInfo> data;
    private AddProjectActivity activity;
    //接口对象
    private IProjectSafeInfo listener;
    //fragment对象
    private ProjectSafeFragment projectSafeFragment;
    private LinearLayout linearLayout;

    public void changeData(List<ProjectSafeInfo> list) {
        this.data = new ArrayList<>();
        this.data.addAll(list);
        setListViewHeightBasedOnChildren(listView);
        notifyDataSetChanged();
        listener.getSafeData(list);
    }

    @Override
    public boolean onLongClick(final View v) {
        int pos = (int) v.getTag();
        if (v != null) {
            switch (v.getId()) {
                case R.id.fragment_project_safe_custom_item_iv_first:
                    Log.e(TAG, "onLongClick: ");
                    showPopwindow(v,pos);
                    break;
                case R.id.fragment_project_safe_custom_item_iv_second:
                    showPopwindow(v,pos);
                    break;
                case R.id.fragment_project_safe_custom_item_iv_third:
                    showPopwindow(v,pos);
                    break;
            }
        }
        return false;
    }

    private void showPopwindow(final View v, final int pos) {
        View inflate = inflater.inflate(R.layout.popup_delete_project_img, null);
        final TextView textView = (TextView) inflate.findViewById(R.id.popup_delete_project_img_tv);
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setWidth((int) (activity.getWindowManager().getDefaultDisplay().getWidth() * 0.2));
        popupWindow.setHeight(200);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setContentView(inflate);
        v.post(new Runnable() {
            @Override
            public void run() {
                int width = v.getWidth();// 获取宽度
                int height = v.getHeight();// 获取高度
                Log.e(TAG, "run: " + width + height);
                popupWindow.showAsDropDown(v, width / 2, -height / 2);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(TAG, "onClick: 11111" );
                        v.setVisibility(View.GONE);
                        data.set(pos,new ProjectSafeInfo(data.get(pos).getId(),data.get(pos).getName(),new ArrayList<String>()));
                        notifyDataSetChanged();

//                        setListViewHeightBasedOnChildren(listView);
                    }
                });
            }
        });
    }

    //接口 ProjectSafeFragment实现
    public interface IProjectSafeInfo {
        void getSafeData(List<ProjectSafeInfo> data);
    }

    private ListView listView;

    /**
     * @param context            上下文对象
     * @param data               safe页的数据 包括edittext中的内容和图片的地址的一个list
     * @param addProjectActivity activity对象
     * @param listViewCustom
     * @desc 添加项目Activity的构造方法
     * @date 2017/12/11 16:33
     */
    public ProjectSafeAdapter(ProjectSafeFragment projectSafeFragment, Context context, List<ProjectSafeInfo> data, AddProjectActivity addProjectActivity, ListView listViewCustom, IProjectSafeInfo listener) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
        this.activity = addProjectActivity;
        this.listener = listener;
        this.projectSafeFragment = projectSafeFragment;
        this.inflater = LayoutInflater.from(context);
        this.listView = listViewCustom;
    }

    private EditText etPointName;
    private ImageView imgImage;
    private ImageView imgAddPoint;
    private Button imgDeletePoint;
    private ImageView imgFirst;
    private ImageView imgSecond;
    private ImageView imgThird;
    //底部的弹出菜单
    private Dialog mCameraDialog;

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.fragment_project_safe_custom_item, parent, false);
        DragView dragView = (DragView) convertView.findViewById(R.id.fragment_project_safe_custom_item_dv);
        dragView.setOnDragStateListener(new DragView.DragStateListener() {
            @Override
            public void onOpened(DragView dragView) {

            }

            @Override
            public void onClosed(DragView dragView) {

            }

            @Override
            public void onForegroundViewClick(DragView dragView, View v) {

            }

            @Override
            public void onBackgroundViewClick(DragView dragView, View v) {

            }
        });
        etPointName = (EditText) convertView.findViewById(R.id.fragment_project_safe_custom_item_et);
        imgImage = (ImageView) convertView.findViewById(R.id.fragment_project_safe_custom_item_iv_image);
        imgAddPoint = (ImageView) convertView.findViewById(R.id.fragment_project_safe_custom_item_iv_add);
        imgDeletePoint = (Button) convertView.findViewById(R.id.fragment_project_safe_custom_item_btn_del);
        linearLayout = (LinearLayout) convertView.findViewById(R.id.fragment_project_safe_custom_item_ll);
        imgFirst = (ImageView) convertView.findViewById(R.id.fragment_project_safe_custom_item_iv_first);
        imgSecond = (ImageView) convertView.findViewById(R.id.fragment_project_safe_custom_item_iv_second);
        imgThird = (ImageView) convertView.findViewById(R.id.fragment_project_safe_custom_item_iv_third);
        imgFirst.setTag(position);
        imgFirst.setOnLongClickListener(this);
        imgSecond.setTag(position);
        imgSecond.setOnLongClickListener(this);
        imgThird.setTag(position);
        imgThird.setOnLongClickListener(this);
        imgImage.setOnClickListener(this);
        imgImage.setTag(position);
        imgAddPoint.setOnClickListener(this);
        imgAddPoint.setTag(position);
        imgDeletePoint.setOnClickListener(this);
        imgDeletePoint.setTag(position);
        etPointName.setText(data.get(position).getName());
        etPointName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                etPointName.setTag(position);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                data.set(position, new ProjectSafeInfo(data.get(position).getId(), s.toString(), new ArrayList<String>()));
                Constant.POINT_POSITION = position;
                listener.getSafeData(data);
            }
        });
        //如果当前检查点有图片就加载检查点图片
        List<String> jctpList = data.get(position).getData();
        if (jctpList.size() > 0) {//检查点有图片
            Log.e(TAG, "getView: " + jctpList.get(0));
            linearLayout.setVisibility(View.VISIBLE);
            for (int i = 0; i < jctpList.size(); i++) {
                switch (i) {
                    case 0://只有一张图片
                        imgFirst.setVisibility(View.VISIBLE);
                        Log.e(TAG, "getView: " + jctpList.get(i));
                        Picasso.with(context).load(jctpList.get(i)).into(imgFirst);
                        break;
                    case 1://两张图片
                        imgSecond.setVisibility(View.VISIBLE);
                        Picasso.with(context).load(jctpList.get(i)).into(imgSecond);
                        break;
                    case 2://三张及以上
                        imgThird.setVisibility(View.VISIBLE);
                        Picasso.with(context).load(jctpList.get(i)).into(imgThird);
                        break;
                }
            }

        } else {//检查点没有图片
            linearLayout.setVisibility(View.GONE);
            imgFirst.setVisibility(View.GONE);
            imgSecond.setVisibility(View.GONE);
            imgThird.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public void onClick(final View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.fragment_project_safe_custom_item_iv_add:
                    Log.e(TAG, "onClick: " + (int) v.getTag());
                    new AlertDialog.Builder(activity)
                            .setTitle("提示")
                            .setMessage("是否添加新的检查点？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    data.add(data.size(), new ProjectSafeInfo("", "", new ArrayList<String>()));
                                    setListViewHeightBasedOnChildren(ProjectSafeAdapter.this.listView);
                                    notifyDataSetChanged();
                                }
                            })
                            .create()
                            .show();
                    break;
                case R.id.fragment_project_safe_custom_item_btn_del:
                    if (data.size() > 1) {
                        new AlertDialog.Builder(activity)
                                .setTitle("提示")
                                .setMessage("是否删除该检查点？")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        data.remove((int) v.getTag());
                                        listener.getSafeData(data);
                                        setListViewHeightBasedOnChildren(ProjectSafeAdapter.this.listView);
                                        notifyDataSetChanged();
                                    }
                                })
                                .create()
                                .show();

                    } else {
                        Toast.makeText(context, "至少需要一个检查点", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.fragment_project_safe_custom_item_iv_image:
                    Log.e(TAG, "onClick: " + etPointName.getText().toString());
                    v.requestFocus();
                    int pos = (int) v.getTag();
                    View view = getView(pos, null, null);
                    EditText editText = (EditText) view.findViewById(R.id.fragment_project_safe_custom_item_et);
                    if (editText.getText().toString().equals("")) {
                        Toast.makeText(context, "请先填写检查点名称", Toast.LENGTH_SHORT).show();
                    } else {
                        setDialog((int) v.getTag());
                    }
                    break;
                case R.id.btn_choose_img:
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            ) {
                        // 如果没有权限，则现在进行申请
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                        , Manifest.permission.CAMERA}
                                , Constant.READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        showAlbum((Integer) v.getTag());
                    }
                    mCameraDialog.dismiss();
                    break;
                case R.id.btn_open_camera:
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            ) {
                        // 如果没有权限，则现在进行申请
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                        , Manifest.permission.CAMERA}
                                , Constant.READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        showCamera((Integer) v.getTag());
                    }
                    mCameraDialog.dismiss();
                    break;
                case R.id.btn_cancel:
                    mCameraDialog.dismiss();
                    break;
            }
        }
    }

    /**
     * @param position 点击的是哪一项
     * @desc 弹出选项菜单
     * @date 2017/12/11 16:56
     */
    private void setDialog(int position) {
        mCameraDialog = new Dialog(context, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.bottom_dialog, null);
        //初始化视图
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_choose_img).setTag(position);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_open_camera).setTag(position);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) context.getResources().getDisplayMetrics().widthPixels / 2; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    /**
     * @param listView 需要进行设置的listView
     * @desc 动态设置ListView条目的高度
     * @date 2017/12/15 9:33
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        int totalHeight = 0;
        for (int i = 0; i < getCount(); i++) {
            View listItem = getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (getCount() - 1));
        Log.e(TAG, "setListViewHeightBasedOnChildren: " + params.height);
        listView.setLayoutParams(params);
    }

    /**
     * @param position 点击了哪一项
     * @desc 跳转到相机界面
     * @date 2017/12/11 16:56
     */
    private void showCamera(int position) {
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(context.getPackageManager()) != null) {
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            File mTmpFile = TakePhotoUtils.createFile(context.getApplicationContext());
            Constant.PHOTO_ABSOLUTE_PATH = mTmpFile.getAbsolutePath();
            Log.e(TAG, "showCamera: " + Constant.PHOTO_ABSOLUTE_PATH);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            Constant.POINT_POSITION = position;
            projectSafeFragment.startActivityForResult(cameraIntent, Constant.REQUEST_CAMERA);
        } else {
            Toast.makeText(context.getApplicationContext(), "没有找到相机", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @param position 点击了哪一项
     * @desc 跳转到相册
     * @date 2017/12/11 16:56
     */
    private void showAlbum(int position) {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        Constant.POINT_POSITION = position;
        projectSafeFragment.startActivityForResult(albumIntent, Constant.REQUEST_ALBUM_OK);
    }

}
