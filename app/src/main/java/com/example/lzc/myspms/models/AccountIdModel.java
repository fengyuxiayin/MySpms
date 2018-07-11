package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/4/27.
 */

public class AccountIdModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1524021759000,"id":20,"jsmc":"企业管理后台","loginName":"xiaofang","loginType":5,"modifyTime":1524021759000,"roleId":5,"ssId":44,"status":1},{"createBy":1,"createTime":1524454291000,"id":21,"jsmc":"系统管理员","loginName":"zhanghao","loginType":5,"modifyTime":1524454291000,"roleId":3,"ssId":44,"status":1}],"pn":1,"size":10,"total":2}
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
    public class AccountIdMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1524021759000,"id":20,"jsmc":"企业管理后台","loginName":"xiaofang","loginType":5,"modifyTime":1524021759000,"roleId":5,"ssId":44,"status":1},{"createBy":1,"createTime":1524454291000,"id":21,"jsmc":"系统管理员","loginName":"zhanghao","loginType":5,"modifyTime":1524454291000,"roleId":3,"ssId":44,"status":1}]
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
             * createBy : 1
             * createTime : 1524021759000
             * id : 20
             * jsmc : 企业管理后台
             * loginName : xiaofang
             * loginType : 5
             * modifyTime : 1524021759000
             * roleId : 5
             * ssId : 44
             * status : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private String jsmc;
            private String loginName;
            private int loginType;
            private long modifyTime;
            private int roleId;
            private int ssId;
            private int status;

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

            public String getJsmc() {
                return jsmc;
            }

            public void setJsmc(String jsmc) {
                this.jsmc = jsmc;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public int getLoginType() {
                return loginType;
            }

            public void setLoginType(int loginType) {
                this.loginType = loginType;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public int getSsId() {
                return ssId;
            }

            public void setSsId(int ssId) {
                this.ssId = ssId;
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
