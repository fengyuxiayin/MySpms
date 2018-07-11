package com.example.lzc.myspms.utils;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.example.lzc.myspms.bean.NoticeInfo;

import java.util.List;

/**
 * Created by LZC on 2017/12/29.
 */

public class DbManagerNotice {
    //添加数据  
    public void insertMessage(NoticeInfo notice){
        //操作数据库的对象就是实体类本身  
        notice.save();
    }

    //删除数据  
    public void deleteMessage(NoticeInfo notice){
        notice.delete();
    }

    //更新数据  
    public void updateMessage(NoticeInfo notice){

        //在activeandroid中save既可以创建，也可以修改  
        notice.save();
    }

    //查询所有的数据  
    public List<NoticeInfo> queryMessage(String limit){
        List<NoticeInfo> execute = new Select()
                .from(NoticeInfo.class) //model类
                .orderBy("createTime DESC")
                .limit(limit)
                .execute();
        return execute;
    }

    //条件查询  
    public List<NoticeInfo> query(String noticeName){
        List<NoticeInfo> queryName = new Select()
                .from(NoticeInfo.class)
                .where("noticeName = ?", noticeName) //查询条件
                .execute();
        return queryName;
    }
    //条件查询
    public List<NoticeInfo> queryById(String noticeFrom){
        List<NoticeInfo> queryName = new Select()
                .from(NoticeInfo.class)
                .where("ryId = ?", noticeFrom) //查询条件
                .execute();
        return queryName;
    }

    //条件删除  
    public void delete(String noticeName){
        new Delete()
                .from(NoticeInfo.class)
                .where("noticeName = ?", noticeName)
                .execute();
    }

    //条件更新  
    public void update(String noticeName){
        Update update = new Update(NoticeInfo.class);
        update.set("addr = ?","上海")
                .where("noticeName=?",noticeName)
                .execute();
    }
}
