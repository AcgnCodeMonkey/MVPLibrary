package com.xujl.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.xujl.applibrary.mvp.port.ICommonView;

/**
 * Created by xujl on 2017/11/9.
 */
public interface IMainActivityView extends ICommonView {
    void initFragment (FragmentManager supportFragmentManager, Fragment... fragments);
    void showFragment(FragmentManager supportFragmentManager,Fragment showFragment,Fragment... fragment);
}