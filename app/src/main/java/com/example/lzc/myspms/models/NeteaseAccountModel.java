package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/5/16.
 */

public class NeteaseAccountModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"accid":"heihei22","accountId":1,"createBy":1,"createTime":1525919282000,"gender":0,"id":1,"inUse":1,"isBlock":0,"loginName":"admin","modifyBy":1,"modifyTime":1526437979000,"name":"","status":1,"token":"d18d65688c6d7afd9653de390feaaf5f"}
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

    public class NeteaseAccountMsgModel implements Serializable{

        /**
         * accid : heihei22
         * accountId : 1
         * createBy : 1
         * createTime : 1525919282000
         * gender : 0
         * id : 1
         * inUse : 1
         * isBlock : 0
         * loginName : admin
         * modifyBy : 1
         * modifyTime : 1526437979000
         * name :
         * status : 1
         * token : d18d65688c6d7afd9653de390feaaf5f
         */

        private String accid;
        private int accountId;
        private int createBy;
        private long createTime;
        private int gender;
        private int id;
        private int inUse;
        private int isBlock;
        private String loginName;
        private int modifyBy;
        private long modifyTime;
        private String name;
        private int status;
        private String token;

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInUse() {
            return inUse;
        }

        public void setInUse(int inUse) {
            this.inUse = inUse;
        }

        public int getIsBlock() {
            return isBlock;
        }

        public void setIsBlock(int isBlock) {
            this.isBlock = isBlock;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public int getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(int modifyBy) {
            this.modifyBy = modifyBy;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
