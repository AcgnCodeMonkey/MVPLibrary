package com.xujl.baselibrary.mvp.view;

import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;

import com.xujl.baselibrary.mvp.common.BaseToolBarModule;
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
    private BaseToolBarModule mToolBarModule;

    @Override
    public View creatView (IBasePresenter presenter) {
        if(enableToolBar()){
            initToolBar(presenter);//初始化导航
            return getToolBarModule().getRootView();
        }else{
            return  LayoutInflater.from(presenter.exposeContext()).inflate(getLayoutId(),null);
        }
    }

    /**
     * 初始化导航栏
     */
    protected void initToolBar (IBasePresenter presenter) {
        mToolBarModule =  setDefaultToolBarHelper(presenter);
        getToolBarModule().initSetting(presenter.exposeActivity());
    }

    /**
     * 子类可以复写此方法修改默认toobarhelper
     */
    protected BaseToolBarModule setDefaultToolBarHelper (IBasePresenter presenter) {
        return new BaseToolBarModule( presenter.exposeActivity(),getLayoutId());
    }

    /**
     * 子类如果需要自定义ToolBarHelper，可以复写initToolBar方法，并且复写次方法并修改返回值类型
     * 新的ToolBarHelper应该要实现IToolBar接口
     *
     * @return
     */
    public BaseToolBarModule getToolBarModule () {
        return mToolBarModule;
    }

    /**
     * 是否使用toolbar,默认是显示，不需要显示时请复写并返回false
     * @return
     */
    public boolean enableToolBar () {
        return true;
    }

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
