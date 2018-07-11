package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/6/18.
 */

public class TeamFindByIdModel implements Serializable{

    /**
     * code : 1
     * data : true
     * msg : {"bcl":[{"createBy":1,"createTime":1523270439000,"id":6,"modifyTime":1526986520000,"pqId":5,"sqdz":"后桃林街道","sqfwzb":"[{\"lng\":120.390354,\"lat\":36.281866},{\"lng\":120.392242,\"lat\":36.266782},{\"lng\":120.415417,\"lat\":36.258477}]","sqmc":"城阳南片区社区中心","sqmj":1000,"sqrk":1300,"status":1,"xzId":1}],"bsl":[{"createBy":1,"createTime":1523270067000,"gzbh":"A002","id":2,"lxdh":"18669739880","modifyBy":1,"modifyTime":1526981579000,"rymc":"孙兆波","ryzc":1,"ssdwId":1,"ssdwlx":2,"status":1,"xzfzr":0},{"createBy":1,"createTime":1528967262000,"gzbh":"123123","id":489,"lxdh":"15050505050","modifyBy":1,"modifyTime":1528969920000,"rymc":"123123","ryzc":2,"ssdwId":1,"ssdwlx":2,"status":1,"xzfzr":1}],"createBy":1,"createTime":1523269903000,"id":1,"modifyBy":76,"modifyTime":1526986520000,"pqId":1,"status":1,"wsbh":"1","xzmc":"夏庄街道安监小组"}
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
    public class TeamFindByIdMsgModel implements Serializable{

        /**
         * bcl : [{"createBy":1,"createTime":1523270439000,"id":6,"modifyTime":1526986520000,"pqId":5,"sqdz":"后桃林街道","sqfwzb":"[{\"lng\":120.390354,\"lat\":36.281866},{\"lng\":120.392242,\"lat\":36.266782},{\"lng\":120.415417,\"lat\":36.258477}]","sqmc":"城阳南片区社区中心","sqmj":1000,"sqrk":1300,"status":1,"xzId":1}]
         * bsl : [{"createBy":1,"createTime":1523270067000,"gzbh":"A002","id":2,"lxdh":"18669739880","modifyBy":1,"modifyTime":1526981579000,"rymc":"孙兆波","ryzc":1,"ssdwId":1,"ssdwlx":2,"status":1,"xzfzr":0},{"createBy":1,"createTime":1528967262000,"gzbh":"123123","id":489,"lxdh":"15050505050","modifyBy":1,"modifyTime":1528969920000,"rymc":"123123","ryzc":2,"ssdwId":1,"ssdwlx":2,"status":1,"xzfzr":1}]
         * createBy : 1
         * createTime : 1523269903000
         * id : 1
         * modifyBy : 76
         * modifyTime : 1526986520000
         * pqId : 1
         * status : 1
         * wsbh : 1
         * xzmc : 夏庄街道安监小组
         */

        private int createBy;
        private long createTime;
        private int id;
        private int modifyBy;
        private long modifyTime;
        private int pqId;
        private int status;
        private String wsbh;
        private String xzmc;
        private List<BclBean> bcl;
        private List<BslBean> bsl;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getWsbh() {
            return wsbh;
        }

        public void setWsbh(String wsbh) {
            this.wsbh = wsbh;
        }

        public String getXzmc() {
            return xzmc;
        }

        public void setXzmc(String xzmc) {
            this.xzmc = xzmc;
        }

        public List<BclBean> getBcl() {
            return bcl;
        }

        public void setBcl(List<BclBean> bcl) {
            this.bcl = bcl;
        }

        public List<BslBean> getBsl() {
            return bsl;
        }

        public void setBsl(List<BslBean> bsl) {
            this.bsl = bsl;
        }

        public class BclBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1523270439000
             * id : 6
             * modifyTime : 1526986520000
             * pqId : 5
             * sqdz : 后桃林街道
             * sqfwzb : [{"lng":120.390354,"lat":36.281866},{"lng":120.392242,"lat":36.266782},{"lng":120.415417,"lat":36.258477}]
             * sqmc : 城阳南片区社区中心
             * sqmj : 1000
             * sqrk : 1300
             * status : 1
             * xzId : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private long modifyTime;
            private int pqId;
            private String sqdz;
            private String sqfwzb;
            private String sqmc;
            private int sqmj;
            private int sqrk;
            private int status;
            private int xzId;

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

            public int getPqId() {
                return pqId;
            }

            public void setPqId(int pqId) {
                this.pqId = pqId;
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

            public int getXzId() {
                return xzId;
            }

            public void setXzId(int xzId) {
                this.xzId = xzId;
            }
        }

        public class BslBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1523270067000
             * gzbh : A002
             * id : 2
             * lxdh : 18669739880
             * modifyBy : 1
             * modifyTime : 1526981579000
             * rymc : 孙兆波
             * ryzc : 1
             * ssdwId : 1
             * ssdwlx : 2
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
