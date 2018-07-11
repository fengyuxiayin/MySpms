package com.example.lzc.myspms.models;

import java.util.Date;

/**
 * Created by LZC on 2017/11/30.
 */

public class EnterpriseInfoModel {

    /**
     * code : 1
     * data : true
     * msg : {"aqfzr":"高","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"15050505050","aqjgjcjg":"安监局","aqjgszqk":0,"bc":{"createBy":1,"createTime":1511926232000,"id":1,"modifyTime":1511936900000,"pqId":1,"qysl":4,"sqdz":"青岛流亭高家台","sqfwzb":"[{\"lng\":120.462152,\"lat\":36.271037},{\"lng\":120.454899,\"lat\":36.271487},{\"lng\":120.454126,\"lat\":36.266712},{\"lng\":120.461508,\"lat\":36.266436}]","sqmc":"高家台","sqmj":1000000,"sqrk":100000,"status":1},"createBy":1,"createTime":1511933882000,"dzyx":"","fddbr":"刘","gmqk":0,"hylbdm":"I65","id":1,"jgfl":"","jjlxdm":17,"jyfw":"软件制作","lxdh":"15050505050","modifyBy":1,"modifyTime":1511940021000,"qyfwzb":"[{\"lng\":120.387693,\"lat\":36.266089},{\"lng\":120.387093,\"lat\":36.268858},{\"lng\":120.391899,\"lat\":36.268858},{\"lng\":120.390698,\"lat\":36.264705}]","qygm":2,"qylsgx":90,"qymc":"龙华科道","qywzjd":120.390698,"qywzwd":36.264705,"qyzt":1,"scjydz":"空港国际","sfzd":"A","sqId":1,"status":1,"tzedw":"1","xmsl":1,"xzqhdm":266000,"yyedw":"1","zcdz":"青岛流亭","zch":"","zyfzr":"刘","zyfzrdzyx":"","zyfzrgddhhm":"","zyfzryddhhm":"15050505050","zzjgdm":""}
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

    public class EnterpriseMsgInfoModel {

        private BcBean bc;

        public BcBean getBc() {
            return bc;
        }

        public void setBc(BcBean bc) {
            this.bc = bc;
        }


        /**
         * aqfzr : 高
         * aqfzrdzyx :
         * aqfzrgddhhm :
         * aqfzryddhhm : 15050505050
         * aqjgjcjg : 安监局
         * aqjgszqk : 0
         * bc : {"createBy":1,"createTime":1511926232000,"id":1,"modifyTime":1511936900000,"pqId":1,"qysl":4,"sqdz":"青岛流亭高家台","sqfwzb":"[{\"lng\":120.462152,\"lat\":36.271037},{\"lng\":120.454899,\"lat\":36.271487},{\"lng\":120.454126,\"lat\":36.266712},{\"lng\":120.461508,\"lat\":36.266436}]","sqmc":"高家台","sqmj":1000000,"sqrk":100000,"status":1}
         * createBy : 1
         * createTime : 1511933882000
         * dzyx :
         * fddbr : 刘
         * gmqk : 0
         * hylbdm : I65
         * id : 1
         * jgfl :
         * jjlxdm : 17
         * jyfw : 软件制作
         * lxdh : 15050505050
         * modifyBy : 1
         * modifyTime : 1511940021000
         * qyfwzb : [{"lng":120.387693,"lat":36.266089},{"lng":120.387093,"lat":36.268858},{"lng":120.391899,"lat":36.268858},{"lng":120.390698,"lat":36.264705}]
         * qygm : 2
         * qylsgx : 90
         * qymc : 龙华科道
         * qywzjd : 120.390698
         * qywzwd : 36.264705
         * qyzt : 1
         * scjydz : 空港国际
         * sfzd : A
         * sqId : 1
         * status : 1
         * tzedw : 1
         * xmsl : 1
         * xzqhdm : 266000
         * yyedw : 1
         * zcdz : 青岛流亭
         * zch :
         * zyfzr : 刘
         * zyfzrdzyx :
         * zyfzrgddhhm :
         * zyfzryddhhm : 15050505050
         * zzjgdm :
         */

