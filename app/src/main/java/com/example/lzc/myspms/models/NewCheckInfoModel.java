package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 *
 *@desc 新的检查页面的信息
 *@author NewCheckInfoModel.java
 *@date  2017/12/510:17
 */

public class NewCheckInfoModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"list":[{"bzqsj":1514649600000,"createBy":1,"createTime":1512116217000,"id":6,"jcd":"4,5,7,86,87,88","jcdwId":1,"jcdwlx":2,"jclx":1,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1512057600000,\"endDate\":1514649600000,\"id\":4,\"jcd\":\"4,5,7,86,87,88\",\"jcdwlx\":2,\"jcnf\":2017,\"jczq\":1,\"jczqdw\":3,\"modifyBy\":1,\"modifyTime\":1512116211000,\"publishStatus\":0,\"startDate\":1506787200000,\"status\":1,\"xmlx\":\"1\"}","jczt":0,"modifyTime":1512116217000,"qyId":1,"qymc":"龙华科道","status":1,"szqsj":1506787200000},{"bzqsj":1514649600000,"createBy":1,"createTime":1512116218000,"id":7,"jcd":"4,5,7,86,87,88","jcdwId":1,"jcdwlx":2,"jclx":1,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1512057600000,\"endDate\":1514649600000,\"id\":4,\"jcd\":\"4,5,7,86,87,88\",\"jcdwlx\":2,\"jcnf\":2017,\"jczq\":1,\"jczqdw\":3,\"modifyBy\":1,\"modifyTime\":1512116211000,\"publishStatus\":0,\"startDate\":1506787200000,\"status\":1,\"xmlx\":\"1\"}","jczt":0,"modifyTime":1512116218000,"qyId":2,"qymc":"C企业","status":1,"szqsj":1506787200000},{"bzqsj":1514649600000,"createBy":1,"createTime":1512116218000,"id":8,"jcd":"4,5,7,86,87,88","jcdwId":1,"jcdwlx":2,"jclx":1,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1512057600000,\"endDate\":1514649600000,\"id\":4,\"jcd\":\"4,5,7,86,87,88\",\"jcdwlx\":2,\"jcnf\":2017,\"jczq\":1,\"jczqdw\":3,\"modifyBy\":1,\"modifyTime\":1512116211000,\"publishStatus\":0,\"startDate\":1506787200000,\"status\":1,\"xmlx\":\"1\"}","jczt":0,"modifyTime":1512116218000,"qyId":3,"qymc":"B企业","status":1,"szqsj":1506787200000},{"bzqsj":1514649600000,"createBy":1,"createTime":1512116219000,"id":9,"jcd":"4,5,7,86,87,88","jcdwId":1,"jcdwlx":2,"jclx":1,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1512057600000,\"endDate\":1514649600000,\"id\":4,\"jcd\":\"4,5,7,86,87,88\",\"jcdwlx\":2,\"jcnf\":2017,\"jczq\":1,\"jczqdw\":3,\"modifyBy\":1,\"modifyTime\":1512116211000,\"publishStatus\":0,\"startDate\":1506787200000,\"status\":1,\"xmlx\":\"1\"}","jczt":0,"modifyTime":1512116219000,"qyId":4,"qymc":"A企业","status":1,"szqsj":1506787200000},{"bzqsj":1514735999000,"createBy":1,"createTime":1512120171000,"id":10,"jcdwId":1,"jcdwlx":2,"jclx":2,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1512057600000,\"endDate\":1514649600000,\"id\":4,\"jcd\":\"4,5,7,86,87,88\",\"jcdwlx\":2,\"jcnf\":2017,\"jczq\":1,\"jczqdw\":3,\"modifyBy\":1,\"modifyTime\":1512116211000,\"publishStatus\":0,\"startDate\":1506787200000,\"status\":1,\"xmlx\":\"1\"}","jczt":0,"modifyTime":1512120185000,"qyId":2,"qymc":"C企业","status":1,"szqsj":1506787200000},{"bzqsj":1513871999000,"createBy":1,"createTime":1512380475000,"id":11,"jcdwId":1,"jcdwlx":2,"jclx":2,"jcsId":4,"jcsJson":"{\"createBy\":1,\"createTime\":1512057600000,\"endDate\":1514649600000,\"id\":4,\"jcd\":\"4,5,7,86,87,88\",\"jcdwlx\":2,\"jcnf\":2017,\"jczq\":1,\"jczqdw\":3,\"modifyBy\":1,\"modifyTime\":1512116211000,\"publishStatus\":0,\"startDate\":1506787200000,\"status\":1,\"xmlx\":\"1\"}","jczt":0,"modifyTime":1512380491000,"qyId":1,"qymc":"龙华科道","status":1,"szqsj":1506787200000}],"pn":1,"size":10,"total":6}
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
    public class NewCheckMsgInfoModel implements Serializable{

        /**
         * list : [{"createBy":1,"createTime":1527126906000,"endDate":null,"id":7,"jcdwId":1,"jcdwlx":2,"jcjg":0,"jcjssj":null,"jckssj":null,"jclx":1,"jcws":null,"jgfj":"A","jl":6.880648580516911,"jzsj":1530288000000,"kssj":1527782400000,"modifyBy":null,"modifyTime":null,"number":"1-5-24-1","qyId":2,"qyJson":"{\"aqfzr\":\"傅建国\",\"aqfzrdzyx\":\"13954298777@163.com\",\"aqfzrgddhhm\":\"\",\"aqfzryddhhm\":\"13506484991\",\"aqjgjcjg\":\"夏庄街道安监办\",\"aqjgszqk\":1,\"createBy\":1,\"createTime\":1523273447000,\"cyrysl\":4,\"dzyx\":\"\",\"fddbr\":\"傅建国\",\"gmqk\":0,\"hylbdm\":\"F5162\",\"id\":2,\"jgfl\":\"B003\",\"jjlxdm\":17,\"jyfw\":\"石油及制品批发\",\"lxdh\":\"13506484991\",\"modifyBy\":1,\"modifyTime\":1525684668000,\"qyfwzb\":\"[{\\\"lng\\\":120.457785,\\\"lat\\\":36.262993},{\\\"lng\\\":120.458096,\\\"lat\\\":36.263702},{\\\"lng\\\":120.459684,\\\"lat\\\":36.263425},{\\\"lng\\\":120.459491,\\\"lat\\\":36.262733}]\",\"qygm\":3,\"qylsgx\":63,\"qymc\":\"鑫泉加油站\",\"qywzjd\":120.44692,\"qywzwd\":36.23761,\"qyzt\":1,\"scjydz\":\"中国山东省青岛市城阳区王沙路53号\",\"sfzd\":\"A\",\"sqId\":3,\"status\":1,\"tze\":100,\"tzedw\":\"1\",\"tzzyrysl\":0,\"wgfzr\":\"\",\"wgfzrgddhhm\":\"\",\"wgfzryddhhm\":\"\",\"xmsl\":9,\"xzqhdm\":123456,\"yye\":13,\"yyedw\":\"1\",\"zcaqgcsrys\":1,\"zcdz\":\"中国山东省青岛市城阳区王沙路53号\",\"zch\":\"1273325\",\"zsrysl\":1,\"zyfzr\":\"傅建国\",\"zyfzrdzyx\":\"13954298777@163.com\",\"zyfzrgddhhm\":\"\",\"zyfzryddhhm\":\"13506484991\",\"zzaqscglrys\":1,\"zzjgdm\":\"123443333\",\"zzyjglrys\":1}","rwId":1,"rwmc":"鑫泉加油站2018年6月突击检查","rwzt":0,"sfyq":0,"startDate":null,"status":1,"word":"2018","xmsl":25,"zgqx":null,"zgzj":null}]
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
             * createBy : 1
             * createTime : 1527126906000
             * endDate : null
             * id : 7
             * jcdwId : 1
             * jcdwlx : 2
             * jcjg : 0
             * jcjssj : null
             * jckssj : null
             * jclx : 1
             * jcws : null
             * jgfj : A
             * jl : 6.880648580516911
             * jzsj : 1530288000000
             * kssj : 1527782400000
             * modifyBy : null
             * modifyTime : null
             * number : 1-5-24-1
             * qyId : 2
             * qyJson : {"aqfzr":"傅建国","aqfzrdzyx":"13954298777@163.com","aqfzrgddhhm":"","aqfzryddhhm":"13506484991","aqjgjcjg":"夏庄街道安监办","aqjgszqk":1,"createBy":1,"createTime":1523273447000,"cyrysl":4,"dzyx":"","fddbr":"傅建国","gmqk":0,"hylbdm":"F5162","id":2,"jgfl":"B003","jjlxdm":17,"jyfw":"石油及制品批发","lxdh":"13506484991","modifyBy":1,"modifyTime":1525684668000,"qyfwzb":"[{\"lng\":120.457785,\"lat\":36.262993},{\"lng\":120.458096,\"lat\":36.263702},{\"lng\":120.459684,\"lat\":36.263425},{\"lng\":120.459491,\"lat\":36.262733}]","qygm":3,"qylsgx":63,"qymc":"鑫泉加油站","qywzjd":120.44692,"qywzwd":36.23761,"qyzt":1,"scjydz":"中国山东省青岛市城阳区王沙路53号","sfzd":"A","sqId":3,"status":1,"tze":100,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xmsl":9,"xzqhdm":123456,"yye":13,"yyedw":"1","zcaqgcsrys":1,"zcdz":"中国山东省青岛市城阳区王沙路53号","zch":"1273325","zsrysl":1,"zyfzr":"傅建国","zyfzrdzyx":"13954298777@163.com","zyfzrgddhhm":"","zyfzryddhhm":"13506484991","zzaqscglrys":1,"zzjgdm":"123443333","zzyjglrys":1}
             * rwId : 1
             * rwmc : 鑫泉加油站2018年6月突击检查
             * rwzt : 0
             * sfyq : 0
             * startDate : null
             * status : 1
             * word : 2018
             * xmsl : 25
             * zgqx : null
             * zgzj : null
             */

            private int createBy;
            private long createTime;
            private Object endDate;
            private int id;
            private int jcdwId;
            private int jcdwlx;
            private int jcjg;
            private long jcjssj;
            private long jckssj;
            private int jclx;
            private Object jcws;
            private String jgfj;
            private double jl;
            private long jzsj;
            private long kssj;
            private Object modifyBy;
            private Object modifyTime;
            private String number;
            private int qyId;
            private String qyJson;
            private int rwId;
            private String rwmc;
            private int rwzt;
            private int sfyq;
            private Object startDate;
            private int status;
            private String word;
            private int xmsl;
            private long zgqx;
            private Object zgzj;
            private int unPassCount;
            private int jcbzId;

            public int getJcbzId() {
                return jcbzId;
            }

            public void setJcbzId(int jcbzId) {
                this.jcbzId = jcbzId;
            }

            public int getUnPassCount() {
                return unPassCount;
            }

            public void setUnPassCount(int unPassCount) {
                this.unPassCount = unPassCount;
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

            public Object getEndDate() {
                return endDate;
            }

            public void setEndDate(Object endDate) {
                this.endDate = endDate;
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

            public Object getJcws() {
                return jcws;
            }

            public void setJcws(Object jcws) {
                this.jcws = jcws;
            }

            public String getJgfj() {
                return jgfj;
            }

            public void setJgfj(String jgfj) {
                this.jgfj = jgfj;
            }

            public double getJl() {
                return jl;
            }

            public void setJl(double jl) {
                this.jl = jl;
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

            public String getRwmc() {
                return rwmc;
            }

            public void setRwmc(String rwmc) {
                this.rwmc = rwmc;
            }

            public int getRwzt() {
                return rwzt;
            }

            public void setRwzt(int rwzt) {
                this.rwzt = rwzt;
            }

            public int getSfyq() {
                return sfyq;
            }

            public void setSfyq(int sfyq) {
                this.sfyq = sfyq;
            }

            public Object getStartDate() {
                return startDate;
            }

            public void setStartDate(Object startDate) {
                this.startDate = startDate;
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

            public int getXmsl() {
                return xmsl;
            }

            public void setXmsl(int xmsl) {
                this.xmsl = xmsl;
            }

            public long getZgqx() {
                return zgqx;
            }

            public void setZgqx(long zgqx) {
                this.zgqx = zgqx;
            }

            public Object getZgzj() {
                return zgzj;
            }

            public void setZgzj(Object zgzj) {
                this.zgzj = zgzj;
            }
        }
    }
}
