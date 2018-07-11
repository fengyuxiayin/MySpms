package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/3/9.
 */

public class QueryCheckRecordModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"bzqsj":1520611199000,"id":36,"jckssj":1520501744000,"qyId":12,"qymc":"测试添加2018","ssdwmc":"a小组","szqsj":1519833600000}],"pn":1,"size":10,"total":1}
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
    public class QueryCheckRecordMsgModel implements Serializable{

        /**
         * list : [{"bzqsj":1520611199000,"id":36,"jckssj":1520501744000,"qyId":12,"qymc":"测试添加2018","ssdwmc":"a小组","szqsj":1519833600000}]
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

        public  class ListBean implements Serializable{
            /**
             * bzqsj : 1520611199000
             * jcrId : 10
             * id : 36
             * jckssj : 1520501744000
             * qyId : 12
             * qymc : 测试添加2018
             * ssdwmc : a小组
             * szqsj : 1519833600000
             */

            private long bzqsj;
            private int jcrId;
            private int id;
            private long jckssj;
            private int qyId;
            private String qymc;
            private String ssdwmc;
            private long szqsj;

            public int getJcrId() {
                return jcrId;
            }

            public void setJcrId(int jcrId) {
                this.jcrId = jcrId;
            }

            public long getBzqsj() {
                return bzqsj;
            }

            public void setBzqsj(long bzqsj) {
                this.bzqsj = bzqsj;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getJckssj() {
                return jckssj;
            }

            public void setJckssj(long jckssj) {
                this.jckssj = jckssj;
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

            public String getSsdwmc() {
                return ssdwmc;
            }

            public void setSsdwmc(String ssdwmc) {
                this.ssdwmc = ssdwmc;
            }

            public long getSzqsj() {
                return szqsj;
            }

            public void setSzqsj(long szqsj) {
                this.szqsj = szqsj;
            }
        }
    }
}
