package com.example.lzc.myspms.models;

/**
 * Created by LZC on 2017/12/7.
 */

public class TreeDataArray {
    private  int key;
    private String value;

    public TreeDataArray(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {

        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
