package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;

import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.adapter.TabFragmentAdapter;
import com.xujl.mvpllirary.mvp.model.HomeNewsFragmentModel;
import com.xujl.mvpllirary.mvp.model.port.IHomeNewsFragmentModel;
import com.xujl.mvpllirary.mvp.view.HomeNewsFragment;
import com.xujl.mvpllirary.mvp.view.port.IHomeNewsFragmentView;

/**
 * Created by xujl on 2017/9/6.
 * 资讯
 */
public class HomeNewsFragmentPresenter extends CommonFragmentPresenter<IHomeNewsFragmentView, IHomeNewsFragmentModel> {
    private TabFragmentAdapter mAdapter;
    @Override
    protected Class<? extends IHomeNewsFragmentView> getViewClassType () {
        return HomeNewsFragment.class;
    }

    @Override
    protected Class<? extends IHomeNewsFragmentModel> getModelClassType () {
        return HomeNewsFragmentModel.class;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mAdapter = new TabFragmentAdapter(getFragmentManager(), exposeContext()
                , mModel.getTabTitle(), mModel.getFragmentList());
        mView.setViewPagerAdapter(mAdapter,mModel.getMaxTitle());
    }

}