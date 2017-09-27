package com.xujl.mvpllirary.mvp.common;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.xujl.baselibrary.mvp.common.BaseHelper;
import com.xujl.mvpllirary.R;
import com.xujl.utilslibrary.system.Log;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;


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
    public RefreshRecyclerViewHelper setLinearLayoutManager () {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
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
        final ClassicsHeader classicsHeader = new ClassicsHeader(mRefreshLayout.getContext())
                .setSpinnerStyle(SpinnerStyle.Translate);
        classicsHeader.setAccentColor(ContextCompat.getColor(mRefreshLayout.getContext(), R.color.white));
        mRefreshLayout.setRefreshHeader(classicsHeader);
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mRefreshLayout.getContext()));
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
