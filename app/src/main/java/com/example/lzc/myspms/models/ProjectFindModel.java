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

    private Integer code;
    private boolean data;
    private String msg;
    private String url;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

        private Integer pn;
        private Integer size;
        private Integer total;
        private List<ListBean> list;

        public Integer getPn() {
            return pn;
        }

        public void setPn(Integer pn) {
            this.pn = pn;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
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

            private Integer createBy;
            private Long createTime;
            private Integer id;
            private Long modifyTime;
            private Integer qyId;
            private String ssbm;
            private Integer status;
            private Integer wxylx;
            private String wxymc;
            private String bm;

            public String getBm() {
                return bm;
            }
            public void setBm(String bm) {
                this.bm = bm;
            }

            public Integer getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Integer createBy) {
                this.createBy = createBy;
            }

            public Long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Long createTime) {
                this.createTime = createTime;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public Integer getQyId() {
                return qyId;
            }

            public void setQyId(Integer qyId) {
                this.qyId = qyId;
            }

            public String getSsbm() {
                return ssbm;
            }

            public void setSsbm(String ssbm) {
                this.ssbm = ssbm;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public Integer getWxylx() {
                return wxylx;
            }

            public void setWxylx(Integer wxylx) {
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
