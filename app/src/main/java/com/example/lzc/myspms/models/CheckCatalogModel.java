package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/12/6.
 */

public class CheckCatalogModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"bzqsj":1522425600000,"checkInfo":{"id":1,"jcjg":0,"jckssj":1520584641000},"countChecked":8,"countQualified":4,"countTotal":35,"countUnqualified":4,"createBy":1,"createTime":1520580731000,"id":2,"jcd":"1,56,76,359,167","jcdwId":1,"jcdwlx":2,"jclx":1,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1520580712000,\"endDate\":1522425600000,\"id\":4,\"jcd\":\"1,56,76,359,167\",\"jcdwlx\":2,\"jcnf\":2018,\"jczq\":1,\"jczqdw\":2,\"modifyBy\":1,\"modifyTime\":1520580727000,\"publishStatus\":0,\"qyIds\":\"12,11,9,8,7,6,5,2,3,4,1\",\"startDate\":1519833600000,\"status\":1,\"xmlx\":\"1,56,76,359,167\"}","jczt":0,"modifyTime":1520580731000,"qyId":12,"qymc":"测试添加2018","status":1,"szqsj":1519833600000},{"bzqsj":1521302399000,"checkInfo":{"id":3,"jcjg":0,"jckssj":1521186083000},"countChecked":1,"countQualified":0,"countTotal":15,"countUnqualified":1,"createBy":1,"createTime":1521186083000,"id":3,"jcdwId":1,"jcdwlx":2,"jclx":2,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1520580712000,\"endDate\":1522425600000,\"id\":4,\"jcd\":\"1,56,76,359,167\",\"jcdwlx\":2,\"jcnf\":2018,\"jczq\":1,\"jczqdw\":2,\"modifyBy\":1,\"modifyTime\":1520580727000,\"publishStatus\":0,\"qyIds\":\"12,11,9,8,7,6,5,2,3,4,1\",\"startDate\":1519833600000,\"status\":1,\"xmlx\":\"1,56,76,359,167\"}","jczt":0,"modifyTime":1521186082000,"qyId":7,"qymc":"第七有限责任公司","status":1,"szqsj":1519833600000}],"pn":1,"size":10,"total":2}
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
    public class CheckCatalogMsgModel implements Serializable{

        /**
         * list : [{"bzqsj":1522425600000,"checkInfo":{"id":1,"jcjg":0,"jckssj":1520584641000},"countChecked":8,"countQualified":4,"countTotal":35,"countUnqualified":4,"createBy":1,"createTime":1520580731000,"id":2,"jcd":"1,56,76,359,167","jcdwId":1,"jcdwlx":2,"jclx":1,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1520580712000,\"endDate\":1522425600000,\"id\":4,\"jcd\":\"1,56,76,359,167\",\"jcdwlx\":2,\"jcnf\":2018,\"jczq\":1,\"jczqdw\":2,\"modifyBy\":1,\"modifyTime\":1520580727000,\"publishStatus\":0,\"qyIds\":\"12,11,9,8,7,6,5,2,3,4,1\",\"startDate\":1519833600000,\"status\":1,\"xmlx\":\"1,56,76,359,167\"}","jczt":0,"modifyTime":1520580731000,"qyId":12,"qymc":"测试添加2018","status":1,"szqsj":1519833600000},{"bzqsj":1521302399000,"checkInfo":{"id":3,"jcjg":0,"jckssj":1521186083000},"countChecked":1,"countQualified":0,"countTotal":15,"countUnqualified":1,"createBy":1,"createTime":1521186083000,"id":3,"jcdwId":1,"jcdwlx":2,"jclx":2,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1520580712000,\"endDate\":1522425600000,\"id\":4,\"jcd\":\"1,56,76,359,167\",\"jcdwlx\":2,\"jcnf\":2018,\"jczq\":1,\"jczqdw\":2,\"modifyBy\":1,\"modifyTime\":1520580727000,\"publishStatus\":0,\"qyIds\":\"12,11,9,8,7,6,5,2,3,4,1\",\"startDate\":1519833600000,\"status\":1,\"xmlx\":\"1,56,76,359,167\"}","jczt":0,"modifyTime":1521186082000,"qyId":7,"qymc":"第七有限责任公司","status":1,"szqsj":1519833600000}]
         * pn : 1
         * size : 10
         * total : 2
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
             * bzqsj : 1522425600000
             * checkInfo : {"id":1,"jcjg":0,"jckssj":1520584641000}
             * countChecked : 8
             * countQualified : 4
             * countTotal : 35
             * countUnqualified : 4
             * createBy : 1
             * createTime : 1520580731000
             * id : 2
             * jcd : 1,56,76,359,167
             * jcdwId : 1
             * jcdwlx : 2
             * jclx : 1
             * jcsId : 4
             * jcsJson : {"createBy":1,"createTime":1520580712000,"endDate":1522425600000,"id":4,"jcd":"1,56,76,359,167","jcdwlx":2,"jcnf":2018,"jczq":1,"jczqdw":2,"modifyBy":1,"modifyTime":1520580727000,"publishStatus":0,"qyIds":"12,11,9,8,7,6,5,2,3,4,1","startDate":1519833600000,"status":1,"xmlx":"1,56,76,359,167"}
             * jczt : 0
             * modifyTime : 1520580731000
             * qyId : 12
             * qymc : 测试添加2018
             * status : 1
             * szqsj : 1519833600000
             */

            private long bzqsj;
            private CheckInfoBean checkInfo;
            private int countChecked;
            private int countQualified;
            private int countTotal;
            private int countUnqualified;
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

            public CheckInfoBean getCheckInfo() {
                return checkInfo;
            }

            public void setCheckInfo(CheckInfoBean checkInfo) {
                this.checkInfo = checkInfo;
            }

            public int getCountChecked() {
                return countChecked;
            }

            public void setCountChecked(int countChecked) {
                this.countChecked = countChecked;
            }

            public int getCountQualified() {
                return countQualified;
            }

            public void setCountQualified(int countQualified) {
                this.countQualified = countQualified;
            }

            public int getCountTotal() {
                return countTotal;
            }

            public void setCountTotal(int countTotal) {
                this.countTotal = countTotal;
            }

            public int getCountUnqualified() {
                return countUnqualified;
            }

            public void setCountUnqualified(int countUnqualified) {
                this.countUnqualified = countUnqualified;
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

            public class CheckInfoBean implements Serializable{
                /**
                 * id : 1
                 * jcjg : 0
                 * jckssj : 1520584641000
                 */

                private int id;
                private int jcjg;
                private long jckssj;

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

                public long getJckssj() {
                    return jckssj;
                }

                public void setJckssj(long jckssj) {
                    this.jckssj = jckssj;
                }
            }
        }
    }
}
