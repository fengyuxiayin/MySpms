package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/5/28.
 */

public class AccountFindAllModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"data":true,"list":[{"createBy":1,"createTime":1501480099000,"id":1,"loginName":"admin","loginType":2,"modifyBy":1,"modifyTime":1520910653000,"roleId":1,"ssId":1,"ssmc":"夏庄街道安监小组","status":1}]}
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
    public class AccountFindAllMsgModel implements Serializable{

        /**
         * data : true
         * list : [{"createBy":1,"createTime":1501480099000,"id":1,"loginName":"admin","loginType":2,"modifyBy":1,"modifyTime":1520910653000,"roleId":1,"ssId":1,"ssmc":"夏庄街道安监小组","status":1}]
         */

        private boolean data;
        private List<ListBean> list;

        public boolean isData() {
            return data;
        }

        public void setData(boolean data) {
            this.data = data;
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
             * createTime : 1501480099000
             * id : 1
             * loginName : admin
             * loginType : 2
             * modifyBy : 1
             * modifyTime : 1520910653000
             * roleId : 1
             * ssId : 1
             * ssmc : 夏庄街道安监小组
             * status : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private String loginName;
            private int loginType;
            private int modifyBy;
            private long modifyTime;
            private int roleId;
            private int ssId;
            private String ssmc;
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

            public String getSsmc() {
                return ssmc;
            }

            public void setSsmc(String ssmc) {
                this.ssmc = ssmc;
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
