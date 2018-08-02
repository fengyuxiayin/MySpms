package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/5/29.
 */

public class CheckMessageModel implements Serializable {


    /**
     * code : 1
     * data : true
     * msg : {"checkInfo":{"createBy":1,"createTime":1533000862000,"id":183,"jcdwId":2,"jcdwlx":2,"jcdwmc":"东北片组","jcjg":0,"jcjssj":1533002084000,"jckssj":1533001805000,"jclx":1,"jcws":"http://192.168.3.3:8081/hfs/source/document/20188/20180802_394_ZGTZS.pdf","jrzfmd":0,"modifyBy":117,"modifyTime":1533172401000,"number":"2-7-31-2","parentId":0,"qyId":394,"qyJson":"{\"aqfzr\":\"陈玉玉\",\"aqfzrdzyx\":\"\",\"aqfzrgddhhm\":\"\",\"aqfzryddhhm\":\"13325006282\",\"aqjgjcjg\":\"安监办\",\"aqjgszqk\":2,\"bzhcjsj\":\"2018-07-26\",\"bzhfssj\":\"2018-07-26\",\"createBy\":117,\"createTime\":1527471604000,\"cyrysl\":20,\"fddbr\":\"韩道仁\",\"gmqk\":1,\"hylbdm\":\"C192\",\"id\":394,\"isaqfzrxx\":2,\"isbzh\":2,\"islgtx\":2,\"isqyzc\":2,\"iswhp\":1,\"iszyfzrxx\":1,\"jgfl\":\"5\",\"jgfljb\":2,\"jjlxdm\":300,\"jyfw\":\"生产皮包\",\"lgtxcjsj\":\"2018-07-26\",\"lgtxfssj\":\"2018-07-26\",\"lxdh\":\"13608980811\",\"modifyBy\":117,\"modifyTime\":1531961024000,\"qyfwzb\":\"\",\"qyfxfj\":3,\"qygm\":1,\"qyjc\":\"1\",\"qylsgx\":60,\"qymc\":\"青岛共成皮革制品有限公司\",\"qytp\":\"s/source/enterprise/20187/20180719084321_348.jpg\",\"qywzjd\":120.467856,\"qywzwd\":36.273175,\"qyzt\":1,\"scjydz\":\"冷沙\",\"sfzd\":\"B\",\"sqId\":22,\"status\":1,\"tze\":21,\"tzedw\":\"1\",\"tzzyrysl\":0,\"yye\":0,\"yyedw\":\"1\",\"zcaqgcsrys\":0,\"zcdz\":\"城阳区夏庄街道银河路272号\",\"zch\":\"91370214725572516C\",\"zsrysl\":0,\"zyfzr\":\"陈玉玉\",\"zyfzrdzyx\":\"\",\"zyfzryddhhm\":\"13325006282\",\"zzaqscglrys\":0,\"zzyjglrys\":0}","rwId":185,"sfyq":0,"status":1,"word":"2018","zgqx":1534348799000},"problemCount":2}
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
         * checkInfo : {"createBy":1,"createTime":1533000862000,"id":183,"jcdwId":2,"jcdwlx":2,"jcdwmc":"东北片组","jcjg":0,"jcjssj":1533002084000,"jckssj":1533001805000,"jclx":1,"jcws":"http://192.168.3.3:8081/hfs/source/document/20188/20180802_394_ZGTZS.pdf","jrzfmd":0,"modifyBy":117,"modifyTime":1533172401000,"number":"2-7-31-2","parentId":0,"qyId":394,"qyJson":"{\"aqfzr\":\"陈玉玉\",\"aqfzrdzyx\":\"\",\"aqfzrgddhhm\":\"\",\"aqfzryddhhm\":\"13325006282\",\"aqjgjcjg\":\"安监办\",\"aqjgszqk\":2,\"bzhcjsj\":\"2018-07-26\",\"bzhfssj\":\"2018-07-26\",\"createBy\":117,\"createTime\":1527471604000,\"cyrysl\":20,\"fddbr\":\"韩道仁\",\"gmqk\":1,\"hylbdm\":\"C192\",\"id\":394,\"isaqfzrxx\":2,\"isbzh\":2,\"islgtx\":2,\"isqyzc\":2,\"iswhp\":1,\"iszyfzrxx\":1,\"jgfl\":\"5\",\"jgfljb\":2,\"jjlxdm\":300,\"jyfw\":\"生产皮包\",\"lgtxcjsj\":\"2018-07-26\",\"lgtxfssj\":\"2018-07-26\",\"lxdh\":\"13608980811\",\"modifyBy\":117,\"modifyTime\":1531961024000,\"qyfwzb\":\"\",\"qyfxfj\":3,\"qygm\":1,\"qyjc\":\"1\",\"qylsgx\":60,\"qymc\":\"青岛共成皮革制品有限公司\",\"qytp\":\"s/source/enterprise/20187/20180719084321_348.jpg\",\"qywzjd\":120.467856,\"qywzwd\":36.273175,\"qyzt\":1,\"scjydz\":\"冷沙\",\"sfzd\":\"B\",\"sqId\":22,\"status\":1,\"tze\":21,\"tzedw\":\"1\",\"tzzyrysl\":0,\"yye\":0,\"yyedw\":\"1\",\"zcaqgcsrys\":0,\"zcdz\":\"城阳区夏庄街道银河路272号\",\"zch\":\"91370214725572516C\",\"zsrysl\":0,\"zyfzr\":\"陈玉玉\",\"zyfzrdzyx\":\"\",\"zyfzryddhhm\":\"13325006282\",\"zzaqscglrys\":0,\"zzyjglrys\":0}","rwId":185,"sfyq":0,"status":1,"word":"2018","zgqx":1534348799000}
         * problemCount : 2
         */

