package com.xujl.applibrary.mvp.port;

import android.databinding.ViewDataBinding;

/**
 * Created by xujl on 2017/9/12.
 */

public interface IDataBindingPresenter extends ICommonPresenter {
    <D extends ViewDataBinding> D exposeDataBinding();
}
