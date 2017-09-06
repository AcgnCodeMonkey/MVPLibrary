package com.xujl.baselibrary.mvp.view;

import android.app.Activity;
import android.support.annotation.CallSuper;
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
        mViewHelper = setViewHelper(presenter);
        if (!(presenter instanceof Activity)) {
            mToolBarModule = presenter.exposeActivity().exposeView().getToolBarModule();
            return getViewHelper().inflateLayout(getLayoutId(), presenter.exposeContext(),isAddParentView());
        }
        //是否使用toolBar是由presenter和view共同控制的，只有当两边都返回true时才会使用toolbar
        if (enableToolBar() && presenter.enableToolBar()) {
            initToolBar(presenter);//初始化导航
            return getToolBarModule().getRootView();
        } else {
            return getViewHelper().inflateLayout(getLayoutId(), presenter.exposeContext(),isAddParentView());
        }
    }

    /**
     * 不使用toolbar时，是否默认为内容页添加父布局
     * @return
     */
    protected boolean isAddParentView(){
        return true;
    }
    @Override
    public View exposeParentView () {
        if (getViewHelper().getParentLayout() != null) {
            return getViewHelper().getParentLayout();
        } else {
            return mToolBarModule.getRootView();
        }
    }

    /**
     * 初始化导航栏
     */
    public  void initToolBar (IBasePresenter presenter) {
        mToolBarModule = setDefaultToolBarHelper(presenter);
        getToolBarModule().initSetting(presenter.exposeActivity());
    }

    /**
     * 子类可以复写此方法修改默认toobarhelper，
     * 如果当前布局中已经包含了自己写的toolbar，可以使用BaseToolBarModule另一个包含了
     * toolbar的id的构造器
     */
    protected BaseToolBarModule setDefaultToolBarHelper (IBasePresenter presenter) {
        return new BaseToolBarModule(presenter.exposeActivity(), getLayoutId());
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
     *
     * @return
     */
    public boolean enableToolBar () {
        return true;
    }

    protected BaseViewHelper getViewHelper () {
        return mViewHelper;
    }

    protected BaseViewHelper setViewHelper (IBasePresenter presenter) {
        return new BaseViewHelper();
    }

    @CallSuper
    @Override
    public void initView (IBasePresenter presenter) {
        mRootView = presenter.exposeRootView();
    }

    @Override
    public <T extends View> T findView (int id) {
        return (T) mRootView.findViewById(id);
    }
}
