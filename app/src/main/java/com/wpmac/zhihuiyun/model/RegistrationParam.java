package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;


/**
 * Created by wpmac on 16/1/20.
 */
public class RegistrationParam extends RequestParam {
    public final static String method = "/sysinterface/company-op!register.action";
    public String code;//":"",      //企业code唯一，字符串类型，长度[6,20]。*
    public String pwd;//":"",      //企业密码，字符串类型，长度[6,20]。*
    public String name;//":"",     //用户名，字符串类型，长度[2,10]。
    public String address;//":"",    //地址，字符串类型，长度[1,50]。
    public String linkman;//":"",   //联系人，字符串类型，长度[1,50]。
    public String linkphone;//":"",  //联系电话，字符串类型，长度[1,20]。
    public String email;//":""
    public String systemToken;//":""
    @Override
    public Bundle getParam() {
        // TODO Auto-generated method stub
        Bundle bundle=new Bundle();
        bundle.putString("method", method);
        bundle.putString("code", code);
        bundle.putString("pwd", pwd);
        bundle.putString("name", name);
        bundle.putString("address", address);
        bundle.putString("linkman", linkman);
        bundle.putString("linkphone", linkphone);
        bundle.putString("email", email);
        bundle.putString("systemToken", systemToken);
        return bundle;
    }
}
