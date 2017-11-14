package com.xujl.baselibrary.mvp.common;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xujl.baselibrary.R;
import com.xujl.baselibrary.mvp.presenter.BaseActivityPresenter;

/**
 * Created by xujl on 2017/7/4.
 */

public class BaseToolBarModule {
    /**
     * 页面根布局
     */
    protected ViewGroup mRootView;
    /**
     * 页面内容布局
     */
    protected View mContentLayout;
    protected Toolbar mToolbar;
    private ActionBar mActionBar;
    protected ViewDataBinding mDataBinding;

    /**
     * 使用此构造器会根据子类返回的toolBar的布局id自动创建toolBar并和内容布局拼接
     * 到一起
     *
     * @param activity
     * @param layoutId activity布局id
     */
    public BaseToolBarModule (Activity activity, int layoutId, LayoutConfig config) {

        if (config.isEnableDataBinding()) {
            mDataBinding = DataBindingUtil.setContentView(activity, layoutId);
            mRootView = (ViewGroup) activity.findViewById(R.id.dataBindingRootLayout);
            mContentLayout = mRootView.getChildAt(0);
            //找到导航栏控件
            findToolBar(activity, config);
            return;
        }
        mRootView = new LinearLayout(activity);
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((LinearLayout) mRootView).setOrientation(LinearLayout.VERTICAL);
        mContentLayout = LayoutInflater.from(activity).inflate(layoutId, null);
        mContentLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //找到导航栏控件
        findToolBar(activity, config);
        mRootView.addView(mContentLayout);
    }

    private void findToolBar (Activity activity, LayoutConfig config) {
        int toolBarId = getToolBarId();
        //判断view或presenter是否传入了导航id，有就直接寻找导航栏
        if (config.getToolBarId() != 0) {
            toolBarId = config.getToolBarId();
            mToolbar = (Toolbar) mContentLayout.findViewById(toolBarId);
            return;
        }
        //没有则采用动态加载默认导航栏
        mToolbar = (Toolbar) LayoutInflater.from(activity).inflate(getToolBarLayoutId(), null).findViewById(toolBarId);
        mRootView.addView(mToolbar, 0);
    }

    public ViewDataBinding getDataBinding () {
        return mDataBinding;
    }



    public void initSetting (BaseActivityPresenter presenter) {
        presenter.setSupportActionBar(mToolbar);
        mActionBar = presenter.getSupportActionBar();
    }

    /**
     * 返回toolbar布局id
     *
     * @return
     */
    protected int getToolBarLayoutId () {
        return 0;
    }

    /**
     * 返回toolbar id
     *
     * @return
     */
    protected int getToolBarId () {
        return 0;
    }

    public Toolbar getToolbar () {
        return mToolbar;
    }

    public ActionBar getActionBar () {
        return mActionBar;
    }

    public View getRootView () {
        return mRootView;
    }

    public View getContentLayout () {
        return mContentLayout;
    }
}
