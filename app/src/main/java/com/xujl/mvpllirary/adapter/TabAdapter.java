package com.xujl.mvpllirary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xujl on 2017/12/25.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private List<Fragment> fragments;

    public TabAdapter (FragmentManager fm, List<Fragment> fragments, String... titles) {
        super(fm);
        mTitles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem (int position) {
       return fragments.get(position);
    }

    @Override
    public int getCount () {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle (int position) {
        return mTitles[position];
    }
}
