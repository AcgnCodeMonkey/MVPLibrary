package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xujl.applibrary.mvp.model.CommonModel;
import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.adapter.HomeNewsAdapter;
import com.xujl.mvpllirary.mvp.model.HomeNewsFragmentModel;
import com.xujl.mvpllirary.mvp.model.port.IHomeNewsFragmentModel;
import com.xujl.mvpllirary.mvp.view.HomeNewsFragment;
import com.xujl.mvpllirary.mvp.view.port.IHomeNewsFragmentView;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/9/6.
 * 资讯
 */
public class HomeNewsFragmentPresenter extends CommonFragmentPresenter<IHomeNewsFragmentView, IHomeNewsFragmentModel>
        implements RefreshLayout.RefreshListener {
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
    protected Class<? extends IHomeNewsFragmentView> getViewClassType () {
        return HomeNewsFragment.class;
    }

    @Override
    protected Class<? extends IHomeNewsFragmentModel> getModelClassType () {
        return HomeNewsFragmentModel.class;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mAdapter = new HomeNewsAdapter(mModel.getDataList());
        mView.setAdapter(mAdapter);
        mView.getRefreshRecyclerViewHelper().addOnItemTouchListener(mOnItemClickListener);
        mView.getRefreshRecyclerViewHelper().startRefresh();
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