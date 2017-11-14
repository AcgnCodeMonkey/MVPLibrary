package com.xujl.baselibrary.mvp.port;

import android.content.Context;
import android.view.View;

import com.xujl.baselibrary.mvp.presenter.BaseActivityPresenter;

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

public interface IBasePresenter extends View.OnClickListener,IBaseVP {
    /**
     * 退出界面
     */
    void exit();

    /**
     *
     * @return 获取context
     */
    Context exposeContext ();

    /**
     *
     * @return 获取activity
     */
    BaseActivityPresenter exposeActivity ();

    /**
     *
     * @return 获取视图模块
     */
    IBaseView exposeView ();

    /**
     *
     * @return 获取数据模块
     */
    IBaseModel exposeModel ();

    /**
     *
     * @return 设置是否MVP加载模式
     */
    boolean isMVP ();
}
