package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/6/18.
 */

public class ProjectDangerInfoModel implements Serializable {

    /**
     * bhgyy : dfadasf
     * createBy : 1
     * createTime : 1529290523000
     * id : 117
     * jcId : 2
     * jcjg : 2
     * jclx : 2
     * jctp : /source/check/3323.jpg
     * status : 1
     * wxyId : 13
     * wxyJson : {"bfnx":12,"bm":"2313123123","createBy":1,"createTime":1529236127000,"id":13,"modifyBy":1,"modifyTime":1529236952000,"qyId":2,"qyrq":1357488000000,"sbpp":"三菱","sbtp":"/source/project/dafadfa.jpg","sbxh":"三菱111","scrq":1339171200000,"ssbm":"生产部","status":1,"sydd":"车间","synx":10,"tm":"/source/qrcode/tm1111.jpg","wxylx":1,"wxymc":"危险源111"}
     * wxymc : 危险源111
     */

    private String bhgyy;
    private int createBy;
    private long createTime;
    private int id;
    private int jcId;
    private int jcjg;
    private int jclx;
    private String jctp;
    private int status;
    private int wxyId;
    private String wxyJson;
    private String wxymc;

    public String getBhgyy() {
        return bhgyy;
    }

    public void setBhgyy(String bhgyy) {
        this.bhgyy = bhgyy;
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

    public int getJcId() {
        return jcId;
    }

    public void setJcId(int jcId) {
        this.jcId = jcId;
    }

    public int getJcjg() {
        return jcjg;
    }

    public void setJcjg(int jcjg) {
        this.jcjg = jcjg;
    }

    public int getJclx() {
        return jclx;
    }

    public void setJclx(int jclx) {
        this.jclx = jclx;
    }

    public String getJctp() {
        return jctp;
    }

    public void setJctp(String jctp) {
        this.jctp = jctp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWxyId() {
        return wxyId;
    }

    public void setWxyId(int wxyId) {
        this.wxyId = wxyId;
    }

    public String getWxyJson() {
        return wxyJson;
    }

    public void setWxyJson(String wxyJson) {
        this.wxyJson = wxyJson;
    }

    public String getWxymc() {
        return wxymc;
    }

    public void setWxymc(String wxymc) {
        this.wxymc = wxymc;
    }
}
