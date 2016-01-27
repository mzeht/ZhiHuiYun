package com.wpmac.zhihuiyun.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wpmac on 16/1/19.
 */
public class Utils {


    /**
     * 判断是否是有效值
     *
     * @param str
     * @return
     */
    public static boolean isValidValue(String str) {
        boolean b = false;
        if (str == null) {
            b = false;
        } else if ("".equals(str)) {
            b = false;
        } else if ("null".equals(str)) {
            b = false;
        } else if ("NULL".equals(str)) {
            b = false;
        } else if ("0".equals(str)) {
            b = false;
        } else {
            b = true;
        }
        return b;
    }
/*
判断是否为邮箱
 */

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
}
