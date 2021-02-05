package com.example.lzc.myspms.models;

import java.util.List;

/**
 * Created by LZC on 2017/11/29.
 */

public class EnterpriseInfoQueryModel {

    private int code;
    private boolean data;
    private String msg;
    private String url;
    /**
     * list : [{"bc":{"id":1,"sqmc":"高家台"},"hylbdm":"I65","id":1,"jjlxdm":17,"lxdh":"15050505050","qyfwzb":"[{\"lng\":120.387693,\"lat\":36.266089},{\"lng\":120.387093,\"lat\":36.268858},{\"lng\":120.391899,\"lat\":36.268858},{\"lng\":120.390698,\"lat\":36.264705}]","qymc":"龙华科道","qywzjd":120.390698,"qywzwd":36.264705,"sfzd":"A","sqId":1,"xmsl":1,"zyfzr":"刘"}]
     * pn : 1
     * size : 10
     * total : 1
     */
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
    public static class ListSet{
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

        public static class ListBean {
            /**
             * bc : {"id":1,"sqmc":"高家台"}
             * hylbdm : I65
             * id : 1
             * jjlxdm : 17
             * lxdh : 15050505050
             * qyfwzb : [{"lng":120.387693,"lat":36.266089},{"lng":120.387093,"lat":36.268858},{"lng":120.391899,"lat":36.268858},{"lng":120.390698,"lat":36.264705}]
             * qymc : 龙华科道
             * qywzjd : 120.390698
             * qywzwd : 36.264705
             * sfzd : A
             * sqId : 1
             * xmsl : 1
             * zyfzr : 刘
             */

            private BcBean bc;
            private String hylbdm;
            private int id;
            private int jjlxdm;
            private String lxdh;
            private String qyfwzb;
            private String qymc;
            private double qywzjd;
            private double qywzwd;
            private String sfzd;
            private int sqId;
            private int xmsl;
            private String zyfzr;
            private String jxcslx;

            public String getJxcslx() {
                return jxcslx;
            }

            public void setJxcslx(String jxcslx) {
                this.jxcslx = jxcslx;
            }

            public BcBean getBc() {
                return bc;
            }

            public void setBc(BcBean bc) {
                this.bc = bc;
            }

            public String getHylbdm() {
                return hylbdm;
            }

            public void setHylbdm(String hylbdm) {
                this.hylbdm = hylbdm;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getJjlxdm() {
                return jjlxdm;
            }

            public void setJjlxdm(int jjlxdm) {
                this.jjlxdm = jjlxdm;
            }

            public String getLxdh() {
                return lxdh;
            }

            public void setLxdh(String lxdh) {
                this.lxdh = lxdh;
            }

            public String getQyfwzb() {
                return qyfwzb;
            }

            public void setQyfwzb(String qyfwzb) {
                this.qyfwzb = qyfwzb;
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

            public String getSfzd() {
                return sfzd;
            }

            public void setSfzd(String sfzd) {
                this.sfzd = sfzd;
            }

            public int getSqId() {
                return sqId;
            }

            public void setSqId(int sqId) {
                this.sqId = sqId;
            }

            public int getXmsl() {
                return xmsl;
            }

            public void setXmsl(int xmsl) {
                this.xmsl = xmsl;
            }

            public String getZyfzr() {
                return zyfzr;
            }

            public void setZyfzr(String zyfzr) {
                this.zyfzr = zyfzr;
            }

            public static class BcBean {
                /**
                 * id : 1
                 * sqmc : 高家台
                 */

                private int id;
                private String sqmc;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getSqmc() {
                    return sqmc;
                }

                public void setSqmc(String sqmc) {
                    this.sqmc = sqmc;
                }
            }
        }
    }

}
