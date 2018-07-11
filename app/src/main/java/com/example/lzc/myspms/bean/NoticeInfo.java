package com.example.lzc.myspms.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by LZC on 2017/12/29.
 */

/**
 * 1，必须继承Model,继承后就不用声明主键了
 * 2，按照表名添加字段
 * 3，添加注释
 */
@Table(name = "notice",id = "_id")
public class NoticeInfo extends Model implements Serializable{
    @Column
    private long createTimeLong; // 接收到消息的时间 long类型
    @Column
    private String createTime; // 接收到消息的时间 yyyy-MM-dd格式
    @Column
    private String noticeInfo; // 接收到消息的内容

    public NoticeInfo(long createTimeLong, String createTime, String noticeInfo) {
        this.createTimeLong = createTimeLong;
        this.createTime = createTime;
        this.noticeInfo = noticeInfo;
    }

    public long getCreateTimeLong() {
        return createTimeLong;
    }

    public void setCreateTimeLong(long createTimeLong) {
        this.createTimeLong = createTimeLong;
    }

    public NoticeInfo() {
    }

    public NoticeInfo(String createTime, String noticeInfo) {
        this.createTime = createTime;
        this.noticeInfo = noticeInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(String noticeInfo) {
        this.noticeInfo = noticeInfo;
    }
}
