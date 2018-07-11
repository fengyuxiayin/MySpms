package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * 存储项目的安全信息
 * Created by LZC on 2017/11/13.
 */
public class ProjectSafeInfo implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private List<String> data;

    public ProjectSafeInfo(String id, String name, List<String> data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
