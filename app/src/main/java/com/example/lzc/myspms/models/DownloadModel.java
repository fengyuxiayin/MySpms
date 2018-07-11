package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/4/18.
 */

public class DownloadModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1523601037000,"id":4,"modifyTime":1523601037000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180413_14_ZGTZS.doc","wsmc":"20180413_14_ZGTZS.doc"},{"createBy":1,"createTime":1523609992000,"id":5,"modifyTime":1523609992000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180413_14_ZGTZS.doc","wsmc":"20180413_14_ZGTZS.doc"},{"createBy":1,"createTime":1523610702000,"id":6,"modifyTime":1523610702000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180413_14_ZGTZS.doc","wsmc":"20180413_14_ZGTZS.doc"},{"createBy":1,"createTime":1523929582000,"id":7,"modifyTime":1523929582000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180417_13_ZGTZS.doc","wsmc":"20180417_13_ZGTZS.doc"},{"createBy":1,"createTime":1523931057000,"id":8,"modifyTime":1523931057000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180417_14_ZGTZS.doc","wsmc":"20180417_14_ZGTZS.doc"},{"createBy":1,"createTime":1523931892000,"id":9,"modifyTime":1523931892000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180417_13_ZGTZS.doc","wsmc":"20180417_13_ZGTZS.doc"}],"pn":1,"size":10,"total":6}
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
    public class DownloadMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1523601037000,"id":4,"modifyTime":1523601037000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180413_14_ZGTZS.doc","wsmc":"20180413_14_ZGTZS.doc"},{"createBy":1,"createTime":1523609992000,"id":5,"modifyTime":1523609992000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180413_14_ZGTZS.doc","wsmc":"20180413_14_ZGTZS.doc"},{"createBy":1,"createTime":1523610702000,"id":6,"modifyTime":1523610702000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180413_14_ZGTZS.doc","wsmc":"20180413_14_ZGTZS.doc"},{"createBy":1,"createTime":1523929582000,"id":7,"modifyTime":1523929582000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180417_13_ZGTZS.doc","wsmc":"20180417_13_ZGTZS.doc"},{"createBy":1,"createTime":1523931057000,"id":8,"modifyTime":1523931057000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180417_14_ZGTZS.doc","wsmc":"20180417_14_ZGTZS.doc"},{"createBy":1,"createTime":1523931892000,"id":9,"modifyTime":1523931892000,"status":1,"wsbdlj":"http://120.25.251.167:8088/hfs/source/document/20184/20180417_13_ZGTZS.doc","wsmc":"20180417_13_ZGTZS.doc"}]
         * pn : 1
         * size : 10
         * total : 6
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

        public class ListBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1523601037000
             * id : 4
             * modifyTime : 1523601037000
             * status : 1
             * wsbdlj : http://120.25.251.167:8088/hfs/source/document/20184/20180413_14_ZGTZS.doc
             * wsmc : 20180413_14_ZGTZS.doc
             */

            private int createBy;
            private long createTime;
            private int id;
            private long modifyTime;
            private int status;
            private String wsbdlj;
            private String wsmc;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getWsbdlj() {
                return wsbdlj;
            }

            public void setWsbdlj(String wsbdlj) {
                this.wsbdlj = wsbdlj;
            }

            public String getWsmc() {
                return wsmc;
            }

            public void setWsmc(String wsmc) {
                this.wsmc = wsmc;
            }
        }
    }
}
