package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2017/12/21.
 */

public class ProjectInfoModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"bhgyy":"","createBy":1,"createTime":1526035401000,"id":11,"jcId":9,"jcjg":1,"jcjssj":1526291459000,"jckssj":1526035401000,"jclx":1,"jctp":"http://120.25.251.167:8088/hfs/source/project/20185/20180514175047_551.png,http://120.25.251.167:8088/hfs/source/project/20185/20180514175052_894.png","modifyBy":1,"modifyTime":1526291459000,"standardId":1,"status":1}
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
    public class ProjectInfoMsgModel implements Serializable{

        /**
         * bhgyy :
         * createBy : 1
         * createTime : 1526035401000
         * id : 11
         * jcId : 9
         * jcjg : 1
         * jcjssj : 1526291459000
         * jckssj : 1526035401000
         * jclx : 1
         * jctp : http://120.25.251.167:8088/hfs/source/project/20185/20180514175047_551.png,http://120.25.251.167:8088/hfs/source/project/20185/20180514175052_894.png
         * modifyBy : 1
         * modifyTime : 1526291459000
         * standardId : 1
         * status : 1
         */

        private String bhgyy;
        private int createBy;
        private long createTime;
        private int id;
        private int jcId;
        private int jcjg;
        private long jcjssj;
        private long jckssj;
        private int jclx;
        private String jctp;
        private int modifyBy;
        private long modifyTime;
        private int standardId;
        private int status;

        public String getBhgyy() {
            return bhgyy;
        }

        public void setBhgyy(String bhgyy) {
            this.bhgyy = bhgyy;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getJcId() {
            return jcId;
        }

        public void setJcId(int jcId) {
            this.jcId = jcId;
        }

        public int getJcjg() {
            return jcjg;
        }

        public void setJcjg(int jcjg) {
            this.jcjg = jcjg;
        }

        public long getJcjssj() {
            return jcjssj;
        }

        public void setJcjssj(long jcjssj) {
            this.jcjssj = jcjssj;
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

        public String getJctp() {
            return jctp;
        }

        public void setJctp(String jctp) {
            this.jctp = jctp;
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

        public int getStandardId() {
            return standardId;
        }

        public void setStandardId(int standardId) {
            this.standardId = standardId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
