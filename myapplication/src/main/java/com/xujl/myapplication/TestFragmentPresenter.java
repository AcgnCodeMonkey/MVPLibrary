package com.xujl.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.applibrary.util.CustomToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by xujl on 2017/11/13.
 */
public class TestFragmentPresenter extends CommonFragmentPresenter<ITestFragmentView, ITestFragmentModel> {
    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mView.changeBG(getArguments().getInt("color"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void show (Fragment fragment) {
        if (fragment == this) {
            mView.showToastMsg(exposeContext(), getArguments().getString("name") + "：收到toast信息", CustomToast.SUCCESS);
        }
    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
        mView.showWindow(v, exposeContext());
    }

    @Override
    public void onDestroy () {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected Class<? extends ITestFragmentView> getViewClassType () {
        return TestFragment.class;
    }

    @Override
    protected Class<? extends ITestFragmentModel> getModelClassType () {
        return TestFragmentModel.class;
    }

}