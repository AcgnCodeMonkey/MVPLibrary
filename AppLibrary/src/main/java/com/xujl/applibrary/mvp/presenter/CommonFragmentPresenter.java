package com.xujl.applibrary.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xujl.applibrary.mvp.common.CommonPresenterHelper;
import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.applibrary.mvp.port.ICommonModel;
import com.xujl.applibrary.mvp.port.ICommonPresenter;
import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.presenter.BaseFragmentPresenter;

/**
 * Created by xujl on 2017/7/4.
 */

public abstract class CommonFragmentPresenter<V extends ICommonView, M extends ICommonModel>
        extends BaseFragmentPresenter<V, M> implements ICommonPresenter {
    @Override
    public void exit () {
        getPresenterHelper().exit(exposeActivity());
    }

    @Override
    public <S extends Activity> void gotoActivity (Class<S> cls, Bundle bundle) {
        getPresenterHelper().gotoActivity(exposeActivity(), cls, bundle);
    }

    @Override
    public <S extends Activity> void gotoActivity (Class<S> cls, Bundle bundle, int requestCode) {
        getPresenterHelper().gotoActivity(exposeActivity(), cls, bundle, requestCode);
    }

    @Override
    public <S extends Activity> void gotoActivity (Class<S> cls) {
        getPresenterHelper().gotoActivity(exposeActivity(), cls);
    }

    @Override
    public <S extends Activity> void gotoActivity (Class<S> cls, int requestCode) {
        getPresenterHelper().gotoActivity(exposeActivity(), cls, requestCode);
    }

    @Override
    public void gotoActivity (Intent intent, int requestCode) {
        getPresenterHelper().gotoActivity(exposeActivity(), intent, requestCode);
    }

    @Override
    public void gotoActivity (Intent intent) {
        getPresenterHelper().gotoActivity(exposeActivity(), intent);
    }

    @Override
    public void backForResult (Bundle bundle, int result) {
        getPresenterHelper().backForResult(exposeActivity(), bundle, result);
    }

    @Override
    public void backForResult (int result) {
        getPresenterHelper().backForResult(exposeActivity(), result);
    }

    @Override
    public void backForResult (Intent intent, int result) {
        getPresenterHelper().backForResult(exposeActivity(), intent, result);
    }

    @Override
    protected CommonPresenterHelper getPresenterHelper () {
        if (!(super.getPresenterHelper() instanceof CommonPresenterHelper)) {
            setPresenterHelper(new CommonPresenterHelper());
        }
        return (CommonPresenterHelper) super.getPresenterHelper();
    }


    @Override
    protected void autoCreatViewModel () {
        mView = (V) new CommonView(){

            @Override
            public int getLayoutId () {
                return CommonFragmentPresenter.this.getLayoutId();
            }
        };
        mModel = (M) new CommonModel() {
        };
    }
    /**
     * 关闭MVP模式时应复写此方法
     * @return
     */
    public int getLayoutId(){
        return 0;
    }


    @Override
    public void onClick (View v) {

    }
}
