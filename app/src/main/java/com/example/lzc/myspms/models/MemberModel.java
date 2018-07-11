package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/6/18.
 */

public class MemberModel implements Serializable {
    private String name;
    private String position;

    public MemberModel(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
