package com.wpmac.zhihuiyun.model;

import com.google.gson.GsonBuilder;
import com.wpmac.zhihuiyun.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wpmac on 16/1/18.
 */
public class GetSystemTokenBean extends ResponseBean implements Serializable{
    public String systemToken;//":"f77bdb60878f5d0a62210416606c0f3d"
    public String errorCode;//":"f77bdb60878f5d0a62210416606c0f3d"
    public String message;//":"f77bdb60878f5d0a62210416606c0f3d"


    public static GetSystemTokenBean getSystemTokenBean(String josnStr)
            throws JSONException {

        if (josnStr == null || josnStr.equals("") || josnStr.equals("{}")
                || josnStr.equals("null") || josnStr.equals("[]")) {
            throw new JSONException("数据解析异常");
        }
//
        GetSystemTokenBean model = new GetSystemTokenBean();
        JSONObject singleObject = new JSONObject(josnStr);
        model = new GsonBuilder().serializeNulls().create().fromJson(singleObject.toString(), GetSystemTokenBean.class);
        return model;
    }

    public String getSystemToken() {
        return systemToken;
    }

    public void setSystemToken(String systemToken) {
        this.systemToken = systemToken;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
