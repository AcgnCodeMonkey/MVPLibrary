package com.xujl.mvpllirary.mvp.view;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.view.port.IMainActivityView;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainActivity extends CommonView implements IMainActivityView {
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
    }

    @Override
    public int getLayoutId () {
        return R.layout.activity_main;
    }
}