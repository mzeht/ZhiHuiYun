package com.wpmac.zhihuiyun.model;

import android.os.Bundle;

import com.wpmac.zhihuiyun.comment.RequestParam;

/**
 * Created by wpmac on 16/1/26.
 */
public class PostAreaAddParam extends RequestParam {
    public static String method="/sysinterface/area-op!add.action";
    @Override
    public Bundle getParam() {
        Bundle bundle = new Bundle();
        bundle.putString("method",method);
        return bundle;
    }
}
