package com.xujl.baselibrary.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xujl.baselibrary.mvp.common.BasePresenterHelper;
import com.xujl.baselibrary.mvp.port.IBaseModel;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.port.IBaseView;
import com.xujl.baselibrary.mvp.port.LifeCycleCallback;

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

    //<editor-fold desc="基础变量">
    /**
     * 视图
     */
    protected V mView;
    /**
     * 数据
     */
    protected M mModel;
    protected View mRootView;
    /**
     * 通用逻辑帮助类
     */
    protected BasePresenterHelper mPresenterHelper;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    /**
     * 是否已加载过
     */
    protected boolean isLoaded;
    /**
     * 是否初始化完成了控件
     */
    protected boolean isViewCompleted;
    /**
     * 是否每次fragment重新显示都重新懒加载
     */
    protected boolean isEveryReload;
    protected LayoutInflater inflater;
    protected ViewGroup container;
    /**
     * 生命周期回调
     */
    private LifeCycleCallback mLifeCycleCallback;
    //</editor-fold>

    //<editor-fold desc="抽象方法">

    /**
     * 初始化逻辑代码，由实现类实现
     *
     * @param savedInstanceState
     */
    protected abstract void initPresenter (Bundle savedInstanceState);

    /**
     * 自动创建view和model实例，用于关闭mvp模式下。抽象基类应实现此方法
     */
    protected abstract void autoCreateViewModel ();

    //</editor-fold>

    //<editor-fold desc="模板方法">
    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //首选加载项，在布局加载之前需要加载的东西
        firstLoading(savedInstanceState);
        //初始化view和model
        createViewModel();
        if (mView == null) {
            throw new NullPointerException("mView初始化失败");
        }
        if (mModel == null) {
            throw new NullPointerException("mModel初始化失败");
        }
        //创建布局
        mRootView = createLayout(inflater, container, savedInstanceState);
        //初始化控件
        mView.initView(this);
        mModel.initModel(this);
        /*savedInstanceState不为空时调用界面恢复方法，如果需要重新初始化
        则应该在resumePresenter中重新调用initPresenter
         */
//        if (savedInstanceState == null) {
            //初始化逻辑代码
            initPresenter(null);
