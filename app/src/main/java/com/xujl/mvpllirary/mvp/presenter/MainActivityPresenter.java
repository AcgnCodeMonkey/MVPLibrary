package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;

import com.xujl.mvpllirary.base.presenter.CommonActivityPresenter;
import com.xujl.mvpllirary.mvp.model.MainActivityModel;
import com.xujl.mvpllirary.mvp.model.port.IMainActivityModel;
import com.xujl.mvpllirary.mvp.view.MainActivity;
import com.xujl.mvpllirary.mvp.view.port.IMainActivityView;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainActivityPresenter extends CommonActivityPresenter<IMainActivityView, IMainActivityModel> {
    @Override
    protected Class<? extends IMainActivityView> getViewClassType () {
        return MainActivity.class;
    }

    @Override
    protected Class<? extends IMainActivityModel> getModelClassType () {
        return MainActivityModel.class;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        getToolBarModule().setTitle("首页");
    }
}