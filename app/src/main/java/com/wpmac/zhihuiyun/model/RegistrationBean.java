package com.wpmac.zhihuiyun.model;

import com.google.gson.GsonBuilder;
import com.wpmac.zhihuiyun.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wpmac on 16/1/20.
 */
public class RegistrationBean extends ResponseBean {

    public String success;
    public String message;
    public String companyCode;
    public String errorCode;

    public static RegistrationBean registrationBean(String josnStr)
            throws JSONException {

        if (josnStr == null || josnStr.equals("") || josnStr.equals("{}")
                || josnStr.equals("null") || josnStr.equals("[]")) {
            throw new JSONException("数据解析异常");
        }
//
        RegistrationBean model = new RegistrationBean();
        JSONObject singleObject = new JSONObject(josnStr);
        model = new GsonBuilder().serializeNulls().create().fromJson(singleObject.toString(), RegistrationBean.class);
        return model;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
