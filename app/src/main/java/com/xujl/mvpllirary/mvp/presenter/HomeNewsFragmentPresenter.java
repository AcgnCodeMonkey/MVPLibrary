package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.adapter.TabAdapter;
import com.xujl.mvpllirary.mvp.model.port.IHomeNewsFragmentModel;
import com.xujl.mvpllirary.mvp.view.port.IHomeNewsFragmentView;

/**
 * Created by xujl on 2017/9/6.
 * 资讯
 */
public class HomeNewsFragmentPresenter extends CommonFragmentPresenter<IHomeNewsFragmentView, IHomeNewsFragmentModel> {
    private TabAdapter mAdapter;

    public static HomeNewsFragmentPresenter newInstance () {

        Bundle args = new Bundle();

        HomeNewsFragmentPresenter fragment = new HomeNewsFragmentPresenter();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {

    }


    @Override
    public void onLazyInitView (@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mAdapter = new TabAdapter(getFragmentManager()
                , mModel.getFragmentList(), mModel.getTabTitle());
        mView.setViewPagerAdapter(mAdapter);
    }
}