//        } else {
//            resumePresenter(savedInstanceState);
//        }
        isViewCompleted = true;
        isEveryReload = isEveryReload();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onCreateLife(savedInstanceState);
        }
        //判断界面是否可见，如果需要在界面可见时才加载某些功能需要把相关代码写在lazyLoad ()方法中
        isVisible = getUserVisibleHint();
        if (isVisible) {
            onVisible();
        } else {
            onInVisible();
        }
        return mRootView;
    }


    /**
     * 反射实例化view和model
     */
    private void createViewModel () {
        //不是mvp模式时，直接创建子类实例，不使用反射
        if (!isMVP()) {
            autoCreateViewModel();
            return;
        }
        try {
            final Class<? extends V> viewClassType = getViewClassType();
            final Class<? extends M> modelClassType = getModelClassType();
            /**
             *   判断是否返回了model与view的实际类型的，返回则通过类类型反射创建实例,
             *   否则尝试使用全限定名进行反射创建对象
             */
            if (viewClassType != null && modelClassType != null) {
                mView = viewClassType.newInstance();
                mModel = modelClassType.newInstance();
            } else {
                String className = getClass().getSimpleName();
                String viewClassName = classNameToCreateView(getViewClassPackageName(), className);
                mView = (V) Class.forName(viewClassName).newInstance();
                String modelClassName = classNameToCreateModel(getModelClassPackageName(), className);
                mModel = (M) Class.forName(modelClassName).newInstance();
            }
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取view包路径
     *
     * @return
     */
    protected String getViewClassPackageName () {
        return null;
    }

    /**
     * 获取model包路径
     *
     * @return
     */
    protected String getModelClassPackageName () {
        return null;
    }

    /**
     * 获取view实际类型
     *
     * @return
     */
    protected Class<? extends V> getViewClassType () {
        return null;
    }

    /**
     * 获取model实际类型
     *
     * @return
     */
    protected Class<? extends M> getModelClassType () {
        return null;
    }

    /**
     * 尝试通过包名和presenter类名创建view的全限定名
     *
     * @param viewClassPackageName
     * @param simpleName
     * @return
     */
    protected String classNameToCreateView (String viewClassPackageName, String simpleName) {
        return viewClassPackageName + "." + simpleName.replace("Presenter", "");
    }

    /**
     * 尝试通过包名和presenter类名创建model的全限定名
     *
     * @param viewClassPackageName
     * @param simpleName
     * @return
     */
    protected String classNameToCreateModel (String viewClassPackageName, String simpleName) {
        return viewClassPackageName + "." + simpleName.replace("Presenter", "") + "Model";
    }

    /**
     * 创建布局
     */
    protected View createLayout (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        return mView.createUI(this);
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

    /**
     * fragment不可见回调
     */
    protected void onInVisible () {
        //TO DO
        if (isLoaded && isViewCompleted && isEveryReload) {
            isLoaded = false;
        }
    }

    /**
     * fragment可见回调
     */
    protected void onVisible () {
        //数据未加载过并且控件已经初始化完成时，进行懒加载
        if (!isLoaded && isViewCompleted) {
            isLoaded = true;
            lazyLoad();
        }
    }

    /**
     * 复写此方法实现懒加载数据
     */
    protected void lazyLoad () {
    }

    /**
     * 是否每次显示fragment都重新加载
     *
     * @return
     */
    protected boolean isEveryReload () {
        return false;
    }

    /**
     * 重置当前的加载状态标识，让下次fragment再次显示时重新加载
     */
    protected void resetLoadingState () {
        isLoaded = false;
    }

    @Override
    public void setUserVisibleHint (boolean isVisibleToUser) {//设置fragment是否可见
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisible) {
            onVisible();
        } else {
            onInVisible();
        }
    }
    //</editor-fold>

    //<editor-fold desc="配置方法">

    public LayoutInflater getInflater () {
        return inflater;
    }

    public ViewGroup getContainer () {
        return container;
    }

    @Override
    public int getToolBarId () {
        return 0;
    }

    @Override
    public boolean enableToolBar () {
        return true;
    }

    @Override
    public boolean isAddParentLayout () {
        return true;
    }

    @Override
    public boolean enableDataBinding () {
        return false;
    }

    @Override
    public boolean isMVP () {
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="其他方法">

    /**
     * 生命周期回调，设置后各个生命周期方法会回调此接口
     *
     * @param mLifeCycleCallback
     */
    protected void setmLifeCycleCallback (LifeCycleCallback mLifeCycleCallback) {
        this.mLifeCycleCallback = mLifeCycleCallback;
    }

    /**
     * 恢复被回收的界面
     *
     * @param savedInstanceState
     */
    protected void resumePresenter (@NonNull Bundle savedInstanceState) {

    }

    //</editor-fold>

    //<editor-fold desc="Getter方法">
    @Override
    public Context exposeContext () {
        return getContext();
    }

    @Override
    public BaseActivityPresenter exposeActivity () {
        return (BaseActivityPresenter) getActivity();
    }

    @Override
    public IBaseView exposeView () {
        return mView;
    }

    @Override
    public IBaseModel exposeModel () {
        return mModel;
    }

    //</editor-fold>

    //<editor-fold desc="生命周期">
    @Override
    public void onStart () {
        super.onStart();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onStartLife();
        }
    }

    @Override
    public void onResume () {
        super.onResume();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onResumeLife();
        }
    }

    @Override
    public void onPause () {
        super.onPause();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onPauseLife();
        }
    }

    @Override
    public void onStop () {
        super.onStop();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onStopLife();
        }
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onDestroyLife();
        }
        mView = null;
        mModel = null;
    }
    //</editor-fold>
}
