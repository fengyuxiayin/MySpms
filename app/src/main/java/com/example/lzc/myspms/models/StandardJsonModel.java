package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/6/18.
 */

public class StandardJsonModel implements Serializable {
    /**
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 69438934541611211L;
    /**
     * 描述
     */
    private java.lang.String description;
    /**
     * 其他标志，0-否，1-是，设置为1则检查的时候需要手动输入
     */
    private java.lang.Integer otherFlag;
    /**
     * 类型，目前关联base_safety_hazard表pid是0的数据的id
     */
    private java.lang.Integer type;
    /**
     * 使用者类型，参照枚举CODE=ACCOUNT_TYPE
     */
    private java.lang.String useType;
    /**
     * 序号
     */
    private java.lang.Integer num;

    /**
     * 参考依据
     */
    private java.lang.String refrenceBasis;

    /**
     * 处罚情形
     */
    private java.lang.String pubish;

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.Integer getOtherFlag() {
        return otherFlag;
    }

    public void setOtherFlag(java.lang.Integer otherFlag) {
        this.otherFlag = otherFlag;
    }

    public java.lang.Integer getType() {
        return type;
    }

    public void setType(java.lang.Integer type) {
        this.type = type;
    }

    public java.lang.String getUseType() {
        return useType;
    }

    public void setUseType(java.lang.String useType) {
        this.useType = useType;
    }

    public java.lang.Integer getNum() {
        return num;
    }

    public void setNum(java.lang.Integer num) {
        this.num = num;
    }

    public String getRefrenceBasis() {
        return refrenceBasis;
    }

    public void setRefrenceBasis(String refrenceBasis) {
        this.refrenceBasis = refrenceBasis;
    }

    public String getPubish() {
        return pubish;
    }

    public void setPubish(String pubish) {
        this.pubish = pubish;
    }
}