        private Long sqId;  //###
        /**
         * 企业的中文名称，应使用全称。应与工商部门时核准的名称
         */
        private String qymc; //###
        /**
         * 注册号，依法在政府相关部门注册登记获取开展生产经营活动资格时由批准机关赋予的编号
         */
        private String zch;    //###
        /**
         * 组织机构代码，企业依法获取全国组织机构代码管理中心赋予的全国范围内唯一且始终不变的法定代码标识
         */
        private String zzjgdm;  //###
        /**
         * 成立日期，应与工商营业执照等相关政府部门批准开展生产经营活动的资格文件所记载的成立时间一致
         */
        private Date clrq;
        /**
         * 法定代表人，应与工商营业执照等相关政府部门批准开展生产经营活动的资格文件所记载的法定代表人一致
         */
        private String fddbr; //###
        /**
         * 联系电话
         */
        private String lxdh;  //###
        /**
         * 电子邮箱，应为法定代表人的联系邮箱
         */
        private String dzyx;   //###
        /**
         * 注册地址，企业的中文地址，应与工商营业执照等相关政府部门批准开展生产经营活动的资格文件所记载的注册地址一致
         */
        private String zcdz;  //###
        /**
         * 邮政编码，应为企业注册地址所在区域的邮政编码
         */
        private Integer yzbm;   //###
        /**
         * 经济类型代码,规定了企业按单位所有制性质和经营方式划分的类别
         */
        private Integer jjlxdm;  //###
        /**
         * 行政区划代码,以在不同区域内，为全面实现地方国家机构能顺利实现各种职能而建立的不同级别政权机构作为标志，主要指注册地的所属行政区划代码
         */
        private Integer xzqhdm;  //###
        /**
         * 行业类别代码,规定了企业按国民经济的行业划分的类别
         */
        private String hylbdm;   //###
        /**
         * 企业行政隶属关系
         */
        private Integer qylsgx;  //###
        /**
         * 经营范围
         */
        private String jyfw;     //###
        /**
         * 企业状态,企业是否有效，有效指资质齐全且在有效期内，当前无违法违规处理事项
         */
        private Integer qyzt;   //###
        /**
         * 企业位置经度,采用WGS84球面坐标系表示的企业位置经度信息
         */
        private Double qywzjd;  //###
        /**
         * 企业位置纬度,采用WGS84球面坐标系表示的企业位置纬度信息
         */
        private Double qywzwd;  //###
        /**
         * 主要负责人
         */
        private String zyfzr;   //###
        /**
         * 主要负责人固定电话号码
         */
        private String zyfzrgddhhm; //###
        /**
         * 主要负责人移动电话号码
         */
        private String zyfzryddhhm;  //###
        /**
         * 主要负责人电子邮箱
         */
        private String zyfzrdzyx; //###
        /**
         * 安全负责人,在企业中负责协调整体安全生产工作的人
         */
        private String aqfzr;  //###
        /**
         * 安全负责人固定电话号码,企业分管安全生产的负责人的固定电话号码
         */
        private String aqfzrgddhhm;  //###
        /**
         * 安全负责人移动电话号码
         */
        private String aqfzryddhhm;  //###
        /**
         * 安全负责人电子邮箱
         */
        private String aqfzrdzyx;   //###
        /**
         * 安全机构设置情况,企业内部是否专门设置负责安全生产管理的部门
         */
        private Integer aqjgszqk;   //###
        /**
         * 从业人员数量
         */
        private Integer cyrysl;   //###
        /**
         * 特种作业人员数量
         */
        private Integer tzzyrysl;  //###
        /**
         * 专职安全生产管理人员数
         */
        private Integer zzaqscglrys; //###
        /**
         * 专职应急管理人员数
         */
        private Integer zzyjglrys; //###
        /**
         * 注册安全工程师人员数,企业注册执业的注册安全工程师人员数量
         */
        private Integer zcaqgcsrys; //###
        /**
         * 安全监管监察机构,安全生产监督管理部门或者煤矿安全监察机构 编码规则参照《安全生产监督管理信息全国安全生产监管监察机构代码编制规则》
         */
        private String aqjgjcjg;  //###
        /**
         * 生产/经营地址,企业的实际生产/经营地址
         */
        private String scjydz;  //###
        /**
         * 规模情况,是否为规模以上企业
         */
        private Integer gmqk;  //###
        /**
         * 企业规模,指对企业生产、经营规模的分类
         */
        private Integer qygm;  //###
        /**
         * 监管分类,指对监管业务归属及行业的划分
         */
        private String jgfl;
        /**
         * 项目数量
         */
        private Long xmsl;
        /**
         * 投资额
         */
        private Double tze;

