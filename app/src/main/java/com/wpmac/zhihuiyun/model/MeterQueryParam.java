package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;

/**
 * Created by wpmac on 16/1/27.
 */
public class MeterQueryParam extends RequestParam {

    public static String method="/sysinterface/meter-op!query.action";
    public String companyCode;
    public String meterCode;
    @Override
    public Bundle getParam() {
        Bundle bundle = new Bundle();
        bundle.putString("companyCode",companyCode);
        bundle.putString("meterCode",meterCode);
        return bundle;
    }
}
