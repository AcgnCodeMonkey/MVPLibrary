package com.xujl.mvpllirary.mvp.view.port;

import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;

/**
 * Created by xujl on 2017/7/10.
 */
public interface IImageListFragmentView extends ICommonView {
    RefreshRecyclerViewHelper getRefreshRecyclerViewHelper();
    void setAdapter(BaseRecyclerViewAdapter adapter);

    void setTitle (int type);
}