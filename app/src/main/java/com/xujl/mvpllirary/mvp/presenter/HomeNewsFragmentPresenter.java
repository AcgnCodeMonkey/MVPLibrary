package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;

import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.adapter.TabFragmentAdapter;
import com.xujl.mvpllirary.mvp.model.port.IHomeNewsFragmentModel;
import com.xujl.mvpllirary.mvp.view.port.IHomeNewsFragmentView;

/**
 * Created by xujl on 2017/9/6.
 * 资讯
 */
public class HomeNewsFragmentPresenter extends CommonFragmentPresenter<IHomeNewsFragmentView, IHomeNewsFragmentModel> {
    private TabFragmentAdapter mAdapter;

    @Override
    protected void initPresenter (Bundle savedInstanceState) {

    }

    @Override
    protected void lazyLoad () {
        super.lazyLoad();
        mAdapter = new TabFragmentAdapter(getFragmentManager(), exposeContext()
                , mModel.getTabTitle(), mModel.getFragmentList());
        mView.setViewPagerAdapter(mAdapter,mModel.getMaxTitle());
    }
}