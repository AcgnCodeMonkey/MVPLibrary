package com.xujl.quotelibrary.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xujl.quotelibrary.R;
import com.xujl.utilslibrary.data.ListUtils;

import java.util.List;

/**
 * Created by xujl on 2017/6/14.
 */

public abstract class BaseRecyclerViewAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    protected View mFooter;
    private ViewGroup mRecyclerViewParentLayout;

    public BaseRecyclerViewAdapter (int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public BaseRecyclerViewAdapter (List<T> data) {
        super(data);
    }

    public BaseRecyclerViewAdapter (int layoutResId) {
        super(layoutResId);
    }

    public void showFooter (boolean isShow) {
        if (mFooter == null ) {
            return;
        }
        try {
            getFooterLayout().getChildAt(0).setVisibility(isShow ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("BaseRecyclerViewAdapter","footer显示异常");
        }
    }

    public void initFooter (Context context) {
        mFooter = LayoutInflater.from(context).inflate(R.layout.include_listview_null_layout, null);
        mFooter.setVisibility(View.VISIBLE);
        addFooterView(mFooter);
    }


    public void setAnimAndEmptyParentView ( ViewGroup view) {
        mRecyclerViewParentLayout = view;
        openLoadAnimation(com.chad.library.adapter.base.BaseQuickAdapter.SCALEIN);//设置缩放动画
    }

    public void cbNotifyDataSetChanged () {
        if (ListUtils.isEmpty(mData) && getEmptyViewCount() == 0) {
            setEmptyView(LayoutInflater.from(mRecyclerViewParentLayout.getContext()).inflate(R.layout.include_listview_empty_layout, mRecyclerViewParentLayout, false));
        }
        showEmptyView(ListUtils.isEmpty(mData));
        notifyDataSetChanged();
    }

    public void cbNotifyDataSetChanged (int count, int totalCount, boolean showLastView) {
        notifyDataSetChanged();
        if (ListUtils.isEmpty(mData)) {
            if (getEmptyViewCount() == 0) {
                setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.include_listview_empty_layout, mRecyclerViewParentLayout, false));
            }
            showEmptyView(true);
            return;
        }
        final boolean isShowLastView = (count >= totalCount)&&showLastView;
        showFooter(isShowLastView);
    }
    public void showEmptyView (boolean isShow) {
        final View emptyView = getEmptyView();
        if (emptyView != null) {
            emptyView.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }
}
