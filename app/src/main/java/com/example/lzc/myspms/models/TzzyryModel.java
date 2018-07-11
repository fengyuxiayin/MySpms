package com.example.lzc.myspms.models;

/**
 * Created by LZC on 2018/5/12.
 */

public class TzzyryModel {
    private String lxdh;
    private String rymc;
    private String zjh;
    private String xzsj;
    private String zjyxsj;
    private String zjfssj;
    private int status;
    private int xzfzr;

    public TzzyryModel() {
    }

    public TzzyryModel(String lxdh, String rymc, String zjh, String xzsj, String zjyxsj, String zjfssj, int status, int xzfzr) {
        this.lxdh = lxdh;
        this.rymc = rymc;
        this.zjh = zjh;
        this.xzsj = xzsj;
        this.zjyxsj = zjyxsj;
        this.zjfssj = zjfssj;
        this.status = status;
        this.xzfzr = xzfzr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getRymc() {
        return rymc;
    }

    public void setRymc(String rymc) {
        this.rymc = rymc;
    }

    public String getZjh() {
        return zjh;
    }

    public void setZjh(String zjh) {
        this.zjh = zjh;
    }

    public String getXzsj() {
        return xzsj;
    }

    public void setXzsj(String xzsj) {
        this.xzsj = xzsj;
    }

    public String getZjyxsj() {
        return zjyxsj;
    }

    public void setZjyxsj(String zjyxsj) {
        this.zjyxsj = zjyxsj;
    }

    public String getZjfssj() {
        return zjfssj;
    }

    public void setZjfssj(String zjfssj) {
        this.zjfssj = zjfssj;
    }
}
