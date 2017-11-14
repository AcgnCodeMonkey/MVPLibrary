package com.xujl.myapplication;

import android.support.v4.app.Fragment;

import com.xujl.applibrary.mvp.port.ICommonModel;

/**
 * Created by xujl on 2017/11/9.
 */
public interface IMainActivityModel extends ICommonModel {
    Fragment[] getFragments ();
}