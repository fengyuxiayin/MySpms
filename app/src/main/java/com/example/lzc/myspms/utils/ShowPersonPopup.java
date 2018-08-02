package com.example.lzc.myspms.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzc.myspms.R;

import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.InstrumentActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.NoticeActivity;
import com.example.lzc.myspms.activitys.homepageactivitys.menuactivitys.SystemSettingsActivity;
import com.example.lzc.myspms.activitys.queryactivitys.CommunityInfoQueryActivity;
import com.example.lzc.myspms.activitys.queryactivitys.enterpriseinfoactivitys.EnterpriseCheckRecordActivity;
import com.example.lzc.myspms.adapters.CommunityInfoQueryAdapter;
import com.example.lzc.myspms.adapters.GroupMemberAdapter;
import com.example.lzc.myspms.adapters.ListAdapter;
import com.example.lzc.myspms.models.CommunityInfoQueryModel;
import com.example.lzc.myspms.models.Constant;
import com.example.lzc.myspms.models.EnumModel;
import com.example.lzc.myspms.models.GroupModel;
import com.example.lzc.myspms.models.MemberModel;
import com.example.lzc.myspms.models.MyInfoModel;
import com.example.lzc.myspms.models.TeamFindByIdModel;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZC on 2018/6/14.
 */

public class ShowPersonPopup {
    private PopupWindow popupWindow;
    public static final String TAG = ShowPersonPopup.class.getSimpleName();
    private Activity activity;
    private ArrayList<MyInfoModel> data;
    private ListView listView;
    private String loginType;
    private String ssId;
    private Gson gson = new Gson();
    private GroupModel.GroupMsgModel groupMsgModel;
    private ListAdapter listAdapter;
    private String loginId;
    private View view;
    private GridView gvMember;
    private TextView tvGroupName;
    private GridView gvCommunity;
    private TextView tvPosition;
    //人员职称枚举
    private List<EnumModel> dataPosition = new ArrayList<>();


    public ShowPersonPopup(Activity activity, String loginType, String ssId, String loginId, View view) {
        this.activity = activity;
        this.loginType = loginType;
        this.ssId = ssId;
        this.loginId = loginId;
        this.view = view;
    }

    public void showPopup() {
        Log.e(TAG, "showPopup: " );
        View contentView = View.inflate(activity, R.layout.popup_main_personal, null);
        tvGroupName = (TextView) contentView.findViewById(R.id.popup_main_personal_tv_group_name);
        gvMember = (GridView) contentView.findViewById(R.id.popup_main_personal_gv_member);
        gvCommunity = (GridView) contentView.findViewById(R.id.popup_main_personal_gv_community);
        listView = (ListView) contentView.findViewById(R.id.popup_main_personal_lv);
        tvPosition = (TextView) contentView.findViewById(R.id.popup_main_personal_tv_position);
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + Constant.ENUM_URL)
                .addParams("code", "JOB_TITLE_TYPE")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                NetUtil.errorTip(activity, e.getMessage());
            }


            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //解析json数据 这里用不了gson 因为gson要求object开头
                            JSONObject o = (JSONObject) jsonArray.get(i);
                            String value = o.getString("value");
                            String key = o.getString("key");
                            dataPosition.add(new EnumModel(key, value));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        switch (Constant.ACCOUNT_TYPE) {//社区 小组 企业 街道 成员单位 1 2 3 4 5
            case "1":

                break;
            case "2":
                initDataFromServer();
                break;
            case "4":

                OkHttpUtils.post()
                        .url(Constant.SERVER_URL+"/community/find")
                        .addParams("size","100")
                        .build()
                        .execute(new StringCallback() {

                            private CommunityInfoQueryModel.CommunityInfoQueryMsgModel communityInfoQueryMsgModel;
                            private CommunityInfoQueryModel communityInfoQueryModel;
                            private CommunityInfoQueryAdapter communityInfoQueryAdapter;

                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "onError: "+e.getMessage() );
                                NetUtil.errorTip(activity,e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "onResponse: "+response );
                                gson = new Gson();
                                communityInfoQueryModel = gson.fromJson(response, CommunityInfoQueryModel.class);
                                if (communityInfoQueryModel.isData()) {
                                    communityInfoQueryMsgModel = gson.fromJson(communityInfoQueryModel.getMsg(), CommunityInfoQueryModel.CommunityInfoQueryMsgModel.class);
                                    tvGroupName.setText("街道");
                                    if (communityInfoQueryMsgModel!=null) {
                                        List<CommunityInfoQueryModel.CommunityInfoQueryMsgModel.ListBean> list = communityInfoQueryMsgModel.getList();
                                        List<String> gridCommunityList = new ArrayList<>();
                                        if (list!=null) {
                                            for (int i = 0; i < list.size(); i++) {
                                                gridCommunityList.add(list.get(i).getSqmc());
                                            }
                                        }
                                        gvCommunity.setAdapter(new ArrayAdapter<>(activity, R.layout.popup_person_grid_text_only, gridCommunityList));

                                    }else{
                                        Log.e(TAG, "onResponse: 社区信息为空" );
                                    }
                                }
                            }
                        });
                break;

        }
        setListViewData();
        popupWindow = new PopupWindow();
        popupWindow.setWidth((int) (activity.getWindowManager().getDefaultDisplay().getWidth() * (0.279)));
        popupWindow.setHeight((int) (activity.getWindowManager().getDefaultDisplay().getHeight()*0.85));
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(view, 0, 0, Gravity.LEFT);
    }
