package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;


/**
 * Created by wpmac on 16/1/18.
 */
public class GetLogidParam extends RequestParam {

    public final static String method = "/sysinterface/login-op!login1.action";
    public String systemId;

    @Override
    public Bundle getParam() {
        // TODO Auto-generated method stub
        Bundle bundle=new Bundle();
        bundle.putString("method", method);
        bundle.putString("systemId", systemId);
        return bundle;
    }
}
