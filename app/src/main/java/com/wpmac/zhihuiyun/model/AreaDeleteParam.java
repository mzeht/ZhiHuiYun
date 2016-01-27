package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;

/**
 * Created by wpmac on 16/1/26.
 */
public class AreaDeleteParam extends RequestParam {

    public static String method="/sysinterface/area-op!delete.action";
    public String companyCode;
    public String areaCode;

    @Override
    public Bundle getParam() {
        Bundle bundle= new Bundle();
        bundle.putString("method",method);
        bundle.putString("companyCode",companyCode);
        bundle.putString("areaCode",areaCode);
        return bundle;
    }
}
