package com.xujl.mvpllirary.util;

import com.xujl.applibrary.util.AppApplication;

/**
 * Created by xujl on 2017/9/25.
 */

public class DemoApplication extends AppApplication{
    private static DemoApplication sApplication;
    @Override
    public void onCreate () {
        super.onCreate();
        sApplication = this;
    }
    public static DemoApplication getInstance () {
        return sApplication;
    }
}
