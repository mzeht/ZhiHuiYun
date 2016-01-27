package com.wpmac.zhihuiyun.model;

import com.google.gson.Gson;
import com.wpmac.zhihuiyun.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wpmac on 16/1/27.
 */
public class PostMeasUpdateBean extends ResponseBean {

    public String companyCode;
    public UpdateMeas meas;


    public static PostMeasUpdateBean postMeasUpdateBean(String josnStr)
            throws JSONException {

        if (josnStr == null || josnStr.equals("") || josnStr.equals("{}")
                || josnStr.equals("null") || josnStr.equals("[]")) {
            throw new JSONException("数据解析异常");
        }
        Gson gson = new Gson();
        PostMeasUpdateBean model = new PostMeasUpdateBean();
        JSONObject singleObject = new JSONObject(josnStr);
        model = gson.fromJson(singleObject.toString(), PostMeasUpdateBean.class);
        return model;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
