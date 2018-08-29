package com.xujl.applibrary.mvp.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.xujl.applibrary.mvp.common.CommonViewHelper;
import com.xujl.applibrary.mvp.common.ToolBarModule;
import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.baselibrary.mvp.common.BaseToolBarModule;
import com.xujl.baselibrary.mvp.common.BaseViewHelper;
import com.xujl.baselibrary.mvp.common.NullLayoutModule;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.port.IBaseView;
import com.xujl.baselibrary.mvp.view.BaseView;

import java.util.Map;

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
    public void toast (String msg, int code) {
        getViewHelper().toast(msg, code);
    }

    @Override
    public void toast (String msg, int code, int time) {
        getViewHelper().toast(msg, code, time);
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

    @Override
    public BaseToolBarModule createToolBarModule (IBaseView view, IBasePresenter presenter, int layoutId) {
        return new ToolBarModule(presenter.exposeActivity(), layoutId, getViewHelper().getConfig());
    }

    @Override
    protected BaseViewHelper setViewHelper (IBasePresenter presenter) {
        return new CommonViewHelper(presenter);
    }

    @Override
    public Map<Integer, View> nullLayoutSetting (Context context) {
        final Map<Integer, View> viewMap = super.nullLayoutSetting(context);
        viewMap.get(NullLayoutModule.LOADING).setBackgroundColor(0xFF222222);
        return viewMap;
    }
}
