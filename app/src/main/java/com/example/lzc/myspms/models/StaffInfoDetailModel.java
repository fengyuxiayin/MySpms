package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2017/12/15.
 */

public class StaffInfoDetailModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"basecommunity":{"createBy":1,"createTime":1511926232000,"id":1,"modifyBy":1,"modifyTime":1513231499000,"pqId":1,"qysl":6,"sqdz":"城阳区高家台社区","sqfwzb":"[{\"lng\":120.462152,\"lat\":36.271037},{\"lng\":120.454899,\"lat\":36.271487},{\"lng\":120.454126,\"lat\":36.266712},{\"lng\":120.458761,\"lat\":36.26429},{\"lng\":120.461508,\"lat\":36.266436}]","sqmc":"高家台","sqmj":10000,"sqrk":100,"status":1},"createBy":1,"createTime":1512005617000,"gzbh":"3","id":9,"lxdh":"18700000001","modifyBy":1,"modifyTime":1512374614000,"rymc":"哈哈哈哈","ryzc":1,"ssdwId":1,"ssdwlx":1,"status":1,"xzfzr":0}
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
    public class StaffInfoDetailMsgModel implements Serializable{

        /**
         * basecommunity : {"createBy":1,"createTime":1511926232000,"id":1,"modifyBy":1,"modifyTime":1513231499000,"pqId":1,"qysl":6,"sqdz":"城阳区高家台社区","sqfwzb":"[{\"lng\":120.462152,\"lat\":36.271037},{\"lng\":120.454899,\"lat\":36.271487},{\"lng\":120.454126,\"lat\":36.266712},{\"lng\":120.458761,\"lat\":36.26429},{\"lng\":120.461508,\"lat\":36.266436}]","sqmc":"高家台","sqmj":10000,"sqrk":100,"status":1}
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
         * status : 1
         * xzfzr : 0
         */

        private BasecommunityBean basecommunity;
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
        private int status;
        private int xzfzr;

        public BasecommunityBean getBasecommunity() {
            return basecommunity;
        }

        public void setBasecommunity(BasecommunityBean basecommunity) {
            this.basecommunity = basecommunity;
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

        public  class BasecommunityBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1511926232000
             * id : 1
             * modifyBy : 1
             * modifyTime : 1513231499000
             * pqId : 1
             * qysl : 6
             * sqdz : 城阳区高家台社区
             * sqfwzb : [{"lng":120.462152,"lat":36.271037},{"lng":120.454899,"lat":36.271487},{"lng":120.454126,"lat":36.266712},{"lng":120.458761,"lat":36.26429},{"lng":120.461508,"lat":36.266436}]
             * sqmc : 高家台
             * sqmj : 10000
             * sqrk : 100
             * status : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private int modifyBy;
            private long modifyTime;
            private int pqId;
            private int qysl;
            private String sqdz;
            private String sqfwzb;
            private String sqmc;
            private int sqmj;
            private int sqrk;
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

            public int getPqId() {
                return pqId;
            }

            public void setPqId(int pqId) {
                this.pqId = pqId;
            }

            public int getQysl() {
                return qysl;
            }

            public void setQysl(int qysl) {
                this.qysl = qysl;
            }

            public String getSqdz() {
                return sqdz;
            }

            public void setSqdz(String sqdz) {
                this.sqdz = sqdz;
            }

            public String getSqfwzb() {
                return sqfwzb;
            }

            public void setSqfwzb(String sqfwzb) {
                this.sqfwzb = sqfwzb;
            }

            public String getSqmc() {
                return sqmc;
            }

            public void setSqmc(String sqmc) {
                this.sqmc = sqmc;
            }

            public int getSqmj() {
                return sqmj;
            }

            public void setSqmj(int sqmj) {
                this.sqmj = sqmj;
            }

            public int getSqrk() {
                return sqrk;
            }

            public void setSqrk(int sqrk) {
                this.sqrk = sqrk;
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
