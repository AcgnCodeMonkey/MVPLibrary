package com.xujl.baselibrary.mvp.common;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xujl.baselibrary.Logger;
import com.xujl.baselibrary.R;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
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
    private static final String TAG = "BaseViewHelper----------->";
    /**
     * 布局配置
     */
    protected LayoutConfig mConfig;
    /**
     * 根布局
     */
    protected ViewGroup mRootLayout;
    /**
     * 实际布局
     */
    protected View mContentLayout;
    /**
     * 空布局模块
     */
    protected NullLayoutModule mNullLayoutModule;
    /**
     * 导航模块
     */
    protected BaseToolBarModule mToolBarModule;

    protected ViewDataBinding mDataBinding;

    /**
     * 创建视图，通过布局配置参数决定如何加载
     *
     * @param view
     * @param presenter
     * @return
     */
    public View createUI (IBaseView view, IBasePresenter presenter) {
        //初始化布局加载配置
        initLayoutConfig(view, presenter);
        //判断使用activity加载还是fragment加载
        if (!mConfig.isActivity()) {
            fragmentMode(view, presenter);
        } else {
            activityMode(view, presenter);
        }
        return mRootLayout;
    }

    public void activityMode (IBaseView view, IBasePresenter presenter) {
        /**
         * 是否使用toolBar是由presenter和view共同控制的，只有当两边都返回true时才会使用toolbar
         */
        inflateLayout(presenter);
        if (!mConfig.isEnableToolBar()) {
            return;
        }
        //初始化导航
        mToolBarModule = view.createToolBarModule(view, presenter, mConfig.getLayoutId());
        final View toolBar = mToolBarModule.findToolBar(presenter.exposeActivity(), mConfig, mRootLayout);
        mToolBarModule.initSetting(presenter);
        if (toolBar != null && toolBar.getParent() == null) {
            mRootLayout.addView(toolBar, 0);
        }
    }

    /**
     * 如果当前为fragment时，为当前的toobarModule赋值为activity的toolbarModule，并直接进行加载
     * 因为fragment是没有导航的
     */
    public void fragmentMode (IBaseView view, IBasePresenter presenter) {
        inflateLayout(presenter);
        if (!mConfig.isEnableToolBar()) {
            return;
        }
//        mToolBarModule = presenter.exposeActivity().exposeView().getToolBarModule();
        //初始化导航
        mToolBarModule = view.createToolBarModule(view, presenter, mConfig.getLayoutId());
        final View toolBar = mToolBarModule.findToolBar(presenter.exposeContext(), mConfig, mRootLayout);
        mToolBarModule.initSetting(presenter);
        if (toolBar != null && toolBar.getParent() == null) {
            mRootLayout.addView(toolBar, 0);
        }
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
        mContentLayout = mRootLayout.getChildAt(0);
        loadNullLayout(presenter.exposeView(), mRootLayout, mContentLayout, presenter);
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
        if (!mConfig.isAddParentLayout()) {
            //无父布局时，根布局就是内容布局
            mRootLayout = (ViewGroup) LayoutInflater.from(basePresenter.exposeContext()).inflate(mConfig.getLayoutId(), null);
            mRootLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mContentLayout = mRootLayout;
            loadNullLayout(basePresenter.exposeView(), (ViewGroup) mContentLayout.getParent(), mContentLayout, basePresenter);
            return;
        }
        mRootLayout = new LinearLayout(basePresenter.exposeContext());
        mRootLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((LinearLayout) mRootLayout).setOrientation(LinearLayout.VERTICAL);

        mContentLayout = LayoutInflater.from(basePresenter.exposeContext()).inflate(mConfig.getLayoutId(), null);
        mContentLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        loadNullLayout(basePresenter.exposeView(), mRootLayout, mContentLayout, basePresenter);
        //添加真实布局
        mRootLayout.addView(mContentLayout);

    }

    private void loadNullLayout (IBaseView view, ViewGroup parent, View content, IBasePresenter presenter) {
        if (mConfig.isUseLoadingLayout()) {
            //隐藏真实布局
            content.setVisibility(View.GONE);
            //初始化空布局工具
            mNullLayoutModule = new NullLayoutModule(parent);
            //设置空布局控件map
            mNullLayoutModule.setNullView(view.nullLayoutSetting(parent.getContext()));
            mNullLayoutModule.setNullViewClickListener(presenter);
            //显示加载空布局
            mNullLayoutModule.showView(NullLayoutModule.LOADING);
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

    //<editor-fold desc="暴露方法">

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

    public void showNullView (@NullLayoutModule.Code int code) {
        if (mNullLayoutModule == null) {
            Logger.e(TAG, "当前页面未使用空布局，请确认！");
            return;
        }
        mContentLayout.setVisibility(View.GONE);
        mNullLayoutModule.showView(code);
    }

    public void dismissNullView (@NullLayoutModule.Code int code) {
        if (mNullLayoutModule == null) {
            Logger.e(TAG, "当前页面未使用空布局，请确认！");
            return;
        }
        mNullLayoutModule.dismissView(code);
        mContentLayout.setVisibility(View.VISIBLE);
    }

    public NullLayoutModule getNullLayoutModule () {
        return mNullLayoutModule;
    }

    //</editor-fold>
}
