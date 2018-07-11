package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/5/8.
 */

public class JsonModel implements Serializable {

    /**
     * lxdh : 18700000000
     * rymc : 甲
     * status : 1
     * xzfzr : 0
     */

    private String lxdh;
    private String rymc;
    private int status;
    private int xzfzr;

    public JsonModel() {
    }

    public JsonModel(String lxdh, String rymc, int status, int xzfzr) {
        this.lxdh = lxdh;
        this.rymc = rymc;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getXzfzr() {
        return xzfzr;
    }

    public void setXzfzr(int xzfzr) {
        this.xzfzr = xzfzr;
    }
}
