package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/12/18.
 */

public class CheckItemsQueryModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"bhgjl":0,"qyId":6,"qyfzr":"全部修改","qymc":"第六有限责任公司","qywzjd":45.123456,"qywzwd":45.123456,"sbpp":"阿斯顿和","sbxh":"斯蒂芬森","ssbm":"as啊是的健康","sssq":1,"sydd":"还将对公司","xmbm":"2634","xmid":1,"xmlx":56,"xmmc":"全不锈钢"},{"bhgjl":0,"qyId":3,"qyfzr":"B企业","qymc":"第三有限责任公司","qywzjd":45.123456,"qywzwd":45.123456,"sbpp":"倒海翻江","sbxh":"靠的就是","ssbm":"开发部","sssq":2,"sydd":"是大酱","xmbm":"99","xmid":2,"xmlx":167,"xmmc":"鲜蘑菇云"},{"bhgjl":0,"qyId":2,"qyfzr":"C企业","qymc":"第二有限责任公司","qywzjd":45.123456,"qywzwd":45.123456,"sbpp":"阿萨德计划","sbxh":"副科级的思考","ssbm":"好多噶的","sssq":1,"sydd":"上海大剧院","xmbm":"333","xmid":3,"xmlx":359,"xmmc":"第二公司项目"},{"bhgjl":0,"qyId":4,"qyfzr":"A企业","qymc":"第四有限责任公司","qywzjd":45.123456,"qywzwd":45.123456,"sbpp":"食物","sbxh":"实物","ssbm":"人事部","sssq":2,"sydd":"失误","xmbm":"001","xmid":4,"xmlx":56,"xmmc":"第四项目"},{"bhgjl":0,"qyId":1,"qyfzr":"刘","qymc":"第一有限责任公司","qywzjd":120.390698,"qywzwd":36.264705,"sbpp":"健康","sbxh":"大型","ssbm":"开发","sssq":2,"sydd":"呼呼","xmbm":"001","xmid":5,"xmlx":1,"xmmc":"第一公司项目"},{"bhgjl":0,"qyId":9,"qyfzr":"甲","qymc":"第九有限责任公司","qywzjd":45.123456,"qywzwd":30.123123,"sbpp":"蛤蜊","sbxh":"大型","ssbm":"开发部","sssq":1,"sydd":"办公室","xmbm":"001","xmid":6,"xmlx":359,"xmmc":"第九公司项目"},{"bhgjl":0,"qyId":11,"qyfzr":"十二月十一日","qymc":"十二月十一日","qywzjd":120.389576,"qywzwd":36.264465,"sbpp":"大型","sbxh":"大型","ssbm":"财务部","sssq":1,"sydd":"办公室","xmbm":"001","xmid":7,"xmlx":1,"xmmc":"新增项目"},{"bhgjl":0,"qyId":7,"qyfzr":"手机端新增企业","qymc":"第七有限责任公司","qywzjd":120.388095,"qywzwd":36.265647,"sbpp":"飞过海","sbxh":"刚回家","ssbm":"埃索达","sssq":2,"sydd":"豆腐干","xmbm":"233","xmid":8,"xmlx":76,"xmmc":"新招的"},{"bhgjl":0,"qyId":8,"qyfzr":"测试重点企业等级","qymc":"第八有限责任公司","qywzjd":120.23122,"qywzwd":45.123456,"sbpp":"执行操作v","sbxh":"匆匆穿上","ssbm":"大富大贵","sssq":1,"sydd":"的风格反对和","xmbm":"223","xmid":9,"xmlx":56,"xmmc":"阿什顿"}],"pn":1,"size":10,"total":9}
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
    public class CheckItemsQueryMsgModel implements Serializable{

        /**
         * list : [{"bhgjl":0,"qyId":6,"qyfzr":"全部修改","qymc":"第六有限责任公司","qywzjd":45.123456,"qywzwd":45.123456,"sbpp":"阿斯顿和","sbxh":"斯蒂芬森","ssbm":"as啊是的健康","sssq":1,"sydd":"还将对公司","xmbm":"2634","xmid":1,"xmlx":56,"xmmc":"全不锈钢"},{"bhgjl":0,"qyId":3,"qyfzr":"B企业","qymc":"第三有限责任公司","qywzjd":45.123456,"qywzwd":45.123456,"sbpp":"倒海翻江","sbxh":"靠的就是","ssbm":"开发部","sssq":2,"sydd":"是大酱","xmbm":"99","xmid":2,"xmlx":167,"xmmc":"鲜蘑菇云"},{"bhgjl":0,"qyId":2,"qyfzr":"C企业","qymc":"第二有限责任公司","qywzjd":45.123456,"qywzwd":45.123456,"sbpp":"阿萨德计划","sbxh":"副科级的思考","ssbm":"好多噶的","sssq":1,"sydd":"上海大剧院","xmbm":"333","xmid":3,"xmlx":359,"xmmc":"第二公司项目"},{"bhgjl":0,"qyId":4,"qyfzr":"A企业","qymc":"第四有限责任公司","qywzjd":45.123456,"qywzwd":45.123456,"sbpp":"食物","sbxh":"实物","ssbm":"人事部","sssq":2,"sydd":"失误","xmbm":"001","xmid":4,"xmlx":56,"xmmc":"第四项目"},{"bhgjl":0,"qyId":1,"qyfzr":"刘","qymc":"第一有限责任公司","qywzjd":120.390698,"qywzwd":36.264705,"sbpp":"健康","sbxh":"大型","ssbm":"开发","sssq":2,"sydd":"呼呼","xmbm":"001","xmid":5,"xmlx":1,"xmmc":"第一公司项目"},{"bhgjl":0,"qyId":9,"qyfzr":"甲","qymc":"第九有限责任公司","qywzjd":45.123456,"qywzwd":30.123123,"sbpp":"蛤蜊","sbxh":"大型","ssbm":"开发部","sssq":1,"sydd":"办公室","xmbm":"001","xmid":6,"xmlx":359,"xmmc":"第九公司项目"},{"bhgjl":0,"qyId":11,"qyfzr":"十二月十一日","qymc":"十二月十一日","qywzjd":120.389576,"qywzwd":36.264465,"sbpp":"大型","sbxh":"大型","ssbm":"财务部","sssq":1,"sydd":"办公室","xmbm":"001","xmid":7,"xmlx":1,"xmmc":"新增项目"},{"bhgjl":0,"qyId":7,"qyfzr":"手机端新增企业","qymc":"第七有限责任公司","qywzjd":120.388095,"qywzwd":36.265647,"sbpp":"飞过海","sbxh":"刚回家","ssbm":"埃索达","sssq":2,"sydd":"豆腐干","xmbm":"233","xmid":8,"xmlx":76,"xmmc":"新招的"},{"bhgjl":0,"qyId":8,"qyfzr":"测试重点企业等级","qymc":"第八有限责任公司","qywzjd":120.23122,"qywzwd":45.123456,"sbpp":"执行操作v","sbxh":"匆匆穿上","ssbm":"大富大贵","sssq":1,"sydd":"的风格反对和","xmbm":"223","xmid":9,"xmlx":56,"xmmc":"阿什顿"}]
         * pn : 1
         * size : 10
         * total : 9
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
             * bhgjl : 0
             * qyId : 6
             * qyfzr : 全部修改
             * qymc : 第六有限责任公司
             * qywzjd : 45.123456
             * qywzwd : 45.123456
             * sbpp : 阿斯顿和
             * sbxh : 斯蒂芬森
             * ssbm : as啊是的健康
             * sssq : 1
             * sydd : 还将对公司
             * xmbm : 2634
             * xmid : 1
             * xmlx : 56
             * xmmc : 全不锈钢
             */

            private int bhgjl;
            private int qyId;
            private String qyfzr;
            private String qymc;
            private double qywzjd;
            private double qywzwd;
            private String sbpp;
            private String sbxh;
            private String ssbm;
            private int sssq;
            private String sydd;
            private String xmbm;
            private int xmid;
            private int xmlx;
            private String xmmc;
            private String eddy;
            private String glxh;
            private String sbjz;

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

            public String getSbjz() {
                return sbjz;
            }

            public void setSbjz(String sbjz) {
                this.sbjz = sbjz;
            }

            public int getBhgjl() {
                return bhgjl;
            }

            public void setBhgjl(int bhgjl) {
                this.bhgjl = bhgjl;
            }

            public int getQyId() {
                return qyId;
            }

            public void setQyId(int qyId) {
                this.qyId = qyId;
            }

            public String getQyfzr() {
                return qyfzr;
            }

            public void setQyfzr(String qyfzr) {
                this.qyfzr = qyfzr;
            }

            public String getQymc() {
                return qymc;
            }

            public void setQymc(String qymc) {
                this.qymc = qymc;
            }

            public double getQywzjd() {
                return qywzjd;
            }

            public void setQywzjd(double qywzjd) {
                this.qywzjd = qywzjd;
            }

            public double getQywzwd() {
                return qywzwd;
            }

            public void setQywzwd(double qywzwd) {
                this.qywzwd = qywzwd;
            }

            public String getSbpp() {
                return sbpp;
            }

            public void setSbpp(String sbpp) {
                this.sbpp = sbpp;
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

            public int getSssq() {
                return sssq;
            }

            public void setSssq(int sssq) {
                this.sssq = sssq;
            }

            public String getSydd() {
                return sydd;
            }

            public void setSydd(String sydd) {
                this.sydd = sydd;
            }

            public String getXmbm() {
                return xmbm;
            }

            public void setXmbm(String xmbm) {
                this.xmbm = xmbm;
            }

            public int getXmid() {
                return xmid;
            }

            public void setXmid(int xmid) {
                this.xmid = xmid;
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
