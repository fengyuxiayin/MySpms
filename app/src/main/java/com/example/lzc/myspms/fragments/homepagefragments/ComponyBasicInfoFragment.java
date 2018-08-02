package com.example.lzc.myspms.fragments.homepagefragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.activitys.homepageactivitys.AddEnterpriseSimpleActivity;
import com.example.lzc.myspms.adapters.AddEnterpriseAdapter;
import com.example.lzc.myspms.adapters.MyAdapter;
import com.example.lzc.myspms.custom.ClearEditText;
import com.example.lzc.myspms.custom.PicassoImageLoder;
import com.example.lzc.myspms.custom.XHLoadingView;
import com.example.lzc.myspms.fragments.BaseFragment;
import com.example.lzc.myspms.fragments.NewCheckFragment;
import com.example.lzc.myspms.fragments.queryfragments.communityinfofragments.CommunityBasicInfoFragment;
import com.example.lzc.myspms.models.BaseEnterpriseModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnterpriseInfoModel;
import com.example.lzc.myspms.models.EnumCommunityModel;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.FindByIdWithStaffModel;
import com.example.lzc.myspms.models.Location;
import com.example.lzc.myspms.models.LoginInfoModel;
import com.example.lzc.myspms.utils.BitmapUtils;
import com.example.lzc.myspms.utils.GpsUtil;
import com.example.lzc.myspms.utils.NetUtil;
import com.example.lzc.myspms.utils.TakePhotoUtils;
import com.example.lzc.myspms.utils.ValidateUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LZC on 2017/11/27.
 */

