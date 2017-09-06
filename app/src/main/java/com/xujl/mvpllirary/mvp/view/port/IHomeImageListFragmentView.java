package com.xujl.mvpllirary.mvp.view.port;

import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by xujl on 2017/9/5.
 */
public interface IHomeImageListFragmentView extends ICommonView {
    RefreshRecyclerViewHelper getRefreshRecyclerViewHelper();
    void setAdapter(BaseRecyclerViewAdapter adapter);
    void bannerPause();
    void bannerPlay();
    void setBannerData(List<Integer> list);
}