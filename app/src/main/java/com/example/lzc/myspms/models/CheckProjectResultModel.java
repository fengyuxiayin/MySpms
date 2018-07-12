package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/6/17.
 */

public class CheckProjectResultModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"jzsj":1533657600000,"kssj":1527782400000,"passCount":41,"passPercent":93.18,"totalCount":44}]}
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
    public class CheckProjectResultMsgModel implements Serializable{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean implements Serializable{
            /**
             * jzsj : 1533657600000
             * kssj : 1527782400000
             * passCount : 41
             * passPercent : 93.18
             * totalCount : 44
             */

            private long jcjzsj;
            private long jckssj;
            private int passCount;
            private double passPercent;
            private int totalCount;

            public long getJcjzsj() {
                return jcjzsj;
            }

            public void setJcjzsj(long jcjzsj) {
                this.jcjzsj = jcjzsj;
            }

            public long getJckssj() {
                return jckssj;
            }

            public void setJckssj(long jckssj) {
                this.jckssj = jckssj;
            }

            public int getPassCount() {
                return passCount;
            }

            public void setPassCount(int passCount) {
                this.passCount = passCount;
            }

            public double getPassPercent() {
                return passPercent;
            }

            public void setPassPercent(double passPercent) {
                this.passPercent = passPercent;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }
        }
    }
}
