package com.xujl.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xujl.applibrary.mvp.model.CommonModel;

/**
 * Created by xujl on 2017/11/9.
 */
public class MainActivityModel extends CommonModel implements IMainActivityModel {
    private Fragment[] mFragments = new Fragment[2];

    public MainActivityModel () {
        final TestFragmentPresenter fragmentPresenter1 = new TestFragmentPresenter();
        final Bundle bundle1 = new Bundle();
        bundle1.putInt("color",R.color.colorAccent);
        bundle1.putString("name","fragment1");
        fragmentPresenter1.setArguments(bundle1);
        final TestFragmentPresenter fragmentPresenter2 = new TestFragmentPresenter();
        final Bundle bundle2 = new Bundle();
        bundle2.putInt("color",R.color.colorPrimary);
        bundle2.putString("name","fragment2");
        fragmentPresenter2.setArguments(bundle2);
        mFragments[0] = fragmentPresenter1;
        mFragments[1] = fragmentPresenter2;
    }

    @Override
    public Fragment[] getFragments () {
        return mFragments;
    }
}