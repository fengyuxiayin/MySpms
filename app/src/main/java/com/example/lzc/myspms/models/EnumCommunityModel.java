package com.example.lzc.myspms.models;

/**
 * Created by LZC on 2017/11/2.
 */
public class EnumCommunityModel {

    /**
     * code : 1
     * data : true
     * msg : [{"createBy":1,"createTime":1504592944000,"id":1,"modifyBy":1,"modifyTime":1509505024000,"qysl":6,"sqdz":"山东青岛城阳区夏庄街道高家台社区","sqfwzb":"[{\"lng\":120.444943,\"lat\":36.264567},{\"lng\":120.444084,\"lat\":36.263737},{\"lng\":120.447775,\"lat\":36.263806}]","sqmc":"高家台社区","sqmj":20,"sqrk":2000,"status":1},{"createBy":1,"createTime":1504592990000,"id":4,"modifyBy":1,"modifyTime":1509505079000,"qysl":1,"sqdz":"山东青岛城阳区夏庄街道马家台社区","sqfwzb":"[\"{\\\"lng\\\":120.440651,\\\"lat\\\":36.267197}\",\"{\\\"lng\\\":120.450865,\\\"lat\\\":36.262837}\",\"{\\\"lng\\\":120.442196,\\\"lat\\\":36.255362}\"]","sqmc":"马家台社区","sqmj":15,"sqrk":3000,"status":1},{"createBy":1,"createTime":1508898127000,"id":11,"modifyBy":1,"modifyTime":1509505041000,"qysl":0,"sqdz":"111撒旦法撒旦法","sqmc":"高家台小区","sqmj":100,"sqrk":100,"status":1},{"createBy":1,"createTime":1508991287000,"id":12,"modifyBy":1,"modifyTime":1509505050000,"qysl":0,"sqdz":"test112323213","sqmc":"test11","sqmj":1,"sqrk":500,"status":1},{"createBy":1,"createTime":1509002654000,"id":14,"modifyBy":1,"modifyTime":1509004412000,"qysl":0,"sqdz":"test112323213","sqfwzb":"[\"{\\\"lng\\\":120.440908,\\\"lat\\\":36.266228}\",\"{\\\"lng\\\":120.453011,\\\"lat\\\":36.264913}\",\"{\\\"lng\\\":120.453354,\\\"lat\\\":36.252732}\",\"{\\\"lng\\\":120.440136,\\\"lat\\\":36.25467}\"]","sqmc":"test222","sqmj":1,"sqrk":500,"status":1},{"createBy":1,"createTime":1509018179000,"id":16,"qysl":0,"sqdz":"234234","sqfwzb":"[{\"lng\":120.445543,\"lat\":36.266159},{\"lng\":120.452581,\"lat\":36.264844},{\"lng\":120.456916,\"lat\":36.257958},{\"lng\":120.446831,\"lat\":36.258684}]","sqmc":"2","sqmj":34,"sqrk":2,"status":1}]
     * url :
     */

    private int code;
    private boolean data;
    private String msg;
    private String url;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
