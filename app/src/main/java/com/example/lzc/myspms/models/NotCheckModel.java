package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/2/9.
 */

public class NotCheckModel implements Serializable {

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
    public class NotCheckMsgModel implements Serializable {

        /**
         * list : [{"bzqsj":1519747200000,"createBy":1,"createTime":1517983954000,"id":55,"jcd":"1","jcdwId":1,"jcdwlx":2,"jclx":1,"jcsId":9,"jcsJson":"{\"createBy\":1,\"createTime\":1517983935000,\"endDate\":1519747200000,\"id\":9,\"jcd\":\"1\",\"jcdwlx\":2,\"jcnf\":2018,\"jczq\":2,\"jczqdw\":2,\"modifyBy\":1,\"modifyTime\":1517983949000,\"publishStatus\":0,\"qyIds\":\"11,8,7,6,5,2,3,9,4,1\",\"startDate\":1514736000000,\"status\":1,\"xmlx\":\"1,76,359,167,56\"}","jczt":0,"modifyTime":1517983954000,"qyId":2,"qymc":"第二有限责任公司","status":1,"szqsj":1514736000000},{"bzqsj":1518191999000,"createBy":1,"createTime":1518060608000,"id":63,"jcdwId":1,"jcdwlx":2,"jclx":2,"jcsId":9,"jcsJson":"{\"createBy\":1,\"createTime\":1517983935000,\"endDate\":1519747200000,\"id\":9,\"jcd\":\"1,2,3,6,4,5,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,39,40,27,41,42,28,43,44,29,45,46,30,47,48,31,49,50,32,51,52,33,53,54,34,55,57,35,58,59,36,60,61,37,62,63,38,64,65,66,67,68,69,70,71,72,73,74,75,77,78,79,81,82,84,86,87,88,92,89,91,93,94,95,97,98,100,101,102,104,106,108,110,111,114,115,117,118,120,122,123,125,126,127,128,130,131,132,134,136,137,139,140,142,143,144,146,147,149,150,152,154,156,157,158,160,161,163,164,112,56,305,316,317,318,319,320,321,322,323,381,324,326,327,328,329,330,331,332,333,334,335,337,338,339,340,342,343,344,346,347,348,349,350,351,352,353,354,355,356,357,382,307,306,308,309,310,311,312,313,314,315,170,253,255,256,257,258,263,265,267,268,270,271,272,273,274,275,276,171,76,286,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,80,99,85,96,103,105,107,109,113,116,121,124,129,133,138,141,148,151,153,155,159,162,165,169,172,173,177,213,214,215,216,217,238,242,245,247,249,252,260,264,266,277,278,279,280,281,283,284,285,359,166,174,178,180,181,182,176,168,191,193,203,204,205,206,207,208,209,218,219,220,360,361,362,194,221,222,363,364,365,195,223,224,225,226,368,367,366,196,227,228,369,370,371,197,229,230,231,374,373,372,200,232,233,234,375,376,377,201,235,236,380,379,378,192,243,244,246,248,250,251,241,167,184,185,186,187,188,189\",\"jcdwlx\":2,\"jcnf\":2018,\"jczq\":2,\"jczqdw\":2,\"modifyBy\":1,\"modifyTime\":1517983949000,\"publishStatus\":0,\"qyIds\":\"11,8,7,6,5,2,3,9,4,1\",\"startDate\":1514736000000,\"status\":1,\"xmlx\":\"1,76,359,167,56\"}","jczt":0,"modifyTime":1518060608000,"qyId":1,"qymc":"第一有限责任公司","status":1,"szqsj":1514736000000}]
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
             * bzqsj : 1519747200000
             * createBy : 1
             * createTime : 1517983954000
             * id : 55
             * jcd : 1
             * jcdwId : 1
             * jcdwlx : 2
             * jclx : 1
             * jcsId : 9
             * jcsJson : {"createBy":1,"createTime":1517983935000,"endDate":1519747200000,"id":9,"jcd":"1","jcdwlx":2,"jcnf":2018,"jczq":2,"jczqdw":2,"modifyBy":1,"modifyTime":1517983949000,"publishStatus":0,"qyIds":"11,8,7,6,5,2,3,9,4,1","startDate":1514736000000,"status":1,"xmlx":"1,76,359,167,56"}
             * jczt : 0
             * modifyTime : 1517983954000
             * qyId : 2
             * qymc : 第二有限责任公司
             * status : 1
             * szqsj : 1514736000000
             */

            private long bzqsj;
            private int createBy;
            private long createTime;
            private int id;
            private String jcd;
            private int jcdwId;
            private int jcdwlx;
            private int jclx;
            private int jcsId;
            private String jcsJson;
            private int jczt;
            private long modifyTime;
            private int qyId;
            private String qymc;
            private int status;
            private long szqsj;

            public long getBzqsj() {
                return bzqsj;
            }

            public void setBzqsj(long bzqsj) {
                this.bzqsj = bzqsj;
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

            public String getJcd() {
                return jcd;
            }

            public void setJcd(String jcd) {
                this.jcd = jcd;
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

            public int getJclx() {
                return jclx;
            }

            public void setJclx(int jclx) {
                this.jclx = jclx;
            }

            public int getJcsId() {
                return jcsId;
            }

            public void setJcsId(int jcsId) {
                this.jcsId = jcsId;
            }

            public String getJcsJson() {
                return jcsJson;
            }

            public void setJcsJson(String jcsJson) {
                this.jcsJson = jcsJson;
            }

            public int getJczt() {
                return jczt;
            }

            public void setJczt(int jczt) {
                this.jczt = jczt;
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

            public String getQymc() {
                return qymc;
            }

            public void setQymc(String qymc) {
                this.qymc = qymc;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public long getSzqsj() {
                return szqsj;
            }

            public void setSzqsj(long szqsj) {
                this.szqsj = szqsj;
            }

        }
    }
}
