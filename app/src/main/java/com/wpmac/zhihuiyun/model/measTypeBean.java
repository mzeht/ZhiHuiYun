package com.wpmac.zhihuiyun.model;


import com.wpmac.zhihuiyun.comment.ResponseBean;

/**
 * Created by wpmac on 16/1/20.
 */
public class measTypeBean extends ResponseBean {

    public String code;//":"01",                //量测类型编码。
    public String name;//":"水流量表",         //量测类型名称。
    public String unit;//":"立方米",            //量测类型单位名称。
    public String unit_symbol;//":"M3",        //量测类型单位符号。
    public String des;//":""                //类型描述。

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit_symbol() {
        return unit_symbol;
    }

    public void setUnit_symbol(String unit_symbol) {
        this.unit_symbol = unit_symbol;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
