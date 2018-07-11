package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LZC on 2018/2/27.
 */

public class ProjectBasicInfoModel implements Serializable {
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 项目类型
     */
    private Integer xmlx;
    /**
     * 二级安全要素
     */
    private Integer ejys;
    /**
     * 三级安全要素
     */
    private Integer says;
    /**
     * 四级安全要素
     */
    private Integer sjys;
    /**
     * 项目编码
     */
    private String xmbm;
    /**
     * 企业的中文名称，应使用全称。应与工商部门时核准的名称
     */
    private String qymc;
    /**
     * 主要负责人
     */
    private String qyfzr;
    /**
     * 检查结束时间
     */
    private Date jcrq;
    /**
     * 企业位置经度,采用WGS84球面坐标系表示的企业位置经度信息
     */
    private Double qywzjd;
    /**
     * 企业位置纬度,采用WGS84球面坐标系表示的企业位置纬度信息
     */
    private Double qywzwd;
    /**
     * 检查结果
     */
    private Integer vjcjg;
    /**
     * 不合格记录
     */
    private Long bhgjl;
    /**
     * 主键，自增
     */
    private Long xmid;
    /**
     * 企业ID
     */
    private Long qyId;
    /**
     * 检查开始时间
     */
    private Date jcksrq;
    /**
     * 检查信息表id
     */
    private Long vjcid;
    /**
     * 使用地点
     */
    private String sydd;
    /**
     * 所属部门
     */
    private String ssbm;
    /**
     * 设备品牌
     */
    private String sbpp;
    /**
     * 设备型号
     */
    private String sbxh;
    /**
     * 额定电压
     */
    private String eddy;
    /**
     * 功率消耗
     */
    private String glxh;
    /**
     * 设备净重
     */
    private String sbjz;
    /**
     * 生产日期
     */
    private String scrq;
    /**
     * 启用日期
     */
    private String qyrq;
    /**
     * 保修日期
     */
    private String bxrq;
    /**
     * 建档日期
     */
    private String jdrq;

    public ProjectBasicInfoModel() {
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public Integer getXmlx() {
        return xmlx;
    }

    public void setXmlx(Integer xmlx) {
        this.xmlx = xmlx;
    }

    public Integer getEjys() {
        return ejys;
    }

    public void setEjys(Integer ejys) {
        this.ejys = ejys;
    }

    public Integer getSays() {
        return says;
    }

    public void setSays(Integer says) {
        this.says = says;
    }

    public Integer getSjys() {
        return sjys;
    }

    public void setSjys(Integer sjys) {
        this.sjys = sjys;
    }

    public String getXmbm() {
        return xmbm;
    }

    public void setXmbm(String xmbm) {
        this.xmbm = xmbm;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getQyfzr() {
        return qyfzr;
    }

    public void setQyfzr(String qyfzr) {
        this.qyfzr = qyfzr;
    }

    public Date getJcrq() {
        return jcrq;
    }

    public void setJcrq(Date jcrq) {
        this.jcrq = jcrq;
    }

    public Double getQywzjd() {
        return qywzjd;
    }

    public void setQywzjd(Double qywzjd) {
        this.qywzjd = qywzjd;
    }

    public Double getQywzwd() {
        return qywzwd;
    }

    public void setQywzwd(Double qywzwd) {
        this.qywzwd = qywzwd;
    }

    public Integer getVjcjg() {
        return vjcjg;
    }

    public void setVjcjg(Integer vjcjg) {
        this.vjcjg = vjcjg;
    }

    public Long getBhgjl() {
        return bhgjl;
    }

    public void setBhgjl(Long bhgjl) {
        this.bhgjl = bhgjl;
    }

    public Long getXmid() {
        return xmid;
    }

    public void setXmid(Long xmid) {
        this.xmid = xmid;
    }

    public Long getQyId() {
        return qyId;
    }

    public void setQyId(Long qyId) {
        this.qyId = qyId;
    }

    public Date getJcksrq() {
        return jcksrq;
    }

    public void setJcksrq(Date jcksrq) {
        this.jcksrq = jcksrq;
    }

    public Long getVjcid() {
        return vjcid;
    }

    public void setVjcid(Long vjcid) {
        this.vjcid = vjcid;
    }

    public String getSydd() {
        return sydd;
    }

    public void setSydd(String sydd) {
        this.sydd = sydd;
    }

    public String getSsbm() {
        return ssbm;
    }

    public void setSsbm(String ssbm) {
        this.ssbm = ssbm;
    }

    public String getSbpp() {
        return sbpp;
    }

    public void setSbpp(String sbpp) {
        this.sbpp = sbpp;
    }

    public String getSbxh() {
        return sbxh;
    }

    public void setSbxh(String sbxh) {
        this.sbxh = sbxh;
    }

    public String getEddy() {
        return eddy;
    }

    public void setEddy(String eddy) {
        this.eddy = eddy;
    }

    public String getGlxh() {
        return glxh;
    }

    public void setGlxh(String glxh) {
        this.glxh = glxh;
    }

    public String getSbjz() {
        return sbjz;
    }

    public void setSbjz(String sbjz) {
        this.sbjz = sbjz;
    }

    public String getScrq() {
        return scrq;
    }

    public void setScrq(String scrq) {
        this.scrq = scrq;
    }

    public String getQyrq() {
        return qyrq;
    }

    public void setQyrq(String qyrq) {
        this.qyrq = qyrq;
    }

    public String getBxrq() {
        return bxrq;
    }

    public void setBxrq(String bxrq) {
        this.bxrq = bxrq;
    }

    public String getJdrq() {
        return jdrq;
    }

    public void setJdrq(String jdrq) {
        this.jdrq = jdrq;
    }
}