        private CheckInfoBean checkInfo;
        private int problemCount;

        public CheckInfoBean getCheckInfo() {
            return checkInfo;
        }

        public void setCheckInfo(CheckInfoBean checkInfo) {
            this.checkInfo = checkInfo;
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
             * createTime : 1533000862000
             * id : 183
             * jcdwId : 2
             * jcdwlx : 2
             * jcdwmc : 东北片组
             * jcjg : 0
             * jcjssj : 1533002084000
             * jckssj : 1533001805000
             * jclx : 1
             * jcws : http://192.168.3.3:8081/hfs/source/document/20188/20180802_394_ZGTZS.pdf
             * jrzfmd : 0
             * modifyBy : 117
             * modifyTime : 1533172401000
             * number : 2-7-31-2
             * parentId : 0
             * qyId : 394
             * qyJson : {"aqfzr":"陈玉玉","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13325006282","aqjgjcjg":"安监办","aqjgszqk":2,"bzhcjsj":"2018-07-26","bzhfssj":"2018-07-26","createBy":117,"createTime":1527471604000,"cyrysl":20,"fddbr":"韩道仁","gmqk":1,"hylbdm":"C192","id":394,"isaqfzrxx":2,"isbzh":2,"islgtx":2,"isqyzc":2,"iswhp":1,"iszyfzrxx":1,"jgfl":"5","jgfljb":2,"jjlxdm":300,"jyfw":"生产皮包","lgtxcjsj":"2018-07-26","lgtxfssj":"2018-07-26","lxdh":"13608980811","modifyBy":117,"modifyTime":1531961024000,"qyfwzb":"","qyfxfj":3,"qygm":1,"qyjc":"1","qylsgx":60,"qymc":"青岛共成皮革制品有限公司","qytp":"s/source/enterprise/20187/20180719084321_348.jpg","qywzjd":120.467856,"qywzwd":36.273175,"qyzt":1,"scjydz":"冷沙","sfzd":"B","sqId":22,"status":1,"tze":21,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"城阳区夏庄街道银河路272号","zch":"91370214725572516C","zsrysl":0,"zyfzr":"陈玉玉","zyfzrdzyx":"","zyfzryddhhm":"13325006282","zzaqscglrys":0,"zzyjglrys":0}
             * rwId : 185
             * sfyq : 0
             * status : 1
             * word : 2018
             * zgqx : 1534348799000
             */

            private int createBy;
            private long createTime;
            private int id;
            private int jcdwId;
            private int jcdwlx;
            private String jcdwmc;
            private int jcjg;
            private long jcjssj;
            private long jckssj;
            private int jclx;
            private String jcws;
            private int jrzfmd;
            private int modifyBy;
            private long modifyTime;
            private String number;
            private int parentId;
            private int qyId;
            private String qyJson;
            private int rwId;
            private int sfyq;
            private int status;
            private String word;
            private long zgqx;

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

            public String getJcdwmc() {
                return jcdwmc;
            }

            public void setJcdwmc(String jcdwmc) {
                this.jcdwmc = jcdwmc;
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

            public String getJcws() {
                return jcws;
            }

            public void setJcws(String jcws) {
                this.jcws = jcws;
            }

            public int getJrzfmd() {
                return jrzfmd;
            }

            public void setJrzfmd(int jrzfmd) {
                this.jrzfmd = jrzfmd;
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

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getQyId() {
                return qyId;
            }

            public void setQyId(int qyId) {
                this.qyId = qyId;
            }

            public String getQyJson() {
                return qyJson;
            }

            public void setQyJson(String qyJson) {
                this.qyJson = qyJson;
            }

            public int getRwId() {
                return rwId;
            }

            public void setRwId(int rwId) {
                this.rwId = rwId;
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
        }
    }
}
