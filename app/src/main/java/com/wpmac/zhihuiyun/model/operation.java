package com.wpmac.zhihuiyun.model;


import com.wpmac.zhihuiyun.comment.ResponseBean;

import java.io.Serializable;

/**
 * Created by wpmac on 16/1/24.
 */
public class operation extends ResponseBean implements Serializable {

    public String opertion;

    public String getOpertion() {
        return opertion;
    }

    public void setOpertion(String opertion) {
        this.opertion = opertion;
    }
}
