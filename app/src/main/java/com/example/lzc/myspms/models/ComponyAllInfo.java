package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2017/11/1.
 */
public class ComponyAllInfo implements Serializable{
    private String title;
    private String content;
    private boolean canCommit=true;

    public ComponyAllInfo(String title, String content, boolean canCommit) {
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

    public ComponyAllInfo(String title, String content) {
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
