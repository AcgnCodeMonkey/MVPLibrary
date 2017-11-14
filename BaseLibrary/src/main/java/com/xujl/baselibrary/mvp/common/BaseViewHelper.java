package com.xujl.baselibrary.mvp.common;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xujl.baselibrary.R;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.port.IBaseVP;
import com.xujl.baselibrary.mvp.port.IBaseView;
import com.xujl.baselibrary.mvp.presenter.BaseFragmentPresenter;

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
 * Created by xujl on 2017/5/2.
 */

public class BaseViewHelper extends BaseMvpHelper {
    /**
     * 根布局
     */
    protected View mRootLayout;
    /**
     * 实际布局
     */
    protected View mContentLayout;
    /**
     * 导航模块
     */
    protected BaseToolBarModule mToolBarModule;
    /**
     * 布局配置
     */
    protected LayoutConfig mConfig;
    protected ViewDataBinding mDataBinding;

    /**
     * 创建视图，通过布局配置参数决定如何加载
     *
     * @param view
     * @param presenter
     * @return
     */
    public View createUI (IBaseVP view, IBaseVP presenter) {
        final IBaseView baseView = (IBaseView) view;
        final IBasePresenter basePresenter = (IBasePresenter) presenter;
        //初始化布局加载配置
        initLayoutConfig(baseView, basePresenter);
        /**
         *   如果当前为fragment时，为当前的toobarModule赋值为activity的toolbarModule，并直接进行加载
         *   因为fragment是没有导航的
         */
        if (!mConfig.isActivity()) {
            mToolBarModule = basePresenter.exposeActivity().exposeView().getToolBarModule();
            inflateLayout(basePresenter);
            return mRootLayout;
        }
        /**
         * 是否使用toolBar是由presenter和view共同控制的，只有当两边都返回true时才会使用toolbar
         * 不使用默认toolBar时，布局由当前helper类进行加载，使用时由toolBarModule进行布局加载
         */
        if (mConfig.isEnableToolBar()) {
            //初始化导航
            mToolBarModule = baseView.createToolBarModule(baseView, basePresenter, mConfig.getLayoutId());
            mToolBarModule.initSetting(basePresenter.exposeActivity());
            //通过导航模块类获取根布局及内容布局
            mDataBinding = mToolBarModule.getDataBinding();
            mRootLayout = mToolBarModule.getRootView();
            mContentLayout = mToolBarModule.getContentLayout();
        } else {
            inflateLayout(basePresenter);
        }
        return mRootLayout;
    }

    /**
     * 使用dataBinding加载布局
     *
     * @param presenter
     * @return
     */
    private View inflateLayoutForDataBinding (IBasePresenter presenter) {
        //判断是activity还是fragment然后进行对应加载
        if (mConfig.isActivity()) {
            mDataBinding = DataBindingUtil.setContentView((Activity) presenter, mConfig.getLayoutId());
        } else {
            final BaseFragmentPresenter fragmentPresenter = (BaseFragmentPresenter) presenter;
            mDataBinding = DataBindingUtil.inflate(fragmentPresenter.getInflater(), mConfig.getLayoutId(), fragmentPresenter.getContainer(), false);
        }
        //获取根布局
        mRootLayout = mDataBinding.getRoot().findViewById(R.id.dataBindingRootLayout);
        //未使用导航时，根布局就是内容布局
        mContentLayout = mRootLayout;
        return mRootLayout;
    }


    /**
     * @param basePresenter
     * @return
     */
    public void inflateLayout (IBasePresenter basePresenter) {
        //判断是否启用dataBinding进行加载
        if (mConfig.isEnableDataBinding()) {
            inflateLayoutForDataBinding(basePresenter);
            return;
        }
        //判断是否需要为内容布局添加一个父布局
        if (mConfig.isAddParentLayout()) {
            mRootLayout = new LinearLayout(basePresenter.exposeContext());
            mRootLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((LinearLayout) mRootLayout).setOrientation(LinearLayout.VERTICAL);
            mContentLayout = LayoutInflater.from(basePresenter.exposeContext()).inflate(mConfig.getLayoutId(), null);
            mContentLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((LinearLayout) mRootLayout).addView(mContentLayout);
        } else {
            //无父布局时，根布局就是内容布局
            mRootLayout = LayoutInflater.from(basePresenter.exposeContext()).inflate(mConfig.getLayoutId(), null);
            mRootLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mContentLayout = mRootLayout;
        }
    }

    /**
     * 初始化布局配置
     *
     * @param view
     * @param presenter
     */
    protected void initLayoutConfig (IBaseView view, IBasePresenter presenter) {
        mConfig = new LayoutConfig(view, presenter);
    }

    //<editor-fold desc="Getter方法">

    public View getContentLayout () {
        return mContentLayout;
    }

    public BaseToolBarModule getToolBarModule () {
        return mToolBarModule;
    }

    public LayoutConfig getConfig () {
        return mConfig;
    }

    public View getRootLayout () {
        return mRootLayout;
    }

    public <D> D getDataBinding () {
        return (D) mDataBinding;
    }
    //</editor-fold>
}
