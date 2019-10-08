package com.xujl.applibrary.adapter;


import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * 一个简单的viewpager+fragment适配器
 */
public class SimpleViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList;

    public SimpleViewPagerAdapter (FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mFragmentList = list;
    }

    @Override
    public Fragment getItem (int arg0) {

        return mFragmentList.get(arg0);
    }

//    @Override
//    public int getItemPosition (Object object) {
//        return PagerAdapter.POSITION_NONE;
//    }

    @Override
    public int getCount () {
        return mFragmentList.size();
    }

}
