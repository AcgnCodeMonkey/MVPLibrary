package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.widget.GranzortView;
import com.xujl.task.RxExecutor;
import com.xujl.task.RxHelper;

/**
 * Created by xujl on 2017/9/8.
 */

public class SplashFragmentPresenter extends CommonFragmentPresenter {

    public static SplashFragmentPresenter newInstance () {
        Bundle args = new Bundle();
        SplashFragmentPresenter fragment = new SplashFragmentPresenter();
        fragment.setArguments(args);
        return fragment;
    }

    private boolean isCompleted = false;

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        // 初始化
        final GranzortView granzortView = mView.findView(R.id.activity_splash_granzortView);
        RxExecutor.getInstance()
                .executeTask(new RxHelper.CountDownTask(1500, 1500) {
                    @Override
                    public void count (long time) {
                        granzortView.startAnim();
                    }
                });
        granzortView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                isCompleted = true;
                //使用路由跳转
//                Router.build(RouterConst.MAIN).go(exposeContext());
                SplashFragmentPresenter.this.startWithPop(MainFragmentPresenter.newInstance());
            }
        });
        granzortView.setCallback(new GranzortView.Callback() {
            @Override
            public void end () {
//                exit();
                if (!isCompleted) {
                    SplashFragmentPresenter.this.startWithPop(MainFragmentPresenter.newInstance());
                }
                //使用路由跳转
//                Router.build(RouterConst.MAIN).go(exposeContext());
//                gotoActivity(TestActivity.class);
            }
        });

    }


    @Override
    public int getLayoutId () {
        return R.layout.fragment_splash;
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
    public boolean enableUseLoadingLayout () {
        return false;
    }
}
