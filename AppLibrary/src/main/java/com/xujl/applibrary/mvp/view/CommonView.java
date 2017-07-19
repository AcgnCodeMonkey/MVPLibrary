package com.xujl.applibrary.mvp.view;

import android.content.Context;

import com.xujl.applibrary.mvp.common.CommonViewHelper;
import com.xujl.applibrary.mvp.port.ICommonPresenter;
import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.baselibrary.mvp.common.BaseViewHelper;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.view.BaseView;
import com.xujl.applibrary.mvp.common.ToolBarModule;

/**
 * Created by xujl on 2017/7/4.
 */

public abstract class CommonView extends BaseView implements ICommonView {

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
        getViewHelper().showToastMsg(context, msg, code, time);
    }

    @Override
    public void showLoading () {
        getViewHelper().showLoading();
    }

    @Override
    public void dismissLoading () {
        getViewHelper().dismissLoading();
    }

    @Override
    public ToolBarModule getToolBarModule () {
        return (ToolBarModule) super.getToolBarModule();
    }

    protected ToolBarModule setDefaultToolBarHelper (IBasePresenter presenter) {
        if (presenter.isMVP()) {
            return new ToolBarModule(presenter.exposeActivity(), getLayoutId());
        }
        return new ToolBarModule(presenter.exposeActivity(), ((ICommonPresenter) presenter).getLayoutId());
    }

    @Override
    protected BaseViewHelper setViewHelper (IBasePresenter presenter) {
        return new CommonViewHelper(presenter);
    }
}
