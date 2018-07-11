package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/12/13.
 */

public class CommunityStaffInfoModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1512005617000,"gzbh":"3","id":9,"lxdh":"18700000001","modifyBy":1,"modifyTime":1512374614000,"rymc":"哈哈哈哈","ryzc":1,"ssdwId":1,"ssdwlx":1,"ssdwmc":"高家台","status":1,"xzfzr":0},{"createBy":1,"createTime":1511950054000,"gzbh":"6","id":6,"lxdh":"18753202303","modifyTime":1512008389000,"rymc":"各过各的","ryzc":1,"ssdwId":1,"ssdwlx":1,"ssdwmc":"高家台","status":1,"xzfzr":0},{"createBy":1,"createTime":1511950031000,"gzbh":"5","id":5,"lxdh":"18753202306","modifyBy":1,"modifyTime":1512108233000,"rymc":"C人员","ryzc":1,"ssdwId":1,"ssdwlx":1,"ssdwmc":"高家台","status":1,"xzfzr":0},{"createBy":1,"createTime":1511949774000,"gzbh":"4","id":4,"lxdh":"18600000000","modifyBy":1,"modifyTime":1512110216000,"rymc":"D人员","ryzc":1,"ssdwId":1,"ssdwlx":1,"ssdwmc":"高家台","status":1,"xzfzr":1}],"pn":1,"size":10,"total":4}
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
    public class CommunityStaffInfoMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1512005617000,"gzbh":"3","id":9,"lxdh":"18700000001","modifyBy":1,"modifyTime":1512374614000,"rymc":"哈哈哈哈","ryzc":1,"ssdwId":1,"ssdwlx":1,"ssdwmc":"高家台","status":1,"xzfzr":0},{"createBy":1,"createTime":1511950054000,"gzbh":"6","id":6,"lxdh":"18753202303","modifyTime":1512008389000,"rymc":"各过各的","ryzc":1,"ssdwId":1,"ssdwlx":1,"ssdwmc":"高家台","status":1,"xzfzr":0},{"createBy":1,"createTime":1511950031000,"gzbh":"5","id":5,"lxdh":"18753202306","modifyBy":1,"modifyTime":1512108233000,"rymc":"C人员","ryzc":1,"ssdwId":1,"ssdwlx":1,"ssdwmc":"高家台","status":1,"xzfzr":0},{"createBy":1,"createTime":1511949774000,"gzbh":"4","id":4,"lxdh":"18600000000","modifyBy":1,"modifyTime":1512110216000,"rymc":"D人员","ryzc":1,"ssdwId":1,"ssdwlx":1,"ssdwmc":"高家台","status":1,"xzfzr":1}]
         * pn : 1
         * size : 10
         * total : 4
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

        public  class ListBean implements Serializable {
            public ListBean(String lxdh, int ryzc, String rymc) {
                this.lxdh = lxdh;
                this.ryzc = ryzc;
                this.rymc = rymc;
            }

            /**
             * createBy : 1
             * createTime : 1512005617000
             * gzbh : 3
             * id : 9
             * lxdh : 18700000001
             * modifyBy : 1
             * modifyTime : 1512374614000
             * rymc : 哈哈哈哈
             * ryzc : 1
             * ssdwId : 1
             * ssdwlx : 1
             * ssdwmc : 高家台
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