public class ComponyBasicInfoFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = ComponyBasicInfoFragment.class.getSimpleName();
    private Banner banner;
    private EditText etComponyName;
    private EditText etComponySimpleName;
    private EditText etSjjydz;
    private EditText etWxdj;
    private EditText etZcdz;
    private EditText etJyzt;
    private EditText etShjydm;
    private RadioButton rbYes;
    private AutoCompleteTextView etHygs;
    private EditText etYye;
    private TextView tvYye;
    private EditText etTze;
    private TextView tvTze;
    private EditText etQydw;
    private EditText etQyfw;
    private EditText etSssq;
    private EditText etJyfw;
    private Button btnUpload;
    private Button btnSave;
    //存放两个按钮的
    private LinearLayout linearLayout;
    private RadioButton rbNo;
    //社区名称数据
    private List<EnumModel> dataCommunity = new ArrayList<>();
    //行业类别代码数据
    private List<EnumModel> dataIndustryCateary = new ArrayList<>();
    //企业风险等级数据
    private List<EnumModel> dataEnterpriseRisk = new ArrayList<>();
    //投资额单位数据
    private List<EnumModel> dataUnitType = new ArrayList<>();
    //经营状态数据
    private List<EnumModel> dataStatus = new ArrayList<>();
    //企业规模数据
    private List<EnumModel> dataEnterpriseType = new ArrayList<>();
    private String qyId;
    //which 判断是添加 修改 还是查看企业
    private String which;
    private Gson gson = new Gson();
    private FindByIdWithStaffModel.FindByIdWithStaffMsgModel componyInfo;
    private String jctp = "";
    private boolean isOpen;
    private Dialog mCameraDialog;
    private String picPath;
    private File mTmpFile;
    private Fragment mShowFragment = this;
    private List<String> industryList = new ArrayList<>();
    private List<String> strings;
    private RadioButton rbSmall;
    private List<String> stringsCopy =  new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_compony_info, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        which = getArguments().getString("which");
        qyId = getArguments().getString("qyId");
        componyInfo = (FindByIdWithStaffModel.FindByIdWithStaffMsgModel) getArguments().getSerializable("componyInfo");
        initView();
        initData();
        initListener();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mShowFragment = this;
            componyInfo = (FindByIdWithStaffModel.FindByIdWithStaffMsgModel) getArguments().getSerializable("componyInfo");

        }
    }

    private void initView() {
        banner = (Banner) view.findViewById(R.id.fragment_basic_info_simple_banner);
        etComponyName = (EditText) view.findViewById(R.id.fragment_basic_info_et_qymc);
        etComponySimpleName = (EditText) view.findViewById(R.id.fragment_basic_info_et_qyjc);
        etSjjydz = (EditText) view.findViewById(R.id.fragment_basic_info_et_sjjydz);
        etWxdj = (EditText) view.findViewById(R.id.fragment_basic_info_et_wxdj);
        etZcdz = (EditText) view.findViewById(R.id.fragment_basic_info_et_zcdz);
        etJyzt = (EditText) view.findViewById(R.id.fragment_basic_info_et_jyzt);
        etShjydm = (EditText) view.findViewById(R.id.fragment_basic_info_et_shjydm);
        etHygs = (AutoCompleteTextView) view.findViewById(R.id.fragment_basic_info_et_hygs);
        etYye = (EditText) view.findViewById(R.id.fragment_basic_info_et_yye);
        tvYye = (TextView) view.findViewById(R.id.fragment_basic_info_tv_yye);
        etTze = (EditText) view.findViewById(R.id.fragment_basic_info_et_tze);
        tvTze = (TextView) view.findViewById(R.id.fragment_basic_info_tv_tze);
        rbYes = (RadioButton) view.findViewById(R.id.fragment_basic_info_rb_yes);
        rbNo = (RadioButton) view.findViewById(R.id.fragment_basic_info_rb_no);
        rbSmall = (RadioButton) view.findViewById(R.id.fragment_basic_info_rb_small);
        etQydw = (EditText) view.findViewById(R.id.fragment_basic_info_et_qydw);
        etQyfw = (EditText) view.findViewById(R.id.fragment_basic_info_et_qyfw);
        etSssq = (EditText) view.findViewById(R.id.fragment_basic_info_et_sssq);
        etJyfw = (EditText) view.findViewById(R.id.fragment_basic_info_et_jyfw);
        linearLayout = (LinearLayout) view.findViewById(R.id.fragment_basic_info_textview_ll);
        btnUpload = (Button) view.findViewById(R.id.fragment_basic_info_btn_upload);
        btnSave = (Button) view.findViewById(R.id.fragment_basic_info_btn_save);
    }

    private void initListener() {
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(final int position) {
                Picasso.with(getActivity()).load(strings.get(position)).fetch(new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e(TAG, "onSuccess: " );
                        PopupWindow popupWindow = new PopupWindow();
                        View inflate = View.inflate(getActivity(), R.layout.popup_upload_image_preview, null);
                        ImageView imgPreview = (ImageView) inflate.findViewById(R.id.popup_upload_image_preview);
                        ImageView imgDelete = (ImageView) inflate.findViewById(R.id.popup_upload_image_delete);
                        Picasso.with(getActivity()).load(strings.get(position)).error(R.mipmap.img_load_error).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into((ImageView) imgPreview);
                        popupWindow = new PopupWindow(inflate, (int) (getActivity().getWindowManager().getDefaultDisplay().getWidth() * 0.8), (int) (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 0.8),true);
                        popupWindow.setBackgroundDrawable(new BitmapDrawable());
                        popupWindow.setOutsideTouchable(true);
                        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                        lp.alpha = 0.5f;
                        getActivity().getWindow().setAttributes(lp);
                        popupWindow.showAtLocation(banner, Gravity.CENTER, 0, 50);
                        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                                lp.alpha = 1f;
                                getActivity().getWindow().setAttributes(lp);
                            }
                        });
                        if (which.equals("view")) {
                            imgDelete.setVisibility(View.GONE);
                        }else{
                            imgDelete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    stringsCopy.remove(position);
                                    banner.stopAutoPlay();
                                    banner.setImages(stringsCopy);
                                    banner.setImageLoader(new PicassoImageLoder());
                                    banner.start();
                                    String jctp = "";
                                    for (int i = 0; i < stringsCopy.size(); i++) {
                                        jctp += stringsCopy.get(i).replace(Constant.UPLOAD_IMG_IP,"");
                                        if (stringsCopy.size()>1) {
                                            jctp+=",";
                                        }
                                    }
                                    Log.e(TAG, "onClick: "+jctp );
                                    componyInfo.setQytp(jctp);
                                    sendComponyInfoToServer();
                                }
                            });
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
            }
        });
        if (which.equals("view")) {
            //让所有控件不可响应
            etComponyName.setEnabled(false);
            etComponySimpleName.setEnabled(false);
            etSjjydz.setEnabled(false);
            etWxdj.setEnabled(false);
            etZcdz.setEnabled(false);
            etJyzt.setEnabled(false);
            etShjydm.setEnabled(false);
            rbYes.setClickable(false);
            rbNo.setClickable(false);
            rbSmall.setClickable(false);
            etHygs.setEnabled(false);
            etYye.setEnabled(false);
            etTze.setEnabled(false);
            etQydw.setEnabled(false);
            etQyfw.setClickable(false);
            etQyfw.setFocusable(false);
            etSssq.setEnabled(false);
            etJyfw.setEnabled(false);
            btnSave.setEnabled(false);
            btnUpload.setEnabled(false);
        } else {
            etWxdj.setFocusable(false);
            etJyzt.setFocusable(false);
//            etHygs.setFocusable(false);
            etQydw.setFocusable(false);
            etQyfw.setFocusable(false);
            etSssq.setFocusable(false);
            //设置点击事件
            etWxdj.setOnClickListener(this);
            etJyzt.setOnClickListener(this);
//            etHygs.setOnClickListener(this);
            etSssq.setOnClickListener(this);
            etQydw.setOnClickListener(this);
            etQyfw.setOnClickListener(this);
            btnUpload.setOnClickListener(this);
            btnSave.setOnClickListener(this);
            tvTze.setOnClickListener(this);
            tvYye.setOnClickListener(this);
        }
    }
