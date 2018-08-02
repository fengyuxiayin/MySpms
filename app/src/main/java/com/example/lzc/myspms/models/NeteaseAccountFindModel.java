package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/5/18.
 */

public class NeteaseAccountFindModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"accid":"sdasdasd","accountId":117,"createBy":1,"createTime":1529226704000,"gender":0,"id":3,"inUse":1,"isBlock":0,"name":"sdsdasda","showName":"东北片组","status":1,"token":"0c79c11c830cafd8784833af4ecc2ba3"},{"accid":"111111","accountId":1,"createBy":1,"createTime":1529224923000,"gender":0,"id":2,"inUse":1,"isBlock":0,"modifyBy":1,"modifyTime":1532081593000,"name":"sdasd","showName":"超级管理员","status":1,"token":"122d5bc1eecf9ea3b6f4e0df8b185ed4"},{"accid":"121212","accountId":110,"createBy":1,"createTime":1526539381000,"gender":0,"id":1,"inUse":1,"isBlock":0,"modifyBy":1,"modifyTime":1532081587000,"name":"hello","showName":"东北片组","status":1,"token":"2e032e7f27def2a06fa2d6acea26c8c3"}],"pn":1,"size":1000,"total":3}
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
    public class NeteaseAccountFindMsgModel implements Serializable{

        /**
         * list : [{"accid":"sdasdasd","accountId":117,"createBy":1,"createTime":1529226704000,"gender":0,"id":3,"inUse":1,"isBlock":0,"name":"sdsdasda","showName":"东北片组","status":1,"token":"0c79c11c830cafd8784833af4ecc2ba3"},{"accid":"111111","accountId":1,"createBy":1,"createTime":1529224923000,"gender":0,"id":2,"inUse":1,"isBlock":0,"modifyBy":1,"modifyTime":1532081593000,"name":"sdasd","showName":"超级管理员","status":1,"token":"122d5bc1eecf9ea3b6f4e0df8b185ed4"},{"accid":"121212","accountId":110,"createBy":1,"createTime":1526539381000,"gender":0,"id":1,"inUse":1,"isBlock":0,"modifyBy":1,"modifyTime":1532081587000,"name":"hello","showName":"东北片组","status":1,"token":"2e032e7f27def2a06fa2d6acea26c8c3"}]
         * pn : 1
         * size : 1000
         * total : 3
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
             * accid : sdasdasd
             * accountId : 117
             * createBy : 1
             * createTime : 1529226704000
             * gender : 0
             * id : 3
             * inUse : 1
             * isBlock : 0
             * name : sdsdasda
             * showName : 东北片组
             * status : 1
             * token : 0c79c11c830cafd8784833af4ecc2ba3
             * modifyBy : 1
             * modifyTime : 1532081593000
             */

            private String accid;
            private int accountId;
            private int createBy;
            private long createTime;
            private int gender;
            private int id;
            private int inUse;
            private int isBlock;
            private String name;
            private String showName;
            private int status;
            private String token;
            private int modifyBy;
            private long modifyTime;

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
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

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getInUse() {
                return inUse;
            }

            public void setInUse(int inUse) {
                this.inUse = inUse;
            }

            public int getIsBlock() {
                return isBlock;
            }

            public void setIsBlock(int isBlock) {
                this.isBlock = isBlock;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShowName() {
                return showName;
            }

            public void setShowName(String showName) {
                this.showName = showName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
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
        }
    }
}
