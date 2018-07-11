package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * 根据社区id查社区的任务信息
 * Created by LZC on 2018/6/19.
 */

public class FindCheckInfoModel implements Serializable {


    /**
     * code : 1
     * data : true
     * msg : {"list":[{"jcdwId":1,"jcdwlx":1,"jcdwmc":"城阳村社区","jzsj":1532102400000,"kssj":1529424000000,"rwmc":"社区任务","tags":"a26f6ad9b6504c95b3428c7f049136db","unPassCount":3},{"jcdwId":1,"jcdwlx":1,"jcdwmc":"城阳村社区","jzsj":1532016000000,"kssj":1532102400000,"rwmc":"社区任务（复查）","unPassCount":1}],"pn":1,"size":10,"total":2}
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

    public class FindCheckInfoMsgModel implements Serializable{

        /**
         * list : [{"jcdwId":1,"jcdwlx":1,"jcdwmc":"城阳村社区","jzsj":1532102400000,"kssj":1529424000000,"rwmc":"社区任务","tags":"a26f6ad9b6504c95b3428c7f049136db","unPassCount":3},{"jcdwId":1,"jcdwlx":1,"jcdwmc":"城阳村社区","jzsj":1532016000000,"kssj":1532102400000,"rwmc":"社区任务（复查）","unPassCount":1}]
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

        public class ListBean {
            /**
             * jcdwId : 1
             * jcdwlx : 1
             * jcdwmc : 城阳村社区
             * jzsj : 1532102400000
             * kssj : 1529424000000
             * rwmc : 社区任务
             * tags : a26f6ad9b6504c95b3428c7f049136db
             * unPassCount : 3
             */

            private int jcdwId;
            private int jcdwlx;
            private String jcdwmc;
            private long jzsj;
            private long kssj;
            private String rwmc;
            private String tags;
            private int unPassCount;

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

            public String getJcdwmc() {
                return jcdwmc;
            }

            public void setJcdwmc(String jcdwmc) {
                this.jcdwmc = jcdwmc;
            }

            public long getJzsj() {
                return jzsj;
            }

            public void setJzsj(long jzsj) {
                this.jzsj = jzsj;
            }

            public long getKssj() {
                return kssj;
            }

            public void setKssj(long kssj) {
                this.kssj = kssj;
            }

            public String getRwmc() {
                return rwmc;
            }

            public void setRwmc(String rwmc) {
                this.rwmc = rwmc;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public int getUnPassCount() {
                return unPassCount;
            }

            public void setUnPassCount(int unPassCount) {
                this.unPassCount = unPassCount;
            }
        }
    }
}
