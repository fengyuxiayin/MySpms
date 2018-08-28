package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/8/23.
 */

public class ProjectFindModel implements Serializable{

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1534329518000,"id":47,"modifyTime":1534329518000,"qyId":816,"ssbm":"生产部","status":1,"wxylx":1,"wxymc":"自动膜盒线"}],"pn":1,"size":10,"total":1}
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
    public class ProjectFindMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1534329518000,"id":47,"modifyTime":1534329518000,"qyId":816,"ssbm":"生产部","status":1,"wxylx":1,"wxymc":"自动膜盒线"}]
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
             * createTime : 1534329518000
             * id : 47
             * modifyTime : 1534329518000
             * qyId : 816
             * ssbm : 生产部
             * status : 1
             * wxylx : 1
             * wxymc : 自动膜盒线
             */

            private int createBy;
            private long createTime;
            private int id;
            private long modifyTime;
            private int qyId;
            private String ssbm;
            private int status;
            private int wxylx;
            private String wxymc;
            private String bm;

            public String getBm() {
                return bm;
            }

            public void setBm(String bm) {
                this.bm = bm;
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

            public long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getQyId() {
                return qyId;
            }

            public void setQyId(int qyId) {
                this.qyId = qyId;
            }

            public String getSsbm() {
                return ssbm;
            }

            public void setSsbm(String ssbm) {
                this.ssbm = ssbm;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getWxylx() {
                return wxylx;
            }

            public void setWxylx(int wxylx) {
                this.wxylx = wxylx;
            }

            public String getWxymc() {
                return wxymc;
            }

            public void setWxymc(String wxymc) {
                this.wxymc = wxymc;
            }
        }
    }
}
