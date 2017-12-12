package com.xujl.applibrary.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.xujl.applibrary.db.bean.DaoMaster;
import com.xujl.applibrary.db.bean.DaoSession;
import com.xujl.utilslibrary.other.DebugConfig;
import com.xujl.utilslibrary.system.DelegateApplication;

/**
 * Created by xujl on 2017/7/6.
 */

public class AppApplication extends MultiDexApplication {
    private static AppApplication sApplication;
    private static DaoSession sDaoSession;//greenDao
    private String mViewPackageName;
    private String mModelPackageName;

    public static AppApplication getInstance () {
        return sApplication;
    }

    @Override
    protected void attachBaseContext (Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate () {
        super.onCreate();
        sApplication = this;
        DelegateApplication.getInstance().init(this);
        DebugConfig.setDebug(true);
        setupDatabase(); //配置数据库
    }

    /**
     * 配置数据库
     */
    private void setupDatabase () {
        //创建数据库db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mvpLibraryImage.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        sDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant () {
        return sDaoSession;
    }

    public String getViewPackageName () {
        return mViewPackageName;
    }

    public void setViewPackageName (String viewPackageName) {
        mViewPackageName = viewPackageName;
    }

    public String getModelPackageName () {
        return mModelPackageName;
    }

    public void setModelPackageName (String modelPackageName) {
        mModelPackageName = modelPackageName;
    }

}
