package com.xujl.mvpllirary.mvp.view;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.presenter.SplashFragmentPresenter;
import com.xujl.mvpllirary.mvp.view.port.ISingleActivityView;

import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by xujl on 2017/12/25.
 */
public class SingleActivity extends CommonView implements ISingleActivityView {
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        SupportActivity supportActivity = (SupportActivity) presenter;
        if (supportActivity.findFragment(SplashFragmentPresenter.class) == null) {
            supportActivity.loadRootFragment(R.id.root_fl, SplashFragmentPresenter.newInstance());
        }
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_single;
    }
}