package com.xujl.applibrary.mvp.common;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.xujl.applibrary.R;
import com.xujl.applibrary.mvp.view.DataBindingView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;

/**
 * Created by xujl on 2017/9/12.
 */

public class DataBindingViewHelper extends CommonViewHelper {
    protected ViewDataBinding mDataBinding;
    protected DataBindingView mBindingView;
    public DataBindingViewHelper (IBasePresenter presenter,DataBindingView bindingView) {
        super(presenter);
        mBindingView = bindingView;
    }
    public View inflateLayout (Activity activity,int layoutId){

        mDataBinding = DataBindingUtil.setContentView(activity,layoutId);
        mParentView = activity.findViewById(R.id.dataBindingRootView);
        return mParentView;
    }

    public <D> D getDataBinding () {
        if(mDataBinding == null){
            mDataBinding = mBindingView.getToolBarModule().getDataBinding();
        }
        return (D) mDataBinding;
    }
}
