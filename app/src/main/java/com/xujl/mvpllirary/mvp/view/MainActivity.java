package com.xujl.mvpllirary.mvp.view;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.RadioGroup;

import com.xujl.applibrary.mvp.view.CommonView;
import com.xujl.baselibrary.mvp.port.IBasePresenter;
import com.xujl.mvpllirary.R;
import com.xujl.mvpllirary.mvp.view.port.IMainActivityView;

/**
 * Created by xujl on 2017/7/4.
 */
public class MainActivity extends CommonView implements IMainActivityView {
    private ViewPager mViewPager;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void initView (final IBasePresenter presenter) {
        super.initView(presenter);
        getToolBarModule().setTitle("妹纸");
        mDrawerLayout = findView(R.id.activity_main_drawerLayout);
        mViewPager = findView(R.id.activity_main_viewPager);
        getToolBarModule().getActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getToolBarModule().getActionBar().setDisplayHomeAsUpEnabled(true);
        getToolBarModule().getRightIB().setImageResource(R.drawable.qr_code_icon);
        getToolBarModule().getRightIB().setOnClickListener(presenter);
        mDrawerToggle = new ActionBarDrawerToggle(presenter.exposeActivity(), mDrawerLayout, getToolBarModule().getToolbar(), R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened (View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed (View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mRootView.findViewById(R.id.part_activity_main_menu_downloadTV).setOnClickListener(presenter);
        mRootView.findViewById(R.id.part_activity_main_menu_collectionTV).setOnClickListener(presenter);
        mRootView.findViewById(R.id.part_activity_main_menu_personTV).setOnClickListener(presenter);
        RadioGroup radioGroup = (RadioGroup) mRootView.findViewById(R.id.activity_main_radioGroup);
        radioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) presenter);
    }


    @Override
    public int getLayoutId () {
        return R.layout.activity_main;
    }

    @Override
    public void setAdapter (FragmentStatePagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
//        mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void setCurrentItem (int position, String title) {
        getToolBarModule().setTitle(title);
        mViewPager.setCurrentItem(position);
    }


}