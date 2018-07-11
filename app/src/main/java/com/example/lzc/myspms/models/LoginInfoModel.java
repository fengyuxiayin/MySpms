package com.example.lzc.myspms.models;

/**
 * Created by LZC on 2017/10/31.
 */
public class LoginInfoModel {
    /**
     * code : 1
     * data : true
     * msg : {"createBy":1,"createTime":1501480099000,"id":1,"loginName":"admin","loginType":1,"modifyBy":1,"modifyTime":1509000131000,"roleId":1,"ssId":1,"status":1}
     * url :
     */

    private int code;
    private boolean data;
    private String msg;
    private String url;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
