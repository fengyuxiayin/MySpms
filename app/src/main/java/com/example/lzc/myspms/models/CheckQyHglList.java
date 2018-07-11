package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/6/21.
 */

public class CheckQyHglList implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"kssj":1529424000000,"passCount":0,"passPercent":0,"rwmc":"社区任务","tags":"a26f6ad9b6504c95b3428c7f049136db","totalCount":3}]}
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
    public class CheckQyHglListMsg implements Serializable{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean implements Serializable{
            /**
             * kssj : 1529424000000
             * passCount : 0
             * passPercent : 0
             * rwmc : 社区任务
             * tags : a26f6ad9b6504c95b3428c7f049136db
             * totalCount : 3
             */

            private long kssj;
            private int passCount;
            private double passPercent;
            private String rwmc;
            private String tags;
            private int totalCount;

            public double getPassPercent() {
                return passPercent;
            }

            public void setPassPercent(double passPercent) {
                this.passPercent = passPercent;
            }

            public long getKssj() {
                return kssj;
            }

            public void setKssj(long kssj) {
                this.kssj = kssj;
            }

            public int getPassCount() {
                return passCount;
            }

            public void setPassCount(int passCount) {
                this.passCount = passCount;
            }

            public String getRwmc() {
                return rwmc;
            }

            public void setRwmc(String rwmc) {
                this.rwmc = rwmc;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
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
