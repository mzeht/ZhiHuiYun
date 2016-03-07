package com.wpmac.zhihuiyun.base;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by wpmac on 16/2/13.
 */
public class BaseApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
