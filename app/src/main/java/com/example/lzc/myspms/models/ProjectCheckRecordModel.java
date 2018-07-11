package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/3/20.
 */

public class ProjectCheckRecordModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"pager":{"list":[{"bzqsj":1522425600000,"createBy":1,"createTime":1520585072000,"id":4,"jcId":1,"jcdwId":1,"jcdwMc":"admin","jcjg":1,"jcjssj":1520585091000,"jckssj":1520585072000,"jclx":1,"jcxmId":181,"modifyBy":1,"modifyTime":1520585091000,"qyId":12,"status":1,"szqsj":1519833600000}],"pn":1,"size":10,"total":1},"qymc":"测试添加2018","xmmc":"0000000"}
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
    public class ProjectCheckRecordMsgModel implements Serializable{

        /**
         * pager : {"list":[{"bzqsj":1522425600000,"createBy":1,"createTime":1520585072000,"id":4,"jcId":1,"jcdwId":1,"jcdwMc":"admin","jcjg":1,"jcjssj":1520585091000,"jckssj":1520585072000,"jclx":1,"jcxmId":181,"modifyBy":1,"modifyTime":1520585091000,"qyId":12,"status":1,"szqsj":1519833600000}],"pn":1,"size":10,"total":1}
         * qymc : 测试添加2018
         * xmmc : 0000000
         */

        private PagerBean pager;
        private String qymc;
        private String xmmc;

        public PagerBean getPager() {
            return pager;
        }

        public void setPager(PagerBean pager) {
            this.pager = pager;
        }

        public String getQymc() {
            return qymc;
        }

        public void setQymc(String qymc) {
            this.qymc = qymc;
        }

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public class PagerBean implements Serializable{
            /**
             * list : [{"bzqsj":1522425600000,"createBy":1,"createTime":1520585072000,"id":4,"jcId":1,"jcdwId":1,"jcdwMc":"admin","jcjg":1,"jcjssj":1520585091000,"jckssj":1520585072000,"jclx":1,"jcxmId":181,"modifyBy":1,"modifyTime":1520585091000,"qyId":12,"status":1,"szqsj":1519833600000}]
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

            public class ListBean implements Serializable{
                /**
                 * bzqsj : 1522425600000
                 * createBy : 1
                 * createTime : 1520585072000
                 * id : 4
                 * jcId : 1
                 * jcdwId : 1
                 * jcdwMc : admin
                 * jcjg : 1
                 * jcjssj : 1520585091000
                 * jckssj : 1520585072000
                 * jclx : 1
                 * jcxmId : 181
                 * modifyBy : 1
                 * modifyTime : 1520585091000
                 * qyId : 12
                 * status : 1
                 * szqsj : 1519833600000
                 */

                private long bzqsj;
                private int createBy;
                private long createTime;
                private int id;
                private int jcId;
                private int jcdwId;
                private String jcdwMc;
                private int jcjg;
                private long jcjssj;
                private long jckssj;
                private int jclx;
                private int jcxmId;
                private int modifyBy;
                private long modifyTime;
                private int qyId;
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

                public int getJcId() {
                    return jcId;
                }

                public void setJcId(int jcId) {
                    this.jcId = jcId;
                }

                public int getJcdwId() {
                    return jcdwId;
                }

                public void setJcdwId(int jcdwId) {
                    this.jcdwId = jcdwId;
                }

                public String getJcdwMc() {
                    return jcdwMc;
                }

                public void setJcdwMc(String jcdwMc) {
                    this.jcdwMc = jcdwMc;
                }

                public int getJcjg() {
                    return jcjg;
                }

                public void setJcjg(int jcjg) {
                    this.jcjg = jcjg;
                }

                public long getJcjssj() {
                    return jcjssj;
                }

                public void setJcjssj(long jcjssj) {
                    this.jcjssj = jcjssj;
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

                public long getSzqsj() {
                    return szqsj;
                }

                public void setSzqsj(long szqsj) {
                    this.szqsj = szqsj;
                }
            }
        }
    }
}
