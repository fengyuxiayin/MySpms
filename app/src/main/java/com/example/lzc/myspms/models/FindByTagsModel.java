package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/6/18.
 */

public class FindByTagsModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1529321346000,"id":12,"jcdwlx":2,"jcdx":"ggdf","jzsj":1531843200000,"kssj":1527523200000,"modifyTime":1529321346000,"qyId":129,"qymc":"ggdf","rwlx":1,"rwmc":"ascds","rwzt":0,"status":1,"tags":"1a7ae3eb7c064ad8a6969f3ba51a5bb7"},{"createBy":1,"createTime":1529220679000,"id":5,"jcdwId":2,"jcdwlx":2,"jcdwmc":"流亭街道安监小组","jcdx":"中国石油山东青岛销售分公司第3加油站","jczt":"流亭街道安监小组","jzsj":1531238400000,"kssj":1528646400000,"modifyTime":1529220679000,"qyId":5,"qymc":"中国石油山东青岛销售分公司第3加油站","rwlx":1,"rwmc":"测试任务1","rwzt":0,"status":1,"tags":"5d010a7aedaa4742a40a927f41062b39"},{"createBy":1,"createTime":1529220601000,"id":1,"jcdwId":1,"jcdwlx":2,"jcdwmc":"夏庄街道安监小组","jcdx":"中国石油山东青岛销售分公司第131加油站","jczt":"夏庄街道安监小组","jzsj":1533657600000,"kssj":1527782400000,"modifyBy":1,"modifyTime":1529316317000,"qyId":1,"qymc":"中国石油山东青岛销售分公司第131加油站","rwlx":1,"rwmc":"测试任务1","rwzt":2,"status":1,"tags":"a437f3183c58489a88a4b1b527f2e776"},{"createBy":1,"createTime":1529322034000,"id":20,"jcdwlx":2,"jcdx":"多条件（5）","jzsj":1531929600000,"kssj":1529424000000,"modifyTime":1529322034000,"qyId":144,"qymc":"gfdgfdg","rwlx":1,"rwmc":"页面新增","rwzt":0,"status":1,"tags":"d56394550d7745719156616c146e6981"}],"pn":1,"size":10,"total":13}
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
    public class FindByTagsMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1529321346000,"id":12,"jcdwlx":2,"jcdx":"ggdf","jzsj":1531843200000,"kssj":1527523200000,"modifyTime":1529321346000,"qyId":129,"qymc":"ggdf","rwlx":1,"rwmc":"ascds","rwzt":0,"status":1,"tags":"1a7ae3eb7c064ad8a6969f3ba51a5bb7"},{"createBy":1,"createTime":1529220679000,"id":5,"jcdwId":2,"jcdwlx":2,"jcdwmc":"流亭街道安监小组","jcdx":"中国石油山东青岛销售分公司第3加油站","jczt":"流亭街道安监小组","jzsj":1531238400000,"kssj":1528646400000,"modifyTime":1529220679000,"qyId":5,"qymc":"中国石油山东青岛销售分公司第3加油站","rwlx":1,"rwmc":"测试任务1","rwzt":0,"status":1,"tags":"5d010a7aedaa4742a40a927f41062b39"},{"createBy":1,"createTime":1529220601000,"id":1,"jcdwId":1,"jcdwlx":2,"jcdwmc":"夏庄街道安监小组","jcdx":"中国石油山东青岛销售分公司第131加油站","jczt":"夏庄街道安监小组","jzsj":1533657600000,"kssj":1527782400000,"modifyBy":1,"modifyTime":1529316317000,"qyId":1,"qymc":"中国石油山东青岛销售分公司第131加油站","rwlx":1,"rwmc":"测试任务1","rwzt":2,"status":1,"tags":"a437f3183c58489a88a4b1b527f2e776"},{"createBy":1,"createTime":1529322034000,"id":20,"jcdwlx":2,"jcdx":"多条件（5）","jzsj":1531929600000,"kssj":1529424000000,"modifyTime":1529322034000,"qyId":144,"qymc":"gfdgfdg","rwlx":1,"rwmc":"页面新增","rwzt":0,"status":1,"tags":"d56394550d7745719156616c146e6981"}]
         * pn : 1
         * size : 10
         * total : 13
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
             * createTime : 1529321346000
             * id : 12
             * jcdwlx : 2
             * jcdx : ggdf
             * jzsj : 1531843200000
             * kssj : 1527523200000
             * modifyTime : 1529321346000
             * qyId : 129
             * qymc : ggdf
             * rwlx : 1
             * rwmc : ascds
             * rwzt : 0
             * status : 1
             * tags : 1a7ae3eb7c064ad8a6969f3ba51a5bb7
             * jcdwId : 2
             * jcdwmc : 流亭街道安监小组
             * jczt : 流亭街道安监小组
             * modifyBy : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private int jcdwlx;
            private String jcdx;
            private long jzsj;
            private long kssj;
            private long modifyTime;
            private int qyId;
            private String qymc;
            private int rwlx;
            private String rwmc;
            private int rwzt;
            private int status;
            private String tags;
            private int jcdwId;
            private String jcdwmc;
            private String jczt;
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

            public int getJcdwlx() {
                return jcdwlx;
            }

            public void setJcdwlx(int jcdwlx) {
                this.jcdwlx = jcdwlx;
            }

            public String getJcdx() {
                return jcdx;
            }

            public void setJcdx(String jcdx) {
                this.jcdx = jcdx;
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

            public int getJcdwId() {
                return jcdwId;
            }

            public void setJcdwId(int jcdwId) {
                this.jcdwId = jcdwId;
            }

            public String getJcdwmc() {
                return jcdwmc;
            }

            public void setJcdwmc(String jcdwmc) {
                this.jcdwmc = jcdwmc;
            }

            public String getJczt() {
                return jczt;
            }

            public void setJczt(String jczt) {
                this.jczt = jczt;
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
