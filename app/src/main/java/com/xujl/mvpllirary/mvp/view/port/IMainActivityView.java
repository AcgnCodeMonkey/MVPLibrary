package com.xujl.mvpllirary.mvp.view.port;

import android.content.Context;

import com.xujl.applibrary.mvp.port.ICommonView;
import com.xujl.mvpllirary.mvp.common.RefreshRecyclerViewHelper;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;

import java.util.List;

/**
 * Created by xujl on 2017/7/4.
 */
public interface IMainActivityView extends ICommonView {
    RefreshRecyclerViewHelper getRefreshRecyclerViewHelper();
    void setAdapter(BaseRecyclerViewAdapter adapter);
    void setTitles(List<String> titles,Context context);
    void bannerPause();
    void bannerPlay();
    void setBannerData(List<Integer> list);
}