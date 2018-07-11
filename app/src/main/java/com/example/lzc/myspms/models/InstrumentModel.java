package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/4/17.
 */

public class InstrumentModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1525430953000,"htmlUrl":"http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_FCYJS.html","id":2,"jcId":1,"scjd":100,"scnr":"http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_FCYJS.doc","sczt":1,"status":1},{"createBy":1,"createTime":1525430192000,"htmlUrl":"http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_ZGTZS.html","id":1,"jcId":1,"scjd":100,"scnr":"http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_ZGTZS.doc","sczt":1,"status":1}],"pn":1,"size":10,"total":2}
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
    public class InstrumentMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1525430953000,"htmlUrl":"http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_FCYJS.html","id":2,"jcId":1,"scjd":100,"scnr":"http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_FCYJS.doc","sczt":1,"status":1},{"createBy":1,"createTime":1525430192000,"htmlUrl":"http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_ZGTZS.html","id":1,"jcId":1,"scjd":100,"scnr":"http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_ZGTZS.doc","sczt":1,"status":1}]
         * pn : 1
         * size : 10
         * total : 2
         */

        private int pn;
        private int size;
        private int total;
        private List<ListBean> list;

        public int getPn() {
            return pn;
        }

        public void setPn(int pn) {
            this.pn = pn;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1525430953000
             * htmlUrl : http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_FCYJS.html
             * id : 2
             * jcId : 1
             * scjd : 100
             * scnr : http://120.25.251.167:8088/hfs/source/document/20185/20180504_2_FCYJS.doc
             * sczt : 1
             * status : 1
             */

            private int createBy;
            private long createTime;
            private String htmlUrl;
            private int id;
            private int jcId;
            private int scjd;
            private String scnr;
            private int sczt;
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

            public String getHtmlUrl() {
                return htmlUrl;
            }

            public void setHtmlUrl(String htmlUrl) {
                this.htmlUrl = htmlUrl;
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

            public int getScjd() {
                return scjd;
            }

            public void setScjd(int scjd) {
                this.scjd = scjd;
            }

            public String getScnr() {
                return scnr;
            }

            public void setScnr(String scnr) {
                this.scnr = scnr;
            }

            public int getSczt() {
                return sczt;
            }

            public void setSczt(int sczt) {
                this.sczt = sczt;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
