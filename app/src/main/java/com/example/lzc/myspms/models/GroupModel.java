package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * 获取小组信息
 * Created by LZC on 2018/4/20.
 */

public class GroupModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"createBy":1,"createTime":1523269903000,"id":1,"modifyTime":1523270157000,"pqId":1,"rysl":3,"status":1,"xzmc":"夏庄街道安监小组"}
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
    public class GroupMsgModel implements Serializable{

        /**
         * createBy : 1
         * createTime : 1523269903000
         * id : 1
         * modifyTime : 1523270157000
         * pqId : 1
         * rysl : 3
         * status : 1
         * xzmc : 夏庄街道安监小组
         */

        private int createBy;
        private long createTime;
        private int id;
        private long modifyTime;
        private int pqId;
        private int rysl;
        private int status;
        private String xzmc;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getPqId() {
            return pqId;
        }

        public void setPqId(int pqId) {
            this.pqId = pqId;
        }

        public int getRysl() {
            return rysl;
        }

        public void setRysl(int rysl) {
            this.rysl = rysl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getXzmc() {
            return xzmc;
        }

        public void setXzmc(String xzmc) {
            this.xzmc = xzmc;
        }
    }
}
