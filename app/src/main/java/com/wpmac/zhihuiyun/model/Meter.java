package com.wpmac.zhihuiyun.model;

import com.wpmac.zhihuiyun.comment.ResponseBean;

/**
 * Created by wpmac on 16/1/27.
 */
public class Meter extends ResponseBean {



    public String code;//”:””,  //表计编号
    public String name;//":"",           //表计名称，字符串，长度[1,20] *
    public String area;//":"",            //区域编号，对应区域code*
    public String locationLat;//":"",      //所在经度，非必填，浮点型
    public String locationLon;//":"",     //所在维度，非必填，浮点型
    public String uploadDevice;//": "",  //对应的设备code（对应该公司下面的设备）*
    public String type;//":""             //表计类型，对应于表计类型字典code*

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

    public String getUploadDevice() {
        return uploadDevice;
    }

    public void setUploadDevice(String uploadDevice) {
        this.uploadDevice = uploadDevice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
