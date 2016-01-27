package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;


/**
 * Created by wpmac on 16/1/18.
 */
public class GetSystemTokenParam extends RequestParam {
    public final static String method = "/sysinterface/login-op!testLogin2.action";
    public String loginId;//":"f77bdb60878f5d0a62210416606c0f3d",
    public String password;//":"123456"


    @Override
    public Bundle getParam() {
        // TODO Auto-generated method stub
        Bundle bundle=new Bundle();
        bundle.putString("method", method);
        bundle.putString("loginId", loginId);
        bundle.putString("password", password);
        return bundle;
    }
}
