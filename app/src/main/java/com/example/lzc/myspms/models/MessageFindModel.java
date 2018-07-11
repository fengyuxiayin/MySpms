package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/5/28.
 */

public class MessageFindModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1528807130000,"fj":"/source/msgFile/11.zip,/source/msgFile/22.jpg","fjrId":4,"fjrMc":"中国化工加油站第2站加油站","fjrlx":3,"id":3,"jszList":null,"modifyBy":null,"modifyTime":null,"parentId":0,"sjrId":1,"sjrMc":"夏庄街道安监小组","sjrlx":2,"status":1,"tags":null,"xxbt":"巴拉","xxlx":1,"xxnr":"d打发点","xxzt":0}],"pn":1,"size":10,"total":1}
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

    public class MessageFindMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1528807130000,"fj":"/source/msgFile/11.zip,/source/msgFile/22.jpg","fjrId":4,"fjrMc":"中国化工加油站第2站加油站","fjrlx":3,"id":3,"jszList":null,"modifyBy":null,"modifyTime":null,"parentId":0,"sjrId":1,"sjrMc":"夏庄街道安监小组","sjrlx":2,"status":1,"tags":null,"xxbt":"巴拉","xxlx":1,"xxnr":"d打发点","xxzt":0}]
         * pn : 1
         * size : 10
         * total : 1
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
             * createTime : 1528807130000
             * fj : /source/msgFile/11.zip,/source/msgFile/22.jpg
             * fjrId : 4
             * fjrMc : 中国化工加油站第2站加油站
             * fjrlx : 3
             * id : 3
             * jszList : null
             * modifyBy : null
             * modifyTime : null
             * parentId : 0
             * sjrId : 1
             * sjrMc : 夏庄街道安监小组
             * sjrlx : 2
             * status : 1
             * tags : null
             * xxbt : 巴拉
             * xxlx : 1
             * xxnr : d打发点
             * xxzt : 0
             */

            private int createBy;
            private long createTime;
            private String fj;
            private int fjrId;
            private String fjrMc;
            private int fjrlx;
            private int id;
            private Object jszList;
            private Object modifyBy;
            private Object modifyTime;
            private int parentId;
            private int sjrId;
            private String sjrMc;
            private int sjrlx;
            private int status;
            private Object tags;
            private String xxbt;
            private int xxlx;
            private String xxnr;
            private int xxzt;

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

            public String getFj() {
                return fj;
            }

            public void setFj(String fj) {
                this.fj = fj;
            }

            public int getFjrId() {
                return fjrId;
            }

            public void setFjrId(int fjrId) {
                this.fjrId = fjrId;
            }

            public String getFjrMc() {
                return fjrMc;
            }

            public void setFjrMc(String fjrMc) {
                this.fjrMc = fjrMc;
            }

            public int getFjrlx() {
                return fjrlx;
            }

            public void setFjrlx(int fjrlx) {
                this.fjrlx = fjrlx;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getJszList() {
                return jszList;
            }

            public void setJszList(Object jszList) {
                this.jszList = jszList;
            }

            public Object getModifyBy() {
                return modifyBy;
            }

            public void setModifyBy(Object modifyBy) {
                this.modifyBy = modifyBy;
            }

            public Object getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Object modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getSjrId() {
                return sjrId;
            }

            public void setSjrId(int sjrId) {
                this.sjrId = sjrId;
            }

            public String getSjrMc() {
                return sjrMc;
            }

            public void setSjrMc(String sjrMc) {
                this.sjrMc = sjrMc;
            }

            public int getSjrlx() {
                return sjrlx;
            }

            public void setSjrlx(int sjrlx) {
                this.sjrlx = sjrlx;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getTags() {
                return tags;
            }

            public void setTags(Object tags) {
                this.tags = tags;
            }

            public String getXxbt() {
                return xxbt;
            }

            public void setXxbt(String xxbt) {
                this.xxbt = xxbt;
            }

            public int getXxlx() {
                return xxlx;
            }

            public void setXxlx(int xxlx) {
                this.xxlx = xxlx;
            }

            public String getXxnr() {
                return xxnr;
            }

            public void setXxnr(String xxnr) {
                this.xxnr = xxnr;
            }

            public int getXxzt() {
                return xxzt;
            }

            public void setXxzt(int xxzt) {
                this.xxzt = xxzt;
            }
        }
    }
}
