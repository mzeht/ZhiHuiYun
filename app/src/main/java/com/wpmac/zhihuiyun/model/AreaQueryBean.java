package com.wpmac.zhihuiyun.model;

import com.google.gson.Gson;
import com.wpmac.zhihuiyun.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wpmac on 16/1/26.
 */
public class AreaQueryBean extends ResponseBean {


    public String success;
    public String errorCode;
    public String message;

    public AreaQueryareas areas;
    public static AreaQueryBean AreaQueryBean(String josnStr)
            throws JSONException {

        if (josnStr == null || josnStr.equals("") || josnStr.equals("{}")
                || josnStr.equals("null") || josnStr.equals("[]")) {
            throw new JSONException("数据解析异常");
        }
        Gson gson = new Gson();
        AreaQueryBean model = new AreaQueryBean();
        JSONObject singleObject = new JSONObject(josnStr);
        model = gson.fromJson(singleObject.toString(), AreaQueryBean.class);
        return model;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public AreaQueryareas getAreas() {
        return areas;
    }

    public void setAreas(AreaQueryareas areas) {
        this.areas = areas;
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

    //    Response
//    {
//        "success":true,
//            "areas":[
//        {
//            "name":"北京市",
//                "code":"01",
//                "parentCode":"",
//                "children":[
//            {
//                "name":"海淀区",
//                    "code":"0101",
//                    "parentCode":"01",
//                    "children":[
//                "name":"清华南路",
//                    "code":"010101",
//                    "parentCode":"0101",
//                    "children":[]
//                ]
//            },
//            {
//                "name":"东城区",
//                    "code":"0102",
//                    parentCode:"01",
//                    "children":[]
//            }
//            ]
//        }
//        ]
//    }

}
