package com.xujl.mvpllirary.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.mvpllirary.adapter.ImageListAdaper;
import com.xujl.mvpllirary.json.ImagePassBean;
import com.xujl.mvpllirary.mvp.model.port.IImageListActivityModel;
import com.xujl.mvpllirary.mvp.view.port.IImageListActivityView;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/7/10.
 * 收藏&已下载
 */
public class ImageListActivityPresenter extends CommonActivityPresenter<IImageListActivityView, IImageListActivityModel>
        implements RefreshLayout.RefreshListener {
    public static final int REQUEST_CODE = 6;
    private BaseRecyclerViewAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick (BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(IntentKey.IMAGE_ENTITY, new ImagePassBean(mModel.getDataList().get(position)));
            bundle.putInt(IntentKey.TYPE, mModel.getType());
            gotoActivity(ShowImageActivityPresenter.class, bundle, REQUEST_CODE);
        }
    };


    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mModel.saveType(getIntent());
        mView.setTitle(mModel.getType());
        mAdapter = new ImageListAdaper(mModel.getDataList());
        mView.setAdapter(mAdapter);
        mView.getRefreshRecyclerViewHelper().addOnItemTouchListener(mOnItemClickListener);
        mView.getRefreshRecyclerViewHelper().startRefresh();
    }

    @Override
    public void onRefresh (RefreshLayout refreshLayout) {
        mModel.addData();
        mAdapter.cbNotifyDataSetChanged();
        mView.getRefreshRecyclerViewHelper().refreshLoadingComplete();
    }

    @Override
    public void onLoading (RefreshLayout refreshLayout) {

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ShowImageActivityPresenter.RESULT_CODE) {
            mView.getRefreshRecyclerViewHelper().startRefresh();
        }
    }
}