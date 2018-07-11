package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/3/12.
 */

public class FindProjectPointsModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"jcjg":1,"jckssj":1520584901000,"jcry":"admin","jcxId":3,"points":[{"createBy":1,"createTime":1520584916000,"id":63,"jcjg":1,"jctp":"","jcxId":3,"jcyhId":125,"modifyBy":1,"modifyTime":1520585528000,"status":1,"yhly":1,"yhms":"是否制订职业卫生操作规程"}],"qyId":12,"qyMc":"测试添加2018","xmId":180,"xmmc":"1234567898"}
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
    public class FindProjectPointsMsgModel implements Serializable{

        /**
         * jcjg : 1
         * jckssj : 1520584901000
         * jcry : admin
         * jcxId : 3
         * points : [{"createBy":1,"createTime":1520584916000,"id":63,"jcjg":1,"jctp":"","jcxId":3,"jcyhId":125,"modifyBy":1,"modifyTime":1520585528000,"status":1,"yhly":1,"yhms":"是否制订职业卫生操作规程"}]
         * qyId : 12
         * qyMc : 测试添加2018
         * xmId : 180
         * xmmc : 1234567898
         */

        private String jcjg;
        private long jckssj;
        private String jcry;
        private int jcxId;
        private int qyId;
        private String qyMc;
        private int xmId;
        private String xmmc;
        private List<PointsBean> points;

        public String getJcjg() {
            return jcjg;
        }

        public void setJcjg(String jcjg) {
            this.jcjg = jcjg;
        }

        public long getJckssj() {
            return jckssj;
        }

        public void setJckssj(long jckssj) {
            this.jckssj = jckssj;
        }

        public String getJcry() {
            return jcry;
        }

        public void setJcry(String jcry) {
            this.jcry = jcry;
        }

        public int getJcxId() {
            return jcxId;
        }

        public void setJcxId(int jcxId) {
            this.jcxId = jcxId;
        }

        public int getQyId() {
            return qyId;
        }

        public void setQyId(int qyId) {
            this.qyId = qyId;
        }

        public String getQyMc() {
            return qyMc;
        }

        public void setQyMc(String qyMc) {
            this.qyMc = qyMc;
        }

        public int getXmId() {
            return xmId;
        }

        public void setXmId(int xmId) {
            this.xmId = xmId;
        }

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public List<PointsBean> getPoints() {
            return points;
        }

        public void setPoints(List<PointsBean> points) {
            this.points = points;
        }

        public  class PointsBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1520584916000
             * id : 63
             * jcjg : 1
             * jctp :
             * jcxId : 3
             * jcyhId : 125
             * modifyBy : 1
             * modifyTime : 1520585528000
             * status : 1
             * yhly : 1
             * yhms : 是否制订职业卫生操作规程
             */

            private int createBy;
            private long createTime;
            private int id;
            private String jcjg;
            private String jctp;
            private int jcxId;
            private int jcyhId;
            private int modifyBy;
            private long modifyTime;
            private int status;
            private int yhly;
            private String yhms;

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

            public String getJcjg() {
                return jcjg;
            }

            public void setJcjg(String jcjg) {
                this.jcjg = jcjg;
            }

            public String getJctp() {
                return jctp;
            }

            public void setJctp(String jctp) {
                this.jctp = jctp;
            }

            public int getJcxId() {
                return jcxId;
            }

            public void setJcxId(int jcxId) {
                this.jcxId = jcxId;
            }

            public int getJcyhId() {
                return jcyhId;
            }

            public void setJcyhId(int jcyhId) {
                this.jcyhId = jcyhId;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getYhly() {
                return yhly;
            }

            public void setYhly(int yhly) {
                this.yhly = yhly;
            }

            public String getYhms() {
                return yhms;
            }

            public void setYhms(String yhms) {
                this.yhms = yhms;
            }
        }
    }
}
