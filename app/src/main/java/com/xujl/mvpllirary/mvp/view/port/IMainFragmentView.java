package com.xujl.mvpllirary.mvp.view.port;

import com.xujl.applibrary.mvp.port.ICommonView;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xujl on 2017/7/4.
 */
public interface IMainFragmentView extends ICommonView {
    void setCurrentItem (SupportFragment supportFragment, ISupportFragment[] fragments, int position, String title);//改变标题和页面

    void loadMultipleRootFragment (SupportFragment supportFragment, ISupportFragment[] fragments);
}