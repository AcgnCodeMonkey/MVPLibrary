package com.xujl.mvpllirary.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.adapter.TabAdapter;
import com.xujl.mvpllirary.adapter.TabFragmentAdapter;
import com.xujl.mvpllirary.mvp.view.port.IHomeNewsFragmentView;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;

/**
 * Created by xujl on 2017/9/6.
 */
public class HomeNewsFragment extends CommonView implements IHomeNewsFragmentView {
    private ViewPager mViewPager;
    private TabLayout mTab;
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mViewPager = (ViewPager) findView(R.id.fragment_home_news_viewPager);
        mTab = findView(R.id.tab);
        mTab.setupWithViewPager(mViewPager);
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
    }

    @Override
    public int getLayoutId () {
        return R.layout.fragment_home_news;
    }


    @Override
    public void setViewPagerAdapter (TabAdapter adapter) {
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void setPage (int position) {
        mViewPager.setCurrentItem(position);
    }
}