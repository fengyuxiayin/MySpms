package com.example.lzc.myspms.models;

/**
 * Created by LZC on 2017/9/7.
 */
public class Constant {
    //Read权限标志位
    public static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    //Camera,麦克风权限标志位
    public static final int CAMERA_STATE_CODE = 2;
    //精确位置标志位&粗略位置标志位
    public static final int ACCESS_FINE_LOCATION_COARSE_LOCATION_REQUEST_CODE = 3;
    //DiaLog加载的文字
    public static final String DIALOG_CONTENT = "正在加载...";
    //获取GPS权限标志位
    public static final int GPS_REQUEST_CODE = 4;
    //高经理地址
    public static final String SERVER_URL = "http://192.168.3.92:8080";
//    public static final String SERVER_URL = "http://192.168.43.236:8080";
    //风选地址
//    public static final String SERVER_URL = "http://192.168.3.66:8080";
    //许哥地址
//    public static final String SERVER_URL = "http://192.168.3.69:8080";
//    public static final String SERVER_URL = "http://10.8.59.132:8080";
    //仇哥地址
//
//    public static final String SERVER_URL = "http://10.8.56.239:8080";
    //伟良地址
//    public static final String SERVER_URL = "http://192.168.3.17:8080";
//    public static final String SERVER_URL = "https://120.25.251.167:1443";
//    public static final String SERVER_URL = "http://120.25.251.167:68";
//    public static final String SERVER_URL = "http://27.223.106.182:68";
//    public static final String SERVER_URL = "https://120.25.251.167:3306";
    //FENGXUAN服务器地址
//    public static final String FENGXUAN_URL = "http://192.168.3.4:8080";
    //请求枚举信息后缀
    public static final String ENUM_URL = "/enum/getEnums";
    //请求企业信息后缀
    public static final String FINDALL_URL = "/community/findAll";
    //公司名
    public static  String ENTERPRISE_NAME ="";
    //企业ssid
    public static  String ENTERPRISE_ID ="";
    //账号id 上传位置信息时需要的参数
    public static  String ACCOUNT_ID ="";
    //账号类型 社区 小组 企业 街道 成员单位 1 2 3 4 5
    public static  String ACCOUNT_TYPE ="";
    //账号的名字
    public static  String USER_NAME ="";
    //
    public static  String LOCATION_INFO = "";
    //添加/修改项目  区别在于是否传了id
    public static final String ADD_PROJECT = "/project/save";
    //获取所有项目信息 参数为id 数据是qyid
    public static final String GET_ALL_PROJECT_ = "/project/find";
    //获取项目的详细信息 id为项目id
    public static final String GET_PROJECT_INFO_BY_ID = "/project/findById";
    //获取相机的RequestCode
    public static final int REQUEST_CAMERA = 0;
    //照片的绝对路径
    public static  String PHOTO_ABSOLUTE_PATH= "";
    //position 记录点击的检查点的条目的position
    public static  int POINT_POSITION =0;
    //获取相册的RequestCode
    public static final int REQUEST_ALBUM_OK = 1;
    //上传地址的前缀
//    public static String UPLOAD_IMG_IP = "http://27.223.106.182:9093/hfs";
    public static String UPLOAD_IMG_IP = "http://120.25.251.167:8088/hfs";
    //网易云对方的账户id
    public static String RECEIVE_ID = "heihei22";
    //第一层gridview的position
    public static  int GRID_POS_ONE = 0;
    //第二层gridview的position
    public static  int GRID_POS_TWO = 0;
}
