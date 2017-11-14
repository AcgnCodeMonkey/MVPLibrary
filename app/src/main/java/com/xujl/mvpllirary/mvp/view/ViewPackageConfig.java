package com.xujl.mvpllirary.mvp.view;

import com.xujl.mvpllirary.mvp.model.ModelPackageConfig;

/**
 * Created by xujl on 2017/11/13.
 */

public class ViewPackageConfig {
    public static String getViewPackageName(){
        final ViewPackageConfig config = new ViewPackageConfig();
        return config.getClass().getPackage().getName();
    }
}
