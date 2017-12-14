package com.xujl.mvpllirary.util;

import android.content.Context;

import com.chenenyu.router.RouteTable;
import com.chenenyu.router.Router;
import com.github.zhangjianli.stallbuster.StallBuster;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.xujl.applibrary.util.AppApplication;
import com.xujl.baselibrary.utils.ActivityManger;
import com.xujl.mvpllirary.mvp.model.ModelPackageConfig;
import com.xujl.mvpllirary.mvp.presenter.MainActivityPresenter;
import com.xujl.mvpllirary.mvp.view.ViewPackageConfig;
import com.xujl.utilslibrary.other.DebugConfig;

import java.util.Map;

/**
 * Created by xujl on 2017/9/25.
 */

public class DemoApplication extends AppApplication {
    private static DemoApplication sApplication;

    @Override
    public void onCreate () {
        super.onCreate();
        registerActivityLifecycleCallbacks(ActivityManger.newInstance());
        StallBuster.getInstance().init(this);
        refWatcher = LeakCanary.install(this);
        sApplication = this;
        Router.initialize(this);
        Router.handleRouteTable(new RouteTable() {
            @Override
            public void handle (Map<String, Class<?>> map) {
                map.put(RouterConst.MAIN, MainActivityPresenter.class);
            }
        });
        // 开启log
        Router.setDebuggable(DebugConfig.isDebug());
        setModelPackageName(ModelPackageConfig.getModelPackageName());
        setViewPackageName(ViewPackageConfig.getViewPackageName());
    }
    public static RefWatcher getRefWatcher(Context context) {
        return getInstance().refWatcher;
    }

    private RefWatcher refWatcher;
    public static DemoApplication getInstance () {
        return sApplication;
    }
}
