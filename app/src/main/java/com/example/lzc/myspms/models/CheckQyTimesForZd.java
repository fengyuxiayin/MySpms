package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/6/17.
 */

public class CheckQyTimesForZd implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"jccs":2,"zddj":"A"}]}
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
    public class CheckQyTimesForZdMsg implements Serializable{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean implements Serializable{
            /**
             * jccs : 2
             * zddj : A
             */

            private int jccs;
            private String zddj;

            public int getJccs() {
                return jccs;
            }

            public void setJccs(int jccs) {
                this.jccs = jccs;
            }

            public String getZddj() {
                return zddj;
            }

            public void setZddj(String zddj) {
                this.zddj = zddj;
            }
        }
    }
}