        /**
         * 营业额
         */
        private Double yye;

        /**
         * 是否是重点企业  0-否 1-是
         */
        private String sfzd;
        /**
         * 企业范围坐标点，格式：[[120.456873, 36.258892],[120.457313, 36.25756],...]
         */
        private String qyfwzb;
        /**
         * 所属片区ID
         */
        private Long pqId;
        /**
         * 营业额单位
         */
        private String yyedw;
        /**
         * 投资额单位
         */
        private String tzedw;

        public Long getPqId() {
            return pqId;
        }

        public void setPqId(Long pqId) {
            this.pqId = pqId;
        }

        public Long getXmsl() {
            return xmsl;
        }

        public void setXmsl(Long xmsl) {
            this.xmsl = xmsl;
        }

        public Long getSqId() {
            return sqId;
        }

        public void setSqId(Long sqId) {
            this.sqId = sqId;
        }

        public String getQymc() {
            return qymc;
        }

        public void setQymc(String qymc) {
            this.qymc = qymc;
        }

        public String getZch() {
            return zch;
        }

        public void setZch(String zch) {
            this.zch = zch;
        }

        public String getZzjgdm() {
            return zzjgdm;
        }

        public void setZzjgdm(String zzjgdm) {
            this.zzjgdm = zzjgdm;
        }

        public Date getClrq() {
            return clrq;
        }

        public void setClrq(Date clrq) {
            this.clrq = clrq;
        }

        public String getFddbr() {
            return fddbr;
        }

        public void setFddbr(String fddbr) {
            this.fddbr = fddbr;
        }

