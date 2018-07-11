package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/6/19.
 */

public class YhdJsonModel implements Serializable {

    /**
     * bz : aaa
     * createBy : 1
     * createTime : 1525570193000
     * id : 1
     * modifyTime : 1525570193000
     * status : 1
     * wxyId : 1
     * xh : 111
     * yhdmc : 隐患点1
     * yhdtp : [/source/project/20185/20180506111200_555.jpg]
     * yhdxq : 隐患点1111
     */

    private String bz;
    private int createBy;
    private long createTime;
    private int id;
    private long modifyTime;
    private int status;
    private int wxyId;
    private int xh;
    private String yhdmc;
    private String yhdtp;
    private String yhdxq;

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
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

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public String getYhdmc() {
        return yhdmc;
    }

    public void setYhdmc(String yhdmc) {
        this.yhdmc = yhdmc;
    }

    public String getYhdtp() {
        return yhdtp;
    }

    public void setYhdtp(String yhdtp) {
        this.yhdtp = yhdtp;
    }

    public String getYhdxq() {
        return yhdxq;
    }

    public void setYhdxq(String yhdxq) {
        this.yhdxq = yhdxq;
    }
}
