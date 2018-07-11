package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * key Enum数据的键 类型代码
 * value Enum数据的值 类型代码对应的中文名称
 * Created by LZC on 2017/11/9.
 */
public class EnumModel implements Serializable {
    private String key;
    private String value;
    private String memo;

    public EnumModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public EnumModel(String key, String value, String memo) {
        this.key = key;
        this.value = value;
        this.memo = memo;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
