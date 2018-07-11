package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/6/20.
 */

public class YhdItemModel implements Serializable {
    private int id;
    private int jcxId;
    private String jctp;
    private String yhms;
    private int jcjg;

    public YhdItemModel(int id, int jcxId, String jctp, String yhms, int jcjg) {
        this.id = id;
        this.jcxId = jcxId;
        this.jctp = jctp;
        this.yhms = yhms;
        this.jcjg = jcjg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJcxId() {
        return jcxId;
    }

    public void setJcxId(int jcxId) {
        this.jcxId = jcxId;
    }

    public String getJctp() {
        return jctp;
    }

    public void setJctp(String jctp) {
        this.jctp = jctp;
    }

    public String getYhms() {
        return yhms;
    }

    public void setYhms(String yhms) {
        this.yhms = yhms;
    }

    public int getJcjg() {
        return jcjg;
    }

    public void setJcjg(int jcjg) {
        this.jcjg = jcjg;
    }
}
