package com.xujl.applibrary.util;

import android.database.sqlite.SQLiteDatabase;

import com.xujl.applibrary.db.bean.DaoMaster;
import com.xujl.applibrary.db.bean.DaoSession;
import com.xujl.utilslibrary.system.DensityUtil;
import com.xujl.utilslibrary.system.InternetState;
import com.xujl.utilslibrary.system.StartUpApplication;

/**
 * Created by xujl on 2017/7/6.
 */

public class AppApplication extends StartUpApplication{
    private  static AppApplication sApplication;
    private static DaoSession sDaoSession;//greenDao
    private String mViewPackageName;
    private String mModelPackageName;
    public static AppApplication getInstance(){
        return sApplication;
    }
    @Override
    public void onCreate () {
        super.onCreate();
        sApplication = this;
        setModelPackageName("com.xujl.mvpllirary.mvp.model");
        setViewPackageName("com.xujl.mvpllirary.mvp.view");
        DensityUtil.mContext = getApplicationContext();
        InternetState.setContext(getApplicationContext());
        setDubug(true);
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
