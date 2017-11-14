package com.xujl.mvpllirary.mvp.model;

/**
 * Created by xujl on 2017/11/13.
 */

public class ModelPackageConfig {
    public static String getModelPackageName(){
        final ModelPackageConfig config = new ModelPackageConfig();
        return config.getClass().getPackage().getName();
    }
}
