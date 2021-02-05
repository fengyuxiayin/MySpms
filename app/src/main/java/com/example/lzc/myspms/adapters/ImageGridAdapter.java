package com.example.lzc.myspms.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.models.CheckInfoModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.utils.TakePhotoUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

/**
 * Created by LZC on 2017/10/24.
 */
public class ImageGridAdapter extends BaseAdapter implements View.OnClickListener {
    public static final String TAG = ImageGridAdapter.class.getSimpleName();
    private List<String> data;
    private LayoutInflater inflater;
    private Context context;
    private Dialog mCameraDialog;
    private Activity activity;
    private String isView;
    private int pos;
    private PopupWindow popupWindow;
    private CallBack callBack;
    private Uri uri;

    public ImageGridAdapter(List<String> data, Context context,String isView) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = (Activity) context;
        this.isView = isView;
    }
    public ImageGridAdapter(List<String> data, Context context,String isView,int pos) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = (Activity) context;
        this.isView = isView;
        this.pos = pos;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_project_detail_item, parent, false);
        ImageView imgShow = (ImageView) convertView.findViewById(R.id.activity_project_detail_item_img_show);
        ImageView imgAdd = (ImageView) convertView.findViewById(R.id.activity_project_detail_item_img_add);
        if (!isView.equals("isView")) {
            //如果不是仅查看，那么就给添加图片设置监听
            imgAdd.setOnClickListener(this);
        }
        if (data.get(position).length()>0) {
            Picasso.with(context).load(Constant.UPLOAD_IMG_IP+data.get(position)).into(imgShow);
            imgAdd.setVisibility(View.GONE);
            imgShow.setVisibility(View.VISIBLE);
            imgShow.setOnClickListener(this);
            imgShow.setTag(position);
        }else{
            imgAdd.setVisibility(View.VISIBLE);
            imgAdd.setTag(position);
            imgShow.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void notifyData(String jctpList) {
        this.data.add(data.size()-1,jctpList);
        notifyDataSetChanged();
    }
    public interface CallBack {
        void doSomeThing(List<String> data);
    }
    public void setListener( CallBack callBack) {
        this.callBack = callBack;
    }

        @Override
    public void onClick(final View v) {
        if (v!=null) {

            switch (v.getId()) {
                case R.id.activity_project_detail_item_img_add:
                    Constant.GRID_POS_ONE = this.pos;
                    Log.e(TAG, "onClick: "+Constant.GRID_POS_ONE );
                    setDialog();
                    break;
                case R.id.activity_project_detail_item_img_show:
                    final int pos = (int) v.getTag();
                    Constant.GRID_POS_TWO = pos;
                    Picasso.with(context).load(Constant.UPLOAD_IMG_IP+data.get(pos)).fetch(new Callback() {
                        @Override
                        public void onSuccess() {
                            popupWindow = new PopupWindow();
                            View inflate = View.inflate(context, R.layout.popup_upload_image_preview, null);
                            ImageView imgPreview = (ImageView) inflate.findViewById(R.id.popup_upload_image_preview);
                            ImageView imgDelete = (ImageView) inflate.findViewById(R.id.popup_upload_image_delete);
                            Picasso.with(context).load(Constant.UPLOAD_IMG_IP+data.get(pos)).error(R.mipmap.img_load_error).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into((ImageView) imgPreview);
                            popupWindow = new PopupWindow(inflate, (int) (activity.getWindowManager().getDefaultDisplay().getWidth() * 0.8), (int) (activity.getWindowManager().getDefaultDisplay().getHeight() * 0.8),true);
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            popupWindow.setOutsideTouchable(true);
                            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                            lp.alpha = 0.5f;
                            activity.getWindow().setAttributes(lp);
                            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 50);
                            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                                    lp.alpha = 1f;
                                    activity.getWindow().setAttributes(lp);
                                }
                            });
                            if (isView.equals("isView")) {
                                imgDelete.setVisibility(View.GONE);
                            }else{
                                //删除图片
                             imgDelete.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     data.remove(pos);
                                     notifyDataSetChanged();
                                     popupWindow.dismiss();
                                     if (callBack!=null) {
                                         callBack.doSomeThing(data);
                                     }
                                 }
                             });
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    });



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
                        showAlbum();
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
                        showCamera();
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
     * @desc 弹出选项菜单
     * @date 2017/12/11 16:56
     */
    private void setDialog() {
        mCameraDialog = new Dialog(context, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(context).inflate(
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
        lp.width = (int) context.getResources().getDisplayMetrics().widthPixels / 2; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }
    /**
     * @desc 跳转到相机界面
     * @date 2017/12/11 16:56
     */
    private void showCamera() {
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(context.getPackageManager()) != null) {
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            File mTmpFile = TakePhotoUtils.createFile(context.getApplicationContext());
            Constant.PHOTO_ABSOLUTE_PATH = mTmpFile.getAbsolutePath();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果是7.0android系统
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, mTmpFile.getAbsolutePath());
                uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
            }else{
                uri = Uri.fromFile(mTmpFile);
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(cameraIntent, Constant.REQUEST_CAMERA);
        } else {
            Toast.makeText(context.getApplicationContext(), "没有找到相机", Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * @desc 跳转到相册
     * @date 2017/12/11 16:56
     */
    private void showAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(albumIntent, Constant.REQUEST_ALBUM_OK);
    }
}
