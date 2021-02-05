package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/12/13.
 */

public class CommunityInfoQueryModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"createBy":1,"createTime":1511926232000,"id":1,"modifyBy":1,"modifyTime":1512976403000,"pqId":1,"qysl":6,"sqdz":"青岛流亭高家台","sqfwzb":"[{\"lng\":120.462152,\"lat\":36.271037},{\"lng\":120.454899,\"lat\":36.271487},{\"lng\":120.454126,\"lat\":36.266712},{\"lng\":120.458761,\"lat\":36.26429},{\"lng\":120.461508,\"lat\":36.266436}]","sqmc":"高家台","sqmj":1000000,"sqrk":10000,"status":1},{"createBy":1,"createTime":1512351242000,"id":2,"modifyBy":1,"modifyTime":1512547595000,"pqId":1,"qysl":4,"sqdz":"城阳区丹山社区","sqfwzb":"[{\"lng\":120.431038,\"lat\":36.228148},{\"lng\":120.426832,\"lat\":36.228477},{\"lng\":120.427497,\"lat\":36.226348},{\"lng\":120.430952,\"lat\":36.226399}]","sqmc":"丹山社区","sqmj":20000,"sqrk":20,"status":1},{"createBy":1,"createTime":1512695440000,"id":4,"modifyTime":1512695440000,"pqId":2,"qysl":0,"sqdz":"山东省青岛市城阳区","sqfwzb":"[{\"lng\":120.530859,\"lat\":36.31258},{\"lng\":120.458847,\"lat\":36.323576},{\"lng\":120.441853,\"lat\":36.313203},{\"lng\":120.467602,\"lat\":36.297709}]","sqmc":"夏庄","sqmj":3000,"sqrk":30000,"status":1}],"pn":1,"size":10,"total":3}
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
    public class CommunityInfoQueryMsgModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1511926232000,"id":1,"modifyBy":1,"modifyTime":1512976403000,"pqId":1,"qysl":6,"sqdz":"青岛流亭高家台","sqfwzb":"[{\"lng\":120.462152,\"lat\":36.271037},{\"lng\":120.454899,\"lat\":36.271487},{\"lng\":120.454126,\"lat\":36.266712},{\"lng\":120.458761,\"lat\":36.26429},{\"lng\":120.461508,\"lat\":36.266436}]","sqmc":"高家台","sqmj":1000000,"sqrk":10000,"status":1},{"createBy":1,"createTime":1512351242000,"id":2,"modifyBy":1,"modifyTime":1512547595000,"pqId":1,"qysl":4,"sqdz":"城阳区丹山社区","sqfwzb":"[{\"lng\":120.431038,\"lat\":36.228148},{\"lng\":120.426832,\"lat\":36.228477},{\"lng\":120.427497,\"lat\":36.226348},{\"lng\":120.430952,\"lat\":36.226399}]","sqmc":"丹山社区","sqmj":20000,"sqrk":20,"status":1},{"createBy":1,"createTime":1512695440000,"id":4,"modifyTime":1512695440000,"pqId":2,"qysl":0,"sqdz":"山东省青岛市城阳区","sqfwzb":"[{\"lng\":120.530859,\"lat\":36.31258},{\"lng\":120.458847,\"lat\":36.323576},{\"lng\":120.441853,\"lat\":36.313203},{\"lng\":120.467602,\"lat\":36.297709}]","sqmc":"夏庄","sqmj":3000,"sqrk":30000,"status":1}]
         * pn : 1
         * size : 10
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

        public  class ListBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1511926232000
             * id : 1
             * modifyBy : 1
             * modifyTime : 1512976403000
             * pqId : 1
             * qysl : 6
             * sqdz : 青岛流亭高家台
             * sqfwzb : [{"lng":120.462152,"lat":36.271037},{"lng":120.454899,"lat":36.271487},{"lng":120.454126,"lat":36.266712},{"lng":120.458761,"lat":36.26429},{"lng":120.461508,"lat":36.266436}]
             * sqmc : 高家台
             * sqmj : 1000000
             * sqrk : 10000
             * status : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private int modifyBy;
            private long modifyTime;
            private int pqId;
            private int qysl;
            private String sqdz;
            private String sqfwzb;
            private String sqmc;
            private double sqmj;
            private int sqrk;
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

            public int getPqId() {
                return pqId;
            }

            public void setPqId(int pqId) {
                this.pqId = pqId;
            }

            public int getQysl() {
                return qysl;
            }

            public void setQysl(int qysl) {
                this.qysl = qysl;
            }

            public String getSqdz() {
                return sqdz;
            }

            public void setSqdz(String sqdz) {
                this.sqdz = sqdz;
            }

            public String getSqfwzb() {
                return sqfwzb;
            }

            public void setSqfwzb(String sqfwzb) {
                this.sqfwzb = sqfwzb;
            }

            public String getSqmc() {
                return sqmc;
            }

            public void setSqmc(String sqmc) {
                this.sqmc = sqmc;
            }

            public double getSqmj() {
                return sqmj;
            }

            public void setSqmj(double sqmj) {
                this.sqmj = sqmj;
            }

            public int getSqrk() {
                return sqrk;
            }

            public void setSqrk(int sqrk) {
                this.sqrk = sqrk;
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
