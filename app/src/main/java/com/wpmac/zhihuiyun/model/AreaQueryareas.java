package com.wpmac.zhihuiyun.model;

import com.wpmac.zhihuiyun.comment.ResponseBean;

import java.util.List;

/**
 * Created by wpmac on 16/1/26.
 */
public class AreaQueryareas extends ResponseBean {


    public String name;
    public String code;
    public String parentCode;
    public List<CityChildren> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<CityChildren> getChildren() {
        return children;
    }

    public void setChildren(List<CityChildren> children) {
        this.children = children;
    }
}
