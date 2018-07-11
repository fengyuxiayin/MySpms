package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/12/6.
 */

public class ProjectDataModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"bxrq":null,"countermeasure":null,"createBy":1,"createTime":1512524587000,"eddy":null,"ejys":166,"glxh":null,"id":1,"jcId":null,"jcjg":null,"jckssj":null,"jclx":null,"jcxId":null,"jdrq":1512524568000,"modifyBy":null,"modifyTime":1512524587000,"monitor":null,"qyId":5,"qyrq":1512524568000,"qywzjd":null,"qywzwd":null,"result":null,"says":null,"sbjz":null,"sbpp":"格力","sbtp":"http://120.25.251.167:8088/hfs/source/project/201712/20171206094025_782.jpg","sbxh":"大中小型","scrq":null,"sjys":null,"ssbm":"开发","status":1,"sydd":"办公室","unsafe":null,"xmbm":"001","xmlx":359,"xmmc":"六日测试新","xmtm":null}],"pn":1,"size":10,"total":1}
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
    public class ProjectDataMsgModel implements  Serializable{

        /**
         * list : [{"bxrq":null,"countermeasure":null,"createBy":1,"createTime":1512524587000,"eddy":null,"ejys":166,"glxh":null,"id":1,"jcId":null,"jcjg":null,"jckssj":null,"jclx":null,"jcxId":null,"jdrq":1512524568000,"modifyBy":null,"modifyTime":1512524587000,"monitor":null,"qyId":5,"qyrq":1512524568000,"qywzjd":null,"qywzwd":null,"result":null,"says":null,"sbjz":null,"sbpp":"格力","sbtp":"http://120.25.251.167:8088/hfs/source/project/201712/20171206094025_782.jpg","sbxh":"大中小型","scrq":null,"sjys":null,"ssbm":"开发","status":1,"sydd":"办公室","unsafe":null,"xmbm":"001","xmlx":359,"xmmc":"六日测试新","xmtm":null}]
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

        public class ListBean {
            /**
             * bxrq : null
             * countermeasure : null
             * createBy : 1
             * createTime : 1512524587000
             * eddy : null
             * ejys : 166
             * glxh : null
             * id : 1 检查项目id
             * jcId : null
             * jcjg : null
             * jckssj : null
             * jclx : null
             * jcxId : null
             * jdrq : 1512524568000
             * modifyBy : null
             * modifyTime : 1512524587000
             * monitor : null
             * qyId : 5
             * qyrq : 1512524568000
             * qywzjd : null
             * qywzwd : null
             * result : null
             * says : null
             * sbjz : null
             * sbpp : 格力
             * sbtp : http://120.25.251.167:8088/hfs/source/project/201712/20171206094025_782.jpg
             * sbxh : 大中小型
             * scrq : null
             * sjys : null
             * ssbm : 开发
             * status : 1
             * sydd : 办公室
             * unsafe : null
             * xmbm : 001
             * xmlx : 359
             * xmmc : 六日测试新
             * xmtm : null
             */

            private Object bxrq;
            private Object countermeasure;
            private int createBy;
            private long createTime;
            private Object eddy;
            private int ejys;
            private Object glxh;
            private int id;
            private int jcId;
            private String jcjg;
            private Object jckssj;
            private Object jclx;
            private int jcxId;
            private long jdrq;
            private Object modifyBy;
            private long modifyTime;
            private Object monitor;
            private int qyId;
            private long qyrq;
            private Object qywzjd;
            private Object qywzwd;
            private Object result;
            private Object says;
            private Object sbjz;
            private String sbpp;
            private String sbtp;
            private String sbxh;
            private Object scrq;
            private Object sjys;
            private String ssbm;
            private int status;
            private String sydd;
            private Object unsafe;
            private String xmbm;
            private int xmlx;
            private String xmmc;
            private Object xmtm;

            public Object getBxrq() {
                return bxrq;
            }

            public void setBxrq(Object bxrq) {
                this.bxrq = bxrq;
            }

            public Object getCountermeasure() {
                return countermeasure;
            }

            public void setCountermeasure(Object countermeasure) {
                this.countermeasure = countermeasure;
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

            public Object getEddy() {
                return eddy;
            }

            public void setEddy(Object eddy) {
                this.eddy = eddy;
            }

            public int getEjys() {
                return ejys;
            }

            public void setEjys(int ejys) {
                this.ejys = ejys;
            }

            public Object getGlxh() {
                return glxh;
            }

            public void setGlxh(Object glxh) {
                this.glxh = glxh;
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

            public String getJcjg() {
                return jcjg;
            }

            public void setJcjg(String jcjg) {
                this.jcjg = jcjg;
            }

            public Object getJckssj() {
                return jckssj;
            }

            public void setJckssj(Object jckssj) {
                this.jckssj = jckssj;
            }

            public Object getJclx() {
                return jclx;
            }

            public void setJclx(Object jclx) {
                this.jclx = jclx;
            }

            public int getJcxId() {
                return jcxId;
            }

            public void setJcxId(int jcxId) {
                this.jcxId = jcxId;
            }

            public long getJdrq() {
                return jdrq;
            }

            public void setJdrq(long jdrq) {
                this.jdrq = jdrq;
            }

            public Object getModifyBy() {
                return modifyBy;
            }

            public void setModifyBy(Object modifyBy) {
                this.modifyBy = modifyBy;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public Object getMonitor() {
                return monitor;
            }

            public void setMonitor(Object monitor) {
                this.monitor = monitor;
            }

            public int getQyId() {
                return qyId;
            }

            public void setQyId(int qyId) {
                this.qyId = qyId;
            }

            public long getQyrq() {
                return qyrq;
            }

            public void setQyrq(long qyrq) {
                this.qyrq = qyrq;
            }

            public Object getQywzjd() {
                return qywzjd;
            }

            public void setQywzjd(Object qywzjd) {
                this.qywzjd = qywzjd;
            }

            public Object getQywzwd() {
                return qywzwd;
            }

            public void setQywzwd(Object qywzwd) {
                this.qywzwd = qywzwd;
            }

            public Object getResult() {
                return result;
            }

            public void setResult(Object result) {
                this.result = result;
            }

            public Object getSays() {
                return says;
            }

            public void setSays(Object says) {
                this.says = says;
            }

            public Object getSbjz() {
                return sbjz;
            }

            public void setSbjz(Object sbjz) {
                this.sbjz = sbjz;
            }

            public String getSbpp() {
                return sbpp;
            }

            public void setSbpp(String sbpp) {
                this.sbpp = sbpp;
            }

            public String getSbtp() {
                return sbtp;
            }

            public void setSbtp(String sbtp) {
                this.sbtp = sbtp;
            }

            public String getSbxh() {
                return sbxh;
            }

            public void setSbxh(String sbxh) {
                this.sbxh = sbxh;
            }

            public Object getScrq() {
                return scrq;
            }

            public void setScrq(Object scrq) {
                this.scrq = scrq;
            }

            public Object getSjys() {
                return sjys;
            }

            public void setSjys(Object sjys) {
                this.sjys = sjys;
            }

            public String getSsbm() {
                return ssbm;
            }

            public void setSsbm(String ssbm) {
                this.ssbm = ssbm;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSydd() {
                return sydd;
            }

            public void setSydd(String sydd) {
                this.sydd = sydd;
            }

            public Object getUnsafe() {
                return unsafe;
            }

            public void setUnsafe(Object unsafe) {
                this.unsafe = unsafe;
            }

            public String getXmbm() {
                return xmbm;
            }

            public void setXmbm(String xmbm) {
                this.xmbm = xmbm;
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

            public Object getXmtm() {
                return xmtm;
            }

            public void setXmtm(Object xmtm) {
                this.xmtm = xmtm;
            }
        }
    }
}