/**
 *
 *@desc 提交修改后的企业信息到服务器
 *@date 2018/7/2 16:03
*/
    private void sendComponyInfoToServer() {
        Log.e(TAG, "sendComponyInfoToServer: "+gson.toJson(componyInfo) );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/save")
                .addParams("id", qyId.length() == 0 ? "" : qyId)
                .addParams("qymc", componyInfo.getQymc()==null?"":componyInfo.getQymc())
                .addParams("zch", componyInfo.getZch() ==null?"":componyInfo.getZch() + "")
                .addParams("fddbr", componyInfo.getFddbr() == null ? "" : componyInfo.getFddbr() + "")
                .addParams("zcdz", componyInfo.getZcdz() == null ? "" : componyInfo.getZcdz() + "")
                .addParams("lxdh", componyInfo.getLxdh() == null ? "" : componyInfo.getLxdh() + "")
                .addParams("sqId", (componyInfo.getSqId() +"").equals("null")?"":componyInfo.getSqId() + "")
                .addParams("qyzt", (componyInfo.getQyzt() +"").equals("null")?"":componyInfo.getQyzt() + "")
                .addParams("jjlxdm", (componyInfo.getJjlxdm() +"").equals("null")? "":componyInfo.getJjlxdm() + "")
                .addParams("hylbdm", componyInfo.getHylbdm() == null ? "" : componyInfo.getHylbdm() + "")
                .addParams("qylsgx", (componyInfo.getQylsgx() +"").equals("null")?"":componyInfo.getQylsgx() + "")
                .addParams("qywzjd", (componyInfo.getQywzjd() +"").equals("null")?"":componyInfo.getQywzjd() + "")
                .addParams("qywzwd", (componyInfo.getQywzwd() +"").equals("null")?"":componyInfo.getQywzwd() + "")
                .addParams("jyfw", componyInfo.getJyfw() == null ? "" : componyInfo.getJyfw() + "")
                .addParams("zyfzr", componyInfo.getZyfzr() == null ? "" : componyInfo.getZyfzr() + "")
                .addParams("zyfzryddhhm", componyInfo.getZyfzryddhhm() == null ? "" : componyInfo.getZyfzryddhhm() + "")
                .addParams("zyfzrgddhhm", componyInfo.getZyfzrgddhhm() == null ? "" : componyInfo.getZyfzrgddhhm() + "")
                .addParams("zyfzrdzyx", componyInfo.getZyfzrdzyx() == null ? "" : componyInfo.getZyfzrdzyx() + "")
                .addParams("dzyx", componyInfo.getDzyx() == null ? "" : componyInfo.getDzyx())
                .addParams("iszyfzrxx", (componyInfo.getIszyfzrxx() +"").equals("null")?"":componyInfo.getIszyfzrxx() + "")
                .addParams("aqfzr", componyInfo.getAqfzr() == null ? "" : componyInfo.getAqfzr() + "")
                .addParams("aqfzryddhhm", componyInfo.getAqfzryddhhm() == null ? "" : componyInfo.getAqfzryddhhm() + "")
                .addParams("aqfzrgddhhm", componyInfo.getAqfzrgddhhm() == null ? "" : componyInfo.getAqfzrgddhhm() + "")
                .addParams("aqfzrdzyx", componyInfo.getAqfzrdzyx() == null ? "" : componyInfo.getAqfzrdzyx() + "")
                .addParams("isaqfzrxx", (componyInfo.getIsaqfzrxx() +"").equals("null")?"":componyInfo.getIsaqfzrxx() + "")
                .addParams("cyrysl", (componyInfo.getCyrysl() +"").equals("null")?"":componyInfo.getCyrysl() + "")
                .addParams("tzzyrysl", (componyInfo.getTzzyrysl() +"").equals("null")?"":componyInfo.getTzzyrysl() + "")
                .addParams("zzaqscglrys", (componyInfo.getZzaqscglrys() +"").equals("null")?"":componyInfo.getZzaqscglrys() + "")
                .addParams("zzyjglrys", (componyInfo.getZzyjglrys() +"").equals("null")?"":componyInfo.getZzyjglrys() + "")
                .addParams("zcaqgcsrys", (componyInfo.getZcaqgcsrys() +"").equals("null")?"":componyInfo.getZcaqgcsrys() + "")
                .addParams("zsrysl", (componyInfo.getZsrysl() +"").equals("null")?"": componyInfo.getZsrysl() + "")
                .addParams("qyjc", (componyInfo.getQyjc() +"").equals("null")?"":componyInfo.getQyjc() + "")
                .addParams("isbzh", (componyInfo.getIsbzh() +"").equals("null")?"":componyInfo.getIsbzh() + "")
                .addParams("isqyzc", (componyInfo.getIsqyzc() +"").equals("null")?"":componyInfo.getIsqyzc() + "")
                .addParams("cyryJson", componyInfo.getCyryJson() == null ? "" : componyInfo.getCyryJson() + "")
                .addParams("tzzyryJson", componyInfo.getTzzyryJson() == null ? "" : componyInfo.getTzzyryJson() + "")
                .addParams("zzaqscryJson", componyInfo.getZzaqscryJson() == null ? "" : componyInfo.getZzaqscryJson() + "")
                .addParams("zzyjglryJson", componyInfo.getZzyjglryJson() == null ? "" : componyInfo.getZzyjglryJson() + "")
                .addParams("zcaqgcsJson", componyInfo.getZcaqgcsJson() == null ? "" : componyInfo.getZcaqgcsJson() + "")
//                .addParams("ssJson", componyInfo.getZsryJson() == null ? "" : componyInfo.getZsryJson() + "")
                .addParams("ssJson", componyInfo.getSsJson() == null ? "" : componyInfo.getSsJson() + "")
                .addParams("aqjgszqk", (componyInfo.getAqjgszqk() +"").equals("null")?"":componyInfo.getAqjgszqk() + "")
                .addParams("aqjgjcjg", componyInfo.getAqjgjcjg() == null ? "" : componyInfo.getAqjgjcjg() + "")
                .addParams("gmqk", (componyInfo.getGmqk() +"").equals("null")?"":componyInfo.getGmqk() + "")
                .addParams("jgfl", componyInfo.getJgfl()  == null?"" :componyInfo.getJgfl() + "")
                .addParams("jgfljb", (componyInfo.getJgfljb() +"").equals("null")?"":componyInfo.getJgfljb() + "")
                .addParams("bzhfj",( componyInfo.getBzhfj() +"").equals("null")?"": componyInfo.getBzhfj() + "")
                .addParams("bzhcjsj", (componyInfo.getBzhcjsj() +"").equals("null")?"":componyInfo.getBzhcjsj() + "")
                .addParams("bzhfssj", (componyInfo.getBzhfssj() +"").equals("null")?"":componyInfo.getBzhfssj() + "")
                .addParams("qyfxfj", (componyInfo.getQyfxfj() +"").equals("null")?"":componyInfo.getQyfxfj() + "")
                .addParams("scjydz", componyInfo.getScjydz() == null ? "" : componyInfo.getScjydz() + "")
                .addParams("iswhp", (componyInfo.getIswhp() +"").equals("null")?"":componyInfo.getIswhp() + "")
                .addParams("islgtx",( componyInfo.getIslgtx() +"").equals("null")?"":componyInfo.getIslgtx() + "")
                .addParams("lgtxcjsj", componyInfo.getLgtxcjsj() == null ? "" : componyInfo.getLgtxcjsj() + "")
                .addParams("lgtxfssj", componyInfo.getLgtxfssj() == null ? "" : componyInfo.getLgtxfssj() + "")
                .addParams("whpJson", componyInfo.getWhpJson() == null ? "" : componyInfo.getWhpJson() + "")
                .addParams("tze", (componyInfo.getTze() +"").equals("null")?"":componyInfo.getTze() + "")
                .addParams("tzedw", componyInfo.getTzedw() == null ? "" : componyInfo.getTzedw() + "")
                .addParams("yye", (componyInfo.getYye() +"").equals("null")?"":componyInfo.getYye() + "")
                .addParams("yyedw", componyInfo.getYyedw() == null ? "" : componyInfo.getYyedw() + "")
                .addParams("qyfwzb", componyInfo.getQyfwzb() == null ? "" : componyInfo.getQyfwzb() + "")
                .addParams("qytp", componyInfo.getQytp() == null ? "" : componyInfo.getQytp() + "")
                .addParams("fddh", componyInfo.getFddh() == null ? "" : componyInfo.getFddh() + "")
                .addParams("fdxm", componyInfo.getFdxm() == null ? "" : componyInfo.getFdxm() + "")
                .addParams("sfzd",componyInfo.getSfzd()==null?"":componyInfo.getSfzd()+"")
                .addParams("qyejfl",componyInfo.getQyejfl()==null?"":componyInfo.getQyejfl()+"")
                .addParams("hyzgbm",(componyInfo.getHyzgbm()+"").equals("null")?"":componyInfo.getHyzgbm()+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getActivity(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        LoginInfoModel info = gson.fromJson(response, LoginInfoModel.class);
                        Toast.makeText(getContext(), "" + info.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public String getEdittextContent(Editable s) {
        return s.toString().trim();
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
            Log.e(TAG, "doPhoto: ");
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
//                .url("https://120.25.251.167:1443" + "/fileUpload/doFileUpload")
                .addParams("folder", "/source/enterprise/")
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
                            if (jctp.length() > 0) {
                                jctp += ",";
                                jctp += infoModel.getMsg();
                            } else {
                                jctp += infoModel.getMsg();
                            }
                            componyInfo.setQytp(jctp);
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

    private void initData() {
        //经营状态数据
        dataStatus.add(new EnumModel("1", "正常生产"));
        dataStatus.add(new EnumModel("2", "停产置业"));
        dataStatus.add(new EnumModel("3", "闲置"));
        //所属社区数据 高家台社区
        initEnumData("/community/findAll", null, dataCommunity);
        //行业类别代码 农业等 这条数据最多 加载最慢，所以在这里setAdapter
        initEnumData("/enum/getEnums", "INDUSTRY_CATEGORY_TYPE", dataIndustryCateary);
        //企业风险等级
        initEnumData("/enum/getEnums", "ENTERPRISE_RISK_TYPE", dataEnterpriseRisk);
        //投资额单位
        initEnumData("/enum/getEnums", "CURRENCY_UNIT_TYPE", dataUnitType);
        //企业规模
        initEnumData("/enum/getEnums", "ENTERPRISE_SCALL_STATE", dataEnterpriseType);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            switch (v.getId()) {
                case R.id.fragment_basic_info_et_wxdj:
                    showChooseDialog(dataEnterpriseRisk, etWxdj, "企业风险等级");
                    break;
                case R.id.fragment_basic_info_et_jyzt:
                    showChooseDialog(dataStatus, etJyzt, "经营状态");
                    break;
                case R.id.fragment_basic_info_et_hygs:
                    showChooseDialog(dataIndustryCateary, etHygs, "行业归属");
                    break;
                case R.id.fragment_basic_info_et_sssq:
                    showChooseDialog(dataCommunity, etSssq, "所属社区");
                    break;
                case R.id.fragment_basic_info_et_qydw:
                    ////gps是打开状态的话，就去设置位置信息，反之提示用户去打开gps
                    isOpen = GpsUtil.openGPSSettings(getActivity());
                    if (isOpen) {//gps是打开状态的话
                        if (!(Constant.LOCATION_INFO.equals("null,null") || Constant.LOCATION_INFO.length() < 1)) {//还没定位成功的时候 提示用户 定位成功的话，设置位置信息
                            if (Constant.LOCATION_INFO.equals(etQydw.getText().toString().trim())) {
                                //啥也不用干
                            } else {
                                Toast.makeText(getContext(), "企业位置更新成功", Toast.LENGTH_SHORT).show();
                                etQydw.setText(Constant.LOCATION_INFO);
                            }
                        } else {
                            Toast.makeText(getContext(), "Gps正在定位，请稍等", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.fragment_basic_info_et_qyfw:
                    //判断定位点是否重复
                    isOpen = GpsUtil.openGPSSettings(getActivity());
                    if (isOpen) {
                        if (!(Constant.LOCATION_INFO.equals("null,null") || Constant.LOCATION_INFO.length() < 1)) {//如果位置信息不为空串或者不为null（就是已经获取到了位置信息）
                            String content = etQyfw.getText().toString().trim();
                            if (content.length() > 0) {
                                String substring;
                                if (content.contains(";")) {
                                    //点击2次及2次以上定位按钮
                                    substring = content.substring(content.lastIndexOf(";") + 1, content.length());
                                } else {//点击一次定位按钮
                                    substring = content;
                                }
                                if (substring.equals(Constant.LOCATION_INFO)) {//重复定位问题
                                    Toast.makeText(getContext(), "定位点重复，请重新定位", Toast.LENGTH_SHORT).show();
                                } else {
                                    etQyfw.setText(etQyfw.getText().toString().trim() + ";" + Constant.LOCATION_INFO);
                                }
                            } else {
                                etQyfw.setText(Constant.LOCATION_INFO);
                            }
                        } else {
                            Toast.makeText(getContext(), "Gps正在定位，请稍等", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.fragment_basic_info_btn_upload:
                    setDialog();
                    break;
                case R.id.btn_choose_img:
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            ) {
                        // 如果没有权限，则现在进行申请
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                        , Manifest.permission.CAMERA}
                                , Constant.READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        showAlbum();
                    }
                    mCameraDialog.dismiss();
                    break;
                case R.id.btn_open_camera:
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            ) {
                        // 如果没有权限，则现在进行申请
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                        , Manifest.permission.CAMERA}
                                , Constant.READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        showCamera();
                    }
                    mCameraDialog.dismiss();
                    break;
                case R.id.btn_cancel:
                    mCameraDialog.dismiss();
                case R.id.fragment_basic_info_btn_save:
                    //先判断填写数据是否合格 合格跳转 不合格提示报错信息
                    isDataQualified();
                    break;
                case R.id.fragment_basic_info_tv_yye:
                    showChooseDialog(dataUnitType, tvYye, "营业额");
                    break;
                case R.id.fragment_basic_info_tv_tze:
                    showChooseDialog(dataUnitType, tvTze, "投资额");
                    break;
            }
        }
    }

    /**
     * @desc 判断数据是否合格
     * @date 2018/6/20 16:17
     */
    private void isDataQualified() {
        if (getEdittextContent(etComponyName).length() < 1) {
            Log.e(TAG, "isDataQualified: " );
            Toast.makeText(getContext(), "备注为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            componyInfo.setQymc(getEdittextContent(etComponyName));
        }
        if (getEdittextContent(etComponySimpleName).length() < 1) {
            Log.e(TAG, "isDataQualified: " );
            Toast.makeText(getContext(), "备注为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            componyInfo.setQyjc(getEdittextContent(etComponySimpleName));
        }
        for (int i = 0; i < dataEnterpriseRisk.size(); i++) {
            if (dataEnterpriseRisk.get(i).getValue().equals(getEdittextContent(etWxdj))) {
                componyInfo.setQyfxfj(Integer.parseInt(dataEnterpriseRisk.get(i).getKey()));
                break;
            }
        }
        if (rbYes.isChecked()) {
            componyInfo.setGmqk(2);
        }else if(rbNo.isChecked()){
            componyInfo.setGmqk(3);
        }else{
            componyInfo.setGmqk(1);
        }
        if (getEdittextContent(etYye).length()>0) {
            if (ValidateUtil.isAllNumber(getEditTextContent(etYye))) {
                componyInfo.setYye(Float.parseFloat(getEdittextContent(etYye)));
            }else{
                Toast.makeText(getContext(), "营业额必须为数字", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (getEdittextContent(etTze).length()>0) {
            if (ValidateUtil.isAllNumber(getEditTextContent(etTze))) {
                componyInfo.setTze(Float.parseFloat(getEdittextContent(etTze)));
            }else{
                Toast.makeText(getContext(), "投资额必须为数字", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        for (int i = 0; i < dataUnitType.size(); i++) {
            if (dataUnitType.get(i).getValue().equals(tvYye.getText())) {
                componyInfo.setYyedw(dataUnitType.get(i).getKey());
            }
        }
        for (int i = 0; i < dataUnitType.size(); i++) {
            if (dataUnitType.get(i).getValue().equals(tvTze.getText())) {
                componyInfo.setTzedw(dataUnitType.get(i).getKey());
            }
        }
        if (etQydw.getText().toString().trim().length() > 0) {
            String longtitude = etQydw.getText().toString().trim().substring(0, etQydw.getText().toString().trim().indexOf(","));
            String latitude = etQydw.getText().toString().trim().substring(etQydw.getText().toString().trim().indexOf(",") + 1, etQydw.getText().toString().trim().length());
            componyInfo.setQywzjd(Double.parseDouble(longtitude));
            componyInfo.setQywzwd(Double.parseDouble(latitude));
        }
        componyInfo.setJyfw(etJyfw.getText().toString().trim());
        if (!etQyfw.getText().toString().trim().isEmpty()) {
            if (etQyfw.getText().toString().trim().contains(";")) {
                String[] split = etQyfw.getText().toString().trim().split(";");
                if (split.length > 3) {
                    //当坐标点大于四个才上传
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("[");
                    for (int i = 0; i < split.length; i++) {
                        stringBuilder.append("{\"");
                        stringBuilder.append("lng\":");
                        stringBuilder.append(split[i].substring(0, split[i].indexOf(",") - 1));
                        stringBuilder.append(",");
                        stringBuilder.append("\"");
                        stringBuilder.append("lat\":");
                        stringBuilder.append(split[i].substring(split[i].indexOf(",") + 1, split[i].length()));
                        stringBuilder.append("}");
                        if (i != split.length - 1) {
                            stringBuilder.append(",");
                        }
                    }
                    stringBuilder.append("]");
                    componyInfo.setQyfwzb(String.valueOf(stringBuilder));
                } else {
                    componyInfo.setQyfwzb("");
                }
            } else {
                componyInfo.setQyfwzb("");
            }
        }else{
            componyInfo.setQyfwzb("");
        }
        if (getEdittextContent(etZcdz).length() < 1) {
            Toast.makeText(getContext(), "注册地址为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            componyInfo.setZcdz(getEdittextContent(etZcdz));
        }
        if (getEdittextContent(etSjjydz).length() < 1) {
            Toast.makeText(getContext(), "实际经营地址为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            componyInfo.setScjydz(getEdittextContent(etSjjydz));
        }
        if (getEdittextContent(etJyzt).length() < 1) {
            Toast.makeText(getContext(), "经营状态为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < dataStatus.size(); i++) {
                if (dataStatus.get(i).getValue().equals(getEdittextContent(etJyzt))) {
                    componyInfo.setQyzt(Integer.parseInt(dataStatus.get(i).getKey()));
                    break;
                }
            }
        }
        if (getEdittextContent(etComponySimpleName).length() < 1) {
            Toast.makeText(getContext(), "企业简称为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            componyInfo.setQyjc(getEdittextContent(etComponySimpleName));
        }
        if (getEdittextContent(etHygs).length() < 1) {
            Toast.makeText(getContext(), "国民经济类型为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < dataIndustryCateary.size(); i++) {
                if (dataIndustryCateary.get(i).getValue().equals(getEdittextContent(etHygs))) {
                    componyInfo.setHylbdm(dataIndustryCateary.get(i).getKey());
                }
            }
        }
        if (getEdittextContent(etShjydm).length() < 1) {
            Toast.makeText(getContext(), "社会信用代码为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (getEdittextContent(etShjydm).length() < 6) {
                Toast.makeText(getContext(), "社会信用代码过短，请正确填写", Toast.LENGTH_SHORT).show();
                return;
            }else{
                componyInfo.setZch(getEdittextContent(etShjydm));
            }
        }
        if (getEdittextContent(etSssq).length() < 1) {
            Toast.makeText(getContext(), "所属社区为必填项", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < dataCommunity.size(); i++) {
                if (dataCommunity.get(i).getValue().equals(getEdittextContent(etSssq))) {
                    componyInfo.setSqId(Integer.parseInt(dataCommunity.get(i).getKey()));
                }
            }
        }
        //如果能走到这一步证明数据全都合格了
        switchPages(ComponyStaffInfoFragment.TAG, ComponyStaffInfoFragment.class);

    }

    private String getEdittextContent(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void switchPages(String tag, Class<? extends Fragment> cls) {
        /**
         * 将当前显示的碎片进行隐藏，之后将要显示的页面显示出来
         */
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏显示的页面
        transaction.hide(mShowFragment);
        // 根据TAG去FragmentManager中进行查找碎片
        mShowFragment = fm.findFragmentByTag(tag);
        // 如果找到了，直接进行显示
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            // 如果没找到，添加到容器中并且设置了一个标记
            try {
                // 使用反射进行了一个对象的实例化
                mShowFragment = cls.getConstructor().newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("which", which);
                bundle.putString("qyId", qyId);
                bundle.putSerializable("componyInfo", componyInfo);
                mShowFragment.setArguments(bundle);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            transaction.add(R.id.activity_add_enterprise_simple_fl, mShowFragment, tag);
        }
        transaction.commit();
    }

    private String getEditTextContent(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * @param data 待转化数据源
     * @desc data2Array 将数据源转化为array
     * @date 2017/11/27 14:56
     */
    private String[] data2Array(List<EnumModel> data) {
        String[] array = new String[data.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = data.get(i).getValue();
        }
        return array;
    }

    /**
     * @param epointName 显示内容的edittext
     * @param data       dialog显示的内容
     * @param methodName 判断是哪个Edittext
     * @desc 点击对应项弹出dialog供用户选择
     * @date 2018/2/21 11:14
     */
    private void showChooseDialog(final List<EnumModel> data, final EditText epointName, final String methodName) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());// 自定义对话框
        final String[] array = data2Array(data);
        int which = 0;
        for (int i = 0; i < array.length; i++) {
            if (epointName.getText().toString().trim().equals(array[i])) {
                which = i;
                break;
            }
        }
        builder.setSingleChoiceItems(array, which, new DialogInterface.OnClickListener() {// 0默认的选中
            @Override
            public void onClick(DialogInterface dialog, final int which) {// which是被选中的位置
                epointName.setText(array[which]);
                switch (methodName) {
                    case "行业归属":
                        componyInfo.setHylbdm(dataIndustryCateary.get(which).getKey());
                        break;
                    case "企业状态":
                        componyInfo.setQyzt(Integer.valueOf(dataStatus.get(which).getKey()));
                        break;
                    case "所属社区":
                        componyInfo.setSqId(Integer.parseInt(dataCommunity.get(which).getKey()));
                        //将最近添加的社区放到首位
                        dataCommunity.add(0, new EnumModel(dataCommunity.get(which).getKey(), dataCommunity.get(which).getValue()));
                        dataCommunity.remove(which + 1);
                        break;
                    case "企业风险等级":
                        componyInfo.setQyfxfj(Integer.parseInt(dataEnterpriseRisk.get(which).getKey()));
                        break;

                }
                dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }
    /**
     * @param epointName 显示内容的edittext
     * @param data       dialog显示的内容
     * @param methodName 判断是哪个Edittext
     * @desc 点击对应项弹出dialog供用户选择
     * @date 2018/2/21 11:14
     */
    private void showChooseDialog(final List<EnumModel> data, final TextView epointName, final String methodName) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());// 自定义对话框
        final String[] array = data2Array(data);
        int which = 0;
        for (int i = 0; i < array.length; i++) {
            if (epointName.getText().toString().trim().equals(array[i])) {
                which = i;
                break;
            }
        }
        builder.setSingleChoiceItems(array, which, new DialogInterface.OnClickListener() {// 0默认的选中
            @Override
            public void onClick(DialogInterface dialog, final int which) {// which是被选中的位置
                epointName.setText(array[which]);
                switch (methodName) {
                    case "营业额":
                        componyInfo.setYyedw(dataUnitType.get(which).getKey());
                        break;
                    case "投资额":
                        componyInfo.setTzedw(dataUnitType.get(which).getKey());
                        break;
                }
                dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder.show();// 让弹出框显示
    }
    /**
     * @desc 跳转到相机界面
     * @date 2017/12/11 16:56
     */
    private void showCamera() {
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            File mTmpFile = TakePhotoUtils.createFile(getContext());
            Constant.PHOTO_ABSOLUTE_PATH = mTmpFile.getAbsolutePath();
            Log.e(TAG, "showCamera: " + Constant.PHOTO_ABSOLUTE_PATH);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            this.startActivityForResult(cameraIntent, Constant.REQUEST_CAMERA);
        } else {
            Toast.makeText(getContext(), "没有找到相机", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @desc 跳转到相册
     * @date 2017/12/11 16:56
     */
    private void showAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        this.startActivityForResult(albumIntent, Constant.REQUEST_ALBUM_OK);
    }

    /**
     * @desc 弹出选项菜单
     * @date 2017/12/11 16:56
     */
    private void setDialog() {
        mCameraDialog = new Dialog(getContext(), R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getContext()).inflate(
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

    private void initEnumData(String url, final String params, final List<EnumModel> data) {
        if (params == null) {
            //只有获取所属社区数据时不需要参数 所以用get方法获取
            OkHttpUtils.get()
                    .url(Constant.SERVER_URL + url)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    Log.e(TAG, "onError: findall" + e.getMessage() + e.getCause());
                }

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "onResponse: " + response);
                    if (response != null) {
                        //解析json
                        EnumCommunityModel allCommunityModel = gson.fromJson(response, EnumCommunityModel.class);
                        try {
                            JSONArray jsonArray = new JSONArray(allCommunityModel.getMsg());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject o = (JSONObject) jsonArray.get(i);
                                String value = o.getString("sqmc");
                                String key = o.getString("id");
                                data.add(new EnumModel(key, value));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e(TAG, "onResponse: " + data.size());
                    }

                }
            });
        } else {
            //获取其他类型的Enum数据时都需要参数
            OkHttpUtils.post()
                    .url(Constant.SERVER_URL + url)
                    .addParams("code", params)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    NetUtil.errorTip(getContext(), e.getMessage());
                }

                @Override
                public void onBefore(Request request) {

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
//                                if (params.equals("REGULATORY_LEVEL") || params.equals("STANDARDIZATION_LEVEL") || params.equals("ENTERPRISE_RISK_TYPE") || params.equals("ENTERPRISE_SCALE_TYPE")) {
//                                    value = o.getString("value") + ": " + o.optString("memo");
//                                } else {
                                value = o.getString("value");
//                                }
                                String key = o.getString("key");
                                industryList.add(value);
                                data.add(new EnumModel(key, value));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if ("INDUSTRY_CATEGORY_TYPE".equals(params)) {
                            Log.e(TAG, "onResponse: "+dataIndustryCateary.size() );
                            etHygs.setAdapter(new MyAdapter<>(getActivity(), android.R.layout.simple_list_item_1, industryList));
                            if (which.equals("edit")) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        linearLayout.setVisibility(View.VISIBLE);
                                        setDataFromServer();
                                    }
                                }, 500);
                            } else if (which.equals("add")) {
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        linearLayout.setVisibility(View.VISIBLE);
                                    }
                                }, 500);
                            } else {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        setDataFromServer();
                                        linearLayout.setVisibility(View.GONE);
                                    }
                                }, 500);
                            }


                        }
                    }
                }
            });
        }

    }

    private void setDataFromServer() {
        //获得经纬度信息，放到list中
        List<Location> locationList = new ArrayList<Location>();
        try {
            if (componyInfo.getQyfwzb() != null) {
                Log.e(TAG, "onResponse: 企业范围坐标" + componyInfo.getQyfwzb());
                JSONArray jsonArray = new JSONArray(componyInfo.getQyfwzb());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Location location = new Location();
                    location.setLat(jsonObject.optString("lat"));
                    location.setLng(jsonObject.optString("lng"));
                    locationList.add(location);
                }
                Log.e(TAG, "onResponse: " + locationList);
            }

        } catch (JSONException e) {
            Log.e(TAG, "onResponse: " + e.getMessage());
            e.printStackTrace();
        }
        //将获取到的信息设置到对应控件上
        setData(componyInfo, locationList);
        linearLayout.setVisibility(View.VISIBLE);
    }

    /**
     * @desc 将数据加载并设置到控件上
     * @date 2018/5/12 9:02
     */
    private void initDataFromServer() {
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/baseEnterprise/findByIdWithStaff")
                .addParams("id", qyId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        NetUtil.errorTip(getContext(), e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Log.e(TAG, "onResponse: /baseEnterprise/findByIdWithStaff" + response);
                            FindByIdWithStaffModel enterpriseInfoModel = gson.fromJson(response, FindByIdWithStaffModel.class);
                            if (enterpriseInfoModel.isData()) {

                            } else {
                                Toast.makeText(getContext(), enterpriseInfoModel.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void setData(FindByIdWithStaffModel.FindByIdWithStaffMsgModel componyInfo, List<Location> locationList) {
        etComponyName.setText(componyInfo.getQymc());
        etComponySimpleName.setText(componyInfo.getQyjc());
        etSjjydz.setText(componyInfo.getScjydz());
        for (int i = 0; i < dataEnterpriseRisk.size(); i++) {
            if (dataEnterpriseRisk.get(i).getKey().equals(componyInfo.getQyfxfj() + "")) {
                etWxdj.setText(dataEnterpriseRisk.get(i).getValue());
                break;
            }
        }
        etZcdz.setText(componyInfo.getZcdz());
        for (int i = 0; i < dataStatus.size(); i++) {
            if (dataStatus.get(i).getKey().equals(componyInfo.getQyzt() + "")) {
                etJyzt.setText(dataStatus.get(i).getValue());
                break;
            }
        }
        etShjydm.setText(componyInfo.getZch());
        if (componyInfo.getGmqk() == 2) {//2规模以上 3 规模以下 1微型企业
            rbYes.setChecked(true);
        } else if(componyInfo.getGmqk() == 3){
            rbNo.setChecked(true);
        }else if(componyInfo.getGmqk() == 1){
            rbSmall.setChecked(true);
        }

        for (int i = 0; i < dataIndustryCateary.size(); i++) {
            if (dataIndustryCateary.get(i).getKey().equals(componyInfo.getHylbdm() + "")) {
                etHygs.setText(dataIndustryCateary.get(i).getValue() + "");
                break;
            }
        }
        etYye.setText(componyInfo.getYye() + "");
        for (int i = 0; i < dataUnitType.size(); i++) {
            if (dataUnitType.get(i).getKey().equals(componyInfo.getYyedw() + "")) {
                tvYye.setText(dataUnitType.get(i).getValue() + "");
                break;
            }
        }
        etTze.setText(componyInfo.getTze() + "");
        for (int i = 0; i < dataUnitType.size(); i++) {
            if (dataUnitType.get(i).getKey().equals(componyInfo.getTzedw() + "")) {
                tvTze.setText(dataUnitType.get(i).getValue() + "");
                break;
            }
        }
        // TODO: 2018/6/17 这里有可能有bug
        if (componyInfo.getQywzjd() > 0) {
            etQydw.setText(componyInfo.getQywzjd() + "," + componyInfo.getQywzwd());
        }
        //把从服务器获取到的企业范围坐标格式转化一下（服务器的{[lng:46.123456,lat:45.123456]} 我们显示的 45.123456,46.123456）
        String location = "";
        for (int i = 0; i < locationList.size(); i++) {
            if (i != locationList.size() - 1) {
                location += locationList.get(i).getLng() + "," + locationList.get(i).getLat() + ";";
            } else {
                location += locationList.get(i).getLng() + "," + locationList.get(i).getLat();
            }
        }
        etQyfw.setText(location);
        for (int i = 0; i < dataCommunity.size(); i++) {
            Log.e(TAG, "setData: " + dataCommunity.get(i).getKey() + "  " + componyInfo.getSqId());
            if (dataCommunity.get(i).getKey().equals(componyInfo.getSqId() + "")) {
                etSssq.setText(dataCommunity.get(i).getValue() + "");
                break;
            }
        }
        etJyfw.setText(componyInfo.getJyfw());
        jctp = componyInfo.getQytp();
        List<String> list = new ArrayList<>();
        if (jctp != null) {
            if (jctp.length() > 0) {
                if (jctp.contains(",")) {
                    //如果有两个以上图片 循环拼接地址
                    List<String> list1 = Arrays.asList(jctp.split(","));
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(i, Constant.UPLOAD_IMG_IP + list1.get(i));
                    }
                    list.add("");
                } else {
                    //如果只有一张图片
                    list.add(Constant.UPLOAD_IMG_IP + jctp);
                    list.add("");
                }

            } else {
                list.add("");
            }
        } else {
            list.add("");
            jctp = "";
        }
        for (int i = 0; i < list.size(); i++) {
            Log.e(TAG, "setData: "+list.get(i) );
        }
        strings = list.subList(0, list.size() - 1);//是为了去掉最后的“”
        stringsCopy.addAll(strings);
        banner.setImages(strings);
        banner.setImageLoader(new PicassoImageLoder());
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.stopAutoPlay();
    }
}
