package com.xujl.baselibrary.mvp.port;

/**
 * Created by xujl on 2017/9/18.
 * view和presenter共有方法，为了兼容mvp和mvc开发，此接口包含部分需要两边同时可以
 * 控制界面加载的部分参数
 */

public interface IBaseVP {
    /**
     * @return 页面是否使用导航
     */
    boolean enableToolBar ();

    /**
     * 返回当前布局id
     *
     * @return
     */
    int getLayoutId ();

    /**
     * 此方法用于当前布局自定义了导航时
     *
     * @return 导航布局Id
     */
    int getToolBarId ();

    /**
     * @return 是否为当前布局自动添加一层线性父布局用于统一管理界面
     */
    boolean isAddParentLayout ();

    /**
     * @return 是否使用dataBinding进行布局加载
     */
    boolean enableDataBinding ();

    /**
     * 是否使用加载布局
     */
    boolean enableUseLoadingLayout ();
}
