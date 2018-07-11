package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/5/12.
 */

public class WhpJsonModel implements Serializable{
    private Long qyid;
    /**
     * 危化品名称
     */
    private String whpmc;
    /**
     * 危化品数量
     */
    private Double whpsl;
    /**
     * 危化品单位
     */
    private Integer whpdw;

    public WhpJsonModel() {
    }

    public WhpJsonModel(Long qyid, String whpmc, Double whpsl, Integer whpdw) {
        this.qyid = qyid;
        this.whpmc = whpmc;
        this.whpsl = whpsl;
        this.whpdw = whpdw;
    }

    public Long getQyid() {
        return qyid;
    }

    public void setQyid(Long qyid) {
        this.qyid = qyid;
    }

    public String getWhpmc() {
        return whpmc;
    }

    public void setWhpmc(String whpmc) {
        this.whpmc = whpmc;
    }

    public Double getWhpsl() {
        return whpsl;
    }

    public void setWhpsl(Double whpsl) {
        this.whpsl = whpsl;
    }

    public Integer getWhpdw() {
        return whpdw;
    }

    public void setWhpdw(Integer whpdw) {
        this.whpdw = whpdw;
    }
}
