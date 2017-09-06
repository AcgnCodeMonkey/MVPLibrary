package com.xujl.mvpllirary.mvp.model.port;

import android.support.v4.app.Fragment;

import com.xujl.applibrary.mvp.port.ICommonModel;

import java.util.List;

/**
 * Created by xujl on 2017/9/6.
 */
public interface IHomeNewsFragmentModel extends ICommonModel {
    List<String> getTabTitle ();

    List<Fragment> getFragmentList ();

    String getMaxTitle();
}