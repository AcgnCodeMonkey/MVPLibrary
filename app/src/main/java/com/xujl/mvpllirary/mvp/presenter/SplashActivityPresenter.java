package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;

import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.widget.GranzortView;
import com.xujl.rxlibrary.BaseConsumer;
import com.xujl.rxlibrary.RxHelper;

/**
 * Created by xujl on 2017/9/8.
 */

public class SplashActivityPresenter extends CommonActivityPresenter {
    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        final GranzortView granzortView = mView.findView(R.id.activity_splash_granzortView);
        RxHelper.onCreat()
                .createTimer(1500)
                .newThreadToMain()
                .run(new BaseConsumer<Long>() {
                    @Override
                    public void accept (Long o) throws Exception {
                        super.accept(o);
                        granzortView.startAnim();
                    }
                });
        granzortView.setCallback(new GranzortView.Callback() {
            @Override
            public void end () {
                exit();
                gotoActivity(MainActivityPresenter.class);
            }
        });
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_splash;
    }

    @Override
    public boolean isMVP () {
        return false;
    }

    @Override
    public boolean enableToolBar () {
        return false;
    }

    @Override
    protected Class getViewClassType () {
        return null;
    }

    @Override
    protected Class getModelClassType () {
        return null;
    }
}
