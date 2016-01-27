package com.wpmac.zhihuiyun.model;


import com.wpmac.zhihuiyun.comment.ResponseBean;

/**
 * Created by wpmac on 16/1/21.
 */
public class meterTypeBean extends ResponseBean {

    public String code;//":"01",             //表计类型编码。
    public String name;//":"水流量表",      //表计类型名称。
    public String des;//":""                //表计类型描述。

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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
