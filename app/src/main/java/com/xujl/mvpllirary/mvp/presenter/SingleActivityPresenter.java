package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;

import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.mvpllirary.mvp.model.port.ISingleActivityModel;
import com.xujl.mvpllirary.mvp.view.port.ISingleActivityView;

/**
 * Created by xujl on 2017/12/25.
 */
public class SingleActivityPresenter extends CommonActivityPresenter<ISingleActivityView, ISingleActivityModel> {
    @Override
    protected void initPresenter (Bundle savedInstanceState) {

    }

    @Override
    public boolean enableToolBar () {
        return false;
    }
}