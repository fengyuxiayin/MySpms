package com.example.lzc.myspms.models;

import java.io.Serializable;

/**
 * Created by LZC on 2018/3/23.
 */

public class ScanResultModel implements Serializable {
    //每个项目的检查项目id
    private String id;
    //项目所属的企业id
    private String qyId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQyId() {
        return qyId;
    }

    public void setQyId(String qyId) {
        this.qyId = qyId;
    }

    public ScanResultModel(String id, String qyId) {
        this.id = id;
        this.qyId = qyId;
    }
}
