package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/8/24.
 */

public class UnsafeFindModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"bz":"阿达","createBy":117,"createTime":1535004135000,"id":95,"modifyBy":117,"modifyTime":1535020415000,"status":1,"wxyId":65,"xh":1,"yhdmc":"大萨达","yhdtp":"/source/project/20188/20180823140207_899.png","yhdxq":"大"}],"pn":1,"size":100,"total":1}
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
    public static class UnsafeFindMsgModel implements Serializable{

        /**
         * list : [{"bz":"阿达","createBy":117,"createTime":1535004135000,"id":95,"modifyBy":117,"modifyTime":1535020415000,"status":1,"wxyId":65,"xh":1,"yhdmc":"大萨达","yhdtp":"/source/project/20188/20180823140207_899.png","yhdxq":"大"}]
         * pn : 1
         * size : 100
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
        public  ListBean getInstance(){
            return  new ListBean("","","","","");
        }
        public  class ListBean implements Serializable{
            public ListBean(String xh, String bz, String yhdmc, String yhdtp, String yhdxq) {
                this.xh = xh;
                this.bz = bz;
                this.yhdmc = yhdmc;
                this.yhdtp = yhdtp;
                this.yhdxq = yhdxq;
            }

            public ListBean() {
            }

            /**
             * bz : 阿达
             * createBy : 117
             * createTime : 1535004135000
             * id : 95
             * modifyBy : 117
             * modifyTime : 1535020415000
             * status : 1
             * wxyId : 65
             * xh : 1
             * yhdmc : 大萨达
             * yhdtp : /source/project/20188/20180823140207_899.png
             * yhdxq : 大
             */

            private String bz;
            private Integer createBy;
            private Long createTime;
            private Integer id;
            private Integer modifyBy;
            private Long modifyTime;
            private Integer status;
            private Integer wxyId;
            private String xh;
            private String yhdmc;
            private String yhdtp;
            private String yhdxq;

            public String getBz() {
                return bz;
            }

            public void setBz(String bz) {
                this.bz = bz;
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

            public int getWxyId() {
                return wxyId;
            }

            public void setWxyId(int wxyId) {
                this.wxyId = wxyId;
            }

            public String getXh() {
                return xh;
            }

            public void setXh(String xh) {
                this.xh = xh;
            }

            public String getYhdmc() {
                return yhdmc;
            }

            public void setYhdmc(String yhdmc) {
                this.yhdmc = yhdmc;
            }

            public String getYhdtp() {
                return yhdtp;
            }

            public void setYhdtp(String yhdtp) {
                this.yhdtp = yhdtp;
            }

            public String getYhdxq() {
                return yhdxq;
            }

            public void setYhdxq(String yhdxq) {
                this.yhdxq = yhdxq;
            }
        }
    }
}
