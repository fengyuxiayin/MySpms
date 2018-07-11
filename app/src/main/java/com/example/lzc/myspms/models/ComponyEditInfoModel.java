package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * 存储公司的所有信息
 * title 公司信息条目的名称
 * content 公司信息条目的内容
 * canCommit 公司信息是否合格的判断标志 默认都为true
 * Created by LZC on 2017/11/1.
 */
public class ComponyEditInfoModel implements Serializable{
    private String title;
    private String content;
    private boolean canCommit=true;

    public ComponyEditInfoModel(String title, String content, boolean canCommit) {
        this.title = title;
        this.content = content;
        this.canCommit = canCommit;
    }

    public boolean isCanCommit() {
        return canCommit;
    }

    public void setCanCommit(boolean canCommit) {
        this.canCommit = canCommit;
    }

    public ComponyEditInfoModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
