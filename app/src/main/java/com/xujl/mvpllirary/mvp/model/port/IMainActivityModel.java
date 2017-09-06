package com.xujl.mvpllirary.mvp.model.port;

import android.support.v4.app.Fragment;

import com.xujl.applibrary.mvp.port.ICommonModel;

import java.util.List;

/**
 * Created by xujl on 2017/7/4.
 */
public interface IMainActivityModel extends ICommonModel {

    List<Fragment> getFragmentList ();
}