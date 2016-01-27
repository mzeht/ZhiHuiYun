package com.wpmac.zhihuiyun.model;

import com.wpmac.zhihuiyun.comment.ResponseBean;

/**
 * Created by wpmac on 16/1/27.
 */
public class Meas extends ResponseBean {

    public String uploadMeter;
    public String type;

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
