package com.xujl.applibrary.mvp.presenter;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;

import com.xujl.applibrary.mvp.port.IDataBindingModel;
import com.xujl.applibrary.mvp.port.IDataBindingPresenter;
import com.xujl.applibrary.mvp.port.IDataBindingView;

/**
 * Created by xujl on 2017/9/12.
 * dataBinding专用
 */

public abstract class DataBindingActivityPresenter<V extends IDataBindingView, M extends IDataBindingModel,D extends ViewDataBinding>
        extends CommonActivityPresenter<V, M> implements IDataBindingPresenter{
    protected D mDataBinding;


    @Override
    public  D exposeDataBinding () {
        return  mDataBinding;
    }

    @Override
    protected void createLayout () {
        mView.creatView(this);
    }
    @CallSuper
    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mDataBinding = mView.getDataBinding();
    }
}
