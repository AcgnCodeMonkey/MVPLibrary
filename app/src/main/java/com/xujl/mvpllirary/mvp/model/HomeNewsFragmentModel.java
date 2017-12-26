package com.xujl.mvpllirary.mvp.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.mvpllirary.mvp.model.port.IHomeNewsFragmentModel;
import com.xujl.mvpllirary.mvp.presenter.NewsTabFragmentPresenter;
import com.xujl.mvpllirary.util.IntentKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xujl on 2017/9/6.
 */
public class HomeNewsFragmentModel extends CommonModel implements IHomeNewsFragmentModel {
    private String[] mTabTitles =new String[]{"all", "Android", "iOS", "前端", "休息视频", "拓展资源"};


    @Override
    public String[] getTabTitle () {
        return mTabTitles;
    }

    @Override
    public List<Fragment> getFragmentList () {
        List<Fragment> fragmentList = new ArrayList<>();
        for (String tabTitle : mTabTitles) {
            final Fragment fragment = new NewsTabFragmentPresenter();
            final Bundle bundle = new Bundle();
            bundle.putString(IntentKey.TYPE, tabTitle);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        return fragmentList;
    }

}