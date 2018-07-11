package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/5/29.
 */

public class CheckMessageModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"checkInfo":{"createBy":1,"createTime":1527126906000,"id":7,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1527126906000,"jclx":1,"modifyBy":1,"modifyTime":1527126938000,"number":"1-5-24-1","qyId":1,"sfyq":0,"status":1,"word":"2018","zgqx":1527263999000,"zqjlId":1},"enterprise":{"aqfzr":"常翠","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13793201391","aqjgjcjg":"夏庄街道安监办","aqjgszqk":1,"bzhfj":0,"createBy":1,"createTime":1523271950000,"cyrysl":2,"dzyx":"","fddbr":"屈新宇","gmqk":0,"hylbdm":"F5162","id":1,"jgfl":"G011","jjlxdm":11,"jyfw":"石油及制品批发  ","lxdh":"15266216787","modifyBy":1,"modifyTime":1527050796000,"qyfwzb":"[]","qygm":3,"qylsgx":10,"qymc":"中国石油山东青岛销售分公司第131加油站","qytp":"","qywzjd":120.43509,"qywzwd":36.21689,"qyzt":1,"scjydz":"青岛市城阳区王沙路中国石油加油站","sfzd":"A","sqId":3,"status":1,"tze":1000,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xmsl":1,"xzqhdm":123456,"yye":100,"yyedw":"1","zcdz":"青岛市城阳区王沙路","zch":"","zsrysl":0,"zyfzr":"方海玲","zyfzrdzyx":"qdsofa@163.com","zyfzrgddhhm":"","zyfzryddhhm":"15964275670","zzaqscglrys":1,"zzjgdm":"","zzyjglrys":1},"problemCount":2}
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
    public class CheckMessageMsgModel implements Serializable{

        /**
         * checkInfo : {"createBy":1,"createTime":1527126906000,"id":7,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jckssj":1527126906000,"jclx":1,"modifyBy":1,"modifyTime":1527126938000,"number":"1-5-24-1","qyId":1,"sfyq":0,"status":1,"word":"2018","zgqx":1527263999000,"zqjlId":1}
         * enterprise : {"aqfzr":"常翠","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13793201391","aqjgjcjg":"夏庄街道安监办","aqjgszqk":1,"bzhfj":0,"createBy":1,"createTime":1523271950000,"cyrysl":2,"dzyx":"","fddbr":"屈新宇","gmqk":0,"hylbdm":"F5162","id":1,"jgfl":"G011","jjlxdm":11,"jyfw":"石油及制品批发  ","lxdh":"15266216787","modifyBy":1,"modifyTime":1527050796000,"qyfwzb":"[]","qygm":3,"qylsgx":10,"qymc":"中国石油山东青岛销售分公司第131加油站","qytp":"","qywzjd":120.43509,"qywzwd":36.21689,"qyzt":1,"scjydz":"青岛市城阳区王沙路中国石油加油站","sfzd":"A","sqId":3,"status":1,"tze":1000,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xmsl":1,"xzqhdm":123456,"yye":100,"yyedw":"1","zcdz":"青岛市城阳区王沙路","zch":"","zsrysl":0,"zyfzr":"方海玲","zyfzrdzyx":"qdsofa@163.com","zyfzrgddhhm":"","zyfzryddhhm":"15964275670","zzaqscglrys":1,"zzjgdm":"","zzyjglrys":1}
         * problemCount : 2
         */

        private CheckInfoBean checkInfo;
        private EnterpriseBean enterprise;
        private int problemCount;

        public CheckInfoBean getCheckInfo() {
            return checkInfo;
        }

        public void setCheckInfo(CheckInfoBean checkInfo) {
            this.checkInfo = checkInfo;
        }

        public EnterpriseBean getEnterprise() {
            return enterprise;
        }

        public void setEnterprise(EnterpriseBean enterprise) {
            this.enterprise = enterprise;
        }

