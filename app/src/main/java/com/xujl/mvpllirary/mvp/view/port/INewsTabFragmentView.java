package com.xujl.mvpllirary.mvp.view.port;

import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;

/**
 * Created by xujl on 2017/9/6.
 */
public interface INewsTabFragmentView extends ICommonView {
    RefreshRecyclerViewHelper getRefreshRecyclerViewHelper();
    void setAdapter(BaseRecyclerViewAdapter adapter);
}