package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;

/**
 * Created by wpmac on 16/1/27.
 */
public class NetStatusParam extends RequestParam {

    public static String method="/sysinterface/device-op!gataWayStatus.action";
    public String companyCode;
    public String deviceCode;
    @Override
    public Bundle getParam() {
        Bundle bundle = new Bundle();
        bundle.putString("companyCode",companyCode);
        bundle.putString("deviceCode",deviceCode);
        return bundle;
    }
}
