package com.xujl.applibrary.mvp.common;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.xujl.applibrary.R;

/**
 * Created by xujl on 2017/9/12.
 */

public class DataBindingToolBarModule extends ToolBarModule {
    protected ViewDataBinding mDataBinding;
    public DataBindingToolBarModule (Activity activity, int layoutId) {
        mDataBinding = DataBindingUtil.setContentView(activity,layoutId);
        mToolbar = (Toolbar) LayoutInflater.from(activity).inflate(getToolBarLayoutId(), null).findViewById(getToolBarId());
        mRootView = (ViewGroup) activity.findViewById(R.id.dataBindingRootView);
        mRootView.addView(mToolbar,0);
    }

    public ViewDataBinding getDataBinding () {
        return mDataBinding;
    }
}
