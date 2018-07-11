package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/5/11.
 */

public class CheckProjectFindModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"checked":null,"childrens":null,"createBy":1,"createTime":1525939434000,"description":"企业主要负责任或安全管理人员参加安全生产和职业卫生管理培训并取得《培训合格证书》。","id":1,"ids":null,"jcId":null,"jcjg":null,"jckssj":null,"jclx":null,"jcxId":null,"modifyBy":1,"modifyTime":1525943986000,"num":null,"otherFlag":0,"parentName":null,"selectedIds":null,"state":null,"status":1,"text":null,"type":3,"useType":"2"},{"checked":null,"childrens":null,"createBy":1,"createTime":1525944978000,"description":"建立、健全本单位职能部门及其负责人安全生产责任制。","id":4,"ids":null,"jcId":null,"jcjg":null,"jckssj":null,"jclx":null,"jcxId":null,"modifyBy":null,"modifyTime":1525944978000,"num":null,"otherFlag":0,"parentName":null,"selectedIds":null,"state":null,"status":1,"text":null,"type":3,"useType":"2"}],"pn":1,"size":10,"total":2}
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
    public class CheckProjectFindMsgModel implements Serializable{

        /**
         * list : [{"bhgyy":null,"createBy":1,"createTime":1529128024000,"id":2048,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":1,"standartDescription":"企业主要负责人、安全管理人员未参加安全生产和职业卫生管理培训并取得《培训合格证书》；","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128024000,"id":2049,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":2,"standartDescription":"未建立、健全本单位职能部门及其负责人安全生产责任制；","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128024000,"id":2050,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":3,"standartDescription":"未组织制定本单位安全生产、职业卫生管理制度和操作规程；","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128025000,"id":2051,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":4,"standartDescription":"未制定本单位生产安全事故、职业病危害事故应急救援预案；未定期组织实施应急救援演练；","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128025000,"id":2052,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":5,"standartDescription":"未制定年度安全生产教育培训计划、职业卫生教育培训计划，未订立从业人员教育培训记录卡，未按规定建立本单位教育培训档案（无教育培训记录，无培训考核资料等）；","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128025000,"id":2053,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":6,"standartDescription":"未建立安全生产事故隐患排查治理等各项制度；未按照规定排查治理事故隐患、建立事故隐患信息档案；未按规定上报事故隐患排查治理统计分析表","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128025000,"id":2054,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":7,"standartDescription":"未按规定配备足够的消防器材及消防设施，部分便携式灭火器过期失效，需冲压加粉；","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128025000,"id":2055,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":8,"standartDescription":"未按规定设置两个以上符合紧急疏散需要的安全出口或者堵塞安全出口严重阻塞，消防通道不畅；","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128026000,"id":2056,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":17,"standartDescription":"企业主要负责人、安全管理人员未参加安全生产和职业卫生管理培训并取得《培训合格证书》；","status":1,"wxyId":null,"wxymc":null},{"bhgyy":null,"createBy":1,"createTime":1529128026000,"id":2057,"jcId":45,"jcjg":2,"jcjssj":null,"jckssj":null,"jclx":1,"jctp":null,"modifyBy":null,"modifyTime":null,"standardId":18,"standartDescription":"未建立、健全本单位职能部门及其负责人安全生产责任制；","status":1,"wxyId":null,"wxymc":null}]
         * pn : 1
         * size : 10
         * total : 47
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
             * bhgyy : null
             * createBy : 1
             * createTime : 1529128024000
             * id : 2048
             * jcId : 45
             * jcjg : 2
             * jcjssj : null
             * jckssj : null
             * jclx : 1
             * jctp : null
             * modifyBy : null
             * modifyTime : null
             * standardId : 1
             * standartDescription : 企业主要负责人、安全管理人员未参加安全生产和职业卫生管理培训并取得《培训合格证书》；
             * status : 1
             * wxyId : null
             * wxymc : null
             */

            private String bhgyy;
            private int createBy;
            private long createTime;
            private int id;
            private int jcId;
            private int jcjg;
            private long jcjssj;
            private long jckssj;
            private int jclx;
            private String jctp;
            private Object modifyBy;
            private Object modifyTime;
            private int standardId;
            private String standardDescription;
            private int status;
            private int wxyId;
            private String wxymc;

            public String getStandardDescription() {
                return standardDescription;
            }

            public void setStandardDescription(String standardDescription) {
                this.standardDescription = standardDescription;
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

            public int getJcjg() {
                return jcjg;
            }

            public void setJcjg(int jcjg) {
                this.jcjg = jcjg;
            }

            public int getJclx() {
                return jclx;
            }

            public void setJclx(int jclx) {
                this.jclx = jclx;
            }

            public Object getModifyBy() {
                return modifyBy;
            }

            public void setModifyBy(Object modifyBy) {
                this.modifyBy = modifyBy;
            }

            public Object getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Object modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getStandardId() {
                return standardId;
            }

            public void setStandardId(int standardId) {
                this.standardId = standardId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getBhgyy() {
                return bhgyy;
            }

            public void setBhgyy(String bhgyy) {
                this.bhgyy = bhgyy;
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

            public String getJctp() {
                return jctp;
            }

            public void setJctp(String jctp) {
                this.jctp = jctp;
            }

            public int getWxyId() {
                return wxyId;
            }

            public void setWxyId(int wxyId) {
                this.wxyId = wxyId;
            }

            public String getWxymc() {
                return wxymc;
            }

            public void setWxymc(String wxymc) {
                this.wxymc = wxymc;
            }
        }
    }
}
