package com.wpmac.zhihuiyun.model;

import com.wpmac.zhihuiyun.comment.ResponseBean;

/**
 * Created by wpmac on 16/1/26.
 */
public class Device extends ResponseBean {

    public String code;//":"",           //设备编码，字符串，长度[1,20] *
    public String name;//":"",           //设备名称，字符串，长度[1,20] *
    public String area;//":"",            //区域编号，对应区域code*
    public String locationLat;//":"",      //所在经度，浮点型
    public String locationLon;//":"",     //所在维度，浮点型
    public String allowControl;//": true,  //是否允许下达命令， true：允许;false:不允许*
    public String type;//":""             //设备类型，对应于设备类型字典code*
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(String locationLon) {
        this.locationLon = locationLon;
    }

    public String getAllowControl() {
        return allowControl;
    }

    public void setAllowControl(String allowControl) {
        this.allowControl = allowControl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
