package com.xujl.baselibrary.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xujl.baselibrary.mvp.common.BasePresenterHelper;
import com.xujl.baselibrary.mvp.common.BaseToolBarModule;
import com.xujl.baselibrary.mvp.port.IBaseModel;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.baselibrary.mvp.port.IBaseView;
import com.xujl.baselibrary.mvp.port.LifeCycleCallback;
import com.xujl.baselibrary.utils.ActivityManger;
import com.xujl.baselibrary.utils.ListUtils;
import com.xujl.baselibrary.utils.PermissionsHelper;

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
    protected V mView;//视图
    protected M mModel;//数据
    protected BasePresenterHelper mPresenterHelper;//通用逻辑帮助类
    private BaseToolBarModule mToolBarModule;
    /**
     * 生命周期回调
     */
    private LifeCycleCallback mLifeCycleCallback;//生命周期回调

    /**
     * 初始化导航栏
     */
    protected void initToolBar () {
        mToolBarModule =  setDefaultToolBarHelper();
        getToolBarModule().initSetting(this);
    }

    /**
     * 子类可以复写此方法修改默认toobarhelper
     */
    public BaseToolBarModule setDefaultToolBarHelper () {
        return new BaseToolBarModule(exposeActivity(), mView.getLayoutId());
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
    //================抽象方法==================================================================================================

    /**
     * 生命周期回调，设置后各个生命周期方法会回调此接口
     *
     * @param mLifeCycleCallback
     */
    public void setmLifeCycleCallback (LifeCycleCallback mLifeCycleCallback) {
        this.mLifeCycleCallback = mLifeCycleCallback;
    }

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

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        firstLoading(savedInstanceState);//首选加载项，在布局加载之前需要加载的东西
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            //防止软件安装直接打开后activity重新启动首页的bug
            finish();
            return;
        }
        //判断是否需要申请权限决定是否继续加载
        final String[] permissions = needPermissions();
        if (ListUtils.isEmpty(permissions)) {
            continueLoading(savedInstanceState);//继续加载
            return;
        }
        final boolean hasPermissions = EasyPermissions.hasPermissions(exposeContext(), permissions);
        //判断需要的权限是否已经授权决定是否继续加载
        if (hasPermissions) {
            continueLoading(savedInstanceState);//继续加载
            return;
        }
        //没有授权时发起授权
        EasyPermissions.requestPermissions(this, "必要的权限:\t" +
                PermissionsHelper.getNoPermissionsStrings(exposeContext(), permissions) +
                "缺少权限会导致无法使用部分功能", 996, permissions);

    }


    //================模板方法==================================================================================================
    private void continueLoading (final Bundle savedInstanceState) {
        if (isMVP()) {
            creatViewModel();//初始化view和model
        } else {
            autoCreatViewModel();
        }
        createLayout();//创建布局
        mView.initView(this);//初始化控件
        ActivityManger.newInstance().addActivity(this);//管理打开的activity
        initPresenter(savedInstanceState);//初始化逻辑代码

        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onCreateLife(savedInstanceState);
        }
    }


    /**
     * 反射实例化view和model
     */
    private void creatViewModel () {
        try {
            final Class<? extends V> viewClassType = getViewClassType();
            final Class<? extends M> modelClassType = getModelClassType();
            //判断是否复写了返回model与view的实际类型的方法，返回则直接创建实例
            if (viewClassType != null) {
                mView = viewClassType.newInstance();
            }
            if (modelClassType != null) {
                mModel = modelClassType.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建布局
     */
    protected void createLayout () {
        //判断是否使用导航
        if (enableToolBar()) {
            initToolBar();//初始化导航
            setContentView(getToolBarModule().getRootView());
        } else {
            setContentView(LayoutInflater.from(this).inflate(mView.getLayoutId(), null));
        }
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

    //================动态权限==================================================================================================
    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //下面两个方法是实现EasyPermissions的EasyPermissions.PermissionCallbacks接口
    //分别返回授权成功和失败的权限
    @Override
    public void onPermissionsGranted (int requestCode, List<String> perms) {
        if (!ListUtils.isEmpty(perms) && perms.size() >= ListUtils.getSize(needPermissions())) {
            continueLoading(null);
        }
    }

    @Override
    public void onPermissionsDenied (final int requestCode, List<String> perms) {
        if (!ListUtils.isEmpty(perms)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("系统提示");
            builder.setMessage("缺少权限:\t" + PermissionsHelper.getNoPermissionsStrings(exposeContext(), needPermissions()) + "，是否跳转系统设置手动开启权限？");
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
    }

    //================公共方法==================================================================================================

    @Override
    public Context exposeContext () {
        return this;
    }

    @Override
    public Activity exposeActivity () {
        return this;
    }

    @Override
    public IBasePresenter exposeBasePresenter () {
        return this;
    }

    @Override
    public View exposeRootView () {
        return getWindow().getDecorView().getRootView();
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
    public boolean isShouldHideInput (View v, MotionEvent event) {
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
        super.onDestroy();
        if (mLifeCycleCallback != null) {
            mLifeCycleCallback.onDestroyLife();
        }
    }


}
