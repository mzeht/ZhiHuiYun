package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;


/**
 * Created by wpmac on 16/1/20.
 */
public class GetDicParam extends RequestParam {

    public final static String method = "/sysinterface/company-op!getDictionary.action";
    public String companyCode;//":""                   //公司编号*

    @Override
    public Bundle getParam() {
        // TODO Auto-generated method stub
        Bundle bundle=new Bundle();
        bundle.putString("method", method);
        bundle.putString("companyCode", companyCode);
        return bundle;
    }

}
