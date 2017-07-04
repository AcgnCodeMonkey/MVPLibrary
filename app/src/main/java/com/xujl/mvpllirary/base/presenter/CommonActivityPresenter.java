package com.xujl.mvpllirary.base.presenter;

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
import com.xujl.baselibrary.mvp.presenter.BaseActivityPresenter;
import com.xujl.mvpllirary.mvp.common.ToolBarModule;

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
    protected void autoCreatViewModel () {
        mView = (V) new CommonView(){

            @Override
            public int getLayoutId () {
                return CommonActivityPresenter.this.getLayoutId();
            }
        };
        mModel = (M) new CommonModel() {
        };
    }
    /**
     * 关闭MVP模式时应复写此方法
     * @return
     */
    protected int getLayoutId(){
        return 0;
    }

    @Override
    public void onClick (View v) {

    }

    @Override
    public ToolBarModule getToolBarModule () {
        return (ToolBarModule) super.getToolBarModule();
    }

    @Override
    public ToolBarModule setDefaultToolBarHelper () {
        if(isMVP()){
            return new ToolBarModule(this,mView.getLayoutId());
        }
        return new ToolBarModule(this,getLayoutId());
    }
}
