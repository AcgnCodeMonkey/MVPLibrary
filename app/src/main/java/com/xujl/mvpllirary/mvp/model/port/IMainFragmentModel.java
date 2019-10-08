package com.xujl.mvpllirary.mvp.model.port;


import com.xujl.applibrary.mvp.port.ICommonModel;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xujl on 2017/7/4.
 */
public interface IMainFragmentModel extends ICommonModel {

    ISupportFragment[] getFragments ();

    void setFragments (SupportFragment supportFragment,boolean isFirstLoad);
}