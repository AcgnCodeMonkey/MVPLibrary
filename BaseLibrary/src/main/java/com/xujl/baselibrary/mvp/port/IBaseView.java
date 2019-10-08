package com.xujl.baselibrary.mvp.port;

import android.content.Context;
import android.view.View;

import com.xujl.baselibrary.mvp.common.BaseToolBarModule;
import com.xujl.baselibrary.mvp.common.NullLayoutModule;

import java.util.Map;

import androidx.databinding.ViewDataBinding;

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

public interface IBaseView extends IBaseVP {
    /**
     * 初始化操作，由presenter负责调用
     *
     * @param presenter 实际对象
     */
    void initView (IBasePresenter presenter);

    /**
     * 创建视图
     *
     * @param presenter
     * @return 返回创建的视图
     */
    View createUI (IBasePresenter presenter);

    /**
     * @return 返回根布局
     */
    View exposeRootView ();

    /**
     * 通过id找控件
     *
     * @param id
     * @param <T>
     * @return
     */
    <T extends View> T findView (int id);

    /**
     * 获取当前页面的databinding
     *
     * @param <D>
     * @return
     */
    <D extends ViewDataBinding> D getDataBinding ();

    /**
     * @return 返回页面导航模块
     */
    BaseToolBarModule getToolBarModule ();

    /**
     * 创建页面导航模块
     *
     * @param view
     * @param presenter
     * @param layoutId  页面布局id
     * @return
     */
    BaseToolBarModule createToolBarModule (IBaseView view, IBasePresenter presenter, int layoutId);

    /**
     * toast提示用户消息
     * 方法已经过
     * @param context
     * @param msg
     * @param code
     * @deprecated 请使用 {@link #toast(String, int)} 代替.
     */
    @Deprecated
    void showToastMsg (Context context, String msg, int code);

    /**
     * toast提示用户消息，自定义时间
     *
     * @param context
     * @param msg
     * @param code
     * @param time
     * @deprecated 请使用 {@link #toast(String, int,int)} 代替.
     */
    @Deprecated
    void showToastMsg (Context context, String msg, int code, int time);

    /**
     * toast提示用户消息
     * @param msg
     * @param code
     */
    void toast (String msg, int code);

    /**
     * toast提示用户消息
     * @param msg
     * @param code
     * @param time
     */
    void toast (String msg, int code, int time);
    /**
     * 显示加载
     */
    void showLoading ();

    /**
     * 隐藏加载
     */
    void dismissLoading ();

    /**
     * 显示空布局
     * @param code
     */
    void showNullView (@NullLayoutModule.Code int code);
    /**
     * 隐藏空布局
     * @param code
     */
    void dismissNullView (@NullLayoutModule.Code int code);

    /**
     * 空布局配置
     * @return
     */
    Map<Integer,View> nullLayoutSetting (Context context);
}
