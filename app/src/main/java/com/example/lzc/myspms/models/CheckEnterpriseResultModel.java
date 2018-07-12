package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/6/17.
 */

public class CheckEnterpriseResultModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"jcdwId":1,"jcdwlx":2,"jzsj":1533657600000,"kssj":1527782400000,"passPercent":75}]}
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
    public class CheckEnterpriseResultMsgModel implements Serializable{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean implements Serializable{
            /**
             * jcdwId : 1
             * jcdwlx : 2
             * jzsj : 1533657600000
             * kssj : 1527782400000
             * passPercent : 75
             */

            private int jcdwId;
            private int jcdwlx;
            private long jzsj;
            private long jckssj;
            private long jcjssj;
            private double passPercent;

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

            public long getJzsj() {
                return jzsj;
            }

            public void setJzsj(long jzsj) {
                this.jzsj = jzsj;
            }

            public long getJckssj() {
                return jckssj;
            }

            public void setJckssj(long jckssj) {
                this.jckssj = jckssj;
            }

            public long getJcjssj() {
                return jcjssj;
            }

            public void setJcjssj(long jcjssj) {
                this.jcjssj = jcjssj;
            }

            public double getPassPercent() {
                return passPercent;
            }

            public void setPassPercent(double passPercent) {
                this.passPercent = passPercent;
            }
        }
    }
}
