package com.xujl.mvpllirary.mvp.view.port;

import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.mvpllirary.adapter.TabAdapter;
import com.xujl.mvpllirary.adapter.TabFragmentAdapter;

/**
 * Created by xujl on 2017/9/6.
 */
public interface IHomeNewsFragmentView extends ICommonView {
    void setViewPagerAdapter (TabAdapter adapter);//设置适配器
    void setPage (int position);//显示指定下标界面
}