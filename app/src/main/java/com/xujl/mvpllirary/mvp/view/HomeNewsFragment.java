package com.xujl.mvpllirary.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.adapter.TabFragmentAdapter;
import com.xujl.mvpllirary.mvp.view.port.IHomeNewsFragmentView;
import com.xujl.widgetlibrary.widget.ViewPagerTabView;

import java.util.ArrayList;

/**
 * Created by xujl on 2017/9/6.
 */
public class HomeNewsFragment extends CommonView implements IHomeNewsFragmentView {
    private ViewPager mViewPager;
    private ViewPagerTabView mPagerTabView;
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mViewPager = (ViewPager) findView(R.id.fragment_home_news_viewPager);
        mPagerTabView = (ViewPagerTabView) findView(R.id.fragment_home_news_viewPagerTab);
        mPagerTabView.setIndicatorViewPager(mViewPager);
        mPagerTabView.setAdapter(new TabFragmentAdapter(((Fragment) presenter).getFragmentManager(),
                presenter.exposeContext(), new ArrayList<String>(), new ArrayList<Fragment>()));
        mViewPager.setOffscreenPageLimit(6);
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_home_news;
    }


    @Override
    public void setViewPagerAdapter (TabFragmentAdapter adapter, String maxTitle) {
        TabFragmentAdapter.initTitleView(mPagerTabView,mRootView.getContext(),maxTitle);
        mPagerTabView.setAdapter(adapter);
    }

    @Override
    public void setPage (int position) {
        mPagerTabView.setCurrentItem(position, true);
        mViewPager.setCurrentItem(position);
    }
}