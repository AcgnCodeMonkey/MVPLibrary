package com.xujl.mvpllirary.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.mvpllirary.adapter.ImageListAdaper;
import com.xujl.mvpllirary.json.ImagePassBean;
import com.xujl.mvpllirary.mvp.model.ImageListActivityModel;
import com.xujl.mvpllirary.mvp.model.port.IImageListActivityModel;
import com.xujl.mvpllirary.mvp.view.ImageListActivity;
import com.xujl.mvpllirary.mvp.view.port.IImageListActivityView;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.quotelibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.quotelibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/7/10.
 */
public class ImageListActivityPresenter extends CommonActivityPresenter<IImageListActivityView, IImageListActivityModel>
        implements RefreshLayout.RefreshListener {
    private BaseRecyclerViewAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick (BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(IntentKey.IMAGE_ENTITY, new ImagePassBean(mModel.getDataList().get(position)));
            bundle.putInt(IntentKey.TYPE,mModel.getType());
            gotoActivity(ShowImageActivityPresenter.class, bundle);
        }
    };

    @Override
    protected Class<? extends IImageListActivityView> getViewClassType () {
        return ImageListActivity.class;
    }

    @Override
    protected Class<? extends IImageListActivityModel> getModelClassType () {
        return ImageListActivityModel.class;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mModel.saveType(getIntent());
        mView.setTitle(mModel.getType());
        mAdapter = new ImageListAdaper(mModel.getDataList());
        mView.setAdapter(mAdapter);
        mView.getRefreshRecyclerViewHelper().addOnItemTouchListener(mOnItemClickListener);
        mModel.addData();
        mView.getRefreshRecyclerViewHelper().startRefresh();
    }

    @Override
    public void onRefresh (RefreshLayout refreshLayout) {
        mAdapter.cbNotifyDataSetChanged();
        mView.getRefreshRecyclerViewHelper().refreshLoadingComplete();
    }

    @Override
    public void onLoading (RefreshLayout refreshLayout) {

    }
}