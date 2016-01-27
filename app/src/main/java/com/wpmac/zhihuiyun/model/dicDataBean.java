package com.wpmac.zhihuiyun.model;


import com.wpmac.zhihuiyun.comment.ResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wpmac on 16/1/21.
 */
public class dicDataBean extends ResponseBean implements Serializable {
        public List<measTypeBean> measType;
        public List<meterTypeBean> meterType;
        public List<devicTypeBean> devicType;


    public List<measTypeBean> getMeasType() {
        return measType;
    }

    public void setMeasType(List<measTypeBean> measType) {
        this.measType = measType;
    }

    public List<meterTypeBean> getMeterType() {
        return meterType;
    }

    public void setMeterType(List<meterTypeBean> meterType) {
        this.meterType = meterType;
    }

    public List<devicTypeBean> getDevicType() {
        return devicType;
    }

    public void setDevicType(List<devicTypeBean> devicType) {
        this.devicType = devicType;
    }
}