/**
 *
 *@desc 获取小组的数据
 *@param
 *@date 2018/6/18 20:54
*/
    private void initDataFromServer() {
        Log.e(TAG, "initDataFromServer: "+Constant.ENTERPRISE_ID );
        OkHttpUtils.post()
                .url(Constant.SERVER_URL + "/team/findById")
                .addParams("id", Constant.ENTERPRISE_ID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        NetUtil.errorTip(activity, e.getMessage() + "/team/findById");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response + "");
                        TeamFindByIdModel teamFindByIdModel = gson.fromJson(response, TeamFindByIdModel.class);
                        if (teamFindByIdModel.isData()) {
                            TeamFindByIdModel.TeamFindByIdMsgModel teamFindByIdMsgModel = gson.fromJson(teamFindByIdModel.getMsg(), TeamFindByIdModel.TeamFindByIdMsgModel.class);
                            if (teamFindByIdMsgModel!=null) {
                                tvGroupName.setText(teamFindByIdMsgModel.getXzmc());
                                List<TeamFindByIdModel.TeamFindByIdMsgModel.BclBean> bcl = teamFindByIdMsgModel.getBcl();
                                List<TeamFindByIdModel.TeamFindByIdMsgModel.BslBean> bsl = teamFindByIdMsgModel.getBsl();
                                if (bsl != null) {
                                    List<MemberModel> gridMemberList = new ArrayList<>();
                                    for (int i = 0; i < bsl.size(); i++) {
                                        //设置小组人员GridView的数据
                                        if (dataPosition!=null) {
                                            for (int j = 0; j < dataPosition.size(); j++) {
                                                if ((bsl.get(i).getRyzc()+"").equals(dataPosition.get(j).getKey())) {
                                                    gridMemberList.add(new MemberModel(bsl.get(i).getRymc(), dataPosition.get(j).getValue()));

                                                }
                                            }
                                        }else{
                                            Toast.makeText(activity, "人员职称正在获取", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    GroupMemberAdapter groupMemberAdapter = new GroupMemberAdapter(gridMemberList, activity);
                                    gvMember.setAdapter(groupMemberAdapter);
                                }
                                List<String> gridCommunityList = new ArrayList<>();
                                if (bcl != null) {
                                    for (int i = 0; i < bcl.size(); i++) {
                                        gridCommunityList.add(bcl.get(i).getSqmc());
                                    }
                                }
                                gvCommunity.setAdapter(new ArrayAdapter<>(activity, R.layout.popup_person_grid_text_only, gridCommunityList));
                            }else{
                                Log.e(TAG, "onResponse: /team/findById返回的数据为空" );
                            }

                        }
                    }
                });
    }
/**
 *
 *@desc 设置下方listview的数据
 *@param
 *@date 2018/6/18 20:54
*/
    private void setListViewData() {
        data = new ArrayList<>();
        data.add(new MyInfoModel("文书管理", R.mipmap.document_management));
        data.add(new MyInfoModel("检查记录", R.mipmap.check_record));
//        data.add(new MyInfoModel("安检法规", R.mipmap.security_regulation));
        data.add(new MyInfoModel("系统设置", R.mipmap.system_setup));
//            data.add(new MyInfoModel("gps定位 工作日8:00-17:00开启", R.mipmap.gps));
        listAdapter = new ListAdapter(data, activity);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0://文书管理
                        intent = new Intent(activity, InstrumentActivity.class);
                        activity.startActivity(intent);
                        popupWindow.dismiss();
                        break;
                    case 1://检查记录
                        intent = new Intent();
                        intent.setClass(activity, EnterpriseCheckRecordActivity.class);
                        intent.putExtra("loginId", loginId);
                        intent.putExtra("startClass", "group");
                        activity.startActivity(intent);
                        popupWindow.dismiss();
                        break;
                    case 2://系统设置
                        intent = new Intent(activity, SystemSettingsActivity.class);
                        intent.putExtra("id", loginId);
                        activity.startActivity(intent);
                        popupWindow.dismiss();
                        break;
                    case 3://系统设置

                        break;
//                        case 5://gps
//                            boolean gpsReady = GpsUtil.openGPSSettings(activity);
//                            if (gpsReady) {
//                                Toast.makeText(activity, "正在上传定位信息", Toast.LENGTH_SHORT).show();
//                                popupWindow.dismiss();
//                            } else {
//                                Toast.makeText(activity, "被禁止获取定位权限，请手动开启", Toast.LENGTH_SHORT).show();
//                            }
//                            break;
                }
            }
        });
    }

}
