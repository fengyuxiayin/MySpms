package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/6/19.
 */

public class PointInfoModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1529290523000,"id":4,"jcjg":0,"jcxId":114,"status":1,"yhdId":1,"yhdJson":"{\"bz\":\"aaa\",\"createBy\":1,\"createTime\":1525570193000,\"id\":1,\"modifyTime\":1525570193000,\"status\":1,\"wxyId\":1,\"xh\":111,\"yhdmc\":\"隐患点1\",\"yhdtp\":\"/source/project/20185/20180506111200_555.jpg\",\"yhdxq\":\"隐患点1111\"}","yhdmc":"隐患点1"},{"createBy":1,"createTime":1529290523000,"id":5,"jcjg":0,"jcxId":114,"status":1,"yhdId":2,"yhdJson":"{\"bz\":\"bbb\",\"createBy\":1,\"createTime\":1525682551000,\"id\":2,\"modifyTime\":1525682551000,\"status\":1,\"wxyId\":1,\"xh\":222,\"yhdmc\":\"隐患点2\",\"yhdtp\":\"/source/project/20185/20180511155353_238.jpg\",\"yhdxq\":\"隐患点2222\"}","yhdmc":"隐患点2"}]}
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
    public class PointInfoMsgModel implements Serializable{

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public class ListBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1529290523000
             * id : 4
             * jcjg : 0
             * jcxId : 114
             * status : 1
             * yhdId : 1
             * yhdJson : {"bz":"aaa","createBy":1,"createTime":1525570193000,"id":1,"modifyTime":1525570193000,"status":1,"wxyId":1,"xh":111,"yhdmc":"隐患点1","yhdtp":"/source/project/20185/20180506111200_555.jpg","yhdxq":"隐患点1111"}
             * yhdmc : 隐患点1
             */

            private int createBy;
            private long createTime;
            private int id;
            private int jcjg;
            private int jcxId;
            private int status;
            private int yhdId;
            private String yhdJson;
            private String yhdmc;
            private String yhms;
            private String jctp;

            public String getYhms() {
                return yhms;
            }

            public void setYhms(String yhms) {
                this.yhms = yhms;
            }

            public String getJctp() {
                return jctp;
            }

            public void setJctp(String jctp) {
                this.jctp = jctp;
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

            public int getJcjg() {
                return jcjg;
            }

            public void setJcjg(int jcjg) {
                this.jcjg = jcjg;
            }

            public int getJcxId() {
                return jcxId;
            }

            public void setJcxId(int jcxId) {
                this.jcxId = jcxId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getYhdId() {
                return yhdId;
            }

            public void setYhdId(int yhdId) {
                this.yhdId = yhdId;
            }

            public String getYhdJson() {
                return yhdJson;
            }

            public void setYhdJson(String yhdJson) {
                this.yhdJson = yhdJson;
            }

            public String getYhdmc() {
                return yhdmc;
            }

            public void setYhdmc(String yhdmc) {
                this.yhdmc = yhdmc;
            }
        }
    }
}
