package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2017/12/7.
 */

public class TreeDataModel implements Serializable {

    /**
     * checked : false
     * createBy : 1
     * createTime : 1510538491000
     * descript :
     * id : 305
     * modifyTime : 1511504463000
     * name : 场所环境
     * path : 56
     * pid : 56
     * referenceBasis :
     * state : closed
     * status : 1
     * text : 场所环境
     * number :258
     */

    private boolean checked;
    private int createBy;
    private long createTime;
    private String descript;
    private int id;
    private long modifyTime;
    private String name;
    private String path;
    private int pid;
    private String referenceBasis;
    private String state;
    private int status;
    private String text;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getReferenceBasis() {
        return referenceBasis;
    }

    public void setReferenceBasis(String referenceBasis) {
        this.referenceBasis = referenceBasis;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