        public String getLxdh() {
            return lxdh;
        }

        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }

        public String getDzyx() {
            return dzyx;
        }

        public void setDzyx(String dzyx) {
            this.dzyx = dzyx;
        }

        public String getZcdz() {
            return zcdz;
        }

        public void setZcdz(String zcdz) {
            this.zcdz = zcdz;
        }

        public Integer getYzbm() {
            return yzbm;
        }

        public void setYzbm(Integer yzbm) {
            this.yzbm = yzbm;
        }

        public Integer getJjlxdm() {
            return jjlxdm;
        }

        public void setJjlxdm(Integer jjlxdm) {
            this.jjlxdm = jjlxdm;
        }

        public Integer getXzqhdm() {
            return xzqhdm;
        }

        public void setXzqhdm(Integer xzqhdm) {
            this.xzqhdm = xzqhdm;
        }

        public String getHylbdm() {
            return hylbdm;
        }

        public void setHylbdm(String hylbdm) {
            this.hylbdm = hylbdm;
        }

        public Integer getQylsgx() {
            return qylsgx;
        }

        public void setQylsgx(Integer qylsgx) {
            this.qylsgx = qylsgx;
        }

        public String getJyfw() {
            return jyfw;
        }

        public void setJyfw(String jyfw) {
            this.jyfw = jyfw;
        }

        public Integer getQyzt() {
            return qyzt;
        }

        public void setQyzt(Integer qyzt) {
            this.qyzt = qyzt;
        }

        public Double getQywzjd() {
            return qywzjd;
        }

        public void setQywzjd(Double qywzjd) {
            this.qywzjd = qywzjd;
        }

        public Double getQywzwd() {
            return qywzwd;
        }

        public void setQywzwd(Double qywzwd) {
            this.qywzwd = qywzwd;
        }

        public String getZyfzr() {
            return zyfzr;
        }

        public void setZyfzr(String zyfzr) {
            this.zyfzr = zyfzr;
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

        public String getZyfzrdzyx() {
            return zyfzrdzyx;
        }

        public void setZyfzrdzyx(String zyfzrdzyx) {
            this.zyfzrdzyx = zyfzrdzyx;
        }

        public String getAqfzr() {
            return aqfzr;
        }

        public void setAqfzr(String aqfzr) {
            this.aqfzr = aqfzr;
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

        public String getAqfzrdzyx() {
            return aqfzrdzyx;
        }

        public void setAqfzrdzyx(String aqfzrdzyx) {
            this.aqfzrdzyx = aqfzrdzyx;
        }

        public Integer getAqjgszqk() {
            return aqjgszqk;
        }

        public void setAqjgszqk(Integer aqjgszqk) {
            this.aqjgszqk = aqjgszqk;
        }

        public Integer getCyrysl() {
            return cyrysl;
        }

        public void setCyrysl(Integer cyrysl) {
            this.cyrysl = cyrysl;
        }

        public Integer getTzzyrysl() {
            return tzzyrysl;
        }

        public void setTzzyrysl(Integer tzzyrysl) {
            this.tzzyrysl = tzzyrysl;
        }

        public Integer getZzaqscglrys() {
            return zzaqscglrys;
        }

        public void setZzaqscglrys(Integer zzaqscglrys) {
            this.zzaqscglrys = zzaqscglrys;
        }

        public Integer getZzyjglrys() {
            return zzyjglrys;
        }

        public void setZzyjglrys(Integer zzyjglrys) {
            this.zzyjglrys = zzyjglrys;
        }

        public Integer getZcaqgcsrys() {
            return zcaqgcsrys;
        }

        public void setZcaqgcsrys(Integer zcaqgcsrys) {
            this.zcaqgcsrys = zcaqgcsrys;
        }

        public String getAqjgjcjg() {
            return aqjgjcjg;
        }

        public void setAqjgjcjg(String aqjgjcjg) {
            this.aqjgjcjg = aqjgjcjg;
        }

        public String getScjydz() {
            return scjydz;
        }

        public void setScjydz(String scjydz) {
            this.scjydz = scjydz;
        }

        public Integer getGmqk() {
            return gmqk;
        }

        public void setGmqk(Integer gmqk) {
            this.gmqk = gmqk;
        }

        public Integer getQygm() {
            return qygm;
        }

        public void setQygm(Integer qygm) {
            this.qygm = qygm;
        }

        public String getJgfl() {
            return jgfl;
        }

        public void setJgfl(String jgfl) {
            this.jgfl = jgfl;
        }

        public Double getTze() {
            return tze;
        }

        public void setTze(Double tze) {
            this.tze = tze;
        }

        public Double getYye() {
            return yye;
        }

        public void setYye(Double yye) {
            this.yye = yye;
        }

        public String getSfzd() {
            return sfzd;
        }

        public void setSfzd(String sfzd) {
            this.sfzd = sfzd;
        }

        public String getQyfwzb() {
            return qyfwzb;
        }

        public void setQyfwzb(String qyfwzb) {
            this.qyfwzb = qyfwzb;
        }

        public String getYyedw() {
            return yyedw;
        }

        public void setYyedw(String yyedw) {
            this.yyedw = yyedw;
        }

        public String getTzedw() {
            return tzedw;
        }

        public void setTzedw(String tzedw) {
            this.tzedw = tzedw;
        }



        public class BcBean {
            /**
             * createBy : 1
             * createTime : 1511926232000
             * id : 1
             * modifyTime : 1511936900000
             * pqId : 1
             * qysl : 4
             * sqdz : 青岛流亭高家台
             * sqfwzb : [{"lng":120.462152,"lat":36.271037},{"lng":120.454899,"lat":36.271487},{"lng":120.454126,"lat":36.266712},{"lng":120.461508,"lat":36.266436}]
             * sqmc : 高家台
             * sqmj : 1000000
             * sqrk : 100000
             * status : 1
             */

            private int createBy;
            private long createTime;
            private int id;
            private long modifyTime;
            private int pqId;
            private int qysl;
            private String sqdz;
            private String sqfwzb;
            private String sqmc;
            private int sqmj;
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

            public int getSqmj() {
                return sqmj;
            }

            public void setSqmj(int sqmj) {
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
