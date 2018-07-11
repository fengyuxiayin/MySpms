package com.example.lzc.myspms.utils;

import com.example.lzc.myspms.models.ProjectSafeInfo;

import java.util.List;

/**
 * Created by LZC on 2017/11/15.
 */
public class UnsafeUtils {
    private static final String TAG = "UnsafeUtils";
    private static String str;
/**
 *
 *@desc 吧unsafe数据转成json  添加项目的时候
 *@param data safe页的数据 只需要传递name 和jctp项就可以
 *@date 2017/12/12 15:40
*/
    public static String setPointDataToJson(List<ProjectSafeInfo> data){
        if (data==null) {
            return "";
        }else{
            //        "[{\"memo\":\"qweqwe\",\"img\":[]},{\"memo\":\"qweqwe\",\"img\":[\"cccc\"]}]"
            str = "[";
            for (int i = 0; i < data.size(); i++) {
//            str = str +"{"+"\"id\""+":"+"\""+data.get(i).getId()+"\"";
                str = str +"{";
//            str = str+","+"\"memo\""+":"+"\""+data.get(i).getName()+"\""+","+"\"jctp\""+":";
                str = str+"\"memo\""+":"+"\""+data.get(i).getName()+"\""+","+"\"jctp\""+":";
                if (data.get(i).getData().size()==0) {
                    //图片的url路径为null
                    str = str+"\""+"\"";
                }else{
                    str+="\"";
                    for (int j = 0; j < data.get(i).getData().size(); j++) {
                        if (data.get(i).getData().size()>1) {
                            if (j!=data.get(i).getData().size()-1){
                                str = str +data.get(i).getData().get(j)+",";
                            }else{
                                str = str +data.get(i).getData().get(j);
                            }
                        }else{
                            str = str +data.get(i).getData().get(j);
                        }
                    }
                    str+="\"";
                }

                if (i!=data.size()-1) {
                    str = str+"}"+",";
                }else{
                    str = str+"}";
                }
            }
            str = str+"]";
            return str;
        }
    }
}
