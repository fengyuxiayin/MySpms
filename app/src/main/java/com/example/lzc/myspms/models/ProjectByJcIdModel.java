package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/4/25.
 */

public class ProjectByJcIdModel implements Serializable {


    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1524727803000,"id":32,"jcId":15,"jcjg":0,"jckssj":1524727803000,"jclx":1,"jcxmId":21,"qyId":15,"status":1},{"createBy":1,"createTime":1524727052000,"id":30,"jcId":15,"jcjg":0,"jckssj":1524727052000,"jclx":1,"jcxmId":23,"qyId":15,"status":1,"xmlx":359,"xmmc":"消防通道"},{"createBy":1,"createTime":1524727017000,"id":29,"jcId":15,"jcjg":0,"jckssj":1524727017000,"jclx":1,"jcxmId":27,"qyId":15,"status":1,"xmlx":56,"xmmc":"现场地面"},{"createBy":1,"createTime":1524726931000,"id":28,"jcId":15,"jcjg":0,"jckssj":1524726931000,"jclx":1,"jcxmId":25,"qyId":15,"status":1,"xmlx":56,"xmmc":"逃生通道"},{"createBy":1,"createTime":1524726557000,"id":27,"jcId":15,"jcjg":0,"jckssj":1524726557000,"jclx":1,"jcxmId":24,"qyId":15,"status":1,"xmlx":167,"xmmc":"烟雾报警器"},{"createBy":1,"createTime":1524726551000,"id":26,"jcId":15,"jcjg":0,"jckssj":1524726551000,"jclx":1,"jcxmId":20,"qyId":15,"status":1},{"createBy":1,"createTime":1524726545000,"id":25,"jcId":15,"jcjg":0,"jckssj":1524726545000,"jclx":1,"jcxmId":22,"qyId":15,"status":1,"xmlx":359,"xmmc":"配电箱"},{"createBy":1,"createTime":1524709275000,"id":22,"jcId":15,"jcjg":0,"jckssj":1524709275000,"jclx":2,"jcxmId":28,"qyId":15,"status":1,"xmlx":167,"xmmc":"消火栓"},{"createBy":1,"createTime":1524709275000,"id":23,"jcId":15,"jcjg":0,"jckssj":1524709275000,"jclx":2,"jcxmId":30,"qyId":15,"status":1,"xmlx":167,"xmmc":"药品检查"},{"createBy":1,"createTime":1524709275000,"id":24,"jcId":15,"jcjg":0,"jckssj":1524709275000,"jclx":2,"jcxmId":38,"qyId":15,"status":1,"xmlx":167,"xmmc":"环境监测"}],"pn":1,"size":10,"total":12}
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

    public class ProjectByJcIdMsgModel implements Serializable{


        /**
         * list : [{"createBy":1,"createTime":1524727803000,"id":32,"jcId":15,"jcjg":0,"jckssj":1524727803000,"jclx":1,"jcxmId":21,"qyId":15,"status":1},{"createBy":1,"createTime":1524727052000,"id":30,"jcId":15,"jcjg":0,"jckssj":1524727052000,"jclx":1,"jcxmId":23,"qyId":15,"status":1,"xmlx":359,"xmmc":"消防通道"},{"createBy":1,"createTime":1524727017000,"id":29,"jcId":15,"jcjg":0,"jckssj":1524727017000,"jclx":1,"jcxmId":27,"qyId":15,"status":1,"xmlx":56,"xmmc":"现场地面"},{"createBy":1,"createTime":1524726931000,"id":28,"jcId":15,"jcjg":0,"jckssj":1524726931000,"jclx":1,"jcxmId":25,"qyId":15,"status":1,"xmlx":56,"xmmc":"逃生通道"},{"createBy":1,"createTime":1524726557000,"id":27,"jcId":15,"jcjg":0,"jckssj":1524726557000,"jclx":1,"jcxmId":24,"qyId":15,"status":1,"xmlx":167,"xmmc":"烟雾报警器"},{"createBy":1,"createTime":1524726551000,"id":26,"jcId":15,"jcjg":0,"jckssj":1524726551000,"jclx":1,"jcxmId":20,"qyId":15,"status":1},{"createBy":1,"createTime":1524726545000,"id":25,"jcId":15,"jcjg":0,"jckssj":1524726545000,"jclx":1,"jcxmId":22,"qyId":15,"status":1,"xmlx":359,"xmmc":"配电箱"},{"createBy":1,"createTime":1524709275000,"id":22,"jcId":15,"jcjg":0,"jckssj":1524709275000,"jclx":2,"jcxmId":28,"qyId":15,"status":1,"xmlx":167,"xmmc":"消火栓"},{"createBy":1,"createTime":1524709275000,"id":23,"jcId":15,"jcjg":0,"jckssj":1524709275000,"jclx":2,"jcxmId":30,"qyId":15,"status":1,"xmlx":167,"xmmc":"药品检查"},{"createBy":1,"createTime":1524709275000,"id":24,"jcId":15,"jcjg":0,"jckssj":1524709275000,"jclx":2,"jcxmId":38,"qyId":15,"status":1,"xmlx":167,"xmmc":"环境监测"}]
         * pn : 1
         * size : 10
         * total : 12
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
             * createTime : 1524727803000
             * id : 32
             * jcId : 15
             * jcjg : 0
             * jckssj : 1524727803000
             * jclx : 1
             * jcxmId : 21
             * qyId : 15
             * status : 1
             * xmlx : 359
             * xmmc : 消防通道
             */

            private int createBy;
            private long createTime;
            private int id;
            private int jcId;
            private int jcjg;
            private long jckssj;
            private int jclx;
            private int jcxmId;
            private int qyId;
            private int status;
            private int xmlx;
            private String xmmc;

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

            public int getJcId() {
                return jcId;
            }

            public void setJcId(int jcId) {
                this.jcId = jcId;
            }

            public int getJcjg() {
                return jcjg;
            }

            public void setJcjg(int jcjg) {
                this.jcjg = jcjg;
            }

            public long getJckssj() {
                return jckssj;
            }

            public void setJckssj(long jckssj) {
                this.jckssj = jckssj;
            }

            public int getJclx() {
                return jclx;
            }

            public void setJclx(int jclx) {
                this.jclx = jclx;
            }

            public int getJcxmId() {
                return jcxmId;
            }

            public void setJcxmId(int jcxmId) {
                this.jcxmId = jcxmId;
            }

            public int getQyId() {
                return qyId;
            }

            public void setQyId(int qyId) {
                this.qyId = qyId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getXmlx() {
                return xmlx;
            }

            public void setXmlx(int xmlx) {
                this.xmlx = xmlx;
            }

            public String getXmmc() {
                return xmmc;
            }

            public void setXmmc(String xmmc) {
                this.xmmc = xmmc;
            }
        }
    }
}
