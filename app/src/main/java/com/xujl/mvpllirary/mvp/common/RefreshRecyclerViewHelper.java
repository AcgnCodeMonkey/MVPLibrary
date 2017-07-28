package com.xujl.mvpllirary.mvp.common;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.xujl.baselibrary.mvp.common.BaseHelper;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.quotelibrary.widget.RefreshLayout;
import com.xujl.utilslibrary.system.Log;


/**
 * Created by xujl on 2017/6/14.
 */

public class RefreshRecyclerViewHelper extends BaseHelper {
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    public RefreshRecyclerViewHelper (View view, int layoutId, int recyclerViewId) {
        mRefreshLayout = (RefreshLayout) view.findViewById(layoutId);
        mRecyclerView = (RecyclerView) view.findViewById(recyclerViewId);
    }


    public RefreshRecyclerViewHelper setGridLayoutManager (int count) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mRecyclerView.getContext(), count);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        return this;
    }

    public RefreshRecyclerViewHelper setAdapter (BaseRecyclerViewAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
        return this;
    }

    public RefreshRecyclerViewHelper addOnItemTouchListener (RecyclerView.OnItemTouchListener listener) {
        mRecyclerView.addOnItemTouchListener(listener);
        return this;
    }
    //====================刷新布局方法===============================================================================

    /**
     * 默认不使用上拉加载
     */
    public RefreshRecyclerViewHelper initRefreshLayout () {
        if (mRefreshLayout == null) {
            Log.e("RefreshStyleViewHelper->", "RefreshLayout引用为空");
            return this;
        }
        //设置 Header 为 Material风格
//        mRefreshLayout.setRefreshHeader(new MaterialHeader(mRefreshLayout.getContext()).setShowBezierWave(true));
        mRefreshLayout.setRefreshHeader(new WaveSwipeHeader(mRefreshLayout.getContext()));

        //设置 Footer 为 球脉冲
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(mRefreshLayout.getContext()).setSpinnerStyle(SpinnerStyle.Scale));
//        final int themeColor = ContextCompat.getColor(mRefreshLayout.getContext(), R.color.colorPrimary);
//        ProgressLayout headerView = new ProgressLayout(mRefreshLayout.getContext());
//        headerView.setColorSchemeColors(themeColor);
//        mRefreshLayout.setHeaderView(headerView);//设置加载样式
//        final BallPulseView bottomView = new BallPulseView(mRefreshLayout.getContext());
//        bottomView.setAnimatingColor(themeColor);
//        mRefreshLayout.setBottomView(bottomView);
//        enableLoading(false);
//        mRefreshLayout.setFloatRefresh(true);//是否使用悬浮加载样式（无侵入式）
        return this;
    }

    public RefreshRecyclerViewHelper setOnRefreshListener (RefreshLayout.RefreshListener listener) {
        mRefreshLayout.setOnRefreshListener(listener);
        return this;
    }

    public RefreshRecyclerViewHelper startRefresh () {
        if (mRefreshLayout == null) {
            Log.e("RefreshStyleViewHelper->", "RefreshLayout引用为空");
            return this;
        }
        mRefreshLayout.startRefresh();
        return this;
    }

    public RefreshRecyclerViewHelper startLoadMore () {
        if (mRefreshLayout == null) {
            Log.e("RefreshStyleViewHelper->", "RefreshLayout引用为空");
            return this;
        }
        mRefreshLayout.startLoadMore();
        return this;
    }
    public void finishRefreshing(){
        mRefreshLayout.finishRefreshing();
    }
    public void refreshLoadingComplete () {
        if (mRefreshLayout == null) {
            Log.e("RefreshStyleViewHelper->", "RefreshLayout引用为空");
            return;
        }
        mRefreshLayout.finishRefreshing();
        mRefreshLayout.finishLoadmore();
    }

    public RefreshRecyclerViewHelper enableLoading (boolean enable) {
        if (mRefreshLayout == null) {
            Log.e("RefreshStyleViewHelper->", "RefreshLayout引用为空");
            return this;
        }
        mRefreshLayout.setEnableLoadmore(enable);
        return this;
    }

    public RefreshRecyclerViewHelper enableRefresh (boolean enable) {
        if (mRefreshLayout == null) {
            Log.e("RefreshStyleViewHelper->", "RefreshLayout引用为空");
            return this;
        }
        mRefreshLayout.setEnableRefresh(enable);
        return this;
    }
}
