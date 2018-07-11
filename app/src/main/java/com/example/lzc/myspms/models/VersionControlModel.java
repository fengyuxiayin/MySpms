package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/4/28.
 */

public class VersionControlModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"createBy":1,"createTime":1524815430000,"versionCode":"1.0","versionName":"0.1.1","downloadUrl":"http://120.25.251.167:8088/hfs/source/apk/xiazhuang.apk","id":1,"isMust":0,"status":1}
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
    public class VersionControlMsgModel implements Serializable{


        /**
         * createBy : 1
         * createTime : 1524815430000
         * versionCode : 1.0
         * versionName : 0.1.1
         * downloadUrl : http://120.25.251.167:8088/hfs/source/apk/xiazhuang.apk
         * id : 1
         * isMust : 0
         * status : 1
         */

        private int createBy;
        private long createTime;
        private String versionCode;
        private String versionName;
        private String downloadUrl;
        private int id;
        private int isMust;
        private int status;

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsMust() {
            return isMust;
        }

        public void setIsMust(int isMust) {
            this.isMust = isMust;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
