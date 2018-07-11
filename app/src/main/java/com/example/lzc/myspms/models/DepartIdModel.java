package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/4/27.
 */

public class DepartIdModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"createBy":1,"createTime":1510309698000,"descript":"","id":167,"modifyBy":1,"modifyTime":1510558680000,"name":"消防类","number":null,"path":"0","pid":0,"referenceBasis":"","sendDepart":"44","status":1}
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
    public class DepartIdMsgModel implements Serializable{

        /**
         * createBy : 1
         * createTime : 1510309698000
         * descript :
         * id : 167
         * modifyBy : 1
         * modifyTime : 1510558680000
         * name : 消防类
         * number : null
         * path : 0
         * pid : 0
         * referenceBasis :
         * sendDepart : 44
         * status : 1
         */

        private int createBy;
        private long createTime;
        private String descript;
        private int id;
        private int modifyBy;
        private long modifyTime;
        private String name;
        private Object number;
        private String path;
        private int pid;
        private String referenceBasis;
        private String sendDepart;
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

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Object getNumber() {
            return number;
        }

        public void setNumber(Object number) {
            this.number = number;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getReferenceBasis() {
            return referenceBasis;
        }

        public void setReferenceBasis(String referenceBasis) {
            this.referenceBasis = referenceBasis;
        }

        public String getSendDepart() {
            return sendDepart;
        }

        public void setSendDepart(String sendDepart) {
            this.sendDepart = sendDepart;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
