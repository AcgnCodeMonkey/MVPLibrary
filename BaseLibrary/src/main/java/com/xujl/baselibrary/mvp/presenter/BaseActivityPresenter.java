package com.xujl.baselibrary.mvp.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xujl.baselibrary.Logger;
import com.xujl.baselibrary.mvp.common.BasePresenterHelper;
import com.xujl.baselibrary.mvp.common.NullLayoutModule;
import com.xujl.baselibrary.mvp.port.Callback;
import com.xujl.baselibrary.mvp.port.IBaseModel;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.port.IBaseView;
import com.xujl.baselibrary.mvp.port.LifeCycleCallback;
import com.xujl.baselibrary.utils.ListUtils;
import com.xujl.baselibrary.utils.PermissionsHelper;
import com.xujl.rxlibrary.BaseObservable;
import com.xujl.rxlibrary.BaseObservableEmitter;
import com.xujl.rxlibrary.BaseObserver;
import com.xujl.rxlibrary.RxHelper;
import com.xujl.rxlibrary.RxLife;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


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

public abstract class BaseActivityPresenter<V extends IBaseView, M extends IBaseModel> extends AppCompatActivity
        implements IBasePresenter, EasyPermissions.PermissionCallbacks {

    //<editor-fold desc="基础变量">
    /**
     * 视图
     */
    protected V mView;
    /**
     * 数据
     */
    protected M mModel;
    /**
     * 视图是否已加载过
     */
    protected boolean isViewLoaded;
    /**
     * 界面是否已经加载完成
     */
    protected boolean isViewCompleted;
    protected Bundle savedInstanceState;
    /**
     * 通用逻辑帮助类
     */
    protected BasePresenterHelper mPresenterHelper;
    /**
     * 生命周期回调
     */
    private LifeCycleCallback mLifeCycleCallback;
    /**
     * 动态申请的权限
     * 特指界面加载完成后
     * 单独进行的权限请求
     */
    private String[] varyPermissions;

    protected RxLife mRxLife = new RxLife();
    //</editor-fold>

    //<editor-fold desc="抽象方法">


    /**
     * 初始化逻辑代码，由实现类实现
     *
     * @param savedInstanceState
     */
    protected abstract void initPresenter (Bundle savedInstanceState);

    /**
     * 自动创建view实例，用于关闭mvp模式下。抽象基类应实现此方法
     */
    protected abstract IBaseView autoCreateView ();

    /**
     * 自动创建model实例，用于关闭mvp模式下。抽象基类应实现此方法
     */
    protected abstract IBaseModel autoCreateModel ();


    //</editor-fold>

    //<editor-fold desc="模板方法">

    /**
     * 主加载流程模板
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        //首选加载项，在布局加载之前需要加载的内容可以复写此方法
        firstLoading(savedInstanceState);
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            //防止软件安装直接打开后activity重新启动首页的bug
            finish();
            return;
        }
        //初始化view
        createView();
        //创建视图
        createLayout();
        //初始化控件
        mView.initView(this);
        this.savedInstanceState = savedInstanceState;
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onCreateLife(savedInstanceState);
        }
    }

    private void continueLoading (final Bundle savedInstanceState) {
        //子线程初始化model并回归主线程初始化逻辑
        subToMain(new Callback() {
            @Override
            public void callback () {
                //创建model
                createModel();
                //初始化model
                mModel.initModel(BaseActivityPresenter.this);
            }
        }, new Callback() {
            @Override
            public void callback () {
                /*savedInstanceState不为空时调用界面恢复方法，如果需要重新初始化
                则应该在resumePresenter中重新调用initPresenter
                */
                if (savedInstanceState == null) {
                    mView.dismissNullView(NullLayoutModule.LOADING);
                    //初始化逻辑代码
                    initPresenter(null);
                } else {
                    resumePresenter(savedInstanceState);
                }
            }
        });

    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        isViewCompleted = hasFocus;
        judgeLoading();
    }

    /**
     * 当前界面已获取焦点，且没有加载过，才进行后续加载
     */
    private void judgeLoading () {
        //已经加载过或者当前界面未获取焦点时跳出
        if (isViewLoaded || !isViewCompleted) {
            return;
        }
        isViewLoaded = true;
        final String[] permissions = needPermissions();
        //不需要权限或者需要的权限已全部获取到时直接继续加载
        if (ListUtils.isEmpty(permissions) || EasyPermissions.hasPermissions(exposeContext(), permissions)) {
            //继续加载
            continueLoading(savedInstanceState);
            return;
        }
        //没有授权时发起授权
        requestPermissions(permissions, true);
    }




    private void createView () {
        //不是mvp模式时，直接创建子类实例，不使用反射
        if (!isMVP()) {
            mView = (V) autoCreateView();
            return;
        }
        try {
            final Class<? extends V> viewClassType = getViewClassType();
            /*
             *   判断是否返回了view的实际类型的，返回则通过类类型反射创建实例,
             *   否则尝试使用全限定名进行反射创建对象
             */
            if (viewClassType != null) {
                mView = viewClassType.newInstance();
            } else {
                String className = getClass().getSimpleName();
                String viewClassName = classNameToCreateView(getViewClassPackageName(), className);
                mView = (V) Class.forName(viewClassName).newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (mView == null) {
            throw new NullPointerException("mView初始化失败");
        }
    }

    private void createModel () {
        //不是mvp模式时，直接创建子类实例，不使用反射
        if (!isMVP()) {
            mModel = (M) autoCreateModel();
            return;
        }
        try {
            final Class<? extends M> modelClassType = getModelClassType();
            /*
             *   判断是否返回了model的实际类型的，返回则通过类类型反射创建实例,
             *   否则尝试使用全限定名进行反射创建对象
             */

            if (modelClassType != null) {
                mModel = modelClassType.newInstance();
            } else {
                String className = getClass().getSimpleName();
                String modelClassName = classNameToCreateModel(getModelClassPackageName(), className);
                mModel = (M) Class.forName(modelClassName).newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (mModel == null) {
            throw new NullPointerException("mModel初始化失败");
        }
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
    protected void createLayout () {
        setContentView(mView.createUI(this));
    }

    /**
     * 当前页面需要的权限
     *
     * @return 子类返回权限数组
     */
    protected String[] needPermissions () {
        return new String[]{};
    }

    /**
     * 加载布局之前需要执行的代码，例如请求全屏
     *
     * @param savedInstanceState
     */
    protected void firstLoading (Bundle savedInstanceState) {
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
    //</editor-fold>

    //<editor-fold desc="动态权限">

    /**
     * 动态申请权限
     *
     * @param permissions
     * @return isGet 是否已经获取了权限
     */
    protected boolean requestPermissions (String[] permissions) {
        return requestPermissions(permissions, false);
    }

    /**
     * @param permissions
     * @param isInitRequest 是否动态申请？
     * @return 是否已经获取了权限
     */
    private boolean requestPermissions (String[] permissions, boolean isInitRequest) {
        //不需要权限或者需要的权限
        if (ListUtils.isEmpty(permissions) || EasyPermissions.hasPermissions(exposeContext(), permissions)) {
            //继续加载
            Logger.e("requestPermissions", "已获取到申请的所有权限");
            return true;
        }
        if (!isInitRequest) {
            varyPermissions = permissions;
        }
        //没有授权时发起授权
        EasyPermissions.requestPermissions(this, permissionsDescription(permissions), 996, permissions);
        return false;
    }

    /**
     * 申请的权限描述信息
     * 如果需要变更权限描述，请复写此方法
     * 并返回相关描述信息
     *
     * @param permissions
     * @return
     */
    protected String permissionsDescription (String[] permissions) {
        return "必要的权限:\t" +
                PermissionsHelper.getNoPermissionsStrings(exposeContext(), permissions) +
                "缺少权限会导致无法使用部分功能";
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
     * 分别返回授权成功和失败的权限
     */
    @Override
    public void onPermissionsGranted (int requestCode, List<String> perms) {
        /*
         动态申请的权限varyPermissions不为空时
         表示当前正在进行的权限申请是动态申请
         申请全部被通过时直接回调权限申请成功方法
         */
        if (!ListUtils.isEmpty(varyPermissions)) {
            if (!ListUtils.isEmpty(perms) && perms.size() >= ListUtils.getSize(varyPermissions)) {
                permissionsComplete(varyPermissions);
                varyPermissions = null;
            }
            return;
        }
        //获取到所有权限后进行下一步加载
        if (!ListUtils.isEmpty(perms) && perms.size() >= ListUtils.getSize(needPermissions())) {
            continueLoading(null);
        }
    }

    @Override
    public void onPermissionsDenied (final int requestCode, List<String> perms) {
        if (!ListUtils.isEmpty(perms)) {
            //判断是动态还是静态申请的权限
            if (ListUtils.isEmpty(varyPermissions)) {
                permissionsRefuse(needPermissions());
                return;
            }
            permissionsRefuse(varyPermissions);
            varyPermissions = null;
        }
    }

    /**
     * 权限授权成功
     * 动态申请的权限
     * 成功时会回调此方法
     */
    protected void permissionsComplete (String[] permissions) {

    }

    /**
     * 申请的权限被拒绝
     * 需要自定义显示时
     * 复写此方法
     */
    protected void permissionsRefuse (String[] permissions) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("系统提示");
        builder.setCancelable(false);
        builder.setMessage("缺少权限:\t" + PermissionsHelper.getNoPermissionsStrings(exposeContext(), permissions) + "，是否跳转系统设置手动开启权限？");
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                Intent localIntent = new Intent();
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {
                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    localIntent.setAction(Intent.ACTION_VIEW);
                    localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
                }
                startActivity(localIntent);
                exit();
            }
        });
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                exit();
            }
        });
        builder.show();
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
    protected void resumePresenter (@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void nullViewClick (View view, int code) {

    }

    //</editor-fold>

    //<editor-fold desc="公共方法">



    @Override
    public int getToolBarId () {
        return 0;
    }

    @Override
    public boolean isAddParentLayout () {
        return true;
    }

    @Override
    public boolean enableToolBar () {
        return true;
    }

    @Override
    public boolean enableDataBinding () {
        return false;
    }

    @Override
    public Context exposeContext () {
        return this;
    }

    @Override
    public BaseActivityPresenter exposeActivity () {
        return this;
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

    @Override
    public boolean enableUseLoadingLayout () {
        return true;
    }

    //</editor-fold>

    //<editor-fold desc="生命周期">
    @Override
    protected void onStart () {
        super.onStart();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onStartLife();
        }
    }

    @Override
    protected void onResume () {
        super.onResume();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onResumeLife();
        }
    }

    @Override
    protected void onPause () {
        super.onPause();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onPauseLife();
        }
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onStopLife();
        }
    }

    @Override
    protected void onDestroy () {
        mRxLife.destroyAll();
        super.onDestroy();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onDestroyLife();
        }
        mView = null;
        mModel = null;
    }

    //</editor-fold>
    @Override
    public boolean dispatchTouchEvent (MotionEvent ev) {//解决输入框软键盘点击其他区域不消失的问题。
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 是否隐藏输入框
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput (View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 子线程任务，完成后回调主线程
     *
     * @param taskCallback
     * @param callback
     */
    protected void subToMain (final Callback taskCallback, final Callback callback) {
        RxHelper.onCreate(mRxLife)
                .createNormal(new BaseObservable<Object>() {
                    @Override
                    public void emitAction (BaseObservableEmitter<Object> e) throws Exception {
                        if (taskCallback != null) {
                            taskCallback.callback();
                        }
                        Thread.sleep(70);
                        e.onComplete();
                    }
                })
                .newThreadToMain()
                .run(new BaseObserver<Object>() {
                    @Override
                    public void onComplete () {
                        super.onComplete();
                        if (callback != null) {
                            callback.callback();
                        }
                    }
                });
    }
}
