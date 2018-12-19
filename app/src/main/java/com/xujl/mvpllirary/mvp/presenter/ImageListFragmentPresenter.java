package com.xujl.mvpllirary.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xujl.applibrary.mvp.presenter.CommonActivityPresenter;
import com.xujl.applibrary.mvp.presenter.CommonFragmentPresenter;
import com.xujl.mvpllirary.adapter.ImageListAdaper;
import com.xujl.mvpllirary.json.ImagePassBean;
import com.xujl.mvpllirary.mvp.model.port.IImageListFragmentModel;
import com.xujl.mvpllirary.mvp.view.port.IImageListFragmentView;
import com.xujl.mvpllirary.util.IntentKey;
import com.xujl.widgetlibrary.adapter.BaseRecyclerViewAdapter;
import com.xujl.widgetlibrary.widget.RefreshLayout;

/**
 * Created by xujl on 2017/7/10.
 * 收藏&已下载
 */
public class ImageListFragmentPresenter extends CommonFragmentPresenter<IImageListFragmentView, IImageListFragmentModel>
        implements RefreshLayout.RefreshListener {
    public static final int REQUEST_CODE = 6;
    private BaseRecyclerViewAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick (BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(IntentKey.IMAGE_ENTITY, mModel.getImagePassBeans());
            bundle.putInt(IntentKey.POSITION,position);
            bundle.putInt(IntentKey.TYPE, mModel.getType());
            startForResult(ShowImageFragmentPresenter.newInstance(bundle),REQUEST_CODE);
        }
    };
    public static ImageListFragmentPresenter newInstance ( Bundle bundle) {

        ImageListFragmentPresenter fragment = new ImageListFragmentPresenter();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initPresenter (Bundle savedInstanceState) {
        mModel.saveType(getArguments());
        mView.setTitle(mModel.getType());
        mAdapter = new ImageListAdaper(mModel.getDataList());
        mView.setAdapter(mAdapter);
        mView.getRefreshRecyclerViewHelper().addOnItemTouchListener(mOnItemClickListener);
        mView.getRefreshRecyclerViewHelper().startRefresh();
    }

    @Override
    public boolean enableToolBar () {
        return true;
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
    public void onFragmentResult (int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == ShowImageFragmentPresenter.RESULT_CODE) {
            mView.getRefreshRecyclerViewHelper().startRefresh();
        }
    }

}