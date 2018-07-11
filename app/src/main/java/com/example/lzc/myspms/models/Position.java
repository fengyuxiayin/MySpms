package com.example.lzc.myspms.models;

/**
 * Created by LZC on 2017/12/14.
 */
/**
 *
 *@desc 职业职称的实体类
 *@author Position.java
 *@date  2017/12/1410:13
 */

public class Position {
    //对应的key和value
    private String key;
    private String value;

    public Position(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
