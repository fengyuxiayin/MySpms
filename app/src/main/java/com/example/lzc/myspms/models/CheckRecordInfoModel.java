package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/12/4.
 */

public class CheckRecordInfoModel implements Serializable{

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"bzqsj":1490889600000,"jckssj":1512355097000,"qyId":1,"qymc":"龙华科道","ssdwmc":"a小组","szqsj":1483200000000},{"bzqsj":1514649600000,"jckssj":1512119056000,"qyId":1,"qymc":"龙华科道","ssdwmc":"a小组","szqsj":1506787200000}],"pn":1,"size":10,"total":2}
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
    public class CheckRecordMsgInfoModel implements Serializable{
        /**
         * list : [{"bzqsj":1490889600000,"jckssj":1512355097000,"qyId":1,"qymc":"龙华科道","ssdwmc":"a小组","szqsj":1483200000000},{"bzqsj":1514649600000,"jckssj":1512119056000,"qyId":1,"qymc":"龙华科道","ssdwmc":"a小组","szqsj":1506787200000}]
         * pn : 1
         * size : 10
         * total : 2
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

        public class ListBean {
            /**
             * bzqsj : 1490889600000
             * jckssj : 1512355097000
             * qyId : 1
             * qymc : 龙华科道
             * ssdwmc : a小组
             * szqsj : 1483200000000
             */

            private long bzqsj;
            private long jckssj;
            private int qyId;
            private String qymc;
            private String ssdwmc;
            private long szqsj;

            public long getBzqsj() {
                return bzqsj;
            }

            public void setBzqsj(long bzqsj) {
                this.bzqsj = bzqsj;
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
