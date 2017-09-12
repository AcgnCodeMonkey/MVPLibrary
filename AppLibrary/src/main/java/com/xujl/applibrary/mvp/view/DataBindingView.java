package com.xujl.applibrary.mvp.view;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.xujl.applibrary.mvp.common.DataBindingToolBarModule;
import com.xujl.applibrary.mvp.common.DataBindingViewHelper;
import com.xujl.applibrary.mvp.common.ToolBarModule;
import com.xujl.applibrary.mvp.port.ICommonPresenter;
import com.xujl.applibrary.mvp.port.IDataBindingView;
import com.xujl.baselibrary.mvp.common.BaseViewHelper;
import com.xujl.baselibrary.mvp.port.IBasePresenter;

/**
 * Created by xujl on 2017/9/12.
 */

public abstract class DataBindingView<D extends ViewDataBinding> extends CommonView implements IDataBindingView {
    protected D mDataBinding ;

    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mDataBinding = getViewHelper().getDataBinding();
    }
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
            return getViewHelper().inflateLayout(presenter.exposeActivity(),getLayoutId());
        }
    }

    @Override
    public DataBindingToolBarModule getToolBarModule () {
        return (DataBindingToolBarModule) super.getToolBarModule();
    }

    @Override
    protected ToolBarModule setDefaultToolBarHelper (IBasePresenter presenter) {
        if (presenter.isMVP()) {
            return new DataBindingToolBarModule(presenter.exposeActivity(), getLayoutId());
        }
        return new DataBindingToolBarModule(presenter.exposeActivity(), ((ICommonPresenter) presenter).getLayoutId());
    }

    @Override
    protected BaseViewHelper setViewHelper (IBasePresenter presenter) {
        return new DataBindingViewHelper(presenter,this);
    }

    @Override
    protected DataBindingViewHelper getViewHelper () {
        return (DataBindingViewHelper) super.getViewHelper();
    }

    public D getDataBinding () {
        return mDataBinding;
    }
}
