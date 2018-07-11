package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/12/21.
 */

public class CheckPointModel implements Serializable {

    /**
     * code : 1
     * data : true
     * msg : {"points":null,"qyId":6,"qymc":"第六有限责任公司","safety":null,"unsafe":[{"createBy":1,"createTime":1513071234000,"id":1,"jcId":null,"jcdId":null,"jcdPic":null,"jcjg":null,"jctp":null,"jcxId":null,"jcxmId":1,"memo":"斯蒂芬森","modifyBy":1,"modifyTime":1513733149000,"qyId":null,"status":0,"yhly":null},{"createBy":1,"createTime":1513733144000,"id":12,"jcId":null,"jcdId":null,"jcdPic":null,"jcjg":null,"jctp":"","jcxId":null,"jcxmId":1,"memo":"斯蒂芬森啊","modifyBy":1,"modifyTime":1513735432000,"qyId":null,"status":0,"yhly":null}],"xmId":1,"xmmc":"全不锈钢啊"}
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
    public static class CheckPointMsgModel implements Serializable{

        /**
         * points : null
         * qyId : 6
         * qymc : 第六有限责任公司
         * safety : null
         * unsafe : [{"createBy":1,"createTime":1513071234000,"id":1,"jcId":null,"jcdId":null,"jcdPic":null,"jcjg":null,"jctp":null,"jcxId":null,"jcxmId":1,"memo":"斯蒂芬森","modifyBy":1,"modifyTime":1513733149000,"qyId":null,"status":0,"yhly":null},{"createBy":1,"createTime":1513733144000,"id":12,"jcId":null,"jcdId":null,"jcdPic":null,"jcjg":null,"jctp":"","jcxId":null,"jcxmId":1,"memo":"斯蒂芬森啊","modifyBy":1,"modifyTime":1513735432000,"qyId":null,"status":0,"yhly":null}]
         * xmId : 1
         * xmmc : 全不锈钢啊
         */

        private Object points;
        private int qyId;
        private String qymc;
        private List<SafetyBean> safety;
        private int xmId;
        private String xmmc;
        private List<UnsafeBean> unsafe;

        public List<SafetyBean> getSafety() {
            return safety;
        }

        public void setSafety(List<SafetyBean> safety) {
            this.safety = safety;
        }

        public Object getPoints() {
            return points;
        }

        public void setPoints(Object points) {
            this.points = points;
        }

        public int getQyId() {
            return qyId;
        }

        public void setQyId(int qyId) {
            this.qyId = qyId;
        }

        public String getQymc() {
            return qymc;
        }

        public void setQymc(String qymc) {
            this.qymc = qymc;
        }

        public int getXmId() {
            return xmId;
        }

        public void setXmId(int xmId) {
            this.xmId = xmId;
        }

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public List<UnsafeBean> getUnsafe() {
            return unsafe;
        }

        public void setUnsafe(List<UnsafeBean> unsafe) {
            this.unsafe = unsafe;
        }
        public static class SafetyBean implements Serializable{

            /**
             * checked : null
             * children : []
             * createBy : null
             * createTime : null
             * id : 253
             * jcdId : 29
             * jcdPic :
             * jcjg : 0
             * jcxId : 119
             * modifyBy : null
             * modifyTime : null
             * parentId : 170
             * path : 56,170
             * state : open
             * status : null
             * text : 生产布局是否合理
             */

            private Object checked;
            private Object createBy;
            private Object createTime;
            private int id;
            private int jcdId;
            private String jcdPic;
            private int jcjg;
            private int jcxId;
            private Object modifyBy;
            private Object modifyTime;
            private int parentId;
            private String path;
            private String state;
            private Object status;
            private String text;
            private List<?> children;

            public Object getChecked() {
                return checked;
            }

            public void setChecked(Object checked) {
                this.checked = checked;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getJcdId() {
                return jcdId;
            }

            public void setJcdId(int jcdId) {
                this.jcdId = jcdId;
            }

            public String getJcdPic() {
                return jcdPic;
            }

            public void setJcdPic(String jcdPic) {
                this.jcdPic = jcdPic;
            }

            public int getJcjg() {
                return jcjg;
            }

            public void setJcjg(int jcjg) {
                this.jcjg = jcjg;
            }

            public int getJcxId() {
                return jcxId;
            }

            public void setJcxId(int jcxId) {
                this.jcxId = jcxId;
            }

            public Object getModifyBy() {
                return modifyBy;
            }

            public void setModifyBy(Object modifyBy) {
                this.modifyBy = modifyBy;
            }

            public Object getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Object modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
        public static class UnsafeBean implements Serializable{
            public UnsafeBean() {
            }

            public UnsafeBean(String id, String jcId, String jcdId, String jcjg, String jctp, String jcxId, String jcxmId, String memo, String qyId, String yhly) {
                this.id = id;
                this.jcId = jcId;
                this.jcdId = jcdId;
                this.jcjg = jcjg;
                this.jctp = jctp;
                this.jcxId = jcxId;
                this.jcxmId = jcxmId;
                this.memo = memo;
                this.qyId = qyId;
                this.yhly = yhly;
            }

            /**
             * createBy : 1
             * createTime : 1513071234000
             * id : 1
             * jcId : null
             * jcdId : null
             * jcdPic : null
             * jcjg : null
             * jctp : null
             * jcxId : null
             * jcxmId : 1
             * memo : 斯蒂芬森
             * modifyBy : 1
             * modifyTime : 1513733149000
             * qyId : null
             * status : 0
             * yhly : null
             */

//            private int createBy;
//            private long createTime;
            private String id;
            private String jcId;
            private String jcdId;
//            private Object jcdPic;
            private String jcjg;
            private String jctp;
            private String jcxId;
            private String jcxmId;
            private String memo;
//            private int modifyBy;
//            private long modifyTime;
            private String qyId;
//            private int status;
            private String yhly;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getJcId() {
                return jcId;
            }

            public void setJcId(String jcId) {
                this.jcId = jcId;
            }

            public String getJcdId() {
                return jcdId;
            }

            public void setJcdId(String jcdId) {
                this.jcdId = jcdId;
            }

            public String getJcjg() {
                return jcjg;
            }

            public void setJcjg(String jcjg) {
                this.jcjg = jcjg;
            }

            public String getJctp() {
                return jctp;
            }

            public void setJctp(String jctp) {
                this.jctp = jctp;
            }

            public String getJcxId() {
                return jcxId;
            }

            public void setJcxId(String jcxId) {
                this.jcxId = jcxId;
            }

            public String getJcxmId() {
                return jcxmId;
            }

            public void setJcxmId(String jcxmId) {
                this.jcxmId = jcxmId;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getQyId() {
                return qyId;
            }

            public void setQyId(String qyId) {
                this.qyId = qyId;
            }

            public String getYhly() {
                return yhly;
            }

            public void setYhly(String yhly) {
                this.yhly = yhly;
            }
        }
    }
}
