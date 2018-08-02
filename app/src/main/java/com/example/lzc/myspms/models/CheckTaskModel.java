package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/8/2.
 */

public class CheckTaskModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":117,"createTime":1533178742000,"id":13,"jcdwId":2,"jcdwlx":2,"jcdwmc":"东北片组","jcdx":"3家企业","jczt":"东北片组","jzsj":1533225600000,"kssj":1533225600000,"modifyTime":1533178742000,"qyId":785,"qymc":"格力","rwjd":0,"rwlx":1,"rwmc":"8-2-1","rwzt":0,"status":1,"tags":"4d2a1a349a924086b2cdf4b38aeccbf7"},{"createBy":117,"createTime":1533178633000,"id":11,"jcdwId":2,"jcdwlx":2,"jcdwmc":"东北片组","jcdx":"2家企业","jczt":"东北片组","jzsj":1533139200000,"kssj":1533139200000,"modifyTime":1533178633000,"qyId":785,"qymc":"格力","rwjd":0,"rwlx":1,"rwmc":"8-2","rwzt":0,"status":1,"tags":"8afba96cd7544e838f859745c428c95f"},{"createBy":1,"createTime":1532682609000,"id":1,"jcdwId":2,"jcdwlx":2,"jcdwmc":"东北片组","jcdx":"8家企业","jczt":"东北片组","jzsj":1532966400000,"kssj":1532620800000,"modifyBy":117,"modifyTime":1532683127000,"qyId":808,"qymc":"青岛银丰金属工业有限公司","rwjd":25,"rwlx":1,"rwmc":"7-27","rwzt":1,"status":1,"tags":"b6a97a8ce68d491e91d0315fcc58a82e"}],"pn":1,"size":10,"total":3}
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
    public class CheckTaskMsgModel implements Serializable{

        /**
         * list : [{"createBy":117,"createTime":1533178742000,"id":13,"jcdwId":2,"jcdwlx":2,"jcdwmc":"东北片组","jcdx":"3家企业","jczt":"东北片组","jzsj":1533225600000,"kssj":1533225600000,"modifyTime":1533178742000,"qyId":785,"qymc":"格力","rwjd":0,"rwlx":1,"rwmc":"8-2-1","rwzt":0,"status":1,"tags":"4d2a1a349a924086b2cdf4b38aeccbf7"},{"createBy":117,"createTime":1533178633000,"id":11,"jcdwId":2,"jcdwlx":2,"jcdwmc":"东北片组","jcdx":"2家企业","jczt":"东北片组","jzsj":1533139200000,"kssj":1533139200000,"modifyTime":1533178633000,"qyId":785,"qymc":"格力","rwjd":0,"rwlx":1,"rwmc":"8-2","rwzt":0,"status":1,"tags":"8afba96cd7544e838f859745c428c95f"},{"createBy":1,"createTime":1532682609000,"id":1,"jcdwId":2,"jcdwlx":2,"jcdwmc":"东北片组","jcdx":"8家企业","jczt":"东北片组","jzsj":1532966400000,"kssj":1532620800000,"modifyBy":117,"modifyTime":1532683127000,"qyId":808,"qymc":"青岛银丰金属工业有限公司","rwjd":25,"rwlx":1,"rwmc":"7-27","rwzt":1,"status":1,"tags":"b6a97a8ce68d491e91d0315fcc58a82e"}]
         * pn : 1
         * size : 10
         * total : 3
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
             * createBy : 117
             * createTime : 1533178742000
             * id : 13
             * jcdwId : 2
             * jcdwlx : 2
             * jcdwmc : 东北片组
             * jcdx : 3家企业
             * jczt : 东北片组
             * jzsj : 1533225600000
             * kssj : 1533225600000
             * modifyTime : 1533178742000
             * qyId : 785
             * qymc : 格力
             * rwjd : 0
             * rwlx : 1
             * rwmc : 8-2-1
             * rwzt : 0
             * status : 1
             * tags : 4d2a1a349a924086b2cdf4b38aeccbf7
             * modifyBy : 117
             */

            private int createBy;
            private long createTime;
            private int id;
            private int jcdwId;
            private int jcdwlx;
            private String jcdwmc;
            private String jcdx;
            private String jczt;
            private long jzsj;
            private long kssj;
            private long modifyTime;
            private int qyId;
            private String qymc;
            private int rwjd;
            private int rwlx;
            private String rwmc;
            private int rwzt;
            private int status;
            private String tags;
            private int modifyBy;

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

            public String getJcdwmc() {
                return jcdwmc;
            }

            public void setJcdwmc(String jcdwmc) {
                this.jcdwmc = jcdwmc;
            }

            public String getJcdx() {
                return jcdx;
            }

            public void setJcdx(String jcdx) {
                this.jcdx = jcdx;
            }

            public String getJczt() {
                return jczt;
            }

            public void setJczt(String jczt) {
                this.jczt = jczt;
            }

            public long getJzsj() {
                return jzsj;
            }

            public void setJzsj(long jzsj) {
                this.jzsj = jzsj;
            }

            public long getKssj() {
                return kssj;
            }

            public void setKssj(long kssj) {
                this.kssj = kssj;
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

            public String getQymc() {
                return qymc;
            }

            public void setQymc(String qymc) {
                this.qymc = qymc;
            }

            public int getRwjd() {
                return rwjd;
            }

            public void setRwjd(int rwjd) {
                this.rwjd = rwjd;
            }

            public int getRwlx() {
                return rwlx;
            }

            public void setRwlx(int rwlx) {
                this.rwlx = rwlx;
            }

            public String getRwmc() {
                return rwmc;
            }

            public void setRwmc(String rwmc) {
                this.rwmc = rwmc;
            }

            public int getRwzt() {
                return rwzt;
            }

            public void setRwzt(int rwzt) {
                this.rwzt = rwzt;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public int getModifyBy() {
                return modifyBy;
            }

            public void setModifyBy(int modifyBy) {
                this.modifyBy = modifyBy;
            }
        }
    }
}
