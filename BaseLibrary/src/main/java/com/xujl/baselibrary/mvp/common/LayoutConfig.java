package com.xujl.baselibrary.mvp.common;

import android.app.Activity;

import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.port.IBaseView;

/**
 * Created by xujl on 2017/9/19.
 * 布局配置类
 */

public class LayoutConfig {

    //是否为当前布局添加父布局
    private boolean isAddParentLayout;
    //是否使用导航
    private boolean enableToolBar;
    //是否使用mvp进行加载
    private boolean enableMVP;
    //是否使用dataBinding进行加载,只要有view或presenter开启就开启databinding
    private boolean enableDataBinding;
    //当前布局Id
    private int layoutId;
    //当前导航栏Id
    private int toolBarId;
    //当前界面是否activity
    private boolean isActivity;

    public LayoutConfig (IBaseView view, IBasePresenter presenter) {
        isAddParentLayout = view.isAddParentLayout() && presenter.isAddParentLayout();
        enableToolBar = view.enableToolBar() && presenter.enableToolBar();
        enableMVP = presenter.isMVP();
        enableDataBinding = view.enableDataBinding() || presenter.enableDataBinding();
        layoutId = enableMVP ? view.getLayoutId() : presenter.getLayoutId();
        isActivity = presenter instanceof Activity;
        toolBarId = enableMVP ? view.getToolBarId() : presenter.getToolBarId();
    }

    //<editor-fold desc="getter">
    public boolean isAddParentLayout () {
        return isAddParentLayout;
    }

    public boolean isEnableToolBar () {
        return enableToolBar;
    }

    public boolean isEnableMVP () {
        return enableMVP;
    }

    public boolean isEnableDataBinding () {
        return enableDataBinding;
    }

    public int getLayoutId () {
        return layoutId;
    }

    public boolean isActivity () {
        return isActivity;
    }

    public int getToolBarId () {
        return toolBarId;
    }
    //</editor-fold>
}
