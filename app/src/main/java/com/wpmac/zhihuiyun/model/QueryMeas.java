package com.wpmac.zhihuiyun.model;

/**
 * Created by wpmac on 16/1/27.
 */
public class QueryMeas {

//    public String
    public String code;//":""     //量测编号
    public String name;//":"",   //量测名称，
    public String uploadMeter;//": ""//对应表计code
    public String type;//":""//量测类型，对应于量测类型字典code


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadMeter() {
        return uploadMeter;
    }

    public void setUploadMeter(String uploadMeter) {
        this.uploadMeter = uploadMeter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
