package com.example.lzc.myspms.models;

/**
 * Created by LZC on 2017/10/30.
 */
public class MyInfoModel {
    private String name;
    private int imgId;

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

    public MyInfoModel(String name, int imgId) {

        this.name = name;
        this.imgId = imgId;
    }
}
