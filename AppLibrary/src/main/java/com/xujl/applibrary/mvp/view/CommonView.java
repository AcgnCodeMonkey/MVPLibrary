package com.xujl.applibrary.mvp.view;

import android.content.Context;

import com.xujl.applibrary.mvp.common.CommonViewHelper;
import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.view.BaseView;

/**
 * Created by xujl on 2017/7/4.
 */

public abstract class CommonView extends BaseView implements ICommonView{
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        setViewHelper(new CommonViewHelper(presenter));
    }


    @Override
    protected CommonViewHelper getViewHelper () {
        return (CommonViewHelper) super.getViewHelper();
    }

    @Override
    public void showToastMsg (Context context, String msg, int code) {
        getViewHelper().showToastMsg(context, msg, code);
    }

    @Override
    public void showToastMsg (Context context, String msg, int code, int time) {
        getViewHelper().showToastMsg(context, msg, code,time);
    }

    @Override
    public void showLoading () {
        getViewHelper().showLoading();
    }

    @Override
    public void dismissLoading () {
        getViewHelper().dismissLoading();
    }
}
