package com.xujl.applibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

import static android.R.id.list;

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
	public Fragment getItem(int arg0) {

		return mFragmentList.get(arg0);
	}

	@Override
	public int getItemPosition(Object object) {
		return PagerAdapter.POSITION_NONE;
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}

}
