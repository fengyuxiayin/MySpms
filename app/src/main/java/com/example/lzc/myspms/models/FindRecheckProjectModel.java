package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/5/16.
 */

public class FindRecheckProjectModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"bhgyy":"不合格","createBy":1,"createTime":1526453119000,"description":"企业主要负责任或安全管理人员参加安全生产和职业卫生管理培训并取得《培训合格证书》。","id":2,"jcId":2,"jcjg":0,"jckssj":1526453119000,"jclx":2,"jctp":"http://120.25.251.167:8088/hfs/source/project/20185/20180516143953_759.jpg","qyId":1,"standardId":1,"status":1}],"pn":1,"size":10,"total":1}
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
    public class FindRecheckProjectMsgModel implements Serializable{

        /**
         * list : [{"bhgyy":"不合格","createBy":1,"createTime":1526453119000,"description":"企业主要负责任或安全管理人员参加安全生产和职业卫生管理培训并取得《培训合格证书》。","id":2,"jcId":2,"jcjg":0,"jckssj":1526453119000,"jclx":2,"jctp":"http://120.25.251.167:8088/hfs/source/project/20185/20180516143953_759.jpg","qyId":1,"standardId":1,"status":1}]
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
             * bhgyy : 不合格
             * createBy : 1
             * createTime : 1526453119000
             * description : 企业主要负责任或安全管理人员参加安全生产和职业卫生管理培训并取得《培训合格证书》。
             * id : 2
             * jcId : 2
             * jcjg : 0
             * jckssj : 1526453119000
             * jclx : 2
             * jctp : http://120.25.251.167:8088/hfs/source/project/20185/20180516143953_759.jpg
             * qyId : 1
             * standardId : 1
             * status : 1
             */

            private String bhgyy;
            private int createBy;
            private long createTime;
            private String description;
            private int id;
            private int jcId;
            private int jcjg;
            private long jckssj;
            private int jclx;
            private String jctp;
            private int qyId;
            private int standardId;
            private int status;
            private String pubish;
            private String refrenceBasis;

            public String getPubish() {
                return pubish;
            }

            public void setPubish(String pubish) {
                this.pubish = pubish;
            }

            public String getRefrenceBasis() {
                return refrenceBasis;
            }

            public void setRefrenceBasis(String refrenceBasis) {
                this.refrenceBasis = refrenceBasis;
            }

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public int getQyId() {
                return qyId;
            }

            public void setQyId(int qyId) {
                this.qyId = qyId;
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
}
