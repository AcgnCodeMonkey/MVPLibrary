package com.xujl.mvpllirary.mvp.model.port;


import com.xujl.applibrary.mvp.port.ICommonModel;

import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * Created by xujl on 2017/9/6.
 */
public interface IHomeNewsFragmentModel extends ICommonModel {
    String[] getTabTitle ();

    List<Fragment> getFragmentList ();

}