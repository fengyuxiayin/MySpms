package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/12/13.
 */

public class CommunityBasicInfoModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"createBy":1,"createTime":1511926232000,"id":1,"modifyBy":1,"modifyTime":1512976403000,"pqId":1,"qysl":6,"sqdz":"青岛流亭高家台","sqfwzb":"[{\"lng\":120.462152,\"lat\":36.271037},{\"lng\":120.454899,\"lat\":36.271487},{\"lng\":120.454126,\"lat\":36.266712},{\"lng\":120.458761,\"lat\":36.26429},{\"lng\":120.461508,\"lat\":36.266436}]","sqmc":"高家台","sqmj":1000000,"sqrk":10000,"status":1}
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
    public class CommunityBasicInfoMsgModel implements Serializable{

        /**
         * aqfzr : asd
         * aqfzrbm :
         * aqfzrlxdh : 15050505050
         * aqfzrzc : 123
         * bel : [{"aqfzr":"崔国松","aqfzrdzyx":"","aqfzrgddhhm":"053267731834","aqfzryddhhm":"18953209567","aqjgjcjg":"夏庄街道安监办","aqjgszqk":0,"bzhcjsj":"2018-05-28","bzhfj":0,"bzhfssj":"2018-05-28","createBy":1,"createTime":1510040938000,"cyrysl":6,"dzyx":"","fddbr":"崔国松","gmqk":0,"hylbdm":"C18","id":6,"jgfljb":1,"jjlxdm":10,"jyfw":"生产、加工、销售：服装及辅料、鞋及辅料、纺织品、工艺品、包装材料；货物进出口、技术进出口。（依法须经批准的项目，经相关部门批准后方可开展经营活动）","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"18953209567","modifyBy":1,"modifyTime":1525831177000,"qyfwzb":"[]","qyfxfj":4,"qygm":1,"qylsgx":61,"qymc":"青岛美金服装有限公司","qytp":"","qywzjd":120.111111,"qywzwd":39.111111,"qyzt":1,"scjydz":"山东省青岛市城阳区夏庄街道中黄埠社区中兴路9号","sfzd":"","sqId":2,"status":1,"tze":50,"tzedw":"1","tzzyrysl":0,"wgfzr":"崔国松","wgfzrdzyx":"","wgfzrgddhhm":"18953209567","wgfzryddhhm":"","xzqhdm":370214,"yyedw":"1","zcaqgcsrys":0,"zcdz":"山东省青岛市城阳区夏庄街道中黄埠社区中兴路9号","zch":"91370214MA3CCF237A","zyfzr":"崔国松","zyfzrdzyx":"","zyfzrgddhhm":"053267731834","zyfzryddhhm":"18953209567","zzaqscglrys":0,"zzjgdm":"MA3CCF237","zzyjglrys":0},{"aqfzr":"李海涛","aqfzrdzyx":"","aqfzrgddhhm":"0532-84717729","aqfzryddhhm":"18663933957","aqjgjcjg":"夏庄街道安监办","aqjgszqk":0,"bzhcjsj":"2018-05-28","bzhfj":0,"bzhfssj":"2018-05-28","createBy":1,"createTime":1510042371000,"cyrysl":18,"dzyx":"624111780@qq.com","fddbr":"高学文","gmqk":0,"hylbdm":"C35","id":14,"jgfljb":1,"jjlxdm":10,"jyfw":"一般经营项目:加工：纺织机械、橡胶机械、纺织品、塑料制品、工艺品；零售：机电产品。 （以上范围需经许可经营的，须凭许可证经营）。","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"13608964971","modifyBy":1,"modifyTime":1525830847000,"qyfwzb":"[]","qyfxfj":4,"qygm":1,"qylsgx":62,"qymc":"青岛城阳华祥纺织机械厂","qytp":"","qywzjd":120.443419,"qywzwd":36.262069,"qyzt":1,"scjydz":"青岛市城阳区青岛市城阳区夏庄街道华夏路8-16号","sfzd":"","sqId":2,"status":1,"tze":20,"tzedw":"1","wgfzr":"高学文","wgfzrdzyx":"13608964971","wgfzrgddhhm":"","wgfzryddhhm":"","xzqhdm":370214,"yyedw":"1","yzbm":266000,"zcdz":"青岛市城阳区青岛市城阳区夏庄街道华夏路8-16号","zch":"91370214264839889J","zyfzr":"高学文","zyfzrdzyx":"624111780@qq.com","zyfzrgddhhm":"0532-84717729","zyfzryddhhm":"13608964971","zzjgdm":"264839889"},{"aqfzr":"朱义强","aqfzrdzyx":"13789860748@163.com","aqfzrgddhhm":"053289081665","aqfzryddhhm":"13789860748","aqjgjcjg":"夏庄街道安监办","aqjgszqk":1,"bzhcjsj":"2018-05-24","bzhfj":0,"bzhfssj":"2018-05-24","createBy":1,"createTime":1510051230000,"cyrysl":10,"dzyx":"","fddbr":"朱义祥","gmqk":2,"hylbdm":"C201","id":27,"isaqfzrxx":1,"isbzh":2,"islgtx":2,"iswhp":2,"iszyfzrxx":1,"jgfljb":1,"jjlxdm":10,"jyfw":"生产、加工、研发、批发、零售：木材、板材、家具、建筑材料、装饰装潢材料、五金配件、厨卫用品、实验室设备；上门安装：厨卫设备（不含特种设备）；室内外装饰装潢。 （依法须经批准的项目，经相关部门批准后方可开展经营活动）。","lgtxcjsj":"2018-05-24","lgtxfssj":"2018-05-24","lxdh":"13789860748","modifyBy":116,"modifyTime":1527144292000,"qyfwzb":"","qyfxfj":4,"qygm":2,"qylsgx":61,"qymc":"青岛帝威亚木业有限公司","qytp":"","qywzjd":120.43655,"qywzwd":36.255342,"qyzt":1,"scjydz":"山东省青岛市城阳区中黄埠社区华夏路8-10号","sfzd":"","sqId":2,"status":1,"tze":100,"tzedw":"1","tzzyrysl":0,"wgfzr":"朱义强","wgfzrdzyx":"","wgfzrgddhhm":"053289081665","wgfzryddhhm":"13789860748","xzqhdm":370214,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"山东省青岛市城阳区中黄埠社区华夏路8-10号","zch":"913702140967102684","zsrysl":0,"zyfzr":"朱义祥","zyfzrdzyx":"13789860748@163.com","zyfzrgddhhm":"053289081665","zyfzryddhhm":"13789860748","zzaqscglrys":1,"zzjgdm":"096710268","zzyjglrys":0},{"aqfzr":"刘建华","aqfzrdzyx":"xiezhong.86@163.com","aqfzrgddhhm":"0532-55787015","aqfzryddhhm":"13697688759","aqjgjcjg":"夏庄街道安监办","aqjgszqk":1,"bzhcjsj":"2018-07-03","bzhfj":1,"bzhfssj":"2018-07-03","createBy":1,"createTime":1510060787000,"cyrysl":270,"dzyx":"","fddbr":"曹建","gmqk":1,"hylbdm":"C35","id":36,"isaqfzrxx":1,"isbzh":1,"islgtx":1,"iswhp":0,"iszyfzrxx":1,"jgfljb":1,"jjlxdm":100,"jyfw":"模具、塑料注射机械、塑料制品、五金、汽车塑料配件；模具、机械设备、塑料制品技术的研发及相关服务。 （依法须经批准的项目，经相关部门批准后方可开展经营活动）。","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"13697688759","modifyBy":116,"modifyTime":1527046837000,"qyfwzb":"[]","qyfxfj":4,"qygm":4,"qylsgx":60,"qymc":"青岛华涛汽车模具有限公司","qytp":"","qywzjd":120.449233,"qywzwd":36.262209,"qyzt":1,"scjydz":"青岛市城阳区夏庄街道中黄埠社区华安路11号东100米","sfzd":"A","sqId":2,"status":1,"tze":4479.33,"tzedw":"1","tzzyrysl":3,"wgfzr":"陈延民","wgfzrdzyx":"xiezhong.86@163.com","wgfzrgddhhm":"13697688759","wgfzryddhhm":"55787092","xzqhdm":370214,"yyedw":"1","yzbm":266000,"zcaqgcsrys":0,"zcdz":"青岛市市北区重庆南路67号","zch":"91370200614393013B","zsrysl":0,"zyfzr":"陈延民","zyfzrdzyx":"xiezhang.86@163.com","zyfzrgddhhm":"0532-55787092","zyfzryddhhm":"13697688759","zzaqscglrys":2,"zzjgdm":"614393013","zzyjglrys":2},{"aqfzr":"王萌","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13585870543","aqjgjcjg":"安监办","aqjgszqk":1,"bzhcjsj":"2018-05-28","bzhfssj":"2018-05-28","createBy":1,"createTime":1510106859000,"cyrysl":12,"dzyx":"","fddbr":"陈志峰","gmqk":0,"hylbdm":"F51","id":38,"jjlxdm":33,"jyfw":"\t批发兼零售预包装食品（食品流通许可证 有效期至：2015-09-27）。自动售货机的批发、进出口、佣金代理（不含拍卖），并提供相关配套服务；企业营销策划咨询（涉及行政许可的，凭许可证经营）。 （以上范围需经许可经营的，须凭许可证经营）。","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"13585870543","modifyTime":1510106859000,"qyfwzb":"[]","qygm":1,"qylsgx":61,"qymc":"上海米源饮料有限公司青岛分公司","qytp":"","qywzjd":45.123456,"qywzwd":45.123456,"qyzt":1,"scjydz":"青岛市保税区北京路60号亿达发展大厦8层809A室","sqId":2,"status":1,"tze":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xzqhdm":370214,"zcdz":"青岛市保税区北京路60号亿达发展大厦8层809A室","zch":"913702000572629929","zyfzr":"陈志峰","zyfzrdzyx":"","zyfzrgddhhm":"053287721295","zyfzryddhhm":"13585870543","zzjgdm":"057262992"},{"aqfzr":"马克才","aqfzrdzyx":"无","aqfzrgddhhm":"87933127","aqfzryddhhm":"13969718080","aqjgjcjg":"夏庄街道安监办","aqjgszqk":1,"bzhcjsj":"2018-05-28","bzhfj":0,"bzhfssj":"2018-05-28","createBy":1,"createTime":1510108143000,"cyrysl":11,"dzyx":"","fddbr":"马克才","gmqk":0,"hylbdm":"C21","id":42,"isaqfzrxx":2,"isbzh":2,"islgtx":2,"iswhp":0,"iszyfzrxx":2,"jgfljb":1,"jjlxdm":10,"jyfw":"批发、零售：办公家具、木制品、铁制品、厨具、办公用品、保险柜、消防柜、消防箱、展柜、货架、门窗、铝合金制品、五金交电、日用百货、建筑材料、装饰材料、钢材、木材、塑料制品、电子产品、玻璃制品、机械设备、实验室设备；生产、加工：消防柜、消防箱；办公家具设计、加工、安装；货物进出口、技术进出口。（依法须经批准的项目，经相关部门批准后方可开展经营活动）","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"13969718080","modifyBy":116,"modifyTime":1526978326000,"qyfwzb":"[]","qyfxfj":1,"qygm":1,"qylsgx":62,"qymc":"青岛万德利办公家具有限公司","qytp":"","qywzjd":120.439372,"qywzwd":36.25888,"qyzt":1,"scjydz":"山东省青岛市城阳区夏庄街道中黄埠社区华夏路8-7号","sfzd":"","sqId":2,"status":1,"tze":600,"tzedw":"1","tzzyrysl":0,"wgfzr":"王旭峰","wgfzrdzyx":"无","wgfzrgddhhm":"87933127","wgfzryddhhm":"15978638017","xzqhdm":370214,"yye":50,"yyedw":"1","yzbm":266000,"zcaqgcsrys":0,"zcdz":"山东省青岛市城阳区夏庄街道中黄埠社区华夏路8-7号","zch":"9137021433412517X6","zsrysl":4,"zyfzr":"马克才","zyfzrdzyx":"无","zyfzrgddhhm":"87933127","zyfzryddhhm":"13969718080","zzaqscglrys":1,"zzjgdm":"33412517X","zzyjglrys":0},{"aqfzr":"李庆奎","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"18562589796","aqjgjcjg":"夏庄街道安监办","aqjgszqk":0,"bzhcjsj":"2018-05-28","bzhfssj":"2018-05-28","createBy":1,"createTime":1510109991000,"cyrysl":6,"dzyx":"","fddbr":"姬建当","gmqk":0,"hylbdm":"C201","id":50,"jjlxdm":10,"jyfw":"设计、生产、安装：家具、门、地板、厨房橱柜；批发、零售：家用电器、五金配件、装饰材料；建筑装修装饰工程施工（凭资质经营）。（依法须经批准的项目，经相关部门批准后方可开展经营活动）","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"18561619388","modifyTime":1510109991000,"qyfwzb":"[]","qygm":1,"qylsgx":61,"qymc":"青岛裕合众森木业有限公司","qytp":"","qywzjd":120.111111,"qywzwd":39.111111,"qyzt":1,"scjydz":"山东省青岛市城阳区夏庄街道新民村社区居委会西100米","sfzd":"","sqId":2,"status":1,"tze":100,"tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xzqhdm":370214,"zcaqgcsrys":0,"zcdz":"山东省青岛市城阳区夏庄街道新民村社区居委会西100米","zch":"91370214MA3DBUCC08","zyfzr":"姬建当","zyfzrdzyx":"","zyfzrgddhhm":"","zyfzryddhhm":"18561619388","zzaqscglrys":0,"zzjgdm":"MA3DBUCC0","zzyjglrys":0},{"aqfzr":"李单华","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13864241599","aqjgjcjg":"夏庄街道安监办","aqjgszqk":0,"bzhcjsj":"2018-05-28","bzhfssj":"2018-05-28","createBy":1,"createTime":1510110770000,"cyrysl":17,"dzyx":"","fddbr":"武云海","gmqk":0,"hylbdm":"C20","id":55,"jjlxdm":10,"jyfw":"加工木制品；制造：家用厨房电器具：批发、零售：厨具、家用电器、灯具、塑料制品、橡胶制品、办公用品。 （依法须经批准的项目，经相关部门批准后方可开展经营活动）。","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"13864241599","modifyTime":1510110770000,"qyfwzb":"[]","qygm":1,"qylsgx":61,"qymc":"青岛雅嘉木业有限公司","qytp":"","qywzjd":120.111111,"qywzwd":39.111111,"qyzt":1,"scjydz":"青岛市城阳区夏庄街道王家曹村社区","sfzd":"","sqId":2,"status":1,"tze":800,"tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xzqhdm":370214,"zcaqgcsrys":0,"zcdz":"青岛市城阳区夏庄街道王家曹村社区","zch":"913702147537693080","zyfzr":"武云海","zyfzrdzyx":"463959733@qq.com","zyfzrgddhhm":"","zyfzryddhhm":"13706348098","zzaqscglrys":0,"zzjgdm":"753769308","zzyjglrys":0},{"aqfzr":"王正成","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"18561552151","aqjgjcjg":"夏庄街道安监办","aqjgszqk":0,"bzhcjsj":"2018-05-28","bzhfssj":"2018-05-28","createBy":1,"createTime":1510111038000,"cyrysl":9,"dzyx":"","fddbr":"王正成","gmqk":0,"hylbdm":"C29","id":57,"jjlxdm":10,"jyfw":"一般经营项目:生产、销售：橡塑制品及配件；批发、零售：电子称、水泵、五金配件；货物进出口、技术进出口（法律、行政法规禁止的项目除外，法律、行政法规限制的项目应取得许可证方可经营）。 （以上范围需经许可经营的，须凭许可证经营）。","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"18561552151","modifyTime":1510111038000,"qyfwzb":"[]","qygm":1,"qylsgx":61,"qymc":"青岛东沃橡塑制品有限公司","qytp":"","qywzjd":120.111111,"qywzwd":39.111111,"qyzt":1,"scjydz":"青岛市城阳区夏庄街道王家曹村社区美食水岸南50米","sfzd":"","sqId":2,"status":1,"tze":50,"tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xzqhdm":370214,"zcaqgcsrys":0,"zcdz":"青岛市城阳区夏庄街道王家曹村社区居委会西北300米","zch":"91370214587811397G","zyfzr":"王正成","zyfzrdzyx":"sales@doowin-dooflex.com","zyfzrgddhhm":"053287788178","zyfzryddhhm":"18561552151","zzaqscglrys":0,"zzjgdm":"587811397","zzyjglrys":0},{"aqfzr":"刘云桥","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13145322119","aqjgjcjg":"安监办","aqjgszqk":2,"bzhcjsj":"2018-05-24","bzhfssj":"2018-05-24","createBy":1,"createTime":1510111504000,"cyrysl":8,"dzyx":"","fddbr":"田立强","gmqk":2,"hylbdm":"C30","id":59,"isaqfzrxx":2,"islgtx":2,"iswhp":2,"iszyfzrxx":2,"jgfl":"5","jgfljb":2,"jjlxdm":150,"jyfw":"一般经营项目:生产、加工：玻璃制品及玻璃设备。 （以上范围需经许可经营的，须凭许可证经营）","lgtxcjsj":"2018-05-24","lgtxfssj":"2018-05-24","lxdh":"13553089188","modifyBy":117,"modifyTime":1527126994000,"qyfwzb":"","qyfxfj":3,"qygm":1,"qylsgx":60,"qymc":"青岛圣意达玻璃有限公司城阳加工厂","qytp":"","qywzjd":120.4742,"qywzwd":36.28817,"qyzt":1,"scjydz":"青岛市城阳区夏庄街道王家曹村社区东侧","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xzqhdm":370214,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"青岛市城阳区夏庄街道王家曹村社区东侧","zch":"91370214MA3CU4JW0X","zsrysl":0,"zyfzr":"田立强","zyfzrdzyx":"","zyfzrgddhhm":"05320000000","zyfzryddhhm":"13553089188","zzaqscglrys":0,"zzjgdm":"MA3CU4JW0","zzyjglrys":0},{"aqfzr":"李德杰","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13780661317","aqjgjcjg":"安监办","aqjgszqk":2,"bzhcjsj":"2018-05-24","bzhfssj":"2018-05-24","createBy":1,"createTime":1510122794000,"cyrysl":3,"dzyx":"","fddbr":"李德杰","gmqk":2,"hylbdm":"C22","id":81,"isaqfzrxx":2,"islgtx":2,"iswhp":2,"iszyfzrxx":2,"jgfl":"5","jgfljb":3,"jjlxdm":150,"jyfw":"生产、加工：纸制品、包装袋、工艺品、玩具；批发、零售：服装、鞋帽、棉纱；货物进出口、技术进出口。（依法须经批准的项目，经相关部门批准后方可开展经营活动）","lgtxcjsj":"2018-05-24","lgtxfssj":"2018-05-24","lxdh":"13780661317","modifyBy":117,"modifyTime":1527126591000,"qyfwzb":"","qyfxfj":3,"qygm":1,"qylsgx":60,"qymc":"青岛鑫德盛包装有限公司","qytp":"","qywzjd":120.47307,"qywzwd":36.288001,"qyzt":1,"scjydz":"生产、加工：纸制品、包装袋、工艺品、玩具；批发、零售：服装、鞋帽、棉纱；货物进出口、技术进出口。（依法须经批准的项目，经相关部门批准后方可开展经营活动）","sqId":2,"status":1,"tze":50,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xzqhdm":370214,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"山东省青岛市城阳区夏庄街道王家曹村社区","zch":"91370214334140118A","zsrysl":0,"zyfzr":"李德杰","zyfzrdzyx":"","zyfzrgddhhm":"","zyfzryddhhm":"13780661317","zzaqscglrys":0,"zzjgdm":"334140118","zzyjglrys":0},{"aqfzr":"安洙连","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13678860216","aqjgjcjg":"夏庄街道安监办","aqjgszqk":0,"bzhcjsj":"2018-05-28","bzhfssj":"2018-05-28","createBy":1,"createTime":1510122798000,"cyrysl":10,"dzyx":"","fddbr":"安洙连","gmqk":0,"hylbdm":"C3525","id":82,"jjlxdm":17,"jyfw":"模具加工。(以上范围需经许可经营的,须凭许可证经营)","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"13678860216","modifyTime":1510205673000,"qyfwzb":"[]","qygm":1,"qylsgx":61,"qymc":"城阳区诚德信模具厂","qytp":"","qywzjd":120.111111,"qywzwd":39.111111,"qyzt":1,"scjydz":"青岛市城阳区夏庄街道王家曹村","sfzd":"","sqId":2,"status":1,"tze":0,"tzzyrysl":0,"wgfzr":"","wgfzrgddhhm":"","wgfzryddhhm":"","xzqhdm":370214,"zcaqgcsrys":0,"zcdz":"青岛市城阳区夏庄街道王家曹村","zch":"370214600540781","zyfzr":"安洙连","zyfzrdzyx":"","zyfzrgddhhm":"053284718707","zyfzryddhhm":"13678860216","zzaqscglrys":0,"zzjgdm":"","zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":115,"createTime":1526714608000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":191,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"","modifyTime":1526714608000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛杰玛工艺品有限公司","qytp":"","qywzjd":120.44455,"qywzwd":36.243931,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":115,"createTime":1526714722000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":192,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"","modifyTime":1526714722000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛博信印通包装有限公司","qytp":"","qywzjd":120.44463,"qywzwd":36.243931,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":115,"createTime":1526715227000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":196,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"","modifyTime":1526715227000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛徳元宏电子科技有限公司","qytp":"","qywzjd":120.44491,"qywzwd":36.244157,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":115,"createTime":1526715737000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":197,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"","modifyTime":1526715737000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛众瑞智能仪器有限公司","qytp":"","qywzjd":120.44401,"qywzwd":36.244221,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"王华阳","aqfzrdzyx":"","aqfzrgddhhm":"13395320098","aqfzryddhhm":"13395320098","aqjgjcjg":"夏庄街道安监办","aqjgszqk":1,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526719745000,"cyrysl":3,"dzyx":"","fddbr":"王华阳","gmqk":0,"hylbdm":"C347","id":203,"isaqfzrxx":2,"isbzh":2,"islgtx":2,"iswhp":0,"iszyfzrxx":2,"jgfljb":4,"jjlxdm":100,"jyfw":"防水透气产品及塑胶产品的开发，","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"13395320098","modifyBy":116,"modifyTime":1527046194000,"qyfwzb":"[]","qyfxfj":4,"qygm":1,"qylsgx":60,"qymc":"青岛凯荣科技有限公司","qytp":"","qywzjd":120.444988,"qywzwd":36.251112,"qyzt":1,"scjydz":"夏庄街道书云东路57号","sfzd":"A","sqId":2,"status":1,"tze":50,"tzedw":"1","tzzyrysl":0,"wgfzr":"王华阳","wgfzrdzyx":"","wgfzrgddhhm":"13395320098","wgfzryddhhm":"13395320098","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"书云东路57号","zch":"91370214667869716k","zsrysl":0,"zyfzr":"王华阳","zyfzrdzyx":"","zyfzrgddhhm":"13395320098","zyfzryddhhm":"13395320098","zzaqscglrys":1,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":114,"createTime":1526785102000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":204,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"","modifyTime":1526785102000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛恒锦制衣有限公司","qytp":"","qywzjd":120.42368,"qywzwd":36.233824,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"申锦华","aqfzrdzyx":"shenjin@scintl。biz","aqfzrgddhhm":"55566776","aqfzryddhhm":"13606391292","aqjgjcjg":"夏庄安监办","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526869432000,"cyrysl":32,"dzyx":"","fddbr":"朴钟圭","gmqk":0,"hylbdm":"C243","id":228,"isaqfzrxx":1,"isbzh":1,"islgtx":2,"iswhp":0,"iszyfzrxx":1,"jgfljb":4,"jjlxdm":100,"jyfw":"生产：非贵金属工艺品、箱包制品。","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"13583227289","modifyBy":116,"modifyTime":1527040004000,"qyfwzb":"[]","qyfxfj":4,"qygm":1,"qylsgx":50,"qymc":"青岛船舱工艺品有限公司","qytp":"","qywzjd":120.444358,"qywzwd":36.251743,"qyzt":1,"scjydz":"夏庄街道华岭路北，华夏路东","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"申锦华","wgfzrdzyx":"shenjin@scintl。biz","wgfzrgddhhm":"55566776","wgfzryddhhm":"13606391292","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"华岭路北、华夏路东","zch":"913702147180503493","zsrysl":0,"zyfzr":"申锦华","zyfzrdzyx":"shenjin2scintl.biz","zyfzrgddhhm":"55566776","zyfzryddhhm":"13606391292","zzaqscglrys":1,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526881315000,"cyrysl":0,"fddbr":"刘汉鹏","gmqk":0,"hylbdm":"C38","id":242,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"自动化控制系统的设计、改造丶安装和调试","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"18678962531","modifyTime":1526881315000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛富森自动化有限公司城阳分公司","qytp":"","qywzjd":120.44045,"qywzwd":36.266423,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中兴路11考","zch":"91370214ma3n477lol","zsrysl":0,"zyfzr":"刘汉鹏","zyfzrdzyx":"","zyfzryddhhm":"18678962531","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526883496000,"cyrysl":0,"fddbr":"张其波","gmqk":0,"hylbdm":"C38","id":248,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"防腐保温材料及制品的安装","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"15866879121","modifyTime":1526883496000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛远通达中通能源集团有限公司","qytp":"","qywzjd":120.44122,"qywzwd":36.267253,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"李沧区九水东路","zch":"913702137735135027","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526884014000,"cyrysl":0,"fddbr":"于丕蛟","gmqk":0,"hylbdm":"C38","id":249,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"机械、工程施工、建筑租赁","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"13396429158","modifyTime":1526884014000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛海丰德基础工程有限公司","qytp":"","qywzjd":120.44075,"qywzwd":36.267779,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中黄华社区","zch":"91370214797547037w","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526884735000,"cyrysl":0,"fddbr":"郑东日","gmqk":0,"hylbdm":"C38","id":251,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"电源设备、机械设备、电池生产设备","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"15315428181","modifyTime":1526884735000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛格林新能源技术有限公司","qytp":"","qywzjd":120.44076,"qywzwd":36.267479,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中兴路19号","zch":"9137021459902477xg","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526972214000,"cyrysl":0,"fddbr":"姚永春","gmqk":0,"hylbdm":"A01","id":261,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"生产：糕点(烘烤类糕点、油炸类糕点)","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"18553208628","modifyTime":1526972214000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛雅宝莉食品有限公司城阳分公司","qytp":"","qywzjd":120.43739,"qywzwd":36.2544,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中黄埠社区","zch":"9137021467528518xq","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526973913000,"cyrysl":0,"fddbr":"屈书贞","gmqk":0,"hylbdm":"C17","id":263,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"电脑刺绣、面料复合。","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"15763918668","modifyTime":1526973913000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"城阳区天绘美刺绣厂","qytp":"","qywzjd":120.438645,"qywzwd":36.254433,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中黄埠社区西南侧500米","zch":"370214600614956","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":2,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1526978162000,"cyrysl":0,"fddbr":"林余","gmqk":2,"hylbdm":"A01","id":264,"isaqfzrxx":2,"islgtx":2,"iswhp":2,"iszyfzrxx":2,"jgfljb":0,"jjlxdm":100,"jyfw":"家具塑料制品仓库","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"18663912611","modifyBy":125,"modifyTime":1527025726000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛兰得家居有限公司","qytp":"","qywzjd":120.43855,"qywzwd":36.259946,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"华安路5号","zch":"91370214ma3cnb4477","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1527037692000,"cyrysl":0,"fddbr":"南相哲","gmqk":0,"hylbdm":"C177","id":265,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"床上用品、袜子、手套","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"18653244325","modifyTime":1527037692000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛不老峰纺织有限公司","qytp":"","qywzjd":120.447136,"qywzwd":36.258928,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"东黄埠社区","zch":"91370282ma3c813aow","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1527057393000,"cyrysl":0,"fddbr":"丁积会","gmqk":0,"hylbdm":"C2110","id":290,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"木制品、实木门、家具","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"13964229899","modifyTime":1527057393000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛林源荣昌木业有限公司","qytp":"","qywzjd":120.441008,"qywzwd":36.266813,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"仙家寨社区南流亭","zch":"91370214095098177u","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1527061757000,"cyrysl":0,"fddbr":"李志双","gmqk":0,"hylbdm":"A01","id":297,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"饮料、大桶水油","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"15863032177","modifyTime":1527061757000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛尊源栩山泉水有限公司","qytp":"","qywzjd":120.45469,"qywzwd":36.244296,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"太平庄社区东30米","zch":"913702143503125227","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1527063078000,"cyrysl":0,"fddbr":"夏定军","gmqk":0,"hylbdm":"C201","id":300,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"木制家具及家具部件","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"18561619779","modifyTime":1527063078000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛百木川木业有限公司","qytp":"","qywzjd":120.43931,"qywzwd":36.257638,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中黄埠社区华夏路9号","zch":"91370214061056374h","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"bzhcjsj":"2018-07-03","bzhfj":0,"bzhfssj":"2018-07-03","createBy":116,"createTime":1527067097000,"cyrysl":0,"fddbr":"栾永芳","gmqk":0,"hylbdm":"C243","id":309,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"工艺品","lgtxcjsj":"2018-07-03","lgtxfssj":"2018-07-03","lxdh":"15953467885","modifyTime":1527067097000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛鑫多宝金属有限公司","qytp":"","qywzjd":120.444867,"qywzwd":36.251646,"qyzt":1,"scjydz":"","sfzd":"A","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"wgfzr":"","wgfzrdzyx":"","wgfzrgddhhm":"","wgfzryddhhm":"","yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"书云东路","zch":"91370282ma3fA7ht5y","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":116,"createTime":1527126838000,"cyrysl":0,"fddbr":"兰晓亮","gmqk":0,"hylbdm":"A01","id":314,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"销售租赁叉车","lxdh":"15053278577","modifyTime":1527126838000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛升辉叉车有限公司","qytp":"","qywzjd":120.434246,"qywzwd":36.26022,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"西黄埠","zch":"91370214ma3f5lf229","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":116,"createTime":1527129271000,"cyrysl":0,"fddbr":"张平","gmqk":0,"hylbdm":"A01","id":320,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"加工制作家橱具","lxdh":"13808966431","modifyTime":1527129271000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛平制海琴家具制造有限公司家具厂","qytp":"","qywzjd":120.439145,"qywzwd":36.260759,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中黄埠社区","zch":"37014329021880","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":116,"createTime":1527129985000,"cyrysl":0,"fddbr":"林武炫","gmqk":0,"hylbdm":"A01","id":322,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"电子元件用粉体环氧包封材料及","lxdh":"13953222831","modifyTime":1527129985000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛大洲电子材料有限公司","qytp":"","qywzjd":120.43904,"qywzwd":36.261177,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中黄埠","zch":"91370214743973161l","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":118,"createTime":1527211512000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":334,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527211512000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛清水源饮料有限公司","qytp":"","qywzjd":120.45632,"qywzwd":36.232256,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":2,"createBy":117,"createTime":1527296867000,"cyrysl":0,"fddbr":"","gmqk":2,"hylbdm":"A01","id":371,"isaqfzrxx":0,"islgtx":2,"iswhp":1,"iszyfzrxx":0,"jgfl":"4","jgfljb":4,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527296867000,"qyfwzb":"","qyfxfj":0,"qygm":1,"qylsgx":60,"qymc":"青岛三丰源工艺品厂","qytp":"","qywzjd":120.4805,"qywzwd":36.288766,"qyzt":1,"scjydz":"辛曹","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":117,"createTime":1527298386000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":375,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527298386000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛吴越轩红木家具","qytp":"","qywzjd":120.47554,"qywzwd":36.292646,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"安监办","aqjgszqk":2,"createBy":117,"createTime":1527299554000,"cyrysl":12,"fddbr":"张家栋","gmqk":2,"hylbdm":"A01","id":378,"isaqfzrxx":2,"islgtx":2,"iswhp":2,"iszyfzrxx":2,"jgfl":"4","jgfljb":2,"jjlxdm":150,"jyfw":"模具加工","lxdh":"13668898510","modifyTime":1527299554000,"qyfwzb":"","qyfxfj":3,"qygm":1,"qylsgx":60,"qymc":"青岛海永盛模具制品有限公司","qytp":"","qywzjd":120.48132,"qywzwd":36.293176,"qyzt":1,"scjydz":"西石沟","sqId":2,"status":1,"tze":200,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"青岛市城阳区西石沟","zch":"913702143502716284","zsrysl":0,"zyfzr":"张家栋","zyfzrdzyx":"","zyfzryddhhm":"13668898510","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":2,"bzhcjsj":"2018-05-28","bzhfssj":"2018-05-28","createBy":114,"createTime":1527471367000,"cyrysl":0,"fddbr":"","gmqk":2,"hylbdm":"A01","id":393,"isaqfzrxx":2,"islgtx":2,"iswhp":2,"iszyfzrxx":2,"jgfljb":0,"jjlxdm":100,"jyfw":"","lgtxcjsj":"2018-05-28","lgtxfssj":"2018-05-28","lxdh":"","modifyBy":114,"modifyTime":1527471387000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"测试1","qytp":"/source/enterprise/20185/20180528093622_720.jpg","qywzjd":120.392997,"qywzwd":36.266158,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":125,"createTime":1527579270000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":425,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527579270000,"qyfwzb":"[{\"lng\":120.4649,\"lat\":36.288133},{\"lng\":120.4650,\"lat\":36.288103},{\"lng\":120.4650,\"lat\":36.288099},{\"lng\":120.4650,\"lat\":36.288086}]","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛鑫鸿鼎门窗有限公司","qytp":"/source/enterprise/20185/20180529153142_381.jpg","qywzjd":120.4651,"qywzwd":36.288088,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"安","aqjgszqk":2,"createBy":117,"createTime":1527668012000,"cyrysl":6,"fddbr":"桑旭宏","gmqk":2,"hylbdm":"A01","id":446,"isaqfzrxx":0,"islgtx":2,"iswhp":2,"iszyfzrxx":2,"jgfl":"5","jgfljb":3,"jjlxdm":150,"jyfw":"塑料加工模具制造","lxdh":"13356391377","modifyTime":1527668012000,"qyfwzb":"","qyfxfj":3,"qygm":1,"qylsgx":60,"qymc":"金源康（青岛）处理科技有限公司","qytp":"","qywzjd":120.47853,"qywzwd":36.279003,"qyzt":1,"scjydz":"李沙","sqId":2,"status":1,"tze":100,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"青岛经济技术开发区","zch":"91370200797523369H","zsrysl":0,"zyfzr":"桑旭宏","zyfzrdzyx":"","zyfzryddhhm":"13356391377","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":125,"createTime":1527735016000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":448,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527735016000,"qyfwzb":"[{\"lng\":120.4379,\"lat\":36.284079},{\"lng\":120.4380,\"lat\":36.284058},{\"lng\":120.4377,\"lat\":36.283873},{\"lng\":120.4378,\"lat\":36.283776}]","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛伯尔集成家居有限公司","qytp":"","qywzjd":120.43785,"qywzwd":36.284043,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":125,"createTime":1527735159000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":449,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527735159000,"qyfwzb":"[{\"lng\":120.4376,\"lat\":36.284102},{\"lng\":120.4376,\"lat\":36.284183},{\"lng\":120.4372,\"lat\":36.284344},{\"lng\":120.4374,\"lat\":36.284165}]","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛泰尔特机械制造有限公司","qytp":"","qywzjd":120.43761,"qywzwd":36.284102,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":125,"createTime":1527735523000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":450,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527735523000,"qyfwzb":"[{\"lng\":120.4373,\"lat\":36.283827},{\"lng\":120.4374,\"lat\":36.283856},{\"lng\":120.4374,\"lat\":36.283884},{\"lng\":120.4372,\"lat\":36.283725}]","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛中大方正水务环保科技有限公司","qytp":"","qywzjd":120.43728,"qywzwd":36.28369,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"孙涛","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"15092257541","aqjgjcjg":"","aqjgszqk":0,"createBy":116,"createTime":1527839967000,"cyrysl":3,"fddbr":"张锐","gmqk":0,"hylbdm":"A01","id":481,"isaqfzrxx":1,"islgtx":0,"iswhp":0,"iszyfzrxx":2,"jgfljb":0,"jjlxdm":100,"jyfw":"电子产品的技术开发、技术转让及技术服务","lxdh":"15092257542","modifyTime":1527839967000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛智迈电子科技有限公司","qytp":"","qywzjd":120.44421,"qywzwd":36.25173,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"青岛市城阳区204国道88-20号","zch":"91370214394079531F","zsrysl":0,"zyfzr":"张锐","zyfzrdzyx":"","zyfzryddhhm":"15092257542","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":118,"createTime":1527902085000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":483,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527902085000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛安邦石油有限公司白沙河加油站","qytp":"","qywzjd":120.46439,"qywzwd":36.270595,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"安监办","aqjgszqk":2,"createBy":117,"createTime":1527902332000,"cyrysl":6,"fddbr":"陈惠超","gmqk":2,"hylbdm":"A01","id":484,"isaqfzrxx":2,"islgtx":2,"iswhp":2,"iszyfzrxx":2,"jgfl":"5","jgfljb":3,"jjlxdm":150,"jyfw":"加工塑料制品","lxdh":"18561673980","modifyTime":1527902332000,"qyfwzb":"","qyfxfj":3,"qygm":1,"qylsgx":60,"qymc":"青岛韩刚工贸有限公司","qytp":"","qywzjd":120.46989,"qywzwd":36.283345,"qyzt":1,"scjydz":"中曹","sqId":2,"status":1,"tze":100,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"青岛市城阳区李曹","zch":"91370214679082224Q","zsrysl":0,"zyfzr":"刘佳修","zyfzrdzyx":"","zyfzryddhhm":"13356399026","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":118,"createTime":1527903012000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":486,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527903012000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛市城阳区汇源加油站","qytp":"","qywzjd":120.46537,"qywzwd":36.273127,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":125,"createTime":1527905860000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":490,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1527905860000,"qyfwzb":"[{\"lng\":120.4379,\"lat\":36.291480},{\"lng\":120.4377,\"lat\":36.291599},{\"lng\":120.4378,\"lat\":36.291793},{\"lng\":120.4377,\"lat\":36.291805}]","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"11","qytp":"","qywzjd":120.43798,"qywzwd":36.291491,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":117,"createTime":1528110529000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":537,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1528110529000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛佑诚电子有限公司","qytp":"","qywzjd":120.47523,"qywzwd":36.289338,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"安监办","aqjgszqk":2,"createBy":117,"createTime":1528163474000,"cyrysl":2,"fddbr":"戴恩翠","gmqk":2,"hylbdm":"A01","id":552,"isaqfzrxx":0,"islgtx":2,"iswhp":2,"iszyfzrxx":2,"jgfl":"4","jgfljb":3,"jjlxdm":150,"jyfw":"机械零部件加工","lxdh":"15689101609","modifyTime":1528163474000,"qyfwzb":"","qyfxfj":3,"qygm":1,"qylsgx":60,"qymc":"青岛市城阳水域实业有限公司机械分公司","qytp":"","qywzjd":120.47448,"qywzwd":36.284315,"qyzt":1,"scjydz":"中曹","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"青岛市城阳区夏庄街道水域","zch":"","zsrysl":0,"zyfzr":"戴静静","zyfzrdzyx":"","zyfzryddhhm":"13210223052","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":116,"createTime":1528164726000,"cyrysl":0,"fddbr":"高建东","gmqk":0,"hylbdm":"A01","id":559,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"空气净化工程；机电设备安装工程","lxdh":"13953212366","modifyTime":1528164726000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛德瑞鑫净化工程有限公司","qytp":"","qywzjd":120.438747,"qywzwd":36.26196,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"中黄埠华夏路8-10号","zch":"91370214086497918b","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":116,"createTime":1528443449000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":577,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1528443449000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛海威茨仪表有限公司城阳分公司","qytp":"","qywzjd":120.447951,"qywzwd":36.248001,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"随之明","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"13210059390","aqjgjcjg":"夏庄安监办","aqjgszqk":0,"bzhcjsj":"2018-07-06","bzhfssj":"2018-07-06","createBy":114,"createTime":1528872971000,"cyrysl":25,"fddbr":"随春华","gmqk":2,"hylbdm":"C223","id":668,"isaqfzrxx":0,"isbzh":0,"islgtx":0,"isqyzc":0,"iswhp":0,"iszyfzrxx":0,"jgfl":"5","jgfljb":3,"jjlxdm":100,"jyfw":"包装装潢印刷品印刷：普通货运：批发、零售:包装材料、纸制品、塑料制品","lgtxcjsj":"2018-07-06","lgtxfssj":"2018-07-06","lxdh":"13210059390","modifyBy":114,"modifyTime":1530844015000,"qyfwzb":"[{\"lng\":120.4134,\"lat\":36.238285},{\"lng\":120.4131,\"lat\":36.238228},{\"lng\":120.4130,\"lat\":36.238166},{\"lng\":120.4132,\"lat\":36.238178}]","qyfxfj":3,"qygm":0,"qyjc":"世康","qylsgx":50,"qymc":"青岛世康兴伟印刷包装有限公司","qytp":"","qywzjd":120.41343,"qywzwd":36.238152,"qyzt":1,"scjydz":"夏庄街道秦家小水社区","sfzd":"C","sqId":2,"status":1,"tze":1000,"tzedw":"1","tzzyrysl":1,"yye":0,"yyedw":"1","zcaqgcsrys":1,"zcdz":"山东省青岛市城阳区夏庄街道刘家新村社区西","zch":"91370214MA3CTBHY8X","zsrysl":0,"zyfzr":"随春华","zyfzrdzyx":"","zyfzrgddhhm":"053280927882","zyfzryddhhm":"13210059390","zzaqscglrys":1,"zzyjglrys":1},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":114,"createTime":1528873110000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":670,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1528873110000,"qyfwzb":"[{\"lng\":120.4140,\"lat\":36.237533},{\"lng\":120.4143,\"lat\":36.237491},{\"lng\":120.4145,\"lat\":36.237452},{\"lng\":120.4141,\"lat\":36.237452}]","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛华速国际货运代理有限公司","qytp":"","qywzjd":120.41384,"qywzwd":36.237521,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":116,"createTime":1528938071000,"cyrysl":0,"fddbr":"尚庆伟","gmqk":0,"hylbdm":"C231","id":673,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"批发、零售：纸张，机电产品","lxdh":"18705328806","modifyTime":1528938071000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛东方丽彩包装有限公司","qytp":"","qywzjd":120.441437,"qywzwd":36.270518,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"夏庄中兴路27号","zch":"91370214583654435g","zsrysl":0,"zyfzr":"尚庆伟","zyfzrdzyx":"","zyfzryddhhm":"18705328806","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":115,"createTime":1529369627000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":694,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1529369627000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛联合包装有限公司","qytp":"","qywzjd":120.43831,"qywzwd":36.250071,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":115,"createTime":1529370635000,"cyrysl":0,"fddbr":"王建春","gmqk":0,"hylbdm":"A01","id":696,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"13705322032","modifyTime":1529370635000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛翱鹏工艺品有限公司","qytp":"","qywzjd":120.43756,"qywzwd":36.248585,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":116,"createTime":1529544611000,"cyrysl":0,"fddbr":"历萍","gmqk":0,"hylbdm":"A01","id":717,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"仓库存玻璃瓶","lxdh":"18669702598","modifyTime":1529544611000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"青岛汇元亨国际贸易有限公司","qytp":"","qywzjd":120.44088,"qywzwd":36.256924,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"城阳街道社区中心办公楼","zch":"91370214395684418N","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0},{"aqfzr":"","aqfzrdzyx":"","aqfzrgddhhm":"","aqfzryddhhm":"","aqjgjcjg":"","aqjgszqk":0,"createBy":117,"createTime":1529979023000,"cyrysl":0,"fddbr":"","gmqk":0,"hylbdm":"A01","id":757,"isaqfzrxx":0,"islgtx":0,"iswhp":0,"iszyfzrxx":0,"jgfljb":0,"jjlxdm":100,"jyfw":"","lxdh":"","modifyTime":1529979023000,"qyfwzb":"","qyfxfj":0,"qygm":0,"qylsgx":50,"qymc":"西石沟","qytp":"","qywzjd":120.4812,"qywzwd":36.293025,"qyzt":1,"scjydz":"","sqId":2,"status":1,"tze":0,"tzedw":"1","tzzyrysl":0,"yye":0,"yyedw":"1","zcaqgcsrys":0,"zcdz":"","zch":"","zsrysl":0,"zyfzr":"","zyfzrdzyx":"","zyfzryddhhm":"","zzaqscglrys":0,"zzyjglrys":0}]
         * bt : {"createBy":1,"createTime":1525744096000,"id":4,"modifyBy":1,"modifyTime":1530859983000,"pqId":4,"status":1,"wsbh":"4","xzmc":"中片组"}
         * createBy : 1
         * createTime : 1525743375000
         * id : 2
         * modifyBy : 1
         * modifyTime : 1534327954000
         * pqId : 4
         * qysl : 60
         * sqdz : 中黄埠
         * sqfwzb : [{"O":36.255836,"M":120.429555,"lng":120.429555,"lat":36.255836},{"O":36.256111,"M":120.43229500000001,"lng":120.432295,"lat":36.256111},{"O":36.255621,"M":120.43518599999999,"lng":120.435186,"lat":36.255621},{"O":36.262723,"M":120.43573400000002,"lng":120.435734,"lat":36.262723},{"O":36.263056,"M":120.438921,"lng":120.438921,"lat":36.263056},{"O":36.263676,"M":120.43890599999997,"lng":120.438906,"lat":36.263676},{"O":36.26371,"M":120.439997,"lng":120.439997,"lat":36.26371},{"O":36.264973,"M":120.440182,"lng":120.440182,"lat":36.264973},{"O":36.265684,"M":120.439391,"lng":120.439391,"lat":36.265684},{"O":36.267277,"M":120.43911800000001,"lng":120.439118,"lat":36.267277},{"O":36.269301,"M":120.43874,"lng":120.43874,"lat":36.269301},{"O":36.270602,"M":120.44478800000002,"lng":120.444788,"lat":36.270602},{"O":36.26884,"M":120.44405999999998,"lng":120.44406,"lat":36.26884},{"O":36.266271,"M":120.44398999999999,"lng":120.44399,"lat":36.266271},{"O":36.261109,"M":120.44387799999998,"lng":120.443878,"lat":36.261109},{"O":36.257417,"M":120.44347299999998,"lng":120.443473,"lat":36.257417},{"O":36.255467,"M":120.44355100000001,"lng":120.443551,"lat":36.255467},{"O":36.255426,"M":120.44208900000001,"lng":120.442089,"lat":36.255426},{"O":36.253793,"M":120.441913,"lng":120.441913,"lat":36.253793},{"O":36.253922,"M":120.43919299999999,"lng":120.439193,"lat":36.253922},{"O":36.253911,"M":120.43530599999997,"lng":120.435306,"lat":36.253911},{"O":36.251283703804205,"M":120.43510089154051,"lng":120.435101,"lat":36.251284},{"O":36.251286592658865,"M":120.43167713226325,"lng":120.431677,"lat":36.251287},{"O":36.251378659065416,"M":120.43008277511592,"lng":120.430083,"lat":36.251379},{"O":36.25203688175077,"M":120.42918733397676,"lng":120.429187,"lat":36.252037},{"O":36.253006567157364,"M":120.42857084257508,"lng":120.428571,"lat":36.253007},{"O":36.255465,"M":120.42954800000001,"lng":120.429548,"lat":36.255465}]
         * sqmc : 中黄埠社区
         * sqmj : 105450
         * sqrk : 1434
         * sqtp : /source/community/20188/20180815112917_505.png,/source/community/20188/20180815124538_523.png,/source/community/20188/20180815181224_255.png
         * status : 1
         * xzId : 4
         */

        private String aqfzr;
        private String aqfzrbm;
        private String aqfzrlxdh;
        private String aqfzrzc;
        private BtBean bt;
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
        private int sqmj;
        private int sqrk;
        private String sqtp;
        private int status;
        private int xzId;
        private List<BelBean> bel;

        public String getAqfzr() {
            return aqfzr;
        }

        public void setAqfzr(String aqfzr) {
            this.aqfzr = aqfzr;
        }

        public String getAqfzrbm() {
            return aqfzrbm;
        }

        public void setAqfzrbm(String aqfzrbm) {
            this.aqfzrbm = aqfzrbm;
        }

        public String getAqfzrlxdh() {
            return aqfzrlxdh;
        }

        public void setAqfzrlxdh(String aqfzrlxdh) {
            this.aqfzrlxdh = aqfzrlxdh;
        }

        public String getAqfzrzc() {
            return aqfzrzc;
        }

        public void setAqfzrzc(String aqfzrzc) {
            this.aqfzrzc = aqfzrzc;
        }

        public BtBean getBt() {
            return bt;
        }

        public void setBt(BtBean bt) {
            this.bt = bt;
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

        public String getSqtp() {
            return sqtp;
        }

        public void setSqtp(String sqtp) {
            this.sqtp = sqtp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getXzId() {
            return xzId;
        }

        public void setXzId(int xzId) {
            this.xzId = xzId;
        }

        public List<BelBean> getBel() {
            return bel;
        }

        public void setBel(List<BelBean> bel) {
            this.bel = bel;
        }

        public class BtBean implements Serializable{
            /**
             * createBy : 1
             * createTime : 1525744096000
             * id : 4
             * modifyBy : 1
             * modifyTime : 1530859983000
             * pqId : 4
             * status : 1
             * wsbh : 4
             * xzmc : 中片组
             */

            private int createBy;
            private long createTime;
            private int id;
            private int modifyBy;
            private long modifyTime;
            private int pqId;
            private int status;
            private String wsbh;
            private String xzmc;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getWsbh() {
                return wsbh;
            }

            public void setWsbh(String wsbh) {
                this.wsbh = wsbh;
            }

            public String getXzmc() {
                return xzmc;
            }

            public void setXzmc(String xzmc) {
                this.xzmc = xzmc;
            }
        }

        public class BelBean implements Serializable{
            /**
             * aqfzr : 崔国松
             * aqfzrdzyx :
             * aqfzrgddhhm : 053267731834
             * aqfzryddhhm : 18953209567
             * aqjgjcjg : 夏庄街道安监办
             * aqjgszqk : 0
             * bzhcjsj : 2018-05-28
             * bzhfj : 0
             * bzhfssj : 2018-05-28
             * createBy : 1
             * createTime : 1510040938000
             * cyrysl : 6
             * dzyx :
             * fddbr : 崔国松
             * gmqk : 0
             * hylbdm : C18
             * id : 6
             * jgfljb : 1
             * jjlxdm : 10
             * jyfw : 生产、加工、销售：服装及辅料、鞋及辅料、纺织品、工艺品、包装材料；货物进出口、技术进出口。（依法须经批准的项目，经相关部门批准后方可开展经营活动）
             * lgtxcjsj : 2018-05-28
             * lgtxfssj : 2018-05-28
             * lxdh : 18953209567
             * modifyBy : 1
             * modifyTime : 1525831177000
             * qyfwzb : []
             * qyfxfj : 4
             * qygm : 1
             * qylsgx : 61
             * qymc : 青岛美金服装有限公司
             * qytp :
             * qywzjd : 120.111111
             * qywzwd : 39.111111
             * qyzt : 1
             * scjydz : 山东省青岛市城阳区夏庄街道中黄埠社区中兴路9号
             * sfzd :
             * sqId : 2
             * status : 1
             * tze : 50
             * tzedw : 1
             * tzzyrysl : 0
             * wgfzr : 崔国松
             * wgfzrdzyx :
             * wgfzrgddhhm : 18953209567
             * wgfzryddhhm :
             * xzqhdm : 370214
             * yyedw : 1
             * zcaqgcsrys : 0
             * zcdz : 山东省青岛市城阳区夏庄街道中黄埠社区中兴路9号
             * zch : 91370214MA3CCF237A
             * zyfzr : 崔国松
             * zyfzrdzyx :
             * zyfzrgddhhm : 053267731834
             * zyfzryddhhm : 18953209567
             * zzaqscglrys : 0
             * zzjgdm : MA3CCF237
             * zzyjglrys : 0
             * yzbm : 266000
             * isaqfzrxx : 1
             * isbzh : 2
             * islgtx : 2
             * iswhp : 2
             * iszyfzrxx : 1
             * yye : 0
             * zsrysl : 0
             * jgfl : 5
             * isqyzc : 0
             * qyjc : 世康
             */

            private String aqfzr;
            private String aqfzrdzyx;
            private String aqfzrgddhhm;
            private String aqfzryddhhm;
            private String aqjgjcjg;
            private int aqjgszqk;
            private String bzhcjsj;
            private int bzhfj;
            private String bzhfssj;
            private int createBy;
            private long createTime;
            private int cyrysl;
            private String dzyx;
            private String fddbr;
            private int gmqk;
            private String hylbdm;
            private int id;
            private int jgfljb;
            private int jjlxdm;
            private String jyfw;
            private String lgtxcjsj;
            private String lgtxfssj;
            private String lxdh;
            private int modifyBy;
            private long modifyTime;
            private String qyfwzb;
            private int qyfxfj;
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
            private String tze;
            private String tzedw;
            private int tzzyrysl;
            private String wgfzr;
            private String wgfzrdzyx;
            private String wgfzrgddhhm;
            private String wgfzryddhhm;
            private int xzqhdm;
            private String yyedw;
            private int zcaqgcsrys;
            private String zcdz;
            private String zch;
            private String zyfzr;
            private String zyfzrdzyx;
            private String zyfzrgddhhm;
            private String zyfzryddhhm;
            private int zzaqscglrys;
            private String zzjgdm;
            private int zzyjglrys;
            private int yzbm;
            private int isaqfzrxx;
            private int isbzh;
            private int islgtx;
            private int iswhp;
            private int iszyfzrxx;
            private String yye;
            private int zsrysl;
            private String jgfl;
            private int isqyzc;
            private String qyjc;

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

            public String getBzhcjsj() {
                return bzhcjsj;
            }

            public void setBzhcjsj(String bzhcjsj) {
                this.bzhcjsj = bzhcjsj;
            }

            public int getBzhfj() {
                return bzhfj;
            }

            public void setBzhfj(int bzhfj) {
                this.bzhfj = bzhfj;
            }

            public String getBzhfssj() {
                return bzhfssj;
            }

            public void setBzhfssj(String bzhfssj) {
                this.bzhfssj = bzhfssj;
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

            public int getJgfljb() {
                return jgfljb;
            }

            public void setJgfljb(int jgfljb) {
                this.jgfljb = jgfljb;
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

            public String getLgtxcjsj() {
                return lgtxcjsj;
            }

            public void setLgtxcjsj(String lgtxcjsj) {
                this.lgtxcjsj = lgtxcjsj;
            }

            public String getLgtxfssj() {
                return lgtxfssj;
            }

            public void setLgtxfssj(String lgtxfssj) {
                this.lgtxfssj = lgtxfssj;
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

            public int getQyfxfj() {
                return qyfxfj;
            }

            public void setQyfxfj(int qyfxfj) {
                this.qyfxfj = qyfxfj;
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

            public String getTze() {
                return tze;
            }

            public void setTze(String tze) {
                this.tze = tze;
            }

            public void setYye(String yye) {
                this.yye = yye;
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

            public String getWgfzrdzyx() {
                return wgfzrdzyx;
            }

            public void setWgfzrdzyx(String wgfzrdzyx) {
                this.wgfzrdzyx = wgfzrdzyx;
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

            public int getXzqhdm() {
                return xzqhdm;
            }

            public void setXzqhdm(int xzqhdm) {
                this.xzqhdm = xzqhdm;
            }

            public String getYyedw() {
                return yyedw;
            }

            public void setYyedw(String yyedw) {
                this.yyedw = yyedw;
            }

            public int getZcaqgcsrys() {
                return zcaqgcsrys;
            }

            public void setZcaqgcsrys(int zcaqgcsrys) {
                this.zcaqgcsrys = zcaqgcsrys;
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

            public int getYzbm() {
                return yzbm;
            }

            public void setYzbm(int yzbm) {
                this.yzbm = yzbm;
            }

            public int getIsaqfzrxx() {
                return isaqfzrxx;
            }

            public void setIsaqfzrxx(int isaqfzrxx) {
                this.isaqfzrxx = isaqfzrxx;
            }

            public int getIsbzh() {
                return isbzh;
            }

            public void setIsbzh(int isbzh) {
                this.isbzh = isbzh;
            }

            public int getIslgtx() {
                return islgtx;
            }

            public void setIslgtx(int islgtx) {
                this.islgtx = islgtx;
            }

            public int getIswhp() {
                return iswhp;
            }

            public void setIswhp(int iswhp) {
                this.iswhp = iswhp;
            }

            public int getIszyfzrxx() {
                return iszyfzrxx;
            }

            public void setIszyfzrxx(int iszyfzrxx) {
                this.iszyfzrxx = iszyfzrxx;
            }

            public int getZsrysl() {
                return zsrysl;
            }

            public void setZsrysl(int zsrysl) {
                this.zsrysl = zsrysl;
            }

            public String getJgfl() {
                return jgfl;
            }

            public void setJgfl(String jgfl) {
                this.jgfl = jgfl;
            }

            public int getIsqyzc() {
                return isqyzc;
            }

            public void setIsqyzc(int isqyzc) {
                this.isqyzc = isqyzc;
            }

            public String getQyjc() {
                return qyjc;
            }

            public void setQyjc(String qyjc) {
                this.qyjc = qyjc;
            }
        }
    }
}
