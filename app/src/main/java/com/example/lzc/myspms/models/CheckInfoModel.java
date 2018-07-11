package com.example.lzc.myspms.models;

/**
 *
 *@desc 检查点信息
 *@author CheckInfoModel.java
 *@date  2017/12/510:13
 */
public class CheckInfoModel {
    private String name;
    private int imgId;

    public CheckInfoModel(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
