package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/5/8.
 */


public class JsonModel implements Serializable {


    /**
     * createBy : 1
     * createTime : 1535685543000
     * id : 539
     * lxdh : 13455209261
     * modifyBy : 1
     * modifyTime : 1535685583000
     * rymc : 奥术大师多
     * ryzc : 8
     * ssdwId : 816
     * ssdwlx : 3
     * status : 1
     */

    private Integer createBy;
    private Long createTime;
    private Integer id;
    private String lxdh;
    private Integer modifyBy;
    private Long modifyTime;
    private String rymc;
    private Integer ryzc;
    private Integer ssdwId;
    private Integer ssdwlx;
    private Integer status;

    public JsonModel() {
    }

    public JsonModel(String lxdh, String rymc, Integer status) {
        this.lxdh = lxdh;
        this.rymc = rymc;
        this.status = status;
    }

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

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
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

    public String getRymc() {
        return rymc;
    }

    public void setRymc(String rymc) {
        this.rymc = rymc;
    }

    public Integer getRyzc() {
        return ryzc;
    }

    public void setRyzc(Integer ryzc) {
        this.ryzc = ryzc;
    }

    public Integer getSsdwId() {
        return ssdwId;
    }

    public void setSsdwId(Integer ssdwId) {
        this.ssdwId = ssdwId;
    }

    public Integer getSsdwlx() {
        return ssdwlx;
    }

    public void setSsdwlx(Integer ssdwlx) {
        this.ssdwlx = ssdwlx;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}