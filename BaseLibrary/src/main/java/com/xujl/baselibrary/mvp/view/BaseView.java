package com.xujl.baselibrary.mvp.view;

import android.support.annotation.CallSuper;
import android.view.View;

import com.xujl.baselibrary.mvp.common.BaseViewHelper;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.port.IBaseView;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * Created by xujl on 2017/4/28.
 */

public abstract class BaseView implements IBaseView {
    protected View mRootView;
    private BaseViewHelper mViewHelper;

    protected BaseViewHelper getViewHelper () {
        if (mViewHelper == null) {
            mViewHelper = new BaseViewHelper();
        }
        return mViewHelper;
    }

    public void setViewHelper (BaseViewHelper viewHelper) {
        mViewHelper = viewHelper;
    }

    @CallSuper
    @Override
    public void initView (IBasePresenter presenter) {
        mRootView = presenter.exposeRootView();
    }

}
