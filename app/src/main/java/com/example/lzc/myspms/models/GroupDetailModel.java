package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/4/20.
 */

public class GroupDetailModel implements Serializable{

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1523270031000,"gzbh":"A003","id":1,"lxdh":"13853277795","modifyBy":1,"modifyTime":1523270120000,"rymc":"邓怀宇","ryzc":2,"ssdwId":1,"ssdwlx":2,"ssdwmc":"夏庄街道安监小组","status":1,"xzfzr":0},{"createBy":1,"createTime":1523270157000,"gzbh":"A001","id":3,"lxdh":"13863980276","modifyTime":1523270157000,"rymc":"于友涛","ryzc":1,"ssdwId":1,"ssdwlx":2,"ssdwmc":"夏庄街道安监小组","status":1,"xzfzr":0},{"createBy":1,"createTime":1523270067000,"gzbh":"A002","id":2,"lxdh":"18669739880","modifyBy":1,"modifyTime":1523270079000,"rymc":"孙兆波","ryzc":1,"ssdwId":1,"ssdwlx":2,"ssdwmc":"夏庄街道安监小组","status":1,"xzfzr":0}],"pn":1,"size":10,"total":3}
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
    public class GroupDetailMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1523270031000,"gzbh":"A003","id":1,"lxdh":"13853277795","modifyBy":1,"modifyTime":1523270120000,"rymc":"邓怀宇","ryzc":2,"ssdwId":1,"ssdwlx":2,"ssdwmc":"夏庄街道安监小组","status":1,"xzfzr":0},{"createBy":1,"createTime":1523270157000,"gzbh":"A001","id":3,"lxdh":"13863980276","modifyTime":1523270157000,"rymc":"于友涛","ryzc":1,"ssdwId":1,"ssdwlx":2,"ssdwmc":"夏庄街道安监小组","status":1,"xzfzr":0},{"createBy":1,"createTime":1523270067000,"gzbh":"A002","id":2,"lxdh":"18669739880","modifyBy":1,"modifyTime":1523270079000,"rymc":"孙兆波","ryzc":1,"ssdwId":1,"ssdwlx":2,"ssdwmc":"夏庄街道安监小组","status":1,"xzfzr":0}]
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
             * createBy : 1
             * createTime : 1523270031000
             * gzbh : A003
             * id : 1
             * lxdh : 13853277795
             * modifyBy : 1
             * modifyTime : 1523270120000
             * rymc : 邓怀宇
             * ryzc : 2
             * ssdwId : 1
             * ssdwlx : 2
             * ssdwmc : 夏庄街道安监小组
             * status : 1
             * xzfzr : 0
             */

            private int createBy;
            private long createTime;
            private String gzbh;
            private int id;
            private String lxdh;
            private int modifyBy;
            private long modifyTime;
            private String rymc;
            private int ryzc;
            private int ssdwId;
            private int ssdwlx;
            private String ssdwmc;
            private int status;
            private int xzfzr;

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

            public String getGzbh() {
                return gzbh;
            }

            public void setGzbh(String gzbh) {
                this.gzbh = gzbh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLxdh() {
                return lxdh;
            }

            public void setLxdh(String lxdh) {
                this.lxdh = lxdh;
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

            public String getRymc() {
                return rymc;
            }

            public void setRymc(String rymc) {
                this.rymc = rymc;
            }

            public int getRyzc() {
                return ryzc;
            }

            public void setRyzc(int ryzc) {
                this.ryzc = ryzc;
            }

            public int getSsdwId() {
                return ssdwId;
            }

            public void setSsdwId(int ssdwId) {
                this.ssdwId = ssdwId;
            }

            public int getSsdwlx() {
                return ssdwlx;
            }

            public void setSsdwlx(int ssdwlx) {
                this.ssdwlx = ssdwlx;
            }

            public String getSsdwmc() {
                return ssdwmc;
            }

            public void setSsdwmc(String ssdwmc) {
                this.ssdwmc = ssdwmc;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getXzfzr() {
                return xzfzr;
            }

            public void setXzfzr(int xzfzr) {
                this.xzfzr = xzfzr;
            }
        }
    }
}
