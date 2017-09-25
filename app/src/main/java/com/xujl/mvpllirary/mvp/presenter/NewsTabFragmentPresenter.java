package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.adapter.NewsTabAdapter;
import com.xujl.mvpllirary.mvp.model.port.INewsTabFragmentModel;
import com.xujl.mvpllirary.mvp.view.port.INewsTabFragmentView;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/9/6.
 */
public class NewsTabFragmentPresenter extends CommonFragmentPresenter<INewsTabFragmentView, INewsTabFragmentModel>
        implements RefreshLayout.RefreshListener{
    private BaseRecyclerViewAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick (BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putString(IntentKey.URL, mModel.getDataList().get(position).url);
            gotoActivity(NewsDetailActivityPresenter.class, bundle);
        }
    };

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mModel.initType(getArguments());
        mAdapter = new NewsTabAdapter(mModel.getDataList());
        mView.setAdapter(mAdapter);
        mView.getRefreshRecyclerViewHelper().addOnItemTouchListener(mOnItemClickListener);
    }

    @Override
    protected void lazyLoad () {
        mView.getRefreshRecyclerViewHelper().startRefresh();
    }

    @Override
    protected boolean isEveryReload () {
        return true;
    }

    @Override
    public void onRefresh (RefreshLayout refreshLayout) {
        requestForGetNoHint(CommonModel.MODE_1);
    }

    @Override
    public void onLoading (RefreshLayout refreshLayout) {
        requestForGetNoHint(CommonModel.MODE_2);
    }

    @Override
    public void requestSuccess (int mode, String json) {
        mModel.addData(mode, json);
        mAdapter.cbNotifyDataSetChanged();
        mView.getRefreshRecyclerViewHelper().refreshLoadingComplete();
    }
}