package com.wpmac.zhihuiyun.model;

import com.wpmac.zhihuiyun.comment.ResponseBean;

/**
 * Created by wpmac on 16/1/27.
 */
public class PostMeasDeleteBean extends ResponseBean {
    public String companyCode;
    public DeleteMeas meas;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public DeleteMeas getMeas() {
        return meas;
    }

    public void setMeas(DeleteMeas meas) {
        this.meas = meas;
    }
}
