package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2017/12/6.
 */

public class CheckIsExistModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"createBy":1,"createTime":1512539897000,"id":4,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1512539897000,"jclx":1,"qyId":5,"sfyq":0,"status":1,"zqjlId":19}
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
    public class CheckIsExistMsgModel implements Serializable{

        /**
         * createBy : 1
         * createTime : 1512539897000
         * id : 4
         * jcdwId : 1
         * jcdwlx : 2
         * jcjg : 0
         * jckssj : 1512539897000
         * jclx : 1
         * qyId : 5
         * sfyq : 0
         * status : 1
         * zqjlId : 19
         */

        private int createBy;
        private long createTime;
        private int id;
        private int jcdwId;
        private int jcdwlx;
        private int jcjg;
        private long jckssj;
        private int jclx;
        private int qyId;
        private int sfyq;
        private int status;
        private int zqjlId;

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

        public int getJcdwId() {
            return jcdwId;
        }

        public void setJcdwId(int jcdwId) {
            this.jcdwId = jcdwId;
        }

        public int getJcdwlx() {
            return jcdwlx;
        }

        public void setJcdwlx(int jcdwlx) {
            this.jcdwlx = jcdwlx;
        }

        public int getJcjg() {
            return jcjg;
        }

        public void setJcjg(int jcjg) {
            this.jcjg = jcjg;
        }

        public long getJckssj() {
            return jckssj;
        }

        public void setJckssj(long jckssj) {
            this.jckssj = jckssj;
        }

        public int getJclx() {
            return jclx;
        }

        public void setJclx(int jclx) {
            this.jclx = jclx;
        }

        public int getQyId() {
            return qyId;
        }

        public void setQyId(int qyId) {
            this.qyId = qyId;
        }

        public int getSfyq() {
            return sfyq;
        }

        public void setSfyq(int sfyq) {
            this.sfyq = sfyq;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getZqjlId() {
            return zqjlId;
        }

        public void setZqjlId(int zqjlId) {
            this.zqjlId = zqjlId;
        }
    }
}
