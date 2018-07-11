package com.example.lzc.myspms.models;

/**
 * Created by LZC on 2018/5/23.
 */

public class ZsryJsonModel {
    /**
     * 建造结构
     */
    private String jzjg;
    /**
     * 建造面积
     */
    private String jzmj;
    /**
     * 住宿人数
     */
    private String zsrs;
    /**
     * 建造时间
     */
    private String jzsj;
    /**
     * 管理人员
     */
    private String glry;
    /**
     * 管理人员联系电话
     */
    private String glrylxdh;
    /**
     * 企业id
     */
    private String qyId;

    public ZsryJsonModel() {
    }

    public ZsryJsonModel(String jzjg, String jzmj, String zsrs, String jzsj, String glry, String glrylxdh, String qyId) {
        this.jzjg = jzjg;
        this.jzmj = jzmj;
        this.zsrs = zsrs;
        this.jzsj = jzsj;
        this.glry = glry;
        this.glrylxdh = glrylxdh;
        this.qyId = qyId;
    }

    public ZsryJsonModel(String jzjg, String jzmj, String zsrs, String glry, String jzsj, String glrylxdh) {
        this.jzjg = jzjg;
        this.jzmj = jzmj;
        this.zsrs = zsrs;
        this.glry = glry;
        this.jzsj = jzsj;
        this.glrylxdh = glrylxdh;
    }

    public String getJzjg() {
        return jzjg;
    }

    public void setJzjg(String jzjg) {
        this.jzjg = jzjg;
    }

    public String getJzmj() {
        return jzmj;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public String getZsrs() {
        return zsrs;
    }

    public void setZsrs(String zsrs) {
        this.zsrs = zsrs;
    }

    public String getJzsj() {
        return jzsj;
    }

    public void setJzsj(String jzsj) {
        this.jzsj = jzsj;
    }

    public String getGlry() {
        return glry;
    }

    public void setGlry(String glry) {
        this.glry = glry;
    }

    public String getGlrylxdh() {
        return glrylxdh;
    }

    public void setGlrylxdh(String glrylxdh) {
        this.glrylxdh = glrylxdh;
    }

    public String getQyId() {
        return qyId;
    }

    public void setQyId(String qyId) {
        this.qyId = qyId;
    }
}
