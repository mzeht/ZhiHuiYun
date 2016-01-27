package com.wpmac.zhihuiyun.model;

import com.google.gson.GsonBuilder;
import com.wpmac.zhihuiyun.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wpmac on 16/1/26.
 */
public class SimpleResponseBean extends ResponseBean implements Serializable {



    public String success;//":false,
    public String errorCode;//":1,
    public String message;//":"systemToken校验不合格!"

    public static SimpleResponseBean SimpleResponseBean(String josnStr)
            throws JSONException {

        if (josnStr == null || josnStr.equals("") || josnStr.equals("{}")
                || josnStr.equals("null") || josnStr.equals("[]")) {
            throw new JSONException("数据解析异常");
        }
//
        SimpleResponseBean model = new SimpleResponseBean();
        JSONObject singleObject = new JSONObject(josnStr);
        model = new GsonBuilder().serializeNulls().create().fromJson(singleObject.toString(), SimpleResponseBean.class);
        return model;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
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
