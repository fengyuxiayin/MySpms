package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/6/18.
 */

public class ProjectPublicInfoModel implements Serializable {

    /**
     * bhgyy : balabala
     * createBy : 1
     * createTime : 1529222806000
     * id : 1
     * jcId : 1
     * jcjg : 0
     * jclx : 1
     * jctp : /source/check/111.jpg
     * standardId : 1
     * standartDescription : 企业主要负责人、安全管理人员未参加安全生产和职业卫生管理培训并取得《培训合格证书》；
     * status : 1
     */

    private String bhgyy;
    private int createBy;
    private long createTime;
    private int id;
    private int jcId;
    private int jcjg;
    private int jclx;
    private String jctp;
    private int standardId;
    private String standardDescription;
    private int status;
    private String standardJson;
    private String wxyJson;

    public String getWxyJson() {
        return wxyJson;
    }

    public void setWxyJson(String wxyJson) {
        this.wxyJson = wxyJson;
    }

    public String getStandardJson() {
        return standardJson;
    }

    public void setStandardJson(String standardJson) {
        this.standardJson = standardJson;
    }

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

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public String getStandardDescription() {
        return standardDescription;
    }

    public void setStandardDescription(String standardDescription) {
        this.standardDescription = standardDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