        public int getProblemCount() {
            return problemCount;
        }

        public void setProblemCount(int problemCount) {
            this.problemCount = problemCount;
        }

        public class CheckInfoBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1527126906000
             * id : 7
             * jcdwId : 1
             * jcdwlx : 2
             * jcjg : 0
             * jckssj : 1527126906000
             * jclx : 1
             * modifyBy : 1
             * modifyTime : 1527126938000
             * number : 1-5-24-1
             * qyId : 1
             * sfyq : 0
             * status : 1
             * word : 2018
             * zgqx : 1527263999000
             * zqjlId : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private int jcdwId;
            private int jcdwlx;
            private int jcjg;
            private long jckssj;
            private int jclx;
            private int modifyBy;
            private long modifyTime;
            private String number;
            private int qyId;
            private int sfyq;
            private int status;
            private String word;
            private long zgqx;
            private int zqjlId;

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

            public int getJclx() {
                return jclx;
            }

            public void setJclx(int jclx) {
                this.jclx = jclx;
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

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public int getQyId() {
                return qyId;
            }

            public void setQyId(int qyId) {
                this.qyId = qyId;
            }

            public int getSfyq() {
                return sfyq;
            }

            public void setSfyq(int sfyq) {
                this.sfyq = sfyq;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public long getZgqx() {
                return zgqx;
            }

            public void setZgqx(long zgqx) {
                this.zgqx = zgqx;
            }

            public int getZqjlId() {
                return zqjlId;
            }

            public void setZqjlId(int zqjlId) {
                this.zqjlId = zqjlId;
            }
        }

        public class EnterpriseBean implements Serializable{
            /**
             * aqfzr : 常翠
             * aqfzrdzyx :
             * aqfzrgddhhm :
             * aqfzryddhhm : 13793201391
             * aqjgjcjg : 夏庄街道安监办
             * aqjgszqk : 1
             * bzhfj : 0
             * createBy : 1
             * createTime : 1523271950000
             * cyrysl : 2
             * dzyx :
             * fddbr : 屈新宇
             * gmqk : 0
             * hylbdm : F5162
             * id : 1
             * jgfl : G011
             * jjlxdm : 11
             * jyfw : 石油及制品批发
             * lxdh : 15266216787
             * modifyBy : 1
             * modifyTime : 1527050796000
             * qyfwzb : []
             * qygm : 3
             * qylsgx : 10
             * qymc : 中国石油山东青岛销售分公司第131加油站
             * qytp :
             * qywzjd : 120.43509
             * qywzwd : 36.21689
             * qyzt : 1
             * scjydz : 青岛市城阳区王沙路中国石油加油站
             * sfzd : A
             * sqId : 3
             * status : 1
             * tze : 1000
             * tzedw : 1
             * tzzyrysl : 0
             * wgfzr :
             * wgfzrgddhhm :
             * wgfzryddhhm :
             * xmsl : 1
             * xzqhdm : 123456
             * yye : 100
             * yyedw : 1
             * zcdz : 青岛市城阳区王沙路
             * zch :
             * zsrysl : 0
             * zyfzr : 方海玲
             * zyfzrdzyx : qdsofa@163.com
             * zyfzrgddhhm :
             * zyfzryddhhm : 15964275670
             * zzaqscglrys : 1
             * zzjgdm :
             * zzyjglrys : 1
             */

            private String aqfzr;
            private String aqfzrdzyx;
            private String aqfzrgddhhm;
            private String aqfzryddhhm;
            private String aqjgjcjg;
            private int aqjgszqk;
            private int bzhfj;
            private int createBy;
            private long createTime;
            private int cyrysl;
            private String dzyx;
            private String fddbr;
            private int gmqk;
            private String hylbdm;
            private int id;
            private String jgfl;
            private int jjlxdm;
            private String jyfw;
            private String lxdh;
            private int modifyBy;
            private long modifyTime;
            private String qyfwzb;
            private int qygm;
            private int qylsgx;
            private String qymc;
            private String qytp;
            private double qywzjd;
            private double qywzwd;
            private int qyzt;
            private String scjydz;
            private String sfzd;
            private int sqId;
            private int status;
            private int tze;
            private String tzedw;
            private int tzzyrysl;
            private String wgfzr;
            private String wgfzrgddhhm;
            private String wgfzryddhhm;
            private int xmsl;
            private int xzqhdm;
            private int yye;
            private String yyedw;
            private String zcdz;
            private String zch;
            private int zsrysl;
            private String zyfzr;
            private String zyfzrdzyx;
            private String zyfzrgddhhm;
            private String zyfzryddhhm;
            private int zzaqscglrys;
            private String zzjgdm;
            private int zzyjglrys;

            public String getAqfzr() {
                return aqfzr;
            }

            public void setAqfzr(String aqfzr) {
                this.aqfzr = aqfzr;
            }

            public String getAqfzrdzyx() {
                return aqfzrdzyx;
            }

            public void setAqfzrdzyx(String aqfzrdzyx) {
                this.aqfzrdzyx = aqfzrdzyx;
            }

            public String getAqfzrgddhhm() {
                return aqfzrgddhhm;
            }

            public void setAqfzrgddhhm(String aqfzrgddhhm) {
                this.aqfzrgddhhm = aqfzrgddhhm;
            }

            public String getAqfzryddhhm() {
                return aqfzryddhhm;
            }

            public void setAqfzryddhhm(String aqfzryddhhm) {
                this.aqfzryddhhm = aqfzryddhhm;
            }

            public String getAqjgjcjg() {
                return aqjgjcjg;
            }

            public void setAqjgjcjg(String aqjgjcjg) {
                this.aqjgjcjg = aqjgjcjg;
            }

            public int getAqjgszqk() {
                return aqjgszqk;
            }

            public void setAqjgszqk(int aqjgszqk) {
                this.aqjgszqk = aqjgszqk;
            }

            public int getBzhfj() {
                return bzhfj;
            }

            public void setBzhfj(int bzhfj) {
                this.bzhfj = bzhfj;
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

            public int getCyrysl() {
                return cyrysl;
            }

            public void setCyrysl(int cyrysl) {
                this.cyrysl = cyrysl;
            }

            public String getDzyx() {
                return dzyx;
            }

            public void setDzyx(String dzyx) {
                this.dzyx = dzyx;
            }

            public String getFddbr() {
                return fddbr;
            }

            public void setFddbr(String fddbr) {
                this.fddbr = fddbr;
            }

            public int getGmqk() {
                return gmqk;
            }

            public void setGmqk(int gmqk) {
                this.gmqk = gmqk;
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

            public String getJgfl() {
                return jgfl;
            }

            public void setJgfl(String jgfl) {
                this.jgfl = jgfl;
            }

            public int getJjlxdm() {
                return jjlxdm;
            }

            public void setJjlxdm(int jjlxdm) {
                this.jjlxdm = jjlxdm;
            }

            public String getJyfw() {
                return jyfw;
            }

            public void setJyfw(String jyfw) {
                this.jyfw = jyfw;
            }

            public String getLxdh() {
                return lxdh;
            }

            public void setLxdh(String lxdh) {
                this.lxdh = lxdh;
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

            public String getQyfwzb() {
                return qyfwzb;
            }

            public void setQyfwzb(String qyfwzb) {
                this.qyfwzb = qyfwzb;
            }

            public int getQygm() {
                return qygm;
            }

            public void setQygm(int qygm) {
                this.qygm = qygm;
            }

            public int getQylsgx() {
                return qylsgx;
            }

            public void setQylsgx(int qylsgx) {
                this.qylsgx = qylsgx;
            }

            public String getQymc() {
                return qymc;
            }

            public void setQymc(String qymc) {
                this.qymc = qymc;
            }

            public String getQytp() {
                return qytp;
            }

            public void setQytp(String qytp) {
                this.qytp = qytp;
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

            public int getQyzt() {
                return qyzt;
            }

            public void setQyzt(int qyzt) {
                this.qyzt = qyzt;
            }

            public String getScjydz() {
                return scjydz;
            }

            public void setScjydz(String scjydz) {
                this.scjydz = scjydz;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTze() {
                return tze;
            }

            public void setTze(int tze) {
                this.tze = tze;
            }

            public String getTzedw() {
                return tzedw;
            }

            public void setTzedw(String tzedw) {
                this.tzedw = tzedw;
            }

            public int getTzzyrysl() {
                return tzzyrysl;
            }

            public void setTzzyrysl(int tzzyrysl) {
                this.tzzyrysl = tzzyrysl;
            }

            public String getWgfzr() {
                return wgfzr;
            }

            public void setWgfzr(String wgfzr) {
                this.wgfzr = wgfzr;
            }

            public String getWgfzrgddhhm() {
                return wgfzrgddhhm;
            }

            public void setWgfzrgddhhm(String wgfzrgddhhm) {
                this.wgfzrgddhhm = wgfzrgddhhm;
            }

            public String getWgfzryddhhm() {
                return wgfzryddhhm;
            }

            public void setWgfzryddhhm(String wgfzryddhhm) {
                this.wgfzryddhhm = wgfzryddhhm;
            }

            public int getXmsl() {
                return xmsl;
            }

            public void setXmsl(int xmsl) {
                this.xmsl = xmsl;
            }

            public int getXzqhdm() {
                return xzqhdm;
            }

            public void setXzqhdm(int xzqhdm) {
                this.xzqhdm = xzqhdm;
            }

            public int getYye() {
                return yye;
            }

            public void setYye(int yye) {
                this.yye = yye;
            }

            public String getYyedw() {
                return yyedw;
            }

            public void setYyedw(String yyedw) {
                this.yyedw = yyedw;
            }

            public String getZcdz() {
                return zcdz;
            }

            public void setZcdz(String zcdz) {
                this.zcdz = zcdz;
            }

            public String getZch() {
                return zch;
            }

            public void setZch(String zch) {
                this.zch = zch;
            }

            public int getZsrysl() {
                return zsrysl;
            }

            public void setZsrysl(int zsrysl) {
                this.zsrysl = zsrysl;
            }

            public String getZyfzr() {
                return zyfzr;
            }

            public void setZyfzr(String zyfzr) {
                this.zyfzr = zyfzr;
            }

            public String getZyfzrdzyx() {
                return zyfzrdzyx;
            }

            public void setZyfzrdzyx(String zyfzrdzyx) {
                this.zyfzrdzyx = zyfzrdzyx;
            }

            public String getZyfzrgddhhm() {
                return zyfzrgddhhm;
            }

            public void setZyfzrgddhhm(String zyfzrgddhhm) {
                this.zyfzrgddhhm = zyfzrgddhhm;
            }

            public String getZyfzryddhhm() {
                return zyfzryddhhm;
            }

            public void setZyfzryddhhm(String zyfzryddhhm) {
                this.zyfzryddhhm = zyfzryddhhm;
            }

            public int getZzaqscglrys() {
                return zzaqscglrys;
            }

            public void setZzaqscglrys(int zzaqscglrys) {
                this.zzaqscglrys = zzaqscglrys;
            }

            public String getZzjgdm() {
                return zzjgdm;
            }

            public void setZzjgdm(String zzjgdm) {
                this.zzjgdm = zzjgdm;
            }

            public int getZzyjglrys() {
                return zzyjglrys;
            }

            public void setZzyjglrys(int zzyjglrys) {
                this.zzyjglrys = zzyjglrys;
            }
        }
    }
}
