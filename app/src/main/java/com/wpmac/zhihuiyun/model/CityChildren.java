package com.wpmac.zhihuiyun.model;

import java.util.List;

/**
 * Created by wpmac on 16/1/26.
 */
public class CityChildren {
    public String name;
    public String code;
    public String parentCode;
    public List<CountyChildren> children;

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

    public List<CountyChildren> getChildren() {
        return children;
    }

    public void setChildren(List<CountyChildren> children) {
        this.children = children;
    }
}
