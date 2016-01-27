package com.wpmac.zhihuiyun.model;

import com.google.gson.Gson;
import com.wpmac.zhihuiyun.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wpmac on 16/1/26.
 */
public class PostAreaAddBean extends ResponseBean {

    public String companyCode;
    public Area area;

    public static PostAreaAddBean postAreaAddBean(String josnStr)
            throws JSONException {

        if (josnStr == null || josnStr.equals("") || josnStr.equals("{}")
                || josnStr.equals("null") || josnStr.equals("[]")) {
            throw new JSONException("数据解析异常");
        }
        Gson gson = new Gson();
        PostAreaAddBean model = new PostAreaAddBean();
        JSONObject singleObject = new JSONObject(josnStr);
        model = gson.fromJson(singleObject.toString(), PostAreaAddBean.class);
        return model;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
