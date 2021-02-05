package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/5/12.
 */

public class WhpJsonModel implements Serializable {

    /**
     * createBy : 1
     * createTime : 1535685543000
     * id : 244
     * modifyBy : 1
     * modifyTime : 1535685583000
     * qyid : 816
     * status : 1
     * whpdw : 1
     * whpmc : 哈哈哈
     * whpsl : 2
     */

    private Integer createBy;
    private Long createTime;
    private Integer id;
    private Integer modifyBy;
    private Long modifyTime;
    private Integer qyid;
    private Integer status;
    private Integer whpdw;
    private String whpmc;
    private Double whpsl;

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getQyid() {
        return qyid;
    }

    public void setQyid(Integer qyid) {
        this.qyid = qyid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWhpdw() {
        return whpdw;
    }

    public void setWhpdw(Integer whpdw) {
        this.whpdw = whpdw;
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
}
