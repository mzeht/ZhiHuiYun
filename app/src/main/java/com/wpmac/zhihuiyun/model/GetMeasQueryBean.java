package com.wpmac.zhihuiyun.model;

import com.google.gson.Gson;
import com.wpmac.zhihuiyun.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wpmac on 16/1/27.
 */
public class GetMeasQueryBean extends ResponseBean {

    public String success;
    public String errorCode;
    public String message;
    public QueryMeas meas;
    public static GetMeasQueryBean getMeasQueryBean(String josnStr)
            throws JSONException {

        if (josnStr == null || josnStr.equals("") || josnStr.equals("{}")
                || josnStr.equals("null") || josnStr.equals("[]")) {
            throw new JSONException("数据解析异常");
        }
        Gson gson = new Gson();
        GetMeasQueryBean model = new GetMeasQueryBean();
        JSONObject singleObject = new JSONObject(josnStr);
        model = gson.fromJson(singleObject.toString(), GetMeasQueryBean.class);
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

    public QueryMeas getMeas() {
        return meas;
    }

    public void setMeas(QueryMeas meas) {
        this.meas = meas;
    }
}
