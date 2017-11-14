package com.xujl.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.rxlibrary.BaseConsumer;
import com.xujl.rxlibrary.RxHelper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xujl on 2017/11/9.
 */
public class MainActivityPresenter extends CommonActivityPresenter<IMainActivityView, IMainActivityModel> {
    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mView.initFragment(getSupportFragmentManager(), mModel.getFragments());
    }

    @Override
    public void onClick (View v) {
        super.onClick(v);
        final Fragment[] fragments = mModel.getFragments();
        switch (v.getId()) {
            case R.id.fg1:
                mView.showFragment(getSupportFragmentManager(), fragments[0], fragments);
                break;
            case R.id.fg2:
                mView.showFragment(getSupportFragmentManager(), fragments[1], fragments);
                break;
            case R.id.sender1:
                RxHelper.onCreate(mRxLife)
                        .createDelay(2500)
                        .newThreadToMain()
                        .run(new BaseConsumer<Object>() {
                            @Override
                            public void accept (Object o) throws Exception {
                                super.accept(o);
                                EventBus.getDefault().post(fragments[0]);
                            }
                        });

                break;
            case R.id.sender2:
                RxHelper.onCreate(mRxLife)
                        .createDelay(2500)
                        .newThreadToMain()
                        .run(new BaseConsumer<Object>() {
                            @Override
                            public void accept (Object o) throws Exception {
                                super.accept(o);
                                EventBus.getDefault().post(fragments[1]);
                            }
                        });
                break;
            default:

                break;

        }
    }

    @Override
    protected Class<? extends IMainActivityView> getViewClassType () {
        return MainActivity.class;
    }

    @Override
    protected Class<? extends IMainActivityModel> getModelClassType () {
        return MainActivityModel.class;
    }
}