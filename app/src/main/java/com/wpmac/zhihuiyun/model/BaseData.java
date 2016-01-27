package com.wpmac.zhihuiyun.model;


import com.wpmac.zhihuiyun.comment.ResponseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wpmac on 16/1/24.
 */
public class BaseData extends ResponseBean implements Serializable {

    public String title;
    public List<operation> opList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<operation> getOpList() {
        return opList;
    }

    public void setOpList(List<operation> opList) {
        this.opList = opList;
    }
}
