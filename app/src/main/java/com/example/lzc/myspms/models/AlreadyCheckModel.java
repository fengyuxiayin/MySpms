package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/2/9.
 */

public class AlreadyCheckModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1513926179000,"id":1,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513926179000,"jclx":1,"qyId":2,"qymc":"第二有限责任公司","sfyq":0,"status":1,"zqjlId":2},{"createBy":1,"createTime":1513926232000,"id":2,"jcdwId":1,"jcdwlx":2,"jcjg":1,"jcjssj":1517984254000,"jckssj":1513926232000,"jclx":1,"modifyBy":1,"modifyTime":1517984254000,"qyId":1,"qymc":"第一有限责任公司","sfyq":0,"status":1,"zqjlId":1},{"createBy":1,"createTime":1513926291000,"id":3,"jcdwId":1,"jcdwlx":2,"jcjg":1,"jcjssj":1513926306000,"jckssj":1513926291000,"jclx":1,"modifyBy":1,"modifyTime":1513926306000,"qyId":3,"qymc":"第三有限责任公司","sfyq":0,"status":1,"zqjlId":3},{"createBy":1,"createTime":1513927036000,"id":4,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513927036000,"jclx":1,"qyId":7,"qymc":"第七有限责任公司","sfyq":0,"status":1,"zqjlId":6},{"createBy":1,"createTime":1513932222000,"id":5,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513932222000,"jclx":1,"qyId":4,"qymc":"第四有限责任公司","sfyq":0,"status":1,"zqjlId":4},{"createBy":1,"createTime":1513932230000,"id":6,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513932230000,"jclx":2,"qyId":4,"qymc":"第四有限责任公司","sfyq":0,"status":1,"zgqx":1514044799000,"zqjlId":10},{"createBy":1,"createTime":1513932297000,"id":7,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513932297000,"jclx":1,"qyId":6,"qymc":"第六有限责任公司","sfyq":0,"status":1,"zqjlId":5},{"createBy":1,"createTime":1513932305000,"id":8,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513932305000,"jclx":2,"qyId":6,"qymc":"第六有限责任公司","sfyq":0,"status":1,"zgqx":1514044799000,"zqjlId":11},{"createBy":1,"createTime":1515566114000,"id":9,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1515566114000,"jclx":2,"qyId":6,"qymc":"第六有限责任公司","sfyq":0,"status":1,"zgqx":1516377599000,"zqjlId":12},{"createBy":1,"createTime":1515721599000,"id":10,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1515721599000,"jclx":2,"qyId":6,"qymc":"第六有限责任公司","sfyq":0,"status":1,"zgqx":1516982399000,"zqjlId":13}],"pn":1,"size":10,"total":14}
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
    public class AlreadyCheckMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1513926179000,"id":1,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513926179000,"jclx":1,"qyId":2,"qymc":"第二有限责任公司","sfyq":0,"status":1,"zqjlId":2},{"createBy":1,"createTime":1513926232000,"id":2,"jcdwId":1,"jcdwlx":2,"jcjg":1,"jcjssj":1517984254000,"jckssj":1513926232000,"jclx":1,"modifyBy":1,"modifyTime":1517984254000,"qyId":1,"qymc":"第一有限责任公司","sfyq":0,"status":1,"zqjlId":1},{"createBy":1,"createTime":1513926291000,"id":3,"jcdwId":1,"jcdwlx":2,"jcjg":1,"jcjssj":1513926306000,"jckssj":1513926291000,"jclx":1,"modifyBy":1,"modifyTime":1513926306000,"qyId":3,"qymc":"第三有限责任公司","sfyq":0,"status":1,"zqjlId":3},{"createBy":1,"createTime":1513927036000,"id":4,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513927036000,"jclx":1,"qyId":7,"qymc":"第七有限责任公司","sfyq":0,"status":1,"zqjlId":6},{"createBy":1,"createTime":1513932222000,"id":5,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513932222000,"jclx":1,"qyId":4,"qymc":"第四有限责任公司","sfyq":0,"status":1,"zqjlId":4},{"createBy":1,"createTime":1513932230000,"id":6,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513932230000,"jclx":2,"qyId":4,"qymc":"第四有限责任公司","sfyq":0,"status":1,"zgqx":1514044799000,"zqjlId":10},{"createBy":1,"createTime":1513932297000,"id":7,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513932297000,"jclx":1,"qyId":6,"qymc":"第六有限责任公司","sfyq":0,"status":1,"zqjlId":5},{"createBy":1,"createTime":1513932305000,"id":8,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1513932305000,"jclx":2,"qyId":6,"qymc":"第六有限责任公司","sfyq":0,"status":1,"zgqx":1514044799000,"zqjlId":11},{"createBy":1,"createTime":1515566114000,"id":9,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1515566114000,"jclx":2,"qyId":6,"qymc":"第六有限责任公司","sfyq":0,"status":1,"zgqx":1516377599000,"zqjlId":12},{"createBy":1,"createTime":1515721599000,"id":10,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1515721599000,"jclx":2,"qyId":6,"qymc":"第六有限责任公司","sfyq":0,"status":1,"zgqx":1516982399000,"zqjlId":13}]
         * pn : 1
         * size : 10
         * total : 14
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
             * createBy : 1
             * createTime : 1513926179000
             * id : 1
             * jcdwId : 1
             * jcdwlx : 2
             * jcjg : 0
             * jckssj : 1513926179000
             * jclx : 1
             * qyId : 2
             * qymc : 第二有限责任公司
             * sfyq : 0
             * status : 1
             * zqjlId : 2
             * jcjssj : 1517984254000
             * modifyBy : 1
             * modifyTime : 1517984254000
             * zgqx : 1514044799000
             */

            private int createBy;
            private long createTime;
            private int id;
            private int jcdwId;
            private int jcdwlx;
            private int jcjg;
            private long jckssj;
            private int jclx;
            private int qyId;
            private String qymc;
            private int sfyq;
            private int status;
            private int zqjlId;
            private long jcjssj;
            private int modifyBy;
            private long modifyTime;
            private long zgqx;

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

            public int getSfyq() {
                return sfyq;
            }

            public void setSfyq(int sfyq) {
                this.sfyq = sfyq;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getZqjlId() {
                return zqjlId;
            }

            public void setZqjlId(int zqjlId) {
                this.zqjlId = zqjlId;
            }

            public long getJcjssj() {
                return jcjssj;
            }

            public void setJcjssj(long jcjssj) {
                this.jcjssj = jcjssj;
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

            public long getZgqx() {
                return zgqx;
            }

            public void setZgqx(long zgqx) {
                this.zgqx = zgqx;
            }
        }
    }
}
