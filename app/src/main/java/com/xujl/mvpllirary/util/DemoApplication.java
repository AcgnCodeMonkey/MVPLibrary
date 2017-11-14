package com.xujl.mvpllirary.util;

import com.chenenyu.router.RouteTable;
import com.chenenyu.router.Router;
import com.xujl.applibrary.util.AppApplication;
import com.xujl.mvpllirary.mvp.model.ModelPackageConfig;
import com.xujl.mvpllirary.mvp.presenter.MainActivityPresenter;
import com.xujl.mvpllirary.mvp.view.ViewPackageConfig;

import java.util.Map;

/**
 * Created by xujl on 2017/9/25.
 */

public class DemoApplication extends AppApplication {
    private static DemoApplication sApplication;

    @Override
    public void onCreate () {
        super.onCreate();
        sApplication = this;
        Router.initialize(this);
        Router.handleRouteTable(new RouteTable() {
            @Override
            public void handle (Map<String, Class<?>> map) {
                map.put(RouterConst.MAIN, MainActivityPresenter.class);
            }
        });
        // 开启log
        Router.setDebuggable(isDubug());
        setModelPackageName(ModelPackageConfig.getModelPackageName());
        setViewPackageName(ViewPackageConfig.getViewPackageName());
    }

    public static DemoApplication getInstance () {
        return sApplication;
    }
}
