package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.widget.GranzortView;
import com.xujl.rxlibrary.BaseConsumer;
import com.xujl.rxlibrary.RxHelper;

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

    @Override
    protected void initPresenter (Bundle savedInstanceState) {

        // 初始化
        final GranzortView granzortView = mView.findView(R.id.activity_splash_granzortView);
        RxHelper.onCreate()
                .createDelay(1500)
                .newThreadToMain()
                .run(new BaseConsumer<Long>() {
                    @Override
                    public void accept (Long o) throws Exception {
                        super.accept(o);
                        granzortView.startAnim();
                    }
                });
        granzortView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                //使用路由跳转
//                Router.build(RouterConst.MAIN).go(exposeContext());

                SplashFragmentPresenter.this.start(MainFragmentPresenter.newInstance());
            }
        });
        granzortView.setCallback(new GranzortView.Callback() {
            @Override
            public void end () {
//                exit();
//                //使用路由跳转
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
