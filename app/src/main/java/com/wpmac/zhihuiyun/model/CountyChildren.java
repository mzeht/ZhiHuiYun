package com.wpmac.zhihuiyun.model;

/**
 * Created by wpmac on 16/1/26.
 */
public class CountyChildren {

    public String name;
    public String code;
    public String parentCode;
    public String children;

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

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}
