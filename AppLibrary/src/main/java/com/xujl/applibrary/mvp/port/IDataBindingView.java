package com.xujl.applibrary.mvp.port;

import android.databinding.ViewDataBinding;

/**
 * Created by xujl on 2017/9/12.
 */

public interface IDataBindingView extends ICommonView {
    <D extends ViewDataBinding> D getDataBinding();//通过dataBinding创建视图
}
