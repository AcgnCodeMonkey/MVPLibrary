package com.xujl.baselibrary.mvp.view;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;

import com.xujl.baselibrary.R;
import com.xujl.baselibrary.mvp.common.BaseToolBarModule;
import com.xujl.baselibrary.mvp.common.BaseViewHelper;
import com.xujl.baselibrary.mvp.common.NullLayoutModule;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.port.IBaseView;

import java.util.HashMap;
import java.util.Map;

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
    /**
     * 根布局
     */
    protected View mRootView;
    /**
     * 实际布局
     */
    protected View mContentLayout;
    protected BaseViewHelper mViewHelper;
    private ViewDataBinding mDataBinding;

    //<editor-fold desc="配置">

    @Override
    public int getToolBarId () {
        return 0;
    }

    /**
     * 不使用toolbar时，是否默认为内容页添加父布局
     *
     * @return
     */
    @Override
    public boolean isAddParentLayout () {
        return true;
    }

    @Override
    public boolean enableDataBinding () {
        return false;
    }

    @Override
    public BaseToolBarModule createToolBarModule (IBaseView view, IBasePresenter presenter, int layoutId) {
        return new BaseToolBarModule(presenter.exposeActivity(), layoutId, getViewHelper().getConfig());
    }

    /**
     * 是否使用toolbar,默认是显示，不需要显示时请复写并返回false
     *
     * @return
     */
    @Override
    public boolean enableToolBar () {
        return false;
    }

    @Override
    public boolean enableUseLoadingLayout () {
        return true;
    }
    //</editor-fold>
    //<editor-fold desc="其他方法">

    /**
     * 子类如果需要自定义ToolBarHelper，可以复写initToolBar方法，并且复写次方法并修改返回值类型
     * 新的ToolBarHelper应该要实现IToolBar接口
     *
     * @return
     */
    @Override
    public BaseToolBarModule getToolBarModule () {
        return mViewHelper.getToolBarModule();
    }

    protected BaseViewHelper getViewHelper () {
        return mViewHelper;
    }

    protected BaseViewHelper setViewHelper (IBasePresenter presenter) {
        return new BaseViewHelper();
    }

    @Override
    public View exposeRootView () {
        return mRootView;
    }

    @Override
    public <D extends ViewDataBinding> D getDataBinding () {
        return (D) mDataBinding;
    }

    @Override
    public void showNullView (int code) {
        mViewHelper.showNullView(code);
    }

    @Override
    public void dismissNullView (int code) {
        mViewHelper.dismissNullView(code);
    }

    //</editor-fold>
    @Override
    public <T extends View> T findView (int id) {
        return (T) mRootView.findViewById(id);
    }

    @Override
    public View createUI (IBasePresenter presenter) {
        mViewHelper = setViewHelper(presenter);
        return getViewHelper().createUI(this, presenter);
    }

    @CallSuper
    @Override
    public void initView (IBasePresenter presenter) {
        //未开启dataBinding时不调用相关方法，防止抛出异常
        if (mViewHelper.getConfig().isEnableDataBinding()) {
            mDataBinding = getViewHelper().getDataBinding();
        }
        mRootView = mViewHelper.getRootLayout();
        mContentLayout = mViewHelper.getContentLayout();
    }

    @Override
    public Map<Integer, View> nullLayoutSetting (Context context) {
        final Map<Integer, View> viewMap = new HashMap<>();
        final View view = LayoutInflater.from(context)
                .inflate(R.layout.progress_layout, null);
        viewMap.put(NullLayoutModule.LOADING, view);
        return viewMap;
    }

}
