package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;

/**
 * Created by wpmac on 16/1/27.
 */
public class MeterUpdateParam extends RequestParam {
    public static String method="/sysinterface/meter-op!update.action";
    @Override
    public Bundle getParam() {
        Bundle bundle = new Bundle();
        bundle.putString("method",method);
        return bundle;
    }
}
