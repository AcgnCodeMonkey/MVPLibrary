package com.xujl.baselibrary.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xujl.baselibrary.mvp.common.BasePresenterHelper;
import com.xujl.baselibrary.mvp.port.IBaseModel;
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

public abstract class BaseFragmentPresenter<V extends IBaseView, M extends IBaseModel> extends Fragment implements IBasePresenter {
    protected V mView;//视图
    protected M mModel;//数据
    protected View mRootView;
    protected BasePresenterHelper mPresenterHelper;//通用逻辑帮助类
    protected boolean isVisible;//Fragment当前状态是否可见
    protected boolean isLoading;//是否已加载过

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firstLoading(savedInstanceState);//首选加载项，在布局加载之前需要加载的东西
        if (isMVP()) {
            creatViewModel();//初始化view和model
        } else {
            autoCreatViewModel();
        }
        mRootView = createLayout(inflater, container, savedInstanceState);//创建布局
        mView.initView(this);//初始化控件
        initPresenter(savedInstanceState);//初始化逻辑代码
        //判断界面是否可见，如果需要在界面可见时才加载某些功能需要把相关代码写在lazyLoad ()方法中
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
        return mRootView;
    }
    //================抽象方法==================================================================================================

    /**
     * 初始化逻辑代码，由实现类实现
     *
     * @param savedInstanceState
     */
    protected abstract void initPresenter (Bundle savedInstanceState);

    /**
     * 自动创建view和model实例，用于关闭mvp模式下。抽象基类应实现此方法
     */
    protected abstract void autoCreatViewModel ();

    /**
     * 获取view实际类型
     *
     * @return
     */
    protected abstract Class<? extends V> getViewClassType ();

    /**
     * 获取model实际类型
     *
     * @return
     */
    protected abstract Class<? extends M> getModelClassType ();


    //================模板方法==================================================================================================

    /**
     * 反射实例化view和model
     */
    private void creatViewModel () {
        try {
            mView = getViewClassType().newInstance();
            mModel = getModelClassType().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建布局
     */
    protected View createLayout (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(mView.getLayoutId(), null);
    }

    /**
     * 加载布局之前需要执行的代码，例如请求全屏
     *
     * @param savedInstanceState
     */
    protected void firstLoading (Bundle savedInstanceState) {
        //TO DO
    }

    protected BasePresenterHelper getPresenterHelper () {
        if (mPresenterHelper == null) {
            mPresenterHelper = new BasePresenterHelper();
        }
        return mPresenterHelper;
    }

    public void setPresenterHelper (BasePresenterHelper presenterHelper) {
        mPresenterHelper = presenterHelper;
    }

    protected void onInvisible () {
        //fragment不可见
    }

    protected void onVisible () {
        //fragment可见
        if (!isLoading) {
            isLoading = true;
            lazyLoad();
        }
    }

    protected void lazyLoad () {
        //延时加载
    }

    protected void resetLoadingState () {
        isLoading = false;
    }

    @Override
    public void setUserVisibleHint (boolean isVisibleToUser) {//设置fragment是否可见
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    //================公共方法==================================================================================================

    @Override
    public Context exposeContext () {
        return getContext();
    }

    @Override
    public BaseActivityPresenter exposeActivity () {
        return (BaseActivityPresenter) getActivity();
    }

    @Override
    public View exposeRootView () {
        return mRootView;
    }

    @Override
    public IBaseView exposeView () {
        return mView;
    }

    @Override
    public IBaseModel exposeModel () {
        return mModel;
    }

    @Override
    public boolean isMVP () {
        return true;
    }

}
