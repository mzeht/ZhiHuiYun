package com.wpmac.zhihuiyun.model;

import com.google.gson.Gson;
import com.wpmac.zhihuiyun.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wpmac on 16/1/18.
 */
public class GetLogidBean extends ResponseBean implements Serializable{
    public String loginId;
    public String errorCode;
    public String message;
    public static GetLogidBean getLogidBean(String josnStr)
            throws JSONException {

        if (josnStr == null || josnStr.equals("") || josnStr.equals("{}")
                || josnStr.equals("null") || josnStr.equals("[]")) {
            throw new JSONException("数据解析异常");
        }
        Gson gson = new Gson();
        GetLogidBean model = new GetLogidBean();
        JSONObject singleObject = new JSONObject(josnStr);
        model = gson.fromJson(singleObject.toString(), GetLogidBean.class);
        return model;
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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
