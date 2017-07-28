package com.xujl.baselibrary.mvp.common;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xujl.baselibrary.mvp.presenter.BaseActivityPresenter;

/**
 * Created by xujl on 2017/7/4.
 */

public class BaseToolBarModule {
    private ViewGroup mRootView;//页面根布局
    private Toolbar mToolbar;
    private ActionBar mActionBar;

    /**
     * 使用此构造器会根据子类返回的toolBar的布局id自动创建toolBar并和内容布局拼接
     * 到一起
     *
     * @param activity
     * @param layoutId activity布局id
     */
    public BaseToolBarModule (Activity activity, int layoutId) {
        mRootView = new LinearLayout(activity);
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((LinearLayout) mRootView).setOrientation(LinearLayout.VERTICAL);
        mToolbar = (Toolbar) LayoutInflater.from(activity).inflate(getToolBarLayoutId(), null).findViewById(getToolBarId());
        mRootView.addView(mToolbar);
        View contentView = LayoutInflater.from(activity).inflate(layoutId, null);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRootView.addView(contentView);
    }

    /**
     * 此构造器用于页面本身已经包含了一个toolBar的情况
     * 直接传入布局Id和toolBar的id即可
     *
     * @param activity
     * @param layoutId
     * @param toolBarId
     */
    public BaseToolBarModule (Activity activity, int layoutId, int toolBarId) {
        mRootView = (ViewGroup) LayoutInflater.from(activity).inflate(layoutId, null);
        mToolbar = (Toolbar) mRootView.findViewById(toolBarId);
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
}
