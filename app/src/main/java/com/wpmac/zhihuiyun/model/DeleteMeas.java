package com.wpmac.zhihuiyun.model;

import com.wpmac.zhihuiyun.comment.ResponseBean;

/**
 * Created by wpmac on 16/1/27.
 */
public class DeleteMeas extends ResponseBean{

    public String meterCode;
    public String measCode;

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public String getMeasCode() {
        return measCode;
    }

    public void setMeasCode(String measCode) {
        this.measCode = measCode;
    }
}
