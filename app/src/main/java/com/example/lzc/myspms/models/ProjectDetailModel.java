package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2017/12/19.
 */

public class ProjectDetailModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"countermeasure":"[{\"createBy\":1,\"createTime\":1513071245000,\"id\":1,\"jcxmId\":2,\"memo\":\"导航手机\",\"modifyTime\":1513071245000,\"status\":1}]","createBy":1,"createTime":1513071244000,"eddy":"220","glxh":"500","id":2,"jdrq":1513071248000,"modifyTime":1513071244000,"qyId":3,"qyrq":1513071248000,"result":"[]","sbjz":"3","sbpp":"倒海翻江","sbtp":"","sbxh":"靠的就是","ssbm":"开发部","status":1,"sydd":"是大酱","unsafe":"[{\"createBy\":1,\"createTime\":1513071245000,\"id\":2,\"jcxmId\":2,\"memo\":\"计算机和地方\",\"modifyTime\":1513071245000,\"status\":1}]","xmbm":"99","xmlx":167,"xmmc":"鲜蘑菇云"}
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
    public class ProjectDetailMsgModel implements Serializable{

        /**
         * countermeasure : [{"createBy":1,"createTime":1513071245000,"id":1,"jcxmId":2,"memo":"导航手机","modifyTime":1513071245000,"status":1}]
         * createBy : 1
         * createTime : 1513071244000
         * eddy : 220
         * glxh : 500
         * id : 2
         * jdrq : 1513071248000
         * modifyTime : 1513071244000
         * qyId : 3
         * qyrq : 1513071248000
         * result : []
         * sbjz : 3
         * sbpp : 倒海翻江
         * sbtp :
         * sbxh : 靠的就是
         * ssbm : 开发部
         * status : 1
         * sydd : 是大酱
         * unsafe : [{"createBy":1,"createTime":1513071245000,"id":2,"jcxmId":2,"memo":"计算机和地方","modifyTime":1513071245000,"status":1}]
         * xmbm : 99
         * xmlx : 167
         * xmmc : 鲜蘑菇云
         */

        private String countermeasure;
        private int createBy;
        private long createTime;
        private String eddy;
        private String glxh;
        private int id;
        private long jdrq;
        private long modifyTime;
        private int qyId;
        private long qyrq;
        private long scrq;
        private long bxrq;
        private String result;
        private String sbjz;
        private String sbpp;
        private String sbtp;
        private String sbxh;
        private String ssbm;
        private int status;
        private String sydd;
        private String unsafe;
        private String xmbm;
        private int xmlx;
        private String xmmc;
        private String yjys;
        private String ejys;
        private String says;
        private String sjys;

        public String getYjys() {
            return yjys;
        }

        public void setYjys(String yjys) {
            this.yjys = yjys;
        }

        public String getEjys() {
            return ejys;
        }

        public void setEjys(String ejys) {
            this.ejys = ejys;
        }

        public String getSays() {
            return says;
        }

        public void setSays(String says) {
            this.says = says;
        }

        public String getSjys() {
            return sjys;
        }

        public void setSjys(String sjys) {
            this.sjys = sjys;
        }

        public long getScrq() {
            return scrq;
        }

        public void setScrq(long scrq) {
            this.scrq = scrq;
        }

        public long getBxrq() {
            return bxrq;
        }

        public void setBxrq(long bxrq) {
            this.bxrq = bxrq;
        }

        public String getCountermeasure() {
            return countermeasure;
        }

        public void setCountermeasure(String countermeasure) {
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

        public String getEddy() {
            return eddy;
        }

        public void setEddy(String eddy) {
            this.eddy = eddy;
        }

        public String getGlxh() {
            return glxh;
        }

        public void setGlxh(String glxh) {
            this.glxh = glxh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getJdrq() {
            return jdrq;
        }

        public void setJdrq(long jdrq) {
            this.jdrq = jdrq;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
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

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getSbjz() {
            return sbjz;
        }

        public void setSbjz(String sbjz) {
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

        public String getUnsafe() {
            return unsafe;
        }

        public void setUnsafe(String unsafe) {
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
        public class ProjectDetailUnsafeModel implements Serializable{

            /**
             * createBy : 1
             * createTime : 1513071245000
             * id : 2
             * jcxmId : 2
             * memo : 计算机和地方
             * modifyTime : 1513071245000
             * status : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private int jcxmId;
            private String memo;
            private long modifyTime;
            private int status;
            private String jctp;

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

            public int getJcxmId() {
                return jcxmId;
            }

            public void setJcxmId(int jcxmId) {
                this.jcxmId = jcxmId;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
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
        }
    }
}
