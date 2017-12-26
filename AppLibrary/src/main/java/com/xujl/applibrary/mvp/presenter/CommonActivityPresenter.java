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
import com.xujl.applibrary.util.AppApplication;
import com.xujl.applibrary.util.CustomToast;
import com.xujl.baselibrary.mvp.port.IBaseModel;
import com.xujl.baselibrary.mvp.port.IBaseView;
import com.xujl.baselibrary.mvp.presenter.BaseActivityPresenter;
import com.xujl.rxlibrary.RxLife;
import com.xujl.utilslibrary.data.ParamsMapTool;

/**
 * Created by xujl on 2017/7/4.
 */

public abstract class CommonActivityPresenter<V extends ICommonView, M extends ICommonModel>
        extends BaseActivityPresenter<V, M> implements ICommonPresenter {
    @Override
    public <S extends Activity> void gotoActivity (Class<S> cls, Bundle bundle) {
        getPresenterHelper().gotoActivity(this, cls, bundle);
    }

    @Override
    public <S extends Activity> void gotoActivity (Class<S> cls, Bundle bundle, int requestCode) {
        getPresenterHelper().gotoActivity(this, cls, bundle, requestCode);
    }

    @Override
    public void gotoActivity (Intent intent, int requestCode) {
        getPresenterHelper().gotoActivity(this, intent, requestCode);
    }

    @Override
    public void gotoActivity (Intent intent) {
        getPresenterHelper().gotoActivity(this, intent);
    }

    @Override
    public <S extends Activity> void gotoActivity (Class<S> cls) {
        getPresenterHelper().gotoActivity(this, cls);
    }

    @Override
    public <S extends Activity> void gotoActivity (Class<S> cls, int requestCode) {
        getPresenterHelper().gotoActivity(this, cls, requestCode);
    }

    @Override
    public void backForResult (Bundle bundle, int result) {
        getPresenterHelper().backForResult(this, bundle, result);
    }

    @Override
    public void backForResult (Intent intent, int result) {
        getPresenterHelper().backForResult(this, intent, result);
    }

    @Override
    public void backForResult (int result) {
        getPresenterHelper().backForResult(this, result);
    }

    @Override
    public void exit () {
        getPresenterHelper().exit(this);
    }

    @Override
    protected CommonPresenterHelper getPresenterHelper () {
        if (!(super.getPresenterHelper() instanceof CommonPresenterHelper)) {
            setPresenterHelper(new CommonPresenterHelper());
        }
        return (CommonPresenterHelper) super.getPresenterHelper();
    }

    @Override
    protected IBaseModel autoCreateModel () {
        return  new CommonModel() {
        };
    }

    @Override
    protected IBaseView autoCreateView () {
        return new CommonView() {

            @Override
            public int getLayoutId () {
                return 0;
            }

            @Override
            public int getToolBarId () {
                return 0;
            }

        };
    }

    /**
     * 关闭MVP模式时应复写此方法
     *
     * @return
     */
    @Override
    public int getLayoutId () {
        return 0;
    }

    @Override
    public void onClick (View v) {

    }

    protected void requestForGet (int mode) {
        requestForGet(mode, null);
    }

    protected void requestForGet (int mode, ParamsMapTool paramsMapTool) {
        getPresenterHelper().requestForGet(mode, paramsMapTool, true, mModel, mView, this);
    }

    protected void requestForGetNoHint (int mode) {
        requestForGetNoHint(mode, null);
    }

    protected void requestForGetNoHint (int mode, ParamsMapTool paramsMapTool) {
        getPresenterHelper().requestForGet(mode, paramsMapTool, false, mModel, mView, this);
    }

    @Override
    public void requestSuccess (int mode, String json) {

    }

    @Override
    public void requestFailed (int mode, int errorCode, String errorMsg, String json) {
        mView.showToastMsg(exposeContext(), "请求失败", CustomToast.ERROR);
    }

    @Override
    protected void onDestroy () {
        mRxLife.destroyAll();
        super.onDestroy();
    }
    @Override
    protected String getViewClassPackageName () {
        return AppApplication.getInstance().getViewPackageName();
    }

    @Override
    protected String getModelClassPackageName () {
        return AppApplication.getInstance().getModelPackageName();
    }

    @Override
    public RxLife getRxLife () {
        return mRxLife;
    }
}
