package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2018/5/28.
 */

/**
 * @param
 * @desc 接受者model  发送邮件
 * @date 2018/5/28 16:43
 */
public class JszModel implements Serializable {
    private int sjrlx;
    private int sjrId;

    public JszModel(int sjrId) {
        this.sjrId = sjrId;
    }

    public JszModel(int sjrlx, int sjrId) {
        this.sjrlx = sjrlx;
        this.sjrId = sjrId;
    }

    public int getSjrlx() {
        return sjrlx;
    }

    public void setSjrlx(int sjrlx) {
        this.sjrlx = sjrlx;
    }

    public int getSjrId() {
        return sjrId;
    }

    public void setSjrId(int sjrId) {
        this.sjrId = sjrId;
    }
}
